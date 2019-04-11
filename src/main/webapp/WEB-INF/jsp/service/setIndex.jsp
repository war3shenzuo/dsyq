<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
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
    <title>帐号管理-系统设置</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<input type="hidden" id="pgId">
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            
            <div class="ibox-content">
                <div class="row row-lg">
                    <label class="col-sm-12 control-label">服务配置</label>
                    <div class="hr-line-dashed"></div>
                    <div class="row">
                       <div class="col-sm-12 ">
	                    <div class="col-sm-2">
	                       <div class="panel panel-default">
							  <div class="panel-heading">
									<h3 class="panel-title">园区组栏</h3>
							  </div>
							  <div class="panel-body">
		                        <div id="tree"></div>
			                  </div>
				           </div>
	                    </div>
	                    <div class="col-sm-10">
	                    <div class="row">
	                    	<div class="col-sm-6">
				                <div class="tabs-container">
				                    <ul class="nav nav-tabs">
				                        <li id="rolefunc0" class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">园区组服务</a>
				                        </li>
				                        <li id="rolefunc1" class=""><a  data-toggle="tab" href="#tab-2" aria-expanded="false">绑定服务</a>
				                         </li>
				                    </ul>
				                    <div class="tab-content">
				                        <div id="tab-1" class="tab-pane active">
				                            <div class="panel-body">
				                                <div class="form-group">
				                            	<label class="col-sm-12 control-label">当前园区组：<span id="pgName"></span></label>
			                                	</div>
				                                <div class="form-group">
				                                <div  class="ibox-content no-padding">
				                            		<ul id="pgService" class="list-group" >
							                       </ul>
				                            	</div>
				                            	</div>
				                            </div>
				                        </div>
				                        <div id="tab-2" class="tab-pane">
				                            <div class="panel-body">
				                               <div  class="ibox-content no-padding">
			                                	<div class="col-sm-12 "  >
			                                	<div class="form-group">
			                                	     <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
					                                    <a class="btn btn-outline btn-default" 
					                                        onclick="addServiceTypePage()" >
					                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
					                                        <span>新建</span>
					                                    </a>
					                                </div>
							                       <table id="table1"
						                           	    data-mobile-responsive="true"
										               data-toggle="table"
										               data-url="<%=basePath%>/etopService/getServiceTypeList.do"
										               data-data-type="json"
										               data-side-pagination="server"
										               data-pagination="true"
										               data-query-params = "queryParams"
										               data-page-list="[5, 10, 20, 50, 100, 200]"
										               data-striped="true"
										              >
											            <thead>
											            <tr>
											                
											                <th data-align="center"data-formatter="formatterCheckbox"> <input type="checkbox" class="i-checks" /></th>
											                <th data-field="serviceCode" data-align="center" >编号</th>
											                <th data-field="serviceName" data-align="center">服务名称</th>
											                <th data-field="title" data-align="center">服务类型</th>
											                <th data-align="center" data-formatter='formatterFun2' >操作</th>
											            </tr>
											            </thead>
									       		   </table>
									       		   <a id="bindUser"  style="display: none;"  class="btn btn-primary"  onclick="bindService()">绑定</a>
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
	                </div>
                </div>
            </div>
        </div>
    
        <!-- End Panel Other -->
    </div>
    
	<jsp:include page="../shared/js.jsp"/>
    <script type="text/javascript">
    	var basePath='<%=basePath%>';
    	function queryParams(params){
			return params
		}
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/service.js"></script>
</body>
</html>