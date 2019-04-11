<%@ page import="com.etop.management.properties.ImgProperties" %>
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
    <title>基本信息-课程管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" href="<%=basePath%>/css/select/select2.min.css">
	<jsp:include page="../shared/css.jsp"/>
</head>
<jsp:include page="../shared/js.jsp"/>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

    	<div class="row">
            <div class="col-sm-12">

                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">基本信息</a>
                        </li>
                         <c:if test="${userType != 2}">
                        <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">题库</a>
                        </li>
                        <li class=""><a data-toggle="tab" href="#tab-3" aria-expanded="false">参与人</a>
                        </li>
                        </c:if>
                    </ul>

                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">

                                <div class="ibox float-e-margins">

                                    <div class="ibox-content">

                                        <form method="get" class="form-horizontal" id="signupForm">

                                            <div class="form-group">
                                                <input type="text" class="form-control" placeholder="" id="id" name="id" value="${notice.id}" style="visibility: hidden">
                                                <label class="col-sm-1 control-label">课程编号</label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" placeholder="" id="courseId" name="courseId" value="${notice.courseId}" readonly="readonly">
                                                </div>
											</div>
											
											<div class="hr-line-dashed"></div>
											
											<div class="form-group">
                                                <label class="col-sm-1 control-label">课程类型</label>
                                                <div class="col-sm-2">
                                                    <select class="form-control m-b" id="courseType" name="courseType"
                                                            style="float: left; margin-right: 1%;">
                                                        <option value="0"
                                                                <c:if test="${notice.courseType eq 0}">selected</c:if> >全部</option>
                                                        <option value="1"
                                                                <c:if test="${notice.courseType eq 1}">selected</c:if> >高管研修</option>
                                                        <option value="2"
                                                                <c:if test="${notice.courseType eq 2}">selected</c:if> >创业辅助</option>
                                                        <option value="3"
                                                                <c:if test="${notice.courseType eq 3}">selected</c:if> >淘系美工</option>
                                                        <option value="4"
                                                                <c:if test="${notice.courseType eq 4}">selected</c:if> >淘系运营</option>
                                                        <option value="5"
                                                                <c:if test="${notice.courseType eq 5}">selected</c:if> >淘系客服</option>
                                                        <option value="6"
                                                                <c:if test="${notice.courseType eq 6}">selected</c:if> >淘系推广</option>
                                                        <option value="7"
                                                                <c:if test="${notice.courseType eq 7}">selected</c:if> >淘系无线</option>
                                                        <option value="8"
                                                                <c:if test="${notice.courseType eq 8}">selected</c:if> >微商</option>
                                                        <option value="9"
                                                                <c:if test="${notice.courseType eq 9}">selected</c:if> >京东系列</option>
                                                        <option value="10"
                                                                <c:if test="${notice.courseType eq 10}">selected</c:if> >跨境系列</option>
                                                        <option value="11"
                                                                <c:if test="${notice.courseType eq 11}">selected</c:if> >其他平台</option>
                                                        <option value="12"
                                                                <c:if test="${notice.courseType eq 12}">selected</c:if> >其他类型</option>
                                                    </select>
                                                </div>


												<label class="col-sm-1 control-label">课程主题<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="courseName" name="courseName" value="${notice.courseName}">
                                                    </div>
                                                    
                                               <label class="col-sm-1 control-label">时长<font color="red">*</font></label>
					                                <div class="col-sm-2">
					                                   <input type="text" class="form-control timeRange"   placeholder="" name="duration" id="duration"  value="${notice.duration}"/>
					                                </div>
					                                
				                                </div>
				                                
				                                <div class="hr-line-dashed"></div>
				                                
												<div class="form-group">
                                                <label class="col-sm-1 control-label">适用平台</label>
                                                <div class="col-sm-2">
                                                    <select class="form-control m-b" id="platform" name="platform" style="float: left; margin-right: 1%;">
                                                        <option value="0"
                                                                <c:if test="${notice.platform eq 0}">selected</c:if> >全部
                                                        </option>
                                                        <option value="1"
                                                                <c:if test="${notice.platform eq 1}">selected</c:if> >淘宝
                                                        </option>
                                                        <option value="2"
                                                                <c:if test="${notice.platform eq 2}">selected</c:if> >微信
                                                        </option>
                                                        <option value="3"
                                                                <c:if test="${notice.platform eq 3}">selected</c:if> >京东
                                                        </option>
                                                        <option value="4"
                                                                <c:if test="${notice.platform eq 4}">selected</c:if> >苏宁</option>
                                                        <option value="5"
                                                                <c:if test="${notice.platform eq 5}">selected</c:if> >跨境平台</option>
                                                        <option value="6"
                                                                <c:if test="${notice.platform eq 6}">selected</c:if> >其他平台</option>
                                                    </select>
                                                </div>

												<label class="col-sm-1 control-label">适用岗位</label>

                                                    <div class="col-sm-2">
                                                        <select class="form-control m-b" id="post" name="post"  style="float: left; margin-right: 1%;">
					                                         <option value="0"
                                                                <c:if test="${notice.post eq 0}">selected</c:if> >全部
                                                        </option>
                                                        <option value="1"
                                                                <c:if test="${notice.post eq 1}">selected</c:if> >客户
                                                        </option>
                                                        <option value="2"
                                                                <c:if test="${notice.post eq 2}">selected</c:if> >美工
                                                        </option>
                                                        <option value="3"
                                                                <c:if test="${notice.post eq 3}">selected</c:if> >推广
                                                        </option>
                                                        <option value="4"
                                                                <c:if test="${notice.post eq 4}">selected</c:if> >运营</option>
                                                        <option value="5"
                                                                <c:if test="${notice.post eq 5}">selected</c:if> >视觉设计</option>
                                                        <option value="6"
                                                                <c:if test="${notice.post eq 6}">selected</c:if> >活动策划</option>
                                                        <option value="7"
                                                                <c:if test="${notice.post eq 7}">selected</c:if> >企业高管</option>
                                                        </select>
                                                    </div>
                                                <label class="col-sm-1 control-label">园区选择</label>


												<c:if test="${notice.createdBy eq user}">
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
                                                    </c:if>
													<c:if test="${notice.createdBy ne user}">
                                                    <div class="col-sm-2" >
                                                    	<c:if test="${notice.target ne 'all'}">
                                                        <input type="text" class="form-control" id="target2" name="target2"  value="${notice.target}">
                                                        </c:if>
                                                    	<c:if test="${notice.target eq 'all'}">
                                                        <input type="text" class="form-control" id="target2" name="target2"  value="所有园区">
                                                        </c:if>
                                                    </div>
                                                    </c:if>                                            
                                            </div>

                                            <div class="hr-line-dashed"></div>

                                            <div class="form-group">

                                                <label class="col-sm-1 control-label">播放地址</label>
                                                <div class="col-sm-6">
                                                    <input type="text" class="form-control" id="playUrl" name="playUrl" value="${notice.playUrl}">
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
                                                        <img id="buildImg" alt="" src="<%=ImgProperties.LOAD_PATH%>${notice.coverImg}" height="100px" width="100px">
                                                    </div>
                                                </div>
                                                
                                            <div class="hr-line-dashed"></div>

                                            <div class="form-group">
                                                <label class="col-sm-1 control-label">课程描述</label>
                                                <div class="col-md-11">
                                                    <textarea id="textWeb" name="textWeb" rows="" cols="" class="ckeditor" >${notice.description}</textarea>
                                                </div>
                                            </div>

                                            <div class="hr-line-dashed"></div>
                                            
											<c:if test="${notice.createdBy eq user}">
                                            <div class="form-group" <c:if test="${readonly eq true}">style="display: none;"</c:if> >
                                                <div class="col-sm-4 col-sm-offset-5">
                                                    <input class="btn btn-primary" type="submit" value="确认并保存">
                                                </div>
                                            </div>
                                            </c:if>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <%--题库页面--%>
                        <div id="tab-2" class="tab-pane">
                            <div class="panel-body">
                                <jsp:include page="../etopQuestionBank/etopQuestionBankList.jsp" />
                            </div>
                        </div>

                        <%--参与人页面--%>
                        <div id="tab-3" class="tab-pane">
                            <div class="panel-body">
                                <jsp:include page="../etopParticipant/etopParticipantList.jsp" />
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="<%=basePath%>/myjs/tree.js"></script>
    <script type="text/javascript">
        var basePath='<%=basePath%>';
    </script>
    <script type="text/javascript" src="<%=basePath%>/ckeditor/ckeditor.js"></script>
        <script type="text/javascript">CKEDITOR.replace("textWeb");</script>
    <script type="text/javascript" src="<%=basePath%>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/timeRange2.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/etopTraining.js"></script>
    <script src="<%=basePath%>/js/select/select2.full.min.js"></script>
    <script type="text/javascript">
	function ajaxFileUpload() {
		 upload('<%=basePath%>/parkgroup/uploadImg.do?id=${notice.id}',"file",function(data){
			 	$("#coverImg").val(data.data.path);
				$("#buildImg").attr("src",'<%=ImgProperties.LOAD_PATH%>'+ data.data.path)
				$("#buildImg").show();
		});
	}
        var target = document.getElementById("target");
        selected(target,'${notice.target}');

        $("#target").select2({
            tags: true,
            separator: ",",
        });
 
        var target = '${notice.target}'.split(",");
        $("#target").select2().val(target).trigger("change");

        var standard = "<i class='fa fa-times-circle'></i> ";

        $("#signupForm").validate({
            rules: {//这里加校验规则
            	 courseName: "required",//课程主题
                 courseId: "required",//课程编码
                 series: "required",//课程系列
                 courseType: "required",//课程类型
                 platform: "required",//适用平台
                 post: "required",//适用岗位
                 target: "required",//发布对象
                 price: "required",//培训价格
                 peopleNum: "required",//开课人数
                 trainTime: "required",//时长
                 header: "required",//负责人
                 trainingAddress: "required"//培训地址
            },
            messages: {//这里给对应的提示
            	courseName: standard + "请输入课程主题 !",
                courseId: standard + "请输入课程编码 !",
                series: standard + "请输入课程系列 !",
                courseType: standard + "请选择课程类型 !",
                platform: standard + "请选择适用平台 !",
                post: standard + "请选择适用岗位 !",
                target: standard + "请选择发布对象 !",
                price: standard + "请输入培训价格 !",
                peopleNum: standard + "请输入开课人数 !",
                trainTime: standard + "请输入时长 !",
                header: standard + "请输入负责人 !",
                trainingAddress: standard + "请输入培训地址 !"
            },
            submitHandler: function(form){
                submit();
            }
        })

        //编辑信息
        function submit(){
            var stem = CKEDITOR.instances.textWeb.getData();
            var params = {
                "id" : '${id}',
                "courseId" : $("#courseId").val(), // 课程编号
                "courseType" : $("#courseType").val(), // 课程编号
                "speaker" : $("#speaker").val(), // 主讲人
                "courseName" : $("#courseName").val(), // 课程主题
                "platform" : $("#platform").val(), // 适用平台
                "post" : $("#post").val(), // 适用岗位
                "description" : stem, // 课程描述
                "target" : $("#target").val(), // 园区选择
                "playUrl" : $("#playUrl").val(),// 播放地址
                "duration" : $("#duration").val(), // 时长
                "coverImg" : $("#coverImg").val() // 封面
            }
            
            if( $("#target").val()!=null && $("#target").val().length>0){
                params.target= $("#target").val().toString() ;
            }else{
                params.target= "";
            }
            $.post("updateOnlineTraining.do", params, function(data){
            	if(data.status==10001){
            		refreshetopOnlineTrainingList();
                    swal({
                        title : data.msg,
                        text: "修改完成",
                        type : "success",
                        confirmButtonText : "确定",
                        closeOnConfirm : true
                    }, function() {

                    });
                }else{
                    swal( "修改失败！", "","error");
                }
            });
        }
    </script>
</body>
</html>