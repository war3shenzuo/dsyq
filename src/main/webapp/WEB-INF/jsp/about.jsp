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
		<title>平台介绍</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/jieshao.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>	
		
	</head>
	<body>
		<jsp:include page="header.jsp" flush="true" />
		${value }
		<jsp:include page="footer.jsp" flush="true" />
		

		<script src="<%=basePath %>/js/jieshao.js" type="text/javascript"></script>
		<script>
		function scrollToanywhere(txt){
			var theDiv=document.getElementById(txt);
			var numH=theDiv.offsetTop-130;
			//alert(numH);
			window.scrollTo(0,numH);
		}
		/* window.onscroll=function(){
			var numH=document.getElementById("one").offsetTop+document.body.scrollTop-130;
	        var node=document.getElementById('one');
	        if(top>20){//20就是滚动条滚动到的位置，大于20才显示
	            node.style;
	        }else{
	            node.removeClass("xxx");
	        }
	    } */
		</script>
	</body>
</html>
