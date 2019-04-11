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
    <title>密码修改-帐号管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
 
    	<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                  
                    <div class="ibox-content">
                    	
                        <form method="get" class="form-horizontal">
                          	<div class="form-group">
                                <label class="col-sm-2 control-label">密码</label>
                                <div class="col-sm-4">
                                    <input type="text" class="col-md-2" placeholder="" id="passWord"> 
                                    <span class="help-block m-b-none">新的密码</span>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">确认密码</label>
                                <div class="col-sm-4">
                                    <input type="password" class="col-md-2" placeholder="" id="passWord2"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                            
                                                    
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <a class="btn btn-primary"  onclick="submit()">保存内容</a>
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
    
    	function submit(){
    		
    		var parkGroupName = $("#prakGroupName").val();
    		var prakGroupDescribe = $("#prakGroupDescribe").val();
    		var approval = $("#approval").val();
    		var activated = $("#activated").val();
    		
    		$.post("<%=basePath %>/parkgroup/addParkGroup.do",{"prakGroupName":parkGroupName,"prakGroupDescribe":prakGroupDescribe,"approval":approval,"activated":activated},function(data){
				   if(data.status==10001){
						swal("保存成功", "", "success",function(){   alert("aa") });
						
						
						swal({   title: "保存成功！",   
								 type: "success",  
								 confirmButtonText: "ok",  
								 closeOnConfirm: false 
							  }, 
							  function(){  
									 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
									 parent.layer.close(index); 
							  }
							 );
						
						
				   }else{
				   }
			   });
    		
    	}
    
    </script>
    
    
</body>
</html>