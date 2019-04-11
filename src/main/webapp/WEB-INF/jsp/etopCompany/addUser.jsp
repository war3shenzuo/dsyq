<!-- 选择员工  -->
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
    <title>新增员工-客户管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

  <!-- Panel Other -->
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <div class="row row-lg">
            <div class="col-sm-12">
                <div class="row">

                    <div class="col-sm-2">
                        <label>员工姓名<font color="red">*</font></label>
                        <input type="text" class=" form-control" placeholder="请输入员工姓名" id="name">
                    </div>

                    <div class="hr-line-dashed"></div>

                    <div class="col-sm-2">
                        <label>员工手机号<font color="red">*</font></label>
                        <input type="text" class=" form-control" placeholder="请输入手机号码" id="userName">
                    </div>
                    <div class="col-sm-2">
                        <!--<label>确认搜索</label>-->
                        <button class="btn btn-primary" onclick="addEmployee()"
                                type="button" style="margin-top: 23px;;">绑定
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</div>
<jsp:include page="../shared/js.jsp"/>
<script type="text/javascript">

    function addEmployee(){
        var userName = $.trim($("#userName").val());
        var name = $.trim($("#name").val());

        if(name == ''){
            swal("请输入员工姓名");
            return;
        }

        if(userName == ''){
            swal("请输入员工手机号码");
            return;
        }

        if(!/^1\d{10}$/g.test(userName)){
            swal("请输入正确的手机格式");
            return;
        }

        swal({
              title: "确认绑定该名员工! ",
              type: "warning",
              showCancelButton: true,
              confirmButtonColor: "#DD6B55",
              confirmButtonText: "是",
              cancelButtonText: "否",
              closeOnConfirm: false
            }, function(){
                $.get("<%=basePath %>/etopCompEmployees/addEmployees.do",
                    {
                        "companyId":'${companyId}',
                        "userName": userName,
                        "name": name,
                    },function(data){
                        if (data.status == 10001) {
                            swal({
                                title: data.msg,
                                type: "success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            }, function () {
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index);
                            });
                        } else {
                            swal({
                              title: data.msg,
                              type: "error",
                              confirmButtonText: "确定",
                              closeOnConfirm: true
                            }, function () {
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index);
                            });
                        }
                })
            }
    );
}
</script>
</body>
</html>

