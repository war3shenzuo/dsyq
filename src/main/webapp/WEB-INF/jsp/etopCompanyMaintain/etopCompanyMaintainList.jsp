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
                        <label>拜访时间</label>
                            <div class="input-group date m-b">
                                <span class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </span>
                                <input type="text" class="datepicker form-control" id="createdAt" name="createdAt" placeholder="请输入拜访时间">
                            </div>
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-primary"
                                onclick="getEtopCompanyMaintainList()"
                                type="button" style="margin-top: 23px;;">搜索
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
                           onclick="openMainPage('新建拜访','65%','65%','<%=basePath %>/etopCompanyMaintain/addVisitPage.do?companyId=${companyId}&companyType=${companyType}','<%=basePath%>/etopCompanyMaintain/getEtopCompanyMaintainList.do?companyId=${companyId}&companyType=${companyType}')">
                            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                            <span>新建</span>
                        </a>
                        <button type="button" class="btn btn-outline btn-default" onclick="deleteMaintain()">
                            <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <span>删除</span>
                        </button>
                    </div>

                    <table id="tableMaintain"
                           data-mobile-responsive="true"
                           data-toggle="table"
                           data-url="<%=basePath%>/etopCompanyMaintain/getEtopCompanyMaintainList.do?companyId=${companyId}&companyType=${companyType}"
                           data-data-type="json"
                           data-side-pagination="server"
                           data-pagination="true"
                           data-query-params="queryParams"
                           data-page-list="[5, 10, 20, 50, 100, 200]"
                           data-striped="true"
                            >
                        <thead>
                        <tr>
                            <th data-field="state" data-checkbox="true"></th>
                            <th data-field="id" data-align="center" data-visible="false">id</th>
                            <th data-field="companyId" data-align="center" data-visible="false">公司id</th>
                            <th data-field="companyName" data-align="center">公司名称</th>
                            <th data-field="parkName" data-align="center">园区名称</th>
                            <th data-field="receiver" data-align="center">接待人</th>
                            <th data-field="createdAt" data-align="center">拜访时间</th>
                            <th data-field="cause" data-align="center">拜访原因</th>
                            <th data-field="content" data-align="center">拜访内容</th>
                            <th data-align="center" data-formatter='formatterMaintain'>操作</th>
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

    $('.datepicker').datepicker({
        todayBtn: "linked",
        keyboardNavigation: !1,
        forceParse: !1,
        autoclose: !0
    });

    var tableMaintain = $("#tableMaintain");

    /*查询条件*/
    function queryParams(params) {
        params.createdAt = $("#createdAt").val();
        return params
    }

    function getEtopCompanyMaintainList() {

        tableMaintain.bootstrapTable('refresh', {url: '<%=basePath%>/etopCompanyMaintain/getEtopCompanyMaintainList.do?companyId=${companyId}&companyType=${companyType}'});

    }

    tableMaintain.on('click-row.bs.table', function (row, obj, index) {

        $("#companyId").val(obj.companyId);
    })

    /*增加表格按钮**/
    function formatterMaintain(value, row, index) {

        var getMaintainInfo = "getMaintainInfo('" + row.id + "','"+ row.companyId +"')";
        return '<button class="btn btn-info" onclick="' +
                getMaintainInfo + '" type="button" >详情</button> '
    }

    function getMaintainInfo(id,companyId) {
        $.post(openMainPage('编辑拜访记录','65%','65%','<%=basePath %>/etopCompanyMaintain/getMaintainInfo.do?id=' + id + "&companyId=" + companyId + "&companyType=" + ${companyType} + "&readonly=" + ${readonly},
                '<%=basePath%>/etopCompanyMaintain/getEtopCompanyMaintainList.do?companyId=${companyId}&companyType=${companyType}'))
    }

    function deleteMaintain() {
        var selectionsMaintain = tableMaintain.bootstrapTable('getSelections');
        if(selectionsMaintain.length){
            var ids = [];
            if (selectionsMaintain.length == 0)
                return;
            for (var i = 0; i < selectionsMaintain.length; i++) {
                ids = ids + selectionsMaintain[i].id + ",";
            }
            var reg=/,$/gi;
            ids = ids.replace(reg,"");
            swal({
                        title: "确定删除这"+selectionsMaintain.length+"条拜访记录?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是",
                        cancelButtonText: "否",
                        closeOnConfirm: false
                    }, function () {
                        $.get("<%=basePath %>/etopCompanyMaintain/deleteEtopCompanyMaintainInfo.do", {
                            "ids": ids
                        }, function (data) {
                            if (data.status == 10001) {
                                swal({
                                    title: "成功删除" + data.data + "条拜访记录！",
                                    type: "success",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('<%=basePath %>/etopCompanyMaintain/getEtopCompanyMaintainList.do?companyId=${companyId}&companyType=${companyType}');
                                });
                            } else {
                                swal({
                                    title: data.msg,
                                    type: "error",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('<%=basePath %>/etopCompanyMaintain/getEtopCompanyMaintainList.do?companyId=${companyId}&companyType=${companyType}');
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

    function tableRefresh(dataUrl){

        tableMaintain.bootstrapTable('refresh',{url:dataUrl});

    }

    function openMainPage(title,height,width,addUrl,refreshUrl){
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
                    tableMaintain.bootstrapTable('refresh',{url:refreshUrl});
                }
                if (typeof(tree) != "undefined") {
                    tree.refresh();
                }
            }
        });
    }

</script>
