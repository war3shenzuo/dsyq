<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="/WEB-INF/jsp/shared/css.jsp" />
<title>账单支付</title>
</head>
<body class="grey-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form method="get" class="form-horizontal" id="signupForm">
						<div class="form-group">
							<label class="col-sm-2 control-label">支付金额</label>
							<div class="col-sm-10">
								<div class="input-group">
									<span class="input-group-addon">￥</span> <input type="number"
										class="form-control" id="amount" name="amount">
								</div>
							</div>
						</div>
						<div class="form-group" id="payType">
							<label class="col-sm-2 control-label">支付方式</label>
							<div class="radio i-checks col-sm-1">
								<label><input type="radio" value="1" name="a"
									checked="">银行卡</label>
							</div>
							<div class="radio i-checks col-sm-1">
								<label><input type="radio" value="2" name="a">支票</label>
							</div>
							<div class="radio i-checks col-sm-1">
								<label><input type="radio" value="3" name="a">支付宝</label>
							</div>
							<div class="radio i-checks col-sm-1">
								<label><input type="radio" value="4" name="a">微信</label>
							</div>
							<div class="radio i-checks col-sm-1">
								<label><input type="radio" value="5" name="a">现金</label>
							</div>
							<div class="radio i-checks col-sm-1">
								<label><input type="radio" value="9" name="a">其他</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">支付凭证号</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="payNoOut">
							</div>
						</div>
						<div class="form-group" style="display: none;">
							<label class="col-sm-2 control-label">附件</label>
							<div class="col-sm-8">
								<div id="file-pretty">
									<input class="form-control" type="file" name="file" id='file'>
								</div>
							</div>
							<a class="btn btn-default" onclick="uploadFile()">上传</a>
						</div>
						<div class="form-group">
							<div class="col-sm-10 col-sm-offset-2" id="filelist"></div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2">
								<!-- <a class="btn btn-primary" onclick="submit()">提交</a> -->
								<input class="btn btn-primary" type="submit" value="提交">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/shared/js.jsp" />
<script type="text/javascript">
	var billId = "${billId}";

	function submit() {
		var amount = $("#amount").val();
		var payType = $('#payType input:radio:checked').val();
		var payNoOut = $("#payNoOut").val();
		var attachment = '';
		var fln = $('#filelist').find('input');
		for (var i = 0; i < fln.length; i++) {
			attr = fln.eq(i).val() + ",";
			attachment += attr;
		}

		$.post("add.do", {
			"billId" : billId,
			"amount" : amount,
			"payType" : payType,
			"payNoOut" : payNoOut,
			"attachment" : attachment
		}, function(data) {
			if (data.status == 10001) {
				swal({
					title : "保存成功！",
					type : "success",
					confirmButtonText : "确定",
					closeOnConfirm : false
				}, function() {
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index);
				});
			} else {
				swal({
					title : "添加失败！",
					text : data.msg,
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : true
				});
			}
		});
	}

	$("#signupForm").validate({
		rules : {
			amount : "required"
		},
		submitHandler : function(form) {
			submit();
		}
	});

	function uploadFile() {
		$
				.ajaxFileUpload({
					url : '../../file/upload.do',
					type : 'post',
					secureuri : false, //一般设置为false
					fileElementId : 'file', // 上传文件的id、name属性名
					dataType : 'json', //返回值类型，一般设置为json、application/json
					success : function(data) {
						if (data.status == 10001) {
							$('.input-append input').val("");
							$('input[id=file]').change(function() {
								var path = $(this).val().split('\\');
								var infotxt = path[path.length - 1];
								$('.input-append input').val(infotxt);
							});
							file = data.data[0];
							var oDiv = document.createElement('div');
							$('#filelist').append(oDiv);
							var oInput = document.createElement('input');
							oInput.value = file.path;
							oInput.hidden = true;
							var oA = document.createElement('a');
							oA.innerHTML = '<span class="glyphicon glyphicon-remove"></span>';
							oA.setAttribute('onclick', 'deleteFile(this)');
							$(oDiv).append(oInput);
							$(oDiv).append(file.original);
							$(oDiv).append(oA);
						} else {
							swal({
								title : "上传失败！",
								text : data.msg,
								type : "error",
								confirmButtonText : "确定",
								closeOnConfirm : true
							});
						}
					}
				});
	}

	function deleteFile(obj) {
		$(obj).parent().remove();
	}
</script>
</body>
</html>