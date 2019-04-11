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
    <title>咨询添加-平台管理</title>
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
                           <div class="row">
                            <div class="form-group">
                                <label class="col-md-1 control-label">资讯标题<font color="red">*</font></label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" placeholder="" name="title" id="title"> 
                                </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
	                        <div class="row">
	                        <div class="form-group">
	                           <label class="col-md-1 control-label">激活状态</label>
                                <div class="col-md-3">
                                    <select class="form-control" name="state" id="state">
                                        <option value="1">激活</option>
                                        <option value="0">不激活</option>
                                    </select>
                                </div>
                             </div>
	                         </div>
                           <div class="hr-line-dashed"></div> 
                           <div class="row">
                            <div class="form-group">
                                <label class="col-md-1 control-label">资讯内容<font color="red">*</font></label>
                                <div class="col-md-11">
                                    <textarea id="textWeb" name="textWeb" rows="" cols="" class="ckeditor" ></textarea>
                                </div>
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
    <jsp:include page="../shared/js.jsp"/>
    <script type="text/javascript">
    	var basePath='<%=basePath%>';
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/news.js"></script>
    <script type="text/javascript" src="<%=basePath%>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">CKEDITOR.replace("textWeb");</script> 
    <script type="text/javascript">
 	var e = "<i class='fa fa-times-circle'></i> ";
	 $("#form").validate({     
		rules: {//这里加校验规则
			title:"required",
			
		},
		messages: {//这里给对应的提示
			title:e+"必填项未填",
			
		},
	    submitHandler: function(form){      
		 addsubmit();  //去提交   
	    }  
	})
    </script>
    
</body>
</html>