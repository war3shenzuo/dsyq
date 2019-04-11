<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<meta charset="UTF-8">
		<jsp:include page="../shared/css.jsp" />
		<title>个人信息详情</title>
		<link href="<%=basePath%>/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/accountManageDetail.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
	
	</head>
	<body>

		<div class="wrap">
			<p class="headtit">个人信息管理</p>
			<div class="firsttab">
				<p class="ht">员工基础信息：</p>
				<div class="onefloor">
					<label for="name">姓名</label>
					<input  type="text" id="employeesName"  value="${EmployeesUserInfo.employeesName}">
					
					<label for="telephone">手机号码</label>
					<input type="text" id="mobile" value="${EmployeesUserInfo.mobile}">
					
					<label for="address">户口所在地</label>
					<input  type="text" id="address"  value="${EmployeesUserInfo.address}">
				</div>
				<div class="second">
					<label for="sex">性别</label>
<%-- 					<select id="employeesSex"  style="width:200px;height:30px">
	
					    <option>${employeesSexType}</option>
						<option value="1">男</option>
						<option value="2">女</option>
					</select> --%>
                   <select id="employeesSex"  style="width:200px;height:30px">
                       <option value="1" 
                       		<c:if test="${EmployeesUserInfo.employeesSex == 1}"> selected</c:if> > &nbsp;&nbsp;&nbsp;男 
                       
                       </option>

                       <option value="2" <c:if test="${EmployeesUserInfo.employeesSex == 2}"> selected</c:if>>  &nbsp;&nbsp;&nbsp;女 </option>

                   </select>
					<label for="email">邮箱</label>
					<input type="email" id="email" value="${EmployeesUserInfo.email}">
					
					<label for="IDCard">身份证号码</label>
					<input type="text" id="card" value="${EmployeesUserInfo.card}">
				</div>
				<div>
					
					<label for="birthday" class="time">出生日期</label>
					<input style="width:200px;height:30px" class="laydate-icon form-control layer-date timeinput" 
					   id="birth"  
					  value='<fmt:formatDate value="${EmployeesUserInfo.birth}" pattern="yyyy-MM-dd"/>'>
							
					<label for="wei">微信</label>
					<input type="text" id="wechat" value="${EmployeesUserInfo.wechat}">
							
					<label for="other">其他联系方式</label>
					<input type="text" id="otherContact" value="${EmployeesUserInfo.otherContact}">
<!-- 					<label for="password">密码</label><input type="password" id="password"> -->
<!-- 					<a href="#" class="change">修改</a> -->
				</div>

			</div>
			
			<div class="firsttab">
				<p class="ht">就职信息：</p>
				<div class="onefloor">
					<label for="companyName">公司名称</label><input type="text" id="companyName"  value="${EmployeesUserInfo.companyName}">
					<label for="depart">部门</label><input type="text" id="department" value="${EmployeesUserInfo.department}">
					<label for="position">岗位</label><input type="text" id="jobs" value="${EmployeesUserInfo.jobs}">
				</div>
				
				<div class="second">
					<label for="rutime" class="time">入职时间</label>
					<input  style="width:200px;height:30px;margin-left:-5px;"  id="hiredate" class="laydate-icon form-control layer-date timeinput" 
					 value='<fmt:formatDate value="${EmployeesUserInfo.hiredate}" pattern="yyyy-MM-dd"/>'>						

				<label for="begin_time" style="letter-spacing: 0px;text-indent:-10px;margin-left:7px;margin-right:12px;" >合同起始时间</label>
				<input style="width:200px;height:30px;margin-left:5px;" id="startTime" class="laydate-icon form-control layer-date timeinput"  
				placeholder="从" value='<fmt:formatDate value="${EmployeesUserInfo.startTime}" pattern="yyyy-MM-dd"/>'>
				<label for="begin_time" style="letter-spacing: 0px;text-indent:-10px;margin-left:16px;margin-right:12px;">合同终止时间</label>
				<input  style="width:200px;height:30px;margin-left:-2px;" class="laydate-icon form-control layer-date timeinput"   
				 placeholder="到"  id="endTime" value='<fmt:formatDate value="${EmployeesUserInfo.endTime}" pattern="yyyy-MM-dd"/>'>
				</div>

			</div>
			
			<div class="firsttab">
				<p class="ht">教育背景：</p>
				<div class="onefloor">
					<label for="graduatedSchool">毕业院校</label><input type="text" id="graduate"  value="${EmployeesUserInfo.graduate}">
					<label for="start">开始日期</label><input  style="width:200px;height:30px" class="laydate-icon form-control layer-date timeinput"  id="startDate"  
					value='<fmt:formatDate value="${EmployeesUserInfo.startDate}" pattern="yyyy-MM-dd"/>'>
					<label for="over">结束日期</label><input  style="width:200px;height:30px" class="laydate-icon form-control layer-date timeinput"  id="overDate"  
					value='<fmt:formatDate value="${EmployeesUserInfo.overDate}" pattern="yyyy-MM-dd"/>'>
				</div>
				<div class="second">
					<label for="school">学校名称</label><input type="text" id="schoolName" value="${EmployeesUserInfo.schoolName}">			
					<label for="technology">专业</label><input type="text" id="professional" value="${EmployeesUserInfo.professional}">			
					<label for="xuewei">学位</label><input type="text" id="degree" value="${EmployeesUserInfo.degree}">			
				</div>
				<div>
					<label for="addition">备注</label><input type="text" id="remark" value="${EmployeesUserInfo.remark}">			
<%-- 					<label for="commit">证件上传</label><input type="file" id="cardImg" value="${EmployeesUserInfo.cardImg}" >			 --%>
								
				</div>
			</div>
						<div class="row row-lg">
				<div class="row">
				<p class="ht">工作经历：</p>
					<div class="example-wrap">

						<table id="billListTable"
                           	   data-mobile-responsive="true"
				               data-toggle="table"
				               data-url="getExperienceByemployeesId.do"
				               data-data-type="json"
				               data-side-pagination="server"
				               data-pagination="true"
				               data-query-params="queryParams"
				               data-page-list="[5, 10, 20, 50, 100]"
				              >
							<thead>
								<tr>
	
									<th data-field="employeesId"  data-visible="false">员工ID</th>
									<th data-field="workName">工作名</th>
									<th data-field="depart">部门</th>
									<th data-field="positions">职位</th>
									<th data-field="workStart">开始时间</th>
									<th data-field="workEnd">结束时间</th>
								    <th data-field="id" data-formatter="formatterFun">操作</th>									
			
								</tr>
							</thead>
						</table>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group" >
							<button type="button" class="btn btn-outline btn-default"
								onclick="openAddPage('新建工作经历','60%','60%','<%=basePath%>/personal/createview.do', '<%=basePath%>/personal/getExperienceByemployeesId.do')">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i> <span>新建工作经历</span>
							</button>
						</div>
					</div>
				</div>
				</div>
			<p class="btnwr">	    		
			<div class="midsub" style="text-align: center; ">
	    		<a  id="sub" class="btn btn-primary" style="margin-right: 0;" href="javascript:" onclick="submit()"> 提交 </a>
    			</div>
    			
		</div>
	<jsp:include page="../shared/js.jsp"/>
		 <script src="<%=basePath %>/js/layer/laydate/laydate.js"></script>
		     <script>
        laydate({elem:"#birth",event:"focus"});
        laydate({elem:"#startTime",event:"focus"});
        laydate({elem:"#endTime",event:"focus"});
        laydate({elem:"#hiredate",event:"focus"});
        laydate({elem:"#startDate",event:"focus"});
        laydate({elem:"#overDate",event:"focus"});

    </script>
    <script type="text/javascript">
		table = $("#billListTable");
		
		function formatterFun(value,row,index){

			var delect = "delect('" + row.id + "')";
			var deleteUrl = '<%=basePath%>/personal/deleteExperience.do?id='+row.id;
			var infoUrl = '<%=basePath%>/personal/getExperienceInfoById.do?id='+row.id;
			var refreshUrl = '<%=basePath%>/personal/getExperienceByemployeesId.do';
			var showInfo = "openAddPage('工作经历修改','60%','60%','"+infoUrl+"','"+refreshUrl+"')";
	
			return '<button class="btn btn-info" onclick="'+showInfo+'"type="button" >修改</button> '+
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
                    $.get("deleteExperience.do", {
                        "id": id
                    }, function (data) {
                        if (data.status == 10001) {
                            swal({
                                title: "成功删除! ",
                                type: "success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            }, function () {
                                tableRefresh('getExperienceByemployeesId.do');
                            });
                        } else {
                            swal({
                                title: data.msg,
                                type: "error",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            }, function () {
                                tableRefresh('getExperienceByemployeesId.do');
                            });
                        }

                    })
                }
            );
        }

    	 function submit(){
    		    var birth=$("#birth").val();
    			var employeesName=$("#employeesName").val();
    			var mobile=$("#mobile").val();
    			var address=$("#address").val();
    			var email=$("#email").val();
    			var card=$("#card").val();
    			var wechat=$("#wechat").val();
    			var employeesSex=$("#employeesSex").val();
    			var otherContact=$("#otherContact").val();
    			var companyName=$("#companyName").val();
    			var department=$("#department").val();
    			var jobs=$("#jobs").val();
    			var startTime=$("#startTime").val();
    			var endTime=$("#endTime").val();   			
    			var hiredate=$("#hiredate").val();
    			var graduate=$("#graduate").val();
    			var startDate=$("#startDate").val();
    			var overDate=$("#overDate").val();
    			var schoolName=$("#schoolName").val();
    			var professional=$("#professional").val();
    			var degree=$("#degree").val();
    			var remark=$("#remark").val();
    			var cardImg=$("#cardImg").val();
    			$.ajax({
    				type:'post',
    				url:'<%=basePath%>/personal/updateUserInfo.do',
    				data:{

    					"employeesName":employeesName,
    					"birth":birth,
    					"mobile":mobile,
    					"address":address,
    					"email":email,
    					"card":card,
    					"wechat":wechat,
    					"employeesSex":employeesSex,
    					"otherContact":otherContact,
    					"companyName":companyName,
    					"department":department,
    					"jobs":jobs,
    					"startTime":startTime,
    					"endTime":endTime,
    					"hiredate":hiredate,
    					"graduate":graduate,
    					"startDate":startDate,
    					"overDate":overDate,
    					"schoolName":schoolName,
    					"professional":professional,
    					"degree":degree,
    					"remark":remark,
    					"cardImg":cardImg
    				},
    				success:function(data){
    					if (data.status == 10001) {
    						swal({
    							title : "更新成功！",
    							type : "success",
    							confirmButtonText : "确定",
    							closeOnConfirm : true
    						} ,function() {
    							window.location.reload();
    						}
    						);
    					} else {
    						swal({
    							title : "更新失败！",
    							text : data.msg,
    							type : "error",
    							confirmButtonText : "确定",
    							closeOnConfirm : true
    						});
    					}
    					
    				}
    			});
    		}

		</script>
	</body>
</html>
