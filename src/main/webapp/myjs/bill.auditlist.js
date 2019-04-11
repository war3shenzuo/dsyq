var table = $('#billListTable');

$(document).ready(function() {
	getStatistics();
});

function detailButtonFormatter(value, row, index) {
	var url = "bill/detailview.do?billId=" + value;
	var a1 = '<a class="btn btn-info" onclick="totabs(\'' + url + '\', \'账单详情\')">详情</a>';
	if(level == 1)
		a1 = a1 +  '<a class="btn btn-danger" onclick="refuseAndUpdate(\'' + value + '\')">拒绝</a>';
	return a1;
}

/* 查询条件 */
function queryParams(params) {
	params.billId = $("#billId").val();
	params.companyName = $("#companyName").val();
	params.billType = $("#billType").val();
	params.billSource = $("#billSource").val();
	params.auditStatus = level-1;
	return params;
}

function audit(status) {
	var selections = table.bootstrapTable('getSelections');
	if (selections.length == 0)
		return;
	var ids = "";
	for (var i = 0; i < selections.length; i++) {
		ids = ids + selections[i].billId + ",";
	}
	var act;
	if (status) {
		act = "批准";
		swal({
			title : "确定" + act + selections.length + "个账单？",
			type : "info",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			cancelButtonText : "取消",
			confirmButtonText : "执行",
			showLoaderOnConfirm : true,
			closeOnConfirm : false
		}, function() {
			auditAction(ids, status, level, act);
		});
	}
	else {
		act = "拒绝";
		swal({
			title : "确定" + act + selections.length + "个账单？",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			cancelButtonText : "取消",
			confirmButtonText : "执行",
			showLoaderOnConfirm : true,
			closeOnConfirm : false
		}, function() {
			auditAction(ids, status, level, act);
		});
	}
}

function auditAction(ids, status, level, act) {
	$.post("audit.do", {
		"billIds" : ids,
		"status" : status,
		"level" : level
	}, function(data) {
		if (data.status == 10001) {
			swal({
				title : "成功" + act + data.data + "个账单！",
				type : "success",
				confirmButtonText : "确定",
				closeOnConfirm : true
			}, function() {
				tableRefresh('list.do');
			});
		} else {
			swal({
				title : "操作失败:" + data.msg,
				type : "error",
				confirmButtonText : "确定",
				closeOnConfirm : true
			}, function() {
				tableRefresh('list.do');
			});
		}
	});
}

function refuseAndUpdate(billId) {
	layer.open({
		type : 2,
		title : '拒绝并修改后重新提交',
		closeBtn : "1",
		shadeClose : true,
		shade : [ 0 ],
		shift : 2,
		area : [ '800px', '600px' ],
		content : 'updateview.do?billId=' + billId,
		end : function() {
			table.bootstrapTable('refresh', {
				url : 'list.do'
			});
			getStatistics();
		}
	});
}

function getStatistics() {
	$.get("getStatistics.do", queryParams({})
	, function(data) {
		if(data.status == 10001) {
			data = data.data;
			$("#totalAccount").val(data.total|0);
		}
	});
}