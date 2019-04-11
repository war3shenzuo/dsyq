<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>基本信息-培训管理</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <jsp:include page="../shared/css.jsp"/>
        <link rel="stylesheet" href="<%=basePath%>/css/select/select2.min.css">
    </head>

    <body class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">

                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">基本信息</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">

                                    <div class="ibox float-e-margins">
                                        <div class="ibox-content">

                                            <form method="get" class="form-horizontal" id="signupForm">

                                                <div class="form-group">

                                                    <input id="courseId" name="courseId" value="${info.courseId}" hidden>

                                                    <label class="col-sm-1 control-label">培训日期<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <div class="input-group date">
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </span>
                                                            <input name="trainDate" type="text" class="form_datetime form-control" id="trainDate"
                                                                   value='<fmt:formatDate value="${info.trainDate}" pattern="yyyy-MM-dd"/>'>
                                                        </div>
                                                    </div>

                                                    <label class="col-sm-1 control-label">培训类型<font color="red">*</font></label>

                                                    <div class="col-sm-1">
                                                        <select class="form-control m-b" id="trainType" name="trainType">
                                                            <option value="0"
                                                                    <c:if test="${info.trainType eq 0}">selected</c:if> >全部</option>
                                                            <option value="7"
                                                                <c:if test="${info.trainType eq 13}">selected</c:if> >沙龙</option>
                                                        <option value="8"
                                                                <c:if test="${info.trainType eq 14}">selected</c:if> >论坛</option>
                                                        <option value="9"
                                                                <c:if test="${info.trainType eq 15}">selected</c:if> >会议</option>
                                                        <option value="10"
                                                                <c:if test="${info.trainType eq 16}">selected</c:if> >文娱</option>
                                                        <option value="11"
                                                                <c:if test="${info.trainType eq 17}">selected</c:if> >体育</option>
                                                        <option value="12"
                                                                <c:if test="${info.trainType eq 12}">selected</c:if> >其他类型</option>
                                                        </select>
                                                    </div>

                                                    <label class="col-sm-1 control-label">发布对象<font color="red">*</font></label>

                                                    <input id="userType" class="hidden" value="${userType}">

                                                    <div>
                                                        <c:choose>
                                                            <c:when test="${userType eq 4}">
                                                                <div class="col-sm-2" style=" padding-top: 18px;">
                                                                    <select name="target" class="js-example-tags form-control" multiple="multiple" id="target">
                                                                        <option value="0">全部</option>
                                                                        <c:forEach items="${parks}" var="park">
                                                                            <option value="${park.parkName}">${park.parkName}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="col-sm-2">
                                                                    <input type="text" class="form-control" id="target1" name="target1" value="${info.target}" readonly>
                                                                    <input type="text" class="form-control hidden" id="parkId" name="parkId" value="${info.parkId}">
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>

                                                    <label class="col-sm-1 control-label">负责人</label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="charge" name="charge" value="${info.charge}">
                                                    </div>

                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">参与部门</label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="department" name="department" value="${info.department}">
                                                    </div>

                                                    <label class="col-sm-1 control-label">预计人数</label>

                                                    <div class="col-sm-1" style="position: relative">
                                                        <input type="number" class="form-control" id="expectPeople" name="expectPeople" aria-required="true" value="${info.expectPeople}">
                                                        <div style="position: absolute; top: 10px; right: -5px;">人</div>
                                                    </div>

                                                    <label class="col-sm-1 control-label">预计时长</label>

                                                    <div class="col-sm-1" style="position: relative">
                                                        <input type="number" class="form-control" id="expectDate" name="expectDate" aria-required="true" value="${info.expectDate}">
                                                        <div style="position: absolute; top: 10px; right: -15px;">小时</div>
                                                    </div>

                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">准备周期<font color="red">*</font></label>

                                                    <div class="col-sm-2" style="position: relative">
                                                        <input type="number" class="form-control" id="cycle" name="cycle" aria-required="true" value="${info.cycle}">
                                                        <div style="position: absolute; top: 10px; right: 0px;">天</div>
                                                    </div>


                                                    <label class="col-sm-1 control-label">提醒日期<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <div class="input-group date">
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </span>
                                                            <input name="remindDate" type="text" class="form_datetime form-control" id="remindDate"
                                                                   value='<fmt:formatDate value="${info.remindDate}" pattern="yyyy-MM-dd"/>'>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">举办地点</label>

                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" id="expectPlace" name="expectPlace" value="${info.expectPlace}">
                                                    </div>
                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">培训主题<font color="red">*</font></label>

                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" id="title" name="title" value="${info.title}">
                                                    </div>
                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <div class="col-sm-4 col-sm-offset-5">
                                                        <%--<a class="btn btn-primary"  onclick="submit()">确认并保存</a>--%>
                                                        <input class="btn btn-primary" type="submit" value="确认并保存">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../shared/js.jsp"/>
        <script type="text/javascript">
            var basePath = "<%=basePath%>";
        </script>
        <script src="<%=basePath%>/myjs/etopTraining.js"></script>
        <script src="<%=basePath%>/js/select/select2.full.min.js"></script>
        <script type="text/javascript">

            $('.form_datetime').datepicker({
                todayBtn: "linked",
                keyboardNavigation: !1,
                forceParse: !1,
                autoclose: !0
            });

            $("#target").select2({
                tags: true,
                separator: ",",
            });

            var target = '${info.target}'.split(",");
            $("#target").select2().val(target).trigger("change");

            var standard = "<i class='fa fa-times-circle'></i> ";

            $("#signupForm").validate({
                rules: {//这里加校验规则
                    trainDate: "required",
                    trainType: "required",
                    target: "required",
                    cycle: "required",
                    remindDate: "required",
                    title: "required"
                },
                messages: {//这里给对应的提示
                    trainDate: standard + "请选择培训日期 !",
                    trainType: standard + "请选择培训类型 !",
                    cycle: standard + "请输入准备周期 !",
                    remindDate: standard + "请选择提醒日期 !",
                    title: standard + "请输入培训主题 !"
                },
                submitHandler: function(form){
                    submit();
                }
            })

            //保存
            function submit(){

                var params = {
                    "courseId" : $("#courseId").val(),
                    "trainDate" : $("#trainDate").val(), // 培训日期
                    "trainType" : $("#trainType").val(), // 培训类型
                    "title" : $("#title").val(), // 主题
                    "charge" : $("#charge").val(), // 负责人
                    "target" : $("#target").val(), // 发布对象
                    "parkId" : $("#parkId").val(), // 园区id
                    "department" : $("#department").val(), // 部门
                    "expectPeople" : $("#expectPeople").val(), // 预计人数
                    "expectDate" : $("#expectDate").val(), // 预计时长
                    "expectPlace" : $("#expectPlace").val(), // 举办地点
                    "cycle" : $("#cycle").val(), // 周长
                    "remindDate" : $("#remindDate").val() // 提醒时间
                }

                if($("#userType").val() == 4){
                    if( $("#target").val()!=null && $("#target").val().length>0){
                        params.target= $("#target").val().toString() ;
                    }else{
                        params.target= "";
                    }
                }else{
                    params.target = $("#target1").val();
                }

                $.post("updateEtopTrainPlan.do",params,function(data){
                    if(data.status==10001){
                        refreshEtopTrainPlanList();
                        swal({
                            title : data.msg,
                            type : "success",
                            confirmButtonText : "确定",
                            closeOnConfirm : true
                        }, function() {
                        });
                    }else{
                        swal( "保存失败！", "","error");
                    }
                });
            }
        </script>
    </body>
</html>