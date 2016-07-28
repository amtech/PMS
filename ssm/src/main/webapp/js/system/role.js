jQuery.validator.addMethod("codeCheck", function(value, element) {
	  return this.optional(element) ||  /^ROLE_+[a-zA-Z0-9_]*$/.test(value);
});

var Role = {
	//角色选择树
	buildTree : function (){
    	$('#role_tree').jstree({
			plugins:["checkbox", "wholerow"], 
			core : {
				themes : {
					name : false,
					dots : false,
					icons : false
				},
		    	data : {
		    		type : "json",
		    		method : "post",
		    		url : contextPath + "/system/role/listForTree?timestamp=" + new Date().getTime()
		    	}
			},
			checkbox : {
				three_state : true
			}
		})
		.bind('load_node.jstree', function() {   
			var checkNodeIds = $("#roleIds").val().split(",");
			$("#role_tree").find("li").each(function(index,item) {
				for (var i = 0; i < checkNodeIds.length; i++) {
					if ($(item).attr("id") == checkNodeIds[i]) { 
						if ($('#role_tree').jstree("is_parent", '#'+$(item).attr("id")) == false){
							$('#role_tree').jstree("select_node", '#'+$(item).attr("id"), true);
						}
					}
				}
			});
	    });
	},
	//列表页面加载方法
	loadPage : function() {
		jQuery("#grid-table").jqGrid({
			url:'list',
		    datatype: 'json',     
			mtype: "POST",
			height: "100%",
			rownumbers: true,
			rownumWidth:50,
			colNames:['id','','角色名称', '角色编码', '状态', '创建人','创建时间','最后修改人','最后修改时间','操作'],
			colModel:[
			    {name:'id',index:'id',width:90,hidden:true},
			    {name:'action',index:'action',width:25,hidden:false,fixed:true},
			    {name:'roleName',index:'roleName',width:90,editable:false},
			    {name:'roleCode',index:'roleCode',width:90,editable:false},
				{name:'isValid',index:'isValid', width:90,editable:false,formatter:statusChange},
				{name:'createUserName',index:'createUserName',width:90},
				{name:'createDate',index:'createDate',width:100,formatter:dateFormatter},
				{name:'lastUpdateUserName',index:'lastUpdateUserName',width:90},
				{name:'lastUpdateDate',index:'lastUpdateDate',width:100,formatter:dateFormatter},
				{name:'operation',index:'operation', width:90}
			], 
			viewrecords : true,
			rowNum:10,
			rowList:[10,20,30],
			pager : "#grid-pager",
			altRows: true,
			autowidth: true,
			autoScroll: false,
			caption: "角色列表",
			jsonReader : {   
		      root:"result",
		      total:'totalPages',
		      page:'page',
		      records:'records'   
			},
			loadComplete : function() {
				var table = this;
				setTimeout(function(){
					updatePagerIcons(table);
				}, 0);
			},
			gridComplete : function(){
				var ids = jQuery("#grid-table").jqGrid('getDataIDs');
				for(var i=0;i < ids.length;i++){
					var cl = ids[i];
					checkbox = "<label><input name=\"grid-checkbox\" value=\"" 
						+ cl + "\"type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label>"; 
					jQuery("#grid-table").jqGrid('setRowData',ids[i],{action:checkbox});
				}	
				
				/**
				 * 窗口缩放时，经动态变化宽度
				 */
				$(window).resize(function(){ 
					var winwidth=$('.page-content').width(); 	//当前页面的宽度
					$("#grid-table").setGridWidth(winwidth);
					$('.ui-jqgrid-bdiv').css('width',winwidth+1);
				});
				
				/**
				 * 点击菜单边框收缩菜单时，动态变化表格宽度
				 */
				$('#sidebar-collapse').click(function(){
					var winwidth=$('.main-content .col-xs-12').width(); 	//当前窗口中，一行的宽度
					$("#grid-table").setGridWidth(winwidth);
					$('.ui-jqgrid-bdiv').css('width',winwidth+1);
				});
			},
			afterInsertRow: function(rowid, entity){
		    	switch (entity.isValid) {
		    		case '1':
		    			var cellHtml = "<button onclick=\"Role.changeValid('" + entity.id 
									 + "','0')\" class=\"btn btn-info btn-minier\" type=\"button\">"
									 + "<i class=\"icon-lock bigger-110\"></i>无效</button>";
		    			jQuery("#grid-table").jqGrid('setRowData',rowid,{operation:cellHtml});
		    		break;
		    		case '0':
		    			var cellHtml = "<button onclick=\"Role.changeValid('" + entity.id 
									 + "','1')\" class=\"btn btn-info btn-minier\" type=\"button\">"
									 + "<i class=\"icon-unlock bigger-110\"></i>有效</button>";
		    			jQuery("#grid-table").jqGrid('setRowData',rowid,{operation:cellHtml});
		    		break;
		    	}
			}
		});
		
		//查询按钮
		$('#search').click(function(){
			var json = {
				'roleName':$("#roleName").val(),
			};
			jQuery("#grid-table").jqGrid('setGridParam', { postData: json }).jqGrid('setGridParam', { 'page': 1 })
			.trigger("reloadGrid");
		});
	
		//新增按钮
		$('#new').click(function(){
			$('#role-input').removeData("bs.modal");
			$('#role-input').modal({
				remote:'input?timestamp=' + new Date().getTime(),
				backdrop:'static'
			});
			$('#new').button('loading');
		});
		
		//修改按钮
		$('#edit').click(function(){
			
			var rowIds = CommUtils.getJqgridSelected("grid-table");    
			
			if (rowIds.length > 1 || rowIds.length == 0){
				CommUtils.commAlert("role-input", "修改记录时只能选择一条记录！");
			}else{
				$('#role-input').removeData("bs.modal");
				$('#role-input').modal({
					remote:'input?timestamp=' + new Date().getTime() + "&id=" + rowIds[0],
					backdrop:'static'
				});
				$('#edit').button('loading');
			}
		});

		//选择菜单按钮
		$('#selectMenu').click(function(){
			var rowIds = CommUtils.getJqgridSelected("grid-table");
			if (rowIds.length > 1 || rowIds.length == 0){
				CommUtils.commAlert("role-input", "请选择一条记录！");
			}else{
				$('#role-input').removeData("bs.modal");
				$('#btn_selectMenu').button('loading');
				$('#role-input').modal({
					remote:'selectMenu?timestamp=' + new Date().getTime() + "&id=" + rowIds[0],
					backdrop:'static'
				});
				$('#selectMenu').button('loading');
			}
		});

		//删除按钮
		$('#delete').click(function(){
			var rowIds = CommUtils.getJqgridSelected("grid-table");
			if (rowIds.length == 0){
				CommUtils.commAlert("role-input", "请选择一条或多条记录！");
			}else{
				var options = new Object;
				options.modalId = "role-input";
				options.msg = "确认您是否要删除所选中的记录？";
				options.delIds = rowIds;
				options.url = "delete";
				options.gridTableId = "grid-table";
				CommUtils.commDelete(options);
			}
		});
	},
	//修改页面加载方法
	loadInputPage: function(){
		
		$('#new').button('reset');
		$('#edit').button('reset');
		
		if ($("#editId").val()){
			$("#input-title").html("修改角色");
		}else{
			$("#input-title").html("新增角色");
		}
		
		$('#role_fmt').validate({
			errorElement: 'div',
			errorClass: 'help-block',
			focusInvalid: true,
			rules:{
				roleName:{
					required:true,
					remote:{
						url:'validateExist',
						type:'post',
						data:{
							roleName:function(){
								return $('#roleNameVal').val();
							},
							id:function(){
								return $('#editId').val();
							}
						}
					}				
				},
				roleCode:{
					required:true,
					codeCheck:true,
					remote:{
						url:'validateExistCode',
						type:'post',
						data:{
							roleCode:function(){
								return $('#roleCode').val();
							},
							id:function(){
								return $('#editId').val();
							}
						}
					}				
				}
			},messages:{
				roleName:{
					required:"角色名称为必填项",
					remote:"角色名称已存在"
				},
				roleCode:{
					required:"角色编码为必填项",
					codeCheck:"角色编码必填以ROLE_开始",
					remote:"角色编码已存在"
				}
			},
			highlight: function (e) {
				$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
			},
	
			success: function (e) {
				$(e).closest('.form-group').removeClass('has-error').addClass('has-info');
				$(e).remove();
			}
		});

		//保存按钮
		$('#save_btn').click(function(){
			var isValid = $('#role_fmt').valid();
			if(isValid){
				$.ajax({
					type:'POST',
					url:'save',
					data:$('#role_fmt').serialize(),
					dataType:'json',
					success:function(json){
						if(json.status == "success"){
							$('#role-input').load(contextPath + '/common/success.jsp',
									{msg:'操作已成功，角色"<b>'+$('#roleNameVal').val()+'"</b>保存成功',
									 seconds:'3000',
									 tips:''},'');
							
							$('#grid-table').trigger("reloadGrid");
						}else{
							$('#role-input').load();
						}
					}
				});
			}
		});
	},
	//选择菜单加载
	loadSelectMenuPage: function(){
		
		$('#selectMenu').button('reset');
		
		Menu.buildTree();
		
		var rowIds = CommUtils.getJqgridSelected("grid-table"); 
		$("#roleId").val(rowIds[0]);
		
		$('#save_selectMenu_btn').click(function(){
			var undeterminedNodes = [];
			$(".jstree-undetermined").each(function(index,item) {
				undeterminedNodes.push($(item).parent().parent().attr("id"));
			});
			$("#menuIds").val($('#menu_tree').jstree("get_selected") + "," + undeterminedNodes);
			$.ajax({
				type:'POST',
				url:'saveRoleMenuRel',
				data:"roleId="+$("#roleId").val()+"&menuIds="+$("#menuIds").val(),
				dataType:'json',
				success:function(json){
					if(json.status == "success"){
						$('#role-input').load(contextPath + '/common/success.jsp',
								{msg:'操作已成功',
								 seconds:'3000',
								 tips:''},'');
					}else{
						$('#role-input').load();
					}
				}
			});
		});
	},
	changeValid : function(id,flag){
		$.ajax({
			type:'POST',
			url:'changeValid',
			async: false,
			data:{
				id:id,
				flag:flag
			},
			dataType:'json',
			success:function(json){
				if(json.status == "success"){
					$('#grid-table').trigger("reloadGrid");
				}
			}
		});
	}
}