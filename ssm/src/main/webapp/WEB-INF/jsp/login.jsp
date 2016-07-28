<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh">
  <head>
  	<%@ include file="/common/include.jsp"%>
  	<style type="text/css">
  	.loginpage{
  		margin: 100px auto;
  	}
  	</style>
  </head>
	<body class="login-layout loginpage">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="icon-leaf green"></i>
									<span class="red">指南针数据管理系统</span>
									<!-- <span class="white">指南针数据管理系统</span> -->
								</h1>
								<!-- <h4 class="blue">&copy; 指南针数据管理系统</h4>-->
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												请输入您的账号信息
											</h4>

											<div class="space-6"></div>
											<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
												<fieldset>
													<label id="lab-user" class="block clearfix">
														用户名：
														<span class="block input-icon input-icon-right">
															<input id="username" type="text" name="j_username" class="form-control" placeholder="请输入用户名" />
															<i class="icon-user"></i>
														</span>
														<div class="help-block hidden"> 请输入用户名! </div>
													</label>

													<label id="lab-pwd" class="block clearfix">
														密码：
														<span class="block input-icon input-icon-right">
															<input id="passwd" name="j_password" type="password" class="form-control" placeholder="请输入密码" />
															<i class="icon-lock"></i> 
														</span>
														<div class="help-block hidden"> 请输入密码! </div>
													</label>
													
													<div id="login-error" class="alert alert-danger ${param.error == true ? '' : 'hidden' }" >
														用户名或密码不正确！
													</div>
													<div class="space"></div>

													<div class="clearfix">
    													<input class="width-35 pull-left btn btn-sm btn-primary" type="reset" value="重置"/>
    													<input id="btn-login" class="width-35 pull-right btn btn-sm btn-primary" type="submit" value="登录"/>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

											
										</div><!-- /widget-main -->

									</div><!-- /widget-body -->
								</div><!-- /login-box -->

							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

		<script type="text/javascript">
		$(function() {
			$("#btn-login").click(function(){
				var json = {
					'username':$("#username").val(),
					'pwd':$("#passwd").val()
				};
				var isPass = true;
				if(json.username==null||json.username==""){
					isPass = false;
					$("#lab-user").addClass("has-error");
					$("#lab-user div").removeClass("hidden");
				}else{
					$("#lab-user").removeClass("has-error");
					$("#lab-user div").addClass("hidden");
				}
				
				if(json.pwd==null||json.pwd==""){
					isPass = false;
					$("#lab-pwd").addClass("has-error");
					$("#lab-pwd div").removeClass("hidden");
				}else{
					$("#lab-pwd").removeClass("has-error");
					$("#lab-pwd div").addClass("hidden");
				}
				
				if(!isPass){
					return false;
				}
			});
			
			$("body").keydown(function() {
	             if (event.keyCode == "13") {//keyCode=13是回车键
	                 $('#btn-login').click();
	             }
	         });
		});
		</script>
	</body>


</html>