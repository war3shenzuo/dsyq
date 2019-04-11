$(document).ready(function(){
	
	
	
	var expressitemtable = $('#express-item-table')
	.DataTable(
			{
				//"order" : [ [ 0, "asc" ] ],// desc
				"paging" : false,
				
				"info" : false,
				"dom" : '<"top">rt<"bottom"ip><"clear">',
				"type" : "post",
				// "pagingType": "full_numbers",
				"ajax" : {
					"url" : basePath + "/contract/getExpressItemList.do",
					"data" : function(d) {
						d.contractId = $('#id').val();
					}
				},
				"serverSide" : "true",
				"processing" : "true",
				"columns" : [ 
				              {data : "destination",className : "all"},
				              {data : "firstHeavy",className : "all"}, 
				              {data : "firstHeavyPrice",className : "all"},
				              {data : "forwardingHeavy",className : "desktop"}, 
				              {data : "forwardingHeavyPrice",className : "desktop"},
				              {data : "id"}
				              ],
				"columnDefs" : [

				{
					"targets" : 5,
					"orderable" : false,
					"data" : "download_link",
					"render" : function(data, type,	full, meta) {
						 
						
						var s='<a class="btn btn-sm btn-info" href="javascript:void(0)" onClick=javascript:editExpressItem("'
								+ data
								+ '")>编辑</a> | '
								+

								'<a class="btn btn-sm btn-danger con-save-btn" href="javascript:void(0)" onClick=javascript:removeExpressItem("'
								+ data + '")>删除</a>';
						
						return s;
					}
				}
				
				
				]
			// end of columnDefs

			}).on('preXhr.dt',function(e, settings, data) {

			}).on('xhr.dt',function(e, settings, data) {

				

					
				
				
			}).on('draw.dt', function() {
				
				if($('#contract-status-sele option:selected').val()==1)
				{
											
					$('#express-item-table .con-save-btn').attr('disabled','disabled');
					
				}
				
			}).on('processing.dt',function(e, settings, processing) {

			}).on('error.dt',function(e, settings, techNote, message) {

			});// end of dataTable



		$('#express-item-table tbody').on('click', 'tr', function() {
		
			$('#express-item-table tr.selected').removeClass('selected');
			
			$(this).addClass('selected');
		
		});
	
	
	
	
	
	
	
	
	
	
	
	loadExpress();
	
	$('#express-save').on('click',function(){
	
		
		$.noty.closeAll();
		
		if($('#id').val()=='')
		{
			myNoty('请先保存基本信息。',10002);
			
			return;
		}
		if(!myNotyRegx($('.check-express-float'),1))
		{
			return;
		}
		if(!myNotyRegx($('.check-express-int'),0))
		{
			return;
		}
		
		
		
		saveExpress();
		
	})
	
	$('#express-item-save').on('click',function(){
	
		
		$.noty.closeAll();
		
		if($('#id').val()=='')
		{
			myNoty('请先保存基本信息。',10002);
			
			return;
		}
		if(!myNotyRegx($('.check-express-item-null'),3))
		{
			return;
		}
		if(!myNotyRegx($('.check-express-item-float'),1))
		{
			return;
		}
		
		
		
		
		saveExpressItem();
		
	})
	
	
	$('#express-item-new').on('click',function(){
	
		resetExpressItemForm();
		
		$('#express-item-destination').focus();
		
	})
	
	
	
	$('#bill-rule-container').on('click','input',function(){
			
			//myNoty($(this).attr('value'),10001);
			
			$('#express-bill-date').val($(this).attr('value'));
			
			$('#express-payment-date').val($(this).attr('paymentdate'));
			
			$('#express-bill-period').val($(this).attr('billperiod'));
			
			//$('#bill-rule-container input[checked="true"]');
		})
	
	
})



function loadExpress()
{	
	if($('#id').val()=='')
		return;
	
	$.post(basePath+'/contract/getContractExpress.do',{refContractId:$('#id').val()},function(data){
		
		if(data.status===10001)
		{
			var d=data.data;
			
			$('#express-id').val(d.id);
			
			$('#express-lost-bill-price').val(d.lostBillPrice);
			
			$('#express-incomplete-bill-price').val(d.incompleteBillPrice);
			
			$('#express-bill-date').val(d.billDate);
			
			$('#express-payment-date').val(d.paymentDate);
			
			$('#express-bill-period').val(d.billPeriod);
			
			
		}
	})
}





function saveExpress()
{
	$.post(basePath+'/contract/saveExpress.do',{
		
		id:$('#express-id').val(),
		
		refContractId:$('#id').val(),
		
		lostBillPrice:$('#express-lost-bill-price').val(),
	
		incompleteBillPrice:$('#express-incomplete-bill-price').val(),
	
		billDate:$('#express-bill-date').val(),
	
		paymentDate:$('#express-payment-date').val(),
	
		billPeriod:$('#express-bill-period').val()
		
		
	},function(data){
		
		myNoty(data.msg,data.status);
		
		if(data.status===10001)
		{
			$('#express-id').val(data.data);
		}
		
	})
	
}


function editExpressItem(id)
{
$.post(basePath+'/contract/getContractExpressItem.do',{id:id},function(data){
		
		if(data.status===10001)
		{	
			var d=data.data;
			
			$('#express-item-id').val(d.id);
			
			$('#express-item-destination').val(d.destination);
			
			$('#express-item-first-heavy').val(d.firstHeavy);
					
			$('#express-item-first-heavy-price').val(d.firstHeavyPrice);
			
			$('#express-item-forward-heavy').val(d.forwardingHeavy);
			
			$('#express-item-forward-heavy-price').val(d.forwardingHeavyPrice);
		
			
		}
		
		
		
	})
}


function saveExpressItem()
{
	
	$.post(basePath+'/contract/saveExpressItem.do',{
		
		id:$('#express-item-id').val(),
		
		refContractId:$('#id').val(),
		
		destination:$('#express-item-destination').val(),
		
		firstHeavy:$('#express-item-first-heavy').val(),
				
		firstHeavyPrice:$('#express-item-first-heavy-price').val(),
		
		forwardingHeavy:$('#express-item-forward-heavy').val(),
		
		forwardingHeavyPrice:$('#express-item-forward-heavy-price').val()
		
	
	},function(data){
		
		
		
		myNoty(data.msg,data.status);
		
		if(data.status===10001)
		{
			$('#express-item-id').val(data.data);
			
			$('#express-item-table').DataTable().ajax.reload();
		}
		
		
	})

}


function removeExpressItem(id)
{
	
            //myConfirm("确定要删除吗？",removeExpressItemFunc(id));

            
}


var removeExpressItemFunc=function (id)
{
	$.post(basePath+'/contract/removeExpressItem.do',{id:id},function(data){
		
		myNoty(data.msg,data.status);
		
		if(data.status===10001)
		{
			$('#express-item-table').DataTable().ajax.reload();
		}
		
	})

}

function resetExpressItemForm()
{
	$('#express-item-id').val('');
	
	$('#express-item-destination').val('');
	
	$('#express-item-first-heavy').val('');
			
	$('#express-item-first-heavy-price').val('');
	
	$('#express-item-forward-heavy').val('');
	
	$('#express-item-forward-heavy-price').val('');

}