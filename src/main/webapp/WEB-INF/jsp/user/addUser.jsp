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
                        <form id="form" method="get" class="form-horizontal">
                       	   <div class="row">
                           <div class="col-xs-12">
                           <div class="row">
                           	   <label class="col-xs-2 control-label">用户帐号<font color="red">*</font></label>
                               <div class="col-xs-10">
                                   <input type="text" class="form-control" placeholder="" name="userName" id="userName"> 
                               </div>
                           </div>
                           <div class="hr-line-dashed"></div>
                           <div class="row">
                           		
                                <label class="col-xs-2 control-label">用户姓名<font color="red">*</font></label>
                                <div class="col-xs-4">
                                    <input type="text" class="form-control" placeholder="" name="name" id="name"> 
                                </div>
                                <label class="col-xs-2 control-label">用户密码<font color="red">*</font></label>
                                <div class="col-xs-4">
                                    <input type="password" class="form-control" placeholder="" name="passWord"id="passWord"> 
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                                <label class="col-xs-2 control-label">联系电话<font color="red">*</font></label>
                                <div class="col-xs-4">
                                    <input type="text" class="form-control" placeholder="" name="mobile" id="mobile"> 
                                </div>
                           
                                <label class="col-xs-2 control-label">联系邮箱<font color="red">*</font></label>
                                <div class="col-xs-4">
                                    <input type="text" class="form-control" placeholder="" name="email" id="email"> 
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
	                        <div class="row" >
	                         		<label class="col-xs-2 control-label">所属园区</label>
                             		<div class="col-xs-4">
	                               <select class="form-control" name="account" id="parkId" onchange="gradeChange()" >
	                               <option value="0">请选择园区</option>
	                               <c:forEach items="${parks}" var="park">
	                                	<option value="${park.id }">${park.parkName }</option>
	                               </c:forEach>
	                          	   </select>
	                        	  </div>
	                           <label class="col-xs-2 control-label">所属岗位</label>
                                <div class="col-xs-4">
                                    <select class="form-control"  id="roleId" >
                                    </select>
                                </div>
	                         </div>
	                          <div class="hr-line-dashed"></div>
	                        <div class="row">
	                           <label class="col-xs-2 control-label">激活状态</label>
                                <div class="col-xs-4">
                                    <select class="form-control" name="account" id="activated">
                                        <option value="1">激活</option>
                                        <option value="0">不激活</option>
                                    </select>
                                </div>
	                         </div>
                           <div class="hr-line-dashed"></div>  
                           <div class="row"> 
                           <div class="col-xs-12">
	                                <div class="col-xs-12" style="text-align: center;">
	                                   <!--  <a class="btn btn-primary"  onclick="addsubmit()">保存内容</a> -->
	                                    <input class="btn btn-primary" type="submit" value="提交">
	                                </div>
                           </div>
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
    <script type="text/javascript">
    
    function gradeChange(){
    	
    	$.post(basePath+"/role/getRoleListByParkId.do",{"parkId":$("#parkId").val()},function(data){
    		
    		var test='';
    		
    		for(var i=0;i<data.length;i++){
    			
    			test = test+"<option value='"+data[i].id+"'>"+data[i].roleName+"</option>";
    			
    		}
    		
    		$("#roleId").html(test)
    		
 	   });
    }
    
 	var e = "<i class='fa fa-times-circle'></i> ";
	 $("#form").validate({     
		rules: {//这里加校验规则
			userName:"required",
			name:"required",
			passWord:"required",
			email:{
				required:true,
				email:true
			},
			mobile:{
				required:true,
				digits:true
			}
		},
		messages: {//这里给对应的提示
			userName:e+"必填项未填",
			name:e+"必填项未填",
			passWord:e+"必填项未填",
			email:{
				required:e+"必填项未填",
				email:e+"不是邮箱格式"
			},
			mobile:{
				required:e+"必填项未填",
				digits:e+"必需是数字"
			}
		},
	    submitHandler: function(form){      
		 addsubmit();  //去提交   
	    }  
	})
    </script>
    
</body>
</html>