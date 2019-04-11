<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>报表管理-系统设置</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>

	<style>
		.fixed-table-container thead th .both {
			background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABMAAAATCAQAAADYWf5HAAAAkElEQVQoz7X QMQ5AQBCF4dWQSJxC5wwax1Cq1e7BAdxD5SL+Tq/QCM1oNiJidwox0355mXnG/DrEtIQ6azioNZQxI0ykPhTQIwhCR+BmBYtlK7kLJYwWCcJA9M4qdrZrd8pPjZWPtOqdRQy320YSV17OatFC4euts6z39GYMKRPCTKY9UnPQ6P+GtMRfGtPnBCiqhAeJPmkqAAAAAElFTkSuQmCC');
		}

		.fixed-table-container thead th .asc {
			background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABMAAAATCAYAAAByUDbMAAAAZ0lEQVQ4y2NgGLKgquEuFxBPAGI2ahhWCsS/gDibUoO0gPgxEP8H4ttArEyuQYxAPBdqEAxPBImTY5gjEL9DM+wTENuQahAvEO9DMwiGdwAxOymGJQLxTyD+jgWDxCMZRsEoGAVoAADeemwtPcZI2wAAAABJRU5ErkJggg==');
		}

		.fixed-table-container thead th .desc {
			background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABMAAAATCAYAAAByUDbMAAAAZUlEQVQ4y2NgGAWjYBSggaqGu5FA/BOIv2PBIPFEUgxjB+IdQPwfC94HxLykus4GiD+hGfQOiB3J8SojEE9EM2wuSJzcsFMG4ttQgx4DsRalkZENxL+AuJQaMcsGxBOAmGvopk8AVz1sLZgg0bsAAAAASUVORK5CYII= ');
		}
	</style>

</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            
            <div class="ibox-content">
                <div class="row row-lg">
					<div class="ibox-content"  style="padding-bottom: 0;border:none;">
						<div class="row">
							<input id="userType" class="hidden" value="${userType}">
							<div>
								<c:choose>
									<c:when test="${userType eq 4}">
										<div class="col-sm-2">
											<label>选择园区</label>
											<select name="target" class="js-example-tags form-control" id="target">
											<option value="0">全部</option>
											<c:forEach items="${parks}" var="park">
												<option value="${park.id}">${park.parkName}</option>
											</c:forEach>
										</select>
										</div>
									</c:when>
									<c:otherwise>
										<div class="col-sm-2">
											<label>选择园区</label>
											<input type="text" class="form-control" id="target1" name="target1" value="${parks}" readonly>
											<input type="text" class="form-control hidden" id="parkId" name="parkId" value="${parkId}">
										</div>
									</c:otherwise>
								</c:choose>
							</div>

							<div class="col-md-2">
								<label>选择报表类型</label>
								<select class="form-control" id="type" name="type">
									<option value="1">园区动态</option>
									<option value="2">出租率</option>
									<option value="3">欠款统计</option>
									<option value="4">费金回收</option>
									<option value="5">收支统计</option>
									<option value="6">服务统计</option>
								</select>
							</div>

							<div class="col-md-2" id="seleId">
								<button class="btn btn-primary"
										onclick="getList()"
										type="button" style="margin-top: 23px;;">搜索
								</button>

								<button class="btn btn-primary"
										onclick="AutoExcel()"
										type="button" style="margin-top: 23px;;">导出
								</button>
							</div>

							<!--服务类型-->
							<div class="col-md-2" id="serviceDiv"></div>
							<!--服务日期选择-->
							<div class="col-md-2" id="dateDiv"></div>
							<!--期数-->
							<div class="col-md-2" id="periodsDiv"></div>
							<!--期数搜索-->
							<div class="col-md-2" id="periodsSelect"></div>
							<!--搜索-->
							<div class="col-md-2" id="selectDiv"></div>
							<!--出账日期-->
							<div class="col-md-2" id="thediv"></div>
							<!--出账日期-->
							<div class="col-md-2" id="billdiv"></div>
							<!--搜索重置-->
							<div class="col-md-2" id="divId"></div>
						</div>
					</div>

					<div class="hr-line-dashed" style="clear: both;"></div>

					<div class="col-sm-12">

						<div class="example-wrap" id="tab-1">

							<table id="tableParkDynamic"
								   data-mobile-responsive="true"
								   data-toggle="table"
								   data-url="<%=basePath%>/etopReport/getParkDynamicList.do"
								   data-data-type="json"
								   data-side-pagination="server"
								   data-query-params = "queryReceiver"
								   data-striped="true">
								<thead>
								<tr>
									<th data-field="parkName" data-align="center">园区名称</th>
									<th data-field="userNum" data-align="center">管理账户数</th>
									<th data-field="companyNum" data-align="center">园区企业数</th>
									<th data-field="contractNum" data-align="center">合同数</th>
									<th data-field="serviceNum" data-align="center">服务申请数</th>
									<th data-field="serviceCompleteNum" data-align="center">服务完成数</th>
									<th data-field="roomsNum" data-align="center">房间数量</th>
								</tr>
								</thead>
							</table>
						</div>

						<div class="example-wrap hidden" id="tab-2">

							<table id="tableRents"></table>
						</div>

						<div class="example-wrap hidden" id="tab-3">
							<table id="tableBalanceStatistics"></table>
						</div>

						<div class="example-wrap hidden" id="tab-4">
							<table id="tableContractReport"></table>
						</div>

						<div class="example-wrap hidden" id="tab-5">

							<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group"
								 <c:if test="${userType == 4}">style="display: none;"</c:if>>
								<a class="btn btn-danger"
								   onclick="ProfitLossPage('新建明细','65%','65%','<%=basePath%>/etopReport/addProfitLossPage.do','<%=basePath%>/etopReport/getProfitLossList.do')">
									<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
									<span>导入数据</span>
								</a>
							</div>

							<table id="tableProfitLoss"></table>
						</div>

						<div class="example-wrap hidden" id="tab-6">
							<table id="serviceReport"></table>
						</div>
	                    </div>
	                    </div>
	                </div>
                </div>
            </div>
        </div>
    
        <!-- End Panel Other -->
    </div>
    
	<jsp:include page="../shared/js.jsp"/>
	<script type="text/javascript" src="<%=basePath %>/myjs/datetimepicker_zh-CN.js"></script>
	<script src="<%=basePath%>/myjs/etopProfitLoss.js"></script>
    <script type="text/javascript">
    	var basePath='<%=basePath%>';

		function AutoExcel() {
			var type = $("#type").val();
			var parkId = $("#parkId").val();
			if($("#type").val() == 1){
				location.href = '<%=basePath%>/exportUtil/createXls.do?parkId=' + parkId + '&type=' + type;
			}else if($("#type").val() == 2){
				location.href = '<%=basePath%>/exportUtil/createXls.do?parkId=' + parkId + '&type=' + type;
			}else if($("#type").val() == 3){
				var start = $("#start").val();
				var end = $("#end").val();
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				location.href = '<%=basePath%>/exportUtil/createXls.do?parkId=' + parkId + '&type=' + type + '&start=' + start + '&end=' + end + '&startTime=' + startTime + '&endTime=' + endTime;
			}else if($("#type").val() == 4){
				var start = $("#start").val();
				var end = $("#end").val();
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				location.href = '<%=basePath%>/exportUtil/createXls.do?parkId=' + parkId + '&type=' + type + '&start=' + start + '&end=' + end + '&startTime=' + startTime + '&endTime=' + endTime;
			}else if($("#type").val() == 5){
				var periods = $("#periods").val();
				location.href = '<%=basePath%>/exportUtil/createXls.do?parkId=' + parkId + '&type=' + type + '&periods=' + periods ;
			}else if($("#type").val() == 6){
				var serviceype = $("#serviceype").val();
				var start = $("#start").val();
				var end = $("#end").val();
				location.href = '<%=basePath%>/exportUtil/createXls.do?parkId=' + parkId + '&type=' + type + '&serviceype=' + serviceype + '&start=' + start + '&end=' + end;
			}

		}

		/*查询条件*/
		function queryReceiver(){
			var params = {
				"parkId" : $("#parkId").val(),
				"type" : $("#type").val(),
				"periods" : $("#periods").val(),
				"serviceype" : $("#serviceype").val(),
				"start" : $("#start").val(),
				"end" : $("#end").val(),
				"startTime" : $("#startTime").val(),
				"endTime" : $("#endTime").val()
			}
			if($("#userType").val() == 4){
				params.parkId= $("#target").val();
			}
			return params
		}

		function ProfitLossPage(title,height,width,addUrl,refreshUrl){
			layer.open({
				type: 2,
				title: title,
				closeBtn: "1",
				shadeClose: true,
				shade: [0],
				shift: 2,
				area: [height, width],
				content: addUrl,
				end: function(){
					if(refreshUrl!=""){
						getList();
					}
					if (typeof(tree) != "undefined") {
						tree.refresh();
					}
				}
			});
		}

		$("#type").on("change", function(){
			if($(this).val() == 3 || $(this).val() == 4){
				$("#seleId").hide();
				$("#dateDiv").hide();
				$("#periodsDiv").hide();
				$("#periodsSelect").hide();
				$("#serviceDiv").hide();
				$("#selectDiv").hide();
				$("#thediv").show();
				$("#billdiv").show();
				$("#divId").show();
				$("#thediv").html("<label id='labelId'>出账日期</label>" +
						"<div class='input-daterange input-group date' id='reportDiv' > " +
						"<input type='text' class='input-sm form-control check-date' name='start' id='start' placeholder='开始日期'> " +
						"<span class='input-group-addon'>到</span> " +
						"<input type='text' class='input-sm form-control check-date' name='end' id='end' placeholder='结束日期'> </div>");
				$("#billdiv").html("<label id='billId'>缴费期限</label>" +
						"<div class='input-daterange input-group date' id='billDiv' > " +
						"<input type='text' class='input-sm form-control check-date' name='startTime' id='startTime' placeholder='开始日期'> " +
						"<span class='input-group-addon'>到</span> " +
						"<input type='text' class='input-sm form-control check-date' name='endTime' id='endTime' placeholder='结束日期'> </div>");
				$("#divId").html("<button class='btn btn-primary' id='selectId' onclick='getList()' type='button' style='margin-top: 23px;;'>搜索</button> " +
								"<button class='btn btn-primary' id='resetId' onclick='resetCondition()' type='button' style='margin-top: 23px;;'>重置</button> " +
								"<button class='btn btn-primary' id='outPutId' onclick='AutoExcel()' type='button' style='margin-top: 23px;;'>导出</button>");
				$('.date').datepicker(
						{todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,autoclose:!0}
				)
			}else if($(this).val() == 5){

				var mytime = new Date().getFullYear() + "-" + new Date().getMonth();

				$("#seleId").hide();
				$("#thediv").hide();
				$("#billdiv").hide();
				$("#selectId").hide();
				$("#resetId").hide();
				$("#outPutId").hide();
				$("#dateDiv").hide();
				$("#selectDiv").hide();
				$("#serviceDiv").hide();
				$("#periodsDiv").show();
				$("#periodsSelect").show();
				$("#periodsDiv").html("<label id='periodsId'>期数</label>" +
						"<div class='input-group date' id='periodsDate' > " +
						"<span class='input-group-addon'><i class='fa fa-calendar'></i></span> " +
						"<input type='text' class='form_datetime form-control' id='periods' placeholder='请选择日期'></div>");

				$('.form_datetime').datetimepicker({
					format: 'yyyy-mm',
					autoclose: true,
					todayBtn: true,
					startView: 'year',
					minView:'year',
					maxView:'decade',
					language: 'zh-CN'
				});

				$('.form_datetime').val(mytime);

				$("#periodsSelect").html("<button class='btn btn-primary' id='periodId' onclick='getList()' type='button' style='margin-top: 23px;;'>搜索</button> " +
						"<button class='btn btn-primary' id='outId' onclick='AutoExcel()' type='button' style='margin-top: 23px;;'>导出</button>");

			}else if($(this).val() == 6){
				$("#seleId").hide();
				$("#thediv").hide();
				$("#billdiv").hide();
				$("#selectId").hide();
				$("#resetId").hide();
				$("#outPutId").hide();
				$("#periodId").hide();
				$("#outId").hide();
				$("#periodsDiv").hide();
				$("#periodsSelect").hide();
				$("#serviceDiv").show();
				$("#selectDiv").show();
				$("#dateDiv").show();
				$("#serviceDiv").html(" <label>选择服务类型</label>" +
						"<select class='form-control' id='serviceype' name='serviceype'> " +
								"<option value='GGCG'>采购服务</option> " +
								"<option value='WYBX'>物业保修</option> " +
								"<option value='SWFW'>商务服务</option> " +
								"<option value='YYFW'>设施预约</option> " +
								"<option value='RYDZ'>人员代招</option> " +
						"</select>");
				$("#dateDiv").html("<label id='htmlId'>日期</label>" +
						"<div class='input-daterange input-group date' id='serviceDate' > " +
						"<input type='text' class='input-sm form-control check-date' name='start' id='start' placeholder='开始日期'> " +
						"<span class='input-group-addon'>到</span> " +
						"<input type='text' class='input-sm form-control check-date' name='end' id='end' placeholder='结束日期'> </div>");
				$('.date').datepicker(
						{todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,autoclose:!0}
				)
				$("#selectDiv").html("<button class='btn btn-primary' id='serviceId' onclick='getList()' type='button' style='margin-top: 23px;;'>搜索</button> " +
						"<button class='btn btn-primary' id='serviceReset' onclick='resetCondition()' type='button' style='margin-top: 23px;;'>重置</button> " +
						"<button class='btn btn-primary' id='outServiceId' onclick='AutoExcel()' type='button' style='margin-top: 23px;;'>导出</button>");
			}else{
				$("#reportDiv").hide();
				$("#billDiv").hide();
				$("#serviceDiv").hide();
				$("#selectId").hide();
				$("#typeId").hide();
				$("#htmlId").hide();
				$("#serviceId").hide();
				$("#serviceReset").hide();
				$("#outServiceId").hide();
				$("#billId").hide();
				$("#labelId").hide();
				$("#serviceDate").hide();
				$("#resetId").hide();
				$("#outPutId").hide();
				$("#periodsDiv").hide();
				$("#periodsId").hide();
				$("#periodId").hide();
				$("#outId").hide();
				$("#periodsDate").hide();
				//$("#periodReset").hide();
				$("#seleId").show();
			}
		})

		var tableParkDynamic = $('#tableParkDynamic');
		var tableRents = $('#tableRents');
		var tableBalanceStatistics = $('#tableBalanceStatistics')
		var tableContractReport = $('#tableContractReport')
		var serviceReport = $('#serviceReport')
		var tableProfitLoss = $('#tableProfitLoss')
		function tableRefresh(dataUrl){
			tableParkDynamic.bootstrapTable('refresh',{url:dataUrl});
		}
		function tableRefresh(dataUrl){
			tableRents.bootstrapTable('refresh',{url:dataUrl});
		}
		function tableRefresh(dataUrl){
			tableBalanceStatistics.bootstrapTable('refresh',{url:dataUrl});
		}
		function tableRefresh(dataUrl){
			tableContractReport.bootstrapTable('refresh',{url:dataUrl});
		}
		function tableRefresh(dataUrl){
			tableProfitLoss.bootstrapTable('refresh',{url:dataUrl});
		}
		function tableRefresh(dataUrl){
			serviceReport.bootstrapTable('refresh',{url:dataUrl});
		}

		function resetCondition(){
			$("#yearsDate").val("");
			$("#start").val("");
			$("#end").val("");
			$("#startTime").val("");
			$("#endTime").val("");
			$("#target").val("0");
		}


		function getList(){
			if($("#type").val() == 1){
				tableParkDynamic.bootstrapTable('refresh',{url:'<%=basePath%>/etopReport/getParkDynamicList.do'});
				$("#tab-2").addClass("hidden");
				$("#tab-3").addClass("hidden");
				$("#tab-4").addClass("hidden");
				$("#tab-5").addClass("hidden");
				$("#tab-6").addClass("hidden");
				$("#tab-1").removeClass("hidden");
			}else if($("#type").val() == 2){
				var params = queryReceiver();
				$('#tab-2 .bootstrap-table').remove();
				$('#tab-2').append('<table id="tableRents"></table>');
				$.post('<%=basePath%>/etopReport/getRentsList.do',params,function(data){
					var columns=[{field:"parkName",title:"园区",align:"center"},
						{field:"floor",title:"楼号",align:"center"},
						{field:"storey",title:"楼层",align:"center"},
						{field:"area",title:"分区",align:"center"},
						{field:"buildArea",title:"建筑面积",align:"center",sortable: true},
						{field:"userArea",title:"使用面积",align:"center",sortable: true},
						{field:"rentArea",title:"出租面积",align:"center",sortable: true},
						{field:"vacantArea",title:"空置面积",align:"center",sortable: true},
						{field:"roomRate",title:"得房率",align:"center",sortable: true},
						{field:"rents",title:"出租率",align:"center",sortable: true},
						{field:"vacancyRate",title:"空置率",align:"center",sortable: true}]

					var rows=[];
					var report = data.rows; //获取返回值中list对象

					for(var i=0;i<report.length;i++){
						var row = {}    //创建行
						row.parkName = report[i].parkName,
						row.floor = report[i].floor,
						row.storey = report[i].storey,
						row.area = report[i].area,
						row.buildArea = report[i].buildArea,
						row.userArea = report[i].userArea,
						row.rentArea = report[i].rentArea,
						row.vacantArea = report[i].vacantArea,
						row.roomRate = report[i].roomRate,
						row.rents = report[i].rents,
						row.vacancyRate = report[i].vacancyRate,
						rows[i] = row;
					}

					$('#tableRents').bootstrapTable({
						columns:columns,
						data: rows
					});

				});
				$("#tab-1").addClass("hidden");
				$("#tab-3").addClass("hidden");
				$("#tab-4").addClass("hidden");
				$("#tab-5").addClass("hidden");
				$("#tab-6").addClass("hidden");
				$("#tab-2").removeClass("hidden");
			}else if($("#type").val() == 3){

				var params = queryReceiver();
				$('#tab-3 .bootstrap-table').remove();
				$('#tab-3').append('<table id="tableBalanceStatistics"></table>');

				$.post('<%=basePath%>/etopReport/getBalanceStaticList.do',params,function(data){
					var columns=[{field:"parkName",title:"园区",align:"center"},
						{field:"companyName",title:"客户",align:"center"},
						{field:"leaseAmount",title:"租赁合同",align:"center",sortable: true},
						{field:"serviceAmount",title:"服务合同",align:"center",sortable: true},
						{field:"energyAmount",title:"能源合同",align:"center",sortable: true},
						{field:"propertyAmount",title:"物业合同",align:"center",sortable: true},
						{field:"parkServiceAmount",title:"园区服务",align:"center",sortable: true},
						{field:"otherAmount",title:"其他",align:"center",sortable: true},
						{field:"overdueFine",title:"滞纳金",align:"center",sortable: true},
						{field:"amount",title:"合计",align:"center",sortable: true},
						{field:"amountPaid",title:"实收",align:"center",sortable: true},
						{field:"arrears",title:"欠款合计",align:"center",sortable: true}]

					var rows=[];
					var report = data.rows; //获取返回值中list对象

					for(var i=0;i<report.length;i++){
						var row = {}    //创建行
						row.parkName = report[i].parkName,
						row.companyName = report[i].companyName,
						row.leaseAmount = report[i].leaseAmount,
						row.block = report[i].block,
						row.leaseAmount = report[i].leaseAmount,
						row.serviceAmount = report[i].serviceAmount,
						row.energyAmount = report[i].energyAmount,
						row.propertyAmount = report[i].propertyAmount,
						row.parkServiceAmount = report[i].parkServiceAmount,
						row.otherAmount = report[i].otherAmount,
						row.overdueFine = report[i].overdueFine,
						row.amountPaid = report[i].amountPaid,
						row.amount = report[i].amount,
						row.arrears = report[i].arrears,
						rows[i] = row;
					}

					$('#tableBalanceStatistics').bootstrapTable({
						columns:columns,
						data: rows
					});

				});

				//tableBalanceStatistics.bootstrapTable('refresh',{url:'<%=basePath%>/etopReport/getBalanceStaticList.do'});
				$("#tab-1").addClass("hidden");
				$("#tab-2").addClass("hidden");
				$("#tab-4").addClass("hidden");
				$("#tab-5").addClass("hidden");
				$("#tab-6").addClass("hidden");
				$("#tab-3").removeClass("hidden");
			}else if($("#type").val() == 4){

				var params = queryReceiver();
				$('#tab-4 .bootstrap-table').remove();
				$('#tab-4').append('<table id="tableContractReport"></table>');
				$.post('<%=basePath%>/etopReport/getFaiginList.do',params,function(data){
					var columns=[{field:"parkName",title:"园区",align:"center"},
						{field:"building",title:"楼号",align:"center"},
						{field:"floor",title:"楼层",align:"center"},
						{field:"block",title:"分区",align:"center"},
						{field:"leaseAmount",title:"租赁合同",align:"center",sortable: true},
						{field:"serviceAmount",title:"服务合同",align:"center",sortable: true},
						{field:"energyAmount",title:"能源合同",align:"center",sortable: true},
						{field:"propertyAmount",title:"物业合同",align:"center",sortable: true},
						{field:"parkServiceAmount",title:"园区服务",align:"center",sortable: true},
						{field:"otherAmount",title:"其他",align:"center",sortable: true},
						{field:"overdueFine",title:"滞纳金",align:"center",sortable: true},
// 						{field:"accountRemission",title:"本金减免",align:"center",sortable: true},
// 						{field:"overdueRemission",title:"滞纳金减免",align:"center",sortable: true},
						{field:"amount",title:"合计",align:"center",sortable: true},
						{field:"amountPaid",title:"实收",align:"center",sortable: true},
						{field:"arrearsAmount",title:"欠款合计",align:"center",sortable: true},
						{field:"recoveryRate",title:"回收率",align:"center",sortable: true}]

					var rows=[];
					var report = data.rows; //获取返回值中list对象

					for(var i=0;i<report.length;i++){
						var row = {}    //创建行
						row.parkName = report[i].parkName,
						row.building = report[i].building,
						row.floor = report[i].floor,
						row.block = report[i].block,
						row.leaseAmount = report[i].leaseAmount,
						row.serviceAmount = report[i].serviceAmount,
						row.energyAmount = report[i].energyAmount,
						row.propertyAmount = report[i].propertyAmount,
						row.parkServiceAmount = report[i].parkServiceAmount,
						row.otherAmount = report[i].otherAmount,
						row.overdueFine = report[i].overdueFine,
						row.accountRemission = report[i].accountRemission,
						row.overdueRemission = report[i].overdueRemission,
						row.amount = report[i].amount,
						row.amountPaid = report[i].amountPaid,
						row.arrearsAmount = report[i].arrearsAmount,
						row.recoveryRate = report[i].recoveryRate,
						rows[i] = row;
					}

					$('#tableContractReport').bootstrapTable({
						columns:columns,
						data: rows
					});

				});
				//tableContractReport.bootstrapTable('refresh',{url:'<%=basePath%>/etopReport/getFaiginList.do'});
				$("#tab-1").addClass("hidden");
				$("#tab-2").addClass("hidden");
				$("#tab-3").addClass("hidden");
				$("#tab-5").addClass("hidden");
				$("#tab-6").addClass("hidden");
				$("#tab-4").removeClass("hidden");
			}else if($("#type").val() == 5){
				/* if($('#target').val() == 0){
					swal({
						title: "请选择园区!",
						timer: 1000,
						showConfirmButton: false
					});
					return;
				} */
				var params = queryReceiver();
				$('#tab-5 .bootstrap-table').remove();
				$('#tab-5').append('<table id="tableProfitLoss"></table>');
				$.post('<%=basePath%>/etopReport/getProfitLossList.do',params,function(data){
					var columns=[{field:"items",title:"大项",align:"center"},
						{field:"fine",title:"细项",align:"center"},
						{field:"currentLimit",title:"本期额度",align:"center"},
						{field:"lastCurrent",title:"去年同期",align:"center"},
						{field:"previousLimit",title:"上期额度",align:"center",sortable: true},
						{field:"yearGrowth",title:"同比增长",align:"center",sortable: true},
						{field:"momGrowth",title:"环比增长",align:"center",sortable: true},
						{field:"yearCumulative",title:"年累计",align:"center",sortable: true},
						{field:"lastyearPeriod",title:"去年同期",align:"center",sortable: true},
						{field:"lastyearGrowth",title:"同比增长",align:"center",sortable: true},
						{field:"backTotal",title:"倒推12月合计",align:"center",sortable: true}]

					var rows=[];
					var report = data.rows; //获取返回值中list对象

					for(var i=0;i<report.length;i++){
						var row = {}    //创建行
						row.items = dataFormat(report[i].items),
						row.fine = formatterType(report[i].items, report[i].fine),
						row.currentLimit = report[i].currentLimit,
						row.lastCurrent = report[i].lastCurrent,
						row.previousLimit = report[i].previousLimit,
						row.yearGrowth = report[i].yearGrowth,
						row.momGrowth = report[i].momGrowth,
						row.yearCumulative = report[i].yearCumulative,
						row.lastyearPeriod = report[i].lastyearPeriod,
						row.lastyearGrowth = report[i].lastyearGrowth,
						row.backTotal = report[i].backTotal,
						rows[i] = row;
					}

					$('#tableProfitLoss').bootstrapTable({
						columns:columns,
						data: rows
					});
				});
					//tableProfitLoss.bootstrapTable('refresh',{url:'<%=basePath%>/etopReport/getProfitLossList.do'});
					$("#tab-1").addClass("hidden");
					$("#tab-2").addClass("hidden");
					$("#tab-3").addClass("hidden");
					$("#tab-4").addClass("hidden");
					$("#tab-6").addClass("hidden");
					$("#tab-5").removeClass("hidden");
			}else if($("#type").val() == 6){

				var params = queryReceiver();
				$('#tab-6 .bootstrap-table').remove();
				$('#tab-6').append('<table id="serviceReport"></table>');
				$.post('<%=basePath%>/etopReport/getServiceList.do',params,function(data){
					var columns=[{field:"parkName",title:"园区",align:"center"},
						{field:"building",title:"楼号",align:"center"},
						{field:"storey",title:"楼层",align:"center"},
						{field:"zoneNo",title:"分区",align:"center"},
						{field:"applicationsNum",title:"申请数量",align:"center",sortable: true},
						{field:"revocationNum",title:"已撤销",align:"center",sortable: true},
						{field:"approvalNum",title:"已审批",align:"center",sortable: true},
						{field:"refuseNum",title:"已拒绝",align:"center",sortable: true},
						{field:"workersNum",title:"已派工",align:"center",sortable: true},
						{field:"completedNum",title:"完工",align:"center",sortable: true},
						{field:"confirmCompletedNum",title:"确认完工",align:"center",sortable: true},
						{field:"approvalProportion",title:"审批比例",align:"center",sortable: true},
						{field:"workersProportion",title:"派工比例",align:"center",sortable: true},
						{field:"completedProportion",title:"完工比例",align:"center",sortable: true}]

					var rows=[];
					var report = data.rows; //获取返回值中list对象

					for(var i=0;i<report.length;i++){
						var row = {}    //创建行
						row.parkName = report[i].parkName,
						row.building = report[i].building,
						row.storey = report[i].storey,
						row.zoneNo = report[i].zoneNo,
						row.applicationsNum = report[i].applicationsNum,
						row.revocationNum = report[i].revocationNum,
						row.approvalNum = report[i].approvalNum,
						row.refuseNum = report[i].refuseNum,
						row.workersNum = report[i].workersNum,
						row.completedNum = report[i].completedNum,
						row.confirmCompletedNum = report[i].confirmCompletedNum,
						row.approvalProportion = report[i].approvalProportion,
						row.workersProportion = report[i].workersProportion,
						row.completedProportion = report[i].completedProportion,
						rows[i] = row;
					}

					$('#serviceReport').bootstrapTable({
						columns:columns,
						data: rows
					});

				});

				//serviceReport.bootstrapTable('refresh',{url:'<%=basePath%>/etopReport/getServiceList.do'});
				$("#tab-1").addClass("hidden");
				$("#tab-2").addClass("hidden");
				$("#tab-3").addClass("hidden");
				$("#tab-4").addClass("hidden");
				$("#tab-5").addClass("hidden");
				$("#tab-6").removeClass("hidden");
			}
		}
    </script>
</body>
</html>