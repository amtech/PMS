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
		<form id="org_fmt" role="form" class="form-horizontal">
		<input type="hidden" name="id" id="editId" value="${org.id}">
		<input id="orgLevel" name="orgLevel" type="hidden" value="${org.orgLevel}"/>
		<input id="superOrgId" name="superOrgId" type="hidden" value="${org.superOrgId}"/>
		<input id="hasChild" name="hasChild" type="hidden" value="${org.hasChild}"/>
		<input id="oldorgType" name="oldorgType" type="hidden" value="${org.orgType}"/>
		<input id="oldisValid" name="oldisValid" type="hidden" value="${org.isValid}"/>
		<input id="oldProviceId" name="oldProviceId" type="hidden" value="${org.proviceId}"/>
		<input id="oldCityId" name="oldCityId" type="hidden" value="${org.cityId}"/>
		<div class="modal-body">
			<div class="form-group">
				<div class="col-xs-6 col-sm-3">
					<label class="col-sm-12 control-label no-padding-right" for="orgCode">编号</label>
				</div>
				<div class="col-xs-6 col-sm-9">
					<label style="float: left;padding-top: 5px;"  id="superCode"></label>
					<input type="hidden" id="orgCode" name="orgCode" value="${org.orgCode }" />
					<input type="text" id="orgOwnCode" name="orgOwnCode" placeholder="请输入组织机构编号" class="col-xs-5 col-sm-5" value=""/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-6 col-sm-3">
					<label class="col-sm-12 control-label no-padding-right" for="orgName">名称</label>
				</div>
				<div class="col-xs-6 col-sm-9">
					<input type="text" name="orgName" id="orgName" class="col-xs-12 col-sm-8" placeholder="请输入组织机构名称 " value="${org.orgName }"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-6 col-sm-3">
					<label class="col-sm-12 control-label no-padding-right" for="cityId">城市</label>
				</div>
				<div class="col-xs-6 col-sm-9">
					 <select id="proviceId" name="proviceId">  
				          <option value="-1">请选择省份</option>  
				     </select>  
				     <select id="cityId" name="cityId">  
				          <option value="-1">请选择城市</option>  
				     </select> 
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-6 col-sm-3">
					<label class="col-sm-12 control-label no-padding-right" for="orgType">组织机构类型</label>
				</div>
				<div class="col-xs-6 col-sm-9">
					<xpress:dataBase resultType="select" code="ORG_TYPE" id="orgType" name="orgType" value="${org.orgType}"></xpress:dataBase>
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
				<button class="btn btn-sm btn-primary btn-grey" data-dismiss="modal">
					关闭
				</button>
			</div>
		</div>
		</form>
	</div>
</div>
<script type="text/javascript">

	$(document).ready(function(){
		Org.loadInputPage();
	});
</script>
