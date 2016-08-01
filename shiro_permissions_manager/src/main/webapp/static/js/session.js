/*
 * 会话管理 Script 
 */

$(document).ready(function() {
	// $ element
	var $session = null;
	// clean
	APP.w2ui_clean('session');
	
	$session = $('div[session]').w2grid({
		name: 'session',
		header: '会话列表',
		url: APP.PATH + '/session',
		method: 'POST',
		show: {
            header         : true,
            toolbar        : true,
            lineNumbers    : true,
        },
        columns: [                
            { field: 'id', caption: '会话编号', size: '15%' },
            { field: 'user', caption: '用户名', size: '10%' },
            { field: 'host', caption: '主机', size: '10%' },
            { field: 'last', caption: '最后活动时间', size: '10%', render: function(record,index,col_index){
            	var value = this.getCellValue(index, col_index);
            	return w2utils.formatDateTime(new Date(value),'yyyy-MM-dd');
            }},
            { field: 'force', caption: '是否强制退出', size: '10%', render: function(record, index, col_index){
            	var value = this.getCellValue(index, col_index);
            	return Boolean(value) ? "是" : "否";
            }}
    	],
    	toolbar: {
    		items: [{type: 'button', id: 'force', caption: '强制退出', icon: 'w2ui-icon-cross', disabled: 'disabled'}]
    	}
	});
	
	$session.toolbar.hide('w2ui-column-on-off','w2ui-search');
	// 选中时清空表单数据
	$session.on('select', function(event) {
		this.toolbar.enable('force');
	});
	$session.on('unselect', function(event){
		this.toolbar.disable('force');
	});
	
	$session.toolbar.on('click', function(event){
		var sel = this.owner.getSelection(), $grid = this.owner;
		if (event.target == 'force' && sel.length) {
			$.get(APP.PATH + '/session/' + $grid.get(sel[0])['id'] + '/forceLogout', function(data){
				if (data && data.success) {
					w2alert(data.message, '处理结果', function(){
						$grid.reload();
					});
				} else {
					data && data.message && w2alert(data.message);
				}
			},'json');
		}
	});
});
