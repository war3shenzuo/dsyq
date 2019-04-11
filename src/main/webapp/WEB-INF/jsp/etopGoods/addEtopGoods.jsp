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
    <title>商品添加</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
 
    	<div class="row">
            <div class="col-md-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                        <form id="form" method="get" class="form-horizontal">
                        <div class="row">
                           <div class="col-md-12">
                           
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">商品大类<font color="red">*</font></label>
<!--                                 <div class="col-sm-5">
                                    <input type="text" class="form-control" placeholder="" name="category" id="category"> 
                                </div> -->
                            <div class="col-sm-5">
								<input type="hidden" id="categoryId" >
								<input type="text" class="form-control" id="category" name="category" >
							</div>
                            

                            <div class="col-sm-2">                                                            
                     			<a id="findCompany" class="btn btn-info" data-toggle="modal" data-target="#categoryModal" onclick="searchCategory()">大类查找</a>
							</div>
                            </div>
                            
                           <div class="hr-line-dashed"></div> 
                        
                            <div class="form-group">
                                <label class="col-sm-3 control-label">商品名称<font color="red">*</font></label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control"  style="width:400px" placeholder="" name="goodName" id="goodName"> 
                                </div>
                            </div>
                            
                            
                            <div class="hr-line-dashed"></div>
                          
                            <div class="form-group">
                                <label class="col-sm-3 control-label">单位<font color="red">*</font></label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control"  style="width:400px" placeholder="" name="unit" id="unit"> 
                                </div>
                            
                            </div>
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">单价<font color="red">*</font></label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" style="width:400px"  placeholder="" name="unitPrice" id="unitPrice"> 
                                </div>
                           
                            </div>
                          <div class="hr-line-dashed"></div>
                          
                          <div class="form-group">
								<label class="col-sm-3 control-label">说明</label>
								<div class="col-sm-7">
									<input type="text" style="width:400px" class="form-control"  id="description">
								</div>
						  </div>
						  
						<div class="hr-line-dashed"></div>
                          
							<div class="form-group">
								<label class="col-sm-3 control-label">备注</label>
								<div class="col-sm-7">
									<input type="text" style="width:400px" class="form-control"  id="remark">
								</div>
							
						 </div>
							
                            <div class="hr-line-dashed"></div>
                           </div>
                           <div class="row"> 
                           <div class="col-md-12">
	                           <div class="form-group">
	                                <div class="col-md-12" style="text-align: center;">
	                                   <!--  <a class="btn btn-primary"  onclick="addsubmit()">保存内容</a> -->
	                                    <input class="btn btn-primary" type="submit" value="提交">
	                                </div>
	                            </div>
                           </div>
                           </div>
                           </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
      </div> 
      <jsp:include page="goodspic.jsp"></jsp:include>
    <jsp:include page="../shared/js.jsp"/>
    <script type="text/javascript">
    	var basePath='<%=basePath%>';
    </script>
<%--     <script type="text/javascript" src="<%=basePath %>/myjs/goods.create.js"></script> --%>
<script type="text/javascript" src="<%=basePath %>/myjs/goods.js"></script> 
    <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript">
 	var e = "<i class='fa fa-times-circle'></i> ";
	 $("#form").validate({     
		rules: {//这里加校验规则
			goodName:"required",
			category:"required",
			unit:"required",
			unitPrice:{
				required:true,
				number:true
			}
			
		},
		messages: {//这里给对应的提示
			goodName:e+"必填项未填",
			category:e+"必填项未填",
			unit:e+"必填项未填",
			unitPrice:{
				required:e+"必填项未填",
				number:e+"必需是数字"
			}
			
		},
	    submitHandler: function(form){      
	    	prove();  //去提交   
	    }  
	})
		function searchCategory() {
			$.get('../goods/getEtopGoodsListGroup.do', {
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
//                 alert("项目已存在！");    
                
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
    </script>
    
</body>
</html>