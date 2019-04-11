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
<input type="hidden" class="form-control" readonly="readonly" value="${Parkservice.serviceId}" id="serviceId">
<div class="wrapper wrapper-content animated fadeInRight">
			
					<div class="ibox-title">
						<h5>
							<normal>服务信息</normal>
						</h5>
					</div>
					<div class="ibox-content">
                        <form method="get" class="form-horizontal"  id="signupForm">
	                      <div class="form-group">
	                      
	                      		<div class="form-group">  
                                <label class="col-sm-3 control-label">服务编号</label>
                                <div class="col-sm-7">
                                    <input type="text" value="${Parkservice.serviceNo}" class=" form-control" placeholder="" id="companyName" readonly="readonly"> 
                                </div>
                                </div> 
                                
                                 <div class="form-group">  
	                                <label class="col-sm-3 control-label">申请公司</label>
                                <div class="col-sm-7">
                                    <input type="text" value="${Parkservice.companyName}" class=" form-control" placeholder="" id="companyName" readonly="readonly"> 
                                </div>
                                </div> 
                                
	                            <div class="form-group">  
                                <label class="col-sm-3 control-label">申请日期</label>
                                <div class="col-sm-7">
                                    <input type="text" value="<fmt:formatDate value="${Parkservice.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class=" form-control" placeholder="" id="applyTime" readonly="readonly"> 
                               	</div> 
                               	</div>
	                            <div class="form-group">
	                            	<label class="col-sm-3 control-label">类别</label>
	                                <div class="col-sm-7">
	                                    <input type="text" value="${Parkservice.category}" class=" form-control" placeholder="" id="goodsName" readonly="readonly"> 
	                                </div>
	                             </div>
	                              
	                             <div class="form-group">  
	                            	<label class="col-sm-3 control-label">项目名称</label>
	                                <div class="col-sm-7">
	                                    <input type="text" value="${Parkservice.subject}" class=" form-control" placeholder="" id="goodsName" readonly="readonly"> 
	                                </div>
	                             </div>
	                              
	                             <div class="form-group">  
	                                <label class="col-sm-3 control-label">单价</label>
	                                <div class="col-sm-7">
	                                    <input type="text" value="${Parkservice.unitPrice }" class=" form-control" placeholder="" id="unitPrice"  readonly="readonly"> 
	                                </div>
	                             </div> 
	                             
	                            <div class="form-group">  
	                                <label class="col-sm-3 control-label">数量</label>
	                                <div class="col-sm-7">
	                                    <input type="text" value="${Parkservice.number}" class=" form-control" placeholder="" id="number" readonly="readonly"> 
	                                </div>
	                             </div> 
	                             
	                             <div class="form-group">     
	                                <label class="col-sm-3 control-label">金额</label>
	                                <div class="col-sm-7">
	                                    <input type="text" value="${Parkservice.totalPrice}" class=" form-control" placeholder="" id="totalPrice" readonly="readonly"> 
	                                </div>
	                             </div> 
	                             
	                             <div class="form-group">     
				                    <label class="col-sm-3 control-label">审核价格</label>
									<div class="col-sm-7">
										<input type="text" class="form-control"  value="${Parkservice.finalPrice}" id="finalPrice" name="finalPrice">
									</div>
								</div> 
								
	                        <div class="form-group">  
								<div class="col-sm-12" style="text-align: center; ">
<!-- 							     <a  id="sub" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submit()">发布最终报价 </a> -->
	                                    <input class="btn btn-primary" type="submit" value="发布最终报价 ">
						        </div>
					 		 </div>
					  
					  </div>
					  </form>
				</div>
</div>
<jsp:include page="../shared/js.jsp" />
<script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
<script type="text/javascript">
var e = "<i class='fa fa-times-circle'></i> ";
$("#signupForm").validate({     
	rules: {//这里加校验规则
		finalPrice:{
			required:true,
			number:true
		}
		
	},
	messages: {//这里给对应的提示
		finalPrice:{
			required:e+"必填项未填",
			number:e+"必需是数字"
		}
		
	},
    submitHandler: function(form){      
    	submit(); //去提交   
    }  
})

	function submit() {

		var serviceId = $("#serviceId").val();
		var finalPrice = $("#finalPrice").val();
		var totalPrice = $("#totalPrice").val();

		
		var param={
				"serviceId":serviceId,
		     	"finalPrice":finalPrice,
		     	"totalPrice":totalPrice

		}
		$.post('<%=basePath%>/quotation/updateFinalPrice.do?service_id =${Parkservice.serviceId}',param,function(data){
			
			
			if (data.status == 10001) {
				swal({
					title : "更新成功！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : true
				} ,function() {
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					 parent.layer.close(index); 
				}
				);
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