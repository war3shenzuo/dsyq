/**表格刷新*/
function tableRefresh(dataUrl){
	
	table.bootstrapTable('refresh',{url:dataUrl});

}
/**open addPage*/
function openAddPage(title,height,width,addUrl,refreshUrl){
	//iframe层
	layer.open({
	  type: 2,
	  title: title,
	  closeBtn: "1",
	  shadeClose: true,
	  shade: [0],
	  shift: 2,
	  area: [height, width],
	  content: addUrl,
	  end: function(){
			    if(refreshUrl!=""){
			    	table.bootstrapTable('refresh',{url:refreshUrl});
			    }
			    if (typeof(tree) != "undefined") {
			    	tree.refresh();
			    }
	       }
	}); 
}

/**open selectPage*/
function openSelectPage(title,height,width,pageUrl,fun){
	//iframe层
	layer.open({
		type: 2,
		title: title,
		closeBtn: "1",
		shadeClose: true,
		shade: [0],
		shift: 2,
		area: [height, width],
		content: pageUrl,
		end: function(){
		 fun();
		}
	}); 
}


function activatedStatus(value){
	if(value =='0'){
		return "激活";
	}else if(value=='1'){
		return "停用";
	}else{
		return "未知";
	}
}

function activatedAndStopStatus(value){
	if(value =='0'){
		return "停用";
	}else if(value=='1'){
		return "激活";
	}else{
		return "未知";
	}
}


/*激活状态*/
function formatterActivatedStatus(value,row,index){
	if(value =='0'){
		return "未激活";
	}else if(value=='1'){
		return "激活中";
	}else{
		return "未知";
	}
}

/*用户类型*/
function formatterUserType(value,row,index){
	if(value =='1'){
		return "企业";
	}else if(value=='2'){
		return "个人";
	}else if(value=='3'){
		return "园区用户";
	}else if(value=='4'){
		return "园区组用户";
	}else if(value=='5'){
		return "系统管理员";
	}else{
		return "未知";
	}
}
/**新闻类别*/
function formatternewsType(value,row,index){
	if(value =='0'){
		return "平台";
	}else if(value=='1'){
		return "个人";
	}else if(value=='2'){
		return "园区系统管理员";
	}else if(value=='3'){
		return "园区组系统管理员";
	}else if(value=='4'){
		return "系统管理员";
	}else{
		return "未知";
	}
}


function formattertotalType(value,row,index){
	if(value =='1'){
		return "园区新闻 ";
	}else if(value=='2'){
		return "行业资讯";
	}else{
		return "未知";
	}
}

/*房间状态*/
function formatterRoomType(value,row,index){
	if(value =='0'){
		return "待出租(无人)";
	}else if(value=='1'){
		return "已出租";
	}else if(value=='2'){
		return "预留中";
	}else if(value=='3'){
		return "待出租(有人)";
	}else{
		return "未知";
	}
}
/*房间状态*/
//  处理状态：101，待审批；102，待回执；201，已撤销；202，已审批；203，已派工；204，已完工；300，完结
function formatterServiceStatusType(value,row,index){
	if(value =='101'){
		return "待审批";
	}else if(value=='102'){
		return "待回执";
	}else if(value=='201'){
		return "已撤销";
	}else if(value=='202'){
		return "已审批";
	}else if(value=='203'){
		return "已派工";
	}else if(value=='204'){
		return "已完工";
	}else if(value=='300'){
		return "完结";
	}else if(value=='301'){
		return "拒绝";
	}else{
		return "未知";
	}
}
//园区组
function formatterParkGroupType(value,row,index){
	if(value=='1'){
		return "正式"
	}else if(value=='0'){
		return  "临时"
	}else{
		return "未知"
	}
}
function formatterPhoto(value){
	return "<img src='<%=ImgProperties.LOAD_PATH%>value'/>"
}


