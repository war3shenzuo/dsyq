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
<title>忘记密码</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/login.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
</head>
<body>
	
	<jsp:include page="header.jsp" flush="true" />
    <div class="mainBox">
    	<div class="reg">
	        <div class="reg-main-section">
	            <h1>密码找回</h1>
	            <form class="form-reg" name="form_reg" action="javascript:;" vcode_open="0">
	            	<dl class="username">
	            		<dt>*用户名</dt>
	            		<dd><input type="text" id="username" class="text text-username" maxlength="15" name="username" placeholder=""></dd>
	            	</dl>
	            	<dl class="pssword">
	            		<dt>*验证码</dt>
	            		<dd><input type="text" id="Code" class="text text-pssword" maxlength="32" name="Code" placeholder=""><span class="t-normal ml5">&nbsp;</span><input type="button" id="btn" value="发送验证码" onclick="sendCode(this)"  style="margin-top: 5px;"/></dd>
	            	</dl>
	            	<dl class="pssword">
	            		<dt>*新密码</dt>
	            		<dd><input type="password" id="password" class="text text-pssword" maxlength="32" name="password" placeholder=""></dd>
	            	</dl>
	            	<dl class="repssword">
	            		<dt>*确认密码</dt>
	            		<dd><input type="password" id="sepass" class="text text-pssword-re" maxlength="32" name="repassword" placeholder=""></dd>
	            	</dl>
	            	<!-- <dl class="agreement">
	            		<dt></dt>
	            		<dd><label class="remember"><input type="checkbox" name="remember" checked="checked" value="1">请仔细阅读<a href="" target="_blank">《网络服务使用协议》</a></label></dd>
	            	</dl> -->
	            	<dl class="button">
	            		<dt></dt>
	            		<dd><button type="button" onclick="show()" class="btn btn-large btn-primary btn-reg" name="btn_reg">确定</button></dd>
	            	</dl>
	            </form>
	        </div>
	        <div class="reg-aux-section">
	            <div></div>
	            <p>已经有帐号？请直接登录</p>
	            <div class="login-btns"><a class="btn" href="<%=basePath %>/login/login.do">直接登陆</a></div>
	            <div class="login-apps" id="login_apps">
	                <!-- <div class="header">快捷登录</div>
	                <div class="section">
	                    
	                </div> -->
	            </div>
	        </div>
	    </div>
    </div>
 <jsp:include page="footer.jsp" flush="true" />
    <script>
    function show(){
    var username=$("#username").val();
    var password=$("#password").val();
    var sepassword=$("#sepass").val();
    var acode=$("#Code").val();
 	<%--
 	var hr=location.href;
 	var n=hr.indexOf("?");
 	var str=hr.substr(n+1);
 	var userType=str.substr(9)-0;
 	--%>

	if($.trim(username)=="")
		{
		swal("用户名不为空");
		return;
		}
	if($.trim(acode)=="")
	{
	swal("验证码不为空");
	return;
	}
	if($.trim(password)=="")
		{
		swal("密码不为空");
		return;
		}
	else if(password!=sepassword)
		{
			swal("密码不一致,请重新输入");
			return;
		}
  	$.ajax({
  		type:'post',
  		url:'<%=basePath %>/forgetPassword.do',
  		data:{
  			
  			"userName":username,
  			"passWord":password,
  			"acode":acode,
  			
  		},
  		success:function(data){
  			 if(data.status==10001){
	          swal({
					title : "密码修改成功",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function(){
					
					location.href="<%=basePath%>/login.do";
				});
					
	            }else{
	                swal(data.msg);
	            }
  		}
  	});
    }
    function sendCode(obj){
    	settime(obj);
    	var param={
    	    "mobile":$("#mobile").val(),
    	    "type":"back",
    	    "username":$("#username").val()
        }
    	
        $.post("<%=basePath%>/sendSmsCode.do?",param,function(data){
        	if(data.status!=10001){
        		swal(data.msg);
        	}
        })
    }
    	
    //发送验证码 重发倒计时
    var countdown=60; 
    function settime(obj) { 
    	
        if (countdown == 0) { 
            obj.removeAttribute("disabled");    
            obj.value="发送验证码"; 
            countdown = 60; 
            return;
        } else { 
            obj.setAttribute("disabled", true); 
            obj.value="重发(" + countdown + ")"; 
            countdown--; 
        } 
    	setTimeout(function() { 
        settime(obj) }
        ,1000) 
        
    }
    </script>
</body>
</html>
