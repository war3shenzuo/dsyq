<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>采购服务</title>
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
					<normal>采购服务列表</normal>
				</h5>
			</div>
            <div class="ibox-content">
                <div class="row row-lg">
					 <div class="col-sm-12">
                        <div class="row">
                             <div class="col-md-2">
                            <!--  处理状态：101，待审批；102，待回执；201，已撤销；202，已审批；203，已派工；204，已完工；300，完结 ；301，拒绝-->
	                           	<label>服务状态</label>
	                            <select class="form-control m-b" id="status" name="status">
	                             <option value="">全部</option>
	                             <option value="101">待审批</option>
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
	                         	<label>大类</label>
	                         	<input class="form-control m-b" value="" type="text" id="category" />
	                         </div>
	                         <div class="col-md-2">
	                         	<label>商品名称</label>
	                         	<input class="form-control m-b" value="" type="text" id="goodName" />
	                         </div>
	                         <div class="col-md-2">
	                         	<label>申请公司</label>
	                         	<input class="form-control m-b" value="" type="text" id="companyName" />
	                         </div>
	                    </div>
	                    	 <div class="row">
 	                         <div class="col-md-2  date">
								<label>申请起始</label>
	                            <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="applyTime" placeholder="">
	                            <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                            <div style="position: absolute; top: 30px; right: 0px;">-</div>
	                         </div>   
 	                         <div class="col-md-2  date">
								<label>申请日期</label>
	                            <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="applyTime2" placeholder="">
	                            <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                         </div>   
 	                         <div class="col-md-2  date">
								<label>完结起始日期</label>
	                            <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="completeTime2" placeholder="">
	                            <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                             <div style="position: absolute; top: 30px; right: 0px;">-</div>
	                         </div>   
 	                         <div class="col-md-2  date">
								<label>完结日期</label>
	                            <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="completeTime" placeholder="">
	                            <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                         </div>   
	                         <div class="col-md-2">
	                        	<!--<label>确认搜索</label>-->
<%-- 	                        	<button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/purchase/getServiceList.do')"  type="button" style="margin-top: 23px;;">搜索</button> --%>
	                        	<button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/purchase/getServiceList.do');tableRefresh2('<%=basePath%>/purchase/calculateGoods.do')"  type="button" style="margin-top: 23px;;">搜索</button>
	                         </div>
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
					               data-url="<%=basePath%>/purchase/calculateGoods.do"
					               data-data-type="json"
					               data-side-pagination="server"
					               					        
					               data-query-params = "queryParams"
					               data-page-list="[5, 10, 20, 50, 100, 200]"
					               data-striped="true"
					              >
						            <thead>
						            <tr>
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
					               data-url="<%=basePath%>/purchase/getServiceList.do"
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
						                <th data-field="category" data-align="center">大类</th>
						                <th data-field="goodName" data-align="center">商品名称</th>
						                <th data-field="number" data-align="center">数量</th>
						                <th data-field="totalPrice" data-align="center">金额</th>
						                <th data-field="applyTime" data-align="center">申请时间</th>
						                <th data-field="completeTime" data-align="center">完结时间</th>
						                <th data-field="serviceStatus" data-align="center" data-formatter="formatterServiceStatusType">服务状态</th>
						                <th data-align="center" data-formatter='formatterPurchaseFun' >操作</th>
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
   	    var basePath2='<%=basePath%>'+'/purchase';
   	    var serviceTYPE=1;
    	/*查询条件*/
		function queryParams(params){
			params.serviceStatus = $("#status").val();
			params.serviceNo=$("#serviceNo").val();
			params.category=$("#category").val();
			params.goodName=$("#goodName").val();
			params.number=$("#number").val();
			params.totalPrice=$("#totalPrice").val();
			params.companyName=$("#companyName").val();
// 			params.applyTime=$("#applyTime").val();
// 			alert(params.applyTime);
// 			params.completeTime=$("#completeTime").val();
			if($("#applyTime").val() != '')
				params.applyTime = $("#applyTime").val() + " 00:00:00";
			if($("#applyTime2").val() != '')
				params.applyTime2 = $("#applyTime2").val() + " 23:59:59";
// 			alert(params.applyTime);
			if($("#completeTime").val() != '')
				params.completeTime = $("#completeTime" ).val()+ " 00:00:00";
			if($("#completeTime2").val() != '')
				params.completeTime2 = $("#completeTime2" ).val()+ " 23:59:59";
 			return params
		}
		/*增加表格按钮**/
		function formatterServiceType(value,row,index){
			
			return "采购服务";
		}

    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/service.js"></script>
</body>
</html>