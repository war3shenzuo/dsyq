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
    <title>设施列表</title>
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
	                           	<label>类别</label>
                             	<input type="text" class=" form-control m-b" placeholder="请输入类别" id="facilityType"> 
	                         </div>
	                         
                             <div class="col-md-2">
	                           	<label>设施名称</label>
                             	<input type="text" class=" form-control m-b" placeholder="请输入设施名称" id="facilityName"> 
	                         </div>
<!-- 	                         <div class="col-md-2">
	                           	<label>设施类型</label>
                             	<select class="col-md-2 form-control m-b" name="facilityType" id="facilityType">
                             	        <option value="">全部</option>
                                        <option value="0">会议室</option>
                                        <option value="1">设备</option>
                                </select>
	                         </div> -->
                             
	                         <div class="col-md-2">
	                        	<!--<label>确认搜索</label>-->
	                        	<button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/facility/getEtopFacilityList.do')"  type="button" style="margin-top: 23px;;">搜索</button>
	                         </div>
	                         <div class="hr-line-dashed" style="clear: both;"></div>
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                            	
                                <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                    <a class="btn btn-outline btn-default" 
                                        onclick="openAddPage('新增设施','50%','70%','<%=basePath %>/facility/addPage.do','<%=basePath%>/facility/getEtopFacilityList.do')" >
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
                                        <span>新建</span>
                                    </a>
                                </div>
                                
	                           	<table id="table1"
	                           	    data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="<%=basePath%>/facility/getEtopFacilityList.do"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-pagination="true"
					               data-query-params = "queryParams"
					               data-page-list="[5, 10, 20, 50, 100, 200]"
					               data-striped="true"
					              >
						            <thead>
						            <tr>
						                <th data-field="id" data-align="center" data-visible="false">编号</th>
						                <th data-field="facilityType" data-align="center" >类别</th>
						                <th data-field="facilityName" data-align="center">设施名称</th>
						                <th data-field="startTime" data-align="center" >开发起始时间</th>
						                <th data-field="endTime" data-align="center" >开发结束时间</th>
						                <th data-field="facilityPrice" data-align="center" >设施价格</th>
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
			params.facilityName=$("#facilityName").val();
			params.facilityType=$("#facilityType").val();
			return params
		}
    	function formatterFType(value){
    		if(value==1){
    			return "设备";
    		}else{
    			return "会议室";
    		}
    	}
    </script>
    <script type="text/javascript">
    	var basePath='<%=basePath%>';
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/facility.js"></script>
</body>
</html>