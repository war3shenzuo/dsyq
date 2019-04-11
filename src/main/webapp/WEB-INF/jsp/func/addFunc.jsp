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
    <title>园区列表-园区管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
 
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                    	
                        <form id="form" method="get" class="form-horizontal">
                        	<input type="hidden" id="id">	
                        	<div class="row">	
                          	<div class="form-group">
                                <label class="col-sm-2 control-label">功能名称<font color="red">*</font> </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="" name="funcName" id="funcName"> 
                                </div>
                            
                                <label class="col-sm-2 control-label">功能编码<font color="red">*</font></label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="" name="funcCode" id="funcCode"> 
                                </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">	
                            <div class="form-group">
                                <label class="col-sm-2 control-label">功能描述
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="" name="funcDescribe" id="funcDescribe"> 
                                </div>
                            
                                <label class="col-sm-2 control-label">功能链接
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="" name="loadUrl" id="loadUrl"> 
                                </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">	
                            <div class="form-group">
                            	<div style="display: none;">
	                                <label class="col-sm-2 control-label">父类Id
	                                </label>
	                                <div class="col-sm-4">
	                                    <input type="text" class="form-control" value="${parentId}" disabled="disabled" placeholder="" id="parentId"> 
	                                </div>
                                </div>
                                <label class="col-sm-2 control-label">排序Id
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="" id="sortId"> 
                                </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">激活状态</label>
                                <div class="col-sm-4">
                                    <select class="col-md-2 form-control m-b" name="account" id="activated">
                                        <option value="1">激活</option>
                                        <option value="0">不激活</option>
                                    </select>
                                </div>
                            </div>
                            </div>
                                                    
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <a class="btn btn-primary"  onclick="addsubmit()">保存</a>
                                </div>
                                <div id="addChildren" style="display: none;" class="col-sm-4 col-sm-offset-2">
                                    <!-- <a class="btn btn-primary"  onclick="addChildren()">新增子节点</a> -->
                                    <input class="btn btn-primary" type="submit" value="提交">
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
    <script type="text/javascript" src="<%=basePath %>/myjs/fun.js"></script>
    <script type="text/javascript">
 	var e = "<i class='fa fa-times-circle'></i> ";
	 $("#form").validate({     
		rules: {//这里加校验规则
			funcName:"required",
			funcCode:{
				required:e+"必填项未填",
				lowercase:["a","z"]
			}
			
		},
		messages: {//这里给对应的提示
			funcName:e+"必填项未填",
			funcCode:{
				required:e+"必填项未填",
				lowercase:e+"必需是数字和小写字母"
			}
			
		},
	    submitHandler: function(form){      
		 addsubmit();  //去提交   
	    }  
	})
    </script>
</body>
</html>