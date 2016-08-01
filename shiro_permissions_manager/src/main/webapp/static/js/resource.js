/*
 * 资源管理 Script 
 */

$(document).ready(function(){
	var $grid = null, $form = null;
	// 避免重复
	APP.w2ui_clean('regrid','reform');
	
	$grid = $('#regrid').w2grid({
		header: '资源列表',
		name: 'regrid',
		url: APP.PATH + '/resource',
		method: 'POST',
		show: {
            header         : true,
            toolbar     : true,
            lineNumbers    : true,
        },
        columns: [                
	          { field: 'reid', caption: '编号', size: '6%' },
	          { field: 'name', caption: '名称', size: '10%' },
	          { field: 'type', caption: '类型', size: '6%', items:{"1":"按钮","0":"菜单"}, render: function(record, index, col_index){
	          		var value = this.getCellValue(index, col_index);
	          		return this.columns[col_index].items[value] || "未知";
	          	} 
	          },
	          { field: 'permissions', caption: '权限', size:'20%'},
	          { field: 'url', caption: '路径', size:'20%'},
	          { field: 'pid', caption: '上级', size:'10%', render: function(record, index, col_index){
	        	  var value = this.getCellValue(index, col_index), records = this.records;
	        	  for (var i=0,len = records.length; i<len; i++) {
	        		  if (records[i]['reid'] == record['pid']) return records[i]['name'];
	        	  }
	        	  return value || '';
	          }},
	          { field: 'sorted', caption: '排序', size:'10%'},
	      ],
	      toolbar: {
	          items : [
			      { type: 'button', id: 'create', caption: '添加资源', icon: 'w2ui-icon-plus' },
				  { type: 'button', id: 'edit', caption: '编辑资源', icon: 'w2ui-icon-pencil', disabled: 'disabled' },   
				  { type: 'button', id: 'delete', caption: '删除资源', icon: 'w2ui-icon-cross', disabled: 'disabled' },   
	          ]
	      }
	});
	// 隐藏多余按钮
	$grid.toolbar.hide('w2ui-column-on-off','w2ui-search');
	// 按钮可用性
	$grid.on('select', function(event){
		this.toolbar.enable('edit','delete');
	});
	$grid.on('unselect', function(event){
		this.toolbar.disable('edit','delete');
	});
	// 准备父级点数据
	$grid.on('load', function(event){
		event.onComplete = function(event){
			var records = this.records;
			if (records.length) {
				delete this['_parents'];
				this['_parents'] = [];
				// 一级菜单下才允许节点
				for (var i=0,len=records.length; i<len; i++) {
					records[i].pid < 2 && this['_parents'].push({
						id: records[i].reid,
						text: records[i].name
					});
				}
			}
		};
	});
	
	$grid.toolbar.on('click', function(event){
		var target = event.target, $grid = this.owner;
		if (target == 'create') {
			create_resource($grid, $form);
		} else if (target == 'edit') {
			edit_resource($grid, $form);
		} else if (target == 'delete') {
			var sel = $grid.getSelection();
			if (sel.length) {
				var rcd = $grid.get(sel[0]);
				$.get(APP.PATH + '/resource/' + rcd.reid + '/delete', function(data){
					if (data && data.success) {
						$grid.reload();
					}
					data && data.message && w2alert(data.message);
				},'json');
			}
		}
		$grid.selectNone();
	});
	
	$form = $('#reform').w2form({
		header: '资源信息',
		name: 'reform',
	    fields: [
            { field: 'reid',type: 'int'},
            { field: 'name',type: 'text', required: true },
            { field: 'type',type: 'select', required: true, options: { items: [{id:'0',text:'菜单'}, {id:1,text:'按钮'}] }},
            { field: 'permissions',type: 'text'},
            { field: 'url',type: 'text'},
            { field: 'pid',type: 'select', required: true},
            { field: 'sorted',type: 'text'},
        ],
        actions: {
            reset: function (e) {
                this.clear();
            },
            save: function (e) {
                var ret = this.validate(), op = this.record['op'];
                if (ret.length) return;
                if (op == 'create') {
                	form_submit(this, APP.PATH + '/resource/create');
                	return;
                }
                // 修改
            	var isChange = function(a, b) {
            		var vs = ['name','type','permissions','url','pid','sorted'];
            		for (var i=0,len=vs.length; i<len; i++) {
            			if (a[vs[i]] != b[vs[i]]) return true;
            		}
            		return false;
            	}($form['_origin'] || {}, $form.record);
            	if (isChange) {
            		form_submit(this, APP.PATH + '/resource/update');
                	return;
            	}
            	w2alert('没有变动，无需保存');
            }
        }
	});
	
	// 绑定 grid
	$form._$grid = $grid;
});

function create_resource($grid, $form) {
	// 清空表单
	$form.clear();
	// - 准备数据
	$form.record['op'] = 'create';
	$form.record['type'] = 0;
	$form.record['pid'] = 0;
	$form.record['reid'] = 0;
	$form.get('pid').options['items'] = $grid['_parents'] || [];
	$form.refresh();
}

function edit_resource($grid, $form) {
	var sel = $grid.getSelection();
	if (!sel.length) return;
	// 原始数据
	var record = $grid.get(sel[0]) || {};
	if (record['reid'] == 1){
		w2alert('根节点菜单不能修改');
		return;
	}
	// 清空表单
	$form.clear();
	// - 准备数据
	$form.record['op'] = 'edit';
	// 父节点
	$form.get('pid').options['items'] = function(parents, self){
		// 排除自身
		var ret = [];
		for (var i=0,len=parents.length; i<len; i++) {
			if (parents[i]['id'] != self) ret.push(parents[i]);
		}
		return ret;
	}($grid['_parents'] || [], record['reid']);
	$.extend(true, $form.record, record);
	$form.refresh();
	$form['_origin'] = $.extend(true, {}, record);
}

function form_submit($form, url) {
	$form.lock('正在处理...',true);
	//delete $form.record['op'];
	$.post(url, APP.form_param($form.record, 'resource.'),function(data){
		if (data && data.success) 
			w2alert(data.message, '处理结果', function(){
				$form.clear();
				$form._$grid && $form._$grid.reload();
			});
		else {
			data && data.message && w2alert(data.message);
		}
		$form.unlock();
	},'json');
}