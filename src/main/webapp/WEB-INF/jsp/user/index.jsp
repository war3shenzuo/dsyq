<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>帐号管理-系统设置</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            
            <div class="ibox-content">
                <div class="row row-lg">
					 <div class="col-sm-12">
                        <div class="row">
                             <div class="col-md-2">
	                           	<label>用户姓名</label>
                             	<input type="text" class=" form-control m-b" placeholder="输入用户姓名" id="name"> 
	                         </div>
	                         <div class="col-md-2">
	                           	<label>用户类型</label>
                             	<select class="form-control" id="userType" name="userType">
	                             <option value="">全部</option>
	                        <!--      <option value="1">企业</option>
                                 <option value="2">个人</option> -->
                                 <option value="3">园区用户</option>
                                 <option value="4">园区组用户</option>
                                 <!-- <option value="5">系统管理员</option> -->
	                        	</select>
	                         </div>
	                         <div class="col-md-2">
	                         	<label>选择园区</label>
	                           <select class="form-control" id="parkId" name="parkId">
                                 	<option value="">选择园区</option>
                                     <c:forEach items="${parks}" var="park">
                                         <option value="${park.id}">${park.parkName}</option>
                                     </c:forEach>
                                 </select>
	                         </div>
                             <div class="col-md-2">
	                           	<label>状态</label>
	                            <select class="form-control" id="astatus" name="account">
	                             <option value="">全部</option>
	                             <option value="1">已激活</option>
	                             <option value="0">未激活</option>
	                        	</select>
	                         </div>
	                         <div class="col-md-2">
	                        	<!--<label>确认搜索</label>-->
	                        	<button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/user/getUserList.do')"  type="button" style="margin-top: 23px;;">搜索</button>
	                         </div>
	                          <div class="hr-line-dashed" style="clear: both;"></div>
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                            	
                                <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                    <a class="btn btn-outline btn-default" 
                                        onclick="openAddPage('新增用户','625px','560px','<%=basePath %>/user/addUser.do','<%=basePath%>/user/getUserList.do')" >
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
                                        <span>新建</span>
                                    </a>
                                </div>
                                
	                           	<table id="table1"
	                           	    data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="<%=basePath%>/user/getUserList.do"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-pagination="true"
					               data-query-params = "queryParams"
					               data-page-list="[5, 10, 20, 50, 100, 200]"
					               data-striped="true"
					              >
						            <thead>
						            <tr>
						                <th data-field="id" data-align="center" data-visible="false">编号</th>
						                <th data-field="userName" data-align="center">用户账号</th>
						                <th data-field="name" data-align="center">用户姓名</th>
						                <th data-field="userType" data-align="center" data-formatter="formatterUserType" >用户类型</th>
						                <!-- <th data-field="companyId" data-align="center" >所属公司</th> -->
						                <th data-field="parkName" data-align="center" >所属园区</th>
						                <th data-field="mobile" data-align="center" >电话</th>
						                <!-- <th data-field="email" data-align="center" >邮箱</th> -->
						                <th data-field="createAt" data-align="center" >创建时间</th>
						                <!-- <th data-field="updateAt" data-align="center" >更新时间</th> -->
						                <th data-align="center" data-formatter='formatterFun' >操作</th>
						            </tr>
						            </thead>
				       		   </table>
                        </div>
                        <!-- End Example Events -->
                    </div>
                </div>
            </div>
        </div>
    
        <!-- End Panel Other -->
    </div>
	<jsp:include page="../shared/js.jsp"/>
    <script type="text/javascript">
    	/*查询条件*/
		function queryParams(params){
			params.activated = $("#astatus").val();
			params.name=$("#name").val();
			params.userType=$("#userType").val();
			params.parkId=$("#parkId").val();
			return params
		}
    </script>
    <script type="text/javascript">
    	var basePath='<%=basePath%>';
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/user.js"></script>
</body>
</html>