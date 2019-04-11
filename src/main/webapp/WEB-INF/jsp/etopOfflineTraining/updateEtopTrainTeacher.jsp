<%@ page import="com.etop.management.properties.ImgProperties" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
    <title>嘉宾信息-培训管理</title>
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

                                <input type="text" class="form-control" placeholder="" id="id" name="id" value="${info.id}" style="visibility: hidden">
                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">姓名<font color="red">*</font></label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" placeholder="" id="name" name="name" value="${info.name}">
                                </div>

                                <label class="col-sm-1 control-label">上传图片</label>
                                <div class="col-sm-3">
                                    <input type="hidden" class=" form-control" placeholder="" id="photo" value="${info.photo}">
                                    <input type="file" id="file" name="file" class="form-control">
                                </div>
                                <div class="col-sm-1">
                                    <button class="btn btn-group" type="button" onclick="ajaxFileUpload()">上传</button>
                                </div>
                                <div class="col-sm-2">
                                    <img id="buildImg" alt="" src="<%=ImgProperties.LOAD_PATH%>${info.photo}" height="100px" width="100px">
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">

                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">简介<font color="red">*</font></label>

                                <div class="col-sm-6">
                                    <textarea rows="" cols="" class="form-control" placeholder="" name="profile" id="profile">${info.profile}</textarea>
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
<script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
<script type="text/javascript">

    function ajaxFileUpload() {

        upload('<%=basePath%>/etopTrainTeacher/uploadImg.do?courseId=${courseId}',"file",function(data){
            $("#photo").val(data.data.path);
            $("#buildImg").attr("src",'<%=ImgProperties.LOAD_PATH%>'+data.data.path)
            $("#buildImg").show();
        });

    }

    var standard = "<i class='fa fa-times-circle'></i> ";

    $("#checkForm").validate({
        rules: {//这里加校验规则
            name: "required",
            profile: "required"
        },
        messages: {//这里给对应的提示
            name: standard + "请输入姓名 !",
            profile: standard + "请输入简介 !"
        },
        submitHandler: function(form){
            submit();  //去提交
        }
    })

    //保存
    function submit() {

        var params = {
            "id": $("#id").val(),
            "name": $("#name").val(), // 姓名
            "photo": $("#photo").val(), // 照片
            "profile": $("#profile").val() // 简介
        }

        $.post("updateEtopTrainTeacher.do", params, function (data) {
            if (data.status == 10001) {
                swal({
                    title : "修改嘉宾信息成功 !",
                    type : "success",
                    confirmButtonText : "确定",
                    closeOnConfirm : false
                }, function() {
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index);
                });

            } else {
                swal({
                    title : "修改嘉宾信息失败 !",
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