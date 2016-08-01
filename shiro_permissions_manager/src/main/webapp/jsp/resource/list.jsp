<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/resource.js"></script>
<style type="text/css">
.w2ui-field input { width: 70%;}
.w2ui-field select { width: 70%;}
</style>
<div style="position: relative; height: 100%; width: 100%;">
    <div id="regrid" style="position: absolute; left: 0px; width: 49.9%; height: 100%;"></div>
    <div id="reform" style="position: absolute; right: 0px; width: 49.9%; height: 100%;">
        <div class="w2ui-page page-0">
            <div class="w2ui-field">
                <label>资源编号:</label>
                <div>
                    <input name="reid" type="text" readonly="readonly"/>
                </div>
            </div>
            <div class="w2ui-field">
                <label>资源名称:</label>
                <div>
                    <input name="name" type="text" maxlength="20"/>
                </div>
            </div>
            <div class="w2ui-field">
                <label>资源类型:</label>
                <div>
                    <select name="type"></select>
                </div>
            </div>
            <div class="w2ui-field">
                <label>权限标识:</label>
                <div>
                     <input name="permissions" type="text" maxlength="20"/>
                </div>
            </div>
            <div class="w2ui-field">
                <label>资源路径:</label>
                <div>
                     <input name="url" type="text" maxlength="50"/>
                </div>
            </div>
            <div class="w2ui-field">
                <label>上级:</label>
                <div>
                    <select name="pid"></select>
                </div>
            </div>
            <div class="w2ui-field">
                <label>排序标识:</label>
                <div>
                     <input name="sorted" type="text" maxlength="10"/>
                </div>
            </div>
        </div>

        <div class="w2ui-buttons">
            <button class="btn" name="reset">重置</button>
            <button class="btn" name="save">确认</button>
        </div>
    </div>
</div>