//下拉框选中
function selected(obj,value){
	for(var i=0;i<obj.length;i++){
		if(obj.options[i].value == value){
			obj.options[i].selected=true;
		}
	}
}

//执行添加
function executeAdd(url,param){
	$.post(basePath+url,param,function(data){
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


//执行更新操作
function executeUpdate(url,param){
	$.post(basePath+url,param,function(data){
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

//执行更新操作
function executeUpdate2(url,param){
	$.post(basePath+url,param,function(data){
	    if(data.status==10001){
	    	swal("修改成功", "", "success")
	    }else{
		    swal( data.msg, "","error");
	    }
	});
}

/**停用激活*/
function stopGroup(url,id,activated,refUrl){
	if(activated=='1'){
		activated='0';
	}else{
		activated='1';
	}
	var param ={"id":id,"activated":activated}
	var status=activatedAndStopStatus(activated);
	 swal({
         title: "是否更改状态? ",
         type: "warning",
         showCancelButton: true,
         confirmButtonColor: "#DD6B55",
         confirmButtonText: "是",
         cancelButtonText: "否",
         closeOnConfirm: false
     }, function () {
	$.post(url,param,function(data){
		if(data.status==10001){
			swal(   status+"成功！", "","success");
			tableRefresh(refUrl);
	    }else{
	    	swal(   data.msg, "","error");
	    }

	});
     });
}

/**停用激活*/
function stopGroup2(url,id,activated,refUrl){
	if(activated=='1'){
		activated='0';
	}else{
		activated='1';
	}
	var param ={"id":id,"state":activated}
	var status=activatedAndStopStatus(activated);
	$.post(url,param,function(data){
		if(data.status==10001){
			swal(   status+"成功！", "","success");
			tableRefresh(refUrl);
	    }else{
	    	swal(   data.msg, "","error");
	    }

	});
}


function upload(url,id,option) {
/*	var objs=$("#file-pretty").find(".input-large");
	if(objs[0].value==""){
		return ;
	}*/
	$.ajaxFileUpload({
		url : url,
		secureuri : false,
		fileElementId : id,
		dataType : 'json',
		data : {},
		success : function(data, status) {
			if(data.status==10001){
				option(data);
				swal("上传成功");
			}else{
				swal('上传出错');
			}
		},
		error : function(data, status, e) {
			swal('上传出错');
		}
	})
}
//时间选择
$('#openTime').datetimepicker({
	 minView: 1,
	 todayBtn: "linked",
	 format: 'yyyy-mm-dd hh:00:00'
	 //d, dd, D, DD, m, mm, M, MM, yy, yyyy
});

$('#openTime2').datetimepicker({
	 startView:3,
	 minView: 2,
	 todayBtn: "linked",
	 format: 'mm-dd',
	 language: 'zh-CN'
	 //d, dd, D, DD, m, mm, M, MM, yy, yyyy
});
//城市选择
var cityPicker = new HzwCityPicker({
	data : data,
	target : 'cityChoice',
	valType : 'k-v',
	hideCityInput : {
		name : 'city',
		id : 'city'
	},
	hideProvinceInput : {
		name : 'province',
		id : 'province'
	},
	callback : function() {
		
	}
});
cityPicker.init();

