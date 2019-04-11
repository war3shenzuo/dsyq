<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>帐号管理-系统设置</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            
            <div class="ibox-content">
                <div class="row row-lg">
                    <label class="col-sm-12 control-label">系统功能</label>
                    <div class="hr-line-dashed"></div>
                    <div class="row">
	                    <div class="col-sm-2">
	                        <div id="tree"></div>
	                         <div class="col-sm-12" >
			                    <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
			                       <!--   <a class="btn btn-outline btn-default" 
			                            onclick="openAddPage('新增父角色','800px','600px','<%=basePath %>/func/addPage.do','')" >
			                            <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
			                            <span>新增父节点</span>
			                        </a>-->
			                    </div>
	                    	</div>
	                    </div>
	                    <div class="col-sm-10">
	                    <div class="row">
				            <div class="col-sm-6">
				                <div class="tabs-container">
				                    <ul class="nav nav-tabs">
				                        <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">角色详情</a>
				                        </li>
				                        <!-- <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">绑定用户</a>
				                        </li>
				                        <li class=""><a data-toggle="tab" href="#tab-3" aria-expanded="false">绑定功能</a>
				                        </li> -->
				                    </ul>
				                    <div class="tab-content">
				                        <div id="tab-1" class="tab-pane active">
				                            <div class="panel-body">
				                             	<form method="get" class="form-horizontal">
					                        	<input type="hidden" id="id">	
					                          	<div class="form-group">
					                                <label class="col-sm-2 control-label">功能名称 </label>
					                                <div class="col-sm-10">
					                                    <input type="text" class="form-control" placeholder="" id="funcName"> 
					                                </div>
					                            </div>
					                            <div class="hr-line-dashed"></div>
					                            <div class="form-group">
					                                <label class="col-sm-2 control-label">功能编码</label>
					                                <div class="col-sm-10">
					                                    <input type="text" class="form-control" placeholder="" id="funcCode"> 
					                                </div>
					                            </div>
					                            <div class="hr-line-dashed"></div>
					                            <div class="form-group">
					                                <label class="col-sm-2 control-label">功能描述
					                                </label>
					                                <div class="col-sm-10">
					                                    <input type="text" class="form-control" placeholder="" id="funcDescribe"> 
					                                </div>
					                            </div>
					                            <div class="hr-line-dashed"></div>
					                            <div class="form-group">
					                                <label class="col-sm-2 control-label">功能链接
					                                </label>
					                                <div class="col-sm-10">
					                                    <input type="text" class="form-control" placeholder="" id="loadUrl"> 
					                                </div>
					                            </div>
					                             <div class="hr-line-dashed"></div>
					                            <div class="form-group">
					                                <label class="col-sm-2 control-label">激活状态</label>
					                                <div class="col-sm-10">
					                                    <select class="form-control" name="account" id="activated">
					                                        <option value="1">激活</option>
					                                        <option value="0">不激活</option>
					                                    </select>
					                                </div>
					                            </div>
					                                                    
					                            <div class="hr-line-dashed"></div>
					                            <div class="form-group">
					                                <div id="updateChildren" style="display: none;" class="col-sm-4 col-sm-offset-2">
					                                    <a class="btn btn-primary"  onclick="updatasubmit()">更新</a>
					                                </div>
					                                <!--  <div id="addChildren" style="display: none;" class="col-sm-4 col-sm-offset-2">
					                                    <a class="btn btn-primary"  onclick="addChildren()">新增子节点</a>
					                                </div>-->
					                            </div>
					                        </form>
				                            </div>
				                        </div>
				                        <div id="tab-2" class="tab-pane">
				                            <div class="panel-body">
				                                <strong>移动设备优先</strong>
				                                <p>在 Bootstrap 2 中，我们对框架中的某些关键部分增加了对移动设备友好的样式。而在 Bootstrap 3 中，我们重写了整个框架，使其一开始就是对移动设备友好的。这次不是简单的增加一些可选的针对移动设备的样式，而是直接融合进了框架的内核中。也就是说，Bootstrap 是移动设备优先的。针对移动设备的样式融合进了框架的每个角落，而不是增加一个额外的文件。</p>
				                            </div>
				                        </div>
				                        <div id="tab-3" class="tab-pane">
				                            <div class="panel-body">
				                                <strong>移动设备优先</strong>
				                                <p>在 Bootstrap 2 中，我们对框架中的某些关键部分增加了对移动设备友好的样式。而在 Bootstrap 3 中，我们重写了整个框架，使其一开始就是对移动设备友好的。这次不是简单的增加一些可选的针对移动设备的样式，而是直接融合进了框架的内核中。也就是说，Bootstrap 是移动设备优先的。针对移动设备的样式融合进了框架的每个角落，而不是增加一个额外的文件。</p>
				                            </div>
				                        </div>
				                    </div>
				
				
				                </div>
				            </div>
			            </div>
	                    </div>
	                </div>
                </div>
            </div>
        </div>
    
        <!-- End Panel Other -->
    </div>
    
	<jsp:include page="../shared/js.jsp"/>
	<script type="text/javascript">
    	var basePath='<%=basePath%>';
    </script>
	<script type="text/javascript" src="<%=basePath %>/myjs/fun.js"></script>
</body>
</html>