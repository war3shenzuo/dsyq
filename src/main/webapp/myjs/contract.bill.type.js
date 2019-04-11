$(document).ready(function(){
	
	getBillRules();
	
	initBillTypeSelects();	
	
	$('#bill-rule-select').on('click',function(){
		
		var s=$('#bill-rule-select option:selected');
		
		var d=s.attr('value');
		
		$('#bill-date').val(d);
		
		var darr=d.split('-');
		
		$('#bill-date-interval').val(darr[0]);

		$('#bill-date-day').val(darr[1]);
				
		$('#payment-date-1').val(s.attr('paymentDate'));
		
		//$('#bill-period').val(s.attr('billPeriod'));
		
		var p=s.attr('billPeriod');
		
		$('#bill-period').val(p);
		
		var parr=p.split('-');

		$('#bill-period-interval').val(parr[0]);

		$('#bill-period-day').val(parr[1]);
					
		
	})
	
	$('#bill-type-check').on('change',function(){
		
		renderBillType();
		
	})
	
	
	$('#bill-dates-add').on('click',function(){
		
//		var d=$('#bill-dates-to').val();
//		
//		if (d=='') return;
//		
//		d=d.split("-");
//		
//		d=d[1]+'-'+d[2];
		
		var d=$('#bill-dates-month option:selected').val()+'-'+
		
		$('#bill-dates-day option:selected').val();
		
		var oOption=document.createElement('option');
		
		oOption.value=d;
		
		oOption.text=d;
					
		$('#bill-dates-select').append(oOption);
		
		addValToInput($('#bill-dates'),d);
		
	})
	
	$('#bill-dates-remove').on('click',function(){
		
		if($('#bill-dates-select').get(0).selectedIndex < 0) return;
		
		var d=$('#bill-dates-select option:selected').val();
		
		$('#bill-dates-select option:selected').remove();
			
		removeValFromInput($('#bill-dates'),d);
	})
	
	
})


function getBillRules()
{
	$.post(basePath+'/contract/getBillRules.do',{},function(data){
		
		
		
		if(data.status===10001)
		{
//			var u=$('#bill-rule-container');
//			
//			for(var i=0;i<data.data.length;i++)
//			{
//				
//			var li='<div>'+
//            '<label>'+
//                '<input type="radio" value="'+data.data[i].billDate+'" paymentdate="'+data.data[i].paymentDate+'" billperiod="'+data.data[i].billPeriod+'" name="a"> <i></i> 出帐日：'+data.data[i].billDate+'，缴费日：'+data.data[i].paymentDate+'，出帐周期：'+data.data[i].billPeriod+'</label>'+
//                '</div>';
//				
//				
//			//var l='出帐日：'+data.data[i].billDate+'，缴费日：'+data.data[i].paymentDate+'，出帐周期：'+data.data[i].billPeriod
//			
//			u.append(li);
//			}
//			
//			$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})
//			
//			
			for(var i=0;i<data.data.length;i++)
			{
				var d=data.data[i];
				
				var oOption=document.createElement('option');
				
				oOption.value=d.billDate;
				
				var darr=d.billDate.split('-');
				
				var parr=d.billPeriod.split('-');
				
				
				
				oOption.text='结算到最新结算日期'+darr[0]+'个月后的'+parr[1]+'号，并在此日'+parr[0]+'个月前的'+darr[1]+'号通知客户（出帐），客户需在'+d.paymentDate+'日内缴清。';
					
					
					//'出帐日：每'+darr[0]+'个月的'+darr[1]+'日出帐，缴费日：'+d.paymentDate+'，计费周期：出帐日后第'+parr[0]+'个'+parr[1]+'日';
				
				$(oOption).attr('paymentDate',d.paymentDate);
				
				$(oOption).attr('billPeriod',d.billPeriod);
				
				$('#bill-rule-select').append(oOption);
			}
			
		}		
		
	})
}


function renderBillDates()
{
	$('#bill-dates-select').empty();
	
	var ds=$('#bill-dates').val();
	
	if(ds=='') return;
	
	ds=ds.split(',');
	
	for(var i=0;i<ds.length;i++)
	{
		var oOption=document.createElement('option');
		
		oOption.value=ds[i];
		
		oOption.text=ds[i];
					
		$('#bill-dates-select').append(oOption);
		
	}
}

function renderBillDate()
{
	var d=$('#bill-date').val();
	
	if (d=='') return;
	
	d=d.split('-');
	
	if(d.length==2)
	{
		$('#bill-date-interval').val(d[0]);
		
		$('#bill-date-day').val(d[1]);
	}
}

function renderBillPeriod()
{
	var d=$('#bill-period').val();
	
	if (d=='') return;
	
	d=d.split('-');
	
	if(d.length==2)
	{
		if ($('#bill-type-check').prop('checked'))
		{
			$('#bill-period-interval').val(d[0]);
			
			$('#bill-period-day').val(d[1]);
		}
		else
		{
			$('#bill-dates-period-interval').val(d[0]);
			
			$('#bill-dates-period-day').val(d[1]);
			
		}
		
		
		
	}
}

function renderBillType()
{
	if($('#bill-type-check').prop('checked'))
	{
		$('#bill-type-1').show();
	
		$('#bill-type-0').hide();
	}
	else
	{
		$('#bill-type-1').hide();
		
		$('#bill-type-0').show();		
	}
}

function initBillTypeSelects()
{
	createDaySel($('#bill-period-day'));
	
//	createDaySel($('#bill-date-day'));
	createDaySel($('#bill-dates-period-day'));
	
	
	for(var i=1;i<13;i++)
	{
		var oOption=document.createElement('option');
		
		oOption.value=PrefixInteger(i,2);
		
		oOption.text=PrefixInteger(i,2);
		
		$('#bill-dates-month').append(oOption);
	}
	
	for(var i=1;i<32;i++)
	{
		var oOption=document.createElement('option');
		
		oOption.value=PrefixInteger(i,2);
		
		oOption.text=PrefixInteger(i,2);
		
		$('#bill-dates-day').append(oOption);
	}
	
	for(var i=1;i<29;i++)
	{
		var oOption=document.createElement('option');
		
		oOption.value=i;
		
		oOption.text=i;
		
		$('#bill-date-day').append(oOption);
	}
	
}

function createDaySel(obj)
{
	for(var i=1;i<29;i++)
	{
		var oOption=document.createElement('option');
		
		oOption.value=i;
		
		oOption.text=i;
				
		$(obj).append(oOption);
		
		
	}
	
	var oOption=document.createElement('option');
	
	oOption.value=0;
	
	oOption.text='月末';
	
	$(obj).append(oOption);
	
}
