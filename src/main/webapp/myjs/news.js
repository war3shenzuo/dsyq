var table = $('#table1');
function tableRefresh(dataUrl){
	
	table.bootstrapTable('refresh',{url:dataUrl});

}
/*增加表格按钮**/
function formatterFun(value,row,index){
	
	var infoUrl=basePath+'/news/getNewsInfo.do?newsId='+row.id;
	var refreshUrl=basePath+'/news/getNewsList.do';
	var stopUrl=basePath+"/news/activeOrCloseNews.do";
	var showInfo="openAddPage('用户详情','80%','95%','"+infoUrl+"','"+refreshUrl+"')";
	var status=activatedAndStopStatus(row.state);
	var btnS="";
	if(row.state=="1"){
		btnS="btn-danger";
	}else{
		btnS="btn-primary";
	}
	var param="'"+stopUrl+"'"+","+"'"+row.id+"'"+","+"'"+row.state+"'"+","+"'"+refreshUrl+"'";
	var stop="stopGroup2("+param+")";
	return '<button class="btn btn-info" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> '+
	       '&nbsp;&nbsp; '+
	       '<button class="btn '+btnS+'" onclick="'+
	       stop+
	       '"   type="button" >'+status+'</button>';
}


/**添加咨询*/
function addsubmit(){
	var stem = CKEDITOR.instances.textWeb.getData();
	var param={
			"title" : $("#title").val(),
			"totalType" : $("#totalType").val(),  				
			"newsType" : $("#newsType").val(),
			"state" : $("#state").val(),
			"content" : stem
	}
	executeAdd("/news/addNews.do",param)
}

function updatesubmit(id){
	var stem = CKEDITOR.instances.textWeb.getData();
	var param={
			"id"       : id,
			"title" : $("#title").val(),
			"totalType" : $("#totalType").val(),  				
			"newsType" : $("#newsType").val(),
			"state" : $("#state").val(),
			"content" : stem
	}
	executeUpdate("/news/updateNews.do",param)
}
