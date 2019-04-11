<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.etop.management.properties.ImgProperties" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>公司介绍</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/gardendetails.css"/>
    	<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/xueyuan.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
		<style>
		.timerf{ margin: 0 4px;}
		</style>
	</head>
	<body>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style=" position:static; height:131px; width:100%"></div>
		<!--
	    	作者：277250649@qq.com
	    	时间：2016-08-19
	    	描述：页面头部开始
	    -->
		<div class="header" style=" top:0px;">
			<div class="oflogin">
				<div class="ofnavmain">
					<div class="ofloginleft">
						您好，欢迎来到e淘电商园！
					</div>
					<div class="ofloginright">
						<div class="login">
							<ul>
							<c:choose>
							<c:when test="${empty userInfo}">
								<li><a href="<%=basePath%>/login.do"><img src="<%=basePath %>/img/i.png">登录</a></li>
								<li><a href="<%=basePath %>/register/registerChoose.do"><img src="<%=basePath %>/img/i2.png">注册</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="#"><img src="<%=basePath %>/img/i.png">欢迎 ${userInfo.userName}</a></li>
								<li><a href="<%=basePath%>/logout.do"><img src="<%=basePath %>/img/i2.png">退出</a></li>
								
							</c:otherwise>
							</c:choose>
							</ul>
							<div class="getin">
							
								<c:choose>
									<c:when test="${userInfo.userType=='3' or userInfo.userType=='4'}">
										<a href="<%=basePath%>/managerIndex.do" style=" margin-left: 20px;">管理中心</a>
										<%-- <a href="<%=basePath%>/accountManage.do">账户管理</a> --%>
									</c:when>
									<c:when test="${userInfo.userType=='5'}">
										<a href="<%=basePath%>/managerIndex.do" style=" margin-left: 20px;">运维中心</a>
										<%-- <a href="<%=basePath%>/accountManage.do">账户管理</a> --%>
									</c:when>
									<c:when test="${userInfo.userType=='1' or userInfo.userType=='2'}">
										<a href="<%=basePath%>/managerIndex.do" style=" margin-left: 20px;">用户中心</a>
										<%-- <a href="<%=basePath%>/accountManage.do">账户管理</a> --%>
									</c:when>
									<c:otherwise >
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="a-phone">
							全国热线：400-12345678111
						</div>
					</div>	
				</div>
			</div>
			<div class="ofnav">
				<div class="ofnavmain">
					<div class="ofnavmainleft">
						<c:choose>
							<c:when test="${empty logo}">
								
							</c:when>
							<c:otherwise >
								<a href="#"><img src="<%=ImgProperties.LOAD_PATH%>${logo}" style=" height:50px;"/></a>
							</c:otherwise>
						</c:choose>
					</div>
					<ul class="ofnavmainright" id="">	
						<li>
						<a href="#" id="m1" class="hover">公司介绍</a>
				        </li>
				        <li>
						<a href="#" id="m2">园区介绍</a>
				        </li>
				        <li>
						<a href="#" id="m3">服务介绍</a>
				        </li>
				        
				        <li>
						<a href="<%=basePath %>/webparkservice/index.do" target="_blank">在线服务</a>
				        </li>
				        <li>
						<a href="#"  id="m4" target="_blank">在线招商</a>
				        </li>
				        <li>
						<a href="#"   id="m5" target="_blank">园区培训</a>
				        </li>
				        <li>
						<a href="#"  id="m7" target="_blank">园区活动</a>
				        </li>
				        <li>
						<a href="" target="_self" id="goBack">返回首页</a>
				        </li>
					</ul>
				</div>
			</div>
		</div>
		<!--
	    	作者：277250649@qq.com
	    	时间：2016-08-19
	    	描述：页面头部结束
	   -->

	    
		<div class="wrap">
			<div id="demo01" class="flexslider bigimg">
				<ul class="slides">
					<li><div class="img"><img src="<%=basePath %>/img/banner4.jpg"  alt="" /></div></li>
					<li><div class="img"><img src="<%=basePath %>/img/banner.jpg"  alt="" /></div></li>
				</ul>
			</div>
			
			<div class="title">
			
			<span class="span1">${park_group_name}</span> <span class="span2">${city}</span>	
			</div>
			
<%-- 			<div class="cont">
				<img src="<%=ImgProperties.LOAD_PATH%>${img}" class="img1">
					<div class="manyp">
						<p>${parkGroupDescribe}</p>
					</div>
			</div> --%>
			
	        <div class="cont" id="cont" style="margin-top:-50px;">
	        
	        
	        <c:forEach  items="${ParkGroupPresentation}"  var="infoList"  >
	        
	        	<div class='course'>
		        	<%-- <div class='imgl'>
		        		<a><img src='<%=ImgProperties.LOAD_PATH%>${infoList.parkGroupPreimg}' class='img2'/></a>
		        	</div> --%>
		        	<div class='word' style="width:100%;'">
		        	<div class='up'>
		        		<img src='<%=basePath %>/img/lang2.png'/>
		        		<span>${infoList.title}</span>
		        	</div>
		        		<p class='p4'>${infoList.content}</p>
		        	</div>
	        	</div>
	        
	        
	        
	        </c:forEach>
	        
	        
      
             </div>  
			<div class="res">
				
				
			</div>
			
			
		</div>
		<jsp:include page="../footer.jsp" flush="true" />
		<script type="text/javascript">
		var parkGroupId=GetQueryString('parkGroupId');
		
			$(function(){
				$('#demo01').flexslider({
					animation: "slide",
					direction:"horizontal",
					easing:"swing"
				});
				 $("#m1").attr("href","<%=basePath %>/webinpark/parkGroupInfo.do?parkGroupId="+parkGroupId);
				    $("#m2").attr("href","<%=basePath %>/webinpark/parkList.do?parkGroupId="+parkGroupId);
				    $("#m3").attr("href","<%=basePath %>/webinpark/parkGroupService.do?parkGroupId="+parkGroupId);
				    $("#m4").attr("href","<%=basePath %>/webolmerchants/index.do?parkGroupId="+parkGroupId);
				    $("#m5").attr("href","<%=basePath %>/webecollege/index.do?parkGroupId="+parkGroupId);
				   
				    $("#m7").attr("href","<%=basePath %>/Parkactivity/activity.do?parkGroupId="+parkGroupId);
			});
		
			$(document).ready(function(){
				$("#goBack").attr("href","<%=basePath %>/webinpark/inparkInfo.do?parkGroupId="+parkGroupId);
			});
			function GetQueryString(name){
			     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			     var r = window.location.search.substr(1).match(reg);
			     if(r!=null)return  decodeURI(r[2]); return null;
			} 
		      


 </script>
	</body>
</html>