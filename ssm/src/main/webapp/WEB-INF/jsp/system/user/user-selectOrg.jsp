<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${contextPath}/js/system/org.js"></script>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
			<span id="ui-id-26" class="ui-dialog-title">
				<div class="widget-header widget-header-small">
					<h4 class="smaller">
						<i class="icon-ok" id="input-title">选择组织机构</i>
					</h4>
				</div>
			</span>
		</div>
		<form id="selectOrg_fmt" role="form" class="form-horizontal">
		<input type="hidden" name="userId" id="userId">
		<input type="hidden" id="orgIds" name="orgIds" value="${orgIds }">
		<div class="modal-body">
			<div id="org_tree" class="tree tree-selectable"></div>
		</div>
		<div class="modal-footer">
			<div class="col-md-offset-3 col-md-9">
				<button class="btn btn-sm btn-primary" type="button" id="save_selectOrg_btn">
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
		User.loadSelectOrgPage();
	});
</script>
