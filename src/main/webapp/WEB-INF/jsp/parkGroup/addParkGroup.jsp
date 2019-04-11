<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.etop.management.properties.ImgProperties"%>
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
<title>园区列表-园区管理</title>
<meta name="keywords" content="">
<meta name="description" content="">
<jsp:include page="../shared/css.jsp" />
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
						<form id="form" method="get" class="form-horizontal" >
							<div class="ibox-title">
								<h5>
									<normal>园区信息</normal>
								</h5>
							</div>
							<div class="ibox-content">
								<div class="form-group">
									<label class="col-sm-1 control-label">园区组名<font color="red">*</font></label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="parkGroupName" name="parkGroupName">
									</div>
									<label class="col-sm-1 control-label">园区组编号<font color="red">*</font></label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="parkGroupCode" name="parkGroupCode">
									</div>
									<label class="col-sm-1 control-label">管理账号<font color="red">*</font></label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="approval" name="approval">
									</div>
									<label class="col-sm-1 control-label">园区数量</label>
									<div class="col-sm-2">
										<input type="number" class="form-control valid" placeholder=""
											id="parkCount" name="parkCount" aria-required="true">
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-sm-1 control-label">园区组简介</label>
									<div class="col-sm-5">
										<textarea rows="" cols="" class=" form-control" placeholder=""
											name="parkGroupDescribe" id="parkGroupDescribe"></textarea>
									</div>
									<label class="col-sm-1 control-label">园区组介绍</label>
									<div class="col-sm-5">
										<textarea rows="" cols="" class=" form-control" placeholder=""
											name="introduce" id="introduce"></textarea>
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-sm-1 control-label">园区组LOGO</label>
									<div class="col-sm-3">
										<input type="hidden" value="${PG.logo}"
											class=" form-control" placeholder="" id="logo">
										<div id="file-pretty">
											<input type="file" id="file" name="file" class="form-control">
										</div>
									</div>
									<div class="col-sm-1">
										<button class="btn btn-group" type="button"
											onclick="ajaxFileUpload()">上传</button>
									</div>
									<div class="col-sm-1">
										<img style="width: 100px; height: 100px; 
												id="logo2"
												src="<%=ImgProperties.LOAD_PATH %>${PG.logo}" />
									</div>
								</div>	
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-sm-1 control-label">园区组图片</label>
                            		<div class="col-sm-11"  id="images">
	                            		<c:forEach items="${PG.imgList}" var="img">
	                            			<div class="outimg"><img src="<%=ImgProperties.LOAD_PATH%>${img}"><a href="javascript:" onclick="deleteParkImgPath('${img}',this)">删除</a></div>
	                          			</c:forEach>
	                          			<a class="btn btn-primary"  onclick="openUploadImgPage()" style=" margin-left: 20px;">上传图片</a>
                          			</div>
                          			<input type="hidden" value="${PG.parkGroupImg}" class=" form-control" placeholder="" id="parkImg"> 
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-sm-1 control-label">超链接<font color="red">*</font></label>
	                               		 <div class="col-sm-2">
	                                   		 <input type="text" class="form-control" placeholder="" name="link" id="link" > 
	                               		 </div>
									<label class="col-sm-1 control-label">激活状态</label>
									<div class="col-sm-2">
										<select class="form-control" name="account" id="activated">
											<option value="1">激活</option>
											<option value="0">不激活</option>
										</select>
									</div>
									<label class="col-sm-1 control-label">二级域名<font color="red">*</font></label>
	                               		 <div class="col-sm-2">
	                                   		 <input type="text" class="form-control" placeholder="" name="sld" id="sld" > 
	                               		 </div>
								</div>
							</div>
							<div class="ibox-title">
								<h5>
									<normal>详细信息</normal>
								</h5>
							</div>
							<div class="ibox-content">
								<div class="form-group">
									<label class="col-sm-1 control-label">园区地址</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="address" value="${settledInfo.address}">
									</div>
									<label class="col-sm-1 control-label">园区类型</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="parkType" value="${settledInfo.parkType}">
									</div>
									<label class="col-sm-1 control-label">开园时间</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="openTime" value="${settledInfo.openTime}">
									</div>
									<label class="col-sm-1 control-label">联系人</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="contacts" value="${settledInfo.contacts}">
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-sm-1 control-label">所属单位</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="belongUnit" value="${settledInfo.belongUnit}">
									</div>
									<label class="col-sm-1 control-label">运营单位</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="operateUnit" value="${settledInfo.operateUnit}">
									</div>
									<label class="col-sm-1 control-label">园区面积</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="parkSize" value="${settledInfo.parkSize}">
									</div>
									<label class="col-sm-1 control-label">园区出租率</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="rentalRate" value="${settledInfo.rentalRate}">
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">

									<label class="col-sm-1 control-label">电话</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="mobile" value="${settledInfo.mobile}">
									</div>
									<label class="col-sm-1 control-label">QQ</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder="" id="qq"
											value="${settledInfo.qq}">
									</div>
									<label class="col-sm-1 control-label">微信</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="wechat" value="${settledInfo.wechat}">
									</div>
									<label class="col-sm-1 control-label">邮箱</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											id="email" value="${settledInfo.email}">
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-sm-1 control-label">城市</label>
									<div class="col-sm-2">
										<input type="text" class="form-control" placeholder=""
											name="cityChoice" id="cityChoice" value="${settledInfo.city}">
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<div class="col-sm-12" style="text-align: center;">
										<!-- <a class="btn btn-primary"  onclick="addsubmit()">保存内容</a> -->
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
	<jsp:include page="../shared/js.jsp" />
	<script type="text/javascript">
    	var basePath='<%=basePath%>';
    </script>
	<script type="text/javascript" src="<%=basePath%>/myjs/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>/myjs/parkgroup.js"></script>
	<script type="text/javascript">
	 	var e = "<i class='fa fa-times-circle'></i> ";
		$("#form").validate({     
			rules: {//这里加校验规则
				parkGroupName:"required",
				parkGroupCode:"required",
				approval:"required",
				sld:"required",
				link:"required",
				parkGroupCode:{
					required:e+"必填项未填",
					lowercase:["a","z"]
				}
			},
			messages: {//这里给对应的提示
				parkGroupName:e+"必填项未填",
				parkGroupCode:e+"必填项未填",
				approval:e+"必填项未填",
				sld:e+"必填项未填",
				link:e+"必填项未填",
				parkGroupCode:{
					required:e+"必填项未填",
					lowercase:e+"必需是数字和小写字母"
				}
			},
		    submitHandler: function(form){      
			 addsubmit('${id}');  //去提交   
		    }  
		})
<%-- 		function ajaxFileUpload() {
		    upload('<%=basePath%>/parkgroup/uploadImg.do?id=${id}',"file",function(data){
				 	$("#parkGroupImg").val(data.data.path);
					$("#parkGroupImg2").attr("src",'<%=ImgProperties.LOAD_PATH%>'+ data.data.path)
					$("#parkGroupImg2").show();
			});
		} --%>
    	function ajaxFileUpload() {
			 upload('<%=basePath%>/parkgroup/uploadImg.do?id=${PG.id}',"file",function(data){
				 	$("#logo").val(data.data.path);
					$("#logo2").attr("src",'<%=ImgProperties.LOAD_PATH%>'+ data.data.path)
					$("#logo2").show();
			});
		}
		 /**绑定用户*/
		 function openUploadImgPage(){
		 	openSelectPage('上传园区图片','80%','80%',basePath+'/park/openUploadPage.do?parkId=${parkId}',null);
		 } 
		 
		 function deleteParkImgPath(path,obj){
			 var paths =$("#parkImg").val().split(",")
			 var result="";
			 for(var i=0 ;i<paths.length;i++){
				 if(paths[i]!=path){
					 result+=paths[i]+",";
				 }
			 }
			 if(result!=""){
				 result=result.substring(0, result.length-1);	
			 }
			 $("#parkImg").val(result);
			 $(obj).parent().remove();
		 }
		
	</script>
</body>
</html>