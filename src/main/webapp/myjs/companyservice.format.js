   	function serviceFormatter(value) {
   		switch (value) {

   		case '101':
   			return "待审批";
   		case '102':
   			return "待回执";
   		case '201':
   			return "已撤销";
   		case '202':
   			return "已审批";
   		case '203':
   			return "已派工";
   		case '204':
   			return "已完工";
   		case '300':
   			return "完结";
   		case '301':
   			return "拒绝";


   		}
   	}
   	function serviceTypeFormatter(value) {

   		switch (value) {
   		
   		case 'KDFW':
			return "快递服务";
   		case 'WYBX':
			return "物业保修";
   		case 'RYDZ':
			return "人员代招";
   		case 'ZZBL':
			return "执照办理";
   		case 'SBZC':
			return "商标注册";
   		case 'DLKJ':
			return "代理会计";
   		case 'FWZX':
			return "法务咨询";
   		case 'ZXSQ':
			return "装修申请";
   		case 'HYSYY':
			return "会议室预约";
   		case 'SYPYY':
			return "摄影棚预约";
   		case 'SWFW':
			return "商务服务";
   		case 'GGCG':
			return "公共采购";
   		case 'YYFW':
   			return "预约服务";
   		}
}