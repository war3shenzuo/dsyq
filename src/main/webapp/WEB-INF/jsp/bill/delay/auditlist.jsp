<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<title>延期申请审核</title>
	<style type="text/css">
		input[type=checkbox]{
			width: 17px; height: 17px;
		}
	</style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="ibox float-e-margins">
		<h4 class="text-warning">提示：延期申请，请到账单详情页添加</h4>
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
					<div class="example-wrap">
						<label class="checkbox-inline">
                            <input type="checkbox" id="auditStatus" onChange="tableRefresh();">已审核
                        </label>
                        <input type="hidden" id="readonly" value="${readonly}">
						<table id="delayListTable"
                           	   data-mobile-responsive="true"
				               data-toggle="table"
				               data-url="listwithbill.do"
				               data-data-type="json"
				               data-side-pagination="server"
				               data-pagination="true"
				               data-query-params = "queryParams"
				               data-page-list="[5, 10, 20, 50]"
				              >
							<thead>
								<tr>
									<th data-field="delayId">申请号</th>
									<th data-field="billId">账单号</th>
									<th data-field="companyName">客户</th>
									<th data-field="deadline">截止时间</th>
									<th data-field="delayTime">延迟时间</th>
									<th data-field="reason">延迟理由</th>
									<th data-field="applyTime">申请时间</th>
									<th data-field="auditStatus" data-formatter="auditStatusFormatter3">审核状态</th>
									<th data-field="billId" data-formatter="detailButtonFormatter"></th>
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
<script type="text/javascript" src="<%=basePath %>/myjs/bill.delay.audit.js"></script>
</body>
</html>