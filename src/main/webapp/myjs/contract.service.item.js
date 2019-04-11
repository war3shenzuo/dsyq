$(document).ready(function(){
	
		getServiceItems();		
	
	$('#service-item-select').on('click',function(){
		
		var s=$('#service-item-select option:selected');
		
		var d=s.attr('value');
		
		$('#service-item-name').val(d);
		
		$('#service-item-desc').val(s.attr('service-item-desc'));
						
		$('#service-item-id').val(s.attr('service-item-id'));
		
		$('#service-item-parkid').val(s.attr('service-item-parkid'));
		
		$('#service-item-valid').prop('checked',s.attr('service-item-valid')==1?true:false);
					
		btnServiceItemEdit();
		
		
	})
	
	$('#cancel-service-item').on('click',function(){
		
		btnServiceItemCancel();
		
	})
	
	$('#save-service-item').on('click',function(){
		//check
		
		$.noty.closeAll();
		
		if(!myNotyRegx($('.check-service-item-null'),3))
		{
			return;
		}
		
		saveServiceItem();
	})
	
	$('#remove-service-item').on('click',function(){
		
		$.noty.closeAll();
		//check
		if(!myNotyNull('请选择服务',$('#service-item-id')))
		{
			return;
		}
		
		removeServiceItem();
	})
	
	$('#new-service-item').on('click',function(){
		
		resetServiceItemForm();
				
		btnServiceItemNew();
	})
	
	
	
})


function getServiceItems()
{
	$.post(basePath+'/contract/getContractServiceItems.do',{active:null},function(data){
		
		
		
		if(data.status===10001)
		{
	
			for(var i=0;i<data.data.length;i++)
			{
				var d=data.data[i];
				
				var oOption=document.createElement('option');
				
				oOption.value=d.serviceName;
				
				var s=d.valid?'激活':'禁用';
				
				oOption.text=d.serviceName+': '+d.serviceDesc+'('+s+')';
				
				$(oOption).attr('service-item-desc',d.serviceDesc);
				
				$(oOption).attr('service-item-id',d.id);
				
				$(oOption).attr('service-item-parkid',d.refParkId);
				
				$(oOption).attr('service-item-valid',d.valid?1:0);
											
				$('#service-item-select').append(oOption);
			}
			
		}		
		
	})
}

function addServiceItem(data)
{
	var oOption=document.createElement('option');
	
	oOption.value=data.serviceName;
	
	oOption.text=data.serviceName+': '+data.serviceDesc;
	
	$(oOption).attr('desc',data.serviceDesc);
				
	$('#service-item-select').append(oOption);

}


function btnServiceItemEdit()
{
	$('#new-service-item').hide();
	
	$('#save-service-item').show();
	
	$('#remove-service-item').show();
	
	$('#cancel-service-item').show();
	
	$('#service-item-container').show();

}
function btnServiceItemCancel()
{
	$('#new-service-item').show();
	
	$('#save-service-item').hide();
	
	$('#remove-service-item').hide();
	
	$('#cancel-service-item').hide();
	
	$('#service-item-container').hide();

}


function btnServiceItemNew()
{
	$('#new-service-item').hide();
	
	$('#save-service-item').show();
	
	$('#remove-service-item').hide();
	
	$('#cancel-service-item').show();
	

	$('#service-item-container').show();
}

function saveServiceItem()
{
	$.myPost(basePath+'/threshold/saveServiceItem.do',{
		
		id:$('#service-item-id').val(),
		refParkId:$('#servoce-item-parkid').val(),
		serviceName:$('#service-item-name').val(),
		serviceDesc:$('#service-item-desc').val(),
		valid:$('#service-item-valid').prop('checked')
		
		
		
	},function(data){
		
		myNoty(data.msg,data.status);
		
		if(data.status===10001)
		{
			$('#service-item-id').val(data.data);
			
			//TODO:add to select
			$('#service-item-select').empty();
			
			getServiceItems();
			
			resetServiceItemForm();
			
			btnServiceItemCancel();
		}
		
		
	})
}
function removeServiceItem()
{

	swal({
        title: '删除服务项目？',
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: true
        }, function () {
        	
        	$.myPost(basePath+'/threshold/removeServiceItem.do',{id:$('#service-item-id').val()},function(data){
        		
        		myNoty(data.msg,data.status);
        		
        		if(data.status===10001)
        		{
        			resetServiceItemForm();
        			
        			$('#service-item-select').empty();
        			
        			getServiceItems();
        			
        			resetServiceItemForm();
        			
        			btnServiceItemCancel();
        		}        		
        		
        	})
        	
        }
    );
	
	
	
	
	
	
	
	
}


function resetServiceItemForm()
{
	$('#service-item-id').val('');
	
	$('#service-item-name').val('');
	
	$('#service-item-desc').val('');
	
	$('#service-item-valid').prop('checked',true);

	
	$('#service-item-name').focus();

}