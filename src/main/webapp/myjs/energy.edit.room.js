$(document).ready(function(){
	
	initContractCategory($('#energy-type'),energyType);
	
	$('.date').datepicker(
			
			{todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,autoclose:!0}
			
		)	
	
	var floortree = $("#floor-tree").jstree({
        "core": {
            "animation": true,
            "multiple": false,
            "check_callback": true,
            "themes": { "stripes": false },
            'data': {
                'url': basePath+"/floor/getFloorList.do?status=1",
                "dataType": "json",
                'data': function (node) {
                    return { 'id': node.id };
                }
            }
        }

    }).on("changed.jstree", function (event, data) {
    	
//    	$('#selected-block-id').val('');
//		$('#selected-building-name').html('');
//		$('#selected-floor-name').html('');
//		$('#selected-block-name').html('');
    	
    	$.noty.closeAll();
    	
        if (data.selected.length > 0) {

        	if($('#energy-type').val()=='')
        	{
        		myNoty('请选择能源类型',10002);
        		
        		return;
        	
        	}
        	
        	
        	
        	
        	
        	$('#rooms-select').empty();
        	
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
        	
        	var v=$('#energy-category-container input[name="energy-category"]:checked ').attr('data');
        	
        	//select building
        	if(p1=='#')
        	{
        		$('#selected-building-id').val(id);
        		
        		$('#selected-building-name').html($('#floor-tree').jstree().get_text(id));
        		
        		$('#selected-floor-name').html('');
        		
        		$('#selected-block-id').val('');
        		$('#selected-block-name').html('');
        		
        		$('#floor-selected-type').val(0);
        		
        		$('#building-save').show();
        		
        		$('#building-id').val(id);
        		
        		$('#building-name').val($('#floor-tree').jstree().get_text(id));
        		
        		$('#building-cur-record').val('');
        		
        		//get entry date from floor        		
//        		if(v=='0')
//        		{
        			//getEntryDate(id);	
//        		}
        		
        		$('#energy-category').val(1);
        		
        		loadBuildingEnergyModel(id,$('#energy-type').val());
        		       		
        	}
        	//select floor
        	if(p2=='#')
        	{
        		$('#selected-building-id').val(p1);
        		$('#selected-building-name').html($('#floor-tree').jstree().get_text(p1));
        		
        		$('#selected-floor-name').html($('#floor-tree').jstree().get_text(id));
        		
        		$('#selected-block-id').val('');
        		$('#selected-block-name').html('');
        		
        		$('#floor-selected-type').val(1);
        		       		
        	}
        	//select block
        	if(p3=='#')
        	{	
        		$('#selected-block-id').val(id);
        		$('#selected-building-id').val(p2);
        		$('#selected-building-name').html($('#floor-tree').jstree().get_text(p2));
        		$('#selected-floor-name').html($('#floor-tree').jstree().get_text(p1));
        		$('#selected-block-name').html($('#floor-tree').jstree().get_text(id));
        		$('#floor-selected-type').val(2);
        		$('#energy-category').val(0);

        		if($('#record-date').val()=='')
    			{
    				myNoty('请先选择楼。',10002);
    				
    				return;
    			}
        		
        		if($('#building-isentry').html()=='未录')
    			{
    				myNoty('请先录入楼读数。',10002);
    				
    				return;
    			}
        		
        		$('#rooms-table tbody').empty();
        		
        		$('#rooms-table').DataTable().ajax.reload();//.columns.adjust().responsive.recalc();
        		
//        		if(v=='2')
//        		{
        			
        			
        			//loadrooms(id);
//        		}
        			
        		
        		
        	}
        	
        	renderEntryEditView();
        	
          
        }
    }).jstree();
	
		
	$('#rooms-select').on('click',function(){
				
		var s=$('#rooms-select option:selected');
		
		$('#room-id').val(s.val());
		
		$('#room-name').val(s.text());
		
		loadLastRecord(s.val(),0,$('#energy-type option:selected').val());
		
		$('#floor-selected-type').val(3);
		
		$('#room-cur-record').val('');
		
		renderEntryEditView();
		
	})
	
	
	
		
		var roomstable = $('#rooms-table')
		.DataTable(
				{
					//"order" : [ [ 3, "asc" ] ],// desc
					"order":[],
					"paging" :false,
					"info" : false,
					"dom" : '<"top">rt<"bottom"i><"clear">',
					"type" : "post",
//					 "pagingType": "full_numbers",
					 "deferLoading": 0,
					 "autoWidth": true,
					 //"responsive": true,
					"ajax" : {
						"url" : basePath + "/energy/getRoomEditList.do",
						"data" : function(d) {
							d.refBlockId=$('#selected-block-id').val();
							d.energyType=$('#energy-type option:selected').val();
							d.currentRecordDate=$('#record-date').val();
							d.lastRecordDate=$('#building-last-record-date').val()
						}
					},
					"serverSide" : "true",
					"processing" : "true",
					"columns" : [ 
					              {data : "refItemId",className : "all"},
					              {data : "refItemId"},
					              {data : "refItemId"},
					              {data : "itemName"}, 
					              {data : "record"},
					              {data : "recordDate"}, 
					              {data : "lastRecord"},
					              {data : "lastRecordDate"},
					              {data : "isBilled"}
					              ],
					"columnDefs" : [
					                
					   {
						"targets":0,
						"render":function(data,type,row)
						{
							return $('#selected-building-name').html();
						}
					},
					{
						"targets":1,
						"render":function(data,type,row)
						{
							return $('#selected-floor-name').html();
						}
					},
					{
						"targets":2,
						"render":function(data,type,row)
						{
							return $('#selected-block-name').html();
						}
					},
					{
						"targets":4,
						"render":function(data,type,row)
						{
							var i= '<input type="text" style="width:120px;" placeholder="记录读数" data="'+row.refItemId+'" class="form-control room-record" value="'+data+'" />';
							
							if(row.isBilled==1)
							{
								i= '<input type="text" style="width:120px;" readonly placeholder="记录读数" data="'+row.refItemId+'" class="form-control room-record" value="'+data+'" />';
							}
							
							return i;
						}
					},
					{
						"targets":5,
						"render":function(data,type,row)
						{
							return '<input type="text" style="width:120px;" placeholder="记录日期" data="'+row.refItemId+'" disabled="disabled" class="form-control room-record-date" />';
						}
					},
					{
						"targets":6,
						"render":function(data,type,row)
						{
							if(row.lastRecordDate==null || row.lastRecordDate=='')
							{
								return '<input type="text" style="width:120px;" placeholder="最后结算读数" data="'+row.refItemId+'" class="form-control room-last-record" />';
							}
							else
							{
								return '<input type="text" style="width:120px;" placeholder="最后结算读数" readonly data="'+row.refItemId+'" class="form-control room-last-record" value="'+data+'" />';
							}
							
						}
					},
					{
						"targets":7,
						"render":function(data,type,row)
						{
							if(data==null || data=='')
							{
								return '<input type="text" style="width:120px;" placeholder="最后结算日期" readonly data="'+row.refItemId+'" class="form-control room-last-record-date" value="'+$('#building-last-record-date').val()+'" />';
							}
							else
							{
								return '<input type="text" style="width:120px;" placeholder="最后结算日期" readonly data="'+row.refItemId+'" class="form-control room-last-record-date" value="'+data+'" />';
							}
							
						}
					},
					{
						"targets":[0,1,2,3,4,5,6,7],
						"orderable":false
					},
					{
						"targets":8,
						"visible":false
					}
					
					
					]
				// end of columnDefs

				}).on('preXhr.dt',function(e, settings, data) {

				}).on('xhr.dt',function(e, settings, data) {

//					$('#room-start').attr('disabled','disabled');
//					
//					$('#room-save').removeAttr('disabled');
					
					
				}).on('draw.dt', function() {
				
					if($('#rooms-table input.room-record-date').length==0)
					{
						toggleRoomFunc();
						
						myNoty('此区无有效房间',10002);
						
					}else{
					
						$('#rooms-table input.room-record-date').val($('#record-date').val());
					}					
					
					
				}).on('processing.dt',function(e, settings, processing) {

				}).on('error.dt',function(e, settings, techNote, message) {

				});// end of dataTable



			$('#rooms-table tbody').on('click', 'tr', function() {
			
				$('#rooms-table tr.selected').removeClass('selected');
				
				$(this).addClass('selected');
			
			});
		
			
		
		$('#energy-type').on('change',function(){
			
			var v=$('#energy-type').val();
						
			if(v!='')
			{
				$('.energy-type-title').html('---'+$('#energy-type option:selected').text());
				
//				$('#building-start').show();
			}
			else
			{
				$('.energy-type-title').html('');
				
//				$('#building-start').hide();
			}
			
			resetEnergyEditView();
		})
		
		
		$('#rooms-start').on('click',function(){
			
			$.noty.closeAll();
						
			
			
			if(!myNotySele($('#energy-type')))
			{
				return;
			}
			
			if($('#building-isentry').html()=='未录')
			{
				myNoty('请先录入楼读数。',10002);
				
				return;
			}
			
			if($('#record-date').val()=='' || $('#building-last-record-date').val()=='')
			{
				myNoty('请先选择楼。',10002);
				
				return;
			}
			
			
			if(!myNotyNull('请选择楼层',$('#selected-block-id')))
			{
				return;
			}			
			
			toggleRoomFunc();
			
			$('#rooms-table').DataTable().ajax.reload();//.columns.adjust().responsive.recalc();
			
			
			
		})
		
		$('#rooms-save').on('click',function(){
			
			$.noty.closeAll();
			
//			if(!myNotyRegx($('#rooms-table input.room-record-date'),2))
//			{
//				return;
//			}
			
			if($('#building-last-record-date').val()=='')
			{
				myNoty('请先选择楼',10002);
				
				return;
			}
			
			if(!myNotyRegx($('#rooms-table input.room-record'),1))
			{
				return;
			}
			
			if(!myNotyRegx($('#rooms-table input.room-last-record'),1))
			{
				return;
			}
			
		  	roomsSave();			
			
		})
		
		$('#room-save').on('click',function(){
			
			$.noty.closeAll();
			
			if(!myNotySele($('#energy-type')))
			{
				return;
			}
			
			if($('#room-id').val()=='')
			{
				myNoty('请选择房间',10002);
				
				return;
			}
			
			
			if(!myNotyRegx($('#room-cur-record'),1))
			{
				return;
			}
			
			if(!myNotyRegx($('#room-cur-record-date'),2))
			{
				return;
			}
			
			roomSave();			
			
		})
		
		$('#building-save').on('click',function(){
			
			
			$.noty.closeAll();
			
			if(!myNotySele($('#energy-type')))
			{
				return;
			}
			
			if($('#building-id').val()=='')
			{
				myNoty('请选择楼',10002);
				
				return;
			}
			
			
			if(!myNotyRegx($('#building-cur-record'),1))
			{
				return;
			}
			
			if(!myNotyRegx($('#building-cur-record-date'),2))
			{
				return;
			}
			
			if($('#building-has-last').val()==0)
			{
				if(!myNotyRegx($('#building-last-record'),1))
				{
					return;
				}
			}
			
			var amount=parseFloat($('#building-cur-record').val())-parseFloat($('#building-last-record').val());
			
			if(amount<0)
			{
				myNoty('记录不能小于前次',10002);
				
				$('#building-curr-record').focus();
				
				return;
			}
			
			buildingSave();
			
			
			
		})
		
		
		$('#rooms-cancel').on('click',function(){
			
			toggleRoomFunc();
			
			$('#rooms-table tbody').empty();		

		})
		
		$('#rooms-zero-record').on('click',function(){
			
			$.noty.closeAll();
			
			var bid=$('#building-id').val();
			
			if(bid=='')
			{
				myNoty('先选中楼',10002);
				
				return;
			}
			
			if($('#room-amount-used').val()!=0)
			{
				myNoty('此楼此能源类别使用房间使用量',10002);
				
				return;
			}
			
			var energyType=$('#energy-type').val();
			
			var curRecordDate=$('#building-cur-record-date').val();
			
			var lastRecordDate=$('#building-last-record-date').val();
			
			var hasLastRecord=$('#building-has-last').val();
			
			roomBatchFillZero(bid,energyType,curRecordDate,lastRecordDate,hasLastRecord);
			
		})
		
		
		
		
//		$('#add-room-date').on('click',function(){
//			
//			var d=$('#room-date').val();
//			
//			if(d=='') return;
//			
//			$('#rooms-table input.room-record-date').val(d);
//			
//			
//		})
		
	})
	
	
function roomsSave()
{
	var r=$('#rooms-table input.room-record');
	
	//var d=$('#room-table input.room-record-date');
	
	var recArr=[];
	
	for(var i=0;i<r.length;i++)
	{
		var date=$('#rooms-table input.room-record-date[data="'+$(r[i]).attr('data')+'"]');
		
		var d=date.val();
		
		var lr=date.parent().next().next().html();
		
//		if(d==lr)
//		{
//			myNoty('本期抄表日已录入。',10002);
//						
//			return;
//		}
		
		//var pr=date.parent().next().html();
		var lastRecord=$('#rooms-table input.room-last-record[data="'+$(r[i]).attr('data')+'"]');
		
		var lastRecordDate=$('#rooms-table input.room-last-record-date[data="'+$(r[i]).attr('data')+'"]');
		
		var amount=parseFloat($.trim($(r[i]).val()))-parseFloat(lastRecord.val());
		
		if(amount<0)
		{
			myNoty('记录不能小于前次',10002);
			
			$(r[i]).focus();
			
			return;
		}
		
		
		if($(r[i]).is('[readonly]')==false)
		{
			var r1={"record":$.trim($(r[i]).val()),"amount":0,"refItemId":$(r[i]).attr('data'),"recordDate":d,"refBuildingId":$('#selected-building-id').val(),"isBilled":"0","energyType":$('#energy-type').val(),"energyCategory":0};
		
			recArr.push(r1);			
		}
		if(lastRecord.is('[readonly]')==false)
		{
			var r2={"record":lastRecord.val(),"amount":0,"refItemId":$(r[i]).attr('data'),"recordDate":lastRecordDate.val(),"refBuildingId":$('#selected-building-id').val(),"isBilled":"1","energyType":$('#energy-type').val(),"energyCategory":0};
		
			recArr.push(r2);		
		}
			
	}
	
	if(recArr.length==0)
	{
		myNoty('能源已出帐，不能保存。',10001);
		
		return;
	}
	
	var recStr=JSON.stringify(recArr);
	
	$.myPost(basePath+'/energy/saveEnergyRecord.do',
			{
			energyCategory:0,
				//$('#energy-category').val(),
				//$('#energy-category-container input[name="energy-category"]:checked ').val(),
			energyType:$('#energy-type').val(),
			
			
			records:recStr
			
			},function(data){
				
				myNoty(data.msg,data.status);
				
				if(data.status===10001)
				{
					toggleRoomFunc();
					
					$('#rooms-table input').attr('disabled','disabled');
					
					checkSummary();
					
				}
				
			})
	
	
	
}

function toggleRoomFunc()
{	
	
	$('.e-r-func').toggle();
	
	
	if($('.e-func').attr('disabled')=='disabled')
	{
		$('.e-func').removeAttr('disabled');
	}
	else
	{
		$('.e-func').attr('disabled','disabled');
	}
	
	//$('#sign-datepicker').toggle();
}
/*
function getEntryDate(bid)
{
	$.post(basePath+'/floor/getFloorInfo.do',{floorId:bid},function(data){
		if(data.status===10001)
		{	
			$('#enter-type').val(data.data.energyEnterType);
			
			$('#enter-day').val(data.data.energyEnterDay);
			
			
			//这里进行日期判断，如果未到本次录入日，则沿用上次录入日
			var ed=getRecordDate(data.data.energyEnterType,data.data.energyEnterDay);
			
			var td=formatDate(new Date());
			
			if(DateDiff(td,ed)<=0)
			{
				ed=getLastRecordDate(data.data.energyEnterType,data.data.energyEnterDay);
			}
			
			$('#record-date').val(ed);
			
			$('#building-cur-record-date').val(ed);
			
			$('#building-last-record-date').val(getLastRecordDate(data.data.energyEnterType,data.data.energyEnterDay));
			
			checkSummary();
			
		}
		else
		{
			myNoty(data.msg,data.status);
			
			$('#record-date').val('');
		}
		
		
	})
}*/

//加载楼能源
function loadBuildingEnergyModel(id,energyType)
{

	$.myPost(basePath+'/energy/loadBuildingEnergyModel.do',{id:id,energyType:energyType},function(data){
		
		if(data.status===10001)
		{
			var d=data.data;
			
//			if(d.roomAmountUsed==0 && d.shareType==2)
//			{
//				$('#supply-rooms-ibox').hide();
//				
//				$('#building-ibox').hide();
//				
//				$('#rooms-ibox').hide();
//				
//				myNoty('此楼此能源类型无房间使用量，且无公摊，毋须录入。',10001);
//			}
			
			
			//set enterType,enterDay,currentRecordDate
			$('#enter-type').val(d.energyEnterType);
			
			$('#enter-day').val(d.energyEnterDay);
			
			$('#record-date').val(d.currRecordDate);
			
			$('#building-cur-record').val(d.record);
			
			$('#building-cur-record-date').val(d.currRecordDate);
			
			$('#building-curr-isbilled').val(d.isBilled);
			
			//若已出帐，不能修改
			if(d.isBilled==1)
			{
				//$('#building-cur-record-date').attr('readonly','readonly');
				
				$('#building-cur-record').attr('readonly','readonly');
				
			}
			else
			{
				//$('#building-cur-record-date').removeAttr('readonly');
				
				$('#building-cur-record').removeAttr('readonly');
			}
			
			if(d.lastFeeDate!=null && d.lastFeeDate!='')
			{
				
				$('#building-last-record-date').val(d.lastRecordDate);
				
				$('#building-last-record').val(d.lastRecord);
				
				$('#building-last-record').attr('readonly','readonly');
				
				$('#building-has-last').val(1);
				
			}
			else
			{
				$('#building-last-record-date').val(d.lastRecordDate);
				
				$('#building-last-record').val('');
								
				$('#building-last-record').removeAttr('readonly');
				
				$('#building-has-last').val(0);
			}
		
			//两个都是已出帐隐藏SAVE 
			if(d.isBilled==1 && $('#building-has-last').val()==1)
			{
				$('#building-save').hide();
			}
			else
			{
				$('#building-save').show();
			}
			
			checkSummary();
			
			$('#room-amount-used').val(d.roomAmountUsed);
			
			$('#share-type').val(d.shareType);
			
			//TODO:无房间使用量，可以为所有房间生成0记录
			if(d.roomAmountUsed==0)
			{
				$('#rooms-zero-record').show();
			}
			else
			{
				$('#rooms-zero-record').hide();
			}
			
			if(d.supplyList.length>0)
			{
				
//				var list=[];
//				
//				for(var i=0;i<d.supplyList.length;i++)
//				{
//					var e=d.supplyList[i];
//					
//					var l=[];
//					
//					l.push('"'+e.contractNo+'"');
//					l.push('"'+e.contractStatus+'"');
//					l.push('"'+e.room+'"');
//					l.push('"'+e.contractStartDate+'"');
//					l.push('"'+e.contractEndDate+'"');
//					l.push('"'+e.record+'"');
//					l.push('"'+e.recordDate+'"');
//					l.push('"'+e.refRoomId+'"');
//					
//					list.push('['+l.join(',')+']');
//					//list.push(l);
//				}
//				
//				//JSON.stringify(d.supplyList).replace('{','[').replace('}',']')
//				$('#supply-energy-list').val('['+list.join(',')+']');
//				
//				myNoty($('#supply-energy-list').val(),10002);
				
				$('#supply-rooms-table').DataTable().clear();
				//$('#supply-rooms-table').DataTable().data()=d.supplyList;
				$('#supply-rooms-table').DataTable().rows.add(d.supplyList).draw();
				//$.parseJSON('['+list.join(',')+']')
				
				//$('#supply-rooms-table').DataTable().reload();
				
				$('#supply-rooms-ibox').show();
				
				$('#building-ibox').hide();
				
				
				
				
			}
			
		
		}
		else
		{
			$('#building-save').hide();
			
			myNoty('楼信息有误，不能录入能源记录，请联系管理员。',10002);
		}
	})
	
	
}


function getWeekDay(w)
{
	var d = new Date(),
    dd = d.getDay(),
    friday = new Date((w - dd)*(3600*24*1000)+d.getTime());
	
	return friday;
//	myNoty(dd,10002);
//	myNoty(friday,10002);
}

function getLastWeekDay(w)
{
	var d = new Date(),
    dd = d.getDay(),
    friday = new Date((w - dd-7)*(3600*24*1000)+d.getTime());
	
	return friday;
	
}

function getRecordDate(type,day)
{	
	var dd=new Date();
	
	var d='';
	
	
	var ed='';
	
	if(type==0)
	{
		d=day;
		
		ed=dd.getFullYear()+'-'+PrefixInteger((dd.getMonth()+1),2)+'-'+PrefixInteger(d,2);
	}
	else
	{
		var wd=getWeekDay(day);
		
		ed=wd.getFullYear()+'-'+PrefixInteger((wd.getMonth()+1),2)+'-'+PrefixInteger(wd.getDate(),2);
	}
	
	
	
	return ed;
}


//取上一次录入日
function getLastRecordDate(type,day)
{
//	var type=$('#enter-type').val();
//	
//	var day=$('#enter-day').val();
	
//	if(type=='')
//	{
//		return '';
//	}
	
	var dd=new Date();
	
	var d='';
	
	var ed='';
	
	if(type==0)
	{
		d=day;
		
		var m=dd.getMonth()+1;
		
		var y=dd.getFullYear();
		
		if(m==1)
		{
			m=12;
			
			y=y-1;
		}
		
		ed=y+'-'+PrefixInteger(m,2)+'-'+PrefixInteger(d,2);
	}
	else
	{
		var wd=getLastWeekDay(day);
		
		ed=wd.getFullYear()+'-'+PrefixInteger((wd.getMonth()+1),2)+'-'+PrefixInteger(wd.getDate(),2);
	}
	
	
	return ed;
	
	
}

function loadrooms(blockid)
{

	$.post(basePath+'/floor/getRooms.do',{areaId:blockid,floorStatus:''},function(data){
		
		
		if(data.status===10001)
		{
			
			
			for(var i=0;i<data.data.length;i++)
			{
				var oOption=document.createElement('option');
				
				oOption.value=data.data[i].id;
				
				oOption.text=data.data[i].roomNum;
				
				$('#rooms-select').append(oOption);
				
			}
		}
		
	})	

}


function checkSummary()
{
	
//	var v=$('#energy-category-container input[name="energy-category"]:checked ').attr('data');
//	
//	if(v=='2') return;
	
	var bid=$('#selected-building-id').val();
	
	if(bid=='') return;
	
	var energyType=$('#energy-type').val();
	
	if(energyType=='') return;
	
	var recordDate=$('#record-date').val();
	
	if(recordDate=='') return;
	
	var lastRecordDate=$('#building-last-record-date').val();
	
	if(lastRecordDate=='') return;
	
	$.post(basePath+'/energy/checkBuildingEnergyRecordSummaryByDate.do',{
		energyType:energyType,
		refBuildingId:bid,
		recordDate:recordDate,
		lastRecordDate:lastRecordDate
		
		
	},function(data){
				
		if(data.status===10001)
		{
			var d=data.data;
			if(d.buildingIsEntry==1)
			{
				$('#building-isentry').html('已录');	
				$('#building-isentry').removeClass('btn-danger');
				$('#building-isentry').addClass('btn-success');
			}
			else
			{
				$('#building-isentry').html('未录');
				$('#building-isentry').removeClass('btn-success');
				$('#building-isentry').addClass('btn-danger');
			}
			
			$('#room-count').html(d.roomCount);
			
			$('#room-entry-count').html(d.roomEntryCount);
			
			$('#building-sum').html(d.buildingSum);
			
			$('#rooms-sum').html(Math.round(d.roomsSum));
			
			if(d.roomsSum>d.buildingSum)
			{
				$('#used-warning').show();
			}
			else
			{
				$('#used-warning').hide();
			}
			
			
		}
		
	})
}

function loadLastRecord(rid,energyCategory,energyType)
{
	
	
	
	$.post(basePath+'/energy/getLastRecord.do',{id:rid,energyType:energyType,energyCategory:energyCategory},function(data){
		
		if(data.status===10001)
		{
			if(energyCategory===0)
			{
				$('#room-last-record').val(data.data.record);
				
				$('#room-last-record-date').val(data.data.recordDate);
					
			}else
			{
			
				$('#building-last-record').val(data.data.record);
				
				$('#building-last-record-date').val(data.data.recordDate);
				
				$('#building-has-last').val(1);
				
			}
		}
		else
		{
			myNoty("无最后结算数据，请输入",data.status);
			
			$('#room-last-record').val('');
			
			$('#room-last-record-date').val('');			
			
			$('#building-last-record').val('');
			
			//$('#building-last-record-date').val(getLastRecordDate());
			
			$('#building-last-record').removeAttr('readonly');
			
			if(energyCategory===1)
			{
				$('#building-has-last').val(0);
			}
//			$('#building-last-record-date').removeAttr('readonly');
						
			
		}
		
	})

}

function roomSave()
{
	
	var recArr=[];
	
	var recArr1={"record":$('#room-cur-record').val(),"amount":0,"refItemId":$('#room-id').val(),"recordDate":$('#room-cur-record-date').val(),"refBuildingId":$('#selected-building-id').val()};	

	recArr.push(recArr1);
	
	var recStr=JSON.stringify(recArr);
	
	$.post(basePath+'/energy/saveEnergyRecord.do',
			{
			energyCategory:0,//$('#energy-category-container input[name="energy-category"]:checked ').val(),
			energyType:$('#energy-type').val(),
			
			records:recStr
			
			},function(data){
				
				myNoty(data.msg,data.status);
				
				if(data.status===10001)
				{
					//toggleRoomFunc();
					checkSummary();
				}
				
			})

}

function buildingSave()
{
	
	var recArr=[];
	
	
	if($('#building-curr-isbilled').val()==0)
	{
		var recArr1={"record":$('#building-cur-record').val()+"","amount":0,"refItemId":$('#building-id').val(),"recordDate":$('#building-cur-record-date').val(),"refBuildingId":$('#selected-building-id').val(),"isBilled":"0","activeRoomCount":$('#room-count').html(),"energyType":$('#energy-type').val(),"energyCategory":1};	

		recArr.push(recArr1);
	}
	
	if($('#building-has-last').val()==0)
	{
		var recArrLast={"record":$('#building-last-record').val()+"","amount":0,"refItemId":$('#building-id').val(),"recordDate":$('#building-last-record-date').val(),"refBuildingId":$('#selected-building-id').val(),"isBilled":"1","activeRoomCount":$('#room-count').html(),"energyType":$('#energy-type').val(),"energyCategory":1};
		
		recArr.push(recArrLast);
	}
	
	
	var recStr=JSON.stringify(recArr);
	
	$.post(basePath+'/energy/saveEnergyRecord.do',
			{
			energyCategory:1,//$('#energy-category-container input[name="energy-category"]:checked ').val()
			energyType:$('#energy-type').val(),
			
			
			records:recStr
			
			},function(data){
				
				myNoty(data.msg,data.status);
				
				if(data.status===10001)
				{
					//toggleRoomFunc();
					//成功后设置为1
					$('#building-has-last').val(1);
					
					$('#building-last-record').attr('readonly','readonly');
					
					checkSummary();
				}
				
			})

}

function renderEntryEditView()
{
	$('#supply-rooms-ibox').hide();
	//选择树的类型
	var type=$('#floor-selected-type').val();	
	
	if(type==='0')
	{
		$('#building-ibox').show();
		
		$('#rooms-ibox').hide();
		
		$('#room-ibox').hide();
		
		//$('#record-date').show();
		
		//$('#rooms-container').hide();
		
		$('#block-select-container').show();
		
//		$('#entry-summary').show();
	}
	if(type==='1')
	{
		$('#building-ibox').hide();
		
		$('#rooms-ibox').hide();
		
		$('#room-ibox').hide();
		
		//$('#rooms-container').hide();
	}
	if(type==='2')
	{
		$('#rooms-ibox').show();
		
		$('#building-ibox').hide();
		
		$('#room-ibox').hide();
		
		//$('#record-date').show();
		
		$('#block-select-container').show();
		
		//$('#rooms-container').show();
		
//		$('#entry-summary').show();
	}
	
	
	if(type==='3')
	{
		$('#building-ibox').hide();
		
		$('#rooms-ibox').hide();
		
		$('#room-ibox').show();
		
		//$('#record-date').hide();
		
		$('#block-select-container').show();
		
		//$('#rooms-container').show();
		
//		$('#entry-summary').hide();
	}	
	
	
	
	
	if($('#energy-type').val()=='')
	{
		$('#rooms-start').hide();
		
		$('#building-save').hide();
		
		$('#room-save').hide();
	}
	else
	{
		$('#rooms-start').show();
		
		$('#building-save').show();
		
		$('#room-save').show();
	}
}

function roomBatchFillZero(bid,energyType,curRecordDate,lastRecordDate,hasLastRecord)
{
	$.myPost(basePath+'/energy/roomBatchFillZero.do',{
		refBuildingId:bid,
		energyType:energyType,
		curRecordDate:curRecordDate,
		lastRecordDate:lastRecordDate,
		hasLastRecord:hasLastRecord
		
		
	},function(data){
		
		
		myNoty(data.msg,data.status);
		
		if(data.status==10001)
		{
			checkSummary();
		}
		
		
	})
	
	
}

function resetEnergyEditView()
{
	
	$('#supply-rooms-ibox').hide();
	
	$('#building-ibox').hide();
	
	$('#rooms-ibox').hide();
	
	$('#room-ibox').hide();
	
	$('#selected-building-id').val('');
	$('#selected-building-name').html('');
	
	$('#selected-floor-name').html('');
	
	$('#selected-block-id').val('');
	$('#selected-block-name').html('');
	
	$('#floor-selected-type').val('');
		
	$('#building-id').val('');
	
	$('#building-name').val('');
	
	$('#building-cur-record').val('');
	
	$('#record-date').val('');

	
	
	$('#building-isentry').html('');
	
	$('#building-isentry').removeClass('btn-danger');
	
	$('#building-isentry').removeClass('btn-success');

	$('#room-count').html('');
	
	$('#room-entry-count').html('');
	
	$('#building-sum').html('');
	
	$('#rooms-sum').html('');
	
	

}