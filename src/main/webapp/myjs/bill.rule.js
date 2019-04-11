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
		
		$('#payment-date').val(s.attr('paymentDate'));
		
		var p=s.attr('billPeriod');
		
		$('#bill-period').val(p);
		
		var parr=p.split('-');

		$('#bill-period-interval').val(parr[0]);

		$('#bill-period-day').val(parr[1]);
		
		$('#rule-id').val(s.attr('ruleId'));
					
		btnEdit();
		
		
	})
	
	$('#cancel-rule').on('click',function(){
		btnCancel();
		
	})
	
	$('#save-rule').on('click',function(){
		
		$.noty.closeAll();
		
		//check
		
		if(!myNotyRegx($('.check-int'),0))
		{
			return;
		}
		if(!myNotySele($('.check-sel')))
		{
			return;
		}
		
		
		saveRule();
	})
	
	$('#remove-rule').on('click',function(){
		
		$.noty.closeAll();
		
		//check
		if(!myNotyNull('请选择规则',$('#rule-id')))
		{
			return;
		}
		
		removeRule();
	})
	
	$('#new-rule').on('click',function(){
		
		resetForm();
				
		btnNew();
	})
	
	
	
})


function getBillRules()
{
	$.post(basePath+'/contract/getBillRules.do',{},function(data){
		
		
		
		if(data.status===10001)
		{
	
			for(var i=0;i<data.data.length;i++)
			{
				var d=data.data[i];
				
				var oOption=document.createElement('option');
				
				oOption.value=d.billDate;
				
				var darr=d.billDate.split('-');
				
				var parr=d.billPeriod.split('-');
				
				
				
				oOption.text='结算到最新结算日期'+darr[0]+'个月后的'+parr[1]+'号，并在此日'+parr[0]+'个月前的'+darr[1]+'号通知客户（出帐），客户需在'+d.paymentDate+'日内缴清。';
					
					
					//'出帐日：每'+darr[0]+'月的'+darr[1]+'日出帐，缴费日：'+d.paymentDate+'，计费周期：出帐日后第'+parr[0]+'个'+parr[1]+'日';
				
				$(oOption).attr('paymentDate',d.paymentDate);
				
				$(oOption).attr('billPeriod',d.billPeriod);
				
				$(oOption).attr('ruleId',d.id);
							
				$('#bill-rule-select').append(oOption);
			}
			
		}		
		
	})
}

//function addRule(data)
//{
//	var oOption=document.createElement('option');
//	
//	oOption.value=data.billDate;
//	
//	oOption.text='出帐日：'+data.billDate+'，缴费日：'+data.paymentDate+'，计费周期：'+data.billPeriod;
//	
//	$(oOption).attr('paymentDate',data.paymentDate);
//	
//	$(oOption).attr('billPeriod',data.billPeriod);
//				
//	$('#bill-rule-select').append(oOption);
//
//}


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


function initBillTypeSelects()
{
	createDaySel($('#bill-period-day'));
		
	

	var oOption=document.createElement('option');
		
		oOption.value='';
		
		oOption.text='请选择';
		
		$('#bill-date-day').append(oOption);
	
	for(var i=1;i<32;i++)
	{
		var oOption=document.createElement('option');
		
		oOption.value=i;
		
		oOption.text=i;
		
		$('#bill-date-day').append(oOption);
	}
	
}

function createDaySel(obj)
{
	
	var oOption=document.createElement('option');
	
	oOption.value='';
	
	oOption.text='请选择';
	
	$(obj).append(oOption);
	
	for(var i=1;i<29;i++)
	{
		var oOption=document.createElement('option');
		
		oOption.value=i;
		
		oOption.text=i;
				
		$(obj).append(oOption);
		
		
	}
	
	oOption=document.createElement('option');
	
	oOption.value=0;
	
	oOption.text='月末';
	
	$(obj).append(oOption);
	
}

function btnToggle()
{
	$('#new-rule').toggle();
	
	$('#save-rule').toggle();
	
	$('#cancel-rule').toggle();

}

function btnEdit()
{
	$('#new-rule').hide();
	
	$('#save-rule').show();
	
	$('#remove-rule').show();
	
	$('#cancel-rule').show();
	
	$('#bill-rule-container').show();

}
function btnCancel()
{
	$('#new-rule').show();
	
	$('#save-rule').hide();
	
	$('#remove-rule').hide();
	
	$('#cancel-rule').hide();
	
	$('#bill-rule-container').hide();

}


function btnNew()
{
	$('#new-rule').hide();
	
	$('#save-rule').show();
	
	$('#remove-rule').hide();
	
	$('#cancel-rule').show();
	
	$('#bill-rule-container').show();

}

function saveRule()
{
	$.myPost(basePath+'/threshold/saveRule.do',{
		
		id:$('#rule-id').val(),
		paymentDate:$('#payment-date').val(),
		billPeriod:$('#bill-period-interval').val()+'-'+$('#bill-period-day option:selected').val(),
		billDate:$('#bill-date-interval').val()+'-'+$('#bill-date-day').val()
		
		
	},function(data){
		
		myNoty(data.msg,data.status);
		
		if(data.status===10001)
		{
			$('#rule-id').val(data.data);
			
			//TODO:add to select
			$('#bill-rule-select').empty();
			
			getBillRules();
			
			resetForm();
			
			btnCancel();
		}
		
		
	})
}
function removeRule()
{

	swal({
        title: '删除规则？',
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: true
        }, function () {
        	
        	$.myPost(basePath+'/threshold/removeRule.do',{id:$('#rule-id').val()},function(data){
        		
        		myNoty(data.msg,data.status);
        		
        		if(data.status===10001)
        		{
        			resetForm();
        			
        			$('#bill-rule-select').empty();
        			
        			getBillRules();
        			
        			resetForm();
        			
        			btnCancel();
        		}        		
        		
        	})
        	
        }
    );
	
	
	
	
	
	
	
	
}


function resetForm()
{
	$('#rule-id').val('');
	
	$('#bill-date-interval').val('');
	
	$('#payment-date').val('');
	
	$('#bill-date-day').val('');
	
	$('#bill-period').val('');
	
	$('#bill-date-interval').focus();

}