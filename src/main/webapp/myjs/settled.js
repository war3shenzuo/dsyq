var table = $('#table1');
/**表格刷新*/
function tableRefresh(dataUrl){
	
	table.bootstrapTable('refresh',{url:dataUrl});

}
/*增加表格按钮**/
function formatterFun(value,row,index){
	
	var infoUrl=basePath+'/parkgroup/getSettledInfo.do?settledId='+row.id;
	var showInfo="openAddPage('园区组详情','80%','95%','"+infoUrl+"','')";
	var status=activatedStatus(row.activated);
	var stop="stopGroup('"+row.id+"'"+","+"'"+row.activated+"')";
	return '<button class="btn btn-info" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> '+
	       '&nbsp;&nbsp; '+
	       '<button class="btn btn-primary" onclick="setId(\''+row.id+'\')"   type="button"  data-toggle="modal" data-target="#notice">通过</button>';
}
/**激活设置*/
function stopGroup(id,activated){
	var status=activatedStatus(activated);
	if(activated=='1'){
		activated='0';
	}else{
		activated='1';
	}
	var param ={"id":id,"activated":activated}
	$.post(basePath+"/parkgroup/stopParkGroup.do",param,function(data){
		if(data.status==10001){
			swal(   data.msg, "","success");
			tableRefresh(basePath+'/parkgroup/getParkList.do');
	    }else{
	    	swal(   data.msg, "","error");
	    }
	});
}

function adopt(){
	var param ={"id":$("#sid").val(),"code":$("#code").val(),"approval":$("#approval").val()}
	$.post(basePath+"/parkgroup/formalParkGroup.do",param,function(data){
		if(data.status==10001){
			swal(   data.msg, "","success");
			tableRefresh(basePath+'/parkgroup/getSettledList.do');
			$('#notice').modal('hide')
	    }else{
	    	swal(    data.msg, "","error");
	    }
	});
}

function setId(id){
	$("#sid").val(id);
}