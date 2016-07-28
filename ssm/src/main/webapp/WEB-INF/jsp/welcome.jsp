<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<%@ include file="/common/include.jsp"%>
		<script type="text/javascript" src="${contextPath}/js/system/baseData.js"></script>
	</head>
	<body>
		<%@ include file="/common/navbar.jsp"%>
		<div class="main-container-inner">
			<%@ include file="/common/siderbar.jsp"%>
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}</script>
					<!-- <img alt="" src="${contextPath}/images/demo.jpg" height="687px" width="1159px">-->
				</div>
			</div>
		</div>
		
		<div id="baseData-input" class="modal"></div>
	
		<script type="text/javascript">
			$(document).ready(function(){
				BaseData.loadPage();
			});
		</script>
	</body>
</html>

