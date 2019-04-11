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
	                                <label class="col-sm-1 control-label">审核价格</label>
									<div class="col-sm-2">
										<input type="text" class="form-control"  value="${etopservice.finalPrice}" id="finalPrice"  readonly="readonly">
									</div>
	                                <c:if test="${service.serviceStatus == 203 || service.serviceStatus == 204 || service.serviceStatus == 300}">
		                                <div class="col-sm-3" style="text-align: center; ">
										<button id="readResume" class="btn btn-primary" type="button" >查看简历</button>
								   		</div>
			           		   		</c:if>
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
                                    <input type="text" value="人员代招" class=" form-control" placeholder="" id="serviceTypeName" readonly="readonly"> 
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
                                    <input type="text" value="${service.applicantDepartment}" class=" form-control" placeholder=""  id="applicantDepartment" readonly="readonly"> 
                                </div>
                                <label class="col-sm-1 control-label">职位</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.applicantPosition}" class=" form-control" placeholder=""  id="applicantPosition" readonly="readonly"> 
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
	                                    <input type="text" value="${recruitment.education}" class=" form-control" placeholder="" id="education" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">年龄</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.age }" class=" form-control" placeholder="" id="age" readonly="readonly"> 
	                                </div>
	                               
	                                <label class="col-sm-1 control-label">性别</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.sexualStr}" class=" form-control" placeholder="" id="sexual" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">政治面貌</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.politicalFeature}" class=" form-control" placeholder="" id="politicalFeature" readonly="readonly"> 
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
	                                    <input type="text" value="${recruitment.jobName}" class=" form-control" placeholder="" id="jobName" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">部门</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.department }" class=" form-control" placeholder="" id="department" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">职位</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.position}" class=" form-control" placeholder="" id="position" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">职责</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.duties}" class=" form-control" placeholder="" id="duties" readonly="readonly"> 
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
	                                    <input type="text" value="${recruitment.workAge}" class=" form-control" placeholder="" id="workAge" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">薪资</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.salary }" class=" form-control" placeholder="" id="salary" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">保险</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.insurance}" class=" form-control" placeholder="" id="insurance" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">福利</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${recruitment.benefit}" class=" form-control" placeholder="" id="benefit" readonly="readonly"> 
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
<%-- 	                                    <input type="text" value="${recruitment.marriage}" class=" form-control" placeholder="" id="marriage">  --%>
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
<%-- 	                                <div class="col-sm-2">
	                                    <select class="col-md-2 form-control m-b" name="marriage" id="marriage">
	                                        <option value="1" <c:if test="${recruitment.marriage=='1'}">selected</c:if> >已婚</option>
											<option value="0" <c:if test="${recruitment.marriage=='0'}">selected</c:if> >未婚</option>
	                                    </select>
	                                </div> --%>
	                                <label class="col-sm-1 control-label">是否生育</label>
	                                <div class="col-sm-2">
	                                <c:if test="${recruitment.childbearing == 1}">
	                                    <input type="text" value="已育" class=" form-control" placeholder="" id="childbearing" readonly="readonly"> 
	                                </c:if>
	                                <c:if test="${recruitment.childbearing == 2}">
	                                    <input type="text" value="未育" class=" form-control" placeholder="" id="childbearing" readonly="readonly"> 
	                                </c:if>
	                                <c:if test="${recruitment.childbearing == 3}">
	                                    <input type="text" value="无要求" class=" form-control" placeholder="" id="childbearing" readonly="readonly"> 
	                                </c:if>
	                                </div>
	                            	<label class="col-sm-1 control-label">其他要求</label>
	                                <div class="col-sm-5">
	                                	<textarea class=" form-control" id="otherRequire" rows="" cols="" readonly="readonly">${recruitment.otherRequire}</textarea>
	                                </div>
	                                </div>
	                                </div>
	                            </div>
	                            </div>
                            </div>
							</div>
<%-- 				 <div class="row">
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
					               data-url="<%=basePath %>/business/dispatchList.do?serviceId=${etopservice.serviceId}"
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
    	$(document).ready(function () {
    		$("#serviceStatus").val(formatterServiceStatusType($("#serviceStatus").val(),'',''));
    		$('#readResume').click(function() {
     			var serviceId = $("#serviceId").val();
     			var isAffirm = $("#isAffirm").val();

    			var read = "<%=basePath%>/resume/index.do?serviceId="+serviceId;
   				totabsss(read, '简历');

    		});
    	});
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/park.js"></script>
</body>
</html>