<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<jsp:include page="/WEB-INF/jsp/shared/css.jsp" />
	<title>批量付款</title>
	<style>
		#billListTable a:hover{text-decoration: underline;}
	</style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
					<div class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-1 control-label">客户</label>
							<div class="col-sm-6">
								<input type="hidden" id="companyId" value="0">
								<input type="text" class="form-control" id="companyName" readonly="readonly">
							</div>
							<div class="col-sm-2">
								<a id="findCompany" class="btn btn-info" data-toggle="modal" data-target="#companyModal">客户查找</a>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label">账单号</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="billId">
							</div>
							<div class="col-sm-2">
								<a id="searchBill" class="btn btn-info">账单查找</a>
							</div>
						</div>
						<div class="hr-line-dashed" style="clear: both;"></div>
						
						<div class="form-group">
							<label class="col-sm-1 control-label">总金额</label>
							<div class="col-sm-3">
								<div class="input-group">
									<span class="input-group-addon">￥</span>
									<input type="number" class="form-control" id="amount" name="amount">
								</div>
							</div>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="capital-amount" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
								<div class="col-sm-4"  style="text-align:center ;color:#FF0000 ;">
									当该笔费用为对外支出时，请填写负数。
								</div>
						</div>
						<div class="form-group" id="payType">
							<label class="col-sm-1 control-label">方式</label>
							<div class="radio i-checks col-sm-2">
								<label><input type="radio" value="1" name="a" checked="">银行卡</label>
							</div>
							<div class="radio i-checks col-sm-2">
								<label><input type="radio" value="3" name="a">支付宝</label>
							</div>
							<div class="radio i-checks col-sm-2">
								<label><input type="radio" value="4" name="a">微信</label>
							</div>
							<div class="radio i-checks col-sm-2">
								<label><input type="radio" value="5" name="a">现金</label>
							</div>
							<div class="radio i-checks col-sm-2">
								<label><input type="radio" value="9" name="a">其他</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label">凭证号</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="payNoOut">
							</div>
						</div>
						<div class="hr-line-dashed" style="clear: both;"></div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="example-wrap">
						<table id="billListTable"
                           	   data-mobile-responsive="true"
				               data-toggle="table"
				               data-url="../list2.do"
				               data-data-type="json"
				               data-side-pagination="server"
				               data-pagination="true"
				               data-query-params = "queryParams"
				               data-page-list="[10, 20, 50]"
				              >
							<thead>
								<tr>
									<th data-field="billId">账单号</th>
									<th data-field="billType" data-formatter="billTypeFormatter">账单类型</th>
									<th data-field="amount">金额</th>
									<th data-field="overdueFine">滞纳金</th>
									<th data-field="accountRemission">本金减免</th>
									<th data-field="overdueRemission">滞纳金减免</th>
									<th data-field="amountPaid">已支付</th>
									<th data-field="billSource" data-formatter="billSourceFormatter">订单来源</th>
									<th data-field="createdTime">生成时间</th>
									<th data-field="description" data-width = "25%">详情</th>
									<th data-field="billId" data-formatter="inputFormatter">支付</th>
									<th>大写</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="form-horizontal">
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<div class="col-sm-4">
								<a class="btn btn-primary" onclick="submit()">提交</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/bill/companypic.jsp" />
<jsp:include page="/WEB-INF/jsp/shared/js.jsp" />
<script type="text/javascript" src="<%=basePath %>/myjs/bill.format.js"></script>
<script type="text/javascript" src="<%=basePath %>/myjs/bill.pay.js"></script>
<script type="text/javascript" src="<%=basePath %>/myjs/bill.util.js"></script>
</body>
</html>