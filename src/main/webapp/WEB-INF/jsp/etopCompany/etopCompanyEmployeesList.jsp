<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>

<div class="ibox float-e-margins">

    <div class="ibox-content">
        <div class="row row-lg">
            <div class="col-sm-12">
                <div class="row">
                    <div class="col-md-2">
                        <label>员工姓名</label>
                        <input type="text" placeholder="请输入员工姓名" class="form-control m-b" id="employeesName">
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-primary"
                                onclick="getEtopCompEmployeesList()"
                                type="button" style="margin-top: 23px;;">搜索
                        </button>
                    </div>
                    <div class="hr-line-dashed" style="clear: both;"></div>
                </div>
            </div>
            <div class="col-sm-12">
                <!-- Example Events -->
                <div class="example-wrap">


                    <div <c:if test="${permissions eq false}">style="display: none;"</c:if> >

                        <div class="col-md-2" style="padding-left: 0">
                            <input type="text" placeholder="请输入绑定账号" class="form-control m-b" id="account">
                        </div>
                        <button class="btn btn-primary" onclick="binding()" type="button" >绑定</button>

                        <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">

                            <a class="btn btn-outline btn-default" onclick="openBindUser()">
                                <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                <span>新增</span>
                            </a>
                            <button type="button" class="btn btn-outline btn-default" onclick="unbindling()">
                                <i class="glyphicon glyphicon-remove-circle" aria-hidden="true"></i> <span>解绑</span>
                            </button>
                        </div>
                    </div>

                    <table id="empEmployees"
                           data-mobile-responsive="true"
                           data-toggle="table"
                           data-url="<%=basePath%>/etopCompEmployees/getEtopCompEmployeesList.do"
                           data-data-type="json"
                           data-side-pagination="server"
                           data-pagination="true"
                           data-query-params="queryParamsEmployees"
                           data-page-list="[5, 10, 20, 50, 100, 200]"
                           data-striped="true"
                            >
                        <thead>
                        <tr>
                            <c:if test="${readonly eq false}">
                                <th data-field="state" data-checkbox="true"></th>
                            </c:if>
                            <th data-field="companyId" data-align="center" data-visible="false">公司id</th>
                            <th data-field="employeesId" data-align="center" data-visible="false">员工id</th>
                            <th data-field="userName" data-align="center">员工账号</th>
                            <th data-field="employeesName" data-align="center">员工姓名</th>
                            <th data-field="employeesSex" data-align="center" data-formatter="formatterEmploySex">性别</th>
                            <th data-field="mobile" data-align="center">号码</th>
                            <th data-field="department" data-align="center">部门</th>
                            <th data-field="jobs" data-align="center">岗位</th>
                            <th data-field="hiredate" data-align="center">入职时间</th>
                            <th data-field="address" data-align="center">户籍所在地</th>
                            <th data-align="center" data-formatter='formatterFunEmployees'>操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <!-- End Example Events -->
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    var empEmployeesTable = $("#empEmployees");

    /*查询条件*/
    function queryParamsEmployees(params) {
        params.employeesName = $("#employeesName").val();
        params.companyId = '${companyId}';
        return params;
    }

    function getEtopCompEmployeesList(){

        empEmployeesTable.bootstrapTable('refresh',{url:'<%=basePath%>/etopCompEmployees/getEtopCompEmployeesList.do'});

    }

    //新增员工
    function openBindUser(){
        openEmployeesPage('新增员工','90%','80%','<%=basePath%>/etopCompEmployees/addUserPage.do?companyId=${companyId}','<%=basePath%>/etopCompEmployees/getEtopCompEmployeesList.do');
    }

    empEmployeesTable.on('click-row.bs.table', function (row,obj,index) {

        $("#companyId").val(obj.companyId);
    })

    /*增加表格按钮**/
    function formatterFunEmployees(value,row,index){

        var getEtopCompEmployeesInfo = "getEtopCompEmployeesInfo('"+row.employeesId+"')";
        return '<button class="btn btn-primary" onclick="'+
                getEtopCompEmployeesInfo+'" type="button" >详情</button> '/*+
                '&nbsp;&nbsp; '+
                '<button class="btn btn-primary" onclick="'+
                unbundling+'" type="button" >解绑</button>';*/
    }

    function getEtopCompEmployeesInfo(employeesId){
        $.post(totabs('<%=basePath %>/etopCompEmployees/getEtopCompEmployeesInfoById.do?employeesId='+employeesId,'查看员工信息'))
    }

    function binding(){
        var paramsEmployees = {
            "account" : $('#account').val(),
            "companyId" : '${companyId}'
        }
        $.post("<%=basePath %>/etopCompEmployees/bindEmployee.do", paramsEmployees, function(data){

            if (data.status == 10001) {
                swal({
                    title: data.msg,
                    type: "success",
                    confirmButtonText: "确定",
                    closeOnConfirm: true
                }, function () {
                    tableEmployees('<%=basePath %>/etopCompEmployees/getEtopCompEmployeesList.do');
                });
            } else {
                swal({
                    title: data.msg,
                    type: "error",
                    confirmButtonText: "确定",
                    closeOnConfirm: true
                }, function () {
                    tableEmployees('<%=basePath %>/etopCompEmployees/getEtopCompEmployeesList.do');
                });
            }

        });
    }

    function unbindling(){

        var selectionsEmployees = empEmployeesTable.bootstrapTable('getSelections');

        if(selectionsEmployees.length){
            var employeesIds = [];
            if (selectionsEmployees.length == 0)
                return;
            for (var i = 0; i < selectionsEmployees.length; i++) {
                employeesIds = employeesIds + selectionsEmployees[i].employeesId + ",";
            }
            var reg=/,$/gi;
            employeesIds = employeesIds.replace(reg,"");

            swal({
                    title: "确认解绑这"+selectionsEmployees.length+"名员工",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "是",
                    cancelButtonText: "否",
                    closeOnConfirm: false
                }, function(){
                    $.get("<%=basePath %>/etopCompEmployees/unbundlingEtopCompEmployees.do",
                        {
                            "company_id":'${companyId}',
                            "employeesIds": employeesIds
                        },function(data){
                            if (data.status == 10001) {
                                swal({
                                    title: "成功解绑" + data.data + "名员工！",
                                    type: "success",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableEmployees('<%=basePath %>/etopCompEmployees/getEtopCompEmployeesList.do');
                                });
                            } else {
                                swal({
                                    title: data.msg,
                                    type: "error",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableEmployees('<%=basePath %>/etopCompEmployees/getEtopCompEmployeesList.do');
                                });
                            }
                        })
                }
            );
        }else{
            swal({
                title: "请先选择员工!",
                timer: 1000,
                showConfirmButton: false
            });
        }

    }

    function openEmployeesPage(title,height,width,addUrl,refreshUrl){
        //iframe层
        layer.open({
            type: 2,
            title: title,
            closeBtn: "1",
            shadeClose: true,
            shade: [0],
            shift: 2,
            area: [height, width],
            content: addUrl,
            end: function(){
                if(refreshUrl!=""){
                    empEmployeesTable.bootstrapTable('refresh',{url:refreshUrl});
                }
                if (typeof(tree) != "undefined") {
                    tree.refresh();
                }
            }
        });
    }

    function tableEmployees(dataUrl){

        empEmployeesTable.bootstrapTable('refresh',{url:dataUrl});

    }

    //新增公司table
    function totabs(herf,msg) {
        window.parent.addTable(herf,msg);
    }

</script>