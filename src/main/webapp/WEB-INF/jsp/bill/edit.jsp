<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>账单编辑</title>
</head>
<body class="grey-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form class="form-horizontal" id="signupForm">
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">订单类型</label>
							</div>
							<div class="col-sm-3">
								<select class="form-control m-b" name="account" id="billType">
									<option value="1" <c:if test="${etopBill.billType eq 1}">selected="selected"</c:if>>收入</option>
									<option value="2" <c:if test="${etopBill.billType eq 2}">selected="selected"</c:if>>支出</option>
								</select>
							</div>
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">客户<font color="red">＊</font></label>
							</div>
							<div class="col-sm-3">
								<input type="hidden" id="companyId" value="${company.companyId }">
								<input type="text" class="form-control " placeholder="" readonly="readonly"
									id="companyName" name="companyName" value="${company.companyName }">
							</div>
							<div class="col-sm-2">
                     			<a class="btn btn-info" data-toggle="modal" data-target="#companyModal">公司查找</a>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">账单来源</label>
							</div>
							<div class="col-sm-3">
								<select class="form-control m-b" name="account" id="billSource">
									<option value="0" <c:if test="${etopBill.billSource eq 0}">selected="selected"</c:if>>无</option>
									<option value="1" <c:if test="${etopBill.billSource eq 1}">selected="selected"</c:if>>租赁合同</option>
									<option value="2" <c:if test="${etopBill.billSource eq 2}">selected="selected"</c:if>>外包合同</option>
									<option value="3" <c:if test="${etopBill.billSource eq 3}">selected="selected"</c:if>>能源合同</option>
									<option value="4" <c:if test="${etopBill.billSource eq 4}">selected="selected"</c:if>>物业合同</option>
									<option value="5" <c:if test="${etopBill.billSource eq 5}">selected="selected"</c:if>>服务合同</option>
									<option value="5" <c:if test="${etopBill.billSource eq 6}">selected="selected"</c:if>>园区服务</option>
									<option value="9" <c:if test="${etopBill.billSource eq 9}">selected="selected"</c:if>>其他</option>
								</select>
							</div>
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">关联来源编号</label>
							</div>
							<div class="col-sm-3">
								<input type="text" class="form-control " placeholder=""
									id="billNoOut" value="${etopBill.billNoOut }">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">金额<font color="red">＊</font></label>
							</div>
							<div class="col-sm-3">
								<input type="number" class="form-control " placeholder=""
									id="amount" value="${etopBill.amount}">
							</div>
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">缴费期限<font color="red">＊</font></label>
							</div>
							<div class="col-sm-3">
								<div class="input-group date">
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span> <input name="Field_KH_DFJL_RiQi"
										type="text" class="datepicker form-control" id="deadline"
										value='<fmt:formatDate value="${etopBill.deadline}" pattern="yyyy-MM-dd"/>'>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label">账单详情<font color="red">＊</font></label>
							</div>
							<div class="col-sm-9">
								<textarea class="form-control" rows=10 id="description">${etopBill.description }</textarea>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group" <c:if test="${readonly}">style="display: none;"</c:if>>
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
<script type="text/javascript">
	
$(document).ready(function() {

	$("#signupForm").validate({
		rules : {
			companyId : "required",
			amount : {
				required : true,
				min : 0
			},
			deadline : "required",
			description : "required"
		},
		submitHandler : function(form) {
			submit();
		}
	});
	
	$('#companySelect').change(function() {
		var selected = $('#companySelect option:selected');
		$('#companyId').val(selected.val());
		$('#companyName').val(selected.text());
	});
});
	
var billId = "${etopBill.billId}";
function submit() {
	var billType = $("#billType").val();
	var companyId = $("#companyId").val();
	var billSource = $("#billSource").val();
	var billNoOut = $("#billNoOut").val();
	var amount = $("#amount").val();
	var deadline = $("#deadline").val() + " 23:59:59";
	var description = $("#description").val();
	$.post("update.do", {
		"billId" : billId,
		"billType" : billType,
		"companyId" : companyId,
		"billSource" : billSource,
		"amount" : amount,
		"deadline" : deadline,
		"description" : description,
		"billNoOut" : billNoOut
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
				title : "保存失败！",
				text : data.msg,
				type : "error",
				confirmButtonText : "确定",
				closeOnConfirm : true
			});
		}
	});
}

function searchCompany() {
	$.get(
			'<%=basePath %>/etopCompany/getEtopCompanyList.do',
			{'companyName':$('#companyNameText').val()},
			function(data) {
				var rows = data.rows;
				var selector = $('#companySelect');
				selector.empty();
				for(var i=0; i<rows.length; i++) {
					var oOption = document.createElement('option');
					oOption.text = rows[i].companyName;
					oOption.value = rows[i].companyId;
					selector.append(oOption);
				}
			}
	);
}
</script>
</body>
</html>