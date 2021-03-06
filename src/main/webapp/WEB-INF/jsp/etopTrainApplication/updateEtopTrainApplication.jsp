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
                                <label class="col-sm-1 control-label">姓名<font color="red">*</font></label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" placeholder="" id="name" name="name" value="${info.name}">
                                </div>

                                <label class="col-sm-1 control-label">所属单位</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="workUnits" name="workUnits" value="${info.workUnits}">
                                </div>

                                <label class="col-sm-1 control-label">岗位</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="post" name="post" value="${info.post}">
                                </div>


                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-1"></div>

                                <label class="col-sm-1 control-label">联系方式<font color="red">*</font></label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="contactInformation" name="contactInformation" value="${info.contactInformation}">
                                </div>

                                <label class="col-sm-1 control-label">职位</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="position" name="position" value="${info.position}">
                                </div>

                                <label class="col-sm-1 control-label">邮箱</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="email" name="email" value="${info.email}">
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">从事岗位年限</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="postYear" name="postYear" value="${info.postYear}">
                                </div>

                                <label class="col-sm-1 control-label">参加培训日期</label>

                                <div class="col-sm-2">
                                    <div class="input-group date">
                                        <span class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </span>
                                        <input name="joinTime" type="text" class="datepicker form-control" id="joinTime"
                                               value='<fmt:formatDate value="${info.joinTime}" pattern="yyyy-MM-dd"/>'>
                                    </div>
                                </div>

                                <label class="col-sm-1 control-label">是否参加过类似培训</label>

                                <div class="col-sm-2">
                                    <select class="form-control m-b" id="train" name="train">
                                        <option value="0"
                                                <c:if test="${info.train eq 0}">selected</c:if> >未参加
                                        </option>
                                        <option value="1"
                                                <c:if test="${info.train eq 1}">selected</c:if> >已参加
                                        </option>
                                    </select>
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">

                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">培训组织评分</label>

                                <div class="col-sm-1" style="position: relative">
                                    <input type="number" class="form-control" id="organizationScore" name="organizationScore" aria-required="true" value="${info.organizationScore}">
                                    <div style="position: absolute; top: 10px; right: 0px;">分</div>
                                </div>

                                <label class="col-sm-2 control-label">内容适用评分</label>

                                <div class="col-sm-1" style="position: relative">
                                    <input type="number" class="form-control" id="contentScore" name="contentScore" aria-required="true" value="${info.contentScore}">
                                    <div style="position: absolute; top: 10px; right: 0px;">分</div>
                                </div>

                                <label class="col-sm-2 control-label">讲师专业评分</label>

                                <div class="col-sm-1" style="position: relative">
                                    <input type="number" class="form-control" id="professionalScore" name="professionalScore" aria-required="true" value="${info.professionalScore}">
                                    <div style="position: absolute; top: 10px; right: 0px;">分</div>
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">补充反馈</label>

                                <div class="col-sm-6">
                                    <textarea rows="" cols="" class="form-control" placeholder="" name="feedback" id="feedback">${info.feedback}</textarea>
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group" style="display: none">
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
            name: "required",
            contactInformation: "required"
        },
        messages: {//这里给对应的提示
            name: standard + "请输入姓名 !",
            contactInformation: standard + "请输入联系方式 !"
        },
        submitHandler: function(form){
            submit();  //去提交
        }
    })

    //保存
    function submit() {

        var params = {
            "id": '${id}',
            "name": $("#name").val(), // 姓名
            "workUnits": $("#workUnits").val(), // 所属单位
            "post": $("#post").val(), // 岗位
            "position": $("#position").val(), // 职位
            "postYear": $("#postYear").val(), // 从事岗位年限
            "train": $("#train").val(), // 是否参加过类似培训(0未参加、1已参加)
            "joinTime": $("#joinTime").val(), //可参加培训日期
            "contactInformation": $("#contactInformation").val(), // 联系方式
            "email": $("#email").val(), // 邮箱
            "organizationScore": $("#organizationScore").val(), // 培训组织评分
            "contentScore": $("#contentScore").val(), // 内容适用评分
            "professionalScore": $("#professionalScore").val(), // 讲师专业评分
            "feedback": $("#feedback").val() // 补充反馈
        }

        $.post("updateTrainApplication.do", params, function (data) {
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