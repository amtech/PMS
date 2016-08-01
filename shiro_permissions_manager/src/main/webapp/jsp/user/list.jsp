<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 用户列表 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user.js"></script>
<style type="text/css">
div[usergrid] {width: 100%; height: 100%;}
</style>
<div usergrid></div>
<div style="display: none;">
    <ul role>
        <c:forEach var="role" items="${roles }">
            <li rid="${role.rid }" role="${role.role }">${role.description }</li>
        </c:forEach>
    </ul>
</div>