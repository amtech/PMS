<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<%@ include file="/common/include.jsp"%>
		<script type="text/javascript" src="${contextPath}/js/system/org.js"></script>
	
	</head>
	<body>
		<%@ include file="/common/navbar.jsp"%>
		<div class="main-container-inner">
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
							组织机构管理
						</li>
					</ul>
				</div>

				<div class="page-content">
					
					<div class="row">
						<div class="col-xs-12">
							<div class="row">
								<div class="col-sm-3">
									<div class="widget-box">
										<div class="widget-header header-color-blue2">
											<h4 class="lighter smaller">组织机构</h4>
											<span>
												<input type="text" id="orgSearchKey" placeholder="查询" class="col-xs-5 col-sm-5" style="float: right; margin-right: 3px;margin-top: 3px;"/>
											</span>
										</div>
		
										<div class="widget-body">
											<div class="widget-main padding-8">
												<div id="org_tree" class="tree"></div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-sm-9" id="right_grid">
									<form role="form" class="form-horizontal">
										<div class="form-group">
											
											<div class="col-xs-6">
												<button data-target="#org-input" id="new" data-toggle="modal" class="btn btn-info btn-sm" type="button"><i class="icon-plus bigger-110"></i>新建</button>
												<button data-target="#org-input" id="edit" data-toggle="modal" class="btn btn-info btn-sm btn-green" type="button"><i class="icon-edit bigger-110"></i>修改</button>
												<button data-target="#org-input" id="delete" data-toggle="modal" class="btn btn-danger btn-sm " type="button"><i class="icon-trash bigger-110"></i>删除</button>
											</div>
											<input id="orgId" name="orgId" type="hidden" value=""/>
											<input id="oldorgLevel" name="oldorgLevel" type="hidden" value=""/>
											<input id="oldorgCode" name="oldorgCode" type="hidden" value=""/>
										</div>
									</form>
									<div class="widget-box">
										<xpress:dataBase resultType="hideList" code="ORG_TYPE"></xpress:dataBase>
										<table id="grid-table"></table>
										<div id="grid-pager"></div>
										<script type="text/javascript">
												var $path_base = "/";//this will be used in gritter alerts containing images
										</script>
										<!-- PAGE CONTENT ENDS -->
									</div>
									
								</div><!-- /span -->
									
							</div>
							<script type="text/javascript">
								var $assets = "assets";//this will be used in fuelux.tree-sampledata.js
							</script>
							
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
			
		</div>

		<!-- inline scripts related to this page -->
		
		<div id="org-input" class="modal"></div>

		<script type="text/javascript">
		jQuery(function($){
		
			Org.loadPage();
		});
			
		</script>
	</body>
</html>

