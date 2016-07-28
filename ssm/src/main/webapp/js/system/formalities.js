var Formalities = {
	//列表页面加载方法
	loadPage : function() {
		jQuery("#grid-table").jqGrid({
			url:'list',
		    datatype: 'json',     
			mtype: "POST",
			height: "100%",
			rownumbers: true,
			rownumWidth:50,
			colNames:['id','','入离职手续名称', '入离职手续类型','备注', '状态', '创建人','创建时间','操作'],
			colModel:[
			    {name:'id',index:'id',width:90,hidden:true},
			    {name:'action',index:'action',width:25,hidden:false,fixed:true},
			    {name:'formalitiesName',index:'formalitiesName',width:90,editable:false},
			    {name:'formalitiesType',index:'formalitiesType',width:90,editable:false,formatter:getHideListValue,formatoptions: {code:'FORMALITIES_TYPE'}},
			    {name:'remark',index:'remark',width:90,editable:false},
				{name:'isValid',index:'isValid', width:90,editable:false,formatter:statusChange},
				{name:'createUserName',index:'createUserName',width:90},
				{name:'createDate',index:'createDate',width:90,formatter:dateFormatter},
				{name:'operation',index:'operation', width:90}
			], 
			viewrecords : true,
			rowNum:10,
			rowList:[10,20,30],
			pager : "#grid-pager",
			altRows: true,
			autowidth: true,
			autoScroll: false,
			caption: "入离职手续列表",
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
		    			var cellHtml = "<button onclick=\"Formalities.changeValid('" + entity.id 
									 + "','0')\" class=\"btn btn-info btn-minier\" type=\"button\">"
									 + "<i class=\"icon-lock bigger-110\"></i>无效</button>";
		    			jQuery("#grid-table").jqGrid('setRowData',rowid,{operation:cellHtml});
		    		break;
		    		case '0':
		    			var cellHtml = "<button onclick=\"Formalities.changeValid('" + entity.id 
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
				'formalitiesName':$("#formalitiesName").val(),
				'formalitiesType':$("#formalitiesType_key").val()
			};
			jQuery("#grid-table").jqGrid('setGridParam', { postData: json }).jqGrid('setGridParam', { 'page': 1 })
			.trigger("reloadGrid");
		});
	
		//新增按钮
		$('#new').click(function(){
			$('#formalities-input').removeData("bs.modal");
			$('#formalities-input').modal({
				remote:'input?timestamp=' + new Date().getTime(),
				backdrop:'static'
			});
			$('#new').button('loading');
		});
		
		//修改按钮
		$('#edit').click(function(){
			
			var rowIds = CommUtils.getJqgridSelected("grid-table");    
			
			if (rowIds.length > 1 || rowIds.length == 0){
				CommUtils.commAlert("formalities-input", "修改记录时只能选择一条记录！");
			}else{
				$('#formalities-input').removeData("bs.modal");
				$('#formalities-input').modal({
					remote:'input?timestamp=' + new Date().getTime() + "&id=" + rowIds[0],
					backdrop:'static'
				});
				$('#edit').button('loading');
			}
		});

		//删除按钮
		$('#delete').click(function(){
			var rowIds = CommUtils.getJqgridSelected("grid-table");
			if (rowIds.length == 0){
				CommUtils.commAlert("formalities-input", "请选择一条或多条记录！");
			}else{
				var options = new Object;
				options.modalId = "formalities-input";
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
			$("#input-title").html("修改入离职手续");
		}else{
			$("#input-title").html("新增入离职手续");
		}
		
		$('#formalities_fmt').validate({
			errorElement: 'div',
			errorClass: 'help-block',
			focusInvalid: true,
			rules:{
				formalitiesName:{
					required:true,
					remote:{
						url:'validateExist',
						type:'post',
						data:{
							formalitiesName:function(){
								return $('#formalitiesNameVal').val();
							},
							id:function(){
								return $('#editId').val();
							}
						}
					}				
				},
				formalitiesType:{
					required:true
				}
			},messages:{
				formalitiesName:{
					required:"入离职手续名称为必填项",
					remote:"入离职手续名称已存在"
				},
				formalitiesType:{
					required:"入离职手续类型为必选项"
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
			var isValid = $('#formalities_fmt').valid();
			if(isValid){
				$.ajax({
					type:'POST',
					url:'save',
					data:$('#formalities_fmt').serialize(),
					dataType:'json',
					success:function(json){
						if(json.status == "success"){
							$('#formalities-input').load(contextPath + '/common/success.jsp',
									{msg:'操作已成功，入离职手续"<b>'+$('#formalitiesNameVal').val()+'"</b>保存成功',
									 seconds:'3000',
									 tips:''},'');
							
							$('#grid-table').trigger("reloadGrid");
						}else{
							$('#formalities-input').load();
						}
					}
				});
			}
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