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
	<jsp:include page="../shared/css.jsp" />
	<title>账单列表</title>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
					<div class="row">
						<div class="col-md-2">
                           	<label>账单号</label>
                            <input type="text" id="billId" placeholder="" class="form-control">
                        </div>
                        <div class="col-md-2">
                           	<label>客户名称</label>
                            <input type="text" id="companyName" placeholder="" class="form-control">
                        </div>
						<div class="col-md-1">
							<label>账单类型</label>
							<select class="form-control m-b" name="account" id="billType">
								<option value="">全部</option>
								<option value="1">收入</option>
								<option value="2">支出</option>
							</select>
						</div>
						<div class="col-md-1">
							<label>账单状态</label>
							<select class="form-control m-b" name="account" id="billStatus">
								<option value="">全部</option>
								<option value="0">未付款</option>
								<option value="1">未付清</option>
								<option value="2">已付清</option>
							</select>
						</div>
						<div class="col-md-1">
							<label>账单来源</label>
							<select class="form-control m-b" name="account" id="billSource">
								<option value="">全部</option>
								<option value="1">租赁合同</option>
								<option value="2">外包合同</option>
								<option value="3">能源合同</option>
								<option value="4">物业合同</option>
								<option value="5">服务合同</option>
								<option value="6">园区服务</option>
<!-- 								<option value="9">其他</option> -->
								<option value="0">无</option>
							</select>
						</div>
						<div class="col-md-1">
							<label>审核状态</label>
							<select class="form-control m-b" name="account" id="auditStatus">
								<option value="">全部</option>
								<option value="0">未审核</option>
								<option value="1">财务通过</option>
								<option value="-1">财务拒绝</option>
								<option value="2">园长通过</option>
								<option value="-2">园长拒绝</option>
							</select>
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
                        	<button class="btn btn-primary" onclick="tableRefresh('listwithfloor.do');getStatistics();" type="button" style="margin-top: 23px;">搜索</button>
                        </div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="row">
                        <c:choose>
                        <c:when test="${not empty parks }">
                       	<div class="col-md-1">
							<label>园区</label>
							<select class="form-control m-b" name="account" id="parkId">
								<option value="">全部</option>
								<c:forEach var="park" items="${parks }">
                        			<option value="${park.id }">${park.parkName }</option>
                        		</c:forEach>
							</select>
						</div>
                        </c:when>
                        <c:otherwise>
                        	<input type="hidden" id="parkId" value="${parkId }">
                        </c:otherwise>
                        </c:choose>
                        <div class="col-md-1">
							<label>楼</label>
							<select class="form-control m-b" id="building-sele">
								<option value="">全部</option>
							</select>
						</div>
						<div class="col-md-1">
							<label>层</label>
							<select class="form-control m-b" id="floor-sele">
								<option value="">全部</option>
							</select>
						</div>
						<div class="col-md-1">
							<label>区</label>
							<select class="form-control m-b" id="block-sele">
								<option value="">全部</option>
							</select>
						</div>
						<div class="col-md-2">
                           	<label>房间号</label>
                            <input type="text" id="room-num" placeholder="" class="form-control">
                        </div>
						<div class="hr-line-dashed" style="clear: both;"></div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="row">
						<div class="col-md-2">
                           	<label>金额总计（元）</label>
                            <input type="text" id="totalAccount" class="form-control" readonly="readonly">
                        </div>
                        <div class="col-md-2">
                           	<label>未审核（元）</label>
                            <input type="text" id="uncheck" class="form-control" readonly="readonly">
                        </div>
                        <div class="col-md-2">
                           	<label>园长通过（元）</label>
                            <input type="text" id="checkAccept" class="form-control" readonly="readonly">
                        </div>
                        <div class="col-md-2">
                           	<label>财务通过（元）</label>
                            <input type="text" id="financeAccept" class="form-control" readonly="readonly">
                        </div>
                        <div class="col-md-2">
                           	<label>园长拒绝（元）</label>
                            <input type="text" id="checkRefuse" class="form-control" readonly="readonly">
                        </div>
                        <div class="col-md-2">
                           	<label>财务拒绝（元）</label>
                            <input type="text" id="financeRefuse" class="form-control" readonly="readonly">
                        </div>
					</div>
					<div class="hr-line-dashed" style="clear: both;"></div>
				</div>
				<div class="col-sm-12">
					<div class="example-wrap">
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group"
							<c:if test="${readonly}">style="display: none;"</c:if> >
							<button type="button" class="btn btn-outline btn-default"
								onclick="openAddPage('手动创建订单','800px','600px','createview.do', 'listwithfloor.do')">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i> <span>新建</span>
							</button>
							<%-- <button type="button" class="btn btn-outline btn-default" onclick="deleteBill()">
								<i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <span>删除</span>
							</button> --%>
						</div>
						<table id="billListTable"
                           	   data-mobile-responsive="true"
				               data-toggle="table"
				               data-url="listwithfloor.do"
				               data-data-type="json"
				               data-side-pagination="server"
				               data-pagination="true"
				               data-query-params="queryParams"
				               data-page-list="[5, 10, 20, 50, 100]"
				              >
							<thead>
								<tr>
									<%-- <c:if test="${readonly eq false}">
										<th data-field="state" data-checkbox="true"></th>
									</c:if> --%>
									<th data-field="billId">账单号</th>
									<th data-field="companyName">客户名称</th>
									<th data-field="building">楼号</th>
									<th data-field="floor">层号</th>
									<th data-field="block">区号</th>
									<th data-field="room">房间号</th>
									<th data-field="amount">金额(元)</th>
									<th data-field="overdueFine">滞纳金(元)</th>
									<th data-field="billType" data-formatter="billTypeFormatter">账单类型</th>
									<th data-field="billSource" data-formatter="billSourceFormatter">账单来源</th>
									<th data-field="createdTime">生成时间</th>
									<th data-field="billStatus" data-formatter="billStatusFormatter">账单状态</th>
									<th data-field="auditStatus" data-formatter="auditStatusFormatter">审核状态</th>
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
<jsp:include page="../shared/js.jsp" />
<script type="text/javascript" src="<%=basePath %>/myjs/bill.format.js"></script>
<script type="text/javascript" src="<%=basePath %>/myjs/bill.list.js"></script>
</body>
</html>