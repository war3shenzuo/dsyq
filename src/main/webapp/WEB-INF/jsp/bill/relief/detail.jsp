<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<jsp:include page="/WEB-INF/jsp/shared/css.jsp" />
	<title>账单减免申请</title>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form method="get" class="form-horizontal">
						<div class="row">
							<label class="col-sm-2 control-label">账单编号</label>
							<div class="col-sm-4">
								<input type="text" class="form-control">
							</div>
							<label class="col-sm-2 control-label">申请状态</label>
							<div class="col-sm-4">
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">本金减免</label>
							<div class="col-sm-4">
								<input type="text" class="form-control">
							</div>
							<label class="col-sm-2 control-label">滞纳金减免</label>
							<div class="col-sm-4">
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">减免理由</label>
							<div class="col-sm-10">
								<textarea class="form-control" rows=10></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">申请人</label>
							<div class="col-sm-4">
								<input type="text" class="form-control">
							</div>
							<label class="col-sm-2 control-label">申请时间</label>
							<div class="col-sm-4">
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">审批人</label>
							<div class="col-sm-4">
								<input type="text" class="form-control">
							</div>
							<label class="col-sm-2 control-label">审批时间</label>
							<div class="col-sm-4">
								<input type="text" class="form-control">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/shared/js.jsp" />
</body>
</html>