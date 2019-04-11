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
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>查找房间</title>
<meta name="keywords" content="">
<meta name="description" content="">
<jsp:include page="../shared/css.jsp" />
</head>
<input type="hidden" id="parkId">
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">

		<!-- Panel Other -->
		<div class="ibox float-e-margins">

			<div class="ibox-content">
				<div class="row row-lg">
				 <div class="row">
                    <div class="col-sm-12 ">
                    <div class="col-sm-2">
                       <div class="panel panel-default">
						  <div class="panel-heading">
								<h3 class="panel-title">园区栏</h3>
						  </div>
						  <div class="panel-body">
	                        <div id="parktree"></div>
		                  </div>
			           </div>
                    </div>
                    <div class="col-sm-10">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-md-2">
	                           	<label>房间号</label>
                             	<input type="text" class=" form-control" placeholder="请输入房间号" id="roomNum"> 
	                         </div>
                             <div class="col-md-2">
	                           	<label>状态</label>
	                            <select class="form-control m-b" id="roomStatus" name="account">
	                             <option value="">全部</option>
	                             <option value="0">待租（无人）</option>
	                             <option value="1">已出租</option>
	                             <option value="2">预留中</option>
	                             <option value="3">待租（有人）</option>
	                        	</select>
	                         </div>
	                         <div class="col-md-2">
	                           	<label>朝向</label>
	                            <select class="col-md-2 form-control m-b" name="account" id="orientation">
		                            <option value="">全部</option>
                                    <option value="东" >东</option>
									<option value="南" >南</option>
									<option value="西" >西</option>
									<option value="北" >北</option>
									<option value="东南" >东南</option>
									<option value="东北" >东北</option>
									<option value="西南" >西南</option>
									<option value="西北" >西北</option>
                                </select>
	                         </div>
							<%--  <div class="col-md-2">
								<!--<label>确认搜索</label>-->
								<button class="btn btn-primary "
									onclick="refreshT('<%=basePath%>/floor/getRoomList2.do')"
									type="button" style="margin-top: 23px;">搜索</button>
							</div> --%>
						</div>
					</div>
					<div class="hr-line-dashed" style="clear: both;"></div>
					<div class="col-sm-12">
						<div class="row">
							<div class="col-md-1" style="position: relative">

								<label>建筑面积</label> <input type="text"
									class="form-control" id="buildArea1" aria-required="true">
								<div style="position: absolute; top: 30px; right: 0px;">-</div>
							</div>
							<div class="col-sm-1" style="position: relative">
								<label>&nbsp;</label> <input type="text"
									class="form-control" id="buildArea2" aria-required="true">
							</div>
							
							<div class="col-md-1" style="position: relative">

								<label>层高</label> <input type="text"
									class="form-control" id="layerHigh1" aria-required="true">
								<div style="position: absolute; top: 30px; right: 0px;">-</div>
							</div>
							<div class="col-sm-1" style="position: relative">
								<label>&nbsp;</label> <input type="text"
									class="form-control" id="layerHigh2" aria-required="true">
							</div>
							<div class="col-md-1" style="position: relative">
								<label>日单价</label> <input type="text"
									class="form-control" id="dayPrice1" aria-required="true">
								<div style="position: absolute; top: 30px; right: 0px;">-</div>
							</div>
							<div class="col-sm-1" style="position: relative">
								<label>&nbsp;</label> <input type="text"
									class="form-control" id="dayPrice2" aria-required="true">
							</div>
							<div class="col-md-1" style="position: relative">
								<label>月单价</label> <input type="text"
									class="form-control" id="monthPrice1" aria-required="true">
								<div style="position: absolute; top: 30px; right: 0px;">-</div>
							</div>
							<div class="col-sm-1" style="position: relative">
								<label>&nbsp;</label> <input type="text"
									class="form-control" id="monthPrice2" aria-required="true">
							</div>	
                           
							<div class="col-md-2">
								<!--<label>确认搜索</label>-->
								<button class="btn btn-primary "
									onclick="refreshT('<%=basePath%>/floor/getRoomList2.do')"
									type="button" style="margin-top: 23px;">搜索</button>
							</div>
							
						</div>
					</div>
					<div class="hr-line-dashed" style="clear: both;"></div>
					<div class="col-sm-12">
						<!-- Example Events -->
						<div class="example-wrap">
                           	<table id="table1"
                          	   data-mobile-responsive="true"
				               data-toggle="table"
				               data-url=""
				               data-data-type="json"
				               data-side-pagination="server"
				               data-pagination="true"
				               data-query-params = "queryParams"
				               data-page-list="[5, 10, 20, 50, 100, 200]"
				               data-striped="true"
				              >
					            <thead>
					            <tr>
					                <th data-field="id" data-align="center" data-visible="false">编号</th>
					                <th data-field="floorName" data-align="center">楼</th>
					                <th data-field="storeyName" data-align="center">层</th>
					                <th data-field="areaName" data-align="center">区</th>
					                <th data-field="roomNum" data-align="center">房间号</th>
					                <th data-field="floorStatus" data-align="center" data-formatter="formatterRoomType">房间状态</th>
					                <th data-field="buildArea" data-align="center">建筑面积</th>
					                <th data-field="monthPrice" data-align="center" >月单价</th>
					                <th data-field="dayPrice" data-align="center" >日单价</th>
					                <th data-align="center" data-formatter='formatterFun2' >操作</th>
					            </tr>
					            </thead>
			       		   </table>
						</div>
						<!-- End Example Events -->
					</div>
					</div>
					</div>
					</div>
				</div>
			</div>
		</div>

		<!-- End Panel Other -->
	</div>
	<jsp:include page="../shared/js.jsp" />
	<script type="text/javascript">
		/*查询条件*/
		function queryParams(params){
			params.roomNum = $("#roomNum").val();
			params.floorStatus = $("#roomStatus").val();
			params.orientation=$("#orientation").val();
			params.buildArea1=$("#buildArea1").val();
			params.buildArea2=$("#buildArea2").val();
			params.layerHigh1=$("#layerHigh1").val();
			params.layerHigh2=$("#layerHigh2").val();
			params.dayPrice1=$("#dayPrice1").val();
			params.dayPrice2=$("#dayPrice2").val();
			params.monthPrice1=$("#monthPrice1").val();
			params.monthPrice2=$("#monthPrice2").val();
			params.activated='1';
			return params
		}
		function refreshT(url){
			url=url+"?parkId="+$("#parkId").val();
			table.bootstrapTable('refresh',{url:url});
		}
		
		
		function formatterFun2(value,row,index){
			var id=$("#id").val();
			var parkId=$("#parkId").val();
			var infoUrl=basePath+'/floor/getRoomInfo.do?roomId='+row.id+"&userType="+userType;
			var refreshUrl=basePath+'/floor/getRoomList2.do?parkId='+parkId;
			var showInfo="openAddRoomPage('房间详情','80%','95%','"+infoUrl+"','"+refreshUrl+"')";
			var deleted="deleteRoom('"+row.id+"')";
			return '<button class="btn btn-info" onclick="'+
				   showInfo+
			       '"   type="button" >详情</button> '+
			       
				   '&nbsp;&nbsp; '+
				   '<button class="btn btn-info" onclick="'+
				   deleted+
				   '"   type="button" >删除</button>';
		}

    </script>
	<script type="text/javascript">
    	var basePath='<%=basePath%>'; 
    	var userType='${userType}';
	</script>
    <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/floor.js"></script>
</body>
</html>