<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>通知添加-平台管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
 
    	<div class="row">
            <div class="col-md-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                        <form id="form" method="get" class="form-horizontal">
                        <div class="row">
                            <div class="col-md-12">
                            <div class="row">
                            <div class="form-group">
                                <label class="col-md-1">通知标题<font color="red">*</font></label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" placeholder="" name="title" id="title"> 
                                </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
	                        <div class="row">
	                        <div class="form-group">
	                            <label class="col-md-1">激活状态</label>
                                <div class="col-md-3">
                                    <select class="form-control" name="state" id="state">
                                        <option value="1">激活</option>
                                        <option value="0">不激活</option>
                                    </select>
                                </div>

                            </div>
                            </div>
                             <div class="hr-line-dashed"></div>
	                        <div class="row">
	                        <div class="form-group">

                                <label class="col-md-1">接收者<font color="red">*</font></label>
                                <div class="col-md-1">
                                    <!--  <a class="btn btn-primary"  onclick="addsubmit()">保存内容</a> -->
                                    <a class="btn btn-primary" onclick="select()">请选择</a>
                                </div>
                                <div class="col-md-10">
                                    <input type="hidden" class="form-control" placeholder="" id="ids">
                                    <input type="hidden" class="form-control" placeholder="" id="allIds">
<!--                                     <textarea type="text" class="form-control" placeholder="" id="receiver" disabled></textarea> -->
                                    <textarea rows="3" cols="200" class="form-control"  id="receiver" disabled></textarea>   
                                </div>
	                        </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            <div class="form-group">
                                <label class="col-md-1">通知内容<font color="red">*</font></label>
                                <div class="col-md-11">
                                    <textarea id="textWeb" name="textWeb" rows="" cols="" class="ckeditor" ></textarea>
                                </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div> 
                            </div>
                            <div class="row">
                            <div class="col-md-12">
	                            <div class="form-group">
	                                <div class="col-md-12" style="text-align: center;">
	                                   <!--  <a class="btn btn-primary"  onclick="addsubmit()">保存内容</a> -->
	                                    <input class="btn btn-primary" type="submit" value="确认并保存">
	                                </div>
	                            </div>
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
    	var basePath='<%=basePath%>';
    </script>

    <script type="text/javascript" src="<%=basePath%>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">CKEDITOR.replace("textWeb");</script>

    <script type="text/javascript">
        var e = "<i class='fa fa-times-circle'></i> ";
        $("#form").validate({
            rules: {//这里加校验规则
                title : "required",
                receiver : "required",
                textWeb : "required"
            },
            messages: {//这里给对应的提示
                title : e+"请输入通知标题",
                receiver : e+"请选择收件人",
                textWeb : e+"请输入通知内容"
            },
            submitHandler: function(){
                addsubmit();
            }
        })

        /**添加*/
        function addsubmit(){
            var content = CKEDITOR.instances.textWeb.getData();
            var param={
                "title" : $("#title").val(),
                "state" : $("#state").val(),
                "addressee" : $("#ids").val(),
                "allIds" : $("#allIds").val(),
                "receiver" : $("#receiver").val(),
                "content" : content
            }

            $.post(basePath+"/etopNotice/addEtopNotice.do",param,function(data){
                if(data.status==10001){
                    swal({
                                title: "保存成功！",
                                type: "success",
                                confirmButtonText: "确定",
                                closeOnConfirm: false
                            },
                            function(){
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index);
                            }
                    );


                }else{
                    swal( data.msg, "","error");
                }
            });
        }

        function select(){
            openSelectPage('选择收件人','800px','600px',basePath+'/etopNotice/selectReceiverPage.do','');
        }

    </script>
    
</body>
</html>