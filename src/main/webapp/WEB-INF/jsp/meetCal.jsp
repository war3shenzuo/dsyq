<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设施预约时间</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
<link href="<%=basePath %>/css/plugins/fullcalendar/fullcalendar.css" rel="stylesheet"/>
<link href="<%=basePath %>/css/plugins/fullcalendar/fullcalendar.print.css" rel="stylesheet"/>
<link href="<%=basePath %>/css/meetCal.css" rel="stylesheet"/>
<link href="<%=basePath%>/css/plugins/datapicker/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
<style type="text/css">
.wrap{ width:1170px; margin: 0 auto;}
</style>
</head>

<body>
    
	<jsp:include page="header.jsp" flush="true" />

<div class="wrap">
<p style="text-align: center;font-size:40px;margin-top:30px; margin-bottom: 20px;">设施预约</p>
<div class="row animated fadeInDown">
      <div class="col-sm-3">
         <div class="left">
               <p class="p2">当前园区：</p>
			  	<p class="p1">${park.parkName}</p>
			  	<p class="p2">类<span style="display: inline-block; width: 28px;;"></span>别<font color="red">*</font></p>
			  	<select id="facilityType" >	
					<option >请选择类别</option>
				        <c:forEach var="facilityType" items="${facilityType}" >
	                     	<option  value ="${facilityType.facilityType}">${facilityType.facilityType}</option>
	                    </c:forEach> 
				</select>
			  	<p class="p2">设施房间：<font color="red">*</font></p>
				  	<select id="room" onchange="getInfo();getPrice()">
						<option value="">请选择</option>
					</select>
				<p class="p2">价<span style="display: inline-block; width: 28px;;"></span>格：</p>
				<input class="form-control layer-date" id="facilityPrice" readonly="readonly"/>


				<p  class="p2">说<span style="display: inline-block; width: 28px;;"></span>明</p>
<!-- 				<input type="text" class="form-control layer-date" value="" id="descriptions" readonly="readonly"/> -->
				<textarea id="descriptions" rows="3" name="textWeb" cols="" class="form-control" readonly="readonly" style="width: 240px;"></textarea>
    			<p class="p2">开放时段</p>
    			<input type="text" style=" width:42%; display: inline-block; " class="form-control" value="" id="startTime" readonly="readonly"/> 至 <input type="text" style=" width:42%; display: inline-block;	" class="form-control" value="" id="endTime" readonly="readonly"/>
					
				<p class="p2">起始时间：<font color="red">*</font></p> 
				<input placeholder="开始日期" class="form-control layer-date" id="start"/>
				<p class="p2">结束时间：<font color="red">*</font></p>
			    <input placeholder="结束日期" class="form-control layer-date" id="end" />
			    
    			<p class="p2">时<span style="display: inline-block; width: 28px;"></span>长： </p>
				<input type="text" class="form-control layer-date" value="" id="number" readonly="readonly"/> 
				<p class="p2">合计金额：</p>
				<input type="text" class="form-control layer-date" value="" id="totalPrices" readonly="readonly"/>
				<p class="p2">申请人房间 ：<font color="red">*</font></p>
			    <select id="roomNum" class="form-control" style="width: 240px;height: 34px; margin:0 32px 0 0; text-indent: 10px;" >	
						    <option  value=''>请选择房间</option>
						        <c:forEach var="room" items="${room}">
                        			<option>${room.room}</option>
                        		</c:forEach> 
				</select>
				<p class="p2">申请人 ：</p>
			    <input  class="form-control layer-date" id="applicant" />
			    <p class="p2">联系方式：</p>
				<input class="form-control layer-date" id="applicantPhone"/>
				<p class="p2">部<span style="display: inline-block; width: 28px;"></span>门： </p>
				<input type="text" class="form-control layer-date" value="" id="applicantDepartment"/> 
				<p class="p2">职<span style="display: inline-block; width: 28px;"></span>位：</p>
				<input type="text" class="form-control layer-date" value="" id="applicantPosition" />
				<p class="p2">补充信息</p>
     			<textarea id="description" rows="3" name="textWeb" cols="40" class="form-control"></textarea> 
    			
	 <div style="color:dimgrey;margin-top:10px;margin-bottom:15px;font-size:17px;line-height: 35px">
		<!--<p>房间信息：</p>
		<p><span>面积</span><span>130</span><span>平方米</span></p>
		<p><span>容纳</span><span>200</span><span>人</span></p> 
		<p><span>设施</span><span>圆桌</span><span>空调</span><span>投影仪</span></p>-->
	</div> 
	<p style="text-align: ;margin-right:12px;"><a href="javascript:" onclick="check()" class="a1" id="a1" >预约</a></p>
  </div>
            </div>
            <div class="col-sm-9">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div id="calendar"></div>
                    </div>
                </div>
            </div>
        </div>
</div>
<jsp:include page="footer.jsp" flush="true" />
<script type="text/javascript" src="<%=basePath %>/css/plugins/meetCalPlugin/js/jquery-1.10.2.min.js"></script>
<script src="<%=basePath %>/js/plugins/fullcalendar/fullcalendar.min.js"></script>
<script src="<%=basePath %>/js/plugins/layer/laydate/laydate.js"></script>
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>/myjs/meet.js"></script>
<script src="<%=basePath %>/js/sweetalert.min.js"></script>
<script type="text/javascript">
	var basePath='<%=basePath%>';
</script>
<script type="text/javascript">

//开始时间选择
$('#start').datetimepicker({
	 minView: 1,
	 todayBtn: "linked",
	 format: 'yyyy-mm-dd hh:00:00',
	 startDate : new Date() 
	 //d, dd, D, DD, m, mm, M, MM, yy, yyyy
}).on('changeDate',function(e){  
    var startTime = e.date;  
    $('#end').datetimepicker('setStartDate',startTime);  
    clean();
});
//结束时间选择
$('#end').datetimepicker({
	 minView: 1,
	 todayBtn: "linked",
	 format: 'yyyy-mm-dd hh:00:00'
	 //d, dd, D, DD, m, mm, M, MM, yy, yyyy
}).on('changeDate',function(e){  
    var endTime = e.date;  
    $('#start').datetimepicker('setEndDate',endTime); 
    gradeTwo();
	compare();
//     check();
});

/* var start = {
	elem: "#start",
	format: "YYYY/MM/DD hh:00:00",
	min: laydate.now(),
	max: "2099-06-16 23:59:59",
	istime: true,
	istoday: false,
	choose: function(datas) {
		end.min = datas;
		end.start = datas
	}
};
var end = {
	elem: "#end",
	format: "YYYY/MM/DD hh:00:00",
	min: laydate.now(),
	max: "2099-06-16 23:59:59",
	istime: true,
	istoday: false,
	choose: function(datas) {
		start.max = datas;
		gradeChange();
	}
};*/




var b = new Date();
var c = b.getDate();
var a = b.getMonth();
var e = b.getFullYear();
$(document).ready(function() {
	laydate(start);
	laydate(end);
	$("#calendar").fullCalendar({
		header: {
			left: "prev,next",
			center: "title",
			right: "",
		},
		title:dataformat()
		,
		events: []
})
});

function dataformat(){
	var date = new Date();
	Y = date.getFullYear() + '年';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '月';
	D = date.getDate() + '日';
	h = date.getHours() + ':';
	m = date.getMinutes() + ':';
	s = date.getSeconds(); 
	return Y+M+D;
} 

</script>

</body>
</html>