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
	<title>人员代招列表</title>
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
                            <label>类别</label>
                            <input type="text" class=" form-control" placeholder="" id="category">
                        </div>
                        <div class="col-sm-2">
                            <label>服务名称</label>
                            <input type="text" class=" form-control" placeholder="" id="quotationName">
                        </div>
						<div class="col-md-2">
                        	<button class="btn btn-primary" onclick="tableRefresh('makeRecruitment.do')" type="button" style="margin-top: 23px;;">搜索</button>
                        </div>
					</div>
					<div class="hr-line-dashed" style="clear: both;"></div>
				</div>
				<div class="col-sm-12">
					<div class="example-wrap">
                                <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                    <a class="btn btn-outline btn-default" 
                                        onclick="openAddPage('新增项目','50%','70%','<%=basePath %>/recruitment/addPage.do','<%=basePath%>/recruitment/makeRecruitment.do')" >
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
                                        <span>新建项目</span>
                                    </a>
                                </div>
						<table id="quotationListTable"
                           	   data-mobile-responsive="true"
				               data-toggle="table"
				               data-url="makeRecruitment.do"
				               data-data-type="json"
				               data-side-pagination="server"
				               data-pagination="true"
				               data-query-params="queryParams"
				               data-page-list="[5, 10, 20, 50, 100]"
				              >
							<thead>
								<tr>


                                <th data-field="category" data-align="center"> 类别</th>
                                <th data-field="quotationName" data-align="center">服务名称</th>
                                <th data-field="price" data-align="center" >价格</th>
<!--                                 <th data-field="capitalPrice" data-align="center">大写金额</th> -->
                                <th data-field="units" data-align="center">单位</th>                               
                                <th data-field="createTime" data-align="center">创建时间</th>                               
								<th data-field="quotationId" data-formatter="formatterFun">操作</th>
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
var quotationId = $("#quotationId").val();
var table = $('#quotationListTable');

	function queryParams(params){
	
		 params.quotationId = $("#quotationId").val();
		 params.category = $("#category").val();
		 params.quotationName = $("#quotationName").val();

		return params;
	}
   	

    function formatterFun(value,row,index){

		var delect = "delect('" + value + "')";
		var infoUrl = '<%=basePath%>/quotation/getQuotationInfoById.do?quotationId='+value;
		var stopUrl='<%=basePath%>/quotation/activeOrClose.do?quotationId='+value;
		var refreshUrl = '<%=basePath%>/recruitment/makeRecruitment.do';
		var showInfo = "openAddPage('维修项目修改','50%','70%','"+infoUrl+"','"+refreshUrl+"')";
		var status=activatedStatus(row.activated);
		var btnS="";
		if(row.activated=="1"){
			btnS="btn-danger";
		}else{
			btnS="btn-primary";
		}
		var param="'"+stopUrl+"'"+","+"'"+row.quotationId+"'"+","+"'"+row.activated+"'"+","+"'"+refreshUrl+"'";
		var stop="stopGroup("+param+")";
		return '<button class="btn btn-info" onclick="'+showInfo+'"type="button" >编辑</button> '+
		'&nbsp;&nbsp; '+
	       '<button class="btn '+btnS+'" onclick="'+ stop+'"   type="button" >'+status+'</button>';
/* 	       +'&nbsp;&nbsp; ' +
        '<button class="btn btn-danger" onclick="' + delect + '" type="button" >删除</button> '; */
	}
	
    
    function stopGroup(url,id,activated,refUrl){
    	if(activated=='1'){
    		activated='0';
    	}else{
    		activated='1';
    	}
    	var param ={"id":id,"activated":activated}
    	var status=activatedAndStopStatus(activated);
    	 swal({
             title: "是否更改状态? ",
             type: "warning",
             showCancelButton: true,
             confirmButtonColor: "#DD6B55",
             confirmButtonText: "是",
             cancelButtonText: "否",
             closeOnConfirm: false
         }, function () {
    	$.post(url,param,function(data){
    		if(data.status==10001){
    			swal(   status+"成功！", "","success");
    			tableRefresh(refUrl);
    	    }else{
    	    	swal(   data.msg, "","error");
    	    }

    	});
         });
    }
    
    function delect(quotationId){
        swal({
                title: "确认删除? ",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是",
                cancelButtonText: "否",
                closeOnConfirm: false
            }, function () {
                $.get("<%=basePath%>/quotation/deleteQuotation.do?quotationId="+quotationId, {

                }, function (data) {
                    if (data.status == 10001) {
                        swal({
                            title: "成功删除! ",
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableRefresh('makeRecruitment.do');
                        });
                    } else {
                        swal({
                            title: data.msg,
                            type: "error",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableRefresh('makeRecruitment.do');
                        });
                    }

                })
            }
        );
    }
	 

</script>
</body>
</html>