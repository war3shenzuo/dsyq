<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>

<div class="modal fade in" id="categoryModal" tabindex="-1" role="dialog"
	aria-labelledby="categoryModalLabel" aria-hidden="true"
	style="display: none; padding-right: 6px;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title" id="categoryModalLabel">类别查找</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="商品大类" id="categoryText">
					</div>
					<div class="col-sm-3">
						<input type="button" class="btn" onclick="searchCategory()"
							value="查找">
					</div>
					<div class="col-sm-9 m-1-n">
						<select class="form-control" multiple
							style="height: 100px; margin-top: 10px;" id="categorySelect">
							<option value=""></option>
						</select>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal" id="categoryConfirm">确定</button>
			</div>
		</div>
	</div>
</div>

