<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/sweetalert.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/etopclub.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/login.css" />
<style>
	@-webkit-keyframes sk-threeBounceDelay {
	0%,100%,80% {
	-webkit-transform:scale(0);
	transform:scale(0)
}
40% {
	-webkit-transform:scale(1);
	transform:scale(1)
}
}@keyframes sk-threeBounceDelay {
	0%,100%,80% {
	-webkit-transform:scale(0);
	transform:scale(0)
}
40% {
	-webkit-transform:scale(1);
	transform:scale(1)
}
}.sk-spinner-three-bounce.sk-spinner {
	margin:0 auto;
	width:200px;
	text-align:center;
	margin-top: 15%;
}
.sk-spinner-three-bounce div {
	width:36px;
	height:36px;
	background-color:rgb(238,119,15);
	border-radius:100%;
	display:inline-block;
	-webkit-animation:sk-threeBounceDelay 1.4s infinite ease-in-out;
	animation:sk-threeBounceDelay 1.4s infinite ease-in-out;
	-webkit-animation-fill-mode:both;
	animation-fill-mode:both
}
.sk-spinner-three-bounce .sk-bounce1 {
	-webkit-animation-delay:-.32s;
	animation-delay:-.32s
}
.sk-spinner-three-bounce .sk-bounce2 {
	-webkit-animation-delay:-.16s;
	animation-delay:-.16s
}
</style>

</head>
<body>

	<jsp:include page="header.jsp" flush="true" />
	<div class="mainBox">
		<div class="loginbox">
			<div class="login-main-section">
				<h1>用户登陆</h1>
				<form id="form1" class="form-login" name="form_login"
					action="<%=basePath %>${fromurl }" vcode_open="0" method="post">
					<dl class="username">
						<dt>用户名</dt>
						<dd>
							<input type="text" id="text-username" class="text text-username"
								name="username" placeholder="用户名">
						</dd>
					</dl>
					<dl class="password">
						<dt>登陆密码</dt>
						<dd>
							<input type="password" id="text-password"
								class="text text-password" name="password" placeholder="登陆密码">
						</dd>
					</dl>
					<dl class="button">
						<dt></dt>
						<dd>
							<button type="button" id="btn"
								class="btn btn-large btn-primary btn-login" name="btn_login">立即登陆</button>
						</dd>
					</dl>
					<dl class="remember">
						<dt></dt>
						<dd>
							<label class="remember"><input type="checkbox"
								name="remember" checked="checked" value="1">记住我</label><a
								class="forget" href="<%=basePath%>/forgetPass.do">忘记密码？</a>
						</dd>
					</dl>
				</form>

			</div>
			<div class="login-aux-section">
				<p>还没有驿拓园区在线账号？ 立即注册一个吧</p>
				<div class="reg-btns">
					<a class="btn" href="<%=basePath%>/register/registerChoose.do">立即注册</a>
				</div>
				<div class="login-apps" id="login_apps">
					<!-- <div class="header">快捷登录</div>
                <div class="section">
                    
                </div> -->
				</div>
			</div>
		</div>
	</div>
	<div
		style="position: fixed; top: 0; left: 0; bottom: 0; right: 0; z-index: 98999; background: rgba(0, 0, 0, 0.7); display: none"
		id="linding">
		<div class="spiner-example">
			<div class="sk-spinner sk-spinner-three-bounce">
				<div class="sk-bounce1"></div>
				<div class="sk-bounce2"></div>
				<div class="sk-bounce3"></div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" flush="true" />
	<script>
	var fromurl = '${fromurl }';
	function show(){
	    document.getElementById("linding").style.display="block";
   		var userName=$("#text-username").val()	
   		var passWord=$("#text-password").val()
   		if($.trim(userName)=="")
   		{
   				swal("用户名不为空");
   				document.getElementById("linding").style.display="none";
   				return;
   		}
   		if($.trim(passWord)=="")
   		{
			swal("密码不为空");
			document.getElementById("linding").style.display="none";
			return;
   		}
   		
   	 	$.ajax({  
   		        type:'post',  
   		        url:'<%=basePath%>/loginCheck.do',
						data : {
							"userName" : userName,
							"passWord" : passWord
						},
						success : function(data) {
							if (data.status == 100) {
								if(fromurl == "")
									self.location=document.referrer;//返回上一页并刷新！
								else
									self.location="<%=basePath%>" + fromurl;
							} else {
								swal(data.msg, "", "error");
								document.getElementById("linding").style.display = "none";
							}
						}
					});
		}
		$("#btn").bind("click", show);
	</script>
</body>
</html>
