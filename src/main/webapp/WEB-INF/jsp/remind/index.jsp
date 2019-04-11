<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>我的提醒</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
	<style>
	.finaltop{
			float:right;
			display:inline-block;
			margin-left:20px;
			margin-top:-16px;
			
		}
		.finaltop:hover{
			color:red;
		}
		.finaltop:visited{
			red;
		}
		
		.readed{
			float:right;
			display:inline-block;
			margin-left:20px;
			margin-top:-16px;
		}
		.readed:hover{
			color:red;
		}
		.text-info{
			margin-right:10px;
			
		}
		.type{
			margin-right:5px;
		}
	</style>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <jsp:include page="../shared/css.jsp"/>
</head>


<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-6">
                
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>最新提醒</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            
                        </div>
                    </div>
                    <div class="ibox-content no-padding">
                        <ul class="list-group" id="list-group">
                        
                        </ul>
                    </div>
                </div>
            </div>
            
          
          
        </div>
    </div>
 	<jsp:include page="../shared/js.jsp"/>
<script>

var basePath="<%=basePath%>";
 	$(document).ready(function(){
 		 		
 		 $.post("<%=basePath%>/remind/searchbytarget.do",function(data){
 			var remindType;
 			 for(i=0;i<data.length;i++){
 				 
 			 
 			 if(data[i].remindType=='zd'){
				remindType="[账单提醒]";
			}else if(data[i].remindType=='bf'){
				remindType="[拜访提醒]";
			}else if(data[i].remindType=='ht'){
				remindType="[合同提醒]";
			}else if(data[i].remindType=='kh'){
				remindType="[客户提醒]";
			}else if(data[i].remindType=='sp'){
				remindType="[审批提醒]";
			}else if(data[i].remindType=='sq'){
				remindType="[申请提醒]";
			}else if(data[i].remindType=='yqtx'){
				remindType="[园区提醒]";
			}else if(data[i].remindType=='tz'){
				remindType="[审批提醒]";
			}else if(data[i].remindType=='cw'){
				remindType="[审批提醒]";
			}else if(data[i].remindType=='zcw'){
				remindType="[审批提醒]";
			}else if(data[i].remindType=='tzs'){
				remindType="[通知提醒]";
			}else if(data[i].remindType=='rw'){
				remindType="[任务提醒]";
			}else if(data[i].remindType=='jmsq'){
				remindType="[减免申请提醒]";
			}else if(data[i].remindType=='yqsq'){
				remindType="[延期申请提醒]";
			}else if(data[i].remindType=='zf'){
				remindType="[支付提醒]";
			}
 			var id=""+data[i].id;
 			var top=""+data[i].top;
 			
 			test='<li style="overflow:hidden;" class="list-group-item" id="'+id+'"><p><b class="type">'+remindType+'</b><span class="text-info">'+data[i].title+'</span>'+data[i].content+'</p><small class="block text-muted"><i class="fa fa-clock-o"></i>'+data[i].beforeTime+'</small><a href="javascript:void(0)" onclick="readed(\''+id+'\')" class="readed">已读</a>'
 			
 			if(top =='0'){
 				
 				test = test+'<a href="javascript:void(0)" onclick="finaltop(\''+id+'\')" class="finaltop">置顶</a>'
 				
 			}else{
 				test  = test +'<span class="text-info">'+'已置顶'+'</span>'+'</li>'
 				
 			}
 						

 			 $("#list-group").append(test); 

 			 }
 		 });
 	});
 	function finaltop(id){
 		
 		$.ajax({
 			type:'post',
 			url:"<%=basePath%>/remind/finalTop.do?",
 			data:{
 				"id":id,
 				
 			},			
 			success:function(data){
 				if(data.status==10001)
 					{

 					location.reload();
 					

 					}
 			
 			}
 		});
 		
 	}

 	
	function readed(id){
				
		swal({  title: "是否将其标记为已读?", 
			    text: "已读会将其移出列表",
				type: "warning",  
				showCancelButton: true, 
				cancelButtonText: "取消",
				confirmButtonText: "确定",   
				closeOnConfirm: true },
				function(){   
					$.ajax({
			 			type:'post',
			 			url:"<%=basePath%>/remind/upadteStatus.do?",
			 			data:{
			 				"id":id
			 			},
			 			success:function(data){
			 				if(data.status==10001)
			 					{
			 				
			 						$("#"+id).remove();
			 					}
			 				
			 			}
			 		}); 
					
				});
 			
 	}

 	</script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/index_v2.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:46 GMT -->
</html>
