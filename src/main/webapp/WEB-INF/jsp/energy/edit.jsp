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
<title>能源编辑</title>
<jsp:include page="../shared/css.jsp"/>

<jsp:include page="../shared/js.jsp"/>
<script src="<%=basePath%>/js/jquery.noty.packaged.min.js"></script>
<script src="<%=basePath%>/myjs/my.function.js"></script>

<script type="text/javascript">
	
var basePath = "<%=basePath%>";
	
</script>
<style>

.energy-type-title{color:green;}

</style>

</head>
<body class="gray-bg">
<input type="hidden" id="read-only" value="${readonly}" />
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
		
		<div class="col-md-5 col-sm-12">
		
		<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>能源录入条件</h5>
					
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						<a class="close-link hidden"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
				
				<div class="form-horizontal">					
				
				<div class="form-group hidden">
					
					<label class="col-md-2 control-label">类别选择：</label>
					<div class="col-md-10 e-func" id="energy-category-container">
						<input type="radio" value="0" data="0" checked="true" name="energy-category">分区房间能源
	                    <input type="radio" value="1" data="1" name="energy-category">楼能源
	                    <input type="radio" value="0" data="2" name="energy-category">单个房间能源
     				</div>
				</div>
					
						
				<div class="form-group">
		
					<label class="col-md-2 control-label">能源类型：</label>
					<div class="col-md-4">
					<select class="form-control e-func" name="energy-type" id="energy-type" placeholder="能源类型">
							<option value="">选择能源类型</option>
					</select>
					</div>
		
				</div>
				
				<div class="form-group">
				
				
					<label class="col-md-2 control-label">抄表日期：</label>
		                      
                     <div class="col-md-4">
                     <input type="hidden" id="enter-type" />
                     <input type="hidden" id="enter-day" />
	                   <input type="text" class="form-control" readonly id="record-date" placeholder="抄表日期">
	               
					</div>
					
<!-- 					<label class="col-md-4 control-label">每期抄表日期必须一致</label> -->
				
				</div>
				
				<div class="form-group" id="entry-summary">
					<label class="col-md-2 control-label">录入数统计：</label>
					<label class="col-md-1 control-label">楼：</label>
					<label class="col-md-1 btn btn-sm" id="building-isentry"></label>
					
					<label class="col-md-2 control-label">房数：</label>
					<label class="col-md-1 control-label" id="room-count"></label>
					<label class="col-md-2 control-label">已录：</label>
					<label class="col-md-1 control-label" id="room-entry-count"></label>
					
				</div>
				
				<div class="form-group">
				<label class="col-md-2 control-label">录入使用量：</label>
				<label class="col-md-1 control-label">楼：</label>
				<label class="col-md-2 control-label" id="building-sum"></label>
				
				<label class="col-md-1 control-label">房：</label>
				<label class="col-md-2 control-label" id="rooms-sum"></label>
				
				<label class="btn btn-warning control-label" id="used-warning" style="display:none;">房间使用量超过楼使用量</label>
				
				</div>
				
				<div id="block-select-container">			
				
				<div class="form-group">
				<label class="col-md-2 control-label">已选楼层：</label>
					<label class="col-md-4 control-label" style="text-align:left;" id="selected-building-name"></label>
					<label class="col-md-4 control-label" style="text-align:left;" id="selected-floor-name"></label>
					<label class="col-md-2 control-label" style="text-align:left;" id="selected-block-name"></label>
					<input type="hidden" id="selected-block-id">
					<input type="hidden" id="selected-building-id">
				</div>
				
				
							
				<div class="form-group e-r-func">
					<label class="col-md-2 control-label">楼层选择：</label>
					<div class="col-md-4">
					<input type="hidden" id="floor-selected-type" /> 
					<input type="hidden" id="energy-category">
					<div id="floor-tree"></div>
					</div>

					<div id="rooms-container" style="display:none;">
						<label class="col-md-2 control-label">房间选择：</label>
						<div class="col-md-4">
							<select id="rooms-select" class="form-control" multiple style="min-height:300px;"></select>
						</div>
					</div>
					
				</div>
				
				
				
				</div>			
			</div>
				</div>
				</div>
				
		
			
		</div>
			<div class="col-md-7 col-sm-12">
			<div class="ibox float-e-margins" id="rooms-ibox" style="display:none;">
				<div class="ibox-title">
					<h5>分区房间能源录入<span class="energy-type-title"></span><span style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;(若一个房间有多个读数，请填写读数之和。)</span></h5>
					
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						<a class="close-link hidden"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
			
					<div class="form-horizontal">					
	                 <c:if test="${readonly==false}">
	                     <div class="form-group">
	                         <div class="col-md-offset-1 col-md-3">
	                            <button class="btn btn-info e-r-func" style="display:none;" id="rooms-start">开始录入</button>	                            
	                         </div>
	                         
	                         <div class="col-md-offset-1 col-md-3">
	                            <button class="btn btn-primary e-r-func" id="rooms-save"  style="display:none;">保存记录</button>         
	                         </div>
	                         
	                         <div class="col-md-offset-1 col-md-3">	                            
	                            <button class="btn btn-warning e-r-func" id="rooms-cancel"  style="display:none;">取消录入</button>
	                         </div>
	                      </div>
	                 </c:if>
	                     
					</div>
					<hr />
					
					<table id="rooms-table"
							class="display table table-striped table-bordered wrap"  style="width:100%" cellspacing="0">
							<thead>
								<tr>
									<th>楼</th>
									<th>层</th>
									<th>区</th>
									<th>房</th>
									<th>记录读数</th>
									<th>记录日期</th>
									<th>最后结算读数</th>
									<th>最后结算日期</th>
								</tr>
							</thead>
						</table>
					
							
				</div>
			</div>
			
			
			
			<div class="ibox float-e-margins" id="supply-rooms-ibox" style="display:none;">
				<div class="ibox-title">
					<h5>合同结束房间能源录入<span class="energy-type-title"></span><span style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;(若一个房间有多个读数，请填写读数之和。)</span></h5>
					
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						<a class="close-link hidden"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
			
					<div class="form-horizontal">					
	                 <c:if test="${readonly==false}">
	                     <div class="form-group">
	                         	                         
	                         <div class="col-md-offset-1 col-md-3">
	                            <button class="btn btn-primary" id="supply-rooms-save" >保存记录</button>         
	                         </div>
	                         
	                      </div>
	                 </c:if>
	                     
					</div>
					<hr />
					<input type="hidden" id="supply-energy-list" />
					<table id="supply-rooms-table"
							class="display table table-striped table-bordered wrap"  style="width:100%" cellspacing="0">
							<thead>
								<tr>
									<th>合同编号</th>
									<th>状态</th>
									<th>房间</th>
									<th>开始日期</th>
									<th>结束日期</th>
									<th>记录读数</th>
									<th>记录日期</th>
								</tr>
							</thead>
						</table>
					
							
				</div>
			</div>
			
			
			
			
			<div class="ibox float-e-margins" id="building-ibox" style="display:none;">
				<div class="ibox-title">
					<h5>楼能源录入<span class="energy-type-title"></span></h5>
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						<a class="close-link hidden"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
				
				<div class="form-horizontal">					
	                 
	                     <div class="form-group">
	                        <label class="col-md-2 control-label">楼：</label>
							<input type="hidden"  id="building-id"> 
							<input type="hidden" id="share-type">
							<input type="hidden" id="room-amount-used">	
	                        <div class="col-md-4">
	                        	<input type="text" class="form-control" readonly id="building-name">
	                        </div> 	                        
	                        
	                     </div>
	                     <div class="form-group">
	                     
	                     <input type="hidden" id='building-has-last' />
	                     	
	                        <label class="col-md-2 control-label">最后结算读数：</label>
								
	                        <div class="col-md-4">
	                        	<input type="text" class="form-control" readonly id="building-last-record" placeholder="最后结算读数">
	                        </div> 
	                        <label class="col-md-2 control-label">最后结算日期：</label>
								
	                        <div class="col-md-4">
	                        
				               <input type="text" class="form-control" readonly id="building-last-record-date" placeholder="最后结算日期">
				                   
				            
	                        	                        	
	                        </div> 
	                        
	                     </div>
	                     
	                     <div class="form-group">
	                        <label class="col-md-2 control-label">本次记录读数：</label>
								<input type="hidden" id="building-curr-isbilled" >
	                        <div class="col-md-4">
	                        	<input type="text" class="form-control" id="building-cur-record" placeholder="本次记录读数">
	                        </div> 
	                        <label class="col-md-2 control-label">本次记录日期：</label>
								
	                        <div class="col-md-4">
	                        
<!-- 	                         <div class="input-group date"> -->
<!-- 				                   <span class="input-group-addon"><i class="fa fa-calendar"></i></span> -->
				                   <input type="text" class="form-control" readonly id="building-cur-record-date" placeholder="本次记录日期">
				                   
<!-- 				               </div> -->
	                        	
	                        	
	                        	
	                        	
	                        </div> 
	                        
	                     </div>
	                     
	                     <div class="form-group">
	                         <div class="col-md-offset-1 col-md-3">
	                            <button class="btn btn-primary e-s-func" style="display:none;" id="building-save">保存记录</button>         
	                            <button class="btn btn-warning" style="display:none;" id="rooms-zero-record">所有房间批量填零</button>
	                         </div>
	                        
	                      </div>
	                      <hr />
	                      
			                      
					</div>	
				
				
				
			
<!-- 					<div class="form-horizontal">					 -->
	                 
<!-- 	                     <div class="form-group"> -->
<!-- 	                          <div class="col-md-offset-1 col-md-3"> -->
<!-- 	                            <button class="btn btn-info e-s-func"  style="display:none;" id="building-start">开始录入</button>	                             -->
<!-- 	                         </div> -->
	                         
<!-- 	                         <div class="col-md-offset-1 col-md-3"> -->
<!-- 	                            <button class="btn btn-primary e-s-func" id="building-save"  style="display:none;">保存记录</button>          -->
<!-- 	                         </div> -->
	                         
<!-- 	                         <div class="col-md-offset-1 col-md-3">	                             -->
<!-- 	                            <button class="btn btn-warning e-s-func" id="building-cancel"  style="display:none;">取消录入</button> -->
<!-- 	                         </div> -->
<!-- 	                      </div> -->
<!-- 	                      <hr /> -->
	                      
			                      
<!-- 					</div>	 -->
					
<!-- 					<hr /> -->
					
<!-- 					<table id="building-table" -->
<!-- 							class="display table table-striped table-bordered responsive nowrap"  style="width:100%" cellspacing="0"> -->
<!-- 						<thead> -->
<!-- 							<tr> -->
<!-- 								<th>楼</th> -->
<!-- 								<th>记录读数</th> -->
<!-- 								<th>记录日期</th> -->
<!-- 								<th>最近一次记录读数</th> -->
<!-- 								<th>最近一次记录日期</th> -->
<!-- 							</tr> -->
<!-- 						</thead> -->
<!-- 					</table> -->
					
							
				</div>
			</div>
			
			
			<div class="ibox float-e-margins" id="room-ibox" style="display:none;">
				<div class="ibox-title">
					<h5>单个房间能源录入<span class="energy-type-title"></span></h5>
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						<a class="close-link hidden"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
			
					<div class="form-horizontal">					
	                 
	                     <div class="form-group">
	                        <label class="col-md-2 control-label">房间：</label>
							<input type="hidden"  id="room-id"> 	
	                        <div class="col-md-4">
	                        	<input type="text" class="form-control" readonly id="room-name">
	                        </div> 	                        
	                        
	                     </div>
	                     <div class="form-group">
	                        <label class="col-md-2 control-label">最近一次记录读数：</label>
								
	                        <div class="col-md-4">
	                        	<input type="text" class="form-control" readonly id="room-last-record">
	                        </div> 
	                        <label class="col-md-2 control-label">最近一次记录日期：</label>
								
	                        <div class="col-md-4">
	                        	<input type="text" class="form-control" readonly id="room-last-record-date">
	                        </div> 
	                        
	                     </div>
	                     
	                     <div class="form-group">
	                        <label class="col-md-2 control-label">本次记录读数：</label>
								
	                        <div class="col-md-4">
	                        	<input type="text" class="form-control" id="room-cur-record" placeholder="本次记录读数">
	                        </div> 
	                        <label class="col-md-2 control-label">本次记录日期：</label>
								
	                        <div class="col-md-4">
	                        
	                         <div class="input-group date">
				                   <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
				                   <input type="text" class="form-control" readonly id="room-cur-record-date" placeholder="本次记录日期">
				                   
				               </div>
	                        	
	                        	
	                        	
	                        	
	                        </div> 
	                        
	                     </div>
	                     
	                     <div class="form-group">
	                         <div class="col-md-offset-1 col-md-3">
	                            <button class="btn btn-primary e-s-func" style="display:none;" id="room-save">保存记录</button>         
	                         </div>
	                        
	                      </div>
	                      <hr />
	                      
			                      
					</div>	
					
					
					
							
				</div>
			</div>
			
			
						

		</div>					

		</div>

	</div>
</body>

<script src="<%=basePath%>/myjs/energy.edit.room.js"></script>
<script src="<%=basePath%>/myjs/energy.edit.building.js"></script>
</html>