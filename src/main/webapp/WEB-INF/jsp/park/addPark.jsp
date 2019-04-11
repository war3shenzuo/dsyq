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
    <title>添加园区-园区管理</title>
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
                                <label class="col-sm-1 control-label">园区编码<font color="red">*</font></label>
                                <div class="col-sm-2">
                                    <input type="text" class=" form-control" placeholder="" name="parkCode" id="parkCode"> 
                                </div>
                                <label class="col-sm-1 control-label">园区名称<font color="red">*</font></label>
                                <div class="col-sm-2">
                                    <input type="text" class=" form-control" placeholder="" name="parkName" id="parkName"> 
                                </div>
                                <label class="col-sm-1 control-label">园区简写<font color="red">*</font></label>
                                <div class="col-sm-2">
                                    <input type="text" class=" form-control" placeholder="" name="parkTitle" id="parkTitle"> 
                                </div>
                                <label class="col-sm-1 control-label">园区地址<font color="red">*</font></label>
                                <div class="col-sm-2">
                                    <input type="text" class=" form-control" placeholder="" name="address" id="address"> 
                                </div>
                            </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                             
                            <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">联系人<font color="red">*</font></label>
                                <div class="col-sm-2">
                                    <input type="text" class=" form-control" placeholder="" name="contacts" id="contacts"> 
                                </div>
                                <label class="col-sm-1 control-label">QQ</label>
                                <div class="col-sm-2">
                                    <input type="text" class=" form-control" placeholder="" name="qq" id="qq"> 
                                </div>
                           
                                <label class="col-sm-1 control-label">微信</label>
                                <div class="col-sm-2">
                                    <input type="text" class=" form-control" placeholder="" name="wechat" id="wechat"> 
                                </div>
                            </div>
                            </div>
                            
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            
                            <div class="col-sm-12">
                            <div class="form-group">
                            	<label class="col-sm-1 control-label">联系人电话<font color="red">*</font></label>
                                <div class="col-sm-2">
                                    <input type="text" class=" form-control" placeholder="" id="mobile" name="mobile"> 
                                </div>
                                <label class="col-sm-1 control-label">备用电话</label>
                                <div class="col-sm-2">
                                    <input type="text" class=" form-control" placeholder="" id="spareMobile" name="spareMobile"> 
                                </div>
								 <label class="col-sm-1 control-label">城市</label>
                                <div class="col-sm-2">
                                    <input type="text" class=" form-control" placeholder="" name="cityChoice" id="cityChoice"> 
                                </div>
                            </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                             
                            <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">园区简介</label>
                                <div class="col-sm-4">
                                    <textarea rows="" cols=""class=" form-control" placeholder="" name="parkDescribe" id="parkDescribe"></textarea>
                                </div>
                           
                                <label class="col-sm-1 control-label">招商政策</label>
                                <div class="col-sm-4">
                                	<textarea rows="" cols=""class=" form-control" placeholder="" name="policy" id="policy"></textarea>
                                </div>
                                
                            </div>
                            </div>
                            
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            
                            <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">微信二维码</label>
                                <div class="col-sm-2">
                                    <input type="hidden" class=" form-control" placeholder="" id="wechatQr"> 
                                    <div id="file-pretty">
                                    	<input type="file" id="file1" name="file1" class="form-control">
                                    </div>	
                                </div>
                                <div class="col-sm-1">
                                	<button class="btn btn-group" type="button" onclick="ajaxFileUpload('file1')" >上传</button>
                                </div>
                                <div class="col-sm-1">
                                	 <img style="display: none; width: 100px;height: 100px;" id="fileImg1" src="img/profile_small.jpg"/>
                                </div>
                                <label class="col-sm-1 control-label">园区地址图片</label>
                                <div class="col-sm-2">
                                	
                                    <input type="hidden" class=" form-control" placeholder="" id="addressImg">
	                                <div id="file-pretty">
		                                <input type="file" id="file3" name="file3" class="form-control">
	                        		</div>	
                                </div>
                                <div class="col-sm-1">
                               	    <button class="btn btn-group" type="button" onclick="ajaxFileUpload('file3')" >上传</button>
                                </div>
                                <div class="col-sm-1">
                                	<img style="display: none; width: 100px;height: 100px;" id="fileImg3" src="img/profile_small.jpg"/>
                                </div>
                                
                            </div>
                            </div>
                           
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            <div class="col-sm-12">
                            <div class="form-group">
                            	<label class="col-sm-1 control-label">园区图片</label>
                            	<div class="col-sm-11"  id="images">
                          			<a class="btn btn-primary"  onclick="openUploadImgPage()" style=" margin-left: 20px;">上传图片</a>
                          		</div>
                          		<input type="hidden" class=" form-control" placeholder="" id="parkImg"> 
                            </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row"> 
                            <div class="col-sm-12">
	                        <div class="form-group">
	                        	<label class="col-sm-1 control-label">激活状态</label>
                                <div class="col-sm-2">
                                    <select class="col-md-2 form-control m-b" name="account" id="activated">
                                        <option value="1">激活</option>
                                        <option value="0">不激活</option>
                                    </select>
                                </div>
                            </div>
                            </div>
                            
                           </div>                      
                            
                           </div>
                           
                           
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
    <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/park.js"></script>
    <script type="text/javascript">
    
      $(document).ready(function() {
		Dropzone.options.myAwesomeDropzone = {
			autoProcessQueue: false,
			uploadMultiple: true,
			parallelUploads: 100,
			maxFiles: 100,
			acceptedFiles: ".jpg,.gif,.png", //上传的类型
			 acceptedFiles: ".jpg,.gif,.png", //上传的类型
			init: function() {
				var myDropzone = this;
				this.element.querySelector("button[type=submit]").addEventListener("click",
				function(e) {
					e.preventDefault();
					e.stopPropagation();
					myDropzone.processQueue()
				});
				this.on("sendingmultiple",
				function() {});
				this.on("successmultiple",
				function(files, response) {});
				this.on("errormultiple",
				function(files, response) {})
			}
		}
	});
     var e = "<i class='fa fa-times-circle'></i> ";
	 $("#form").validate({     
		rules: {//这里加校验规则
			parkCode:{
				required:e+"必填项未填",
				lowercase:["a","z"]
			},
			parkName:"required",
			parkTitle:"required",
			address:"required",
			contacts:"required",
			mobile:{
				required:true,
				digits:true
			}
		},
		messages: {//这里给对应的提示
			parkCode:{
				required:e+"必填项未填",
				lowercase:e+"必需是数字和小写字母"
			},
			parkName:e+"必填项未填",
			parkTitle:e+"必填项未填",
			address:e+"必填项未填",
			contacts:e+"必填项未填",
			mobile:{
				required:e+"必填项未填",
				digits:e+"必需是数字和小写字母"
			}
		},
	    submitHandler: function(form){      
		 addsubmit('${parkId}');  //去提交   
	    }  
	})
    function ajaxFileUpload(fileId) {
		upload('<%=basePath%>/park/uploadImg.do?parkId=${parkId}',fileId,function(data){
			if(fileId=='file1'){
				$("#fileImg1").attr('src','<%=ImgProperties.LOAD_PATH%>'+data.data.paths[0]);
				$("#wechatQr").val(data.data.paths[0]);
				$("#fileImg1").show();
			}else if(fileId == 'file2'){
				$("#fileImg2").attr('src','<%=ImgProperties.LOAD_PATH%>'+data.data.paths[0]);
				$("#parkImg").val(data.data.paths[0]);
				$("#fileImg2").show();
			}else{
				$("#fileImg3").attr('src','<%=ImgProperties.LOAD_PATH%>'+data.data.paths[0]);
				$("#addressImg").val(data.data.paths[0]);
				$("#fileImg3").show();
			}
		});
    }
	 
	 /**绑定用户*/
	 function openUploadImgPage(){
	 	openSelectPage('上传园区图片','80%','80%',basePath+'/park/openUploadPage.do?parkId=${parkId}',null);
	 } 
	 
	 function deleteParkImgPath(path,obj){
		 var paths =$("#parkImg").val().split(",")
		 var result="";
		 for(var i=0 ;i<paths.length;i++){
			 if(paths[i]!=path){
				 result+=paths[i]+",";
			 }
		 }
		 if(result!=""){
			 result=result.substring(0, result.length-1);	
		 }
		 $("#parkImg").val(result);
		 $(obj).parent().remove();
	 }
    </script>
    
    
</body>
</html>