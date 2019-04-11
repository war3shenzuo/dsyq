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
    <title>选择接收者-系统设置</title>
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
							<div class="col-sm-3">
								<label>用户类型</label>
								<select class="form-control" id="receiverType" name="receiverType" onchange="selectUser()">
									<option value="1">企业</option>
									<option value="2">员工</option>
									<option value="3">园区管理员</option>
									<option value="4">园区组管理员</option>
								</select>
							</div>
							<div class="col-sm-3" style="padding-right: 0;">
	                           	<label>收件人</label>
                             	<input type="text" class=" form-control" placeholder="" id="companyName">
						 	</div>

							<!-- <div class="col-sm-2">
                                  <label>状态</label>
                               <select class="form-control" id="astatus" name="account">
                                <option value="">全部</option>
                                <option value="1">已激活</option>
                                <option value="0">未激活</option>
                               </select>
                            </div> -->
							<div class="col-sm-1">
	                        	<button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/etopNotice/getReceiverByParkId.do?parkId=${parkId}')"  type="button" style="margin-top: 23px;;">搜索</button>
						 	</div>
							<div class="col-sm-2" id="thediv">

							</div>
                        </div>
                    </div>
                    <div style="display: block; clear: both;"><p></p></div>
                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                            	
							<table id="tableReceiver"
								data-mobile-responsive="true"
								data-toggle="table"
								data-url="<%=basePath%>/etopNotice/getReceiverByParkId.do?parkId=${parkId}"
								data-data-type="json"
								data-side-pagination="server"
								data-pagination="true"
								data-query-params = "queryReceiver"
								data-page-list="[5, 10, 20, 50, 100, 200]"
								data-striped="true">
								<thead>
								<tr>
									<th data-field="state" data-checkbox="true"></th>
									<th data-field="id" data-align="center" data-visible="false">id</th>
									<th data-field="companyName" data-align="center">收件人</th>
									<th data-field="userType" data-align="center" data-formatter="formatterReceiverType" >用户类型</th>
								</tr>
								</thead>
							</table>

							<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
								<a class="btn btn-outline btn-default"
									onclick="bindReceiver()" >
									<i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
									<span>确定</span>
								</a>
							</div>
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
   		var tableReceiver = $('#tableReceiver');
		function tableRefresh(dataUrl){
			tableReceiver.bootstrapTable('refresh',{url:dataUrl});
		}
    	/*查询条件*/
		function queryReceiver(params){
			params.receiverType = $("#receiverType").val();
			params.companyName = $("#companyName").val();
			return params
		}

		/*用户类型*/
		function formatterReceiverType(value){
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

		function selectUser(dataUrl){
			dataUrl = '<%=basePath%>/etopNotice/getReceiverByParkId.do?parkId=${parkId}';
			tableReceiver.bootstrapTable('refresh',{url:dataUrl});
		}

		//选择员工添加全选按钮
		$("#receiverType").on("change", function(){
			if($(this).val() == 2){
				$("#thediv").html("<button class='btn btn-danger' id='buttonId' onclick='checkAll()'  type='button' style='margin-top: 23px;'>全选</button>");
			}else{
				$("#buttonId").remove();
			}
		})

		function checkAll(){
			swal({
					title: "选择成功! ",
					type: "success",
					confirmButtonText: "确定",
					closeOnConfirm: false
				},
				function(){
					window.parent.document.getElementById("allIds").value = "allIds";
					window.parent.document.getElementById("receiver").value = "已全选";
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index);
				}
			)
		}

		//选择值传递给父页面
		function bindReceiver(){

			var selections = tableReceiver.bootstrapTable('getSelections');
			if(selections.length){
				var ids = [];
				var receiver = [];
				if (selections.length == 0)
					return;
				for (var i = 0; i < selections.length; i++) {
					ids = ids + selections[i].id + ",";
					receiver = receiver + selections[i].companyName + ",";
				}
				var reg=/,$/gi;
				ids = ids.replace(reg,"");
				receiver = receiver.replace(reg,"");
				swal({
						title: "选择成功！",
						type: "success",
						confirmButtonText: "确定",
						closeOnConfirm: false
					},
					function(){
						window.parent.document.getElementById("ids").value = ids;
						window.parent.document.getElementById("receiver").value = receiver;
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index);
					}
				)
			}else{
				swal({
					title: "请先选择收件人!",
					timer: 1000,
					showConfirmButton: false
				});
			}
		}

		function tableSelectReceiver(dataUrl){

			tableReceiver.bootstrapTable('refresh',{url:dataUrl});

		}
    </script>
</body>
</html>