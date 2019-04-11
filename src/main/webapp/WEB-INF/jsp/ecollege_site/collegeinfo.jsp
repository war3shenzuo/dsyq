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
		<title>课程详情</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/xueyuandetails.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
		<link href="<%=basePath%>/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	</head>
	<body>
		
		<jsp:include page="../header.jsp" flush="true" />
	
		<div class="wrap">
			<div class="title1">
				<span class="span1" id="courseName"></span><span class="span2" id="testNum"></span>
				
			</div>
			<div id="srcUrl" ></div>
			<div id="imgUrl"  class="bigImgContent" style="display:none;"><a href=""  target="_blank"><img alt="" src=""  /></a></div>
			<div class="details" >
			<div class="btn" style="margin:0 auto;text-align:center; ">
						 <c:if test="${empty userInfo && type == 1}">
						<a class="btna2"  onclick="toLogin()">考试</a>
						</c:if> 
<%-- 						<c:if test="${userInfo.userType==2}"> --%>
<!-- 						<a href="#" class="btna2" id="eaxmtion" style="display: none">考试</a> -->
<%-- 						</c:if> --%>
						<c:if test="${userInfo.userType!=2 && userInfo.userType!= null && type == 1}">
						<a class="btna1" onclick="person()">考试</a>
						</c:if> 
<!-- 				<a href="#" class="btna1" id="eaxmtion" style="display: none">考试</a> -->
				<a href="#" class="btna2" id="entered" style="display: none">报名</a>
			</div>
			</div>
			<p class="p1">课程介绍</p>
			<div class="details">
<!-- 				<div  style="width:50%;float: left;"> -->
				<div>
					<p ><span>课程类型</span><span class="span3" id="courseType"></span></p>
					<p ><span>课程主题</span><span class="span3" id="courseName2"></span></p>
					<p ><span>主讲人</span><span class="span3" id="speaker"></span></p>
					<p ><span>适用平台</span><span class="span3" id="platform"></span></p>
					<p ><span>适用岗位</span><span class="span3" id="post"></span></p>
					<p ><span>课程时长</span><span class="span3" id="duration"></span></p>
				</div>
				<div id="f_Offline" >
					<p ><span>培训价格</span><span class="span3" id="price"></span></p>
					<p ><span>招生人数</span><span class="span3" id="peopleNum"></span></p>
					<p ><sapn>报名截止时间</sapn><span class="span3" id="closeTime"></span></p>
					<p ><span>负责人</span><span class="span3" id="header"></span></p>
					<p ><sapn>联系方式</sapn><span class="span3" id="phone"></span></p>
					<p ><span>城市</span><span class="span3" id="city"></span></p>
					<p ><sapn>地址</sapn><span class="span3" id="trainingAddress"></span></p>
				</div>
			</div>
			<div class="details" >
				<p class="p2">课程概述</p>
				<p class="p3" id="description"></p>
			</div>
			<c:if test="${type !=1}">
			<div class="details">
			<p class="p1">开课信息</p>
			<div class="form-group">
                      <div class="ibox-content">

                          <table id="trainScheduleTable"
                                 data-mobile-responsive="true"
                                 data-toggle="table"
                                 data-url="<%=basePath%>/etopTrainSchedule/getEtopTrainScheduleList.do?courseId=${id}"
                                 data-data-type="json"
                                 data-side-pagination="server"
                                 data-query-params="queryParams"
                                 data-striped="true"
                                  >
                              <thead>
                              <tr>
                                  <th data-field="state" data-checkbox="true"></th>
                                  <th data-field="id" data-align="center" data-visible="false">id</th>
                                  <th data-field="courseId" data-align="center" data-visible="false">课程id</th>
                                  <th data-field="startDate" data-align="center">开始时间</th>
                                  <th data-field="endDate" data-align="center">结束时间</th>
                                  <th data-field="title" data-align="center">主题</th>
                                  <th data-field="content" data-align="center">内容</th>
                                  <th data-field="teacher" data-align="center">主讲人</th>
                                  <th data-field="remark" data-align="center">备注</th>
                              </tr>
                              </thead>
                          </table>
                      </div>
                  </div>
                  
                   <p class="p1">讲师信息</p>
                  <div class="form-group">
                     <div class="ibox-content">

                         <table id="trainTeacherTable"
                                data-mobile-responsive="true"
                                data-toggle="table"
                                data-url="<%=basePath%>/etopTrainTeacher/getEtopTrainTeacherList.do?courseId=${id}"
                                data-data-type="json"
                                data-side-pagination="server"
                                data-query-params="queryParams"
                                data-striped="true"
                                 >
                             <thead>
                             <tr>
                                 <th data-field="state" data-checkbox="true"></th>
                                 <th data-field="id" data-align="center" data-visible="false">id</th>
                                 <th data-field="courseId" data-align="center" data-visible="false">课程id</th>
                                 <th data-field="name" data-align="center">姓名</th>
                                 <th data-field="profile" data-align="center">简介</th>
                             </tr>
                             </thead>
                         </table>
                     </div>
                 </div>
			</div>
			</c:if>
		</div>
		<jsp:include page="../footer.jsp" flush="true" />
		<script src="<%=basePath%>/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
		<script src="<%=basePath%>/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
		<script src="<%=basePath%>/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
		<script type="text/javascript">
		var collegeinfoId=GetQueryString("id");
		var listType=GetQueryString("type");
		var url="";
		$("#ids").show(collegeinfoId);
		$(document).ready(function(){
			if(listType==1){
				url="<%=basePath %>/etopOnlineTraining/getOnlineInformation.do";
				$("#eaxmtion").css("display","inline");
				$.post(url,{"id":collegeinfoId},function(data){
					$("#eaxmtion").attr("href","<%=basePath %>/webecollege/examination.do?titleId="+collegeinfoId);
					$("#courseName").append(data.courseName);
					$("#courseName2").append(data.courseName);
					$("#srcUrl").append("<embed src='"+data.playUrl+"' style='margin-left: 167px;' allowFullScreen='true' quality='high' width='820' height='615' align='middle' allowScriptAccess='always' type='application/x-shockwave-flash'></embed>");
					$("#description").append(data.description);
					$("#speaker").append(data.speaker);
					$("#platform").append(platformFormatter(data.platform));
					$("#post").append(postFormatter(data.post));
					$("#courseType").append(courseFormatter(data.courseType));
					$("#duration").append(data.duration);
					$("#testNum").append("考试人数："+data.testNum+"人");
					$("#avgcontentLevel").append(data.avgcontentLevel+"分");
					$("#avgteacherevel").append(data.avgteacherevel+"分");
					$("#avgplaySmooth").append(data.avgplaySmooth+"分");
					$("#avgvideoclarity").append(data.avgvideoclarity+"分");
				});
			}
			else if(listType==2){
				url="<%=basePath %>/etopOfflinePrograms/getOfflineProgramsInformation.do";
				
				$.post(url,{"id":collegeinfoId},function(data){
					if( data.courseStatus == 1 ){
						$("#entered").attr("href","javascript:");
						$("#entered").html("报名已截止");
						
					}
					else{
						
						$("#entered").attr("href","<%=basePath %>/webecollege/offlinereg.do?id="+data.id+"&titleName="+data.courseName+"&listType="+listType+"");
					}
// 					$("#courseStatus").append(data.courseStatus);
					
					$("#entered").css("display","inline");
					$("#courseName").append(data.courseName);
					$("#courseName2").append(data.courseName);
					$("#srcUrl").css("display","none");
					if(data.courseImg != null){
						$("#imgUrl").css("display","block");
						$("#imgUrl img").attr('src',"<%=basePath %>/"+data.courseImg);
					}
					
					$("#description").append(data.courseContent);
					$("#speaker").append(data.header);
					$("#platform").append(platformFormatter(data.platform));
					$("#courseType").append(courseFormatter(data.courseType));
					$("#testNum").append("报名人数："+data.joinPeopleNum+"人");
					$("#f_Offline").css("display","block");
					$("#f_Offline").css("display","block");
					$("#duration").append(data.trainTime);
					$("#price").append(data.price);
					$("#peopleNum").append(data.peopleNum);
					$("#closeTime").append(data.closeTime);
					$("#header").append(data.header);
					$("#phone").append(data.phone);
					$("#city").append(data.city);
					$("#trainingAddress").append(data.trainingAddress);
					
				});
			}
			else{
				url="<%=basePath %>/etopOfflineTraining/getOfflineTrainingInformation.do";
				
				$.post(url,{"id":collegeinfoId},function(data){
					if( data.courseStatus == 1 ){
						$("#entered").attr("href","javascript:");
						$("#entered").html("报名已截止");
						
					}
					else{
						
						$("#entered").attr("href","<%=basePath %>/webecollege/offlinereg.do?id="+data.id+"&titleName="+data.courseName+"&listType="+listType+"");
					}
					$("#entered").css("display","inline");
					$("#courseName").append(data.courseName);
					$("#courseName2").append(data.courseName);
					$("#srcUrl").css("display","none");
					if(data.courseImg != null){
						$("#imgUrl").css("display","block");
						$("#imgUrl img").attr('src',"<%=basePath %>/"+data.courseImg);
					}
					$("#description").append(data.courseContent);
					$("#speaker").append(data.header);
					$("#platform").append(platformFormatter(data.platform));
					$("#courseType").append(courseFormatter(data.courseType));
					$("#testNum").append("报名人数："+data.joinPeopleNum+"人");
					$("#f_Offline").css("display","block");
					$("#duration").append(data.trainTime);
					$("#price").append(data.price);
					$("#peopleNum").append(data.peopleNum);
					$("#closeTime").append(data.closeTime);
					$("#header").append(data.header);
					$("#phone").append(data.phone);
					$("#city").append(data.city);
					$("#trainingAddress").append(data.trainingAddress);
				});
			}
			
			
			
			
		})
		function GetQueryString(name)
			{
			     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			     var r = window.location.search.substr(1).match(reg);
			     if(r!=null)return  decodeURI(r[2]); return null;
			}
		
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
				title : "仅个人用户能参与考试！",
				type : "error",
				confirmButtonText : "确定",
				closeOnConfirm : true
			});
		}
		function platformFormatter(value) {
	   		switch (value) {

	   		case 1:
	   			return "淘宝";
	   		case 2:
	   			return "微信";
	   		case 3:
	   			return "京东";
	   		case 4:
	   			return "苏宁";
	   		case 5:
	   			return "跨境平台";
	   		case 6:
	   			return "其他平台";
	   		default:
	   			break;
	   		}
	   	}
		function postFormatter(value) {
	   		switch (value) {

	   		case 1:
	   			return "客户";
	   		case 2:
	   			return "美工";
	   		case 3:
	   			return "推广";
	   		case 4:
	   			return "运营";
	   		case 5:
	   			return "视觉设计";
	   		case 6:
	   			return "活动策划";
	   		case 7:
	   			return "企业高管";
	   		default:
	   			break;
	   		}
	   	}
		function courseFormatter(value) {
	   		switch (value) {

	   		case 1:
	   			return "高管研修";
	   		case 2:
	   			return "创业辅助";
	   		case 3:
	   			return "淘系美工";
	   		case 4:
	   			return "淘系运营";
	   		case 5:
	   			return "淘系客服";
	   		case 6:
	   			return "淘系推广";
	   		case 7:
	   			return "淘系无线";
	   		case 8:
	   			return "微商";
	   		case 9:
	   			return "京东系列";
	   		case 10:
	   			return "跨境系列";
	   		case 11:
	   			return "其他平台";
	   		case 12:
	   			return "其他类型";
	   		case 13:
	        	return "沙龙";
	        case 14:
	        	return "论坛";
	        case 15:
	        	return "会议";
	        case 16:
	        	return "文娱";
	        case 17:
	        	return "体育";
	        default:
	            return "其他类型";
	   		}
	   	}
		</script>
	</body>
</html>
