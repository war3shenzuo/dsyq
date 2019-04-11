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
	<title>员工列表（暂时无用）</title>
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
                        <div class="col-sm-2">
                            <label>员工姓名</label>
                            <input type="text" class=" form-control" placeholder="" id="employeesName">
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

                                <th data-field="employeesName" data-align="center">员工姓名</th>
                                <th data-field="employeesSex" data-align="center"  data-formatter="contractCategoryFormatter">性别</th>
                                <th data-field="department" data-align="center" >部门</th>
                                <th data-field="jobs" data-align="center">岗位</th>
                                <th data-field="hiredate" data-align="center" >入职时间</th>
                                <th data-field="mobile" data-align="center" >电话号码</th>
								<th data-field="employeesId" data-formatter="detailButtonFormatter">操作</th>
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

	function queryParams(params){
	
		 params.employeesName = $("#employeesName").val();
		return params;
	}
   	
   	function detailButtonFormatter(value, row, index) {

       		url = "etopCompEmployees/EmployeesInfoById.do?id=" + value;
       		return '<a onclick="totabs(\'' + url + '\', \'员工详情\')">详情</a>';
   	
   	}
   	
   	
   	function contractCategoryFormatter(value) {
   		switch (value) {

   		case 1:
   			return "男";
   		case 2:
   			return "女";
   
   		default:
   			break;
   		}
   	}


</script>
</body>
</html>