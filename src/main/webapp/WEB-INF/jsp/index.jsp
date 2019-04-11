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
<meta charset="UTF-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
</head>
<style>
/* flexslider */

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
</style>

<body>

	<jsp:include page="header.jsp" flush="true" />
	${value}
    <jsp:include page="footer.jsp" flush="true" />
	<script>
	  $(function(){

		    $('#demo01').flexslider({
		      animation: "slide",
		      direction:"horizontal",
		      easing:"swing"
		    });
		    
		  });
				$(document).ready(function(){
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
				 
				 $.post("<%=basePath %>/webnews/getNews.do",function(data){
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
				
				function person(){
					swal({
						title : "个人用户不支持申请在线服务！",
						type : "error",
						confirmButtonText : "确定",
						closeOnConfirm : true
					});
				}
			</script>
</body>
</html>
