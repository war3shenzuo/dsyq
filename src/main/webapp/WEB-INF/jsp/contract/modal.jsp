<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%--     <link href="<%=basePath %>/css/plugins/iCheck/custom.css" rel="stylesheet"> --%>

<style>
/* .datepicker.dropdown-menu { */
  
/*   z-index: 3000; */
/*   } */

/* .modal { */
/*     z-index: 2050!important; */
/* } */
</style>

<script>

$(document).ready(function(){

	//$('#roomModal').modal({show: false})



	$('#roomModal').on('shown.bs.modal', function () {

	})
	$('#roomModal').on('hidden.bs.modal', function (e) {
		//get room info,show in editor for contract lease
	})

	//$('#companyModal').modal({show: false})
	
	

	
})
</script>
<div class="modal fade in" id="roomModal" tabindex="-1" role="dialog" aria-labelledby="roomModalLabel" aria-hidden="true" style="display: none;padding-right: 6px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="roomModalLabel">房间选择</h4>
                </div>
                <div class="modal-body">
                
                <div class="wrapper wrapper-content animated fadeInRight">
                <div class="row">
                <div class="col-md-6">
                    <div id="floor-tree"></div>
                    </div>
                    <div class="col-md-6">
                    
                    <select id="rooms-select" class="form-control" multiple>       
                    
                    </select>
                    </div>
                    </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
</div>


<div class="modal fade in" id="companyModal" tabindex="-1" role="dialog" aria-labelledby="companyModalLabel" aria-hidden="true" style="display: none; padding-right: 6px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="companyModalLabel">公司查找</h4>
                </div>
                <div class="modal-body">
                <div class="row">
                    <div class="col-md-8">
                    	<input type="text" class="form-control" id="company-search-value" placeholder="公司名">
                    </div>
                    <div class="col-md-4">
                    	<input type="button" class="btn btn-primary" id="company-search" value="查找">
                    </div>
                  </div>
                  
                    <hr />
                    
                    <select id="company-search-select" class="form-control" multiple>       
                    
                    </select>
                    
                </div>
                <div class="modal-footer">
                	
                    <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
</div>



<div class="modal fade in" id="billRuleModal" tabindex="-1" role="dialog" aria-labelledby="billRuleModalLabel" aria-hidden="true" style="display: none; padding-right: 6px;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="billRuleModalLabel">规则选择</h4>
                </div>
                <div class="modal-body">
                    <div id="bill-rule-container" class="unstyled"></div>
                    <select  id="bill-rule-select" class="form-control" multiple></select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
</div>

<div class="modal fade in" id="serviceItemModal" tabindex="-1" role="dialog" aria-labelledby="serviceItemModalLabel" aria-hidden="true" style="display: none; padding-right: 6px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="serviceItemModalLabel">服务选择</h4>
                </div>
                <div class="modal-body">
                    <div id="service-item-container" class="unstyled"></div>
                    <select  id="service-item-select" class="form-control" multiple></select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
</div>

<div class="modal fade in" id="terminateModal" tabindex="-1" role="dialog" aria-labelledby="terminateModalLabel" aria-hidden="true" style="display: none; padding-right: 6px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="terminateModalLabel">终止合同</h4>
                </div>
                <div class="modal-body">
                	<input type="hidden" id='t-contract-id' />               
                	<input type="hidden" id='t-contract-status' />
                    <form>
			          <div class="form-group">
			            <label for="t-company-name" class="control-label">公司名称：</label>
			            <input type="text" class="form-control" readonly id="t-company-name">
			          </div>
			          <div class="form-group">
			            <label for="t-contract-category" class="control-label">合同类型：</label>
			            <input type="text" class="form-control" readonly id="t-contract-category">
			          </div>
			          <div class="form-group action-date">
			            <label for="t-contract-category" class="control-label">终止日期：</label>
			            
			            <div class="input-group date" id="sign-datepicker">
                   <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                           <input type="text" class="form-control" readonly  id="t-date" placeholder="合同终止日期（格式：yyyy-mm-dd）">
               </div>
			            
			            
			       	               
			          </div>
			          
			          
			          <div class="form-group">
			            <label for="t-reason" class="control-label action-reason">终止原因：</label>
			            <textarea class="form-control" id="t-reason"></textarea>
			          </div>
			        </form>
                    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                     <button type="button" class="btn btn-primary" id="btn-terminate">申请终止</button>
                     <button type="button" class="btn btn-primary" id="btn-del">申请删除</button>
                </div>
            </div>
        </div>
</div>