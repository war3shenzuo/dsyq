<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.etop.management.properties.ImgProperties" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>简历列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<input type="hidden" id="parkId">
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            
            <div class="ibox-content">
                <div class="row row-lg">
                	<div class="col-sm-12">
                		<div class="col-sm-6">
	                       <div class="panel panel-default" id="change"  style=" display:none; clear: both;">
							  <div class="panel-heading">
									<h3 class="panel-title">新增</h3>
							  </div>
							  <div class="panel-body">
		                          <form id="form" method="get" class="form-horizontal">
		                            <input type="hidden" id="id">
			                        <div class="row">
			                           <div class="col-md-12">
			                           <div class="row">
			                            <div class="form-group">
			                                <label class="col-md-2 control-label">姓名</label>
			                                <div class="col-md-9">
			                                    <input type="text" class="form-control" placeholder="" name="name" id="name"> 
			                                </div>
			                            </div>
			                            </div>
			                            
			                            <div class="hr-line-dashed"></div>
			                            <div class="row">
			                            <div class="form-group">
			                                <label class="col-md-2 control-label">手机</label>
			                                <div class="col-md-9">
			                                    <input type="text" class="form-control" placeholder="" name="mobile" id="mobile"> 
			                                </div>
			                            </div>
			                            </div>
			                            <div class="hr-line-dashed"></div>
			                            <div class="row">
			                            <div class="form-group">
			                                <label class="col-md-2 control-label">邮箱</label>
			                                <div class="col-md-9">
			                                    <input type="text" class="form-control" placeholder="" name="email" id="email"> 
			                                </div>
			                            </div>
			                            </div>
			                            
			                       
			                            </div>

				                           
			                          
			                           </div>
			                        </form>
			                  </div>
				           </div>
	                    </div>
	                    <div class="col-sm-12">
							<div class="col-sm-12">
		                        <div class="row">
		                             <div class="col-md-2">
			                           	<label>姓名</label>
		                             	<input type="text" class=" form-control m-b" placeholder="请输入姓名" id="name2"> 
			                         </div>
			                         
			                         <div class="col-md-2">
			                           	<label>手机</label>
		                             	<input type="text" class=" form-control m-b" placeholder="请输入手机号" id="mobile2"> 
			                         </div>
		                             
			                         <div class="col-md-6">
			                        	<!--<label>确认搜索</label>-->
			                        	<button class="btn btn-primary" onclick="search()"  type="button" style="margin-top: 23px;;">搜索</button>
			                        	<c:if test="${status==101}">
			                        	<button class="btn btn-primary" type="button" style="margin-top: 23px;" id="tog" >新增简历</button>
			                        	<button class="btn btn-primary" onclick="updateStatus()" style="margin-top: 23px;"  type="button" >提交简历</button>
			                        	</c:if>
			                         </div>
			                         <div class="hr-line-dashed" style="clear: both;"></div>
		                        </div>
		                    </div>
		                    <div class="col-sm-12">
		                        <!-- Example Events -->
		                        <div class="example-wrap">
			                           	<table id="table1"
			                           	   data-mobile-responsive="true"
							               data-toggle="table"
							               data-url='<%=basePath%>/resume/getEtopResumeList.do?serviceId=${serviceId}'
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
								                <th data-field="name" data-align="center">姓名</th>
								                <th data-field="mobile" data-align="center" >手机号</th>
								                <th data-field="email" data-align="center" >邮箱</th>
								                <th data-field="fileUrl" data-align="center" data-formatter="formatterLoad">下载</th>
								                <th data-field="isAffirm" data-align="center" data-formatter="formatterAffirm" >确认状态</th>
								                <th data-align="center" data-formatter='formatterRemumeFun' >操作</th>
								            </tr>
								            </thead>
						       		   </table>
		                        </div>
		                        <!-- End Example Events -->
		                    </div>
		                    <!-- <div class="col-sm-12">
		                     <button class="btn btn-primary" onclick="updateStatus()" type="button" style="margin-top: 23px;">提交简历</button>
		                    </div> -->
	                    </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Panel Other -->
    </div>
	<jsp:include page="../shared/js.jsp"/>
	<script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/remume.js"></script>
    <script type="text/javascript">
    
    $(document).ready(function(){

   	 $("#tog").click(function(){
   		  $("#change").toggle("normal");
   		  });
 	});
    	function togs(){
    		 $("#change").toggle("normal");
    	}
	    /*查询条件*/
		function queryParams(params){
			params.name=$("#name2").val();
			params.mobile=$("#mobile2").val();
			return params
		}
		function search(){
    		tableRefresh('<%=basePath%>/resume/getEtopResumeList.do?serviceId=${serviceId}');
    	}
		function ajaxFileUpload(fileId) {
			upload('<%=basePath%>/resume/upload.do',fileId,function(data){
				$("#uploadTitle").show();
				$("#fileUrl").val(data.data.path);
			});
	    }
		function formatterLoad(value,row,index){
			
			return "<a download='w3logo' href='<%=ImgProperties.LOAD_PATH%>"+value+"'>下载</a>";
		}
		 function updateStatus(id,status){

				
				swal({
			        title: "是否确认? ",
			        type: "warning",
			        showCancelButton: true,
			        confirmButtonColor: "#DD6B55",
			        confirmButtonText: "是",
			        cancelButtonText: "否",
			        closeOnConfirm: false
			    }, function () {

			    	var param ={"serviceId":serviceId,"status":"102"}
				$.post(basePath+"/etopService/updateStatus.do",param,function(data){
					if(data.status==10001){
						swal( "操作成功！", "","success");
						window.location.reload();
				    }else{
				    	swal( "操作失败！", "","error");
				    }

				});
			    });
			} 
    </script>
    <script type="text/javascript">
    	var basePath='<%=basePath%>';
    	var serviceId='${serviceId}';
    	var status='${status}';
    </script>
    
</body>
</html>