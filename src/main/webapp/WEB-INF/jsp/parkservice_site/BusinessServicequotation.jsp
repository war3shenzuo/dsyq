<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>园区服务-商务服务申请</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>		
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/column.css"/>
	<style>
	.fol{ padding: 20px;}
	.fol input{ border-bottom: 1px solid #6d6d6d; border-left: none; border-right: none; border-top: none; width:200px;  margin-left: 8px;}
	</style>
</head>
<body>

	<jsp:include page="../header.jsp" flush="true" />

    		<div id="wrap">
    			<form method="post" class="form-horizontal" id="signupForm" >
			<div class="le">
			<div class="applbox" id="applbox" style="">
			
					
				    <table border="0" cellspacing="0" cellpadding="0" ">
<!-- 				    维修信息 -->
	                <h1 style="font-size:35px;" id="title">商务服务</h1>
	                <tr>
    					
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px; color:#666">服务信息</e></td>
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;">&nbsp;</e></td>
    				</tr>
    				<tr>
    					<td><p>类<span style="display: inline-block; width: 28px;;"></span>别 <font color="red">*</font></p><select id="category" style="width: 249px;height: 34px; margin:0 32px 0 22px; text-indent: 10px;" class="form-control">	
							<option >请选择类别</option>
						        <c:forEach var="category" items="${category}" >
                        			<option  value ="${category.category}">${category.category}</option>
                        		</c:forEach> 
						    </select></td>
					    <td><p>项目名称 <font color="red">*</font></p>
					    <select id="subject" style="width: 249px;height: 34px; margin:0 32px 0 18px; text-indent: 10px;" class="form-control" onchange="gradeChange()">	

					    </select>
					    </td>
    				</tr>
    				<tr>
    					<td><p>数<span style="display: inline-block; width: 28px;;"></span>量 <font color="red">*</font> </p><input type="text" class="form-control" value="" id="number"  onkeyup="upperCase()""></td>
    					<td><p>单<span style="display: inline-block; width: 28px;;"></span>价</p><input type="text" class="form-control" value="" id="unitPrice"readonly="readonly"></td>
    				</tr>
    				<tr>
    					<td><p>单<span style="display: inline-block; width: 28px;;"></span>位</p><input type="text" class="form-control" value="" id="unit" readonly="readonly"></td>
    					<td><p>合计金额</p><input type="text" readonly="readonly" class="form-control" value="" id="totalPrice" ></td>
    				</tr>
<!--                         房间信息 -->
					<tr>
    					
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;color:#666">房间信息</e></td>
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;">&nbsp;</e></td>
    				</tr>
    				<tr>
    					<td><p>房<span style="display: inline-block; width: 28px;;"></span>间 <font color="red">*</font></p><select id="roomNum" class="form-control" style="width: 249px;height: 34px; margin:0 32px 0 22px; text-indent: 10px;" >	
						    <option  value=''>请选择房间</option>
						        <c:forEach var="room" items="${room}">
                        			<option>${room.room}</option>
                        		</c:forEach> 
						    </select></td>
    				</tr>
<!--                         申请人信息 -->
					<tr>
    					
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;color:#666">申请人信息</e></td>
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;">&nbsp;</e></td>
    				</tr>
    				<tr>
    					<td><p>申请人 </p><input type="text" class="form-control" value="" id="applicant"></td>
    					<td><p>联系方式</p><input type="text" class="form-control" value="" id="applicant_phone"></td>
    				</tr>
    				<tr>
    					<td><p>部<span style="display: inline-block; width: 28px;;"></span>门</p><input type="text" class="form-control" value="" id="applicant_department"></td>
						<td><p>职<span style="display: inline-block; width: 28px;;"></span>位</p><input type="text" class="form-control" value="" id="applicant_position"></td>
    				</tr>
    				<tr>	
    					<td><p>补充信息</p>
<!--     					<input type="text" class="form-control" value="" id="description"> -->
     					 <textarea id="description" rows="3" name="textWeb" cols="40" class="form-control"  style="width: 243px;margin:7px 32px 0 18px; vertical-align:middle; text-indent: 10px;"></textarea> 
    					</td>
    				</tr>
 

    			</table>
    			<div class="form-group">
			        <div class="col-sm-4 col-sm-offset-5" >
			            <a  href="javascript:" class="btn btn-primary" onclick="submit()">提交</a>
			        </div>
			    </div>
			</div>	
			</div>
			</form>
			<div class="rt">
				<h2 style="font-size:20px;">商务服务条款</h2>
				<div  class="scroll">
				<div class="fol">	
<!-- 				<input name="key" type="text" value="" id="categories"/> -->
				<select id="categories" style="width: 249px;height: 34px; margin:0 12px 0 12px; text-indent: 10px;" class="form-control">	
							<option >
						        <c:forEach var="category" items="${category}" >
                        			<option  value ="${category.category}">${category.category}</option>
                        		</c:forEach> 
                        	</option>	
				</select> 
				<button  onclick="binding()" >类别筛选</button>
				</div> 
					<div class="fol" style="padding-top: 0px" id="fol">	
					
	     			</div>
				</div>
			</div>
		</div>
    
<jsp:include page="../footer.jsp" flush="true" />
<script type="text/javascript">

function binding(){
    var params = {
        "categories" : $('#categories').val()
    }
    $.post("<%=basePath %>/quotation/QuotationServiceList.do", params, function(data){
    	
    	
    	if(data!=null && data.length>0){
    		
    		var text = '<table  border="1" cellpadding="5" id="tableList"><thead> <th>类别</th> <th>项目名称</th> <th>单价</th> <th>单位</th> <th>说明</thead>'
    		
    		for(var i=0;i<data.length;i++){
    			text = text + '<tr><td>'+data[i].category+'</td><td>'+data[i].quotationName+'</td><td>'+data[i].price+'</td><td>'+data[i].units+'</td><td>'+data[i].description+'</td></tr>';
    		}
    		text = text +'</tr></table>'
    		
    	}
		
		$("#fol").html(text)
           
    });
}

var basePath='<%=basePath%>';

		function upperCase()
		{
		var number=document.getElementById("number").value;
		var unitPrice=document.getElementById("unitPrice").value;
/* 		alert(number);
		alert(unitPrice); */
		totalPrices=number*unitPrice;
// 		alert(totalPrices);
		$("#totalPrice").val(totalPrices);

		
		}





		$(document).ready(function(){
		$("#category").change(function(){
			categoryName=$("#category").val();
			$.post("<%=basePath %>/quotation/getCategory.do?",{"category":categoryName},function(data){
				$("#subject").empty(); 
				$("#subject").append("<option value=''>请选择服务</option>");
				for(var i=0;i<data.length;i++){
					$("#subject").append("<option value=\'"+data[i].quotation_id+"\' name=\'"+data[i].quotation_name+"\'>"+data[i].quotation_name+"</option>");
				}
			});
			
			
		});
		binding()
		})
				
		function gradeChange(){
			quotationId=$("#subject").val();
			$.post(basePath+"/quotation/getPrice.do",{"quotationId":quotationId},function(data){
		
			 	$("#unit").val(data.units);
				$("#unit").show();
			 	$("#unitPrice").val(data.price);
				$("#unitPrice").show();
		
				var number=document.getElementById("number").value;
				var unitPrice=document.getElementById("unitPrice").value;
				totalPrices=number*unitPrice;
				$("#totalPrice").val(totalPrices);
			   });
		
		}


/* $("#signupForm").validate({
	rules : {
		subject : "required"
	},
	submitHandler : function(form) {
		submit();
	}
}); */

function submit() {
	var roomNum = $("#roomNum").val();
	var applicant = $("#applicant").val();
	var applicant_phone = $("#applicant_phone").val();
	var applicant_department = $("#applicant_department").val();
	var applicant_position = $("#applicant_position").val();
	var description = $("#description").val();
// 	var subject = $("#subject").val();
	var quotationId = $("#subject").val();
	var subject = document.getElementById("subject").options[document.getElementById("subject").selectedIndex].getAttribute("name");
	var number = $("#number").val();
	var unit = $("#unit").val();
	var unitPrice = $("#unitPrice").val();
	var totalPrice = $("#totalPrice").val();
	
	if($.trim(subject) == ''){
    	swal("请选择项目服务!");	
    	return;
    }
	if($.trim(number) == ''){
    	swal("请填写数量!");
    	return;
    }
	if($.trim(number) == ''){
		swal("数量框中请输入数字!");
    	return;
    }
	if($.trim(roomNum) == ''){
    	swal("请选择房间号!");
    	return;
    }
	var param={
			"roomNum":roomNum,
	     	"applicant":applicant,
			"applicant_phone" : applicant_phone,
			"applicant_department" : applicant_department,
			"applicant_position" : applicant_position,
			"description" : description,
			"quotationId" : quotationId,
			"subject" : subject,
			"number" : number,
			"unit" : unit,
			"unitPrice" : unitPrice,
			"totalPrice" : totalPrice
	}
	
	
	$.post('<%=basePath%>/webparkservice/addServer.do?service_type=SWFW',param,function(data){
		
		
		if (data.status == 10001) {
			swal({
				title : "提交成功！",
				type : "success",
				confirmButtonText : "确定",
				closeOnConfirm : false
			},function() {
				window.location.reload();
			});

		} else {
			swal({
				title : "提交失败！",
				text : data.msg,
				type : "error",
				confirmButtonText : "确定",
				closeOnConfirm : true
			});
		}
	
   });

}


</script>
</body>
</html>
