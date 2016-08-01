/*
 * 角色管理 Script 
 */

$(document).ready(function() {
	// clean
	APP.w2ui_clean('student');
	// $ element
	$('#student').w2grid({
		name: 'student',
		header: '学生信息列表 - 演示分页插件',
		url: APP.PATH + '/student',
		method: 'POST',
		show: {
            header         : true,
            lineNumbers    : true,
            footer   : true
        },
        limit: 30,  // 每次加载30条
        autoLoad: false, // 手动加载
        columns: [                
            { field: 'sn', caption: '学号', size: '10%' },
            { field: 'name', caption: '姓名', size: '10%' },
            { field: 'sex', caption: '性别', size: '10%'},
            { field: 'academic', caption: '专业', size: '10%'},
            { field: 'classes', caption: '行政类别', size: '10%'},
    	],
	});
});