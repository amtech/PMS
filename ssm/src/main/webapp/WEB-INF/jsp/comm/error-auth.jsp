<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<%@ include file="/common/include.jsp"%>
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="error-container">
									<div class="well">
										<h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="icon-sitemap"></i>
												Error-auth
											</span>
											权限不够
										</h1>

										<hr>
										<h3 class="lighter smaller">请尝试以下操作：</h3>

										<div>
											<div class="space"></div>
											<ul class="list-unstyled spaced inline bigger-110 margin-15">
												<li>
													<i class="icon-hand-right blue"></i>
													确保浏览器的地址栏中显示的网站地址的拼写和格式正确无误
												</li>

												<li>
													<i class="icon-hand-right blue"></i>
													如果通过单击链接达到了该网页，请与网站管理员联系，权限不够
												</li>

												<li>
													<i class="icon-hand-right blue"></i>
													单击后退按钮尝试另一个链接
												</li>
												<li>
													<i class="icon-hand-right blue"></i>
													单击重新登录按钮确保可以正常使用该功能
												</li>
											</ul>
										</div>

										<hr>
										<div class="space"></div>

										<div class="center">
											<a href="javascript:history.go(-1);" class="btn btn-grey">
												<i class="icon-arrow-left"></i>
												后退
											</a>

											<a href="${contextPath}/login" class="btn btn-primary">
												<i class="icon-refresh"></i>
												重新登陆
											</a>
										</div>
									</div>
								</div><!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->

	</body>
</html>
