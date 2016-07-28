var User = {
	//列表页面加载方法
	loadPage : function() {
		jQuery("#user-grid-table").jqGrid({
			url:'list',
		    datatype: 'json',     
			mtype: "POST",
			height: "100%",
			rownumbers: true,
			rownumWidth:50,
			colNames:['id','','登录名', '姓名','Email', '状态','操作'],
			colModel:[
			    {name:'id',hidden:true},
			    {name:'action',hidden:false,width:30,fixed:true},
			    {name:'username',index:'username',editable:false},
			    {name:'realname',index:'realname',editable:false},
			    {name:'email',index:'email',editable:false},
			    {name:'isValid',editable:false,formatter:statusChange},
			    {name:'operation'}
			], 
			viewrecords : true,
			rowNum:10,
			rowList:[10,20,50],
			pager : "#grid-pager",
			multiselect: false,
	        multiboxonly: false,
			altRows: true,
			autowidth: true,
			autoScroll: false,
			caption: "用户列表",
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
				var ids = jQuery("#user-grid-table").jqGrid('getDataIDs');
				for(var i=0;i < ids.length;i++){
					var cl = ids[i];
					checkbox = "<label><input name=\"grid-checkbox\" value=\"" 
						+ cl + "\"type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label>"; 
					jQuery("#user-grid-table").jqGrid('setRowData',ids[i],{action:checkbox});
				}
				/**
				 * 窗口缩放时，经动态变化宽度
				 */
				$(window).resize(function(){ 
					var winwidth=$('.page-content').width(); 	//当前页面的宽度
					$("#user-grid-table").setGridWidth(winwidth);
					$('.ui-jqgrid-bdiv').css('width',winwidth+1);
				});
				
				/**
				 * 点击菜单边框收缩菜单时，动态变化表格宽度
				 */
				$('#sidebar-collapse').click(function(){
					var winwidth=$('.main-content .col-xs-12').width(); 	//当前窗口中，一行的宽度
					$("#user-grid-table").setGridWidth(winwidth);
					$('.ui-jqgrid-bdiv').css('width',winwidth+1);
				});
				
			},
			afterInsertRow: function(rowid, entity){
		    	switch (entity.isValid) {
		    		case '1':
		    			var cellHtml = "<button onclick=\"User.changeValid('" + entity.id 
									 + "','0')\" class=\"btn btn-info btn-minier\" type=\"button\">"
									 + "<i class=\"icon-lock bigger-110\"></i>无效</button>";
		    			jQuery("#user-grid-table").jqGrid('setRowData',rowid,{operation:cellHtml});
		    		break;
		    		case '0':
		    			var cellHtml = "<button onclick=\"User.changeValid('" + entity.id 
									 + "','1')\" class=\"btn btn-info btn-minier\" type=\"button\">"
									 + "<i class=\"icon-unlock bigger-110\"></i>有效</button>";
		    			jQuery("#user-grid-table").jqGrid('setRowData',rowid,{operation:cellHtml});
		    		break;
		    	}
			}
		});
		
		/*查询按钮*/
		$('#search').click(function(){
			var json = {
				'username':$("#search_username").val(),
				'email':$('#search_email').val(),
				'isValid':$('#search_isValid').val()
			};
			jQuery("#user-grid-table").jqGrid('setGridParam', { postData: json }).jqGrid('setGridParam', { 'page': 1 })
			.trigger("reloadGrid");
		});
	
		/*新增按钮*/
		$('#new').click(function(){
			$('#user-input').removeData("bs.modal");
			$('#user-input').modal({
				remote:'input?timestamp=' + new Date().getTime(),
				backdrop:'static'
			});
			$('#new').button('loading');
		});
		
		/*修改按钮*/
		$('#edit').click(function(){
			
			var rowIds = CommUtils.getJqgridSelected("user-grid-table");    
			
			if (rowIds.length > 1 || rowIds.length == 0){
				CommUtils.commAlert("user-input", "修改记录时只能选择一条记录！");
			}else{
				$('#user-input').removeData("bs.modal");
				$('#user-input').modal({
					remote:'input?timestamp=' + new Date().getTime() + "&id=" + rowIds[0],
					backdrop:'static'
				});
				$('#edit').button('loading');
			}
		});

		/*选择菜单按钮*/
		$('#selectMenu').click(function(){
			var rowIds = CommUtils.getJqgridSelected("user-grid-table");   
			if (rowIds.length > 1 || rowIds.length == 0){
				CommUtils.commAlert("user-input", "请选择一条记录！");
			}else{
				$('#user-input').removeData("bs.modal");
				$('#selectMenu').button('loading');
				$('#user-input').modal({
					remote:'selectMenu?timestamp=' + new Date().getTime() + "&id=" + rowIds[0],
					backdrop:'static'
				});
				$('#selectMenu').button('loading');
			}
		});
		
		//选择角色按钮
		$('#selectRole').click(function(){
			var rowIds = CommUtils.getJqgridSelected("user-grid-table");   
			if (rowIds.length > 1 || rowIds.length == 0){
				CommUtils.commAlert("user-input", "请选择一条记录！");
			}else{
				$('#user-input').removeData("bs.modal");
				$('#selectRole').button('loading');
				$('#user-input').modal({
					remote:'selectRole?timestamp=' + new Date().getTime() + "&id=" + rowIds[0],
					backdrop:'static'
				});
				$('#selectRole').button('loading');
			}
		});
		
		//删除按钮
		$('#delete').click(function(){
			var rowIds = CommUtils.getJqgridSelected("user-grid-table");    
			if (rowIds.length == 0){
				CommUtils.commAlert("user-input", "请选择一条或多条记录！");
			}else{
				var options = new Object; 
				options.modalId = "user-input";
				options.msg = "确认您是否要删除所选中的记录？";
				options.delIds = rowIds;
				options.url = "delete";
				options.gridTableId = "user-grid-table";
				CommUtils.commDelete(options);
			}
		});
	},
	//修改页面加载方法
	loadInputPage: function(){
		
		$('#new').button('reset');
		$('#edit').button('reset');
		
		if ($("#editId").val()){
			$("#input-title").html("修改用户");
		}else{
			$("#input-title").html("新增用户");
		}
		
		$('#user_fmt').validate({
			errorElement: 'div',
			errorClass: 'help-block',
			focusInvalid: true,
			rules:{
				username:{
					required:true,
					remote:{
						url:'validateExist',
						type:'post',
						data:{
							username:function(){
								return $('#username').val();
							},
							id:function(){
								return $('#editId').val();
							}
						}
					}				
				},
				realname:{
					required:true
				},
				email:{
					email:true
				}, 
				passwd: {
					required:true,
				      minlength: 6
			    },
				repasswd: {
			    	required:true,
				      equalTo: "#passwd",
				      minlength: 6
			    }
			},messages:{
				username:{
					required:"帐号名称为必填项",
					remote:"帐号名称已存在"
				},
				realname:{
					required:"真实名为必填项"
				},
				email:{
					email:"Email格式不正确"
				},
				passwd: {
					required:'请输入密码',
					minlength: "密码最少六位"
			    },
				repasswd: {
			    	required:'请再次输入密码',
					equalTo: "两次输入的密码不相同",
					minlength: "密码最少六位"
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
			var isValid = $('#user_fmt').valid();
			if(isValid){
				$.ajax({
					type:'POST',
					url:'save',
					data:$('#user_fmt').serialize(),
					dataType:'json',
					success:function(json){
						if(json.status == "success"){
							$('#user-input').load(contextPath + '/common/success.jsp',
									{msg:'操作已成功，用户"<b>'+$('#username').val()+'"</b>保存成功',
									 seconds:'3000',
									 tips:''},'');
							
							$('#user-grid-table').trigger("reloadGrid");
						}else{
							$('#user-input').load();
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
		
		var rowIds = CommUtils.getJqgridSelected("user-grid-table"); 
		$("#userId").val(rowIds[0]);
		
		$('#save_selectMenu_btn').click(function(){
			var undeterminedNodes = [];
			$(".jstree-undetermined").each(function(index,item) {
				undeterminedNodes.push($(item).parent().parent().attr("id"));
			});
			$("#menuIds").val($('#menu_tree').jstree("get_selected") + "," + undeterminedNodes);
			$.ajax({
				type:'POST',
				url:'saveUserMenuRel',
				data:"userId="+$("#userId").val()+"&menuIds="+$("#menuIds").val(),
				dataType:'json',
				success:function(json){
					if(json.status == "success"){
						$('#user-input').load(contextPath + '/common/success.jsp',
								{msg:'操作已成功',
								 seconds:'3000',
								 tips:''},'');
					}else{
						$('#user-input').load();
					}
				}
			});
		});
	},
	//选择角色加载
	loadSelectRolePage: function(){
		
		$('#selectRole').button('reset');
		
		Role.buildTree();
		
		var rowIds = CommUtils.getJqgridSelected("user-grid-table"); 
		$("#userId").val(rowIds[0]);
		
		$('#save_selectRole_btn').click(function(){
			$("#roleIds").val($('#role_tree').jstree("get_selected"));
			$.ajax({
				type:'POST',
				url:'saveUserRoleRel',
				data:"userId="+$("#userId").val()+"&roleIds="+$("#roleIds").val(),
				dataType:'json',
				success:function(json){
					if(json.status == "success"){
						$('#user-input').load(contextPath + '/common/success.jsp',
								{msg:'操作已成功',
								 seconds:'3000',
								 tips:''},'');
					}else{
						$('#user-input').load();
					}
				}
			});
		});
	}
}



