<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="ibox float-e-margins" id="bill-rule-ibox">
	<div class="ibox-title">
		<h5>出帐规则</h5>
		<input type="hidden" id="instalment-status" />
		<div class="ibox-tools">
			<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
			
			
			<a class="close-link hidden"> <i class="fa fa-times"></i></a>
		</div>
	</div>
	<div class="ibox-content">


	<div class="form-inline">
	
		<jsp:include page="billtype.jsp"/>	
                     
        <hr />
                     
	
	
	</div>

	
	</div>
</div>





<div class="ibox float-e-margins">
	<div class="ibox-title">
		<h5>合同分期    (<span id="instalment-info"></span>)</h5>
		<input type="hidden" id="instalment-status" />
		<div class="ibox-tools">
			<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
			
			
			<a class="close-link hidden"> <i class="fa fa-times"></i></a>
		</div>
	</div>
	<div class="ibox-content" id="test">






		<div class="form-horizontal">
		<input type="hidden" id="item-id" />
		
			<div id="item-editor" style="display:none;">
			
			<div class="form-group">
                   <label class="col-md-2 control-label">起止日期</label>

                   <div class="col-md-6">
                       <div class="input-daterange input-group date" id="item-datepicker">
                     <input type="text" class="input-sm form-control check-item-date" name="item-start" id="item-start-date" value="" disabled="disabled" placeholder="开始日期">
                     <span class="input-group-addon">到</span>
                     <input type="text" class="input-sm form-control check-item-date" name="item-end" id="item-end-date" value="" placeholder="结束日期">
                 </div>
                   </div>
                   
            </div>

			<div class="form-group">        
                   <label class="col-md-2 control-label" id="label-content" style="display:none;">面积</label>
					<div class="col-md-2" id="service-select">
                         <button class="btn btn-info" id="service-item-modal" data-toggle="modal" data-target="#serviceItemModal">服务项目</button>
                     </div>
                   <div class="col-md-10">
                       <input type="text" id="content" class="form-control">                       
                   </div>
               </div>

				<div class="form-group">
				
				
					 <div class="col-md-2">
					<select id="item-balance-monthly"  class="form-control" >
						<option value=0>按日结算</option>
						<option value=1>按月结算</option>					
					</select>
					</div>
				
                    <label class="col-md-1 control-label">日单价</label>

                    <div class="col-md-2">
                        <input type="text" class="form-control check-item-float" data="" id="item-daily-price" placeholder="日单价">
                    </div>
                    <label class="col-md-1 control-label">月单价</label>

                    <div class="col-md-2">
                        <input type="text" class="form-control check-item-float" data="" id="item-monthly-price" placeholder="月单价">
                    </div>
                    
                    <label class="col-md-1 control-label">总价</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control check-item-float" data="" id="item-amount"  placeholder="总价">
                    </div>
                </div>
                        
                        <hr />
                        
                        
                     </div>
                     
                     
	                     <div class="form-group" >
	                         <div class="col-md-offset-2">
<%-- 	                         <c:if test="${readonly eq false and contract.contractStatus le 0}"> --%>
	                         	<button class="btn btn-primary col-md-3 con-save-btn" id="new-item">新建</button>
	                            <button class="col-md-offset-2 btn btn-primary col-md-3 con-save-btn" id="save-item" style="display:none;">保存</button>
<%-- 	                  		 </c:if> --%>
	                            <button class="col-md-offset-2 btn btn-warning col-md-3" id="cancel-item" style="display:none;">取消</button>
	                         </div>
	                      </div>
                      
                      
		</div>
		
		
		<hr />
		
		<table id="item-table"
			class="display table table-striped table-bordered wrap"
			cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>开始日期</th>
					<th>结束日期</th>
<!-- 					<th>最后结算日</th> -->
					<th>总价</th>
					<th>操作</th>
				</tr>
			</thead>
			
			<tbody>
			</tbody>
			
		</table>



	</div>
</div>

<%-- <script src="<%=basePath%>/js/jquery.inputmask.bundle.min.js"></script> --%>
<script src="<%=basePath%>/myjs/contract.edit.instalments.js"></script>