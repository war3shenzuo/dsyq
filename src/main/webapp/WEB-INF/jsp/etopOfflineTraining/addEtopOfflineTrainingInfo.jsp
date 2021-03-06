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
        <title>园区活动</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <jsp:include page="../shared/css.jsp"/>
        <link rel="stylesheet" href="<%=basePath%>/css/select/select2.min.css">
        <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
        <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/hzw-city-picker.css"/>
    </head>

    <body class="gray-bg">
        <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-sm-12">

                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">基本信息</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">

                                    <div class="ibox float-e-margins">
                                        <form method="get" class="form-horizontal" id="courseForm">
                                            <div class="ibox-content">

											<div class="form-group">

                                                <label class="col-sm-1 control-label">类型<font color="red">*</font></label>

                                                <div class="col-sm-2">
<!--                                                     <input type="text" class="form-control" id="offlineType" name="offlineType" value="计划外活动" readonly="readonly"> -->
                                                    <select class="form-control m-b" id="offlineType" name="offlineType">
                                                            <option value="2">计划内活动</option>
                                                            <option value="3">计划外活动</option>
                                                        </select>
                                                </div>

                                                <label class="col-sm-1 control-label"  style="display: none">编码<font color="red">*</font></label>

                                                <div class="col-sm-2" style="display: none">
                                                    <input type="text" class="form-control" id="courseId" name="courseId" value="${info.courseId}">
                                                </div>


                                            </div>
                                            
                                            
                                                <div class="hr-line-dashed"></div>
                                                
                                                <div class="form-group">
													<label class="col-sm-1 control-label">活动类型<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <select class="form-control m-b" id="courseType" name="courseType">
                                                            <option value="">全部</option>
                                                            <option value="13">沙龙</option>
                                                            <option value="14">论坛</option>
                                                            <option value="15">会议</option>
                                                            <option value="16">文娱</option>
                                                            <option value="17">体育</option>
                                                            <option value="12">其他类型</option>
                                                        </select>
                                                    </div>
                                                    
                                                    <label class="col-sm-1 control-label">活动主题<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="courseName" name="courseName">
                                                    </div>
                                                    
													<label class="col-sm-1 control-label">招募对象<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="recruit" name="recruit">
                                                    </div>
                                                    
													<label class="col-sm-1 control-label">发布对象<font color="red">*</font></label>

<%--                                                     <div class="col-sm-2" style=" padding-top: 18px;">
                                                        <select name="target" class="js-example-tags form-control" multiple="multiple" id="target">
                                                            <c:forEach items="${parks}" var="park">
                                                                <option value="${park.parkName}">${park.parkName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div> --%>
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
                                                    <label class="col-sm-1 control-label">活动价格<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="price" name="price">
                                                    </div>

													<label class="col-sm-1 control-label">活动时长<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="trainTime" name="trainTime">
                                                    </div>
                                                    
                                                    <label class="col-sm-1 control-label">招募人数<font color="red">*</font></label>

                                                    <div class="col-sm-1" style="position: relative">
                                                        <input type="number" class="form-control" id="peopleNum" name="peopleNum" aria-required="true">
                                                        <div style="position: absolute; top: 10px; right: 0px;">人</div>
                                                    </div>

                                                    <label class="col-sm-2 control-label">报名截止日期<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control layer-date" id="closeTime" name="closeTime">
                                                    </div>

                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                	<label class="col-sm-1 control-label">负责人<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="header" name="header">
                                                    </div>
                                                    
                                                      <label class="col-sm-1 control-label">负责人联系方式<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="phone" name="phone">
                                                    </div>
                                                    
                                                	<label class="col-sm-1 control-label">城市<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="cityChoice" name="city">
                                                    </div>
                                                </div>
                                                
                                                
                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                <label class="col-sm-1 control-label">活动地址<font color="red">*</font></label>

                                                    <div class="col-sm-5">
                                                        <input type="text" class="form-control" id="trainingAddress" name="trainingAddress">
                                                    </div>
                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">培训内容</label>

                                                    <div class="col-md-8">
                                                        <textarea id="textWeb" name="textWeb" rows="" cols="" class="ckeditor" ></textarea>
                                                    </div>

                                                </div>
                                            </div>

                                           <div class="ibox-title">
                                                <h5>
                                                    <normal>日程信息</normal>
                                                </h5>
                                            </div>

                                            <div class="form-group">
                                                <div class="ibox-content">

                                                    <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">

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
                                                           data-url="<%=basePath%>/etopTrainSchedule/getEtopTrainScheduleList.do?courseId=${id}"
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
                                                            <th data-field="courseId" data-align="center" data-visible="false">活动id</th>
                                                            <th data-field="startDate" data-align="center">开始时间</th>
                                                            <th data-field="endDate" data-align="center">结束时间</th>
                                                            <th data-field="title" data-align="center">主题</th>
                                                            <th data-field="content" data-align="center">内容</th>
                                                            <th data-field="teacher" data-align="center">主讲人</th>
                                                            <th data-field="remark" data-align="center">备注</th>
                                                            <th data-align="center" data-formatter='formatterTrainSchedule' >操作</th>
                                                        </tr>
                                                        </thead>
                                                    </table>
                                                </div>
                                            </div>

                                            <div class="ibox-title">
                                                <h5>
                                                    <normal>嘉宾信息</normal>
                                                </h5>
                                            </div>

                                            <div class="form-group">
                                                <div class="ibox-content">

                                                    <div class="btn-group hidden-xs" role="group">

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
                                                           data-url="<%=basePath%>/etopTrainTeacher/getEtopTrainTeacherList.do?courseId=${id}"
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
                                                            <th data-field="courseId" data-align="center" data-visible="false">活动id</th>
                                                            <th data-field="name" data-align="center">姓名</th>
                                                            <th data-field="photo" data-align="center" data-formatter="formatterPhoto">讲师照片</th>
                                                            <th data-field="profile" data-align="center">简介</th>
                                                            <th data-align="center" data-formatter='formatterTrainTeacher'>操作</th>
                                                        </tr>
                                                        </thead>
                                                    </table>
                                                </div>
                                            </div>

                                            
 											<div class="ibox-title">
                                                <h5>
                                                    <normal>活动总结</normal>
                                                </h5>
                                            </div>

                                            <div class="ibox-content">
                                                <div class="form-group">

                                                    <label class="col-sm-3 control-label">活动参加人数</label>

                                                    <div class="col-sm-1" style="position: relative">
                                                        <input type="number" class="form-control" id="joinPeopleNum" name="joinPeopleNum" aria-required="true">
                                                        <div style="position: absolute; top: 10px; right: 0px;">人</div>
                                                    </div>

<!--                                                     <label class="col-sm-1 control-label">师咨评分</label>

                                                    <div class="col-sm-1" style="position: relative">
                                                        <input type="number" class="form-control" id="teacherLv" name="teacherLv" aria-required="true">
                                                        <div style="position: absolute; top: 10px; right: 0px;">分</div>
                                                    </div> -->

                                                    <label class="col-sm-1 control-label">组织评分</label>

                                                    <div class="col-sm-1" style="position: relative">
                                                        <input type="number" class="form-control" id="organizeLv" name="organizeLv" aria-required="true">
                                                        <div style="position: absolute; top: 10px; right: 0px;">分</div>
                                                    </div>

                                                    <label class="col-sm-1 control-label">内容评分</label>

                                                    <div class="col-sm-1" style="position: relative">
                                                        <input type="number" class="form-control" id="contentLv" name="contentLv" aria-required="true">
                                                        <div style="position: absolute; top: 10px; right: 0px;">分</div>
                                                    </div>
                                                </div>
                                                
                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">上传图片</label>
                                                    <div class="col-sm-3">
                                                        <input type="hidden" class=" form-control" placeholder="" id="sumsImg">
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
                                                    <label class="col-sm-2 control-label">总结</label>

                                                    <div class="col-sm-8">
<!--                                                         <input type="text" class="form-control" id="sumsContent" name="sumsContent"> -->
                                                        <textarea id="sumsContent" rows="5" name="textWeb" cols="40" class="form-control"></textarea>
                                                    </div>
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
        <jsp:include page="../shared/js.jsp"/>
        <script type="text/javascript" src="<%=basePath%>/ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
        <script src="<%=basePath%>/js/select/select2.full.min.js"></script>
        <script type="text/javascript">CKEDITOR.replace("textWeb");</script>
        <script type="text/javascript" src="<%=basePath %>/myjs/etopTraining.js"></script>
        <script type="text/javascript">
            var courseId = '${id}';
            var basePath = "<%=basePath%>";
            
            $('#closeTime').datetimepicker({
           	 minView: 1,
           	 todayBtn: "linked",
           	 format: 'yyyy-mm-dd hh:00:00',
           	 startDate : new Date() 
           	 //d, dd, D, DD, m, mm, M, MM, yy, yyyy
           });
            $("#target").select2({
                tags: true,
                separator: ",",
            });

            var standard = "<i class='fa fa-times-circle'></i> ";

            $("#courseForm").validate({
                rules: {//这里加校验规则
                	 courseName: "required",//活动主题
                     series: "required",//活动系列
                     closeTime: "required",//截止日期
                     city: "required",//城市
                     header: "required",//负责人
                     trainingAddress: "required",//活动地址
                     courseType: "required",//活动类型
                     platform: "required",//适用平台
                     post: "required",//适用岗位
                     target: "required",//发布对象
                     price: "required",//活动价格
                     peopleNum: "required",//招募人数
                     trainTime: "required",//时长
                     header: "required",//负责人
                     phone: "required",//负责人联系方式
                     trainingAddress: "required"//活动地址
                },
                messages: {//这里给对应的提示
                	courseName: standard + "请输入活动主题 !",
                    series: standard + "请输入活动系列 !",
                    courseType: standard + "请选择活动类型 !",
                    city: standard + "请输入城市 !",
                    header: standard + "请负责人 !",
                    trainingAddress: standard + "请活动地址 !",
                    closeTime: standard + "请选择截止日期 !",
                    platform: standard + "请选择适用平台 !",
                    post: standard + "请选择适用岗位 !",
                    target: standard + "请选择发布对象 !",
                    price: standard + "请输入活动价格 !",
                    peopleNum: standard + "请输入招募人数 !",
                    trainTime: standard + "请输入时长 !",
                    header: standard + "请输入负责人 !",
                    phone: standard + "请输入负责人 联系方式!",
                    trainingAddress: standard + "请输入活动地址 !"
                },
                submitHandler: function(form){
                    submit();  //去提交
                }
            })


            //新增正式公司信息保存
            function submit(){
            	
//                 if($("#trainScheduleTable").bootstrapTable('getData')=='' || $("#trainScheduleTable").bootstrapTable('getData')==null ||$("#trainScheduleTable").bootstrapTable('getData').length<=0){
             	   
//              	   swal( "请填写日程信息！", "","error");
//              	   return
//                 }
                
            	var content = CKEDITOR.instances.textWeb.getData();
                var params = {
                    "id" : '${id}',
                    "offlineType" : $("#offlineType").val(), // 活动主题
                    "courseName" : $("#courseName").val(), // 活动主题
                    "courseType" : $("#courseType").val(), // 活动类型
                    "courseStatus" : $("#courseStatus").val(), // 活动状态
                    "trainTime" : $("#trainTime").val(), // 时长
                    "recruit" : $("#recruit").val(), // 招募对象
                    "closeTime" : $("#closeTime").val(), // 截止日期
                    "city" : $("#cityChoice").val(), // 城市
                    "target" : $("#target").val(), // 发布对象
                    "choosePark" : $("#choosePark").val(), // 园区选择
                    "price" : $("#price").val(), // 培训价格
                    "peopleNum" : $("#peopleNum").val(),// 开课人数
                    "courseContent" :content, // 活动内容
                    "trainingAddress" : $("#trainingAddress").val(), // 培训地址
                    "header" : $("#header").val(), // 负责人
                    "phone" : $("#phone").val(), // 负责人联系方式
                    "courseImg" : $("#courseImg").val(), // 活动图片
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

                $.post("addEtopOfflineTraining.do",params,function(data){
                    if(data.status==10001){
                        refreshOfflineTrainingList();
                        refreshEtopTrainPlanList();
                        swal({
                            title : data.msg,
                            text: "提示：填写了培训总结该条培训状态将会变为“已完成”",
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


<%--   		   function citydata(){
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
//   		      etSreach(str_after);
  		      $("#ningbo").html(str_after);
  		    }
  		  });

  		  cityPicker.init();   --%>
        </script>
    </body>
</html>