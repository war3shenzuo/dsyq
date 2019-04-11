$(document).ready(function(){

	initContractStatusSelect('#search-contract-status');
	
	initContractCategory($('#search-contract-category'),contractCategory);
	
		$('.date').datepicker(
				
			{todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,autoclose:!0}
			
		)
		

		
		var contracttable = $('#contract-table')
		.DataTable(
				{
					"order" : [ [ 7, "desc" ] ],// desc
					"paging" : true,
					"info" : true,
					"dom" : '<"top">rt<"bottom"ip><"clear">',
					"type" : "post",
					 "pagingType": "full_numbers",
					 "deferLoading": 0,
					 //"responsive": true,
					 //"autoWidth": true,
					"ajax" : {
						"url" : basePath + "/contract/getContractList.do",
						"data" : function(d) {
							d.searchValue=$('#search-value').val();
							d.startDate=$('#search-start-date').val();
							d.endDate=$('#search-end-date').val();
							d.category=$('#search-contract-category').val();
							d.refParkId=getParkCondition();
							d.refBuildingId=$('#building-sele').val();
							d.refFloorId=$('#floor-sele').val();
							d.refBlockId=$('#block-sele').val();
							d.room=$('#room-num').val();
								//$('#parkId option:selected').val();
							d.contractStatus=$('#contract-status-selected').val();
								//getContractStatusCondition();
						}
					},
					"serverSide" : "true",
					"processing" : "true",
					"columns" : [ 
					              {data : "id",className : "all"},
					              {data : "contractCategory",className : "all"},
					              {data : "contractNo",className : "desktop"},
					              {data : "companyName",className : "all"}, 
					              {data : "building"},
					              {data : "floor"},
					              {data : "block"},
					              {data : "room"},
					              {data : "contractStartDate",className : "desktop"},
					              {data : "contractEndDate",className : "desktop"},
					              {data : "lastBalanceDate",className : "desktop"},
					              {data : "createdAt",className : "desktop"},
					              {data : "contractStatus",className : "desktop"},
//					              {data	: "auditStatus"},
					              {data : "id",className : "desktop"},
					              {data : "refContractLeaseId"},
					              {data : "contractCategory"},
					              {data : "refCompanyId"},
					              {data : "refRoomId"}
					              ],
					"columnDefs" : [
					                {
					                	"targets": 0,
					    	            "data": null,
					    	            "orderable":false,
					    	            "render" : function(data,type, row) {
					    	            	
					    	            	if (row.contractCategory==contractCategory['lease'][0].id  &&  row.contractStatus==contractStatus['parkerAccept'][0].id)
					    	            	{					    	            	
					    	            		return '<input type="checkbox" data="'+data+'" category="'+row.contractCategory+'" company="'+row.refCompanyId+'" room="'+row.refRoomId+'" contractStatus="'+row.contractStatus+'">';
					    	            	}
					    	            	else
					    	            	{
					    	            		return '';
					    	            	}
					    	            }					                	
					                	
					                },
					                {
					                	"targets": 1,
					    	            "data": null,
					    	            //"orderable":false,
					    	            "render" : function(data,type, row) {
					    	            	return renderContractCategory(row.contractCategory); 
					    	            	
					    	            	//contractCategory[row.contractCategory];
					    	            }					                	
					                	
					                },
//					                {
//					                	"targets": 9,
//					    	            "data": null,
//					    	            //"orderable":false,
//					    	            "render" : function(data,type, row) {
//					    	            	return renderAuditStatus(data);
//					    	            }					                	
//					                	
//					                },

					{
						"targets" : 13,
						"orderable" : false,
						"data" : "download_link",
						"render" : function(data, type,	row) {
							
							var s='<a class="btn btn-sm btn-info" href="javascript:void(0)" onClick="javascript:editContract(\''
								+ data
								+ '\',\''+row.contractCategory+'\',\''+$.trim(row.companyName)+'\')">查看</a>';
							
							if(checkContractStatusEditable(row.contractStatus) && $('#read-only').val()=='false')
							{
								s='<input type="hidden" value="'+row.refContractLeaseId+'"><a class="btn btn-sm btn-info" href="javascript:void(0)" onClick="javascript:editContract(\''
										+ data
										+ '\',\''+row.contractCategory+'\',\''+$.trim(row.companyName)+'\')">编辑</a>';
										
//								if($('#read-only').val()=='false')
//								{
	//									' | <a class="btn btn-sm btn-primary" href="javascript:void(0)" onClick=javascript:generateBill("'
	//										+ data + '")>出帐</a>'+
//											s+=' | <a class="btn btn-sm btn-danger" href="javascript:void(0)" onClick=javascript:removeContract("'
//											+ data + '")>删除</a>';
//								}
							}
							
							if(row.contractStatus==contractStatus['financeAuditing'][0].id && $('#finance-audit').val()=='1')
							{
								s+=' | <a class="btn btn-sm btn-primary" href="javascript:void(0)" onClick=javascript:financeAudit("'
									+ data + '",1)>财务审批</a>'+
									' | <a class="btn btn-sm btn-danger" href="javascript:void(0)" onClick=javascript:financeAudit("'
									+ data + '",0)>拒绝</a>';
							}
							if(row.contractStatus==contractStatus['parkerAuditing'][0].id && $('#parker-audit').val()=='1')
							{
								s+=' | <a class="btn btn-sm btn-primary" href="javascript:void(0)" onClick=javascript:parkerAudit("'
									+ data + '",1)>园长审批</a>'+
									' | <a class="btn btn-sm btn-danger" href="javascript:void(0)" onClick=javascript:parkerAudit("'
									+ data + '",0)>拒绝</a>';
							}
							
							if(row.contractStatus==contractStatus['terminateAuditing'][0].id && $('#parker-audit').val()=='1')
							{
								s+=' | <a class="btn btn-sm btn-primary" href="javascript:void(0)" onClick=javascript:terminateAudit("'
									+ data + '",'+row.contractCategory+',1)>同意终止</a>'+
									' | <a class="btn btn-sm btn-danger" href="javascript:void(0)" onClick=javascript:terminateAudit("'
									+ data + '",'+row.contractCategory+',0)>拒绝终止</a>';
							}
							
							if(row.contractStatus==contractStatus['deleteAuditing'][0].id && $('#parker-audit').val()=='1')
							{
								s+=' | <a class="btn btn-sm btn-primary" href="javascript:void(0)" onClick=javascript:deleteAudit("'
									+ data + '",'+row.contractCategory+',1)>同意删除</a>'+
									' | <a class="btn btn-sm btn-danger" href="javascript:void(0)" onClick=javascript:deleteAudit("'
									+ data + '",'+row.contractCategory+',0)>拒绝删除</a>';
							}
							
							return s;
						}
					},
					{
						"targets":12,
						"orderable" : false,
						"render":function(data,type,row)
						{
							return renderContractStatus(data,row.contractStartDate,row.contractEndDate);
						}
					},
					{
						"targets":[14,15,16,17],
						"visible":false
					}
					
					
					]
				// end of columnDefs

				}).on('preXhr.dt',function(e, settings, data) {

				}).on('xhr.dt',function(e, settings, data) {

									
					
					
				}).on('draw.dt', function() {
					
					if($('#check-other-contract').prop('checked'))
					{
						renderLease();	
					}				
					
				}).on('processing.dt',function(e, settings, processing) {

				}).on('error.dt',function(e, settings, techNote, message) {

				});// end of dataTable



			$('#contract-table tbody').on('click', 'tr', function() {
			
				$('#contract-table tr.selected').removeClass('selected');
				
				$(this).addClass('selected');
			
			});
			
			$('#check-other-contract').on('click',function(){
				
				if($(this).prop('checked'))
				{
					$(this).next().show();
					
					renderLease();
				}
				else
				{
					$(this).next().hide();
				}
				
			})
			
			
			
		
		$('#search-contract-status').on('change',function(){
			$('#contract-status-selected').val($('#search-contract-status option:selected').val());
			
			
		})
		
		
		$('#filter-contract').on('click',function(){
			
			$('.contract-create-func').hide();
			
			$('#contract-table').DataTable().ajax.reload();
			
		})
		
		
		$('#generate-all-contract-bill').on('click',function(){
			generateAllBill();
		})
	
		$('#contract-table').on('click','tbody tr input',function(){
			
//			$('#parker-audit-group').hide();
//			
//			$('#finance-audit-group').hide();
			
			$('.contract-create-func').hide();
			
			$('#choosed-contract-id').val('');
			
			if($(this).prop('checked'))
			{
				$('#contract-table tbody tr input').prop('checked',false);
				
				$(this).prop('checked',true);
				
//				$('#checked-contract-id').val($(this).attr('data'));
			
				//判断是否可以创建其他合同
				
				var category=$(this).attr('category');
				
				if(category==contractCategory['lease'][0].id)
				{
					$('.contract-create-func').show();
				}
				
				//选中后，判断是否显示审核功能
				
				/*
				var audit=$(this).attr('audit');
				
				if(audit==0)
				{
					$('#parker-audit-group').show();
					
					$('#choosed-contract-id').val($(this).attr('data'));
				}
				if(audit==1)
				{
				
				
				
				
				
					$('#finance-audit-group').show();
					
					$('#choosed-contract-id').val($(this).attr('data'));
				}*/
				
			}
			else
			{
//				$('#checked-contract-id').val('');
//				

			}
//				
		})
		
		
		$('#parkId').on('change',function(){
			
			//园区改变，先清空其他所有
			initSelect($('#building-sele'),'楼');
			
			initSelect($('#floor-sele'),'层');
			
			initSelect($('#block-sele'),'区');
			
			if($(this).val()!='')
			{
				loadFloorList($(this).val(),'',$('#building-sele'));
			}			
			
		})
		
		
		$('#building-sele').on('change',function(){
			
			//改变，先清空其他所有
					
			initSelect($('#floor-sele'),'层');
			
			initSelect($('#block-sele'),'区');
			
			if($(this).val()!='')
			{
				loadFloorList($('#parkId').val(),$(this).val(),$('#floor-sele'));
			}			
			
		})
		
		
		$('#floor-sele').on('change',function(){
			
			//改变，先清空其他所有
			
			initSelect($('#block-sele'),'区');
			
			if($(this).val()!='')
			{
				loadFloorList($('#parkId').val(),$(this).val(),$('#block-sele'));
			}			
			
		})
		
		
		
		
		
		
		
		
		
		
		
		
		
		initContractList();
		
		
		
		
		
		
	})
	
function createLease()
{
	var url=basePath+'/contract/edit.do?category=1'+'&ro='+$('#read-only').val();
	
	//window.location.href=url;
	
	window.parent.addTable(url,'新建租赁合同');
}

//外包
function createSubContract()
{
	var url=basePath+'/contract/edit.do?category=2'+'&ro='+$('#read-only').val();
	
	//window.location.href=url;
	
	window.parent.addTable(url,'新建外包合同');
}


	
function createContract(category)
{
	$.noty.closeAll();
	
	var data=checkLeaseSeleted();
	
	//12.13服务合同不需要
	if(data.lease=='' && category!=contractCategory['service'][0].id)
	{
		myNoty('请选择租赁合同。',10002);
		
		return;
	}
	
	
	
	var url=basePath+'/contract/edit.do?category='+category+
	'&leaseId='+data.lease+'&ro='+$('#read-only').val()
	//+'&refCompanyId='+data.company+'&refRoomId='+data.room
	;
	//window.location.href=url;
	
	window.parent.addTable(url,'新建'+renderContractCategory(category));
}

function editContract(id,category,companyName)
{
	var url=basePath+'/contract/edit.do?id='+id+'&category='+category+'&ro='+$('#read-only').val();
	
	//window.location.href=url;
	
	window.parent.addTable(url,companyName+renderContractCategory(category)+'编辑');
}

function removeContract(id)
{

	
	swal({
        title: '确定要删除此合同吗？',
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: true
        }, function () {
        	$.myPost(basePath+'/contract/remove.do',{id:id},function(data){
    			
        		myNoty(data.msg,data.status);
        		
    			if(data.status===10001)
    			{
    				$('#contract-table').DataTable().ajax.reload();	
    			}  			
    			
    		})
        }
    );
	
	
	
}

function checkLeaseSeleted()
{
	var data={lease:"",category:contractCategory['lease'][0].id,company:"",room:""};
	
	var cbs=$('#contract-table tbody tr input');
	
//	var ids=[];
	
	for(var i=0;i<cbs.length;i++)
	{
		if($(cbs[i]).prop('checked'))
		{
			if($(cbs[i]).attr('category')==contractCategory['lease'][0].id)
			{
				data.lease=$(cbs[i]).attr('data');
				
				data.company=$(cbs[i]).attr('company');
				
				data.room=$(cbs[i]).attr('room');
				
				break;
			}
//			ids.push($(cbs[i]).attr('data'));				
		}
	}
	
	return data;
	
}

function generateAllBill()
{
	swal({
        title: '确定为所有合同出帐吗？',
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: true
        }, function () {
        	$.myPost(basePath+'/contract/generateBillForOwnPark.do',{},function(data){
    			    			
    			myNoty(data.msg,data.status);
    			if(data.status===10001){
    				$('#contract-table').DataTable().ajax.reload();
    			}
    		})
        	

        	
        }
    );

}


function generateBill(cid)
{
	swal({
        title: '确定为此合同出帐吗？',
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: true
        }, function () {
        	$.post(basePath+'/contract/generateContractBill.do',{contractId:cid},function(data){
    			    			
    			myNoty(data.msg,data.status);
    			
    			//$('#contract-table').DataTable().ajax.reload();
    			
    		})
        }
    );

}


function renderContractCategory(c)
{
	c=c+"";
	
	var result='';
	
	switch(c)
	{
		case contractCategory['lease'][0].id:
			result=contractCategory['lease'][0].name;
			break;
		case contractCategory['property'][0].id:
			result=contractCategory['property'][0].name;
			break;
		case contractCategory['service'][0].id:
			result=contractCategory['service'][0].name;
			break;
		case contractCategory['energy'][0].id:
			result=contractCategory['energy'][0].name;
			break;
		case contractCategory['sub'][0].id:
			result=contractCategory['sub'][0].name;
			break;
		
		default:
			result='<label class="label label-default">未知</label>';
	}
		
	return result;

}






function financeAudit(cid,type)
{
	var title='财务审批通过，确定吗？';
	
	var stype='success';
	
//	var cid=$('#choosed-contract-id').val();
//	
//	if(cid=='') return;
	
	if(type==0)
	{
		title='财务审批不通过，确定吗？';
		
		stype='warning';
	}
	
	swal({
        title: title,
        type: stype,
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: true
        }, function () {
        	$.myPost(basePath+'/contract/financeAudit.do',{contractId:cid,type:type},function(data){
    			    			
    			myNoty(data.msg,data.status);
    			    			
    			if(data.status===10001)
    			{
    				$('#finance-audit-group').hide();
    			}
    			
				$('#contract-table').DataTable().ajax.reload();
    			
    		})
        }
    );
}

function parkerAudit(cid,type)
{
	var title='园长审批通过，确定吗？';
	
	var stype='success';

//	var cid=$('#choosed-contract-id').val();
	
	//if(cid=='') return;
	
	if(type==0)
	{
		title='园长审批不通过，确定吗？';
		
		stype='warning';
	}
	
	swal({
        title: title,
        type: stype,
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: true
        }, function () {
        	$.myPost(basePath+'/contract/parkerAudit.do',{contractId:cid,type:type},function(data){
    			    			
    			myNoty(data.msg,data.status);
    			
    			if(data.status===10001)
    			{
    				
    				$('#parker-audit-group').hide();
    			}
    			
    			$('#contract-table').DataTable().ajax.reload();
    			
    		})
        }
    );
}


function terminateAudit(cid,category,type)
{	
	var l='';
	
	if(category==contractCategory['lease'][0].id)
	{
		l='(终止租赁合同时，将同时终止关联的物业、能源合同)';
	}
	
	var title=String.format('同意终止{0}{1}，确定吗？',renderContractCategory(category),l);
	
	var stype='success';

//	var cid=$('#choosed-contract-id').val();
	
	//if(cid=='') return;
	
	if(type==0)
	{
		title='拒绝终止'+renderContractCategory(category)+'，确定吗？';
		
		stype='warning';
	}
	
	swal({
        title: title,
        type: stype,
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: true
        }, function () {
        	$.myPost(basePath+'/contract/terminateContractAudit.do',{id:cid,terminateType:type},function(data){
    			    			
    			myNoty(data.msg,data.status);
    			
    			if(data.status===10001)
    			{
    				$('#contract-table').DataTable().ajax.reload();
    				
    				//$('#finance-audit-group').hide();
    			}
    			
    		})
        }
    );
}

function deleteAudit(cid,category,type)
{	
	var title=String.format('同意删除{0}，确定吗？',renderContractCategory(category));
	
	var stype='success';

//	var cid=$('#choosed-contract-id').val();
	
	//if(cid=='') return;
	
	if(type==0)
	{
		title='拒绝删除'+renderContractCategory(category)+'，确定吗？';
		
		stype='warning';
	}
	
	swal({
        title: title,
        type: stype,
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "是",
        cancelButtonText: "否",
        closeOnConfirm: true
        }, function () {
        	$.myPost(basePath+'/contract/deleteContractAudit.do',{id:cid,delType:type},function(data){
    			    			
    			myNoty(data.msg,data.status);
    			
    			if(data.status===10001)
    			{
    				$('#contract-table').DataTable().ajax.reload();
    				
    				//$('#finance-audit-group').hide();
    			}
    			
    		})
        }
    );
}


function getContractStatusCondition()
{
	var s=$('#search-contract-status option:selected').val();
	
	if(s=='')
	{
		var options=$('#search-contract-status option');
		
		for(var i=1;i<options.length;i++)
		{
			s=s+','+$(options[i]).val();
		}
		
		s=s.substring(1);
		
	}
	
	return s;
	//return s==''?null:'('+s+')';
}


function initContractList()
{
//	$('#contract-status-selected').val('3');
//	
//	$('#search-contract-status').get(0).selectedIndex=5;
//	
//	if($('#parker-audit').val()=='1')
//	{
//		$('#contract-status-selected').val('2,4');
//		
//		$('#search-contract-status').get(0).selectedIndex=3;
//	}
//	
//	if($('#finance-audit').val()=='1')
//	{
//		$('#contract-status-selected').val('1');
//		
//		$('#search-contract-status').get(0).selectedIndex=1;
//	}
	
	
	if($('#parkId option').length==2)
	{
		$('#parkId').get(0).selectedIndex=1;
		
		$('#contract-table').DataTable().ajax.reload();
		
		//预先加载此园区楼信息
		loadFloorList($('#parkId').val(),'',$('#building-sele'));
		
	}	

}

function loadFloorList(parkId,parentId,obj)
{
	
	$.post(basePath+'/floor/getFloorListByParent.do',{parkId:parkId,parentId:parentId,status:'1'},function(data){
		
		if(data.status===10001)
		{
			
			
			var d=data.data;
			
			for(var i=0;i<d.length;i++)
			{				
				var oOption=document.createElement('option');
				
				oOption.value=d[i].id;
			
				oOption.text=d[i].buildName;
			
				$(obj).append(oOption);
			}
		}
		
		
	})

}

function initSelect(obj,title)
{
	var oOption=document.createElement('option');
	
	oOption.value='';
	
	oOption.text=title;
	
	$(obj).empty().append(oOption);
}



function getParkCondition()
{

	var p=[];
	
	var park=$('#parkId option:selected').val();
	
	if(park==null || park=='')
	{
		
		var options=$('#parkId option');
		
		for(var i=1;i<options.length;i++)
		{
//			park=park+',\"'+$(options[i]).val()+'\"';
			p.push($(options[i]).val());
		}
		
		//park=park.substring(1);
		
	}
	else
	{
		p.push(park);
	}
	
	return p.join(',');
}

function renderLease()
{
	var inputs=$('#contract-table input[type="checkbox"]');
	
	var ids=[];
	
	for(var i=0;i<inputs.length;i++)
	{
		//var td=$(trs[i]).find('input');//.eq(1);
		var category=$(inputs[i]).attr('category');
		
		if(category=='1')
		{
			ids.push($(inputs[i]).attr('data'))
		}
			
	}
	
	if(ids.length==0)
	{
		return;
	}
	
	var lids=JSON.stringify(ids);
	
	$.post(basePath+'/contract/checkOtherContractByLease.do',{leaseIds:lids},function(data){
		
		
		if(data.status===10001)
		{
			
			var d=data.data;
			
			for(var j=0;j<d.length;j++)
			{
				
				var a=$('#contract-table input[data="'+d[j].id+'"]');
				
				var c=$(a).parent().next();
				
				
				if(d[j].result!='3')
				{
					var l="label-danger";
					
					//no property
					if(d[j].result=='1')
					{
						l="label-warning";
					}
					//no energy
					if(d[j].result=='2')
					{
						l="label-success";
					}
					
					
					var cc='<label class="label '+l+'">'+c.html()+'</label>';
					
					
					c.html(cc);
					
				}
				
				
			}
			
			
		}
		
		
	})
	
	
}



