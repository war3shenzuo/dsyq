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
    <title>园区设置-系统设置</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
	<link href="<%=basePath %>/css/plugins/dropzone/basic.css" rel="stylesheet">
    <link href="<%=basePath %>/css/plugins/dropzone/dropzone.css" rel="stylesheet">
    
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            
            <div class="ibox-content">
                <div class="row row-lg">
					 <div class="col-sm-12">
                       <form id="my-awesome-dropzone" class="dropzone" action="#">
                            <div class="dropzone-previews"></div>
                            <button type="submit" class="btn btn-primary pull-right">提交</button>
                        </form>
                     </div>
                </div>
            </div>
        </div>
    
        <!-- End Panel Other -->
    </div>
	<jsp:include page="../shared/js.jsp"/>
	<script src="<%=basePath %>/js/plugins/dropzone/dropzone.js"></script>
    <script>
        $(document).ready(function() {
			Dropzone.options.myAwesomeDropzone = {
				url: "<%=basePath%>/park/uploadImg.do", //必须填写
				method:"post",
				autoProcessQueue: false,
				uploadMultiple: true,
				parallelUploads: 20,
				maxFiles: 6,
				addRemoveLinks: true,
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
					function(files, data) {
						if(data.status==10001){
							
							var images=window.parent.document.getElementById("images");
							var img=data.data.paths;
							var parkImg=window.parent.document.getElementById("parkImg").value;
							var imgpath="";
							if(parkImg!=""){
								imgpath=parkImg+",";
							}else{
								imgpath=parkImg;
							}
							
							for(var i=0;i<img.length;i++){
								imgpath+=img[i]+","
								$(images).prepend("<div class='outimg'><img src='"+'<%=ImgProperties.LOAD_PATH%>'+img[i]+"'><a href='javascript:' onclick="+"deleteParkImgPath('"+img[i]+"',this)"+">删除</a></div>");
							}
							if(imgpath!=""){
								imgpath=imgpath.substring(0, imgpath.length-1);							
							}
							window.parent.document.getElementById("parkImg").value=imgpath;
							
							var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							parent.layer.close(index); 
					   }else{
						    swal(   data.msg, "","error");
					   }
					});
					this.on("errormultiple",
					function(files, response) {})
				}
			}
		});
    </script>
   	<script type="text/javascript">
    	var basePath='<%=basePath%>';
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/park.js"></script>
</body>
</html>