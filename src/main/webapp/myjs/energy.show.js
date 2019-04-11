$(document).ready(function(){

	$('.date').datepicker(
			
			{todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,autoclose:!0}
			
		)
		
	var ebilltable = $('#energy-bill-table')
	.DataTable(
			{
				"order" : [ [ 7, "desc" ] ],
				//"order":[],
				"paging" : true,
				"info" : true,
				"dom" : '<"top">rt<"bottom"ip><"clear">',
				"type" : "post",
				 "pagingType": "full_numbers",
				 "deferLoading": 0,
				 //"responsive": true,
				 //"responsive": true,
//				 "fixedHeader": true,
//				 "fixedColumns": true,
				 "autoWidth": true,
				"ajax" : {
					"url" : basePath + "/energy/getEnergyBillList.do",
					"data" : function(d) {
						
						d.refId=$('#ref-id').val();	
						d.idType=$('#id-type').val();
					}
				},
				"serverSide" : "true",
				"processing" : "true",
				"columns" : [ 
				              {data : "billId",className : "all"},
				              {data : "companyName",className : "desktop"},
				              {data : "description",className : "desktop"},
				              {data : "amount",className : "desktop"},
				              {data : "overdueFine",className : "desktop"},
				              {data : "billStatus",className : "desktop"},
				              {data : "auditStatus",className : "desktop"},
				              {data : "createdTime",className : "desktop"},
				              {data : "deadline",className : "desktop"}
				              ],
				"columnDefs" : [
				                
				   
				{
					"targets":5,
					"render":function(data,type,row)
					{
						return renderBillStatus(row.billStatus);
					}
				},
				{
					"targets":6,
					"render":function(data,type,row)
					{
						return renderAuditStatus(row.auditStatus);
					}
				}
				
				
				]
			// end of columnDefs

			}).on('preXhr.dt',function(e, settings, data) {

			}).on('xhr.dt',function(e, settings, data) {

								
				
				
			}).on('draw.dt', function() {
				
			}).on('processing.dt',function(e, settings, processing) {

			}).on('error.dt',function(e, settings, techNote, message) {

			});// end of dataTable



		$('#share-table tbody').on('click', 'tr', function() {
		
			$('#share-table tr.selected').removeClass('selected');
			
			$(this).addClass('selected');
		
		});
	
	
		var erecordtable = $('#energy-record-table')
		.DataTable(
				{
					"order" : [ [ 2, "desc" ] ],
					//"order":[],
					"paging" : true,
					"info" : true,
					//"dom" : '<"top">Brt<"bottom"ip><"clear">',
					"dom" : '<"top">rt<"bottom"ip><"clear">',
					"type" : "post",
					 "pagingType": "full_numbers",
					 "deferLoading": 0,
					 "autoWidth": true,
					 //"responsive": true,
//					 buttons: [
//								'copyHtml5',
//								//'excelHtml5',
//								'csvHtml5'
//								//'pdfHtml5'
//				               ],
					"ajax" : {
						"url" : basePath + "/energy/getEnergyRecordList.do",
						"data" : function(d) {
							
							d.refId=$('#ref-id').val();
							d.idType=$('#id-type').val();
							d.energyType=$('#search-energy-type option:selected').val();
							d.enterStartDate=$('#search-energy-enter-start-date').val();
							d.enterEndDate=$('#search-energy-enter-end-date').val();
							d.billStartDate=$('#search-energy-bill-start-date').val();
							d.billEndDate=$('#search-energy-bill-end-date').val();
						}
					},
					"serverSide" : "true",
					"processing" : "true",
					"columns" : [ 
					              {data : "room",className : "all"},
					              {data : "energyType",className : "all"},
					              {data : "recordDate",className : "desktop"},
					              {data : "record",className : "desktop"},
					              {data : "amount",className : "desktop"},
					              {data : "days",className : "desktop"},
					              {data : "dailyAmount",className : "desktop"},
					              {data : "isBilled"},
					              {data : "createdTime",className : "desktop"},
					              {data : "billId",className : "desktop"},
					              {data : "id"}
					              
					              ],
					"columnDefs" : [
					                
					   
					{
						"targets":1,
						"render":function(data,type,row)
						{
							return renderEnergyType(row.energyType);
						}
					},
					{
						"targets":3,
						"render":function(data,type,row)
						{
							return '<input type="text" style="width:120px;" placeholder="读数" data="'+row.id+'" class="form-control room-energy-record" value="'+data+'" />';
						}
					},
					{
						"targets":4,
						"render":function(data,type,row)
						{
							return data.toFixed(2);
						}
					},
					  
					{
						"targets":6,
						"render":function(data,type,row)
						{
							return data.toFixed(2);
						}
					},
					{
						"targets":7,
						"render":function(data,type,row)
						{
							var b='<label class="btn btn-sm btn-success">是</label>';
							
							if(data==0)
							{
								b='<label class="btn btn-sm btn-warning">否</label>';
							}
							
							return b;
							
							
						}
					},
					{
						"targets":10,
						"visible":false
					}
					
					
					]
				// end of columnDefs

				}).on('preXhr.dt',function(e, settings, data) {

				}).on('xhr.dt',function(e, settings, data) {

					getEnergySummary();	
					
					
				}).on('draw.dt', function() {
					
					if($('#parker-audit').val()==0)
					{
						$('.room-energy-record').attr('readonly','readonly');
					}
					
					
				}).on('processing.dt',function(e, settings, processing) {

				}).on('error.dt',function(e, settings, techNote, message) {

				});// end of dataTable



			$('#share-table tbody').on('click', 'tr', function() {
			
				$('#share-table tr.selected').removeClass('selected');
				
				$(this).addClass('selected');
			
			});
	
	
	
	$('#filter-energy-record').on('click',function(){
		
//		$.noty.closeAll();
		
//		if($('#ref-room-id').val()=='')
//		{
//			myNoty('请选择房间',10002);
//			
//			return;
//		}
		
		$('#energy-record-table').DataTable().ajax.reload();
		
	})
	
	
	
	
	
	
	
	
	
	var floortree = $("#floor-tree").jstree({
        "core": {
            "animation": true,
            "multiple": false,
            "check_callback": true,
            "themes": { "stripes": false },
            'data': {
                'url':
                	
                	
                	(function () {
                	    var url = basePath+"/floor/getFloorList.do?status=1&parkId="+$('#parkId option:selected').val();

                	    return url;
                	})
                	
                	,
                "dataType": "json",
                'data': function (node) {
                    return { 'id': node.id };
                }
            }
        }

    }).on("changed.jstree", function (event, data) {
    	
        if (data.selected.length > 0) {

        	var id=data.selected[0];
        	if($('#floor-tree').jstree('is_closed',id))
        	{
        		$('#floor-tree').jstree('open_node',id);
        	}else
        	{
        		$('#floor-tree').jstree('close_node',id);
        	}
            //var sel = data.instance.get_node(data.selected[0]).original.attr;
//        	if($("#floor-tree").jstree().is_leaf(id))
//        		{
//        			$('#rooms-select').height($('#floor-tree').height());
//        		
//        			loadRooms(id);
//        		}

            //if leaf,get parents ,show in editor
            
        	var p1=$('#floor-tree').jstree().get_parent(id);
        	
        	var p2=$('#floor-tree').jstree().get_parent(p1);
        	
        	var p3=$('#floor-tree').jstree().get_parent(p2);
        	
        	$('#ref-id').val(id);
        	
        	//building
        	if(p1=='#')
        	{       		
        		$('#id-type').val(0);
        		
        		$('#building').val($('#floor-tree').jstree().get_text(id));
        		
        		$('#floor').val('');
        		
        		$('#block').val('');
        		
        		refreshTables();
        	}
        	//floor
        	if(p2=='#')
        	{	
        		$('#id-type').val(1);
        		
        		$('#building').val($('#floor-tree').jstree().get_text(p1));
        		
        		$('#floor').val($('#floor-tree').jstree().get_text(id));
        		
        		$('#block').val('');
        		
        		refreshTables();
        	}
        	//block
        	if(p3=='#')
        	{
//        		myNoty($('#floor-tree').jstree().get_text(id),10001);
//        		myNoty($('#floor-tree').jstree().get_text(p1),10001);
//        		myNoty($('#floor-tree').jstree().get_text(p2),10001);
//        		
        		$('#id-type').val(2);
        		
        		
        		$('#building').val($('#floor-tree').jstree().get_text(p2));
        		
        		$('#floor').val($('#floor-tree').jstree().get_text(p1));
        		
        		$('#block').val($('#floor-tree').jstree().get_text(id));
        		
        		$('#rooms-select').height($('#floor-tree').height());
        		
    			loadRooms(id);
    			
    			refreshTables();
        		
        	}
        	
          
        }
    }).jstree();
	
	$('#roomModal').on('show.bs.modal', function (event) {
				
			  
	})
	$('#rooms-select').on('click',function(){
		
			
			var s=$('#rooms-select option:selected');
			
			if(s.val()!='' && s.val()!=$('#ref-room-id').val())
			{
				
				$('#ref-id').val(s.val());
				
				$('#id-type').val(3);
				
				
				$('#ref-room-id').val(s.val());
				
				$('#room').val(s.text());
				
				$('#area').val(s.attr('buildArea'));
				
				loadInfo(s.val());
				
			}
			
		})
	
	
	$('#btn-load').on('click',function(){
		
		var rid=$('#ref-room-id').val();
		
		if(rid=='')
		{
			myNoty('请选择房间',10002);
			
			return;
		}
		
		loadInfo(rid);
	})
	
	$('#save-energy-record').on('click',function(){
		
		if(!myNotyRegx($('#energy-record-table input.room-energy-record'),1))
		{
			return;
		}
		
		saveEnergyRecord();		
		
	}) 
	
	$('#parkId').on('change',function(){
		
		$('#floor-tree').jstree().refresh();
		
		
	})
	
	renderView();
	
	
})




function loadRooms(id)
{
	$.post(basePath+'/floor/getRooms.do',{areaId:id},function(data){
		
		
		if(data.status===10001)
		{
			$('#rooms-select').empty();
			
			for(var i=0;i<data.data.length;i++)
			{
				var oOption=document.createElement('option');
				
				oOption.value=data.data[i].id;
				
				oOption.text=data.data[i].roomNum;
				
				$(oOption).attr('buildArea',data.data[i].buildArea);
				
				$('#rooms-select').append(oOption);
				
			}
		}
		
	})

}

function loadInfo(roomid)
{	
	$('#roomModal').modal('hide');
	
	//myNotyLoading();

	$.myPost(basePath+'/contract/getContractEnergyByRoom.do',{refRoomId:roomid},function(data){
		
	
		if(data.status==10001)
		{
			//set contract
			var d=data.data;
			
			$('#c-no').val(d.contractNo);
			
			$('#c-sign-date').val(d.contractSignDate);
			
			$('#c-start-date').val(d.contractStartDate);
			
			$('#c-end-date').val(d.contractEndDate);
		
			
		}
		else
		{
			//myNoty(data.msg,data.status);
		}
		
		
		refreshTables();

		
		
		
	});
	
	
	
	
	
	
	
//	
//	setTimeout(function () {
//        $.noty.closeAll();
//    }, 2000);
	
}

function refreshTables()
{
	//loadEnergyBill();
	
	$('#energy-bill-table').DataTable().ajax.reload();
	
	//loadEnergy
	
	$('#energy-record-table').DataTable().ajax.reload();
}


function renderBillStatus(s)
{
	var result='';
		
	switch(s)
	{
		case 0:
			result='<label class="label label-danger">未付款</label>';
			break;
		case 1:
			result='<label class="label label-warning">未付清</label>';
			break;
		case 2:
			result='<label class="label label-success">已付款</label>';
			break;
		default:
			result='<label class="label label-default">未知</label>';
	}
		
	return result;

}

function renderAuditStatus(s)
{
	var result='';
	
	switch(s)
	{
		case 0:
			result='<label class="label label-info">未审核</label>';
			break;
		case 1:
			result='<label class="label label-success">审核通过</label>';
			break;
		case 2:
			result='<label class="label label-warning">审核不通过</label>';
			break;
		case 3:
			result='<label class="label label-primary">财务通过</label>';
			break;
		case 4:
			result='<label class="label label-danger">财务不通过</label>';
			break;
		default:
			result='<label class="label label-default">未知</label>';
	}
		
	return result;

}


function renderEnergyType(s)
{
	var result='';
		
	switch(s)
	{
		case 0:
			result='<label class="label label-danger">电</label>';
			break;
		case 1:
			result='<label class="label label-info">水</label>';
			break;
		case 2:
			result='<label class="label label-primary">气</label>';
			break;
		case 3:
			result='<label class="label label-success">空</label>';
			break;
		default:
			result='<label class="label label-default">未知</label>';
	}
		
	return result;

}

function saveEnergyRecord()
{

	var r=$('#energy-record-table input.room-energy-record');
	
	//var d=$('#room-table input.room-record-date');
	
	var recArr=[];
	
	for(var i=0;i<r.length;i++)
	{		
		
			var r1={"record":$(r[i]).val(),"id":$(r[i]).attr('data')};
		
			recArr.push(r1);			
			
	}
	
	if(recArr.length==0)
	{
		myNoty('没有需要保存的能源记录。',10001);
		
		return;
	}
	
	var recStr=JSON.stringify(recArr);
	
	$.myPost(basePath+'/energy/updateEnergyRecord.do',
			{
			
			records:recStr
			
			},function(data){
				
				myNoty(data.msg,data.status);
				
				if(data.status===10001 && data.data>0)
				{
			
					$('#energy-record-table').DataTable().ajax.reload();					
					
				}
				
			})
	

}

function getEnergySummary()
{
		
	$.myPost(basePath+'/energy/getEnergySummary.do',{
		
		refId:$('#ref-id').val(),
		idType:$('#id-type').val(),
		energyType:$('#search-energy-type option:selected').val(),
		enterStartDate:$('#search-energy-enter-start-date').val(),
		enterEndDate:$('#search-energy-enter-end-date').val(),
		billStartDate:$('#search-energy-bill-start-date').val(),
		billEndDate:$('#search-energy-bill-end-date').val()
	
		
		
	},function(data){
		
		
		if(data.status===10001)
		{
			renderEnergySummary(data.data);
		}
		else
		{
			$('#energy-summary').empty();
		}
		
	})

}

function renderEnergySummary(data)
{
	var ls='';
	
	for(var i=0;i<data.length;i++)
	{
		
		var days=DateDiff(data[i].minRecordDate,data[i].maxRecordDate);
		
		var daily=data[i].days==0?0:(data[i].amount/days).toFixed(2);
		
		var l='<p>'+renderEnergyType(data[i].energyType)+'：总使用量:'+data[i].amount+
		',使用天数:'+days
		+',日均使用量:'+daily+"</p>";
			
		ls+=l;
	}
	
	
	$('#energy-summary').empty().append(ls);
}


function renderView()
{
	if($('#parkId option').length==2)
	{
		$('#parkId').get(0).selectedIndex=1;
				
	}		
}


