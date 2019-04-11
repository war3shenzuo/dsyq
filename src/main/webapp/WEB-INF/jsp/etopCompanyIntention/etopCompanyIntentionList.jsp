<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>客户列表-客户管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <jsp:include page="../shared/css.jsp"/>
    <script type="text/javascript">
    	var basePath='<%=basePath%>';
    	</script>
</head>
<body class="gray-bg">
<input type="hidden" id="id">

<jsp:include page="../shared/js.jsp"/>

<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">

            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">来访客户</a>
                    </li>
                    <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">退园客户</a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">
                            <div class="ibox float-e-margins">
                                <div class="ibox-content"  style="padding-bottom: 0; border:none;">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <label>公司名称</label>
                                            <input type="text" placeholder="请输入公司名称" class="form-control" id="companyName">
                                        </div>
                                        <div class="col-md-2">
                                            <label>面积</label>
                                            <input type="number" placeholder="请输入面积" class="form-control" id="area" aria-required="true">
                                        </div>
                                        <div class="col-md-2">
                                            <label>价格</label>
                                            <input type="number" placeholder="请输入价格" class="form-control" id="price" aria-required="true">
                                        </div>
                                        <div class="col-md-2  date">
												<label>创建起始时间</label>
					                            <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="createdAtStr" placeholder="请输入创建时间">
					                            <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>					                    
					                    		<div style="position: absolute; top: 30px; right: 0px;">-</div>
					                    </div>  
					                     <div class="col-md-2  date">
												<label>创建结束时间</label>
					                            <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="createdAtStr2" placeholder="请输入创建时间">
					                            <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>					                    
					                    </div> 
                                    </div>
                                </div>

                                <div class="ibox-content"  style="padding-bottom: 0;border:none;">
                                    <div class="row">

                                        <div class="col-md-2">
                                            <label>层高</label>
                                            <input type="number" placeholder="请输入层高" class="form-control" id="layerHigh" aria-required="true">
                                        </div>

                                        <div class="col-md-2">
                                            <label>朝向</label>
                                            <select class="col-md-2 form-control m-b" name="orientation" id="orientation">、
                                                <option value="">全部</option>
                                                <option value="东">东</option>
                                                <option value="南">南</option>
                                                <option value="西">西</option>
                                                <option value="北">北</option>
                                                <option value="东南">东南</option>
                                                <option value="东北">东北</option>
                                                <option value="西南">西南</option>
                                                <option value="西北">西北</option>
                                            </select>
                                        </div>
                                        <%--<div class="col-md-2">
                                            <label>注册资金</label>
                                            <input type="number" placeholder="请输入注册资金" class="form-control" id="companyCapital" aria-required="true">
                                        </div>
                                        <div class="col-md-2">
                                            <label>经营类目</label>
                                            <input type="text" placeholder="请输入经营类目" class="form-control" id="businessType">
                                        </div>--%>
                                        <div class="col-md-2">
                                            <label>状态</label>
                                            <select class="form-control m-b" id="companyStatus" name="companyStatus">
                                                <option value="">全部</option>
                                                <option value="0">未读</option>
                                                <option value="1">已读</option>
                                            </select>
                                        </div>
                                          <div class="col-md-1" style="position: relative">
			                                    <label>单量</label> <input type="number" placeholder="请输入单量"
			                                                               class="form-control" id="number1" aria-required="true">
			                                    <div style="position: absolute; top: 30px; right: 0px;">-</div>
			                                </div>
			                                <div class="col-sm-1" style="position: relative">
			                                    <label>&nbsp;</label> <input type="number" placeholder="请输入单量"
			                                                                 class="form-control" id="number2" aria-required="true">
			                                </div>
                                        <c:if test="${userType eq 4}">
                                            <div class="col-md-2">
                                                <label>选择园区</label>
                                                <select class="form-control" id="parkId" name="parkId">
                                                    <c:forEach items="${parks}" var="park">
                                                        <option value="${park.id}">${park.parkName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </c:if>
                                        <div class="col-md-2">
                                            <button class="btn btn-primary"
                                                    onclick="getEtopCompanyIntentiontList()"
                                                    type="button" style="margin-top: 23px;;">搜索
                                            </button>
                                            <button class="btn btn-primary"
                                                    onclick="resetCondition()"
                                                    type="button" style="margin-top: 23px;;">重置
                                            </button>
                                        </div>
                                        <div class="hr-line-dashed" style="clear: both;"></div>
                                    </div>
                                </div>

                                <div class="col-sm-12">
                                    <!-- Example Events -->
                                    <div class="example-wrap">

                                        <div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
                                             role="group" <c:if test="${readonly eq true}">style="display: none;"</c:if> >
                                            <a class="btn btn-outline btn-default"
                                               onclick="totabs('<%=basePath%>/etopCompanyIntention/addPage.do','新建公司','<%=basePath %>/etopCompanyIntention/etopCompanyIntentionList.do');">
                                                <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                                <span>新建</span>
                                            </a>
                                            <button type="button" class="btn btn-outline btn-default" onclick="deleteComp()">
                                                <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <span>删除</span>
                                            </button>
                                        </div>

                                        <table id="compIntention"
                                               data-mobile-responsive="true"
                                               data-toggle="table"
                                               data-url="<%=basePath%>/etopCompanyIntention/getEtopCompanyIntentionList.do"
                                               data-data-type="json"
                                               data-side-pagination="server"
                                               data-pagination="true"
                                               data-query-params="queryParamsIntention"
                                               data-page-list="[5, 10, 20, 50, 100, 200]"
                                               data-striped="true"
                                                >
                                            <thead>
                                            <tr>
                                                <c:if test="${readonly eq false}">
                                                    <th data-field="state" data-checkbox="true"></th>
                                                </c:if>
                                                <th data-field="id" data-align="center" data-visible="false">公司id</th>
                                                <th data-field="companyName" data-align="center">公司名称</th>
                                                <th data-field="businessType" data-align="center">经营类目</th>
<!--                                                 <th data-field="companyMobile" data-align="center">公司电话</th> -->
                                                <th data-field="contact" data-align="center">联系人</th>
                                                <th data-field="mobile" data-align="center">电话号码</th>
                                                <th data-field="number" data-align="center">单量</th>
                                                <th data-field="times" data-align="center">拜访次数</th>
                                                <th data-field="companyStatus" data-align="center"
                                                    data-formatter="formatterCompIntentionStatus">状态
                                                </th>
                                                <th data-field="reviewStatus" data-align="center"
                                                    data-formatter="formatterReviewStatus">审核状态
                                                </th>
                                                <th data-field="remarks" data-align="center" data-width="30%">备注</th>
                                                <th data-field="createdAt" data-align="center">创建日期</th>
                                                <th data-align="center" data-formatter='formatterFun'>操作</th>
                                            </tr>
                                            </thead>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="tab-2" class="tab-pane">
                        <div class="panel-body">
                            <jsp:include page="etopCompBackPackList.jsp" />
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>


</div>
<script type="text/javascript">
$('.date').datepicker(
		
		{todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,autoclose:!0}
		
	)
	
    var tableIntention = $("#compIntention");

    /*查询条件*/
    function queryParamsIntention(params) {
        params.companyName = $("#companyName").val();
        params.companyStatus = $("#companyStatus option:selected").val();
        params.area = $("#area").val();
        params.price = $("#price").val();
        params.layerHigh = $("#layerHigh").val();
        params.orientation = $("#orientation").val();
        params.companyCapital = $("#companyCapital").val();
        params.businessType = $("#businessType").val();
        params.parkId = $("#parkId").val();
        params.createdAtStr = $("#createdAtStr").val();
        params.createdAtStr2 = $("#createdAtStr2").val();
        params.number1 = $("#number1").val();
        params.number2 = $("#number2").val();
        return params
    }

    function getEtopCompanyIntentiontList() {

        tableIntention.bootstrapTable('refresh', {url: '<%=basePath%>/etopCompanyIntention/getEtopCompanyIntentionList.do'});

    }

    function resetCondition(){
        $("#companyName").val("");
        $("#area").val("");
        $("#price").val("");
        $("#layerHigh").val("");
        $("#orientation").val("");
        $("#companyCapital").val("");
        $("#companyStatus").val("");
        $("#businessType").val("");
        $("#createdAt").val("");
        $("#createdAtStr1").val("");
        $("#createdAtStr2").val("");
        $("#number1").val("");
        $("#number2").val("");
    }

    tableIntention.on('click-row.bs.table', function (row, obj, index) {

        $("#id").val(obj.id);
    })

    /*增加表格按钮**/
    function formatterFun(value, row, index) {

        var getCompIntentionInfo = "getCompIntentionInfo('" + row.id + "')";
        var positive = "positive('" + row.id + "')";
        var res = '<button id="details" class="btn btn-info" onclick="' +
                getCompIntentionInfo + '" type="button" >详情</button> ' +
                '&nbsp;&nbsp; ';
        if(row.reviewStatus != 1 || row.reviewStatus == null){
            res = res + '<button id="in" class="btn btn-warning" <c:if test="${readonly eq true}">style="display: none;"</c:if> onclick="' +
                    positive + '" type="button" >入驻</button>';
        }
        return res;
    }

    function positive(ids) {

        swal({
            title: "确认入驻?",
            type: "warning" +
            "" +
            "",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "是",
            cancelButtonText: "否",
            closeOnConfirm: true
            }, function () {
            	var url=basePath+'/contract/edit.do?category=1&refCompanyId='+ids+'&ro=${readonly}';
            	totabs(url,"新建租赁合同");
            }
        );
    }

    function getCompIntentionInfo(id) {
        $.post(totabs('<%=basePath %>/etopCompanyIntention/getCompInfoById.do?ids=' + id + '&companyId=' +id + '&readonly=' + ${readonly}, '编辑公司信息' ,''))
    }

    function deleteComp() {
        var selections = tableIntention.bootstrapTable('getSelections');
        if(selections.length){
            var ids = [];
            if (selections.length == 0)
                return;
            for (var i = 0; i < selections.length; i++) {
                ids = ids + selections[i].id + ",";
            }
            var reg=/,$/gi;
            ids = ids.replace(reg,"");
            swal({
                    title: "确定删除这"+selections.length+"家公司?",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "是",
                    cancelButtonText: "否",
                    closeOnConfirm: false
                }, function () {
                    $.get("deleteEtopCompanyIntentionInfo.do", {
                        "ids": ids
                    }, function (data) {
                        if (data.status == 10001) {
                            swal({
                                title: "成功删除" + data.data + "家公司！",
                                type: "success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            }, function () {
                                tableIntentionrRefresh('getEtopCompanyIntentionList.do');
                            });
                        } else {
                            swal({
                                title: data.msg,
                                type: "error",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            }, function () {
                                tableIntentionrRefresh('getEtopCompanyIntentionList.do');
                            });
                        }

                    })
                }
            );
        }else{
            swal({
                title: "请先选择公司!",
                timer: 1000,
                showConfirmButton: false
            });
        }

    }

    //新增公司table
    function totabs(herf,msg) {
        window.parent.addTable(herf,msg);
    }

    function tableIntentionrRefresh(dataUrl){

        tableIntention.bootstrapTable('refresh',{url:dataUrl});

    }
</script>
</body>
</html>