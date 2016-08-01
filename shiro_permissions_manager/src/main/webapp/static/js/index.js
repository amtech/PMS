// 本地化
window['w2utils'] && w2utils.locale('static/w2ui/zh-cn.json');

$(document).ready(function(){
	// 主要容器
    var $main = $('#main').w2layout({
        name: 'mainLayout',
        panels: [
            { type: 'top', size: 45, style: 'border: 0px; border-bottom: 1px solid silver; background-color: #5170ad; color: #fff;', overflow: 'hidden'},
            { type: 'left', size: 240, resizable: true, style: 'border-right: 1px solid silver;' },
            { type: 'main', style: 'background-color: #efefef;' }
        ]
    });
    
    $main.content('top', '<div style="padding: 12px 20px; font-size: 18px; font-weight: bold;">SpringMVC/MyBatis/Apache Shiro 整合演示用例</div>');
    // 菜单容器
    var $menu = $().w2sidebar({
    	name: 'menuSidebar',
    	topHTML    : '<div style="background-color: #eee; padding: 10px 5px; border-bottom: 1px solid silver">&nbsp;<a href="'+ APP.PATH +'">首页</a> &nbsp; 当前用户 ['+ APP.USER +']</div>',
    	nodes: [{id: '000', text: 'MENU',expanded: true, group: true}]
    });
    // 菜单内容
    $menu.add('000', function($li){
    	var nodes = [], icon = {'user': 'fa-user','role': 'fa-group','resource':'fa-hdd','session':'fa-link'};
    	$li.each(function(i,o){
    		nodes.push({
    			id : $(o).attr('mid'),
    			text: $(o).attr('text'),
    			url: $(o).attr('url'),
    			icon: icon[$(o).attr('url').substr(1)] || 'fa-desktop'
    		});
    	});
    	return nodes;
    }($('ul[menu] > li')));
    $main.content('left', $menu);
    // 菜单双击事件
    $menu.on('dblClick', function(event) {
    	var node = this.get(event.target);
    	if (node && node.url) {
    		// 主容器加载
    		$main.load('main', APP.PATH + node.url);
    	}
    });
    
    
    // 加载欢迎页
    $main.load('main', APP.PATH + '/welcome',null, function(){
    	$('a.change').on('click', function(e){
    		$main.load('main', APP.PATH + '/user/password');
    	});
    });
});

/**
 * 一些通用性的方法
 */
window['APP'] = window['APP'] || {};

// 处理 w2ui form 参数
window['APP'].form_param = function(record, prefix) {
	record = record || {}, prefix = prefix || '';
	var ret = [];
	for (var i in record)
		ret.push(prefix + i + '=' + record[i]);
	return ret.join('&');
};

// 销毁方法
window['APP'].w2ui_clean = function() {
	if (!arguments.length) return;
	var params = arguments;
	for (var i = 0, len = params.length; i < len ; i++) {
		if (typeof params[i] == 'string') {
			w2ui[params[i]] && w2ui[params[i]].destroy();
		} else if (typeof params[i] == 'object') {
			params[i].destroy && params[i].destroy(); 
		}
	}
};