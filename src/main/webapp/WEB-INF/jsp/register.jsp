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
<title>注册</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/login.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
</head>
<body>
	
	<jsp:include page="header.jsp" flush="true" />
    <div class="mainBox">
    	<div class="reg">
	        <div class="reg-main-section">
	            <h1>新用户注册</h1>
	            <form class="form-reg" name="form_reg" action="javascript:;" vcode_open="0">
	            	<dl class="username">
	            		<dt>*用户名</dt>
	            		<dd><input type="text" id="username" class="text text-username" maxlength="15" name="username" placeholder="给自己取一个用户名"><span class="t-normal ml5"> 由[英文字母数字_-]组成</span></dd>
	            	</dl>
	            	<dl class="username">
	            		<dt>*手机号码</dt>
	            		<dd><input type="text"id="mobile" class="text text-username" placeholder="请填写联系人手机号码"><span class="t-normal ml5">&nbsp;</span><input type="button" id="btn" value="发送验证码" onclick="sendCode(this)"  style="margin-top: 5px;"/></dd>
	            	</dl>
	            	<dl class="pssword">
	            		<dt>*验证码</dt>
	            		<dd><input type="text" id="acode" class="text text-pssword" maxlength="32" name="acode" placeholder="填写验证码"></dd>
	            	</dl>
	            	<dl class="pssword">
	            		<dt>*设置登录密码</dt>
	            		<dd><input type="password" id="password" class="text text-pssword" maxlength="32" name="password" placeholder="设置登录密码"></dd>
	            	</dl>
	            	<dl class="repssword">
	            		<dt>*请确认密码</dt>
	            		<dd><input type="password" id="sepass" class="text text-pssword-re" maxlength="32" name="repassword" placeholder="请再输入一次设置的密码"></dd>
	            	</dl>
	            	<dl class="agreement">
	            		<dt></dt>
	            		<dd><label class="remember"><input type="checkbox" name="remember" checked="checked" value="" id="remember">请仔细阅读<a href="" target="_blank" style="color:blue">《网络服务使用协议》</a></label></dd>
	            	</dl>
	            	<dl class="button">
	            		<dt></dt>
	            		<dd><button type="button" onclick="show()" class="btn btn-large btn-primary btn-reg" name="btn_reg">同意协议并提交注册</button></dd>
	            	</dl>
	            </form>
	        </div>
	        <div class="reg-aux-section">
	            <div></div>
	            <p>已经有帐号？请直接登录</p>
	            <div class="login-btns"><a class="btn" href="<%=basePath %>/login.do">直接登陆</a></div>
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
    var hr=location.href;
 	var n=hr.indexOf("?");
 	var str=hr.substr(n+1);
 	var userType=str.substr(9)-0;
 	
    function show(){
   	var acode = $("#acode").val();
    var username=$("#username").val();
    var password=$("#password").val();
    var sepassword=$("#sepass").val();
    var mobile = $("#mobile").val();
 	
	if($.trim(username)=="")
		{
		swal("用户名不为空");
		return;
		}
	if($.trim(password)=="")
		{
		swal("密码不为空");
		return;
		}
	else if($.trim(password).length<6){
		swal("密码不得小于6位数");
		return;
	}
	else if(password!=sepassword)
		{
			swal("密码不一致,请重新输入");
			return;
		}
	if($("#remember").is(':checked')==false)
	{
	swal("请仔细阅读《网络服务使用协议》,并打钩");
	return;
	}
  	$.ajax({
  		type:'post',
  		url:'<%=basePath %>/user/register.do',
  		data:{
  			"acode":acode,
  			"userName":username,
  			"passWord":password,
  			"userType":userType,
  			"mobile":mobile,
  		},
  		success:function(data){
  			 if(data.status==10001){
	          swal({
					title : "注册成功",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function(){
					
					location.href="<%=basePath%>/webIndex.do";
				});
					
	            }else{
	                swal(data.msg);
	            }
  		}
  	});
    }
   function sendCode(obj){
    	if($("#mobile").val()==""){
    		alert("请输入手机号");
    		return;
    	}
    	if(!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test($("#mobile").val()))
    	{
    	  alert('手机号格式不对');
    	  return;
    	}
    	settime(obj);
    	var param={
    	    "mobile":$("#mobile").val(),
    	    "type":"register"
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
