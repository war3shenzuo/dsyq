<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
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
    <title>房源管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<input type="hidden" id="buildType" >
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            
            <div class="ibox-content">
                <div class="row row-lg">
                    <label class="col-sm-12 control-label">房源管理</label>
                    <div class="hr-line-dashed"></div>
                    
                    <c:if test="${userType=='4' }">
                    <div class="row">
                        <div class="col-sm-12 ">
	                 	<div class="form-group">
	                    	<label class="col-sm-1 ">选择园区</label>
	                        <div class="col-sm-2 ">
	                            <select class="form-control" id="parkIds" name="parkIds" onchange="selectPark()">
	                             	<c:forEach items="${parks }" var="park">
	                             	    <option value="${park.id}">${park.parkName }</option>
	                             	</c:forEach>
	                        	</select>
	                        </div>
	                    </div>
	                    </div>
                    </div>
                    <div class="hr-line-dashed"></div>
                    </c:if>
                    <div class="row">
                       <div class="col-sm-12 ">
	                    <div class="col-sm-2 ">
	                        <div class="panel panel-default">
							  <div class="panel-heading">
									<h3 class="panel-title">楼.层.区
										<span style="float: right; margin-right: 10px; ">
	                                       显示全部&nbsp;<input type="checkbox"  id="isActivated" value="" onclick="checkActivate()" style="margin-top: 0px;" >
                                    	</span>
                                    </h3>
							  </div>
							  <div class="panel-body">
			                        <div id="tree"></div>
			                        <c:if test="${userType=='3' && readonly eq false}">
			                        <div class="col-sm-12" style="padding-top: 15px;">
					                    <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
					                        <a class="btn btn-outline btn-default" 
					                            onclick="openAddPage('新增楼','60%','95%','<%=basePath %>/floor/addPage.do?buildType=floor','')" >
					                            <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
					                            <span>新增楼</span>
					                        </a>
					                    </div>
					                    
					                    <div id="addChildren" style="display: none; " class="btn-group hidden-xs">
		                                    <a class="btn btn-primary"  onclick="addChildren()" id="addOp"></a>
					                    </div>
				                    </div>
				                    </c:if>
				                    <div style="color: red;">
				                  		  建房流程提示：
				                  		 <br>
				                  		 1、顺序：楼-层-区-房间
				                  		 <br>
										 2、新增房间：选中1个“区”后，点击“区详情”标签旁边的“房间列表”即可开始创建。
				                    </div>
		                     </div>
				           </div>
	                    </div>
	                    <div class="col-sm-10">
	                    <div class="row">
	                    	<div class="col-sm-10">
				                <div class="tabs-container">
				                    <ul class="nav nav-tabs">
				                        <li id="rolefunc0" class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true" id="details">详情</a>
				                        </li>
				                        <li id="rolefunc1" style="display: none;" class=""><a  data-toggle="tab" href="#tab-2" aria-expanded="false">房间列表</a>
				                         </li>
				                        
				                    </ul>
				                    <div class="tab-content">
				                        <div id="tab-1" class="tab-pane active">
				                            <div class="panel-body">
				                             	 <form id="form" method="get" class="form-horizontal">
						                        	<input type="hidden" id="id" id="form">	
						                        		
						                        	<div class="form-group">
						                                <label class="col-sm-2 control-label" id="buildTypeName">名称<span id="ts" style="color: red ;display: none" >*本楼没有添加能源公摊</span></label>
						                                <div class="col-sm-6">
						                                    <input type="text" class="form-control" placeholder="" name="buildName" id="buildName"> 
						                                </div>
					                                    <input type="hidden" value="" class="form-control" placeholder="" name="buildType" id="buildType"> 
					                                    <input type="hidden" value="" class="form-control" placeholder="" name="parentId" id="parentId"> 
						                            </div>
						                            <div class="hr-line-dashed"></div>
						                            <div class="form-group">
					                            		<label class="col-sm-2 control-label">建筑面积</label>
						                                <div class="col-sm-2"> 
						                                    <input type="text" value="${buildArea}" class="form-control" placeholder="" name="buildArea" id="buildArea"> 
						                                </div>
						                           
						                            </div>
						                            <div class="hr-line-dashed"></div>
						                            <div id="energy" style="display: none;">
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
						                                <div   id="energyEnterDay11">
							                                <div class="col-sm-2"> 
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
						                                <div style="display: none;" id="energyEnterDay22">
						                                	<div class="col-sm-2"> 
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
								                                <input type="hidden" id="dian_id">
								                                <input type="hidden" id="ranqi_id">
								                                <input type="hidden" id="shui_id">
								                                <input type="hidden" id="kongtiao_id">
								                                <div class="col-sm-2"> 
								                                     <select class="form-control" name="account" id="dian_share">
								                                        <option value="0">使用量</option>
								                                        <option value="1">面积</option>
								                                        <option value="2">不公摊</option>
								                                     </select> 
								                                </div>
								                                <label class="col-sm-3 control-label">(电)房间使用量是否出帐<font color="red">*</font> </label>
								                                <div class="col-sm-2"> 
								                                  <select class="form-control" name="account" id="dian_used">
								                                        <option value="0">不出账</option>
								                                        <option value="1">出账</option>
								                                     </select> 
								                                </div>
							                            </div>
							                                    <div class="form-group">
								                                <label class="col-sm-2 control-label">(燃气)公摊方式<font color="red">*</font> </label>
								                                <div class="col-sm-2"> 
								                                     <select class="form-control" name="account" id="ranqi_share">
								                                        <option value="0">使用量</option>
								                                        <option value="1">面积</option>
								                                        <option value="2">不公摊</option>
								                                     </select> 
								                                </div>
								                                <label class="col-sm-3 control-label">(燃气)房间使用量是否出帐<font color="red">*</font> </label>
								                                <div class="col-sm-2"> 
								                                  <select class="form-control" name="account" id="ranqi_used">
								                                        <option value="0">不出账</option>
								                                        <option value="1">出账</option>
								                                     </select> 
								                                </div>
							                            </div>
							                                    <div class="form-group">
								                                <label class="col-sm-2 control-label">(水)公摊方式<font color="red">*</font> </label>
								                                <div class="col-sm-2"> 
								                                     <select class="form-control" name="account" id="shui_share">
								                                        <option value="0">使用量</option>
								                                        <option value="1">面积</option>
								                                        <option value="2">不公摊</option>
								                                     </select> 
								                                </div>
								                                <label class="col-sm-3 control-label">(水)房间使用量是否出帐<font color="red">*</font> </label>
								                                <div class="col-sm-2"> 
								                                  <select class="form-control" name="account" id="shui_used">
								                                        <option value="0">不出账</option>
								                                        <option value="1">出账</option>
								                                     </select> 
								                                </div>
							                            </div>
							                                    <div class="form-group">
								                                <label class="col-sm-2 control-label">(空调)公摊方式<font color="red">*</font> </label>
								                                <div class="col-sm-2"> 
								                                     <select class="form-control" name="account" id="kongtiao_share">
								                                        <option value="0">使用量</option>
								                                        <option value="1">面积</option>
								                                        <option value="2">不公摊</option>
								                                     </select> 
								                                </div>
								                                <label class="col-sm-3 control-label">(空调)房间使用量是否出帐<font color="red">*</font> </label>
								                                <div class="col-sm-2"> 
								                                  <select class="form-control" name="account" id="kongtiao_used">
								                                        <option value="0">不出账</option>
								                                        <option value="1">出账</option>
								                                     </select> 
								                                </div>
							                            </div>
							                             <div class="hr-line-dashed"></div>
						                            </div>
						                            <div class="form-group">
						                                <label class="col-sm-2 control-label">备注</label>
														<div class="col-sm-5">
						                                    <textarea rows="" cols=""class=" form-control" placeholder="" id="remark"></textarea>
						                                </div>
						                            </div>
						                            <div class="hr-line-dashed"></div>
						                            <div class="form-group">
						                                <label class="col-sm-2 control-label">上传图片</label>
						                                <div class="col-sm-3">
						                                    <input type="hidden" class=" form-control" placeholder="" id="buildImg"> 
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
						                                <div class="col-sm-2" style="display: none;" id="imgTitle">
						                                	  <font color="red">此处请添加楼层示意图，将显示在web端在线招商页面</font>
						                                </div>
						                            </div>
						                            <div class="hr-line-dashed"></div>
						                            <div class="form-group">
						                           		 
						                                <label class="col-sm-2 control-label">激活状态</label>
						                                <div class="col-sm-2">
						                                    <select class="form-control" name="account" id="activated">
						                                        <option value="1">激活</option>
						                                        <option value="0">不激活</option>
						                                    </select>
						                                </div>
						                            </div>
					                                <c:if test="${userType=='3' && readonly eq false}">                       
						                            <div class="hr-line-dashed"></div>
						                                
						                            <div class="form-group">
						                                <div id="delete" style="float: left" class="col-sm-2">
						                                    <input class="btn btn-primary" type="button" value="删除" onclick="deleteFloor()">
						                                </div>
						                                <div id="updateChildren" style="display: none; float: right" class="col-sm-2">
						                                    <input class="btn btn-primary" type="submit" value="更新">
						                                </div>
						                            </div>
						                            </c:if>
						                        </form>
				                            </div>
				                        </div>
				                        <div id="tab-2" class="tab-pane">
				                           <div class="panel-body">
				                           <div class="col-sm-12">
					                        <div class="row">
						                         <div class="col-md-2">
						                           	<label>房间号</label>
					                             	<input type="text" class=" form-control" placeholder="请输入房间号" id="roomNum"> 
						                         </div>
					                             <div class="col-md-2">
						                           	<label>状态</label>
						                            <select class="form-control m-b" id="roomStatus" name="account">
						                             <option value="">全部</option>
						                             <option value="0">待租（无人）</option>
						                             <option value="1">已出租</option>
						                             <option value="2">预留中</option>
						                             <option value="3">待租（有人）</option>
						                        	</select>
						                         </div>
						                         <div class="col-md-2">
						                           	<label>状态</label>
						                            <select class="form-control" id="activated2" name="activated2">
						                             <option value="">全部</option>
						                             <option value="1">已激活</option>
						                             <option value="0">未激活</option>
						                        	</select>
						                         </div>
						                         <div class="col-md-2">
						                        	<!--<label>确认搜索</label>-->
						                        	<button class="btn btn-primary" onclick="searchRoom()"  type="button" style="margin-top: 23px;;">搜索</button>
						                         </div>
						                         <div class="hr-line-dashed" style="clear: both;"></div>
					                        </div>
					                    </div>
					                    <div class="col-sm-12">
					                        <!-- Example Events -->
					                        <div class="example-wrap">
					                                <c:if test="${userType=='3' && readonly eq false}">
					                                <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
					                                    <a class="btn btn-outline btn-default" 
					                                        onclick="addRoomPage()" >
					                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
					                                        <span>新建</span>
					                                    </a>
					                                </div>
		                                			</c:if>
						                           	<table id="table1"
						                           	    data-mobile-responsive="true"
										               data-toggle="table"
										               data-url=""
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
											                <th data-field="roomNum" data-align="center">房间号</th>
											                <th data-field="floorStatus" data-align="center" data-formatter="formatterRoomType">房间状态</th>
											                <th data-field="buildArea" data-align="center">建筑面积</th>
											                <th data-field="monthPrice" data-align="center" >月单价</th>
											                <th data-field="dayPrice" data-align="center" >日单价</th>
											                <c:choose>
											                	<c:when test="${userType=='3' && readonly eq false}">
											                		<th data-align="center" data-formatter='formatterFun' >操作</th>
											                	</c:when>
											                	<c:otherwise>
											                		<th data-align="center" data-formatter='formatterFun2' >操作</th>
											                	</c:otherwise>
											                </c:choose>
											                
											            </tr>
											            </thead>
									       		   </table>
							       		     </div>
					                        <!-- End Example Events -->
					                    </div>
					                    </div>
				                        </div>
				                        
				                        
				                    </div>
				
				
				                </div>
				            </div>
	                    </div>
	                    </div>
	                    </div>
	                </div>
                </div>
            </div>
        </div>
    
        <!-- End Panel Other -->
    </div>
    
	<jsp:include page="../shared/js.jsp"/>
    <script type="text/javascript">
    	var basePath='<%=basePath%>';
    	var imagePath='<%=ImgProperties.LOAD_PATH%>'
    	var userType='${userType}';
    	var parkId ='${parks[0].id}'
    </script>
    <script type="text/javascript" src="<%=basePath %>/myjs/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/myjs/floor.js"></script>
    <script type="text/javascript">
    var e = "<i class='fa fa-times-circle'></i> ";
	 $("#form").validate({     
		 rules: {//这里加校验规则
				buildName:"required",
				energyBillDate:"required",
				open_m:"required",
				open_d:"required",
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
				open_m:"必填项未填",
				open_d:"必填项未填",
				energyBillDate:e+"必填项未填",
				energyPaymentDay:{
					required:e+"必填项未填",
					digits:e+"必需是数字"
				},
				buildArea:{
					required:e+"必填项未填",
					digits:e+"必需是数字"
				}
			},
	    submitHandler: function(form){      
	    	updatesubmit();  //去提交   
	    }  
	})
    function searchRoom(){
    	var id=$("#id").val();
    	tableRefresh('<%=basePath%>/floor/getRoomList.do?refAreaId='+id)
    }
    function addRoomPage(){
    	var id=$("#id").val();
 		openAddRoomPage('新增房间','80%','95%','<%=basePath %>/floor/addRoomPage.do?refAreaId='+id,'<%=basePath%>/floor/getRoomList.do?refAreaId='+id)
    }
    /*查询条件*/
	function queryParams(params){
		params.roomNum = $("#roomNum").val();
		params.floorStatus = $("#roomStatus").val();
		params.activated=$("#activated2").val();
		return params
	}
	function ajaxFileUpload() {
		upload('<%=basePath%>/floor/uploadImg.do',"file",function(data){
			$("#buildImg").val(data.data.path);
			$("#buildImg2").attr("src",'<%=ImgProperties.LOAD_PATH%>'+data.data.path)
		});
    }
    </script>
</body>
</html>