var tree ;
//房间
var table = $('#table1');
function tableRefresh(dataUrl){
	
	table.bootstrapTable('refresh',{url:dataUrl});

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
		    if (typeof(tree) != "undefined") {
		    	tree.refresh();
		    }
     }
	}); 
}



$(document).ready(function () {
	initTree('1');
});



/*添加角色*/
function addsubmit(){
	var energyEnterType=$("#energyEnterType").val();
	var energyEnterDay;
	if(energyEnterType=="0"){
		energyEnterDay =$("#energyEnterDay1").val();
	 }
	 if(energyEnterType=="1"){
		 energyEnterDay=$("#energyEnterDay2").val();
	 }
	
	var param={
			"buildName" : $("#buildName").val(),
			"buildType" : $("#buildType").val(),
			"buildImg" : $("#buildImg").val(),   				
	   	 	"remark" :   $("#remark").val(),
	   	 	"parentId" : $("#parentId").val(),
	    	"status" : $("#activated").val(),
	    	"buildArea":$("#buildArea").val(),
	    	"energyPaymentDay":$("#energyPaymentDay").val(),
		    "energyEnterType":energyEnterType,
		    "energyEnterDay":energyEnterDay
	}
	
		param.energyBillDate = $("#open_m").val()+"-"+$("#open_d").val();
		
	if($("#buildType").val()=='floor'){
		param.dian = '0'+$("#dian_share").val()+$("#dian_used").val();
		param.shui = '1'+$("#shui_share").val()+$("#shui_used").val();
		param.ranqi = '2'+$("#ranqi_share").val()+$("#ranqi_used").val();
		param.kongtiao = '3'+$("#kongtiao_share").val()+$("#kongtiao_used").val();
	}
	
	
	executeAdd("/floor/addFloor.do",param)
}


/**新增楼层区*/
function addChildren(){
	var id=$("#id").val();
	var buildType=$("#buildType").val();
	var name="";
	if(buildType=="floor"){
		buildType="storey";
		name="层";
	}else if(buildType=="storey"){
		buildType="area"
		name="区"
	}
	
	openAddPage('新增'+name,'60%','80%',basePath+'/floor/addPage.do?parentId='+id+'&buildType='+buildType,'');
}


/*更新楼层区*/
function updatesubmit(){
	
	
	swal({
        title: "确认更新？",
        text: "",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        cancelButtonText: "取消",
        confirmButtonText: "确认",
        closeOnConfirm: false
    }, function () {
    	
    	var energyEnterType=$("#energyEnterType").val();
    	var energyEnterDay;
    	if(energyEnterType=="0"){
    		energyEnterDay =$("#energyEnterDay1").val();
    	 }
    	 if(energyEnterType=="1"){
    		 energyEnterDay=$("#energyEnterDay2").val();
    	 }
    	
    	var param={
    			"id"       : $("#id").val(),
    			"buildName" : $("#buildName").val(),
    			"buildType" : $("#buildType").val(),
    			"buildImg" : $("#buildImg").val(),   				
    	   	 	"remark" :   $("#remark").val(),
    	   	 	"parentId" : $("#parentId").val(),
    	    	"status" : $("#activated").val(),
    	    	"buildArea":$("#buildArea").val(),
    	    	"energyPaymentDay":$("#energyPaymentDay").val(),
    		    "energyEnterType":energyEnterType,
    		    "energyEnterDay":energyEnterDay
    	}
    	
    	param.energyBillDate = $("#open_m").val()+"-"+$("#open_d").val();
    	
    	if($("#buildType").val()=='floor'){
    		if($("#dian_id").val()==null || $("#dian_id").val()==""){
    			param.dian = '0'+$("#dian_share").val()+$("#dian_used").val();
    			param.shui = '1'+$("#shui_share").val()+$("#shui_used").val();
    			param.ranqi = '2'+$("#ranqi_share").val()+$("#ranqi_used").val();
    			param.kongtiao = '3'+$("#kongtiao_share").val()+$("#kongtiao_used").val();
    		}else{
    			param.dian = $("#dian_id").val()+$("#dian_share").val()+$("#dian_used").val();
    			param.shui = $("#shui_id").val()+$("#shui_share").val()+$("#shui_used").val();
    			param.ranqi = $("#ranqi_id").val()+$("#ranqi_share").val()+$("#ranqi_used").val();
    			param.kongtiao = $("#kongtiao_id").val()+$("#kongtiao_share").val()+$("#kongtiao_used").val();
    		}
    	}
    	
       $.post(basePath+"/floor/updateFloor.do",param,function(data){
       if(data.status==10001){
    	   tree.refresh();
    	   swal("更新成功", "", "success");
       }else{
    	   swal( data.msg, "","error");
    	   }
       });
    	
    	
    });
	
	
}

/*增加表格按钮**/
function formatterFun(value,row,index){
	var id=$("#id").val();
	var parkId=$("#parkId").val();
	var infoUrl=basePath+'/floor/getRoomInfo.do?roomId='+row.id+"&userType="+userType;
	var refreshUrl;
	if(id==undefined){
		refreshUrl=basePath+'/floor/getRoomList2.do?parkId='+parkId;
	}else{
		refreshUrl=basePath+'/floor/getRoomList.do?refAreaId='+id;
	}
	var stopUrl=basePath+"/floor/activeOrClosePark.do";
	var showInfo="openAddRoomPage('房间详情','80%','95%','"+infoUrl+"','"+refreshUrl+"')";
	var status=activatedStatus(row.activated);
	var btnS="";
	if(row.activated=="1"){
		btnS="btn-danger";
	}else{
		btnS="btn-primary";
	}
	var param="'"+stopUrl+"'"+","+"'"+row.id+"'"+","+"'"+row.activated+"'"+","+"'"+refreshUrl+"'";
	var stop="stopGroup("+param+")";
	var deleted="deleteRoom('"+row.id+"')";
	return '<button class="btn btn-info" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> '+
	       '&nbsp;&nbsp; '+
	       '<button class="btn '+btnS+'" onclick="'+
	       stop+
	       '"   type="button" >'+status+'</button>'+
		   '&nbsp;&nbsp; '+
		   '<button class="btn btn-info" onclick="'+
		   deleted+
		   '"   type="button" >删除</button>';
			
}

function formatterFun2(value,row,index){
	var id=$("#id").val();
	var parkId=$("#parkId").val();
	var infoUrl=basePath+'/floor/getRoomInfo.do?roomId='+row.id+"&userType="+userType;
	var refreshUrl;
	if(id==undefined){
		refreshUrl=basePath+'/floor/getRoomList2.do?parkId='+parkId;
	}else{
		refreshUrl=basePath+'/floor/getRoomList.do?refAreaId='+id;
	}
	var stopUrl=basePath+"/floor/activeOrClosePark.do";
	var showInfo="openAddRoomPage('房间详情','80%','95%','"+infoUrl+"','"+refreshUrl+"')";
	var status=activatedStatus(row.activated);
	var btnS="";
	if(row.activated=="1"){
		btnS="btn-danger";
	}else{
		btnS="btn-primary";
	}
	var param="'"+stopUrl+"'"+","+"'"+row.id+"'"+","+"'"+row.activated+"'"+","+"'"+refreshUrl+"'";
	var stop="stopGroup("+param+")";
	var deleted="deleteRoom('"+row.id+"')";
	return '<button class="btn btn-info" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> ';
			
}

function deleteRoom(id){
	
	
	swal({
        title: "确认删除房间",
        text: "删除后不可恢复，请谨慎操作！",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        cancelButtonText: "取消",
        confirmButtonText: "确认",
        closeOnConfirm: false
    }, function () {
	
	
	
		param={
			"id":id
		};
		$.post(basePath+"/floor/deleteRoom.do",param,function(data){
		   if(data.status==10001){
			   swal("删除成功", "", "success");
			   var refreshUrl;
			   var id=$("#id").val();
			   if(id==undefined){
				   refreshUrl=basePath+'/floor/getRoomList2.do?parkId='+parkId;
			   }else{
				   refreshUrl=basePath+'/floor/getRoomList.do?refAreaId='+id;
			   }
			   tableRefresh(refreshUrl)
		   }else{
			       swal( data.msg, "","error");
			   }
		   });
    });
	
}

function updateRoom(id){
	
	var showOut=document.getElementById("showOut"); 
// 	alert(showOut.checked); 
	if(showOut.checked == true){
		var showOut= "1";
	}else{
		var showOut= "0";
	}
	swal({
        title: "确认更新？",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        cancelButtonText: "取消",
        confirmButtonText: "确认",
        closeOnConfirm: false
    }, function () {
    	
    	var roomImg;
    	if($("#roomImg").val()!='' && $("#roomImgT").val()!=''){
    		roomImg=$("#roomImg").val()+","+$("#roomImgT").val();
    	}else{
    		roomImg=$("#roomImg").val()+$("#roomImgT").val();
    	}
    	var param={
    			"id"       : id,
    			"roomNum" : $("#roomNum").val(),
    			"orientation" : $("#orientation").val(),
    			"buildArea" : $("#buildArea").val(),   				
    		    "userArea" : $("#userArea").val(),
    		    "layerHigh" : $("#layerHigh").val(),
    		    "located" : $("#located").val(),
    		    "renovation" : $("#renovation").val(),
    		    "monthPrice" : $("#monthPrice").val(),
    		    "dayPrice" : $("#dayPrice").val(),
    		    "floorStatus" : $("#floorStatus").val(),
    		    "remark" : $("#remark").val(),
    		    "roomImg" : roomImg,
    		    "refAreaId" : $("#refAreaId").val(),
    		    "showOut":showOut,
    	}
    	executeUpdate("/floor/updateRoom.do",param)
    	
    	
    });
	
	
}

function addRoom(){
	var roomImg;
	if($("#roomImg").val()!='' && $("#roomImgT").val()!=''){
		roomImg=$("#roomImg").val()+","+$("#roomImgT").val();
	}else{
		roomImg=$("#roomImg").val()+$("#roomImgT").val();
	}
	
	var showOut=document.getElementById("showOut"); 
// 	alert(showOut.checked); 
	if(showOut.checked == true){
		var showOut= "1";
	}else{
		var showOut= "0";
	}
	var param={
			"roomNum" : $("#roomNum").val(),
			"orientation" : $("#orientation").val(),
			"buildArea" : $("#buildArea").val(),   				
		    "userArea" : $("#userArea").val(),
		    "layerHigh" : $("#layerHigh").val(),
		    "located" : $("#located").val(),
		    "renovation" : $("#renovation").val(),
		    "monthPrice" : $("#monthPrice").val(),
		    "dayPrice" : $("#dayPrice").val(),
		    "floorStatus" : $("#floorStatus").val(),
		    "remark" : $("#remark").val(),
		    "roomImg" : roomImg,
		    "refAreaId" : refAreaId,
		    "showOut":showOut,
	}
	executeAdd("/floor/addRoom.do",param)
}

function selectPark(){
	if($("#isActivated").prop("checked")){
		$("#tree").jstree("destroy");
		initTree("")
	}else{
		$("#tree").jstree("destroy");
		initTree("1")
	}
}
function initTree(status){
	var parkId=$("#parkIds").val();
	tree = $("#tree").jstree({
		"core": {
    	"animation": true,
    	"multiple": false,
    	"check_callback": true,
    	"themes": { "stripes": false },
    	'data': {
    		'url': basePath+'/floor/getFloorList.do?parkId='+parkId+"&status="+status,
    		"dataType": "json",
    		'data': function (node) {
    			return { 'id': node.id };
	    		}
	    	}
	    }
	
	}).on("changed.jstree", function (event, data) {
	
	if (data.selected.length > 0) {
		
		var id=data.selected[0];
		$('#tree').jstree('open_node',id);
		
		$.post(basePath+"/floor/getFloorInfo.do",{"floorId":id},function(data){
			
			if(data.status==10001){
				
				var floor=data.data;
				$("#id").val(floor.id);
				$("#buildName").val(floor.buildName);
				$("#buildArea").val(floor.buildArea);
				$("#buildType").val(floor.buildType);
				$("#buildImg2").attr("src",imagePath+floor.buildImg); 
				$("#buildImg").val(floor.buildImg); 				
		   	 	$("#remark").val(floor.remark);
		   	 	$("#parentId").val(floor.parentId);
		   	    $("#energyPaymentDay").val(floor.energyPaymentDay);
		   	    
		   	    if(floor.energyBillDate!=null){
		   	    	var n = floor.energyBillDate.indexOf("-");
		   	    	
		   	    	$("#open_m").val(floor.energyBillDate.substring(0,n));
			   	    $("#open_d").val(floor.energyBillDate.substring(n+1,floor.energyBillDate.length))
		   	    }
		   	    
			    var energyEnterType=document.getElementById("energyEnterType");
				selected(energyEnterType,floor.energyEnterType);
				$("#energyEnterDay11").hide();
				$("#energyEnterDay22").hide();  
				if(floor.energyEnterType=="1"){
					$("#energyEnterDay22").show();
				}else{
					$("#energyEnterDay11").show();
			    }
				var energyEnterDay1=document.getElementById("energyEnterDay1");
				selected(energyEnterType,floor.energyEnterType);
				var energyEnterDay2=document.getElementById("energyEnterDay2");
				selected(energyEnterDay1,floor.energyEnterDay);
				selected(energyEnterDay2,floor.energyEnterDay);
				 
				var activated=document.getElementById("activated");
				selected(activated,floor.status);
				
				var ny = floor.ny;
				if(ny.length>0){
					$("#ts").hide();
					for(var i=0;i<ny.length;i++){
						if(ny[i].energy_type==0){
							$("#dian_id").val(ny[i].id)
							$("#dian_share").val(ny[i].share_type)
							$("#dian_used").val(ny[i].room_amount_used)
						}
						if(ny[i].energy_type==1){
							$("#shui_id").val(ny[i].id)
							$("#shui_share").val(ny[i].share_type)
							$("#shui_used").val(ny[i].room_amount_used)
						}
						if(ny[i].energy_type==2){
							$("#ranqi_id").val(ny[i].id)
							$("#ranqi_share").val(ny[i].share_type)
							$("#ranqi_used").val(ny[i].room_amount_used)
						}
						if(ny[i].energy_type==3){
							$("#kongtiao_id").val(ny[i].id)
							$("#kongtiao_share").val(ny[i].share_type)
							$("#kongtiao_used").val(ny[i].room_amount_used)
						}
					}
					
				}else{
					$("#ts").show();
				}
				
				
				
			}
			$("#updateChildren").show();
			$("#bindUser").show();
			$("#bindFunc").show();
			$("#setRead").show();
			$("#rolefunc0").addClass("active");
			$("#rolefunc1").removeClass("active");
			$("#tab-1").addClass("active");
			$("#tab-2").removeClass("active");    		
			if(floor.buildType=='floor'){
				
				$("#buildTypeName").text("楼名称"); 
				$("#addOp").text("新增层");
				$("#details").text("楼详情");
				$("#buildType").val('floor');
				//if($("#isActivated").prop("checked")){
					$("#addChildren").show();
				//}
				$("#rolefunc1").hide();
				$("#energy").show();
				$("#imgTitle").hide();
			}else if(floor.buildType=='storey'){
				$("#buildTypeName").text("层名称");
				$("#addOp").text("新增区");
				$("#details").text("层详情");
				$("#buildType").val('storey');
				//if($("#isActivated").prop("checked")){
					$("#addChildren").show();
				//}
				$("#rolefunc1").hide();
				$("#energy").hide();
				$("#energyPaymentDay").val("0");
			    $("#openTime2").val("00-00");
			    $("#imgTitle").show();
			}else{
				$("#buildType").val('area');
				$("#addChildren").hide();
				$("#buildTypeName").text("区名称");
				$("#details").text("区详情");
				$("#rolefunc1").show();
				$("#energy").hide();
				$("#energyPaymentDay").val("0");
			    $("#openTime2").val("00-00");
			    $("#imgTitle").hide();
			    var activated2=document.getElementById("activated2");
				selected(activated2,status);
				tableRefresh(basePath+'/floor/getRoomList.do?refAreaId='+floor.id);
				
			}
		
			
		});
		
	    
	}
	
	}).jstree();
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
		tableRefresh(basePath+"/floor/getRoomList2.do?parkId="+id);
		
	}).jstree();
	
});

function checkActivate(){
	$("#addChildren").hide();
	$("#updateChildren").hide();
	$("#rolefunc0").addClass("active");
	$("#rolefunc1").removeClass("active");
	$("#tab-1").addClass("active");
	$("#tab-2").removeClass("active"); 
	$("#rolefunc1").hide();
	$('#form')[0].reset();
	if($("#isActivated").prop("checked")){
		$("#tree").jstree("destroy");
		initTree("")
	}else{
		$("#tree").jstree("destroy");
		initTree("1")
	}
	 
}

function selectEnterType(){
	 $("#energyEnterDay11").hide();
	 $("#energyEnterDay22").hide();    	 
	 if($("#energyEnterType").val()=="0"){
		 $("#energyEnterDay11").show();
	 }
	 if($("#energyEnterType").val()=="1"){
		 $("#energyEnterDay22").show();
	 }
}

function deleteFloor(){
	
	
	swal({
        title: "确认删除？",
        text: "删除后不可恢复，请谨慎操作！",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        cancelButtonText: "取消",
        confirmButtonText: "确认",
        closeOnConfirm: false
    }, function () {
    	
    	var buildType;
    	if($("#buildType").val()=='floor'){
    		buildType='floor';
    	}else if($("#buildType").val()=='storey'){
    		buildType='storey';
    	}else{
    		buildType='area';
    	}
    	param={
    			"id":$("#id").val(),
    			"buildType":buildType
    	};
    	$.post(basePath+"/floor/deleteFloor.do",param,function(data){
    	   if(data.status==10001){
    		   swal("删除成功", "", "success");
    		   $("#tree").jstree("refresh")
    	   }else{
    		       swal( data.msg, "","error");
    	   }
       });
    		
    });
	
	

	
	
}