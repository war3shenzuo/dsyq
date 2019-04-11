<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<jsp:include page="../shared/css.jsp" />
	<title>服务列表</title>
	<style>
		#billListTable a:hover{text-decoration: underline;}
	</style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
					<div class="row">

                        <div class="col-sm-2">
                            <label>服务类型</label>
<!--                             <input type="text" class=" form-control" placeholder="" id="serviceType"> -->
							<select class="form-control m-b" name="serviceType" id="serviceType">
								<option value="">全部</option>
								<option value="WYBX">物业保修</option>
								<option value="SWFW">商务服务</option>
								<option value="RYDZ">人员代招</option>
								<option value="GGCG">公共采购</option>
<!-- 								<option value="HYSYY">会议室预约</option> -->
								<option value="YYFW">设施预约</option>
								
							</select>
                        </div>
	                    <div class="col-sm-2">
                            <label>服务状态</label>
<!--                             <input type="text" class=" form-control" placeholder="" id="serviceType"> -->
							<select class="form-control m-b" name="serviceStatus" id="serviceStatus">
								<option value="">全部</option>
								<option value="101">待审批</option>
								<option value="102">待回执</option>
								<option value="201">已撤销</option>
								<option value="202">已审批</option>
								<option value="203">已派工</option>
								<option value="204">已完工</option>
								<option value="300">完结</option>
								<option value="301">拒绝</option>
							</select>
                        </div>
                        <div class="col-md-2">
								<label>服务编号</label>
                          		<input type="text" class=" form-control" placeholder="" id="serviceNo">
	                    </div> 
	                    <div class="col-md-2">
								<label>服务名称</label>
                          		<input type="text" class=" form-control" placeholder="" id="searchValue">
	                    </div>  
	                    </div>
	                    <div class="row">
	                    <div class="col-md-2">
								<label>商品大类</label>
                          		<input type="text" class=" form-control" placeholder="" id="category">
	                    </div>   
						<div class="col-md-2  date">
								<label>申请日期</label>
	                              <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="applyTime" placeholder="">
	                               <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                       </div>  
	                       <div class="col-md-2  date">
								<label>完结日期</label>
	                              <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="completeTime" placeholder="">
	                               <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                       </div> 
						<div class="col-md-2">
                        	<button class="btn btn-primary" onclick="tableRefresh('getServiceBycompanyId.do')" type="button" style="margin-top: 23px;;">搜索</button>
                        
                            <button class="btn btn-primary" onclick="toApply()" type="button" style="margin-top: 23px;;">申请服务</button>
                        </div>

						<div class="hr-line-dashed" style="clear: both;"></div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="example-wrap">

						<table id="serviceListTable"
                           	   data-mobile-responsive="true"
				               data-toggle="table"
				               data-url="getServiceBycompanyId.do"
				               data-data-type="json"
				               data-side-pagination="server"
				               data-pagination="true"
				               data-query-params="queryParams"
				               data-page-list="[5, 10, 20, 50, 100]"
				              >
							<thead>
								<tr> 

								<th data-field="serviceNo" data-align="center">服务编号</th>
                                <th data-field="serviceType" data-align="center" data-formatter="serviceTypeFormatter">服务类型</th>
                                <th data-field="subject" data-align="center">服务名称</th>
                                <th data-field="category" data-align="center">商品大类</th>
                                <th data-field="number" data-align="center">数量</th>
                                <th data-field="totalPrice" data-align="center">金额</th>
                                <th data-field="applyTime" data-align="center" >申请时间</th>
                                <th data-field="completeTime" data-align="center">完成时间</th>
                                <th data-field="serviceStatus" data-align="center" data-formatter="serviceFormatter">服务状态</th>
								<th data-field="serviceId" data-formatter="formatterFun">操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../shared/js.jsp" />
<script type="text/javascript" src="<%=basePath %>/myjs/companyservice.format.js"></script>
<script type="text/javascript">
var table = $('#serviceListTable');

	function queryParams(params){
	
		 params.companyId = $("#companyId").val();
		 params.serviceNo = $("#serviceNo").val();
		 params.serviceType = $("#serviceType").val();
		 params.serviceStatus = $("#serviceStatus").val();
//		 params.applyTime = $("#applyTime").val();
// 		 params.completeTime = $("#completeTime").val();
		if($("#applyTime").val() != '')
		 params.applyTime = $("#applyTime").val() + " 00:00:00";
		if($("#completeTime").val() != '')
		 params.completeTime = $("#completeTime").val() + " 23:59:59";
		 params.searchValue = $("#searchValue").val();
		 params.category = $("#category").val();
		return params;
	}
   	
   	function detailButtonFormatter(value, row, index) {

       		url = "companyService/getServiceType.do?id=" + value;
       		return '<a onclick="totabs(\'' + url + '\', \'服务详情\')">详情</a>';
   	
   	}
    function formatterFun(value,row,index){


<%--     	var cancel =  '<%=basePath%>/etopService/updateStatus.do?serviceId=row.serviceId&status=201'; --%>
		var infoUrl = '<%=basePath%>/companyService/getServiceType.do?id='+value;
		var refreshUrl = '<%=basePath%>/companyService/getServiceBycompanyId.do';
		var showInfo = "openAddPage('服务详情','70%','90%','"+infoUrl+"','"+refreshUrl+"')";
   		
		if(row.serviceStatus == 101){
		return '<button class="btn btn-info" onclick="'+showInfo+'"type="button" >详情</button> '+
        '&nbsp;&nbsp; ' +
	    '<button class="btn btn-primary" onclick="'+ "updateStatus('"+row.serviceId+"'"+",201)"+'"   type="button" >撤销申请</button>';
		}
		if(row.serviceStatus == 102){
			return '<button class="btn btn-info" onclick="'+showInfo+'"type="button" >详情</button> '+
			 '&nbsp;&nbsp; ' +
			 '<button class="btn btn-primary" onclick="'+"submit('"+row.serviceId+"')"+'"   type="button" >撤销该申请</button> '+
			 '&nbsp;&nbsp; ' +
			 '<button class="btn btn-primary" onclick="'+"submittwo('"+row.serviceId+"')"+'"   type="button" >同意该报价</button> ';
	   	}
		if((row.serviceStatus == 204 & row.serviceType == 'WYBX')||(row.serviceStatus == 204 & row.serviceType == 'SWFW')||(row.serviceStatus == 204 & row.serviceType == 'RYDZ')){
		return '<button class="btn btn-info" onclick="'+showInfo+'"type="button" >详情</button> '+
        '&nbsp;&nbsp; ' +
	    '<button class="btn btn-primary" onclick="'+"submitOver('"+row.serviceId+"')"+'"   type="button" >确认完工</button>'+
        '&nbsp;&nbsp; ' +
	    '<button class="btn btn-primary" onclick="'+"submitNoOver('"+row.serviceId+"')"+'"   type="button" >否认完工</button>';
		}
		if((row.serviceStatus == 204 & row.serviceType == 'GGCG')){
		return '<button class="btn btn-info" onclick="'+showInfo+'"type="button" >详情</button> '+
        '&nbsp;&nbsp; ' +
	    '<button class="btn btn-primary" onclick="'+"bill('"+row.serviceId+"')"+'"   type="button" >确认完工</button>'+
        '&nbsp;&nbsp; ' +
	    '<button class="btn btn-primary" onclick="'+"submitNoOver('"+row.serviceId+"')"+'"   type="button" >否认完工</button>';
		}else {
		return '<button class="btn btn-info" onclick="'+showInfo+'"type="button" >详情</button> ';
   		}
	
	}
   	$('.date').datepicker(
   			
   			{todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,autoclose:!0}
   			
   		)
   		
   			function toApply(){
		swal({
			title : "是否申请新服务?",
			type : "success",
// 			confirmButtonText : "确定",
// 			closeOnConfirm : true
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "是",
            cancelButtonText: "否",
		}, function() {
			window.open("<%=basePath%>/webparkservice/index.do"); 
			
		});
	}
   	
   	function updateStatus(id,status){

   		var param ={"serviceId":id,"status":status}
   		$.post("<%=basePath%>/etopService/updateStatus.do",param,function(data){
   			if(data.status==10001){
   				swal( "操作成功！", "","success");
   				window.location.reload();
   		    }else{
   		    	swal( "操作失败！", "","error");
   		    }

   		});
   	}
   	function bill(serviceId){
   		swal({
            title: "是否确认完工并出账单? ",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "是",
            cancelButtonText: "否",
            closeOnConfirm: false
        }, function () {
      
	var param={
	     	"serviceId":serviceId,
	     

	}
		$.post("<%=basePath%>/purchase/addBill.do",{"serviceId":serviceId},function(data){
			if(data.status==10001){
				swal({   title: "已出账",   
					 type: "success",  
					 confirmButtonText: "ok",  
					 closeOnConfirm: false 
				  }, 
				  function(){  
					  window.location.reload();
				  }
				 );
		    }else {
				swal({
					title : "出账失败！",
					text : data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				});
			}

		});
	}
   		);
    }
	function submitOver(serviceId,finalPrice){
        swal({
                title: "是否确认完工并出账单? ",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是",
                cancelButtonText: "否",
                closeOnConfirm: false
            }, function () {
          
		var param={
		     	"serviceId":serviceId,
		     

		}
		$.post('<%=basePath%>/quotation/addService.do',param,function(data){
			
			
			if (data.status == 10001) {
				swal({
					title : "已出账！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {
					window.location.reload();
				});

			} else {
				swal({
					title : "出账失败！",
					text : data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				});
			}
		
	   });
	}
        );
    }
	function submitNoOver(serviceId){
        swal({
                title: "尚未完工? ",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是",
                cancelButtonText: "否",
                closeOnConfirm: false
            }, function () {
          

		var param={
		     	"serviceId":serviceId,
		     	"status": "201"

		}
		$.post('<%=basePath%>/etopService/updateStatus.do',param,function(data){
			
			
			if (data.status == 10001) {
				swal({
					title : "提交成功！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {
					window.location.reload();
				});

			} else {
				swal({
					title : "提交失败！",
					text : data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				});
			}
		
	   });
	}
        );
    }
	 function submit(serviceId){
	     swal({
	             title: "确认撤销该申请? ",
	             type: "warning",
	             showCancelButton: true,
	             confirmButtonColor: "#DD6B55",
	             confirmButtonText: "是",
	             cancelButtonText: "否",
	             closeOnConfirm: false
	         }, function () {
	       
			var finalPrice = $("#finalPrice").val();

			var param={
			     	"serviceId":serviceId,
			     	"finalPrice":finalPrice

			}
			$.post('<%=basePath%>/companyService/cancel.do',param,function(data){
				
				
				if (data.status == 10001) {
					swal({
						title : "撤销成功！",
						type : "success",
						confirmButtonText : "确定",
						closeOnConfirm : true
					}, function() {
						window.location.reload();
					});

				} else {
					swal({
						title : "撤销失败！",
						text : data.msg,
						type : "error",
						confirmButtonText : "确定",
						closeOnConfirm : true
					});
				}});
		}
	     );
	 }

		function submittwo(serviceId){
	        swal({
	                title: "接受报价? ",
	                type: "warning",
	                showCancelButton: true,
	                confirmButtonColor: "#DD6B55",
	                confirmButtonText: "是",
	                cancelButtonText: "否",
	                closeOnConfirm: false
	            }, function () {
	          
			var finalPrice = $("#finalPrice").val();

			var param={
			     	"serviceId":serviceId,
			     	"finalPrice":finalPrice

			}
			$.post('<%=basePath%>/companyService/approve.do',param,function(data){
				
				
				if (data.status == 10001) {
					swal({
						title : "提交成功！",
						type : "success",
						confirmButtonText : "确定",
						closeOnConfirm : true
					}, function() {
						window.location.reload();
					});

				} else {
					swal({
						title : "提交失败！",
						text : data.msg,
						type : "error",
						confirmButtonText : "确定",
						closeOnConfirm : true
					});
				}
			
		   });
		}
	        );
	    }
		

</script>
</body>
</html>