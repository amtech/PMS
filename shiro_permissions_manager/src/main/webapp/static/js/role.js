/*
 * 角色管理 Script 
 */

$(document).ready(function() {
	// 权限列表
	var _RESOURCES = function($li){
		var ret = [];
		$li.each(function(i,o){
			ret.push({
				recid: i,
				reid: $(o).attr('reid'),
				name: $(o).text(),
				permissions: $(o).attr('permissions')
			});
		});
		return ret;
	}($('ul[res] > li'));
	// $ element
	var $role = null, $form = null, $res = null;
	// clean
	APP.w2ui_clean('rolegrid','roleform', 'resgrid');
	
	$role = $('#rolegrid').w2grid({
		name: 'rolegrid',
		header: '角色列表',
		url: APP.PATH + '/role',
		method: 'POST',
		show: {
            header         : true,
            lineNumbers    : true,
        },
        columns: [                
            { field: 'rid', caption: '编号', size: '6%' },
            { field: 'role', caption: '角色', size: '10%' },
            { field: 'description', caption: '描述', size: '10%'},
            { field: 'resources', caption: '资源', size: '10%', render: function(record, index, col_index){
            	var value = this.getCellValue(index, col_index);
            	if (value && value != '') {
            		var reids = value.split(','), ret = [];
            		for (var i=0,len=_RESOURCES.length; i<len; i++) {
            			var res = _RESOURCES[i];
            			if (reids.indexOf(res['reid']) > -1) ret.push(res['name']);
            		}
            		return ret.join(',');
            	}
            	return value;
            }}
    	],
	});
	// 选中时清空表单数据
	$role.on('select', function(event) {
		$form.clear();
		$form.toolbar.uncheck('create','edit','delete');
		$form.toolbar.enable('edit', 'delete');
		$res.selectNone();
	});
	$role.on('unselect', function(event){
		$form.toolbar.disable('edit', 'delete');
	});
	
	$form = $('#roleform').w2form({
		name: 'roleform',
		header: '角色信息',
		fields: [
			{ name: 'role', type: 'text', required: true},
			{ name: 'description', type: 'text', required: true},
			{ name: 'resname', type: 'text'},
		],
		toolbar: {
            items: [
                { id: 'create', type: 'button', caption: '新建角色', icon: 'fa-plus' },
                { id: 'edit', type: 'button', caption: '编辑角色', icon: 'fa-edit', disabled: 'disabled' },
                { id: 'delete', type: 'button', caption: '删除角色', icon: 'fa-remove', disabled: 'disabled' },
            ]
        },
        actions: {
        	reset: function(e){
        		this.clear();
        		this.toolbar.uncheck('create','edit','delete');
        		this.toolbar.enable('edit', 'delete');
        		$res.selectNone();
        	},
        	save: function(e){
        		// 操作类型
        		var op = function(items) {
	        		for (var i=0,len=items.length; i<len; i++) {
	        			if (items[i].checked) return items[i].id;
	        		}
	        		return false;
        		}(this.toolbar.items);
        		if (!op) return;
        		// 删除
        		if (op == 'delete') {
        			form_submit(this, APP.PATH + '/role/' + this.record['rid'] + '/delete','get');
        			return;
        		}
        		// 表单验证
        		var ret = this.validate();
        		if (ret.length) return;
        		// 新增
        		if (op == 'create') {
        			form_submit(this, APP.PATH + '/role/create');
        		} else if (op == 'edit') {
        			var isChange = function(a, b, vs) {
        				for (var i=0,len=vs.length; i<len; i++) {
        					if (a[vs[i]] != b[vs[i]]) return true;
        				}
        				return false;
        			}(this['_origin'], this.record, ['role','description','resources']);
        			
        			if (!isChange) {
        				w2alert('没有变动，无需保存');
        				return;
        			}
        			
        			form_submit(this, APP.PATH + '/role/update');
        		}
        	}
        }
	});
	
	$form.toolbar.on('click',function(event){
		var target = event.target, items = this.items;
		if (target == 'create') {
			create_role($role, $form, $res);
		} else if (target == 'edit') {
			edit_role($role, $form, $res);
		} else if (target == 'delete') {
			delete_role($role, $form, $res);
		}
		
		// 选中
		this.check(target);
		for (var i=0,len=items.length; i<len; i++) {
			items[i].id != target && this.uncheck(items[i].id);
		}
		
	});
	
	$res = $('#resgrid').w2grid({
		name: 'resgrid',
		header: '资源列表',
		show: {
            header         : true,
            lineNumbers    : true,
            selectColumn: true
        },
        columns: [
            { field: 'reid', caption: '编号', size: '8%'},
            { field: 'name', caption: '名称', size: '20%' },
            { field: 'permissions', caption: '权限标识', size: '30%' }
        ],
        records: _RESOURCES || [],
	});
	
	// 选择时绑定数据到表单
	$res.on('select', function(evt){
		evt.onComplete = res_binder;
	});
	$res.on('unselect', function(evt){
		evt.onComplete = res_binder;
	});
	$form['_$res'] = $res;
	$form['_$role'] = $role;
	
	function res_binder(event) {
		var sel = this.getSelection();
		var reids = [], ret = [];
		if (sel.length) {
			for (var i=0, len=sel.length; i<len; i++) {
				var rcd = this.get(sel[i]);
				if (rcd['reid'] == 1) continue;
				
				ret.push(rcd['name']);
				reids.push(rcd['reid']);
			}
		}
		// 绑定数据到表单
		$form.record['resources'] = reids.join(',');
		$form.record['resname'] = ret.join(',');
		$form.refresh();
	}
});

function create_role($role, $form, $res) {
	// 清空选择
	$role.selectNone();
	$res.selectNone();
}

function edit_role($role, $form, $res) {
	var sel = $role.getSelection();
	if (!sel.length) return;
	$form.clear();
	$res.selectNone();
	// 原始数据
	var record = $role.get(sel[0]), reids = record['resources'].split(',');
	$form['_origin'] = $.extend(true, {},  record);
	// 填充数据
	for (var i=0,len=$res['records'].length; i<len; i++) {
		var rcd = $res['records'][i];
		if (reids.indexOf(rcd['reid']) > -1) $res.select(rcd['recid']);
	}
	$.extend(true, $form['record'], record);
	
	$form.refresh();
}

function delete_role($role, $form, $res) {
	var sel = $role.getSelection();
	if (!sel.length) return;
	$form.clear();
	$res.selectNone();
	// 原始数据
	var record = $role.get(sel[0]), reids = record['resources'].split(',');
	// 填充数据
	for (var i=0,len=$res['records'].length; i<len; i++) {
		var rcd = $res['records'][i];
		if (reids.indexOf(rcd['reid']) > -1) $res.select(rcd['recid']);
	}
	$.extend(true, $form['record'], record);
	
	$form.refresh();
}

/**
 * 表单提交
 * @param $form
 * @param url
 */
function form_submit($form, url, method) {
	method = method || 'post';
	$form.lock('正在处理...',true);
	$[method](url, APP.form_param($form.record, 'role.'),function(data){
		if (data && data.success) 
			w2alert(data.message, '处理结果', function(){
				$form.clear();
				$form.toolbar.uncheck('create','edit','delete');
				$form['_$res'] && $form['_$res'].selectNone();
				$form['_$role'] && $form['_$role'].reload();
			});
		else {
			data && data.message && w2alert(data.message);
		}
		$form.unlock();
	},'json');
}