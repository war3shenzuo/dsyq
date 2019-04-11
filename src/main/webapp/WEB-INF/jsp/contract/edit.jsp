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
<title>合同编辑</title>
<jsp:include page="../shared/css.jsp"/>

<jsp:include page="../shared/js.jsp"/>
<script src="<%=basePath%>/js/jquery.noty.packaged.min.js"></script>
<script src="<%=basePath%>/myjs/my.function.js"></script>
<script src="<%=basePath%>/myjs/contract.edit.js"></script>
<script type="text/javascript">
	
var basePath = "<%=basePath%>";
	
</script>

</head>
<body class="gray-bg">
<input type="hidden" id="read-only" value="${readonly}" />
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
		
		
		<div class="col-md-5 col-sm-12">

			<jsp:include page="contract.jsp"/>				

		</div>		
		
			<div class="col-md-7 col-sm-12" id="contract-item-contain">
				
				<c:choose>
				
<%-- 	               	<c:when test="${contract.contractCategory ==2 }"> --%>
	               		               		
<%-- 	               			<jsp:include page="express.jsp"/>	            	 --%>
<%-- 	               	</c:when> --%>
	               	
	               	<c:when test="${contract.contractCategory==3 }">
	               	
	               		<jsp:include page="energy.jsp"/>
	               				            	
	               	</c:when>
	               	
	               	<c:otherwise>
	               	
	               		<jsp:include page="instalments.jsp"/>
	               		
	               	</c:otherwise>
	               	
               	</c:choose>

			</div>			

		</div>

	</div>
	
	
<jsp:include page="modal.jsp"/>

</body>

</html>