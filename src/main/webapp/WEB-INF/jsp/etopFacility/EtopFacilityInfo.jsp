<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>设施详情</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<input id="id" value='${facility.id}' type="hidden">
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
 
    	<div class="row">
            <div class="col-xs-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                    	
                        <form id="form" method="get" class="form-horizontal">
                       
                           <div class="col-md-12">
                            <div class="row">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">类别<font color="red">*</font></label>
                                <div class="col-sm-7">
                                    <input type="text" value="${facility.facilityType}" class="form-control" placeholder="" name="facilityType" id="facilityType"> 
                                </div>
                            </div>
                           
                            
                            <div class="hr-line-dashed"></div>
                          
                            <div class="form-group">
                                <label class="col-sm-3 control-label">设施名称<font color="red">*</font></label>
                                <div class="col-sm-7">
                                    <input type="text" value="${facility.facilityName}" class="form-control" placeholder="" name="facilityName" id="facilityName"> 
                                </div>
                            </div>
                            
                           
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">设施价格<font color="red">*</font></label>
                                <div class="col-sm-7">
                                    <input type="text" value="${facility.facilityPrice}" class="form-control" placeholder="" name="facilityPrice" id="facilityPrice" > 
                                </div>
                            </div>
<%--                             <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">开发起始时间<font color="red">*</font></label>
                                <div class="col-sm-7  clockpicker">
                                    <input type="text" class="form-control" value="${facility.startTime}" placeholder="" name="startTime" id="startTime"> 
                                </div>
								
                            </div>

                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">开放结束时间<font color="red">*</font></label>
                                <div class="col-sm-7  clockpicker">
                                    <input type="text" class="form-control"value="${facility.endTime}" placeholder="" name="endTime" id="endTime"> 
                                </div> --%>
                            
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">开放起始时间<font color="red">*</font></label>
                                <div class="col-sm-7">
                                   <input type="text" class="form-control timeRange" value="${facility.startTime}"  style="width:400px" placeholder="" name="startTime" id="startTime"/>
                                </div>
								
                            </div>

                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">开放结束时间<font color="red">*</font></label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control  timeRange" value="${facility.endTime}" style="width:400px" placeholder="" name="endTime" id="endTime"> 
                                </div>
                            
                            </div>
                            
                             <div class="hr-line-dashed"></div>
                          <div class="form-group">
								<label class="col-sm-3 control-label">说明</label>
								<div class="col-sm-7">
									<input type="text" value="${facility.description}" class="form-control"  id="description">
								</div>
						  </div>
						  
						<div class="hr-line-dashed"></div>
                          
							<div class="form-group">
								<label class="col-sm-3 control-label">备注</label>
								<div class="col-sm-7">
									<input type="text" value="${facility.remark}" class="form-control"  id="remark">
								</div>
							
						 </div>
                            
                            <div class="hr-line-dashed"></div>
                           </div>
                           <c:if test="${read==0}">
	                           <div class="row"> 
	                           <div class="col-md-12">
		                           <div class="form-group">
		                                <div class="col-md-12" style="text-align: center;">
		                                   <!--  <a class="btn btn-primary"  onclick="addsubmit()">保存内容</a> -->
		                                    <input class="btn btn-primary" type="submit" value="提交">
		                                </div>
		                            </div>
	                           </div>
	                           </div>
                           </c:if>
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
    	$('.clockpicker').clockpicker({
    	    placement: 'top',
    	    align: 'left',
    	    donetext: '确定'
    	});
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/facility.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/timeRange.js"></script>
    <script type="text/javascript">
    var e = "<i class='fa fa-times-circle'></i> ";
	 $("#form").validate({     
		rules: {//这里加校验规则
			facilityName:"required",
			facilityType:"required",
			facilityPrice:{
				required:true,
				number:true
			}
			
		},
		messages: {//这里给对应的提示
			goodName:e+"必填项未填",
			facilityType:e+"必填项未填",
			facilityPrice:{
				required:e+"必填项未填",
				number:e+"必需是数字"
			}
			
		},
	    submitHandler: function(form){      
		    updatesubmit();  //去提交   
	    }  
	})
    </script>
</body>
</html>