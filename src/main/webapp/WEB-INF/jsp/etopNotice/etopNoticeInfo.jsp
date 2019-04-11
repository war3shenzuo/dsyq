<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>通知列表-园区管理</title>
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
                                                <label class="col-md-1">通知标题<font color="red">*</font></label>
                                                <div class="col-md-5">
                                                    <input type="text" value="${notice.title}" class="form-control" placeholder="" name="title" id="title" disabled>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-1">激活状态</label>
                                                <div class="col-md-3">
                                                    <select class="form-control" name="state" id="state" disabled>
                                                        <option value="1" <c:if test="${notice.state=='1'}">selected</c:if>>激活</option>
                                                        <option value="0" <c:if test="${notice.state=='0'}">selected</c:if>>不激活</option>
                                                    </select>
                                                </div>
                                        </div>
                                    </div>

                                    <div class="hr-line-dashed"></div>

                                    <div class="row">
                                        <div class="form-group">
                                            <label class="col-md-1">接受者<font color="red">*</font></label>
                                            <div class="col-md-10">
                                            
                                                <textarea id="userName" name="userName" rows="3" cols="100" class="form-control" disabled>${notice.userName }</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <c:if test="${notice.content ne null}">
                                    <div class="hr-line-dashed"></div>

                                    <div class="row">
                                        <div class="form-group">
                                            <label class="col-md-1">通知内容<font color="red">*</font></label>
                                            <div class="col-md-10">
                                                <textarea id="textWeb" name="textWeb" rows="" cols="" class="ckeditor" disabled>${notice.content }</textarea>
                                            </div>
                                        </div>
                                    </div>
									</c:if>
									
									<c:if test="${vote ne null}">						
                                    <div class="hr-line-dashed"></div>
	                               <div class="row">                                       
	                               <div class="form-group">
                                            <label class="col-md-1">投票</label>
                                            <div class="col-md-10">
                                             <c:forEach items="${vote}" var="vote" varStatus="options">
					        							<label>选项： ${vote.options} <span style="display: inline-block; width: 28px;;"></span>投票数： </label> 
					        							<label>${vote.number}</label><br>
					                         </c:forEach>
											</div>
                                        </div>
                                    </div>
                                    </c:if>
                                    <c:if test="${reply ne null}">
                                    <div class="hr-line-dashed"></div>
	                               <div class="row">                                       
	                               <div class="form-group">
                                            <label class="col-md-1">回复内容</label>
                                            <div class="col-md-10">
                                             <c:forEach items="${reply}" var="reply" varStatus="content">
						                            	<p style=" border: 1px solid #d6d6d6; margin-bottom: 5px; padding: 8px;">${reply.content}—— ——  <fmt:formatDate value="${reply.replyTime}" pattern="yyyy-MM-dd HH:mm:ss"/> by ${reply.user}</p>
					                         </c:forEach>
											</div>
                                        </div>
                                    </div>
                                    </c:if>
                                    <%--<div class="form-group" <c:if test="${readonly eq true}">style="display: none;"</c:if>>
                                        <div class="col-md-12" style="text-align: center;">
                                            <a class="btn btn-primary"  onclick="updatesubmit('${notice.id}')">确认并保存</a>
                                        </div>
                                    </div>--%>
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
                "state" : $("#state").val(),
                "content" : stem
            }

            $.post(basePath+"/etopNotice/updateNotice.do",param,function(data){
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