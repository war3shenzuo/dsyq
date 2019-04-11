var tree ;
$(document).ready(function () {
        tree = $("#tree").jstree({
        "core": {
            "animation": true,
            "multiple": false,
            "check_callback": true,
            "themes": { "stripes": false },
            'data': {
                'url': basePath+'/func/getFuncList.do',
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
        	$.post(basePath+"/func/getFuncInfo.do",{"funcId":id},function(data){
        		
        		if(data.status==10001){
        			
        			var func=data.data;
        			$("#id").val(func.id);
        			$("#funcName").val(func.funcName);
        			$("#funcCode").val(func.funcCode);
        			$("#funcDescribe").val(func.funcDescribe);
        			$("#parentId").val(func.parentId);
        			$("#loadUrl").val(func.loadUrl);
        			$("#sortId").val(func.sortId);
        			var activated=document.getElementById("activated");
        			selected(activated,func.activated);
        			
        		}
        		$("#updateChildren").show();
        		$("#addChildren").show();
        	});
        }

    }).jstree();
    
});

/*更新*/
function updatasubmit(){
	var param={
			"id"       : $("#id").val(),
			"funcName" : $("#funcName").val(),
			"funcCode" : $("#funcCode").val(),
			"funcDescribe" : $("#funcDescribe").val(),   				
	   	 	"sortId" : $("#sortId").val(),
	    	"loadUrl" : $("#loadUrl").val(),
	    	"activated" : $("#activated").val()
	}
	
	$.post(basePath+"/func/updateFunc.do",param,function(data){
		   if(data.status==10001){
			   tree.set_text ($("#id").val(), $("#funcName").val());
			   swal("更新成功", "", "success");
		   }else{
			   swal( data.msg, "","error");
		   }
    });
	
}
/**打开新增子类页面*/
function addChildren(){
	var id=$("#id").val()
	openAddPage('新增子角色','800px','600px',basePath+'/func/addPage.do?parentId='+id,'');
}

/**新增*/
function addsubmit(){
	var param={
			"funcName" : $("#funcName").val(),
			"funcCode" : $("#funcCode").val(),
			"funcDescribe" : $("#funcDescribe").val(),   				
	   	 	"sortId" : $("#sortId").val(),
	    	"loadUrl" : $("#loadUrl").val(),
	    	"parentId" : $("#parentId").val(),
	    	"activated" : $("#activated").val()
	}
	
	$.post(basePath+"/func/addFunc.do",param,function(data){
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
		   }
	   });
	
}
