<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
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
						您好，欢迎来到驿拓园区在线！
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
								<li><a href="<%=basePath%>/accountManage.do"><img src="<%=basePath %>/img/i.png">欢迎 ${userInfo.userName}</a></li>
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
							全国热线：${headerValue}
						</div>
					</div>	
				</div>
			</div>
			<div class="ofnav">
				<div class="ofnavmain">
					<div class="ofnavmainleft">
						<a href="#"><img src="<%=basePath %>/img/logo.png"></a>
					</div>
					<ul class="ofnavmainright" id="menu">
						
				        <li>
						<a href="<%=basePath %>/webIndex.do" target="_self">首页</a>
				        </li>
				        
				        <li>
						<a href="<%=basePath %>/about/about.do" target="_self">平台介绍</a>
				        </li>
				        
				        <li>
						<a href="<%=basePath %>/webnews/newsList.do" target="_self">资讯</a>
				        </li>
				        
				        <li>
						<a href="<%=basePath %>/webecollege/index.do" target="_self">电商学院</a>
				        </li>
				        				        
				        <li>
						<a href="<%=basePath %>/webolmerchants/index.do" target="_self">在线招商</a>
				        </li>
				        
				        <li>
						<a href="<%=basePath %>/websettled/index.do" target="_self">入驻平台</a>
				        </li>
				        
				        <li>
						<a href="<%=basePath %>/webparkservice/index.do" target="_self">园区服务</a>
				        </li>
				        
				         <li>
						<a href="<%=basePath %>/Parkactivity/activity.do" target="_self">园区活动</a>
				        </li>
				        
				        <li>
						<a href="<%=basePath %>/webinpark/inparkList.do" target="_self" id="inparkUrl">进入园区</a>
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
<input type="hidden" id="userpkgid" value="${userInfo.parkGroupId}" />
	    