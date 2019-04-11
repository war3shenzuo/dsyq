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
<title>新建域名映射</title>
</head>
<body class="grey-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form method="get" class="form-horizontal" id="signupForm">
						<div class="form-group">
							<label class="col-sm-2 control-label">域名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="hostName">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">园区组id</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="parkGroupId">
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

	function submit() {
		
		var hostName = $("#hostName").val();
		var parkGroupId = $('#parkGroupId').val();
		
		$.get("add.do", {
			"hostName" : hostName,
			"parkGroupId" : parkGroupId
		}, function(data) {
			if (data.status == 10001) {
				swal({
					title : "保存成功！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : false
				}, function() {
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index);
				});
			} else {
				swal({
					title : "添加失败！",
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
			hostName : "required",
			parkGroupId	: "required"
		},
		submitHandler : function(form) {
			submit();
		}
	});
</script>
</body>
</html>