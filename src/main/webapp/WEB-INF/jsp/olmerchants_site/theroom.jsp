<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.etop.management.properties.ImgProperties" %>
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
		<title>${park_name}</title>
			 <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
			 <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/style.min862f.css"/>
			<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
			<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/chooseroomhead.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
	</head>
	<style>
.title{
        padding:20px 0;
        margin:20px 0;
        border-top:1px solid #E4E1E1;
        border-bottom:1px solid #E4E1E1;
      }
      .title .span1{
        font-size:30px;
        color:#EE770F;
        font-weight: bold;
      }
      .title .span2{
        font-weight:bold;
        font-size:23px;
        margin-left:25px;
      }
	.word .p4 {
    line-height: 32px;
    font-size: 16px;
    text-indent: 0;
}.word e img {
    margin: 10px 10px 0 0;
    border: 1px solid #ddd;
    width: 265px;
    height: auto;
}
</style>
	<body>
		<jsp:include page="../header.jsp" flush="true" />
		<input type="hidden" value="${webpark.parkImg}" id="parkImg">
		<div class="wrap">
			<div class="head">
				<p>${webpark.parkName}</p>
					<div class="tuli">
						<img src="<%=basePath %>/img/grey.png" /><span>已使用</span>
						<%-- <img src="<%=basePath %>/img/blue.png" /><span>已预约</span> --%>
						<img src="<%=basePath %>/img/green.png"/><span>未使用</span>
					</div>
			</div>
			<div class="choose" id="choose" >
				<select id="louhao">
				</select>
				<select id="louceng">
				</select>
				<select id="quyu">
				</select>
			</div>
			<img src="" id="floorimg"  style="width: 1155px; height:400px; display:none"/>
			
			<div class="row" id="loading">
			<div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-content">
                        <div class="spiner-example">
                            <div class="sk-spinner sk-spinner-circle">
                                <div class="sk-circle1 sk-circle"></div>
                                <div class="sk-circle2 sk-circle"></div>
                                <div class="sk-circle3 sk-circle"></div>
                                <div class="sk-circle4 sk-circle"></div>
                                <div class="sk-circle5 sk-circle"></div>
                                <div class="sk-circle6 sk-circle"></div>
                                <div class="sk-circle7 sk-circle"></div>
                                <div class="sk-circle8 sk-circle"></div>
                                <div class="sk-circle9 sk-circle"></div>
                                <div class="sk-circle10 sk-circle"></div>
                                <div class="sk-circle11 sk-circle"></div>
                                <div class="sk-circle12 sk-circle"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
			<div class="cont">
				<div class="floors" id="floors">
					
				</div>
			</div>
			
			<div class="foot">
					<ul class="pagination pagination-sm"  id="page">
							
					</ul>
			</div>
			
		</div>
		<div class="wrap">
		      <div class='word'>
		      			<div class="title">
					       <span class="span1">${webpark.parkName}</span> <span class="span2">${webpark.city}</span>  
					       </div>
					        <p class='p4'>
					        <b>园区介绍：</b>${webpark.parkDescribe}<br><br>
					        <b>园区招商政策：</b><br>${webpark.policy}<br>
					        <b>联系人：</b>${webpark.contacts}<br>
					        <b>联系电话：</b>${webpark.mobile}<br>
					        <b>联系地址：</b>${webpark.address}<br>
					        <c:choose>
				              	<c:when test="${webpark.addressImg!=''}">
				              	<img src='<%=ImgProperties.LOAD_PATH%>${webpark.addressImg}' width='240' height='240'/>&nbsp;&nbsp;
				              	 </c:when>
			               	</c:choose>
					        <c:choose>
				              	<c:when test="${webpark.wechatQr!=''}">
				              	<img src='<%=ImgProperties.LOAD_PATH%>${webpark.wechatQr}' width='240' height='240'/>
				              	 </c:when>
			               	</c:choose>
					       </p>
					       <e id="imgtexts"></e>
			        </div>
      
    </div>
		<jsp:include page="../footer.jsp" flush="true" />
		
		<script>
		
			var louHao=$("#louhao").val();
			var louCeng=$("#louceng").val();
			var quYu=$("#quyu").val();
			var parkId;
			var floorId;
			var storeyId;
			var areaId;
			var buildImgs=new Array();
			 var strs= new Array(); //定义一数组 
		      var parkImgs=$("#parkImg").val();
			 
		      var allPage="";
		      var nowMaxPage="5";         //此刻分页码能看到的最大页码
		      var nowMinPage="1";         //此刻分页码能看到的最小页码
			 
			$(document).ready(function(){
				$("#choose").hide();
				parkId=GetQueryString("id");
				$.post("<%=basePath %>/webolmerchants/searchFloorId.do?",{"id":parkId},function(data){
					$("#louhao").empty(); 
					if(data.length==0){
						swal("无房间信息");
						$("#loading").css("display","none");
						$("#choose").show();
						return
					}
					for(var i=0;i<data.length;i++){
						$("#louhao").append("<option value=\'"+data[i].refFloorId+"\'>"+data[i].buildName+"</option>");
					}
					floorId=data[0].refFloorId;
					
					
					$.post("<%=basePath %>/webolmerchants/searchStoreyId.do?",{"id":parkId,"refFloorId":floorId},function(data){
						$("#louceng").empty(); 
						if(data.length==0){
							swal("无可选楼层");
							$("#loading").css("display","none");
							$("#choose").show();
							$("#louceng").append("<option value='0'>无可选楼层</option>");
							return
						}
						buildImgs=data;
						for(var u=0;u<data.length;u++){
							$("#louceng").append("<option value=\'"+data[u].refStoreyId+"\' name=\'"+data[u].buildImg+"\'>"+data[u].buildName+"</option>");
						}
						storeyId=data[0].refStoreyId;
						
						$.post("<%=basePath %>/webolmerchants/searchAreaId.do?",{"id":parkId,"refFloorId":floorId,"refStoreyId":storeyId},function(data){
							$("#floorimg").src="'+data[i].buildImg+'";
							$("#quyu").empty(); 
							if(data.length==0){
								swal("无可选区域");
								$("#loading").css("display","none");
								$("#choose").show();
								$("#quyu").append("<option value='0'>无可选区域</option>");
								return
							}
							for(var h=0;h<data.length;h++){
								$("#quyu").append("<option value=\'"+data[h].refAreaId+"\'>"+data[h].buildName+"</option>");
							}
							areaId=data[0].refAreaId;
							getRoomsF(areaId);
							
						});
						
					});
				});
				
				
				
				
                strs=parkImgs.split(","); //字符分割 
                var imgtext="";
                for(var m=0;m<strs.length;m++){
                  
                  imgtext=imgtext+"<img src='<%=ImgProperties.LOAD_PATH%>"+strs[m]+"' class='img2'/>";
                  
                }
                if(strs==""){
              	  imgtext="";
                }
                $("#imgtexts").append(imgtext);
               
			});
			
			$("#louhao").change(function(){
				floorId=$("#louhao").val();
				$.post("<%=basePath %>/webolmerchants/searchStoreyId.do?",{"id":parkId,"refFloorId":floorId},function(data){
					$("#louceng").empty(); 
					$("#louceng").append("<option value='0'>请选择楼层</option>");
					buildImgs=data;
					for(var i=0;i<data.length;i++){
						$("#louceng").append("<option value=\'"+data[i].refStoreyId+"\' name=\'"+data[i].buildImg+"\'>"+data[i].buildName+"</option>");
					}
				});
				
				
			});
			$("#louceng").change(function(){
				storeyId=$("#louceng").val();
				for(var j=0;j<buildImgs.length;j++){
					if(buildImgs[j].refStoreyId==storeyId){
						$("#floorimg").show();
						$("#floorimg").attr("src","<%=ImgProperties.LOAD_PATH%>"+buildImgs[j].buildImg);
					}
				}
				$.post("<%=basePath %>/webolmerchants/searchAreaId.do?",{"id":parkId,"refFloorId":floorId,"refStoreyId":storeyId},function(data){
					$("#floorimg").src="'+data[i].buildImg+'";
					$("#quyu").empty(); 
					$("#quyu").append("<option value='0'>请选择区域</option>");
					for(var i=0;i<data.length;i++){
						$("#quyu").append("<option value=\'"+data[i].refAreaId+"\'>"+data[i].buildName+"</option>");
					}
				});
				
			});
			$("#quyu").change(function(){
				//alert("要筛选出房间信息了");
				getRooms();
			});
			
			/* $("#louhao").change(function(){
				louHao=$("#louhao").val();
				louCeng=$("#louceng").val();
				quYu=$("#quyu").val();
				
			}); */
			var nowPage=1;
			var dataLength=0;
			var rows=16;
			function getRooms(){
				areaId=$("#quyu").val();
				$.post("<%=basePath %>/webolmerchants/getRoom.do?",{"id":parkId,"refAreaId":areaId},function(data){
					if(data.length==0){
						swal("无房间信息");
						return
					}
					$("#floors a").remove();
					$("#page li").remove();
					
					dataLength=data.length;
					allPage = Math.ceil(dataLength/rows);
					paging('page',allPage);
					
					for(var j=0;j<rows;j++)
					{
						var haveImg;
						if(data[j].showOut=="0"){
							haveImg="面议";
						}
						else{
							haveImg=data[j].dayPrice+"元/㎡天";
						}
						if(j>=dataLength){return}
						var four=j+1;
						if(four%4==0){
							if(data[j].floorStatus ==1){
								$("#floors").append("<a href='#' class='floor' style=' margin-right: 0px;'><div class='lf'><span>房间号<br>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>单价："+haveImg+"</p><p class='p1'>面积："+data[j].buildArea+"㎡"+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：已出租</p></div><img src='<%=basePath %>/img/greymark.jpg' class='img1'/></a>");
								}else{
									$("#floors").append("<a href='<%=basePath %>/webolmerchants/roomInfo.do?fjh="+data[j].roomNum+"&mj="+data[j].buildArea+"&cg="+data[j].renovation+"&roomid="+data[j].id+"' class='floor' style=' margin-right: 0px;'><div class='lf'><span>房间号<br>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>单价："+haveImg+"</p><p class='p1'>面积："+data[j].buildArea+"㎡"+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：待出租</p></div><img src='<%=basePath %>/img/greenmark.jpg' class='img1'/></a>");
								}
						}else{
							if(data[j].floorStatus ==1){
								$("#floors").append("<a href='#' class='floor'><div class='lf'><span>房间号<br>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>单价："+haveImg+"</p><p class='p1'>面积："+data[j].buildArea+"㎡"+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：已出租</p></div><img src='<%=basePath %>/img/greymark.jpg' class='img1'/></a>");
								}<%-- else if(data[j].floorStatus ==2){
									$("#floors").append("<a href='#' class='floor'><div class='lf'><span>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>房间号："+data[j].roomNum+"</p><p class='p1'>面积："+data[j].buildArea+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：已预约</p></div><img src='<%=basePath %>/img/bluemark.jpg' class='img1'/></a>");
								} --%>else{
									$("#floors").append("<a href='<%=basePath %>/webolmerchants/roomInfo.do?fjh="+data[j].roomNum+"&mj="+data[j].buildArea+"&cg="+data[j].renovation+"&roomid="+data[j].id+"' class='floor'><div class='lf'><span>房间号<br>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>单价："+haveImg+"</p><p class='p1'>面积："+data[j].buildArea+"㎡"+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：待出租</p></div><img src='<%=basePath %>/img/greenmark.jpg' class='img1'/></a>");
								}
						}
						
					}
				});
			}
			
				
				function goPage(p){
					
					$.post("<%=basePath %>/webolmerchants/getRoom.do?",{"id":parkId,"refAreaId":areaId},function(data){
						nowPage=p;
						$("#floors a").remove();
						for(j=(p-1)*rows;j<rows*(p-1)+rows;j++)
						{
							var haveImg;
							if(data[j].showOut=="0"){
								haveImg="面议";
							}
							else{
								haveImg=data[j].dayPrice+"元/㎡天";
							}
							if(j>=dataLength){return}
							var four=j+1;
							if(four%4==0){
								if(data[j].floorStatus ==1){
									$("#floors").append("<a href='#' class='floor' style=' margin-right: 0px;'><div class='lf'><span>房间号<br>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>单价："+haveImg+"</p><p class='p1'>面积："+data[j].buildArea+"㎡"+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：已出租</p></div><img src='<%=basePath %>/img/greymark.jpg' class='img1'/></a>");
									}else{
										$("#floors").append("<a href='<%=basePath %>/webolmerchants/roomInfo.do?fjh="+data[j].roomNum+"&mj="+data[j].buildArea+"&cg="+data[j].renovation+"&roomid="+data[j].id+"' class='floor' style=' margin-right: 0px;'><div class='lf'><span>房间号<br>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>单价："+haveImg+"</p><p class='p1'>面积："+data[j].buildArea+"㎡"+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：待出租</p></div><img src='<%=basePath %>/img/greenmark.jpg' class='img1'/></a>");
									}
							}else{
								if(data[j].floorStatus ==1){
									$("#floors").append("<a href='#' class='floor'><div class='lf'><span>房间号<br>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>单价："+haveImg+"</p><p class='p1'>面积："+data[j].buildArea+"㎡"+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：已出租</p></div><img src='<%=basePath %>/img/greymark.jpg' class='img1'/></a>");
									}<%-- else if(data[j].floorStatus ==2){
										$("#floors").append("<a href='#' class='floor'><div class='lf'><span>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>房间号："+data[j].roomNum+"</p><p class='p1'>面积："+data[j].buildArea+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：已预约</p></div><img src='<%=basePath %>/img/bluemark.jpg' class='img1'/></a>");
									} --%>else{
										$("#floors").append("<a href='<%=basePath %>/webolmerchants/roomInfo.do?fjh="+data[j].roomNum+"&mj="+data[j].buildArea+"&cg="+data[j].renovation+"&roomid="+data[j].id+"' class='floor'><div class='lf'><span>房间号<br>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>单价："+haveImg+"</p><p class='p1'>面积："+data[j].buildArea+"㎡"+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：待出租</p></div><img src='<%=basePath %>/img/greenmark.jpg' class='img1'/></a>");
									}
							}
						}
					});
				}
				

				
				
				
				function GetQueryString(name)
				{
				     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
				     var r = window.location.search.substr(1).match(reg);
				     if(r!=null)return  decodeURI(r[2]); return null;
				}
				
				
				
				
				
				//第一次加载数据中的第一个区域的房间
				function getRoomsF(t){
					
				areaId=t;
				$("#loading").css("display","none");
				$("#choose").show();
				$.post("<%=basePath %>/webolmerchants/getRoom.do?",{"id":parkId,"refAreaId":areaId},function(data){
					if(data.length==0){
						swal("无房间信息");
						return
					}
					$("#floors a").remove();
					$("#page li").remove();
					
					
					dataLength=data.length;
					
					allPage = Math.ceil(dataLength/rows);
					paging('page',allPage);
					
					for(var j=0;j<rows;j++)
					{
						var haveImg;
						if(data[j].showOut=="0"){
							haveImg="面议";
						}
						else{
							haveImg=data[j].dayPrice+"元/㎡天";
						}
						if(j>=dataLength){return}
						var four=j+1;
						if(four%4==0){
							if(data[j].floorStatus ==1){
								$("#floors").append("<a href='#' class='floor' style=' margin-right: 0px;'><div class='lf'><span>房间号<br>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>单价："+haveImg+"</p><p class='p1'>面积："+data[j].buildArea+"㎡"+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：已出租</p></div><img src='<%=basePath %>/img/greymark.jpg' class='img1'/></a>");
								}else{
									$("#floors").append("<a href='<%=basePath %>/webolmerchants/roomInfo.do?fjh="+data[j].roomNum+"&mj="+data[j].buildArea+"&cg="+data[j].renovation+"&roomid="+data[j].id+"' class='floor' style=' margin-right: 0px;'><div class='lf'><span>房间号<br>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>单价："+haveImg+"</p><p class='p1'>面积："+data[j].buildArea+"㎡"+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：待出租</p></div><img src='<%=basePath %>/img/greenmark.jpg' class='img1'/></a>");
								}
						}else{
							if(data[j].floorStatus ==1){
								$("#floors").append("<a href='#' class='floor'><div class='lf'><span>房间号<br>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>单价："+haveImg+"</p><p class='p1'>面积："+data[j].buildArea+"㎡"+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：已出租</p></div><img src='<%=basePath %>/img/greymark.jpg' class='img1'/></a>");
								}<%-- else if(data[j].floorStatus ==2){
									$("#floors").append("<a href='#' class='floor'><div class='lf'><span>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>房间号："+data[j].roomNum+"</p><p class='p1'>面积："+data[j].buildArea+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：已预约</p></div><img src='<%=basePath %>/img/bluemark.jpg' class='img1'/></a>");
								} --%>else{
									$("#floors").append("<a href='<%=basePath %>/webolmerchants/roomInfo.do?fjh="+data[j].roomNum+"&mj="+data[j].buildArea+"&cg="+data[j].renovation+"&roomid="+data[j].id+"' class='floor'><div class='lf'><span>房间号<br>"+data[j].roomNum+"</span></div><div class='rf'><p class='p1'>单价："+haveImg+"</p><p class='p1'>面积："+data[j].buildArea+"㎡"+"</p><p class='p1'>装修："+data[j].renovation+"</p><p class='p1'>状态：待出租</p></div><img src='<%=basePath %>/img/greenmark.jpg' class='img1'/></a>");
								}
						}
						
					}
				});
			}
		</script>

 </body>
</html>
