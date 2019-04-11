<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同列表</title>

<jsp:include page="../shared/css.jsp"/>

<jsp:include page="../shared/js.jsp"/>
<script src="<%=basePath%>/js/jquery.noty.packaged.min.js"></script>
<script src="<%=basePath%>/myjs/my.function.js"></script>
<script src="<%=basePath%>/myjs/contract.list.js"></script>

<script type="text/javascript">
	var basePath = "<%=basePath%>";	
</script>
</head>
<body class="gray-bg">

<input type="hidden" id="read-only" value="${readonly}" />
<input type="hidden" id="finance-audit" value="${financeAudit}" />
<input type="hidden" id="parker-audit" value="${parkerAudit}" />

	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-md-12 col-sm-12">

				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>合同列表</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							
							
							<a class="close-link hidden"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">

						<form class="form-inline">

							<div class="form-group">
                                 
                                 <select class="form-control" id="parkId" name="parkId">
                                 	<option value="">选择园区</option>
                                     <c:forEach items="${parks}" var="park">
                                         <option value="${park.id}">${park.parkName}</option>
                                     </c:forEach>
                                 </select>
                            
							</div>
							
							<div class="form-group">
                                 
                                 <select class="form-control" id="building-sele" name="building-sele">
                                 	<option value="">选择楼</option>
                                 </select>
                            
							</div>
							
							<div class="form-group">
                                 
                                 <select class="form-control" id="floor-sele" name="floor-sele">
                                 	<option value="">选择层</option>
                                 </select>
                            
							</div>
							
							<div class="form-group">
                                 
                                 <select class="form-control" id="block-sele" name="block-sele">
                                 	<option value="">选择区</option>
                                 </select>
                            
							</div>
							<div class="form-group">
								<input type="text" placeholder="房间号" id="room-num" class="form-control">
							</div>
							<div class="form-group">
								<select class="form-control" name="contract-category" id="search-contract-category">
									<option value="">选择合同类型</option>
									

								</select>
								
								
							</div>
							<div class="form-group">
							<input type="hidden" id="contract-status-selected"/>
								<select class="form-control" name="search-contract-status" id="search-contract-status">
									<option value="">选择合同状态</option>
								</select>
								
								
							</div>
							<div class="form-group">
								<input type="text" placeholder="合同编号或公司名称" id="search-value" class="form-control">
							</div>
							
							<div class="form-group">
								
								<div class="input-group date">
	                              <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                              <input type="text" class="form-control" value="" id="search-start-date" placeholder="开始日期">
	                          	</div>
	                          	<div class="input-group date">
	                              <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                              <input type="text" class="form-control" value="" id="search-end-date" placeholder="结束日期">
	                          	</div>
								
								
							</div>
							
							
							<div class="form-group">
							
							
							<input type="checkbox" id="check-other-contract">检测其他合同
							<span style="display:none;">
							<label class="label label-danger">均无</label>
							<label class="label label-warning">无物业</label>
							<label class="label label-success">无能源</label>
							</span>
							</div>
							
							<div class="form-group">
								<input type="button" class="btn btn-primary" id="filter-contract" value="  查     找   " />
								
								<input type="button" class="btn btn-primary hidden" id="test-contract" value="  test  " />
								
<!-- 							</div> -->


 								<c:if test="${financeAudit eq 1}">
									<input type="button" class="btn btn-danger" id="generate-all-contract-bill" title="自动出帐失败后，可以使用" value="生成所有合同帐单   " />
								</c:if>
	<!-- 							<div class="form-group"> -->
								<c:if test="${readonly eq false}">
									<div class="input-group">
									<div class="input-group-btn">
	                                      <button data-toggle="dropdown" class="btn btn-info dropdown-toggle" type="button">新建合同 <span class="caret"></span>
	                                      </button>
	                                      <ul class="dropdown-menu">
	                                          <li><a href="javascript:void(0)" onclick="createLease()">租赁合同</a>
	                                          </li>
	                                          <li class="divider"></li>
	                                          <li><a href="javascript:void(0)" class="contract-create-func" style="display:none;" onclick="createContract(4)">物业合同</a>
	                                          </li>
	                                          <li><a href="javascript:void(0)" class="contract-create-func" style="display:none;" onclick="createContract(3)">能源合同</a>
	                                          </li>
	                                          <%-- 	                                          <c:if test="${readonly eq false}"> --%>
		                                          <li><a href="javascript:void(0)" onclick="createContract(5)">服务合同</a>
		                                          </li>                             
<%-- 	                                          </c:if>              --%>
	                                          
	                                          <li class="divider"></li>
<%-- 	                                          <c:if test="${readonly eq false}"> --%>
		                                          <li><a href="javascript:void(0)" onclick="createSubContract()">外包合同</a>
		                                          
		                                          </li>
<%-- 	                                          </c:if> --%>
	                                      </ul>
	                                  </div>
									</div>
								</c:if>
							</div>
							
							
							
							
							
							<div class="form-group">
							<c:if test="${parkerAudit eq 1}">
								<div class="input-group" style="display:none;" id="parker-audit-group">
								<div class="input-group-btn">
                                      <button data-toggle="dropdown" class="btn btn-success dropdown-toggle" type="button">园长审批 <span class="caret"></span>
                                      </button>
                                      <ul class="dropdown-menu">
                                          <li><a href="javascript:void(0)" onclick="parkerAudit(1)">同意</a>
                                          </li>
                                          
                                          <li><a href="javascript:void(0)" onclick="parkerAudit(0)">不同意</a>
                                          </li>
                                          
                                      </ul>
                                  </div>
								</div>
								</c:if>
								
								<input type="hidden" id="choosed-contract-id" >
								
								<c:if test="${financeAudit eq 1}">
								<div class="input-group" style="display:none;" id="finance-audit-group">
								<div class="input-group-btn">
                                      <button data-toggle="dropdown" class="btn btn-success dropdown-toggle" type="button">财务审批 <span class="caret"></span>
                                      </button>
                                      <ul class="dropdown-menu">
                                          <li><a href="javascript:void(0)"  onclick="financeAudit(1)">同意</a>
                                          </li>
                                          
                                          <li><a href="javascript:void(0)" onclick="financeAudit(0)">不同意</a>
                                          </li>
                                          
                                      </ul>
                                  </div>
								</div>
								</c:if>
							</div>
							
							
							
							
						</form>


						<table id="contract-table"
							class="display table table-striped table-bordered wrap" style="width:100%" cellspacing="0">
							<thead>
								<tr>
									<th style="width:27px;">选择</th>
									<th style="width:54px;">合同类型</th>
									<th>合同编号</th>
									<th>公司名称</th>
									<th>楼</th>
									<th>层</th>
									<th>区</th>
									<th>房</th>
									<th>开始日期</th>
									<th>结束日期</th>
									<th>最后结算日</th>
									<th>创建时间</th>									
									<th>合同状态</th>
<!-- 									<th>审批状态</th> -->
									<th>操作</th>
								</tr>
							</thead>
						</table>



					</div>
				</div>


			</div>


			<div class="col-md-4 col-sm-12 hidden">


				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>合同基本信息</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="dropdown-toggle" data-toggle="dropdown"
								href="form_advanced.html#"> <i class="fa fa-wrench"></i>
							</a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="form_advanced.html#">选项1</a></li>
								<li><a href="form_advanced.html#">选项2</a></li>
							</ul>
							<a class="close-link hidden"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">

						<div class="form-horizontal">

							<div class="form-group">
                                <label class="col-sm-3 control-label">合同编号</label>

                                <div class="col-sm-9">
                                    <input type="text" readonly class="form-control">
                                </div>
                            </div>

							<div class="form-group">
                                <label class="col-sm-3 control-label">公司名称</label>

                                <div class="col-sm-9">
                                    <input type="text" readonly class="form-control">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">公司入驻日期</label>

                                <div class="col-sm-9">
                                    <input type="text" readonly class="form-control">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">位置</label>

                                <div class="col-sm-9">
                                    <input type="text" readonly class="form-control">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">合同开始日期</label>

                                <div class="col-sm-9">
                                    <input type="text" readonly class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">合同结束日期</label>

                                <div class="col-sm-9">
                                    <input type="text" readonly class="form-control">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">合同签订日期</label>

                                <div class="col-sm-9">
                                    <input type="text" readonly class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">合同状态</label>

                                <div class="col-sm-9">
                                    <input type="text" placeholder="" class="form-control">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">合同终止原因</label>

                                <div class="col-sm-9">
                                    <textarea  rows="5" class="form-control">
                                    </textarea>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-offset-3">
                                <button class="btn btn-danger">终     止</button>
                                </div>
                            </div>


							
						</div>






					</div>
				</div>



			</div>

		</div>

	</div>




</body>

</html>