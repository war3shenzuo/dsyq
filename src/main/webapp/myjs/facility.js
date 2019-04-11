var table = $('#table1');
function tableRefresh(dataUrl){
	
	table.bootstrapTable('refresh',{url:dataUrl});

}
/*增加表格按钮**/
function formatterFun(value,row,index){
	
	var infoUrl=basePath+'/facility/getEtopFacilityInfo.do?EtopFacilityId='+row.id;
	var refreshUrl=basePath+'/facility/getEtopFacilityList.do';
	var stopUrl=basePath+"/facility/activeOrClose.do";
	var showInfo="openAddPage('设施详情','50%','70%','"+infoUrl+"','"+refreshUrl+"')";
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
	       '"   type="button" >编辑</button> '+
	       '&nbsp;&nbsp; '+
	       '<button class="btn '+btnS+'" onclick="'+
	       stop+
	       '"   type="button" >'+status+'</button>';
}

/*增加表格按钮**/
function formatterFun2(value,row,index){
	
	var infoUrl=basePath+'/facility/getEtopFacilityInfo.do?EtopFacilityId='+row.id;
	var refreshUrl=basePath+'/facility/getEtopFacilityList.do?parkId='+$("#parkId").val();
	var showInfo="openAddPage('用户详情','30%','55%','"+infoUrl+"','"+refreshUrl+"')";
	return '<button class="btn btn-info" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> ';

}


/**添加咨询*/
function addsubmit(){
	var param={
			"facilityName" : $("#facilityName").val(),
			"facilityType" : $("#category").val(),  				
			"facilityPrice" : $("#facilityPrice").val(),
			"description": $("#description").val(),
			"remark": $("#remark").val(),
			"startTime": $("#startTime").val(),
			"endTime": $("#endTime").val()
	}
	executeAdd("/facility/addEtopFacility.do",param)
}

function updatesubmit(id){
	var param={
			"id"       :  $("#id").val(),
			"facilityName" : $("#facilityName").val(),
			"facilityType" : $("#facilityType").val(),  				
			"facilityPrice" : $("#facilityPrice").val(),
			"description": $("#description").val(),
			"remark": $("#remark").val(),
			"startTime": $("#startTime").val(),
			"endTime": $("#endTime").val()
	}
	executeUpdate("/facility/updateEtopFacility.do",param)
}

var parktree ;
$(document).ready(function () {
	parktree = $("#parktree").jstree({
			"core": {
        	"animation": true,
        	"multiple": false,
        	"check_callback": true,
        	"themes": { "stripes": false },
        	'data': {
        		'url': basePath+'/etopService/getParkServiceList.do',
        		"dataType": "json",
        		'data': function (node) {
        			return { 'id': node.id };
        		}
        	}
        }

    }).on("changed.jstree", function (event, data) {
    	
    	var id=data.selected[0];
    	$('#parktree').jstree('open_node',id);
    	$("#parkId").val(id);
		tableRefresh(basePath+'/facility/getEtopFacilityList.do?parkId='+id);
    	
		
	}).jstree();
	
});

