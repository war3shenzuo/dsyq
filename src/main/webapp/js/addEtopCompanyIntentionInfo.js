

function submit(){
	var companyName = $("#companyName").val();// 公司名称
	var companyType = $("#companyType option:selected").val();// 公司类型
	var companyCapital = $("#companyCapital option:selected").val();// 注册资金
	
	var contact = $("#contact").val();// 联系人
	var mobile = $("#mobile").val();// 电话
	var companyMobile = $("#companyMobile").val();// 公司电话
	var companyFax = $("#companyFax").val();// 公司传真
	
	var area = $("#area").html();// 面积
	var renovation = $("#renovation").html();// 层高
	var floorRoom = $("#floorRoom").html();// 房间
	
	var businessType = $("#businessType").val();// 经营类目
	var beforeseat = $("#beforeseat").val();// 所在地
	var remarks = $("#remarks").val();// 备注

    if($.trim(companyName) == ''){
        swal("请输入公司名称!");
        return
    }
    if($.trim(contact) == ''){
        swal("请输入联系人名称!");
        return
    }
    if($.trim(mobile) == ''){
        swal("请输入联系电话!");
        return
    }
    if($.trim(companyMobile) == ''){
        swal("请输入公司电话!");
        return
    }
    if($.trim(area) == ''){
        swal("请输入面积!");
        return
    }
    if($.trim(renovation) == ''){
        swal("请输入楼高!");
        return
    }
    if($.trim(floorRoom) == ''){
        swal("请输入房间!");
        return
    }
    if($.trim(businessType) == ''){
        swal("请输入公司主营业务!");
        return
    }
    if($.trim(beforeseat) == ''){
        swal("请输入地址!");
        return
    }
    /*var re = /^[0-9-#]*$/;
    if (!re.test(contactPhone)) {
        $.toast("联系人电话格式不正确", 2000);
        return false;
    }*/
	/*$.post("http://192.168.0.23:8081/etopManagement/etopCompanyIntention/addEtopCompanyIntentionInfo.do",
        {
            "companyName":companyName,
            "companyType":companyType,
            "companyCapital":companyCapital,
            "contact":contact,
            "mobile":mobile,
            "companyMobile":companyMobile,
            "companyFax":companyFax,
            "area":area,
            "layerHigh":layerHigh,
            "floorRoom":floorRoom,
            "businessType":businessType,
            "beforeseat":beforeseat,
            "remarks":remarks
        },function(data){
            if(data.status==10001){
                swal( "保存成功！", "","success" );

            }else{
                swal( "保存失败！", "","error");
            }
   });*/
    
   var url=basn+'/etopCompanyIntention/addEtopCompanyIntentionInfo.do';
	$.ajax(  
		    {  
		        type:'post',  
		        url:url,  
		        data : {
		            "companyName":companyName,
		            "companyType":companyType,
		            "companyCapital":companyCapital,
		            "contact":contact,
		            "mobile":mobile,
		            "companyMobile":companyMobile,
		            "companyFax":companyFax,
		            "area":area,
		            "renovation":renovation,
		            "floorRoom":floorRoom,
		            "businessType":businessType,
		            "beforeseat":beforeseat,
		            "remarks":remarks,
		            "company_status":'0',
		            "roomId":roomId
		            
		        },
		        success  : function(data){
		            if(data.status==10001){
		                
		                swal({   
		                	title: "提交成功",  
		                	type: "success",   showCancelButton: false,   
		                	closeOnConfirm: false,   
		                	showLoaderOnConfirm: true,
		                	}, function(){  
		                		location.reload();
		                		});

		            }else{
		                swal( "保存失败！", "","error");
		            }
		        }
		    }  
		);  
}