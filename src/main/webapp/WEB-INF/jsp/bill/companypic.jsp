<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>

<div class="modal fade in" id="companyModal" tabindex="-1" role="dialog"
	aria-labelledby="companyModalLabel" aria-hidden="true"
	style="display: none; padding-right: 6px;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title" id="companyModalLabel">公司查找</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="客户名称" id="companyNameText">
					</div>
					<div class="col-sm-3">
						<input type="button" class="btn" onclick="searchCompany()"
							value="查找">
					</div>
					<div class="col-sm-9 m-1-n">
						<select class="form-control" multiple
							style="height: 100px; margin-top: 10px;" id="companySelect">
							<option value=""></option>
						</select>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal" id="companyConfirm">确定</button>
			</div>
		</div>
	</div>
</div>

