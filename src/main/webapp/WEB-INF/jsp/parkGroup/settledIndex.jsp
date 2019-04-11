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
<title>入住申请列表-园区组管理</title>
<meta name="keywords" content="">
<meta name="description" content="">
<jsp:include page="../shared/css.jsp" />
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<input type="hidden" id="sid"/>
		<!-- Panel Other -->
		<div class="ibox float-e-margins">

			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-md-2">
								<label>园区名称</label> <input type="text" class="form-control m-b"
									placeholder="请输入园区名称" id="parkName">
							</div>

							<div class="col-md-2">
								<!--<label>确认搜索</label>-->
								<button class="btn btn-primary "
									onclick="tableRefresh('<%=basePath%>/parkgroup/getSettledList.do')"
									type="button" style="margin-top: 23px;">搜索</button>
							</div>
							<div class="hr-line-dashed" style="clear: both;"></div>
						</div>
					</div>
					<div class="col-sm-12">
						<!-- Example Events -->
						<div class="example-wrap">
							<table id="table1" data-height="400"
								data-mobile-responsive="true" data-toggle="table"
								data-url="<%=basePath%>/parkgroup/getSettledList.do"
								data-data-type="json" data-side-pagination="server"
								data-pagination="true" data-query-params="queryParams"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-striped="true">
								<thead>
									<tr>
										<th data-field="id" data-align="center" data-visible="false">编号</th>
										<th data-field="parkName" data-align="center">园区名称</th>
										<th data-field="address" data-align="center">园区地址</th>
										<th data-field="contacts" data-align="center">联系人</th>
										<th data-field="mobile" data-align="center">联系电话</th>
										<th data-field="applyTime" data-align="center">申请时间</th>
										<th data-align="center" data-formatter='formatterFun'>操作</th>
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
	
	
	
	<div class="modal inmodal" id="notice" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated bounceInRight">
				<div class="modal-body">
					<input type="hidden" class="form-control" placeholder=""
						id="payQuotaId" /> <input type="hidden" class="form-control"
						placeholder="" id="payUserId" />
					<form id="form" method="get" class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label"> 编号 </label>
							<div class="col-sm-3">
								<input type="text" class="form-control" placeholder=""
									id="code" />
							</div>
							
							<label class="col-sm-2 control-label"> 账号 </label>
							<div class="col-sm-3">
								<input type="text" class="form-control" placeholder=""
									id="approval" />
							</div>
							
							<div class="col-sm-2">
								<button class="btn btn-primary" onclick="adopt()" type="button"
									style="">提交</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	<jsp:include page="../shared/js.jsp" />
	<script type="text/javascript">
    	/*查询条件*/
		function queryParams(params){
			//params.activated = $("#astatus").val();
			params.parkName=$("#parkName").val();
			return params
		}

    </script>
	<script type="text/javascript">
    	var basePath='<%=basePath%>'; 
	</script>
	<script type="text/javascript" src="<%=basePath%>/myjs/settled.js"></script>
</body>
</html>