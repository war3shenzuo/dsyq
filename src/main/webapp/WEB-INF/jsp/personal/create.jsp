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
<title>添加工作经历</title>
</head>
<body class="grey-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<form method="post" class="form-horizontal" id="signupForm">
				<div class="ibox-content">

						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">工作名称</label>
							</div> 
							<div class="col-sm-3">
                            <input type="text" id="workName" class="form-control m-b">
							</div>
							
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">部门</label>
							</div>
							<div class="col-sm-3">
								<input type="text" id="depart" class="form-control m-b" >
							</div>
		
						</div>
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">职位</label>
							</div>
							<div class="col-sm-3">
                            <input type="text" id="positions" class="form-control m-b">
							</div>
							
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">入职时间</label>
							</div>
							<div class="col-sm-3">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input name="deadline" type="text" class="datepicker form-control" id="workStart">
								</div>
							</div>							
                          </div>
                          
                          <div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">离职时间</label>
							</div>
							<div class="col-sm-3">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input name="deadline" type="text" class="datepicker form-control" id="workEnd">
								</div>
							</div>							
                          </div>
				</div>	
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2" >
<!--     		<a  id="sub" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submit()"> 提交 </a> -->
								<input class="btn btn-primary" type="submit" value="提交">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../shared/js.jsp" />
<script type="text/javascript">

$("#signupForm").validate({
	rules : {
		workName : "required"
	},
	submitHandler : function(form) {
		submit();
	}
});
	function submit() {
		var workName = $("#workName").val();
		var depart = $("#depart").val();
		var positions = $("#positions").val();
		var workStart = $("#workStart").val();
		var workEnd = $("#workEnd").val();
		
		var param={
				"workName" : workName,
				"depart" : depart,
				"positions" : positions,
				"workStart" : workStart,
				"workEnd" : workEnd
		}
		
		
		$.post('<%=basePath%>/personal/add.do',param,function(data){
			
			
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

</script>
</body>
</html>