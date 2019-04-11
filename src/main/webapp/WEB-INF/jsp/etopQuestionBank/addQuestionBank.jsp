<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>新增题目-培训管理</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">

				<div class="ibox-content">

					<form method="get" class="form-horizontal" id="signupForm">

						<div class="form-group">

							<div class="form-group">
								<label class="col-sm-1 control-label">题目<font color="red">*</font></label>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="title" id="title">
								</div>
								<label class="col-sm-1 control-label">适用平台<font color="red">*</font></label>
									<div class="col-sm-2">
										<select class="form-control m-b" id="platform" name="platform" style="float: left; margin-right: 1%;" disabled>
											<option value="0"
													<c:if test="${questionBank.platform eq 0}">selected</c:if> >全部
											</option>
											<option value="1"
													<c:if test="${questionBank.platform eq 1}">selected</c:if> >淘宝
											</option>
											<option value="2"
													<c:if test="${questionBank.platform eq 2}">selected</c:if> >微信
											</option>
											<option value="3"
													<c:if test="${questionBank.platform eq 3}">selected</c:if> >京东
											</option>
											<option value="4"
													<c:if test="${questionBank.platform eq 4}">selected</c:if> >苏宁</option>
											<option value="5"
													<c:if test="${questionBank.platform eq 5}">selected</c:if> >跨境平台</option>
											<option value="6"
													<c:if test="${questionBank.platform eq 6}">selected</c:if> >其他平台</option>
										</select>
									</div>
								<label class="col-sm-2 control-label">正确答案<font color="red">*</font></label>
								<div class="col-sm-2">
									<select class="form-control m-b" id="correctAnswer" name="correctAnswer" style="float: left; margin-right: 1%;">
										<option value="A">A</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>
									</select>
								</div>
							</div>

							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-sm-1 control-label">A<font color="red">*</font></label>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="answerA" id="answerA">
								</div>
								<label class="col-sm-1 control-label">B<font color="red">*</font></label>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="answerB" id="answerB">
								</div>
							</div>

							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-sm-1 control-label">C<font color="red">*</font></label>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="answerC" id="answerC">
								</div>
								<label class="col-sm-1 control-label">D<font color="red">*</font></label>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="answerD" id="answerD">
								</div>
							</div>

							<div class="hr-line-dashed"></div>

							<div class="form-group" <c:if test="${readonly eq true}">style="display: none;"</c:if> >
								<div class="col-sm-4 col-sm-offset-5">
									<input class="btn btn-primary" type="submit" value="确认并保存">
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../shared/js.jsp"/>
<script type="text/javascript">

	var standard = "<i class='fa fa-times-circle'></i> ";

	$("#signupForm").validate({
		rules: {//这里加校验规则
			title: "required",
			correctAnswer: "required",
			platform: "required",
			answerA: "required",
			answerB: "required",
			answerC: "required",
			answerD: "required"
		},
		messages: {//这里给对应的提示
			title: standard + "请输入题目 !",
			platform: standard + "请选择适用平台 !",
			correctAnswer: standard + "请选择正确答案 !",
			answerA: standard + "请输入A选项内容 !",
			answerB: standard + "请输入B选项内容 !",
			answerC: standard + "请输入C选项内容 !",
			answerD: standard + "请输入D选项内容 !"
		},
		submitHandler: function(form){
			submitQuestionBank();  //去提交
		}
	})

	//新增员工信息保存
	function submitQuestionBank(){

		var params = {
			"titleId" : '${titleId}',
			"title" : $("#title").val(), // 题目
			"platform" : $("#platform").val(), // 适用平台
			"correctAnswer" : $("#correctAnswer").val(), // 正确答案
			"answerA" : $("#answerA").val(), // 答案A
			"answerB" : $("#answerB").val(), // 答案B
			"answerC" : $("#answerC").val(), // 答案C
			"answerD" : $("#answerD").val() // 答案D
		}

		$.post("addEtopQuestionBankInfo.do",params,function(data){
			if (data.status == 10001) {
				swal({
					title : data.msg,
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : false
				}, function() {
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index);
				});

			} else{
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