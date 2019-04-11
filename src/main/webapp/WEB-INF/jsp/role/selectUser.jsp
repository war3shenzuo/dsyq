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
    <title>帐号管理-系统设置</title>
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
                             <div class="col-sm-3" style="padding-right: 0;">
	                           	<label>用户名称</label>
                             	<input type="text" class=" form-control" placeholder="" id="userName"> 
	                         </div>
	                         <!-- <div class="col-sm-2">
	                           	<label>用户类型</label>
                             	<select class="form-control" id="userType" name="userType">
	                             <option value="">全部</option>
	                             <option value="1">企业</option>
                                 <option value="2">个人</option>
                                 <option value="3">园区系统管理员</option>
                                 <option value="4">园区组系统管理员</option>
                                 <option value="5">系统管理员</option>
	                        	</select>
	                         </div>
                             <div class="col-sm-2">
	                           	<label>状态</label>
	                            <select class="form-control" id="astatus" name="account">
	                             <option value="">全部</option>
	                             <option value="1">已激活</option>
	                             <option value="0">未激活</option>
	                        	</select>
	                         </div> -->
	                         <div class="col-sm-2">
	                        	<!--<label>确认搜索</label>-->
	                        	<button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/user/getUserList.do?parkId=${parkId}')"  type="button" style="margin-top: 23px;;">搜索</button>
	                         </div>
                        </div>
                    </div>
                    <div style="display: block; clear: both;"><p></p></div>
                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                            	
	                           	<table id="table1"
	                           	    data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="<%=basePath%>/user/getUserList.do?parkId=${parkId}&activated=1"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-pagination="true"
					               data-query-params = "queryParams"
					               data-page-list="[5, 10, 20, 50, 100, 200]"
					              >
						            <thead>
						            <tr>
						                <th data-field="id" data-align="center" data-visible="false">编号</th>
						                <th data-align="center" data-formatter="formatterCheckbox"> <input type="checkbox" class="i-checks" /></th>
						                <th data-field="userName" data-align="center">用户账号</th>
						                <th data-field="name" data-align="center">用户账号</th>
						                <th data-field="userType" data-align="center" data-formatter="formatterUserType" >用户类型</th>
						                <!-- <th data-field="companyId" data-align="center" >所属公司</th> -->
						                <th data-field="parkId" data-align="center" data-visible="false">所属园区</th>
						                <!-- <th data-field="mobile" data-align="center" >电话</th>
						                <th data-field="email" data-align="center" >邮箱</th> -->
						            </tr>
						            </thead>
				       		   </table>
				       		   
				       		   <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                    <a class="btn btn-outline btn-default" 
                                        onclick="bindUser()" >
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
   		var table = $('#table1');
		function tableRefresh(dataUrl){
			table.bootstrapTable('refresh',{url:dataUrl});
		}
    	/*查询条件*/
		function queryParams(params){
			params.userName = $("#userName").val();
			
			return params
		}
		/*复选框*/
		function formatterCheckbox(value,row,index){
			return '<input name="myCheckbox" value="'+row.id+'" type="checkbox" class="i-checks" />'
		}
		//选择值传递给父页面
		function bindUser(){
			var obj=document.getElementsByName('myCheckbox'); //选择所有name="'test'"的对象，返回数组 
			var s=''; 
			for(var i=0; i<obj.length; i++){ 
				if(obj[i].checked) s+=obj[i].value+','; //如果选中，将value添加到变量s中 
			} 
			if(s!=''){
				s=s.substring(0, s.length-1);
				var rid=window.parent.document.getElementById("id").value;
				var param={"userId":s,"roleId":rid};
				$.post("<%=basePath %>/role/addUserForRole.do",param,function(data){
					if(data.status==10001){
						swal({   title: "保存成功！",   
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
					    swal(   data.msg, "","error");
				   }
				});
			}
		}
    	

    </script>
</body>
</html>