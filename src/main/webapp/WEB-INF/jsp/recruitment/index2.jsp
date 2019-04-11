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
    <title>采购服务</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<input type="hidden"  id="parkId">
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            
            <div class="ibox-content">
                <div class="row row-lg">
                    <label class="col-sm-12 control-label">服务列表</label>
                    <div class="hr-line-dashed"></div>
                    <div class="row">
                       <div class="col-sm-12 ">
	                    <div class="col-sm-2">
	                       <div class="panel panel-default">
							  <div class="panel-heading">
									<h3 class="panel-title">园区栏</h3>
							  </div>
							  <div class="panel-body">
		                        <div id="parktree"></div>
			                  </div>
				           </div>
	                    </div>
	                    <div class="col-sm-10">
	                    <div class="row">
	                    	<div class="col-sm-12">
				                <div class="tabs-container">
				                    <div class="row row-lg">
										 <div class="col-sm-12">
					                        <div class="row">
					                             <div class="col-md-2">
					                            <!--  处理状态：101，待审批；102，待回执；201，已撤销；202，已审批；203，已派工；204，已完工；300，完结 -->
						                           	<label>服务状态</label>
						                            <select class="form-control m-b" id="status" name="status">
						                             <option value="">全部</option>
						                             <option value="101">待审批</option>
						                             <option value="202">已审批</option>
						                             <option value="300">完结</option>
						                        	</select>
						                         </div>
						                         <div class="col-md-2">
						                         	<label>服务编号</label>
						                         	<input class="form-control m-b" value="" type="text" id="serviceNo" />
						                         </div>
						                         <div class="col-md-2">
						                        	<!--<label>确认搜索</label>-->
						                        	<button class="btn btn-primary" onclick="search()"  type="button" style="margin-top: 23px;;">搜索</button>
						                         </div>
						                         <div class="hr-line-dashed" style="clear: both;"></div>
					                        </div>
					                    </div>
					                    
				 <div class="ibox-content">
<%--                 <div class="row row-lg">
                    <div class="col-sm-4">
                        <!-- Example Events -->
                        <div class="example-wrap">
                                
	                           	<table id="table2"
                           	       data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="<%=basePath%>/etopService/calculateRecruitment.do"
					               data-data-type="json"
					               data-side-pagination="server"
										
					               data-query-params = "queryParams"
					               data-page-list="[5, 10, 20, 50, 100, 200]"
					               data-striped="true"
					              >
						            <thead>
						            <tr>
<!-- 						                <th data-field="subject" data-align="center">服务名称</th> -->
						                <th data-field="num" data-align="center">总数</th>
						                <th data-field="money" data-align="center" >总价</th>
						            </tr>
						            </thead>
				       		   </table>
                        </div>
                        <!-- End Example Events -->
                    </div>
                    
                    
                </div> --%>
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
										               data-url="<%=basePath%>/recruitment/getServiceList.do"
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
											                <th data-field="serviceTypeName" data-align="center"  data-formatter="formatterServiceType">服务类型</th>
											                <th data-field="serviceStatus" data-align="center" data-formatter="formatterServiceStatusType">服务状态</th>
											                <th data-field="companyName" data-align="center" >申请公司</th>
											                <th data-field="applyTime" data-align="center">申请时间</th>
<!-- 											                <th data-align="center" data-formatter='formatterPurchaseFun2' >操作</th> -->
											            </tr>
											            </thead>
									       		   </table>
					                        </div>
					                        <!-- End Example Events -->
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
    	var basePath2='<%=basePath%>'+'/recruitment';
    	var serviceTYPE=2;
    	/*查询条件*/
		function queryParams(params){
			params.serviceStatus = $("#status").val();
			params.serviceNo=$("#serviceNo").val();
			return params
		}
    	
    	function search(){
    		tableRefresh('<%=basePath%>/recruitment/getServiceList.do?parkId='+$("#parkId").val());
    	}
    	/*增加表格按钮**/
		function formatterServiceType(value,row,index){
			
			return "待诏服务";
		}
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/service.js"></script>
</body>
</html>