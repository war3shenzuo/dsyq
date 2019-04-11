var table = $('#table1');
/**表格刷新*/
function tableRefresh(dataUrl){
	
	table.bootstrapTable('refresh',{url:dataUrl});
	
}
/*增加表格按钮**/
function formatterFun(value,row,index){
	
	var infoUrl=basePath+'/parkgroup/getParkGroupInfo.do?parkGroupId='+row.id;
	var refreshUrl=basePath+'/parkgroup/getParkGroupList.do';
	var stopUrl=basePath+"/parkgroup/stopParkGroup.do";
	var showInfo="openAddPage('园区组详情','80%','95%','"+infoUrl+"','"+refreshUrl+"')";
	var status=activatedStatus(row.activated);
	
	var btnS="";
	if(row.activated=="1"){
		btnS="btn-danger";
	}else{
		btnS="btn-primary";
	}
	var param="'"+stopUrl+"'"+","+"'"+row.id+"'"+","+"'"+row.activated+"'"+","+"'"+refreshUrl+"'";
	var stop="stopGroup("+param+")";
	var res =  '<button class="btn btn-info" onclick="'+
		   showInfo+
	       '"type="button" >详情</button>'+
	       '&nbsp;&nbsp;'+
	       '<button class="btn '+btnS+'" onclick="'+
	       stop+
	       '"type="button" >'+status+'</button>';
	
	if(row.parkGroupType=='0'){
		res = res+'&nbsp;&nbsp;<button class="btn btn-warning" onclick="formal(\''+row.id+'\')"  type="button" >转正</button>';
		
	}
			
	return res
		   
}

function formal(id){
	var param ={"id":id,"parkGroupType":"1"}
	$.post(basePath+"/parkgroup/updateParkGroup.do",param,function(data){
		if(data.status==10001){
			swal(   status+"成功！", "","success");
			tableRefresh(basePath+'/parkgroup/getParkGroupList.do');
	    }else{
	    	swal(   status+"失败！", "","error");
	    }
	});
}
/*描述截取**/
function formatterDescribe(value,row,index){
	var theTxt=row.parkGroupDescribe;
	if(theTxt!=null){
		if(theTxt.length>30){
			theTxt=theTxt.substring(0, 30)+"...";
		}
		return '<p title="'+row.parkGroupDescribe+'">'+theTxt+'</p>'
	}else{
		return '<p title="'+row.parkGroupDescribe+'"></p>'
		
	}
}
/*添加*/
function addsubmit(id){
	
	var param={
			"id":id,
			"parkGroupName" : $("#parkGroupName").val(),
			"parkGroupCode" : $("#parkGroupCode").val(),
			"parkCount":$("#parkCount").val(),
			"parkGroupDescribe" : $("#parkGroupDescribe").val(),
			"introduce":$("#introduce").val(),
			"parkGroupImg":$("#parkImg").val(),
			"approval" : $("#approval").val(),   				
	   		"activated" : $("#activated").val(),
	   		"parkName" :$("#parkName").val(),
	   		"address":$("#address").val(),
	   		"parkType":$("#parkType").val(),
	   		"openTime":$("#openTime").val(),
	   		"belongUnit":$("#belongUnit").val(),
	   		"operateUnit":$("#operateUnit").val(),
	   		"parkSize":$("#parkSize").val(),
	   		"rentalRate":$("#rentalRate").val(),
	   		"contacts":$("#contacts").val(),
	   		"mobile":$("#mobile").val(),
	   		"qq":$("#qq").val(),
	   		"wechat":$("#wechat").val(),
	   		"email":$("#email").val(),
	   		"city":$("#cityChoice").val(),
	   		"sld":$("#sld").val(),
	   		"link":$("#link").val(),
	   		"logo":$("#logo").val()
	   		
	}
	executeAdd("/parkgroup/addParkGroup.do",param);
}
/*更新*/
function updatesubmit(id){
	var param={
			"id"       : id,
			"parkGroupName" : $("#parkGroupName").val(),
			"parkGroupCode" : $("#parkGroupCode").val(),
			"parkCount":$("#parkCount").val(),
			"parkGroupDescribe" : $("#parkGroupDescribe").val(),
			"introduce":$("#introduce").val(),
			"parkGroupImg":$("#parkImg").val(),
			"account" : $("#account").val(),   				
	   		"activated" : $("#activated").val(),
	   		"parkName" :$("#parkName").val(),
	   		"address":$("#address").val(),
	   		"parkType":$("#parkType").val(),
	   		"openTime":$("#openTime").val(),
	   		"belongUnit":$("#belongUnit").val(),
	   		"operateUnit":$("#operateUnit").val(),
	   		"parkSize":$("#parkSize").val(),
	   		"rentalRate":$("#rentalRate").val(),
	   		"contacts":$("#contacts").val(),
	   		"mobile":$("#mobile").val(),
	   		"qq":$("#qq").val(),
	   		"wechat":$("#wechat").val(),
	   		"email":$("#email").val(),
	   		"city":$("#cityChoice").val(),
	   		"link":$("#link").val(),
	   		"logo":$("#logo").val()
	}
	if(read!=""){
		executeUpdate2("/parkgroup/updateParkGroup.do",param);
	}else{
		executeUpdate("/parkgroup/updateParkGroup.do",param);
	}
}
