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
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
 
    	<div class="row">
            <div class="col-xs-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                    	
                        <form method="get" class="form-horizontal">
                        	<div class="row">
                        	<div class="form-group">
                           	   <label class="col-xs-2 control-label">用户帐号</label>
                               <div class="col-xs-10">
                                   <input type="text" class="form-control" placeholder="" name="userName" id="userName" value ="${user.userName}" disabled="disabled"> 
                               </div>
                              </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="row">
                          	<div class="form-group">
                                <label class="col-xs-2 control-label">用户姓名</label>
                                <div class="col-xs-4">
                                    <input type="text" value ="${user.name}" class="form-control" placeholder="" id="name"> 
                                </div>
                                <label class="col-xs-2 control-label">用户密码</label>
                                <div class="col-xs-4">
                                    <input type="password" value ="${user.passWord}"class="form-control" placeholder="" id="passWord"> 
                                </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            <div class="form-group">
                                <label class="col-xs-2 control-label">联系电话</label>
                                <div class="col-xs-4">
                                    <input type="text" value ="${user.mobile}" class="form-control" placeholder="" id="mobile"> 
                                </div>
                           
                                <label class="col-xs-2 control-label">联系邮箱</label>
                                <div class="col-xs-4">
                                    <input type="text" value ="${user.email}" class="form-control" placeholder="" id="email"> 
                                </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
	                        <div class="row" style="display: none;">
	                           <div class="form-group">
                            
	                         	   <label class="col-xs-2 control-label">所属园区</label>
                             	   <div class="col-xs-4">
                             	   
	                               <input type="text" value ="${user.parkName}" class="form-control" placeholder="" id="" disabled="disabled"> 
	                          	   </div>
	                          	   <label class="col-xs-2 control-label">所属岗位</label>
	                         	   <div class="col-xs-4">
                             	   <input type="text" value ="${user.userType}" class="form-control" placeholder="" id="userType" disabled="disabled"> 
	                              
	                               </div>
	                          
	                         </div>
	                         </div>
                           <div class="hr-line-dashed"></div>  
                           <div class="row"> 
	                        <div class="form-group">
                                <label class="col-xs-2 control-label">激活状态</label>
                                <div class="col-xs-4">
                                    <select class="form-control" name="account" id="activated">
                                        <option value="1" <c:if test="${user.activated=='1'}">selected</c:if> >激活</option>
										<option value="0" <c:if test="${user.activated=='0'}">selected</c:if> >不激活</option>
                                    </select>
                                </div>
                            </div>
                           </div> 
                           <div class="hr-line-dashed"></div>
                            <div class="row">                     
                           <div class="form-group">
                                <div class="col-xs-12" style="text-align: center;">
                                    <a class="btn btn-primary"  onclick="updatesubmit('${user.id}')">编辑内容</a>
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
    <script type="text/javascript" src="<%=basePath %>/myjs/user.js"></script>
</body>
</html>