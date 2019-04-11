<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.etop.management.properties.ImgProperties"%>
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
    <title>新增服务类型</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
	<style>
	.outimg{ width:80px; height:80px; margin-left:10px; float: left; }
	.outimg img{ width:80px; height:80px; }
	.outimg a{ display: block; text-align: center;height: 22px; line-height: 22px; }
	</style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <input type="hidden" class=" form-control" placeholder="" id="parkGroupId" value=${parkGroupId}> 
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                        <form method="get" class="form-horizontal" id="form">
                        <div class="row">
                           <div class="col-sm-12">
                           <div class="row">
                           
                            <div class="col-sm-12">
                          	<div class="form-group">
                                <label class="col-sm-1 control-label">服务编号<font color="red">*</font></label>
                                <div class="col-sm-5">
                                    <input type="text" class=" form-control" placeholder="名称首字母大写" value="${type.serviceCode}" name="serviceCode" id="serviceCode"> 
                                </div>
                                <label class="col-sm-1 control-label">服务名称<font color="red">*</font></label>
                                <div class="col-sm-5">
                                    <input type="text" class=" form-control" placeholder="" value="${type.serviceName}" name="serviceName" id="serviceName"> 
                                </div>
                                
                            </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            <div class="col-sm-12">
                            <div class="form-group">
                            	<label class="col-sm-1 control-label">服务类型<font color="red">*</font></label>
                                <div class="col-sm-5">
                                    <input type="text" class=" form-control" value="${type.title}" placeholder="" name="title" id="title"> 
                                </div>
                                <label class="col-sm-1 control-label">激活状态</label>
                                <div class="col-sm-5">
                                    <select class="col-md-2 form-control m-b" name="account" id="activated">
                                        <option value="1" <c:if test="${type.activated=='1'}">selected</c:if> >激活</option>
										<option value="0" <c:if test="${type.activated=='0'}">selected</c:if> >不激活</option>
                                    </select>
                                </div>
                            </div></div></div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">描述</label>
                                <div class="col-sm-5">
                                    <textarea rows="5" cols=""class=" form-control" placeholder="" name="descript" id="descript">${type.descript }</textarea>
                                </div>
                           
                                <label class="col-sm-1 control-label">条款<font color="red">*</font></label>
                                <div class="col-sm-5">
                                	<textarea rows="5" cols=""class=" form-control" placeholder="" name="item" id="item">${type.item }</textarea>
                                </div>
                            </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            <div class="col-sm-12">
                            <div class="form-group">
								<label class="col-sm-1 control-label">服务类型图片</label>
								<div class="col-sm-4">
									<input type="hidden" class=" form-control" placeholder=""
										id="img">
									<div id="file-pretty">
										<input type="file" id="file" name="file"
											class="form-control">
									</div>
								</div>
								<div class="col-sm-1">
									<button class="btn btn-group" type="button"
										onclick="ajaxFileUpload()">上传</button>
								</div>
								<div class="col-sm-1">
									<img style="width: 100px; height: 100px; display: none;"
										id="img2" />
								</div>
							</div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                           <div class="col-sm-12">
	                           <div class="form-group">
	                                <div class="col-sm-12" style="text-align: center;"><%-- 
	                                    <a class="btn btn-primary"  onclick="addsubmit('${parkId}')">保存内容</a> --%>
	                                    <input class="btn btn-primary" type="submit" value="提交">
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
    <jsp:include page="../shared/js.jsp"/>
    <script src="<%=basePath%>/js/plugins/dropzone/dropzone.js"></script>
   	<script type="text/javascript">
    	var basePath='<%=basePath%>';
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/service.js"></script>
    <script type="text/javascript">
    
     var e = "<i class='fa fa-times-circle'></i> ";
	 $("#form").validate({     
		rules: {//这里加校验规则
			serviceCode:"required",
			serviceName:"required",
			title:"required",
			item:"required"
			
		},
		messages: {//这里给对应的提示
			serviceCode:e+"必填项未填",
			serviceName:e+"必填项未填",
			title:e+"必填项未填",
			item:e+"必填项未填"
			
		},
	    submitHandler: function(form){      
		    updatesubmit();  //去提交   
	    }  
	})
	function ajaxFileUpload() {
		upload('<%=basePath%>/etopService/uploadImg.do?id=${id}',"file",function(data){
			$("#img").val(data.data.path);
			$("#img2").attr("src",'<%=ImgProperties.LOAD_PATH%>'+ data.data.path)
			$("#img2").show();
		});
	}
    </script>
    
    
</body>
</html>