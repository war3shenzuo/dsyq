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
        <title>线下计划列表-培训管理</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <jsp:include page="../shared/css.jsp"/>
    </head>
    <body class="gray-bg">
        <input type="hidden" id="companyId" >

        <div class="wrapper wrapper-content animated fadeInRight">

            <!-- Panel Other -->
            <div class="ibox float-e-margins">

                <div class="ibox-content">
                    <div class="row row-lg">
                        <div class="col-sm-12">
                            <div class="row">
                                <div class="col-md-2">
                                    <label>培训日期</label>
                                    <div class="input-group date m-b">
                                        <span class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </span>
                                        <input type="text" class="form_datetime form-control" id="trainDate" name="trainDate" placeholder="请选择日期">
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <label>培训主题</label>
                                    <input type="text" placeholder="请输入培训主题" class="form-control" id="title">
                                </div>
                                <div class="col-md-2">
                                    <label>负责人姓名</label>
                                    <input type="text" placeholder="请输入负责人姓名" class="form-control" id="charge">
                                </div>
                                <div class="col-md-1">
                                    <label>培训类型</label>
                                    <select class="form-control m-b" id="trainType" name="trainType">
                                        <option value="">全部</option>
                                        <option value="13">沙龙</option>
                                        <option value="14">论坛</option>
                                        <option value="15">会议</option>
                                        <option value="16">文娱</option>
                                        <option value="17">体育</option>
                                        <option value="12">其他类型</option>
                                    </select>
                                </div>
                                <div class="col-md-1">
                                    <label>状态</label>
                                    <select class="form-control m-b" id="status" name="status">
                                        <option value="">全部</option>
                                        <option value="0">招生中</option>
                                        <option value="1">进行中</option>
                                        <option value="2">已完成</option>
                                        <option value="3">已取消</option>
                                        <option value="4">未发布</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <button class="btn btn-primary"
                                            onclick="getEtopTrainPlanList()"
                                            type="button" style="margin-top: 23px;;">搜索
                                    </button>
                                    <button class="btn btn-primary"
                                            onclick="resetrr()"
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
                                        onclick="totabs('<%=basePath%>/etopTrainPlan/addPage.do','新建线下计划');">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                        <span>新建</span>
                                    </a>
                                </div>

                                <table id="trainPlanTable"
                                       data-mobile-responsive="true"
                                       data-toggle="table"
                                       data-url="<%=basePath%>/etopTrainPlan/getEtopTrainPlanList.do"
                                       data-data-type="json"
                                       data-side-pagination="server"
                                       data-pagination="true"
                                       data-query-params="queryParams"
                                       data-page-list="[5, 10, 20, 50, 100, 200]"
                                       data-striped="true"
                                        >
                                    <thead>
                                    <tr>
                                        <th data-field="id" data-align="center" data-visible="false">id</th>
                                        <th data-field="courseId" data-align="center" data-visible="false">培训id</th>
                                        <th data-field="trainDate" data-align="center">培训日期</th>
                                        <th data-field="trainType" data-align="center" data-formatter="formatterCourseType">培训类型</th>
                                        <th data-field="status" data-align="center" data-formatter="formatterCouresStatus">状态</th>
                                        <th data-field="title" data-align="center">培训主题</th>
                                        <th data-field="charge" data-align="center">负责人</th>
                                        <th data-field="department" data-align="center">参与部门</th>
                                        <th data-field="target" data-align="center">发布对象</th>
                                        <th data-field="expectPeople" data-align="center">预计人数</th>
                                        <th data-field="expectDate" data-align="center">预计时长</th>
                                        <th data-field="expectPlace" data-align="center">举办地点</th>
                                        <th data-field="cycle" data-align="center">准备周期(天)</th>
                                        <th data-field="remindDate" data-align="center">提醒日期</th>
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

            $('.form_datetime').datepicker({
                todayBtn: "linked",
                keyboardNavigation: !1,
                forceParse: !1,
                autoclose: !0
            });

            var table = $("#trainPlanTable");

            /*查询条件*/
            function queryParams(params) {
                params.trainDate = $("#trainDate").val();
                params.trainType = $("#trainType").val();
                params.title = $("#title").val();
                params.charge = $("#charge").val();
                params.status = $("#status").val();
                return params
            }

            function resetrr(){
                $("#trainDate").val("");
                $("#trainType").val("");
                $("#title").val("");
                $("#charge").val("");
                $("#status").val("");
            }

            function getEtopTrainPlanList(){

                table.bootstrapTable('refresh',{url:'<%=basePath%>/etopTrainPlan/getEtopTrainPlanList.do'});

            }

            /*增加表格按钮**/
            function formatterFun(value,row,index){

                var getEtopTrainPlanInfo = "getEtopTrainPlanInfo('"+row.courseId+"')";
                var releasePlan = "releasePlan('"+row.courseId+"')";
                var deletePlan = "deletePlan('"+row.id+"')";
                var res = '<button class="btn btn-info" onclick="'+
                        getEtopTrainPlanInfo + '" type="button" >详情</button> ' +
                        '&nbsp;&nbsp; ';
                if(row.status == 4){
                    res = res + '<button class="btn btn-primary" <c:if test="${readonly eq true}">style="display: none;"</c:if> onclick="' +
                            releasePlan + '" type="button" >发布</button> ' ;
                }
                return res
            }

            function getEtopTrainPlanInfo(courseId){
                $.post(totabs('<%=basePath %>/etopTrainPlan/getEtopTrainPlanInfo.do?courseId='+courseId+'&readonly='+${readonly},'编辑培训信息'))
            }

            function releasePlan(courseId){
                $.post(totabs('<%=basePath%>/etopOfflineTraining/addPage.do?courseId='+courseId,'新建培训信息'))
            }

            function deletePlan(id){

                swal({
                        title: "确定删除此条培训计划?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是",
                        cancelButtonText: "否",
                        closeOnConfirm: false
                    }, function () {
                        $.get("deleteEtopTrainPlan.do", {
                            "id": id
                        }, function (data) {
                            if (data.status == 10001) {
                                swal({
                                    title: "成功删除此条培训计划!",
                                    type: "success",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('getEtopTrainPlanList.do');
                                });
                            } else {
                                swal({
                                    title: data.msg,
                                    type: "error",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('getEtopTrainPlanList.do');
                                });
                            }

                        })
                    }
                );

            }

            //新增table
            function totabs(herf,msg) {
        		window.parent.addTable(herf,msg);
        	}

        </script>
    </body>
</html>