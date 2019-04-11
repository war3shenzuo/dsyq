<%@ page import="com.etop.management.properties.ImgProperties" %>
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
        <title>基本信息-课程管理</title>
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
                            <%--<li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">题库</a>
                            </li>
                            <li class=""><a data-toggle="tab" href="#tab-3" aria-expanded="false">参与人</a>
                            </li>--%>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">

                                    <div class="ibox float-e-margins">
                                        <div class="ibox-content">

                                            <form method="get" class="form-horizontal" id="signupForm">

                                                <div class="form-group">

                                                    <div>
                                                        <input type="hidden" id="id" value="${id}">
                                                    </div>

<!--                                                     <label class="col-sm-1 control-label">课程编号</label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" placeholder="" id="courseId"
                                                               name="courseId">
                                                    </div> -->


                                                    <label class="col-sm-1 control-label">课程类型<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <select class="form-control m-b" id="courseType" name="courseType"
                                                                style="float: left; margin-right: 1%;">
                                                            <option value="0">全部</option>
                                                            <option value="1">高管研修</option>
                                                            <option value="2">创业辅助</option>
                                                            <option value="3">淘系美工</option>
                                                            <option value="4">淘系运营</option>
                                                            <option value="5">淘系客服</option>
                                                            <option value="6">淘系推广</option>
                                                            <option value="7">淘系无线</option>
                                                            <option value="8">微商</option>
                                                            <option value="9">京东系列</option>
                                                            <option value="10">跨境系列</option>
                                                            <option value="11">其他平台</option>
                                                            <option value="12">其他类型</option>
                                                        </select>
                                                    </div>

                                                    <label class="col-sm-1 control-label">课程主题<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="courseName" name="courseName">
                                                    </div>
                                                    
                                                    <label class="col-sm-1 control-label">时长<font color="red">*</font></label>
					                                <div class="col-sm-2">
					                                   <input type="text" class="form-control timeRange"   placeholder="" name="duration" id="duration"/>
					                                </div>
                                                                                                        
												</div>
												
                                                <div class="hr-line-dashed"></div>
                                                
                                          		 <div class="form-group">
                                                    <label class="col-sm-1 control-label">适用平台</label>

                                                    <div class="col-sm-2">
                                                        <select class="form-control m-b" id="platform" name="platform"
                                                                style="float: left; margin-right: 1%;">
                                                            <option value="0">全部</option>
                                                            <option value="1">淘宝</option>
                                                            <option value="2">微信</option>
                                                            <option value="3">京东</option>
                                                            <option value="4">苏宁</option>
                                                            <option value="5">跨境平台</option>
                                                            <option value="6">其他平台</option>
                                                        </select>
                                                    </div>
                                                    
                                                    <label class="col-sm-1 control-label">适用岗位</label>

                                                    <div class="col-sm-2">
                                                        <select class="form-control m-b" id="post" name="post"
                                                                style="float: left; margin-right: 1%;">
                                                            <option value="">全部</option>
					                                        <option value="1">客户</option>
					                                        <option value="2">美工</option>
					                                        <option value="3">推广</option>
					                                        <option value="4">运营</option>
					                                        <option value="5">视觉设计</option>
					                                        <option value="6">活动策划</option>
					                                        <option value="7">企业高管</option>
                                                        </select>
                                                    </div>

                                                    <label class="col-sm-1 control-label">园区选择</label>

                                                    <div class="col-sm-2"  style=" padding-top: 18px;">

                                                        <select class="js-example-tags form-control" multiple="multiple" id="target" name="target">
                                                        <c:if test="${userType !=5}">
                                                        	<option value="all">全部</option>
                                                            <c:forEach items="${parks}" var="park">
                                                                <option value="${park.parkName}">${park.parkName}</option>
                                                            </c:forEach>
                                                         </c:if>
                                                        <c:if test="${userType ==5}">
                                                       		 <option value="all">全部</option>
                                                            <c:forEach items="${parks}" var="park">
                                                                <option value="${park.parkGroupName}">${park.parkGroupName}</option>
                                                            </c:forEach>
                                                         </c:if>
                                                        </select>
                                                    </div>

                                                </div>


                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">播放地址<font color="red">*</font></label>

                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" id="playUrl" name="playUrl">
                                                    </div>
                                                </div>
                                                
												<div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">上传图片</label>
                                                    <div class="col-sm-3">
                                                        <input type="hidden" class=" form-control" placeholder="" id="coverImg">
                                                        <input type="file" id="file" name="file" class="form-control">
                                                    </div>
                                                    <div class="col-sm-1">
                                                        <button class="btn btn-group" type="button" onclick="ajaxFileUpload()">上传</button>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <img id="buildImg" alt="" src="" height="100px" width="100px">
                                                    </div>
                                                </div>
                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">课程描述</label>

                                                    <%--<div class="col-sm-6">
                                                        <input type="text" class="form-control" id="description" name="description">
                                                    </div>--%>
                                                    <div class="col-md-11">
                                                        <textarea id="textWeb" name="textWeb" rows="" cols="" class="ckeditor" ></textarea>
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
        <script src="<%=basePath%>/myjs/tree.js"></script>
        <script type="text/javascript">
            var basePath = "<%=basePath%>";
        </script>
        <script src="<%=basePath%>/myjs/etopTraining.js"></script>
        <script type="text/javascript" src="<%=basePath%>/myjs/common.js"></script>
         <script src="<%=basePath%>/js/select/select2.full.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>/ckeditor/ckeditor.js"></script>
        <script type="text/javascript">CKEDITOR.replace("textWeb");</script>
        <script type="text/javascript" src="<%=basePath %>/myjs/timeRange2.js"></script>
        <script src="<%=basePath%>/js/select/select2.full.min.js"></script>
        <script type="text/javascript">
        var courseId = '${id}';
        
    	function ajaxFileUpload() {
			 upload('<%=basePath%>/parkgroup/uploadImg.do?id=${id}',"file",function(data){
				 	$("#coverImg").val(data.data.path);
					$("#buildImg").attr("src",'<%=ImgProperties.LOAD_PATH%>'+ data.data.path)
					$("#buildImg").show();
			});
		}
    	
    	 $("#target").select2({
             tags: true,
             separator: ",",
         });

         var target = '${info.target}'.split(",");
         $("#target").select2().val(target).trigger("change");

            var standard = "<i class='fa fa-times-circle'></i> ";

            $("#signupForm").validate({
                rules: {//这里加校验规则
                	courseType: "required",
                    courseName: "required",
                    duration: "required",
                    platform: "required",
                    textWeb: "required",
                    playUrl: "required"
                },
                messages: {//这里给对应的提示
                    courseName: standard + "请选择课程类型 !",
                    courseName: standard + "请输入课程主题 !",
                    duration: standard + "请选择时间 !",
                    platform: standard + "请选择试用平台 !",
                    textWeb: standard + "请输入课程描述 !",
                    playUrl: standard + "请输入播放地址 !"
                },
                submitHandler: function(form){
                    submit();
                }
            })

            //新增保存
            function submit(){
                var stem = CKEDITOR.instances.textWeb.getData();
                var params = {
                    "id" : '${id}',
                    "courseType" : $("#courseType").val(), // 课程类型
                    "courseName" : $("#courseName").val(), // 课程主题
                    "platform" : $("#platform").val(), // 适用平台
                    "post" : $("#post").val(), // 适用岗位
                    "description" : stem, // 课程描述
                    "target" : $("#target").val(), // 园区选择
                    "playUrl" : $("#playUrl").val(), // 播放地址
                    "duration" : $("#duration").val(), // 时长
                    "coverImg" : $("#coverImg").val() // 封面
                }
                
                if( $("#target").val()!=null && $("#target").val().length>0){
                    params.target= $("#target").val().toString() ;
                }else{
                    params.target= "";
                }
                $.post("addEtopOnlineTraining.do",params,function(data){
                    if(data.status==10001){
                        refreshetopOnlineTrainingList();
                        swal({
                            title : data.msg,
                            type : "success",
                            confirmButtonText : "确定",
                            closeOnConfirm : true
                        }, function() {
                            location.reload();
                        });
                    }else{
                        swal( "保存失败！", "","error");
                    }
                });
            }
        </script>
    </body>
</html>