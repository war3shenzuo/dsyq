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
    <title>入住申请详情-园区组管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
 
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">

					<div class="ibox-content">

						<form method="get" class="form-horizontal">

							<div class="form-group">
								<label class="col-sm-2 control-label">园区名称</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder=""
										id="parkName" value="${settledInfo.parkName}">
								</div>
								<label class="col-sm-2 control-label">园区地址</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder=""
										id="address" value="${settledInfo.address}">
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-sm-2 control-label">园区类型</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder=""
										id="parkType" value="${settledInfo.parkType}">
								</div>
								<label class="col-sm-2 control-label">开园时间</label>
								<div class="col-sm-4">
									<input name="openTime" type="text" class="form-control"
										id="openTime" value='${SS.openTime}' />
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">所属单位</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder=""
										id="belongUnit" value="${settledInfo.belongUnit}">
								</div>
								<label class="col-sm-2 control-label">运营单位</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder=""
										id="operateUnit" value="${settledInfo.operateUnit}">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">园区面积</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder=""
										id="parkSize" value="${settledInfo.parkSize}">
								</div>
								<label class="col-sm-2 control-label">园区出租率</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder=""
										id="rentalRate" value="${settledInfo.rentalRate}">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">联系人</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder=""
										id="contacts" value="${settledInfo.contacts}">
								</div>
								<label class="col-sm-2 control-label">电话</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder=""
										id="mobile" value="${settledInfo.mobile}">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">QQ</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder="" id="qq"
										value="${settledInfo.qq}">
								</div>
								<label class="col-sm-2 control-label">微信</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder=""
										id="wechat" value="${settledInfo.wechat}">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">邮箱</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder=""
										id="email" value="${settledInfo.email}">
								</div>
								<label class="col-sm-2 control-label">城市</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" placeholder=""
										id="city" value="${settledInfo.city}">
								</div>
							</div>
							
							

						</form>
					</div>
				</div>
            </div>
        </div>
    </div>
    <jsp:include page="../shared/js.jsp"/>
    <script type="text/javascript">
    	var basePath='<%=basePath%>';
    </script>
    <script type="text/javascript" src="<%=basePath%>/myjs/common.js"></script>
</body>
</html>