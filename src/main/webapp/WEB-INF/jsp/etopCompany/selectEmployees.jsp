<!-- 选择员工(目前用不到)  -->
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
    <title>员工绑定-客户管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <!-- Panel Other -->
    <div class="ibox float-e-margins">
        <div class="ibox-content">
            <div class="row row-lg">
                <div class="col-sm-12">
                    <div class="row">

                        <div class="col-sm-2">
                            <label>员工姓名</label>
                            <input type="text" class=" form-control" placeholder="" id="userName">
                        </div>
                        <div class="col-sm-2">
                            <!--<label>确认搜索</label>-->
                            <button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/etopCompEmployees/getEmployeesList.do')"
                                    type="button" style="margin-top: 23px;;">搜索
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <!-- Example Events -->
                    <div class="example-wrap">

                        <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                            <a class="btn btn-outline btn-default" onclick="openBindEmployee()"/>
                                <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                <span>绑定</span>
                            </a>
                        </div>

                        <table id="table1"
                               data-mobile-responsive="true"
                               data-toggle="table"
                               data-url="<%=basePath%>/etopCompEmployees/getEmployeesList.do"
                               data-data-type="json"
                               data-side-pagination="server"
                               data-pagination="true"
                               data-query-params="queryParams"
                               data-page-list="[5, 10, 20, 50, 100, 200]"
                                >
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true"></th>
                                <th data-field="id" data-align="center" data-visible="false">id</th>
                                <th data-field="employeesId" data-align="center" data-visible="false">员工id</th>
                                <th data-field="employeesName" data-align="center">员工姓名</th>
                                <th data-field="jobs" data-align="center">岗位</th>
                                <th data-field="hiredate" data-align="center" >入职时间</th>
                                <th data-field="mobile" data-align="center" >电话号码</th>
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
<script type="text/javascript">
    var table = $('#table1');
    function tableRefresh(dataUrl) {
        table.bootstrapTable('refresh', {url: dataUrl});
    }
    /*查询条件*/
    function queryParams(params) {
        params.employeesName = $("#employeesName").val();

        return params
    }

    function openBindEmployee(){

        var selections = table.bootstrapTable('getSelections');
        var employeesIds = [];
        if (selections.length == 0)
            return;
        for (var i = 0; i < selections.length; i++) {
            employeesIds = employeesIds + selections[i].employeesId + ",";
            var companyId = selections[i].companyId;
        }
        var reg=/,$/gi;
        employeesIds = employeesIds.replace(reg,"");

        swal({
            title: "确认绑定这"+selections.length+"名员工",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "是",
            cancelButtonText: "否",
            closeOnConfirm: false
            }, function(){
                $.get("<%=basePath %>/etopCompEmployees/bindEmployee.do",
                    {
                        "companyId":companyId,
                        "userIds": employeesIds
                    },function(data){
                        if (data.status == 10001) {
                            swal({
                                title: "成功绑定" + data.data + "名员工！",
                                type: "success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            }, function () {
                                tableRefresh('<%=basePath %>/etopCompEmployees/getEmployeesList.do');
                            });
                        } else {
                            swal({
                                title: data.msg,
                                type: "error",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            }, function () {
                                tableRefresh('<%=basePath %>/etopCompEmployees/getEmployeesList.do');
                            });
                        }
                    })
            }
        );
    }
</script>
</body>
</html>
