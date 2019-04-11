<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>园区列表-园区管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
	<link rel="stylesheet" href="<%=basePath%>/css/select/select2.min.css">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
 		<input type="hidden" class="form-control" disabled="disabled" value="${parentId}" id="parentId"> 
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                        <form id="form" method="get" class="form-horizontal">
                        <div class="row">
                        	<input type="hidden" id="id">	
                        	<div class="form-group">
                                <label class="col-sm-2 control-label">角色编号<font color="red">*</font></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="" name="roleCode" id="roleCode"> 
                                </div>
                                <label class="col-sm-2 control-label">角色名称<font color="red">*</font> </label>
                                <div class="col-sm-3"> 
                                    <input type="text" class="form-control" placeholder="" name="roleName" id="roleName"> 
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">角色描述</label>
								<div class="col-sm-8">
                                    <textarea rows="" cols=""class=" form-control" placeholder="" id="roleEscribe"></textarea>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                           		 <label class="col-sm-2 control-label">所属园区</label>
                                 <div class="col-sm-3">
	                               <c:forEach items="${parks}" var="park">
	                               		<c:if test="${park.id==parkId}">
	                               			<input type="hidden"  class="form-control" value="${park.id}" placeholder="" name="parkId" id="parkId">
	                               			<input type="text" class="form-control" value="${park.parkName}" disabled="disabled" placeholder="" name="parkName" id="parkName"> 
	                               		</c:if>
	                               </c:forEach>
                                </div>
                                <label class="col-sm-2 control-label">激活状态</label>
                                <div class="col-sm-3">
                                    <select class="form-control" name="account" id="activated">
                                        <option value="1">激活</option>
                                        <option value="0">不激活</option>
                                    </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">审批权限</label>
                                <div class="col-sm-8">
                                      <select class="js-example-tags form-control"  multiple="multiple" id="roleApproval">
															 <option value="tz">园长审批权限</option>
															 <option value="cw"> 财务审批权限</option>
															 <option value="zcw"> 园区组财务审批权限</option>
															 <option value="kh"> 客户提醒</option>
															 <option value="ht"> 合同提醒</option>
															 <option value="sp"> 审批提醒</option>
															 <option value="qs"> 申请提醒</option>
															 <option value="yqtx"> 园区提醒</option>
															 <option value="rw"> 任务提醒</option>
															 <option value="jmsq"> 减免申请</option>
															 <option value="yqsq"> 延期申请</option>
															 <option value="zf"> 支付</option>
										</select>		
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <input class="btn btn-primary" type="submit" value="提交">
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
    	var basePath='<%=basePath%>';
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/role.js"></script>
    <script src="<%=basePath%>/js/select/select2.full.min.js"></script>
    <script type="text/javascript">
 	var e = "<i class='fa fa-times-circle'></i> ";
	 $("#form").validate({     
		rules: {//这里加校验规则
			roleName:"required",
			roleCode:{
				required:e+"必填项未填",
				lowercase:["a","z"]
			}
			
		},
		messages: {//这里给对应的提示
			roleName:e+"必填项未填",
			roleCode:{
				required:e+"必填项未填",
				digits:e+"必需是数字和小写字母"
			}
			
		},
	    submitHandler: function(form){      
		 addsubmit();  //去提交   
	    }  
	})
	
	$("#roleApproval").select2({
    		tags: true,
    		separator: ",",
    	});
    </script>
</body>
</html>