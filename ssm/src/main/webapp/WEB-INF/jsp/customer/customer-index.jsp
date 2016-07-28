<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<%@ include file="/common/include.jsp"%>
		<script type="text/javascript" src="${contextPath}/js/customer/customer.js"></script>
	</head>
	<body>
		<%@ include file="/common/navbar.jsp"%>
		<div id="main-container" class="main-container">
			<%@ include file="/common/siderbar.jsp"%>
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
					<ul class="breadcrumb">
						<li>
							<a href="#">系统管理</a>
						</li>
						<li class="active">
							客户管理
						</li>
					</ul>
				</div>
				<div class="page-content">
					<div class="row">
						
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form role="form" class="form-horizontal">
								<div class="form-group">
									<div class="col-md-2">
										<input id="search_customerid" type="text" placeholder="客户编号" name="customerid" value=""/>
									</div>
									<div class="col-md-2">
										<input id="search_customername" type="text" placeholder="客户名称" name="customername" value=""/>
									</div>
									<div class="col-md-2">
										<select id="search_customertype" name=customertype style="height:28px">
											<option value="">请选择客户类型</option>
											<option value="01">企业</option>
											<option value="02">个人</option>
										</select>
									</div>
									<div class="col-md-2 col-md-offset-4">
										<button id="search" class="btn btn-info btn-sm" type="button"><i class="icon-search icon-on-right"></i>查询</button>
									</div>
								</div>
								
								<div class="hr hr10 hr-dotted"></div>
								<div class="form-group">
									<div class="col-xs-12">
										<button data-target="#customer-input" id="new" data-toggle="modal" class="btn btn-info btn-sm" type="button"><i class="icon-plus bigger-110"></i>新建</button>
										<button data-target="#customer-input" id="edit" data-toggle="modal" class="btn btn-info btn-sm" type="button"><i class="icon-edit bigger-110"></i>修改</button>
										<button data-target="#customer-input" id="delete" data-toggle="modal" class="btn btn-danger btn-sm" type="button"><i class="icon-trash bigger-110"></i>删除</button>
									</div>
								</div>
							</form>
					
							<table id="customer-grid-table"></table>
							<div id="grid-pager"></div>
							<script type="text/javascript">
									var $path_base = "/";//this will be used in gritter alerts containing images
								</script>

							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		
		<div id="customer-input" class="modal"></div>
	
		<script type="text/javascript">
			$(document).ready(function(){
				Customer.loadPage();
			});

		</script>
	</body>
</html>

