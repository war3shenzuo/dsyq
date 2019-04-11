var table = $('#table1');
function tableRefresh(dataUrl){
	
	table.bootstrapTable('refresh',{url:dataUrl});

}

var table2 = $('#table2');
function tableRefresh2(dataUrl){
	
	table2.bootstrapTable('refresh',{url:dataUrl});

}


function refreshServiceTable(){
	if(serviceTYPE==0){
		tableRefresh(basePath+"/etopService/getServiceList.do");
	}else if(serviceTYPE==1){
		tableRefresh(basePath+'/purchase/getServiceList.do');
		tableRefresh2(basePath+'/purchase/goods.do');
	}else if(serviceTYPE==2){
		tableRefresh(basePath+"/recruitment/getServiceList.do");
	}else if(serviceTYPE==3){
		tableRefresh(basePath+"/serviceFacility/getServiceList.do");
	}
}
/**open addPage*/
function openTablePage(title,height,width,addUrl,refreshUrl){
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
/*增加表格按钮**/
function formatterPurchaseFun(value,row,index){
	
	var infoUrl=basePath2+'/getserviceInfo.do?serviceId='+row.serviceId;
	var showInfo="openSelectPage('服务详情','80%','95%','"+infoUrl+"',refreshServiceTable)";
	if(row.serviceStatus==101){
		return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> '+
	       
	       '&nbsp;&nbsp; '+
		    '<button class="btn btn-primary" onclick="'+
		    "updateStatus('"+row.serviceId+"'"+",202)"+
		    '"   type="button" >审核</button>'+
		       
	       '&nbsp;&nbsp; '+
		    '<button class="btn btn-danger" onclick="'+
		    "updateStatus('"+row.serviceId+"'"+",301)"+
		    '"   type="button" >拒绝</button>'
		    ;
	}else if(row.serviceStatus==202){
		return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> '
		   +
	       '&nbsp;&nbsp; '+
		    '<button class="btn btn-primary" onclick="'+
		    "updateStatus('"+row.serviceId+"'"+",204)"+
		    '"   type="button" >完工</button>';
		
	}else if(row.serviceStatus==300){
		return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> ';
	}else{
		return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> ';
	}
}


/*增加表格按钮
 * 101，待审批；102，待回执；201，已撤销；202，已审批；203，已派工；204，已完工；300，完结
 * **/
function formatterRecruitmentFun(value,row,index){
	
	var infoUrl=basePath2+'/getserviceInfo.do?serviceId='+row.serviceId+"&status="+row.serviceStatus;
	var refreshUrl = basePath+'/recruitment/getServiceList.do';
	var showInfo="openSelectPage('服务详情','80%','95%','"+infoUrl+"','"+refreshUrl+"')";
	var resumeUrl=basePath +'/resume/index.do?serviceId='+row.serviceId+"&status="+row.serviceStatus;
//	var showInfo="openSelectPage('服务详情','80%','95%','"+infoUrl+"',refreshServiceTable)";
	var resumeInfo="openSelectPage('派工','70%','65%','"+resumeUrl+"',refreshServiceTable)";
	var examineUrl = basePath +"/recruitment/examine.do?serviceId="+row.serviceId;
	var examineInfo = "openAddPage('审核报价','50%','70%','"+examineUrl+"','"+refreshUrl+"')";
	var dispatchUrl = basePath +"/quotation/dispatch.do?service_id="+row.serviceId;
	var showDispatch = "openAddPage('派工','50%','60%','"+dispatchUrl+"','"+refreshUrl+"')";
	if(row.serviceStatus==101 & row.totalPrice != 0){
		return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> '+
	       '&nbsp;&nbsp; '+
		    '<button class="btn btn-primary" onclick="'+
		    examineInfo+
		    '"   type="button" >审核</button>'+
		       
		       '&nbsp;&nbsp; '+
			    '<button class="btn btn-danger" onclick="'+
			    "updateStatus('"+row.serviceId+"'"+",301)"+
			    '"   type="button" >拒绝</button>';
	}else if(row.serviceStatus==101 & row.totalPrice == 0){
			return '<button class="btn btn-primary" onclick="'+
			showInfo+
			'"   type="button" >详情</button> '+
			'&nbsp;&nbsp; '+
			'<button class="btn btn-primary" onclick="'+
			"updateStatus('"+row.serviceId+"'"+",202)"+
			'"   type="button" >审核</button>'+
			
			'&nbsp;&nbsp; '+
			'<button class="btn btn-danger" onclick="'+
			"updateStatus('"+row.serviceId+"'"+",301)"+
			'"   type="button" >拒绝</button>';
	}else if(row.serviceStatus==202){
		return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> '+
	       '&nbsp;&nbsp; '+
	       '<button class="btn btn-danger" onclick="'+
		   resumeInfo+
	       '"   type="button" >派工</button> ';

	}else if(row.serviceStatus==203){
		return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> '+
	       '&nbsp;&nbsp; '+
	       '<button class="btn btn-danger" onclick="'+
		   resumeInfo+
	       '"   type="button" >派工</button> '+
		    '&nbsp;&nbsp; '+
		    '<button class="btn btn-danger" onclick="'+
		    "updateStatus('"+row.serviceId+"'"+",204)"+
		    '"   type="button" >完工</button>';

	}else if(row.serviceStatus==204){
		return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> '+
	       '&nbsp;&nbsp; '+
	       '<button class="btn btn-danger" onclick="'+
		   resumeInfo+
	       '"   type="button" >派工</button> ';
		
		
	}else if(row.serviceStatus==300){
		return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> ';
	}else if(row.serviceStatus==301){
		return '<button class="btn btn-primary" onclick="'+
		showInfo+
		'"   type="button" >详情</button> ';
	}else{
		return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> ';
	}
}


/*增加表格按钮**/
function formatterPurchaseFun2(value,row,index){
	
	var infoUrl=basePath2+'/getserviceInfo.do?serviceId='+row.serviceId;
	//var refreshUrl=basePath+'/park/getParkList.do';
	var showInfo="openAddPage('服务详情','80%','95%','"+infoUrl+"','')";
	return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> ';
}

function formatterPurchaseFun3(value,row,index){
	
	 var submitOver = "submitOver('" + row.serviceId + "')";
//	var submitOver = basePath+'/serviceFacility/addBill.do?serviceId='+row.serviceId;
	var infoUrl=basePath2+'/getserviceInfo.do?serviceId='+row.serviceId;
	var showInfo="openSelectPage('服务详情','80%','95%','"+infoUrl+"',refreshServiceTable)";
	if(row.serviceStatus==101){
		return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> '+
	       
	       '&nbsp;&nbsp; '+
		    '<button class="btn btn-primary" onclick="'+
		    submitOver+
		    '"   type="button" >审核</button>'+
		       
	       '&nbsp;&nbsp; '+
		    '<button class="btn btn-danger" onclick="'+
		    "updateStatus('"+row.serviceId+"'"+",301)"+
		    '"   type="button" >拒绝</button>'
		    ;
	}else{
		return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> ';
	}
}

function submitOver(serviceId){
    swal({
            title: "确认审批并出账? ",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "是",
            cancelButtonText: "否",
            closeOnConfirm: false
        }, function () {
            $.get("../serviceFacility/addBill.do?serviceId="+serviceId, {

            }, function (data) {
                if (data.status == 10001) {
                    swal({
                        title: "已出账! ",
                        type: "success",
                        confirmButtonText: "确定",
                        closeOnConfirm: true
                    }, function () {
						window.location.reload();
                    });
                } else {
                    swal({
                        title: data.msg,
                        type: "error",
                        confirmButtonText: "确定",
                        closeOnConfirm: true
                    }, function () {
						window.location.reload();
                    });
                }

            })
        }
    );
}
function formatterFacilityFun(value,row,index){
	if(row.expirationTime==1){
		return formatterPurchaseFun2(value,row,index);
	}else{
		return formatterPurchaseFun3(value,row,index);
	}
}

/*增加表格按钮**/
function formatterFun(value,row,index){
	
	var infoUrl=basePath+'/etopService/getserviceInfo.do?serviceId='+row.serviceId;
	//var refreshUrl=basePath+'/park/getParkList.do';
	var showInfo="openAddPage('服务详情','70%','70%','"+infoUrl+"','')";
	return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >编辑</button> ';
}

/*增加表格按钮**/
function formatterFun2(value,row,index){
	
	var infoUrl=basePath+'/etopService/getServiceTypeInfo.do?serviceCode='+row.serviceCode;
	var refreshUrl=basePath+'/etopService/getServiceTypeList.do';
	var showInfo="openTablePage('服务类型详情','60%','70%','"+infoUrl+"','"+refreshUrl+"')";
	var status=activatedStatus(row.activated);
	var stop="stopGroup('"+row.serviceCode+"'"+","+"'"+row.activated+"')";
	return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >编辑</button> '+
	       '&nbsp;&nbsp; '+
	       '<button class="btn btn-primary" onclick="'+
	       stop+
	       '"   type="button" >'+status+'</button>';
}

function updateStatus(id,status){

	
	swal({
        title: "是否确认? ",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: false
    }, function () {

    	var param ={"serviceId":id,"status":status}
	$.post(basePath+"/etopService/updateStatus.do",param,function(data){
		if(data.status==10001){
			swal( "操作成功！", "","success");
			refreshServiceTable();
	    }else{
	    	swal( "操作失败！", "","error");
	    }

	});
    });
}

/**停用激活*/
function stopGroup(id,activated){
	if(activated=='1'){
		activated='0';
	}else{
		activated='1';
	}
	var param ={"serviceCode":id,"activated":activated}
	var status=activatedAndStopStatus(activated);
	$.post(basePath+"/etopService/activeOrClosePark.do",param,function(data){
		if(data.status==10001){
			swal(   status+"成功！", "","success");
			getPGService($("#pgId").val());
			tableRefresh(basePath+'/etopService/getServiceTypeList.do');
	    }else{
	    	swal(   status+"失败！", "","error");
	    }

	});
}
var tree ;
$(document).ready(function () {
	tree = $("#tree").jstree({
			"core": {
        	"animation": true,
        	"multiple": false,
        	"check_callback": true,
        	"themes": { "stripes": false },
        	'data': {
        		'url': basePath+'/etopService/getPGServiceList.do',
        		"dataType": "json",
        		'data': function (node) {
        			return { 'id': node.id };
        		}
        	}
        }

    }).on("changed.jstree", function (event, data) {
    	
    	/*$("#rolefunc0").addClass("active");
		$("#rolefunc1").removeClass("active");
    	$("#tab-1").addClass("active");
		$("#tab-2").removeClass("active");*/
		$("#bindUser").show();
		tableRefresh(basePath+"/etopService/getServiceTypeList.do");
    	var id=data.selected[0];
    	$('#tree').jstree('open_node',id);
    	var text=data.node.text;
    	$("#pgId").val(id);
    	$("#pgName").text(text);
    	
    	getPGService(id)
	}).jstree();
	
});


function getPGService(pgId){
	$("#pgService").children().remove();
	var param={parkGroupId:pgId};
	$.post(basePath+"/etopService/getPGServiceTypeList.do",param,function(data){
	   if(data.length>0){
		   var list=data;
		   var s="";
		   for(var i=0 ;i<list.length;i++){
			    s=s+'<li class="list-group-item">'+
				  '<span class="badge badge-primary"><a href="javascript:void(0);" '+
				  'onclick="unindbService('+"'"+list[i].serviceCode+"'"+')"'+
                  '>X</a></span>'+
				  list[i].serviceName+"("+list[i].title+")"+
				  '</li>';
			   
		   }
		   $("#pgService").append(s);
	   }else{
		   swal( data.msg, "","error");
	   }
    });
}

function unindbService(serviceCode){
	var pgId=$("#pgId").val();
	var param={parkGroupId:pgId,typeIds:serviceCode};
	
	$.post(basePath+"/etopService/unbindServiceTypeList.do",param,function(data){
		if(data.status==10001){
			swal("更新成功", "", "success");
			getPGService(pgId); 
	    }else{
		   swal(data.msg, "","error");
	    }
	});
	
}


/*复选框*/
function formatterCheckbox(value,row,index){
	
	if(row.activated=="1"){
		var flag=true;
		var s="";
		var lis=$("#pgService").find("li");
		for(var i=0;i<lis.length;i++){
			if(lis[i].innerHTML.indexOf(row.serviceCode)>0){
				flag=false;
			}
		}
		if(flag){
			return '<input name="myCheckbox" value="'+row.serviceCode+'" type="checkbox" class="i-checks" />'
		}else{
			return '<input name="myCheckbox" checked value="'+row.serviceCode+'" type="checkbox" class="i-checks" />'
		}
	}
	
}


//选择值传递给父页面
function bindService(){
	var obj=document.getElementsByName('myCheckbox'); //选择所有name="'test'"的对象，返回数组 
	var s=''; 
	for(var i=0; i<obj.length; i++){ 
		if(obj[i].checked) s+=obj[i].value+','; //如果选中，将value添加到变量s中 
	} 
	if(s!=''){
		s=s.substring(0, s.length-1);

		var pgId=$("#pgId").val();
		var param={parkGroupId:pgId,typeIds:s};
		
		$.post(basePath+"/etopService/bindServiceTypeList.do",param,function(data){
			if(data.status==10001){
				swal("更新成功", "", "success");
				getPGService(pgId); 
		    }else{
			   swal(data.msg, "","error");
		    }
		});
	}
}


function addServiceTypePage(){
	openTablePage('新增服务类型','60%','70%',basePath+'/etopService/addServiceTypePage.do',basePath+'/etopService/getServiceTypeList.do');
}

function addsubmit(){
	var param={
			"serviceCode":$("#serviceCode").val(),
			"serviceName":$("#serviceName").val(),
			"title":$("#title").val(),
			"item":$("#item").val(),
			"descript":$("#descript").val(),
		    "activated":$("#activated").val(),
		    "imgUrl":$("#img").val()
	}
	
	$.post(basePath+"/etopService/addServiceType.do",param,function(data){
		   if(data.status==10001){
				swal("保存成功", "", "success",function(){   alert("aa") });
				
				
				swal({   title: "保存成功！",   
						 type: "success",  
						 confirmButtonText: "ok",  
						 closeOnConfirm: false 
					  }, 
					  function(){  
							 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							 parent.layer.close(index); 
					  }
					 );
				
				
		   }else{
			   swal( data.msg, "","error");
		   }
	   });
	
 }

function updatesubmit(){
	var param={
			"serviceCode":$("#serviceCode").val(),
			"serviceName":$("#serviceName").val(),
			"title":$("#title").val(),
			"item":$("#item").val(),
			"descript":$("#descript").val(),
		    "activated":$("#activated").val(),
		    "imgUrl":$("#img").val()
	}
	
	$.post(basePath+"/etopService/updateServiceType.do",param,function(data){
		   if(data.status==10001){
				swal("保存成功", "", "success",function(){   alert("aa") });
				
				
				swal({   title: "保存成功！",   
						 type: "success",  
						 confirmButtonText: "ok",  
						 closeOnConfirm: false 
					  }, 
					  function(){  
							 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							 parent.layer.close(index); 
					  }
					 );
				
				
		   }else{
			   swal( data.msg, "","error");
		   }
	   });
	
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
    	if(serviceTYPE==0){
    		tableRefresh(basePath+"/etopService/getServiceList.do?parkId="+id);
    	}else if(serviceTYPE==1){
    		tableRefresh(basePath+'/purchase/getServiceList.do?parkId='+id);
			tableRefresh2(basePath+'/purchase/goods.do?parkId='+id);
    	}else if(serviceTYPE==2){
    		tableRefresh(basePath+"/recruitment/getServiceList.do?parkId="+id);
    	}else if(serviceTYPE==3){
    		tableRefresh(basePath+"/serviceFacility/getServiceList.do?parkId="+id);
    	}
		
	}).jstree();
	
});


/*是否过期**/
function formatterExpiration(value,row,index){
	if(value ==1){
		return "是";
	}else{
		return "否";
	}
}

