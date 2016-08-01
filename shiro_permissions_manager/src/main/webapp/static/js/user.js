/*
 * 用户管理 Script
 */
$(document).ready(function(){
	// 准备数据
	var roles = function($li){
		var roles = {};
		$li.each(function(i,o){
			roles[$(o).attr('rid')] = $(o).text();
		});
		return roles;
	}($('ul[role] > li'));
	
	// 防止重复
	w2ui['usergrid'] && w2ui['usergrid'].destroy();
	// 用户列表
	var $usergrid = $('div[usergrid]').w2grid({
		name : 'usergrid',
		header: '用户列表',
		url: APP.PATH + '/user',
		method: 'POST',
		show: {
            header         : true,
            toolbar     : true,
            lineNumbers    : true,
        },
		columns: [                
            { field: 'uid', caption: '用户编号', size: '10%' },
            { field: 'username', caption: '用户名称', size: '20%' },
            { field: 'roles', caption: '用户角色', size: '70%', render: function(record, index, col_index){
            		var value = this.getCellValue(index, col_index);
            		if (value && value != '') {
            			var ret = [], rids = value.split(',');
            			for (var i=0,len=rids.length;i<len;i++) {
            				roles[rids[i]] && ret.push(roles[rids[i]]);
            			}
            			return ret.join(',');
            		}
            		return value;
            	} 
            }
        ],
        toolbar: {
        	items : [
				{ type: 'button', id: 'create', caption: '创建用户', icon: 'w2ui-icon-plus' },
				{ type: 'button', id: 'edit', caption: '编辑用户', icon: 'w2ui-icon-pencil', disabled: 'disabled' },   
				{ type: 'button', id: 'delete', caption: '删除用户', icon: 'w2ui-icon-cross', disabled: 'disabled' },   
        	]
        }
	});
	
	// 隐藏多余的按钮
	$usergrid.toolbar.hide('w2ui-column-on-off','w2ui-search');
	// 按钮可用性
	$usergrid.on('select', function(event){
		this.toolbar.enable('edit','delete');
	});
	$usergrid.on('unselect', function(event){
		this.toolbar.disable('edit','delete');
	});
	// 按钮事件
	$usergrid.toolbar.on('click', function(event){
		var $list = this.owner, target = event.target;
		
		if(target == 'create') {
			create_user($list);
		} else if (target == 'edit') {
			edit_user($list);
		} else if (target == 'delete') {
			delete_user($list);
		}
		$list.selectNone();
	});
});

/**
 * 创建用户
 * @param $list
 */
function create_user($list) {
	var $layout = null, $grid = null, $form = null;
	w2ui['create_layout'] && w2ui['create_layout'].destroy();
	$layout = $().w2layout({
		name: 'create_layout',
		panels: [
            { type: 'left', resizable: true, minSize: 180 },
            { type: 'main', minSize: 300 }
        ]
	});
	
	w2ui['create_grid'] && w2ui['create_grid'].destroy();
	$grid = $().w2grid({
		name: 'create_grid',
		header: '角色列表',
		show: {
            header : true,
            selectColumn: true
        },
        columns: [
            {field: 'role', caption: '角色名称', size: '100%', sortable: true},
        ]
	});
	// 角色数据
	$('ul[role] > li').each(function(i, o){
		$grid.records.push({
			recid: i,
			rid: $(o).attr('rid'),
			role: $(o).text()
		});
	});
	// 选择事件
	$grid.on('select', function(event){
		event.onComplete = function(event){
			var sel = this.getSelection(), rids = [],ret = [];
			for (var i=0,len = sel.length; i<len; i++) {
				var rcd = this.get(sel[i]);
				rids.push(rcd.rid);
				ret.push(rcd.role);
			}
			$form.record['rolenames'] = ret.join(',');
			$form.record['roles'] = rids.join(',');
			$form.refresh();
		};
	});
	$grid.on('unselect', function(event){
		event.onComplete = function(event){
			var sel = this.getSelection(), rids = [],ret = [];
			for (var i=0,len = sel.length; i<len; i++) {
				var rcd = this.get(sel[i]);
				rids.push(rcd.rid);
				ret.push(rcd.role);
			}
			$form.record['rolenames'] = ret.join(',');
			$form.record['roles'] = rids.join(',');
			$form.refresh();
		};
	});
	
	
	w2ui['create_form'] && w2ui['create_form'].destroy();
	$form = $().w2form({
		header: '用户表单',
		name: 'create_form',
		fields: [
            { name: 'username', type: 'text', required: true, html: { span: 4,caption: '用户名', attr: 'style="width: 80%;" maxlength="20"' } },
            { name: 'password', type: 'password', required: true, html: {span: 4, caption: '密码', attr: 'style="width: 80%;" maxlength="16"' } },
            { name: 'eqpwd', type: 'password', required: true, html: {span: 4, caption: '确认密码', attr: 'style="width: 80%;" maxlength="16"' } },
            { name: 'rolenames', type: 'text', html: { span: 4,caption: '角色列表', attr: 'size="30" readonly style="width: 80%;"' } },
        ],
        actions: {
        	reset: function(e){
        		$grid.selectNone();
        		this.clear();
        	},
        	save: function(e){
        		var ret = this.validate();
        		if (!ret.length) {
        			form_submit(this, APP.PATH + '/user/create');
        		}
        	}
        },
        onValidate: function(e) {
        	// 密码的验证补充
        	var pwd = this.record['password'];
        	if (pwd != '' && pwd.length < 4) {
        		e.errors.push({
        			error: '密码至少需要 4 个字符',
        			field: this.get('password')
        		});
        	}
        	if (pwd != this.record['eqpwd']) {
        		e.errors.push({
        			error: '两次输入密码不一致',
        			field: this.get('eqpwd')
        		});
        	}
        }
	});
	
	
	w2popup.open({
		title: '创建用户',
		width: 600,
		height: 400,
		modal: true,
		body: '<div create style="width: 100%; height: 100%; padding: 5px 0px 0px; 0px"></div>',
		onOpen: function(event) {
			event.onComplete = function(event) {
				$layout.render($('#w2ui-popup div[create]'));
				$layout.content('left', $grid);
				$layout.content('main', $form);
				// 绑定弹窗到表单
				$form._$popup = this;
				// 改写按钮显示名称
				setTimeout(function(){
					$($form.box).find('button').each(function(i, o){
						if (this.name == 'save') {
							$(this).html('保存');
						} else if (this.name == 'reset') {
							$(this).html('重置');
						}
					});
				},100);
			};
		},
		onClose: function(event) {
			event.onComplete = function(event) {
				$list && $list.reload && $list.reload();
			};
		}
	});
}

/**
 * 编辑用户
 * 
 * @param $list
 */
function edit_user($list) {
	var $layout = null, $grid = null, $form = null;
	w2ui['create_layout'] && w2ui['create_layout'].destroy();
	$layout = $().w2layout({
		name: 'create_layout',
		panels: [
            { type: 'left', resizable: true, minSize: 180 },
            { type: 'main', minSize: 300 }
        ]
	});
	
	w2ui['create_grid'] && w2ui['create_grid'].destroy();
	$grid = $().w2grid({
		name: 'create_grid',
		header: '角色列表',
		show: {
            header : true,
            selectColumn: true
        },
        columns: [
            {field: 'role', caption: '角色名称', size: '100%', sortable: true},
        ]
	});
	// 角色数据
	$('ul[role] > li').each(function(i, o){
		$grid.records.push({
			recid: i,
			rid: $(o).attr('rid'),
			role: $(o).text()
		});
	});
	// 选择事件
	$grid.on('select', function(event){
		event.onComplete = function(event){
			var sel = this.getSelection(), rids = [],ret = [];
			for (var i=0,len = sel.length; i<len; i++) {
				var rcd = this.get(sel[i]);
				rids.push(rcd.rid);
				ret.push(rcd.role);
			}
			$form.record['rolenames'] = ret.join(',');
			$form.record['roles'] = rids.join(',');
			$form.refresh();
		};
	});
	$grid.on('unselect', function(event){
		event.onComplete = function(event){
			var sel = this.getSelection(), rids = [],ret = [];
			for (var i=0,len = sel.length; i<len; i++) {
				var rcd = this.get(sel[i]);
				rids.push(rcd.rid);
				ret.push(rcd.role);
			}
			$form.record['rolenames'] = ret.join(',');
			$form.record['roles'] = rids.join(',');
			$form.refresh();
		};
	});
	
	
	w2ui['create_form'] && w2ui['create_form'].destroy();
	$form = $().w2form({
		header: '用户表单',
		name: 'create_form',
		fields: [
            { name: 'username', type: 'text', required: true, html: { span: 4,caption: '用户名', attr: 'style="width: 80%;" maxlength="20"' } },
            { name: 'rolenames', type: 'text', html: { span: 4,caption: '角色列表', attr: 'size="30" readonly style="width: 80%;"' } },
        ],
        actions: {
        	reset: function(e){
        		this.clear();
        		$grid.selectNone();
        		var record = $.extend(true,{},this._origin || {});
        		this.record = record;
        		// 选择原有的角色
        		if (record && record.roles) {
        			var rids = record.roles.split(','), list = $grid.records;
        			for (var i=0,len=list.length; i<len; i++) {
        				if (rids.indexOf(list[i].rid) > -1)
        					$grid.select(list[i].recid);
        			}
        		}
        		this.refresh();
        	},
        	save: function(e){
        		// 验证必填和是否改变
        		var ret = this.validate(), isChange = function(a,b){
        			var valids = ['username','roles'];
        			for (var i=0; i<2; i++)
        				if (a[valids[i]] != b[valids[i]]) return true;
        			return false;
        		}(this._origin || {}, this.record);
        		// 验证通过
        		if (!ret.length && isChange) {
        			form_submit(this, APP.PATH + '/user/'+ this.record['uid'] + '/update');
        		} else if (!isChange) {
        			w2alert('没有发生改变，无需保存');
        		}
        			
        	}
        }
	});
	// 原始数据
	var record = $list.get($list.getSelection()[0]) || {};
	// 剔除密码
	delete record['password'];
	$form['_origin'] = $.extend(true, {}, record);
	$form.record = $.extend(true, {}, record);
	// 选择原有的角色
	if (record && record.roles) {
		var rids = record.roles.split(','), list = $grid.records;
		for (var i=0,len=list.length; i<len; i++) {
			if (rids.indexOf(list[i].rid) > -1)
				$grid.select(list[i].recid);
		}
	}
	
	w2popup.open({
		title: '更新用户',
		width: 600,
		height: 400,
		modal: true,
		body: '<div create style="width: 100%; height: 100%; padding: 5px 0px 0px; 0px"></div>',
		onOpen: function(event) {
			event.onComplete = function(event) {
				$layout.render($('#w2ui-popup div[create]'));
				$layout.content('left', $grid);
				$layout.content('main', $form);
				// 绑定弹窗
				$form._$popup = this;
				// 改写按钮显示名称
				setTimeout(function(){
					$($form.box).find('button').each(function(i, o){
						if (this.name == 'save') {
							$(this).html('保存');
						} else if (this.name == 'reset') {
							$(this).html('重置');
						}
					});
				},100);
			};
		},
		onClose: function(event) {
			event.onComplete = function(event) {
				$list && $list.reload && $list.reload();
			};
		}
	});
}

/**
 * 删除用户
 * @param $list
 */
function delete_user($list) {
	var sel = $list.getSelection();
	if (sel.length) {
		var user = $list.get(sel[0]);
		w2confirm('确认删除用户[' + user.username + '] ？', function(btn){
			btn == 'Yes' && function($list, user) {
				$.get(APP.PATH + '/user/' + user.uid + '/delete', function(data){
					if (data && data.message) w2alert(data.message);
					if (data && data.success) $list.reload();
				},'json');
			}($list, user);
		});
	}
}

/**
 * 表单提交
 * @param $form
 * @param url
 */
function form_submit($form, url) {
	$form.lock('正在处理...',true);
	$.post(url, APP.form_param($form.record, 'user.'),function(data){
		if (data && data.success) 
			w2alert(data.message, '处理结果', function(){
				$form._$popup && $form._$popup.close();
				delete $form['_$popup'];
			});
		else {
			data && data.message && w2alert(data.message);
		}
		$form.unlock();
	},'json');
}
