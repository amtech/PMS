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
												Session-Timeout
											</span>
											会话超时
										</h1>

										<hr>
										<h3 class="lighter smaller">可能由于长时间没有操作，所以导致会话及登录信息失效。</h3>

										<div>
											<div class="space"></div>
											<h4 class="smaller">尝试以下操作:</h4>
											<div class="space"></div>
											<ul class="list-unstyled spaced inline bigger-110 margin-15">
												<li>
													<i class="icon-hand-right blue"></i>
													单击重新登录按钮确保登录信息正常
												</li>
											</ul>
										</div>

										<hr>
										<div class="space"></div>

										<div class="center">
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
