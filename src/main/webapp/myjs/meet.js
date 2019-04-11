/*$(function(){
	$.post(basePath+"/facility/getEtopFacilityList2.do?",{},function(data){
		
		 if(data.status==10001){
			 for(var i=0;i<data.data.length;i++){
				 $("#room").append('<option value="'+data.data[i].id+'">'+data.data[i].facilityName+'</option>')
			 }
			 
		 }else{
			   swal( data.msg, "","error");
		 }
		 
	});
});*/

function getInfo(){
	
	var facilityId=$("#room").val();
	$.post(basePath+"/serviceFacility/getServiceList2.do?facilityId="+facilityId,{},function(data){

		 if(data.status==10001){
			 var arrays=[];
			 for(var i=0;i<data.data.length;i++){
				 var one={
						 title: "已预约"+(data.data[i].beginStr).substring(11)+"--"+(data.data[i].endStr).substring(11),
						 start: getData(data.data[i].beginStr),
						 end: getData(data.data[i].endStr)
				 }
				 
				 arrays.push(one)
			 }
			 $('#calendar').fullCalendar('destroy');
			 $("#calendar").fullCalendar({
					header: {
						left: "prev,next",
						center: "title",
						right: ""
					},
					events: arrays
			})
			 
		 }else{
			   swal( data.msg, "","error");
		 }
		 
	});	
	
}



function getData(timestamp){
	var date = new Date(timestamp);
	Y = date.getFullYear();
	M = date.getMonth();
	D = date.getDate();
	//(date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1)
	return new Date(Y, M, D);
}


function appoint(){
	if(reday==0){
    	swal({
			title : "超出开放时段！",
			text : data.msg,
			type : "error",
			confirmButtonText : "确定",
			closeOnConfirm : true
		});
    	return;
    }
	if($("#room").val() == ""){
    	swal("请选择设施房间!");
    	return;
    }
	if($("#start").val()=="" || $("#end").val()==""){
		swal("请选择预约时间!");
		return;
	}
	if($("#roomNum").val() == ""){
    	swal("请选择申请人房间!");
    	return;
    }
	var param={
			"roomNum":$("#roomNum").val(),
	     	"applicant":$("#applicant").val(),
			"applicantPhone" : $("#applicantPhone").val(),
			"applicantDepartment" : $("#applicantDepartment").val(),
			"applicantPosition" : $("#applicantPosition").val(),
			"beginTimestamp":$("#start").val(),
			"endTimestamp":$("#end").val(),
			"facilityId":$("#room").val(),
			"duration":$("#number").val(),
			"totalPrices":$("#totalPrices").val(),
			"description":$("#description").val()
			
	};
	$.post(basePath+"/serviceFacility/add.do",param,function(data){
		if (data.status == 10001) {
				swal({
					title : "预约成功！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : true
				} ,function() {
					window.location.reload();
				}
				);
			} else {
				swal({
					title : "预约失败！",
					text : data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				});
			}
		 
	});
	
}

var reday=1;
function compare(){
	
	var start=$("#start").val();
	var end=$("#end").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	a = start.substring(11,13);
	b = end.substring(11,13);
	x = startTime.substring(0,2);
	y = endTime.substring(0,2);
	if(x <= a & a<= y && x <= b & b<= y){
		reday=1;
	}else{
		reday=0;
	}

}

function check(){  
    
    $.ajax({  
           type: "POST",      
            url: basePath+"/serviceFacility/checkAppointTime.do",      
            data: {"facilityId":$("#room").val(),"beginTimestamp":$("#start").val(),"endTimestamp":$("#end").val(),}, 
            success: function(data){  
           if(data.status == 10001){     
            
            swal({
				title : "预约时间重复！",
				type : "error",
				confirmButtonText : "确定",
				closeOnConfirm : false
			});
            return;
           }else{
        	   appoint()
           }
           
           }            
           });     
   }

$(document).ready(function(){
	$("#facilityType").change(function(){
		facilityType=$("#facilityType").val();
		$.post(basePath+"/engagement/getfacilityName.do?",{"facilityType":facilityType},function(data){
			$("#room").empty(); 
			$("#room").append("<option value=''>请选择服务</option>");
			for(var i=0;i<data.length;i++){
				$("#room").append("<option value=\'"+data[i].id+"\'>"+data[i].facilityName+"</option>");
			}
		});
		
		
	});
	})
	
	function getPrice(){
	var id=$("#room").val();
	$.post(basePath+"/facility/getEtopFacilityList2.do?id="+id,{},function(data){
				
		$("#facilityPrice").val(data.data[0].facilityPrice);
		$("#facilityPrice").show();
		$("#descriptions").val(data.data[0].description);
		$("#descriptions").show();
		$("#startTime").val(data.data[0].startTime);
		$("#startTime").show();
		$("#endTime").val(data.data[0].endTime);
		$("#endTime").show();
		var number=document.getElementById("number").value;
		var facilityPrice=document.getElementById("facilityPrice").value;
		totalPrices=number*facilityPrice;
		$("#totalPrices").val(JSON.stringify(totalPrices));
	   });

}
/*function gradeChange(){
	var start=$("#start").val();
	var end=$("#end").val();
	
	var param={
			"start":start,
	     	"end":end
	}
	$.post(basePath+"/serviceFacility/fromToday.do",param,function(gap){

		$("#number").val(JSON.stringify(gap));
		$("#number").show();
		
		var number=document.getElementById("number").value;
		var facilityPrice=document.getElementById("facilityPrice").value;

		totalPrices=number*facilityPrice;
		$("#totalPrices").val(JSON.stringify(totalPrices));
	   });

}*/


function grade(){
	
	var start=$("#start").val();
	var end=$("#end").val();
	var startTime = Date.parse(new Date(start.replace(/-/g, "/")));
	var endTime = Date.parse(new Date(end.replace(/-/g, "/")));
	
	var date=(endTime-startTime )/3600000 //时间差的毫秒数

		$("#number").val(date);
		$("#number").show();
		var number=document.getElementById("number").value;
		var facilityPrice=document.getElementById("facilityPrice").value;

		totalPrices=number*facilityPrice;
		$("#totalPrices").val(JSON.stringify(totalPrices));
}

function gradeTwo(){
	
	var start=$("#start").val();
	var end=$("#end").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	a = start.substring(11,13);
	b = end.substring(11,13);
	date1 = start.substring(8,10);
	date2 = end.substring(8,10);
	x = startTime.substring(0,2);
	y = endTime.substring(0,2);
	if(date2-date1!=0){
	var date=(date2-date1-1)*(y-x)+(b-x)+(y-a);
	}
	else{
		var date=b-a;
	}
	$("#number").val(date);
	$("#number").show();
	var number=document.getElementById("number").value;
	var facilityPrice=document.getElementById("facilityPrice").value;

	totalPrices=number*facilityPrice;
	$("#totalPrices").val(JSON.stringify(totalPrices));
}

function clean()
{
document.getElementById("end").value="";
}
