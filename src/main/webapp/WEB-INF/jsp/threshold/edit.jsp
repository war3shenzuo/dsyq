<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.etop.management.entity.EtopThreshold.ThresholdKey" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
	<jsp:include page="/WEB-INF/jsp/shared/css.jsp" />
	
	<title>阈值设定</title>
	<script type="text/javascript">
	
var basePath = "<%=basePath%>";
	
</script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form class="form-horizontal" id="signupForm">
					<h2>阀值设置</h2>
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label"><%=ThresholdKey.OverdueRate.desc %>(‰)</label>
							</div>
							<div class="col-sm-2">
								<input type="number" class="form-control " placeholder="" readonly="readonly"
									id="overdue-input" thresholdKey="<%=ThresholdKey.OverdueRate.name %>"
									value="${overdue_rate*1000 }">
							</div>
							<div class="col-sm-2">
                     			<a class="btn btn-primary" onclick="updateThreshold(this, '#overdue-input')">修改</a>
							</div>
						</div>
						<div class="form-group" style="color:red;">
						<label class="col-sm-2 control-label">提示:</label>
						<div class="col-sm-10 ">
						<label class="control-label">当账单超过缴费日期后，将按欠款金额×每日滞纳金比例进行累算滞纳金。</label>
						</div>
						</div>
						
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label"> 服务账单缴费期限：</label>
							</div>

                        	<div class="col-sm-2">
								<input type="number" class="form-control " placeholder="" readonly="readonly"
									id="deadline-input"  thresholdKey="<%=ThresholdKey.deadline.name %>"  value="${deadline}">
							</div>
							<div class="col-sm-2">
                     			<a class="btn btn-primary" onclick="updateDeadline(this, '#deadline-input')">修改</a>
							</div>
                        </div>
                        <div class="form-group" style="color:red;">
						<label class="col-sm-2 control-label">提示:</label>
						<div class="col-sm-4 ">
						<label class="control-label">缴费日期设定为创建账单起所要截止的期限。</label>
						</div>
						</div>
						
						<div class="form-group hidden">
							<div class="col-sm-2 " style="text-align: right">
								<label class="control-label"><%=ThresholdKey.BillAmount.desc %></label>
							</div>
							<div class="col-sm-2">
								<input type="number" class="form-control " placeholder="" readonly="readonly"
									id="amount-input" thresholdKey="<%=ThresholdKey.BillAmount.name %>"
									value="${bill_amount }">
							</div>
							<div class="col-sm-2">
                     			<a class="btn btn-primary" onclick="updateThreshold(this, '#amount-input')">修改</a>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
					</form>
					
					
					
					
		<div class="row">
		
		<div class="col-md-12"><h2>出帐统一规则设置</h2></div>
					
		<div class="col-md-5">
			<div class="form-horizontal">
					
						
				<div class="form-group">
				<div>
					<select  id="bill-rule-select" class="form-control" multiple style="min-height:250px;"></select>
				</div>
				
				</div>
			
			</div>
			
		</div>
			
		<div class="col-md-7">
			<div id="bill-rule-container" class="form-inline" style="display:none;">
		
		
<label class="control-label balance-comment">
说明：2017年1月1日通知客户支付2017年2月1日至2017年7月31日这6个月房租，并要求在30天内付清。这里，出账日=2017年1月1日；账单“覆盖开始日期”=2017年2月1日，账单“覆盖截止日期”=2017年7月31日；支付周期=6个月。
</label>		
	
<br />

<input type="hidden" id="rule-id" >
<input type="hidden" id="bill-period" />
<input type="hidden" id="bill-date" >
A：支付周期：<input type="text" style="width:72px;" class="form-control form-control-contract check-int" id="bill-date-interval"	placeholder="">个月；（如3个月一付，6个月一付，12个月一付）<br />
B：覆盖截止日期“日数”为<select class="form-control form-control-contract check-sel" id="bill-period-day" placeholder=""></select>号；（说明中为31号（2017年7月31日））<br />
C：从覆盖截止日期推算，提前<input style="width:72px;" class="form-control form-control-contract check-int" type="text" id="bill-period-interval" placeholder="">个月出账；（说明中为6=7月-1月，一般情况下C≥A）<br />
D：出账日期的“日数”为<select class="form-control form-control-contract check-sel" id="bill-date-day" placeholder="日期"></select>号；（说明中为1号（2017年1月1日））<br />
E：出账后，客户需在<input style="width:72px;" type="text" class="form-control form-control-contract check-int" id="payment-date" placeholder="">日内缴清。（说明中为30日）<br />
<br />
*出账日期比覆盖开始日期提前了（C-A）月+（B-D）天。比如说明中，出账日期比覆盖开始日期提前了1个月，而比截止日期提前了6个月30天。
		
		
			
		
<!-- 			<div class="form-group"> -->
			
<!-- 				<input type="hidden" class="form-control" id="rule-id" > -->
				
<!-- 				<input type="hidden" class="form-control" id="bill-date" > -->
				
<!-- 				<label class="control-label">下一次收费将结算到最新结算日期</label> -->
				
<!-- 				<input type="text" class="form-control check-int" id="bill-date-interval"	placeholder=""> -->
				
<!-- 				<label class="control-label">个月后的</label> -->
				
				
<!-- 				<select class="form-control check-sel" id="bill-period-day" placeholder=""></select> -->
				
				
<!-- 				<label class="control-label">号,</label> -->
			
<!-- 			</div> -->
			
<!-- 			<br /> -->
<!-- 			<div class="form-group" style="margin-top:10px;"> -->
<!-- 			<label class="control-label hidden">计费周期：</label> -->
				
<!-- 				<input type="hidden" id="bill-period" /> -->
			
<!-- 				<label class="control-label">并在这个日期</label> -->
				
				
<!-- 				<input type="text" class="form-control check-int"	id="bill-period-interval" placeholder=""> -->
				
				
<!-- 				<label class="control-label">个月前的</label> -->
				
<!-- 				<select class="form-control check-sel" id="bill-date-day" placeholder="日期"></select> -->
				
				
<!-- 			<label class="control-label">号通知客户（出帐），</label> -->

<!-- 			</div> -->
			
			
<!-- 			<br /> -->
			
<!-- 			<div class="form-group" style="margin-top:10px;"> -->
			
			
<!-- 				<label class="control-label">通知客户后，客户需在</label> -->
			
<!-- 				&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 				<input type="text" class="form-control check-int"	id="payment-date" placeholder="缴费日"> -->
				
<!-- 			<label class="control-label">日内缴清。</label> -->
				
			
<!-- 			</div> -->
			
			
			
			
		</div>
		
		
		
		
		
		
		
		
			</div>
			
			
			
			
			
			<div class="col-md-12">
			
				<div class="form-group">
				<div class="col-md-offset-1">
		          	<button class="btn btn-primary col-md-2" id="new-rule">新建</button>
		             <button class="col-md-offset-1 btn btn-primary col-md-2" id="save-rule" style="display:none;">保存</button>
		             <button class="col-md-offset-1 btn btn-danger col-md-2" id="remove-rule" style="display:none;">删除</button>
		             <button class="col-md-offset-1 btn btn-warning col-md-2" id="cancel-rule" style="display:none;">取消</button>
		          </div>
				</div>	
			</div>
				
			</div>		
					
<div class="hr-line-dashed"></div>			
					
		<div class="row">
		
		<div class="col-md-12"><h2>服务合同服务项目设置</h2></div>
					
		<div class="col-md-5">
			<div class="form-horizontal">
					
						
				<div class="form-group">
				<div>
					<select  id="service-item-select" class="form-control" multiple style="min-height:150px;"></select>
				</div>
				
				</div>
			
			</div>
			
		</div>
			
		<div class="col-md-7">
			<div class="row">
			<div id="service-item-container" class="form-horizontal" style="display:none;">
		
			<div class="form-group">
			
				<input type="hidden" class="form-control" id="service-item-id" >
				
				<input type="hidden" class="form-control" id="service-item-parkid" >
				
				<label class="control-label col-md-2">名称：</label>
				<div class="col-md-4">									
				<input type="text" class="form-control check-service-item-null" id="service-item-name"	placeholder="名称">
				</div>
								
			</div>
			
			
			<div class="form-group">
			
			
				<label class="control-label col-md-2">描述：</label>
			
			<div class="col-md-4">
				<input type="text" class="form-control"	id="service-item-desc" placeholder="描述">
				</div>
			
			</div>
			
			<div class="form-group">
			
			
				<label class="control-label col-md-2">激活：</label>
			
			<div class="col-md-1">
				<input type="checkbox" class="form-control"	id="service-item-valid" placeholder="激活">
				</div>
			
			</div>
			
			
			
			
		</div>
			</div>
			
			
			
			
			
			
				
			</div>
			
			
			
			<div class="col-md-12">
			
				<div class="form-group">
				<div class="col-md-offset-1">
		          	<button class="btn btn-primary col-md-2" id="new-service-item">新建</button>
		             <button class="col-md-offset-1 btn btn-primary col-md-2" id="save-service-item" style="display:none;">保存</button>
		             <button class="col-md-offset-1 btn btn-danger col-md-2" id="remove-service-item" style="display:none;">删除</button>
		             <button class="col-md-offset-1 btn btn-warning col-md-2" id="cancel-service-item" style="display:none;">取消</button>
		          </div>
				</div>	
			</div>				
			</div>		
					
					
					
					
					
					
					
					
					
					
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/shared/js.jsp" />
<script src="<%=basePath%>/js/jquery.noty.packaged.min.js"></script>
<script src="<%=basePath%>/myjs/my.function.js"></script>
<script type="text/javascript">
function updateThreshold(btn, input) {
	btn = $(btn);
	input = $(input);
	if(input.attr("readonly")=='readonly') {
		input.removeAttr("readonly");
		btn.text("保存");
	}
	else if(input.val() != '') {
		$.get("update.do", {
			key : input.attr("thresholdKey"),
			value : input.val()/1000
		}, function(data) {
			if(data.status == 10001) {
				input.attr("readonly", "readonly");
				btn.text("修改");
			}
			else {
				alert(data.msg);
			}
		});
	}
}


function updateDeadline(btn, input) {
	btn = $(btn);
	input = $(input);
	if(input.attr("readonly")=='readonly') {
		input.removeAttr("readonly");
		btn.text("保存");
	}
	else if(input.val() != '') {
		$.get("update.do", {
			key : input.attr("thresholdKey"),
			value : input.val()
		}, function(data) {
			if(data.status == 10001) {
				input.attr("readonly", "readonly");
				btn.text("修改");
			}
			else {
				alert(data.msg);
			}
		});
	}
}
</script>

<script src="<%=basePath%>/myjs/bill.rule.js"></script>
<script src="<%=basePath%>/myjs/contract.service.item.js"></script>
</body>
</html>