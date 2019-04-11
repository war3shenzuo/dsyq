<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>园区服务-服务申请</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>		
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/gardenApplication.css"/>
</head>
<body>
	
	<jsp:include page="../header.jsp" flush="true" />
	<%-- 
    <div class="mainBox" style="">
    	<div class="svsapplbox" style="float:left;">
    		<p id="sel"><p>
    		<dl style="margin-left:-70px;">
    			<dd>
    				<textarea id="article" rows="10"></textarea>
    			</dd>
    		</dl>
    		<dl>
    			<dd><a href="javascript:void(0)" id="submit" style="outline:none;">提     交</a></dd>
    		</dl>
    	</div>
    	<div style="float:right;width:470px;height:590px;">
    		
    	</div>
    </div>
    --%>
    		<div id="wrap">
			<div class="le">
				<h1 style="font-size:35px;" id="title">${parkservice.service_name}</h1>
				<textarea class="text" id="text" placeholder="请填写服务需求，如：什么货物，寄到哪里，约多少重量  不少于5个字"></textarea>
				<c:choose>
								<c:when test="${empty userInfo}">
						    	<a href="javascript:void(0)"  style="outline:none;" onclick="toLogin()">请先登录</a>
								</c:when>
								<c:otherwise>
								<c:choose>
								<c:when test="${userInfo.userType==1}">
								 <a href="javascript:void(0)" id="submit" style="outline:none;">提交</a>
						    	</c:when>
								<c:otherwise>
								<a href="javascript:void(0)" onclick="person()" style="outline:none;"><p>提交</p></a>
						    	
								</c:otherwise>
								</c:choose>
								</c:otherwise>
							</c:choose>
				
			</div>
			<div class="rt">
				<h2 style="font-size:20px;">${parkservice.service_name}服务条款</h2>
				<div  class="scroll">
				<p>
					${parkservice.item}
				</p>
				</div>
			</div>
		</div>
		
    
<jsp:include page="../footer.jsp" flush="true" />
	<script>
	
	var hrefs=location.href; 
	var s=hrefs.indexOf("?"); 
	hrefs=hrefs.substr(s+1);
	var decodeD=decodeURIComponent(hrefs);
	var str=decodeD.substr(13);
	</script>
    <script>
    function show(){
   
    	var article=$("#text").val();
    	 if($.trim(article)=="")
		{
    		swal("需求描述不能为空");
		    return;
		}
    	else if(article.length<5)
    		{
    		swal("需求描述不能少于5个字");
		    return;
    		}
    	$.ajax(  
    		    {  
    		        type:'post',  
    		        url:'<%=basePath %>/webparkservice/addService.do',  
    		        data : {
    		        	"service_type":str,
    		        	"description":article     
    		        },
    		        
    		        success  : function(data){
    		            if(data.status==10001){
    		            	swal({
    							title : "提交成功",
    							type : "success",
    							confirmButtonText : "确定",
    							closeOnConfirm : true
    						}, function(){
    							location.href="<%=basePath%>/webparkservice/index.do";
    						});
    		            }else{
    		                swal( "提交失败！", "","error");
    		            }
    		        }
    		    }  
    		);  
    }
    $("#submit").bind("click",show);
    function toLogin(){
		swal({
			title : "请先登录账号！",
			type : "error",
			confirmButtonText : "确定",
			closeOnConfirm : true
		}, function() {
			window.open("<%=basePath%>/login.do"); 
			
		});
	}
	
	function person(){
		swal({
			title : "此账户暂时不支持申请在线服务！",
			type : "error",
			confirmButtonText : "确定",
			closeOnConfirm : true
		});
	}
    </script>
</body>
</html>
