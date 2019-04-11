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
    <title>设施服务</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">


    <div class="wrapper wrapper-content animated fadeInRight">
        <!-- Panel Other -->
        <div class="ibox float-e-margins">
         <div class="ibox-title">
				<h5>
					<normal>设施预约列表</normal>
				</h5>
		</div>
            <div class="ibox-content">
                <div class="row row-lg">
					 <div class="col-sm-12">
                        <div class="row">
                            
	                         <div class="col-md-2">
	                         	<label>服务编号</label>
	                         	<input class="form-control m-b" value="" type="text" id="serviceNo" />
	                         </div>
	                         <div class="col-md-2">
	                         	<label>申请公司</label>
	                         	<input class="form-control m-b" value="" type="text" id="companyName" />
	                         </div>
	                          
	                         <div class="col-md-2">
	                         	<label>类别</label>
	                         	<input class="form-control m-b" value="" type="text" id="facilityType" />
	                         </div>
	                         <div class="col-md-2">
	                         	<label>项目名称</label>
	                         	<input class="form-control m-b" value="" type="text" id="facilityName" />
	                         </div>
	                          <div class="col-md-2">
	                           	<label>过期状态</label>
	                            <select class="form-control m-b" id="expirationTime" name="expirationTime">
	                            <option value="">全部</option>
	                             <option value="1">已过期</option>
								 <option value="0">未过期</option>
	                        	</select>
	                         </div>
	                         </div>
	                     <div class="row">  
	                      <div class="col-md-2">
                            <!--  处理状态：101，待审批；102，待回执；201，已撤销；202，已审批；203，已派工；204，已完工；300，完结 -->
	                           	<label>服务状态</label>
	                            <select class="form-control m-b" id="status" name="status">
	                             <option value="">全部</option>
								<option value="101">待审批</option>
								<option value="201">已撤销</option>
<!-- 								<option value="102">待回执</option>
								<option value="202">已审批</option>
								<option value="203">已派工</option>
								<option value="204">已完工</option> -->
								<option value="300">完结</option>
								<option value="301">拒绝</option>
	                        	</select>
	                         </div> 
	                         <div class="col-md-2  date">
								<label>申请日期</label>
	                              <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="applyTime" placeholder="">
	                               <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                         </div> 
	                         
	                         <div class="col-md-2  date">
								<label>预约开始时间</label>
	                              <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="beginStr" placeholder="">
	                               <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                         </div>  
	                         <div class="col-md-2  date">
								<label>预约结束时间</label>
	                              <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="endStr" placeholder="">
	                               <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                         </div>  
	                        
	                         <div class="col-md-2">
	                        	<!--<label>确认搜索</label>-->
	                        	<button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/serviceFacility/getServiceList.do');tableRefresh2('<%=basePath%>/serviceFacility/getServiceQuotation.do')"  type="button" style="margin-top: 23px;;">搜索</button>
	                         </div>
	                         <div class="hr-line-dashed" style="clear: both;"></div>
                        </div>
                    </div>
                    
                    <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-4">
                        <!-- Example Events -->
                        <div class="example-wrap">
                                
	                           	<table id="table2"
                           	       data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="<%=basePath%>/serviceFacility/getServiceQuotation.do"
					               data-data-type="json"
					               data-side-pagination="server"
	
					               data-query-params = "queryParams"
					               data-page-list="[5, 10, 20, 50, 100, 200]"
					               data-striped="true"
					              >
						            <thead>
						            <tr>
<!-- 						                <th data-field="facilityName" data-align="center">服务名称</th> -->
						                 <th data-field="num" data-align="center">总数</th>
						                <th data-field="money" data-align="center" >总价</th>
						            </tr>
						            </thead>
				       		   </table>
                        </div>
                        <!-- End Example Events -->
                    </div>
                    
                    
                </div>
            </div>
                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                            	
	                           	<table id="table1"
                           	       data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="<%=basePath%>/serviceFacility/getServiceList.do"
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
						                <th data-field="companyName" data-align="center" >申请公司</th>
<!-- 						                <th data-field="serviceTypeName" data-align="center"  data-formatter="formatterServiceType">服务类型</th> -->
						                <th data-field="facilityType" data-align="center">类别</th>
						                <th data-field="facilityName" data-align="center">项目名称</th>
						                <th data-field="applyTime" data-align="center">申请时间</th>
						                <th data-field="beginStr" data-align="center">预约开始时间</th>
						                <th data-field="endStr" data-align="center" >预约结束时间</th>
						                <th data-field="totalPrices" data-align="center" >金额</th>
						                <th data-field="serviceStatus" data-align="center" data-formatter="formatterServiceStatusType">服务状态</th>
						                <th data-field="expirationTime" data-align="center" data-formatter='formatterExpiration'>是否过期</th>
						                <th data-align="center" data-formatter='formatterFacilityFun' >操作</th>
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
    
   	$('.date').datepicker(
   			
   			{todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,autoclose:!0}
   			
   		)
        var basePath='<%=basePath%>';
   	    var basePath2='<%=basePath%>'+'/serviceFacility';
   	    var serviceTYPE=3;
    	/*查询条件*/
		function queryParams(params){
			params.serviceStatus = $("#status").val();
			params.serviceNo=$("#serviceNo").val();
			params.companyName=$("#companyName").val();
			params.expirationTime=$("#expirationTime").val();
			params.facilityType=$("#facilityType").val();
			params.facilityName=$("#facilityName").val();
			params.beginStr=$("#beginStr").val();
			params.endStr=$("#endStr").val();
			if($("#beginStr").val() != '')
			params.beginTime = Date.parse($("#beginStr").val() + " 00:00:00");
			if($("#endStr").val() != '')
			params.endTime = Date.parse($("#endStr").val()+ " 23:59:59");
			if($("#applyTime").val() != '')
				params.applyTime = $("#applyTime").val() + " 00:00:00";
			return params
		}
		/*增加表格按钮**/
		function formatterServiceType(value,row,index){
			
			return "设施服务";
		}
		
		

    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/service.js"></script>
</body>
</html>