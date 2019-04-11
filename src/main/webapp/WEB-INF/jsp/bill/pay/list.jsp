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
	<title>入账记录</title>
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
					<div class="row">
                        <c:if test="${not empty parks }">
                       	<div class="col-md-1">
							<label>园区</label>
							<select class="form-control m-b" name="account" id="parkId">
								<option value="">全部</option>
								<c:forEach var="park" items="${parks }">
                        			<option value="${park.id }">${park.parkName }</option>
                        		</c:forEach>
							</select>
						</div>
                        </c:if>
						<div class="col-md-2">
                           	<label>入账单号</label>
                            <input type="text" id="incomeId" placeholder="" class="form-control">
                        </div>
                        <div class="col-md-2">
                           	<label>客户名称</label>
                            <input type="text" id="companyName" placeholder="" class="form-control">
                        </div>
						<div class="col-md-1">
                           	<label>起止时间</label>
                            <input type="text" id="beginDate" placeholder="起始时间" class="form-control datepicker">
                        </div>
                        <div class="col-md-1">
                           	<label>&nbsp;</label>
                            <input type="text" id="endDate" placeholder="结束时间" class="form-control datepicker">
                        </div>
						<div class="col-md-1">
                        	<button class="btn btn-primary" onclick="tableRefresh('listincome.do');" type="button" style="margin-top: 23px;">搜索</button>
                        </div>
						<div class="hr-line-dashed" style="clear: both;"></div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="example-wrap">
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group"
							style="display: none;">
							<button type="button" class="btn btn-outline btn-default"
								onclick="openAddPage('手动创建订单','800px','600px','createview.do', 'list.do')">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i> <span>新建</span>
							</button>
						</div>
						<table id="billListTable"
                           	   data-mobile-responsive="true"
				               data-toggle="table"
				               data-url="listincome.do"
				               data-data-type="json"
				               data-side-pagination="server"
				               data-pagination="true"
				               data-query-params="queryParams"
				               data-page-list="[5, 10, 20, 50, 100]"
				              >
							<thead>
								<tr>
									<th data-field="incomeId">入账单号</th>
									<th data-field="companyName">客户名称</th>
									<th data-field="amount">金额(元)</th>
									<th data-field="balance">结余(元)</th>
									<th data-field="payType" data-formatter="payTypeFormatter">支付类型</th>
									<th data-field="payNoOut">付款单号</th>
									<th data-field="payTime">支付时间</th>
									<th data-field="description" data-formatter="lineFormatter">详情</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/shared/js.jsp" />
<script type="text/javascript" src="<%=basePath %>/myjs/bill.format.js"></script>
<script type="text/javascript">
	var table = $('#billListTable');
	
	$(document).ready(function() {
		
	});
   
	function queryParams(params) {
		params.incomeId = $("#incomeId").val();
		params.companyName = $("#companyName").val();
		if($("#beginDate").val() != '')
			params.beginDate = $("#beginDate").val() + " 00:00:00";
		if($("#endDate").val() != '')
			params.endDate = $("#endDate").val() + " 23:59:59";
		if($("#parkId").length>0 && $("#parkId").val() !='')
			params.parkIds = $("#parkId").val();
		return params;
	}
</script>
</body>
</html>