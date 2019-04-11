<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.etop.management.properties.ImgProperties"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>服务详情</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
	<style>
	.outimg{ width:80px; height:80px; margin-left:10px; float: left; }
	.outimg img{ width:80px; height:80px; }
	.outimg a{ display: block; text-align: center;height: 22px; line-height: 22px; }
	</style>
</head>
<body class="gray-bg">
<input id="serviceId" value='${service.serviceId}' type="hidden">
<%-- <input id="isAffirm" value='${etopResume.isAffirm}' type="hidden"> --%>
    <div class="wrapper wrapper-content animated fadeInRight">
 
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                    	<form method="get" class="form-horizontal">
                <div class="row">
                <div class="col-sm-12">
           		<div class="ibox-title">
					<h5>
						<normal>操作记录</normal>
					</h5>
				</div>	
		
				<div class="ibox-content">
						<div class="form-group">
						<label class="col-sm-1 control-label">审核价格</label>
							<div class="col-sm-3">
								<input type="text" class="form-control"  value="${etopservice.finalPrice}" id="finalPrice"  readonly="readonly">
							</div>
						<c:if test="${service.serviceStatus == 102}">
							
								<div class="col-sm-3" style="text-align: center; ">
									<a  id="sub" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submitOut()"> 撤销该申请</a>
									<a  id="sub" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submitAgree()"> 同意该报价</a>
								</div>
							
			           </c:if>
							</div>
						    <div class="hr-line-dashed"></div>
						    <div class="form-group">
							<label class="col-sm-1 control-label">操作记录</label>
							<div class="col-sm-11">
								<textarea class="form-control" readonly="readonly" rows=5>${service.changes}</textarea>
							</div>
						</div>
				</div>	
				</div>
				</div> 
				<div class="ibox-title">
								<h5>
									<normal>代招需求</normal>
								</h5>
							</div>
							<div class="ibox-content">
                            <div class="row">
	                            <div class="col-sm-12">
	                            <div class="form-group">
	                            	<label class="col-sm-1 control-label">类别</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${etopservice.category}" class=" form-control" placeholder="" id="goodsName" readonly="readonly"> 
	                                </div>
	                            	<label class="col-sm-1 control-label">项目名称</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${etopservice.subject}" class=" form-control" placeholder="" id="goodsName" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">单价</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${etopservice.unitPrice }" class=" form-control" placeholder="" id="unitPrice"  readonly="readonly"> 
	                                </div>
	                               
	                           	</div>
	                            </div>
	                            </div>
	                            <div class="hr-line-dashed"></div>
                                <div class="row">
                                   <div class="col-sm-12">
                          		  	<div class="form-group">
	                                <label class="col-sm-1 control-label">需求人数</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${etopservice.number}" class=" form-control" placeholder="" id="number" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">总价</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${etopservice.totalPrice}" class=" form-control" placeholder="" id="totalPrice" readonly="readonly"> 
	                                </div>
	                                
	                                <c:if test="${service.serviceStatus == 203 || service.serviceStatus == 204 || service.serviceStatus == 300}">
	                                
	                                <div class="col-sm-3" style="text-align: center; ">
									<button id="readResume" class="btn btn-primary" type="button" >查看简历</button>
							   		</div> 
							   		</c:if>
<!-- 									<div class="col-sm-3" style="text-align: center; ">
										<a  id="sub" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submit()">撤销</a>
										<a  id="subtwo" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submittwo()">确认面试</a>
								   </div> -->
								   
<%-- 			           		   		</c:if> --%>
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
                                    <input type="text" value="${service.serviceNo}" class=" form-control" placeholder="" id="serviceNo"  readonly="readonly" > 
                                </div>
                                <label class="col-sm-1 control-label">服务类型</label>
                                <div class="col-sm-2">
                                    <input type="text" value="人员代招" class=" form-control" placeholder="" id="serviceTypeName"  readonly="readonly" > 
                                </div>
                                <label class="col-sm-1 control-label">服务状态</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.serviceStatus}" class=" form-control" placeholder="" id="serviceStatus"  readonly="readonly" > 
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
                                    <input type="text" value="${service.companyName}" class=" form-control" placeholder="" id="companyName"  readonly="readonly" > 
                                </div>
                                <label class="col-sm-1 control-label">申请时间</label>
                                <div class="col-sm-2">
                                    <input type="text" value="<fmt:formatDate value="${service.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class=" form-control" placeholder="" id="applyTime"  readonly="readonly" > 
                                </div>
                                <label class="col-sm-1 control-label">完成时间</label>
                                <div class="col-sm-2">
                                    <input type="text" value="<fmt:formatDate value="${service.completeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class=" form-control" placeholder="" id="completeTime"  readonly="readonly" > 
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
                                    <input type="text"value="${service.buildingNo}"  class=" form-control" placeholder="" id="buildingNo"  readonly="readonly" > 
                                </div>
                                <label class="col-sm-1 control-label">层号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.storey}" class=" form-control" placeholder="" id="storey"  readonly="readonly" > 
                                </div>
                                <label class="col-sm-1 control-label">区号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.zoneNo }" class=" form-control" placeholder=""  id="zoneNo"  readonly="readonly" > 
                                </div>
                                <label class="col-sm-1 control-label">房号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.roomNo }" class=" form-control" placeholder=""  id="roomNo"  readonly="readonly" > 
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
                                    <input type="text"value="${service.applicant}"  class=" form-control" placeholder="" id="applicant"  readonly="readonly" > 
                                </div>
                                <label class="col-sm-1 control-label">联系方式</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.applicantPhone}" class=" form-control" placeholder="" id="applicantPhone"  readonly="readonly" > 
                                </div>
                                <label class="col-sm-1 control-label">部门</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.applicantDepartment }" class=" form-control" placeholder=""  id="applicantDepartment"  readonly="readonly" > 
                                </div>
                                <label class="col-sm-1 control-label">职位</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.applicantPosition }" class=" form-control" placeholder=""  id="applicantPosition"  readonly="readonly" > 
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
                                    <textarea rows="" cols=""  class=" form-control" placeholder="" id="description"  readonly="readonly" >${service.description}</textarea>
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
									<normal>岗位信息</normal>
								</h5>
							</div>
							<div class="ibox-content">
                            <div class="row">
	                            <div class="col-sm-12">
	                            <div class="form-group">
	                            	<label class="col-sm-1 control-label">学历</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.education}" class=" form-control" placeholder="" id="education"  readonly="readonly" > 
	                                </div>
	                                <label class="col-sm-1 control-label">年龄</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.age }" class=" form-control" placeholder="" id="age"  readonly="readonly" > 
	                                </div>
	                               
	                                <label class="col-sm-1 control-label">性别</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.sexualStr}" class=" form-control" placeholder="" id="sexual"  readonly="readonly" > 
	                                </div>
	                                <label class="col-sm-1 control-label">政治面貌</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.politicalFeature}" class=" form-control" placeholder="" id="politicalFeature"  readonly="readonly" > 
	                                </div>
	                                
	                            </div>
	                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
	                            <div class="col-sm-12">
	                            <div class="form-group">
	                            	<label class="col-sm-1 control-label">岗位名称</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.jobName}" class=" form-control" placeholder="" id="jobName"  readonly="readonly" > 
	                                </div>
	                                <label class="col-sm-1 control-label">部门</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.department }" class=" form-control" placeholder="" id="department"  readonly="readonly" > 
	                                </div>
	                                <label class="col-sm-1 control-label">职位</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.position}" class=" form-control" placeholder="" id="position"  readonly="readonly" > 
	                                </div>
	                                <label class="col-sm-1 control-label">职责</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.duties}" class=" form-control" placeholder="" id="duties"  readonly="readonly" > 
	                                </div>
	                                
	                            </div>
	                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
	                            <div class="col-sm-12">
	                            <div class="form-group">
	                            	<label class="col-sm-1 control-label">工作年限</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.workAge}" class=" form-control" placeholder="" id="workAge"  readonly="readonly" > 
	                                </div>
	                                <label class="col-sm-1 control-label">薪资</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.salary }" class=" form-control" placeholder="" id="salary"  readonly="readonly" > 
	                                </div>
	                                <label class="col-sm-1 control-label">保险</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.insurance}" class=" form-control" placeholder="" id="insurance"  readonly="readonly" > 
	                                </div>
	                                <label class="col-sm-1 control-label">福利</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.benefit}" class=" form-control" placeholder="" id="benefit"  readonly="readonly" > 
	                                </div>
	                                
	                            </div>
	                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
	                            <div class="col-sm-12">
	                            <div class="form-group">
	                                <label class="col-sm-1 control-label">婚否</label>
	                                <div class="col-sm-2">
		                                <c:if test="${recruitment.marriage == 1}">
	                                     <input type="text" value="已婚" class=" form-control" placeholder="" id="marriage" readonly="readonly"> 
		                                </c:if>
		                                <c:if test="${recruitment.marriage == 2}">
		                                    <input type="text" value="未婚" class=" form-control" placeholder="" id="marriage" readonly="readonly"> 
		                                </c:if>
		                                <c:if test="${recruitment.marriage == 3}">
		                                    <input type="text" value="无要求" class=" form-control" placeholder="" id="marriage" readonly="readonly"> 
		                                </c:if>
	                                </div>
	                                <label class="col-sm-1 control-label">是否生育</label>
	                                <div class="col-sm-2">
		                                 <c:if test="${recruitment.childbearing == '1'}">
		                                 <input type="text" value="已生" class=" form-control" placeholder="" id="benefit"  readonly="readonly" > 
		                                 </c:if>
		                                <c:if test="${recruitment.childbearing == '0'}">
		                                 <input type="text" value="未生" class=" form-control" placeholder="" id="benefit"  readonly="readonly" > 
		                                 </c:if>
		                                <c:if test="${recruitment.childbearing == '2'}">
		                                 <input type="text" value="无要求" class=" form-control" placeholder="" id="benefit"  readonly="readonly" > 
		                                 </c:if>
	                                </div>
	                            	<label class="col-sm-1 control-label">其他要求</label>
	                                <div class="col-sm-5">
	                                	<textarea class=" form-control" id="otherRequire" rows="" cols=""  readonly="readonly" >${recruitment.otherRequire}</textarea>
	                                </div>
	                                
	                            </div>
	                            </div>
                            </div>
							</div>
							</div>
							</div>
							</div>
<%-- 							<div class="row">
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
						            </tr>
						            </thead>
				       		   </table>
                        </div>
                        <!-- End Example Events -->
                    </div>
			</div>
                  
						</div>
			
				        </div>  --%>
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../shared/js.jsp"/>
   	<script type="text/javascript">
    	var basePath='<%=basePath%>';
    	var serviceId = $("#serviceId").val();
    	$(document).ready(function () {
    		$("#serviceStatus").val(formatterServiceStatusType($("#serviceStatus").val(),'',''));
    		$('#readResume').click(function() {
     			var serviceId = $("#serviceId").val();
     			var isAffirm = $("#isAffirm").val();

    			var read = "<%=basePath%>/resume/index2.do?serviceId="+serviceId;
   				totabsss(read, '简历');

    		});
    	});
    	 function submitOut(){
    	     swal({
    	             title: "确认撤销该申请? ",
    	             type: "warning",
    	             showCancelButton: true,
    	             confirmButtonColor: "#DD6B55",
    	             confirmButtonText: "是",
    	             cancelButtonText: "否",
    	             closeOnConfirm: false
    	         }, function () {
    	       
    			var finalPrice = $("#finalPrice").val();

    			var param={
    			     	"serviceId":serviceId,
    			     	"finalPrice":finalPrice

    			}
    			$.post('<%=basePath%>/companyService/cancel.do',param,function(data){
    				
    				
    				if (data.status == 10001) {
    					swal({
    						title : "撤销成功！",
    						type : "success",
    						confirmButtonText : "确定",
    						closeOnConfirm : true
    					}, function() {
    						window.location.reload();
    					});

    				} else {
    					swal({
    						title : "撤销失败！",
    						text : data.msg,
    						type : "error",
    						confirmButtonText : "确定",
    						closeOnConfirm : true
    					});
    				}});
    		}
    	     );
    	 }

    		function submitAgree(){
    	        swal({
    	                title: "接受报价? ",
    	                type: "warning",
    	                showCancelButton: true,
    	                confirmButtonColor: "#DD6B55",
    	                confirmButtonText: "是",
    	                cancelButtonText: "否",
    	                closeOnConfirm: false
    	            }, function () {
    	          
    			var finalPrice = $("#finalPrice").val();

    			var param={
    			     	"serviceId":serviceId,
    			     	"finalPrice":finalPrice

    			}
    			$.post('<%=basePath%>/companyService/approve.do',param,function(data){
    				
    				
    				if (data.status == 10001) {
    					swal({
    						title : "提交成功！",
    						type : "success",
    						confirmButtonText : "确定",
    						closeOnConfirm : true
    					}, function() {
    						window.location.reload();
    					});

    				} else {
    					swal({
    						title : "提交失败！",
    						text : data.msg,
    						type : "error",
    						confirmButtonText : "确定",
    						closeOnConfirm : true
    					});
    				}
    			
    		   });
    		}
    	        );
    	    }
    	 function submit(){
    	     swal({
    	             title: "确认撤销该申请? ",
    	             type: "warning",
    	             showCancelButton: true,
    	             confirmButtonColor: "#DD6B55",
    	             confirmButtonText: "是",
    	             cancelButtonText: "否",
    	             closeOnConfirm: false
    	         }, function () {
    	       
    			var serviceId = $("#serviceId").val();

    			var param={
    			     	"serviceId":serviceId

    			}
    			$.post('<%=basePath%>/etopService/updateStatus.do?status=201',param,function(data){
    				
    				
    				if (data.status == 10001) {
    					swal({
    						title : "撤销成功！",
    						type : "success",
    						confirmButtonText : "确定",
    						closeOnConfirm : true
    					}, function() {
    						window.location.reload();
    					});

    				} else {
    					swal({
    						title : "撤销失败！",
    						text : data.msg,
    						type : "error",
    						confirmButtonText : "确定",
    						closeOnConfirm : true
    					});
    				}});
    		}
    	     );
    	 }
    	 
    		function submittwo(){
    	        swal({
    	                title: "确认要面试该人? ",
    	                type: "warning",
    	                showCancelButton: true,
    	                confirmButtonColor: "#DD6B55",
    	                confirmButtonText: "是",
    	                cancelButtonText: "否",
    	                closeOnConfirm: false
    	            }, function () {
    	          
    	    			var serviceId = $("#serviceId").val();

    	    			var param={
    	    			     	"serviceId":serviceId


    			}
    	    			$.post('<%=basePath%>/etopService/updateStatus.do?status=202',param,function(data){
    				
    				
    				if (data.status == 10001) {
    					swal({
    						title : "已确认成功！",
    						type : "success",
    						confirmButtonText : "确定",
    						closeOnConfirm : true
    					}, function() {
    						window.location.reload();
    					});

    				} else {
    					swal({
    						title : "失败！",
    						text : data.msg,
    						type : "error",
    						confirmButtonText : "确定",
    						closeOnConfirm : true
    					});
    				}
    			
    		   });
    		}
    	        );
    	    }
    		function submitNoOver(){
    	        swal({
    	                title: "尚未完工? ",
    	                type: "warning",
    	                showCancelButton: true,
    	                confirmButtonColor: "#DD6B55",
    	                confirmButtonText: "是",
    	                cancelButtonText: "否",
    	                closeOnConfirm: false
    	            }, function () {
    	          

    			var param={
    			     	"serviceId":serviceId,
    			     	"status": "201"

    			}
    			$.post('<%=basePath%>/etopService/updateStatus.do',param,function(data){
    				
    				
    				if (data.status == 10001) {
    					swal({
    						title : "提交成功！",
    						type : "success",
    						confirmButtonText : "确定",
    						closeOnConfirm : true
    					}, function() {
    						window.location.reload();
    					});

    				} else {
    					swal({
    						title : "提交失败！",
    						text : data.msg,
    						type : "error",
    						confirmButtonText : "确定",
    						closeOnConfirm : true
    					});
    				}
    			
    		   });
    		}
    	        );
    	    }
    		function submitOver(){
    	        swal({
    	                title: "是否确认完工并出账单? ",
    	                type: "warning",
    	                showCancelButton: true,
    	                confirmButtonColor: "#DD6B55",
    	                confirmButtonText: "是",
    	                cancelButtonText: "否",
    	                closeOnConfirm: false
    	            }, function () {
    	          
    	            	var serviceId = $("#serviceId").val();
    	            	var finalPrice = $("#totalPrice").val();
    	            	
    			var param={
    			     	"serviceId":serviceId,
    			     	"finalPrice":finalPrice
    			     	
    			}
    			$.post('<%=basePath%>/quotation/addService.do',param,function(data){
    				
    				
    				if (data.status == 10001) {
    					swal({
    						title : "已出账！",
    						type : "success",
    						confirmButtonText : "确定",
    						closeOnConfirm : true
    					}, function() {
    						window.location.reload();
    					});

    				} else {
    					swal({
    						title : "出账失败！",
    						text : data.msg,
    						type : "error",
    						confirmButtonText : "确定",
    						closeOnConfirm : true
    					});
    				}
    			
    		   });
    		}
    	        );
    	    }
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/park.js"></script>
    
</body>
</html>