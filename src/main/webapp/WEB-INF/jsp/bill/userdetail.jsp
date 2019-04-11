<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>账单详情</title>
<style>
	table a:hover{text-decoration: underline;}
	table a{margin-left: 10px}
</style>
</head>
<body class="grey-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						<normal>账单基本信息</normal>
					</h5>
				</div>
				<div class="ibox-content">
					<form method="get" class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-1 control-label">账单编号</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.billId}">
							</div>
							<label class="col-sm-2 control-label">账单类型</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${billType}">
							</div>
							<label class="col-sm-2 control-label">账单状态</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${billStatus}" id="billStatus">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label">客户名称</label>
							<div class="col-sm-2">
								<input type="hidden" id="companyId" value="${etopBill.companyId }">
								<input type="text" class="form-control" id="companyName" readonly="readonly" value="${companyName}">
							</div>
							<label class="col-sm-2 control-label">账单来源</label>
							<div class="col-sm-2">
								<input type="text" class="form-control"  readonly="readonly"value="${billSource}">
							</div>
							<label class="col-sm-2 control-label">账单来源编号</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.billNoOut}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label">金额</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.amount }">
							</div>
							<label class="col-sm-2 control-label">滞纳金</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.overdueFine }" id="overdueFine">
							</div>
							<label class="col-sm-2 control-label">滞纳金比例（每日）</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.overdueRate}" id="amountPaid">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label">已付金额</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.amountPaid}" id="amountPaid">
							</div>
							<label class="col-sm-2 control-label">金额减免</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.accountRemission}" id="accountRemission">
							</div>
							<label class="col-sm-2 control-label">滞纳金减免</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.overdueRemission}" id="overdueRemission">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label">创建时间</label>
							<div class="col-sm-2">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input name="Field_KH_DFJL_RiQi" type="text" class="form-control" readonly="readonly"
										value='<fmt:formatDate value="${etopBill.createdTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'>
								</div>
							</div>
							<label class="col-sm-2 control-label">缴费期限</label>
							<div class="col-sm-2">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input name="Field_KH_DFJL_RiQi" type="text" class="form-control" readonly="readonly" id="deadline"
										value='<fmt:formatDate value="${etopBill.deadline}" pattern="yyyy-MM-dd HH:mm:ss"/>'>
								</div>
							</div>
							<label class="col-sm-2 control-label">支付时间</label>
							<div class="col-sm-2">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="payTime" name="Field_KH_DFJL_RiQi" type="text" class="form-control" readonly="readonly"
										value='<fmt:formatDate value="${etopBill.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label">详细信息</label>
							<div class="col-sm-10">
								<textarea class="form-control" readonly="readonly" rows=10>${etopBill.description}</textarea>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- 支付表格 -->
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						<normal>账单支付</normal>
					</h5>
				</div>
				<div class="ibox-content">
					<div class="row">
						<div class="example-wrap col-sm-12">
			
							<table id="payListTable" data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="pay/list.do?billId=${etopBill.billId}"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-pagination="true"
					               data-page-list="[5]"
					              >
								<thead>
									<tr>
										<th data-field="payId">支付单号</th>
										<th data-field="payType" data-formatter="payTypeFormatter">支付类型</th>
										<th data-field="amount">金额</th>
										<th data-field="payNoOut">凭证号</th>
										<th data-field="payTime">支付时间</th>
										<th data-field="recorderName">记账员</th>
										<c:if test="${payReadonly eq false}">
											<th data-field="payId" data-formatter="payControlFormatter"></th>
										</c:if>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-12" <c:if test="${etopBill.billType eq 2}">style="display: none"</c:if>>
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						<normal>金额减免</normal>
					</h5>
				</div>
				<div class="ibox-content">
					<div class="row">
						<div class="example-wrap col-sm-12">

							<table id="reliefListTable" data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="relief/list.do?billId=${etopBill.billId}"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-pagination="true"
					               data-page-list="[5]"
					              >
								<thead>
									<tr>
										<th data-field="reliefId">申请编号</th>
										<th data-field="amountRemission">本金减免（元）</th>
										<th data-field="overdueRemission">滞纳金减免（元）</th>
										<th data-field="reason">减免理由</th>
										<th data-field="applicantName">申请人</th>
										<th data-field="applyTime">申请时间</th>
										<th data-field="auditStatus" data-formatter="auditStatusFormatter2">审核状态</th>
										<c:if test="${reliefReadonly eq false}">
											<th data-field="reliefId" data-formatter="reliefControlFormatter"></th>
										</c:if>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-12" <c:if test="${etopBill.billType eq 2}">style="display: none"</c:if>>
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						<normal>延期付款</normal>
					</h5>
				</div>
				<div class="ibox-content">
					<div class="row">
						<div class="example-wrap col-sm-12">

							<table id="delayListTable" data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="delay/list.do?billId=${etopBill.billId}"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-pagination="true"
					               data-page-list="[5]"
					              >
								<thead>
									<tr>
										<th data-field="delayId">申请编号</th>
										<th data-field="delayTime">延迟时间</th>
										<th data-field="reason">延迟理由</th>
										<th data-field="applicantName">申请人</th>
										<th data-field="applyTime">申请时间</th>
										<th data-field="auditStatus" data-formatter="auditStatusFormatter2">审核状态</th>
										<c:if test="${delayReadonly eq false}">
											<th data-field="delayId" data-formatter="delayControlFormatter"></th>
										</c:if>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../shared/js.jsp" />
<script type="text/javascript" src="<%=basePath %>/myjs/bill.format.js"></script>

</body>
</html>