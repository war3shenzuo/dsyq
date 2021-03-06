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
    <title>园区列表-园区管理</title>
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
					 <div class="col-sm-12">
                        <div class="row">
                        	 <div class="col-md-2">
	                           	<label>园区组名</label>
                             	<input type="text" class="form-control m-b" placeholder="请输入园区组名" id="parkGroupName"> 
	                         </div>
                             <div class="col-md-2">
	                           	<label>状态</label>
	                            <select class="form-control m-b" id="astatus" name="account">
	                             <option value="">全部</option>
	                             <option value="1">已激活</option>
	                             <option value="0">未激活</option>
	                        	</select>
	                         </div>
	                         <div class="col-md-2">
	                        	<!--<label>确认搜索</label>-->
	                        	<button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/parkgroup/getParkGroupList.do')"  type="button" style="margin-top: 23px;;">搜索</button>
	                         </div>
	                         <div class="hr-line-dashed" style="clear: both;"></div>
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                            	
                                <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                    <a class="btn btn-outline btn-default" 
                                        onclick="openAddPage('新增园区组','80%','95%','<%=basePath %>/parkgroup/addPage.do','<%=basePath%>/parkgroup/ getParkGroupList.do')" >
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
                                        <span>新建</span>
                                    </a>
                                </div>
                                
	                           	<table id="table1"
	                           	   data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="<%=basePath%>/parkgroup/getParkGroupList.do"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-query-params = "queryParams"
					               data-striped="true"
					              >
						            <thead>
						            <tr>
						                <th data-field="parkGroupCode" data-align="center">园区组编号</th>
						                <th data-field="parkGroupName" data-align="center">园区组名</th>
						                <th data-field="approval" data-align="center" >管理帐号</th>
						                <th data-field="activated" data-align="center" data-formatter="formatterActivatedStatus">激活状态</th>
						                <th data-field="parkGroupType" data-align="center" data-formatter="formatterParkGroupType">园区类型</th>
						                <th data-field="createAt" data-align="center" >创建时间</th>
						                <th data-align="center" data-formatter='formatterFun' >操作</th>
						            </tr>
						            </thead>
				       		   </table>
                        </div>
                        <!-- End Example Events -->
                    </div>
                </div>
            </div>
        </div>
    
        <!-- End Panel Other -->
    </div>
	<jsp:include page="../shared/js.jsp"/>
    <script type="text/javascript">
    	/*查询条件*/
		function queryParams(params){
			params.activated = $("#astatus").val();
			params.parkGroupName=$("#parkGroupName").val();
			return params
		}
    </script>
   	<script type="text/javascript">
    	var basePath='<%=basePath%>';
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/parkgroup.js"></script>
</body>
</html>