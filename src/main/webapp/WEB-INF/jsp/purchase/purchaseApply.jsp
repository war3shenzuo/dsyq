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
<title>园区服务-公共采购服务</title>
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
<%-- <input id="goodsName" value='${item.goodName}' type="hidden"> --%>
    		<div id="wrap">
    			<form method="post" class="form-horizontal" id="signupForm" >
			<div class="le">
			<div class="applbox" id="applbox" style="">
			
					
				    <table border="0" cellspacing="0" cellpadding="0" ">
<!-- 				  商品信息 -->
	                <h1 style="font-size:35px;" id="title">${parkservice.service_name}</h1>
	                <tr>
    					
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px; color:#666">商品信息</e></td>
    					<td><e style=" display:block; border-bottom: 1px solid rgb(161,161,161); width:100%;line-height:26px;">&nbsp;</e></td>
    				</tr>
    				<td><p>类<span style="display: inline-block; width: 28px;;"></span>别 <font color="red">*</font></p><select id="category" style="width: 249px;height: 34px; margin:0 32px 0 22px; text-indent: 10px;" class="form-control">	
							<option >请选择类别</option>
						        <c:forEach var="category" items="${category}" >
                        			<option  value ="${category.category}">${category.category}</option>
                        		</c:forEach> 
						    </select></td>
					    <td><p>服务项目 <font color="red">*</font></p>
					    <select id="goodsId" style="width: 249px;height: 34px; margin:0 32px 0 18px; text-indent: 10px;" class="form-control" onchange="gradeChange()">	

					    </select>
					    </td>
    				</tr>
    				<tr>
    					<td><p>数<span style="display: inline-block; width: 28px;;"></span>量 <font color="red">*</font></p><input type="text" class="form-control" value="" id="number"  onkeyup="upperCase()""></td>
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
    				
    					<td><p>房<span style="display: inline-block; width: 28px;;"></span>间 <font color="red">*</font></p><select id="roomNum" name="roomNum" class="form-control" style="width: 249px;height: 34px; margin:0 32px 0 22px; text-indent: 10px;" >	
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
    					<td><p>联系方式</p><input type="text" class="form-control" value="" id="applicantPhone"></td>
    				</tr>
    				<tr>
    					
    					<td><p>部<span style="display: inline-block; width: 28px;;"></span>门</p><input type="text" class="form-control" value="" id="applicantDepartment"></td>
    					<td><p>职<span style="display: inline-block; width: 28px;;"></span>位</p><input type="text" class="form-control" value="" id="applicantPosition"></td>
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
			            <a href="javascript:"  class="btn btn-primary" onclick="submit()">提交</a>
			        </div>
			    </div>
			</div>	
			</div>
			</form>
			<div class="rt">
				<h2 style="font-size:20px;">${parkservice.service_name}服务条款</h2>
				<div  class="scroll">
				<div class="fol">	
<!-- 				<input name="key" type="text" value="" id="categories"/> -->
				<select id="categories" style="width: 249px;height: 34px; margin:0 32px 0 22px; text-indent: 10px;" class="form-control">	
							<option >
						        <c:forEach var="category" items="${category}" >
                        			<option  value ="${category.category}">${category.category}</option>
                        		</c:forEach> 
                        	</option>	
						    </select>
				<button  onclick="binding()" >类别筛选</button>
				</div> 
					<div class="fol"style="padding-top: 0px"   id="fol">	
					<%-- <table  border="1" cellpadding="5">	    
						<tr>
						    <th>类别</th>
						    <th>项目名称</th>
						    <th>单价</th>
						    <th>单位</th>
						    <th>说明</th>
						</tr>			
						<tr>
								<td><c:forEach var="item" items="${item}" >
                        			<option  value ="${item.id}">${item.category}</option>
                        		</c:forEach> </td>
								<td><c:forEach var="item" items="${item}" >
                        			<option  value ="${item.id}">${item.goodName}</option>
                        		</c:forEach> </td>
								<td><c:forEach var="item" items="${item}" >
                        			<option  value ="${item.id}">${item.unitPrice}</option>
                        		</c:forEach> </td>
								<td><c:forEach var="item" items="${item}" >
                        			<option  value ="${item.id}">${item.unit}</option>
                        		</c:forEach> </td>
								<td><c:forEach var="item" items="${item}" >
                        			<option  value ="${item.id}">${item.description}</option>
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
    $.post("<%=basePath %>/purchase/getPurchaseList.do", params, function(data){
    	
    	
    	if(data!=null && data.length>0){
    		
    		var text = '<table  border="1" cellpadding="5" id="tableList"><thead> <th>类别</th> <th>项目名称</th> <th>单价</th> <th>单位</th> <th>说明</thead>'
    		
    		for(var i=0;i<data.length;i++){
    			text = text + '<tr><td>'+data[i].category+'</td><td>'+data[i].goodName+'</td><td>'+data[i].unitPrice+'</td><td>'+data[i].unit+'</td><td>'+data[i].description+'</td></tr>';
    		}
    		text = text +'</tr></table>'
    		
    	}
		
		$("#fol").html(text)
           
    });
}

$(document).ready(function(){
	$("#category").change(function(){
		categoryName=$("#category").val();
		$.post("<%=basePath %>/goods/getCategory.do?",{"category":categoryName},function(data){
			$("#goodsId").empty(); 
			$("#goodsId").append("<option value=''>请选择服务</option>");
			for(var i=0;i<data.length;i++){
				$("#goodsId").append("<option value=\'"+data[i].id+"\'>"+data[i].goodName+"</option>");
			}
		});
		
		
	});
	binding()
	})
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



function gradeChange(){
	
	$.post("<%=basePath%>/goods/getEtopGoodsInfo2.do",{"EtopGoodsId":$("#goodsId").val()},function(data){

	 	$("#unit").val(data.data.unit);
		$("#unit").show();
	 	$("#unitPrice").val(data.data.unitPrice);
		$("#unitPrice").show();
		
		$("#categorys").val(data.data.category);
		$("#categorys").show();
	 	$("#units").val(data.data.unit);
		$("#units").show();
	 	$("#unitPrices").val(data.data.unitPrice);
		$("#unitPrices").show();
	 	$("#goodsName").val(data.data.goodName);
		$("#goodsName").show();
		$("#descriptions").val(data.data.description);
		$("#descriptions").show();
	 	$("#remark").val(data.data.remark);
		$("#remark").show();
		var number=document.getElementById("number").value;
		var unitPrice=document.getElementById("unitPrice").value;
		totalPrices=number*unitPrice;
		$("#totalPrice").val(totalPrices);
	   });

}

/* var e = "<i class='fa fa-times-circle'></i> ";
$("#signupForm").validate({
	rules : {//这里加校验规则
		goodsId : "required",
		roomNum : "required",
		price: "required",
		capitalPrice: "required",
		units: "required"
	},
	messages: {//这里给对应的提示
		goodsId:e+"必填项未填",
		roomNum:e+"必填项未填",
		price:e+"必填项未填且必需是数字",
		price:{
			required:e+"必填项未填",
			number:e+"必需是数字"
		},
		capitalPrice:e+"必填项未填",
		units:e+"必填项未填"
		
	},
	submitHandler : function(form) {
		submit();
	}
}) */

function submit() {
	var roomNum = $("#roomNum").val();
	var applicant = $("#applicant").val();
	var applicantPhone = $("#applicantPhone").val();
	var applicantDepartment = $("#applicantDepartment").val();
	var applicantPosition = $("#applicantPosition").val();
	var description = $("#description").val();
	var goodsId = $("#goodsId").val();
	var goodsName = $("#goodsName").val();
	var number = $("#number").val();
	var unit = $("#unit").val();
	var unitPrice = $("#unitPrice").val();
	var totalPrice = $("#totalPrice").val();
	if($.trim(goodsId) == ''){
    	swal("请选择项目服务!");
    	return;
    }
	if($.trim(number) == ''){
    	swal("请填写数量!");
    	return;
    }
	if(isNaN($("#number").val())){    
		swal("数量框中请输入数字!");
    	return;
	}
	if($.trim(roomNum) == ''){
    	swal("请选择房间号!");
    	return;
    }
	var param={
			"roomNo":roomNum,
	     	"applicant":applicant,
			"applicantPhone" : applicantPhone,
			"applicantDepartment" : applicantDepartment,
			"applicantPosition" : applicantPosition,
			"description" : description,
			"goodsId" : goodsId,
			"goodsName":goodsName,
			"number" : number,
			"unit" : unit,
			"unitPrice" : unitPrice,
			"totalPrice" : totalPrice
	}
	
	
	$.post('<%=basePath%>/purchase/add.do?serviceType=GGCG',param,function(data){
		
		
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
