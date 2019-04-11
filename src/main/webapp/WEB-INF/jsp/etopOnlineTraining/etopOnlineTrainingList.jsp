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
        <title>线上课程列表-课程管理</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <jsp:include page="../shared/css.jsp"/>
    </head>
    <body class="gray-bg">
        <input type="hidden" id="companyId" >
		<input id="user" value='${user}' type="hidden">
        <div class="wrapper wrapper-content animated fadeInRight">

            <!-- Panel Other -->
            <div class="ibox float-e-margins">

                <div class="ibox-content">
                    <div class="row row-lg">
                        <div class="col-sm-12">
                            <div class="row">
                                <div class="col-md-2">
                                    <label>编号</label>
                                    <input type="text" placeholder="请输入编号" class="form-control" id="courseId">
                                </div>
                                <div class="col-md-2">
                                    <label>课程类型</label>
                                    <select class="form-control m-b" id="courseType" name="courseType">
                                        <option value="">全部</option>
                                        <option value="1">高管研修</option>
                                        <option value="2">创业辅助</option>
                                        <option value="3">淘系美工</option>
                                        <option value="4">淘系运营</option>
                                        <option value="5">淘系客服</option>
                                        <option value="6">淘系推广</option>
                                        <option value="7">淘系无线</option>
                                        <option value="8">微商</option>
                                        <option value="9">京东系列</option>
                                        <option value="10">跨境系列</option>
                                        <option value="11">其他平台</option>
                                        <option value="12">其他类型</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <label>课程主题</label>
                                    <input type="text" placeholder="请输入课程主题" class="form-control" id="courseName">
                                </div>
                                <div class="col-md-2">
                                    <label>适用平台</label>
                                    <select class="form-control m-b" id="platform" name="platform">
                                        <option value="">全部</option>
                                        <option value="1">淘宝</option>
                                        <option value="2">微信</option>
                                        <option value="3">京东</option>
                                        <option value="4">苏宁</option>
                                        <option value="5">跨境平台</option>
                                        <option value="6">其他平台</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <label>适用岗位</label>
                                    <select class="form-control m-b" id="post" name="post">
                                        <option value="">全部</option>
                                        <option value="1">客户</option>
                                        <option value="2">美工</option>
                                        <option value="3">推广</option>
                                        <option value="4">运营</option>
                                        <option value="5">视觉设计</option>
                                        <option value="6">活动策划</option>
                                        <option value="7">企业高管</option>
                                    </select>
                                </div>
                               
                                </div>
                                <div class="row">
                                 <div class="col-md-2">
                                    <label>发布园区</label>
                                    <input type="text" placeholder="请输入发布园区" class="form-control" id="searchValue">
                                 </div>
<!--                                 <div class="col-md-2"> -->
<!--                                     <label>发布园区</label> -->
<!--                                     <input type="text" placeholder="请输入发布园区" class="form-control" id="park"> -->
<!--                                 </div> -->
                                <div class="col-md-2">
                                    <label style=" width:100%; display: inline-block; ">题库数量</label>
                                    <input type="text" style=" width:43%; display: inline-block; " placeholder="" class="form-control" id="questionNum1"> —— <input type="text" style=" width:43%; display: inline-block; " placeholder="" class="form-control" id="questionNum2">
                                </div>
                                <div class="col-md-2">
                                    <label style=" width:100%; display: inline-block; ">参与人数</label>
                                    <input type="text" style=" width:43%; display: inline-block; " placeholder="" class="form-control" id="num1"> —— <input type="text" style=" width:43%; display: inline-block; " placeholder="" class="form-control" id="num2">
                                </div>
                                <div class="col-md-2">
                                    <label style=" width:100%; display: inline-block; ">综合评分</label>
                                    <input type="text" style=" width:43%; display: inline-block; " placeholder="" class="form-control" id="avgScore1"> —— <input type="text" style=" width:43%; display: inline-block; " placeholder="" class="form-control" id="avgScore2">
                                </div>
                                <div class="col-md-2">
                                    <button class="btn btn-primary"
                                            onclick="getEtopCompanyList()"
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

                                <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group"
                                     <c:if test="${readonly}">style="display: none;"</c:if> >
                                    <a class="btn btn-outline btn-default"
                                        onclick="totabs('<%=basePath%>/etopOnlineTraining/addPage.do','新建线上课程');">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                        <span>新建</span>
                                    </a>
                                    <button type="button" class="btn btn-outline btn-default" onclick="deleteOnline()">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <span>删除</span>
                                    </button>
                                </div>

                                <table id="OnlineTrainingTable"
                                       data-mobile-responsive="true"
                                       data-toggle="table"
                                       data-url="<%=basePath%>/etopOnlineTraining/getEtopOnlineTrainingList.do"
                                       data-data-type="json"
                                       data-side-pagination="server"
                                       data-pagination="true"
                                       data-query-params="queryParams"
                                       data-page-list="[5, 10, 20, 50, 100, 200]"
                                       data-striped="true"
                                        >
                                    <thead>
                                    <tr>
                                        <c:if test="${readonly eq false}">
                                            <th data-field="state" data-checkbox="true"></th>
                                        </c:if>
                                        <th data-field="id" data-align="center" data-visible="false">id</th>
                                        <th data-field="courseId" data-align="center">编号</th>
                                        <th data-field="courseType" data-align="center" data-formatter="formatterCourseType">课程类型</th>
                                        <th data-field="courseName" data-align="center">课程主题</th>
                                        <th data-field="platform" data-align="center" data-formatter="formatterPlatform">适用平台</th>
                                        <th data-field="post" data-align="center" data-formatter="formatterPost">适用岗位</th>
                                        <th data-field="targetNumber" data-align="center">发布园区数</th>
                                        <th data-field="questionNum" data-align="center">题库数量</th>
                                        <th data-field="testNum" data-align="center">参与人数</th>
                                        <th data-field="avgScore" data-align="center">综合评分</th>
                                        <%--<th data-field="companyStatus" data-align="center" data-formatter="formatterCompStatus">状态</th>--%>
                                        <th data-align="center" data-formatter='formatterFun' >操作</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                            <!-- End Example Events -->
                        </div>
                    </div>
                </div>
            </div>

            <!-- End Panel Other -->
        </div>
        <jsp:include page="../shared/js.jsp"/>
        <script type="text/javascript" src="<%=basePath %>/myjs/etopTraining.js"></script>
        <script type="text/javascript">

            var table = $("#OnlineTrainingTable");

            /*查询条件*/
            function queryParams(params) {
                params.courseId = $("#courseId").val();
                params.courseName = $("#courseName").val();
                params.courseType = $("#courseType").val();
                params.platform = $("#platform").val();
                params.post = $("#post").val();
                params.questionNum1 = $("#questionNum1").val();
                params.questionNum2 = $("#questionNum2").val();
                params.num1 = $("#num1").val();
                params.num2 = $("#num2").val();
                params.avgScore1 = $("#avgScore1").val();
                params.avgScore2 = $("#avgScore2").val();
                params.searchValue = $("#searchValue").val();
                return params
            }

            function resetCondition(){
                $("#courseId").val("");
                $("#courseType").val("");
                $("#courseName").val("");
                $("#platform").val("");
                $("#post").val("");
                $("#questionNum1").val("");
                $("#questionNum2").val("");
                $("#num1").val("");
                $("#num2").val("");
                $("#avgScore1").val("");
                $("#avgScore2").val("");
                $("#searchValue").val("");
            }

            function getEtopCompanyList(){

                table.bootstrapTable('refresh',{url:'<%=basePath%>/etopOnlineTraining/getEtopOnlineTrainingList.do'});

            }

            /*增加表格按钮*/
            function formatterFun(value,row,index){

                var getOnlineTrainingInfo = "getOnlineTrainingInfo('"+row.id+"')";
                var res = '<button class="btn btn-info" onclick="'+
                        getOnlineTrainingInfo + '" type="button" >详情</button> ' +
                        '&nbsp;&nbsp; ';
                return res
            }

            function getOnlineTrainingInfo(id){
                $.post(totabs('<%=basePath %>/etopOnlineTraining/getOnlineTrainingInfo.do?id='+id+'&readonly='+${readonly},'编辑课程信息'))
            }

            function deleteOnline(){

                var selections = table.bootstrapTable('getSelections');
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
                            title: "确定删除这"+selections.length+"条课程?",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "是",
                            cancelButtonText: "否",
                            closeOnConfirm: false
                        }, function () {
                            $.get("deleteOnlineTrainingInfo.do", {
                                "ids": ids
                            }, function (data) {
                                if (data.status == 10001) {
                                    swal({
                                        title: "成功删除" + data.data + "条课程!",
                                        type: "success",
                                        confirmButtonText: "确定",
                                        closeOnConfirm: true
                                    }, function () {
                                        tableRefresh('getEtopOnlineTrainingList.do');
                                    });
                                } else {
                                    swal({
                                        title: data.msg,
                                        type: "error",
                                        confirmButtonText: "确定",
                                        closeOnConfirm: true
                                    }, function () {
                                        tableRefresh('getEtopOnlineTrainingList.do');
                                    });
                                }

                            })
                        }
                    );
                }else{
                    swal({
                        title: "请先选择课程主题!",
                        timer: 1000,
                        showConfirmButton: false
                    });
                }

            }

            //新增table
            function totabs(herf,msg) {
        		window.parent.addTable(herf,msg);
        	}

        </script>
    </body>
</html>