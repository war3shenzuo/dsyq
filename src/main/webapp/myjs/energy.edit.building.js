$(document).ready(function(){

	var supplyRoomstable = $('#supply-rooms-table')
	.DataTable(
			{
				//"order" : [ [ 3, "asc" ] ],// desc
				"order":[],
				"paging" :false,
				"info" : false,
				"dom" : '<"top">rt<"bottom"i><"clear">',
				 "deferLoading": 0,
				 "autoWidth": true,
				 "data": $('#supply-energy-list').val(),
			//	"processing" : "true",
				"columns" : [ 
				              {data : "contractNo"},
				              {data : "contractStatus"},
				              {data : "room"},
				              {data : "contractStartDate"},
				              {data : "contractEndDate"}, 
				              {data : "record"},
				              {data : "recordDate"},
				              {data : "refRoomId"}
				              ]
			,
			"columnDefs" : [
//				                
//				  
				{
					"targets":1,
					"render":function(data,type,row)
					{
						return renderContractStatus(data,row.contractStartDate,row.contractEndDate);
					}
				},
				{
					"targets":5,
					"render":function(data,type,row)
					{
						var i= '<input type="text" placeholder="记录读数" id="'+row.refRoomId+'" class="form-control supply-rooms-record" value="" />';
												
						return i;
					}
				},
				{
					"targets":6,
					"render":function(data,type,row)
					{
						return 	'<label data="'+row.refRoomId+'" class="control-label supply-rooms-record-date">'+data+'</label>';
					}
					
				},
				{
					"targets":[0,1,2,3,4,5,6],
					"orderable":false
				},
				{
					"targets":[7],
					"visible":false
				}
//				
//				
				]
			// end of columnDefs

			});
//			.on('preXhr.dt',function(e, settings, data) {
//
//			}).on('xhr.dt',function(e, settings, data) {
//
////				$('#room-start').attr('disabled','disabled');
////				
////				$('#room-save').removeAttr('disabled');
//				
//				
//			}).on('draw.dt', function() {
//			
//				
//			}).on('processing.dt',function(e, settings, processing) {
//
//			}).on('error.dt',function(e, settings, techNote, message) {
//
//			});// end of dataTable

		$('#supply-rooms-table tbody').on('click', 'tr', function() {
		
			$('#supply-rooms-table tr.selected').removeClass('selected');
			
			$(this).addClass('selected');
		
		})
			
	
	
	
	
	
	
	
	
	
	
var sharetable = $('#building-table')
		.DataTable(
				{
					//"order" : [ [ 0, "asc" ] ],// desc
					"order":[],
					"paging" : false,
					"info" : false,
					"dom" : '<"top">rt<"bottom"i><"clear">',
					"type" : "post",
//					 "pagingType": "full_numbers",
					 "deferLoading": 0,
					 "autoWidth": true,
					"ajax" : {
						"url" : basePath + "/energy/getBuildingEditList.do",
						"data" : function(d) {
							
							d.energyType=$('#energy-type option:selected').val();							
						}
					},
					"serverSide" : "true",
					"processing" : "true",
					"columns" : [ 
					              {data : "itemName"},
					              {data : "currRecord"},
					              {data : "currRecordDate"},
					              {data : "lastRecord"},
					              {data : "lastRecordDate"},
					              {data : "refItemId"},
					              {data : "energyEnterType"},
					              {data : "energyEnterDay"}
					              ],
					"columnDefs" : [
					                
					   
					{
						"targets":1,
						"render":function(data,type,row)
						{
							return '<input type="text" placeholder="记录读数" id="'+row.refItemId+'" class="form-control building-record" />';
						}
					},
					{
						"targets":2,
						"render":function(data,type,row)
						{
							var v=getRecordDate(row.energyEnterType,row.energyEnterDay);
							
							return 	'<label data="'+row.refItemId+'" class="control-label building-record-date">'+v+'</label>';
						}
					},
					{
						"targets":[5,6,7],
						"visible":false
					},
					{
						"targets":[0,1,2,3,4,5],
						"orderable":false
					}
					
					
					]
				// end of columnDefs

				}).on('preXhr.dt',function(e, settings, data) {

				}).on('xhr.dt',function(e, settings, data) {

									
					toggleShareFunc();
					
				}).on('draw.dt', function() {
					$('#building-table input.building-record-date').val($('#record-date').val());
				}).on('processing.dt',function(e, settings, processing) {

				}).on('error.dt',function(e, settings, techNote, message) {

				});// end of dataTable



			$('#building-table tbody').on('click', 'tr', function() {
			
				$('#building-table tr.selected').removeClass('selected');
				
				$(this).addClass('selected');
			
			});
			
			
			
			$('#building-start').on('click',function(){
				
				$.noty.closeAll();
				
				
				if(!myNotySele($('#energy-type')))
				{
					return;
				}
				
//				if(!myNotyRegx($('#record-date'),2))
//				{
//					return;
//				}
				
				
				$('#building-table').DataTable().ajax.reload();
				
				
				
			})
			
//			$('#building-save').on('click',function(){
//				
//				$.noty.closeAll();
//				
//				if(!myNotyRegx($('#building-table input.building-record'),1))
//				{
//					return;
//				}
//				
//				if(!myNotyRegx($('#building-table input.building-record-date'),2))
//				{
//					return;
//				}
//				
//				shareSave();
//			})
			
			$('#building-cancel').on('click',function(){
				
				toggleShareFunc();
				
				$('#building-table tbody').empty();
			

			})
			
		$('#supply-rooms-save').on('click',function(){
				
				$.noty.closeAll();
				
				if(!myNotyRegx($('#supply-rooms input.supply-rooms-record'),1))
				{
					return;
				}
								
				supplyRoomsSave();
			})
})

function supplyRoomsSave()
{
	var r=$('#supply-rooms-table input.supply-rooms-record');
	
	//var d=$('#building-table input.building-record-date');
	
	var recArr=[];
	
	for(var i=0;i<r.length;i++)
	{
		var dd=$('#supply-rooms-table label.supply-rooms-record-date');
			//$('#supply-rooms-table label.supply-rooms-record-date[data="'+$(r[i]).prop('id')+'"]');
		
		var d=$(dd[i]).html();
		
		if($(r[i]).is('[readonly]')==false)
		{	
			var r1={"record":$.trim($(r[i]).val()),"amount":0,"refItemId":$(r[i]).prop('id'),"recordDate":d,"refBuildingId":$('#selected-building-id').val(),"isBilled":"0","energyType":$('#energy-type').val(),"energyCategory":0};
		}
		
		recArr.push(r1);
	}
	
	if(recArr.length==0)
	{
		myNoty('没有需要保存的记录。',10001);
		
		return;
	}
	
	var recStr=JSON.stringify(recArr);
	
	$.myPost(basePath+'/energy/saveEnergyRecord.do',
			{
			energyCategory:0,
			energyType:$('#energy-type').val(),
			records:recStr
			
			},function(data){
				
				myNoty(data.msg,data.status);
				
				if(data.status===10001)
				{
					//toggleShareFunc();
					
					$('#supply-rooms-table input').attr('disabled','disabled');
					
					$('#supply-rooms-ibox').hide();
					
					$('#building-ibox').show();
				}
				
			})
	
	
	
}
	
function shareSave()
{
	var r=$('#building-table input.building-record');
	
	//var d=$('#building-table input.building-record-date');
	
	var recArr=[];
	
	for(var i=0;i<r.length;i++)
	{
		var date=$('#building-table label.building-record-date[data="'+$(r[i]).prop('id')+'"]');
		
		var d=date.html();
		
		var lr=date.parent().next().next().html();
		
		if(d==lr)
		{
			myNoty('本期抄表日已录入。',10002);
						
			return;
		}
		
		var pr=date.parent().next().html();
		
		var amount=parseFloat($.trim($(r[i]).val()))-parseFloat(pr);
		
		if(amount<0)
		{
			myNoty('记录不能小于前次',10002);
			
			$(r[i]).focus();
			
			return;
		}
		
		var r1={"record":$.trim($(r[i]).val()),"amount":amount,"refItemId":$(r[i]).prop('id'),"recordDate":d,"refBuildingId":""};
		
		recArr.push(r1);
	}
	
	var recStr=JSON.stringify(recArr);
	
	$.myPost(basePath+'/energy/saveEnergyRecord.do',
			{
			energyCategory:$('#energy-category-container input[name="energy-category"]:checked ').val(),
			energyType:$('#energy-type-selected').val(),
			
			records:recStr
			
			},function(data){
				
				myNoty(data.msg,data.status);
				
				if(data.status===10001)
				{
					toggleShareFunc();
					
					$('#building-table input').attr('disabled','disabled');
					
					checkSummary();
				}
				
			})
	
	
	
}

function toggleShareFunc()
{	
	$('.e-s-func').toggle();
	
	$('.e-func').toggle();
	
	//$('#sign-datepicker').toggle();
	
}
