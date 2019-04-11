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
    <title>新闻列表-平台管理</title>
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
	                           	<label>新闻标题</label>
                             	<input type="text" class=" form-control m-b" placeholder="请输入新闻标题" id="title">
	                         </div>
                             <div class="col-md-2">
	                           	<label>状态</label>
	                            <select class="form-control" id="astatus" name="account">
	                             <option value="">全部</option>
	                             <option value="1">已激活</option>
	                             <option value="0">未激活</option>
	                        	</select>
	                         </div>
	                         <div class="col-md-2">
	                        	<!--<label>确认搜索</label>-->
	                        	<button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/press/getPressList.do')"  type="button" style="margin-top: 23px;;">搜索</button>
	                         </div>
	                         <div class="hr-line-dashed" style="clear: both;"></div>
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                            	
							<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group" <c:if test="${readonly eq true}">style="display: none;"</c:if>>
								<a class="btn btn-outline btn-default"
									onclick="openAddPage('新增新闻','1200px','800px','<%=basePath %>/press/addPage.do','<%=basePath%>/press/getPressList.do')" >
									<i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
									<span>新建</span>
								</a>
							</div>

							<table id="table1"
								data-mobile-responsive="true"
								data-toggle="table"
								data-url="<%=basePath%>/press/getPressList.do"
								data-data-type="json"
								data-side-pagination="server"
								data-pagination="true"
								data-query-params = "queryParams"
								data-page-list="[5, 10, 20, 50, 100, 200]"
								   data-striped="true">
								<thead>
								<tr>
									<th data-field="state" data-align="true" data-visible="false">状态</th>
									<th data-field="id" data-align="center" data-visible="false">编号</th>
									<th data-field="title" data-align="center">新闻标题</th>
									<th data-field="createdBy" data-align="center" >新闻作者</th>
									<th data-field="newsType" data-align="center" >新闻类别</th>
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
		var table = $('#table1');

		/*查询条件*/
		function queryParams(params){
			params.state = $("#astatus").val();
			params.title=$("#title").val();
			params.totalType=$("#totalType").val();
			params.newsType=$("#newsType").val();
			return params
		}

		/*增加表格按钮**/
		function formatterFun(value,row,index){

			var infoUrl = '<%=basePath%>/press/getPressInfo.do?id='+row.id+'&readonly=' + ${readonly};
			var refreshUrl = '<%=basePath%>/press/getPressList.do';
			var showInfo = "openAddPage('用户详情','1100px','700px','"+infoUrl+"','"+refreshUrl+"')";
			var btnS="";
			if(row.state=="1"){
				btnS="btn-danger";
			}else{
				btnS="btn-primary";
			}
			var status = activatedStatus(row.state);
			var stop = "stopGroup('"+row.id+"'"+","+"'"+row.state+"')";
			return '<button class="btn btn-info" onclick="'+
					showInfo+
					'"   type="button" >详情</button> '+
					'&nbsp;&nbsp; '+
					'<button class=" btn ' + btnS + ' " <c:if test="${readonly eq true}">style="display: none;"</c:if> onclick="'+
					stop + '"   type="button" >'+ status +'</button>';
		}

		/**激活不激活操作*/
		function stopGroup( id, activated){
			if(activated == '1'){
				activated = '0';
			}else{
				activated = '1';
			}
			var param = {"id":id, "state":activated};
			var status = stateStatus(activated);
			$.post(basePath+"/press/activeOrCloseNews.do",param,function(data){
				if(data.status==10001){
					swal(   status+"成功! ", "","success");
					tableRefresh(basePath+'/press/getPressList.do');
				}else{
					swal(   status+"失败! ", "","error");
				}

			});
		}

		function stateStatus(value){
			if(value =='0'){
				return "停用";
			}else if(value=='1'){
				return "激活";
			}else{
				return "未知";
			}
		}

		function tableRefresh(dataUrl){

			table.bootstrapTable('refresh',{url:dataUrl});

		}

	</script>

</body>
</html>