<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/role.js"></script>

<style type="text/css">
.w2ui-field input {
	width: 70%;
}

.w2ui-field select {
	width: 70%;
}
</style>

<div style="position: relative; height: 100%; width: 100%;">
    <div id="rolegrid" style="position: absolute; left: 0px; width: 33.3%; height: 100%;"></div>
    <div id="roleform" style="position: absolute; right: 33.33%; width: 33.3%; height: 100%;">
        <div class="w2ui-page page-0">
            <div class="w2ui-field">
                <label>角色名称:</label>
                <div>
                    <input name="role" type="text" maxlength="20"/>
                </div>
            </div>
            <div class="w2ui-field">
                <label>角色描述:</label>
                <div>
                    <input name="description" type="text" maxlength="20"/>
                </div>
            </div>
            <div class="w2ui-field">
                <label>角色资源:</label>
                <div>
                    <input name="resname" type="text" maxlength="20" readonly="readonly"/>
                </div>
            </div>
        </div>
        <div class="w2ui-buttons">
            <button class="btn" name="reset">清空</button>
            <button class="btn" name="save">确认</button>
        </div>
    </div>
    <div id="resgrid" style="position: absolute; right: 0px; width: 33.3%; height: 100%;"></div> 
</div>

<div style="display: none;">
    <ul res>
        <c:forEach var="res" items="${resources }">
            <li reid="${res.reid }" text="${res.name }" permissions="${res.permissions }">${res.name }</li>
        </c:forEach>
    </ul>
</div>