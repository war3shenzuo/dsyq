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
<title>账单减免申请</title>
</head>
<body class="grey-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form method="get" class="form-horizontal" id="signupForm">
						<div class="form-group">
							<label class="col-sm-2 control-label">本金减免<font color="red">＊</font></label>
							<div class="col-sm-10">
								<div class="input-group">
									<span class="input-group-addon">￥</span>
 									<input type="number" class="form-control" id="amountRemission"
 										name="amountRemission" value="0">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">滞纳金减免<font color="red">＊</font></label>
							<div class="col-sm-10">
								<div class="input-group">
									<span class="input-group-addon">￥</span>
 										<input type="number" class="form-control" id="overdueRemission"
 											name="overdueRemission" value="0">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">减免理由<font color="red">＊</font></label>
							<div class="col-sm-10">
								<textarea id="reason" name="reason" class="form-control" rows=7></textarea>
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
<jsp:include page="/WEB-INF/jsp/shared/js.jsp" />
<script type="text/javascript">
	var billId = "${billId}";
	function submit() {
		var amountRemission = $("#amountRemission").val();
		var overdueRemission = $("#overdueRemission").val();
		var amount =${amount };
		var overdueFine = ${overdueFine };
		if(amountRemission > amount){
			swal({
				title : "本金减免大于本金！",
				type : "warning",
				confirmButtonText : "确定",
				closeOnConfirm : true
			});
			return;
		}
		if(overdueRemission > overdueFine){
			swal({
				title : "滞纳金减免大于滞纳金！",
				type : "warning",
				confirmButtonText : "确定",
				closeOnConfirm : true
			});
			return;
		}
		var reason = $("#reason").val();
		$.post("apply.do", {
			"billId" : billId,
			"amountRemission" : amountRemission,
			"overdueRemission" : overdueRemission,
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
			amountRemission : {
				required : true,
				min : 0
			},
			overdueRemission : {
				required : true,
				min : 0
			},
			reason : "required"
		},
		submitHandler : function(form) {
			submit();
		}
	});
</script>
</body>
</html>