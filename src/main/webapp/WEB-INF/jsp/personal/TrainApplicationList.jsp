<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title>通知列表-平台管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
<div class="ibox float-e-margins">

    <div class="ibox-content">
        <div class="row row-lg">
<!--             <div class="col-sm-12">
                <div class="row">
                    <div class="col-md-2 m-b">
                        <label>姓名</label>
                            <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-primary"
                                onclick="getTrainApplicationList()"
                                type="button" style="margin-top: 23px;;">搜索
                        </button>
                        <button class="btn btn-primary"
                                onclick="resetQuestionBank()"
                                type="button" style="margin-top: 23px;;">重置
                        </button>
                    </div>
                    <div class="hr-line-dashed" style="clear: both;"></div>
                </div>
            </div> -->
            <div class="hr-line-dashed" style="clear: both;"></div>
            <div class="col-sm-12">
                <!-- Example Events -->
                <div class="example-wrap">


                    <table id="tableTrainApplication"
                           data-mobile-responsive="true"
                           data-toggle="table"
                           data-url="<%=basePath%>/etopTrainApplication/getTrainApplicationList.do"
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
                            <th data-field="offlineType" data-align="center" data-formatter="formatterOfflineType">类型</th>
                            <th data-field="courseType" data-align="center" data-formatter="formatterCourseType">课程类型</th>
                            <th data-field="courseName" data-align="center">主题</th>
                            <th data-field="platform" data-align="center" data-formatter="formatterPlatform">适用平台</th>
                            <th data-field="courseStatus" data-align="center" data-formatter="formatterCouresStatus">课程状态</th>
                            <th data-field="organizationScore" data-align="center">培训组织评分</th>
                            <th data-field="contentScore" data-align="center">内容适用评分</th>
                            <th data-field="professionalScore" data-align="center">讲师专业评分</th>
<!--                             <th data-field="feedback" data-align="center">补充反馈</th> -->
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
</div>
<jsp:include page="../shared/js.jsp"/>
<script type="text/javascript" src="<%=basePath %>/myjs/etopTraining.js"></script>
<script type="text/javascript">

    var tableTrainApplication = $("#tableTrainApplication");

    /*查询条件*/
    function queryParams(params) {
        params.name = $("#name").val();
        params.type = 2;
        return params
    }

    function resetQuestionBank(){
        $("#name").val("");
    }

    function getTrainApplicationList() {
        tableTrainApplication.bootstrapTable('refresh', {url: '<%=basePath%>/etopTrainApplication/getTrainApplicationList.do'});
    }

    /*增加表格按钮**/
    function formatterQuestionBank(value, row, index) {

        var getTrainApplicationInfo = "getTrainApplicationInfo('" + row.id +"')";
        var getOfflineProgramsInfo = "getOfflineProgramsInfo('"+row.trainingId+"')";
        if(row.organizationScore == null){
	        return '<button class="btn btn-info" onclick="'+ getOfflineProgramsInfo+'"   type="button" >详情</button> '
	         	+ '&nbsp;&nbsp; '+ 
	         	'<button class="btn btn-danger" onclick="' + getTrainApplicationInfo + '" type="button" >评价</button>';
        }else {
        	 return '<button class="btn btn-info" onclick="'+ getOfflineProgramsInfo+'"   type="button" >详情</button> ';
        }
    }

    function getTrainApplicationInfo(id) {
        $.post(openTrainApplicationPage('编辑信息','55%','55%','<%=basePath %>/etopTrainApplication/getTrainApplicationInfo2.do?id=' + id ,
                '<%=basePath%>/etopTrainApplication/getTrainApplicationList.do'))
    }


    function tableRefresh(dataUrl){

        tableTrainApplication.bootstrapTable('refresh',{url:dataUrl});

    }

    function getOfflineProgramsInfo(trainingId){
        $.post(totabs('<%=basePath %>/etopOfflinePrograms/getOfflineProgramsInfo.do?id='+trainingId,'编辑课程信息'))
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
</body>
</html>