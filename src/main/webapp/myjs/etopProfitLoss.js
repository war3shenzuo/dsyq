function dataFormat(value){
    switch (value) {
        case 14:
            return "<font color='red'>支出合计</font>";
        case 13:
            return "<font color='maroon'>收益合计</font>";
        case 15:
            return "<font color='#228b22'>收支合计</font>";
        case 1:
            return "合同收益";
        case 2:
            return "服务收益";
        case 3:
            return "其他收益";
        case 4:
            return "合同支出";
        case 5:
            return "能源支出";
        case 6:
            return "人事费";
        case 7:
            return "业务费";
        case 8:
            return "管理费";
        case 9:
            return "服务支出";
        case 10:
        	return "工程维修";
        case 11:
        	return "财务支出";
        case 12:
        	return "税费支出";
        default:
            return "未知";
    }
}

function formatterType(value, data){
    if(value == 1){
        switch (data) {
            case 1:
                return "租赁合同";
            case 5:
                return "服务合同";
            case 4:
                return "物业合同";
            case 3:
                return "能源合同";
        }
    }else if(value == 2){
        switch (data) {
            case 6:
                return "园区服务";
        }
    }else if(value == 3){
        switch (data) {
            case 1:
                return "运营补贴";
            case 2:
                return "快递";
            case 3:
                return "其他";
            case 4:
            	return "装修补贴";
            case 5:
            	return "培训补贴";
            case 6:
            	return "利息收入";
            
        }
    }else if(value == 4){
        switch (data) {
            case 2:
                return "外包合同";
        }
    }else if(value == 5){
        switch (data) {
            case 1:
                return "电费";
            case 2:
                return "水费";
            case 3:
                return "燃气费";
            case 4:
                return "空调费";
            default:
                return "其他";
        }
    }else if(value == 6){
        switch (data) {
            case 1:
                return "工资";
            case 2:
                return "保险";
            case 3:
                return "公积金";
            case 4:
                return "奖金";
            case 5:
                return "补贴";
            case 6:
                return "福利";
            case 7:
            	return "招聘费";
            default:
                return "其他";
        }
    }else if(value == 7){
        switch (data) {
            case 1:
                return "广告宣传";
            case 2:
                return "业务招待";
            case 3:
            	return "货运费";
            default:
                return "其他";
        }
    }else if(value == 8){
        switch (data) {
            case 1:
                return "员工培训";
            case 2:
                return "差旅费";
            case 3:
                return "通讯费";
            case 4:
                return "办公费";
            case 5:
                return "培训费";
            case 6:
            	return "会议费";
            case 7:
            	return "节日装饰";
            case 8:
            	return "快递费";
            case 9:
            	return "垃圾清运";
            case 10:
            	return "汽车租赁";
            case 11:
            	return "保洁日用";
            case 12:
            	return "折旧费";
            default:
                return "其他";
        }
    }else if(value == 9){
        switch (data) {
            case 1:
                return "实物采购";
            case 2:
            	return "商务服务";
            case 3:
            	return "其他";
        }
    }else if(value == 10){
        switch (data) {
        case 1:
            return "维修费";
        case 2:
        	return "装修费";
        case 3:
        	return "检测费";
        case 4:
        	return "工具购置";
        case 5:
        	return "其他";
	    }
	}else if(value == 11){
        switch (data) {
        case 1:
            return "贷款利息";
        case 2:
        	return "审计费";
        case 3:
        	return "手续费";
        case 4:
        	return "其他";
	    }
	}else if(value == 12){
        switch (data) {
        case 1:
            return "残保金";
        case 2:
        	return "附加税";
        case 3:
        	return "所得税";
        case 4:
        	return "水利基金";
        case 5:
        	return "印花税";
        case 6:
        	return "增值税";
        case 7:
        	return "其他";
	    }
	}
}