<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.UUID"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	UUID uuid = UUID.randomUUID(); 
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>投票通知添加-平台管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
<input type="hidden" class="input" id="noticeId" size="35"   value="<%=uuid%>">
    <div class="wrapper wrapper-content animated fadeInRight">
 
    	<div class="row">
            <div class="col-md-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                        <form id="form" method="get" class="form-horizontal">
                        <div class="row">
                            <div class="col-md-12">
                            <div class="row">
                            <div class="form-group">
                                <label class="col-md-1">通知标题<font color="red">*</font></label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" placeholder="" name="title" id="title"> 
                                </div>
                            </div>
                            </div>
                            <div class="hr-line-dashed"></div>
	                        <div class="row">
	                        <div class="form-group">
	                            <label class="col-md-1">激活状态</label>
                                <div class="col-md-3">
                                    <select class="form-control" name="state" id="state">
                                        <option value="1">激活</option>
                                        <option value="0">不激活</option>
                                    </select>
                                </div>

                             </div>
                             </div>
                             
                                 <div class="hr-line-dashed"></div>
		                        <div class="row">
		                        <div class="form-group">

                                <label class="col-md-1">接收者<font color="red">*</font></label>
                                <div class="col-md-1">
                                    <!--  <a class="btn btn-primary"  onclick="addsubmit()">保存内容</a> -->
                                    <a class="btn btn-primary" onclick="select()">请选择</a>
                                </div>
                                <div class="col-md-3">
                                    <input type="hidden" class="form-control" placeholder="" id="ids">
                                    <input type="hidden" class="form-control" placeholder="" id="allIds">
<!--                                     <input type="text" class="form-control" placeholder="" id="receiver" disabled> -->
                                     <textarea rows="3" cols="200" class="form-control"  id="receiver" disabled></textarea> 
                                </div>

                            </div>
                            </div>
                            
                             <div class="hr-line-dashed"></div>
                            <div class="row">
                            <div class="form-group">
                                <label class="col-md-1">通知内容<font color="red">*</font></label>
                                <div class="col-md-11">
                                    <textarea id="textWeb" name="textWeb" rows="" cols="" class="ckeditor" ></textarea>
                                </div>
                            </div>
                            </div>
                            
                <div class="hr-line-dashed"></div>

			    <div class="form-group">
			        <div class="row">
					<label class="col-md-1">投票内容<font color="red">*</font></label>
					<div class="col-md-11">
		            <table id="tabdetail" border="0" cellspacing="0" cellpadding="5"  width="100%" class="tableStyle table table-bordered">
		                <thead>
		                    <tr>
		                        <th width="60" height="40" align="center" valign="middle" >序号</th>
		                        <th align="center" width="140" valign="middle">内容</th>
		
		                    </tr>
		                </thead>
		                <tbody id="tbodyId">
		                    <tr>
		                        <td align="center" valign="middle">
<!-- 		                        <td align="center" valign="middle"  style="display:none"> -->
		                            <span class="num" id="num">1</span>
		                            <span class="actionArea">
		                                <a href="javascript:;" onClick="removerow(this)" class="delLite"></a>
		                            </span>
		                        </td>
		                        
		                        <td align="left" valign="middle" class="highlight">
		                            <input name="options" type="text" class=" form-control"/>
		                        </td>		
		                    </tr>
		                </tbody>
		            </table>

		            <table width="100%" border="0" cellspacing="0" cellpadding="5" class="tableStyle" style="border-top: 0px;">
		                <tbody>
		                <tr>
		                    <td colspan="2" valign="top" style="border-right: 0px;">
		                        <a id="aaddrow" href="javascript:;" onclick="addrow(); initorder();" class="btn btn-primary">
		                            	增加选项
		                        </a>
		                    </td>
		                </tr>
		                </tbody>
		            </table>
		            </div>
		            </div>
		    </div>
                            
                            <div class="hr-line-dashed"></div> 
                            </div>
                            <div class="row">
                            <div class="col-md-12">
	                            <div class="form-group">
	                                <div class="col-md-12" style="text-align: center;">
	                                   <!--  <a class="btn btn-primary"  onclick="addsubmit()">保存内容</a> -->
	                                    <input class="btn btn-primary" type="submit" value="确认并保存">
	                                </div>
	                            </div>
                            </div>
                            </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
      </div> 
    <jsp:include page="../shared/js.jsp"/>
    <script type="text/javascript">
    	var basePath='<%=basePath%>';
    </script>

    <script type="text/javascript" src="<%=basePath%>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">CKEDITOR.replace("textWeb");</script>

    <script type="text/javascript">
        var e = "<i class='fa fa-times-circle'></i> ";
        $("#form").validate({
            rules: {//这里加校验规则
                title : "required",
                receiver : "required",
                textWeb : "required"
            },
            messages: {//这里给对应的提示
                title : e+"请输入通知标题",
                receiver : e+"请选择收件人",
                textWeb : e+"请输入通知内容"
            },
            submitHandler: function(){
                addsubmit();
            }
        })

        /**添加*/
        function addsubmit(){
        	
        	 var business = [];

             var trList = $("#tbodyId").children("tr")
             for (var i=0; i<trList.length; i++) {
                 var tdArr = trList.eq(i).find("td");
                 var options = tdArr.eq(1).find("input").val();// 网址
//                  var createdAt = new Date().toLocaleDateString();
                 business.push(
                         {
                        	 noticeId :$("#noticeId").val(),
                             options : options
                         }
                 );
             }
            var content = CKEDITOR.instances.textWeb.getData();
            var param={
           		    "etopVote" : JSON.stringify(business),
	                "noticeId" : $("#noticeId").val(),
	                "title" : $("#title").val(),
	                "state" : $("#state").val(),
	                "addressee" : $("#ids").val(),
	                "receiver" : $("#receiver").val(),
	                "allIds" : $("#allIds").val(),
	                "content" :content
            }

            $.post(basePath+"/etopNotice/addEtopNoticeVote.do",param,function(data){
                if(data.status==10001){
                    swal({
                                title: "保存成功！",
                                type: "success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            },
                            function(){
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index);
                            }
                    );


                }else{
                    swal( data.msg, "","error");
                }
            });
        }

        function select(){
            openSelectPage('选择收件人','800px','600px',basePath+'/etopNotice/selectReceiverPage.do','');
        }

        
        //初始化序号子表
        function initorder() {
            var num = 1;
            $("#tabdetail tr:gt(0)").find(".num").each(function() {
                $(this).html(num);
                num = num + 1;
            });
        }
        // 删除行子表
        function removerow(obj) {
            if ($("#tabdetail tr:gt(0)").find(".num").length == 1) {
                swal("列表只有一行，不能删除!");
                return;
            }
            $(obj).parent().parent().parent().remove();
            initorder();
        }
        //增加行子表
        function addrow() {

            var newrow = $("#tabdetail>tbody tr:first").clone();
            $(newrow).find("input[name='options']").val("");//网址
            $("#tabdetail tr:last").after(newrow);
            // 初始化计算方法
            return addrow;
        }

    </script>
    
</body>
</html>