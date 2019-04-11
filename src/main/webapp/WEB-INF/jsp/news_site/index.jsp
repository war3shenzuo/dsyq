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
		<title>资讯</title>
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/newli.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/etopclub.css"/>
	</head>
	<body>
	<jsp:include page="../header.jsp" flush="true" />
		
		<div class="container">
			<div class="imgtitle"><img src="<%=basePath %>/img/201604111422000576.jpg" style="width:1144px"/></div>
				<div class="thing">
					<div class="thingtitle">
							<a href="javascript:void(0)" class="act" id="a1" >园区新闻</a> <a href="javascript:void(0)" id="a2">行业资讯</a>

							<a href="#" style="float:right;margin-right:-45px;" id="az">资讯</a>

							
							<a href="<%=basePath %>/webIndex.do" style="float:right;margin-right:-37px;" id="ah">首页></a>
					</div>
					<div class="thingcontainer">
							<ul id="lis">
								<%-- <c:forEach var = "i" items="${requestScope.pageModel.setItems}" varStatus="items">
									<li></li>
								</c:forEach> --%>
							</ul>
					</div>
				
					<div class="foot">
							<ul id="page" class="pagination pagination-sm">
 								<!-- <li class="disabled"><a href="#">&lt;上一页</a></li>
 								<li class="active"><a href="#">1</a></li>
  								 <li ><a  href="#">2</a></li>
 								<li><a href="#">3</a></li>
  								<li><a href="#">4</a></li>
  								<li><a href="#">5</a></li>
  								<li><a href="#">下一页&gt;</a></li> -->
							</ul>
							
					</div>
				</div>
		</div>
		<jsp:include page="../footer.jsp" flush="true" />
	

<script type="text/javascript">
 		
		var nowPage=1;
		var dataLength=0;
		var rows=12;
		var garden=true;
		var doc1=document.getElementById("a1");
 		var doc2=document.getElementById("a2");
 		var nowType=GetQueryString("type");
 		
 		var allPage="";
		var nowMaxPage="5";         //此刻分页码能看到的最大页码
		var nowMinPage="1";         //此刻分页码能看到的最小页码
 		
			doc1.addEventListener("click",function(){
	 			this.className="act";
	 			doc2.className=null;
	 			garden=true;
	 			$("#lis li").remove();
				$("#page li").remove();
				nowsPage=1;
				$.post("<%=basePath %>/webnews/getNews.do",function(data){
					dataLength=data.length;
					if(dataLength>0){
						allPage = Math.ceil(dataLength/rows);
						paging('page',allPage);
						for(j=0;j<rows;j++)
							{
							 if(data[j]==null)
								 break;
							$("#lis").append("<li><a class='one' href='<%=basePath %>/webnews/newsPage.do?id="+data[j].id+"'><img src='<%=basePath %>/img/mark.png'/><span class='spannews'>["+data[j].newsType+"]</span><sapn>"+data[j].title +"</span><span class='timerf'>["+data[j].createdAt+"]</span></a></li>");
							}
					}
				});
 			});
		 	doc2.addEventListener("click",function(){
	 			this.className="act";
	 			doc1.className=null;
	 			garden=false;
	 			$("#lis li").remove();
				$("#page li").remove();
				nowsPage=1;
				$.post("<%=basePath %>/webnews/getInfo.do",function(data){
					dataLength=data.length;
					if(dataLength>0){
						allPage = Math.ceil(dataLength/rows);
						paging('page',allPage);
						for(j=0;j<rows;j++)
							{
							 if(data[j]==null)
								 break;
							$("#lis").append("<li><a class='one' href='<%=basePath %>/webnews/newsPage.do?id="+data[j].id+"'><img src='<%=basePath %>/img/mark.png'/><span class='spannews'>["+data[j].newsType+"]</span><sapn>"+data[j].title +"</span><span class='timerf'>["+data[j].createdAt+"]</span></a></li>");
							}
					}
	 			});
			 });
		
		
		
			$(document).ready(function(){
				if(nowType==2){
					var url="<%=basePath %>/webnews/getInfo.do";
					doc2.className="act";
		 			doc1.className=null;
				}
				else{
					var url="<%=basePath %>/webnews/getNews.do";
				}
				$.post(url,function(data){
					dataLength=data.length;
					if(dataLength>0){
						allPage = Math.ceil(dataLength/rows);
						paging('page',allPage);
						for(j=0;j<rows;j++)
							{ 
							 if(data[j]==null)
								 break;
							$("#lis").append("<li><a class='one' href='<%=basePath %>/webnews/newsPage.do?id="+data[j].id+"'><img src='<%=basePath %>/img/mark.png'/><span class='spannews'>["+data[j].newsType+"]</span><sapn>"+data[j].title +"</span><span class='timerf'>["+data[j].createdAt+"]</span></a></li>");
							}
					}
				});
			});
			function goPage(p){
				$("#page a").each(function(){
					if ($(this).text()==p) {
						$(this).addClass("hover");
					} else{
						$(this).removeClass("hover");
					}
				});
				if(garden){
				$.post("<%=basePath %>/webnews/getNews.do",function(data){
					nowPage=p;
					$("#lis li").remove();
					for(j=(p-1)*rows;j<rows*(p-1)+rows;j++)
					{
					
						if(j>=dataLength){return;}
						$("#lis").append("<li><a class='one' href='<%=basePath %>/webnews/newsPage.do?id="+data[j].id+"'><img src='<%=basePath %>/img/mark.png'/><span class='spannews'>["+data[j].newsType+"]</span><sapn>"+data[j].title +"</span><span class='timerf'>["+data[j].createdAt+"]</span></a></li>");
					}
				});
				}
				else{
					$.post("<%=basePath %>/webnews/getInfo.do",function(data){
						nowPage=p;
						$("#lis li").remove();
						for(j=(p-1)*rows;j<rows*(p-1)+rows;j++)
						{
						if(j>=dataLength){return;}
						$("#lis").append("<li><a class='one' href='<%=basePath %>/webnews/newsPage.do?id="+data[j].id+"'><img src='<%=basePath %>/img/mark.png'/><span class='spannews'>["+data[j].newsType+"]</span><sapn>"+data[j].title +"</span><span class='timerf'>["+data[j].createdAt+"]</span></a></li>");
						}
					});
				}
			}
			
			function GetQueryString(name) {
				var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
				var r = window.location.search.substr(1).match(reg);
				if (r != null)
					return decodeURI(r[2]);
				return null;
			}
 </script>
</body>
</html>
