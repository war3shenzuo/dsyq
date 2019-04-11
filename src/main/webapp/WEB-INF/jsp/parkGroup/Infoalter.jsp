<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 	import="com.etop.management.properties.ImgProperties"%>
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
<title>园区组介绍</title>
</head>
<body class="grey-bg">
<input id="id" value='${presentation.id}' type="hidden">
<input id="id" value='${presentation.parkGroupId}' type="hidden">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
								<form method="post" class="form-horizontal" id="signupForm">
				<div class="ibox-content">
                        <div class="row">
						<div class="form-group">
						 <div class="col-sm-2">
								<label class="control-label">介绍类别<font color="red">*</font></label>
						 </div>
							<div class="col-sm-3">
                           <select id="presentation" class="form-control m-b">

		                       <option value="1" <c:if test="${presentation.presentation == 1}"> selected</c:if>>园区组介绍 </option>
		                       <option value="2" <c:if test="${presentation.presentation == 2}"> selected</c:if>>园区组服务介绍 </option>
		
		                   </select>
							</div>
						</div>
						</div>
						<div class="hr-line-dashed"></div>   	
					    <div class="row">
						<div class="form-group">
						  <div class="col-sm-2">
							<label class="control-label">标题<font color="red">*</font></label>
						  </div>	
							<div class="col-sm-3">
								<input type="text" id="title" class="form-control m-b"   value="${presentation.title}">
							</div>
						</div>
						</div>
						<div class="hr-line-dashed"></div>   
						    <div class="row">
                            <div class="form-group">
                             <div class="col-sm-2">
                                <label class="control-label">内容<font color="red">*</font></label>
                             </div>   
<!--                                 <div class="col-md-10"> -->
<%--                                     <textarea  rows="6" type="text" id="content" name="content"  class="form-control m-b">${presentation.content}</textarea> --%>
<!--                                 </div> -->
								<div class="col-md-10">
                                    <textarea id="textWeb" name="textWeb"  rows="6" cols="" class="ckeditor" >${presentation.content}</textarea>
                                </div>
                            </div>
                            </div>
                          		<div class="hr-line-dashed"></div>
                          		<div class="row">

								<div class="col-sm-2">
									<label class="control-label">图片</label>
								</div>	
									<div class="col-sm-3">
										<input type="hidden" value="${presentation.parkGroupPreimg}"
											class=" form-control" placeholder="" id="parkGroupPreimg">
										<div id="file-pretty">
											<input type="file" id="file" name="file" class="form-control">
										</div>
									</div>
									<div class="col-sm-1">
										<button class="btn btn-group" type="button"
											onclick="ajaxFileUpload()">上传</button>
									</div>
									<div class="col-sm-1">
										<img style="width: 100px; height: 100px; 
												id="parkGroupPreimg2"
												src="<%=ImgProperties.LOAD_PATH %>${presentation.parkGroupPreimg}" />
									</div>
							
                            </div>

				</div>	

						</div>	
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2" style="text-align: center; ">

								<a  id="sub" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submit()"> 更新 </a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../shared/js.jsp" />
    <script type="text/javascript">
    	var basePath='<%=basePath%>';
    </script>

    <script type="text/javascript" src="<%=basePath%>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">CKEDITOR.replace("textWeb");</script>
<script type="text/javascript" src="<%=basePath%>/myjs/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/myjs/parkgroup.js"></script>
<script type="text/javascript">

var id = $("#id").val();
var parkGroupId = $("#parkGroupId").val();
	function submit() {

		var presentation = $("#presentation").val();
		var title = $("#title").val();
		var content = CKEDITOR.instances.textWeb.getData();
// 		var content = $("#content").val();
		var parkGroupPreimg = $("#parkGroupPreimg").val();
		
		var param={
		     	"id":id,
		     	"parkGroupId":parkGroupId,
				"presentation" : presentation,
				"title" : title,
				"content" : content,
				"parkGroupPreimg" : parkGroupPreimg
		}
		$.post('<%=basePath%>/parkgroup/updatePresentation.do?parkGroupId=${presentation.parkGroupId}',param,function(data){
			
			
			if (data.status == 10001) {
				swal({
					title : "更新成功！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : false
				}, function() {
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index);
				});

			} else {
				swal({
					title : "更新失败！",
					text : data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				});
			}
		
	   });
			
<%-- $.ajax({
	type:'post',
	url:'<%=basePath%>/parkgroup/updatePresentation.do?parkGroupId=${presentation.parkGroupId}',
	data:{	
	     	"id":id,
	     	"parkGroupId":parkGroupId,
			"presentation" : presentation,
			"title" : title,
			"content" : content,
			"parkGroupPreimg" : parkGroupPreimg
		},    				
		success:function(data){
			if(data.status==10001)
				{
				swal("更新成功");
				}
			else {
				swal("更新失败");
			}
			
		}
	}); --%>
	}
	
	$("#signupForm").validate({
		rules : {
			presentation : "required"
		},
		submitHandler : function(form) {
			submit();
		}
	})
	function ajaxFileUpload() {
		 upload('<%=basePath%>/parkgroup/uploadImg.do?id=',"file",function(data){
			 	$("#parkGroupPreimg").val(data.data.path);
				$("#parkGroupPreimg2").attr("src",'<%=ImgProperties.LOAD_PATH%>'+ data.data.path)
				$("#parkGroupPreimg2").show();
		});
	}
</script>
</body>
</html>