<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringAGG 演示用例</title>
<!-- 图标字体 -->
<link rel="stylesheet" type="text/css" media="screen" href="static/font-awesome/font-awesome.min.css" />
<!-- W2UI -->
<link rel="stylesheet" type="text/css" media="screen" href="static/w2ui/w2ui-1.4.2.css" />
<!-- INDEX.CSS -->
<link rel="stylesheet" type="text/css" media="screen" href="static/css/index.css" />
<!-- JavaScript -->
<script type="text/javascript">
var APP = window['APP'] || {};
// - 全局路径 ${pageContext.request.contextPath}
APP.PATH = '<%=request.getContextPath() %>';
APP.USER = '<shiro:principal/>';
</script>
<script type="text/javascript" src="static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="static/w2ui/w2ui-1.4.2.min.js"></script>
<!-- 首頁JS -->
<script type="text/javascript" src="static/js/index.js"></script>
</head>
<body>
<div id="main" style="width: 100%; height: 100%"></div>
<div style="display: none">
    <ul menu>
        <c:forEach var="menu" items="${menus }">
            <li mid="${menu.reid }" url="${menu.url }" text="${menu.name }" >${menu.name }</li>
        </c:forEach>
    </ul>
</div>
</body>
</html>