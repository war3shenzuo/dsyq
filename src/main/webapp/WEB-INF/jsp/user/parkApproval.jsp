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
    <input type="hidden" id="parkGroupId" value="${parkGroupId}"/>
    <div class="wrapper wrapper-content animated fadeInRight">
    	<div class="row">
            <div class="col-md-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="form" method="get" class="form-horizontal" style="height:auto;overflow: hidden;">
                           <div class="col-md-12">
                            <div class="row">
	                            <div class="form-group">
	                                <label class="col-md-1">选择园区</label>
	                                <div class="col-md-3">
	                                <select class="form-control" name="parkId" id="parkId" onchange="gradeChange()" >
	                               	 <option value="0">请选择</option>
	                                	<c:forEach items="${parks}" var="park">
	                                        <option value="${park.id}">${park.parkName}</option>
	                                    </c:forEach>
	                                </select>
	                                </div>
	                            </div>
                            </div>
                           <div id="paForm" style="display: none;">
                           <div class="hr-line-dashed"></div>
                           <div class="row">
                            <div  class="form-group">
                                <label class="col-md-1">园区审批</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" placeholder="" name="pa" id="pa">
                                </div>
                                <div class="col-md-1">
                                    <a class="btn btn-primary"  onclick="openSelect('pa')">选择用户</a>
                                </div>
                                
                            </div>
                            </div>
                            </div>
                            <div id="pafForm" style="display: none;">
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            <div  class="form-group" >
                                <label class="col-md-1" >园区财务审批</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" placeholder="" name="paf" id="paf"> 
                                </div>
                                <div class="col-md-1">
                                    <a class="btn btn-primary"  onclick="openSelect('paf')">选择用户</a>
                                </div>
                            </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
	                        <div class="row">
	                         <div class="form-group">
	                           <label class="col-md-1">园区组审批</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" placeholder="" name="pga" id="pga"> 
                                </div>
                                <div class="col-md-1">
                                    <a class="btn btn-primary"  onclick="openSelect('pga')">选择用户</a>
                                </div>
	                         </div>
	                         </div>
                           <div class="hr-line-dashed"></div>  
                           <div class="row"> 
                           <div class="col-md-12">
	                           <div class="form-group">
	                                <div class="col-md-1" >
	                                   <a class="btn btn-primary"  onclick="bind()">绑定</a>
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
   		var param={
       			"parkId":$("#parkId").val(),
       			"parkGroupId":$("#parkGroupId").val()
       	}
   		
   		$.post("<%=basePath%>/user/getAllApproval.do",param,function(data){
   			
   				 $("#pa").val(data.parkApproval);
       			 $("#paf").val(data.parkFinanceApproval);
       			 $("#pga").val(data.parkGroupApproval);
   		});
   		
   		
   	    function gradeChange(){
   	    	var id=$("#parkId").val();
   	    	var pid=$("#parkGroupId").val();
   	    	if(id!="0" && id!=pid){
   	    		$("#paForm").show();
   	    		$("#pafForm").show();
   	    	}else{
   	    		$("#paForm").hide();
   	    		$("#pafForm").hide();
   	    	}
   	    		
   	    	var param={
   	       			"parkId":$("#parkId").val(),
   	       			"parkGroupId":$("#parkGroupId").val()
   	       	}
   	   		
   	   		$.post("<%=basePath%>/user/getAllApproval.do",param,function(data){
   	   			
   	   				 $("#pa").val(data.parkApproval);
   	       			 $("#paf").val(data.parkFinanceApproval);
   	       			 $("#pga").val(data.parkGroupApproval);
   	   		});
   	    }
    	
    	
    	function bind(){
    		var param={
        			"pa" :$("#pa").val(),
        			"paf":$("#paf").val(),
        			"pga":$("#pga").val(),
        			"parkId":$("#parkId").val(),
        			"parkGroupId":'${parkGroupId}'
        	}
    		$.post("<%=basePath%>/user/setAllApproval.do",param,function(data){
    			if(data.status==10001){
    				   swal("更新成功", "", "success");
    			}else{
    				   swal( data.msg, "","error");
    		    }
    		});
    	}
    	
    	/**绑定用户*/
    	function openSelect(type){
    		openSelectPage('绑定用户','800px','600px',basePath+'/user/openBindUserPage.do?parkId='+$("#parkId").val()+'&type='+type,null);
    	} 
    	
    	function fun(){}
    		
    </script>
</body>
</html>