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
	<title>二级域名配置</title>
	<style>
		#billListTable a:hover{text-decoration: underline;}
	</style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-8">
					<div class="example-wrap">
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
							<button type="button" class="btn btn-outline btn-default"
								onclick="openAddPage('手动创建订单','800px','400px','addview.do', 'list.do')">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i> <span>新建</span>
							</button>
							<button type="button" class="btn btn-outline btn-default" onclick="deleteBill()">
								<i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <span>删除</span>
							</button>
						</div>
						<table id="subdomainListTable"
                           	   data-mobile-responsive="true"
				               data-toggle="table"
				               data-url="list.do"
				               data-data-type="json"
				               data-side-pagination="server"
				               data-pagination="true"
				               data-page-list="[5, 10, 20, 50, 100]"
				              >
							<thead>
								<tr>
									<th data-field="state" data-checkbox="true"></th>
									<th data-field="hostName">域名</th>
									<th data-field="parkGroupId">园区组id</th>
									<th data-field="parkGroupName">园区组名字</th>
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
	var table = $('#subdomainListTable');
	
	$(document).ready(function() {
		
	});
   	
   	function deleteBill() {
   		var selections = table.bootstrapTable('getSelections');
   		var ids = "";
   		if(selections.length == 0)
   			return;
   		for(var i=0; i<selections.length; i++) {
   			ids = ids + selections[i].hostName + ",";
   		}
   		swal({
   			title:"确定删除"+selections.length+"个域名映射？",
   			type:"warning",
   			showCancelButton: true,
   	        confirmButtonColor: "#DD6B55",
   	        cancelButtonText: "取消",
   	        confirmButtonText: "确定",
   	        showLoaderOnConfirm: true,
   	        closeOnConfirm: false
   	    }, function () {
   	    	$.get("delete.do", {
   				"hostNames" : ids
   			}, function(data) {
   				if (data.status == 10001) {
   					swal({
   						title : "删除成功！",
   						type : "success",
   						confirmButtonText : "确定",
   						closeOnConfirm : true
   					}, function() {
   						tableRefresh('list.do');
   					});
   				} else {
   					swal({
   						title : "删除失败:" + data.msg,
   						type : "error",
   						confirmButtonText : "确定",
   						closeOnConfirm : true
   					}, function() {
   						tableRefresh('list.do');
   					});
   				}
   			});
   	    });
   	}
</script>
</body>
</html>