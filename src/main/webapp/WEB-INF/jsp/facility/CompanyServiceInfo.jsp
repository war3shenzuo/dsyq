<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.etop.management.properties.ImgProperties"%>
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
    <title>服务详情</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
	<style>
	.outimg{ width:80px; height:80px; margin-left:10px; float: left; }
	.outimg img{ width:80px; height:80px; }
	.outimg a{ display: block; text-align: center;height: 22px; line-height: 22px; }
	</style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
 
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                    	<form method="get" class="form-horizontal">
                <div class="row">
                <div class="col-sm-12">
           		<div class="ibox-title">
					<h5>
						<normal>操作记录</normal>
					</h5>
				</div>	
		
				<div class="ibox-content">
						<div class="form-group">
							<label class="col-sm-1 control-label">操作记录</label>
							<div class="col-sm-11">
								<textarea class="form-control" readonly="readonly" rows=5>${service.changes}</textarea>
							</div>
						</div>
				</div>	
				</div>
				</div> 
				<div class="ibox-title">
								<h5>
									<normal>申请信息</normal>
								</h5>
							</div>
				<div class="ibox-content">
                            <div class="row">
	                            <div class="col-sm-12">
	                            <div class="form-group">
	                            	<label class="col-sm-1 control-label">类别</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${facility.facilityType}" class=" form-control" placeholder="" id="facilityType" readonly="readonly"> 
	                                </div>
	                            	<label class="col-sm-1 control-label">项目名称</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${facility.facilityName}" class=" form-control" placeholder="" id="facilityName" readonly="readonly"> 
	                                </div>
	                                
	                                </div>
	                               <div class="form-group"> 
	                            	<label class="col-sm-1 control-label">预约起始</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${facility.beginTime}" class=" form-control" placeholder="" id="beginTime" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">预约结束</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${facility.endTime}" class=" form-control" placeholder="" id="endTime" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">是否过期</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${facility.isExpiration}" class=" form-control" placeholder="" id="isExpiration" readonly="readonly"> 
	                                </div>
	                            </div>
	                             <div class="form-group"> 
	                            	<label class="col-sm-1 control-label">数量</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${facility.duration}小时" class=" form-control" placeholder="" id="beginTime" readonly="readonly"> 
	                                </div>
	                                 <label class="col-sm-1 control-label">单价</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${facility.facilityPrice}" class=" form-control" placeholder="" id="facilityPrice" readonly="readonly"> 
	                                </div>
	                                <label class="col-sm-1 control-label">总金额</label>
	                                <div class="col-sm-2">
	                                    <input type="text" value="${facility.totalPrices}" class=" form-control" placeholder="" id="endTime" readonly="readonly"> 
	                                </div>
	                                
	                            </div>
	                            </div>
                            </div>
                            </div>
                        <div class="row">
                           <div class="col-sm-12">
                           <div class="ibox-title">
								<h5>
									<normal>服务基本信息</normal>
								</h5>
						   </div>
						   <div class="ibox-content">
                           <div class="row">
                           
                            <div class="col-sm-12">
                          	<div class="form-group">
                                <label class="col-sm-1 control-label">服务编号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.serviceNo}"  readonly="readonly" class=" form-control" placeholder="" id="serviceNo"> 
                                </div>
                                <label class="col-sm-1 control-label">服务类型</label>
                                <div class="col-sm-2">
                                    <input type="text" value="预约服务"  readonly="readonly" class=" form-control" placeholder="" id="serviceTypeName"> 
                                </div>
                                <label class="col-sm-1 control-label">服务状态</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.serviceStatus}"  readonly="readonly"  class=" form-control" placeholder="" id="serviceStatus"> 
                                </div>
                                <c:if test="${service.serviceStatus == 102}">
			
								<div class="col-sm-3" style="text-align: center; ">
									<a  id="sub" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submit()">撤销</a>
<!-- 									<a  id="subtwo" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submittwo()">完工</a> -->
							   </div>
							   </c:if>
                            </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                             
                            <div class="col-sm-12">
                            <div class="form-group">
                            	<label class="col-sm-1 control-label">申请公司</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.companyName}"   readonly="readonly" class=" form-control" placeholder="" id="companyName"> 
                                </div>
                                <label class="col-sm-1 control-label">申请时间</label>
                                <div class="col-sm-2">
                                    <input type="text" value="<fmt:formatDate value="${service.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"   readonly="readonly" class=" form-control" placeholder="" id="applyTime"> 
                                </div>
                                <label class="col-sm-1 control-label">完成时间</label>
                                <div class="col-sm-2">
                                    <input type="text" value="<fmt:formatDate value="${service.completeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"   readonly="readonly" class=" form-control" placeholder="" id="completeTime"> 
                                </div>
                                
                            </div>
                            </div>
                            
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            
                            <div class="col-sm-12">
                            <div class="form-group">
                            	<label class="col-sm-1 control-label">楼号</label>
                                <div class="col-sm-2">
                                    <input type="text"value="${service.buildingNo}"   readonly="readonly"  class=" form-control" placeholder="" id="buildingNo"> 
                                </div>
                                <label class="col-sm-1 control-label">层号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.storey}"   readonly="readonly" class=" form-control" placeholder="" id="storey"> 
                                </div>
                                <label class="col-sm-1 control-label">区号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.zoneNo }"   readonly="readonly" class=" form-control" placeholder=""  id="zoneNo"> 
                                </div>
                                <label class="col-sm-1 control-label">房号</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.roomNo }"  readonly="readonly"  class=" form-control" placeholder=""  id="roomNo"> 
                                </div>
                                
                                
                            </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                             
                            <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">申请人</label>
                                <div class="col-sm-2">
                                    <input type="text"value="${service.applicant}"    readonly="readonly" class=" form-control" placeholder="" id="applicant"> 
                                </div>
                                <label class="col-sm-1 control-label">联系方式</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.applicantPhone}"  readonly="readonly"  class=" form-control" placeholder="" id="applicantPhone"> 
                                </div>
                                <label class="col-sm-1 control-label">部门</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.applicantDepartment }"  readonly="readonly"  class=" form-control" placeholder=""  id="applicantDepartment"> 
                                </div>
                                <label class="col-sm-1 control-label">职位</label>
                                <div class="col-sm-2">
                                    <input type="text" value="${service.applicantPosition }"   readonly="readonly" class=" form-control" placeholder=""  id="applicantPosition"> 
                                </div>
                                
                            </div>
                            </div>
                            
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            
                            <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">补充信息</label>
                                <div class="col-sm-4">
                                    <textarea rows="" cols=""   readonly="readonly"  class=" form-control" placeholder="" id="description">${service.description}</textarea>
                                </div>
                                
                            </div>
                            </div>
                           
                            </div>
                            
        
                           </div>                      

							</div>
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../shared/js.jsp"/>
   	<script type="text/javascript">
	var basePath='<%=basePath%>';
	$(document).ready(function () {
		$("#serviceStatus").val(formatterServiceStatusType($("#serviceStatus").val(),'',''));
	});
	
    	function bill(serviceId){
    		$.post(basePath+"/serviceFacility/addBill.do",{"serviceId":serviceId},function(data){
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
    		    	swal( "操作失败！", "","error");
    		    }

    		});
    	}
    	
   	 function submit(){
	     swal({
	             title: "确认撤销该申请? ",
	             type: "warning",
	             showCancelButton: true,
	             confirmButtonColor: "#DD6B55",
	             confirmButtonText: "是",
	             cancelButtonText: "否",
	             closeOnConfirm: false
	         }, function () {
	       
			var serviceId = $("#serviceId").val();

			var param={
			     	"serviceId":serviceId

			}
			$.post('<%=basePath%>/etopService/updateStatus.do?status=201',param,function(data){
				
				
				if (data.status == 10001) {
					swal({
						title : "撤销成功！",
						type : "success",
						confirmButtonText : "确定",
						closeOnConfirm : true
					}, function() {
						window.location.reload();
					});

				} else {
					swal({
						title : "撤销失败！",
						text : data.msg,
						type : "error",
						confirmButtonText : "确定",
						closeOnConfirm : true
					});
				}});
		}
	     );
	 }
	 
		function submittwo(){
	        swal({
	                title: "确认完工? ",
	                type: "warning",
	                showCancelButton: true,
	                confirmButtonColor: "#DD6B55",
	                confirmButtonText: "是",
	                cancelButtonText: "否",
	                closeOnConfirm: false
	            }, function () {
	          
	    			var serviceId = $("#serviceId").val();

	    			var param={
	    			     	"serviceId":serviceId


			}
	    			$.post('<%=basePath%>/etopService/updateStatus.do?status=204',param,function(data){
				
				
				if (data.status == 10001) {
					swal({
						title : "已确认成功！",
						type : "success",
						confirmButtonText : "确定",
						closeOnConfirm : true
					}, function() {
						window.location.reload();
					});

				} else {
					swal({
						title : "失败！",
						text : data.msg,
						type : "error",
						confirmButtonText : "确定",
						closeOnConfirm : true
					});
				}
			
		   });
		}
	        );
	    }
    </script>
</body>
</html>