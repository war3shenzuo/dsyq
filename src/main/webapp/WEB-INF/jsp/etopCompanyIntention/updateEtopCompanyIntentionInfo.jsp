<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:include page="../shared/js.jsp"/>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">

            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">基本信息</a>
                    </li>
                    <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">拜访记录</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">

                            <div class="ibox float-e-margins">
                                <div class="ibox-content">
                                    <form method="get" class="form-horizontal" id="signupForm">
                                        <div class="form-group">
                                            <input type="text" class="form-control" placeholder="" id="id" name="id" value="${compInfo.id}" style="visibility: hidden">
                                            <label class="col-sm-1 control-label">公司名称<font color="red">*</font></label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" placeholder="" id="companyName" name="companyName" value="${compInfo.companyName}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>

                                            <label class="col-sm-1 control-label">公司类型<font color="red">*</font></label>
                                            <div class="col-sm-2">
                                                <select class="form-control m-b" id="companyType" name="companyType" style="float: left; margin-right: 1%;" <c:if test="${readonly eq true}">disabled="disabled"</c:if>>
                                                    <option value="有限责任公司"
                                                            <c:if test="${compInfo.companyType == '有限责任公司'}">selected</c:if> >有限责任公司
                                                    </option>
                                                    <option value="股份有限公司"
                                                            <c:if test="${compInfo.companyType == '股份有限公司'}">selected</c:if> >股份有限公司
                                                    </option>
                                                </select>
                                            </div>

                                            <label class="col-sm-1 control-label">注册资金<font color="red">*</font></label>
                                            <div class="col-sm-2">
                                                <%--<select class="form-control m-b" id="companyCapital" name="companyCapital" <c:if test="${readonly eq true}">disabled="disabled"</c:if>>
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

                                            <label class="col-sm-1 control-label">审核状态</label>
                                            <div class="col-sm-2">
                                                <c:if test="${compInfo.reviewStatus eq 0}">
                                                    <input type="text" class="form-control" placeholder="" id="reviewStatus" name="reviewStatus" value="未审核" disabled="disabled">
                                                </c:if>
                                                <c:if test="${compInfo.reviewStatus eq 1}">
                                                    <input type="text" class="form-control" placeholder="" id="reviewStatus" name="reviewStatus" value="已审核" disabled="disabled">
                                                </c:if>
                                                <c:if test="${compInfo.reviewStatus eq 2}">
                                                    <input type="text" class="form-control" placeholder="" id="reviewStatus" name="reviewStatus" value="审核失败" disabled="disabled">
                                                </c:if>
                                            </div>
                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group">
                                            <label class="col-sm-1 control-label">联系人<font color="red">*</font></label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="contact" name="contact" value="${compInfo.contact}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>

                                            <label class="col-sm-1 control-label">联系电话<font color="red">*</font></label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="mobile" name="mobile" value="${compInfo.mobile}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>

                                            <label class="col-sm-1 control-label">公司电话</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="companyMobile" name="companyMobile" value="${compInfo.companyMobile}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>

                                            <label class="col-sm-1 control-label">公司传真</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="companyFax" name="companyFax" value="${compInfo.companyFax}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>
                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group">
                                            <label class="col-sm-1 control-label">备用联系人</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="spareContact" name="spareContact" value="${compInfo.spareContact}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>

                                            <label class="col-sm-1 control-label">备用电话</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="spareMobile" name="spareMobile" value="${compInfo.spareMobile}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>

                                            <label class="col-sm-1 control-label">对公邮箱</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="email" name="email" value="${compInfo.email}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>
                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group">
                                            <label class="col-sm-1 control-label">面积</label>
                                            <div class="col-sm-1" style="position: relative">
                                                <input type="number" aria-required="true" class="form-control" id="smallArea" name="smallArea" value="${compInfo.smallArea}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                                <div style="position: absolute; top: 10px; right: 0px;">-</div>
                                            </div>
                                            <div class="col-sm-1">
                                                <input type="number" class="form-control" id="area" name="area" aria-required="true" value="${compInfo.area}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>

                                            <label class="col-sm-1 control-label">价格</label>
                                            <div class="col-sm-1" style="position: relative">
                                                <input type="number" aria-required="true" class="form-control" id="lowPrice" name="lowPrice" value="${compInfo.lowPrice}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                                <div style="position: absolute; top: 10px; right: 0px;">-</div>
                                            </div>
                                            <div class="col-sm-1">
                                                <input type="number" class="form-control" id="price" name="price" aria-required="true" value="${compInfo.price}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>

                                            <label class="col-sm-1 control-label">层高</label>
                                            <div class="col-sm-1" style="position: relative">
                                                <input type="number" aria-required="true" class="form-control" id="lowLayerHigh" name="lowLayerHigh" value="${compInfo.lowLayerHigh}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                                <div style="position: absolute; top: 10px; right: 0px;">-</div>
                                            </div>
                                            <div class="col-sm-1">
                                                <input type="number" class="form-control" id="layerHigh" aria-required="layerHigh" value="${compInfo.layerHigh}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>

                                            <label class="col-sm-1 control-label">朝向</label>
                                            <div class="col-sm-2">
                                                <%--<input type="text" class="form-control" id="orientation" name="orientation" value="${compInfo.orientation}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>--%>
                                                <select class="col-md-2 form-control m-b" name="orientation" id="orientation" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                                    <option value="东" <c:if test="${compInfo.orientation=='东'}">selected</c:if> >东</option>
                                                    <option value="南" <c:if test="${compInfo.orientation=='南'}">selected</c:if> >南</option>
                                                    <option value="西" <c:if test="${compInfo.orientation=='西'}">selected</c:if> >西</option>
                                                    <option value="北" <c:if test="${compInfo.orientation=='北'}">selected</c:if> >北</option>
                                                    <option value="东南" <c:if test="${compInfo.orientation=='东南'}">selected</c:if> >东南</option>
                                                    <option value="东北" <c:if test="${compInfo.orientation=='东北'}">selected</c:if> >东北</option>
                                                    <option value="西南" <c:if test="${compInfo.orientation=='西南'}">selected</c:if> >西南</option>
                                                    <option value="西北" <c:if test="${compInfo.orientation=='西北'}">selected</c:if> >西北</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group">
                                            <div class="col-md-1" id="room-select">
                                                <input class="btn btn-info" id="room-modal" type="button" data-toggle="modal" data-target="#roomModal" data-whatever="formal" value="房间查找">
                                            </div>

                                            <input type="hidden" id="formal"/>

                                            <label class="col-sm-1 control-label">楼号</label>
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" id="floorNum" name="floorNum" value="${compInfo.floorNum}" readonly>
                                            </div>

                                            <label class="col-sm-1 control-label">楼层</label>
                                            <%--<div class="col-sm-1" style="position: relative">
                                                <input type="number" aria-required="true" class="form-control" id="lowFloorLayer" name="lowFloorLayer" value="${compInfo.lowFloorLayer}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                                <div style="position: absolute; top: 10px; right: 0px;">-</div>
                                            </div>--%>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="floorLayer" name="floorLayer" value="${compInfo.floorLayer}" readonly>
                                            </div>

                                            <label class="col-sm-1 control-label">分区</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="floorPartition" name="floorPartition" value="${compInfo.floorPartition}" readonly>
                                            </div>

                                            <label class="col-sm-1 control-label">房间</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="floorRoom" name="floorRoom" value="${compInfo.floorRoom}" readonly>
                                                <input type="hidden" id="ref-room-id"/>
                                            </div>
                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group">
                                            <div class="col-md-1" id="room-select">
                                                <input class="btn btn-info" type="button" data-toggle="modal" r data-target="#roomModal" data-whatever="alternative" value="房间查找">
                                            </div>
                                            <label class="col-sm-1 control-label">备选楼号</label>
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" id="floorNumSpare" disabled name="floorNumSpare" data-whatever="alternative" value="${compInfo.floorNumSpare}" readonly>
                                            </div>

                                            <label class="col-sm-1 control-label">备选楼层</label>
                                            <%--<div class="col-sm-1" style="position: relative">
                                                <input type="number" class="form-control" aria-required="true" id="lowFloorLayerSpare" name="lowFloorLayerSpare" value="${compInfo.lowFloorLayerSpare}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                                <div style="position: absolute; top: 10px; right: 0px;">-</div>
                                            </div>--%>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="floorLayerSpare" disabled name="floorLayerSpare" value="${compInfo.floorLayerSpare}" readonly>
                                            </div>

                                            <label class="col-sm-1 control-label">备选分区</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="floorPartitionSpare" disabled name="floorPartitionSpare" value="${compInfo.floorPartitionSpare}" readonly>
                                            </div>

                                            <label class="col-sm-1 control-label">备选房间</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="floorRoomSpare" disabled name="floorRoomSpare" value="${compInfo.floorRoomSpare}" readonly>
                                            </div>
                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group">
                                            <label class="col-sm-1 control-label">经营类目<font color="red">*</font></label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="businessType" name="businessType" value="${compInfo.businessType}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>
                                            <label class="col-sm-1 control-label">日均单量</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="number" name="number" value="${compInfo.number}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>
                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group">
                                            <label class="col-sm-1 control-label">前经营所在地<font color="red">*</font></label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="beforeseat" name="beforeseat" value="${compInfo.beforeseat}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
                                            </div>
                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group">
                                            <label class="col-sm-1 control-label">备注信息</label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="remarks" name="remarks" value="${compInfo.remarks}" <c:if test="${readonly eq true}">readonly="readonly"</c:if>>
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

                    <%--拜访记录--%>
                    <div id="tab-2" class="tab-pane">
                        <div class="panel-body">
                            <jsp:include page="../etopCompanyMaintain/etopCompanyMaintainList.jsp" />
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade in" id="roomModal" tabindex="-1" role="dialog" aria-labelledby="roomModalLabel" aria-hidden="true" style="display: none;padding-right: 6px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="roomModalLabel">房间选择</h4>
            </div>
            <div class="modal-body">

                <div class="wrapper wrapper-content animated fadeInRight">
                    <div class="row">
                        <div class="col-md-6">
                            <div id="floor-tree"></div>
                        </div>
                        <div class="col-md-6">

                            <select id="rooms-select" class="form-control" multiple>

                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<script src="<%=basePath%>/myjs/etopCompanyIntentionInfo.js"></script>
<script type="text/javascript">

    var basePath = "<%=basePath%>";

    var hrefs=location.href;
    var s=hrefs.indexOf("?");
    hrefs=hrefs.substr(s+1);//获取到了问号后面的参数
    function GetQueryString(name) {

        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = hrefs.substr(0).match(reg);
        if(r!=null)return  r[2]; return "";

    }

    var standard = "<i class='fa fa-times-circle'></i> ";

    $("#signupForm").validate({
        rules: {//这里加校验规则
            companyName: "required",
            contact: "required",
            companyCapital: "required",
            mobile: {
                required:true,
                digits:true
            },
            /*companyMobile: {
                required:true,
                digits:true
            },
            area: "required",
            smallArea: "required",
            price: "required",
            lowPrice: "required",
            orientation: "required",
            floorPartition: "required",
            floorLayer: "required",
            floorNum: "required",
            floorRoom: "required",
            layerHigh: "required",
            lowLayerHigh: "required",*/
            businessType: "required",
            beforeseat: "required"
        },
        messages: {//这里给对应的提示
            companyName: standard + "请输入公司名称 !",
            contact: standard + "请输入联系人姓名 !",
            companyCapital : standard + "请输入注册资金 !",
            mobile: {
                required: standard + "请输入电话号码 !",
                digits: standard + "请输入正确的电话格式 !"
            },
           /* companyMobile: {
                required: standard + "请输入电话号码 !",
                digits: standard + "请输入正确的电话格式 !"
            },
            area: standard + "请输入面积 !",
            smallArea: standard + "请输入面积 !",
            price: standard + "请输入价格 !",
            lowPrice: standard + "请输入价格 !",
            orientation: standard + "请输入朝向 !",
            floorPartition: standard + "请输入分区 !",
            floorLayer: standard + "请输入楼层 !",
            floorNum: standard + "请输入楼号 !",
            floorRoom: standard + "请输入房间 !",
            layerHigh: standard + "请输入层高 !",
            lowLayerHigh: standard + "请输入层高 !",*/
            businessType: standard + "请输入经验类目 !",
            beforeseat: standard + "请输入前经营所在地 !"
        },
        submitHandler: function(form){
            submit();  //去提交
        }
    })

    //更新公司保存
    function submit(){

        var params = {
            "id" : $("#id").val(),
            "number" : $("#number").val(),
            "companyName" : $("#companyName").val(), // 公司名称
            "companyType" : $("#companyType").val(), // 公司类型
            "companyCapital" : $("#companyCapital").val(), // 注册资金
            "contact" : $("#contact").val(), // 联系人
            "mobile" : $("#mobile").val(), // 电话
            "companyMobile" : $("#companyMobile").val(), // 公司电话
            "companyFax" : $("#companyFax").val(), // 公司传真
            "spareContact" : $("#spareContact").val(), // 备用联系人
            "spareMobile" : $("#spareMobile").val(), // 备用联系人电话
            "email" : $("#email").val(), // 电子邮件
            "smallArea" : $("#smallArea").val(), // 面积
            "area" : $("#area").val(), // 面积
            "lowPrice" : $("#lowPrice").val(), // 价格
            "price" : $("#price").val(), // 价格
            "orientation" : $("#orientation").val(), // 朝向
            "lowLayerHigh" : $("#lowLayerHigh").val(), // 层高
            "layerHigh" : $("#layerHigh").val(), // 层高
            "floorNum" :  $("#floorNum").val(), // 楼号
            "floorPartition" : $("#floorPartition").val(), // 分区
            "floorLayer" : $("#floorLayer").val(), // 楼层
            "floorRoom" : $("#floorRoom").val(), // 房间
            "floorNumSpare" : $("#floorNumSpare").val(), // 备选楼号
            "floorPartitionSpare" : $("#floorPartitionSpare").val(), // 备选分区
            "floorLayerSpare" : $("#floorLayerSpare").val(), // 备选楼层
            "floorRoomSpare" : $("#floorRoomSpare").val(), // 备选房间
            "businessType" : $("#businessType").val(),// 经营类目
            "beforeseat" : $("#beforeseat").val(), // 前经营所在地
            "remarks" : $("#remarks").val()// 备注
        }

        $.post("updateEtopCompanyIntentionInfo.do", params, function(data){

            if(data.status==10001){
                refreshCompanyIntentionList();
                swal({
                    title : "保存成功！",
                    type : "success",
                    confirmButtonText : "确定",
                    closeOnConfirm : true
                }, function() {
                    closeTotabs('<%=basePath%>/etopCompanyIntention/getCompInfoById.do','编辑公司','<%=basePath%>/etopCompanyIntention/etopCompanyIntentionList.do');
                });
            }else if(data.status==9999){
                swal({
                    title: data.msg,
                    type : "error",
                    confirmButtonText : "确定",
                    closeOnConfirm : true
                });
            } else{
                swal( "保存失败！", "","error");
            }
        });
    }

    //关闭table
    function closeTotabs(herf,txt,href2) {
        href2 = GetQueryString("herf2");
        window.parent.tabsclosed(herf,txt,href2);
    }
</script>

</body>
</html>