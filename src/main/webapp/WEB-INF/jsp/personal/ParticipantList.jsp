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
<%--             <div class="col-sm-12">
                <div class="row">
                    <div class="col-md-2 m-b">
                        <label>参与人</label>
                            <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入参与人姓名">
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-primary"
                                onclick="getEtopParticipantList()"
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

                    <table id="tableAvg"
                           data-mobile-responsive="true"
                           data-toggle="table"
                           data-url="<%=basePath%>/etopParticipant/getAvg.do?courseId=${id}"
                           data-data-type="json"
                           data-side-pagination="server"
                           data-pagination="true"
                           data-query-params="queryParams"
                           data-page-list="[5, 10, 20, 50, 100, 200]"
                           data-striped="true"
                            >
                        <thead>
                        <tr>
                            <th data-field="avgContent" data-align="center">内容适用</th>
                            <th data-field="avgTeacher" data-align="center">讲师专业</th>
                            <th data-field="avgpPlay" data-align="center">播放流畅</th>
                            <th data-field="avgVideo" data-align="center">视频清晰</th>
                            <th data-field="avgScore" data-align="center">综合评分</th>

                        </tr>
                        </thead>
                    </table>
                </div>
                <!-- End Example Events -->
            </div> --%>
              <div class="hr-line-dashed" style="clear: both;"></div>
            <div class="col-sm-12">
                <!-- Example Events -->
                <div class="example-wrap">


                    <table id="tableEtopParticipant"
                           data-mobile-responsive="true"
                           data-toggle="table"
                           data-url="<%=basePath%>/etopParticipant/getEtopParticipantList.do"
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
                            <th data-field="courseId" data-align="center" data-visible="false">courseId</th>
                            <th data-field="courseName" data-align="center">课程主题</th>
                            <th data-field="courseType" data-align="center" data-formatter="formatterCourseType2">课程类型</th>
                            <th data-field="platform" data-align="center" data-formatter="formatterPlatform2">适用平台</th>
                            <th data-field="answerAt" data-align="center">考试时间</th>
                            <th data-field="score" data-align="center">成绩</th>
                            <th data-field="contentLevel" data-align="center">内容适用</th>
                            <th data-field="teacherLevel" data-align="center">讲师专业</th>
                            <th data-field="playSmooth" data-align="center">播放流畅	</th>
                            <th data-field="videoClarity" data-align="center">视频清晰</th>
                            <th data-align="center" data-formatter='formatterEtopParticipant'>操作</th>
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

    var tableEtopParticipant = $("#tableEtopParticipant");

    /*查询条件*/
    function queryParams(params) {
        params.userName = $("#userName").val();
        return params
    }

    function resetQuestionBank(){
        $("#userName").val("");
    }

    function getEtopParticipantList() {

        tableEtopParticipant.bootstrapTable('refresh', {url: '<%=basePath%>/etopParticipant/getEtopParticipantList.do'});

    }

    tableEtopParticipant.on('click-row.bs.table', function (row, obj, index) {

        $("#courseId").val(obj.id);
    })

    /*增加表格按钮**/
    function formatterEtopParticipant(value, row, index) {

    	 var getOnlineTrainingInfo = "getOnlineTrainingInfo('"+row.courseId+"')";
        var getEtopParticipantInfo = "getEtopParticipantInfo('" + row.id + "')";
//          return '<button class="btn btn-info" onclick="' + getEtopParticipantInfo + '" type="button" >评价</button> '
        if(row.contentLevel == null){
	        return '<button class="btn btn-info" onclick="'+ getOnlineTrainingInfo+'"   type="button" >详情</button> '
	         	+ '&nbsp;&nbsp; '+ 
	         	'<button class="btn btn-danger" onclick="' + getEtopParticipantInfo + '" type="button" >评价</button>';
        }else {
        	 return '<button class="btn btn-info" onclick="'+ getOnlineTrainingInfo+'"   type="button" >详情</button> ';
        }
    }

    function getEtopParticipantInfo(id) {
        $.post(openEtopParticipantPage('编辑信息','70%','60%','<%=basePath %>/etopParticipant/getEtopParticipantInfo.do?id=' + id ,
                '<%=basePath%>/etopParticipant/getEtopParticipantList.do'))
    }

    function getOnlineTrainingInfo(courseId){
        $.post(totabs('<%=basePath %>/etopOnlineTraining/getOnlineTrainingInfo.do?id='+courseId,'编辑课程信息'))
    }
    
    function tableRefresh(dataUrl){

        tableEtopParticipant.bootstrapTable('refresh',{url:dataUrl});

    }

    function openEtopParticipantPage(title,height,width,addUrl,refreshUrl){
//         alert(addUrl);
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
                    tableEtopParticipant.bootstrapTable('refresh',{url:refreshUrl});
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