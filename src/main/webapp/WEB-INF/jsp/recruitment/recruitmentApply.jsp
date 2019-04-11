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
<title>园区服务-人员代招</title>
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
	                <h1 style="font-size:35px;" id="title">${parkservice.service_name}</h1>
	                 					<tr>
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px; color:#666">代招需求</e></td>
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
    				    <td><p>需求人数 <font color="red">*</font></p><input type="text" class="form-control" value="" id="number"  onkeyup="upperCase()""></td>
    					<td><p>单<span style="display: inline-block; width: 28px;;"></span>位</p><input type="text" class="form-control" value="" id="unit" readonly="readonly"></td>
    				</tr>
    				<tr>
    					<td><p>单<span style="display: inline-block; width: 28px;;"></span>价</p><input type="text" class="form-control" value="" id="unitPrice"readonly="readonly"></td>
    					<td><p>合计金额</p><input type="text" readonly="readonly" class="form-control" value="" id="totalPrice" ></td>
    					
<!-- 	                岗位信息 -->
	                <tr>
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px; color:#666">岗位信息</e></td>
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;">&nbsp;</e></td>
    				</tr>
    				<tr>
						<td><p>岗位名称<font color="red">*</font></p><input type="text" class="form-control" value="" id="jobName"></td>
						
    				</tr>
    				<tr>
    					<td><p>部<span style="display: inline-block; width: 28px;;"></span>门 </p><input type="text" class="form-control" value="" id="department"></td>
    					<td><p>职<span style="display: inline-block; width: 28px;;"></span>位</p><input type="text" class="form-control" value="" id="position"></td>
  						
    				</tr>
    				<tr>
    					<td><p>薪<span style="display: inline-block; width: 28px;;"></span>资</p><input type="text" class="form-control" value="" id="salary" ></td>
    					
					    <td><p>保<span style="display: inline-block; width: 28px;;"></span>险</p><input type="text" class="form-control" value="" id="insurance" ></td>
    				</tr>
    				<tr>
    					<td><p>岗位职责</p>
<!--     					<input type="text" class="form-control" value="" id="duties" ></td>  -->
    					<textarea id="duties" rows="5" name="textWeb1" cols="40" class="form-control"  style="width: 243px;margin:7px 32px 0 18px; vertical-align:middle; text-indent: 10px;"></textarea></td>
    				    <td><p>福<span style="display: inline-block; width: 28px;;"></span>利</p>
<!--     				    <input type="text" class="form-control" value="" id="benefit" ></td> -->
						<textarea id="benefit" rows="5" name="textWeb2" cols="40" class="form-control"  style="width: 243px;margin:7px 32px 0 18px; vertical-align:middle; text-indent: 10px;"></textarea></td>
    				</tr>
    				<tr>
    					
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px; color:#666">岗位要求</e></td>
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;">&nbsp;</e></td>
    				</tr>
    				<tr>
					    <td><p>工作年限</p><input type="text" class="form-control" value="" id="workAge" ></td>
					    <td><p style=" margin-left: -120px; font-size: 12px; font-weight: 400">年以上</p></td>
    				</tr>
    				<tr>
    					<td><p>学<span style="display: inline-block; width: 28px;;"></span>历</p><input type="text" class="form-control" value="" id="education"></td>
    					<td><p>政治面貌</p><input type="text" class="form-control" value="" id="politicalFeature"></td>
    				</tr>
    				<tr>
    					<td><p>性<span style="display: inline-block; width: 28px;;"></span>别</p>
<!--     					<input type="text" class="form-control" value="" id="sexual"></td> -->
    					<select style="width: 249px;height: 34px; margin:0 32px 0 18px; text-indent: 10px;" class="form-control m-b" name="sexual" id="sexual">
								<option value="3">无要求</option>
								<option value="1">男</option>
								<option value="2">女</option>
							</select></td>
    					<td><p>年<span style="display: inline-block; width: 28px;;"></span>龄</p><input type="text" style=" width:28%; display: inline-block;    margin: 0 2px 0 22px;" class="form-control" value="" id="ageOne">至<input type="text" style=" width:28%; display: inline-block;    margin: 0 2px 0 2px;" class="form-control" value="" id="ageTwo"></td>
<!--     				    <td><p>年<span style="display: inline-block; width: 28px;;"></span>龄</p><input type="text" class="form-control" value="" id="age"></td> -->
    				</tr>
    				<tr>
    					<td><p>婚<span style="display: inline-block; width: 28px;;"></span>否</p>
<!--     					<input type="text" class="form-control" value="" id="marriage"></td> -->
    					<select style="width: 249px;height: 34px; margin:0 32px 0 18px; text-indent: 10px;" class="form-control m-b" name="marriage" id="marriage">
								<option value="3">无要求</option>
								<option value="1">已婚</option>
								<option value="2">未婚</option>
							</select></td>
    					<td><p>生<span style="display: inline-block; width: 28px;;"></span>育</p>
<!--     					<input type="text" class="form-control" value="" id="childbearing"></td> -->
						<select style="width: 249px;height: 34px; margin:0 32px 0 18px; text-indent: 10px;" class="form-control m-b" name="childbearing" id="childbearing">
								<option value="3">无要求</option>
								<option value="1">已育</option>
								<option value="2">未育</option>
							</select></td>
    				</tr>
    				<tr>	
    					<td><p>其他要求</p>
<!--     					<input type="text" class="form-control" value="" id="description"> -->
     					 <textarea id="otherRequire" rows="5" name="textWeb" cols="40" class="form-control"  style="width: 243px;margin:7px 32px 0 18px; vertical-align:middle; text-indent: 10px;"></textarea> 
    					</td>
    				</tr>

<!--                         申请人信息 -->
					<tr>
    					
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;color:#666">申请人信息</e></td>
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;">&nbsp;</e></td>
    				</tr>
    				<tr>
    				<td><p>房<span style="display: inline-block; width: 28px;;"></span>间 <font color="red">*</font></p><select id="roomNum" class="form-control" style="width: 249px;height: 34px; margin:0 32px 0 22px; text-indent: 10px;" >	
						    <option  value=''>请选择房间</option>
						        <c:forEach var="room" items="${room}">
                        			<option>${room.room}</option>
                        		</c:forEach> 
						    </select></td>
    					<td><p>申请人 </p><input type="text" class="form-control" value="" id="applicant"></td>
    				</tr>
    				<tr>
    					<td><p>联系方式</p><input type="text" class="form-control" value="" id="applicantPhone"></td>
    					<td><p>部<span style="display: inline-block; width: 28px;;"></span>门</p><input type="text" class="form-control" value="" id="applicantDepartment"></td>
    				</tr>
    				<tr>	
    					<td><p>职<span style="display: inline-block; width: 28px;;"></span>位</p><input type="text" class="form-control" value="" id="applicantPosition"></td>
    					<td><p>补充信息</p>
<!--     					<input type="text" class="form-control" value="" id="description"> -->
     					 <textarea id="description" rows="3" name="textWeb" cols="40" class="form-control"  style="width: 243px;margin:7px 32px 0 18px; vertical-align:middle; text-indent: 10px;"></textarea> 
    					</td>
    				</tr>
 
 

    			</table>
    			<div class="form-group">
			        <div class="col-sm-6 col-sm-offset-5" >
			            <a  href="javascript:"  class="btn btn-primary" onclick="submit()">提交</a>
			        </div>
			    </div>
			</div>	
			</div>
			</form>
			<div class="rt">
				<h2 style="font-size:20px;">${parkservice.service_name}服务条款</h2>
				<div  class="scroll">
				<div class="fol" >	
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
<%-- 					<table  border="1" cellpadding="5">	    
						<tr>
						    <th>类别</th>
						    <th>项目名称</th>
						    <th>单价</th>
						    <th>单位</th>
						    <th>说明</th>
						</tr>			
						<tr>
								<td><c:forEach var="item" items="${item}" >
                        			<option  value ="${item.quotationId}">${item.category}</option>
                        		</c:forEach> </td>
								<td><c:forEach var="item" items="${item}" >
                        			<option  value ="${item.quotationId}">${item.quotationName}</option>
                        		</c:forEach> </td>
								<td><c:forEach var="item" items="${item}" >
                        			<option  value ="${item.quotationId}">${item.price}</option>
                        		</c:forEach> </td>
								<td><c:forEach var="item" items="${item}" >
                        			<option  value ="${item.quotationId}">${item.units}</option>
                        		</c:forEach> </td>
								<td><c:forEach var="item" items="${item}" >
                        			<option  value ="${item.quotationId}">${item.description}</option>
                        		</c:forEach> </td>
                         </tr>
                         </table> --%>
	     			</div>
				</div>
			</div>
		</div>
    
<jsp:include page="../footer.jsp" flush="true" />
<script type="text/javascript">
var basePath='<%=basePath%>';


function binding(){
    var params = {
        "categories" : $('#categories').val()
    }
    $.post("<%=basePath %>/quotation/getRecruitmentList.do", params, function(data){
    	
    	
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

/* $("#signupForm").validate({
	rules : {
		subject : "required"
	},
	submitHandler : function(form) {
		submit();
	}
}); */

function submit() {
	var jobName = $("#jobName").val();
	var department = $("#department").val();
	var position = $("#position").val();
	var duties = $("#duties").val();
	var salary = $("#salary").val();
	var insurance = $("#insurance").val();
	var benefit = $("#benefit").val();
	var workAge = $("#workAge").val();
	var education = $("#education").val();
	var politicalFeature = $("#politicalFeature").val();
	var sexual = $("#sexual").val();
	var ageOne = $("#ageOne").val();
	var ageTwo = $("#ageTwo").val();
	var marriage = $("#marriage").val();
	var childbearing = $("#childbearing").val();
	var otherRequire = $("#otherRequire").val();
	var roomNum = $("#roomNum").val();
	var applicant = $("#applicant").val();
	var applicantPhone = $("#applicantPhone").val();
	var applicantDepartment = $("#applicantDepartment").val();
	var applicantPosition = $("#applicantPosition").val();
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
    	swal("请填写需求人数!");
    	return;
    }
	if(isNaN($("#number").val())){    
		swal("需求人数框中请输入数字!");
    	return;
	}
	if($.trim(jobName) == ''){
    	swal("请选择岗位名称!");
    	return;
    }
	if($.trim(roomNum) == ''){
    	swal("请选择房间号!");
    	return;
    }
	var param={
			"jobName":jobName,
			"quotationId" : quotationId,
	     	"department":department,
			"position" : position,
			"duties" : duties,
			"salary" : salary,
			"insurance" : insurance,
			"benefit" : benefit,
			"workAge" : workAge,
			"education" : education,
			"politicalFeature" : politicalFeature,
			"sexual" : sexual,
			"ageOne" : ageOne,
			"ageTwo" : ageTwo,
			"marriage" : marriage,
			"childbearing" : childbearing,
			"otherRequire" : otherRequire,
			"roomNum":roomNum,
	     	"applicant":applicant,
			"applicantPhone" : applicantPhone,
			"applicantDepartment" : applicantDepartment,
			"applicantPosition" : applicantPosition,
			"description" : description,
			"subject" : subject,
			"number" : number,
			"unit" : unit,
			"unitPrice" : unitPrice,
			"totalPrice" : totalPrice
	}
	
	
	$.post('<%=basePath%>/recruitment/add.do?serviceType=RYDZ',param,function(data){
		
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
				title : "提交失败！",
				text : data.msg,
				type : "error",
				confirmButtonText : "确定",
				closeOnConfirm : false
			});
		}
	
   });

}

function gradeChange(){
	quotationId=$("#subject").val();
	$.post(basePath+"/quotation/getPrice.do",{"quotationId":quotationId},function(data){

	 	$("#unit").val(data.units);
		$("#unit").show();
	 	$("#unitPrice").val(data.price);
		$("#unitPrice").show();
		
	 	$("#categorys").val(data.category);
		$("#categorys").show();
	 	$("#units").val(data.units);
		$("#units").show();
	 	$("#unitPrices").val(data.price);
		$("#unitPrices").show();
	 	$("#subjects").val(data.quotation_name);
		$("#subjects").show();
	 	$("#descriptions").val(data.description);
		$("#descriptions").show();
		$("#remark").val(data.remark);
		$("#remark").show();
		
		var number=document.getElementById("number").value;
		var unitPrice=document.getElementById("unitPrice").value;
		totalPrices=number*unitPrice;
		$("#totalPrice").val(totalPrices);
	   });

}



function upperCase()
{
var number=document.getElementById("number").value;
var unitPrice=document.getElementById("unitPrice").value;
/* 		alert(number);
alert(unitPrice); */
totalPrices=number*unitPrice;
//	alert(totalPrices);
$("#totalPrice").val(totalPrices);


}
</script>
</body>
</html>
