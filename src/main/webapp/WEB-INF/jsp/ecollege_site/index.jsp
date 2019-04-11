<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import="com.etop.management.properties.ImgProperties" %>
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
		<title>电商学院</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/xueyuan2.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
	    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/hzw-city-picker.css"/>
				<style> 
	body{ text-align:center} 
#divcss5{margin:0 auto;} 

	</style> 
	</head>
	<body>
	
	<jsp:include page="../header.jsp" flush="true" />
	
		<div class="wrap">
			<div id="demo01" class="flexslider bigimg">
			<ul class="slides">
				<li><div class="img"><img src="<%=basePath %>/img/banner4.jpg"  alt="" /></div></li>
				<li><div class="img"><img src="<%=basePath %>/img/banner.jpg"  alt="" /></div></li>
			</ul>
		</div>
		<div class="title" >
				<p class="p1"></p>
				<div class="things"  id="divcss5">
					<div class="thing">
						<a href="javascript:" onclick="linePrograms(1)">
						<img src="<%=basePath %>/img/e2.png"/>
						<p>线上培训</p>
						</a>
					</div>
					<div class="thing" >
						<a href="javascript:" onclick="linePrograms(3)">
						<img src="<%=basePath %>/img/e1.png"/>
						<p>线下培训</p>
						</a>
					</div>
					<div class="thing">
						<a href="javascript:" onclick="linePrograms(0)">

						<img src="<%=basePath %>/img/e3.png"/>
						<p>线下课程</p>
						</a>
					</div>
					
				</div>
		</div>
		<div class="cont">
			<div class="conttitle">
				<div class="title2">
					<a class="a1" href="#" id="typeName">最新园区培训</a> 
					<div style=" clear: both;">
						<div  class="screenO">
							<h2>课程类型</h2>
							<ul>
								<li>
									<a href="javascript:"  onclick="screenO('')">所有类型</a>
								</li>
								<li>
									<a href="javascript:"  onclick="screenO('1')">高管研修</a>
								</li>
								<li>
									<a href="javascript:"  onclick="screenO('2')">创业辅助</a>
								</li>
								<li>
									<a href="javascript:"  onclick="screenO('3')">淘系美工</a>
								</li>
								<li>
									<a href="javascript:"  onclick="screenO('4')">淘系运营</a>
								</li>
								<li>
									<a href="javascript:"  onclick="screenO('5')">淘系客服</a>
								</li>
								<li>
									<a href="javascript:"  onclick="screenO('6')">淘系推广</a>
								</li>
								<li>
									<a href="javascript:"  onclick="screenO('7')">淘系无线</a>
								</li>
								<li>
									<a href="javascript:"  onclick="screenO('8')">微商</a>
								</li>
								<li>
									<a href="javascript:"  onclick="screenO('9')">京东系列</a>
								</li>
								<li>
									<a href="javascript:"  onclick="screenO('10')">跨境系列</a>
								</li>
								<li>
									<a href="javascript:"  onclick="screenO('11')">其他平台</a>
								</li>
								<li>
									<a href="javascript:"  onclick="screenO('12')">其他类型</a>
								</li>
							</ul>
						</div>
						<div  class="screenO">
							<!-- <h2>所在园区</h2>
							<ul id="parks">
								
							</ul> -->
							<p class="Esreach">
							园区：<input type="text" id="targetName" class="" name="" placeholder="输入园区" style=" margin: 0 15px 0 15px;">
							课程标题：<input type="text" id="courseName" class="" name="" placeholder="输入课程标题" style=" margin: 0 15px 0 15px;">
<!-- 							城市：<input type="text" id="" style=" margin: 0 0 0 15px;" placeholder="输入城市"> -->
							城市：<select class="" id="cityChoice" name="cityChoice">
							 	<option value="">全部</option>
                              <c:forEach items="${getCitys}" var="Citys">
                                  <option value="${Citys.city}">${Citys.city}</option>
                              </c:forEach>
                              </select>
							<button onclick="etSreach()">搜 索</button>
							</p>
						</div>
					</div>
					
				</div>
			</div>
				      
			<div id="somecourses">
				<div class="course" id="lis">
					
				</div>
			</div>
			<div class="foot">
					<ul id="page" class="pagination pagination-sm">
					
					</ul>
			</div>
		
			
		</div>	
		</div>	
	    <jsp:include page="../footer.jsp" flush="true" />
		<script src="<%=basePath %>/js/xueyuan.js" type="text/javascript"></script>
		<script>
		var nowPage=1;//当前页页码
		var dataLength=0;
		var rows=6;//每页显示的条数
		var garden=true;
		var thePark="";//所属园区
		var theNum="";//所属分类
		var parkAr="";//所有有数据园区的名字集合，用于过滤重复的园区名字
		var nowlistType=1;//现在的数据类型  1是线上课程，2是线下课程，3是线下培训
		var urlType=""
		var parkGroupId=GetQueryString("parkGroupId");
		var target=GetQueryString("target");
		
		var allPage="";
		var nowMaxPage="5";         //此刻分页码能看到的最大页码
		var nowMinPage="1";         //此刻分页码能看到的最小页码
		var targetName="";
		var courseName="";
		
		urlType=GetQueryString("urlType");
		
		function GetQueryString(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return decodeURI(r[2]);
			return null;
		}
		
		$(function(){

			$('#demo01').flexslider({//banner图滚动的js
				animation: "slide",
				direction:"horizontal",
				easing:"swing"
			});
			 if(urlType){
				nowlistType=urlType;
				$("#typeName").html("最新园区线下课程");
			}
// 			 var aaaa=$('#city').attr('value');
// 			    var str_after = aaaa.split('-')[1]; 
// 				city=str_after;
			 var getUrl="<%=basePath %>/webecollege/selectTraining.do";
			$.post(getUrl,{"parkGroupId":parkGroupId,"trainType":nowlistType,"target":target},function(data){
					
					/* for(var m=0;m<dataLength;m++){
						if(parkAr.indexOf(data[m].target)>=0){
							
						}else{
							$("#parks").append("<li><a  href='javascript:'  onclick=screenO('','"+data[m].target+"')>"+data[m].target+"</a></li>");
							parkAr=parkAr+data[m].target;
							
						}
						
					} */
					
					dataLength=data.length;
					allPage = Math.ceil(dataLength/rows);
					pagingEcollege('page',allPage,nowlistType);
					
					for(j=0;j<rows;j++)
						{ 
							 if(data[j]==null)
								 break;
							 if((data[j].courseImg =="" || data[j].courseImg==null) && (data[j].coverImg =="" || data[j].coverImg==null)){
								$("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=basePath %>/img/t4.jpg'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description+"</p></div></a></div>");
						 	}else if(data[j].courseImg !=undefined ||data[j].courseImg !=null ){
							    $("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=ImgProperties.LOAD_PATH%>"+data[j].courseImg+"'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +"</p></div></a></div>");
						 	}else if(data[j].coverImg !=undefined ||data[j].coverImg !=null ){
							    $("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=ImgProperties.LOAD_PATH%>"+data[j].coverImg+"'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +"</p></div></a></div>");
						 	}
						}
			});
		});
		/****************************数据筛选****************************************/
		function screenO(num){
			var url="<%=basePath %>/webecollege/selectTraining.do";
			if(num==""){
				theNum="";
			}
			else{
				theNum=num;
			}
// 			var aaaa=$('#city').attr('value');
// 		    var str_after = aaaa.split('-')[1]; 
// 			city=str_after;
			var screenOlength=0;
			$.post(url,{"parkGroupId":parkGroupId,"trainType":nowlistType,"courseType":theNum,"courseName":courseName,"target":target,"targetName":targetName},function(data){	
				dataLength=data.length;
				$("#page li").remove();
				$("#lis div").remove();
				
				if(dataLength==0){
					$("#lis").append("<div class='imgl'>您选择的类型暂无更多内容。。。</div>");
					return;
				}
					 
				allPage = Math.ceil(dataLength/rows);
				pagingEcollege('page',allPage,nowlistType)
				for(j=0;j<rows;j++)
					{ 
					 if(data[j]==null){ break;}
						if(data[j].courseContent==undefined || data[j].courseContent==null){data[j].courseContent=""}
						if(data[j].description==undefined || data[j].description==null){data[j].description=""}
					if((data[j].courseImg =="" || data[j].courseImg==null) && (data[j].coverImg =="" || data[j].coverImg==null)){
						$("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=basePath %>/img/t4.jpg'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +""+data[j].courseContent +"</p></div></a></div>");
					}else if(data[j].courseImg !=undefined ||data[j].courseImg !=null ){
						$("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=ImgProperties.LOAD_PATH%>"+data[j].courseImg+"'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +""+data[j].courseContent +"</p></div></a></div>");
				 	}else if(data[j].coverImg !=undefined ||data[j].coverImg !=null ){
				 		$("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=ImgProperties.LOAD_PATH%>"+data[j].coverImg+"'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +""+data[j].courseContent +"</p></div></a></div>");
					}
					}
				
		});
		}
		function linePrograms(a){//数据类型筛选
			nowlistType=a;
			$("#cityChoice").empty(); 
			$("#cityChoice").append("<option value="+""+">全部</option>")
			var citys = getCitys();
			for(var i=0;i<citys.length;i++){
				$("#cityChoice").append(" <option value="+citys[i].city+">"+citys[i].city+"</option>");
			} 
			city = $("#cityChoice option:selected").val();
			/* parkAr=""; */
			var url="<%=basePath %>/webecollege/selectTraining.do";
			if(a==3){
				$("#typeName").html("最新园区线下培训");
			}else if(a==0){
				$("#typeName").html("最新园区线下课程");
			}else{
				$("#typeName").html("最新园区线上培训");
			}
// 			var aaaa=$('#city').attr('value');
// 		    var str_after = aaaa.split('-')[1]; 
// 			city=str_after;
			$.post(url,{"parkGroupId":parkGroupId,"trainType":nowlistType,"courseName":courseName,"target":target,"targetName":targetName,"city":city},function(data){
				$("#page li").remove();
				$("#lis div").remove();
				$("#parks li").remove()
				dataLength=data.length;
				/* for(var m=0;m<dataLength;m++){
					if(parkAr.indexOf(data[m].target)>=0){
						
					}else{
						$("#parks").append("<li><a  href='javascript:'  onclick=screenO('','"+data[m].target+"')>"+data[m].target+"</a></li>");
						parkAr=parkAr+data[m].target;
						
					}
					
				} */
				allPage = Math.ceil(dataLength/rows);
				pagingEcollege('page',allPage,nowlistType)
				for(j=0;j<rows;j++)
					{ 
					 if(data[j]==null){ break;}
					if(data[j].courseContent==undefined || data[j].courseContent==null){data[j].courseContent=""}
					if(data[j].description==undefined || data[j].description==null){data[j].description=""}
					if((data[j].courseImg =="" || data[j].courseImg==null) && (data[j].coverImg =="" || data[j].coverImg==null)){
						$("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=basePath %>/img/t4.jpg'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +""+data[j].courseContent +"</p></div></a></div>");
					}else if(data[j].courseImg !=undefined ||data[j].courseImg !=null ){
						$("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=ImgProperties.LOAD_PATH%>"+data[j].courseImg+"'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +""+data[j].courseContent +"</p></div></a></div>");
				 	}else if(data[j].coverImg !=undefined ||data[j].coverImg !=null ){
				 		$("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=ImgProperties.LOAD_PATH%>"+data[j].coverImg+"'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +""+data[j].courseContent +"</p></div></a></div>");
					}
				}
				
		});
		}
		/************************分页跳转 页码 上一页 下一页********************************************/
		function goPage(p,a){
			var url="<%=basePath %>/webecollege/selectTraining.do";
			city = $("#cityChoice option:selected").val();
// 			var aaaa=$('#city').attr('value');
// 		    var str_after = aaaa.split('-')[1]; 
// 			city=str_after;
			$.post(url,{"parkGroupId":parkGroupId,"trainType":nowlistType,"courseName":courseName,"target":target,"targetName":targetName,"city":city},function(data){
				nowPage=p;
				$("#lis div").remove();
				for(j=(p-1)*rows;j<rows*(p-1)+rows;j++)
				{
					if(j>=dataLength){return;}
					if(data[j].courseContent==undefined || data[j].courseContent==null){data[j].courseContent=""}
					if(data[j].description==undefined || data[j].description==null){data[j].description=""}
					if((data[j].courseImg =="" || data[j].courseImg==null) && (data[j].coverImg =="" || data[j].coverImg==null)){
						$("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=basePath %>/img/t4.jpg'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +""+data[j].courseContent +"</p></div></a></div>");
					}else if(data[j].courseImg !=undefined ||data[j].courseImg !=null ){
						$("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=ImgProperties.LOAD_PATH%>"+data[j].courseImg+"'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +""+data[j].courseContent +"</p></div></a></div>");
				 	}else if(data[j].coverImg !=undefined ||data[j].coverImg !=null ){
				 		$("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=ImgProperties.LOAD_PATH%>"+data[j].coverImg+"'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +""+data[j].courseContent +"</p></div></a></div>");
					}
				}
			});
		}
		
		function etSreach(){
			var url="<%=basePath %>/webecollege/selectTraining.do";
			targetName=$("#targetName").val();
			courseName=$("#courseName").val();
			 city = $("#cityChoice option:selected").val();
// 			var aaaa=$('#city').attr('value');
// 		    var str_after = aaaa.split('-')[1]; 
// 			city=str_after;
			$.post(url,{"parkGroupId":parkGroupId,"trainType":nowlistType,"courseName":courseName,"target":target,"targetName":targetName,"city":city},function(data){
				$("#page li").remove();
				$("#lis div").remove();
				$("#parks li").remove()
				dataLength=data.length;
				
				allPage = Math.ceil(dataLength/rows);
				pagingEcollege2('page',allPage,nowlistType,target,targetName,city)
				for(j=0;j<rows;j++)
					{ 
					 if(data[j]==null){ break;}
					if(data[j].courseContent==undefined || data[j].courseContent==null){data[j].courseContent=""}
					if(data[j].description==undefined || data[j].description==null){data[j].description=""}
					if((data[j].courseImg =="" || data[j].courseImg==null) && (data[j].coverImg =="" || data[j].coverImg==null)){
						$("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=basePath %>/img/t4.jpg'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +""+data[j].courseContent +"</p></div></a></div>");
					}else if(data[j].courseImg !=undefined ||data[j].courseImg !=null ){
						$("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=ImgProperties.LOAD_PATH%>"+data[j].courseImg+"'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +""+data[j].courseContent +"</p></div></a></div>");
				 	}else if(data[j].coverImg !=undefined ||data[j].coverImg !=null ){
				 		$("#lis").append("<div class='imgl'><a href='<%=basePath %>/webecollege/collegeinfo.do?id="+data[j].id+"&type="+nowlistType+"'><img src='<%=ImgProperties.LOAD_PATH%>"+data[j].coverImg+"'/><div class='imgword'><p style='font-size: 18px;line-height:50px;height:50px;'>"+data[j].courseName +"</p><p>"+data[j].description +""+data[j].courseContent +"</p></div></a></div>");
					}
				}
				
		});
		}
		
		 function getCitys(){
		      var data3;
		      $.ajax({  
		          type : "post",  
		          url : "<%=basePath%>/webecollege/getCitys.do",  
		          data : {"offlineType" : nowlistType},
		          async: false,
		          success : function(data2){ 
		            data3=data2;
		          }  
		      
		      });
		      return data3;
		     }  
		 <%-- 
		   function citydata(){
		      var data3;
		      $.ajax({  
		          type : "post",  
		          url : "<%=basePath%>/webecollege/getCity.do",  
		          data : {"type" : nowlistType},
		          async: false,
		          success : function(data2){/* 
		            alert(cityses(data2.hot,data2.province));
		            return cityses(data2.hot,data2.province); */
		            data3=data2;
		          }  
		      
		      });
		      return data3;
		     }  
		    
		  var cityPicker = new HzwCityPicker2({
		    data: citydata(),
		    target: 'cityChoice',
		    valType: 'k-v',
		    hideCityInput: {
		      name: 'city',
		      id: 'city'
		    },
		    hideProvinceInput: {
		      name: 'province',
		      id: 'province'
		    },
		    callback: function(){
		      var aaaa=$('#city').attr('value');
		      var str_after = aaaa.split('-')[1]; 
		    }
		  });

		  cityPicker.init();   --%>
		  
		</script>
	</body>
</html>
