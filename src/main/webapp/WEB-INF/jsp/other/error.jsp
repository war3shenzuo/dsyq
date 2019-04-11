<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误信息</title>
<jsp:include page="../shared/css.jsp"/>

<jsp:include page="../shared/js.jsp"/>
<script src="<%=basePath%>/js/jquery.noty.packaged.min.js"></script>
<script src="<%=basePath%>/myjs/my.function.js"></script>

<script type="text/javascript">
	
var basePath = "<%=basePath%>";
	
</script>

</head>
<body class="gray-bg">

	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
		
		
		<div class="col-md-12 col-sm-12">

			<div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>页面出错信息</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="typography.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="typography.html#">选项1</a>
                                </li>
                                <li><a href="typography.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="well">
                            <h3> 页面出错   </h3> 
                            
                            
                            <p>${error }</p>
                            <p><a href="<%=basePath %>/contract/index.do">返回合同列表</a></p>
                            
                        </div>
                    </div>
                </div>			

		</div>		
		
					

		</div>

	</div>
	
	

</body>

</html>