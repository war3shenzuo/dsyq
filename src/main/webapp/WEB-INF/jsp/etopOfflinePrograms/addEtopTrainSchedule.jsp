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
    <title>开课信息-培训管理</title>
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

                                <input type="text" class="form-control" placeholder="" id="courseId" name="courseId" value="${courseId}" style="visibility: hidden">
                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">主题<font color="red">*</font></label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" placeholder="" id="title" name="title" >
                                </div>

                                <label class="col-sm-1 control-label">主讲人<font color="red">*</font></label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" placeholder="" id="teacher" name="teacher">
                                </div>

                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-1"></div>

                                <label class="col-sm-1 control-label">开始时间<font color="red">*</font></label>

                                <div class="col-sm-2">
                                    <div class="input-group date">
                                        <span class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </span>
                                        <input type="text" class="form-control layer-date" id="startDate" name="startDate">
                                    </div>
                                </div>

                                <label class="col-sm-1 control-label">结束时间<font color="red">*</font></label>

                                <div class="col-sm-2">
                                    <div class="input-group date">
                                        <span class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </span>
                                        <input type="text" class="form_datetime form-control" id="endDate" name="endDate">
                                    </div>
                                </div>

                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">

                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">内容<font color="red">*</font></label>

                                <div class="col-sm-6">
                                    <textarea rows="" cols="" class="form-control" placeholder="" name="content" id="content"></textarea>
                                </div>

                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-1"></div>
                                <label class="col-sm-1 control-label">备注</label>

                                <div class="col-sm-6">
                                    <textarea rows="" cols="" class="form-control" placeholder="" name="remark" id="remark"></textarea>
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
<script type="text/javascript" src="<%=basePath %>/myjs/datetimepicker_zh-CN.js"></script>
<script type="text/javascript">
$('#startDate').datetimepicker({
	 minView: 1,
	 todayBtn: "linked",
	 format: 'yyyy-mm-dd hh:00:00',
	 startDate : new Date() 
	 //d, dd, D, DD, m, mm, M, MM, yy, yyyy
}).on('changeDate',function(e){  
  var startTime = e.date;  
  $('#endDate').datetimepicker('setStartDate',startTime);  

});
$('#endDate').datetimepicker({
	 minView: 1,
	 todayBtn: "linked",
	 format: 'yyyy-mm-dd hh:00:00'
	 //d, dd, D, DD, m, mm, M, MM, yy, yyyy
}).on('changeDate',function(e){  
   var endTime = e.date;  
   $('#startDate').datetimepicker('setEndDate',endTime); 

});
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:00:00',
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left"

    });

    var standard = "<i class='fa fa-times-circle'></i> ";

    $("#checkForm").validate({
        rules: {//这里加校验规则
            title: "required",
            teacher: "required",
            startDate: "required",
            endDate: "required",
            content: "required"
        },
        messages: {//这里给对应的提示
            title: standard + "请输入主题 !",
            teacher: standard + "请输入主讲人 !",
            startDate: standard + "请选择开始时间 !",
            endDate: standard + "请选择结束时间 !",
            content: standard + "请输入内容 !"
        },
        submitHandler: function(form){
            submit();  //去提交
        }
    })

    //保存
    function submit() {

        var params = {
            "courseId": $("#courseId").val(),
            "startDate": $("#startDate").val(), // 开始时间
            "endDate": $("#endDate").val(), // 结束时间
            "title": $("#title").val(), // 主题
            "teacher": $("#teacher").val(), // 主讲人
            "content": $("#content").val(), // 内容
            "remark": $("#remark").val()  // 备注
        }

        if($.trim(params.startDate) > $.trim(params.endDate)){
            swal("开始时间不能大于结束时间!");
            return;
        }

        $.post("addEtopTrainSchedule.do", params, function (data) {
            if (data.status == 10001) {
                swal({
                    title : "新增开课信息成功 !",
                    type : "success",
                    confirmButtonText : "确定",
                    closeOnConfirm : false
                }, function() {
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index);
                });

            } else {
                swal({
                    title : "新增开课信息失败 !",
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