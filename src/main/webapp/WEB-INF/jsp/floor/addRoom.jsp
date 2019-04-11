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
    <title>添加园区-园区管理</title>
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
        <input type="hidden" class=" form-control" placeholder="" id="parkGroupId" value=${parkGroupId}> 
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                        <form id="form" method="get" class="form-horizontal">
                        <div class="row">
                           <div class="col-sm-12">
                           <div class="row">
                            <div class="col-sm-12">
                          	<div class="form-group">
                                <label class="col-sm-1">房间号<font color="red">*</font></label>
                                <div class="col-sm-2">
                                    <input type="text" value="${room.roomNum}" class=" form-control" placeholder="" name="roomNum" id="roomNum"> 
                                </div>
                                <label class="col-sm-1">层高<font color="red">*</font></label>
                                <div class="col-sm-2">
                                    <input type="text" value="${room.layerHigh}" class=" form-control" placeholder="" name="layerHigh" id="layerHigh"> 
                                </div>
                                <label class="col-sm-1">朝向</label>
                                <div class="col-sm-2">
                                    <select class="col-md-2 form-control m-b" name="account" id="orientation">
                                        <option value="东" <c:if test="${room.orientation=='东'}">selected</c:if> >东</option>
										<option value="南" <c:if test="${room.orientation=='南'}">selected</c:if> >南</option>
										<option value="西" <c:if test="${room.orientation=='西'}">selected</c:if> >西</option>
										<option value="北" <c:if test="${room.orientation=='北'}">selected</c:if> >北</option>
										<option value="东南" <c:if test="${room.orientation=='东南'}">selected</c:if> >东南</option>
										<option value="东北" <c:if test="${room.orientation=='东北'}">selected</c:if> >东北</option>
										<option value="西南" <c:if test="${room.orientation=='西南'}">selected</c:if> >西南</option>
										<option value="西北" <c:if test="${room.orientation=='西北'}">selected</c:if> >西北</option>
                                    </select>
                                </div>
                                <label class="col-sm-1">坐落</label>
                                <div class="col-sm-2">
                                    <select class="col-md-2 form-control m-b" name="account" id="located">
                                        <option value="东首" <c:if test="${room.located=='东首'}">selected</c:if> >东首</option>
										<option value="西首" <c:if test="${room.located=='西首'}">selected</c:if> >西首</option>
										<option value="近东首" <c:if test="${room.located=='近东首'}">selected</c:if> >近东首</option>
										<option value="近西首" <c:if test="${room.located=='近西首'}">selected</c:if> >近西首</option>
										<option value="居中" <c:if test="${room.located=='居中'}">selected</c:if> >居中</option>
                                    </select>
                                </div>
                            </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                             
                            <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-1">建筑面积<font color="red">*</font></label>
                                <div class="col-sm-2">
                                    <input type="text" value="${room.buildArea}" class=" form-control" placeholder="单位（平方米）" name="buildArea" id="buildArea"> 
                                </div>
                                <label class="col-sm-1">使用面积<font color="red">*</font></label>
                                <div class="col-sm-2">
                                    <input type="text" value="${room.userArea}" class=" form-control" placeholder="单位（平方米）" name="userArea" id="userArea"> 
                                </div>
                           
                                <label class="col-sm-1">月单价<font color="red">*</font></label>
                                <div class="col-sm-2">
                                    <input type="text" value="${room.monthPrice}" class=" form-control" placeholder="单位（月/元）" name="monthPrice" id="monthPrice"> 
                                </div>
                                
                                <label class="col-sm-1">日单价<font color="red">*</font></label>
                                <div class="col-sm-2">
                                    <input type="text" value="${room.dayPrice}" class=" form-control" placeholder="单位（日/元）" name="dayPrice" id="dayPrice"> 
                                </div>
                                
                            </div>
                            </div>
                            
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                            
                            <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-1">装修程度</label>
                                <div class="col-sm-2">
                                    <select class="col-md-2 form-control m-b" name="account" id="renovation">
                                        <option value="毛坯" <c:if test="${room.renovation=='毛坯'}">selected</c:if> >毛坯</option>
										<option value="简装" <c:if test="${room.renovation=='简装'}">selected</c:if> >简装</option>
										<option value="精装" <c:if test="${room.renovation=='精装'}">selected</c:if> >精装</option>
                                    </select>
                                </div>
                                
                                <label class="col-sm-1">价格显示</label>
                                 <div class="col-sm-2">
                                <input type="checkbox" name="checkbox" id="showOut" checked="" value="1" />价格显示
                                </div>
<!--                                 <input type=submit name=s1 value="提交" onclick="return check();">  -->
                            	<!-- <label class="col-sm-1">房间状态</label> -->
                                <!-- <div class="col-sm-2"> -->
                                	<input type="hidden" value="0" id="floorStatus">
                                    <%-- <select class="col-md-2 form-control m-b" name="account" id="floorStatus">
                                        <option value="0" <c:if test="${room.floorStatus=='0'}">selected</c:if> >待出租</option>
										<option value="1" <c:if test="${room.floorStatus=='1'}">selected</c:if> >已出租</option>
										<option value="2" <c:if test="${room.floorStatus=='2'}">selected</c:if> >预留中</option>
                                    </select> --%>
                                <!-- </div> -->
                                <%-- <label class="col-sm-1">入住用户</label>
                                <div class="col-sm-2">
                                    <input type="text" disabled="disabled" value="${room.companyName }" class=" form-control" placeholder="" id="companyName"> 
                                    <input type="hidden" value="${room.companyId }" id="companyId" >
                                </div> --%>
                            </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                             
                            <div class="col-sm-12">
                            <div class="form-group">
                                <label class="col-sm-1">备注</label>
                                <div class="col-sm-4">
                                    <textarea rows="" cols=""  class=" form-control" placeholder="" id="remark">${room.remark}</textarea>
                                </div>
                                
                            </div>
                            </div>
                            
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            <div class="row">
                             
                            <div class="col-sm-12">
                            <div class="form-group">
                           		<label class="col-sm-1 control-label">房间平面图1</label>
                                <div class="col-sm-2">
                                    <input type="hidden"  class=" form-control" placeholder="" id="roomImg"> 
                                    <div id="file-pretty">
                                    	<input type="file" id="file1" name="file1" class="form-control">
                                    </div>	
                                </div>
                                <div class="col-sm-1">
                                	<button class="btn btn-group" type="button" onclick="ajaxFileUpload('file1')" >上传</button>
                                </div>
                                <div class="col-sm-1">
                                	 <img id="roomImg2" alt="" src="" height="100px" width="100px">
                                </div>
                               	<label class="col-sm-1 control-label">房间平面图2</label>
                                <div class="col-sm-2">
                                    <input type="hidden" value="" class=" form-control" placeholder="" id="roomImgT"> 
                                    <div id="file-pretty">
                                    	<input type="file" id="file2" name="file2" class="form-control">
                                    </div>	
                                </div>
                                <div class="col-sm-1">
                                	<button class="btn btn-group" type="button" onclick="ajaxFileUpload('file2')" >上传</button>
                                </div>
                                <div class="col-sm-1">
                                	 <img id="roomImgT2" alt="" src="" height="100px" width="100px">
                                </div>
                            </div>
                            </div>
                            
                            </div>
                            
                           <div class="hr-line-dashed"></div>
                           <div class="col-sm-12">
	                           <div class="form-group">
	                                <div class="col-sm-12" style="text-align: center;">
	                                    <!-- <a class="btn btn-primary"  onclick="addRoom()">保存内容</a> -->
	                                    <input class="btn btn-primary" type="submit" value="提交">
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
    	var refAreaId='${refAreaId}'
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/floor.js"></script>
    <script type="text/javascript">
    function check(){ 
    	var showOut=document.getElementById("showOut"); 
//     	alert(showOut.checked); 
    	if(showOut.checked == true){
    		var showOut= "1";
    	}else{
    		var showOut= "0";
    	}
    	} 
	     var e = "<i class='fa fa-times-circle'></i> ";
		 $("#form").validate({     
			rules: {//这里加校验规则
				roomNum:"required",
				dayPrice:{
					required:true,
					number:true
				},
				monthPrice :{
					required:true,
					number:true
				},
				userArea :{
					required:true,
					number:true
				},
				buildArea :{
					required:true,
					number:true
				},
				layerHigh :"required"
			},
			messages: {//这里给对应的提示
				roomNum:e+"必填项未填",
				dayPrice:{
					required:e+"必填项未填",
					digits:e+"必需是数字"
				},
				monthPrice :{
					required:e+"必填项未填",
					digits:e+"必需是数字"
				},
				userArea :{
					required:e+"必填项未填",
					digits:e+"必需是数字"
				},
				buildArea :{
					required:e+"必填项未填",
					digits:e+"必需是数字"
				},
				layerHigh :e+"必填项未填"
			},
		    submitHandler: function(form){      
		    	addRoom();  //去提交   
		    }  
		})
	   function ajaxFileUpload(id) {
		   upload('<%=basePath%>/floor/uploadImg.do',id,function(data){
			   if(id=='file1'){
					$("#roomImg").val(data.data.path);
  					$("#roomImg2").attr("src",'<%=ImgProperties.LOAD_PATH%>'+data.data.path)
				}else{
					$("#roomImgT").val(data.data.path);
  					$("#roomImgT2").attr("src",'<%=ImgProperties.LOAD_PATH%>'+data.data.path)
				}
		   });
	   }
	
    </script>
    
    
</body>
</html>