var table = $('#table1');
function tableRefresh(dataUrl){
	
	table.bootstrapTable('refresh',{url:dataUrl});

}
function formatterAffirm(value,row,index){
	if(value==1){
		return "已确认";
	}else{
		return "未确认";
	}
}
/*增加表格按钮**/
function formatterFun(value,row,index){
	
	var infoUrl=basePath+'/resume/getEtopResumeInfo.do?etopResumeId='+row.id;
	var getInfo="getInfo('"+row.id+"')";
	var deleteInfo="deleteInfo('"+row.id+"')";
	var affirm="affirm('"+row.id+"')";
	if(row.isAffirm =='0'){
	if(status=='202' || status=='203' || status=='204'){
		
		return '<button class="btn btn-info" onclick="togs();'+ getInfo+'"   type="button" >编辑</button> '+
		       
		       '&nbsp;&nbsp; '+
		       '<button class="btn btn-info" onclick="'+
		       deleteInfo+
		       '"   type="button"  >删除</button>';
	}else{
		return '<button class="btn btn-info" onclick="togs();'+ getInfo+'"   type="button" >详情</button> ';
	}
	}else{
		if(status=='202' || status=='203' || status=='204'){
			
			return '<button class="btn btn-info" onclick="togs();'+ getInfo+'"   type="button" >详情</button> '
		}else{
			return '<button class="btn btn-info" onclick="togs();'+ getInfo+'"   type="button" >详情</button> ';
		}
	}
}


/*增加表格按钮**/
function formatterRemumeFun(value,row,index){
	
	var infoUrl=basePath+'/resume/getEtopResumeInfo.do?etopResumeId='+row.id;
	var getInfo="getInfo('"+row.id+"')";
	var deleteInfo="deleteInfo('"+row.id+"')";
	var affirm="affirm('"+row.id+"')";
    if(row.isAffirm=='0'){
		return '<button class="btn btn-info" onclick="togs();'+ getInfo+'"   type="button" >详情</button>  '+
	       
	       '&nbsp;&nbsp; '+
	       '<button class="btn btn-info" onclick="'+
	       affirm+
	       '"   type="button" >确认</button>';
	}else{
		return '<button class="btn btn-info" onclick="togs();'+getInfo+ '"   type="button" >详情</button> ';
	}
}


/*增加表格按钮**/
function formatterFun2(value,row,index){
	
	var getInfo="getInfo('"+row.id+"')";
	return '<button class="btn btn-info" onclick="'+
		   getInfo+
	       '"   type="button" >详情</button> ';
}

function affirm(id){
	var param={
			"id"    :id,
			"isAffirm" : "1"
	}
	$.post(basePath+"/resume/updateEtopResume.do",param,function(data){
		if(data.status==10001){
			tableRefresh(basePath+"/resume/getEtopResumeList.do?serviceId="+serviceId);
			swal("确认成功", "", "success")
	    }else{
		    swal( data.msg, "","error");
	    }
	});
}

function deleteInfo(id){
	var param={
			"id":id
	}
	$.post(basePath+"/resume/deleteEtopResumeInfo.do",param,function(data){
		if(data.status==10001){
			tableRefresh(basePath+"/resume/getEtopResumeList.do?serviceId="+serviceId);
			swal("删除成功", "", "success")
			$("#uploadTitle").hide();
			$("#updateResume").hide();
	    }else{
		    swal( data.msg, "","error");
	    }
	});
}

function getInfo(id){
	var param={
			"etopResumeId":id
	}
	$.post(basePath+"/resume/getEtopResumeInfo.do",param,function(data){
		if(data.status==10001){
			$("#id").val(data.data.id);
			$("#name").val(data.data.name);
			$("#mobile").val(data.data.mobile);
			$("#email").val(data.data.email);
			$("#uploadTitle").hide();
			$("#updateResume").show();
	    }else{
		    swal( data.msg, "","error");
	    }
	});
}

/*增加表格按钮**/
function formatterFun2(value,row,index){
	
	var infoUrl=basePath+'/goods/getEtopGoodsInfo.do?EtopGoodsId='+row.id;
	var refreshUrl=basePath+'/goods/getEtopGoodsList.do?parkId='+$("#parkId").val();
	var showInfo="openAddPage('用户详情','30%','55%','"+infoUrl+"','"+refreshUrl+"')";
	return '<button class="btn btn-info" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> ';

}


/**添加*/
function addsubmit(){
	var param={
			"name" : $("#name").val(),
			"mobile":$("#mobile").val(),
			"email" : $("#email").val(),  				
			"fileUrl" : $("#fileUrl").val(),
			"isAffirm" : "0",
			"serviceId":serviceId,
			"status":"203"
	}
	$.post(basePath+"/resume/addEtopResume.do",param,function(data){
		if(data.status==10001){
			 swal({
                 title: "新增成功! ",
                 type: "success",
                 confirmButtonText: "确定",
                 closeOnConfirm: true
             }, function () {
					window.location.reload();
             });
	    }else{
		    swal( data.msg, "","error");
	    }
	});
}

function updatesubmit(){
	var param={
			"id"    :$("#id").val(),
			"name" : $("#name").val(),
			"mobile":$("#mobile").val(),
			"email" : $("#email").val(),  				
			"fileUrl" : $("#fileUrl").val()
	}
	$.post(basePath+"/resume/updateEtopResume.do",param,function(data){
		if(data.status==10001){
			tableRefresh(basePath+"/resume/getEtopResumeList.do?serviceId="+serviceId);
			swal("修改成功", "", "success")
			$("#uploadTitle").hide();
	    }else{
		    swal( data.msg, "","error");
	    }
	});
}
