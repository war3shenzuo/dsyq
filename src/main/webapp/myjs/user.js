var table = $('#table1');
function tableRefresh(dataUrl){
	
	table.bootstrapTable('refresh',{url:dataUrl});

}
/*增加表格按钮**/
function formatterFun(value,row,index){
	
	var infoUrl=basePath+'/user/getUserInfo.do?userId='+row.id;
	var refreshUrl=basePath+'/user/getUserList.do';
	var showInfo="openAddPage('用户详情','600px','560px','"+infoUrl+"','"+refreshUrl+"')";
	var status=activatedStatus(row.activated);
	var stop="stopGroup('"+row.id+"'"+","+"'"+row.activated+"')";
	var resetPW="resetPW('"+row.id+"')";
	return '<button class="btn btn-primary" onclick="'+
		   resetPW+
      	   '"   type="button" >重置密码</button> '+
   		   '&nbsp;&nbsp; '+
		   '<button class="btn btn-primary" onclick="'+
		   showInfo+
	       '"   type="button" >详情</button> '+
	       '&nbsp;&nbsp; '+
	       '<button class="btn btn-primary" onclick="'+
	       stop+
	       '"   type="button" >'+status+'</button>';
}
/**激活不激活操作*/
function stopGroup(id,activated){
	if(activated=='1'){
		activated='0';
	}else{
		activated='1';
	}
	var param ={"id":id,"activated":activated};
	var status=activatedAndStopStatus(activated);
	$.post(basePath+"/user/activeOrCloseUser.do",param,function(data){
		if(data.status==10001){
			swal(   status+"成功！", "","success");
			tableRefresh(basePath+'/user/getUserList.do');
	    }else{
	    	swal(   status+"失败！", "","error");
	   }

	});
}
/**重置密码*/
function resetPW(id){
	var param ={"id":id};
	$.post(basePath+"/user/resetPassword.do",param,function(data){
		if(data.status==10001){
			swal(   status+"成功！", "","success");
	    }else{
	    	swal(   status+"失败！", "","error");
	   }

	});
}
/**添加用户*/
function addsubmit(){
	var param={
		"userName" : $("#userName").val(),
		"passWord" : $("#passWord").val(),  				
	    "parkId" : $("#parkId").val(),
	    "companyId" : $("#companyId").val(),
	    "mobile" : $("#mobile").val(),
	    "email" : $("#email").val(),
	    "activated" : $("#activated").val(),
	    "roleId" : $("#roleId").val(),
	    "name" : $("#name").val(),
	}
	
	$.post(basePath+"/user/addUserInfo.do",param,function(data){
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

function updatesubmit(id){
	var param={
			"id"       : id,
			"userName" : $("#userName").val(),
			"passWord" : $("#passWord").val(),
			"userType" : $("#userType").val(),   				
	    "mobile" : $("#mobile").val(),
	    "email" : $("#email").val(),
	    "activated" : $("#activated").val(),
	    "name" : $("#name").val()
	}
	
	$.post(basePath+"/user/updateUserInfo.do",param,function(data){
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


