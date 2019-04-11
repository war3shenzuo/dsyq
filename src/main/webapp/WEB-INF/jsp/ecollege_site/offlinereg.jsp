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
		<meta charset="UTF-8">
		<title>报名</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/xueyuandetails.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/eaxmtion.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
<link href="<%=basePath%>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<style>


.eaxmtion h1{ margin: 110px 0 60px 0; font-size: 32px; color: #000;text-align: center;font-weight: 500;}
.eaxmtion table{ margin: 0 auto 60px auto;font-weight:bold;}
.eaxmtion td{ height: 50px; line-height: 50px; font-size: 15px; color: rgb(161,161,161); position: relative;}
.eaxmtion td p{ display: inline-block; width: 80px;  }
.eaxmtion td input,.eaxmtion td select{ width: 245px;height: 28px; margin:0 32px 0 22px; text-indent: 10px;border-radius:5px; border: 1px solid #ccc;}
.eaxmtion .midsub{width:200px;margin:0 auto}
.eaxmtion #sub{width: 168px;border:none;outline:none;height: 48px; border-radius: 22px; font-size: 24px ;color: #fff; background: #ee770f; display: block;}
.eaxmtion #sub:hover{color:red;}
#eaxmtion p {
    line-height: 16px;
}
.error{position: absolute; left:110px; color:red; font-size: 12px; font-weight: 300; top:0}

</style>
	</head>
	<body>
		
		<jsp:include page="../header.jsp" flush="true" />
	
		<div class="wrap">
			<div style=" width:100%; text-align: center;font-size: 35px;font-weight: bold;margin: 30px 0 0 0 ">
				<span id="courseName"></span>
			</div>
			<form method="get" class="form-horizontal" id="courseForm">
			<div id="eaxmtion" class="eaxmtion">
			
			<table border="0" cellspacing="0" cellpadding="0" style="">
			
			<tr>
    					
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;color:#666">个人信息</e></td>
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;">&nbsp;</e></td>
    				</tr>
    				<tr>
    					<td><p>姓<span style="display: inline-block;height: 24px; width: 28px;;"></span>名 </p><input  name="pName" id="pName" value="" style="vertical-align: middle;height:;"></td>
    					<td><p>所属单位 </p><input type="text" name="pDanwei" id="pDanwei" value="" /></td>
    				</tr>
    				
    				<tr>
    					<td><p>联系方式</p><input type="text" name="pPhone" id="pPhone" value="" /></td>
    					<td><p>邮<span style="display: inline-block;height: 24px; width: 28px;;"></span>箱</p><input type="text" name="pemail" id="pemail" value="" /></td>
    				</tr>
    				<tr>
    					
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;color:#666">职业信息</e></td>
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;">&nbsp;</e></td>
    				</tr>
    				<tr>
    					<td><p>岗<span style="display: inline-block;height: 24px; width: 28px;;"></span>位</p><input type="text" name="gangwei" id="gangwei" value="" /></td>
    					<td><p>职<span style="display: inline-block;height: 24px; width: 28px;;"></span>位</p><input type="text" name="zhiwei" id="zhiwei" value="" /></td>
    				</tr>
    				<tr>
    					<td><p>从事岗位的工作年限</p><input type="text" name="workingLife" id="workingLife" value="" /></td>
    				<c:if test="${type eq 0 || type eq 3}">
    					<td><p>是否参加过类似培训</p><select name="isTrain" id="isTrain" >
										<option value="0">未参加</option>
										<option value="1">已参加</option>
						</select></td>
					</c:if>
    				</tr>
    				<c:if test="${type == 0}">
    				<tr>
    					<td><p>可参加培训日期</p><input name="tureTime" id="tureTime"  class="form-control layer-date" placeholder=""></td>
    				</tr>
    				</c:if>
    				<!-- <tr >
    					<td style="height:10px;line-height:10px;font-size:12px;"><span style="color:red">*</span><e>(提交信息后将进行审核，审核结果将发往该邮箱，请正确填写邮箱地址！)</e></td>		
    				</tr> -->
    			</table>
    			
    			</div>
			<div  style=" width:168px; margin: 20px auto;">
	    		<button  id="sub"  style="margin-right: 0; cursor: pointer; text-align: center; line-height: 48px;">提交</button>
    			</div>
    			</form>
		</div>
		<jsp:include page="../footer.jsp" flush="true" />
		<script src="<%=basePath%>/js/plugins/validate/jquery.validate.min.js"></script>
		<script src="<%=basePath%>/js/plugins/validate/messages_zh.min.js"></script>
		<script src="<%=basePath%>/js/plugins/layer/laydate/laydate.js"></script>
		<script type="text/javascript">
		var trainingId=GetQueryString("id");
		var titleName=GetQueryString("titleName");
		var type=GetQueryString("listType");
		var Enum="";
		var eAnswers = "";
		
		var pName="";
		var pDanwei="";
		var pPhone="";
		var pemail="";
		var gangwei="";
		var zhiwei="";
		var workingLife="";
		var isTrain="";
		var tureTime="";
	
		$(document).ready(function(){
			$("#courseName").append(titleName+"报名");
		});
		
		
		$.validator.setDefaults({
			highlight: function(e) {
				$(e).closest(".form-group div").removeClass("has-success").addClass("has-error")
			},
			success: function(e) {
				e.closest(".form-group div").removeClass("has-error").addClass("has-success")
			},
			errorElement: "span",
			errorPlacement: function(e, r) {
				e.appendTo(r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent())
			}
		});
		 var standard = "<i class='fa fa-times-circle'></i> ";

         $("#courseForm").validate({
             rules: {//这里加校验规则
            	 pName: "required",//
            	 pDanwei: "required",//
            	 pPhone: "required",//
            	 pemail: "required",//
            	 gangwei: "required",//
            	 zhiwei: "required",//
            	 workingLife: "required"//
//             	 isTrain: "required",//
//             	 tureTime: "required"
             },
             messages: {//这里给对应的提示
            	 pName: standard + "请输入姓名 !",
            	 pDanwei: standard + "请输入所属单位 !",
            	 pPhone: standard + "请输入联系方式!",
            	 pemail: standard + "请输入邮箱!",
            	 gangwei: standard + "请输入岗位 !",
            	 zhiwei: standard + "请输入职位 !",
            	 workingLife: standard + "请输入工作年限 !"
//             	 isTrain: standard + "请输入是否参加过培训 !",
//             	 tureTime: standard + "请输入可以参加培训的日期 !"
             },
             submitHandler: function(form){
                 submit();  //去提交
             }
         })
		
         function submit(){
        	 pName=$("#pName").val();
        	 pDanwei=$("#pDanwei").val();
        	 pPhone=$("#pPhone").val();
        	 pemail=$("#pemail").val();
        	 gangwei=$("#gangwei").val();
        	 zhiwei=$("#zhiwei").val();
        	 workingLife=$("#workingLife").val();
        	 isTrain=$("#isTrain").val();
        	 tureTime=$("#tureTime").val();
        	/* //alert(pName+pDanwei+pPhone+gangwei+zhiwei+workingLife+isTrain+tureTime);  */
        	 $.ajax({  
        		    type : "post",  
        		    url : "<%=basePath %>/etopTrainApplication/addEtopTrainApplication.do",  
        		    data : {
        		    	
        		    	"trainingId":trainingId,
        		        "name":pName,
        		        "workUnits":pDanwei,
        		        "contactInformation":pPhone,
        		        "email":pemail,
        		        "post":gangwei,
        		        "position":zhiwei,
        		        "postYear":workingLife,
        		        "train":isTrain,
        		        "joinTime":tureTime
        		    },
        		    success : function(data){  
        		    	if (data.status == 10001) {
    						swal({
    							title : "提交成功！",
    							type : "success",
    							confirmButtonText : "确定",
    							closeOnConfirm : true
    						} ,function() {
    							window.location.reload();
    						}
    						);
    					} else {
    						swal({
    							title : "提交失败，您可能未登陆！",
    							text : data.msg,
    							type : "error",
    							confirmButtonText : "确定",
    							closeOnConfirm : true
    						});
    					}
    					
        		    }  
        		});
         }
         
        
		function GetQueryString(name)
			{
			     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			     var r = window.location.search.substr(1).match(reg);
			     if(r!=null)return  decodeURI(r[2]); return null;
			}
		</script>
	</body>
</html>
