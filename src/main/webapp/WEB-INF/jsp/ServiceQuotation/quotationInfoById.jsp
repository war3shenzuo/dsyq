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
<title>维修服务详情</title>
<style>
	table a:hover{text-decoration: underline;}
	table a{margin-left: 10px}
</style>
</head>
<body class="grey-bg">
<input value="${ServiceQuotation.quotationId}" id="quotationId"  type="hidden">
<div class="wrapper wrapper-content animated fadeInRight">
		<div class="col-sm-12">
	<div class="ibox float-e-margins">
		<div class="ibox-title">
			<h5>
				<normal>维修服务信息</normal>
			</h5>
		</div>
		<div class="ibox-content">
			<form method="get" class="form-horizontal"  id="signupForm">
			<div class="form-group">

				
				<div class="form-group">	
					<label class="col-sm-3 control-label">类别<font color="red">*</font></label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" value="${ServiceQuotation.category}"  id="category">
	                </div>
	            </div>
				<div class="form-group">	
					<label class="col-sm-3 control-label">维修服务名称</label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" value="${ServiceQuotation.quotationName}" id="quotationName">
	                </div>
				</div>
					<div class="form-group">
					<label class="col-sm-3 control-label">价格</label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" value="${ServiceQuotation.price}" id="price">
					</div>
                   </div>
                   
<%-- 				<div class="form-group">
					<label class="col-sm-3 control-label">大写金额</label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" value="${ServiceQuotation.capitalPrice}" id="capitalPrice">
					</div>
				</div> --%>
				<div class="form-group">
					<label class="col-sm-3 control-label">单位</label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" value="${ServiceQuotation.units}" id="units">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">说明</label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" value="${ServiceQuotation.description}" id="description">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">备注</label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" value="${ServiceQuotation.remark}" id="remark">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">创建时间</label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" id="description" readonly="readonly"
						value='<fmt:formatDate value="${ServiceQuotation.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'>
					</div>
				</div>
			</div>
			</form>
				<div class="form-group">
					<div class="col-sm-7 col-sm-offset-2" style="text-align: center; ">

						<a  id="sub" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submit()"> 更新 </a>
					</div>
				</div>
</div>
</div>
</div>
</div>

<jsp:include page="../shared/js.jsp" />
<script type="text/javascript">

var quotationId = $("#quotationId").val();

	function submit() {


		var category = $("#category").val();
		var quotationName = $("#quotationName").val();
		var price = $("#price").val();
		var capitalPrice = $("#capitalPrice").val();
		var description = $("#description").val();
		var units = $("#units").val();
		var remark = $("#remark").val();
		
		var param={
		     	"category":category,
		     	"quotationId":quotationId,
		     	"quotationName":quotationName,
				"price" : price,
				"capitalPrice" : capitalPrice,
				"description" : description,
				"units" : units,
				"remark" : remark
		}
		$.post('<%=basePath%>/quotation/updateQuotation.do',param,function(data){
			
			
			if (data.status == 10001) {
				swal({
					title : "更新成功！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : false
				}, function() {
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index);
				});

			} else {
				swal({
					title : "更新失败！",
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
			quotationId : "required"
		},
		submitHandler : function(form) {
			submit();
		}
	})
</script>
</body>
</html>