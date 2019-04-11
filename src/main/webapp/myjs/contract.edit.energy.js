$(document).ready(function(){	
	
	//getBillRules();
	
	readEnergy($('#id').val());
	
//	$('#bill-rule-container').on('click','input',function(){
//				
//		$('#energy-bill-date').val($(this).attr('value'));
//		
//		$('#energy-payment-date').val($(this).attr('paymentdate'));
//		
//		$('#energy-bill-period').val($(this).attr('billperiod'));		
//		
//	})
	
	
	$('#energy-item-save').on('click',function(){

		$.noty.closeAll();
		
		if($('#id').val()=='')
		{
			myNoty('请先保存基本信息。',10002);
			
			return;
		}
		
		if(!myNotyRegx($('.check-energy-float'),1))
		{
			return;
		}
		
		if(!$('.e-record').is(':hidden'))
		{
			if(!myNotyRegx($('.check-energy-record-float'),1))
			{
				return;
			}	
			
			if(!myNotyRegx($('.check-energy-record-date'),2))
			{
				return;
			}	
			
			var rd=$('#energy-power-record-date').val();
			
			var sd=$('#contract-start-date').val();
			
			var td=formatDate(new Date());
		
			
			if(DateDiff(rd,sd)>1 || DateDiff(rd,td)<1)
			{
				myNoty('结算日范围为合同前一天至昨天。',10002);
				
				return;
			}
			
			
		}
		
		
//		if(!myNotyRegx($('.check-energy-int'),0))
//		{
//			return;
//		}
		
//		var c=$('#bill-type-check').prop('checked');
//		
//		if (c)
//		{
//			if(!myNotyNull('无出帐日',$('#bill-date')))
//			{
//				return;
//			}
//		}
//		else
//		{
//			if(!myNotyNull('无出帐日',$('#bill-dates')))
//			{
//				return;
//			}
//			
//		}
		
		
//		if(!myNotyRegx($('.check-bill-int'),0))
//		{
//			return;
//		}
		
		
		saveEnergy();
		
	})
		
	
	
	$('#energy-power-record-date').on('change',function(){
		
		var d=$('#energy-power-record-date').val();
		
		$('#energy-water-record-date').val(d);
		
		$('#energy-gas-record-date').val(d);
		
		$('#energy-ac-record-date').val(d);
		
		
		
		
	})
	
})

function readEnergy(refContractId)
{
	
//	if($('#energy-id').val()=='')
//	{
//		$('#last-fee-date').removeAttr('disabled');
//	}
//	else
//	{
//		$('#last-fee-date').attr('disabled','disabled');	
//	}
	
	
	
	if(refContractId=='')
	{
//		$('#energy-start-date').val($('#contract-start-date').val());
//		
//		$('#energy-end-date').val($('#contract-end-date').val());
//		
		$('#energy-item-save').hide();
		
		return;		
	}
	
	$.myPost(basePath+'/contract/getContractEnergy.do',{refContractId:refContractId},function(data){
		
		if(data.status===10001)
		{
			
			var e=data.data;
			
			$('#energy-id').val(e.id);
			
//			$('#energy-start-date').val(e.startDate);
//			
//			$('#energy-end-date').val(e.endDate);
			
			$('#energy-power').val(e.powerPrice);
			
			$('#energy-water').val(e.waterPrice);
			
			$('#energy-gas').val(e.gasPrice);
			
			$('#energy-ac').val(e.acPrice);
			
			$('#energy-power-record').val(e.powerRecord);
			
			$('#energy-water-record').val(e.waterRecord);
			
			$('#energy-gas-record').val(e.gasRecord);
			
			$('#energy-ac-record').val(e.acRecord);
			
			
			$('#energy-power-record-date').val(e.powerRecordDate);
			
			$('#energy-water-record-date').val(e.waterRecordDate);
			
			$('#energy-gas-record-date').val(e.gasRecordDate);
			
			$('#energy-ac-record-date').val(e.acRecordDate);
			
			//$('#last-fee-date').val(e.lastFeeDate);
			
			$('#energy-item-save').show();
			
//			$('#bill-date').val(e.billDate);
//			
//			$('#bill-dates').val(e.billDates);
//			
//			$('#payment-date').val(e.paymentDate);
//			
//			$('#bill-period').val(e.billPeriod);
//			
//			$('#bill-type-check').prop('checked',e.billType==1);
			
//			renderBillType();
//			
//			renderBillDates();
		}
//		else
//		//if(data.status===10000)
//		{
//			$('#energy-start-date').val($('#contract-start-date').val());
//			
//			$('#energy-end-date').val($('#contract-end-date').val());
//		}		
		
		renderEnergyView();
		
		getBuildingEnergy();
	})	
}

//取回楼能源设置
function getBuildingEnergy()
{
	$.post(basePath+'/contract/getBuildingEnergyByRoom.do',
			{refRoomId:$('#ref-room-id').val()},
			function(data){
		
		if(data.status===10001)
		{
			var d=data.data;
			
			for(var i=0;i<d.length;i++)
			{
				$('#room-amount-'+d[i].energyType).val(d[i].roomAmountUsed);
			
				$('#room-share-'+d[i].energyType).val(d[i].shareType);
			}
			
			renderRoomAmountUsed();
		}
		else
		{
			myNoty('楼能源信息设置不全，请联系管理员',10002);
		}
		
		
	})


}

function saveEnergy()
{
	$.myPost(basePath+'/contract/saveEnergy.do',{
		
		id:$('#energy-id').val(),
		refContractId:$('#id').val(),
		powerPrice:$('#energy-power').val(),
		waterPrice:$('#energy-water').val(),
		gasPrice:$('#energy-gas').val(),
		acPrice:$('#energy-ac').val(),
		powerRecord:$('#energy-power-record').val(),
		waterRecord:$('#energy-water-record').val(),
		gasRecord:$('#energy-gas-record').val(),
		acRecord:$('#energy-ac-record').val(),
		powerRecordDate:$('#energy-power-record-date').val(),
		waterRecordDate:$('#energy-water-record-date').val(),
		gasRecordDate:$('#energy-gas-record-date').val(),
		acRecordDate:$('#energy-ac-record-date').val()
		//lastFeeDate:$('#last-fee-date').val()
//		billType:$('#bill-type-check').prop('checked')?1:0,
//		billDates:$('#bill-dates').val(),
//		billDate:$('#bill-date').val(),
//		paymentDate:$('#payment-date').val(),
//		billPeriod:$('#bill-period').val()
//		startDate:$('#energy-start-date').val(),
//		endDate:$('#energy-end-date').val()
		//lastBillDate:$('#contract-start-date').val()
		
		
	},function(data){
		
		myNoty(data.msg,data.status);
		
		if(data.status===10001)
		{
			$('#energy-id').val(data.data);
				
			renderEnergyView();
		}
		
	})
}

function renderEnergyView()
{

	//审批为初始，且已保存能源设置，可以提交申请
	if($('#contract-status-sele option:selected').val()==contractStatus['init'][0].id && $('#energy-id').val()!='' &&
			$('#read-only').val()=='false' )
	{
		$('#contract-submit-audit').show();
	}
	
	//PS：当合同结束日期小于当前日期，已结算读数和已结算日期不可填
	
	var enddate=$('#contract-end-date').val();
	
	var today=formatDate(new Date());
	

	if(DateDiff(enddate,today)>0)
	{
		$('.e-record').hide();
	}
	else
	{
		$('.e-record').show();
	}
	
	
	
}
//不使用房间使用量，直接置为0，且不能修改
function renderRoomAmountUsed()
{
		
	for(var i=0;i<4;i++)
	{
		var p=$('#room-amount-'+i);
		
		if(p.val()==0)
		{
			p.val('不出帐');
			
			//$(p).parent().prev().prev().find('input').val(0);
			
			//$(p).parent().prev().prev().find('input').attr('readonly','readonly');
		}	
		else
		{
			p.val('出帐');
		}
		
		var s=$('#room-share-'+i);
		
		if(s.val()==0)
		{
			s.val('使用量');
		}
		if(s.val()==1)
		{
			s.val('建筑面积');
		}
		if(s.val()==2)
		{
			s.val('不公摊');
		}
		
		
	}
	

}
