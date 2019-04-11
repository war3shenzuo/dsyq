<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.etop.management.properties.ImgProperties" %>
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
    <title>园区列表-园区管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
 		<input type="hidden" class="form-control" disabled="disabled" value="${parentId}" id="parentId"> 
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                        <form id="form" method="get" class="form-horizontal">
                        <div class="row">
                        	<input type="hidden" id="id">	
                        	<div class="form-group">
                                <label class="col-sm-2 control-label">
                                <c:if test="${buildType=='floor'}">
                                	楼名称
                                </c:if>
                                <c:if test="${buildType=='storey'}">
                                  	 层名称
                                </c:if>
                                <c:if test="${buildType=='area'}">
                                	区名称
                                </c:if>
                                <font color="red">*</font></label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" placeholder="" name="buildName" id="buildName"> 
                                </div>
                                <div style="display: none;">
	                                <label class="col-sm-2 control-label">楼类型<font color="red">*</font> </label>
	                                <div class="col-sm-3"> 
	                                    <input type="text" value="${buildType}" class="form-control" placeholder="" name="buildType" id="buildType"> 
	                                </div>
	                                <label class="col-sm-2 control-label">父类Id<font color="red">*</font> </label>
	                                <div class="col-sm-3"> 
	                                    <input type="text" value="${parentId}" class="form-control" placeholder="" name="parentId" id="parentId"> 
	                                </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                            		<label class="col-sm-2 control-label">建筑面积<font color="red">*</font> </label>
	                                <div class="col-sm-4"> 
	                                    <input type="text" value="${buildArea}" class="form-control" placeholder="" name="buildArea" id="buildArea"> 
	                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <c:if test="${buildType=='floor'}">
	                            <div class="form-group">
	                            	<label class="col-sm-2 control-label">能源缴费天数</label>
	                                <div class="col-sm-2"> 
	                                	 <input type="text" value="" class="form-control" placeholder="" name="energyPaymentDay" id="energyPaymentDay"> 
	                                </div>
	                               <div class="col-sm-8 form-inline" > 

														
															<label class=" control-label">能源出账日：</label>
															
															
															<label class="control-label">每</label>
															
															
																<input type="number" class="form-control" id="open_m"	name="open_m"placeholder="月间隔" style="width:100px;">
															
															<label class=" control-label">个月的</label>
															
															
															
																<select class="form-control" id="open_d" name="open_d" placeholder="日期">
																<option value="1">1</option>
							                                        <option value="2">2</option>
							                                        <option value="3">3</option>
							                                        <option value="4">4</option>
							                                        <option value="5">5</option>
							                                        <option value="6">6</option>
							                                        <option value="7">7</option>
							                                        <option value="8">8</option>
							                                        <option value="9">9</option>
							                                        <option value="10">10</option>
							                                        <option value="11">11</option>
							                                        <option value="12">12</option>
							                                        <option value="13">13</option>
							                                        <option value="14">14</option>
							                                        <option value="15">15</option>
							                                        <option value="16">16</option>
							                                        <option value="17">17</option>
							                                        <option value="18">18</option>
							                                        <option value="19">19</option>
							                                        <option value="20">20</option>
							                                        <option value="21">21</option>
							                                        <option value="22">22</option>
							                                        <option value="23">23</option>
							                                        <option value="24">24</option>
							                                        <option value="25">25</option>
							                                        <option value="26">26</option>
							                                        <option value="27">27</option>
							                                        <option value="28">28</option>
							                                        <option value="28">29</option>
							                                        <option value="28">30</option>
							                                        <option value="28">31</option>
																</select>
															
															<label class=" control-label">日出帐</label>
													</div>
	                             </div>
	                              <div class="hr-line-dashed"></div>
	                              <div class="form-group">  
	                            	<label class="col-sm-2 control-label">能源录入日期</label>
	                                <div class="col-sm-2"> 
	                                	 <select class="form-control" name="energyEnterType" id="energyEnterType" onchange="selectEnterType()">
	                                        <option value="0">每月</option>
	                                        <option value="1">每周</option>
	                                     </select>
	                                </div>
	                                <div  class="col-sm-3"  id="energyEnterDay11">
		                                <div class="col-sm-8"> 
		                                	<select class="form-control" name="energyEnterDay1" id="energyEnterDay1">
		                                        <option value="1">1号</option>
		                                        <option value="2">2号</option>
		                                        <option value="3">3号</option>
		                                        <option value="4">4号</option>
		                                        <option value="5">5号</option>
		                                        <option value="6">6号</option>
		                                        <option value="7">7号</option>
		                                        <option value="8">8号</option>
		                                        <option value="9">9号</option>
		                                        <option value="10">10号</option>
		                                        <option value="11">11号</option>
		                                        <option value="12">12号</option>
		                                        <option value="13">13号</option>
		                                        <option value="14">14号</option>
		                                        <option value="15">15号</option>
		                                        <option value="16">16号</option>
		                                        <option value="17">17号</option>
		                                        <option value="18">18号</option>
		                                        <option value="19">19号</option>
		                                        <option value="20">20号</option>
		                                        <option value="21">21号</option>
		                                        <option value="22">22号</option>
		                                        <option value="23">23号</option>
		                                        <option value="24">24号</option>
		                                        <option value="25">25号</option>
		                                        <option value="26">26号</option>
		                                        <option value="27">27号</option>
		                                        <option value="28">28号</option>
		                                    </select>
		                                </div>   
	                                </div> 
	                                <div style="display: none;" class="col-sm-3"  id="energyEnterDay22">
	                                	<div class="col-sm-8"> 
	                                    <select class="form-control" name="energyEnterDay2" id="energyEnterDay2">
	                                        <option value="1">星期一</option>
	                                        <option value="2">星期二</option>
	                                        <option value="3">星期三</option>
	                                        <option value="4">星期四</option>
	                                        <option value="5">星期五</option>
	                                        <option value="6">星期六</option>
	                                        <option value="7">星期日</option>
	                                    </select>
	                                    </div>
	                                </div>
	                                
	                            </div>
	                            <div class="hr-line-dashed"></div>
                           
                            <div class="form-group">
	                                <label class="col-sm-2 control-label">(电)公摊方式<font color="red">*</font> </label>
	                                <div class="col-sm-2"> 
	                                     <select class="form-control" name="account" id="dian_share">
	                                     	<option value="2">不公摊</option>
	                                        <option value="0">使用量</option>
	                                        <option value="1">面积</option>
	                                        
	                                     </select> 
	                                </div>
	                                <label class="col-sm-3 control-label">(电)房间使用量是否出帐<font color="red">*</font> </label>
	                                <div class="col-sm-2"> 
	                                  <select class="form-control" name="account" id="dian_used">
	                                        <option value="0">不出账</option>
	                                        <option value="1">使用</option>
	                                     </select> 
	                                </div>
                            </div>
                                    <div class="form-group">
	                                <label class="col-sm-2 control-label">(燃气)公摊方式<font color="red">*</font> </label>
	                                <div class="col-sm-2"> 
	                                     <select class="form-control" name="account" id="ranqi_share">
	                                     	<option value="2">不公摊</option>
	                                        <option value="0">使用量</option>
	                                        <option value="1">面积</option>
	                                     </select> 
	                                </div>
	                                <label class="col-sm-3 control-label">(燃气)房间使用量是否出帐<font color="red">*</font> </label>
	                                <div class="col-sm-2"> 
	                                  <select class="form-control" name="account" id="ranqi_used">
	                                  		<option value="0">不出账</option>
	                                        <option value="1">使用</option>
	                                     </select> 
	                                </div>
                            </div>
                                    <div class="form-group">
	                                <label class="col-sm-2 control-label">(水)公摊方式<font color="red">*</font> </label>
	                                <div class="col-sm-2"> 
	                                     <select class="form-control" name="account" id="shui_share">
	                                     	<option value="2">不公摊</option>
	                                        <option value="0">使用量</option>
	                                        <option value="1">面积</option>
	                                     </select> 
	                                </div>
	                                <label class="col-sm-3 control-label">(水)房间使用量是否出帐<font color="red">*</font> </label>
	                                <div class="col-sm-2"> 
	                                  <select class="form-control" name="account" id="shui_used">
	                                        <option value="0">不出账</option>
	                                        <option value="1">使用</option>
	                                     </select> 
	                                </div>
                            </div>
                                    <div class="form-group">
	                                <label class="col-sm-2 control-label">(空调)公摊方式<font color="red">*</font> </label>
	                                <div class="col-sm-2"> 
	                                     <select class="form-control" name="account" id="kongtiao_share">
	                                     	<option value="2">不公摊</option>
	                                        <option value="0">使用量</option>
	                                        <option value="1">面积</option>
	                                     </select> 
	                                </div>
	                                <label class="col-sm-3 control-label">(空调)房间使用量是否出帐<font color="red">*</font> </label>
	                                <div class="col-sm-2"> 
	                                  <select class="form-control" name="account" id="kongtiao_used">
	                                        <option value="0">不出账</option>
	                                        <option value="1">使用</option>
	                                     </select> 
	                                </div>
                            </div>
                             <div class="hr-line-dashed"></div>
                              </c:if>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
								<div class="col-sm-10">
                                    <textarea rows="" cols=""class=" form-control" placeholder="" id="remark"></textarea>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                            	 <label class="col-sm-2 control-label">上传图片</label>
                                <div class="col-sm-4">
                                    <input type="hidden" class=" form-control" placeholder=""  name="buildImg" id="buildImg"> 
                                    <div id="file-pretty">
                                    	<input type="file" id="file" name="file" class="form-control">
                                    </div>	
                                </div>
                                <div class="col-sm-1">
                                	<button class="btn btn-group" type="button" onclick="ajaxFileUpload()" >上传</button>
                                </div>
                                <div class="col-sm-2">
                                	 <img id="buildImg2" alt="" src="" height="100px" width="100px">
                                </div>
                                <div class="col-sm-2">
                                	  <font color="red">此处请添加楼层示意图，将显示在web端在线招商页面</font>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                           		 
                                <label class="col-sm-2 control-label">激活状态</label>
                                <div class="col-sm-10">
                                    <select class="form-control" name="account" id="activated">
                                        <option value="1">激活</option>
                                        <option value="0">不激活</option>
                                    </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-6 col-sm-offset-2">
                                    <input class="btn btn-primary" type="submit" value="提交">
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
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/floor.js"></script>
    <script type="text/javascript">
    
 	 var e = "<i class='fa fa-times-circle'></i> ";
	 $("#form").validate({     
		rules: {//这里加校验规则
			buildName:"required",
			energyBillDate:"required",
			energyPaymentDay:{
				required:true,
				number:true
			},
			buildArea:{
				required:true,
				number:true
			}
		},
		messages: {//这里给对应的提示
			buildName:e+"必填项未填",
			energyBillDate:e+"必填项未填",
			open_m:"required",
			open_d:"required",
			energyPaymentDay:{
				required:e+"必填项未填",
				digits:e+"必需是数字"
			},
			buildArea:{
				open_m:"必填项未填",
				open_d:"必填项未填",
				required:e+"必填项未填",
				digits:e+"必需是数字"
			}
		},
	    submitHandler: function(form){     
	    	if($("#buildImg").val()=="" && '${buildType}'=='storey'){
	    		alert("图片必需上传");
	    	}else{
	    		 addsubmit();  //去提交   
	    	}
	    }  
	});
	
	function ajaxFileUpload() {
		upload('<%=basePath%>/floor/uploadImg.do',"file",function(data){
			$("#buildImg").val(data.data.path);
			$("#buildImg2").attr("src",'<%=ImgProperties.LOAD_PATH%>'+data.data.path)
		});
    }
    </script>
</body>
</html>