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
<title>注册</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/login.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/registerChoose.css"/>
</head>
<body>
	
	<jsp:include page="header.jsp" flush="true" />
    <div class="midwrap">
		<a href="<%=basePath %>/register/register.do?userType=2" class="a1">
			<img src="<%=basePath %>/img/person.png" width="120x" height="120px" >
			<p class="p1">个人用户注册</p>
			<p class="p2">适合企业员工使用</p>
			
		</a>
		<a href="<%=basePath %>/register/register.do?userType=1" class="a1">
			<img src="<%=basePath %>/img/company.png" width="120x" height="120px" >
			<p class="p1">企业用户注册</p>
			<p class="p2">适合企业管理使用</p>
			
		</a>
		
		</div>
 <jsp:include page="footer.jsp" flush="true" />
   
</body>
</html>
