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
    <title>基本信息-培训管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
    <link rel="stylesheet" href="<%=basePath%>/css/select/select2.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/hzw-city-picker.css"/>
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
                        <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">学员信息</a>
                        </li>
                        </c:if>
                    </ul>

                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">

                                <div class="ibox float-e-margins">

                                    <div class="ibox-content">

                                        <form method="get" class="form-horizontal" id="offlineProgramsForm">

                                            <div class="form-group">

                                                <label class="col-sm-1 control-label">类型<font color="red">*</font></label>

                                                <div class="col-sm-2">
                                                   
                                                    <select class="form-control m-b" id="offlineType" name="offlineType">
                                                        <option value="0"
                                                                <c:if test="${info.offlineType eq 0}">selected</c:if> >线下课程</option>
                                                        <option value="1"
                                                                <c:if test="${info.offlineType eq 1}">selected</c:if> >计划内培训</option>
                                                        <option value="4"
                                                                <c:if test="${info.offlineType eq 4}">selected</c:if> >计划外培训</option>
                                                        </select>
                                                </div>

                                                <label class="col-sm-1 control-label">课程编码<font color="red">*</font></label>

                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" id="courseId" name="courseId" value="${info.courseId}" readonly="readonly">
                                                </div>
                                                </div>
                                                
                                            <div class="hr-line-dashed"></div>
                                            
											<div class="form-group">
											
											<label class="col-sm-1 control-label">课程类型<font color="red">*</font></label>

                                                <div class="col-sm-2">
                                                    <select class="form-control m-b" id="courseType" name="courseType">
                                                        <option value="0"
                                                                <c:if test="${info.courseType eq 0}">selected</c:if> >全部</option>
                                                        <option value="1"
                                                                <c:if test="${info.courseType eq 1}">selected</c:if> >高管研修</option>
                                                        <option value="2"
                                                                <c:if test="${info.courseType eq 2}">selected</c:if> >创业辅助</option>
                                                        <option value="3"
                                                                <c:if test="${info.courseType eq 3}">selected</c:if> >淘系美工</option>
                                                        <option value="4"
                                                                <c:if test="${info.courseType eq 4}">selected</c:if> >淘系运营</option>
                                                        <option value="5"
                                                                <c:if test="${info.courseType eq 5}">selected</c:if> >淘系客服</option>
                                                        <option value="6"
                                                                <c:if test="${info.courseType eq 6}">selected</c:if> >淘系推广</option>
                                                        <option value="7"
                                                                <c:if test="${info.courseType eq 7}">selected</c:if> >淘系无线</option>
                                                        <option value="8"
                                                                <c:if test="${info.courseType eq 8}">selected</c:if> >微商</option>
                                                        <option value="9"
                                                                <c:if test="${info.courseType eq 9}">selected</c:if> >京东系列</option>
                                                        <option value="10"
                                                                <c:if test="${info.courseType eq 10}">selected</c:if> >跨境系列</option>
                                                        <option value="11"
                                                                <c:if test="${info.courseType eq 11}">selected</c:if> >其他平台</option>
                                                        <option value="12"
                                                                <c:if test="${info.courseType eq 12}">selected</c:if> >其他类型</option>
                                                    </select>
                                                </div>
                                                
                                                <div id="forSeries">
                                                <label class="col-sm-1 control-label">课程系列<font color="red">*</font></label>

                                                <div class="col-sm-2" style="position: relative">
                                                    <input type="text" class="form-control" id="series" name="series" value="${info.series}">
                                                    <div class="input-group-btn" style=" position: absolute ; right:6px; top:0;">
                                                        <button data-toggle="dropdown" class="btn btn-white dropdown-toggle" type="button">选择 <span class="caret"></span>
                                                        </button>
                                                        <ul class="dropdown-menu pull-right" >
                                                            <c:forEach items="${list}" var="list" >
                                                                <li><a onclick="selectSeries('${list.series}')" >${list.series}</a></li>
                                                            </c:forEach>
                                                        </ul>
                                                    </div>
                                                </div>
                                                </div>
                                                
												<label class="col-sm-1 control-label">课程主题<font color="red">*</font></label>

                                                    <div class="col-sm-5">
                                                        <input type="text" class="form-control" id="courseName" name="courseName"  value="${info.courseName}">
                                                    </div>
                                            </div>

                                            <div class="hr-line-dashed"></div>

                                            <div class="form-group">
                                                

                                                <label class="col-sm-1 control-label">适用平台<font color="red">*</font></label>

                                                <div class="col-sm-2">
                                                    <select class="form-control m-b" id="platform" name="platform">
                                                        <option value="0"
                                                                <c:if test="${info.platform eq 0}">selected</c:if> >全部
                                                        </option>
                                                        <option value="1"
                                                                <c:if test="${info.platform eq 1}">selected</c:if> >淘宝
                                                        </option>
                                                        <option value="2"
                                                                <c:if test="${info.platform eq 2}">selected</c:if> >微信
                                                        </option>
                                                        <option value="3"
                                                                <c:if test="${info.platform eq 3}">selected</c:if> >京东
                                                        </option>
                                                        <option value="4"
                                                                <c:if test="${info.platform eq 4}">selected</c:if> >苏宁</option>
                                                        <option value="5"
                                                                <c:if test="${info.platform eq 5}">selected</c:if> >跨境平台</option>
                                                        <option value="6"
                                                                <c:if test="${info.platform eq 6}">selected</c:if> >其他平台</option>
                                                    </select>
                                                </div>

                                                <label class="col-sm-1 control-label">适用岗位<font color="red">*</font></label>

                                                <div class="col-sm-2">
                                                    <select class="form-control m-b" id="post" name="post">
                                                        <option value="0"
                                                                <c:if test="${info.post eq 0}">selected</c:if> >全部
                                                        </option>
                                                        <option value="1"
                                                                <c:if test="${info.post eq 1}">selected</c:if> >客户
                                                        </option>
                                                        <option value="2"
                                                                <c:if test="${info.post eq 2}">selected</c:if> >美工
                                                        </option>
                                                        <option value="3"
                                                                <c:if test="${info.post eq 3}">selected</c:if> >推广
                                                        </option>
                                                        <option value="4"
                                                                <c:if test="${info.post eq 4}">selected</c:if> >运营</option>
                                                        <option value="5"
                                                                <c:if test="${info.post eq 5}">selected</c:if> >视觉设计</option>
                                                        <option value="6"
                                                                <c:if test="${info.post eq 6}">selected</c:if> >培训策划</option>
                                                        <option value="7"
                                                                <c:if test="${info.post eq 7}">selected</c:if> >企业高管</option>
                                                    </select>
                                                </div>

                                                <label class="col-sm-1 control-label">发布对象<font color="red">*</font></label>

                                                <c:if test="${info.createdBy eq user}">
                                                    <div class="col-sm-2" style=" padding-top: 18px;">
                                                        <select name="target" class="js-example-tags form-control" multiple="multiple" id="target">
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
													<c:if test="${info.createdBy ne user}">
                                                    <div class="col-sm-2" >
                                                    	<c:if test="${info.target ne 'all'}">
                                                        <input type="text" class="form-control" id="target2" name="target2"  value="${info.target}">
                                                        </c:if>
                                                    	<c:if test="${info.target eq 'all'}">
                                                        <input type="text" class="form-control" id="target2" name="target2"  value="该园区组下所有园区">
                                                        </c:if>
                                                    </div>
                                                    </c:if>
                                            </div>

                                            <div class="hr-line-dashed"></div>

                                            <div class="form-group">
                                                <label class="col-sm-1 control-label">培训价格<font color="red">*</font></label>

                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" id="price" name="price" value="${info.price}">
                                                </div>

                                                <label class="col-sm-1 control-label">培训时长<font color="red">*</font></label>

                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" id="trainTime" name="trainTime" value="${info.trainTime}">
                                                </div>

                                                <label class="col-sm-1 control-label">培训人数<font color="red">*</font></label>

                                                <div class="col-sm-1" style="position: relative">
                                                    <input type="number" class="form-control" id="peopleNum" name="peopleNum" aria-required="true" value="${info.peopleNum}">
                                                    <div style="position: absolute; top: 10px; right: 0px;">人</div>
                                                </div>

                                                    <label class="col-sm-2 control-label">报名截止日期<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="closeTime" name="closeTime" value='<fmt:formatDate value="${info.closeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'>
                                                    </div>
                                            
                                            </div>

                                            <div class="hr-line-dashed"></div>

                                            <div class="form-group">
                                            
                                                <label class="col-sm-1 control-label">负责人<font color="red">*</font></label>

                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" id="header" name="header" value="${info.header}">
                                                </div>
                                            
                                                <label class="col-sm-1 control-label">负责人联系方式<font color="red">*</font></label>

                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" id="phone" name="phone" value="${info.phone}">
                                                </div>
                                                  
                                                    <label class="col-sm-1 control-label">城市<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="cityChoice" name="city" value="${info.city}">
                                                    </div>
                                            </div>
                                            
											 <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                            
                                             <label class="col-sm-1 control-label">培训地址<font color="red">*</font></label>

                                                <div class="col-sm-5">
                                                    <input type="text" class="form-control" id="trainingAddress" name="trainingAddress" value="${info.trainingAddress}">
                                                </div>
                                            </div>

											 <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">课程图片</label>
                                                    <div class="col-sm-3">
                                                        <input type="hidden" class=" form-control" placeholder="" id="courseImg">
                                                        <input type="file" id="file" name="file" class="form-control">
                                                    </div>
                                                    <div class="col-sm-1">
                                                        <button class="btn btn-group" type="button" onclick="ajaxFileUpload()">上传</button>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <img id="coverImg" alt="" src="<%=ImgProperties.LOAD_PATH%>${info.courseImg}" height="100px" width="100px">
                                                    </div>
                                                </div>
                                                
                                            <div class="hr-line-dashed"></div>

                                            <div class="form-group">
                                                <label class="col-sm-1 control-label">课程内容</label>

                                                <div class="col-md-8">
                                                    <textarea id="textWeb" name="textWeb" rows="" cols="" class="ckeditor" >${info.courseContent}</textarea>
                                                </div>

                                            </div>

												
												<div class="ibox-title">
                                                <h5>
                                                    <normal>开课信息</normal>
                                                </h5>
                                            </div>

                                            <div class="form-group">
                                                <div class="ibox-content">

                                                    <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group"
                                                    <c:if test="${info.createdBy ne user}">style="display: none;"</c:if>>
                                                    

                                                        <a class="btn btn-outline btn-default" onclick="newCourse()">
                                                            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                                            <span>新增</span>
                                                        </a>
                                                        <button type="button" class="btn btn-outline btn-default" onclick="deleteCourse()">
                                                            <i class="glyphicon glyphicon-remove-circle" aria-hidden="true"></i> <span>删除</span>
                                                        </button>
                                                    </div>

                                                    <table id="trainScheduleTable"
                                                           data-mobile-responsive="true"
                                                           data-toggle="table"
                                                           data-url="<%=basePath%>/etopTrainSchedule/getEtopTrainScheduleList.do?courseId=${courseId}"
                                                           data-data-type="json"
                                                           data-side-pagination="server"
                                                           data-pagination="true"
                                                           data-query-params="queryParams"
                                                           data-page-list="[5, 10, 20, 50, 100, 200]"
                                                           data-striped="true"
                                                            >
                                                        <thead>
                                                        <tr>
                                                            <th data-field="state" data-checkbox="true"></th>
                                                            <th data-field="id" data-align="center" data-visible="false">id</th>
                                                            <th data-field="courseId" data-align="center" data-visible="false">课程id</th>
                                                            <th data-field="startDate" data-align="center">开始时间</th>
                                                            <th data-field="endDate" data-align="center">结束时间</th>
                                                            <th data-field="title" data-align="center">主题</th>
                                                            <th data-field="content" data-align="center">内容</th>
                                                            <th data-field="teacher" data-align="center">主讲人</th>
                                                            <th data-field="remark" data-align="center">备注</th>
                                                            <c:if test="${info.createdBy eq user}">
                                                            <th data-align="center" data-formatter='formatterTrainSchedule' >操作</th>
                                                            </c:if>
                                                        </tr>
                                                        </thead>
                                                    </table>
                                                </div>
                                            </div>
												
												
                                            <div class="ibox-title">
                                                <h5>
                                                    <normal>讲师信息</normal>
                                                </h5>
                                            </div>

                                            <div class="form-group">
                                                <div class="ibox-content">

                                                    <div class="btn-group hidden-xs" role="group"
                                                    <c:if test="${info.createdBy ne user}">style="display: none;"</c:if> >

                                                        <a class="btn btn-outline btn-default" onclick="newTeacher()">
                                                            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                                            <span>新增</span>
                                                        </a>
                                                        <button type="button" class="btn btn-outline btn-default" onclick="deleteTeacher()">
                                                            <i class="glyphicon glyphicon-remove-circle" aria-hidden="true"></i> <span>删除</span>
                                                        </button>
                                                    </div>

                                                    <table id="trainTeacherTable"
                                                           data-mobile-responsive="true"
                                                           data-toggle="table"
                                                           data-url="<%=basePath%>/etopTrainTeacher/getEtopTrainTeacherList.do?courseId=${courseId}"
                                                           data-data-type="json"
                                                           data-side-pagination="server"
                                                           data-pagination="true"
                                                           data-query-params="queryParams"
                                                           data-page-list="[5, 10, 20, 50, 100, 200]"
                                                           data-striped="true"
                                                            >
                                                        <thead>
                                                        <tr>
                                                            <th data-field="state" data-checkbox="true"></th>
                                                            <th data-field="id" data-align="center" data-visible="false">id</th>
                                                            <th data-field="courseId" data-align="center" data-visible="false">课程id</th>
                                                            <th data-field="name" data-align="center">姓名</th>
                                                            <th data-field="photo" data-align="center" data-formatter="formatterPhoto">讲师照片</th>
                                                            <th data-field="profile" data-align="center">简介</th>
                                                            <c:if test="${info.createdBy eq user}">
                                                            <th data-align="center" data-formatter='formatterTrainTeacher'>操作</th>
                                                            </c:if>
                                                        </tr>
                                                        </thead>
                                                    </table>
                                                </div>
                                            </div>

                                            
                                            <div class="ibox-title">
                                                <h5>
                                                    <normal>培训总结</normal>
                                                </h5>
                                            </div>

                                            <div class="ibox-content">
                                                <div class="form-group">

                                                    <label class="col-sm-3 control-label">培训参加人数</label>

                                                    <div class="col-sm-1" style="position: relative">
                                                        <input type="number" class="form-control" id="joinPeopleNum" name="joinPeopleNum" aria-required="true" value="${info.joinPeopleNum}">
                                                        <div style="position: absolute; top: 10px; right: 0px;">人</div>
                                                    </div>

                                                    <label class="col-sm-1 control-label">师咨评分</label>

                                                    <div class="col-sm-1" style="position: relative">
                                                        <input type="number" class="form-control" id="teacherLv" name="teacherLv" aria-required="true" value="${info.teacherLv}"  min="1" max="5">
                                                        <div style="position: absolute; top: 10px; right: 0px;">分</div>
                                                    </div>

                                                    <label class="col-sm-1 control-label">组织评分</label>

                                                    <div class="col-sm-1" style="position: relative">
                                                        <input type="number" class="form-control" id="organizeLv" name="organizeLv" aria-required="true" value="${info.organizeLv}"  min="1" max="5">
                                                        <div style="position: absolute; top: 10px; right: 0px;">分</div>
                                                    </div>

                                                    <label class="col-sm-1 control-label">内容评分</label>

                                                    <div class="col-sm-1" style="position: relative">
                                                        <input type="number" class="form-control" id="contentLv" name="contentLv" aria-required="true" value="${info.contentLv}"  min="1" max="5">
                                                        <div style="position: absolute; top: 10px; right: 0px;">分</div>
                                                    </div>
                                                </div>
                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">上传图片</label>
                                                    <div class="col-sm-3">
                                                        <input type="hidden" class=" form-control" placeholder="" id="sumsImg">
                                                        <input type="file" id="file2" name="file2" class="form-control">
                                                    </div>
                                                    <div class="col-sm-1">
                                                        <button class="btn btn-group" type="button" onclick="ajaxFileUpload2()">上传</button>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <img id="buildImg" alt="" src="<%=ImgProperties.LOAD_PATH%>${info.sumsImg}" height="100px" width="100px">
                                                    </div>
                                                </div>
                                                
                                                <div class="hr-line-dashed"></div>
                                                
                                                    <div class="form-group">
                                                    <label class="col-sm-2 control-label">总结</label>

                                                    <div class="col-sm-8">
<%--                                                         <input type="text" class="form-control" id="sumsContent" name="sumsContent" value="${info.sumsContent}"> --%>
                                                        <textarea id="sumsContent" rows="5" name="textWeb" cols="40" class="form-control">${info.sumsContent}</textarea>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="hr-line-dashed"></div>
                                             <c:if test="${info.createdBy eq user}">

                                            <div class="form-group">
                                                <div class="col-sm-4 col-sm-offset-5">
                                                    <%--<a class="btn btn-primary"  onclick="submit()">确认并保存</a>--%>
                                                    <input class="btn btn-primary" type="submit" value="确认并保存">
                                                </div>
                                            </div>
                                            </c:if>

                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <%--学员信息--%>
                        <div id="tab-2" class="tab-pane">
                            <div class="panel-body">
                                <jsp:include page="../etopTrainApplication/etopTrainApplicationList.jsp" />
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
    <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/etopTraining.js"></script>
    <script src="<%=basePath%>/js/select/select2.full.min.js"></script>
    <script type="text/javascript">
    var courseId = '${courseId}';
    $(document).ready(function(){
    	//alert(0);
    	if($("#offlineType").val() != 0)
		{
		$("#forSeries").css("display","none");
		$("#series").rules("remove");  
		}
	else
		{
		$("#forSeries").css("display","block");
		$("#series").rules("add",{required:true});  
		}
    });
    $('#closeTime').datetimepicker({
    	 minView: 1,
    	 todayBtn: "linked",
    	 language: 'zh-CN',
    	 format: 'yyyy-mm-dd hh:00:00',
    	 startDate : new Date() 
    	 //d, dd, D, DD, m, mm, M, MM, yy, yyyy
    });
    
        $("#target").select2({
            tags: true,
            separator: ",",
        });
       
        var target = '${info.target}'.split(",");
        $("#target").select2().val(target).trigger("change");

        var standard = "<i class='fa fa-times-circle'></i> ";

        $("#offlineProgramsForm").validate({
            rules: {//这里加校验规则
            	courseName: "required",//培训主题
                series: "required",//培训系列
                closeTime: "required",//截止日期
                city: "required",//城市
                header: "required",//负责人
                trainingAddress: "required",//培训地址
                courseType: "required",//培训类型
                platform: "required",//适用平台
                post: "required",//适用岗位
                target: "required",//发布对象
                price: "required",//培训价格
                peopleNum: "required",//招募人数
                trainTime: "required",//时长
                header: "required",//负责人
                phone: "required",//负责人联系方式
                trainingAddress: "required"//培训地址
            },
            messages: {//这里给对应的提示
            	courseName: standard + "请输入培训主题 !",
                series: standard + "请输入培训系列 !",
                courseType: standard + "请选择培训类型 !",
                city: standard + "请输入城市 !",
                header: standard + "请负责人 !",
                trainingAddress: standard + "请培训地址 !",
                closeTime: standard + "请选择截止日期 !",
                platform: standard + "请选择适用平台 !",
                post: standard + "请选择适用岗位 !",
                target: standard + "请选择发布对象 !",
                price: standard + "请输入培训价格 !",
                peopleNum: standard + "请输入招募人数 !",
                trainTime: standard + "请输入时长 !",
                header: standard + "请输入负责人 !",
                phone: standard + "请输入负责人联系方式 !",
                trainingAddress: standard + "请输入培训地址 !"
            },
            submitHandler: function(form){
                submit();  //去提交
            }
        })

	function ajaxFileUpload() {
		 upload('<%=basePath%>/parkgroup/uploadImg.do?id=${info.id}',"file",function(data){
			 	$("#courseImg").val(data.data.path);
				$("#coverImg").attr("src",'<%=ImgProperties.LOAD_PATH%>'+ data.data.path)
				$("#coverImg").show();
		});
	}

	function ajaxFileUpload2() {
		 upload('<%=basePath%>/parkgroup/uploadImg.do?id=${info.id}',"file2",function(data){
			 	$("#sumsImg").val(data.data.path);
				$("#buildImg").attr("src",'<%=ImgProperties.LOAD_PATH%>'+ data.data.path)
				$("#buildImg").show();
		});
	}
	
        //新增正式公司信息保存
        function submit(){
        	
//             if($("#trainScheduleTable").bootstrapTable('getData')=='' || $("#trainScheduleTable").bootstrapTable('getData')==null ||$("#trainScheduleTable").bootstrapTable('getData').length<=0){
         	   
//          	   swal( "请填写日程信息！", "","error");
//          	   return
//             }
            var content = CKEDITOR.instances.textWeb.getData();
            var params = {
            		 "id" : '${courseId}',
            		 "offlineType" :$("#offlineType").val(),
                     "courseName" : $("#courseName").val(), // 培训主题
                     "series" : $("#series").val(), // 培训系列
                     "courseType" : $("#courseType").val(), // 培训类型
                     "platform" : $("#platform").val(), // 适用平台
                     "city" : $("#cityChoice").val(), // 适用平台
                     "recruit" : $("#recruit").val(), // 招募对象
                     "closeTime" : $("#closeTime").val(), // 截止日期
                     "courseStatus" : $("#courseStatus").val(), // 培训状态
                     "post" : $("#post").val(), // 适用岗位
                     "trainTime" : $("#trainTime").val(), // 时长
                     "target" : $("#target").val(), // 发布对象
                     "choosePark" : $("#choosePark").val(), // 园区选择
                     "price" : $("#price").val(), // 培训价格
                     "courseContent" :content, // 培训内容
                     "trainingAddress" : $("#trainingAddress").val(), // 培训地址
                     "header" : $("#header").val(), // 负责人
                     "phone" : $("#phone").val(), // 负责人
                     "courseImg" : $("#courseImg").val(), // 培训图片
                     "peopleNum" : $("#peopleNum").val(), // 开课人数
                     "joinPeopleNum" : $("#joinPeopleNum").val(),// 参加人数
                     "teacherLv" : $("#teacherLv").val(), // 师咨评分
                     "organizeLv" : $("#organizeLv").val(), // 组织评分
                     "contentLv" : $("#contentLv").val(), // 内容评分
                     "sumsContent" : $("#sumsContent").val(), // 总结内容
                     "sumsImg" : $("#sumsImg").val() // 总结图片
            }

            if( $("#target").val()!=null && $("#target").val().length>0){
                params.target= $("#target").val().toString() ;
            }else{
                params.target= "";
            }

            $.post("updateOfflinePrograms.do",params,function(data){
                if(data.status==10001){
                    refreshOfflineProgramsList();
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
        $("#offlineType").change(function(){
        	if($("#offlineType").val() != 0)
        		{
        		$("#forSeries").css("display","none");
        		$("#series").rules("remove");  
        		}
        	else
        		{
        		$("#forSeries").css("display","block");
        		$("#series").rules("add",{required:true});  
        		}
        });
        
<%--         function citydata(){
		      var data3;
		      $.ajax({  
		          type : "post",  
		          url : "<%=basePath%>/etopOfflinePrograms/selectCity.do",  
		          async: false,
		          success : function(data2){/* 
		            alert(cityses(data2.hot,data2.province));
		            return cityses(data2.hot,data2.province); */
		            data3=data2;
		          }  
		      
		      });
		      return data3;
		     }  
		    
		  var cityPicker = new HzwCityPicker2({
		    data: citydata(),
		    target: 'cityChoice',
		    valType: 'k-v',
		    hideCityInput: {
		      name: 'city',
		      id: 'city'
		    },
		    hideProvinceInput: {
		      name: 'province',
		      id: 'province'
		    },
		    callback: function(){
		      var aaaa=$('#city').attr('value');
		      var str_after = aaaa.split('-')[1]; 
//		      etSreach(str_after);
		      $("#ningbo").html(str_after);
		    }
		  });

		  cityPicker.init();  
		  
		  
		  function GetQueryString(name)
			{
			     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			     var r = window.location.search.substr(1).match(reg);
			     if(r!=null)return  decodeURI(r[2]); return null;
			} --%>
    </script>
</body>
</html>