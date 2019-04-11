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
	<title>新增明细-报表管理</title>
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
								<label class="col-sm-2 control-label">大项</label>
									<div class="col-sm-2">
										<select class="form-control m-b" id="items" style="float: left; margin-right: 1%;" onchange="selectType()">
											<option value="5">能源支出</option>
											<option value="6">人事费</option>
											<option value="7">业务费</option>
											<option value="8">管理费</option>
											<option value="9">服务支出</option>
											<option value="3">其他收益</option>
											<option value="10">工程维修</option>
											<option value="11">财务支出</option>
											<option value="12">税费支出</option>
										</select>
									</div>
								<label class="col-sm-1 control-label">细项</label>
								<div class="col-sm-2" id="selectId1">
									<select class="form-control m-b" id="fine1"  style="float: left; margin-right: 1%;">
										<option value="1">电费</option>
										<option value="2">水费</option>
										<option value="3">燃气费</option>
										<option value="4">空调费</option>
										<option value="5">其他</option>
									</select>
								</div>
								<div class="col-sm-2 hidden" id="selectId2">
									<select class="form-control m-b" id="fine2" style="float: left; margin-right: 1%;" >
										<option value="1">工资</option>
										<option value="2">保险</option>
										<option value="3">公积金</option>
										<option value="4">奖金</option>
										<option value="5">补贴</option>
										<option value="6">福利</option>
										<option value="7">招聘费</option>
										<option value="8">其他</option>
									</select>
								</div>
								<div class="col-sm-2 hidden" id="selectId3">
									<select class="form-control m-b" id="fine3" style="float: left; margin-right: 1%;">
										<option value="1">广告宣传</option>
										<option value="2">业务招待</option>
										<option value="3">货运费</option>
										<option value="4">其他</option>
									</select>
								</div>
								<div class="col-sm-2 hidden" id="selectId4">
									<select class="form-control m-b" id="fine4" style="float: left; margin-right: 1%;">
										<option value="1">员工培训</option>
										<option value="2">差旅费</option>
										<option value="3">通讯费</option>
										<option value="4">办公费</option>
										<option value="5">培训费</option>
										<option value="6">会议费</option>
										<option value="7">节日装饰</option>
										<option value="8">快递费</option>
										<option value="9">垃圾清运</option>
										<option value="10">汽车租赁</option>
										<option value="11">保洁日用</option>
										<option value="12">折旧费</option>
										<option value="13">其他</option>
									</select>
								</div>
								<div class="col-sm-2 hidden" id="selectId5">
									<select class="form-control m-b" id="fine5" style="float: left; margin-right: 1%;">
										<option value="1">实物采购</option>
										<option value="2">商务服务</option>
										<option value="3">其他</option>
									</select>
								</div>
								<div class="col-sm-2 hidden" id="selectId6">
									<select class="form-control m-b" id="fine6" style="float: left; margin-right: 1%;">
										<option value="1">运营补贴</option>
										<option value="4">装修补贴</option>
										<option value="5">培训补贴</option>
										<option value="2">快递</option>
										<option value="6">利息收入</option>
										<option value="3">其他</option>
									</select>
								</div>
								<div class="col-sm-2 hidden" id="selectId7">
									<select class="form-control m-b" id="fine7" style="float: left; margin-right: 1%;">
										<option value="1">维修费</option>
										<option value="2">装修费</option>
										<option value="3">检测费</option>
										<option value="4">工具购置</option>
										<option value="5">其他</option>
									</select>
								</div>
								<div class="col-sm-2 hidden" id="selectId8">
									<select class="form-control m-b" id="fine8" style="float: left; margin-right: 1%;">
										<option value="1">贷款利息</option>
										<option value="2">审计费</option>
										<option value="3">手续费</option>
										<option value="4">其他</option>
									</select>
								</div>
								<div class="col-sm-2 hidden" id="selectId9">
									<select class="form-control m-b" id="fine9" style="float: left; margin-right: 1%;">
										<option value="1">残保金</option>
										<option value="2">附加税</option>
										<option value="3">所得税</option>
										<option value="4">水利基金</option>
										<option value="5">印花税</option>
										<option value="6">增值税</option>
										<option value="7">其他</option>
									</select>
								</div>
								<label class="col-sm-1 control-label">分期选择</label>
								<div class="col-sm-2">
									<select class="form-control m-b" id="stage" style="float: left; margin-right: 1%;" onchange="selectStage()">
										<option value="1">单期</option>
										<option value="2">多期</option>
									</select>
								</div>
							</div>

							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-sm-2 control-label">开始分期<font color="red">*</font></label>
								<div class="col-sm-2">
									<div class="input-group date">
										<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										<input type="text" class="form_datetime form-control" id="periods" name="periods">
									</div>
								</div>

								<label class="col-sm-1 control-label">数值<font color="red">*</font></label>
								<div class="col-sm-2">
									<input type="number" class="form-control" name="currentLimit" id="currentLimit" aria-required="true">
								</div>

								<label class="col-sm-1 control-label hidden" id="labelId">多期数量<font color="red">*</font></label>
								<div class="col-sm-2 hidden" id="divId">
									<input type="number" class="form-control" name="multiPeriod" id="multiPeriod" aria-required="true">
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
<script type="text/javascript" src="<%=basePath %>/myjs/datetimepicker_zh-CN.js"></script>
<script type="text/javascript">

	var mytime = new Date().getFullYear() + "-" + new Date().getMonth();

	$('.form_datetime').datetimepicker({
		format: 'yyyy-mm',
		autoclose: true,
		todayBtn: true,
		startView: 'year',
		minView:'year',
		maxView:'decade',
		language: 'zh-CN'
	});
/* 	$('#periods').datetimepicker({
		format: 'yyyy-mm',
		autoclose: true,
		todayBtn: true,
		startView: 'year',
		minView:'year',
		maxView:'decade',
		language: 'zh-CN'
	}); */
	$('.form_datetime').val(mytime);

	var standard = "<i class='fa fa-times-circle'></i> ";

	$("#signupForm").validate({
		rules: {//这里加校验规则
			periods: "required",
			currentLimit: "required"
		},
		messages: {//这里给对应的提示
			periods: standard + "请选择期数 !",
			currentLimit: standard + "请填写数值 !"
		},
		submitHandler: function(form){
			submitProfitLoss();  //去提交
		}
	})

	function selectStage(){
		if($("#stage").val() == 2){
			$("#labelId").removeClass("hidden");
			$("#divId").removeClass("hidden");
		}else{
			$("#labelId").addClass("hidden");
			$("#divId").addClass("hidden");
		}
	}

	function selectType(){
		if($("#items").val() == 5){
			$("#selectId1").removeClass("hidden");
			$("#selectId2").addClass("hidden");
			$("#selectId3").addClass("hidden");
			$("#selectId4").addClass("hidden");
			$("#selectId5").addClass("hidden");
			$("#selectId6").addClass("hidden");
			$("#selectId7").addClass("hidden");
			$("#selectId8").addClass("hidden");
			$("#selectId9").addClass("hidden");
		}else if($("#items").val() == 6){
			$("#selectId1").addClass("hidden");
			$("#selectId2").removeClass("hidden");
			$("#selectId3").addClass("hidden");
			$("#selectId4").addClass("hidden");
			$("#selectId5").addClass("hidden");
			$("#selectId6").addClass("hidden");
			$("#selectId7").addClass("hidden");
			$("#selectId8").addClass("hidden");
			$("#selectId9").addClass("hidden");
		}else if($("#items").val() == 7){
			$("#selectId3").removeClass("hidden");
			$("#selectId1").addClass("hidden");
			$("#selectId2").addClass("hidden");
			$("#selectId4").addClass("hidden");
			$("#selectId5").addClass("hidden");
			$("#selectId6").addClass("hidden");
			$("#selectId7").addClass("hidden");
			$("#selectId8").addClass("hidden");
			$("#selectId9").addClass("hidden");
		}else if($("#items").val() == 8){
			$("#selectId4").removeClass("hidden");
			$("#selectId1").addClass("hidden");
			$("#selectId2").addClass("hidden");
			$("#selectId3").addClass("hidden");
			$("#selectId5").addClass("hidden");
			$("#selectId6").addClass("hidden");
			$("#selectId7").addClass("hidden");
			$("#selectId8").addClass("hidden");
			$("#selectId9").addClass("hidden");
		}else if($("#items").val() == 9){
			$("#selectId5").removeClass("hidden");
			$("#selectId1").addClass("hidden");
			$("#selectId2").addClass("hidden");
			$("#selectId3").addClass("hidden");
			$("#selectId4").addClass("hidden");
			$("#selectId6").addClass("hidden");
			$("#selectId7").addClass("hidden");
			$("#selectId8").addClass("hidden");
			$("#selectId9").addClass("hidden");
		}else if($("#items").val() == 3){
			$("#selectId6").removeClass("hidden");
			$("#selectId1").addClass("hidden");
			$("#selectId2").addClass("hidden");
			$("#selectId3").addClass("hidden");
			$("#selectId4").addClass("hidden");
			$("#selectId5").addClass("hidden");
			$("#selectId7").addClass("hidden");
			$("#selectId8").addClass("hidden");
			$("#selectId9").addClass("hidden");
		}else if($("#items").val() == 10){
			$("#selectId7").removeClass("hidden");
			$("#selectId1").addClass("hidden");
			$("#selectId2").addClass("hidden");
			$("#selectId3").addClass("hidden");
			$("#selectId4").addClass("hidden");
			$("#selectId5").addClass("hidden");
			$("#selectId6").addClass("hidden");
			$("#selectId8").addClass("hidden");
			$("#selectId9").addClass("hidden");
		}else if($("#items").val() == 11){
			$("#selectId8").removeClass("hidden");
			$("#selectId1").addClass("hidden");
			$("#selectId2").addClass("hidden");
			$("#selectId3").addClass("hidden");
			$("#selectId4").addClass("hidden");
			$("#selectId5").addClass("hidden");
			$("#selectId6").addClass("hidden");
			$("#selectId7").addClass("hidden");
			$("#selectId9").addClass("hidden");
		}else if($("#items").val() == 12){
			$("#selectId9").removeClass("hidden");
			$("#selectId1").addClass("hidden");
			$("#selectId2").addClass("hidden");
			$("#selectId3").addClass("hidden");
			$("#selectId4").addClass("hidden");
			$("#selectId5").addClass("hidden");
			$("#selectId6").addClass("hidden");
			$("#selectId7").addClass("hidden");
			$("#selectId8").addClass("hidden");
		}
	}

	//新增保存
	function submitProfitLoss(){

		var params = {
			"parkId": '${parkId}',
			"items" : $("#items").val(), // 大项
			"periods" : $("#periods").val(), // 期数
			"currentLimit" : $("#currentLimit").val() // 数值
		}

		if($("#items").val() == 5){//细项
			params.fine = $("#fine1").val();
		}else if($("#items").val() == 6){
			params.fine = $("#fine2").val();
		}else if($("#items").val() == 7){
			params.fine = $("#fine3").val();
		}else if($("#items").val() == 8){
			params.fine = $("#fine4").val();
		}else if($("#items").val() == 9){
			params.fine = $("#fine5").val();
		}else if($("#items").val() == 3){
			params.fine = $("#fine6").val();
		}else if($("#items").val() == 10){
			params.fine = $("#fine7").val();
		}else if($("#items").val() == 11){
			params.fine = $("#fine8").val();
		}else if($("#items").val() == 12){
			params.fine = $("#fine9").val();
		}

		if($("#items").val() == 3){
			params.reportType = 1;
		}else{
			params.reportType = 2;
		}

		if($("#stage").val() == 2){
			params.multiPeriod = $("#multiPeriod").val();
		}

		$.post("check.do",params,function(data){
			if (data.status == 10001) {
				swal({
					title : data.msg,
					type : "warning",
					showCancelButton: true,
	                confirmButtonColor: "#DD6B55",
	                confirmButtonText: "是",
	                cancelButtonText: "否",
	                closeOnConfirm: false
				}, function () {
					$.post("addProfitLoss.do",params,function(data){
						if (data.status == 10001) {
							swal({
								title : data.msg,
								type : "success",
								confirmButtonText : "确定",
								closeOnConfirm : false
							});

						} else{
							swal({
								title : data.msg,
								type : "error",
								confirmButtonText : "确定",
								closeOnConfirm : true
							})
						}
					});
				}
					
				);

			} else{
				swal({//数据存在
					title : data.msg,
					type : "warning",
					showCancelButton: true,
	                confirmButtonColor: "#DD6B55",
	                confirmButtonText: "是",
	                cancelButtonText: "否",
	                closeOnConfirm: false
				}, function () {
					$.post("addProfitLoss2.do",params,function(data){
						if (data.status == 10001) {
							swal({
								title : data.msg,
								type : "success",
								confirmButtonText : "确定",
								closeOnConfirm : false
							});

						} else{
							swal({
								title : data.msg,
								type : "error",
								confirmButtonText : "确定",
								closeOnConfirm : true
							})
						}
					});
				}
				)
			}
		}); 
/* 		$.post("addProfitLoss.do",params,function(data){
			if (data.status == 10001) {
				swal({
					title : data.msg,
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : false
				});

			} else{
				swal({
					title : data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				})
			}
		}); */
	}
</script>
</body>
</html>