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
    <title>公司拜访记录-客户管理</title>
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

                    <form method="get" class="form-horizontal" id="signupForm">

                        <div class="form-group">

                            <div class="form-group">

                                <input type="text" class="form-control" placeholder="" id="id" name="id" value="${maintainInfo.id}"
                                       style="visibility: hidden">
                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">公司名称<font color="red">*</font></label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" placeholder="" id="companyName"
                                           name="companyName" value="${maintainInfo.companyName}" readonly="readonly">
                                </div>

                                <label class="col-sm-1 control-label">园区名称</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" placeholder="" readonly="readonly"
                                           id="parkName" name="parkName" value="${maintainInfo.parkName}">
                                </div>

                                <label class="col-sm-1 control-label">拜访者</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="visitors" name="visitors"
                                           value="${maintainInfo.visitors}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">拜访时间</label>

                                <div class="col-sm-2">
                                    <div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input name="createdAt" type="text" class="datepicker form-control"
                                               id="createdAt"
                                               value='<fmt:formatDate value="${maintainInfo.createdAt}" pattern="yyyy-MM-dd"/>' <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                    </div>
                                </div>

                                <label class="col-sm-1 control-label">提醒时间</label>

                                <div class="col-sm-2">
                                    <div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input name="createdAt" type="text" class="datepicker form-control"
                                               id="remindAt"
                                               value='<fmt:formatDate value="${maintainInfo.remindAt}" pattern="yyyy-MM-dd"/>' <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                    </div>
                                </div>

                                <label class="col-sm-1 control-label">接待人</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="receiver" name="receiver" value="${maintainInfo.receiver}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">职位</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="position" name="position" value="${maintainInfo.position}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                </div>

                                <label class="col-sm-1 control-label">联系方式</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="mobile" name="mobile" value="${maintainInfo.mobile}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                </div>

                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">

                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">拜访原因<font color="red">*</font></label>

                                <div class="col-sm-6">
                                    <textarea rows="" cols="" class="form-control" placeholder="" name="cause" id="cause" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>${maintainInfo.cause}</textarea>
                                </div>

                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">拜访内容<font color="red">*</font></label>

                                <div class="col-sm-6">
                                    <textarea rows="" cols="" class="form-control" placeholder="" name="content" id="content" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>${maintainInfo.content}</textarea>
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group" <c:if test="${readonly eq true}">style="display: none;"</c:if> >
                                <div class="col-sm-4 col-sm-offset-5">
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

    $("#signupForm").validate({
        rules: {//这里加校验规则
            cause: "required",
            content: "required"
        },
        messages: {//这里给对应的提示
            cause: standard + "请输入拜访原因 !",
            content: standard + "请输入拜访内容 !"
        },
        submitHandler: function(form){
            submit();  //去提交
        }
    })

    //保存
    function submit() {

        var params = {
            "id": $("#id").val(),
            "companyName": $("#companyName").val(), // 公司名称
            "parkName": $("#parkName").val(), // 园区名称
            "contact": $("#contact").val(), // 拜访者
            "receiver": $("#receiver").val(), //接待人
            "position": $("#position").val(), // 职位
            "mobile": $("#mobile").val(), // 电话
            "createdAt": $("#createdAt").val(), // 拜访时间
            "remindAt": $("#remindAt").val(), //提醒时间
            "cause": $("#cause").val(), // 拜访原因
            "content": $("#content").val() // 拜访内容
        }

        $.post("updateEtopCompanyMaintainInfo.do", params, function (data) {
            if (data.status == 10001) {
                swal({
                    title : "修改公司拜访记录成功! ",
                    type : "success",
                    confirmButtonText : "确定",
                    closeOnConfirm : false
                }, function() {
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index);
                });

            } else {
                swal({
                    title : "修改公司拜访记录失败! ",
                    text : data.msg,
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