var table = $("#reliefListTable");

function detailButtonFormatter(value, row, index) {
	var btns;
	var url = "bill/detailview.do?billId=" + value;
	var a1 = '<a class="btn btn-info" onclick="totabs(\'' + url + '\', \'账单详情\')">详情</a>';
	if($('#auditStatus').prop("checked") || $("#readonly").val()=="true") {
		btns = a1;
	}
	else {
		var a2 = '<a class="btn btn-primary" onclick="audit(\'' + row.reliefId + '\', true)">同意</a>';
		var a3 = '<a class="btn btn-danger" onclick="audit(\'' + row.reliefId + '\', false)">拒绝</a>';
		btns = a2 + a3 + a1;
	}
	return btns;
}

function queryParams(params) {
	params.audited = $('#auditStatus').prop("checked");
	return params;
}

function audit(id, status) {
	var act = status ? "批准" : "拒绝";
	swal({
		title : "确定" + act + "这个申请？",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		cancelButtonText : "取消",
		confirmButtonText : "执行",
		showLoaderOnConfirm : true,
		closeOnConfirm : false
	}, function() {
		$.post("audit.do", {
			"reliefId" : id,
			"status" : status
		}, function(data) {
			if (data.status == 10001) {
				swal({
					title : "操作成功！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {
					tableRefresh();
				});
			} else {
				swal({
					title : "操作失败！",
					text : data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {
					tableRefresh();
				});
			}
		});
	});
}

function auditBatch(status) {
	if ($('#auditStatus').prop("checked"))
		return;

	var selections = table.bootstrapTable('getSelections');
	if (selections.length == 0)
		return;
	var ids = "";
	for (var i = 0; i < selections.length; i++) {
		ids = ids + selections[i].reliefId + ",";
	}
	var act = status ? "批准" : "拒绝";
	swal({
		title : "确定" + act + selections.length + "个申请？",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		cancelButtonText : "取消",
		confirmButtonText : "执行",
		showLoaderOnConfirm : true,
		closeOnConfirm : false
	}, function() {
		$.post("audit.do", {
			"reliefIds" : ids,
			"status" : status
		}, function(data) {
			if (data.status == 10001) {
				swal({
					title : "成功" + act + data.data + "个申请！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {
					tableRefresh('listByParkId.do?auditStatus=0');
				});
			} else {
				swal({
					title : "操作失败:" + data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {
					tableRefresh('listByParkId.do?auditStatus=0');
				});
			}
		});
	});
}