/*意向公司状态*/
function formatterCompIntentionStatus(value) {
    switch (value) {
        case 0:
            return "<font color='red'>未读</font>";
        case 1:
            return "<font color='green'>已读</font>";
        default:
            return "<font color='maroon'>未知</font>";
    }
}


function formatterReviewStatus(value) {
    switch (value) {
        case 0:
            return "未审核";
        case 1:
            return "<font color='green'>审核中</font>";
        case 2:
            return "<font color='maroon'>园长未通过</font>";
        case 3:
            return "<font color='maroon'>财务未通过</font>";
        default:
            return "未知";
    }
}

/*正式公司状态*/
function formatterCompStatus(value) {
    switch (value) {
        case ("1"):
            return "<font color='green'>正式</font>";
        case ("2"):
            return "<font color='maroon'>合同过期</font>";
        case ("3"):
            return "<font color='maroon'>合同终止</font>";
        case ("4"):
        	return "<font color='maroon'>退园</font>";
        default:
            return "未知";
    }
}

/*员工性别*/
function formatterEmploySex(value) {
    switch (value) {
        case 1:
            return "男";
        case 2:
            return "女";
        default:
            return "未知";
    }
}
