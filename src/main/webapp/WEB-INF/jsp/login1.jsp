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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>系统后台 FOR EtopClub</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <jsp:include page="shared/css.jsp"/>
    <link href="<%=basePath%>/css/login.min.css" rel="stylesheet">
    
</head>

<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>[ e淘后台登录 ]</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>欢迎 <strong>登入e淘后台管理系统</strong></h4>
                    <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                    </ul>
                    <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
                </div>
            </div>
            <div class="col-sm-5">
                <form  id="form1" method="post" action="<%=basePath %>/managerIndex.do?url=${fromurl}" >
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录e淘后台管理系统</p>
                    <input type="text" id="userName" name="userName"class="form-control uname" placeholder="用户名" />
                    <input type="password" id="passWord"  name="passWord" class="form-control pword m-b" placeholder="密码" />
                    <a href="#">忘记密码了？</a>
                    <a class="btn btn-success btn-block" onclick="login()">登录</a>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2015 All Rights Reserved. etopclub
            </div>
        </div>
    </div>
    <jsp:include page="shared/js.jsp"/>
    
    <script type="text/javascript">
    	function login(){
    		
    		var userName = $("#userName").val();
    		var passWord = $("#passWord").val();
    		
    		$.post("<%=basePath %>/loginCheck.do",{"userName":userName,"passWord":passWord},function(data){
				   if(data.status==100){
					   document.getElementById("form1").submit();
				   }else{
					   alert(data.msg);
				   }
			   });
 		
    		
    	}
    
    </script>
    
</body>
</html>
