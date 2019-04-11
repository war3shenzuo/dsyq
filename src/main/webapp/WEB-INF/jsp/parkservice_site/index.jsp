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
<title>园区服务</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
</head>
<body>
	
	<jsp:include page="../header.jsp" flush="true" />
	
    <div class="mainBox">
    	<div id="demo01" class="flexslider">
			<ul class="slides">
				<li><div class="img"><img src="<%=basePath %>/img/bignav3.jpg"  alt="" /></div></li>
				<li><div class="img"><img src="<%=basePath %>/img/bignav2.jpg"  alt="" /></div></li>
				<li><div class="img"><img src="<%=basePath %>/img/bignav2.jpg"  alt="" /></div></li>
			</ul>
		</div>
		<div class="svsapplbox">
			<div class="smallbox">
				<img src="<%=basePath %>/img/yqq_01.png"/>
				<div class="smallbox-pice">
					<h1>采购服务</h1>
					<p>企业向园区发起采购需求，园区集合多家企业采购需求后统一采购。</p>
					 <span>
						<c:choose>
							<c:when test="${empty userInfo}">
								<e onclick="toLogin()">采购服务</e>
							</c:when>
							<c:otherwise>
								<c:choose>
								<c:when test="${userInfo.userType==1}">
								<a href="<%=basePath%>/purchase/purchaseApply.do?service_type=GGCG" >采购服务</a>
								</c:when>
								<c:otherwise>
								<a href="javascript:void(0)" onclick="person()">采购服务</a>
								</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						
					</span>
				</div>
			</div>
			<div class="smallbox">
				<img src="<%=basePath %>/img/yqq_02.png"/>
				<div class="smallbox-pice">
					<h1>办公服务</h1>
					<p>企业服务申请审批后，园区向企业进行派工，当企业园区双方都确认完工后，该次申请完结并生成账单。</p>
					<span>
							<c:choose>
							<c:when test="${empty userInfo}">
								<e onclick="toLogin()">物业保修</e>
								<e onclick="toLogin()">人员代招</e>
							</c:when>
							<c:otherwise>
								<c:choose>
								<c:when test="${userInfo.userType==1}">
								<a href="<%=basePath%>/quotation/selectRoom.do?service_type=WYBX">物业保修</a>
								<a href="<%=basePath%>/recruitment/recruitmentApply.do?service_type=RYDZ" >人员代招</a>
								</c:when>
								<c:otherwise>
								<e onclick="person()">物业保修</e>
								<e onclick="person()">人员代招</e>
							
								</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						
					</span>
				</div>
			</div>
			<div class="smallbox">
				<img src="<%=basePath %>/img/yqq_03.png"/>
				<div class="smallbox-pice">
					<h1>商务服务</h1>
					<p>企业可向园区托办商务相关事物，整个申请-审批-确认-完工流程，都可通过“用户中心”跟踪进度。</p>
					<span>
						<c:choose>
							<c:when test="${empty userInfo}">
							<e onclick="toLogin()">商务服务</e>
<!-- 							<e onclick="toLogin()">执照办理</e>
								<e onclick="toLogin()">商标注册</e>
								<e onclick="toLogin()">代理会计</e>
								<e onclick="toLogin()">法务咨询</e> -->
							</c:when>
							<c:otherwise>
								<c:choose>
								<c:when test="${userInfo.userType==1}">
								<a href="<%=basePath%>/quotation/BusinessService.do?service_type=SWFW">商务服务</a>
<%-- 								<a href="<%=basePath%>/quotation/BusinessService.do?service_type=ZZBL">执照办理</a>
								<a href="<%=basePath%>/quotation/BusinessService.do?service_type=SBZC" >商标注册</a>
								<a href="<%=basePath%>/quotation/BusinessService.do?service_type=DLKJ" >代理会计</a>
								<a href="<%=basePath%>/quotation/BusinessService.do?service_type=FWZX" >法务咨询</a> --%>
								</c:when>
								<c:otherwise>
								<e onclick="person()">商务服务</e>
<!-- 								<e onclick="person()">执照办理</e>
								<e onclick="person()">商标注册</e>
								<e onclick="person()">代理会计</e>
								<e onclick="person()">法务咨询</e> -->
							
								</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					
					
				</div>
			</div>
			<div class="smallbox">
				<img src="<%=basePath %>/img/yqq_04.png"/>
				<div class="smallbox-pice">
					<h1>预约租赁</h1>
					<p>“预约租赁”服务是指企业对园区公共设施设备进行使用申请的服务，包括如会议室、演播厅等的预约使用，以及办公电器、办公家具等设备的租赁。</p>
					<span>
					
							<c:choose>
							<c:when test="${empty userInfo}">
							
									<e onclick="toLogin()">设施预约</e>
<!-- 								<e onclick="toLogin()">装修申请</e> -->
<!-- 								<e onclick="toLogin()">摄影棚预约</e> -->
							</c:when>
							<c:otherwise>
								<c:choose>
								<c:when test="${userInfo.userType==1}">
								<a href="<%=basePath%>/engagement/meetCal.do" >设施预约</a>
<%-- 								<a href="<%=basePath%>/webparkservice/serviceapl.do?service_type=ZXSQ">装修申请</a> --%>
<%-- 								<a href="<%=basePath%>/webparkservice/serviceapl.do?service_type=SYPYY" >摄影棚预约</a> --%>
									</c:when>
								<c:otherwise>
								<e onclick="person()">设施预约</e>
<!-- 								<e onclick="person()">装修申请</e> -->								
<!-- 								<e onclick="person()">摄影棚预约</e> -->
							
								</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					
					
				
				</div>
			</div>
		</div>
    </div>
   	<jsp:include page="../footer.jsp" flush="true" />
	

<script type="text/javascript">
$(function(){

	$('#demo01').flexslider({
		animation: "slide",
		direction:"horizontal",
		easing:"swing"
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
			title : "个人用户不支持申请园区服务！",
			type : "error",
			confirmButtonText : "确定",
			closeOnConfirm : true
		});
	}
</script>
</body>
</html>
