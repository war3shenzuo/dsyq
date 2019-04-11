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
<jsp:include page="/WEB-INF/jsp/shared/css.jsp" />
<title>延迟付款申请</title>
<style type="text/css">
	input[type=checkbox]{
		width: 17px; height: 17px;
	}
</style>
</head>
<body class="grey-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form method="get" class="form-horizontal" id="signupForm">
						<div class="form-group">
							<label class="col-sm-2 control-label">延迟时间<font color="red">＊</font></label>
							<div class="col-sm-10">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="delayTime" name="delayTime" type="text" class="datepicker form-control">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">延迟理由<font color="red">＊</font></label>
							<div class="col-sm-10">
								<textarea class="form-control" rows=10 id="reason" name="reason"></textarea>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2">
								<a class="btn btn-primary" onclick="submit()">提交</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/shared/js.jsp" />
<script type="text/javascript">
	
	var billId = "${billId}";
	function submit() {
		var delayTime = $("#delayTime").val() + " 23:59:59";
		var reason = $("#reason").val();
		$.post("apply.do", {
			"billId" : billId,
			"delayTime" : delayTime,
			"reason" : reason
		}, function(data) {
			if (data.status == 10001) {
				swal({
					title : "申请成功！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				});
			} else {
				swal({
					title : "申请失败！",
					text : data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				});
			}
		});
	}
	
	$("#signupForm").validate({
		rules : {
			delayTime : "required",
			reason : "required"
		},
		submitHandler : function(form) {
			submit();
		}
	});
</script>
</body>
</html>