<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../shared/css.jsp" />
<title>账单详情</title>
<style>
	table a:hover{text-decoration: underline;}
	table a{margin-left: 10px}
</style>
</head>
<body class="grey-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						<normal>账单基本信息</normal>
					</h5>
				</div>
				<div class="ibox-content">
					<form method="get" class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-1 control-label">账单编号</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.billId}">
							</div>
							<label class="col-sm-2 control-label">账单类型</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${billType}">
							</div>
							<label class="col-sm-2 control-label">客户名称</label>
							<div class="col-sm-2">
								<input type="hidden" id="companyId" value="${etopBill.companyId }">
								<input type="text" class="form-control" id="companyName" readonly="readonly" value="${etopBill.companyName}">
							</div>
							<div class="col-sm-1">
								<a class="btn btn-primary" id="companyInfo" href="javascript:void(0)">客户详情</a>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label">账单状态</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${billStatus}" id="billStatus">
							</div>
							<label class="col-sm-2 control-label">账单来源</label>
							<div class="col-sm-2">
								<input type="text" class="form-control"  readonly="readonly" value="${billSource}" category="${etopBill.billSource}" id="billSource">
							</div>
							<label class="col-sm-2 control-label">账单来源编号</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.billNoOut}" id="billNoOut">
							</div>
							<div class="col-sm-1">
								<a class="btn btn-primary" id="sourceInfo">来源详情</a>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label">金额</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.amount }">
							</div>
							<label class="col-sm-2 control-label">滞纳金</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.overdueFine }" id="overdueFine">
							</div>
							<label class="col-sm-2 control-label">滞纳金比例（每日）</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.overdueRate}" id="amountPaid">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label">已付金额</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.amountPaid}" id="amountPaid">
							</div>
							<label class="col-sm-2 control-label">金额减免</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.accountRemission}" id="accountRemission">
							</div>
							<label class="col-sm-2 control-label">滞纳金减免</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.overdueRemission}" id="overdueRemission">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label">创建时间</label>
							<div class="col-sm-2">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input name="Field_KH_DFJL_RiQi" type="text" class="form-control" readonly="readonly"
										value='<fmt:formatDate value="${etopBill.createdTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'>
								</div>
							</div>
							<label class="col-sm-2 control-label">缴费期限</label>
							<div class="col-sm-2">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input name="Field_KH_DFJL_RiQi" type="text" class="form-control" readonly="readonly" id="deadline"
										value='<fmt:formatDate value="${etopBill.deadline}" pattern="yyyy-MM-dd HH:mm:ss"/>'>
								</div>
							</div>
							<label class="col-sm-2 control-label">支付时间</label>
							<div class="col-sm-2">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input id="payTime" name="Field_KH_DFJL_RiQi" type="text" class="form-control" readonly="readonly"
										value='<fmt:formatDate value="${etopBill.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label">覆盖起始时间</label>
							<div class="col-sm-2">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input name="Field_KH_DFJL_RiQi" type="text" class="form-control" readonly="readonly"
										value='<fmt:formatDate value="${etopBill.startTime}" pattern="yyyy-MM-dd"/>'>
								</div>
							</div>
							<label class="col-sm-2 control-label">覆盖截止时间</label>
							<div class="col-sm-2">
								<div class="input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
									<input name="Field_KH_DFJL_RiQi" type="text" class="form-control" readonly="readonly" id="deadline"
										value='<fmt:formatDate value="${etopBill.endTime}" pattern="yyyy-MM-dd"/>'>
								</div>
							</div>
						</div>
						<c:if test="${etopBill.category != null}">
						<div class="form-group">
							<label class="col-sm-1 control-label">服务大类</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.category}" id="amountPaid">
							</div>
							<label class="col-sm-2 control-label">名称</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.subject}" id="accountRemission">
							</div>
							<label class="col-sm-2 control-label">单价</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.unitPrice}" id="overdueRemission">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label">数量</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.number}" id="amountPaid">
							</div>
							<label class="col-sm-2 control-label">金额</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly" value="${etopBill.totalPrice}" id="accountRemission">
							</div>
						</div>
						</c:if>
						<div class="form-group">
							<label class="col-sm-1 control-label">详细信息</label>
							<div class="col-sm-10">
								<textarea class="form-control" readonly="readonly" rows=10>${etopBill.description}</textarea>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- 支付表格 -->
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						<normal>账单支付</normal>
					</h5>
				</div>
				<div class="ibox-content">
					<div class="row">
						<div class="example-wrap col-sm-12">
							<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group"
								style="display: none;">
								<button type="button" class="btn btn-outline btn-default" onclick="addPay()">
									<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
									<span>添加支付</span>
								</button>
							</div>
							<table id="payListTable" data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="pay/list.do?billId=${etopBill.billId}"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-pagination="true"
					               data-page-list="[5]"
					              >
								<thead>
									<tr>
										<th data-field="payId">支付单号</th>
										<th data-field="payType" data-formatter="payTypeFormatter">支付类型</th>
										<th data-field="amount">金额</th>
										<th data-field="payNoOut">凭证号</th>
										<th data-field="payTime">支付时间</th>
										<th data-field="recorderName">记账员</th>
										<c:if test="${payReadonly eq false}">
											<th data-field="payId" data-formatter="payControlFormatter"></th>
										</c:if>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						<normal>金额减免</normal>
					</h5>
				</div>
				<div class="ibox-content">
					<div class="row">
						<div class="example-wrap col-sm-12">
							<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group"
								<c:if test="${reliefReadonly or etopBill.billStatus eq 2}">style="display: none;"</c:if> >
								<a class="btn btn-outline btn-default" onclick="addRelief()">
									<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
									<span>减免申请</span>
								</a>
							</div>
							<table id="reliefListTable" data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="relief/list.do?billId=${etopBill.billId}"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-pagination="true"
					               data-page-list="[5]"
					              >
								<thead>
									<tr>
										<th data-field="reliefId">申请编号</th>
										<th data-field="amountRemission">本金减免（元）</th>
										<th data-field="overdueRemission">滞纳金减免（元）</th>
										<th data-field="reason">减免理由</th>
										<th data-field="applicantName">申请人</th>
										<th data-field="applyTime">申请时间</th>
										<th data-field="auditStatus" data-formatter="auditStatusFormatter3">审核状态</th>
										<c:if test="${reliefReadonly eq false}">
											<th data-field="reliefId" data-formatter="reliefControlFormatter"></th>
										</c:if>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						<normal>延期付款</normal>
					</h5>
				</div>
				<div class="ibox-content">
					<div class="row">
						<div class="example-wrap col-sm-12">
							<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group"
								<c:if test="${delayReadonly or etopBill.billStatus eq 2}">style="display: none;"</c:if> >
								<a class="btn btn-outline btn-default" onclick="addDelay()">
									<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
									<span>延期申请</span>
								</a>
							</div>
							<table id="delayListTable" data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="delay/list.do?billId=${etopBill.billId}"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-pagination="true"
					               data-page-list="[5]"
					              >
								<thead>
									<tr>
										<th data-field="delayId">申请编号</th>
										<th data-field="delayTime">延迟时间</th>
										<th data-field="reason">延迟理由</th>
										<th data-field="applicantName">申请人</th>
										<th data-field="applyTime">申请时间</th>
										<th data-field="auditStatus" data-formatter="auditStatusFormatter3">审核状态</th>
										<c:if test="${delayReadonly eq false}">
											<th data-field="delayId" data-formatter="delayControlFormatter"></th>
										</c:if>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../shared/js.jsp" />
<script type="text/javascript" src="<%=basePath %>/myjs/bill.format.js"></script>
<script type="text/javascript">
	var billId = "${etopBill.billId}";
	
	$(document).ready(function() {
		if($('#companyId').val() == "") {
			$('#companyInfo').attr('disabled',"true");
		}
		
		$('#companyInfo').click(function() {
			var companyId = $('#companyId').val();
			if(companyId != "")
				totabs('etopCompany/getCompInfoById.do?readonly=true&companyId='+companyId, '公司详情');
		});
		
		var id = $('#billNoOut').val();
		var category = $('#billSource').attr('category');
		if(id == null || category == null || category == 0 || category > 6) {
			$('#sourceInfo').attr('disabled',"true");
		}
		
		$('#sourceInfo').click(function() {
			id = $('#billNoOut').val();
			category = $('#billSource').attr('category');
			if(id != ""){
				if( category != null && category > 0 && category < 6) {
					totabs('contract/edit.do?id='+id+'&category='+category, '账单来源');
				}else{
					totabs('companyService/getServiceType.do?id='+id, '账单来源');
				}
			}else {
				$('#sourceInfo').attr('disabled',"true");
			}
		});
	});
	
	function addPay() {
		layer.open({
			type : 2,
			title : "添加支付信息",
			closeBtn : "1",
			shadeClose : true,
			shade : [ 0 ],
			shift : 2,
			area : [ "800px", "450px" ],
			content : "pay/addview.do?billId="+billId,
			end : function() {
				refreshPayTable();
				refreshDetail();
			}
		});
	}
	
	function deletePay(payId) {
		swal({
			title:"确定删除？",
			type:"warning",
			showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        cancelButtonText: "取消",
	        confirmButtonText: "是的，执行删除！",
	        showLoaderOnConfirm: true,
	        closeOnConfirm: false
	    }, function () {
	    	$.post("pay/delete.do", {
				"payId" : payId
			}, function(data) {
				if (data.status == 10001) {
					swal({
						title : "成功删除支付账单！",
						type : "success",
						confirmButtonText : "确定",
						closeOnConfirm : true
					}, function() {
						refreshPayTable();
						refreshDetail();
					});
				} else {
					swal({
						title : "删除失败:" + data.msg,
						type : "error",
						confirmButtonText : "确定",
						closeOnConfirm : true
					}, function() {
						refreshPayTable();
						refreshDetail();
					});
				}
			});
	    });
	}
	
	function addDelay() {
		layer.open({
			type : 2,
			title : "延期申请",
			closeBtn : "1",
			shadeClose : true,
			shade : [ 0 ],
			shift : 2,
			area : [ "800px", "450px" ],
			content : "delay/applyview.do?billId="+billId,
			end : function() {
				refreshDelayTable();
			}
		});
	}
	
	function editDelay(delayId) {
		layer.open({
			type : 2,
			title : "延期申请",
			closeBtn : "1",
			shadeClose : true,
			shade : [ 0 ],
			shift : 2,
			area : [ "800px", "450px" ],
			content : "delay/editview.do?delayId="+delayId,
			end : function() {
				refreshDelayTable();
			}
		});
	}
	
	function deleteDelay(delayId) {
		swal({
			title:"确定删除？",
			type:"warning",
			showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        cancelButtonText: "取消",
	        confirmButtonText: "是的，执行删除！",
	        showLoaderOnConfirm: true,
	        closeOnConfirm: false
	    }, function () {
	    	$.post("delay/delete.do", {
				"delayId" : delayId
			}, function(data) {
				if (data.status == 10001) {
					swal({
						title : "成功删除!",
						type : "success",
						confirmButtonText : "确定",
						closeOnConfirm : true
					}, function() {
						refreshDelayTable();
					});
				} else {
					swal({
						title : "删除失败:" + data.msg,
						type : "error",
						confirmButtonText : "确定",
						closeOnConfirm : true
					}, function() {
						refreshDelayTable();
					});
				}
			});
	    });
	}
	
	function addRelief() {
		var amount =${etopBill.amount };
		var overdueFine = ${etopBill.overdueFine };
		layer.open({
			type : 2,
			title : "减免申请",
			closeBtn : "1",
			shadeClose : true,
			shade : [0],
			shift : 2,
			area : [ "800px", "450px" ],
			content : "relief/applyview.do?billId="+billId+"&amount="+amount+"&overdueFine="+overdueFine,
			end : function() {
				refreshReliefTable();
			}
		});
	}
	
	function editRelief(reliefId) {
		layer.open({
			type : 2,
			title : "延期申请",
			closeBtn : "1",
			shadeClose : true,
			shade : [ 0 ],
			shift : 2,
			area : [ "800px", "450px" ],
			content : "relief/editview.do?reliefId="+reliefId,
			end : function() {
				refreshReliefTable();
			}
		});
	}
	
	function deleteRelief(reliefId) {
		swal({
			title:"确定删除？",
			type:"warning",
			showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        cancelButtonText: "取消",
	        confirmButtonText: "是的，执行删除！",
	        showLoaderOnConfirm: true,
	        closeOnConfirm: false
	    }, function () {
	    	$.post("relief/delete.do", {
				"reliefId" : reliefId
			}, function(data) {
				if (data.status == 10001) {
					swal({
						title : "成功删除!",
						type : "success",
						confirmButtonText : "确定",
						closeOnConfirm : true
					}, function() {
						refreshReliefTable();
					});
				} else {
					swal({
						title : "删除失败:" + data.msg,
						type : "error",
						confirmButtonText : "确定",
						closeOnConfirm : true
					}, function() {
						refreshReliefTable();
					});
				}
			});
	    });
	}
	
	function refreshDetail() {
		$.get("findById.do", {
			"billId" : billId
		}, function(data) {
			if (data.status == 10001) {
				var bill = data.data;
				$('#amountPaid').val(bill.amountPaid);
				$('#billStatus').val(billStatusFormatter2(bill.billStatus));
				$('#payTime').val(bill.payTime);
				$('#overdueFine').val(bill.overdueFine);
			}
		});
	}
	
	function refreshPayTable() {
		$('#payListTable').bootstrapTable('refresh', {
			url : "pay/list.do?billId="+billId
		});
	}
	
	function refreshReliefTable() {
		$('#reliefListTable').bootstrapTable('refresh', {
			url : "relief/list.do?billId="+billId
		});
	}
	
	function refreshDelayTable() {
		$('#delayListTable').bootstrapTable('refresh', {
			url : "delay/list.do?billId="+billId
		});
	}
	
	function payControlFormatter(value) {
		return '<a href="javascript:void(0)" onclick="deletePay(\'' + value + '\')">删除</a>';
	}
	
	function delayControlFormatter(value, row, index) {
		if(row.auditStatus == 0) {
			editbtn = '<a onclick="editDelay(\'' + value + '\')">编辑</a>';
			delbtn = '<a onclick="deleteDelay(\'' + value + '\')">删除</a>';
			return editbtn + delbtn;
		}
	}
	
	function reliefControlFormatter(value, row, index) {
		if(row.auditStatus == 0) {
			editbtn = '<a onclick="editRelief(\'' + value + '\')">编辑</a>';
			delbtn = '<a onclick="deleteRelief(\'' + value + '\')">删除</a>';
			return editbtn + delbtn;
		}
	}
</script>
</body>
</html>