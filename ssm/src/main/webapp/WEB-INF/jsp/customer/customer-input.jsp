<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
			<span id="ui-id-26" class="ui-dialog-title">
				<div class="widget-header widget-header-small">
					<h4 class="smaller">
						<i class="icon-ok" id="input-title"></i>
					</h4>
				</div>
			</span>
		</div>
		<form id="customer_fmt" role="form" class="form-horizontal">
		<input type="hidden" name="customerid" id="customerid" value="${customer.customerid}">
		<div class="modal-body">
			<div class="form-group">
				<div class="col-xs-6 col-sm-3">
					<label class="col-sm-12 control-label no-padding-right" for="username">客户名称</label>
				</div>
				<div class="col-xs-6 col-sm-9">
					<input type="text" name="username" id="username" class="col-xs-12 col-sm-8" placeholder="请输入帐号名称" value="${customer.username }"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-6 col-sm-3">
					<label class="col-sm-12 control-label no-padding-right" for="realname">真实名</label>
				</div>
				<div class="col-xs-6 col-sm-9">
					<input type="text" name="realname" id="realname" class="col-xs-12 col-sm-8" placeholder="请输入真实名" value="${customer.realname }"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-6 col-sm-3">
					<label class="col-sm-12 control-label no-padding-right" for="passwd">密码</label>
				</div>
				<div class="col-xs-6 col-sm-9">
					<input type="password" name="passwd" id="passwd" class="col-xs-12 col-sm-8" placeholder="请输入密码"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-6 col-sm-3">
					<label class="col-sm-12 control-label no-padding-right" for="repasswd">重复密码</label>
				</div>
				<div class="col-xs-6 col-sm-9">
					<input type="password" name="repasswd" id="repasswd" class="col-xs-12 col-sm-8" placeholder="请再次输入密码"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-6 col-sm-3">
					<label class="col-sm-12 control-label no-padding-right" for="email">Email</label>
				</div>
				<div class="col-xs-6 col-sm-9">
					<input type="text" name="email" id="email" class="col-xs-12 col-sm-8" placeholder="请输入Email" value="${customer.email }"/>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<div class="col-md-offset-3 col-md-9">
				<button class="btn btn-sm btn-primary" type="button" id="save_btn">
					<i class="icon-ok bigger-110"></i>
					保存
				</button>
				&nbsp; &nbsp; &nbsp;
				<button class="btn btn-sm btn-primary btn-grey" data-dismiss="modal" id="btn_close">
					关闭
				</button>
			</div>
		</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		customer.loadInputPage();
	});
</script>
