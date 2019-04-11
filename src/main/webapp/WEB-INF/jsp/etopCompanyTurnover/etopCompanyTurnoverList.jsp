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
                        <label>平台</label>
                        <select class="form-control m-b" id="platform" name="platform">
                            <option value="">全部</option>
                            <option value="淘宝">淘宝</option>
                            <option value="天猫">天猫</option>
                            <option value="京东">京东</option>
                            <option value="亚马逊">亚马逊</option>
                            <option value="实体店">实体店</option>
                            <option value="加盟商">加盟商</option>
                            <option value="代理">代理</option>
                            <option value="其他">其他</option>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label>日期</label>
                        <div class="input-group date m-b">
                            <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </span>
                            <input type="text" class="form_datetime form-control" id="yearsDate" name="yearsDate" placeholder="请选择日期">
                        </div>
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-primary"
                                onclick="getEtopCompanyTurnoverList()"
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
                           onclick="openTurnoverPage('新建营业额','60%','65%','<%=basePath %>/etopCompanyTurnover/addVisitPage.do?companyId=${companyId}','<%=basePath%>/etopCompanyTurnover/getEtopCompanyTurnoverList.do?companyId=${companyId}')">
                            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                            <span>新建</span>
                        </a>
                        <button type="button" class="btn btn-outline btn-default" onclick="deleteTurnover()">
                            <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <span>删除</span>
                        </button>
                    </div>

                    <table id="tableTurnover"
                           data-mobile-responsive="true"
                           data-toggle="table"
                           data-url="<%=basePath%>/etopCompanyTurnover/getEtopCompanyTurnoverList.do?companyId=${companyId}"
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
                            <th data-field="platform" data-align="center">平台</th>
                            <th data-field="yearsDate" data-align="center">日期</th>
                            <th data-field="turnover" data-align="center">营业额</th>
                            <th data-align="center" data-formatter='formatterTurnover'>操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <!-- End Example Events -->
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=basePath %>/myjs/datetimepicker_zh-CN.js"></script>
<script type="text/javascript">

    $('.form_datetime').datetimepicker({
        format: 'yyyy-mm',
        autoclose: true,
        todayBtn: true,
        startView: 'year',
        minView:'year',
        maxView:'decade',
        language: 'zh-CN'
    });

    var tableTurnover = $("#tableTurnover");

    /*查询条件*/
    function queryParams(params) {
        params.platform = $("#platform").val();
        params.yearsDate = $("#yearsDate").val();
        return params
    }

    function resetCondition(){
        $("#platform").val("");
        $("#yearsDate").val("");
    }

    function getEtopCompanyTurnoverList() {

        tableTurnover.bootstrapTable('refresh', {url: '<%=basePath%>/etopCompanyTurnover/getEtopCompanyTurnoverList.do?companyId=${companyId}'});

    }

    tableTurnover.on('click-row.bs.table', function (row, obj, index) {

        $("#companyId").val(obj.companyId);
    })

    /*增加表格按钮**/
    function formatterTurnover(value, row, index) {

        var getTurnoverInfo = "getTurnoverInfo('" + row.id + "','"+ row.companyId +"')";
        return '<button class="btn btn-info" onclick="' +
                getTurnoverInfo + '" type="button" >详情</button> '
    }

    function getTurnoverInfo(id,companyId) {
        $.post(openTurnoverPage('编辑营业额记录','65%','65%','<%=basePath %>/etopCompanyTurnover/getTurnoverInfo.do?id=' + id + "&companyId=" + companyId + "&readonly=" + ${readonly},
                '<%=basePath%>/etopCompanyTurnover/getEtopCompanyTurnoverList.do?companyId=${companyId}'))
    }

    function deleteTurnover() {
        var selectionsTurnover = tableTurnover.bootstrapTable('getSelections');
        if(selectionsTurnover.length){
            var ids = [];
            if (selectionsTurnover.length == 0)
                return;
            for (var i = 0; i < selectionsTurnover.length; i++) {
                ids = ids + selectionsTurnover[i].id + ",";
            }
            var reg=/,$/gi;
            ids = ids.replace(reg,"");
            swal({
                        title: "确定删除这"+selectionsTurnover.length+"条营业额数据?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是",
                        cancelButtonText: "否",
                        closeOnConfirm: false
                    }, function () {
                        $.get("<%=basePath %>/etopCompanyTurnover/deleteEtopCompanyTurnoverInfo.do", {
                            "ids": ids
                        }, function (data) {
                            if (data.status == 10001) {
                                swal({
                                    title: "成功删除" + data.data + "条拜访记录！",
                                    type: "success",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('<%=basePath %>/etopCompanyTurnover/getEtopCompanyTurnoverList.do?companyId=${companyId}&companyType=${companyType}');
                                });
                            } else {
                                swal({
                                    title: data.msg,
                                    type: "error",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('<%=basePath %>/etopCompanyTurnover/getEtopCompanyTurnoverList.do?companyId=${companyId}&companyType=${companyType}');
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

        tableTurnover.bootstrapTable('refresh',{url:dataUrl});

    }

    function openTurnoverPage(title,height,width,addUrl,refreshUrl){
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
                    tableTurnover.bootstrapTable('refresh',{url:refreshUrl});
                }
                if (typeof(tree) != "undefined") {
                    tree.refresh();
                }
            }
        });
    }

</script>
