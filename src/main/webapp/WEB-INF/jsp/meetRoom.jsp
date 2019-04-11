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
  			 <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
  			 <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
			<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/chooseroomhead.css"/>
			
			
	</head>
	<body>
		<jsp:include page="header.jsp" flush="true" />
		
		
		<div class="wrap" style="width:1200px;">
			<div class="head">
				<p>会议室预约</p>
			</div>
			
			<div class="choose">
				<select id="building">
					<option>楼1</option>
					<option>楼2</option>
					<option>楼3</option>
					<option>楼4</option>
					<option>楼5</option>
					<option>楼6</option>
				</select>
				<select id="bfloor">
					<option>1F</option>
					<option>2F</option>
					<option>3F</option>
					<option>1F</option>
					<option>2F</option>
					<option>3F</option>
				</select>
				<select id="bpart">
					<option>A区</option>
					<option>B区</option>
					<option>C区</option>
					<option>A区</option>
					<option>B区</option>
					<option>C区</option>
				</select>
			</div>
	
			
			<div class="cont">
				<div class="floors">
					<a href="#" class="floor">
						<div class="lf"><span>101</span></div>
						<div class="rf">
							<p class="p1">房间号：101</p>
							<p class="p1">面积：100平方米</p>
							<p class="p1">层高：1层</p>	
							
						</div>
					
					</a>
					<a href="#" class="floor midfloor">
						<div class="lf"><span>101</span></div>
						<div class="rf">
							<p class="p1">房间号：101</p>
							<p class="p1">面积：100平方米</p>
							<p class="p1">层高：1层</p>	
							
						</div>
						
					</a>
					<a href="#" class="floor">
						<div class="lf"><span>101</span></div>
						<div class="rf">
							<p class="p1">房间号：101</p>
							<p class="p1">面积：100平方米</p>
							<p class="p1">层高：1层</p>	
							
						</div>
					
					</a>
				</div>
				
						<div class="floors">
					<a href="#" class="floor">
						<div class="lf"><span>101</span></div>
						<div class="rf">
							<p class="p1">房间号：101</p>
							<p class="p1">面积：100平方米</p>
							<p class="p1">层高：1层</p>	
							
						</div>
					
					</a>
					<a href="#" class="floor midfloor">
						<div class="lf"><span>101</span></div>
						<div class="rf">
							<p class="p1">房间号：101</p>
							<p class="p1">面积：100平方米</p>
							<p class="p1">层高：1层</p>	
							
						</div>
						
					</a>
					<a href="#" class="floor">
						<div class="lf"><span>101</span></div>
						<div class="rf">
							<p class="p1">房间号：101</p>
							<p class="p1">面积：100平方米</p>
							<p class="p1">层高：1层</p>	
							
						</div>
					
					</a>
				</div>
				<div class="floors">
					<a href="#" class="floor">
						<div class="lf"><span>101</span></div>
						<div class="rf">
							<p class="p1">房间号：101</p>
							<p class="p1">面积：100平方米</p>
							<p class="p1">层高：1层</p>	
							
						</div>
					
					</a>
					<a href="#" class="floor midfloor">
						<div class="lf"><span>101</span></div>
						<div class="rf">
							<p class="p1">房间号：101</p>
							<p class="p1">面积：100平方米</p>
							<p class="p1">层高：1层</p>	
							
						</div>
						
					</a>
					<a href="#" class="floor">
						<div class="lf"><span>101</span></div>
						<div class="rf">
							<p class="p1">房间号：101</p>
							<p class="p1">面积：100平方米</p>
							<p class="p1">层高：1层</p>	
							
						</div>
					
					</a>
				</div>
			
				<div class="floors">
					<a href="#" class="floor">
						<div class="lf"><span>101</span></div>
						<div class="rf">
							<p class="p1">房间号：101</p>
							<p class="p1">面积：100平方米</p>
							<p class="p1">层高：1层</p>	
							
						</div>
					
					</a>
					<a href="#" class="floor midfloor">
						<div class="lf"><span>101</span></div>
						<div class="rf">
							<p class="p1">房间号：101</p>
							<p class="p1">面积：100平方米</p>
							<p class="p1">层高：1层</p>	
							
						</div>
						
					</a>
					<a href="#" class="floor">
						<div class="lf"><span>101</span></div>
						<div class="rf">
							<p class="p1">房间号：101</p>
							<p class="p1">面积：100平方米</p>
							<p class="p1">层高：1层</p>	
							
						</div>
					
					</a>
				</div>
			
				
			</div>
			<div class="foot" style="width:500px;">
							<ul class="pagination pagination-sm">
 								<li class="disabled"><a href="#">&lt;上一页</a></li>
 								<li class="active"><a href="#">1</a></li>
  								<li ><a href="#">2</a></li>
 								<li><a href="#">3</a></li>
  								<li><a href="#">4</a></li>
  								<li><a href="#">5</a></li>
  								<li ><a href="#">下一页&gt;</a></li>
							</ul>
			</div>
			
		</div>
	<jsp:include page="footer.jsp" flush="true" />
	<script>
		 
			$("#building").bind("change",function(){
				alert($(this).val());
				
			});
			$("#bfloor").bind("change",function(){
				alert($(this).val());	
			});
			$("#bpart").bind("change",function(){
				alert($(this).val());
			});
			
			
			//选择框数据后台的初始化 
	$(document).ready(function(){
		$.post("url链接",function(data){
		for(i=0;i<data.length;i++)
			{
			$("#building").append('<option>'+data[i].**+'</option>');
			$("#bfloor").append('<option>'+data[i].**+'</option>');
			$("#bpart").append('<option>'+data[i].**+'</option>');
			}
		});
	});
			
			//
	</script>
	</body>
</html>
