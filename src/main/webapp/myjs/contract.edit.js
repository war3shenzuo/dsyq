$(document).ready(function(){

	$('.date').datepicker(
			
		{todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,autoclose:!0}
		
	)	
		
	
	
	
	
	$('#terminateModal').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var action=button.data('action')
//		  var companyName = button.data('companyName') // Extract info from data-* attributes
//		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
//		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
			var modal = $(this)
			if(action)
			{
				if(action==='t')
				{
				  modal.find('.modal-title').text('终止合同')
				  modal.find('.action-reason').text('终止原因：')
				  modal.find('.action-date').show();
				  modal.find('#btn-terminate').show();
				  modal.find('#btn-del').hide();
				  
				}	  
				else
				{
					modal.find('.modal-title').text('删除合同')
					modal.find('.action-reason').text('删除原因：')
					modal.find('.action-date').hide();
					modal.find('#btn-terminate').hide();
					modal.find('#btn-del').show();
					
				}
			
			
				$('#t-company-name').val($('#company-name').val());
				
				$('#t-contract-category').val($('#contract-category option:selected').text());
				
				$('#t-contract-id').val($('#id').val());
				
				$('#t-date').val(formatDate(new Date()));	
			}
			  
	})
	
	$('#terminateModal').on('hidden.bs.modal', function (event) {

		$('#t-company-name').val('');
		
		$('#t-contract-category').val('');
		
		$('#t-contract-id').val('');
		
		$('#t-reason').val('');		
			  
	})
		
		
		
		
		
		$('#terminate-contract').on('click',function(){
			
//			if($('#t-contract-status').val()=='0')
//			{
				//$('#terminateModal').modal('show');
//			}
			
			
			
		})
		
		$('#btn-terminate').on('click',function(){
			
			$.noty.closeAll();			
			
			if(!myNotyRegx($('#t-date'),2))
			{
				return;
			}
			
			
			var fdd=DateDiff($('#contract-start-date').val(),$('#t-date').val());
			
			if(fdd<0)
			{
				myNoty('终止日不能小于开始日期',10002);
				
				return;
			}
			
			
			fdd=DateDiff($('#t-date').val(),$('#contract-end-date').val());
			
			if(fdd<0)
			{
				myNoty('终止日不能大于结束日期',10002);
				
				return;
			}
			
			
			
			
			if(!myNotyNull('请输入终止原因',$('#t-reason')))
			{
				return;
			}
			
			
			
			terminateContract();
			
		})
		
		
		$('#del-contract').on('click',function(){
			
			//$('#terminateModal').modal('show');
			//delContract();
		})
		
		$('#btn-del').on('click',function(){
			
			$.noty.closeAll();			
			
			
			if(!myNotyNull('请输入删除原因',$('#t-reason')))
			{
				return;
			}	
			
			delContract();
			
		})
	
		
	
})



function getServiceItems()
{
	
$.post(basePath+'/contract/getContractServiceItems.do',{active:true},function(data){
		
		
		
		if(data.status===10001)
		{
//			var u=$('#service-item-container');
//			
//			for(var i=0;i<data.data.length;i++)
//			{
//				
//			var li='<div>'+
//            '<label>'+
//                '<input type="radio" value="'+data.data[i].serviceName+'" name="s"> <i></i> <b>'+data.data[i].serviceName+':</b>'+data.data[i].serviceDesc+'</label>'+
//                '</div>';
//			
//			u.append(li);
//			
//			
//			
//			
//			
//			}
			$('#service-item-select').empty();
			//$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})
			
			for(var i=0;i<data.data.length;i++)
			{
			
			var oOption=document.createElement('option');
			
			oOption.value=data.data[i].serviceName;
			
			oOption.text=data.data[i].serviceName+': '+data.data[i].serviceDesc;
						
			$('#service-item-select').append(oOption);
			
			}
		}	
		
		
	})
}





function renderStatus()
{
//	var as=$('#contract-audit-status option:selected').val();
	
	var s=$('#contract-status-sele option:selected').val();
	
	//除了初始状态，两个拒绝状态，其他不能编辑
	if(checkContractStatusEditable(s)==false || $('#read-only').val()=='true')
	{
		$('.con-save-btn').hide();
		//.attr('disabled','disabled');
		
		$('#contract-submit-audit').hide();
		
		$('#terminate-contract').hide();
		
		$('#del-contract').hide();
		
		$('#company-modal').hide();
		
		$('#room-modal').hide();
		
		$('#use-unified-role').hide();
		
		
		
	}
	
	//已审批状态且合同正常，可以终止
	//0329：可以删除
	if(s==contractStatus['parkerAccept'][0].id 
			
			&& ($('#contract-status-str').val()=='正常' 
				|| $('#contract-status-str').val()=='未开始')
			
			&& $('#read-only').val()=='false')
	{
		$('#terminate-contract').show();
		$('#del-contract').show();
	}
	
//	
//	//编辑，正常
//	if($('#id').val()!='' && $('#contract-status-sele option:selected').val()==0 && $('#contract-status-str').val()=='合同正常')
//	{
//		$('#terminate-contract').show();
//	}
//	else
//	{
//		$('#terminate-contract').show();
//	}
	
	

	
	//终止状态，不能保存
	if(s==contractStatus['terminateAuditing'][0].id || s==contractStatus['terminate'][0].id)
	{
		//$('#status-check').attr('disabled','disabled');
		
		$('#terminate-group').show();
		$('#terminate-date-contain').show();
		$('#terminate-reason-label').text('终止原因');
		
		//$('.con-save-btn').attr('disabled','disabled');
		
	}	
	
	if(s==contractStatus['deleteAuditing'][0].id || s==contractStatus['deleted'][0].id)
	{
		//$('#status-check').attr('disabled','disabled');
		
		$('#terminate-group').show();
		
		$('#terminate-date-contain').hide();
		
		$('#terminate-reason-label').text('删除原因');
		
		//$('.con-save-btn').attr('disabled','disabled');
		
	}
	
	if($('#id').val()=='')
	{
		$('#contract-item-contain').hide();
	}
	
	
}

function refreshContractList()
{
	window.parent.refreshIframe(basePath+"/contract/index.do");	
}
