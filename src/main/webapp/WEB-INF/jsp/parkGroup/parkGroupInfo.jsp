<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.etop.management.properties.ImgProperties"%>
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
						<input type="hidden" class="form-control" placeholder="" id="id"
							value="${PG.id}">
						<form method="get" class="form-horizontal">

							<div class="ibox-title">
								<h5>
									<normal>园区信息</normal>
								</h5>
							</div>
							<div class="ibox-content">
								<div class="form-group">
									<label class="col-sm-1 control-label">园区组名<font color="red">*</font></label>
									<div class="col-sm-2">
										<input type="text" <c:if test="${read !=null}">disabled</c:if> class="form-control"
											value="${PG.parkGroupName }" placeholder=""
											id="parkGroupName">
									</div>
									<label class="col-sm-1 control-label">园区组编号<font color="red">*</font></label>
									<div class="col-sm-2">
										<input <c:if test="${read !=null}">disabled</c:if>  type="text" class="form-control" placeholder=""
											value="${PG.parkGroupCode }" id="parkGroupCode">
									</div>
									<label class="col-sm-1 control-label">管理账号</label>
									<div class="col-sm-2">
										<input type="text" disabled="disabled" class="form-control"
											placeholder="" value="${PG.approval }" id="approval">
									</div>
									<label class="col-sm-1 control-label">园区数量</label>
									<div class="col-sm-2">
										<input <c:if test="${read !=null}">disabled</c:if> type="number" class="form-control valid" placeholder=""
											value="${PG.parkCount }" id="parkCount" name="parkCount"
											aria-required="true">
									</div>
								</div>
<!-- 								<div class="hr-line-dashed"></div> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label class="col-sm-1 control-label">园区组LOGO</label> -->
<!--                             		<div class="col-sm-11"  id="logoimage"> -->

<%-- 	                            			<div class="outimg"><img src="<%=ImgProperties.LOAD_PATH%>${logo}"><a href="javascript:" onclick="deleteLogo('${logo}',this)">删除</a></div> --%>
	
<!-- 	                          			<a class="btn btn-primary"  onclick="ajaxFileUpload()" style=" margin-left: 20px;">上传图片</a> -->
<!--                           			</div> -->
<%--                           			<input type="hidden" value="${PG.logo}" class=" form-control" placeholder="" id="logo">  --%>
<!-- 								</div> -->
								
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
									<label class="col-sm-1 control-label">超链接</label>
	                           		 <div class="col-sm-2">
	                               		 <input type="text" class="form-control" placeholder="" id="link"  value="${PG.link}"> 
	                           		 </div>
									<label class="col-sm-1 control-label">激活状态</label>
									<div class="col-sm-2">
										<select class="form-control" name="account" id="activated" <c:if test="${read !=null}">disabled</c:if>>
											<option value="1">激活</option>
											<option value="0">不激活</option>
										</select>
									</div>
									<label class="col-sm-1 control-label">二级域名</label>
	                           		 <div class="col-sm-2">
	                               		 <input type="text" class="form-control" placeholder="" id="sld"  value="${PG.sld}" disabled="disabled"> 
	                           		 </div>
								</div>
							</div>
							<div class="ibox-title">
								<h5>
									<normal>详细信息</normal>
								</h5>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">

								<label class="col-sm-1 control-label">园区地址</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="" 
										id="address" value="${SS.address}">
								</div>
								<label class="col-sm-1 control-label">园区类型</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="" 
										id="parkType" value="${SS.parkType}">
								</div>
								<label class="col-sm-1 control-label">开园时间</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="" 
										id="openTime" value="${SS.openTimeStr}">
								</div>
								<label class="col-sm-1 control-label">联系人</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="" 
										id="contacts" value="${SS.contacts}">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-1 control-label">所属单位</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="" 
										id="belongUnit" value="${SS.belongUnit}">
								</div>
								<label class="col-sm-1 control-label">运营单位</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="" 
										id="operateUnit" value="${SS.operateUnit}">
								</div>
								<label class="col-sm-1 control-label">园区面积</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="" 
										id="parkSize" value="${SS.parkSize}">
								</div>
								<label class="col-sm-1 control-label">园区出租率</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="" 
										id="rentalRate" value="${SS.rentalRate}"> 
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">

								<label class="col-sm-1 control-label">电话</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="" 
										id="mobile" value="${SS.mobile}">
								</div>
								<label class="col-sm-1 control-label">QQ</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="" id="qq" 
										value="${SS.qq}">
								</div>
								<label class="col-sm-1 control-label">微信</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="" 
										id="wechat" value="${SS.wechat}">
								</div>
								<label class="col-sm-1 control-label">邮箱</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="" 
										id="email" value="${SS.email}">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-1 control-label">城市</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="" 
										name="cityChoice" id="cityChoice" value="${SS.city}">
								</div>
						<div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                        <div class="hr-line-dashed"></div>
                                <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                    <a class="btn btn-outline btn-default" 
                                        onclick="openAddPage('新增园区组','80%','95%','<%=basePath %>/parkgroup/create.do?parkGroupId=${PG.id}','<%=basePath%>/parkgroup/getPresentationByParkGroupId.do?parkGroupId=${PG.id}')" >
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"  ></i>
                                        <span>新建介绍</span>
                                    </a>
                                </div>
                                
	                           	<table id="table1"
	                           	   data-mobile-responsive="true"
					               data-toggle="table"
					               data-url="<%=basePath%>/parkgroup/getPresentationByParkGroupId.do?parkGroupId=${PG.id}"
					               data-data-type="json"
					               data-side-pagination="server"
					               data-query-params = "queryParams"
					               data-striped="true"
					              >
						            <thead>
						            <tr>
		

						                <th data-field="presentation" data-align="center" data-formatter='presentationFormatter'>介绍类别</th>
						                <th data-field="title" data-align="center">标题</th>
						                <th data-field="content" data-align="center">内容</th>
						                <th data-align="center" data-formatter='formatterFun2' >操作</th>
						            </tr>
						            </thead>
				       		   </table>
                        </div>
                        <!-- End Example Events -->
                    </div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<div class="col-sm-12" style="text-align: center;">
									<a class="btn btn-primary" onclick="updatesubmit('${PG.id}')">提交</a>
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
    	var read='${read}';
    </script>
	<script type="text/javascript" src="<%=basePath%>/myjs/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>/myjs/parkgroup.js"></script>
	<script type="text/javascript">
    	var e = "<i class='fa fa-times-circle'></i> ";
		 $("#form").validate({     
			rules: {//这里加校验规则
				parkGroupName:"required",
				approval:"required",
				parkGroupCode:{
					required:e+"必填项未填",
					lowercase:["a","z"]
				}
			},
			messages: {//这里给对应的提示
				parkGroupName:e+"必填项未填",
				approval:e+"必填项未填",
				parkGroupCode:{
					required:e+"必填项未填",
					lowercase:e+"必需是小写字母"
				}
			},
		    submitHandler: function(form){      
			 addsubmit('${id}');  //去提交   
		    }  
		})
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

		 function deleteLogo(path,obj){
			 var paths =$("#logo").val().split(",")
			 var result="";
			 for(var i=0 ;i<paths.length;i++){
				 if(paths[i]!=path){
					 result+=paths[i]+",";
				 }
			 }
			 if(result!=""){
				 result=result.substring(0, result.length-1);	
			 }
			 $("#logo").val(result);
			 $(obj).parent().remove();
		 }
		 function formatterFun2(value,row,index){

				var delect = "delect('" + row.id + "')";
				var infoUrl = '<%=basePath%>/parkgroup/getExperienceInfoById.do?id='+row.id;
				var refreshUrl = '<%=basePath%>/parkgroup/getPresentationByParkGroupId.do?parkGroupId=${PG.id}';
				var showInfo = "openAddPage('园区组介绍修改','80%','95%','"+infoUrl+"','"+refreshUrl+"')";

				return '<button class="btn btn-info" onclick="'+showInfo+'"type="button" >详情</button> '+
		        '&nbsp;&nbsp; ' +
		        '<button class="btn btn-danger" onclick="' + delect + '" type="button" >删除</button> ';
			}
			
		    function delect(id){
		        swal({
		                title: "确认删除? ",
		                type: "warning",
		                showCancelButton: true,
		                confirmButtonColor: "#DD6B55",
		                confirmButtonText: "是",
		                cancelButtonText: "否",
		                closeOnConfirm: false
		            }, function () {
		                $.get("deletePresentation.do", {
		                    "id": id
		                }, function (data) {
		                    if (data.status == 10001) {
		                        swal({
		                            title: "成功删除! ",
		                            type: "success",
		                            confirmButtonText: "确定",
		                            closeOnConfirm: true
		                        }, function () {
		                            tableRefresh('getPresentationByParkGroupId.do?parkGroupId=${PG.id}');
		                        });
		                    } else {
		                        swal({
		                            title: data.msg,
		                            type: "error",
		                            confirmButtonText: "确定",
		                            closeOnConfirm: true
		                        }, function () {
		                            tableRefresh('getPresentationByParkGroupId.do?parkGroupId=${PG.id}');
		                        });
		                    }

		                })
		            }
		        );
		    }
			 

			    function presentationFormatter(value) {
					switch (value) {

					case 1:
						return "园区组介绍";
					case 2:
						return "园区组服务介绍";

					default:
						break;
					}
				}
	</script>
</body>
</html>