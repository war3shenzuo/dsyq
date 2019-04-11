<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<link href="<%=basePath%>/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="<%=basePath%>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="<%=basePath%>/css/animate.min.css" rel="stylesheet">
<link href="<%=basePath%>/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=basePath%>/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="<%=basePath%>/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="<%=basePath%>/css/sweetalert/sweetalert.css" rel="stylesheet">
<link href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
<link href="<%=basePath%>/css/plugins/datapicker/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="<%=basePath%>/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
<%-- <link href="<%=basePath%>/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet"> --%>
<link rel="stylesheet" href="<%=basePath%>/js/jstree/themes/default/style.min.css" type="text/css" media="screen" charset="utf-8" />
<!-- Morris -->
<link href="<%=basePath%>/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
<!-- Gritter -->
<link href="<%=basePath%>/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

<link href="<%=basePath%>/js/datatables/css/jquery.dataTables.min.css"	rel="stylesheet">
<link href="<%=basePath%>/js/datatables/css/dataTables.bootstrap.min.css"	rel="stylesheet">
<link href="<%=basePath%>/js/datatables/css/responsive.dataTables.min.css"	rel="stylesheet">

<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/hzw-city-picker.css"/>
<style type="text/css">

.snAdd h1{ font-size:18px; font-weight:normal; margin-bottom:5px;}
.snAdd h2{ font-size:14px; font-weight:normal; color:#888;}
.snTab{ float:left;}
.snTab li{ float:left;}
.snTab .tabN a{ border:1px solid #d7d7d7; background:#eee; margin-bottom:-1px; padding:5px 10px; color:#666; float:left; margin-right:5px;}
.snTab .tabS a, .snTab .tabN a:hover{ border:1px solid #d7d7d7;  margin-bottom:-1px; background:#FFF; border-bottom:1px solid #FFF; padding:5px 10px; color:#333; float:left; margin-right:5px;}
.snTable{ border:1px solid #d7d7d7;}
.snTab ul li a{ color:#666;}
.snTab ul li a:hover{ color:#333;}
.tableStyle tr:hover .delLite{ background:url(<%=basePath%>/img/icon2.png) no-repeat -20px -56px; width:13px; height:13px; display:inline-block; margin:0 5px;}
.tableStyle tr:hover .delLite:hover{ background:url(<%=basePath%>/img/icon2.png) no-repeat -20px -73px;}
.tableStyle tr .num{ display:block; width:30px; margin:0 0px;}
.tableStyle tr .actionArea{display:none;}
.tableStyle tr:hover .num{ display:none;}
.tableStyle tr:hover .actionArea{display:block; }

.snSetProList{ width:100%; float:left;}
.snSetProList ul li{ float:left; width:100%; padding:10px 0; margin-bottom:1px; cursor:pointer;}
.snSetProList ul .select, .snSetProList ul .normal:hover{ background:#2197fc;}
.snSetProList ul .select a{  width:94%; margin:0 3%; float:left; color:#fff;}
.snSetProList ul .select a h2{ color:#FFF;}
.snSetProList ul .normal{ background:#FFF;}
.snSetProList ul .normal a{ border-bottom:1px solid #e4e4e4; width:94%; margin:0 3%; float:left; color:#fff;}
.snSetProList ul li a h1{ font-size:14px; line-height:120%; padding-bottom:2px;}
.snSetProList ul li a h2{ font-size:12px; color:#999;}
.snSetProList ul .normal a h1{ color:#666;}
.snSetProList ul .normal:hover a h1{ color:#fff;}
.snSetProList ul .normal a h2{ color:#999;}
.snSetProList ul .normal:hover a h2{ color:#fff;}
.snSetProList ul .normal:hover a{ border-bottom:1px solid #2197fc;}

.tableTitleBg{ background:url(/X_UserSkin/hujun/images/css/thBgN.png) bottom repeat-x #fdfefe;}
.error{ color:red;}

/* start of loading */
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
/* end of loading */


.label-last-balance-date{
	color:red;
	background-color:yellow;
}

.balance-comment{
	color:red;
	font-size:0.8em;
	text-indent:2em;
}
.form-control-contract{
border:0;
border-bottom: 1px solid #ccc;

}
</style>
