<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../shared/css.jsp" />
<title>拒绝并修改后重新提交</title>
</head>
<body class="grey-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<h4 class="text-warning">本手工账单保留原账单除“金额”和“缴费期限”外的所有信息，包括覆盖起止日期，提交后原账单状态将变为“拒绝”</h4>
				<div class="ibox-content">
					<form method="post" class="form-horizontal" id="signupForm">
						<input type="hidden" id="billId" value="${bill.billId }">
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">订单类型</label>
							</div>
							<div class="col-sm-3">
								<input type="hidden" id="billType" value="${bill.billType }">
								<input type="text" class="form-control" readonly="readonly" value="${billType }">
							</div>
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">金额</label>
							</div>
							<div class="col-sm-3">
								<input type="number" class="form-control" id="amount" name="amount" value="${bill.amount }">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">账单来源</label>
							</div>
							<div class="col-sm-3">
								<input type="hidden" id="billSource" value="${bill.billSource }">
								<input type="text" class="form-control" readonly="readonly" value="${billSource }">
							</div>
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">客户</label>
							</div>
							<div class="col-sm-3">
								<input type="hidden" id="companyId" value="${bill.companyId }">
								<input type="text" class="form-control" id="companyName" readonly="readonly" value="${bill.companyName }">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">关联来源编号</label>
							</div>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="billNoOut" readonly="readonly" value="${bill.billNoOut }">
							</div>
						</div>
						<div class="form-group"	<c:if test="${bill.billType eq 2}">style="display:none;"</c:if>>
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">缴费期限</label>
							</div>
							<div class="col-sm-3">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input name="deadline" type="text" class="datepicker form-control" id="deadline"
									value='<fmt:formatDate value="${bill.deadline }" pattern="yyyy-MM-dd"/>'>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">账单详情</label>
							</div>
							<div class="col-sm-9">
								<textarea class="form-control" rows=7 id="description" name="description">${bill.description }</textarea>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2">
								<input class="btn btn-primary" type="submit" value="提交">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../shared/js.jsp" />
<script type="text/javascript" src="<%=basePath %>/myjs/bill.update.js"></script>
</body>
</html>