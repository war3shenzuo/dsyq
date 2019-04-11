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
<title>新增员工详情</title>
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
					<label class="col-sm-1 control-label">员工姓名</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="employeesName" name="employeesName" >
					</div>
					<label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-2">
						<select class="form-control m-b" id="employeesSex" name="employeesSex">
							<option value="">未知</option>
							<option value="1">男</option>
							<option value="2">女</option>
						</select>
					</div>
					<label class="col-sm-2 control-label">证件号码</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="card" name="card">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label">手机号码</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="mobile" name="mobile">
					</div>
					<label class="col-sm-2 control-label">其他联系方式</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="otherContact" name="otherContact">
					</div>
					<label class="col-sm-2 control-label">户籍所在地</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="address" name="address">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label">微信号</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="wechat" name="wechat">
					</div>
					<label class="col-sm-2 control-label">出生年月</label>
					<div class="col-sm-2">
						<div class="input-group date">
							<span class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</span>
							<input type="text" class="datepicker form-control" id="birth" name="birth">
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
							<input type="text" class="form-control" id="companyName" name="companyName">
						</div>
						<label class="col-sm-2 control-label">部门</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="department" name="department">
						</div>
						<label class="col-sm-2 control-label">岗位</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="jobs" name="jobs">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">入职时间</label>
						<div class="col-sm-2">
							<div class="input-group date">
							<span class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</span>
								<input type="text" class="datepicker form-control" id="hiredate" name="hiredate">
							</div>
						</div>
						<label class="col-sm-2 control-label">劳动合同开始时间</label>
						<div class="col-sm-2">
							<div class="input-group date">
							<span class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</span>
								<input type="text" class="datepicker form-control" id="startTime" name="startTime">
							</div>
						</div>
						<label class="col-sm-2 control-label">劳动合同结束时间</label>
						<div class="col-sm-2">
							<div class="input-group date">
							<span class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</span>
								<input type="text" class="datepicker form-control" id="endTime" name="endTime">
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
							<input type="text" class="form-control" id="graduate" name="graduate">
						</div>
						<label class="col-sm-2 control-label">专业</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="professional" name="professional">
						</div>
						<label class="col-sm-2 control-label">学位</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="degree" name="degree">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">开始日期</label>
						<div class="col-sm-2">
							<div class="input-group date">
							<span class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</span>
								<input type="text" class="datepicker form-control" id="startDate" name="startDate">
							</div>
						</div>
						<label class="col-sm-2 control-label">结束日期</label>
						<div class="col-sm-2">
							<div class="input-group date">
							<span class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</span>
								<input type="text" class="datepicker form-control" id="overDate" name="overDate">
							</div>
						</div>
						<label class="col-sm-2 control-label">备注</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="remark" name="remark">
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
						<normal>最近一次工作经历</normal>
					</h5>
				</div>
				<div class="ibox-content">
					<div class="form-group">
						<label class="col-sm-1 control-label">公司</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="company" name="company">
						</div>
						<label class="col-sm-2 control-label">部门</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="dapart" name="dapart">
						</div>
						<label class="col-sm-2 control-label">岗位</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="position" name="position">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">工作开始时间</label>
						<div class="col-sm-2">
							<div class="input-group date">
							<span class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</span>
								<input type="text" class="datepicker form-control" id="workTime" name="workTime">
							</div>
						</div>
						<label class="col-sm-2 control-label">工作结束时间</label>
						<div class="col-sm-2">
							<div class="input-group date">
							<span class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</span>
								<input type="text" class="datepicker form-control" id="overTime" name="overTime">
							</div>
						</div>
						<label class="col-sm-2 control-label">备注</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="note" name="note">
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

		$.post("addEmployees.do",params,function(data){
			if(data.status==10001){
				swal({
					title : data.msg,
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : false
				}, function() {
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index);
				});
			}else{
				swal({
					title : data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {

				});
			}
		});
	}

</script>
</body>
</html>