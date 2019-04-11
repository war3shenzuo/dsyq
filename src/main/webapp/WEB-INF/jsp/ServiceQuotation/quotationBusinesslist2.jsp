<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<title>维修项目列表</title>
	<style>
		#billListTable a:hover{text-decoration: underline;}
	</style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="ibox float-e-margins">
	 <div class="ibox-title">
				<h5>
					<normal>物业维修列表</normal>
				</h5>
			</div>
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
				<div class="col-sm-2">
	                       <div class="panel panel-default">
							  <div class="panel-heading">
									<h3 class="panel-title">园区栏</h3>
							  </div>
							  <div class="panel-body">
		                        <div id="parktree"></div>
			                  </div>
				           </div>
	                    </div>
	                    <div class="col-sm-10">
							<div class="col-sm-12">
					<div class="row">
  						<div class="col-sm-2">
  						   <label>服务编号</label>
                            <input type="text" class=" form-control" placeholder="请输入服务编号" id="serviceNo">
                        </div>      
                        <div class="col-sm-2">
  						   <label>申请公司</label>
                            <input type="text" class=" form-control" placeholder="请输入客户名称" id="companyName">
                        </div>                        
                         <div class="col-sm-2">
                            <label>类别</label>
                            <input type="text" class=" form-control" placeholder="请输入服务类型" id="categoryName"> 
                        </div>
                         <div class="col-sm-2">
                            <label>项目名称</label>
                            <input type="text" class=" form-control" placeholder="请输入服务类型" id="subject"> 
                        </div>
                        </div>
                        
                        <div class="row">
                        <div class="col-sm-2">
                            <label>服务状态</label>
                            <select class="form-control m-b" name="service_status" id="serviceStatus">
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
						<div class="col-md-2  date">
								<label>申请日期</label>
	                            <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="applyTime" placeholder="请输入申请日期">
	                            <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                    </div>  
						<div class="col-md-2  date">
								<label>完结日期</label>
	                            <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="completeTime" placeholder="请输入完结日期">
	                            <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                    </div>  
						<div class="col-md-2">
                        	<button class="btn btn-primary" onclick="tableRefresh('getQuotationBusiness.do');tableRefresh2('<%=basePath%>/etopService/calculateQuotation.do')" type="button" style="margin-top: 23px;;">搜索</button>
                        </div>
					</div>
				</div>
				<div class="ibox-content">
               <%--  <div class="row row-lg">
                    <div class="col-sm-4">
                        <!-- Example Events -->
                        <div class="example-wrap">
                                
	                           	<table id="table2"
                           	       data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="<%=basePath%>/etopService/calculateQuotation.do"
					               data-data-type="json"
					               data-side-pagination="server"
					              
					               data-query-params = "queryParams"
					               data-page-list="[5, 10, 20, 50, 100, 200]"
					               data-striped="true"
					              >
						            <thead>
						            <tr>
<!-- 						                <th data-field="subject" data-align="center">服务名称</th> -->
						                <th data-field="num" data-align="center">总数</th>
						                <th data-field="money" data-align="center" >总价</th>
						            </tr>
						            </thead>
				       		   </table>
                        </div>
                        <!-- End Example Events -->
                    </div>
                                       
                </div> --%>
            </div>
				<div class="col-sm-12">
					<div class="example-wrap">

						<table id="quotationListTable"
                           	   data-mobile-responsive="true"
				               data-toggle="table"
				               data-url="getQuotationBusiness.do"
				               data-data-type="json"
				               data-side-pagination="server"
				               data-pagination="true"
				               data-query-params="queryParams"
				               data-page-list="[5, 10, 20, 50, 100]"
				              >
							<thead>
								<tr>


                                <th data-field="serviceNo" data-align="center">服务编号</th>    
                                <th data-field="companyName" data-align="center">申请公司</th>    
                                <th data-field="categoryName" data-align="center">类别</th>    
                                <th data-field="subject" data-align="center">项目名称</th>
                                <th data-field="number" data-align="center" >数量</th>
                                <th data-field="totalPrice" data-align="center">金额</th>
                                <th data-field="applyTime" data-align="center">申请时间</th>
                                <th data-field="completeTime" data-align="center">完成时间</th>    
<!--                                 <th data-field="finalPrice" data-align="center">最终报价</th>      -->
                                <th data-field="serviceStatus" data-align="center" data-formatter="serviceStatusFormatter">审核状态</th>                      
<!-- 								<th data-field="serviceId" data-formatter="detailButtonFormatter">操作</th> -->
								</tr>
							</thead>
						</table>
					</div>
				</div>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../shared/js.jsp" />

<script type="text/javascript">
var basePath='<%=basePath%>';
$('.date').datepicker(
		
		{todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,autoclose:!0}
		
	)
var serviceId = $("#serviceId").val();
var table = $('#quotationListTable');

	function queryParams(params){
	
		 params.serviceNo = $("#serviceNo").val();
		 params.categoryName = $("#categoryName").val();
		 params.companyName = $("#companyName").val();
 		 params.subject = $("#subject").val();
 		 params.serviceStatus = $("#serviceStatus").val();
// 		 params.applyTime = $("#applyTime").val();
// 		 params.completeTime = $("#completeTime").val();
		 if($("#applyTime").val() != '')
		 params.applyTime = $("#applyTime").val() + " 00:00:00";
		 if($("#completeTime").val() != '')
		 params.completeTime = $("#completeTime").val() + " 23:59:59";
		return params;
	}
   	
   	function detailButtonFormatter(value, row, index) {
   		
   		    var statusOfSend = "statusOfSend('" + value + "')";
   		    var overOfSend = "overOfSend('" + value + "')";
   		    var getOut = "getOut('" + value + "')";
   		 	var examineUrl = "<%=basePath%>/business/examine.do?service_id="+value;
   		    var dispatchUrl = "<%=basePath%>/quotation/dispatch.do?service_id="+value;
   			var infoUrl = "<%=basePath%>/quotation/getQuotationBusinessInfoById.do?service_id="+value;
       		var refreshUrl = '<%=basePath%>/quotation/getQuotationBusiness.do';
       		var showInfo = "openAddPage('维修项目修改','80%','95%','"+infoUrl+"','"+refreshUrl+"')";
       		var showDispatch = "openAddPage('派工','50%','70%','"+dispatchUrl+"','"+refreshUrl+"')";
       		var examineInfo = "openAddPage('审核报价','50%','70%','"+examineUrl+"','"+refreshUrl+"')";
       		
       		if(row.serviceStatus == 202 || row.serviceStatus == 204){
    		return '<button class="btn btn-primary" onclick="'+showInfo+'" type="button" >详情</button> '+
            '&nbsp;&nbsp; ' +
            '<button class="btn btn-danger" onclick="' + showDispatch + '" type="button" >派工</button> '; 
       		}
       		else if(row.serviceStatus == 203){
        		return '<button class="btn btn-primary" onclick="'+showInfo+'" type="button" >详情</button> '+
        		'&nbsp;&nbsp; ' +
                '<button class="btn btn-danger" onclick="' + showDispatch + '" type="button" >派工</button> '+
                '&nbsp;&nbsp; ' +
                '<button class="btn btn-danger" onclick="' + overOfSend + '" type="button" >完工</button> '; 
           	}
       		else if(row.serviceStatus == 101 & row.totalPrice != 0){
       			return '<button class="btn btn-primary" onclick="'+showInfo+'" type="button" >编辑</button> '+
                '&nbsp;&nbsp; ' +
       		    '<button class="btn btn-primary" onclick="'+examineInfo+'" type="button" >审核</button>'+
                '&nbsp;&nbsp; ' +
       		    '<button class="btn btn-danger" onclick="'+"updateStatus('"+row.serviceId+"'"+",301)"+ '" type="button" >拒绝</button>';
       		}
       		else if(row.serviceStatus == 101 & row.totalPrice == 0){
       			return '<button class="btn btn-primary" onclick="'+showInfo+'" type="button" >编辑</button> '+
       			'&nbsp;&nbsp; '+
    		    '<button class="btn btn-primary" onclick="'+"updateStatus('"+row.serviceId+"'"+",202)"+ '"type="button" >审核</button>'+
                '&nbsp;&nbsp; ' +
       		    '<button class="btn btn-danger" onclick="'+"updateStatus('"+row.serviceId+"'"+",301)"+ '" type="button" >拒绝</button>';
       		}
       		else{
       			return '<button class="btn btn-primary" onclick="'+showInfo+'" type="button" >详情</button> ';
       		}
            
//        		url ="quotation/getQuotationBusinessInfoById.do?service_id=" + value;
//        		return '<a onclick="totabs(\'' + url + '\', \'维修服务详情\')">详情</a>';
       		
   	}

   	function updateStatus(id,status){

   		
   		swal({
   	        title: "是否确认? ",
   	        type: "warning",
   	        showCancelButton: true,
   	        confirmButtonColor: "#DD6B55",
   	        confirmButtonText: "是",
   	        cancelButtonText: "否",
   	        closeOnConfirm: false
   	    }, function () {

   	    	var param ={"serviceId":id,"status":status}
   		$.post("<%=basePath%>/etopService/updateStatus.do",param,function(data){
   			if(data.status==10001){
   				swal( "操作成功！", "","success");
   				tableRefresh('getQuotationBusiness.do');
   		    }else{
   		    	swal( "操作失败！", "","error");
   		    }

   		});
   	    });
   	}
   	
    function statusOfSend(service_id){
        swal({
                title: "确认派工? ",
                text: "请确定该服务最终报价。",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是",
                cancelButtonText: "否",
                closeOnConfirm: false
            }, function () {
                $.get("<%=basePath%>/quotation/statusOfSend.do?service_id="+service_id, {

                }, function (data) {
                    if (data.status == 10001) {
                        swal({
                            title: "已修改为派工! ",
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableRefresh('getQuotationBusiness.do');
                        });
                    } else {
                        swal({
                            title: data.msg,
                            type: "error",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableRefresh('getQuotationBusiness.do');
                        });
                    }

                })
            }
        );
    }
    
    function overOfSend(service_id){
        swal({
                title: "确认已完工? ",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是",
                cancelButtonText: "否",
                closeOnConfirm: false
            }, function () {
                $.get("<%=basePath%>/quotation/overOfSend.do?service_id="+service_id, {

                }, function (data) {
                    if (data.status == 10001) {
                        swal({
                            title: "已修改为完工! ",
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableRefresh('getQuotationBusiness.do');
                        });
                    } else {
                        swal({
                            title: data.msg,
                            type: "error",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableRefresh('getQuotationBusiness.do');
                        });
                    }

                })
            }
        );
    }
    
    
    function getOut(service_id){
        swal({
                title: "确认出账? ",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是",
                cancelButtonText: "否",
                closeOnConfirm: false
            }, function () {
                $.get("<%=basePath%>/quotation/addService.do?service_id="+service_id, {

                }, function (data) {
                    if (data.status == 10001) {
                        swal({
                            title: "出账! ",
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableRefresh('getQuotationBusiness.do');
                        });
                    } else {
                        swal({
                            title: data.msg,
                            type: "error",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableRefresh('getQuotationBusiness.do');
                        });
                    }

                })
            }
        );
    }
    
   	function serviceStatusFormatter(value) {
   		switch (value) {

   		case "101":
   			return "待审批";
   		case "102":
   			return "待回执";
   		case "201":
   			return "已撤销";
   		case "202":
   			return "已审批";
   		case "203":
   			return "已派工";
   		case "204":
   			return "已完工";
   		case "300":
   			return "完结";
   		case "301":
   			return "拒绝";
   		default:
   			break;
   		}
   	}
   	var table2 = $('#table2');
   	function tableRefresh2(dataUrl){
   		
   		table2.bootstrapTable('refresh',{url:dataUrl});

   	}
    var parktree ;
    $(document).ready(function () {
    	parktree = $("#parktree").jstree({
    			"core": {
            	"animation": true,
            	"multiple": false,
            	"check_callback": true,
            	"themes": { "stripes": false },
            	'data': {
            		'url': basePath+'/etopService/getParkServiceList.do',
            		"dataType": "json",
            		'data': function (node) {
            			return { 'id': node.id };
            		}
            	}
            }

        }).on("changed.jstree", function (event, data) {
        	
        	var id=data.selected[0];
        	$('#parktree').jstree('open_node',id);
        	$("#parkId").val(id);
    		tableRefresh(basePath+'/quotation/getQuotationBusiness.do?parkId='+id);
        	
    		
    	}).jstree();
    	
    });
</script>

</body>
</html>