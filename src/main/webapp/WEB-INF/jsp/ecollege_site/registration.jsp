<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<title></title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/xueyuandetails.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
	<style type="text/css">
	
	</style>
	</head>
	<body>
	
		
	<jsp:include page="../header.jsp" flush="true" />
	
		<div class="wrap">
			<div class="title1">
				<span class="span1 underborder">${course_name}</span>
			</div>
			
			
			<p class="p1">课程介绍</p>
			<div class="details">
			<p ><span>主讲人</span><span class="span3">${teacher}</span></p>
			<p class="p2">课程概述</p>
			
		
			<p class="p3">${course_content}</p>
						
			<div class="btn">
				<a href="#" class="btna1">报名参加</a>
			</div>
			</div>
		</div>
		
	    	
	 
		<jsp:include page="../footer.jsp" flush="true" />
		
	</body>
</html>
