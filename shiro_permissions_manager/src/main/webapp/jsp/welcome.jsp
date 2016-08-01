<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<style type="text/css">
div[welcome] {padding-left: 5px;}
</style>
<div welcome>
    <h2>欢迎 [<shiro:principal/>] 登录演示系统， <a class="change" href="#">修改密码</a> | <a href="${pageContext.request.contextPath}/logout">退出</a> </h2>
    <hr />
         拥有的角色： ${roles }<br/>
         拥有的权限： ${permissions }<br/>
</div>
