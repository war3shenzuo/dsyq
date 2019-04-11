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
	<link rel="stylesheet" href="<%=basePath%>/css/select/select2.min.css">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            
            <div class="ibox-content">
                <div class="row row-lg">
                    <label class="col-sm-12 control-label">用户角色管理</label>
                    <div class="hr-line-dashed"></div>
                    <div class="row">
                       <div class="col-sm-12 ">
	                    <div class="col-sm-2">
	                       <div class="panel panel-default">
							  <div class="panel-heading">
									<h3 class="panel-title">角色栏</h3>
							  </div>
							  <div class="panel-body">
		                        <div id="tree"></div>
		                        <div class="col-sm-12" style="padding-top: 15px; display: none;">
				                    <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
				                        <a class="btn btn-outline btn-default" 
				                            onclick="openAddPage('新增父角色','800px','500px','<%=basePath %>/role/addPage.do','')" >
				                            <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
				                            <span>新增父角色</span>
				                        </a>
				                    </div>
			                    </div>
			                  </div>
				           </div>
	                    </div>
	                    <div class="col-sm-10">
	                    <div class="row">
	                    	<div class="col-sm-6">
				                <div class="tabs-container">
				                    <ul class="nav nav-tabs">
				                        <li id="rolefunc0" class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">角色详情</a>
				                        </li>
				                        <li id="rolefunc1" style="display: none;" class=""><a  data-toggle="tab" href="#tab-2" aria-expanded="false">绑定用户</a>
				                         </li>
				                        <li id="rolefunc2" style="display: none;" class=""><a data-toggle="tab" href="#tab-3" aria-expanded="false">功能设置	</a>
				                        </li>
				                        <li id="rolefunc3" style="display: none;" class=""><a data-toggle="tab" href="#tab-4" aria-expanded="false">读写设置</a>
				                        </li>
				                    </ul>
				                    <div class="tab-content">
				                        <div id="tab-1" class="tab-pane active">
				                            <div class="panel-body">
				                             	 <form method="get" class="form-horizontal">
						                        	<input type="hidden" id="id">

						                        	<div class="form-group">
						                                <label class="col-sm-2 control-label">角色编号</label>
						                                <div class="col-sm-10">
						                                    <input type="text" class="form-control" placeholder="" id="roleCode">
						                                </div>
						                            </div>
						                        	<div class="hr-line-dashed"></div>
						                          	<div class="form-group">
						                                <label class="col-sm-2 control-label">角色名称 </label>
						                                <div class="col-sm-10">
						                                    <input type="text" class="form-control" placeholder="" id="roleName">
						                                </div>
						                            </div>
						                            <div class="hr-line-dashed"></div>
						                            <div class="form-group">
						                                <label class="col-sm-2 control-label">角色描述</label>
						                                <div class="col-sm-10">
						                                    <input type="text" class="form-control" placeholder="" id="roleEscribe">
						                                </div>
						                            </div>
						                            <div class="hr-line-dashed"></div>
						                            <div class="form-group">

						                                <label class="col-sm-2 control-label">所属园区
						                                </label>
						                                <div class="col-sm-10">
						                                    <input type="text" class="form-control"   disabled="disabled" placeholder="" id="parkName">
						                                    <input type="hidden" class="form-control"  disabled="disabled"  placeholder="" id="parkId">
						                                </div>
						                            </div>
						                            <div style="display: none;">
						                             <div class="hr-line-dashed"></div>
						                            <div class="form-group">
						                                <label class="col-sm-2 control-label">父类Id
						                                </label>
						                                <div class="col-sm-10">
						                                    <input type="text" class="form-control" disabled="disabled" placeholder="" id="parentId">
						                                </div>
						                            </div>
						                            </div>
						                            <div class="hr-line-dashed"></div>
						                            <div class="form-group">
						                                <label class="col-sm-2 control-label">角色类型</label>
						                                <div class="col-sm-10">
						                                   <!--  <select class="form-control" name="account" id="roleType">
						                                        <option value="1">园区组</option>
						                                        <option value="2">园区</option>
						                                    </select> -->
						                                    <input type="hidden" class="form-control" disabled="disabled" placeholder="" id="roleType">
						                                    <input type="text" class="form-control" disabled="disabled" placeholder="" id="roleTypeName">
						                                </div>
						                            </div>
						                            <div class="hr-line-dashed" id="roleApprovaldivhr"></div>
						                            <div class="form-group" id="roleApprovaldiv">
						                                <label class="col-sm-2 control-label">权限设置</label>
						                                <div class="col-sm-10">
													　　  <select class="js-example-tags form-control"  multiple="multiple" id="roleApproval">
															 <option value="tz">园长审批权限</option>
															 <option value="cw"> 财务审批权限</option>
															 <option value="kh"> 客户提醒</option>
															 <option value="ht"> 合同提醒</option>
															 <option value="qs"> 申请提醒</option>
															 <option value="yqtx"> 园区提醒</option>
															 <option value="rw"> 任务提醒</option>
															 <option value="jmsq"> 减免申请</option>
															 <option value="yqsq"> 延期申请</option>
															 <option value="zf"> 支付</option>
															 <option value="hr"> 员工信息录入</option>
														</select>					                                    
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
						                            	<div id="copyRole2" style="display: none;padding-right: 0;" class="col-sm-3">
					                                		<select class="form-control" name="account" id="toParkId">
						                                        <c:forEach items="${parkLists}" var="item">
								                                	<option value="${item.id}">${item.parkName}</option>
								                                </c:forEach>
						                                    </select>
							                            </div>

						                            	<div id="copyRole" style="display: none;" class="col-sm-2">
					                                    	<a class="btn btn-primary"  onclick="copyRole()">复制角色</a>
						                                </div>

						                                <div id="addChildren" style="display: none; " class="col-sm-2">
						                                    <a class="btn btn-primary"  onclick="addChildren()">新增角色</a>
						                                </div>

						                                <div id="updateChildren" style="display: none; float: right" class="col-sm-2">
						                                    <a class="btn btn-primary"  onclick="updatesubmit()">更&nbsp;&nbsp;新</a>
						                                </div>
						                            </div>
						                        </form>
				                            </div>
				                        </div>
				                        <div id="tab-2" class="tab-pane">
				                            <div class="panel-body">
				                            	<form method="get" class="form-horizontal"  >
				                            	<div class="form-group" style=" margin: 0">
				                            	<div class="col-sm-12 "  >
				                                    <a id="bindUser" style="display: none;"  class="btn btn-primary"  onclick="openBindUser()">绑定</a>
			                                	</div>
			                                	</div>
			                                	<div class="col-sm-12 "  >
			                                	<div class="form-group">
			                                	<div  class="ibox-content no-padding">
							                       <ul id="bindUserList" class="list-group" >
							                       </ul>
						                    	</div>
						                    	</div>
						                    	</div>
			                                	</form>
				                            </div>
				                        </div>
				                        <div id="tab-3" class="tab-pane">
				                            <div class="panel-body">
				                                <div class="form-group">
				                            	<div class="col-sm-12 "  >
				                                    <a id="bindFunc" style="display: none;"  class="btn btn-primary"  onclick="bindFunc()">设置</a>
			                                	</div>
			                                	</div>
				                                <div class="form-group">
				                                <div  class="ibox-content no-padding">
				                            	<div id="functree"></div>
				                            	<input type="hidden" value="0" id="funcInit">
				                            	</div>
				                            	</div>
				                            </div>
				                        </div>
				                        <div id="tab-4" class="tab-pane">
				                            <div class="panel-body">
				                                <div class="form-group">
				                            	<div class="col-sm-12 "  >
				                                    <a id="setRead" style="display: none;"  class="btn btn-primary"  onclick="setRead()">设置</a>
			                                	</div>
			                                	</div>
				                                <div class="form-group">
				                                <div  class="ibox-content no-padding">
				                            	<div id="readtree"></div>
				                            	<input type="hidden" value="0" id="readInit">
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
	<script src="<%=basePath%>/js/select/select2.full.min.js"></script>
    <script type="text/javascript">
    	var basePath='<%=basePath%>';
		
    	$("#roleApproval").select2({
    		tags: true,
    		separator: ",",
    	});
    	
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/role.js"></script>
</body>
</html>