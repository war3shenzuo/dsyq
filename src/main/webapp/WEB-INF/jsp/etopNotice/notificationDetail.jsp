<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path;
%>

<html>

<head>

  <meta charset="utf-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>查看通知信息</title>

  <jsp:include page="../shared/css.jsp"/>
</head>

<jsp:include page="../shared/js.jsp"/>

<body class="gray-bg">
<div class="wrapper wrapper-content">
<input id="noticeId" value='${notice.noticeId}' type="hidden">
    <div class="row">

        <div class="col-sm-10 animated fadeInRight">
            <div class="mail-box-header">
                <div class="pull-right tooltip-demo">
                    <a href="<%=basePath%>/etopNotice/getMessageList.do" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="回复"><i class="fa fa-reply"></i> 返回</a>
                </div>
                <h2 style="color: #337ab7">通知详情</h2>
                <div class="mail-tools tooltip-demo m-t-md">
                    <h3>
                      <span class="font-noraml" style="color: magenta">标题：</span>${notice.title}
                    </h3>
                    <h5>
                      <span class="pull-right font-noraml"><i class="fa fa-clock-o"></i><fmt:formatDate value="${notice.createdAt}" pattern="yyyy-MM-dd"/></span>
                      <span class="font-noraml" style="color: magenta">发件人： </span>${notice.userName}
                    </h5>
                </div>

            </div>
            <div class="mail-box">

				<c:if test="${notice.content ne null}">
                <div class="mail-body">
                    <h4 style="color: #337ab7">通知内容：</h4>
                    <p>
                      ${notice.content}
                    </p>
                    <c:if test="${reply ne null}">
                                    <div class="hr-line-dashed"></div>
	                               <div class="row">                                       
	                               <div class="form-group">
                                            <label class="col-md-1">回复内容</label>
                                            <div class="col-md-10">
                                             <c:forEach items="${reply}" var="reply" varStatus="content">
						                            	<p style=" border: 1px solid #d6d6d6; margin-bottom: 5px; padding: 8px;">${reply.content}—— ——  <fmt:formatDate value="${reply.replyTime}" pattern="yyyy-MM-dd HH:mm:ss"/> by ${reply.replyer}</p>
					                         </c:forEach>
											</div>
                                        </div>
                                    </div>
                    </c:if>

				<div class="pull-right form-group">	
				    <button  type="button" style="margin-top: 23px;" id="tog" class="btn btn-primary">回复</button>
				</div>
				<div id="change"  style=" display:none; clear: both;  text-align:center">
				     <textarea style="min-width:100%;min-height:15%;" id="content"></textarea>      
				     <button  type="button" style="margin-top: 23px;" id="queryBtn" class="btn btn-primary">提交</button>  
				</div>
                </div>
				</c:if>
					<div class="clearfix"></div>
							
					<c:if test="${notice.noticeType eq '投票通知'}">
                  	<div class="mail-body">
                    <h4 style="color: #337ab7">投票：</h4>
                     <div class="row">                                       
                         <div class="form-group">
                                     <div class="col-md-10" id='id' >
	                                      <c:forEach items="${vote}" var="vote" varStatus="options">
	      										<label><input type='radio' name='id'   class='radios' value="${vote.id}">${vote.options}</label> 
	                      				  </c:forEach>
									 </div>
                          </div> 
                          <div class="col-md-12" style="text-align: center;">
<!--                                  <a class="btn btn-primary"  onclick="voteSubmit()">确认并投票</a> -->
							<button  type="button" style="margin-top: 23px;" id="voteSubmit" class="btn btn-primary">提交</button>  
                          </div>
                    </div>
		            </div>
		            </c:if>
        </div>
    </div>
</div>
</div>
<script>
  $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
  
  $(document).ready(function(){

	  $("#tog").click(function(){
	  $("#change").toggle("normal");
	  });
	  
	$("#queryBtn").click(function(){
	  var content=$("#content").val();
	  var noticeId=$("#noticeId").val();
	
	  $.post("<%=basePath %>/reply/addReply.do",
	  {
		  content:content,
		  noticeId:noticeId
	  },function(data){window.location.reload();});
	});
	  
	  
	$("#voteSubmit").click(function(){
		 var param={
		          "id" : $('#id input:checked').val()
		      }

		      $.post("<%=basePath%>/etopVote/updateBynoticeId.do",param,function(data){
		          if(data.status==10001){
		              swal({   title: "投票成功！",
		                          type: "success",
		                          confirmButtonText: "ok",
		                          closeOnConfirm: true
		                      },
		                      function(){
		                    	  $("#voteSubmit").attr("disabled", "true");	
		                      }
		              );

		          }else{
		              swal( data.msg, "","error");
		          }
		      });
	});

	});
  
//   $('#id input').click(function(){
//  	  alert($('#id input:checked').val());
//   })
  function voteSubmit(){
      var param={
          "id" : $('#id input:checked').val()
      }

      $.post("<%=basePath%>/etopVote/updateBynoticeId.do",param,function(data){
          if(data.status==10001){
              swal({   title: "投票成功！",
                          type: "success",
                          confirmButtonText: "ok",
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
</script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>


</html>
