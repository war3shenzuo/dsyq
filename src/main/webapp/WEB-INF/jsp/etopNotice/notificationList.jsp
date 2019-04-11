<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <title>收件箱</title>

    <jsp:include page="../shared/css.jsp"/>

    <style>
        .unread a:hover{color: #337ab7;}
        .pagination a{    background-color: #FFF;
            border: 1px solid #DDD;
            color: inherit;
            float: left;
            line-height: 1.42857;
            margin-left: -1px;
            padding: 4px 10px;
            position: relative;
            text-decoration: none;
        }
        .pagination a:hover{    background-color: #f4f4f4;
            border-color: #DDD;
            color: inherit;
            cursor: pointer;
            z-index: 2;
        }
    </style>
</head>

<jsp:include page="../shared/js.jsp"/>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">

        <div class="col-sm-10 animated fadeInRight">
            <div class="mail-box-header">

                <form method="get" action="" class="pull-right mail-search">
                    <div class="input-group">
                        <input type="text" class="form-control input-sm" id="title" name="title" placeholder="搜索通知标题">
                        <div class="input-group-btn">
                            <button type="submit" class="btn btn-sm btn-primary" onclick="searchNotices()">
                              搜索
                            </button>
                        </div>
                    </div>
                </form>
                <h2 class="fa fa-inbox " style="font-weight: bolder; font-size: 16px">
                  收件箱
                    <span class="label label-warning">${receiverNum}</span>
                </h2>

                <%--<div class="mail-tools tooltip-demo m-t-md">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" title="刷新通知列表" onclick="refresh()"><i class="fa fa-refresh"></i> 刷新</button>
                </div>--%>
            </div>
            <div class="mail-box">

                <table class="table table-hover table-mail" id="notice">
                    <tbody>
                        <c:choose>
                            <c:when test="${!empty list}">
                                <c:forEach items="${list}" var="message">
                                    <tr class="unread">
                                        <td class="mail-ontact">${message.noticeType}</td>
                                        <td class="mail-ontact" style="color: #337ab7"><a href="<%=basePath%>/etopNotice/getMessageInfo.do?id=${message.id}">${message.title}</a></td>
                                        <td class="text-right mail-date" >
                                            <i class="fa fa-clock-o">
                                                    <fmt:formatDate value="${message.createdAt}" pattern="yyyy-MM-dd"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr class="unread">
                                    <td class="mail-ontact"></td>
                                    <td class="mail-ontact">没有找到匹配的记录</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div id="pages" class="pagination">
        <a href="getMessageList.do?currentPage=1">首页</a>
        <c:choose>
            <c:when test="${page.currentPage==1}">
                <a href="#">上一页</a>
            </c:when>
            <c:otherwise>
                <a href="getMessageList.do?currentPage=${page.currentPage-1}">上一页</a>
            </c:otherwise>
        </c:choose>

        <c:forEach begin="1" end="${page.totalPage}" var="p">
            <c:choose>
                <c:when test="${p==page.currentPage}">
                    <a href="getMessageList.do?currentPage=${p}" class="current_page">${p}</a>
                </c:when>
                <c:otherwise>
                    <a href="getMessageList.do?currentPage=${p}">${p}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${page.currentPage==page.totalPage}">
                <a href="#">下一页</a>
            </c:when>
            <c:otherwise>
                <a href="getMessageList.do?currentPage=${page.currentPage+1}">下一页</a>
            </c:otherwise>
        </c:choose>
        <a href="getMessageList.do?currentPage=${page.totalPage}">末页</a>
    </div>



</div>
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>
<script src="js/content.min.js?v=1.0.0"></script>
<script src="js/plugins/iCheck/icheck.min.js"></script>
<script>
    $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>

<script type="text/javascript">

    var notice = $("#notice")

    function refresh(){
        notice.bootstrapTable('refresh', {url: '<%=basePath%>/etopNotice/getMessageList.do'});
    }

    function searchNotices(){
        var title = $("#title").val();
        $.ajax({
            type : "post",
            url : "<%=basePath%>/etopNotice/getMessageList.do",
            data : {"title" : title},
            dataType : "text",
            async : true,
            success : function(data){
                if(data == "true"){
                    location.reload();
                }
            }
        });
    }

</script>
</body>


</html>
