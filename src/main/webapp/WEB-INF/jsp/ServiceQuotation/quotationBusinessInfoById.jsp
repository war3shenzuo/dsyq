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
			<div class="ibox-content">	
				<div class="row">
				<div class="col-sm-12">
				<div class="ibox-title">
					<h5>
						<normal>操作记录</normal>
					</h5>
				</div>				
				<div class="ibox-content">
						<div class="form-group">
							<label class="col-sm-1 control-label">操作记录</label>
							<div class="col-sm-11">
								<textarea class="form-control" readonly="readonly" rows=5>${Parkservice.changes}</textarea>
							</div>
						</div>
				</div>
				</div>
				</div>
			</div>	
					 <div class="ibox-title">
								<h5>
									<normal>服务信息</normal>
								</h5>
					</div>
					<div class="ibox-content">
                            <div class="row">
	                            <div class="col-sm-12">
	                            <div class="form-group">
	                            	<label class="col-sm-1 control-label">类别</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${Parkservice.category}" class=" form-control" placeholder="" id="goodsName" readonly="readonly"> 
	                                </div>
	                            	<label class="col-sm-1 control-label">项目名称</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${Parkservice.subject}" class=" form-control" placeholder="" id="goodsName" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">单价</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${Parkservice.unitPrice }" class=" form-control" placeholder="" id="unitPrice"  readonly="readonly"> 
	                                </div>
	                                </div>
	                            </div>
	                            </div>
	                                <div class="hr-line-dashed"></div>
	                                <div class="row">
	                                      <div class="col-sm-12">
	                            		  <div class="form-group">
	                                <label class="col-sm-1 control-label">数量</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${Parkservice.number}" class=" form-control" placeholder="" id="number" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">总价</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${Parkservice.totalPrice}" class=" form-control" placeholder="" id="totalPrice" readonly="readonly"> 
	                                </div>
	                                
	                                <label class="col-sm-1 control-label">审核价格</label>
									<div class="col-sm-2">
										<input type="text" class="form-control"  value="${Parkservice.finalPrice}" id="finalPrice" readonly="readonly">
									</div>
<%-- 								<c:if test="${Parkservice.serviceStatus == 101}">		
							<div class="col-sm-1" style="text-align: center; ">
						     <a  id="sub" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submit()">发布最终报价 </a>
					        </div>
					        	</c:if> --%>
					        	
	                                </div>
	                            </div>
	                            </div> 
	                   </div>
	                  
	                       <div class="row">
                           <div class="col-sm-12">
                           <div class="ibox-title">
								<h5>
									<normal>服务基本信息</normal>
								</h5>
						   </div>
						   <div class="ibox-content">
                            <div class="row">
                            <div class="col-sm-12">
                          	<div class="form-group">
                                <label class="col-sm-1 control-label">服务编号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${Parkservice.serviceNo}" class=" form-control" placeholder="" id="serviceNo" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">服务类型</label>
                                <div class="col-sm-2">
                                	<c:if test="${Parkservice.serviceType == 'WYBX'}">
                                    <input type="text" value="物业维修" class=" form-control" placeholder="" id="serviceTypeName" readonly="readonly"> 
                                    </c:if>
                                	<c:if test="${Parkservice.serviceType == 'SWFW'}">
                                    <input type="text" value="商务服务" class=" form-control" placeholder="" id="serviceTypeName" readonly="readonly"> 
                                    </c:if>
                                </div>
                                <label class="col-sm-1 control-label">服务状态</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${Parkservice.serviceStatus}" class=" form-control" placeholder="" id="serviceStatus" readonly="readonly"> 
                                </div>
                                
                            </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                             
                            <div class="col-sm-12">
                            <div class="form-group">
                            	<label class="col-sm-1 control-label">申请公司</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${Parkservice.companyName}" class=" form-control" placeholder="" id="companyName" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">申请时间</label>
                                <div class="col-sm-2">
                                    <input type="text" value="<fmt:formatDate value="${Parkservice.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class=" form-control" placeholder="" id="applyTime" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">完成时间</label>
                                <div class="col-sm-2">
                                    <input type="text" value="<fmt:formatDate value="${Parkservice.completeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class=" form-control" placeholder="" id="completeTime" readonly="readonly"> 
                                </div>
                                
                            </div>
                            </div>
                            
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            
                            <div class="col-sm-12">
                            <div class="form-group">
                            	<label class="col-sm-1 control-label">楼号</label>
                                <div class="col-sm-2">
                                    <input type="text"value="${Parkservice.buildingNo}"  class=" form-control" placeholder="" id="buildingNo" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">层号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${Parkservice.storey}" class=" form-control" placeholder="" id="storey" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">区号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${Parkservice.zoneNo }" class=" form-control" placeholder=""  id="zoneNo" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">房号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${Parkservice.roomNo }" class=" form-control" placeholder=""  id="roomNo" readonly="readonly"> 
                                </div>
                                
                                
                            </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                             
                            <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">申请人</label>
                                <div class="col-sm-2">
                                    <input type="text"value="${Parkservice.applicant}"  class=" form-control" placeholder="" id="applicant" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">联系方式</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${Parkservice.applicantPhone}" class=" form-control" placeholder="" id="applicantPhone" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">部门</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${Parkservice.applicantDepartment}" class=" form-control" placeholder=""  id="applicantDepartment" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">职位</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${Parkservice.applicantPosition}" class=" form-control" placeholder=""  id="applicantPosition" readonly="readonly"> 
                                </div>
                                
                            </div>
                            </div>
                            
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            
                            <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">补充信息</label>
                                <div class="col-sm-4">
                                    <textarea rows="" cols=""  class=" form-control" placeholder="" id="description" readonly="readonly">${Parkservice.description}</textarea>
                                </div>
                                
                            </div>
                            </div>
                           
                            </div>
                       </div>     
                            
                       </div> 
                       </div>
			<div class="row">
            <div class="col-sm-12">
			<div class="ibox-title">
					<h5>
						<normal>派工信息</normal>
					</h5>
				</div>
				<div class="ibox-content">
					<div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">

	                           	<table id="table1"
	                           	   data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="<%=basePath %>/business/dispatchList.do?serviceId=${Parkservice.serviceId}"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-query-params = "queryParams"
					               data-striped="true"
					              >
						            <thead>
						            <tr>

						                <th data-field="name" data-align="center">委派人员</th>
						                <th data-field="phone" data-align="center">联系方式</th>
						                <th data-field="email" data-align="center">邮箱</th>
						                <th data-field="dispatcher" data-align="center">委派人员</th>
						                <th data-field="despatchTime" data-align="center">派工时间</th>
						                <th data-field="completeTime" data-align="center">完成时间</th>
						                <th data-field="description" data-align="center">补充描述</th>
<!-- 						                <th data-align="center" data-formatter='formatterFun2' >操作</th> -->
						            </tr>
						            </thead>
				       		   </table>
                        </div>
                        <!-- End Example Events -->
                    </div>
			</div>
			</div>
			</div>	
</div>
<jsp:include page="../shared/js.jsp" />
<script type="text/javascript">



	function submit() {

		var serviceId = $("#serviceId").val();
		var finalPrice = $("#finalPrice").val();

		
		var param={
				"serviceId":serviceId,
		     	"finalPrice":finalPrice

		}
		$.post('<%=basePath%>/quotation/updateFinalPrice.do?service_id =${Parkservice.serviceId}',param,function(data){
			
			
			if (data.status == 10001) {
				swal({
					title : "更新成功！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : true
				} ,function() {
					window.location.reload();
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