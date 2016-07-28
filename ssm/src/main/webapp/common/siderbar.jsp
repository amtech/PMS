<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript">
		try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>
	<a class="menu-toggler" id="menu-toggler" href="#">
		<span class="menu-text"></span>
	</a>

	<div class="sidebar" id="sidebar">
		<script type="text/javascript">
				try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
			</script>

		<div class="sidebar-shortcuts" id="sidebar-shortcuts">
			<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
				<button class="btn btn-success">
					<i class="icon-signal"></i>
				</button>

				<button class="btn btn-info">
					<i class="icon-pencil"></i>
				</button>

				<button class="btn btn-warning">
					<i class="icon-group"></i>
				</button>

				<button class="btn btn-danger">
					<i class="icon-cogs"></i>
				</button>
			</div>
		</div>
		<!-- #sidebar-shortcuts -->

		<ul class="nav nav-list" id="leftMenu_ul">

		</ul>
		<!-- /.nav-list -->

		<div class="sidebar-collapse" id="sidebar-collapse">
			<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
		</div>

		<script type="text/javascript">
			try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
		</script>
			
		<script type="text/javascript">
			jQuery(function($){
				$.ajax({
				    type: "post",
				    url: "${contextPath }/system/menu/availableMenu",
				    dataType: "json",
				    success: function(json){
				    	treeObject = null;
				    	var tree_data= $.parseJSON(json.result);
				    	buildLeftMenu(tree_data);
				    }
				 });
			});
			
			//构建菜单
			function buildLeftMenu(json){
				$.each(json, function (index, item) {
					var _li = $("<li id='" + item.id + "' class='level_1'>").appendTo($("#leftMenu_ul"));
					var _li_a = $("<a href=\"#\" class=\"dropdown-toggle\"/>").appendTo($(_li));
					if (item.menuPath != null && item.menuPath != ''){
						$(_li_a).attr("href","${contextPath }" + item.menuPath);			
					}
					var _li_a_i = $("<i class=\"icon-desktop\"></i>").appendTo($(_li_a));
					if (item.iconCode != null && item.iconCode != ''){
						$(_li_a_i).attr("class",item.iconCode);			
					}
					$("<span class=\"menu-text\">" + item.name + "</span>").appendTo($(_li_a));
					$("<b class=\"arrow icon-angle-down\"></b>").appendTo($(_li_a));
					
					var children = item.additionalParameters.children;
					
					var _child_ul = $("<ul class=\"submenu\" id='" + item.id + "' style=\"display:none;\"/>").appendTo($(_li));
						
					$.each(children, function (index, childItem) {
						var _child_ul_li = $("<li class='level_2' id='" + childItem.id + "'/>").appendTo($(_child_ul));
						var _child_ul_li_a = $("<a href=\"#\">" + childItem.name + "</a>").appendTo($(_child_ul_li));
						if (childItem.menuPath != null && childItem.menuPath != ''){
							$(_child_ul_li_a).attr("href","${contextPath}" + childItem.menuPath);			
						}
						var _child_ul_li_a_i = $("<i class=\"icon-double-angle-right\"></i>").appendTo($(_child_ul_li_a));
						if (childItem.iconCode != null && childItem.iconCode != ''){
							$(_child_ul_li_a_i).attr("class",childItem.iconCode);			
						}
					});
				});
				
				$('.level_1').click(function(){
					var comparStr = $(this).attr("id");
					if (!cookie_openMenuId || ("" + cookie_openMenuId).indexOf(comparStr) == -1){
						$.cookies.set("openMenuId", $(this).attr("id"));
					}
				});
	
				$('.level_2').click(function(){
					var comparStr = $(this).attr("id");
					if (!cookie_activeMenuId || ("" + cookie_activeMenuId).indexOf(comparStr) == -1){
						$.cookies.set("activeMenuId", $(this).attr("id"));
					}
				});
				
				//展开当前URL的菜单栏
				$("#" + cookie_activeMenuId).addClass("active");
				$("#" + cookie_openMenuId).addClass("active open");
				$("#" + cookie_openMenuId).find("ul").css("display","");
				
			}
		</script>
			
	</div>
