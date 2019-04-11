//刷新列表数据  线上培训
function refreshetopOnlineTrainingList() {
    window.parent.refreshIframe(basePath+"/etopOnlineTraining/etopOnlineTrainingList.do");
}

//线下课程
function refreshOfflineProgramsList() {
    window.parent.refreshIframe(basePath+"/etopOfflinePrograms/etopOfflineProgramsList.do");
}

//线下培训
function refreshOfflineTrainingList() {
    window.parent.refreshIframe(basePath+"/etopOfflineTraining/etopOfflineTrainingList.do");
}

//线下计划
function refreshEtopTrainPlanList() {
    window.parent.refreshIframe(basePath+"/etopTrainPlan/etopTrainPlanList.do");
}

function formatterCourseType2(value) {
    switch (value) {
        case "0":
            return "全部";
        case "1":
            return "高管研修";
        case "2":
            return "创业辅助";
        case "3":
            return "淘系美工";
        case "4":
            return "淘系运营";
        case "5":
            return "淘系客服";
        case "6":
            return "淘系推广";
        case "7":
            return "淘系无线";
        case "8":
            return "微商";
        case "9":
            return "京东系列";
        case "10":
            return "跨境系列";
        case "11":
            return "其他平台";
        case "13":
        	return "沙龙";
        case "14":
        	return "论坛";
        case "15":
        	return "会议";
        case "16":
        	return "文娱";
        case "17":
        	return "体育";
        default:
            return "其他类型";
    }
}


function formatterPlatform2(value) {
    switch (value) {
        case "0":
            return "所有平台";
        case "1":
            return "淘宝";
        case "2":
            return "微信";
        case "3":
            return "京东";
        case "4":
            return "苏宁";
        case "5":
            return "跨境平台";
        default:
            return "其他平台";
    }
}

function formatterCourseType(value) {
    switch (value) {
        case 0:
            return "全部";
        case 1:
            return "高管研修";
        case 2:
            return "创业辅助";
        case 3:
            return "淘系美工";
        case 4:
            return "淘系运营";
        case 5:
            return "淘系客服";
        case 6:
            return "淘系推广";
        case 7:
            return "淘系无线";
        case 8:
            return "微商";
        case 9:
            return "京东系列";
        case 10:
            return "跨境系列";
        case 11:
            return "其他平台";
        case 13:
        	return "沙龙";
        case 14:
        	return "论坛";
        case 15:
        	return "会议";
        case 16:
        	return "文娱";
        case 17:
        	return "体育";
        default:
            return "其他类型";
    }
}


function formatterPlatform(value) {
    switch (value) {
        case 0:
            return "所有平台";
        case 1:
            return "淘宝";
        case 2:
            return "微信";
        case 3:
            return "京东";
        case 4:
            return "苏宁";
        case 5:
            return "跨境平台";
        default:
            return "其他平台";
    }
}

function formatterCouresStatus(value) {
    switch (value) {
        case 0:
            return "<font color='maroon'>报名中</font>";
        case 1:
            return "<font color='red'>进行中</font>";
        case 2:
            return "<font color='green'>已完成</font>";
        case 3:
            return "已撤销";
        case 3:
        	return "<font color='#dc143c'>未发布</font>";
        default:
            return "<font color='#dc143c'>未发布</font>";
    }
}

function formatterPost(value) {
    switch (value) {
        case 0:
            return "全部";
        case 1:
            return "客户";
        case 2:
            return "美工";
        case 3:
            return "推广";
        case 4:
            return "运营";
        case 5:
            return "视觉设计";
        case 6:
            return "活动策划";
        default:
            return "企业高管";
    }
}

function formatterTrain(value) {
    switch (value) {
        case 0:
            return "<font color='red'>未参加</font>";
        case 1:
            return "<font color='green'>已参加</font>";
    }
}

function formatterOfflineType(value) {
    switch (value) {
        case "0":
            return "<font color='green'>线下课程</font>";
        case "1":
        	return "<font color='maroon'>线下培训</font>";//计划内
        case "2":
            return "<font color='green'>园区活动</font>";//计划内
        case "3":
        	return "<font color='green'>园区活动</font>";//计划外
        case "4":
        	return "<font color='maroon'>线下培训</font>";//计划外
    }
}

function selectSeries(data){
    $("#series").val(data);
}

var trainScheduleTable = $("#trainScheduleTable");
var trainTeacherTable = $("#trainTeacherTable");

//开课信息
function formatterTrainSchedule(value,row,index){

    var getTrainScheduleInfo = "getTrainScheduleInfo('"+row.id+"')";
    var res = '<button class="btn btn-info" onclick="'+
        getTrainScheduleInfo + '" type="button" >详情</button> ' +
        '&nbsp;&nbsp; ';
    return res
}
function formatterTrainSchedule2(value,row,index){
	
	var getTrainScheduleInfo2 = "getTrainScheduleInfo2('"+row.id+"')";
	var res = '<button class="btn btn-info" onclick="'+
	getTrainScheduleInfo2 + '" type="button" >详情</button> ' +
	'&nbsp;&nbsp; ';
	return res
}

//讲师信息
function formatterTrainTeacher(value,row,index){

    var getTrainTeacherInfo = "getTrainTeacherInfo('"+row.id+"')";
    var res = '<button class="btn btn-info" onclick="'+
        getTrainTeacherInfo + '" type="button" >详情</button> ' +
        '&nbsp;&nbsp; ';
    return res
}
//讲师信息
function formatterTrainTeacher2(value,row,index){
	
	var getTrainTeacherInfo2 = "getTrainTeacherInfo2('"+row.id+"')";
	var res = '<button class="btn btn-info" onclick="'+
	getTrainTeacherInfo2 + '" type="button" >详情</button> ' +
	'&nbsp;&nbsp; ';
	return res
}

//新增讲师信息
function newTeacher(){
    openEtopTrainTeacherPage('新增讲师信息','70%','60%',basePath+'/etopTrainTeacher/addTrainTeacherPage.do?courseId=' + courseId,
        basePath+'/etopTrainTeacher/getEtopTrainTeacherList.do?courseId=' + courseId);
}

//新增讲师信息2
function newTeacher2(){
	openEtopTrainTeacherPage('新增嘉宾信息','70%','60%',basePath+'/etopTrainTeacher/addTrainTeacherPage2.do?courseId=' + courseId,
			basePath+'/etopTrainTeacher/getEtopTrainTeacherList.do?courseId=' + courseId);
}

//新增开课信息
function newCourse(){
    openTrainSchedulePage('新增开课信息','85%','70%',basePath+'/etopTrainSchedule/addTrainSchedulePage.do?courseId=' + courseId,
        basePath+'/etopTrainSchedule/getEtopTrainScheduleList.do?courseId=' + courseId);
}
//新增开课信息2
function newCourse2(){
	openTrainSchedulePage('新增日程信息','85%','70%',basePath+'/etopTrainSchedule/addTrainSchedulePage2.do?courseId=' + courseId,
			basePath+'/etopTrainSchedule/getEtopTrainScheduleList.do?courseId=' + courseId);
}

function getTrainScheduleInfo(id) {
    $.post(openTrainSchedulePage('编辑开课信息','85%','70%',basePath+'/etopTrainSchedule/getEtopTrainScheduleInfo.do?id=' + id,
        basePath+'/etopTrainSchedule/getEtopTrainScheduleList.do?courseId=' + courseId))
}
function getTrainScheduleInfo2(id) {
	$.post(openTrainSchedulePage('编辑日程信息','85%','70%',basePath+'/etopTrainSchedule/getEtopTrainScheduleInfo2.do?id=' + id,
			basePath+'/etopTrainSchedule/getEtopTrainScheduleList.do?courseId=' + courseId))
}

function getTrainTeacherInfo(id) {
    $.post(openEtopTrainTeacherPage('编辑讲师信息','70%','60%',basePath+'/etopTrainTeacher/getEtopTrainTeacherInfo.do?id=' + id,
        basePath+'/etopTrainTeacher/getEtopTrainTeacherList.do?courseId=' + courseId))
}
function getTrainTeacherInfo2(id) {
	$.post(openEtopTrainTeacherPage('编辑嘉宾信息','70%','60%',basePath+'/etopTrainTeacher/getEtopTrainTeacherInfo2.do?id=' + id,
			basePath+'/etopTrainTeacher/getEtopTrainTeacherList.do?courseId=' + courseId))
}

function ajaxFileUpload() {
    upload(basePath+'/etopOfflinePrograms/uploadImg.do?id=' + courseId,"file",function(data){
        $("#sumsImg").val(data.data.path);
        $("#buildImg").attr("src",'<%=ImgProperties.LOAD_PATH%>'+data.data.path)
        $("#buildImg").show();
    });
}

function deleteTeacher(){

    var selections = trainTeacherTable.bootstrapTable('getSelections');
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
                title: "确定删除这"+selections.length+"条讲师信息?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是",
                cancelButtonText: "否",
                closeOnConfirm: false
            }, function () {
                $.get(basePath+"/etopTrainTeacher/deleteEtopTrainScheduleInfo.do", {
                    "ids": ids
                }, function (data) {
                    if (data.status == 10001) {
                        swal({
                            title: "成功删除" + data.data + "条讲师信息!",
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableEtopTrainTeacherRefresh(basePath +'/etopTrainTeacher/getEtopTrainTeacherList.do?courseId=' + courseId);
                        });
                    } else {
                        swal({
                            title: data.msg,
                            type: "error",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableEtopTrainTeacherRefresh(basePath +'/etopTrainTeacher/getEtopTrainTeacherList.do?courseId=' + courseId);
                        });
                    }

                })
            }
        );
    }else{
        swal({
            title: "请先选择讲师!",
            timer: 1000,
            showConfirmButton: false
        });
    }

}

function deleteCourse2(){

    var selections = trainScheduleTable.bootstrapTable('getSelections');
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
                title: "确定删除这"+selections.length+"条日程信息?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是",
                cancelButtonText: "否",
                closeOnConfirm: false
            }, function () {
                $.get(basePath+"/etopTrainSchedule/deleteEtopTrainScheduleInfo.do", {
                    "ids": ids
                }, function (data) {
                    if (data.status == 10001) {
                        swal({
                            title: "成功删除" + data.data + "条日程信息!",
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableTrainScheduleRefresh(basePath+'/etopTrainSchedule/getEtopTrainScheduleList.do?courseId=' + courseId);
                        });
                    } else {
                        swal({
                            title: data.msg,
                            type: "error",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableTrainScheduleRefresh(basePath+'/etopTrainSchedule/getEtopTrainScheduleList.do?courseId=' + courseId);
                        });
                    }

                })
            }
        );
    }else{
        swal({
            title: "请先选择日程信息!",
            timer: 1000,
            showConfirmButton: false
        });
    }

}
function deleteTeacher2(){
	
	var selections = trainTeacherTable.bootstrapTable('getSelections');
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
			title: "确定删除这"+selections.length+"条嘉宾信息?",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "是",
			cancelButtonText: "否",
			closeOnConfirm: false
		}, function () {
			$.get(basePath+"/etopTrainTeacher/deleteEtopTrainScheduleInfo.do", {
				"ids": ids
			}, function (data) {
				if (data.status == 10001) {
					swal({
						title: "成功删除" + data.data + "条嘉宾信息!",
						type: "success",
						confirmButtonText: "确定",
						closeOnConfirm: true
					}, function () {
						tableEtopTrainTeacherRefresh(basePath +'/etopTrainTeacher/getEtopTrainTeacherList.do?courseId=' + courseId);
					});
				} else {
					swal({
						title: data.msg,
						type: "error",
						confirmButtonText: "确定",
						closeOnConfirm: true
					}, function () {
						tableEtopTrainTeacherRefresh(basePath +'/etopTrainTeacher/getEtopTrainTeacherList.do?courseId=' + courseId);
					});
				}
				
			})
		}
		);
	}else{
		swal({
			title: "请先选择嘉宾!",
			timer: 1000,
			showConfirmButton: false
		});
	}
	
}

function deleteCourse(){
	
	var selections = trainScheduleTable.bootstrapTable('getSelections');
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
			title: "确定删除这"+selections.length+"条开课信息?",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "是",
			cancelButtonText: "否",
			closeOnConfirm: false
		}, function () {
			$.get(basePath+"/etopTrainSchedule/deleteEtopTrainScheduleInfo.do", {
				"ids": ids
			}, function (data) {
				if (data.status == 10001) {
					swal({
						title: "成功删除" + data.data + "条开课信息!",
						type: "success",
						confirmButtonText: "确定",
						closeOnConfirm: true
					}, function () {
						tableTrainScheduleRefresh(basePath+'/etopTrainSchedule/getEtopTrainScheduleList.do?courseId=' + courseId);
					});
				} else {
					swal({
						title: data.msg,
						type: "error",
						confirmButtonText: "确定",
						closeOnConfirm: true
					}, function () {
						tableTrainScheduleRefresh(basePath+'/etopTrainSchedule/getEtopTrainScheduleList.do?courseId=' + courseId);
					});
				}
				
			})
		}
		);
	}else{
		swal({
			title: "请先选择开课信息!",
			timer: 1000,
			showConfirmButton: false
		});
	}
	
}

function tableEtopTrainTeacherRefresh(dataUrl){

    trainTeacherTable.bootstrapTable('refresh',{url:dataUrl});

}

function openEtopTrainTeacherPage(title,height,width,addUrl,refreshUrl){
    //iframe层
    layer.open({
        type: 2,
        title: title,
        closeBtn: "1",
        shadeClose: true,
        shade: [0],
        shift: 2,
        area: [height, width],
        content: addUrl,
        end: function(){
            if(refreshUrl!=""){
                trainTeacherTable.bootstrapTable('refresh',{url:refreshUrl});
            }
            if (typeof(tree) != "undefined") {
                tree.refresh();
            }
        }
    });
}

function tableTrainScheduleRefresh(dataUrl){

    trainScheduleTable.bootstrapTable('refresh',{url:dataUrl});

}

function openTrainSchedulePage(title,height,width,addUrl,refreshUrl){
    //iframe层
    layer.open({
        type: 2,
        title: title,
        closeBtn: "1",
        shadeClose: true,
        shade: [0],
        shift: 2,
        area: [height, width],
        content: addUrl,
        end: function(){
            if(refreshUrl!=""){
                trainScheduleTable.bootstrapTable('refresh',{url:refreshUrl});
            }
            if (typeof(tree) != "undefined") {
                tree.refresh();
            }
        }
    });
}