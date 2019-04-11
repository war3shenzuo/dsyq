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
    <title>公司营业额记录-客户管理</title>
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

                                <input type="text" class="form-control" placeholder="" id="id" name="id" value="${turnoverInfo.id}"
                                       style="visibility: hidden">
                                <div class="col-sm-2"></div>
                                <label class="col-sm-2 control-label">公司名称</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" placeholder="" id="companyName"
                                           name="companyName" value="${turnoverInfo.companyName}" readonly="readonly">
                                </div>

                                <label class="col-sm-1 control-label">平台<font color="red">*</font></label>

                                <div class="col-sm-2">
                                    <select class="form-control m-b" id="platform" name="platform">
                                        <option value="淘宝" <c:if test="${turnoverInfo.platform == '淘宝'}">selected</c:if> >淘宝</option>
                                        <option value="天猫" <c:if test="${turnoverInfo.platform == '天猫'}">selected</c:if> >天猫</option>
                                        <option value="京东" <c:if test="${turnoverInfo.platform == '京东'}">selected</c:if> >京东</option>
                                        <option value="亚马逊" <c:if test="${turnoverInfo.platform == '亚马逊'}">selected</c:if> >亚马逊</option>
                                        <option value="实体店" <c:if test="${turnoverInfo.platform == '实体店'}">selected</c:if> >实体店</option>
                                        <option value="加盟商" <c:if test="${turnoverInfo.platform == '加盟商'}">selected</c:if> >加盟商</option>
                                        <option value="代理" <c:if test="${turnoverInfo.platform == '代理'}">selected</c:if> >代理</option>
                                        <option value="其他" <c:if test="${turnoverInfo.platform == '其他'}">selected</c:if> >其他</option>
                                    </select>
                                </div>

                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-2"></div>
                                <label class="col-sm-2 control-label">日期<font color="red">*</font></label>

                                <div class="col-sm-2">
                                    <div class="input-group date">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        <input name="yearsDate" type="text" class="form_datetime form-control"
                                               id="yearsDate"
                                               value='<fmt:formatDate value="${turnoverInfo.yearsDate}" pattern="yyyy-MM"/>' <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                    </div>
                                </div>

                                <label class="col-sm-1 control-label">营业额<font color="red">*</font></label>
                                <div class="col-sm-2">
                                    <input type="number" class="form-control" id="turnover" name="turnover" value="${turnoverInfo.turnover}" aria-required="true">
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

    var standard = "<i class='fa fa-times-circle'></i> ";

    $("#signupForm").validate({
        rules: {//这里加校验规则
            platform: "required",
            yearsDate: "required",
            turnover: "required"
        },
        messages: {//这里给对应的提示
            platform: standard + "请选择平台 !",
            yearsDate: standard + "请选择日期 !",
            turnover: standard + "请输入营业额 !"
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
            "platform": $("#platform").val(), // 平台
            "yearsDate": $("#yearsDate").val(), // 拜访时间
            "turnover": $("#turnover").val() // 营业额
        }

        $.post("updateEtopCompanyTurnoverInfo.do", params, function (data) {
            if (data.status == 10001) {
                swal({
                    title : "修改营业额记录成功! ",
                    type : "success",
                    confirmButtonText : "确定",
                    closeOnConfirm : false
                }, function() {
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index);
                });

            } else if(data.status == 8888){
                swal({
                    title : "该平台在此月已有营业额记录! 请重新选择平台或者日期 ",
                    type : "error",
                    confirmButtonText : "确定",
                    closeOnConfirm : true
                }, function() {

                });
            }else{
                swal({
                    title : "修改营业额记录失败! ",
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