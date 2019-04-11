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
    <title>商品列表</title>
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
			                           	<label>商品大类</label>
		                             	<input type="text" class=" form-control m-b" placeholder="请输入商品大类" id="category"> 
			                         </div>
			                         
                             <div class="col-md-2">
	                           	<label>商品名称</label>
                             	<input type="text" class=" form-control m-b" placeholder="请输入商品名称" id="goodName"> 
	                         </div>

	                         <div class="col-md-2">
	                        	<!--<label>确认搜索</label>-->
	                        	<button class="btn btn-primary" onclick="tableRefresh('<%=basePath%>/goods/getEtopGoodsList.do')"  type="button" style="margin-top: 23px;;">搜索</button>
	                         </div>
	                         <div class="hr-line-dashed" style="clear: both;"></div>
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                            	
                                <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                    <a class="btn btn-outline btn-default" 
                                        onclick="openAddPage('新增商品','50%','70%','<%=basePath %>/goods/addPage.do','<%=basePath%>/goods/getEtopGoodsList.do')" >
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
                                        <span>新建</span>
                                    </a>
                                </div>
                                
	                           	<table id="table1"
	                           	    data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="<%=basePath%>/goods/getEtopGoodsList.do"
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
						                <th data-field="category" data-align="center">商品大类</th>
						                <th data-field="goodName" data-align="center">商品名称</th>
						                <th data-field="unit" data-align="center" >单位</th>
						                <th data-field="unitPrice" data-align="center" >单价</th>
						                <th data-field="createdStr" data-align="center" >创建时间</th>
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
    	/*查询条件*/
		function queryParams(params){
			params.goodName=$("#goodName").val();
			params.category=$("#category").val();
			return params
		}
    </script>
    <script type="text/javascript">
    	var basePath='<%=basePath%>';
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/goods.js"></script>
</body>
</html>