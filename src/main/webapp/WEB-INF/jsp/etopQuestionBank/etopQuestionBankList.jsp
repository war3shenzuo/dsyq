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
                    <div class="col-md-2 m-b">
                        <label>题目</label>
                            <input type="text" class="form-control" id="title" name="title" placeholder="请输入题目">
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-primary"
                                onclick="getEtopQuestionBankList()"
                                type="button" style="margin-top: 23px;;">搜索
                        </button>
                        <button class="btn btn-primary"
                                onclick="resetQuestionBank()"
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
                           onclick="openQuestionBankPage('新建题目','65%','65%','<%=basePath %>/etopQuestionBank/addQuestionBankPage.do?titleId=${id}','<%=basePath%>/etopQuestionBank/getEtopQuestionBankList.do?titleId=${id}')">
                            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                            <span>新建</span>
                        </a>
                        <button type="button" class="btn btn-outline btn-default" onclick="deleteQuestionBank()">
                            <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <span>删除</span>
                        </button>
                    </div>

                    <table id="tableQuestionBank"
                           data-mobile-responsive="true"
                           data-toggle="table"
                           data-url="<%=basePath%>/etopQuestionBank/getEtopQuestionBankList.do?titleId=${id}"
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
                            <th data-field="titleId" data-align="center" data-visible="false">题目编号</th>
                            <th data-field="id" data-align="center" data-visible="false">id</th>
                            <th data-field="title" data-align="center">题目</th>
                            <th data-field="platform" data-align="center" data-formatter="formatterPlatform">适用平台</th>
                            <th data-field="correctAnswer" data-align="center">正确答案</th>
                            <th data-field="createdAt" data-align="center">创建时间</th>
                            <th data-align="center" data-formatter='formatterQuestionBank'>操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <!-- End Example Events -->
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=basePath %>/myjs/etopTraining.js"></script>
<script type="text/javascript">

    var tableQuestionBank = $("#tableQuestionBank");

    /*查询条件*/
    function queryParams(params) {
        params.title = $("#title").val();
        return params
    }

    function resetQuestionBank(){
        $("#title").val("");
    }

    function getEtopQuestionBankList() {

        tableQuestionBank.bootstrapTable('refresh', {url: '<%=basePath%>/etopQuestionBank/getEtopQuestionBankList.do?titleId=${id}'});

    }

    tableQuestionBank.on('click-row.bs.table', function (row, obj, index) {

        $("#titleId").val(obj.id);
    })

    /*增加表格按钮**/
    function formatterQuestionBank(value, row, index) {

        var getQuestionBankInfo = "getQuestionBankInfo('" + row.titleId +"' , '" + row.id+"')";
        return '<button class="btn btn-info" onclick="' +
                getQuestionBankInfo + '" type="button" >详情</button> '
    }

    function getQuestionBankInfo(titleId,id) {
        $.post(openQuestionBankPage('编辑题目信息','65%','65%','<%=basePath %>/etopQuestionBank/getEtopQuestionBankInfo.do?titleId=' + titleId + "&id=" + id + "&readonly=" + ${readonly},
                '<%=basePath%>/etopQuestionBank/getEtopQuestionBankList.do?titleId=${id}'))
    }

    function deleteQuestionBank() {
        var selectionsTurnover = tableQuestionBank.bootstrapTable('getSelections');
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
                        title: "确定删除这"+selectionsTurnover.length+"条题目数据?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是",
                        cancelButtonText: "否",
                        closeOnConfirm: false
                    }, function () {
                        $.get("<%=basePath %>/etopQuestionBank/deleteEtopQuestionBankInfo.do", {
                            "ids": ids
                        }, function (data) {
                            if (data.status == 10001) {
                                swal({
                                    title: "成功删除" + data.data + "条拜访记录！",
                                    type: "success",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('<%=basePath %>/etopQuestionBank/getEtopQuestionBankList.do?titleId=${id}');
                                });
                            } else {
                                swal({
                                    title: data.msg,
                                    type: "error",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('<%=basePath %>/etopQuestionBank/getEtopQuestionBankList.do?titleId=${id}');
                                });
                            }

                        })
                    }
            );
        }else{
            swal({
                title: "请先选择题目!",
                timer: 1000,
                showConfirmButton: false
            });
        }

    }

    function tableRefresh(dataUrl){

        tableQuestionBank.bootstrapTable('refresh',{url:dataUrl});

    }

    function openQuestionBankPage(title,height,width,addUrl,refreshUrl){
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
                    tableQuestionBank.bootstrapTable('refresh',{url:refreshUrl});
                }
                if (typeof(tree) != "undefined") {
                    tree.refresh();
                }
            }
        });
    }

</script>
