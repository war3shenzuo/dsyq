<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="ibox float-e-margins">
	<div class="ibox-title">
		<h5>合同基本信息</h5>
		<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							
							
							<a class="close-link hidden"> <i class="fa fa-times"></i>
							</a>
						</div>
	</div>
	<div class="ibox-content">
		
		<input type="hidden" id="id" value="${contract.id }" />
		
		<input type="hidden" id="ref-lease-id" value="${contract.refContractLeaseId }" />
		
		<div class="form-horizontal">
		
			<div class="form-group">
                            <label class="col-md-2 control-label">合同类别</label>
							
                            <div class="col-md-10">
                                <select class="form-control" id="contract-category" disabled="true">
                                <option <c:if test="${contract.contractCategory ==1}">selected="true"</c:if> value="1">租赁合同</option>
                                <option <c:if test="${contract.contractCategory ==4}">selected="true"</c:if> value="4">物业合同</option>
                                <option <c:if test="${contract.contractCategory ==5}">selected="true"</c:if> value="5">服务合同</option>
                                <option <c:if test="${contract.contractCategory ==3}">selected="true"</c:if> value="3">能源合同</option>
                                <option <c:if test="${contract.contractCategory ==2}">selected="true"</c:if> value="2">外包合同</option>
                                
                                </select>
                            </div>
                        </div>

			<div class="form-group">
                            <label class="col-md-2 control-label">合同编号</label>

                            <div class="col-md-10">
                                <input type="text" readonly class="form-control" id="contract-no" value="${contract.contractNo }">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-2 control-label">纸质编号</label>

                            <div class="col-md-10">
                                <input type="text" class="form-control" id="paper-no" value="${contract.paperNo }">
                            </div>
                        </div>

			<hr />
			
			<div class="form-group">
			
            <div class="col-md-2" id="company-select" style="display:none;">                                                   
                     	<button class="btn btn-info" id="company-modal" data-toggle="modal" data-target="#companyModal">公司查找</button>
			</div>
			<label class="col-md-2 control-label" id="company-label">公司信息</label>

					
                     <div class="col-md-10">
                     	<input type="hidden" id="ref-company-id" value="${contract.refCompanyId }"  placeholder="公司" />
                     <input type="text" id="company-name" class="form-control" value="${contract.companyName }" placeholder="公司名称" disabled="disabled">
                 </div>
                 
                 
             </div>
             
             <div class="form-group" style="display:none;">
                 <label class="col-md-2 control-label" id="company-registration-date">公司注册日期</label>

                 <div class="col-md-10">
                     <input type="text" readonly class="form-control" value="${contract.companyRegistrationDate }">
                 </div>
             </div>
             
             
             <div class="form-group">
                  <label class="col-md-2 control-label">联系人</label>

               <div class="col-md-4">
                   <input type="text" readonly id="company-contacts-name" class="form-control" value="${contract.companyContactsName }">
               </div>
               
               <label class="col-md-2 control-label">联系电话</label>

               <div class="col-md-4">
                   <input type="text" readonly id="company-contacts-phone" class="form-control" value="${contract.companyContactsPhone }">
                   </div>
              </div>
              
             <div class="form-group" id="paymethod-container" style="display:none;">
			

				<label class="col-md-2 control-label">付款渠道</label>

					
                 <div class="col-md-10">
                     <input type="text" id="pay-method" class="form-control" value="${contract.contractPayMethod }" placeholder="付款渠道">
                 </div>                 
                 
             </div>
              
                 <hr />
                 
                 <div class="form-group room-info">
                 
                 	<input type="hidden" id="park-id" value="${contract.refParkId }" />
<%--                  	<input type="hidden" id="ref-building-id" value="${contract.refBuildingId }" /> --%>
                 	<input type="hidden" id="ref-room-id" value="${contract.refRoomId }" placeholder="房间信息" />
                 
                 	<label class="col-md-2 control-label" id="room-label" style="display:none;">房间信息</label>
                 	
                     <div class="col-md-2" id="room-select">
                         <button class="btn btn-info" id="room-modal" data-toggle="modal" data-target="#roomModal">房间查找</button>
                     </div>
					
					<div class="col-md-4">
                         <input type="text" readonly id="park" class="form-control" value="${contract.park }">
                 	</div>
                 	
                 	<label class="col-md-2 control-label">楼</label>
                 <div class="col-md-4">
                     <input type="text" readonly id="building" class="form-control" value="${contract.building }">
                 </div>
                 
                 </div>
                 
                 <div class="form-group room-info">
                 
                 
                 <label class="col-md-2 control-label">层</label>
                 <div class="col-md-4">
                     <input type="text" readonly id="floor" class="form-control" value="${contract.floor }">
                 </div>
                  <label class="col-md-2 control-label">区</label>
                 <div class="col-md-4">
                     <input type="text" readonly id="block" class="form-control" value="${contract.block }">
                 </div>
                 </div>
                 
                 <div class="form-group room-info">
                 
                
                 <label class="col-md-2 control-label">房</label>
                 <div class="col-md-4">
                     <input type="text" readonly id="room" class="form-control" value="${contract.room }">
                     
                 </div>
                 <label class="col-md-2 control-label">建筑面积</label>

                  <div class="col-md-4">
                      <input type="text" id="build-area" readonly class="form-control" value="${contract.roomStructureArea }">
                  </div>
                 
             </div>
             
             <div>
             
              <div class="form-group room-info hidden">
                  
                  <label class="col-md-2 control-label">使用面积</label>

                  <div class="col-md-4">
                      <input type="text" readonly id="used-area" class="form-control" value="${contract.roomUsableArea }">
                  </div>
              </div>
             
             
             	<div class="form-group room-info hidden">
                  <label class="col-md-2 control-label">预设月单价</label>

                  <div class="col-md-4">
                      <input type="text" readonly id="monthly-price" class="form-control"value="${contract.roomMonthlyPrice }">
                  </div>
                  
                  <label class="col-md-2 control-label">预设日单价</label>

                  <div class="col-md-4">
                      <input type="text" readonly id="daily-price" class="form-control"value="${contract.roomDailyPrice }">
                  </div>
              </div>
              
              <div class="form-group room-info">
                  <label class="col-md-2 control-label">朝向</label>

                  <div class="col-md-4">
                      <input type="text" readonly id="orientation" class="form-control" value="${contract.roomOrientation }">
                  </div>
                  
                  <label class="col-md-2 control-label">坐落</label>

                  <div class="col-md-4">
                      <input type="text" readonly id="located" class="form-control" value="${contract.roomLocated }">
                  </div>
              </div>
              
              <div class="form-group room-info">
                  <label class="col-md-2 control-label">层高</label>

                  <div class="col-md-4">
                      <input type="text" readonly id="layer-height" class="form-control" value="${contract.roomWindows }">
                  </div>
                  
                  <label class="col-md-2 control-label">装修程度</label>

                  <div class="col-md-4">
                      <input type="text" readonly id="decoration" class="form-control" value="${contract.roomDecoration }">
                  </div>
              </div>
             
             
             </div>
             
             <hr class="room-info" />
             
             <div class="form-group">
                 <label class="col-md-2 control-label">合同起止日期</label>
					<input type="hidden" id="lease-contract-start-date" value="${contract.leaseContractStartDate }">
					<input type="hidden" id="lease-contract-end-date" value="${contract.leaseContractEndDate }">
                 <div class="col-md-10">
                     <div class="input-daterange input-group date" id="se-datepicker">
                   
	                   <input type="text" class="input-sm form-control check-date" name="start" id="contract-start-date" value="${contract.contractStartDate }" placeholder="合同开始日期">
	                   <span class="input-group-addon">到</span>
	                   
	                   <input type="text" class="input-sm form-control check-date" name="end" id="contract-end-date" value="${contract.contractEndDate }" placeholder="合同结束日期">
               		</div>
                 </div>
                
                 
             </div>
             
             
             <div class="form-group">
                 <label class="col-md-2 control-label">合同签订日期</label>

                 <div class="col-md-4">
                     <div class="input-group date" id="sign-datepicker">
                   <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                   <input type="text" class="form-control check-date"  value="${contract.contractSignDate }" id="contract-sign-date" placeholder="合同签订日期">
               </div>
                 </div>
             </div>
             
             
             <div class="form-group" id="last-balance-container">
	
				<label class="col-md-2 control-label"><span class=" label-last-balance-date">最新结算日期</span></label>
			
				<div class="col-md-8">
					
				<div class="input-group date">
			          <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
			          <input type="text" class="form-control"  value="${contract.lastBalanceDate }" id="last-balance-date" placeholder="最新结算日期">
			      </div>
			      
			      
				<p class="balance-comment">指最近一次付费的覆盖截止日期；比如2017年1月31日支付了2017年2月1日至2017年7月31日的房租，“最新结算日期”就是2017年7月31日。
				</p>
				<p class="balance-comment">系统只对最新结算日期往后的未付费用进行计算并生成账单，如果合同需要从开始日期计算，则不需要填写“最新结算日期”。</p>
				
				</div>
				
			
			</div>
			
			<div class="form-group" id="deposit-container">
	
				<label class="col-md-2 control-label">合同押金</label>
			
				<div class="col-md-4">
							
			         <input type="text" class="form-control"  value="${contract.deposit }" id="contract-deposit" placeholder="押金">
			    
				</div>
			
			</div>
             
<!--              <div class="form-group hidden"> -->
             
<!--              <label class="col-md-2 control-label">合同审批状态</label> -->
<!-- 	             <div class="col-md-4"> -->
<!-- 	             <select class="form-control" id="contract-audit-status" disabled="true"> -->
<%-- 		             <option <c:if test="${contract.auditStatus ==-5}">selected="true"</c:if> value="-5">初始</option> --%>
<%-- 		             <option <c:if test="${contract.auditStatus ==0}">selected="true"</c:if> value="0">未审批</option> --%>
<%-- 		             <option <c:if test="${contract.auditStatus ==-1}">selected="true"</c:if> value="-1">财务拒绝</option> --%>
<%-- 		             <option <c:if test="${contract.auditStatus ==1}">selected="true"</c:if> value="1">财务已审批</option> --%>
<%-- 		             <option <c:if test="${contract.auditStatus ==-2}">selected="true"</c:if> value="-2">园长拒绝</option>	              --%>
<%-- 		             <option <c:if test="${contract.auditStatus ==2}">selected="true"</c:if> value="2">已审批</option> --%>
<!-- 	             </select>  -->
<!-- 	             </div>              -->
<!--              </div> -->
             
             <hr />
             
             <div class="form-group">
                  <label class="col-md-2 control-label">合同状态
                  
<!--                     <input type="checkbox" name="activate" id="status-check" disabled="disabled" -->
<%-- 					   <c:if test="${contract.contractStatus ==1}">checked="checked"</c:if> /> --%>
                  
                  </label>
                  
                  <div class="col-md-4">
                  <input type="hidden" id="contract-status" value="${contract.contractStatus}" />
                  <select class="form-control" id="contract-status-sele" disabled="true">
		             
	             </select> 
                  
                  </div>					
                  <div class="col-md-4">
                      <input type="text" readonly style="display:none;" class="form-control" id="contract-status-str" value="">
                  </div>
                  
                  <input type="hidden" id="contract-type" value="${contract.contractType }">
                  
              </div>
              
              <div class="form-group" id="terminate-group" style="display:none;">
              <div id="terminate-date-contain">
              	<label class="col-md-2 control-label">终止日期</label>
                	<div class="col-md-4">
                	
			        <input type="text" class="form-control" disabled="disabled" id="terminate-date" value="${contract.terminateDate }" />
			      
	              	
	              </div>
              </div>
              	<label class="col-md-2 control-label" id="terminate-reason-label">终止原因</label>
	              <div class="col-md-4">
	              	<textarea class="form-control" readonly id="terminate-reason" >${contract.terminateReason }</textarea>
	              </div>
              </div>
              <div class="form-group">
              <label class="col-md-2 control-label">合同备注</label>
	              <div class="col-md-10">
	              	<textarea class="form-control" id="contract-notes" >${contract.contractNotes }</textarea>
	              </div>
              </div>
              
              <hr />
             <c:if test="${readonly==false}">    
	             <div class="form-group">
	             
	             <div class="col-md-offset-1 col-md-2">
	             
	             	<input type="button" class="btn btn-warning"  style="display:none;" data-toggle="modal" data-action="t" data-target="#terminateModal" id="terminate-contract" value="  终止合同   " />
	            
	             </div>
	             
	             
	            <div class="col-md-offset-1 col-md-2">
	            	<input type="button" class="btn btn-danger"  style="display:none;" data-toggle="modal" data-action="d" data-target="#terminateModal" id="del-contract" value="  申请删除   " />
		         </div>
	             
	             <div class="col-md-offset-1 col-md-2">
	                 <button class="btn btn-primary con-save-btn" id="contract-save">保  存</button>
	             </div>
	             
		         <div class="col-md-offset-1 col-md-2">
		             <button class="btn btn-success con-save-btn" style="display:none;" id="contract-submit-audit">申请审批</button>
		         </div>
		         

	             
	             </div>
             
              </c:if>
			
		</div>

	</div>
</div>

<script src="<%=basePath%>/myjs/contract.edit.contract.js"></script>