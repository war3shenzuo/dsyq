var tree ;
$(document).ready(function () {
	tree = $("#tree").jstree({
			"core": {
        	"animation": true,
        	"multiple": false,
        	"check_callback": true,
        	"themes": { "stripes": false },
        	'data': {
        		'url': basePath+'/role/getRoleList.do',
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
    	$.post(basePath+"/role/getRoleInfo.do",{"roleId":id},function(data){
    		
    		if(data.status==10001){
    			
    			var role=data.data;
    			$("#id").val(role.id);
    			$("#roleCode").val(role.roleCode);
    			$("#roleName").val(role.roleName);
    			$("#roleEscribe").val(role.roleEscribe);
    			$("#parentId").val(role.parentId);
    			$("#defFunc").val(role.defFunc);
    			$("#parkId").val(role.parkId);
    			$("#parkName").val(role.parkName);
    			$("#createAt").val(role.createAt);
    			$("#roleType").val(role.roleType);
    			if(role.roleType=='1'){
    				$("#roleTypeName").val("园区组");
    			}else{
    				$("#roleTypeName").val("园区");
    			}
    			var type=document.getElementById("roleType");
    			selected(type,role.roleType);
    			var activated=document.getElementById("activated");
    			selected(activated,role.activated);
    			
    			if(role.roleApproval!=null && role.roleApproval!=''){
    				
    				var approval = role.roleApproval.split(",")
        			
        	    	$("#roleApproval").select2().val(approval).trigger("change");

    			}else{
    				$("#roleApproval").select2().val('').trigger("change");
    				
    			}
    			
//    			var test="";
//        		if(role.roleType="2"){
//        			 test = "<option value='0'>无权限</option><option value='1'>园区审批</option><option value='2'>财务审批</option>";
//        		}else if(role.roleType="1"){
//        			 test = "<option value='0'>无权限</option><option value='4'>组财务审批</option>";
//        		}
        		
        		//$("#roleApproval").html(test);
        		//var roleApproval=document.getElementById("roleApproval");
    			//selected(roleApproval,role.roleApproval);
    			
    		}
    		$("#updateChildren").show();
    		$("#bindUser").show();
    		$("#bindFunc").show();
    		$("#setRead").show();
    		
    		if(role.parentId!=null && role.parentId!=''){
    			$("#addChildren").hide();
    			$("#copyRole").show();
        		$("#copyRole2").show();
        		$("#rolefunc1").show();
        		$("#rolefunc2").show();
        		$("#rolefunc3").show();
        		$("#roleApprovaldiv").show();
        		$("#roleApprovaldivhr").show();

    		}else{
    			$("#roleApprovaldiv").hide();
    			$("#roleApprovaldivhr").hide();
    			$("#copyRole").hide();
        		$("#copyRole2").hide();
    			$("#addChildren").show();
    			$("#rolefunc1").hide();
        		$("#rolefunc2").hide();
        		$("#rolefunc3").hide();
        		$("#rolefunc0").addClass("active");
        		$("#rolefunc1").removeClass("active");
        		$("#rolefunc2").removeClass("active");
        		$("#rolefunc3").removeClass("active");
        		$("#tab-1").addClass("active");
        		$("#tab-2").removeClass("active");
        		$("#tab-3").removeClass("active");
        		$("#tab-4").removeClass("active");
        		
    		}
    		
    		setBindUserLis();
    		functreeInit();
    		readtreeInit();
    		
    	});
		
        var sel = data.instance.get_node(data.selected[0]).original.attr;
        
        //infoShow(data.selected[0]);
        
    }

	}).jstree();
    
    var funcTree = $("#functree").jstree({
        "core": {
        "animation": 0,
        "check_callback": true,
        "themes": { "stripes": false },
        'data': {
            'url': basePath+"/func/getRoleFuncList.do",
            "dataType": "json",
            'data': function (node) {
                return { 'id': node.id };
            }
        }
    },
    "checkbox": {
        "keep_selected_style": false,
        "tie_selection": false
    },
    "plugins": ["checkbox"]

    }).jstree();
    
});
/*添加角色*/
function addsubmit(){
	
	var param={
			"roleCode" : $("#roleCode").val(),
			"roleName" : $("#roleName").val(),
			"roleEscribe" : $("#roleEscribe").val(),   				
	   	 	"parkId" :   $("#parkId").val(),
	   	 	"parentId" : $("#parentId").val(),
	    	"activated" : $("#activated").val()
	}
	
	if( $("#roleApproval").val()!=null && $("#roleApproval").val().length>0){
		param.roleApproval= $("#roleApproval").val().toString() ;
	}else{
		param.roleApproval= "";
	}
	
	$.post(basePath+"/role/addRole.do",param,function(data){
		   if(data.status==10001){
				swal({   title: "保存成功！",   
						 type: "success",  
						 confirmButtonText: "ok",  
						 closeOnConfirm: false 
					  }, 
					  function(){  
							 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							 parent.layer.close(index); 
					  });
		   }else{
			   swal( data.msg, "","error");
		   }
	   });
	
}
/*更新*/
function updatesubmit(){
	var param={
			"id"       : $("#id").val(),
		"roleCode" : $("#roleCode").val(),
		"roleName" : $("#roleName").val(),
		"roleEscribe" : $("#roleEscribe").val(),   				
    	"activated" : $("#activated").val()
	}
	
	if( $("#roleApproval").val()!=null && $("#roleApproval").val().length>0){
		param.roleApproval= $("#roleApproval").val().toString() ;
	}else{
		param.roleApproval= "";
	}

$.post(basePath+"/role/updateRole.do",param,function(data){
   if(data.status==10001){
	   tree.set_text ($("#id").val(), $("#roleName").val());
	   swal("更新成功", "", "success");
   }else{
	   swal( data.msg, "","error");
	   }
   });
	
}
/**新增子类*/
function addChildren(){
	var id=$("#id").val()
	var parkId=$("#parkId").val();
	var roleType=$("#roleType").val();
	openAddPage('新增子角色','50%','60%',basePath+'/role/addPage.do?parentId='+id+'&parkId='+parkId+'&roleType='+roleType,'');
}

//设置绑定用户列表
function setBindUserLis(){
	$("#bindUserList").children().remove();
	var rid=$("#id").val();
	var param={"roleId":rid};
	$.post(basePath+"/user/getUserListByRoleIds.do",param,function(data){
	   if(data.status==10001){
		   var list=data.data;
		   var s='<li class="list-group-item" style=" font-weight: 600;">用户</li>';
		   for(var i=0 ;i<list.length;i++){
			  s=s+'<li class="list-group-item">'+
				  '<span class="badge badge-primary"><a href="javascript:void(0);" '+
				  'onclick="unBindUser('+"'"+list[i].id+"'"+')"'+
                  '>X</a></span>'+
				  list[i].userName+
				  '</li>';
			   
		   }
		   $("#bindUserList").append(s);
		   //functreeInit();
	   }else{
		   swal( data.msg, "","error");
		   }
    });
}
/**取消绑定*/
function unBindUser(userId){
	var rid=$("#id").val();
	var param={"userId":userId,"roleId":rid};
	$.post(basePath+"/role/delUserForRole.do",param,function(data){
		if(data.status==10001){
			swal("更新成功", "", "success");
		  	setBindUserLis(); 
	    }else{
		   swal(data.msg, "","error");
	    }
	});
}
  
/**绑定用户*/
function openBindUser(){
	openSelectPage('绑定用户','80%','95%',basePath+'/role/openBindUserPage.do?parkId='+$("#parkId").val(),setBindUserLis);
}

/**功能初始化**/
function functreeInit(){
	$('#functree').jstree("uncheck_all");
	var rid=$("#id").val();
	var param={"roleId":rid};
	$.post(basePath+"/func/getFuncListByRoleId.do",param,function(data){
		var ids=[];
		var list=data.data;
		for(var i=0;i<list.length;i++){
			
			ids.push(list[i].id);
		
		}
		if(list.length>0){
			$('#functree').jstree('check_node',ids);
		}
	});
}

/**绑定功能*/
function bindFunc(){
	var array=$('#functree').jstree('get_checked');
	var s="";
	for(var i=0;i<array.length;i++){
	s+=array[i]+",";
	}
	if(s!=""){
		s=s.substring(0, s.length-1);
	}	
	var rid=$("#id").val();
	var param={"funcId":s ,"roleId":rid};
	
	$.post(basePath+"/role/addFuncForRole.do",param,function(data){
	   if(data.status==10001){
		   swal("更新成功", "", "success");
		   readtreeInit();
	   }else{
		   swal( data.msg, "","error");
	   }
	});

	
}

/**复制角色*/
function copyRole(){
	var rid=$("#id").val();
	var parkId=$("#toParkId").val();
	var param={"parkId":parkId,"oldRole":rid};
	$.post(basePath+"/role/copyRoleFunc.do",param,function(data){
	if(data.status==10001){
		tree.refresh();
		swal("更新成功", "", "success");
    }else{
	   swal( data.msg, "","error");
	   }
	});
}

/**读写树初始化*/
function readtreeInit(){
	$("#readtree").jstree("destroy");
	var rid=$("#id").val();
	$("#readtree").jstree({
        "core": {
        "animation": 0,
        "check_callback": true,
        "themes": { "stripes": false },
        'data': {
            'url': basePath+"/func/getFuncListBySelected.do?roleId="+rid,
            "dataType": "json",
            'data': function (node) {
                return { 'id': node.id };
            }
        }
    },
    "checkbox": {
        "keep_selected_style": false,
        "tie_selection": false
    },
    "plugins": ["checkbox"]

    }).on("ready.jstree", function (event, data) {

    	readtreeSelectInit();

    }).jstree();
}
/**设置选中*/
function readtreeSelectInit(){
	var rid=$("#id").val();
	var param={"roleId":rid};
	$.post(basePath+"/func/getFuncListRead.do",param,function(data){
		var ids=[];
		var list=data.data;
		for(var i=0;i<list.length;i++){
			
			ids.push(list[i].id);
		
		}
		if(list.length==0){
			$('#readtree').jstree("uncheck_all");
		}else{
			$('#readtree').jstree('check_node',ids);
		}
	});
}

/**设置读写*/
function setRead(){
	var array=$('#readtree').jstree('get_checked');
	var s="";
	for(var i=0;i<array.length;i++){
	s+=array[i]+",";
	}
	if(s!=""){
		s=s.substring(0, s.length-1);
	}	
	var rid=$("#id").val();
	var param={"funcId":s ,"roleId":rid};
	
	$.post(basePath+"/role/addReadFuncForRole.do",param,function(data){
	   if(data.status==10001){
		   swal("更新成功！", "", "success");
	   }else{
		   swal( data.msg, "","error");
	   }
	});
	
	
}
