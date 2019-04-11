$(document).ready(function(){
		
	initContractStatusSelect('#contract-status-sele');
	
	renderContractForm();
		
		loadRoomInfo();
		
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
//	        	if($("#floor-tree").jstree().is_leaf(id))
//	        		{
//	        			$('#rooms-select').height($('#floor-tree').height());
//	        		
//	        			loadRooms(id);
//	        		}

	            //if leaf,get parents ,show in editor
	            
	        	var p1=$('#floor-tree').jstree().get_parent(id);
	        	
	        	var p2=$('#floor-tree').jstree().get_parent(p1);
	        	
	        	var p3=$('#floor-tree').jstree().get_parent(p2);
	        	
	        	if(p3=='#')
	        	{
//	        		myNoty($('#floor-tree').jstree().get_text(id),10001);
//	        		myNoty($('#floor-tree').jstree().get_text(p1),10001);
//	        		myNoty($('#floor-tree').jstree().get_text(p2),10001);
//	        		
	        		
	        		
	        		$('#building').val($('#floor-tree').jstree().get_text(p2));
	        		
	        		$('#floor').val($('#floor-tree').jstree().get_text(p1));
	        		
	        		$('#block').val($('#floor-tree').jstree().get_text(id));
	        		$('#rooms-select').height($('#floor-tree').height());
	        		
        			loadRooms(id);
	        		
	        	}
	        	
	          
	        }
	    }).jstree();
		
		
		$('#company-search').on('click',function(){
		
			
			if(myNotyNull('无园区信息，不能查找公司，请退出重新登陆。',$('#park-id')))
			{				
				companySearch($('#park-id').val(),$.trim($('#company-search-value').val()));	
			}
						
			
		})
		
		
		$('#rooms-select').on('click',function(){
		
			
			var s=$('#rooms-select option:selected').val();
			
			if(s!='')
			{
				$('#ref-room-id').val(s);
				
				loadRoomInfo();
			}
			
		})
		
		$('#company-search-select').on('click',function(){
		
			
			var s=$('#company-search-select option:selected');
			
			$('#ref-company-id').val(s.val());
			
			$('#company-name').val(s.text());
			
			$('#company-contacts-name').val(s.attr('contact'));
			
			$('#company-contacts-phone').val(s.attr('mobile'));
			
			$('#park-id').val(s.attr('parkId'));
			
			$('#park').val(s.attr('parkName'));
				
			
			
			
		})
		
		
		
		$('#contract-save').on('click',function(){
			
			$.noty.closeAll();
			
			//外包不需要
			if($('#contract-category option:selected').val()!=contractCategory['sub'][0].id)
			{
				if(!myNotyRegx($('#ref-company-id'),3))
				{
					$('#compamy-modal').focus();
					
					return;
				}
				
				if(!myNotyRegx($('#ref-room-id'),3))
				{
					$('#room-modal').focus();
					
					return;
				}
			}
			else
			{
				if(!myNotyRegx($('#pay-method'),3))
				{
					$('#pay-method').focus();
					
					return;
				}
			}
			
			if(!myNotyRegx($('.check-date'),2))
			{
				return;
			}
			
			//TODO:前端控件控制，冗余检测
			var dd=DateDiff($('#contract-start-date').val(),$('#contract-end-date').val());
			
			if(dd<0)
			{
				myNoty('开始日期不能大于结束日期',10002);
				
				return;
			}
			
			var fd=$('#last-balance-date').val();
			
			if(fd!='')
			{
				if(!myNotyRegx($('#last-balance-date'),2))
				{
					return;
				}
			}
			
			var fdd=DateDiff($('#contract-start-date').val(),$('#last-balance-date').val());
			
			if(fdd<0 && $('#contract-category').val()!=contractCategory['energy'][0].id)
			{
				myNoty('最后结算日不能小于开始日期',10002);
				
				return;
			}
			
			
			fdd=DateDiff($('#last-balance-date').val(),$('#contract-end-date').val());
			
			if(fdd<0)
			{
				myNoty('最后结算日不能大于结束日期',10002);
				
				return;
			}
			
			
			//物业，能源日期不能超出对应租赁日期
			
			var leaseStart=$('#lease-contract-start-date').val();
			
			var leaseEnd=$('#lease-contract-end-date').val();
			
			if($('#contract-category').val()==contractCategory['property'][0].id ||
					$('#contract-category').val()==contractCategory['energy'][0].id
			)
			{
				//开始日期与租赁开始日期>=0,ok
				var d1=DateDiff(leaseStart,$('#contract-start-date').val());
				//开始日期与租赁结束日期,>0,ok
				var d2=DateDiff($('#contract-start-date').val(),leaseEnd);
				//>0,ok
				var d3=DateDiff(leaseStart,$('#contract-end-date').val());
				//>=0,ok
				var d4=DateDiff($('#contract-end-date').val(),leaseEnd);
				
				if(d1<0 || d2 <=0 || d3<=0 || d4<0)
				{
					myNoty('开始结束日期不能超出租赁合同日期<br />'+leaseStart+' 至 '+leaseEnd,10002);
					
					return;
				}
				
				
			}
			
			if(!myNotyRegx($('#contract-deposit'),1))
			{
				return;
			}			
			
			
			save();
		})
		
		
		
//		$('#status-check').on('click',function(){
//			
//			if($('#status-check').prop('checked'))
//			{	
//				$('#terminate-group').show();
//			}
//			else
//			{
//				$('#terminate-group').hide();
//			}
//			
//		})
		
//		$('#contract-start-date').on('change',function(){
//			
//			$('#energy-start-date').val($(this).val());
//			
//		})
//		
//		
//		$('#contract-end-date').on('change',function(){
//			
//			$('#energy-end-date').val($(this).val());
//			
//		})
		
		$('#contract-submit-audit').on('click',function(){
			
			var category=$('#contract-category option:selected').val();
			
			//非能源合同
			if(category!=contractCategory['energy'][0].id)
			{
				var s=$('#instalment-status').val();
				
				if(s!=instalmentStatus['finished'][0].id)
				{
					myNoty('分期未完整，不能审批',10002);
					
					return;
				}
			}
			else
			{
				if($('#energy-id').val()=='')
				{
					myNoty('能源信息没有录入，不能审批',10002);
					
					return;
				}
				
				//05-19增加能源日期判断
				
				var rd=$('#energy-power-record-date').val();
				
				var sd=$('#contract-start-date').val();
				
				var td=formatDate(new Date());
			
				
				if(DateDiff(rd,sd)>1 || DateDiff(rd,td)<1)
				{
					myNoty('结算日范围为合同前一天至昨天。',10002);
					
					return;
				}
			}
			
			
			submitAudit();
		})
		
		
		
})

function save()
{
	$.myPost(basePath+'/contract/save.do',{
		
		id:$('#id').val(),
		refContractLeaseId:$('#ref-lease-id').val(),
		contractNo:$('#contract-no').val(),
		paperNo:$('#paper-no').val(),
		refCompanyId:$('#ref-company-id').val(),
		companyName:$.trim($('#company-name').val()),
		companyRegistrationDate:$('#company-registration-date').val(),
		companyContactsName:$.trim($('#company-contacts-name').val()),
		companyContactsPhone:$.trim($('#company-contacts-phone').val()),
		refRoomId:$('#ref-room-id').val(),
		refParkId:$('#park-id').val(),
//		refBuildingId:$('#ref-building-id').val(),
		park:$('#park').val(),
		building:$('#building').val(),
		floor:$('#floor').val(),
		block:$('#block').val(),
		room:$('#room').val(),
		contractStartDate:$('#contract-start-date').val(),
		contractEndDate:$('#contract-end-date').val(),
		contractSignDate:$('#contract-sign-date').val(),
		//被拒绝或者初始可以保存，被拒绝后编辑保存，初始化状态
		contractStatus:contractStatus['init'][0].id,
			//$('#contract-status-sele option:selected').val(),
		auditStatus:$('#contract-audit-status option:selected').val(),
		contractType:$('#contract-type').val(),
		contractCategory:$('#contract-category option:selected').val(),
		terminateReason:$('#terminate-reason').val(),
		lastBalanceDate:$('#last-balance-date').val(),
		contractPayMethod:$('#pay-method').val(),
		contractNotes:$('#contract-notes').val(),
		deposit:$('#contract-deposit').val()
		
	},function(data){
		
		
		myNoty(data.msg,data.status);
		
		if(data.status===10001)
		{
			refreshContractList();
			
			if($('#contract-no').val()=='')
			{
				
				var url=basePath+'/contract/edit.do?id='+data.data+'&category='+$('#contract-category option:selected').val()+'&ro='+$('#read-only').val();
				
				window.parent.resetActiveIframe(url,$('#company-name').val()+$('#contract-category option:selected').text()+'编辑')
				
				window.location.href=url				
			}
			else
			{
				window.location.reload();
			}
		}
		
		
	})
}

function submitAudit()
{
		
	swal({
        title: '此合同将提交审批，确定吗？',
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: true
        }, function () {
        	
        	
        	$.myPost(basePath+'/contract/submitAudit.do',{id:$('#id').val()},function(data){
    			
    			myNoty(data.msg,data.status);
    			
    			if(data.status===10001)
    			{
    				$('#contract-status-sele').val(contractStatus['financeAuditing'][0].id);
    				
    				renderStatus();
    				//
    				if($('#contract-category option:selected').val()==contractCategory['energy'][0].id)
    				{
    					renderEnergyView();
    				}
    				else
    				{
    					renderInstalmentsView();
    				}
    				
    				refreshContractList();
    				
    				
    			}	    			
    			
    		})
        	
        }
    );
		
		
}




function renderContractForm()
{
	
	//get company info
	
	$('#contract-start-date').val(formatDate($('#contract-start-date').val()));
	
	$('#contract-end-date').val(formatDate($('#contract-end-date').val()));
	
	$('#lease-contract-start-date').val(formatDate($('#lease-contract-start-date').val()));
	
	$('#lease-contract-end-date').val(formatDate($('#lease-contract-end-date').val()));
	
	$('#contract-sign-date').val(formatDate($('#contract-sign-date').val()));
	
	$('#terminate-date').val(formatDate($('#terminate-date').val()));
	
	$('#contract-status-sele').val($('#contract-status').val());
	
	if($('#last-balance-date').val()!='')
	{
		$('#last-balance-date').val(formatDate($('#last-balance-date').val()));
	}
	
	
	
	//if create lease, and from intention
	//$('#id').val()=='' &&
	if( $('#contract-category').val()==contractCategory['lease'][0].id ||
			$('#contract-category').val()==contractCategory['service'][0].id
		
	)
//	{
//		$('#company-select').hide();
//		
//		$('#company-label').show();
//		
//		//$('#company-name').removeAttr('disabled');
//	}
//	else
	{
		$('#company-select').show();
		
		$('#company-label').hide();
	
		
		if($('#contract-category').val()==contractCategory['sub'][0].id)
		//if($('#ref-company-id').val()=='')
		{
			$('#company-name').removeAttr('disabled');
		}
		
	}
	
	//已终止，显示
//	if($('#status-check').prop('checked'))
//	{
//		$('#terminate-group').show();
//		
//		$('#contract-save').hide();
//	}
//	else
//	{
//		$('#terminate-group').hide();
//		
//		$('#contract-save').show();
//	}
	
	
	if($('#contract-category').val()!=contractCategory['lease'][0].id &&
			$('#contract-category').val()!=contractCategory['service'][0].id		
	)
		//|| $('#contract-no').val()!=''
	{
		$('#room-select').hide();
		
		$('#room-label').show();
	}
	else
	{
		$('#room-select').show();
		
		$('#room-label').hide();
	}
	
	//外包，不需要房间
	if($('#contract-category option:selected').val()==contractCategory['sub'][0].id)
	{
		$('.room-info').hide();
		
		$('#company-name').removeAttr('disabled');
		
		$('#company-contacts-name').removeAttr('readonly');
		
		$('#company-contacts-phone').removeAttr('readonly');
		
		$('#paymethod-container').show();
		
	}
	
	//能源不需要最后结算日
	if($('#contract-category').val()==contractCategory['energy'][0].id)
	{
		$('#last-balance-container').hide();
		
	}
	//其他合同不需要押金
	if($('#contract-category').val()!=contractCategory['lease'][0].id)
	{
		$('#deposit-container').hide();
		
	}
	
	if($('#contract-status-sele option:selected').val()==contractStatus['parkerAccept'][0].id)
	{
		$('#contract-status-str').show();
		
		$('#contract-status-str').val(renderContractStatus3(
				
				$('#contract-start-date').val(),$('#contract-end-date').val()
		
		));
		
	}
		
	renderStatus();
	
}

function loadRooms(id)
{
	$.myPost(basePath+'/floor/getRooms.do',{areaId:id,floorStatus:'',activated:'1'},function(data){
		//floorStatus:'(0,2)'
		
		if(data.status===10001)
		{
			$('#rooms-select').empty();
			
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


function loadRoomInfo()
{
	var rid=$('#ref-room-id').val();
	if(rid=='')
		return;
	
	$.post(basePath+'/floor/getRoom.do',{roomId:rid},function(data){
		
		
		if(data.status===10001)
			{
				//set room info
				var r=data.data;
				
				$('#room').val(r.roomNum);
				
				$('#build-area').val(r.buildArea);
				
				$('#used-area').val(r.userArea);
				
				$('#monthly-price').val(r.monthPrice);
				
				$('#daily-price').val(r.dayPrice);
				
				$('#orientation').val(r.orientation);
				
				$('#located').val(r.located);
				
				$('#layer-height').val(r.layerHigh);
				
				$('#decoration').val(r.renovation);
				
				
			
			
			}
		else
		{
			myNoty('取房间信息错误。',10002);
		}
		
		
	})
	
	
}


function companySearch(parkId,searchValue)
{

	$.myPost(basePath+'/etopCompany/searchCompanyInPark.do',{parkId:parkId,searchValue:searchValue},function(data){
	
	if(data.status===10001)
	{
		//render select
		$('#company-search-select').empty();
		
		for(var i=0;i<data.data.length;i++)
		{
			var oOption=document.createElement('option');
			
			oOption.value=data.data[i].companyId;
			
			oOption.text=data.data[i].companyName;
			
			$(oOption).attr('contact',data.data[i].contact);
			
			$(oOption).attr('mobile',data.data[i].mobile);
			
			$(oOption).attr('parkId',data.data[i].parkId);
			
			$(oOption).attr('parkName',data.data[i].parkName);
			
			$('#company-search-select').append(oOption);
			
		}
		
	}
	
	
	
})	
}



//TODO:terminate contract
function terminateContract()
{
	var title='确定要申请终止此合同吗？';
	
	var type='warning';
	
	if($('#contract-category option:selected').val()==contractCategory['lease'][0].id)
	{
		title='终止租赁合同，同时将终止该公司相关物业、能源合同，继续申请吗？';
		
		//type='danger';
	}
	
	
	swal({
        title: title,
        type: type,
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: true
        }, function () {
        	$.myPost(basePath+'/contract/terminateContract.do',{
        		id:$('#t-contract-id').val(),
        		tDate:$('#t-date').val(),
        		reason:$('#t-reason').val()
        		
        		
        	},function(data){
    			    			
    			myNoty(data.msg,data.status);
    			
    			if(data.status===10001)
    			{
    				//$('#status-check').prop('checked',true);
    				
    				$('#contract-status-sele').val(contractStatus['terminateAuditing'][0].id);
    				
    				//$('#contract-status-str').val("终止审批中");
    				
    				renderStatus();
    				
    				$('#terminate-date').val($('#t-date').val());
    				
    				$('#terminate-reason').val($('#t-reason').val());
    				
    				
    				$('#terminateModal').modal('hide');
    				
    				refreshContractList();
    			}
    			
    			
    		})
        }
    );

}


function delContract()
{
	var title='确定要申请删除此合同吗？';
	
	var type='warning';
	
	
	swal({
        title: title,
        type: type,
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: true
        }, function () {
        	$.myPost(basePath+'/contract/delContract.do',{
        		id:$('#t-contract-id').val(),
        		tDate:$('#t-date').val(),
        		reason:$('#t-reason').val()
        		
        		
        	},function(data){
    			    			
    			myNoty(data.msg,data.status);
    			
    			if(data.status===10001)
    			{
    				//$('#status-check').prop('checked',true);
    				
    				$('#contract-status-sele').val(contractStatus['deleteAuditing'][0].id);
    				
    				//$('#contract-status-str').val("终止审批中");
    				
    				renderStatus();
    				
    				$('#terminate-date').val($('#t-date').val());
    				
    				$('#terminate-reason').val($('#t-reason').val());
    				
    				
    				$('#terminateModal').modal('hide');
    				
    				refreshContractList();
    			}
    			
    			
    		})
        }
    );

}

