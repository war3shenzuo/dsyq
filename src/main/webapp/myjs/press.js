var table = $('#table1');
function tableRefresh(dataUrl){
	
	table.bootstrapTable('refresh',{url:dataUrl});

}
/*增加表格按钮**/
function formatterFun(value,row,index){

	var infoUrl = basePath+'/press/getPressInfo.do?id='+row.id+'&readonly=' + readonly;
	var refreshUrl = basePath+'/press/getPressList.do';
	var showInfo = "openAddPage('用户详情','1100px','700px','"+infoUrl+"','"+refreshUrl+"')";
	var status = activatedStatus(row.state);
	var stop = "stopGroup('"+row.id+"'"+","+"'"+row.state+"')";
	return '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> '+
	       '&nbsp;&nbsp; '+
	       '<button class="btn btn-primary" <c:if test="readonly">style="display: none;"</c:if> onclick="'+
	       stop + '"   type="button" >'+ status +'</button>';
}

/**激活不激活操作*/
function stopGroup( id, activated){
	if(activated == '1'){
		activated = '0';
	}else{
		activated = '1';
	}
	var param = {"id":id, "state":activated};
	var status = activatedStatus(activated);
	$.post(basePath+"/press/activeOrCloseNews.do",param,function(data){
		if(data.status==10001){
			swal(   status+"成功! ", "","success");
			tableRefresh(basePath+'/press/getPressList.do');
	    }else{
	    	swal(   status+"失败! ", "","error");
	   }

	});
}

/**添加*/
function addsubmit(){
	var content = CKEDITOR.instances.textWeb.getData();
	var param={
			"title" : $("#title").val(),
			"totalType" : $("#totalType").val(),  				
			"newsType" : $("#newsType").val(),
			"state" : $("#state").val(),
			"content" : content
	}
	
	$.post(basePath+"/press/addPress.do",param,function(data){
		   if(data.status==10001){
				swal({
						title: "保存成功！",
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

function updatesubmit(id){
	var stem = CKEDITOR.instances.textWeb.getData();
	var param={
			"id" : id,
			"title" : $("#title").val(),
			"totalType" : $("#totalType").val(),  				
			"newsType" : $("#newsType").val(),
			"state" : $("#state").val(),
			"content" : stem
	}
	
	$.post(basePath+"/press/updatePress.do",param,function(data){
		if(data.status==10001){
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
