<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>能源记录</title>
<jsp:include page="../shared/css.jsp"/>

<jsp:include page="../shared/js.jsp"/>
<script src="<%=basePath%>/js/jquery.noty.packaged.min.js"></script>
<script src="<%=basePath%>/myjs/my.function.js"></script>
<%-- <link rel="stylesheet" type="text/css" href="<%=basePath%>/js/Buttons-1.2.2/css/buttons.dataTables.min.css"> --%>

<script type="text/javascript">
	
var basePath = "<%=basePath%>";
	
</script>
<style type="text/css">


td { word-wrap:break-word;}
</style>
</head>
<body class="gray-bg">
<input type="hidden" id="parker-audit" value="${parkerAudit}" />
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
		
		
		<div class="col-md-3 col-sm-12">
			<div class="ibox float-e-margins" id="room-ibox">
				<div class="ibox-title">
					<h5>房间选择</h5>
					<input type="hidden" id="energy-type-selected">
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
						<a class="close-link hidden"> <i class="fa fa-times"></i></a>
					</div>
				</div>
				<div class="ibox-content">
					<input type="hidden" id="ref-id" />
					<input type="hidden" id="id-type" />					
					<div class="row">
					
						<div class="col-md-12">
                                 
                                <select class="form-control" id="parkId" name="parkId">
                                 	<option value="">选择园区</option>
                                     <c:forEach items="${parks}" var="park">
                                         <option value="${park.id}">${park.parkName}</option>
                                     </c:forEach>
                                 </select>
                            
						</div>
					
<hr />
					
	                	<div class="col-md-7">
		                    <div id="floor-tree"></div>
		                    </div>
	                    <div class="col-md-5">
	                    	<select id="rooms-select" class="form-control" multiple>       
	                    
	                    	</select>
	                    </div>
                    </div>
									
							
				</div>
			</div>
							

		</div>			
		
		
		
		<div class="col-md-9 col-sm-12">
		
		<div class="tabs-container">
             <ul class="nav nav-tabs">
                 <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true"> 能源帐单 </a>
                 </li>
                 <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false"> 能源记录 </a>
                 </li>
             </ul>
             <div class="tab-content">
                 <div id="tab-1" class="tab-pane active">
                     <div class="panel-body">
                         
               
				
				<div class="form-horizontal">					
				
					<div class="form-group room-info">
	                 	
	                     <div class="col-md-2 hidden" id="room-select">
	                         <button class="btn btn-info" id="room-modal" data-toggle="modal" data-target="#roomModal">房间查找</button>
	                     </div>
	                     
	                     <label class="col-md-1 control-label">房间信息:</label>
					 
		                 <label class="col-md-1 control-label">楼</label>
		                 <div class="col-md-1">
		                     <input type="text" readonly id="building" class="form-control" value="">
		                 </div>
		                 <label class="col-md-1 control-label">层</label>
		                 <div class="col-md-1">
		                     <input type="text" readonly id="floor" class="form-control" value="">
		                 </div>

	                 
		                 <label class="col-md-1 control-label">区</label>
		                 <div class="col-md-1">
		                     <input type="text" readonly id="block" class="form-control" value="">
		                 </div>
		                 <label class="col-md-1 control-label">房</label>
		                 <div class="col-md-1">
		                     <input type="text" readonly id="room" class="form-control" value="">
		                     <input type="hidden" id="ref-room-id" value=""  placeholder="房间" />
		                 </div>
	                  	<label class="col-md-1 control-label">面积</label>
		                 <div class="col-md-2">
		                     <input type="text" readonly id="area" class="form-control" value="">
		                     
		                 </div>
	             	</div>
	             	
	             	<div class="form-group room-info">
	                 
		                 <label class="col-md-1 control-label">所属合同:</label>
		                 <div class="col-md-2">
		                 	<input type="text" readonly id="c-no" class="form-control" value="">
		                 </div>
		                 <label class="col-md-1 control-label">签订日期:</label>
		                 <div class="col-md-2">
		                 	<input type="text" readonly id="c-sign-date" class="form-control" value="">
		                 </div>
		                 <label class="col-md-1 control-label">开始日期:</label>
		                 <div class="col-md-2">
		                 	<input type="text" readonly id="c-start-date" class="form-control" value="">
		                 </div>
		                 <label class="col-md-1 control-label">结束日期:</label>
		                 <div class="col-md-2">
		                 	<input type="text" readonly id="c-end-date" class="form-control" value="">
		                 </div>
<!-- 		                 <label class="col-md-2 control-label">结束日期</label> -->
		                 
	                 
	             	</div>
							
				</div>
			
			<hr />
					
			<table id="energy-bill-table"
					class="display table table-striped table-bordered wrap"  style="width:100%" cellspacing="0">
					<thead>
						<tr>
							<th>帐单编号</th>
							<th>客户名称</th>
							<th style="width:400px;">描述</th>
							<th>金额</th>
							<th>滞纳金</th>
							<th>账单状态</th>
							<th>审核状态</th>
							<th>出帐日期</th>
							<th>截止日期</th>							
						</tr>
					</thead>
				</table>
			
			
			
			
			
                         
                     </div>
                 </div>
                 <div id="tab-2" class="tab-pane">
                     <div class="panel-body">
                     <div class="form-inline">
                     
                     <select class="form-control" id="search-energy-type">
                     	<option value="">选择能源</option>
                     	<option value="0">电</option>
                     	<option value="1">水</option>
                     	<option value="2">燃气</option>
                     	<option value="3">空调</option>
                     
                     </select>
                     
                     <div class="form-group">
								
						<div class="input-group date">
                           <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                           <input type="text" class="form-control" value="" id="search-energy-enter-start-date" placeholder="录入开始日期">
                       	</div>
                       	<div class="input-group date">
                           <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                           <input type="text" class="form-control" value="" id="search-energy-enter-end-date" placeholder="录入结束日期">
                       	</div>						
						
					</div>
					
					<div class="form-group">
								
						<div class="input-group date">
                           <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                           <input type="text" class="form-control" value="" id="search-energy-bill-start-date" placeholder="出帐开始日期">
                       	</div>
                       	<div class="input-group date">
                           <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                           <input type="text" class="form-control" value="" id="search-energy-bill-end-date" placeholder="出帐结束日期">
                       	</div>						
						
					</div>
                     
                     
                     <input type="button" class="btn btn-primary" value="查找" id="filter-energy-record" />
                     <c:if test="${parkerAudit eq 1}">
                     	<input type="button" class="btn btn-danger" value="保存" id="save-energy-record" />
                     </c:if>
                     </div>
                     <p></p>
                     <div class="form-horizontal">
	                     <div class="form-group">
	                     
	                     <div class="alert alert-success col-md-12" id="energy-summary">
                            
                            
                            
                            
                        </div>
	                     
	                     
	                     </div>
                     </div>
                     
                     
                         <table id="energy-record-table"
							class="display table table-striped table-bordered wrap "  style="width:100%" cellspacing="0">
							<thead>
								<tr>
									<th>房间</th>
									<th>类别</th>
									<th>抄表日期</th>
									<th>读数</th>
									<th>使用量</th>
									<th>天数</th>
									<th>日均</th>
									<th>出帐状态</th>
									<th>出帐日期</th>
									<th>已结算帐单</th>
								</tr>
							</thead>
						</table>
                         
                         
                     </div>
                 </div>
             </div>


         </div>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
				
		
			
		</div>
						

		</div>

	</div>
	
	<div class="modal fade in" id="roomModal" tabindex="-1" role="dialog" aria-labelledby="roomModalLabel" aria-hidden="true" style="display: none;padding-right: 6px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="roomModalLabel">房间选择</h4>
                </div>
                <div class="modal-body">
                
                <div class="wrapper wrapper-content animated fadeInRight">
                
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="btn-load">加载</button>
                </div>
            </div>
        </div>
</div>
	
	
</body>
<%-- <script type="text/javascript" src="<%=basePath%>/js/Buttons-1.2.2/js/dataTables.buttons.min.js"  charset="utf-8"></script> --%>
<%-- <script type="text/javascript" src="<%=basePath%>/js/Buttons-1.2.2/js/buttons.html5.js" charset="utf-8"></script> --%>
<script src="<%=basePath%>/myjs/energy.show.js"></script>
</html>