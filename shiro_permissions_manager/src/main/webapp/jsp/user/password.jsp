<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 用户列表 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user.js"></script>
<style type="text/css">
div[userform] {
	width: 40%;
	height: 100%;
}
</style>
<div userform>
    <div class="w2ui-page page-0">
        <div class="w2ui-field w2ui-span5" style="clear: both">
            <label>密码:</label>
            <div>
                <input name="password" type="password" maxlength="16" style="width: 80%;">
            </div>
        </div>
        <div class="w2ui-field w2ui-span5" style="clear: both">
            <label>确认密码:</label>
            <div>
                <input name="eqpwd" type="password" maxlength="16" style="width: 80%;">
            </div>
        </div>
    </div>
    <div class="w2ui-buttons">
        <button class="btn" name="reset">重置</button>
        <button class="btn" name="save">确认</button>
    </div>
</div>
<script type="text/javascript">
var uid = '${user.uid }';
w2ui['pwd_form'] && w2ui['pwd_form'].destroy();
$('div[userform]').w2form({
	name: 'pwd_form',
	header: '修改密码',
	fields: [
		{ name: 'password', type: 'password', required: true},
		{ name: 'eqpwd', type: 'password', required: true},
	],
	actions: {
    	reset: function(e){
    		$grid.selectNone();
    		this.clear();
    	},
    	save: function(e){
    		var ret = this.validate();
    		if (!ret.length) {
    			$.post(APP.PATH + '/user/' + uid + '/password','newPassword=' + this.record['password'],function(data){
    				if (data && data.success) {
    					w2alert(data.message, '处理结果', function(){
    						window.location.href = APP.PATH + '/logout';
    					});
    				} else {
    					data && data.message && w2alert(data.message);	
    				}
    			},'json');
    		}
    	}
    },
    onValidate: function(e) {
    	// 密码的验证补充
    	var pwd = this.record['password'];
    	if (pwd != '' && pwd.length < 4) {
    		e.errors.push({
    			error: '密码至少需要 4 个字符',
    			field: this.get('password')
    		});
    	}
    	if (pwd != this.record['eqpwd']) {
    		e.errors.push({
    			error: '两次输入密码不一致',
    			field: this.get('eqpwd')
    		});
    	}
    }
});
</script>