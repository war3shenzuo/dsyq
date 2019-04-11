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
		<title>考试</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/xueyuandetails.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/eaxmtion.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
	</head>
	<body>
		
		<jsp:include page="../header.jsp" flush="true" />
	
		<div class="wrap">
			<div style=" width:100%; text-align: center;font-size: 35px;font-weight: bold;margin: 30px 0 0 0 ">
				<span id="courseName"></span>
			</div>
			<div id="eaxmtion"></div>
			<div  style=" width:168px; margin: 20px auto;">
	    		<a href="javascript:" onclick="eaxmtionTo()" id="sub"  style="margin-right: 0; cursor: pointer; text-align: center; line-height: 48px;">提交</a>
    			</div>
		</div>
		<jsp:include page="../footer.jsp" flush="true" />
		<script type="text/javascript">
		var collegeinfoId=GetQueryString("titleId");
		var Enum="";
		var Ids= [];
		var eAnswers = "";
		$(document).ready(function(){
			$.post("<%=basePath %>/etopQuestionBank/getEtopQuestionBank.do?",{"titleId":collegeinfoId},function(data){
				if(data.length>0){
					$("#courseName").append(""+data[0].courseName+"的考试");
					for(var i=0;i<data.length;i++){
						Enum=i+1;
						Ids.push(data[i].id);
// 						alert(Ids);
						$("#eaxmtion").append(
								"<p id='qest"+Enum+"'>"+Enum+"."+data[i].title+"<br><label>" +
								"<input type='radio' name='RadioGroup"+Enum+"' value='A' id='a' class='radios'>A."+data[i].answerA+"</label><label>" +
								"<input type='radio' name='RadioGroup"+Enum+"' value='B' id='b' class='radios'>B."+data[i].answerB+"</label><label>" +
								"<input type='radio' name='RadioGroup"+Enum+"' value='C' id='c' class='radios'>C."+data[i].answerA+"</label><label>" +
								"<input type='radio' name='RadioGroup"+Enum+"' value='D' id='d' class='radios'>D."+data[i].answerD+"</label></p>");
					}
				}
			});
		})
		function eaxmtionTo(){
			eAnswers="";
			var pLength=$("#eaxmtion p").length;
			for(var j=1;j<=pLength;j++){
				var questionNum="qest"+j;
				var eAnswer=$('#'+questionNum+' input:checked').val();
				if(eAnswer==undefined){
					swal("第"+j+"题未回答");
					return;
				}
				for(var i=0;i<Ids.length;i++){
					var id= Ids[i];
				if(i==j-1){
				if(j==pLength){
						eAnswers=eAnswers+id+":"+eAnswer;
				}
				else{
					eAnswers=eAnswers+id+":"+eAnswer+",";
				}
				}
			}
			}
			ajaxTo()
		}
		function ajaxTo(){
			$.ajax({  
			    type : "post",  
			    url : "<%=basePath %>/etopQuestionBank/calculateScore.do",
				async: true,
			    data : {
			        "titleId":collegeinfoId,
			        "ids":eAnswers
			    },
			    success : function(data){  
			        if(data.status==10001){
						swal({title :"您的分数为" + data.data + "分"} 
						,function() {
							window.location.reload();
						}
						);
			        }else{
			            swal("提交失败，请检查是否登录，网络是否连接！");
			        }
			    }  
			});
		}
		
		function GetQueryString(name)
			{
			     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			     var r = window.location.search.substr(1).match(reg);
			     if(r!=null)return  decodeURI(r[2]); return null;
			}
		</script>
	</body>
</html>
