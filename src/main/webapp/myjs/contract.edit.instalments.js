$(document).ready(function(data){
	

	//load table
	
	var itemtable = $('#item-table')
	.DataTable(
			{
				//"order" : [ [ 0, "asc" ] ],// desc
				"paging" : false,
				
				"info" : false,
				"dom" : '<"top">rt<"bottom"ip><"clear">',
				"type" : "post",
				//"responsive": true,
				// "pagingType": "full_numbers",
				"ajax" : {
					"url" : basePath + "/contract/getContractItemList.do",
					"data" : function(d) {
						d.contractId = $('#id').val();
					}
				},
				"serverSide" : "true",
				"processing" : "true",
				"columns" : [ 
				              {data : "startDate",className : "all"},
				              {data : "endDate",className : "all"}, 
//				              {data : "lastFeeDate",className : "desktop"},
				              {data : "totalAmount",className : "desktop"},
				              {data : "id",className : "desktop"}
				              ],
				"columnDefs" : [

				{
					"targets" : 3,
					"orderable" : false,
					"data" : "download_link",
					"render" : function(data, type,	row) {
						var s='';
						if($('#read-only').val()=='false'
							&& checkContractStatusEditable($('#contract-status-sele option:selected').val()) ) 
						{
						s='<a class="btn btn-sm btn-info" href="javascript:void(0)" onClick=javascript:edititem("'
								+ data
								+ '",1)>编辑</a> | '
								+

								'<a class="btn btn-sm btn-danger con-save-btn" href="javascript:void(0)" onClick=javascript:removeitem("'
								+ data + '")>删除</a>';
						
						}
						else
							{
							s='<a class="btn btn-sm btn-primary" href="javascript:void(0)" onClick=javascript:edititem("'
								+ data
								+ '",0)>查看</a> '
							
							}
						
						return s;
					}
				},
				{
					"targets" : 2,
					"orderable" : false,
					"data" : "download_link",
					"render" : function(data, type,	row) {
					
						return (data*1).toFixed(2);
					}
					
				}
				
				
				]
			// end of columnDefs

			}).on('preXhr.dt',function(e, settings, data) {

			}).on('xhr.dt',function(e, settings, data) {

				

					
				
				
			}).on('draw.dt', function() {
				
//				if($('#contract-status-sele option:selected').val()==1)
//				{
//											
//					$('#item-table .con-save-btn').attr('disabled','disabled');
//					
//				}
				
				checkInstalments();
				
				//renderInstalmentsView();
				
				
			}).on('processing.dt',function(e, settings, processing) {

			}).on('error.dt',function(e, settings, techNote, message) {

			});// end of dataTable



		$('#item-table tbody').on('click', 'tr', function() {
		
			$('#item-table tr.selected').removeClass('selected');
			
			$(this).addClass('selected');
		
		});

	
//		$('#bill-rule-container').on('click','input',function(){
//			
//			//myNoty($(this).attr('value'),10001);
//			
//			$('#bill-date').val($(this).attr('value'));
//			
//			$('.payment-date').val($(this).attr('paymentdate'));
//			
//			$('#bill-period').val($(this).attr('billperiod'));
//			
//			//$('#bill-rule-container input[checked="true"]');
//		})
		
		

		
//		$('#bill-radio').on('click','input',function(){
//			
//			var v=$('#bill-radio input[name="bill-type"]:checked ').val();
//			
//			if(v=='0')
//			{
//				$('#bill-type-0').show();
//				
//				$('#bill-type-1').hide();
//			}
//			
//			if(v=='1')
//			{
//				$('#bill-type-1').show();
//				
//				$('#bill-type-0').hide();
//			}
//			
//			
//		})
//		
		
	
		
//	
//		$('#service-item-container').on('click','input',function(){
//			
//			$('#content').val($(this).attr('value'));
//			
//		})
		
		
		$('#service-item-select').on('click',function(){
			
			var s=$('#service-item-select option:selected');
			
			$('#content').val(s.attr('value'));
		

			
		})
		
		
	
	$('#save-item').on('click',function(){
		
		$.noty.closeAll();
		

		if($('#id').val()=='')
		{
			myNoty('请先保存基本信息。',10002);
			
			return;
		}
		
		if(!myNotyRegx($('.check-item-date'),2))
		{
			return;
		}
		
		if(!myNotyRegx($('.check-item-float'),1))
		{
			return;
		}
		
		if(!myNotyRegx($('.check-item-int'),0))
		{
			return;
		}
		if(!myNotyRegx($('.check-item-null'),3))
		{
			return;
		}
		
		if($('#bill-type-check').prop('checked'))
		{
			if(!myNotyRegx($('#bill-date-interval'),0))
			{
				$('#bill-date-interval').focus();
				
				return;
			}
			
			if(!myNotyRegx($('#bill-date-day'),0))
			{
				$('#bill-date-day').focus();
				
				return;
			}		
			
			if(parseInt($.trim($('#bill-date-interval').val()))==0)
			{
				myNoty('间隔月不能等于0',10002);
				
				return;
				
			}
			
			$('#bill-date').val($('#bill-date-interval').val()+'-'+$('#bill-date-day option:selected').val());
			
		}
		else
		{
			if(!myNotyNull('请输入出帐日',$('#bill-dates')))
			{
				$('#bill-dates-to').focus();
				
				return;
			}
		}
		
//		if(!myNotyRegx($('.check-bill-int'),0))
//		{
//			return;
//		}
		
		if($('#bill-type-check').prop('checked'))
		{
			if(!myNotyRegx($('payment-date-1'),0))
			{
				return;
			}	
		}
		else
		{
			if(!myNotyRegx($('payment-date-0'),0))
			{
				return;
			}
		}
		
		
		
		if(!myNotySele($('#bill-period')))
		{
			return;
		}
		
		//TODO:前端控件控制，冗余检测
				
		if(checkItemDate())
		{
			return;
		}
		
		
		dd=DateDiff($('#contract-start-date').val(),$('#item-start-date').val());
		
		if(dd<0)
		{
			myNoty('开始日期不能小于合同开始日期',10002);
			$('#item-start-date').focus();
			return;
		}
		
		
		dd=DateDiff($('#item-end-date').val(),$('#contract-end-date').val());
		
		if(dd<0)
		{
			myNoty('结束日期不能大于合同结束日期',10002);
			$('#item-end-date').focus();
			return;
		}
		
		
		
		if(checkItemDateConflict())
		{
			myNoty('分期起止日期与列表中有冲突，请修改。',10002);
					
			return;
		}
		
		//还是要检测三个data
		
		if($.trim($('#item-daily-price').attr('data'))=='')
		{
			myNoty('请输入日单价',10002);
			
			$('#item-daily-price').focus();
			
			return;
			
		}
		
		if($.trim($('#item-monthly-price').attr('data'))=='')
		{
			myNoty('请输入月单价',10002);
			
			$('#item-monthly-price').focus();
			
			return;
			
		}
		
		if($.trim($('#item-amount').attr('data'))=='')
		{
			myNoty('请输入总价',10002);
			
			$('#item-amount').focus();
			
			return;
			
		}
		
		
		saveitem();
		
		
	})
	
	$('#new-item').on('click',function(){
		
		resetItemForm();
		
		//if it is first, set start date from contract
		//or set last item date from table
		var tr=$('#item-table tbody tr');
		
		var lastitemdate=$(tr[tr.length-1]).children('td').eq(1).html();
		
		if(lastitemdate==null || lastitemdate=='')
		{
			$('#item-start-date').val($('#contract-start-date').val());
		}
		else
		{
			$('#item-start-date').val(addDate(lastitemdate,1));
		}
		
//		if($('#item-table a').length<1)
//		{
//			$('#item-start-date').val($('#contract-start-date').val());
//		}
		
		$('#content').val('');
		
		//服务、外包合同不需要面积
		var ccategory=$('#contract-category option:selected').val();
		
		if(ccategory!=contractCategory['sub'][0].id && ccategory!=contractCategory['service'][0].id)
		{
		
			$('#content').val($('#build-area').val());
		}
				
		//$('#item-start-date').removeAttr('disabled');
		$('#item-start-date').attr('disabled','disabled');
		
		$('#item-end-date').removeAttr('disabled');
		
		//togglebtn();
		
		
		
		//$('#item-start-date').focus();
		
		$('#cancel-item').show();
		
		renderInstalmentsView();
		
		//TODO:get bill rule;
		
		getBillRule();
		
		
	})
	
	
	$('#cancel-item').on('click',function(){
		
		//$('#save-item').show();
		
		//togglebtn();.
		
		$('#cancel-item').hide();
		
		renderInstalmentsView();
		
	})
	
	
	$('#item-start-date').on('change',function(){
		
		//TODO:当天日期>合同开始日期
		var d=new Date();
		
		//var today=d.
		
		var dd=DateDiff(formatDate(d),$('#item-start-date').val());
		//TODO:
		//当天也不需要显示
//		if(dd>=0)
//		{
//			$('#last-fee-date-container').show();
//		}
//		else
//		{
//			$('#last-fee-date-container').hide();
//		}
		
		
		
		
		var ds=$('#item-start-date').val();
		
		var de=$('#item-end-date').val();
		
		if(ds=='' || de=='')
		{	
			return;
		}
		
		setByDailyPrice();
		
	})
	//TODO:触发四次
//	$('#item-end-date').on('change',function(){
//
//		var ds=$('#item-start-date').val();
//		
//		var de=$('#item-end-date').val();
//		
//		if(ds=='' || de=='')
//		{	
//			return;
//		}
//		
//		setByDailyPrice();
//	})
	//TODO:BUG FIRED TWICE	
	$('#item-end-date').datepicker()
    .on('changeDate', function(e) {


    	var ds=$('#item-start-date').val();
		
		var de=$('#item-end-date').val();
		
		if(ds=='' || de=='')
		{	
			return;
		}
		
		setByDailyPrice();
    	
    	
    });
	
	
	
	$('#item-balance-monthly').on('change',function(){
		
//		if(this.prop('checked'))
//		{
//			$('#item-amount').attr('readonly','readonly');
//		}
//		else
//		{
//			$('#item-amount').removeAttr('readonly');
//		}
		
		renderInstalmentsView();
		
		setAmount();
	})
	
	
	$('#item-monthly-price').on('blur',function(){
	
		$.noty.closeAll();
		
		var m=$('#item-monthly-price').val();
		
		if(m=='')
		{
			return;
		}
		
		$('#item-monthly-price').val((m*1).toFixed(2));
		
		//月结时不用自动重算日单价
		if($('#item-balance-monthly').val()==0)
		{
			//日单价
			var dp=m*12/365;
			
			$('#item-daily-price').attr('data',dp);
			
			$('#item-daily-price').val(dp.toFixed(2));
		}
		
		setAmount();
		
	})
	
	
	
	
	$('#item-amount').on('blur',function(){
		
		$.noty.closeAll();
		
		var a=$('#item-amount').val();
		
		if(a=='')
		{
			return;
		}
		
		var area=$('#content').val();
		
		//外包与服务不需要与面积关联
		var ccategory=$('#contract-category option:selected').val();
		
		if(ccategory==contractCategory['sub'][0].id || ccategory==contractCategory['service'][0].id)
		{
			area=1;
		}
				
		var ds=$('#item-start-date').val();
		
		var de=$('#item-end-date').val();
		
		if(ds=='' || de=='')
		{
			myNoty('先输入起止日期。',10002);
			
			return;
		}
		
		var dd=DateDiff(ds,de);
		//TODO:前端控件控制，冗余检测
		
		if(checkItemDate())
		{
			return;
		}
		
		dd=dd+1;
		
		var dp=(a/dd/area);
		
		$('#item-daily-price').attr('data',dp);
		
		$('#item-daily-price').val(dp.toFixed(2));
		
		var m=dp*365/12;
		
		$('#item-monthly-price').val(m.toFixed(2));
		
		$('#item-monthly-price').attr('data',m);
		
		$('#item-amount').val((a*1).toFixed(2));
	})
	
	
	$('#item-monthly-price').on('change',function(){
		
		
		$('#item-monthly-price').attr('data',$('#item-monthly-price').val());
		
	})
	
	$('#item-daily-price').on('change',function(){
		
		
		$('#item-daily-price').attr('data',$('#item-daily-price').val());
		
	})
	
	$('#item-amount').on('change',function(){
		
		$('#item-amount').attr('data',$('#item-amount').val());
		
	})
	
	
	$('#item-daily-price').on('blur',function(){
		
		//setByDailyPrice();
		
		$.noty.closeAll();
		
		var d=$('#item-daily-price').val();
		
		if(d=='')
		{
			return;
		}
		
		
		var dp=$('#item-daily-price').attr('data');
				
		$('#item-daily-price').val((dp*1).toFixed(2));
		
		//1227月结时不用自动重算
		if($('#item-balance-monthly').val()==0)
		{
			var m=dp*365/12;
			
			$('#item-monthly-price').attr('data',m);
			
			$('#item-monthly-price').val(m.toFixed(2));
		}
		
		setAmount();
		
	})
	
})



function saveitem()
{
	$.myPost(basePath+'/contract/saveitem.do',{
		
		id:$('#item-id').val(),
		refContractId:$('#id').val(),
		startDate:$('#item-start-date').val(),
		endDate:$('#item-end-date').val(),
		balanceMonthly:$('#item-balance-monthly').val(),
		dailyUnitPrice:$('#item-daily-price').attr('data'),
			//$('#item-daily-price').val(),
		monthlyUnitPrice:$('#item-monthly-price').attr('data'),
		totalAmount:$('#item-amount').attr('data'),
		content:$.trim($('#content').val()),
		billType:$('#bill-type-check').prop('checked')?1:0,
		billDate:$.trim($('#bill-date-interval').val())+'-'+$.trim($('#bill-date-day').val()),	
		billDates:$('#bill-dates').val(),	
		paymentDate:$('#bill-type-check').prop('checked')?$.trim($('#payment-date-1').val()):$.trim($('#payment-date-0').val()),
		billPeriod:getBillPeriod()
			
			
		//lastFeeDate:$('#last-fee-date').val()
		
	},function(data){
		
		myNoty(data.msg,data.status);
		
		if(data.status===10001)
		{
			$('#item-id').val(data.data);
			
			$('#item-table').DataTable().ajax.reload();
			
			//togglebtn();
			
			$('#cancel-item').hide();
			
			renderInstalmentsView();
			
		}
		
	})
}
function edititem(id,action)
{
	$.myPost(basePath+'/contract/getContractItem.do',{id:id},function(data){
		
		if(data.status===10001)
		{	
			resetItemForm();
			
			if($('#item-editor').is(':hidden'))
			{
				//togglebtn();
				
			}
			
			$('#cancel-item').show();
			
			
			
			var d=data.data;
			
			$('#item-id').val(d.id);
			
			$('#item-start-date').val(formatDate(d.startDate));
					
			$('#item-end-date').val(formatDate(d.endDate));
			
			$('#item-balance-monthly').val(d.balanceMonthly);
			
			$('#item-daily-price').attr('data',d.dailyUnitPrice);
			
			$('#item-daily-price').val(d.dailyUnitPrice.toFixed(2));
						
			$('#item-monthly-price').attr('data',d.monthlyUnitPrice);
			
			$('#item-monthly-price').val(d.monthlyUnitPrice.toFixed(2));
						
			$('#item-amount').val(d.totalAmount.toFixed(2));
			
			$('#item-amount').attr('data',d.totalAmount);
			
			$('#content').val(d.content);
			$('#bill-type-check').prop('checked',d.billType==1);					
			
			$('#bill-dates').val(d.billDates);
			$('#bill-date').val(d.billDate);
			
			if(d.billType==1)
			{
				$('#payment-date-1').val(d.paymentDate);
			}else
			{
				$('#payment-date-0').val(d.paymentDate);
			}
			$('#bill-period').val(d.billPeriod);
			$('#last-fee-date').val(d.lastFeeDate);						
//			$('#item-start-date').attr('disabled','disabled');
//			
//			$('#item-end-date').attr('disabled','disabled');
			
			$('#item-start-date').removeAttr('disabled');
			
			renderBillType();
			
			renderBillDates();
			
			renderBillDate();
			
			renderBillPeriod();
			
			renderInstalmentsView();
			
//			//view
//			if(action==0)
//			{
//				$('#save-item').hide();
//			}
//			//edit
//			else
//			{
//				$('#save-item').show();
//			}
			
			
			
		}
		
		
		
	})
}


function removeitem(id)
{
	//大于0的状态，不能删
	if(checkContractStatusEditable($('#contract-status-sele option:selected').val())==false)
	{								
		return;		
	}
	
	
	swal({
        title: '删除此项，确定？',
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: true
        }, function () {
        	$.myPost(basePath+'/contract/deleteitem.do',{id:id},function(data){
        		
        		myNoty(data.msg,data.status);
        		
        		if(data.status===10001)
        		{	
        			$('#item-table').DataTable().ajax.reload();
        			
        			if(!$('#item-editor').is(':hidden'))
        			{
        				resetItemForm();
        				
        				//togglebtn();
        				
        				$('#cancel-item').hide();
        				
        				renderInstalmentsView();
        			}
        		}
        	})
        }
    );
	
	//var c=confirm();
	
	//mySwalConfirm('删除此项，确定？',removeItemFunc(id));
}

function removeItemFunc(id)
{
	$.post(basePath+'/contract/deleteitem.do',{id:id},function(data){
		
		myNoty(data.msg,data.status);
		
		if(data.status===10001)
		{	
			$('#item-table').DataTable().ajax.reload();
		}
	})
}


function resetItemForm()
{
	$('#item-id').val('');
	
	$('#item-start-date').val('');
			
	$('#item-end-date').val('');
	$('#item-daily-price').val('');
	$('#item-monthly-price').val('');
	$('#item-amount').val('');
	
	$('#item-daily-price').attr('data','');
	$('#item-monthly-price').attr('data','');
	$('#item-amount').attr('data','');
	
	
	$('#content').val('');	
	
	
	$('#bill-type-check').prop('checked',true);
	
	renderBillType();
	
	$('#bill-date').val('');
	$('#bill-date-interval').val('');
	$('#bill-dates').val('');
	$('#bill-dates-select').empty();
	$('#payment-date-1').val('');
	$('#payment-date-0').val('');
	$('#bill-period').val('');
	$('#bill-period-interval').val('');
	
	$('#last-fee-date').val('');
	

}
//修改需要排除，与列表中各时间段对比，start,end均不能属于任何一段
function checkItemDateConflict()
{
	var result=false;
	
	var s = $('#item-start-date').val();
			
	var e = $('#item-end-date').val();
	
	var tr=$('#item-table tbody tr');
	
	for(var i=0;i<tr.length;i++)
	{
		var ds=$(tr[i]).children('td').eq(0).html();
		
		var de=$(tr[i]).children('td').eq(1).html();
		
		var did=$(tr[i]).children('td').eq(3).html();
		
		//no data
		if(did=='')
		{
			continue;
		}
		
		var iid=$('#item-id').val();
		
		if(iid!='' && did.indexOf(iid)>=0)
		{
			continue;
		}
		
		
		if(DateDiff(ds,s)>=0 && DateDiff(s,de)>=0)
		{
			result=true;
			$('#item-table tr.selected').removeClass('selected');
			$(tr[i]).addClass('selected');
			break;
		}
		if(DateDiff(ds,e)>=0 && DateDiff(e,de)>=0)
		{
			result=true;
			$('#item-table tr.selected').removeClass('selected');
			$(tr[i]).addClass('selected');
			break;
		}
		
	}
	
	return result;
}


function checkItemDate()
{
	var dd=DateDiff($('#item-start-date').val(),$('#item-end-date').val());
	
	if(dd<0)
	{
		myNoty('开始日期不能大于结束日期',10002);
		
		return true;
	}
	
	return false;
}

//function togglebtn()
//{
//	$('#item-editor').toggle();
//	
//	$('#new-item').toggle();
//	
//	$('#save-item').toggle();
//	
//	$('#cancel-item').toggle();	
//}

function renderInstalmentsView()
{
	//render service item
	$('#service-select').hide();
	
	$('#label-content').show();
	
	//$('#content').val($('#build-area').val());
		
	$('#content').attr('disabled','disabled');

	
	if($('#contract-category option:selected').val()==contractCategory['service'][0].id)
	{
		$('#service-select').show();
		
		getServiceItems();
		
		$('#label-content').hide();
	}
	
	if($('#contract-category option:selected').val() ==contractCategory['sub'][0].id)
	{
		$('#service-select').hide();
		
		$('#label-content').html('外包内容');
		
		$('#label-content').show();
		
		$('#content').removeAttr('disabled');
		
	}	
	
	//审批为初始，且分期完成，可以提交申请
	if($('#contract-status-sele option:selected').val()==contractStatus['init'][0].id 
			&& $('#instalment-status').val()==instalmentStatus['finished'][0].id)
	{
		$('#contract-submit-audit').show();
		
	}
	else
	{
		$('#contract-submit-audit').hide();
		
	}
	
	//render btn
	if($('#read-only').val()=='false'
		&& checkContractStatusEditable($('#contract-status-sele option:selected').val()) ) 
	{
		if($('#cancel-item').is(':hidden'))
		{
			//当分期状态为已全部录入进，不需要新建
			if($('#instalment-status').val()!=instalmentStatus['finished'][0].id)
			{
				$('#new-item').show();
			}
			else
			{
				$('#new-item').hide();
			}
			
			
			$('#save-item').hide();
			
			$('#item-editor').hide();
			
		}else
		{
			$('#save-item').show();
			
			$('#new-item').hide();
			
			$('#item-editor').show();
		}
		
		
		
		
	}
	else
	{
		$('#save-item').hide();
		
		$('#new-item').hide();
	}
	
	
	if($('#cancel-item').is(':hidden'))
	{
		
		$('#item-editor').hide();
		
	}else
	{
		
		$('#item-editor').show();
	}
	
	//比较开始日期，若是第一期，显示bill rule
	
	if($('#contract-start-date').val()==$('#item-start-date').val()
	&& 	!$('#cancel-item').is(':hidden')	
	)
	{
		$('#bill-rule-ibox').show();
	}
	else
	{
		$('#bill-rule-ibox').hide();
	}
	
	if($('#item-balance-monthly').val()==1)
	{
		$('#item-amount').attr('readonly','readonly');
	}
	else
	{
		$('#item-amount').removeAttr('readonly');
	}
	

}

//计算总价，需按月按日计算
function setAmount()
{
	
	var ds=$('#item-start-date').val();
	
	var de=$('#item-end-date').val();
	
	if(ds=='' || de=='')
	{
		myNoty('先输入起止日期。',10002);
		
		return;
	}
	
	var dd=DateDiff(ds,de);
	//TODO:前端控件控制，冗余检测
	
	if(checkItemDate())
	{
		return;
	}
	
	dd=dd+1;
	
	
	//按日，不变
	
	var dp=$('#item-daily-price').attr('data');
	
	var mp=$('#item-monthly-price').attr('data');
	
	if(dp=='')
	{
		$('#item-daily-price').attr('data',0);
		
		$('#item-daily-price').val(0);
		
		dp=0;
	}
	if(mp=='')
	{
		$('#item-monthly-price').attr('data',0);
		
		$('#item-monthly-price').val(0);
		
		mp=0;
	}
	
//	if(dp*1==0 || mp*1==0)
//	{
//		myNoty('请输入日单价或月单价',10002);
//		
//		return;
//	}
	
	
	var area=$('#content').val();
	
	var ccategory=$('#contract-category option:selected').val();
	
	if(ccategory==contractCategory['sub'][0].id || ccategory==contractCategory['service'][0].id)
	{
		area=1;
	}
	
	var a=0;
	
	if($('#item-balance-monthly').val()==1)
	{
		$.post(basePath+'/contract/getCalendarDiff.do',{dateStart:ds,dateEnd:de},function(data){
			
			if(data.status==10001)
			{
				//TODO:test
				myNoty(data.data.months+'-'+data.data.days,10001);
				
				a=data.data.months*area*mp+data.data.days*area*dp;
			}
			else
			{
				myNoty('月份日期计算有误，请联系管理员',10002);
				
				a=0;
			}
			
			$('#item-amount').attr('data',a);
			
			$('#item-amount').val(a.toFixed(2));
			
			
			
		})
	}
	else
	{
		a=dd*dp*area;
		
		$('#item-amount').attr('data',a);
		
		$('#item-amount').val(a.toFixed(2));
		
	}
	
	

}

function setByDailyPrice()
{
	$.noty.closeAll();
	
//	if($('#item-balance-monthly').val()==0)
//	{
//		var dp=$('#item-daily-price').attr('data');//.val();
//		
//		if(dp=='')
//		{
//			return;
//		}	
//		
//		var m=dp*365/12;
//		
//		$('#item-monthly-price').attr('data',m);
//		
//		$('#item-monthly-price').val(m.toFixed(2));
//	}
	setAmount();

}
function checkInstalments()
{
	var info='';
	
	var css='';
	
	var status=instalmentStatus['none'][0].id;
	
	var tr=$('#item-table tbody tr');
	
	var lastitemdate=$(tr[tr.length-1]).children('td').eq(1).html();
	
	var firstitemdate=$(tr[0]).children('td').eq(0).html();
	
	if(lastitemdate==null || lastitemdate=='')
	{
		info=instalmentStatus['none'][0].name;
		
		css='btn-danger';
	}
	else 
	{
		var partial=false;
		//第一个分期开始
		if(firstitemdate!=$('#contract-start-date').val())
		{
			partial=true;
		}
		//最后一个分期结束
		if(lastitemdate!=$('#contract-end-date').val())
		{
			partial=true;
		}
		
		if(partial==false)
		{
			//分期结束+1＝下一个分期开始
			for(var i=0;i<tr.length;i++)
			{
				var te=$(tr[i]).children('td').eq(1).html();
				
				var ns=$(tr[i+1]).children('td').eq(0).html();
				
				if(te!=null && ns!=null && te!='' && ns!='')
				{
					if(addDate(te,1)!=ns)
					{
						partial=true;
						
						break;
					}
				}
				
			}
		}
		
		
		
//		lastitemdate==$('#contract-end-date').val()
		
		
		if(partial==false)
		{
			info=instalmentStatus['finished'][0].name;
			
			css='btn-success';
			
			status=instalmentStatus['finished'][0].id;
			
			$('#new-item').hide();
		}
		else
		{
			info=instalmentStatus['partial'][0].name;
			
			css='btn-warning';
			
			status=instalmentStatus['partial'][0].id;
		}
	}
	
	$('#instalment-info').html(info);

	$('#instalment-info').removeClass();
	
	$('#instalment-info').addClass(css);
	
	$('#instalment-status').val(status);
	
	
	renderInstalmentsView();
	
	
}

function getBillPeriod()
{
	
	if ($('#bill-type-check').prop('checked'))
	{
		return $.trim($('#bill-period-interval').val())+'-'+$.trim($('#bill-period-day').val());
	}
	else
	{
		return $.trim($('#bill-dates-period-interval').val())+'-'+$.trim($('#bill-dates-period-day').val());
	}
	
}

function getBillRule()
{

	$.post(basePath+'/contract/getItemBillRule.do',{refContractId:$('#id').val()},function(data){
		
		if(data.status===10001)
		{
			var d=data.data;
			//render
			$('#bill-type-check').prop('checked',d.billType==1);					
			
			$('#bill-dates').val(d.billDates);
			$('#bill-date').val(d.billDate);
			
			$('#bill-type-check').prop('checked')?$('#payment-date-1').val(d.paymentDate):$('#payment-date-0').val(d.paymentDate),
			
			$('#bill-period').val(d.billPeriod);			
			
			renderBillType();
			
			renderBillDates();
			
			renderBillDate();
			
			renderBillPeriod();
			
		}
		
		
	})
	

}