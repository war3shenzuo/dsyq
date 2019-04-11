<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../shared/css.jsp" />
<title>商务服务详情</title>
<style>
	table a:hover{text-decoration: underline;}
	table a{margin-left: 10px}
</style>
</head>
<body class="grey-bg">
<div class="wrapper wrapper-content animated fadeInRight">
		<div class="col-sm-12">
	<div class="ibox float-e-margins">
		<div class="ibox-title">
			<h5>
				<normal>商务服务信息</normal>
			</h5>
		</div>
		<div class="ibox-content">
			<form method="get" class="form-horizontal"  id="signupFormss">
		
				<div class="form-group">	
					<label class="col-sm-3 control-label">类别<font color="red">*</font></label>
					<div class="col-sm-5">
<!-- 						<input type="text" style="width:400px" class="form-control" name="category" id="category"> -->
								<input type="hidden" id="categoryId" >
								<input type="text" class="form-control" id="category" name="category" >
                     </div>     
                            <div class="col-sm-2">                                                            
                     			<a id="findCompany" class="btn btn-info" data-toggle="modal" data-target="#categoryModal" onclick="searchCategory()">大类查找</a>
							</div>
	            </div>
	            
				<div class="form-group">	
					<label class="col-sm-3 control-label">服务名称<font color="red">*</font></label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" name="quotationName" id="quotationName">
	                </div>
					
				</div>
					<div class="form-group">
					<label class="col-sm-3 control-label">价格<font color="red">*</font></label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" name="price" id="price">
					</div>
                   </div>
                   
<!-- 				<div class="form-group">
					<label class="col-sm-3 control-label">大写金额<font color="red">*</font></label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" name="capitalPrice" id="capitalPrice">
					</div>
				</div> -->
				<div class="form-group">
					<label class="col-sm-3 control-label">单位<font color="red">*</font></label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control" name="units" id="units">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">说明</label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control"  id="description">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">备注</label>
					<div class="col-sm-7">
						<input type="text" style="width:400px" class="form-control"  id="remark">
					</div>
				</div>
				
			</div>
				<div class="form-group">
					<div class="col-sm-7 col-sm-offset-2" style="text-align: center; ">
<!-- 						<a  id="sub" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submit()"> 新建 </a> -->
						<input class="btn btn-primary" type="submit" value="新建">
					</div>
				</div>
				</form>
</div>
</div>
</div>
<jsp:include page="repairpic.jsp"></jsp:include>
<jsp:include page="../shared/js.jsp" />
<script type="text/javascript">
var basePath='<%=basePath%>';
	var e = "<i class='fa fa-times-circle'></i> ";
	$("#signupFormss").validate({
		rules : {//这里加校验规则
			category: "required",
			quotationName : "required",
				price: "required",
				capitalPrice: "required",
				units: "required"
		},
		messages: {//这里给对应的提示
			category:e+"必填项未填",
			quotationName:e+"必填项未填",
			price:e+"必填项未填且必需是数字",
			price:{
				required:e+"必填项未填",
				number:e+"必需是数字"
			},
			capitalPrice:e+"必填项未填",
			units:e+"必填项未填"
			
		},
		submitHandler : function(form) {
			prove();
		}
	})

var quotationId = $("#quotationId").val();

	function submit() {

		
		var category = $("#category").val();
		var quotationName = $("#quotationName").val();
		var price = $("#price").val();
		var capitalPrice = $("#capitalPrice").val();
		var description = $("#description").val();
		var units = $("#units").val();
		var remark = $("#remark").val();
		
		var param={
		     	"category":category,
		     	"quotationName":quotationName,
				"price" : price,
				"capitalPrice" : capitalPrice,
				"description" : description,
				"units" : units,
				"remark":remark
		}
		$.post('<%=basePath%>/recruitment/addRecruitment.do',param,function(data){
			
			
			if (data.status == 10001) {
				swal({
					title : "新建成功！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : false
				}, function() {
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index);
				});

			} else {
				swal({
					title : "新建失败！",
					text : data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				});
			}
		
	   });
	}
	function searchCategory() {
		$.get('../recruitment/makeRecruitmentGroup.do', {
			'category' : $('#categoryText').val()
		}, function(data) {
			var rows = data.rows;
			var selector = $('#categorySelect');
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
	$(document).ready(function() {
		

		$('#categoryConfirm').click(function() {
				$("#categoryId").val($("#categorySelect").val());
				$("#category").val($("#categorySelect option:selected").text());
				
		});


	});
	
function prove(){  
        
        var quotationName = $("#quotationName").val();
        if(quotationName == "")  
        {  
           alert("项目名称不能为空!");  
           return;  
        }  
        $.ajax({  
               type: "POST",      
                url: "<%=basePath%>/quotation/proveQuotationName.do",      
//              data: "quotationName="+quotationName, 
                data: {quotationName: $("#quotationName").val(), type:"person"}, 
                success: function(data){  
               if(data.status == 10001){     
//                 alert("项目已存在！");    
                
                swal({
					title : "该服务已存在！",
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : false
				});
                return;
               }else{

       			submit();
               }
               
               }            
               });     
       }
</script>
</body>
</html>