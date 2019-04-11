var table = $('#billListTable');

$(document).ready(function() {
	$('#companyConfirm').click(function() {
		$("#companyId").val($("#companySelect").val());
		$("#companyName").val($("#companySelect option:selected").text());
		tableRefresh('../list2.do');
	});
	
	$('#searchBill').click(function() {
		tableRefresh('../list2.do');
//		var t=setTimeout("alert(JSON.stringify($('#billListTable').bootstrapTable('getData')))",1000)
//		alert(JSON.stringify($('#billListTable').bootstrapTable('getData')))
	});
	
	$("#amount").blur(function() {
		if($("#amount").val() == "") {
			$("#capital-amount").val("");
			return;
		}
		var n = Number($("#amount").val());
		$("#capital-amount").val(upDigit(n));
	});
	//var t=setTimeout(alert(JSON.stringify($("#billListTable").bootstrapTable('getData'))),5000);
	
});

/* 查询条件 */
function queryParams(params) {
	params.companyId = $("#companyId").val();
//	params.billType = 1;
	params.auditStatus = 2;
	params.paid = false;
	params.billId = $("#billId").val();
	if(params.billId != '' && params.companyId == '0')
		params.companyId = null;
	
	return params;
	
}

function submit() {
	if($("#amount").val() == "") {
		swal({
			title : "请填写总金额！",
			type : "warning",
			confirmButtonText : "确定",
			closeOnConfirm : true
		});
		return;
	}
	if($("input[name='payment']").val() == "") {
		swal({
			title : "请输入支付金额！",
			type : "warning",
			confirmButtonText : "确定",
			closeOnConfirm : true
		});
		return;
	}
	var total = Number($("#amount").val());
	var payType = $('#payType input:radio:checked').val();
	var payNoOut = $("#payNoOut").val();
	var arr = new Array();
	var inputs = $("input[name='payment']");
	var data = table.bootstrapTable('getData');
	for(var i=0; i<inputs.length; i++) {
		var input = $(inputs[i]);
		if(input.val() >= 0) {
			if(data[i].amount + data[i].overdueFine - data[i].accountRemission - data[i].overdueRemission < Number(input.val())) {
				swal({
					title : "账单(" + data[i].billId + ")支付款大于待收款！",
					type : "warning",
					confirmButtonText : "确定",
					closeOnConfirm : true
				});
				return;
			}
			var pay = {};
			pay["billId"] = input.attr("billId");
			pay["amount"] = Number(input.val());
			pay["payType"] = Number(payType);
			pay["payNoOut"]= payNoOut;
			arr.push(pay);
			if(data[i].billType == 1){
				total -= pay.amount;
			}else if(data[i].billType == 2){
				total += pay.amount;
		}
		}
		else {
			swal({
				title : "账单(" + data[i].billId + ")支付款应为正数！",
				type : "warning",
				confirmButtonText : "确定",
				closeOnConfirm : true
			});
			return;
		}
//		else{
//			if(data[i].amount + data[i].overdueFine - data[i].accountRemission - data[i].overdueRemission < Math.abs(Number(input.val())) ) {
//				swal({
//					title : "账单(" + data[i].billId + ")支付款大于待收款！",
//					type : "warning",
//					confirmButtonText : "确定",
//					closeOnConfirm : true
//				});
//				return;
//			}
//			var pay = {};
//			pay["billId"] = input.attr("billId");
//			pay["amount"] = Number(input.val());
//			pay["payType"] = Number(payType);
//			pay["payNoOut"]= payNoOut;
//			arr.push(pay);
//				total -= pay.amount;
//		}
	}
	if(total < -1e-9) {
		swal({
			title : "总金额不足以支付分项！",
			type : "warning",
			confirmButtonText : "确定",
			closeOnConfirm : true
		});
		return;
	}
	if(arr.length == 0) {
		swal({
			title : "请填写要支付的项目！",
			type : "warning",
			confirmButtonText : "确定",
			closeOnConfirm : true
		});
		return;
	}

	var url = "addbatch.do?total="+$("#amount").val()
			+ "&companyId=" + $("#companyId").val()
			+ "&billId=" + $("#billId").val()
			+ "&companyName=" + $("#companyName").val();
	
	$.ajax({
		type : "post",
		url : url,
		data : JSON.stringify(arr),
		contentType: "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			if (data.status == 10001) {
				swal({
					title : "支付成功！",
					text : total>0?"支付账单有结余，请注意记录！":"",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {
//					tableRefresh('../list.do');
					window.location.reload();
				});
			} else {
				swal({
					title : "支付失败！",
					text : data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				});
			}
		}
	});
}

function searchCompany() {
	$.get('../../etopCompany/getEtopCompanyList2.do', {
		'companyName' : $('#companyNameText').val()
	}, function(data) {
		var rows = data.rows;
		var selector = $('#companySelect');
		selector.empty();
		var oOption = document.createElement('option');
		oOption.text = "";
		oOption.value = 0;
		selector.append(oOption);
		for (var i = 0; i < rows.length; i++) {
			oOption = document.createElement('option');
			oOption.text = rows[i].companyName;
			oOption.value = rows[i].companyId;
			selector.append(oOption);
		}
	});
}

function inputFormatter(value, row, index) {
	return '<input type="number" class="form-control" name="payment" billId="' + value + '" onblur="toUpDigit(this);">';
}

function toUpDigit(obj) {
	var input = $(obj);
	var output = input.parent().next();
	if(input.val() == "") {
		output.text("");
	}
	else {
		output.text(upDigit(input.val()));
	}
}