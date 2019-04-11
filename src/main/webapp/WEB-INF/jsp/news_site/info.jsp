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
		<meta charset="UTF-8">
		<title>资讯详情页</title>
		<link rel="stylesheet" href="<%=basePath %>/css/news.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
	</head>
	<body>
		
		<jsp:include page="../header.jsp" flush="true" />
		<div class="container" style=" height: auto; margin-bottom: 100px;">
			<div class="imgtitle"><img src="<%=basePath %>/img/201604111422000576.jpg" style="width:1144px"/></div>
					<div class="lang">
						<img src="<%=basePath %>/img/lang.png" class="langimg">
						<a href="<%=basePath %>/webIndex.do" class="aa">首页</a><span>&gt;</span><a href="<%=basePath %>/webnews/newsList.do">资讯</a><span>&gt;</span><a href="<%=basePath %>/webnews/newsList.do?"  class="aa" id="aname"></a>
					</div>
					<div class="bigtitle">
					<p class="p1">${title}</p>
					
					<c:if test="${news.newsType == '园区新闻'}">
					<p class="p2" >发布者：${parkName} 发布时间：${date}</p>
					</c:if>
					<c:if test="${news.newsType == '资讯'}">
<%-- 					<c:if test="${news.totalType == 2}"> --%>
					<p class="p2" >发布者：驿拓资讯部  发布时间：${date}</p>
					</c:if>
					</div>
					<div  class="thing">
						<div class="inthing" >
				
							<p>
							${content}
							</p>
							
						</div>
					</div>
			</div>
			<jsp:include page="../footer.jsp" flush="true" />
			
		<script>
			var temp=${total_type};
			if(temp==1)
				{
					$("#aname").text("园区新闻");
				}
			else{
				$("#aname").text("行业资讯");
			}
		</script>
	</body>
</html>

