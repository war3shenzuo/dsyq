<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同列表详情页</title>

<jsp:include page="../shared/css.jsp"/>

<jsp:include page="../shared/js.jsp"/>


<script type="text/javascript">
	var basePath = "<%=basePath%>";	
</script>
</head>
<body class="gray-bg">


				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>合同基本信息</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="dropdown-toggle" data-toggle="dropdown"
								href="form_advanced.html#"> <i class="fa fa-wrench"></i>
							</a>
								<a class="close-link hidden"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">

						<div class="form-horizontal">

							<div class="form-group">
                                <label class="col-sm-4 control-label">合同编号</label>

                                <div class="col-sm-7">
                                    <input style="width:400px" type="text" readonly class="form-control" value="${contractNo}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">纸质编号</label>

                                <div class="col-sm-7">
                                    <input style="width:400px" type="text" readonly class="form-control" value="${paperNo}">
                                </div>
                            </div>
							<div class="form-group">
                                <label class="col-sm-4 control-label">公司名称</label>

                                <div class="col-sm-7">
                                    <input style="width:400px" type="text" readonly class="form-control"  value="${companyName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">合同签订日期</label>

                                <div class="col-sm-7">
                                    <input style="width:400px" type="text" readonly class="form-control" value="${contractSignDate}">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-4 control-label">公司入驻日期</label>

                                <div class="col-sm-7">
                                    <input style="width:400px" type="text" readonly class="form-control"  value="${companyRegistrationDate}">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-4 control-label">合同开始日期</label>

                                <div class="col-sm-7">
                                    <input style="width:400px" type="text" readonly class="form-control" value="${contractStartDate}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">合同结束日期</label>

                                <div class="col-sm-7">
                                    <input style="width:400px" type="text" readonly class="form-control" value="${contractEndDate}">
                                </div>
                            </div>
                            

                            <div class="form-group">
                                <label class="col-sm-4 control-label">合同状态</label>

                                <div class="col-sm-7">
                                    <input style="width:400px" type="text" readonly class="form-control" value="${contractStatusStr}" >
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-4 control-label">合同终止原因</label>

                                <div class="col-sm-7">
                                    <textarea  style="width:400px" rows="5" readonly class="form-control" >${terminateReason}
                                    </textarea>
                                </div>
                            </div>
                            



							
						</div>






					</div>
				</div>



</body>

</html>