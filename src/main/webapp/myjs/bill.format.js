function billTypeFormatter(value) {
	switch (value) {
	case 1:
		return "收入";
	case 2:
		return "支出";
	default:
		break;
	}
}

function billStatusFormatter(value) {
	switch (value) {
	case 0:
		return "<font color='maroon'>未付款</font>";
	case 1:
		return "<font color='darkorange'>未付清</font>";
	case 2:
		return "<font color='green'>已付款</font>";
	default:
		break;
	}
}

function billStatusFormatter2(value) {
	switch (value) {
	case 0:
		return "未付款";
	case 1:
		return "未付清";
	case 2:
		return "已付款";
	default:
		break;
	}
}

function billSourceFormatter(value) {
	switch (value) {
	case 0:
		return "无";
	case 1:
		return "租赁合同";
	case 2:
		return "外包合同";
	case 3:
		return "能源合同";
	case 4:
		return "物业合同";
	case 5:
		return "服务合同";
	case 6:
		return "园区服务";
	default:
		return "其他";
	}
}

function auditStatusFormatter(value) {
	switch (value) {
	case 0:
		return "未审核";
	case 2:
		return "<font color='green'>园长通过</font>";
	case -2:
		return "<font color='maroon'>园长拒绝</font>";
	case 1:
		return "<font color='green'>财务通过</font>";
	case -1:
		return "<font color='maroon'>财务拒绝</font>";
	default:
		break;
	}
}

function payTypeFormatter(value) {
	switch (value) {
	case 0:
		return "无";
	case 1:
		return "银行卡";
	case 2:
		return "支票";
	case 3:
		return "支付宝";
	case 4:
		return "微信";
	case 5:
		return "现金";	
	default:
		return "其他";
	}
}

function auditStatusFormatter2(value) {
	switch (value) {
	case 0:
		return "未审核";
	case 2:
		return "园长通过";
	case -2:
		return "园长拒绝";
	case 1:
		return "财务通过";
	case -1:
		return "财务拒绝";
	default:
		break;
	}
}

function auditStatusFormatter3(value) {
	switch (value) {
	case 0:
		return "未审核";
	case 1:
		return "<font color='green'>通过</font>";
	case 2:
		return "<font color='maroon'>拒绝</font>";
	default:
		break;
	}
}

function longtextFormatter(value) {
	if(value != null && value.length > 30) {
		value = value.substring(0, 30) + "......";
	}
	return value;
}

function lineFormatter(str) {
	if(str != null)
		return str.replace(new RegExp("\n","gm"), "<br/>");
	else
		return str;
}