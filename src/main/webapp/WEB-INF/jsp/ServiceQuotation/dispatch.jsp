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

<title>服务详情</title>
<style>
	table a:hover{text-decoration: underline;}
	table a{margin-left: 10px}
</style>
</head>
<body class="grey-bg">
<input id="serviceId" value='${Parkservice.serviceId}' type="hidden">
<input id="serviceStatus" value='${Parkservice.serviceStatus}' type="hidden">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="col-sm-12">
	<div class="ibox float-e-margins">
		<div class="ibox-title">
			<h5>
				<normal>派工信息</normal>
			</h5>
		</div>
		<div class="ibox-content">
			<form method="get" class="form-horizontal"  id="signupForm">
			<div class="form-group">
			
				<div class="form-group">
					<label class="col-sm-3 control-label">名字</label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" value="" id="name" name="name">
					</div>
				</div>

                   
				<div class="form-group">
					<label class="col-sm-3 control-label">联系方式</label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control"  value="" id="phone" name="phone">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">邮箱</label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" value="" id="email" name="email">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">派工时间</label>
					<div class="col-sm-7  layer-date">
						<input type="text" style="width:400px" class="form-control" value="" id="despatchTime" name="despatchTime">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">补充信息</label>
					<div class="col-sm-7">
<!-- 						<input type="text" style="width:400px" class="form-control" value="" id="description"> -->
						 <textarea id="description" rows="3" name="textWeb" cols="40" class="form-control"  style="width: 400px;"></textarea> 
					</div>
				</div>
				</div>

				<div class="form-group" >
					<div class="col-sm-6 col-sm-offset-2" style="text-align: center; ">
<!-- 						<a  id="sub" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submit()"> 确定</a> -->
						<input class="btn btn-primary" type="submit" value="确定">
					</div>
				</div>

		</form>
  </div>
  </div>
</div>
</div>

<jsp:include page="../shared/js.jsp" />
<script type="text/javascript">

var e = "<i class='fa fa-times-circle'></i> ";
$("#signupForm").validate({
	rules : {//这里加校验规则
		name: "required",
		phone : "required",
		email : "required",
		despatchTime : "required"
	},
	messages: {//这里给对应的提示
		name:e+"必填项未填",
		phone:e+"必填项未填",
		email:e+"必填项未填",
		despatchTime:e+"必填项未填"
		
	},
	submitHandler : function(form) {
		submit();
	}
})

$('#despatchTime').datetimepicker({
// 	 minView: 1,
	 todayBtn: "linked",
	 format: 'yyyy-mm-dd hh:ii'
	        // 2016-12-7 11:45
	 //d, dd, D, DD, m, mm, M, MM, yy, yyyy
});
var serviceId = $("#serviceId").val();
 function submit(){
     swal({
             title: "确认提交? ",
             type: "warning",
             showCancelButton: true,
             confirmButtonColor: "#DD6B55",
             confirmButtonText: "是",
             cancelButtonText: "否",
             closeOnConfirm: false
         }, function () {
        var serviceId = $("#serviceId").val();
		var name = $("#name").val();
		var phone = $("#phone").val();
		var email = $("#email").val();
		var despatchTime = $("#despatchTime").val()+ ":00";
		var description = $("#description").val();

		var param={
		     	"serviceId":serviceId,
		     	"name":name,
		     	"phone":phone,
		     	"email":email,
		     	"despatchTime":despatchTime,
		     	"description":description

		}
		$.post('<%=basePath%>/quotation/addDispatch.do',param,function(data){
			
			
			if (data.status == 10001) {
				swal({
					title : "提交成功！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index);
				});

			} else {
				swal({
					title : "提交失败！",
					text : data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				});
			}});
	}
     );
 }

	
</script>
</body>
</html>