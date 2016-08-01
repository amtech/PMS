<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SPRING-AGG 演示系统</title>
    <link rel="stylesheet" type="text/css" media="screen" href="static/css/normalize.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="static/css/login.css" />
</head>

<body>
    <div class="login-form-wrap">
        <h1>SPRING-AGG 系统登录</h1>
        <form class="login-form" action="" method="post">
            <label>
                <input type="text" name="username" required placeholder="用户名" value="<shiro:principal/>">
            </label>
            <label>
                <input type="password" name="password" required placeholder="密码">
            </label>
            <label remember>
                <input name="rememberMe" type="checkbox" />&nbsp;自动登录 &nbsp;<span class="error">${error }</span>
            </label>
            <input type="submit" value="登录">
        </form>
    </div>
</body>

</html>