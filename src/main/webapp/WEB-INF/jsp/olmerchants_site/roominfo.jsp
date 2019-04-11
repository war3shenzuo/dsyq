<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import="com.etop.management.properties.ImgProperties"  %>
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
		<title>意向房间申请</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/chooseroomdetails.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
	</head>
	<body>
		<jsp:include page="../header.jsp" flush="true" />
		<input id='roomImgs' value='${roomImg}' type="hidden"/>
		<div class="wrap">
			<div class="title" >
				<p>意向房间基本信息</p>
				<img src="<%=basePath %>/img/bottomsan.jpg"/>
			</div>
			<div class="infos">
				<div class="info">
					<span>房间号</span>
					<p id="floorRoom"></p>
				</div>
				<div class="info">
					<span>面积</span>
					<p><span id="area"></span></p>
				</div>
				<div class="info">
					<span>装修</span>
					<p id="renovation"></p>
				</div>
			</div>
			
			<p class="p1">客户基本信息</p>
			<form class="form1">
				<div class="whole">
					<label for="name">公司名称</label><input type="text" class="rf" name="name" id="companyName" value=""/>
				</div>
				<div>
					<label for="xinzhi" class="label1">公司性质 </label>
					<select id="companyType">
						<option value="有限责任公司">有限责任公司</option>
                        <option value="股份有限公司">股份有限公司</option>
					</select>
					<label for="guimo" class="label2">公司规模 </label>
					
					<select id="companyCapital">
						<option value="10万以下">10万以下</option>
                        <option value="10万-50万">10万-50万</option>
                        <option value="50万-100万">50万-100万</option>
                        <option value="100-500万">100-500万</option>
                        <option value="500万以上">500万以上</option>	
					</select>
				</div>
				<div class="whole">
					<label for="yewu">公司主营业务</label><input type="text" class="rf" name="yewu" id="businessType" value=""/>
				</div>
				<div class="whole">
					<label for="address">地址</label><input type="text" class="rf" name="address" id="beforeseat" value=""/>
				</div>
				<div class="small">
					<label for="tel"  class="label1">公司电话</label><input type="text" name="tel" id="companyMobile" value="" style="margin-left: 8px"/>
					<label for="chuang"  class="label2">传真</label><input type="text" name="chuang" id="companyFax" value="" style="margin-left: 8px"/>
				</div>
				<div class="whole">
					<label for="tel2">公司其他联系方式</label><input type="text" class="rf" name="tel2" id="mobile" value=""/>
				</div>
				<div class="whole">
					<label for="people">公司对接联系人</label><input type="text" class="rf" name="people" id="contact" value=""/>
				</div><!-- 
				<div class="small">
					<label for="time1" class="label3" >预计入住时间</label><input type="text" name="time1" id="time1"/> <span class="one">*</span>提示说明
				</div> -->
				<div class="small">
					<label class="label3" for="time2">预计入住时间</label><input type="text" name="time2" id="remarks" value=""/> <span class="one">*</span>提示说明
				</div>
			</form>
			<div id="roomImgbox">
				
			</div>
			<div class="da">
				<a href="javascript:" class="a1" onclick="submit()"><p>点击提交</p></a>
			</div>
		</div>
		
		<jsp:include page="../footer.jsp" flush="true" />
		
		<script>
		var basn="<%=basePath %>";
		var roomId=GetQueryString("roomid");
		var roomImgs=$("#roomImgs").val()
		
		var strs= new Array(); //定义一数组 

		strs=roomImgs.split(","); //字符分割 
		if(roomImgs != ""){
			for (i=0;i<strs.length ;i++ ) 
			{ 
			$("#roomImgbox").append("<img src='<%=ImgProperties.LOAD_PATH%>"+strs[i]+"'  style='width:100%; margin-top:10px;'><br>")
			} 
		}
		
		$(document).ready(function(){
			$("#floorRoom").text(GetQueryString("fjh"));
			$("#area").text(GetQueryString("mj"));
			$("#renovation").text(GetQueryString("cg"));
		});
		
		function GetQueryString(name)
		{
		     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		     var r = window.location.search.substr(1).match(reg);
		     if(r!=null)return  decodeURI(r[2]); return null;
		}
		</script>
	</body>
</html>

