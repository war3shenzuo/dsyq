var validate;

$(document).ready(function() {
	validate = $("#signupForm").validate({
		rules : {
			amount : {
				required : true,
				min : 0
			},
			deadline : "required",
			description : "required"
		},
		submitHandler : submit
	});
	
	if($("#billType").val()==1) {
		$("#deadline").rules("add", {
			required : true
		});
	}
});

function submit() {
	swal({
		title : "确认提交订单修改？",
		type : "warning",
		confirmButtonText : "确定",
		confirmButtonColor : "#DD6B55",
		showCancelButton : true,
		cancelButtonText : "取消",
		closeOnConfirm : false,
		closeOnCancel : true
	}, function() {
		realsubmit();
	});
}

function realsubmit() {
	var billId = $("#billId").val();
	var billType = $("#billType").val();
	var companyId = $("#companyId").val();
	var companyName = $("#companyName").val();
	var billSource = $("#billSource").val();
	var billNoOut = $("#billNoOut").val();
	var amount = $("#amount").val();
	var deadline = $("#deadline").val() + " 23:59:59";
	var description = $("#description").val();
	$.post("refuseAndUpdate.do", {
		"billId" : billId,
		"billType" : billType,
		"companyId" : companyId,
		"companyName" : companyName,
		"billSource" : billSource,
		"billNoOut" : billNoOut,
		"amount" : amount,
		"deadline" : deadline,
		"description" : description
	}, function(data) {
		if (data.status == 10001) {
			swal({
				title : "操作成功！",
				type : "success",
				confirmButtonText : "确定",
				closeOnConfirm : false
			}, function() {
				var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
				parent.layer.close(index);
			});

		} else {
			swal({
				title : "操作失败！",
				text : data.msg,
				type : "error",
				confirmButtonText : "确定",
				closeOnConfirm : true
			});
		}
	});
}