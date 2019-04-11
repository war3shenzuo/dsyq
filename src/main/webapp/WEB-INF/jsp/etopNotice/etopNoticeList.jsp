<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>通知列表-平台管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            
            <div class="ibox-content">
                <div class="row row-lg">
					 <div class="col-sm-12">
                        <div class="row">
                             <div class="col-md-2">
	                           	<label>通知标题</label>
                             	<input type="text" class=" form-control m-b" placeholder="请输入通知标题" id="title">
	                         </div>
                             <div class="col-md-2">
	                           	<label>通知状态</label>
	                            <select class="form-control" id="status" name="state">
	                             <option value="">全部</option>
	                             <option value="1">已激活</option>
	                             <option value="0">未激活</option>
	                        	</select>
	                         </div>
	                         <div class="col-md-2">
	                        	<!--<label>确认搜索</label>-->
	                        	<button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/etopNotice/getEtopNoticeList.do')"  type="button" style="margin-top: 23px;;">搜索</button>
	                         </div>
	                         <div class="hr-line-dashed" style="clear: both;"></div>
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                            	
                                <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group" <c:if test="${readonly eq true}">style="display: none;"</c:if>>
                                    <a class="btn btn-outline btn-default" 
                                        onclick="openAddPage('新增普通通知','1200px','800px','<%=basePath %>/etopNotice/addPage.do','<%=basePath%>/etopNotice/getEtopNoticeList.do')" >
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
                                        <span>新建普通通知</span>
                                    </a>
                                    <a class="btn btn-outline btn-default" 
                                        onclick="openAddPage('新增投票通知','1200px','800px','<%=basePath %>/etopNotice/addVote.do','<%=basePath%>/etopNotice/getEtopNoticeList.do')" >
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
                                        <span>新建投票通知</span>
                                    </a>
									<button type="button" class="btn btn-outline btn-default" onclick="deleteNotice()">
										<i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <span>删除</span>
									</button>
                                </div>
                                
	                           	<table id="tableNotice"
									data-mobile-responsive="true"
									data-toggle="table"
									data-url="<%=basePath%>/etopNotice/getEtopNoticeList.do"
									data-data-type="json"
									data-side-pagination="server"
									data-pagination="true"
									data-query-params = "queryParams"
									data-page-list="[5, 10, 20, 50, 100, 200]"
								   	data-striped="true">
						            <thead>
						            <tr>
										<c:if test="${readonly eq false}">
											<th data-field="state" data-checkbox="true"></th>
										</c:if>
						                <th data-field="id" data-align="center" data-visible="false">编号</th>
						                <th data-field="title" data-align="center">通知标题</th>
						                <th data-field="userName" data-align="center" >收件人</th>
										<th data-field="userType" data-align="center" data-formatter="formatterUserType">接收者类型</th>
										<th data-field="noticeType" data-align="center" >通知类别</th>
						                <th data-field="createdAt" data-align="center" >发布时间</th>
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
	<script type="text/javascript">
		var basePath='<%=basePath%>';
	</script>
	<script type="text/javascript">
		var table = $('#tableNotice');

		/*查询条件*/
		function queryParams(params){
			params.state = $("#status").val();
			params.title=$("#title").val();
			return params
		}

		/*增加表格按钮**/
		function formatterFun(value,row,index){

			var infoUrl = '<%=basePath%>/etopNotice/getNoticeInfo.do?noticeId='+row.noticeId+'&readonly=' + ${readonly};
			var refreshUrl = '<%=basePath%>/etopNotice/getEtopNoticeList.do';
			var showInfo = "openAddPage('通知详情','1100px','700px','"+infoUrl+"','"+refreshUrl+"')";
			return '<button class="btn btn-info" onclick="'+
					showInfo+
					'" type="button" >详情</button> '
		}

		/*用户类型*/
		function formatterUserType(value){
			if(value =='1'){
				return "企业";
			}else if(value=='2'){
				return "员工";
			}else if(value=='3'){
				return "园区管理员";
			}else if(value=='4'){
				return "园区组管理员";
			}else{
				return "未知";
			}
		}

		/**添加*/
		function addsubmit(){
			var content = CKEDITOR.instances.textWeb.getData();
			var param={
				"title" : $("#title").val(),
				"state" : $("#state").val(),
				"content" : content
			}

			$.post(basePath+"/press/addPress.do",param,function(data){
				if(data.status==10001){
					swal({
								title: "保存成功！",
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

		function deleteNotice(){

			var selections = table.bootstrapTable('getSelections');
			if(selections.length){
				var ids = [];
				if (selections.length == 0)
					return;
				for (var i = 0; i < selections.length; i++) {
					ids = ids + selections[i].noticeId + ",";
				}
				var reg=/,$/gi;
				ids = ids.replace(reg,"");

				swal({
							title: "确定删除这"+selections.length+"条通知?",
							type: "warning",
							showCancelButton: true,
							confirmButtonColor: "#DD6B55",
							confirmButtonText: "是",
							cancelButtonText: "否",
							closeOnConfirm: false
						}, function () {
							$.get("deleteEtopNoticeInfo.do", {
								"ids": ids
							}, function (data) {
								if (data.status == 10001) {
									swal({
										title: "成功删除" + data.data + "条通知！",
										type: "success",
										confirmButtonText: "确定",
										closeOnConfirm: true
									}, function () {
										tableRefresh('getEtopNoticeList.do');
									});
								} else {
									swal({
										title: data.msg,
										type: "error",
										confirmButtonText: "确定",
										closeOnConfirm: true
									}, function () {
										tableRefresh('getEtopNoticeList.do');
									});
								}

							})
						}
				);
			}else{
				swal({
					title: "请先选择通知!",
					timer: 1000,
					showConfirmButton: false
				});
			}

		}

		function tableRefresh(dataUrl){

			table.bootstrapTable('refresh',{url:dataUrl});

		}

	</script>

</body>
</html>