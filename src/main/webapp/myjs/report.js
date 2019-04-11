var tree ;
var floorTree;

var table = $('#tableReport');
var table1 = $("#faiginReport");
var table2 = $("#balanceReport");

function formatter(value) {
	switch (value) {
		case "1":
			return "租赁合同";
		case "2":
			return "外包合同";
		case "3":
			return "能源合同";
		case "4":
			return "物业合同";
		case "5":
			return "服务合同";
		default:
			return "未知";
	}
}

function tableRefresh(dataUrl){
	table.bootstrapTable('refresh',{url:dataUrl});
}
function tableRefresh1(dataUrl){
	table1.bootstrapTable('refresh',{url:dataUrl});
}
function tableRefresh2(dataUrl){
	table2.bootstrapTable('refresh',{url:dataUrl});
}

function queryBalance(params) {
	params.start = $("#start").val();
	params.end = $("#end").val();
	return params
}

function queryParams(params) {
	params.startTime = $("#startTime").val();
	params.endTime = $("#endTime").val();
	return params
}

function getBalanceList(){
	var id = $("#id").val();
	var parkId = $("#parkId").val();
	var type = $("#type").val();
	table2.bootstrapTable('refresh',{url:'getBalanceStaticList.do?id=' + id + '&parkId='+ parkId + '&type=' + type});
}

function getFaiginList(){
	var id = $("#id").val();
	var parkId = $("#parkId").val();
	var type = $("#type").val();
	table1.bootstrapTable('refresh',{url:'getFaiginList.do?id=' + id + '&parkId='+ parkId + '&type=' + type});
}

function resetBalance(){
	$("#start").val("");
	$("#end").val("");
}

function resetFaigin(){
	$("#startTime").val("");
	$("#endTime").val("");
}

/**open addPage*/
function openAddRoomPage(title,height,width,addUrl,refreshUrl){
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
		}
	});
}


$(document).ready(function () {

	$('.date').datepicker(

		{todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,autoclose:!0}

	)

	tree = $("#tree").jstree({
			"core": {
        	"animation": true,
        	"multiple": false,
        	"check_callback": true,
        	"themes": { "stripes": false },
        	'data': {
        		'url': basePath+'/park/getParkName.do',
        		"dataType": "json",
        		'data': function (node) {
        			return { 'id': node.id };
        		}
        	}
        }

    }).on("changed.jstree", function (event, data) {

    	var id = data.selected[0];
    	$('#tree').jstree('open_node',id);
    	$.post(basePath+"/etopReport/getEtopRentsList.do",{"parkId":id},function(data){
    		if(data.status == 10001){
				var num = data.data;
				$("#userNum").text(num.userNum);
				$("#companyNum").text(num.companyNum);
				$("#contractNum").text(num.contractNum);
				$("#serviceNum").text(num.serviceNum);
				$("#serviceCompleteNum").text(num.serviceCompleteNum);
			}

    	});

		$("#floorTree").jstree("destroy");

		var parkId = id;
		$("#parkId").val(parkId);

		floorTree = $("#floorTree").jstree({
			"core": {
				"animation": true,
				"multiple": false,
				"check_callback": true,
				"themes": { "stripes": false },
				'data': {
					'url': basePath+'/floor/getReportFloorList.do?parkId=' + parkId,
					"dataType": "json",
					'data': function (node) {
						return { 'id': node.id };
					}
				}
			}

		}).on("changed.jstree", function (event, data) {

			if (data.selected.length > 0) {

				var id = data.selected[0];
				var type = data.node.original.type;
				if($('#floorTree').jstree('is_closed',id))
				{
					$('#floorTree').jstree('open_node',id);
				}else
				{
					$('#floorTree').jstree('close_node',id);
				}
				$("#id").val(id);
				if(type == "room"){
					$.post(basePath+"/floor/getRoomInfo.do",{"roomId":id},function(data){

						tableRefresh(basePath+'/etopReport/getRentsList.do?id=' + id + '&parkId='+ parkId);
						tableRefresh1(basePath+'/etopReport/getFaiginList.do?id=' + id + '&parkId='+ parkId);
						tableRefresh2(basePath+'/etopReport/getBalanceStaticList.do?id=' + id + '&parkId='+ parkId);
						/*if(data.status == 10001){
							var floor = data.data;
							if(floor != null){
								$("#type").val(floor.buildType);
								tableRefresh(basePath+'/etopReport/getRentsList.do?id=' + id + '&parkId='+ parkId + '&type=' + floor.buildType);
								tableRefresh1(basePath+'/etopReport/getFaiginList.do?id=' + id + '&parkId='+ parkId + '&type=' + floor.buildType);
							}else{
								tableRefresh(basePath+'/etopReport/getRentsList.do?id=' + id + '&parkId='+ parkId);
								tableRefresh1(basePath+'/etopReport/getFaiginList.do?id=' + id + '&parkId='+ parkId);
							}

						}*/

					});
				}else{
					$.post(basePath+"/floor/getFloorInfo.do",{"floorId":id},function(data){

						if(data.status == 10001){
							var floor = data.data;
							if(floor != null){
								$("#type").val(floor.buildType);
								tableRefresh(basePath+'/etopReport/getRentsList.do?id=' + id + '&parkId='+ parkId + '&type=' + floor.buildType);
								tableRefresh1(basePath+'/etopReport/getFaiginList.do?id=' + id + '&parkId='+ parkId + '&type=' + floor.buildType);
								tableRefresh2(basePath+'/etopReport/getBalanceStaticList.do?id=' + id + '&parkId='+ parkId + '&type=' + floor.buildType);
							}/*else{
								tableRefresh(basePath+'/etopReport/getRentsList.do?id=' + id + '&parkId='+ parkId);
								tableRefresh1(basePath+'/etopReport/getFaiginList.do?id=' + id + '&parkId='+ parkId);
							}*/

						}

					});
				}
			}
		}).jstree();
	}).jstree();

});


