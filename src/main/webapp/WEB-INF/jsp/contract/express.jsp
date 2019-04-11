<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>


<div class="ibox float-e-margins">
	<div class="ibox-title">
		<h5>快递合同信息</h5>
		<div class="ibox-tools">
			<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
			
			
			<a class="close-link hidden"> <i class="fa fa-times"></i>
			</a>
		</div>
	</div>
	<div class="ibox-content" id="test">

		<div class="form-horizontal">
		
			<input type="hidden" id="express-id" />
			<div class="form-group">
                            <label class="col-sm-2 control-label">丢失面单价格</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control check-express-float" id="express-lost-bill-price" placeholder="丢失面单价格">
                            </div>
                            <label class="col-sm-2 control-label">残缺面单价格</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control check-express-float" id="express-incomplete-bill-price" placeholder="残缺面单价格">
                            </div>
                            
                           
                        </div>
                        
                        <hr />
                        
                                                   
                        <div class="form-group">
                          
                          
                          <div class="col-sm-1">
                            <button class="btn btn-primary"  data-toggle="modal" data-target="#billRuleModal">使用统一规则</button>
                         </div>
                          
                            <label class="col-sm-2 control-label">出账日</label>

                            <div class="col-sm-2">
                            	<input type="text" class="form-control check-express-int" id="express-bill-date"  placeholder="出帐日">
                            </div>
                            
                            <label class="col-sm-1 control-label">缴费日</label>

                            <div class="col-sm-2">
                            	<input type="text" class="form-control check-express-int" id="express-payment-date"  placeholder="缴费日">
                            </div>
                            
                            <label class="col-sm-1 control-label">计费周期</label>

                            <div class="col-sm-2">
                                <input type="text" class="form-control check-express-int" id="express-bill-period" placeholder="计费周期">
                            </div>
                            
                        </div>
                     
                     <hr />
                     
                     <div class="form-group">
                         <div class="col-sm-offset-4">
                            <button class="btn btn-primary col-sm-4 con-save-btn" id="express-save">保存快递合同信息</button>
                         </div>
                      </div>
                      
		</div>
		
		
		
		
		<hr />
		
		<div class="form-horizontal">
		
			<input type="hidden" id="express-item-id" />
			<div class="form-group">
                            <label class="col-sm-2 control-label">目的地</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control check-express-item-null" id="express-item-destination" placeholder="目的地">
                            </div>                           
                            
                        </div>
			
			
			

						<div class="form-group">
                            <label class="col-sm-2 control-label">首重</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control check-express-item-float" id="express-item-first-heavy" placeholder="首重">
                            </div>
                            <label class="col-sm-2 control-label">首重价格</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control check-express-item-float" id="express-item-first-heavy-price" placeholder="首重价格">
                            </div>
                            
                            
                        </div>
                        
                        
                        <div class="form-group">
                            <label class="col-sm-2 control-label">续重</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control check-express-item-float" id="express-item-forward-heavy" placeholder="续重">
                            </div>
                            <label class="col-sm-2 control-label">续重价格</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control check-express-item-float" id="express-item-forward-heavy-price" placeholder="续重价格">
                            </div>
                            
                            
                        </div>
                        
                        
                        <hr />
                      
                     
                     <hr />
                     
                     <div class="form-group">
                         <div class="col-sm-offset-2">
                         <button class="btn btn-info col-sm-3 con-save-btn" id="express-item-new">新建快递合同项</button>
                            <button class="btn btn-primary col-sm-offset-2 col-sm-3 con-save-btn" id="express-item-save">保存快递合同项</button>
                         </div>
                      </div>
                      
		</div>
		
		
		
		<hr />
		
		<table id="express-item-table"
			class="display table table-striped table-bordered responsive wrap"
			cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>目的地</th>
					<th>首重</th>
					<th>首重价格</th>
					<th>续重</th>
					<th>续重价格</th>
					<th>操作</th>
				</tr>
			</thead>
			
			<tbody>
			</tbody>
			
		</table>



	</div>
</div>
<script src="<%=basePath%>/myjs/contract.edit.express.js"></script>