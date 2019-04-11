<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
    <title>学员信息-线下课程</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <jsp:include page="../shared/css.jsp"/>
</head>
<input id="feedId" value='${info.feedId}' type="hidden">
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">

                <div class="ibox-content">

                    <form method="get" class="form-horizontal" id="checkForm">

                        <div class="form-group">

                            <div class="form-group">

                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">课程</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" placeholder="" id="courseName" name="courseName" value="${info.courseName}" readonly="readonly">
                                </div>

                                <label class="col-sm-1 control-label">课程类型</label>

                                <div class="col-sm-2">
                                	<c:if test="${info.courseType == 1}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="高管研修" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 2}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="创业辅助" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 3}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="淘系美工" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 4}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="淘系运营" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 5}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="淘系客服" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 6}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="淘系推广" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 7}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="淘系无线" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 8}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="微商" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 9}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="京东系列" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 10}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="跨境系列" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 11}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="其他平台" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 12}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="其他类型" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 13}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="沙龙" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 14}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="论坛" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 15}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="会议" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 16}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="文娱" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.courseType == 17}">
                                    <input type="text" class="form-control" id="courseType" name="courseType" value="体育" readonly="readonly">
                                    </c:if>
                                </div>

                                <label class="col-sm-1 control-label">适用平台</label>

                                <div class="col-sm-2">
                                	<c:if test="${info.platform == 1}">	
                                    <input type="text" class="form-control" id="platform" name="platform" value="淘宝" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.platform == 2}">	
                                    <input type="text" class="form-control" id="platform" name="platform" value="微信" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.platform == 3}">	
                                    <input type="text" class="form-control" id="platform" name="platform" value="京东" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.platform == 4}">	
                                    <input type="text" class="form-control" id="platform" name="platform" value="苏宁" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.platform == 5}">	
                                    <input type="text" class="form-control" id="platform" name="platform" value="跨境平台" readonly="readonly">
                                    </c:if>
                                	<c:if test="${info.platform == 6}">	
                                    <input type="text" class="form-control" id="platform" name="platform" value="其他平台" readonly="readonly">
                                    </c:if>
                                </div>

                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">

                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">考试时间</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" placeholder="" id="answerAt" name="answerAt" 
                                    value='<fmt:formatDate value="${info.answerAt}" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly">
                                </div>

                                <label class="col-sm-1 control-label">成绩</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="score" name="score" value="${info.score}" readonly="readonly">
                                </div>

                                 <label class="col-sm-1 control-label">内容适用<font color="red">*</font></label>

                                <div class="col-sm-2" style="position: relative">
                                    <input type="number" class="form-control" id="contentLevel" name="contentLevel" aria-required="true" value="${info.contentLevel}" min="1" max="5">
                                    <div style="position: absolute; top: 10px; right: 0px;">分</div>
                                </div>

                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">

                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">讲师专业<font color="red">*</font></label>

                                <div class="col-sm-2" style="position: relative">
                                    <input type="number" class="form-control" id="teacherLevel" name="teacherLevel" aria-required="true" value="${info.teacherLevel}" min="1" max="5">
                                    <div style="position: absolute; top: 10px; right: 0px;">分</div>
                                </div>

                                <label class="col-sm-1 control-label">播放流畅<font color="red">*</font></label>

                                <div class="col-sm-2" style="position: relative">
                                    <input type="number" class="form-control" id="playSmooth" name="playSmooth" aria-required="true" value="${info.playSmooth}" min="1" max="5">
                                    <div style="position: absolute; top: 10px; right: 0px;">分</div>
                                </div>

                                <label class="col-sm-1 control-label">视频清晰<font color="red">*</font></label>

                                <div class="col-sm-2" style="position: relative">
                                    <input type="number" class="form-control" id="videoClarity" name="videoClarity" aria-required="true" value="${info.videoClarity}" min="1" max="5"> 
                                    <div style="position: absolute; top: 10px; right: 0px;">分</div>
                                </div>
                            </div>
                            
                             <span class="col-sm-2"></span><span style="color:red">*</span><e>(评分请在1-5之间选择)</e>
                            
                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">补充反馈</label>

                                <div class="col-sm-6">
                                    <textarea rows="5" cols="" class="form-control" placeholder="" name="remark" id="remark">${info.remark}</textarea>
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-5">
                                    <%--<a class="btn btn-primary" onclick="submit()">确认并保存</a>--%>
                                    <input class="btn btn-primary" type="submit" value="确认并保存">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../shared/js.jsp"/>
<script type="text/javascript">

    $('.datepicker').datepicker({
        todayBtn: "linked",
        keyboardNavigation: !1,
        forceParse: !1,
        autoclose: !0
    });

    var standard = "<i class='fa fa-times-circle'></i> ";

    $("#checkForm").validate({
        rules: {//这里加校验规则
        	contentLevel: "required",
        	teacherLevel: "required",
        	playSmooth: "required",
        	videoClarity: "required",
        },
        messages: {//这里给对应的提示 	
        	contentLevel: standard + "请选择内容适用评分 !",
        	teacherLevel: standard + "请选择讲师专业评分 !",
        	playSmooth: standard + "请选择讲师专业评分 !",
        	videoClarity: standard + "请选择视频清晰评分 !",
        },
        submitHandler: function(form){
            submit();  //去提交
        }
    })

    //保存
    function submit() {

        var params = {
            "feedId":  $("#feedId").val(),
            "contentLevel": $("#contentLevel").val(), // 
            "teacherLevel": $("#teacherLevel").val(), // 
            "playSmooth": $("#playSmooth").val(), // 内容适用评分
            "videoClarity": $("#videoClarity").val(), // 讲师专业评分
            "remark": $("#remark").val() // 补充反馈
        }

        $.post("updateFeedback.do", params, function (data) {
            if (data.status == 10001) {
                swal({
                    title : data.msg,
                    type : "success",
                    confirmButtonText : "确定",
                    closeOnConfirm : false
                }, function() {
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index);
                });

            } else {
                swal({
                    title : data.msg,
                    type : "error",
                    confirmButtonText : "确定",
                    closeOnConfirm : true
                }, function() {

                });
            }

        });
    }

</script>
</body>
</html>