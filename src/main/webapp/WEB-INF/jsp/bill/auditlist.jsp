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
	<title>账单审核列表</title>
	<style type="text/css">
		input[type=checkbox]{
			width: 17px; height: 17px;
		}
	</style>
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
                            <input type="text" placeholder="" class="form-control" id="billId">
                        </div>
                        <div class="col-md-2">
                           	<label>客户名称</label>
                            <input type="text" id="companyName" placeholder="" class="form-control">
                        </div>
						<div class="col-md-2">
							<label>账单类型</label>
							<select class="form-control m-b" name="account" id="billType">
								<option value="">全部</option>
								<option value="1">收入</option>
								<option value="2">支出</option>
							</select>
						</div>
						<div class="col-md-2">
							<label>账单来源</label>
							<select class="form-control m-b" name="account" id="billSource">
								<option value="">全部</option>
								<option value="1">租赁合同</option>
								<option value="2">外包合同</option>
								<option value="3">能源合同</option>
								<option value="4">物业合同</option>
								<option value="5">服务合同</option>
								<option value="6">园区服务</option>
								<option value="0">无</option>
							</select>
						</div>
						<div class="col-md-2">
                        	<button class="btn btn-primary" onclick="tableRefresh('list.do');getStatistics();"
                        			type="button" style="margin-top: 23px;;">搜索</button>
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
					</div>
					<div class="hr-line-dashed" style="clear: both;"></div>
				</div>
				<div class="col-sm-12">
					<div class="example-wrap">
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group"
							<c:if test="${readonly}">style="display: none;"</c:if> >
							<button type="button" class="btn btn-primary" onclick="audit(true)">
								<i class="glyphicon glyphicon-ok" aria-hidden="true"></i><span>批准</span>
							</button>
							<button type="button" class="btn btn-danger" onclick="audit(false)"
								<c:if test="${auditLevel eq 1}">style="display: none;"</c:if> >
								<i class="glyphicon glyphicon-remove" aria-hidden="true"></i><span>拒绝</span>
							</button>
						</div>
						<table id="billListTable"
                           	   data-mobile-responsive="true"
				               data-toggle="table"
				               data-url="list.do"
				               data-data-type="json"
				               data-side-pagination="server"
				               data-pagination="true"
				               data-query-params = "queryParams"
				               data-page-list="[5, 10, 20, 50]"
				              >
							<thead>
								<tr>
									<c:if test="${readonly eq false}">
										<th data-field="state" data-checkbox="true"></th>
									</c:if>
									<th data-field="billId">账单号</th>
									<th data-field="companyName">客户名称</th>
									<th data-field="amount">金额</th>
									<th data-field="overdueFine">滞纳金</th>
									<th data-field="billType" data-formatter="billTypeFormatter">账单类型</th>
									<th data-field="billSource" data-formatter="billSourceFormatter">订单来源</th>
									<th data-field="createdTime">生成时间</th>
									<th data-field="description" data-formatter="lineFormatter">详情</th>
									<th data-field="comment">备注</th>
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
<script type="text/javascript" src="<%=basePath %>/myjs/bill.auditlist.js"></script>
<script type="text/javascript">
	var level = ${auditLevel };
</script>
</body>
</html>