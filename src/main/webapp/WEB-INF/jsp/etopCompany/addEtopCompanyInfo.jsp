<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
        <title>基本信息-客户管理</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <jsp:include page="../shared/css.jsp"/>
    </head>

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
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">

                                    <div class="ibox float-e-margins">
                                        <div class="ibox-content">

                                            <form method="get" class="form-horizontal" id="signupForm">

                                                <div class="form-group">

                                                    <div>
                                                        <input type="hidden" id="companyId" value="${companyId}">
                                                    </div>

                                                    <label class="col-sm-1 control-label">公司名称<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" placeholder="" id="companyName"
                                                               name="companyName">
                                                    </div>

                                                    <label class="col-sm-1 control-label">公司类型<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <select class="form-control m-b" id="companyType" name="companyType"
                                                                style="float: left; margin-right: 1%;">
                                                            <option value="有限责任公司">有限责任公司</option>
                                                            <option value="股份有限公司">股份有限公司</option>
                                                        </select>
                                                    </div>

                                                    <label class="col-sm-1 control-label">公司状态<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <select class="form-control m-b" id="companyStatus" name="companyStatus"
                                                                style="float: left; margin-right: 1%;">
                                                            <option value="1">正式</option>
                                                            <option value="2">合同过期</option>
                                                            <option value="3">合同终止</option>
                                                            <option value="4">已迁出</option>
                                                        </select>
                                                    </div>

                                                    <label class="col-sm-1 control-label">注册资金<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <select class="form-control m-b" id="companyCapital"
                                                                name="companyCapital">
                                                            <option value="10万以下">10万以下</option>
                                                            <option value="10万-50万">10万-50万</option>
                                                            <option value="50万-100万">50万-100万</option>
                                                            <option value="100-500万">100-500万</option>
                                                            <option value="500万以上">500万以上</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">联系人<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="contact" name="contact">
                                                    </div>

                                                    <label class="col-sm-1 control-label">联系电话<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="mobile" name="mobile">
                                                    </div>

                                                    <label class="col-sm-1 control-label">公司电话<font color="red">*</font></label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="companyMobile"
                                                               name="companyMobile">
                                                    </div>

                                                    <label class="col-sm-1 control-label">公司传真</label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="companyFax"
                                                               name="companyFax">
                                                    </div>
                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">备用联系人</label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="spareContact"
                                                               name="spareContact">
                                                    </div>

                                                    <label class="col-sm-1 control-label">备用电话</label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="spareMobile"
                                                               name="spareMobile">
                                                    </div>

                                                    <label class="col-sm-1 control-label">对公邮箱</label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" id="email" name="email">
                                                    </div>
                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">迁入时间</label>

                                                    <div class="col-sm-2">
                                                        <div class="input-group date">
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </span>
                                                            <input type="text" class="datepicker form-control" id="inAt" name="inAt">
                                                        </div>
                                                    </div>

                                                    <label class="col-sm-1 control-label">迁出时间</label>

                                                    <div class="col-sm-2">
                                                        <div class="input-group date">
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </span>
                                                            <input type="text" class="datepicker form-control" id="outAt" name="outAt">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">经营类目<font color="red">*</font></label>

                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" id="businessType"
                                                               name="businessType">
                                                    </div>
                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">前经营所在地<font
                                                            color="red">*</font></label>

                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" id="beforeseat"
                                                               name="beforeseat">
                                                    </div>
                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <label class="col-sm-1 control-label">备注信息</label>

                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" id="remarks" name="remarks">
                                                    </div>
                                                </div>

                                                <div class="hr-line-dashed"></div>

                                                <div class="form-group">
                                                    <div class="col-sm-4 col-sm-offset-5">
                                                        <%--<a class="btn btn-primary"  onclick="submit()">确认并保存</a>--%>
                                                        <input class="btn btn-primary" type="submit" value="确认并保存">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="tab-2" class="tab-pane">
                                <div class="panel-body">
                                    <jsp:include page="additionalEtopCompanyInfo.jsp" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../shared/js.jsp"/>
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
                    submit();  //去提交
                }
            })

            //新增正式公司信息保存
            function submit(){

                var params = {
                    "companyId" : '${companyId}',
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
                    "email" : $("#email").val(), // 电子邮件
                    "inAt" : $("#inAt").val(), // 迁入时间
                    "outAt" : $("#outAt").val(), // 迁出时间
                    "businessType" : $("#businessType").val(),// 经营类目
                    "beforeseat" : $("#beforeseat").val(), // 前经营所在地
                    "remarks" : $("#remarks").val()// 备注
                }

                if($.trim(params.inAt) > $.trim(params.outAt)){
                    swal("迁入时间不能大于迁出时间!");
                    return;
                }

                $.post("addEtopCompanyInfo.do",params,function(data){
                    if(data.status==10001){
                        swal({
                            title : data.msg,
                            type : "success",
                            confirmButtonText : "确定",
                            closeOnConfirm : true
                        }, function() {
                            location.reload();
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