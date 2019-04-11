var table = $('#billListTable');

$(document).ready(function() {
	getStatistics();
	
	if ($("#parkId").val() != '') {
		loadFloorList($("#parkId").val(), '', $('#building-sele'));
	}
	
	$('#parkId').on('change',function(){
		//园区改变，先清空其他所有
		initSelect($('#building-sele'),'全部');
		initSelect($('#floor-sele'),'全部');
		initSelect($('#block-sele'),'全部');
		if($(this).val()!='') {
			loadFloorList($(this).val(),'',$('#building-sele'));
		}
	});
	
	$('#building-sele').on('change',function() {
		//改变，先清空其他所有
		initSelect($('#floor-sele'),'全部');
		initSelect($('#block-sele'),'全部');
		if($(this).val()!='') {
			loadFloorList($('#parkId').val(),$(this).val(),$('#floor-sele'));
		}
	});
	
	$('#floor-sele').on('change',function() {
		//改变，先清空其他所有
		initSelect($('#block-sele'),'全部');
		if($(this).val()!='') {
			loadFloorList($('#parkId').val(),$(this).val(),$('#block-sele'));
		}
	});
});

function queryParams(params) {
	params.billId = $("#billId").val();
	params.companyName = $("#companyName").val();
	params.billType = $("#billType").val();
	params.billStatus = $("#billStatus").val();
	params.billSource = $("#billSource").val();
	params.auditStatus = $("#auditStatus").val();
	if ($("#beginDate").val() != '')
		params.beginDate = $("#beginDate").val() + " 00:00:00";
	if ($("#endDate").val() != '')
		params.endDate = $("#endDate").val() + " 23:59:59";
	if ($("#parkId").val() != '')
		params.parkIds = $("#parkId").val();
	if ($("#building-sele").val() != '')
		params.buildingSele = $("#building-sele").val();
	if ($("#floor-sele").val() != '')
		params.floorSele = $("#floor-sele").val();
	if ($("#block-sele").val() != '')
		params.blockSele = $("#block-sele").val();
	if ($("#room-num") != '')
		params.roomNum = $("#room-num").val();
	return params;
}

function detailButtonFormatter(value, row, index) {
	url = "bill/detailview.do?billId=" + value;
	return '<a class="btn btn-info" onclick="totabs(\'' + url
			+ '\', \'账单详情\')">详情</a>';
}

function deleteBill() {
	var selections = table.bootstrapTable('getSelections');
	var ids = "";
	if (selections.length == 0)
		return;
	for (var i = 0; i < selections.length; i++) {
		ids = ids + selections[i].billId + ",";
	}
	swal({
		title : "确定删除" + selections.length + "个账单？",
		text : "审核过的账单不能删除",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		cancelButtonText : "取消",
		confirmButtonText : "是的，执行删除！",
		showLoaderOnConfirm : true,
		closeOnConfirm : false
	}, function() {
		$.get("delete.do", {
			"billIds" : ids
		}, function(data) {
			if (data.status == 10001) {
				swal({
					title : "成功删除" + data.data + "个账单！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {
					tableRefresh('listwithfloor.do');
				});
			} else {
				swal({
					title : "删除失败:" + data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {
					tableRefresh('listwithfloor.do');
				});
			}
		});
	});
}

function getStatistics() {
	$.get("getStatistics.do", queryParams({}), function(data) {
		if (data.status == 10001) {
			data = data.data;
			$("#totalAccount").val(data.total | 0);
			$("#uncheck").val(data.uncheck | 0);
			$("#checkAccept").val(data.check_accept | 0);
			$("#checkRefuse").val(data.check_refuse | 0);
			$("#financeAccept").val(data.finance_accept | 0);
			$("#financeRefuse").val(data.finance_refuse | 0);
		}
	});
}

function initSelect(obj,title) {
	var oOption=document.createElement('option');
	oOption.value='';
	oOption.text=title;
	$(obj).empty().append(oOption);
}

function loadFloorList(parkId, parentId, obj) {
	$.post('../floor/getFloorListByParent.do',
			{parkId:parkId,parentId:parentId,status:'1'}, function(data){
		if(data.status===10001) {
			var d=data.data;
			for(var i=0;i<d.length;i++) {
				var oOption=document.createElement('option');
				oOption.value=d[i].id;
				oOption.text=d[i].buildName;
				$(obj).append(oOption);
			}
		}
	});
}