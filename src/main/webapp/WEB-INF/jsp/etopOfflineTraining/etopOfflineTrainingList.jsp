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
        <title>园区活动列表-活动管理</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <jsp:include page="../shared/css.jsp"/>
    </head>
    <body class="gray-bg">
        <input type="hidden" id="companyId" >
    	<input id="user" value='${user}' type="hidden">
        <div class="wrapper wrapper-content animated fadeInRight">

            <!-- Panel Other -->
            <div class="ibox float-e-margins">

                <div class="ibox-content">
                    <div class="row row-lg">
                        <div class="col-sm-12">
                            <div class="row">
                                <div class="col-md-2">
                                    <label>编号</label>
                                    <input type="text" placeholder="请输入活动编号" class="form-control" id="courseId">
                                </div>

                                <div class="col-md-2  date">
								<label>活动日期</label>
	                            <input style=" width:80%; float: left;  display:block"  type="text" class="form-control" value="" id="courseTime" placeholder="请输入申请日期">
	                            <span class="input-group-addon" style=" width:20%; float: left;height:34px;  display:block"><i class="fa fa-calendar"></i></span>
	                   			 </div> 
                                <div class="col-md-2">
                                    <label>城市</label>
                                    <input type="text" placeholder="请输入活动主题" class="form-control" id="city">
                                </div>
                                <div class="col-md-2">
                                    <label>活动类型</label>
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
                               <div class="col-md-2">
                                    <label>主题</label>
                                    <input type="text" placeholder="请输入活动主题" class="form-control" id="courseName">
                                </div>
                              
                            </div>
                              
                            <div class="hr-line-dashed" style="clear: both;"></div>
                            <div class="row">
                            <div class="col-md-2">
                                    <label>招募对象</label>
                                    <input type="text" placeholder="请输入活动对象" class="form-control" id="recruit">
                                </div>
                            <div class="col-md-2">
                                    <label>状态</label>
                                    <select class="form-control m-b" id="courseStatus" name="courseStatus">
                                        <option value="">全部</option>
                                        <option value="0">报名中</option>
                                        <option value="1">进行中</option>
                                        <option value="2">已完成</option>
                                        <option value="3">已取消</option>
                                        <option value="4">未发布</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <label>负责人</label>
                                    <input type="text" placeholder="请输入负责人姓名" class="form-control" id="header">
                                </div>
								<div class="col-md-2">
                                    <label>发布园区</label>
                                    <input type="text" placeholder="请输入发布园区" class="form-control" id="searchValue">
                                </div>
                                <div class="col-md-2">
                                    <button class="btn btn-primary"
                                            onclick="getEtopOfflineProgramsList()"
                                            type="button" style="margin-top: 23px;;">搜索
                                    </button>
                                    <button class="btn btn-primary"
                                            onclick="resetCondition()"
                                            type="button" style="margin-top: 23px;;">重置
                                    </button>
                                </div>
                                
                                <div class="hr-line-dashed" style="clear: both;"></div>
                            </div>
                            
                        </div>
                        <div class="col-sm-12">
                            <!-- Example Events -->
                            <div class="example-wrap">

                                <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group"
                                     <c:if test="${readonly}">style="display: none;"</c:if> >
                                    <a class="btn btn-outline btn-default"
                                        onclick="totabs('<%=basePath%>/etopOfflineTraining/addPage.do','新建园区活动');">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                        <span>新建</span>
                                    </a>
<!--                                     <button type="button" class="btn btn-outline btn-default" onclick="deleteOffline()">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <span>删除</span>
                                    </button> -->
                                </div>

                                <table id="OfflineProgramsTable"
                                       data-mobile-responsive="true"
                                       data-toggle="table"
                                       data-url="<%=basePath%>/etopOfflineTraining/getEtopOfflineTrainingList.do"
                                       data-data-type="json"
                                       data-side-pagination="server"
                                       data-pagination="true"
                                       data-query-params="queryParams"
                                       data-page-list="[5, 10, 20, 50, 100, 200]"
                                       data-striped="true"
                                        >
                                    <thead>
                                    <tr>
<%--                                         <c:if test="${readonly eq false}">
                                            <th data-field="state" data-checkbox="true"></th>
                                        </c:if> --%>
                                        <th data-field="id" data-align="center" data-visible="false">id</th>
                                        <th data-field="courseId" data-align="center">编号</th>
                                        <th data-field="courseTime" data-align="center">日期</th>
                                        <th data-field="city" data-align="center">城市</th>
                                        <th data-field="offlineType" data-align="center" data-formatter="formatterOfflineType">类型</th>
                                        <th data-field="courseType" data-align="center" data-formatter="formatterCourseType">活动类型</th>
                                        <th data-field="courseName" data-align="center">主题</th>
                                        <th data-field="recruit" data-align="center">招募对象</th>
                                        <th data-field="courseStatus" data-align="center" data-formatter="formatterCouresStatus">状态</th>
                                        <th data-field="header" data-align="center">负责人</th>
                                        <th data-field="targetNumber" data-align="center">发布园区数</th>
                                        <th data-field="trainTime" data-align="center">时长</th>
                                        <th data-field="registrationNum" data-align="center">报名人数</th>
                                        <th data-field="overallScore" data-align="center">总体评分</th>
<!--                                         <th data-field="sumsContent" data-align="center">总结</th> -->
                                        <th data-align="center" data-formatter='formatterFun' >操作</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                            <!-- End Example Events -->
                        </div>
                    </div>
                </div>
            </div>

            <!-- End Panel Other -->
        </div>
        <jsp:include page="../shared/js.jsp"/>
        <script type="text/javascript" src="<%=basePath %>/myjs/etopTraining.js"></script>
        <script type="text/javascript">
       	$('.date').datepicker(
       			
       			{todayBtn:"linked",keyboardNavigation:!1,forceParse:!1,autoclose:!0}
       			
       		)
            var table = $("#OfflineProgramsTable");

       	
            /*查询条件*/
            function queryParams(params) {
                params.courseId = $("#courseId").val();
                params.courseName = $("#courseName").val();
                params.header = $("#header").val();
                params.trainingAddress = $("#trainingAddress").val();
                params.courseType = $("#courseType").val();
                params.platform = $("#platform").val();
                params.post = $("#post").val();
                params.courseStatus = $("#courseStatus").val();
                params.city = $("#city").val();
                params.target = $("#target").val();
                params.courseTime = $("#courseTime").val();
                params.park = $("#park").val();
                params.recruit = $("#recruit").val();
                params.searchValue = $("#searchValue").val();
                return params
            }

            function resetCondition(){
                $("#courseId").val("");
                $("#courseName").val("");
                $("#header").val("");
                $("#trainingAddress").val("");
                $("#courseType").val("");
                $("#platform").val("");
                $("#post").val("");
                $("#courseStatus").val("");
                $("#city").val("");
                $("#target").val("");
                $("#courseTime").val("");
                $("#park").val("");
                $("#recruit").val("");
                $("#searchValue").val("");
            }

            //刷新列表页
            function getEtopOfflineProgramsList(){
                table.bootstrapTable('refresh',{url:'<%=basePath%>/etopOfflineTraining/getEtopOfflineTrainingList.do'});
            }

            /*增加表格按钮**/
            function formatterFun(value,row,index){

                var getOfflineProgramsInfo = "getOfflineProgramsInfo('"+row.id+"')";
                var Unpublish = "Unpublish('"+row.id+"')";
                var delect = "delect('"+row.id+"')";
                var confirmClass = "confirmClass('"+row.id+"')";
                var finish = "finish('"+row.id+"')";
                var check = "check('"+row.id+"')";
                var checkScheduleNum = "checkScheduleNum('"+row.id+"')";
                var res = '<button class="btn btn-info" onclick="'+
                        getOfflineProgramsInfo + '" type="button" >详情</button> ' +
                        '&nbsp;&nbsp; ';
                if(row.createdBy != user.value){
               	  res = res ;
               }
                if(row.createdBy == user.value && row.courseStatus == 4 && row.offlineType == 2){
                    res = res + '<button class="btn btn-primary" <c:if test="${readonly eq true}">style="display: none;"</c:if> onclick="' +
                    	checkScheduleNum + '" type="button" >发布</button> ';
                }
                if(row.createdBy == user.value && row.courseStatus == 4 && row.offlineType == 3){
                    res = res + '<button class="btn btn-primary" <c:if test="${readonly eq true}">style="display: none;"</c:if> onclick="' +
                    checkScheduleNum + '" type="button" >发布</button> ' + '&nbsp;&nbsp; '+ 
                            '<button class="btn btn-danger" <c:if test="${readonly eq true}">style="display: none;"</c:if> onclick="' +
                            delect + '" type="button" >删除</button> ';
                }
                if(row.createdBy == user.value && row.courseStatus == 0){
                    res = res + '<button class="btn btn-danger" <c:if test="${readonly eq true}">style="display: none;"</c:if> onclick="' +
                            Unpublish + '" type="button" >撤销</button> ';
                }
                if(row.createdBy == user.value && row.courseStatus == 1 ){
                    res = res + '<button class="btn btn-danger" <c:if test="${readonly eq true}">style="display: none;"</c:if> onclick="' +
                            Unpublish + '" type="button" >撤销</button> ' + '&nbsp;&nbsp; '+
                            '<button class="btn btn-info" <c:if test="${readonly eq true}">style="display: none;"</c:if> onclick="' +
                            check + '" type="button" >完结</button> ';
                }
/*                 if(row.courseStatus == 1 && row.sumsContent == ''){
                    res = res + '<button class="btn btn-danger" <c:if test="${readonly eq true}">style="display: none;"</c:if> onclick="' +
                            Unpublish + '" type="button" >撤销</button> ';
                } */
                return res
            }

            function getOfflineProgramsInfo(id){
                $.post(totabs('<%=basePath %>/etopOfflineTraining/getOfflineTrainingInfo.do?id='+id+'&readonly='+${readonly},'编辑活动信息'))
            }
            function finish(id){
                
                $.get("unpublish.do", {
                    "id": id,
                    "type": 3
                }, function (data) {
                    if (data.status == 10001) {
                        swal({
                            title: "修改完结成功!" ,
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableRefresh('getEtopOfflineTrainingList.do');
                        });
                    } else {
                        swal({
                            title: data.msg,
                            type: "error",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableRefresh('getEtopOfflineTrainingList.do');
                        });
                    }

                })

}
            function confirmClass(id){
                
                            $.get("unpublish.do", {
                                "id": id,
                                "type": 4
                            }, function (data) {
                                if (data.status == 10001) {
                                    swal({
                                        title: "发布成功!" ,
                                        type: "success",
                                        confirmButtonText: "确定",
                                        closeOnConfirm: true
                                    }, function () {
                                        tableRefresh('getEtopOfflineTrainingList.do');
                                    });
                                } else {
                                    swal({
                                        title: data.msg,
                                        type: "error",
                                        confirmButtonText: "确定",
                                        closeOnConfirm: true
                                    }, function () {
                                        tableRefresh('getEtopOfflineTrainingList.do');
                                    });
                                }

                            })
               
            }
            function delect(id){
                swal({
                        title: "确认删除? ",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是",
                        cancelButtonText: "否",
                        closeOnConfirm: false
                    }, function () {
                        $.get("<%=basePath%>/etopOfflineTraining/deleteTrain.do?id="+id, {

                        }, function (data) {
                            if (data.status == 10001) {
                                swal({
                                    title: "成功删除! ",
                                    type: "success",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('getEtopOfflineTrainingList.do');
                                });
                            } else {
                                swal({
                                    title: data.msg,
                                    type: "error",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('getEtopOfflineTrainingList.do');
                                });
                            }

                        })
                    }
                );
            }
            
            function Unpublish(id){
                swal({
                            title: "确认撤销? ",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "是",
                            cancelButtonText: "否",
                            closeOnConfirm: false
                        }, function () {
                            $.get("unpublish.do", {
                                "id": id,
                                "type": 1
                            }, function (data) {
                                if (data.status == 10001) {
                                    swal({
                                        title: "撤销园区活动成功!" ,
                                        type: "success",
                                        confirmButtonText: "确定",
                                        closeOnConfirm: true
                                    }, function () {
                                        tableRefresh('getEtopOfflineTrainingList.do');
                                    });
                                } else {
                                    swal({
                                        title: data.msg,
                                        type: "error",
                                        confirmButtonText: "确定",
                                        closeOnConfirm: true
                                    }, function () {
                                        tableRefresh('getEtopOfflineTrainingList.do');
                                    });
                                }

                            })
                        }
                );
            }
            function finish(id){
                
                            $.get("unpublish.do", {
                                "id": id,
                                "type": 3
                            }, function (data) {
                                if (data.status == 10001) {
                                    swal({
                                        title: "修改完结成功!" ,
                                        type: "success",
                                        confirmButtonText: "确定",
                                        closeOnConfirm: true
                                    }, function () {
                                        tableRefresh('getEtopOfflineTrainingList.do');
                                    });
                                } else {
                                    swal({
                                        title: data.msg,
                                        type: "error",
                                        confirmButtonText: "确定",
                                        closeOnConfirm: true
                                    }, function () {
                                        tableRefresh('getEtopOfflineTrainingList.do');
                                    });
                                }

                            })

            }
            function checkScheduleNum(id){
                swal({
                        title: "确认发布? ",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是",
                        cancelButtonText: "否",
                        closeOnConfirm: false
                    }, function () {
                        $.get("<%=basePath%>/etopOfflineTraining/provescheduleNum.do?id="+id, {

                        }, function (data) {
                            if (data.status == 10001) {
                            	confirmClass(id)
                            } else {
                                swal({
                                    title: "请填写日程信息!",
                                    type: "error",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('getEtopOfflineTrainingList.do');
                                });
                            }

                        })
                    }
                );
            }
            function check(id){
                swal({
                        title: "确认完结? ",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是",
                        cancelButtonText: "否",
                        closeOnConfirm: false
                    }, function () {
                        $.get("<%=basePath%>/etopOfflineTraining/proveContent.do?id="+id, {

                        }, function (data) {
                            if (data.status == 10001) {
                            	finish(id)
                            } else {
                                swal({
                                    title: "请填写活动总结!",
                                    type: "error",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: true
                                }, function () {
                                    tableRefresh('getEtopOfflineTrainingList.do');
                                });
                            }

                        })
                    }
                );
            }

            
            
            function deleteOffline(){

                var selections = table.bootstrapTable('getSelections');
                if(selections.length){
                    var ids = [];
                    if (selections.length == 0)
                        return;
                    for (var i = 0; i < selections.length; i++) {
                        ids = ids + selections[i].id + ",";
                    }
                    var reg=/,$/gi;
                    ids = ids.replace(reg,"");

                    swal({
                            title: "确定删除这"+selections.length+"条活动?",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "是",
                            cancelButtonText: "否",
                            closeOnConfirm: false
                        }, function () {
                            $.get("deleteOfflineTrainingInfo.do", {
                                "ids": ids
                            }, function (data) {
                                if (data.status == 10001) {
                                    swal({
                                        title: "成功删除" + data.data + "条活动!",
                                        type: "success",
                                        confirmButtonText: "确定",
                                        closeOnConfirm: true
                                    }, function () {
                                        tableRefresh('getEtopOfflineTrainingList.do');
                                    });
                                } else {
                                    swal({
                                        title: data.msg,
                                        type: "error",
                                        confirmButtonText: "确定",
                                        closeOnConfirm: true
                                    }, function () {
                                        tableRefresh('getEtopOfflineTrainingList.do');
                                    });
                                }

                            })
                        }
                    );
                }else{
                    swal({
                        title: "请先选择活动!",
                        timer: 1000,
                        showConfirmButton: false
                    });
                }

            }

            //新增table
            function totabs(herf,msg) {
        		window.parent.addTable(herf,msg);
        	}

        </script>
    </body>
</html>