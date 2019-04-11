<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.etop.management.properties.ImgProperties"  import="java.util.UUID"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
    UUID uuid = UUID.randomUUID(); 
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>投票通知添加-平台管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<jsp:include page="../shared/js.jsp"/>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
<input class="input" type="hidden" id="noticeId" size="35"   value="<%=uuid%>">
<div class="ibox float-e-margins">


    <div class="ibox-title">
        <h4>
            <big style="color:rgb(39,53,67);">投票通知</big>
        </h4>
    </div>

    <div class="form-group">
        <div class="ibox-content">

            <table id="tabdetail" border="0" cellspacing="0" cellpadding="5"  width="100%" class="tableStyle table table-bordered">
                <thead>
                    <tr>
                        <th width="60" height="40" align="center" valign="middle">序号</th>
                        <th align="center" width="140" valign="middle">内容</th>
                    </tr>
                </thead>
                <tbody id="tbodyId">
<%--                 <c:forEach items="${business}" var="business" varStatus="num"> --%>
                    <tr>
                        <td align="center" valign="middle">
                            <span class="num">1</span>
                                    <span class="actionArea">
                                        <a href="javascript:;" onClick="removerow(this)" class="delLite"></a>
                                    </span>
                        </td>
                        <td align="left" valign="middle" class="highlight">
                            <input name="options" type="text" class=" form-control" value=""/>
                        </td>
                    </tr>
<%--                 </c:forEach> --%>
                </tbody>
            </table>

            <table width="100%" border="0" cellspacing="0" cellpadding="5" class="tableStyle" style="border-top: 0px;">
                <tbody>
                <tr>
                    <td colspan="2" valign="top" style="border-right: 0px;">
                        <a id="aaddrow" href="javascript:;" onclick="addrow(); initorder();" class="btn btn-primary">
                            增加行
                        </a>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
    </div>

    <div class="hr-line-dashed"></div>

    <div class="form-group">
        <div class="col-sm-4 col-sm-offset-5">
            <a class="btn btn-primary" onclick="submitAdditional()">确认并保存</a>
        </div>
    </div>
    </form>
</div>
</div>
<script type="text/javascript">


    //编辑正式公司信息
    function submitAdditional(){

        var business = [];

        var trList = $("#tbodyId").children("tr")
        for (var i=0; i<trList.length; i++) {
            var tdArr = trList.eq(i).find("td");
            var options = tdArr.eq(1).find("input").val();// 网址
//             var createdAt = new Date().toLocaleDateString();
            business.push(
                    {
                        noticeId :$("#noticeId").val(),
                        options : options
                    }
            );
        }

        var params = {
            "etopVote" : JSON.stringify(business)
        }

        $.post("<%=basePath%>/etopVote/add.do", params, function(data){
            if(data.status==10001){
                swal( "新增成功！", "","success" );

            }else{
                swal( "新增失败！", "","error");
            }
        });
    }

    //初始化序号子表
    function initorder() {
        var num = 1;
        $("#tabdetail tr:gt(0)").find(".num").each(function() {
            $(this).html(num);
            num = num + 1;
        });
    }
    // 删除行子表
    function removerow(obj) {
        if ($("#tabdetail tr:gt(0)").find(".num").length == 1) {
            swal("列表只有一行，不能删除!");
            return;
        }
        $(obj).parent().parent().parent().remove();
        initorder();
    }
    //增加行子表
    function addrow() {

        var newrow = $("#tabdetail>tbody tr:first").clone();
        $(newrow).find("input[name='options']").val("");//网址
        $("#tabdetail tr:last").after(newrow);
        // 初始化计算方法
        return addrow;
    }

</script>
</body>
</html>

