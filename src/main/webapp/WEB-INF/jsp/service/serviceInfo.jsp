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
                           <div class="row">
                           
                            <div class="col-sm-12">
                          	<div class="form-group">
                                <label class="col-sm-1 control-label">服务编号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.serviceNo}" class=" form-control" placeholder="" id="serviceNo"> 
                                </div>
                                <label class="col-sm-1 control-label">服务类型</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.serviceTypeName}" class=" form-control" placeholder="" id="serviceTypeName"> 
                                </div>
                                <label class="col-sm-1 control-label">服务状态</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.serviceStatus}" class=" form-control" placeholder="" id="serviceStatus"> 
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
                                    <input type="text" value="${service.companyName}" class=" form-control" placeholder="" id="companyName"> 
                                </div>
                                <label class="col-sm-1 control-label">申请时间</label>
                                <div class="col-sm-2">
                                    <input type="text" value="<fmt:formatDate value="${service.applyTime}" pattern="yyyy-MM-dd hh:mm:ss"/>" class=" form-control" placeholder="" id="applyTime"> 
                                </div>
                                <label class="col-sm-1 control-label">完成时间</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.completeTime}" class=" form-control" placeholder="" id="completeTime"> 
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
                                    <input type="text"value="${service.buildingNo}"  class=" form-control" placeholder="" id="buildingNo"> 
                                </div>
                                <label class="col-sm-1 control-label">层号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.storey}" class=" form-control" placeholder="" id="storey"> 
                                </div>
                                <label class="col-sm-1 control-label">区号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.zoneNo }" class=" form-control" placeholder=""  id="zoneNo"> 
                                </div>
                                <label class="col-sm-1 control-label">房号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.roomNo }" class=" form-control" placeholder=""  id="roomNo"> 
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
                                    <input type="text"value="${service.applicant}"  class=" form-control" placeholder="" id="applicant"> 
                                </div>
                                <label class="col-sm-1 control-label">申请人联系方式</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.applicantPhone}" class=" form-control" placeholder="" id="applicantPhone"> 
                                </div>
                                <label class="col-sm-1 control-label">申请人部门</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.applicantDepartment }" class=" form-control" placeholder=""  id="applicantDepartment"> 
                                </div>
                                <label class="col-sm-1 control-label">申请人职位</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.applicantPosition }" class=" form-control" placeholder=""  id="applicantPosition"> 
                                </div>
                                
                            </div>
                            </div>
                            
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            
                            <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-1">服务描述</label>
                                <div class="col-sm-4">
                                    <textarea rows="" cols=""  class=" form-control" placeholder="" id="description">${service.description}</textarea>
                                </div>
                                
                            </div>
                            </div>
                           
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            <div class="row"> 
                            <div class="col-sm-12">
	                        <div class="form-group">
	                        	<label class="col-sm-1">是否免费</label>
                                <div class="col-sm-2">
                                    <select class="col-md-2 form-control m-b" name="account" id="isFree">
                                        <option value="1" <c:if test="${user.isfree=='1'}">selected</c:if> >免费</option>
										<option value="0" <c:if test="${user.isfree=='0'}">selected</c:if> >不免费</option>
                                    </select>
                                </div>
                            
                            </div>
                            </div>
                            
                           </div>                      
                           
                           <%-- <div class="col-sm-12">
	                           <div class="form-group">
	                                <div class="col-sm-12" style="text-align: center;">
	                                    <a class="btn btn-primary"  onclick="updatesubmit('${park.id}')">保存内容</a>
	                                </div>
	                            </div>
                            </div> --%>
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
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/park.js"></script>
</body>
</html>