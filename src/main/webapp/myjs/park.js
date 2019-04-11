//数据表格id
var table = $('#table1');
function tableRefresh(dataUrl){
	table.bootstrapTable('refresh',{url:dataUrl});
}
/*增加表格按钮**/
function formatterFun(value,row,index){
	
	var infoUrl=basePath+'/park/getParkInfo.do?parkId='+row.id;
	var refreshUrl=basePath+'/park/getParkList.do';
	var stopUrl=basePath+"/park/activeOrClosePark.do";
	
	var showInfo="openAddPage('园区详情','80%','95%','"+infoUrl+"','"+refreshUrl+"')";
	var status=activatedStatus(row.activated);
	var btnS="";
	if(row.activated=="1"){
		btnS="btn-danger";
	}else{
		btnS="btn-primary";
	}
	var param="'"+stopUrl+"'"+","+"'"+row.id+"'"+","+"'"+row.activated+"'"+","+"'"+refreshUrl+"'";
	var stop="stopGroup("+param+")";
	return '<button class="btn btn-info" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> '+
	       '&nbsp;&nbsp; '+
	       '<button class="btn '+btnS+'" onclick="'+
	       stop+
	       '"   type="button" >'+status+'</button>';
}

function addsubmit(id){
	var param={
		"id":id,
		"parkCode" : $("#parkCode").val(),
		"parkName" : $("#parkName").val(),
		"parkTitle" : $("#parkTitle").val(),   				
	    "parkDescribe" : $("#parkDescribe").val(),
	    "address":$("#address").val(),
	    "addressImg" : $("#addressImg").val(),
	    "contacts" : $("#contacts").val(),
	    "mobile" : $("#mobile").val(),
	    "spareMobile" : $("#spareMobile").val(),
	    "qq" : $("#qq").val(),
	    "wechat" : $("#wechat").val(),
	    "wechatQr" : $("#wechatQr").val(),
	    "policy" : $("#policy").val(),
	    "parkImg" : $("#parkImg").val(),
	    "approval" : $("#approval").val(),
	    "financeApproval" : $("#financeApproval").val(),
	    "parkGroupId" : $("#parkGroupId").val(),
	    "activated" : $("#activated").val(),
	    "city":$("#cityChoice").val()
	}
	executeAdd("/park/addPark.do",param);
}

function updatesubmit(id){
	var param={
		"id"       : id,
		"parkCode" : $("#parkCode").val(),
		"parkName" : $("#parkName").val(),
		"parkTitle" : $("#parkTitle").val(),   				
	    "parkDescribe" : $("#parkDescribe").val(),
	    "address":$("#address").val(),
	    "addressImg" : $("#addressImg").val(),
	    "contacts" : $("#contacts").val(),
	    "mobile" : $("#mobile").val(),
	    "spareMobile" : $("#spareMobile").val(),
	    "qq" : $("#qq").val(),
	    "wechat" : $("#wechat").val(),
	    "wechatQr" : $("#wechatQr").val(),
	    "policy" : $("#policy").val(),
	    "parkImg" : $("#parkImg").val(),
	    "approval" : $("#approval").val(),
	    "financeApproval" : $("#financeApproval").val(),
	    "parkGroupId" : $("#parkGroupId").val(),
	    "activated" : $("#activated").val(),
	    "city":$("#cityChoice").val()
	}
	executeUpdate("/park/updatePark.do",param);
	
 }


