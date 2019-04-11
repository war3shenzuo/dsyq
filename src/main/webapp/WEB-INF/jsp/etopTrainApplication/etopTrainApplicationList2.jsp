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
                        <label>姓名</label>
                            <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
                    </div>
                    <div class="col-md-2 m-b">
                        <label>园区</label>
                            <input type="text" class="form-control" id="parkTitle" name="parkTitle" placeholder="请输入园区">
                    </div>
                    <div class="col-md-2 m-b">
                        <label>园区组</label>
                            <input type="text" class="form-control" id="parkGroupCode" name="parkGroupCode" placeholder="请输入园区组">
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-primary"
                                onclick="getEtopTrainApplicationList()"
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
                        style="display: none;" >
                        <a class="btn btn-outline btn-default"
                           onclick="openTrainApplicationPage('新建学员信息','80%','80%','<%=basePath%>/etopTrainApplication/addTrainApplicationPage.do?trainingId=${courseId}',
                                   '<%=basePath%>/etopTrainApplication/getEtopTrainApplicationList.do?trainingId=${courseId}')">
                            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                            <span>新建</span>
                        </a>
                        <button type="button" class="btn btn-outline btn-default" onclick="deleteTrainApplication()">
                            <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <span>删除</span>
                        </button>
                    </div>

                    <table id="tableTrainApplication"
                           data-mobile-responsive="true"
                           data-toggle="table"
                           data-url="<%=basePath%>/etopTrainApplication/getEtopTrainApplicationList.do?trainingId=${courseId}"
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
                            <th data-field="trainingId" data-align="center" data-visible="false">课程id</th>
                            <th data-field="id" data-align="center" data-visible="false">id</th>
                            <th data-field="name" data-align="center">姓名</th>
                            <th data-field="parkTitle" data-align="center">园区</th>
                            <th data-field="parkGroupCode" data-align="center">园区组</th>
                            <th data-field="workUnits" data-align="center">所属单位</th>
                            <th data-field="post" data-align="center">岗位</th>
                            <th data-field="position" data-align="center">职位</th>
                            <th data-field="postYear" data-align="center">从事岗位年限</th>
                            <th data-field="train" data-align="center" data-formatter="formatterTrain">是否参加过类似培训</th>
                            <th data-field="joinTime" data-align="center">可参加培训日期</th>
                            <th data-field="contactInformation" data-align="center">联系方式</th>
                            <th data-field="email" data-align="center">邮箱</th>
                            <th data-field="organizationScore" data-align="center">培训组织评分</th>
                            <th data-field="contentScore" data-align="center">内容适用评分</th>
<!--                             <th data-field="professionalScore" data-align="center">讲师专业评分</th> -->
                            <th data-field="feedback" data-align="center">补充反馈</th>
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
<script type="text/javascript">

    var tableTrainApplication = $("#tableTrainApplication");

    /*查询条件*/
    function queryParams(params) {
        params.name = $("#name").val();
        params.parkTitle = $("#parkTitle").val();
        params.parkGroupCode = $("#parkGroupCode").val();
        return params
    }

    function resetQuestionBank(){
        $("#name").val("");
        $("#parkTitle").val("");
        $("#parkGroupCode").val("");
    }

    function getEtopTrainApplicationList() {
        tableTrainApplication.bootstrapTable('refresh', {url: '<%=basePath%>/etopTrainApplication/getEtopTrainApplicationList.do?trainingId=${courseId}'});
    }

    /*增加表格按钮**/
    function formatterQuestionBank(value, row, index) {

        var getTrainApplicationInfo = "getTrainApplicationInfo('" + row.id +"')";
        return '<button class="btn btn-info" onclick="' +
                getTrainApplicationInfo + '" type="button" >详情</button> '
    }

    function getTrainApplicationInfo(id) {
        $.post(openTrainApplicationPage('编辑题目信息','80%','80%','<%=basePath %>/etopTrainApplication/getTrainApplicationInfo.do?id=' + id ,
                '<%=basePath%>/etopTrainApplication/getEtopTrainApplicationList.do?trainingId=${courseId}'))
    }

    function deleteTrainApplication() {
        var selectionsApplication = tableTrainApplication.bootstrapTable('getSelections');
        if(selectionsApplication.length){
            var ids = [];
            if (selectionsApplication.length == 0)
                return;
            for (var i = 0; i < selectionsApplication.length; i++) {
                ids = ids + selectionsApplication[i].id + ",";
            }
            var reg=/,$/gi;
            ids = ids.replace(reg,"");
            swal({
                        title: "确定删除这"+selectionsApplication.length+"条学员信息?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是",
                        cancelButtonText: "否",
                        closeOnConfirm: false
                    }, function () {
                        $.get("<%=basePath%>/etopTrainApplication/deleteTrainApplicationInfo.do", {
                            "ids": ids
                        }, function (data) {
                            if (data.status == 10001) {
                                swal({
                                    title: "成功删除" + data.data + "条拜访记录！",
                                    type: "success",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('<%=basePath %>/etopTrainApplication/getEtopTrainApplicationList.do?trainingId=${courseId}');
                                });
                            } else {
                                swal({
                                    title: data.msg,
                                    type: "error",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('<%=basePath %>/etopTrainApplication/getEtopTrainApplicationList.do?trainingId=${courseId}');
                                });
                            }

                        })
                    }
            );
        }else{
            swal({
                title: "请先选择学员!",
                timer: 1000,
                showConfirmButton: false
            });
        }

    }

    function tableRefresh(dataUrl){

        tableTrainApplication.bootstrapTable('refresh',{url:dataUrl});

    }

    function openTrainApplicationPage(title,height,width,addUrl,refreshUrl){
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
                    tableTrainApplication.bootstrapTable('refresh',{url:refreshUrl});
                }
                if (typeof(tree) != "undefined") {
                    tree.refresh();
                }
            }
        });
    }

</script>
