var validate;

$(document).ready(function() {
	validate = $("#form").validate({
		rules : {
			amount : {
				required : true,
				min : 0
			},
			deadline : "required",
			description : "required",
			lastBalance : "required"
		},
		submitHandler : submit
	});

	$('#billType').change(switchBillType);

	$('#companyConfirm').click(function() {
			$("#companyId").val($("#companySelect").val());
			$("#companyName").val($("#companySelect option:selected").text());
			searchContract();
	});
	$('#categoryConfirm').click(function() {
		$("#id").val($("#categorySelect").val());
		$("#category").val($("#categorySelect option:selected").text());
		searchContract();
});

	$("#billSource").change(switchBillSource);

//		$("#companyId").change(searchContract);
});

function submit() {
	var billType = $("#billType").val();
	var companyId = $("#companyId").val();
	var companyName = $("#companyName").val();
	var billSource = $("#billSource").val();
	var billNoOut = billType==1?$("#billNoOut").val():$("#billNoOut2").val();
	var amount = $("#amount").val();
	var deadline = $("#deadline").val() + " 23:59:59";
	var lastBalance = $("#lastBalance").val();
	var description = $("#description").val();
	$.post("add.do", {
		"billType" : billType,
		"companyId" : companyId,
		"companyName" : companyName,
		"billSource" : billSource,
		"billNoOut" : billNoOut,
		"amount" : amount,
		"deadline" : deadline,
		"lastBalance" : lastBalance,
		"description" : description
	}, function(data) {
		if (data.status == 10001) {
			swal({
				title : "保存成功！",
				type : "success",
				confirmButtonText : "确定",
				closeOnConfirm : false
			}, function() {
				var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
				parent.layer.close(index);
			});

		} else {
			swal({
				title : "添加失败！",
				text : data.msg,
				type : "error",
				confirmButtonText : "确定",
				closeOnConfirm : true
			});
		}
	});
}

function searchCategory() {
	$.get('../goods/getEtopGoodsList.do', {
		'category' : $('#categoryNameText').val()
	}, function(data) {
		var rows = data.rows;
		var selector = $('#categorySelect');
		selector.empty();
		var oOption = document.createElement('option');
		selector.append(oOption);
		for (var i = 0; i < rows.length; i++) {
			var oOption = document.createElement('option');
			oOption.text = rows[i].category;
			oOption.value = rows[i].id;
			selector.append(oOption);
		}
	});
}

function searchCompany() {
	$.get('../etopCompany/getEtopCompanyList.do', {
		'companyName' : $('#companyNameText').val()
	}, function(data) {
		var rows = data.rows;
		var selector = $('#companySelect');
		selector.empty();
		var oOption = document.createElement('option');
		selector.append(oOption);
		for (var i = 0; i < rows.length; i++) {
			var oOption = document.createElement('option');
			oOption.text = rows[i].companyName;
			oOption.value = rows[i].companyId;
			selector.append(oOption);
		}
	});
}

function searchContract() {
	var billSource = $("#billSource").val();
	var companyId = $("#companyId").val();
	var selector = $("#billNoOut");
	selector.empty();
	selector.text("");
	if(companyId == "") {
		return;
	}
	else if(billSource == 0) {
		return;
	}
	else if(billSource == 2) {
		return;
	}
	else if(billSource < 6) {
		$.get("../contract/getActiveContractsByCompanyAndCategory.do",
				{"refCompanyId" : companyId, "contractCategory" : billSource},
				function(data) {
					if(data.status == 10001) {
						data = data.data;
						for(var i=0; i<data.length; i++) {
							var oOption = document.createElement('option');
							oOption.text = "编号:" + data[i].contractNo + "；楼层:" + data[i].floor + "；房间号:" + data[i].room;
							oOption.value = data[i].contractNo;
							selector.append(oOption);
						}
					}
				});
	}
	else if(billSource == 6) {
		
	}
}

function switchBillType() {
	var billType = $('#billType').val();
	validate.resetForm();
	if(billType == 1) {
		var billSourceSelector = $("#billSource");
		billSourceSelector.empty();
		var oOption = document.createElement('option');
		oOption.text = "无";
		oOption.value = 0;
		billSourceSelector.append(oOption);
		oOption = document.createElement('option');
		oOption.text = "租赁合同";
		oOption.value = 1;
		billSourceSelector.append(oOption);
		oOption = document.createElement('option');
		oOption.text = "能源合同";
		oOption.value = 3;
		billSourceSelector.append(oOption);
		oOption = document.createElement('option');
		oOption.text = "物业合同";
		oOption.value = 4;
		billSourceSelector.append(oOption);
		oOption = document.createElement('option');
		oOption.text = "服务合同";
		oOption.value = 5;
		billSourceSelector.append(oOption);
		oOption = document.createElement('option');
		oOption.text = "园区服务";
		oOption.value = 6;
		billSourceSelector.append(oOption);
		
		$("#companyId").val("");
		$("#companyName").val("");
		$("#companyName").attr("readonly", true);
		$("#findCompany").show();
		
		$("#billNoOut").empty();
		$("#billNoOut").show();
		$("#billNoOut2").val("");
		$("#billNoOut2").hide();
		
		$("#deadline").val("");
		$("#deadline").attr("disabled", false);
		$("#deadline").rules("add", {
			required : true
		});
		
		$("#lastBalance").val("");
		$("#lastBalance").attr("disabled", false);
	}
	else if(billType == 2) {
		var billSourceSelector = $("#billSource");
		billSourceSelector.empty();
		var oOption = document.createElement('option');
		oOption.text = "无";
		oOption.value = 0;
		billSourceSelector.append(oOption);
		oOption = document.createElement('option');
		oOption.text = "外包合同";
		oOption.value = 2;
		billSourceSelector.append(oOption);
		
		$("#companyId").val("");
		$("#companyName").val("");
		$("#companyName").attr("readonly", false);
		$("#findCompany").hide();
		
		$("#billNoOut").empty();
		$("#billNoOut").hide();
		$("#billNoOut2").val("");
		$("#billNoOut2").show();
		
		$("#deadline").val("");
		$("#deadline").attr("disabled", true);
		$("#deadline").rules("remove");
		
		$("#lastBalance").val("");
		$("#lastBalance").attr("disabled", true);
		$("#lastBalance").rules("remove");
	}
}

function switchBillSource() {
	validate.resetForm();
	
	var billType = $('#billType').val();
	var billSource = $("#billSource").val();
	
	if(billSource == 0) {
		$("#companyName").rules("remove");
		$("#billNoOut").rules("remove");
		$("#billNoOut2").rules("remove");
		$("#lastBalance").rules("remove");
		$("#lastBalance").val("");
		$("#lastBalance").attr("disabled", true);
	}
	else if(billSource < 6) {
		$("#companyName").rules("add", {
			required : true
		});
		$("#lastBalance").attr("disabled", false);
		if(billType == 1) {
			$("#billNoOut").rules("add", {
				required : true
			});
			$("#lastBalance").rules("add", {
				required : true
			});
			$("#billNoOut2").rules("remove");
		}
		else if(billType == 2) {
			$("#billNoOut2").rules("add", {
				required : true
			});
			$("#billNoOut").rules("remove");
			$("#lastBalance").rules("remove");
		}
	}
	else if(billSource == 6) {
		$("#companyName").rules("add", {
			required : true
		});
		$("#billNoOut").rules("remove");
		$("#billNoOut2").rules("remove");
		$("#lastBalance").rules("remove");
		$("#lastBalance").val("");
		$("#lastBalance").attr("disabled", true);
	}
	
	searchContract();
}