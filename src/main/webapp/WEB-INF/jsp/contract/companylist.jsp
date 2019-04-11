<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<jsp:include page="../shared/css.jsp" />
	<title>合同列表</title>
	<style>
		#billListTable a:hover{text-decoration: underline;}
	</style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
					<div class="row">
						<div class="col-md-3">
                           	<label>合同编号</label>
                            <input type="text" id="contractNo" placeholder="请输入合同编号" class="form-control">
                        </div>
						<div class="col-md-2">
							<label>合同类型</label>
							<select class="form-control m-b" name="account" id="contractCategory">
								<option value="">全部</option>
								<option value="1">租赁合同</option>
								<option value="2">外包合同</option>
								<option value="3">能源合同</option>
								<option value="4">物业合同</option>
								<option value="5">服务合同</option>
							</select>
						</div>

						<div class="col-md-2  date">
								<label>开始日期</label>
	                              <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="contractStartDate" placeholder="请选择开始日期">
	                               <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                       </div>  
	 
	                    <div class="col-md-2  date">
	                          	<label>结束日期</label>
	                              
	                              <input  style=" width:80%; float: left;  display:block" type="text" class="form-control" value="" id="contractEndDate" placeholder="请选择结束日期">
								  <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
						 </div> 	

						<div class="col-md-2">
                        	<button class="btn btn-primary" onclick="tableRefresh('listbyCompanyId.do')" type="button" style="margin-top: 23px;;">搜索</button>
                        </div>
						<div class="hr-line-dashed" style="clear: both;"></div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="example-wrap">

						<table id="contractListTable"
                           	   data-mobile-responsive="true"
				               data-toggle="table"
				               data-url="listbyCompanyId.do"
				               data-data-type="json"
				               data-side-pagination="server"
				               data-pagination="true"
				               data-query-params="queryParams"
				               data-page-list="[5, 10, 20, 50, 100]"
				              >
							<thead>
								<tr>
	
									<th data-field="contractCategory" data-formatter="contractCategoryFormatter">合同类型</th>
									<th data-field="contractNo">合同编号</th>
									<th data-field="companyName">公司名称</th>
									<th data-field="contractStartDate">开始时间</th>
									<th data-field="contractEndDate">结束时间</th>
									<th data-field="createdAt">创建时间</th>
									<th data-field="lastBalanceDate">最后结算日</th>
									<th data-field="contractStatusStr" >合同状态</th>
									<th data-field="id" data-formatter="detailButtonFormatter">操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../shared/js.jsp" />

<script type="text/javascript">
var table = $('#contractListTable');
var basePath='<%=basePath%>';

//日期选择器
$('.date').datepicker(
		
		{todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,autoclose:!0}
		
	)




	function queryParams(params){
	
		params.contractNo = $("#contractNo").val();
		params.contractCategory = $("#contractCategory").val();
		if($("#contractStartDate").val()!= ''){
		params.contractStartDate = $("#contractStartDate").val();
		}
		if( $("#contractEndDate").val()!= ''){
		params.contractEndDate = $("#contractEndDate").val();
		}
		return params;
	}
   	
   	function detailButtonFormatter2(value, row, index) {

   				url = "contract/userlistInfo.do?contractNo=" + value;
				return '<a onclick="totabs(\'' + url + '\', \'合同详情\')">详情</a>';

   	}
   	
    function detailButtonFormatter(data, type,row) {
		
		var s='<a class="btn btn-sm btn-info" href="javascript:void(0)" onClick=javascript:editContract("'
			+ data
			+ '","'+type.contractCategory+'")>查看</a>';
		return s;
	}
    
   	function editContract(id,category)
   	{
   		var url=basePath+'/contract/edit.do?id='+id+'&category='+category+'&ro='+true;
   		
   		//window.location.href=url;
   		
   		window.parent.addTable(url,renderContractCategory(category)+'编辑');
   	}
   	function renderContractCategory(c)
   	{
   		c=c+"";
   		
   		var result='';
   		
   		switch(c)
   		{
   			case "1":
   				result="租赁合同";
   				break;
   			case "2":
   				result="外包合同";
   				break;
   			case "3":
   				result="能源合同";
   				break;
   			case "4":
   				result="物业合同";
   				break;
   			case "5":
   				result="服务合同";
   				break;
   			
   			default:
   				result='<label class="label label-default">未知</label>';
   		}
   			
   		return result;

   	}
   	function contractCategoryFormatter(value) {
   		switch (value) {
   		
   		case 1:
   			return "租赁合同";
   		case 2:
   			return "外包合同";
   		case 3:
   			return "能源合同";
   		case 4:
   			return "物业合同";
   		case 5:
   			return "服务合同";
   		default:
   			break;
   		}
   	}

</script>
</body>
</html>