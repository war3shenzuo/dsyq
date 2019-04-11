<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ibox float-e-margins">
	<div class="ibox-title">
		<h5>能源合同信息</h5>
		<div class="ibox-tools">
			<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
			
			
			<a class="close-link hidden"> <i class="fa fa-times"></i>
			</a>
		</div>
	</div>
	<div class="ibox-content" id="test">

		<div class="form-horizontal">
		
		
			<input type="hidden" id="energy-id" />
		
		
<!-- 			<div class="form-group"> -->
<!--                    <label class="col-md-2 control-label">开始日期</label> -->

<!--                    <div class="col-md-4"> -->
<!--                        <input type="text" disabled="disabled" id="energy-start-date" class="form-control" placeholder="开始日期" > -->
<!--                    </div> -->
                   
                    
<!--                    <label class="col-md-2 control-label">结束日期</label> -->

<!--                    <div class="col-md-4"> -->
<!--                        <input type="text" disabled="disabled" id="energy-end-date" class="form-control" placeholder="结束日期"> -->
<!--                    </div> -->
<!--              </div> -->
		
		
		
						<div class="form-group">
                            <label class="col-md-1 control-label">类型</label>
							<label class="col-md-2 control-label" style="text-align:left;">单价</label>
							<label class="col-md-2 control-label" style="text-align:left;">最后结算读数</label>
                            <label class="col-md-3 control-label" style="text-align:left;">最后结算日期</label>
                            <label class="col-md-2 control-label" style="text-align:left;">房间使用量是否出帐</label>
                            <label class="col-md-2 control-label" style="text-align:left;">公摊方式</label>
                        </div>
					
						<div class="hr-line-dashed"></div>
		
						<div class="form-group">
                            <label class="col-md-1 control-label">电费</label>

                            <div class="col-md-2">
                                <input type="text" id="energy-power" class="form-control check-energy-float" placeholder="电费单价" >
                            </div>
                            
                            <div class="col-md-2">
                                <input type="text" id="energy-power-record" class="form-control check-energy-record-float" placeholder="已结算电费读数" >
                            </div>
                            
                            <div class="col-md-3">
                            
                            <div class="input-group date">
					          <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
					          <input type="text" id="energy-power-record-date" class="form-control check-energy-record-date" readonly placeholder="已结算电费日期" >
					      	</div>
                                
                            </div>
                            
                            <div class="col-md-2">
                                <input type="text" id="room-amount-0" readonly class="form-control" placeholder="" >
                            </div>
                            
                            <div class="col-md-2">
                                <input type="text" id="room-share-0" readonly class="form-control" placeholder="" >
                            </div>
                            
                        </div>

						<div class="form-group">
                             
                            <label class="col-md-1 control-label">水费</label>

                            <div class="col-md-2">
                                <input type="text" id="energy-water" class="form-control check-energy-float" placeholder="水费单价">
                            </div>                           
                            <div class="col-md-2">
                                <input type="text" id="energy-water-record" class="form-control check-energy-record-float" placeholder="已结算水费读数" >
                            </div>
                            
                            <div class="col-md-3">
                                <input type="text" id="energy-water-record-date" class="form-control check-energy-record-date" readonly placeholder="已结算水费日期" >
                            </div>
                            
                            <div class="col-md-2">
                                <input type="text" id="room-amount-1" readonly class="form-control" placeholder="" >
                            </div>
                            
                            <div class="col-md-2">
                                <input type="text" id="room-share-1" readonly class="form-control" placeholder="" >
                            </div>
                        </div>

						<div class="form-group">
                            <label class="col-md-1 control-label">燃气</label>

                            <div class="col-md-2">
                                <input type="text" class="form-control check-energy-float" id="energy-gas" placeholder="燃气单价">
                            </div>
                            
                            <div class="col-md-2">
                                <input type="text" id="energy-gas-record" class="form-control check-energy-record-float" placeholder="已结算燃气读数" >
                            </div>
                            
                            <div class="col-md-3">
                                <input type="text" id="energy-gas-record-date" class="form-control check-energy-record-date" readonly placeholder="已结算燃气日期" >
                            </div>
                            
                            <div class="col-md-2">
                                <input type="text" id="room-amount-2" readonly class="form-control" placeholder="" >
                            </div>
                            
                            <div class="col-md-2">
                                <input type="text" id="room-share-2" readonly class="form-control" placeholder="" >
                            </div>
                   		</div>

						<div class="form-group">
                            <label class="col-md-1 control-label">空调</label>

                            <div class="col-md-2">
                                <input type="text" class="form-control check-energy-float" id="energy-ac" placeholder="空调单价">
                            </div>
                            <div class="col-md-2">
                                <input type="text" id="energy-ac-record" class="form-control check-energy-record-float" placeholder="已结算空调读数" >
                            </div>
                            
                            <div class="col-md-3">
                                <input type="text" id="energy-ac-record-date" class="form-control check-energy-record-date" readonly placeholder="已结算空调日期" >
                            </div>
                            <div class="col-md-2">
                                <input type="text" id="room-amount-3" readonly class="form-control" placeholder="" >
                            </div>
                            
                            <div class="col-md-2">
                                <input type="text" id="room-share-3" readonly class="form-control" placeholder="" >
                            </div>
                          
                        </div>
                        <div class="form-group e-record">
                        <label class="col-md-1 control-label">备注</label>
                        <label class="col-md-11 control-label" style="color:red;text-align:left;">如果本合同还未开始收取能源费用，“已结算读数”请填写合同初始读数，“已结算日期”填写合同开始日期前一日。</label>
                        </div>
                        
<!--                         <div class="form-group" id="last-fee-container"> -->
	
<!-- 							<label class="col-md-2 control-label">最后结算日</label> -->
						
<!-- 							<div class="col-md-4"> -->
								
<!-- 							<div class="input-group date"> -->
<!-- 						          <span class="input-group-addon"><i class="fa fa-calendar"></i></span> -->
<!-- 						          <input type="text" class="form-control"  value="" id="last-fee-date" placeholder="最后结算日"> -->
<!-- 						      </div> -->
<!-- 							</div> -->
						
<!-- 						</div> -->

                        
<%--                         <jsp:include page="billtype.jsp"/>	                            --%>
                        
                     
                     <hr />
                     <c:if test="${readonly==false}">
                     <div class="form-group">
                         <div class="col-md-offset-4">
                            <button class="btn btn-primary col-md-4 con-save-btn" id="energy-item-save">保存</button>
                         </div>
                      </div>
                      </c:if> 
		</div>


	</div>
</div>

<script src="<%=basePath%>/myjs/contract.edit.energy.js"></script>