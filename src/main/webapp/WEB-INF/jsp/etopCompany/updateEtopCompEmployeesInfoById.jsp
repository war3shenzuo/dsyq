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
<title>员工详情</title>
<style>
	table a:hover{text-decoration: underline;}
	table a{margin-left: 10px}
</style>
</head>
<body class="grey-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<form method="get" class="form-horizontal" id="signupForm">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>
					<normal>员工基本信息</normal>
				</h5>
			</div>
			<div class="ibox-content">

				<div class="form-group">
					<label class="col-sm-1 control-label">员工姓名<font color="red">*</font></label>
					<div class="col-sm-2">
						<input type="text" class="form-control" value="${compEmployeesInfo.employeesName}" id="employeesName">
					</div>
					<label class="col-sm-2 control-label">性别<font color="red">*</font></label>
					<div class="col-sm-2">
						<select class="form-control m-b" id="employeesSex" name="employeesSex" style="float: left; margin-right: 1%;">
							<option value=""
									<c:if test="${compEmployeesInfo.employeesSex eq null}">selected</c:if> >未知
							</option>
							<option value="1"
									<c:if test="${compEmployeesInfo.employeesSex eq 1}">selected</c:if> >男
							</option>
							<option value="2"
									<c:if test="${compEmployeesInfo.employeesSex eq 2}">selected</c:if> >女
							</option>
						</select>
					</div>
					<label class="col-sm-2 control-label">证件号码<font color="red">*</font></label>
					<div class="col-sm-2">
						<input type="text" class="form-control" value="${compEmployeesInfo.card}" id="card">
					</div>

				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label">手机号码<font color="red">*</font></label>
					<div class="col-sm-2">
						<input type="text" class="form-control" value="${compEmployeesInfo.mobile}" id="mobile">
					</div>
					<label class="col-sm-2 control-label">其他联系方式</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" value="${compEmployeesInfo.otherContact}" id="otherContact">
					</div>
					<label class="col-sm-2 control-label">户籍所在地</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" value="${compEmployeesInfo.address}" id="address">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label">微信号</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" value="${compEmployeesInfo.wechat}" id="wechat">
					</div>
					<label class="col-sm-2 control-label">出生年月</label>
					<div class="col-sm-2">
						<div class="input-group date">
							<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
							<input name="Field_KH_DFJL_RiQi" type="text" class="datepicker form-control" id="birth"
								   value='<fmt:formatDate value="${compEmployeesInfo.birth}" pattern="yyyy-MM-dd"/>'>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 就职信息 -->
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						<normal>就职信息</normal>
					</h5>
				</div>
				<div class="ibox-content">
					<div class="form-group">
						<label class="col-sm-1 control-label">公司名称</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${compEmployeesInfo.companyName}" id="companyName">
						</div>
						<label class="col-sm-2 control-label">部门</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${compEmployeesInfo.department}" id="department">
						</div>
						<label class="col-sm-2 control-label">岗位</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${compEmployeesInfo.jobs}" id="jobs">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">入职时间</label>
						<div class="col-sm-2">
							<div class="input-group date">
								<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								<input name="Field_KH_DFJL_RiQi" type="text" class="datepicker form-control" id="hiredate"
									   value='<fmt:formatDate value="${compEmployeesInfo.hiredate}" pattern="yyyy-MM-dd"/>'>
							</div>
						</div>
						<label class="col-sm-2 control-label">劳动合同开始时间</label>
						<div class="col-sm-2">
							<div class="input-group date">
								<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								<input name="Field_KH_DFJL_RiQi" type="text" class="datepicker form-control" id="startTime"
									   value='<fmt:formatDate value="${compEmployeesInfo.startTime}" pattern="yyyy-MM-dd"/>'>
							</div>
						</div>
						<label class="col-sm-2 control-label">劳动合同结束时间</label>
						<div class="col-sm-2">
							<div class="input-group date">
								<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								<input name="Field_KH_DFJL_RiQi" type="text" class="datepicker form-control" id="endTime"
									   value='<fmt:formatDate value="${compEmployeesInfo.endTime}" pattern="yyyy-MM-dd"/>'>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 教育背景 -->
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						<normal>教育背景</normal>
					</h5>
				</div>
				<div class="ibox-content">
					<div class="form-group">
						<label class="col-sm-1 control-label">毕业院校</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${compEmployeesInfo.graduate}" id="graduate">
						</div>
						<label class="col-sm-2 control-label">专业</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${compEmployeesInfo.professional}" id="professional">
						</div>
						<label class="col-sm-2 control-label">学位</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${compEmployeesInfo.degree}" id="degree">
						</div>
					</div>
					<div class="form-group">

						<label class="col-sm-1 control-label">开始日期</label>
						<div class="col-sm-2">
							<div class="input-group date">
								<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								<input name="Field_KH_DFJL_RiQi" type="text" class="datepicker form-control" id="startDate"
									   value='<fmt:formatDate value="${compEmployeesInfo.startDate}" pattern="yyyy-MM-dd"/>'>
							</div>
						</div>
						<label class="col-sm-2 control-label">结束日期</label>
						<div class="col-sm-2">
							<div class="input-group date">
								<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								<input name="Field_KH_DFJL_RiQi" type="text" class="datepicker form-control" id="overDate"
									   value='<fmt:formatDate value="${compEmployeesInfo.overDate}" pattern="yyyy-MM-dd"/>'>
							</div>
						</div>
						<label class="col-sm-2 control-label">备注</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${compEmployeesInfo.remark}" id="remark">
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--工作经历-->
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						<normal>工作经历</normal>
					</h5>
				</div>
				<div class="ibox-content">
					<div class="form-group">
						<label class="col-sm-1 control-label">公司</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${compEmployeesInfo.company}" id="company">
						</div>
						<label class="col-sm-2 control-label">部门</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${compEmployeesInfo.dapart}" id="dapart">
						</div>
						<label class="col-sm-2 control-label">岗位</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${compEmployeesInfo.position}" id="position">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">工作开始时间</label>
						<div class="col-sm-2">
							<div class="input-group date">
								<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								<input name="Field_KH_DFJL_RiQi" type="text" class="datepicker form-control" id="workTime"
									   value='<fmt:formatDate value="${compEmployeesInfo.workTime}" pattern="yyyy-MM-dd"/>'>
							</div>
						</div>
						<label class="col-sm-2 control-label">工作结束时间</label>
						<div class="col-sm-2">
							<div class="input-group date">
								<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								<input name="Field_KH_DFJL_RiQi" type="text" class="datepicker form-control" id="overTime"
									   value='<fmt:formatDate value="${compEmployeesInfo.overTime}" pattern="yyyy-MM-dd"/>'>
							</div>
						</div>
						<label class="col-sm-2 control-label">备注</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" value="${compEmployeesInfo.note}" id="note">
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-4 col-sm-offset-5">
				<input class="btn btn-primary" type="submit" value="确认并保存">
			</div>
		</div>

	</form>
</div>
<jsp:include page="../shared/js.jsp" />
<script type="text/javascript">

	$('.datepicker').datepicker({
		todayBtn: "linked",
		keyboardNavigation: !1,
		forceParse: !1,
		autoclose: !0
	});

	var standard = "<i class='fa fa-times-circle'></i> ";

	$("#signupForm").validate({
		rules: {//这里加校验规则
			employeesName: "required",
			employeesSex: "required",
			card: "required",
			mobile: {
				required:true,
				digits:true
			},
		},
		messages: {//这里给对应的提示
			employeesName: standard + "请输入员工姓名 !",
			employeesSex: standard + "请选择性别 !",
			card: standard + "请输入证件号码 !",
			mobile: {
				required: standard + "请输入电话号码 !",
				digits: standard + "请输入正确的电话格式 !"
			}
		},
		submitHandler: function(form){
			submitEmployees();  //去提交
		}
	})

	//新增员工信息保存
	function submitEmployees(){

		var params = {
			"companyId" : '${companyId}',
			"employeesId" : '${employeesId}',
			"employeesName" : $("#employeesName").val(), // 员工姓名
			"employeesSex" : $("#employeesSex").val(), // 性别
			"card" : $("#card").val(), // 证件号码
			"mobile" : $("#mobile").val(), // 手机号码
			"otherContact" : $("#otherContact").val(), // 其他联系方式
			"address" : $("#address").val(), // 户籍所在地
			"wechat" : $("#wechat").val(), // 微信号
			"birth" : $("#birth").val(), // 出生年月
			"companyName" : $("#companyName").val(), // 公司名称
			"department" : $("#department").val(), // 部门
			"jobs" : $("#jobs").val(), // 岗位
			"hiredate" : $("#hiredate").val(), // 入职时间
			"startTime" : $("#startTime").val(), // 劳动合同开始时间
			"endTime" : $("#endTime").val(),// 劳动合同结束时间
			"graduate" : $("#graduate").val(), // 毕业院校
			"professional" : $("#professional").val(),// 专业
			"degree" : $("#degree").val(), // 学位
			"startDate" : $("#startDate").val(), // 开始日期
			"overDate" : $("#overDate").val(), // 结束日期
			"remark" : $("#remark").val(), // 备注
			"company" : $("#company").val(), // 公司
			"dapart" : $("#dapart").val(), // 部门
			"position" : $("#position").val(), // 岗位
			"workTime" : $("#workTime").val(), // 工作开始时间
			"overTime" : $("#overTime").val(), // 工作结束时间
			"note" : $("#note").val() // 备注
		}

		if($.trim(params.startTime) > $.trim(params.endTime)){
			swal("劳动合同开始时间不能大于劳动合同结束日期!");
			return;
		}
		if($.trim(params.startDate) > $.trim(params.overDate)){
			swal("开始日期不能大于结束日期!");
			return;
		}
		if($.trim(params.workTime) > $.trim(params.overTime)){
			swal("工作开始时间不能大于工作结束时间!");
			return;
		}

		$.post("updateEmployees.do",params,function(data){
			if(data.status==10001){
				swal( "保存成功！", "","success" );

			}else{
				swal( "保存失败！", "","error");
			}
		});
	}

</script>
</body>
</html>