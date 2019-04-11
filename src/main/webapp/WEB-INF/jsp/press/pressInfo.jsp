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
    <title>园区新闻-园区管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
 
    	<div class="row">
            <div class="col-xs-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                    	
                        <form id="form" method="get" class="form-horizontal">
                            <div class="row">
                                <div class="col-md-12">
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-1">新闻标题<font color="red">*</font></label>
                                                <div class="col-md-5">
                                                    <input type="text" value="${news.title}" class="form-control" placeholder="" name="title" id="title">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-1">激活状态</label>
                                                <div class="col-md-3">
                                                    <select class="form-control" name="state" id="state">
                                                        <option value="1" <c:if test="${news.state=='1'}">selected</c:if>>激活</option>
                                                        <option value="0" <c:if test="${news.state=='0'}">selected</c:if>>不激活</option>
                                                    </select>
                                                </div>
                                        </div>
                                    </div>

                                    <div class="hr-line-dashed"></div>

                                    <div class="row">
                                        <div class="form-group">
                                            <label class="col-md-1">新闻内容<font color="red">*</font></label>
                                            <div class="col-md-11">
                                                <textarea id="textWeb" name="textWeb" rows="" cols="" class="ckeditor" >${news.content }</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group" <c:if test="${readonly eq true}">style="display: none;"</c:if>>
                                        <div class="col-md-12" style="text-align: center;">
                                            <a class="btn btn-primary"  onclick="updatesubmit('${news.id}')">确认并保存</a>
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
        function updatesubmit(id){
            var stem = CKEDITOR.instances.textWeb.getData();
            var param={
                "id" : id,
                "title" : $("#title").val(),
                "totalType" : $("#totalType").val(),
                "newsType" : $("#newsType").val(),
                "state" : $("#state").val(),
                "content" : stem
            }

            $.post(basePath+"/press/updatePress.do",param,function(data){
                if(data.status==10001){
                    swal({   title: "保存成功！",
                                type: "success",
                                confirmButtonText: "ok",
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
    </script>
</body>
</html>