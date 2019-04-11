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
    <title>人员代招服务</title>
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
					<normal>人员代招列表</normal>
				</h5>
		</div>
            <div class="ibox-content">
                <div class="row row-lg">
					<div class="col-sm-12">
					<div class="row">
  
                        <div class="col-sm-2">
  						   <label>服务编号</label>
                            <input type="text" class=" form-control" placeholder="请输入服务编号" id="serviceNo">
                        </div>                        
                        <div class="col-sm-2">
  						   <label>申请公司</label>
                            <input type="text" class=" form-control" placeholder="请输入客户名称" id="companyName">
                        </div>                        
                         <div class="col-sm-2">
                            <label>类别</label>
                            <input type="text" class=" form-control" placeholder="请输入服务类型" id="category"> 
                        </div>
                         <div class="col-sm-2">
                            <label>项目名称</label>
                            <input type="text" class=" form-control" placeholder="请输入服务类型" id="subject"> 
                        </div>
                        </div>
                        
                        <div class="row">
                        <div class="col-sm-2">
                            <label>服务状态</label>
                            <select class="form-control m-b" name="serviceStatus" id="serviceStatus">
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
						<div class="col-md-2  date">
								<label>申请日期</label>
	                            <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="applyTime" placeholder="请输入申请日期">
	                            <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                    </div>  
						<div class="col-md-2  date">
								<label>完结日期</label>
	                            <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="completeTime" placeholder="请输入完结日期">
	                            <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                    </div>  
						<div class="col-md-2">
                        	<button class="btn btn-primary" onclick="tableRefresh('getServiceList.do');tableRefresh2('<%=basePath%>/etopService/calculateRecruitment.do')" type="button" style="margin-top: 23px;;">搜索</button>
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
                    
                    
                </div>
            </div>
                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                                
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
						                <th data-field="companyName" data-align="center" >申请公司</th>
						                <th data-field="category" data-align="center">类别</th>    
		                                <th data-field="subject" data-align="center">项目名称</th>
		                                <th data-field="number" data-align="center" >数量</th>
		                                <th data-field="totalPrice" data-align="center">金额</th>
						                <th data-field="applyTime" data-align="center">申请时间</th>
                                		<th data-field="completeTime" data-align="center">完成时间</th>  
						                <th data-field="serviceStatus" data-align="center" data-formatter="formatterServiceStatusType">服务状态</th>
						                <th data-align="center" data-formatter='formatterRecruitmentFun' >操作</th>
						             
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
   	    var basePath2='<%=basePath%>'+'/recruitment';
   	    var serviceTYPE=2;
    	/*查询条件*/
		function queryParams(params){
			params.serviceStatus = $("#serviceStatus").val();
			params.serviceNo=$("#serviceNo").val();
			 params.category = $("#category").val();
			 params.companyName = $("#companyName").val();
			 params.subject = $("#subject").val();
// 			 params.applyTime = $("#applyTime").val();
// 			 params.completeTime = $("#completeTime").val();
			if($("#applyTime").val() != '')
				params.applyTime = $("#applyTime").val() + " 00:00:00";
			if($("#completeTime").val() != '')
				params.completeTime = $("#completeTime" ).val()+ " 23:59:59";
			return params
		}
		/*增加表格按钮**/
		function formatterServiceType(value,row,index){
			
			return "代招服务";
		}

    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/service.js"></script>
</body>
</html>