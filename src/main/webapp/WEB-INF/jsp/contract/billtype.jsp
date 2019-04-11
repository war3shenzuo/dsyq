<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>


<div class="form-group" id="bill-radio">
	<div class="col-md-12">
		<input type="checkbox" id="bill-type-check">使用统一规则
		<!-- <input type="radio" value="0" id="bill-radio-0" name="bill-type">自定义出帐 -->
		<!-- <input type="radio" value="1" id="bill-radio-1" name="bill-type">统一规则 -->
	</div>
</div>

<hr />

<div class="form-inline" id="bill-type-1" style="display: none;">


<input type="button" class="btn btn-primary" id="use-unified-role"
			data-toggle="modal" data-target="#billRuleModal" value="统一规则">
			
<label class="control-label balance-comment">说明：2017年1月1日通知客户支付2017年2月1日至2017年7月31日这6个月房租，并要求在30天内付清。这里，出账日=2017年1月1日；账单“覆盖开始日期”=2017年2月1日，账单“覆盖截止日期”=2017年7月31日；支付周期=6个月。
	</label>		
	
			

<br />


<input type="hidden" id="bill-period" />
<input type="hidden" id="bill-date" >
A：支付周期：<input type="text" style="width:72px;" class="form-control form-control-contract" id="bill-date-interval"	placeholder="">个月；（如3个月一付，6个月一付，12个月一付）<br />
B：覆盖截止日期“日数”为<select class="form-control form-control-contract" id="bill-period-day" placeholder=""></select>号；（说明中为31号（2017年7月31日））<br />
C：从覆盖截止日期推算，提前<input style="width:72px;" class="form-control form-control-contract" type="text" id="bill-period-interval" placeholder="">个月出账；（说明中为6=7月-1月，一般情况下C≥A）<br />
D：出账日期的“日数”为<select class="form-control form-control-contract" id="bill-date-day" placeholder="日期"></select>号；（说明中为1号（2017年1月1日））<br />
E：出账后，客户需在<input style="width:72px;" type="text" class="form-control form-control-contract" id="payment-date-1" placeholder="">日内缴清。（说明中为30日）<br />
<br />
*出账日期比覆盖开始日期提前了（C-A）月+（B-D）天。比如说明中，出账日期比覆盖开始日期提前了1个月，而比截止日期提前了6个月30天。





<!-- 	<label class="control-label">下次收费覆盖截止日期为：<span class="label-last-balance-date">最新结算日期</span></label> -->
	
<!-- 	<input type="hidden" id="bill-period" />	 -->

<!-- 		<input type="text" class="form-control" id="bill-date-interval"	placeholder=""> -->
		

	
<!-- 	<label class="control-label">个月后的</label> -->
	

<!-- 	<select class="form-control" id="bill-period-day" placeholder=""> -->
	
<!-- 	</select> -->


<!-- <label class="control-label">号，</label> -->
<!-- <label class="control-label hidden">计费周期：</label> -->
<!-- 	<p></p> -->
<!-- 	<input type="hidden" id="bill-date" > -->
<!-- 	<label class="control-label">并在这个覆盖截止日期</label> -->

<!-- 	<input class="form-control" type="text" id="bill-period-interval" placeholder=""> -->

<!-- 	<label class="control-label">个月前的</label> -->

	
<!-- 		<select class="form-control" id="bill-date-day" placeholder="日期"></select> -->

<!-- 	<label class="control-label">号通知客户（出帐），</label> -->

</div>


<div class="form-inline" id="bill-type-0">


<p class="balance-comment">
说明：2017年1月1日通知客户支付2017年2月1日至2017年7月31日这6个月房租，并要求在30天内付清。这里，出账日=2017年1月1日；账单“覆盖开始日期”=2017年2月1日，账单“覆盖截止日期”=2017年7月31日；支付周期=6个月。

	</p>

<input type="hidden" id="bill-dates"> 
		
A：每年出账的月份+日期为：

月份：
<select class="form-control" id="bill-dates-month"></select>
日期：
<select class="form-control" id="bill-dates-day"></select>
<input type="button" class="btn" id="bill-dates-add" value="添加">
<input type="button" class="btn" id="bill-dates-remove" value="删除">	

（说明中为1月1日）<br />
<select class="form-control" style="width:120px;" multiple id="bill-dates-select"></select>
<br />

B：覆盖截止日期的月份-出账日期的月份=<input style="width:72px;" class="form-control form-control-contract" type="text" id="bill-dates-period-interval" placeholder="">个月（说明中为6个月=7月-1月（2017年7月31日，2017年1月1日））<br />
C：覆盖截止日期“日数”为<select class="form-control form-control-contract" id="bill-dates-period-day" placeholder="日期"></select>号；（说明中为31号（2017年7月31日））<br />
D：出账后，客户需在<input style="width:72px;" type="text" class="form-control form-control-contract" id="payment-date-0" placeholder="">日内缴清。（说明中为30日）<br />


<!-- 	<label class="control-label">自定义结算日期：</label> -->

<!-- 	<label class="control-label">每年通知客户缴费的日期（出帐日）</label> -->

<!-- 	<label class="control-label">月份：</label> -->


<!-- 		<select class="form-control" id="bill-dates-month"></select> -->


<!-- 	<label class="control-label">日期：</label> -->

	
<!-- 		<select class="form-control" id="bill-dates-day"></select> -->
	

<!-- 	<div class=""> -->
<!-- 		<input type="button" class="btn" id="bill-dates-add" value="添加"> -->
<!-- 		<input type="button" class="btn" id="bill-dates-remove" value="删除"> -->
<!-- 	</div> -->

	
<!-- 		<input type="hidden" id="bill-dates">  -->
<!-- 		<select	class="form-control"  style="width:100px;" multiple id="bill-dates-select"></select> -->
	
<!-- 	<br /> -->
<!-- 	<label class="control-label">本次通知将结算至通知日后</label> -->
<!-- 	<input class="form-control" type="text" id="bill-dates-period-interval" placeholder=""> -->
<!-- 	<label class="control-label">个月的</label> -->
<!-- 	<select class="form-control" id="bill-dates-period-day" placeholder="日期"></select> -->
<!-- 	<label class="control-label">号。</label> -->

</div>






<!-- <div class="form-group" style="margin-top:10px;"> -->


<!-- 	<label class="control-label">通知客户后，客户需在</label> -->


<!-- 		<input type="text" class="form-control check-bill-int"	id="payment-date" placeholder="缴费日"> -->

<!-- <label class="control-label">日内缴清。</label> -->
	

<!-- </div> -->


<script src="<%=basePath%>/myjs/contract.bill.type.js"></script>