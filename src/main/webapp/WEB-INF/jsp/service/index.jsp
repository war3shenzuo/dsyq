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
    <title>服务管理</title>
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
	                           	<label>服务类型</label>
	                           	<select class="form-control m-b" id="type" name="type">
<%-- 							  <option value="">全部</option>
	                             <c:forEach items="${types}" var="type">
	                                 <option value="${type.serviceCode }">${type.serviceName }</option>
	                             </c:forEach>
                        	</select>
                        	<select class="form-control m-b" name="serviceType" id="serviceType"> --%>                           
								<option value="">全部</option>
								<option value="WYBX">物业保修</option>
								<option value="SWFW">商务服务</option>
								<option value="RYDZ">人员代招</option>
								<option value="GGCG">公共采购</option>
								<option value="YYFW">设施预约</option>
							</select>
	                        	</select>
	                         </div>
                             <div class="col-md-2">
                            <!--  处理状态：101，待审批；102，待回执；201，已撤销；202，已审批；203，已派工；204，已完工；300，完结 -->
	                           	<label>服务状态</label>
	                            <select class="form-control m-b" id="status" name="status">
	                             <option value="">全部</option>
	                             <option value="101">待审批</option>
	                             <option value="102">待回执</option>
	                             <option value="201">已撤销</option>
	                             <option value="202">已审批</option>
	                             <option value="203">已派工</option>
	                             <option value="204">已完工</option>
	                             <option value="300">完结</option>
	                             <option value="301">拒绝</option>
	                        	</select>
	                         </div>
	                         <div class="col-md-2">
	                         	<label>服务编号</label>
	                         	<input class="form-control m-b" value="" type="text" id="serviceNo" />
	                         </div>
	                         <div class="col-md-2">
	                        	<!--<label>确认搜索</label>-->
	                        	<button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/etopService/getServiceBycompanyId.do')"  type="button" style="margin-top: 23px;;">搜索</button>
	                         </div>
	                         <div class="hr-line-dashed" style="clear: both;"></div>
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                            	
                                <!-- <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                    <a class="btn btn-outline btn-default" 
                                        onclick="addParkPage()" >
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
                                        <span>新建</span>
                                    </a>
                                </div> -->
                                
	                           	<table id="table1"
                           	       data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="<%=basePath%>/etopService/getServiceBycompanyId.do"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-pagination="true"
					               data-query-params = "queryParams"
					               data-page-list="[5, 10, 20, 50, 100, 200]"
					               data-striped="true"
					              >
						            <thead>
						            <tr>
						                <th data-field="serviceId" data-align="center" data-visible="false">Id</th>
						                <th data-field="serviceNo" data-align="center">服务编号</th>
<!-- 									<th data-field="serviceTypeName" data-align="center">服务类型</th> -->
										<th data-field="serviceType" data-align="center" data-formatter="serviceTypeFormatter">服务类型</th>
						                <th data-field="serviceStatus" data-align="center" data-formatter="formatterServiceStatusType">服务状态</th>
						                <th data-field="companyName" data-align="center" >申请公司</th>
						                <th data-field="applyTime" data-align="center">申请时间</th>
						                <th data-align="center" data-formatter='formatterFunService' >操作</th>
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
   	    var basePath='<%=basePath%>';
   	    var serviceTYPE=0;
    	/*查询条件*/
		function queryParams(params){
			params.serviceStatus = $("#status").val();
			params.serviceType = $("#type").val();
			params.serviceNo=$("#serviceNo").val();
			return params
		}
       	function serviceTypeFormatter(value) {

       		switch (value) {
       		

       		case 'WYBX':
    			return "物业保修";
       		case 'RYDZ':
    			return "人员代招";
       		case 'SWFW':
    			return "商务服务";
       		case 'GGCG':
    			return "公共采购";
       		case 'YYFW':
       			return "预约服务";
       		}
       	}
        function formatterFunService(value,row,index){

        			var infoUrl = '<%=basePath%>/companyService/getServiceType.do?id='+row.serviceId;
        			var refreshUrl = '<%=basePath%>/etopService/getServiceBycompanyId.do';
        			var showInfo = "openAddPage('服务详情','70%','90%','"+infoUrl+"','"+refreshUrl+"')";
        			
        			return '<button class="btn btn-info" onclick="'+showInfo+'"type="button" >详情</button> ';
        	   		
        		
        		}
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/service.js"></script>
</body>
</html>