<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!--
	    	作者：277250649@qq.com
	    	时间：2016-08-19
	    	描述：页面底部开始	
	    -->
	   ${footerValue }
	    <!--
	    	作者：277250649@qq.com
	    	时间：2016-08-19
	    	描述：页面底部结束
	    -->
	    
    	<script src="<%=basePath %>/js/jquery.2.1.4.min.js"></script>
		<script src="<%=basePath %>/js/bootstrap.min.js"></script>
		<script src="<%=basePath %>/js/addEtopCompanyIntentionInfo.js"></script>
		<script src="<%=basePath %>/js/sweetalert.min.js"></script>
		<script src="<%=basePath %>/js/slider.js" type="text/javascript" ></script>
		<script type="text/javascript" src="<%=basePath %>/js/city-data.js"></script>
		<script type="text/javascript" src="<%=basePath %>/js/hzw-city-picker.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>/js/paging.js"></script>
	    <script type="text/javascript">
		window.onload=function()
		{
			var hrefs=location.href;
			var number=hrefs.indexOf("com/")+4;
			
			hrefs=hrefs.substring(number,number+7);
			//alert(hrefs);
			var menu=document.getElementById("menu");
			var menuLi=menu.getElementsByTagName("a");
			for (var i=0;i<menuLi.length;i++) {
				var mherf=menuLi[i].href;
				if (mherf.indexOf(hrefs) > -1) {
					menuLi[i].className="hover";
					return
				}
			}
		}
		
		
		if($('#userpkgid').val()!=""){
			var pgd="<%=basePath %>/webinpark/inparkInfo.do?parkGroupId="+$('#userpkgid').val();
			$("#inparkUrl").attr("href",pgd);
		}
		
		
	</script>
	    