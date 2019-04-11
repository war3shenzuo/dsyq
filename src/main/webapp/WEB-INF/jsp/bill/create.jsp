<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>创建账单</title>
</head>
<body class="grey-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form method="post" class="form-horizontal" id="signupForm">
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">订单类型</label>
							</div>
							<div class="col-sm-3">
								<select class="form-control m-b" name="billType" id="billType">
									<option value="1">收入</option>
									<option value="2">支出</option>
								</select>
							</div>
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">金额</label>
							</div>
							<div class="col-sm-3">
								<input type="number" class="form-control" placeholder="" id="amount" name="amount">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">账单来源</label>
							</div>
							<div class="col-sm-3">
								<select class="form-control m-b" name="account" id="billSource">
									<option value="0">无</option>
									<option value="1">租赁合同</option>
									<option value="3">能源合同</option>
									<option value="4">物业合同</option>
									<option value="5">服务合同</option>
									<option value="6">园区服务</option>
								</select>
							</div>
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">客户</label>
							</div>
							<div class="col-sm-3">
								<input type="hidden" id="companyId" >
								<input type="text" class="form-control" id="companyName" name="companyName" readonly="readonly">
							</div>
							<div class="col-sm-2">                                                            
                     			<a id="findCompany" class="btn btn-info" data-toggle="modal" data-target="#companyModal">公司查找</a>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">缴费期限</label>
							</div>
							<div class="col-sm-3">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input name="deadline" type="text" class="datepicker form-control" id="deadline">
								</div>
							</div>
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">来源编号</label>
							</div>
							<div class="col-sm-5">
								<select class="form-control m-b" name="billNoOut" id="billNoOut">
								</select>
							</div>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="billNoOut2" name="billNoOut2" style="display: none;">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">起始覆盖时间</label>
							</div>
							<div class="col-sm-3">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input name="startTime" type="text" class="datepicker form-control" id="startTime" disabled="disabled">
								</div>
							</div>
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">截止覆盖时间</label>
							</div>
							<div class="col-sm-3">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input name="endTime" type="text" class="datepicker form-control" id="endTime" disabled="disabled">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">账单详情</label>
							</div>
							<div class="col-sm-9">
								<textarea class="form-control" rows=7 id="description" name="description"></textarea>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2">
								<!-- <a class="btn btn-primary" onclick="submit()">提交</a> -->
								<input class="btn btn-primary" type="submit" value="提交">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="companypic.jsp"></jsp:include>
<jsp:include page="../shared/js.jsp" />
<script type="text/javascript" src="<%=basePath %>/myjs/bill.create.js"></script>
</body>
</html>