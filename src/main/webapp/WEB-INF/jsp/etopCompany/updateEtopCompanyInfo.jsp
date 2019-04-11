<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
    <title>公司列表-客户管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
	<jsp:include page="../shared/css.jsp"/>
</head>
<jsp:include page="../shared/js.jsp"/>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

    	<div class="row">
            <div class="col-sm-12">

                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">基本信息</a>
                        </li>
                        <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">附加信息</a>
                        </li>
                        <li class=""><a data-toggle="tab" href="#tab-3" aria-expanded="false">员工信息</a>
                        </li>
                        <li class=""><a data-toggle="tab" href="#tab-4" aria-expanded="false">拜访记录</a>
                        </li>
                        <li class=""><a data-toggle="tab" href="#tab-5" aria-expanded="false">营业额</a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">

                                <div class="ibox float-e-margins">

                                    <div class="ibox-content">

                                        <form method="get" class="form-horizontal" id="signupForm">

                                            <div class="form-group">
                                                <input type="text" class="form-control" placeholder="" id="companyId" name="companyId" value="${compInfo.companyId}" style="visibility: hidden">
                                                <label class="col-sm-1 control-label">公司名称<font color="red">*</font></label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" placeholder="" id="companyName" name="companyName" value="${compInfo.companyName}">
                                                </div>

                                                <label class="col-sm-1 control-label">公司类型<font color="red">*</font></label>
                                                <div class="col-sm-2">
                                                    <select class="form-control m-b" id="companyType" name="companyType" style="float: left; margin-right: 1%;">
                                                        <option value="有限责任公司"
                                                                <c:if test="${compInfo.companyType == '有限责任公司'}">selected</c:if> >有限责任公司
                                                        </option>
                                                        <option value="股份有限公司"
                                                                <c:if test="${compInfo.companyType == '股份有限公司'}">selected</c:if> >股份有限公司
                                                        </option>
                                                    </select>
                                                </div>

                                                <label class="col-sm-1 control-label">公司状态<font color="red">*</font></label>
                                                <div class="col-sm-2">
                                                    <select class="form-control m-b" id="companyStatus" name="companyStatus" style="float: left; margin-right: 1%;" disabled>
                                                        <option value="1"
                                                                <c:if test="${compInfo.companyStatus == '1'}">selected</c:if> >正式
                                                        </option>
                                                        <option value="2"
                                                                <c:if test="${compInfo.companyStatus == '2'}">selected</c:if> >合同过期
                                                        </option>
                                                        <option value="3"
                                                                <c:if test="${compInfo.companyStatus == '3'}">selected</c:if> >合同终止
                                                        </option>
                                                    </select>
                                                </div>

                                                <label class="col-sm-1 control-label">注册资金<font color="red">*</font></label>
                                                <div class="col-sm-2">
                                                   <%-- <select class="form-control m-b" id="companyCapital" name="companyCapital">
                                                        <option value="10万以下"
                                                                <c:if test="${compInfo.companyCapital == '10万以下'}">selected</c:if> >10万以下
                                                        </option>
                                                        <option value="10万-50万"
                                                                <c:if test="${compInfo.companyCapital == '10万-50万'}">selected</c:if> >10万-50万
                                                        </option>
                                                        <option value="50万-100万"
                                                                <c:if test="${compInfo.companyCapital == '50万-100万'}">selected</c:if> >50万-100万
                                                        </option>
                                                        <option value="100-500万"
                                                                <c:if test="${compInfo.companyCapital == '100-500万'}">selected</c:if> >100-500万
                                                        </option>
                                                        <option value="500万以上"
                                                                <c:if test="${compInfo.companyCapital == '500万以上'}">selected</c:if> >500万以上
                                                        </option>
                                                    </select>--%>
                                                    <input type="number" class="form-control" value="${compInfo.companyCapital}" id="companyCapital" name="companyCapital" aria-required="true" <c:if test="${readonly eq true}">disabled="disabled"</c:if>>
                                                </div>
                                            </div>

                                            <div class="hr-line-dashed"></div>

                                            <div class="form-group">
                                                <label class="col-sm-1 control-label">联系人<font color="red">*</font></label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" id="contact" name="contact" value="${compInfo.contact}">
                                                </div>

                                                <label class="col-sm-1 control-label">联系电话<font color="red">*</font></label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" id="mobile" name="mobile" value="${compInfo.mobile}">
                                                </div>

                                                <label class="col-sm-1 control-label">公司电话<font color="red">*</font></label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" id="companyMobile" name="companyMobile" value="${compInfo.companyMobile}">
                                                </div>

                                                <label class="col-sm-1 control-label">公司传真</label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" id="companyFax" name="companyFax" value="${compInfo.companyFax}">
                                                </div>
                                            </div>

                                            <div class="hr-line-dashed"></div>

                                            <div class="form-group">
                                                <label class="col-sm-1 control-label">备用联系人</label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" id="spareContact" name="spareContact" value="${compInfo.spareContact}">
                                                </div>

                                                <label class="col-sm-1 control-label">备用电话</label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" id="spareMobile" name="spareMobile" value="${compInfo.spareMobile}">
                                                </div>

                                                <label class="col-sm-1 control-label">对公邮箱</label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" id="email" name="email" value="${compInfo.email}">
                                                </div>

                                                <label class="col-sm-1 control-label">公司编码</label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control" id="companyCode" name="companyCode" value="${compInfo.companyCode}" disabled="disabled">
                                                </div>
                                            </div>

                                            <div class="hr-line-dashed"></div>

                                            <div class="form-group">

                                                <label class="col-sm-1 control-label">迁入时间</label>
                                                <div class="col-sm-2">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                        <input name="inAt" type="text" class="datepicker form-control" id="inAt"
                                                               value='<fmt:formatDate value="${compInfo.inAt}" pattern="yyyy-MM-dd"/>'>
                                                    </div>
                                                </div>

                                                <label class="col-sm-1 control-label">迁出时间</label>
                                                <div class="col-sm-2">
                                                    <div class="input-group date">
                                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                        <input name="outAt" type="text" class="datepicker form-control" id="outAt"
                                                               value='<fmt:formatDate value="${compInfo.outAt}" pattern="yyyy-MM-dd"/>'>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="hr-line-dashed"></div>

                                            <div class="form-group">
                                                <label class="col-sm-1 control-label">经营类目<font color="red">*</font></label>
                                                <div class="col-sm-6">
                                                    <input type="text" class="form-control" id="businessType" name="businessType" value="${compInfo.businessType}">
                                                </div>
                                            </div>

                                            <div class="hr-line-dashed"></div>

                                            <div class="form-group">
                                                <label class="col-sm-1 control-label">前经营所在地<font color="red">*</font></label>
                                                <div class="col-sm-6">
                                                    <input type="text" class="form-control" id="beforeseat" name="beforeseat" value="${compInfo.beforeseat}">
                                                </div>
                                            </div>

                                            <div class="hr-line-dashed"></div>

                                            <div class="form-group">
                                                <label class="col-sm-1 control-label">备注信息</label>
                                                <div class="col-sm-6">
                                                    <input type="text" class="form-control" id="remarks" name="remarks" value="${compInfo.remarks}">
                                                </div>
                                            </div>

                                            <div class="hr-line-dashed"></div>

                                            <div class="form-group" <c:if test="${readonly eq true}">style="display: none;"</c:if> >
                                                <div class="col-sm-4 col-sm-offset-5">
                                                    <input class="btn btn-primary" type="submit" value="确认并保存">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <%--附加信息页面--%>
                        <div id="tab-2" class="tab-pane">
                            <div class="panel-body">
                                <jsp:include page="updateAdditionalEtopCompanyInfo.jsp" />
                            </div>
                        </div>

                        <%--员工页面--%>
                        <div id="tab-3" class="tab-pane">
                            <div class="panel-body">
                                <jsp:include page="etopCompanyEmployeesList.jsp" />
                            </div>
                        </div>

                        <%--拜访记录--%>
                        <div id="tab-4" class="tab-pane">
                            <div class="panel-body">
                                <jsp:include page="../etopCompanyMaintain/etopCompanyMaintainList.jsp" />
                            </div>
                        </div>

                        <%--营业额--%>
                        <div id="tab-5" class="tab-pane">
                            <div class="panel-body">
                                <jsp:include page="../etopCompanyTurnover/etopCompanyTurnoverList.jsp" />
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $('.datepicker').datepicker({
            todayBtn: "linked",
            keyboardNavigation: !1,
            forceParse: !1,
            autoclose: !0
        });

        var standard = "<i class='fa fa-times-circle'></i> ";

        $("#signupForm").validate({
            rules: {//这里加校验规则
                companyName: "required",
                contact: "required",
                mobile: {
                    required:true,
                    digits:true
                },
                companyMobile: {
                    required:true,
                    digits:true
                },
                area: "required",
                price: "required",
                floorLayer: "required",
                floorNum: "required",
                floorRoom: "required",
                businessType: "required",
                beforeseat: "required"
            },
            messages: {//这里给对应的提示
                companyName: standard + "请输入公司名称 !",
                contact: standard + "请输入联系人姓名 !",
                mobile: {
                    required: standard + "请输入电话号码 !",
                    digits: standard + "请输入正确的电话格式 !"
                },
                companyMobile: {
                    required: standard + "请输入电话号码 !",
                    digits: standard + "请输入正确的电话格式 !"
                },
                businessType: standard + "请输入经验类目 !",
                beforeseat: standard + "请输入前经营所在地 !"
            },
            submitHandler: function(form){
            	prove();  //去提交
            }
        })
 function prove(){  
        var companyId = $("#companyId").val();
        var companyName = $("#companyName").val();
        if(companyName == "")  
        {  
           alert("公司名称不能为空!");  
           return;  
        }  
        $.ajax({  
               type: "POST",      
                url: "<%=basePath%>/etopCompanyIntention/proveCompanyName.do",      
                data: {companyName: $("#companyName").val(),companyId: $("#companyId").val()}, 
                success: function(data){  
               if(data.status == 10001){     
                swal({
					title : "公司名字重复！",
					type : "error",
					confirmButtonText : "确定",
					closeOnConfirm : false
				});
                return;
               }else{
            	   submit();
               }
               
               }            
               });     
       } 
        //编辑正式公司信息
        function submit(){

            var params = {
                "companyId" : $("#companyId").val(),
                "companyName" : $("#companyName").val(), // 公司名称
                "companyType" : $("#companyType").val(), // 公司类型
                "companyStatus" : $("#companyStatus").val(), // 公司状态
                "companyCapital" : $("#companyCapital").val(), // 注册资金
                "contact" : $("#contact").val(), // 联系人
                "mobile" : $("#mobile").val(), // 电话
                "companyMobile" : $("#companyMobile").val(), // 公司电话
                "companyFax" : $("#companyFax").val(), // 公司传真
                "spareContact" : $("#spareContact").val(), // 备用联系人
                "spareMobile" : $("#spareMobile").val(), // 备用联系人电话
                "email" : $("#email").val(), // 对公邮箱
                "companyCode" : $("#companyCode").val(),
                "inAt" : $("#inAt").val(), // 迁入时间
                "outAt" : $("#outAt").val(), // 迁出时间
                "businessType" : $("#businessType").val(),// 经营类目
                "beforeseat" : $("#beforeseat").val(), // 前经营所在地
                "remarks" : $("#remarks").val()// 备注
            }

            $.post("updateEtopCompanyInfo.do", params, function(data){
                if(data.status==10001){
                    swal({
                        title : data.msg,
                        type : "success",
                        confirmButtonText : "确定",
                        closeOnConfirm : true
                    });
                }else{
                    swal({
                        title : data.msg,
                        type : "error",
                        confirmButtonText : "确定",
                        closeOnConfirm : true
                    });
                }
            });
        }
    </script>
</body>
</html>