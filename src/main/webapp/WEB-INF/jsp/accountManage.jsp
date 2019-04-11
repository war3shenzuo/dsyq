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
		<title>账户管理</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/accountManage.css"/>
		
		<style>
		#pw{
				text-decoration: none;
				display:inline-block;
				color:rgb(238, 119, 15);
				border:1px solid rgb(238, 119, 15);
				background-color:white;
				border-radius: 5px;
				letter-spacing: 4px;
				height:30px;
			
				line-height:30px;
		}
		#pw:hover{
			background-color:rgb(238, 119, 15);
			color:white;
		}
		
		</style>
	</head>
	<body>
		<jsp:include page="header.jsp" flush="true" />
		
		<input type="hidden" id="userName" value="${userName}">
		<div class="inapplbox" id="inapplbox" style="margin-top:-100px;width:1500px;">
    		<h1>账户管理</h1>
    		
    			<table border="0" cellspacing="0" cellpadding="0" style="margin-top:-50px;">
    				<tr>
    					<td><p>姓名</p><input type="text" name="" id="name" value="${name}"  /></td>
    					<!-- 以下input是隐藏框 储存用户id -->
    					<td><input type="hidden" id="userid" value="${id}"></td>
    				</tr>
    			
    				<tr>
    					<td><p>注册邮箱</p><input type="text" name="" id="email" value="${email}" /></td>
    			
    				</tr>
    				<tr>
    					<td><p>联系电话</p><input type="text" name="" id="phone" value="${mobile}" /></td>
    			
    				</tr>
    					<tr>
    					<td><p>密码</p><input type="password" name="" id="password" value="${PassWord}" disabled="disabled" /><a href="#" id="pw" style="padding:1px 5px">修改</a></td>
    					</tr>
    			</table>
    			
	    		<div class="midsub" style=" width:168px;">
	    		<input type="button" id="sub" value="提交" style="margin-right: 0;"/>
    			</div>
    		
    	</div>
    	<div class="jump" id="jump">
			<div class="changepw">
			<a href="javascript:void(0)" id="close">&#10006</a>
				<p>修改密码</p>
				<div><label for="oldpw">原密码</label><input type="password" name="" id="oldpw" value=""  style="margin-left:20px;"/></div>
				<div><label for="newpw">新密码</label><input type="password" name="" id="newpw" value="" style="margin-left:20px;"/></div>
				<div><label for="confirmpw" style="margin-right:30px;">确认密码</label><input type="password" name="" id="confirmpw" value="" /></div>
				<p class="p3"><a href="#" id="confirm">确认</a></p>
			</div>
			<div class="up">
			</div>
	
			</div>
    	<jsp:include page="footer.jsp" flush="true" />
    		<script type="text/javascript">
		$(document).ready(function(){
			$("#pw").bind("click",function(){
			$("#jump").css("display","block");
			reset($("#oldpw"),$("#newpw"),$("#confirmpw"));
		});
		
		$("#close").bind("click",function(){
			$("#jump").css("display","none");
		});
		$("#confirm").bind("click",function(){
			var id=$("#userid").val();
			var newpw=$("#newpw").val();
			var oldpw=$("#oldpw").val();
			var confirmpw=$("#confirmpw").val();	
			var userName=$("#userName").val();
			
			if($.trim(newpw)=="")
				{
				swal("新密码不为空");
				return;
				}
			if(newpw!=confirmpw)
				{
					swal("新密码与确认密码不一致，请重新输入");
					return;
				}
			$.ajax({
				type:'post',
				url:'<%=basePath%>/user/changePassword.do',
				data:{
					"passWord":oldpw,
					"newPassWord":newpw,
					"userName":userName,
					"id":id
				},
				success:function(data){
					if(data.status==10002)
						{
						swal("原密码错误请重新输入");
						
						return;
						}
					else if(data.status==10001)
						{
						   swal({
								title : "密码修改成功",
								type : "success",
								confirmButtonText : "确定",
								closeOnConfirm : true
							}, function(){
								$("#jump").css("display","none");
							});
						
						}
				}
			});
			
		});
		
		$("#sub").bind("click",function(){
			var id=$("#userid").val();
			var email=$("#email").val();
			var mobile=$("#phone").val();
			var name=$("#name").val();
	
			if($.trim(email)==""){
				swal("注册邮箱不为空");
				return;
			}
			if($.trim(mobile)==""){
				swal("联系电话不为空");
				return;
			}
			$.ajax({
				type:'post',
				url:'<%=basePath%>/user/updateUserInfo.do',
				data:{
					"email":email,
					"mobile":mobile,
					"id":id,
					"name":name
				},
				success:function(data){
					if(data.status==10001)
						{
						swal("更新成功");
						}
					else {
						swal("更新失败");
					}
					
				}
			});
		});
		
		});
		
		
		function reset(input1,input2,input3)
		{
			input1.val(null);
			input2.val(null);
			input3.val(null);
		}
		</script>
	</body>
</html>
