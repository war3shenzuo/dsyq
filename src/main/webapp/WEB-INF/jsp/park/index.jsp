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
    <title>园区设置-系统设置</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">


    <div class="wrapper wrapper-content animated fadeInRight">
        <input type="hidden" id="isadd" value="${isAdd}" />
        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            
            <div class="ibox-content">
                <div class="row row-lg">
					 <div class="col-sm-12">
                        <div class="row">
	                         <div class="col-md-2">
	                           	<label>园区名称</label>
                             	<input type="text" class=" form-control" placeholder="请输入园区名称" id="parkName"> 
	                         </div>
                             <div class="col-md-2">
	                           	<label>状态</label>
	                            <select class="form-control m-b" id="astatus" name="account">
	                             <option value="">全部</option>
	                             <option value="1">已激活</option>
	                             <option value="0">未激活</option>
	                        	</select>
	                         </div>
	                         <div class="col-md-2">
	                        	<!--<label>确认搜索</label>-->
	                        	<button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/park/getParkList.do')"  type="button" style="margin-top: 23px;;">搜索</button>
	                         </div>
	                         <div class="hr-line-dashed" style="clear: both;"></div>
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                            	
                                <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                    <a class="btn btn-outline btn-default" 
                                        onclick="addParkPage()" >
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
                                        <span>新建</span>
                                    </a>
                                </div>
                                
	                           	<table id="table1"
	                           	    data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="<%=basePath%>/park/getParkList.do"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-pagination="true"
					               data-query-params = "queryParams"
					               data-page-list="[5, 10, 20, 50, 100, 200]"
					               data-striped="true"
					              >
						            <thead>
						            <tr>
						                <th data-field="id" data-align="center" data-visible="false">编号</th>
						                <th data-field="parkName" data-align="center">园区名称</th>
						                <th data-field="address" data-align="center">园区地址</th>
						                <th data-field="contacts" data-align="center">联系人</th>
						                <th data-field="mobile" data-align="center" >联系电话</th>
						                <th data-field="createAt" data-align="center" >创建时间</th>
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
   	    function addParkPage(){
   	   	    var isAdd = $("#isadd").val();
   	   	    if(isAdd=='1'){
   	   			  openAddPage('新增园区','80%','95%','<%=basePath %>/park/addPage.do','<%=basePath%>/park/getParkList.do')
   	   	    }else{
   	   		  swal("新建园区超出限定范围", "","error");
   	   	    }
   	    }
    	/*查询条件*/
		function queryParams(params){
			params.activated = $("#astatus").val();
			params.parkName = $("#parkName").val();
			params.parkCode = $("#parkCode").val(); 
			
			return params
		}
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/park.js"></script>
</body>
</html>