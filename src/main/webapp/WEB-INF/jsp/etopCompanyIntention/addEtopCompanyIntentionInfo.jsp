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
                </ul>
                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">

                            <div class="ibox float-e-margins">
                                <div class="ibox-content">
                                    <form method="get" class="form-horizontal" id="signupForm">
                                        <div class="form-group">

                                            <div>
                                                <input type="hidden" id="id">
                                            </div>

                                            <label class="col-sm-1 control-label">公司名称<font color="red">*</font></label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" placeholder="" id="companyName" name="companyName">
                                            </div>

                                            <label class="col-sm-1 control-label">公司类型<font color="red">*</font></label>
                                            <div class="col-sm-2">
                                                <select class="form-control m-b" id="companyType" name="companyType" style="float: left; margin-right: 1%;">
                                                    <option value="有限责任公司">有限责任公司</option>
                                                    <option value="股份有限公司">股份有限公司</option>
                                                </select>
                                            </div>

                                            <label class="col-sm-1 control-label">注册资金<font color="red">*</font></label>
                                            <div class="col-sm-2">
                                                <input type="number" class="form-control" id="companyCapital" name="companyCapital" aria-required="true">
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

                                            <label class="col-sm-1 control-label">公司电话</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="companyMobile" name="companyMobile">
                                            </div>

                                            <label class="col-sm-1 control-label">公司传真</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="companyFax" name="companyFax">
                                            </div>
                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group">
                                            <label class="col-sm-1 control-label">备用联系人</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="spareContact" name="spareContact">
                                            </div>

                                            <label class="col-sm-1 control-label">备用电话</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="spareMobile" name="spareMobile">
                                            </div>

                                            <label class="col-sm-1 control-label">对公邮箱</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="email" name="email" >
                                            </div>



                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group">
                                            <label class="col-sm-1 control-label">面积</label>
                                            <div class="col-sm-1" style="position: relative">
                                                <input type="number" class="form-control" id="smallArea" name="smallArea" aria-required="true">
                                                <div style="position: absolute; top: 10px; right: 0px;">-</div>
                                            </div>
                                            <div class="col-sm-1">
                                                <input type="number" class="form-control" id="area" name="area" aria-required="true">
                                            </div>

                                            <label class="col-sm-1 control-label">价格</label>
                                            <div class="col-sm-1" style="position: relative">
                                                <input type="number" class="form-control" id="lowPrice" name="lowPrice" aria-required="true">
                                                <div style="position: absolute; top: 10px; right: 0px;">-</div>
                                            </div>
                                            <div class="col-sm-1">
                                                <input type="number" class="form-control" id="price" name="price" aria-required="true">
                                            </div>

                                            <label class="col-sm-1 control-label">层高</label>
                                            <div class="col-sm-1" style="position: relative">
                                                <input type="number" class="form-control" id="lowLayerHigh" name="lowLayerHigh" aria-required="true">
                                                <div style="position: absolute; top: 10px; right: 0px;">-</div>
                                            </div>
                                            <div class="col-sm-1">
                                                <input type="number" class="form-control" id="layerHigh" name="layerHigh" aria-required="true">
                                            </div>

                                            <label class="col-sm-1 control-label">朝向</label>
                                            <div class="col-sm-2">
                                                <%--<input type="text" class="form-control" id="orientation" name="orientation">--%>
                                                <select class="col-md-2 form-control m-b" name="orientation" id="orientation">
                                                    <option value="">全部</option>
                                                    <option value="东">东</option>
                                                    <option value="南">南</option>
                                                    <option value="西">西</option>
                                                    <option value="北">北</option>
                                                    <option value="东南">东南</option>
                                                    <option value="东北">东北</option>
                                                    <option value="西南">西南</option>
                                                    <option value="西北">西北</option>
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
                                                <input type="text" class="form-control" id="floorNum" name="floorNum" readonly>
                                            </div>

                                            <label class="col-sm-1 control-label">楼层</label>
                                            <%--<div class="col-sm-1" style="position: relative">
                                                <input type="number" class="form-control" id="lowFloorLayer" name="lowFloorLayer" aria-required="true" value="${contract.building }" readonly>
                                                <div style="position: absolute; top: 10px; right: 0px;">-</div>
                                            </div>--%>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="floorLayer" name="floorLayer" readonly>
                                            </div>

                                            <label class="col-sm-1 control-label">分区</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="floorPartition" name="floorPartition" readonly>
                                            </div>

                                            <label class="col-sm-1 control-label">房间</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="floorRoom" name="floorRoom" readonly>
                                                <input type="hidden" id="ref-room-id"/>
                                            </div>
                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group">

                                            <div class="col-md-1" id="room-select">
                                                <input class="btn btn-info" type="button" data-toggle="modal" data-target="#roomModal" data-whatever="alternative" value="房间查找">
                                            </div>
                                            <label class="col-sm-1 control-label">备选楼号</label>
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" id="floorNumSpare" readonly name="floorNumSpare">
                                            </div>

                                            <label class="col-sm-1 control-label">备选楼层</label>
                                            <%--<div class="col-sm-1" style="position: relative">
                                                <input type="number" class="form-control" id="lowFloorLayerSpare" name="lowFloorLayerSpare" aria-required="true">
                                                <div style="position: absolute; top: 10px; right: 0px;">-</div>
                                            </div>--%>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="floorLayerSpare" name="floorLayerSpare" aria-required="true" readonly>
                                            </div>

                                            <label class="col-sm-1 control-label">备选分区</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="floorPartitionSpare" name="floorPartitionSpare" readonly>
                                            </div>

                                            <label class="col-sm-1 control-label">备选房间</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="floorRoomSpare" name="floorRoomSpare" readonly>
                                                <input type="hidden" id="room-id"/>
                                            </div>


                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group">
                                            <label class="col-sm-1 control-label">经营类目<font color="red">*</font></label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="businessType" name="businessType">
                                            </div>
                                            <label class="col-sm-1 control-label">日均单量</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" id="number" name="number">
                                            </div>
                                        </div>

                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group">
                                            <label class="col-sm-1 control-label">前经营所在地<font color="red">*</font></label>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" id="beforeseat" name="beforeseat">
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

<jsp:include page="../shared/js.jsp"/>
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
            /*area: "required",
            smallArea: "required",
            price: "required",
            lowPrice: "required",
            orientation: "required",
            floorPartition: "required",
            floorLayer: "required",
            lowFloorLayer: "required",
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
            /*area: standard + "请输入面积 !",
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
        	prove();  //去提交
        }
    })

    
    function prove(){  
        
        var companyName = $("#companyName").val();
        if(companyName == "")  
        {  
           alert("公司名称不能为空!");  
           return;  
        }  
        $.ajax({  
               type: "POST",      
                url: "<%=basePath%>/etopCompanyIntention/proveCompanyName.do",      
                data: {companyName: $("#companyName").val()}, 
                success: function(data){  
               if(data.status == 10001){     
                swal({
					title : "公司已存在！",
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
    
    //新增公司保存
    function submit(){

        var params = {
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
            "number" : $("#number").val(),// 经营类目
            "beforeseat" : $("#beforeseat").val(), // 前经营所在地
            "roomId" : $("#ref-room-id").val(), // 前经营所在地
            "remarks" : $("#remarks").val()// 备注
        }

        $.post("addEtopCompanyIntentionInfo.do",params,function(data){
            if(data.status==10001){
                refreshCompanyIntentionList();
                swal({
                    title : "保存成功！",
                    type : "success",
                    confirmButtonText : "确定",
                    closeOnConfirm : true
                }, function() {
                    location.reload();
                });
                $("#id").val(data.data);
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

</script>


</body>
</html>