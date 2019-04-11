<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>


<!--
	描述：主要JS部分，几乎所有页面都要用到
-->
<script src="<%=basePath%>/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=basePath%>/js/bootstrap.min.js?v=3.3.6"></script>
<script src="<%=basePath%>/js/content.min.js?v=1.0.0"></script>
<!--
	描述：首页要用到的，但并不是绝对只有首页用，请看情况定
	jquery.metisMenu.js --- 菜单插件js一般就首页用到，就是首页左边的导航栏
	contabs.min.js      --- tab插件，主要用处：首页的tab栏
	jquery.slimscroll.min.js         ---  滚动条插件，支持把内容放在一个盒子里面,固定一个高度,超出的则使用滚动。
-->
<script src="<%=basePath%>/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="<%=basePath%>/js/contabs.min.js" type="text/javascript"></script>
<script src="<%=basePath%>/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!--

描述：其他有用处的都一一描述，不作详细分类了
pace.min.js                      ---  网页自动加载进度条插件js，有用到的加，一般也就首页有用到-->
<script src="<%=basePath%>/js/plugins/pace/pace.min.js"></script>
<!--	bootstrap-datepicker.js          ---  我们用到的‘时间日历’插件，配合css ‘datepicker3.css’ 使用-->
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>

<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath%>/js/plugins/clockpicker/clockpicker.js"></script>
<!--bootstrap-colorpicker            ---  选颜色的插件，并不知道有用到的地方
<script src="<%=basePath%>/js/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>-->
<!--  bootstrap-prettyfile.js          ---  上传文件按钮 的美化插件      用不用随你心意-->
<script src="<%=basePath%>/js/plugins/prettyfile/bootstrap-prettyfile.js"></script>
<!--  cropper.min.js                   ---  头像上传-->
<script src="<%=basePath%>/js/plugins/cropper/cropper.min.js"></script>
<!--  jquery.slimscroll.min.js         ---  滚动条插件，支持把内容放在一个盒子里面,固定一个高度,超出的则使用滚动。-->

<!--  layer.min.js                     ---  弹出层插件，用发百度一下 很详细-->
<script src="<%=basePath%>/js/plugins/layer/layer.min.js"></script>
<script src="<%=basePath%>/js/hplus.min.js?v=4.1.0"></script>
<!--  bootstrap-treeview.js            ---  树结构 插件-->
<%-- <script src="<%=basePath%>/js/plugins/treeview/bootstrap-treeview.js"></script> --%>
<!--  
bootstrap-table.min.js           ---  我们用到的表格插件
bootstrap-table-mobile.min.js
bootstrap-table-zh-CN.min.js     ---  讲道理，如果用到表格的话，这几个都要引用-->
<script src="<%=basePath%>/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=basePath%>/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="<%=basePath%>/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>





    
<!--  jquery.peity.min.js              ---  基于jq的图表插件
jquery.easypiechart.js
jquery.sparkline.min.js
<script src="<%=basePath%>/js/plugins/peity/jquery.peity.min.js"></script>
<script src="<%=basePath%>/js/plugins/easypiechart/jquery.easypiechart.js"></script>
<script src="<%=basePath%>/js/plugins/sparkline/jquery.sparkline.min.js"></script>-->

<!--  jquery-ui.min.js                 ---  一些小组件，基本没什么用处
<script src="<%=basePath%>/js/plugins/jquery-ui/jquery-ui.min.js"></script>-->
<!--jquery.gritter.min.js            ---  Gritter 是一个小型的 jQuery 消息通知插件  
<script src="<%=basePath%>/js/plugins/gritter/jquery.gritter.min.js"></script>-->
<!--  icheck.min.js                    ---  复选框单选框等美化插件-->
<script src="<%=basePath%>/js/plugins/iCheck/icheck.min.js"></script>
<!--  sweetalert.min.js                ---  警告框，  -->
<script src="<%=basePath%>/js/plugins/sweetalert/sweetalert.min.js"></script>

<!-- 上传文件 -->
<script src="<%=basePath%>/js/ajaxfileupload.js"></script>
<!--
	描述：一下是图表插件，各种图表 
	
	由于文档网站是英文的，我看不懂， 不怎么推荐使用

<script src="<%=basePath%>/js/plugins/flot/jquery.flot.js"></script>
<script src="<%=basePath%>/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="<%=basePath%>/js/plugins/flot/jquery.flot.spline.js"></script>
<script src="<%=basePath%>/js/plugins/flot/jquery.flot.resize.js"></script>
<script src="<%=basePath%>/js/plugins/flot/jquery.flot.pie.js"></script>
-->

<!--
用不到的js

dataTable表格插件，我们用的是bootstrap-table，所以下两个都用不到
-->
<script src="<%=basePath%>/js/datatables/js/jquery.dataTables.min.js"></script>
<script	src="<%=basePath%>/js/datatables/js/dataTables.bootstrap.min.js"></script>
<script	src="<%=basePath%>/js/datatables/js/dataTables.responsive.min.js"></script>
<!-- jstree 树 -->
<script type="text/javascript" src="<%=basePath%>/js/jstree/jstree.min.js"></script>


<!-- myjs -->
<script src="<%=basePath%>/myjs/table.js"></script>
<script src="<%=basePath%>/myjs/company.js"></script>
<script src="<%=basePath%>/myjs/tree.js"></script>
<!--  
表单验证JS-->
<script src="<%=basePath%>/js/plugins/validate/jquery.validate.min.js"></script>
<script src="<%=basePath%>/js/plugins/validate/messages_zh.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/city-data.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/hzw-city-picker.min.js"></script>

<script>
$(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
function totabsss(herf,txt) {
	window.parent.parent.addTable(herf,txt);
}
function totabs(herf,txt) {
	window.parent.addTable(herf,txt);
}
function totabss(href,txt,href2) {
	window.parent.addTable2(href,txt,href2);
}
$('#file-pretty input[type="file"]').prettyFile();//这个是更改上传文件控件样式的JS调用

$('#degreesImg input[type="file"]').prettyFile();


$.validator.setDefaults({
	highlight: function(e) {
		$(e).closest(".form-group div").removeClass("has-success").addClass("has-error")
	},
	success: function(e) {
		e.closest(".form-group div").removeClass("has-error").addClass("has-success")
	},
	errorElement: "span",
	errorPlacement: function(e, r) {
		e.appendTo(r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent())
	}
});

$.validator.addMethod("lowercase",function(value,element,params){  
	if(value.length==0){
		return false;
	}
	if (/^[0-9a-z]+$/.test(value)) {
		return true;
	}else{
		return false;
	}
},"必须是一个小写字母和数字");


$('.datepicker').datepicker({
	todayBtn: "linked",
	keyboardNavigation: !1,
	autoclose: !0
});
</script>       
