var table = $('#table1');
function tableRefresh(dataUrl){
	
	table.bootstrapTable('refresh',{url:dataUrl});

}
/*增加表格按钮**/
function formatterFun(value,row,index){
	
	var infoUrl=basePath+'/goods/getEtopGoodsInfo.do?EtopGoodsId='+row.id;
	var refreshUrl=basePath+'/goods/getEtopGoodsList.do';
	var stopUrl=basePath+"/goods/activeOrClose.do";
	var showInfo="openAddPage('商品详情','50%','70%','"+infoUrl+"','"+refreshUrl+"')";
    var status=activatedStatus(row.activated);
	var btnS="";
	if(row.activated=="1"){
		btnS="btn-danger";
	}else{
		btnS="btn-primary";
	}
	var param="'"+stopUrl+"'"+","+"'"+row.id+"'"+","+"'"+row.activated+"'"+","+"'"+refreshUrl+"'";
	var stop="stopGroup("+param+")";
	return '<button class="btn btn-info" onclick="'+
		   showInfo+
	       '"   type="button" >编辑</button> '+
	       '&nbsp;&nbsp; '+
	       '<button class="btn '+btnS+'" onclick="'+
	       stop+
	       '"   type="button" >'+status+'</button>';
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

function prove(){  
    
    var goodName = $("#goodName").val();
    if(goodName == "")  
    {  
       alert("项目名称不能为空!");  
       return;  
    }  
    $.ajax({  
           type: "POST",      
            url: "<%=basePath%>/goods/proveGoodName.do",      
             data: "goodName="+goodName,   
            success: function(data){  
           if(data.status == 10001){     
//             alert("项目已存在！");    
            
            swal({
				title : "该服务已存在！",
				type : "error",
				confirmButtonText : "确定",
				closeOnConfirm : false
			});
            return;
           }else{
        	   addsubmit()
           }
           
           }            
           });     
   }
/**添加咨询*/
function addsubmit(){
	var param={
			"goodName" : $("#goodName").val(),
			"unit" : $("#unit").val(),  				
			"unitPrice" : $("#unitPrice").val(),
			"category" : $("#category").val(),
			"description": $("#description").val(),
			"remark": $("#remark").val()
			
	}
	executeAdd("/goods/addEtopGoods.do",param)
}

function updatesubmit(id){
	var param={
			"id"       : id,
			"goodName" : $("#goodName").val(),
			"unit" : $("#unit").val(),  				
			"unitPrice" : $("#unitPrice").val(),
			"category" : $("#category").val(),
			"description": $("#description").val(),
			"remark": $("#remark").val()
	}
	executeUpdate("/goods/updateEtopGoods.do",param)
}

function searchCompany() {
	$.get('../etopCompany/getEtopCompanyList.do', {
		'companyName' : $('#companyNameText').val()
	}, function(data) {
		var rows = data.rows;
		var selector = $('#companySelect');
		selector.empty();
		var oOption = document.createElement('option');
		selector.append(oOption);
		for (var i = 0; i < rows.length; i++) {
			var oOption = document.createElement('option');
			oOption.text = rows[i].companyName;
			oOption.value = rows[i].companyId;
			selector.append(oOption);
		}
	});
}

function searchCategory() {
	$.get('../goods/getEtopGoodsList.do', {
		'companyName' : $('#companyNameText').val()
	}, function(data) {
		var rows = data.rows;
		var selector = $('#companySelect');
		selector.empty();
		var oOption = document.createElement('option');
		selector.append(oOption);
		for (var i = 0; i < rows.length; i++) {
			var oOption = document.createElement('option');
			oOption.text = rows[i].category;
			oOption.value = rows[i].id;
			selector.append(oOption);
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
		tableRefresh(basePath+'/goods/getEtopGoodsList.do?parkId='+id);
    	
		
	}).jstree();
	
});
