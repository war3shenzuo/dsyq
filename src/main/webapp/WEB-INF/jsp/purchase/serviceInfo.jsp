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
									<normal>采购信息</normal>
								</h5>
							</div>
							<div class="ibox-content">
                            <div class="row">
	                            <div class="col-sm-12">
	                            <div class="form-group">
	                            	<label class="col-sm-1 control-label">商品大类</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${purchase.category}" class=" form-control" placeholder="" id="goodsName" readonly="readonly"> 
	                                </div>
	                            	<label class="col-sm-1 control-label">商品名称</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${purchase.goodsName}" class=" form-control" placeholder="" id="goodsName" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">单价</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${purchase.unitPrice }" class=" form-control" placeholder="" id="unitPrice"  readonly="readonly"> 
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
	                                    <input type="text" value="${purchase.number}" class=" form-control" placeholder="" id="number" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">总价</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${purchase.totalPrice}" class=" form-control" placeholder="" id="totalPrice" readonly="readonly"> 
	                                </div>
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
                                    <input type="text" value="${service.serviceNo}" class=" form-control" placeholder="" id="serviceNo" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">服务类型</label>
                                <div class="col-sm-2">
                                    <input type="text" value="公共采购" class=" form-control" placeholder="" id="serviceTypeName" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">服务状态</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.serviceStatus}" class=" form-control" placeholder="" id="serviceStatus" readonly="readonly"> 
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
                                    <input type="text" value="${service.companyName}" class=" form-control" placeholder="" id="companyName" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">申请时间</label>
                                <div class="col-sm-2">
                                    <input type="text" value="<fmt:formatDate value="${service.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class=" form-control" placeholder="" id="applyTime" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">完成时间</label>
                                <div class="col-sm-2">
                                    <input type="text" value="<fmt:formatDate value="${service.completeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class=" form-control" placeholder="" id="completeTime" readonly="readonly"> 
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
                                    <input type="text"value="${service.buildingNo}"  class=" form-control" placeholder="" id="buildingNo" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">层号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.storey}" class=" form-control" placeholder="" id="storey" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">区号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.zoneNo }" class=" form-control" placeholder=""  id="zoneNo" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">房号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.roomNo }" class=" form-control" placeholder=""  id="roomNo" readonly="readonly"> 
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
                                    <input type="text"value="${service.applicant}"  class=" form-control" placeholder="" id="applicant" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">联系方式</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.applicantPhone}" class=" form-control" placeholder="" id="applicantPhone" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">部门</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.applicantDepartment }" class=" form-control" placeholder=""  id="applicantDepartment" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">职位</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.applicantPosition }" class=" form-control" placeholder=""  id="applicantPosition" readonly="readonly"> 
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
                                    <textarea rows="" cols=""  class=" form-control" placeholder="" id="description" readonly="readonly">${service.description}</textarea>
                                </div>
                                
                            </div>
                            </div>
                           
                            </div>
                            
 <%--                            <div class="hr-line-dashed"></div>
                            <div class="row"> 
                            <div class="col-sm-12">
	                        <div class="form-group">
	                        	<label class="col-sm-1 control-label">是否免费</label>
                                <div class="col-sm-2">
                                    <select class="col-md-2 form-control m-b" name="account" id="isFree">
                                        <option value="0" <c:if test="${user.isfree=='0'}">selected</c:if> >免费</option>
										<option value="1" <c:if test="${user.isfree=='1'}">selected</c:if> >不免费</option>
                                    </select>
                                </div>
                            
                            </div>
                            </div>
                            
                           </div>   --%>                    

<%--                             <div class="hr-line-dashed"></div>
                            <div class="row">
	                            <div class="col-sm-12">
	                            <div class="form-group">
	                            	<label class="col-sm-1 control-label">采购描述</label>
	                                <div class="col-sm-5">
	                                	<textarea class=" form-control" id="description" rows="" cols="">${purchase.description}</textarea>
	                                </div>
	                                
	                            </div>
	                            </div>
                            </div> --%>
<%-- 								<c:if test="${bill ==1 }">
									<div class="hr-line-dashed"></div>
									<div class="col-sm-12">
										<div class="form-group">
											<div class="col-sm-12" style="text-align: center;">
												<a class="btn btn-primary"
													onclick="bill('${service.serviceId}')">出账</a>
											</div>
										</div>
									</div>
								</c:if> --%>
							</div>
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../shared/js.jsp"/>
   	<script type="text/javascript">
    	var basePath='<%=basePath%>';
    	$(document).ready(function () {
    		$("#serviceStatus").val(formatterServiceStatusType($("#serviceStatus").val(),'',''));
    	});
    	function bill(serviceId){
    		$.post(basePath+"/purchase/addBill.do",{"serviceId":serviceId},function(data){
    			if(data.status==10001){
    				swal({   title: "保存成功！",   
						 type: "success",  
						 confirmButtonText: "ok",  
						 closeOnConfirm: false 
					  }, 
					  function(){  
							 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							 parent.layer.close(index); 
					  }
					 );
    		    }else{
    		    	swal( "操作失败！", "","error");
    		    }

    		});
    	}
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/park.js"></script>
</body>
</html>