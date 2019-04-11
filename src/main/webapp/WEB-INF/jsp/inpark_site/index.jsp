<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import="com.etop.management.properties.ImgProperties" %>
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
<meta charset="UTF-8">
<title>${park_group_name}</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
<style>
.flexslider{width: 100%; min-width: 1170px;height: auto !important; overflow: hidden;zoom:1; margin-bottom: 60px;}

.flex-viewport{max-height:2000px;-webkit-transition:all 1s ease;-moz-transition:all 1s ease;transition:all 1s ease;}
.flexslider .slides{zoom:1;}
.flexslider .slides img{ width: 100%;}
.flex-direction-nav a{width:60px;height:90px;line-height:99em;overflow:hidden;margin:-60px 0 0;display:block;background:url(<%=basePath %>/img/ad_ctr.png) no-repeat;position:absolute;top:50%;z-index:10;cursor:pointer;opacity:0;filter:alpha(opacity=0);-webkit-transition:all .3s ease;}
.flex-direction-nav .flex-next{background-position:0 -90px;right:0;}
.flex-direction-nav .flex-prev{left:0;}
.flexslider:hover .flex-next{opacity:0.8;filter:alpha(opacity=25);}
.flexslider:hover .flex-prev{opacity:0.8;filter:alpha(opacity=25);}
.flexslider:hover .flex-next:hover, .flexslider:hover .flex-prev:hover{opacity:1;filter:alpha(opacity=50);}
.flex-control-nav{width:100%;position:absolute;bottom:10px;text-align:center;}
.flex-control-nav li{margin:0 5px;display:inline-block;zoom:1;*display:inline;}
.flex-control-paging li a{background:url(<%=basePath %>/img/dot.png) no-repeat 0 -16px;display:block;height:16px;overflow:hidden;text-indent:-99em;width:16px;cursor:pointer;}
.flex-control-paging li a.flex-active{background-position:0 0;}

.listtitle a {
    font-size: 16px;
    height: 26px;
    text-indent: 5px;
    line-height: 25px;
    display: block;
    float: right; margin-right: 40px;
}
.ejx{ display: none !important;}
</style>
</head>
<body>

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
					<ul class="ofnavmainright" id="menu">	
				        <li>
						<a href="#" id="m1">公司介绍</a>
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
						<a href="<%=basePath %>/webIndex.do" >返回平台首页</a>
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
	<c:choose>
		<c:when test="${empty img}">
			
		</c:when>
		<c:otherwise >
		    <div id="demo01" class="flexslider bigImg">
		        <ul class="slides">
					<c:forEach  items="${img}"  var="img">
					<li><div class="img"><a href="${link}"> <img src="<%=ImgProperties.LOAD_PATH%>${img}" /></a></div></li>
					</c:forEach>
		        </ul>
		      </div>
	      </c:otherwise>
      </c:choose>
    <div class="mainBox" id="one">
    	<div class="introduction">
    		<div class="introd-title">
    			<h1><span>${park_group_name}</span></h1>
    			<p></p>
    		</div>
    		<ul>
    			<li>
    				<a href=""    id="toparkGroup">
    					<img src="<%=basePath %>/img/p1.png"/>
    					<h2>公司介绍</h2>
    					<p><%-- ${parkGroupDescribe} --%></p>
    				</a>
    			</li>
    			<li>
    				<a href=""  id="toparkList">
    					<img src="<%=basePath %>/img/p2.png"/>
    					<h2>园区介绍</h2>
    					<p><!-- 点击查看当前园区组下面所有园区的介绍 --></p>
    				</a>
    				
    			</li>
    			<li>
    				<a href="#"   id="toparkService">
    					<img src="<%=basePath %>/img/p3.png"/>
    					<h2>服务介绍</h2>
    					<p id="ffjs"></p>
    				</a>
    			</li>
    		</ul>
    		
    	</div>
    </div>
    
    
    <div class="mainBox" style="background:#dfdfdf;">
	    	<div class="introduction2">
	    		<div class="introd-title">
	    			<h1><span>在线招商</span></h1>
	    			<p>智能在线招商，不用上门也能看房</p>
	    		</div>
	    		<ul>
	    			<li>
	    				<a href="<%=basePath %>/webolmerchants/index.do?parkGroupId=${parkGroupId}" target="_blank">
	    					<img src="<%=basePath %>/img/p7.png"/>
	    					<h2>选择园区</h2>
	    				</a>
	    			</li>
	    			<li>
	    				<a href="<%=basePath %>/webolmerchants/index.do?parkGroupId="+parkGroupId" target="_blank">
	    					<img src="<%=basePath %>/img/p8.png"/>
	    					<h2>选择房间</h2>
	    				</a>
	    				
	    			</li>
	    			<li>
	    				<a href="<%=basePath %>/webolmerchants/index.do?parkGroupId="+parkGroupId" target="_blank">
	    					<img src="<%=basePath %>/img/p9.png"/>
	    					<h2>申请入驻</h2>
	    				</a>
	    			</li>
	    			<li>
	    				<a href="<%=basePath %>/webolmerchants/index.do?parkGroupId="+parkGroupId" target="_blank">
	    					<img src="<%=basePath %>/img/p10.png"/>
	    					<h2>预约看房</h2>
	    				</a>
	    			</li>
	    		</ul>
	    	</div>
    </div>
    <div class="mainBox" style="background:#ececec ;">
	    	<div class="introduction2">
	    		<div class="introd-title">
	    			<h1><span>在线服务</span></h1>
	    			<p>让服务更专业，让服务更简单</p>
	    		</div>
	    		<ul>
	    			<li>
	    				<%-- <a href="<%=basePath%>/purchase/purchaseApply.do?service_type=GGCG" target="_blank"> --%>
	    				<a href="<%=basePath%>/webparkservice/index.do" target="_blank">
	    					<img src="<%=basePath %>/img/p11.png"/>
	    					<h2>采购服务</h2>
	    				</a>
	    				<span class="ejx"  id="kdfw">
				             <div style="padding-bottom:60px;"></div>
				           	
			            </span>
	    			</li>
	    			<li>
	    				<a href="<%=basePath %>/webparkservice/index.do" target="_blank">
	    					<img src="<%=basePath %>/img/p12.png"/>
	    					<h2>办公服务</h2>
	    					
	    				</a>
	    				<span class="ejx" id="bgfw">
				            <div style=" padding-bottom:35px;"></div>
				            
			            </span>
	    			</li>
	    			<li>
	    				<%-- <a href="<%=basePath%>/quotation/BusinessService.do?service_type=SWFW" target="_blank"> --%>
	    				<a href="<%=basePath%>/webparkservice/index.do" target="_blank">
	    					<img src="<%=basePath %>/img/p13.png"/>
	    					<h2>商务服务</h2>
	    				</a>
	    				<span class="ejx" id="swfw">
				             <div style=" padding-bottom:35px;"></div>
				             
			            </span>
	    			</li>
	    			<li>
	    				<%-- <a href="<%=basePath%>/engagement/meetCal.do" target="_blank"> --%>
	    				<a href="<%=basePath%>/webparkservice/index.do" target="_blank">
	    					<img src="<%=basePath %>/img/p14.png"/>
	    					<h2>预约租赁</h2>
	    				</a>
	    				<span class="ejx" id="lcsp">
				             <div style=" padding-bottom:26px;"></div>
				             
			            </span>
	    			</li>
	    		</ul>
	    		<div id="two" style="position: relative;"></div>
	    	</div>
    </div>
    <div class="mainBox" style="background: #fff;">  
    	<div class="index-list">
    		<div class="index-list-left">
    			<div class="listtitle">
    				<span>行业资讯</span><a href="<%=basePath %>/webnews/newsList.do?type=2">更多</a>
    			</div>
    			<ul id="ul1">
    					
    			</ul>
    		</div>
    		<div class="index-list-left">
    			<div class="listtitle">
    				<span>园区新闻</span><a href="<%=basePath %>/webnews/newsList.do?type=1">更多</a>
    			</div>
    			<ul id="ul2">
    					
    				</ul>
    		</div>
    		<div id="three" style="position: relative;"></div>
    	</div>
    </div>
    
    <jsp:include page="../footer.jsp" flush="true" />
	<script>
		var svsList="";
		var parkGroupId="${parkGroupId}";
		var parkGroupName="${park_group_name}";
		$(document).ready(function(){
			    $('#demo01').flexslider({
			      animation: "slide",
			      direction:"horizontal",
			      easing:"swing"
			    });
			    $("#m1").attr("href","<%=basePath %>/webinpark/parkGroupInfo.do?parkGroupId="+parkGroupId);
			    $("#m2").attr("href","<%=basePath %>/webinpark/parkList.do?parkGroupId="+parkGroupId);
			    $("#m3").attr("href","<%=basePath %>/webinpark/parkGroupService.do?parkGroupId="+parkGroupId);
			    $("#m4").attr("href","<%=basePath %>/webolmerchants/index.do?parkGroupId="+parkGroupId);
			    $("#m5").attr("href","<%=basePath %>/webecollege/index.do?parkGroupId="+parkGroupId+"&target="+parkGroupName);
			   
			    $("#m7").attr("href","<%=basePath %>/Parkactivity/activity.do?parkGroupId="+parkGroupId+"&target="+parkGroupName);
			    
				$("#toparkGroup").attr("href","<%=basePath %>/webinpark/parkGroupInfo.do?parkGroupId="+parkGroupId);
				$("#toparkList").attr("href","<%=basePath %>/webinpark/parkList.do?parkGroupId="+parkGroupId);
				$("#toparkService").attr("href","<%=basePath %>/webinpark/parkGroupService.do?parkGroupId="+parkGroupId);
				 $.post("<%=basePath %>/webnews/getInfo.do",function(data){
						for(var i=0;i<9;i++)
							{
							if(data[i]==null) 
								break;
							if(data[i].title.length>24){
							var str=data[i].title.substr(0,24)+"...";
							$("#ul1").append("<li><a target='_blank'  href='<%=basePath %>/webnews/newsPage.do?id="+data[i].id+"'>"+str +"<span>["+data[i].createdAt+"]</span></a></li>");
							
							}
							else{
							$("#ul1").append("<li><a target='_blank'  href='<%=basePath %>/webnews/newsPage.do?id="+data[i].id+"'>"+data[i].title +"<span>["+data[i].createdAt+"]</span></a></li>");
							}				
							}
				 });
				 
				 $.post("<%=basePath %>/webnews/getNews2.do",{"parkGroupId":parkGroupId},function(data){
						for(var i=0;i<9;i++)
							{
							if(data[i]==null) 
								break;
							if(data[i].title.length>24)
								{	
								var str=data[i].title.substr(0,24)+"...";
								$("#ul2").append("<li><a target='_blank'  href='<%=basePath %>/webnews/newsPage.do?id="+data[i].id+"'>"+str+"<span>["+data[i].createdAt+"]</span></a></li>");
								}
							else{
				$("#ul2").append("<li><a target='_blank'  href='<%=basePath %>/webnews/newsPage.do?id="+data[i].id+"'>"+data[i].title +"<span>["+data[i].createdAt+"]</span></a></li>");
							}
							}
				 });
				 $.post("<%=basePath %>/etopService/getPGServiceTypeList.do?",{"parkGroupId":parkGroupId},function(data){
						for(var i=0;i<data.length;i++)
							{
								svsList=svsList+data[i].serviceName+'.';
								if(data[i].title=="快递服务"){
									$("#kdfw").append("<a href='<%=basePath%>/webparkservice/serviceapl.do?service_type="+data[i].serviceCode+"' ><p>"+data[i].serviceName+"</p></a>");
								}
								else if(data[i].title=="办公服务"){
									$("#bgfw").append("<a href='<%=basePath%>/webparkservice/serviceapl.do?service_type="+data[i].serviceCode+"' ><p>"+data[i].serviceName+"</p></a>");
								}
								else if(data[i].title=="商务服务"){
									$("#swfw").append("<a href='<%=basePath%>/webparkservice/serviceapl.do?service_type="+data[i].serviceCode+"' ><p>"+data[i].serviceName+"</p></a>");
								}
								else{
									$("#lcsp").append("<a href='<%=basePath%>/webparkservice/serviceapl.do?service_type="+data[i].serviceCode+"' ><p>"+data[i].serviceName+"</p></a>");
								}
							}
						 /* $("#ffjs").append(svsList); */
						 
				 });
				
			});
				
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

		function person() {
			swal({
				title : "个人用户不支持申请在线服务！",
				type : "error",
				confirmButtonText : "确定",
				closeOnConfirm : true
			});
		}
		function GetQueryString(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return decodeURI(r[2]);
			return null;
		}
		function scrollToanywhere(txt) {
			var theDiv = document.getElementById(txt);
			var numH = theDiv.offsetTop - 130;
			//alert(numH);
			window.scrollTo(0, numH);
		}
	</script>
</body>
</html>
