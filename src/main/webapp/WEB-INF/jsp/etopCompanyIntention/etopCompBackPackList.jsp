<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>

<div class="ibox float-e-margins">

    <div class="ibox-content">
        <div class="row row-lg">
            <div class="col-sm-12">
                <div class="row">
                    <div class="col-md-2">
                        <label>公司名称</label>
                        <input type="text" placeholder="请输入公司名称" class="form-control" id="company">
                    </div>
                    <div class="col-md-2">
                        <label>经营类目</label>
                        <input type="text" placeholder="请输入经营类目" class="form-control" id="businessType">
                    </div>
                    <div class="col-md-2">
                        <label>注册资金</label>
                        <input type="number" placeholder="请输入注册资金" class="form-control" id="companyCapital" aria-required="true">
                    </div>
                    <div class="col-md-1">
                        <label>平台</label>
                        <select class="form-control m-b" id="businessPractice" name="businessPractice">
                            <option value="">全部</option>
                            <option value="淘宝">淘宝</option>
                            <option value="天猫">天猫</option>
                            <option value="京东">京东</option>
                            <option value="亚马逊">亚马逊</option>
                            <option value="实体店">实体店</option>
                            <option value="加盟商">加盟商</option>
                            <option value="代理">代理</option>
                            <option value="其他">其他</option>
                        </select>
                    </div>
                    <!-- <div class="col-md-1">
                        <label>公司状态</label>
                        <select class="form-control m-b" id="companyStatus" name="companyStatus">
                            <option value="">全部</option>
                            <option value="=1">正式</option>
                            <option value="=2">合同过期</option>
                            <option value="=3">合同终止</option>
                        </select>
                    </div> -->
                    <c:if test="${userType eq 4}">
                        <div class="col-md-2">
                            <label>选择园区</label>
                            <select class="form-control" id="parkBackId" name="parkBackId">
                                <c:forEach items="${parks}" var="park">
                                    <option value="${park.id}">${park.parkName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                    <div class="col-md-2">
                        <button class="btn btn-primary"
                                onclick="getEtopCompanyList()"
                                type="button" style="margin-top: 23px;;">搜索
                        </button>
                        <button class="btn btn-primary"
                                onclick="resetBack()"
                                type="button" style="margin-top: 23px;;">重置
                        </button>
                    </div>
                    <div class="hr-line-dashed" style="clear: both;"></div>
                </div>
            </div>
            <div class="col-sm-12">
                <!-- Example Events -->
                <div class="example-wrap">

                    <%--<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group"
                         <c:if test="${readonly}">style="display: none;"</c:if> >
                        <a class="btn btn-outline btn-default"
                            onclick="totabs('<%=basePath%>/etopCompany/addPage.do','新建公司');">
                            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                            <span>新建</span>
                        </a>
                        <button type="button" class="btn btn-outline btn-default" onclick="deleteCompBackPack()">
                            <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <span>删除</span>
                        </button>
                    </div>--%>

                    <table id="compBackPack"
                           data-mobile-responsive="true"
                           data-toggle="table"
                           data-url="<%=basePath%>/etopCompany/getEtopCompanyList.do?genre=${genre}"
                           data-data-type="json"
                           data-side-pagination="server"
                           data-pagination="true"
                           data-query-params="queryParamsBackPack"
                           data-page-list="[5, 10, 20, 50, 100, 200]"
                           data-striped="true"
                            >
                        <thead>
                        <tr>
                            <c:if test="${readonly eq false}">
                                <th data-field="state" data-checkbox="true"></th>
                            </c:if>
                            <th data-field="companyId" data-align="center" data-visible="false">公司id</th>
                            <th data-field="companyName" data-align="center">公司名称</th>
                            <th data-field="businessType" data-align="center">经营类目</th>
                            <th data-field="companyMobile" data-align="center">公司电话</th>
                            <th data-field="contact" data-align="center">联系人</th>
                            <th data-field="mobile" data-align="center">号码</th>
<!--                             <th data-field="companyStatus" data-align="center" data-formatter="formatterCompStatus">状态</th> -->
                            <th data-field="outAt" data-align="center">迁出时间</th>
                            <th data-field="remarks" data-align="center">备注</th>
                            <th data-align="center" data-formatter='formatterFunBack' >操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <!-- End Example Events -->
            </div>
        </div>
    </div>
</div>

</div>
<script type="text/javascript">

    var tableBackPack = $("#compBackPack");

    /*查询条件*/
    function queryParamsBackPack(params) {
        params.companyName = $("#company").val();
        params.companyCapital = $("#companyCapital").val();
        params.businessType = $("#businessType").val();
        params.companyStatus = $("#companyStatus").val();
        params.businessPractice = $("#businessPractice").val();
        params.parkId = $("#parkBackId").val();
        return params
    }

    function resetBack(){
        $("#company").val("");
        $("#companyCapital").val("");
        $("#companyStatus").val("");
        $("#businessType").val("");
        $("#businessPractice").val("");
    }

    function getEtopCompanyList(){

        tableBackPack.bootstrapTable('refresh',{url:'<%=basePath%>/etopCompany/getEtopCompanyList.do?genre=${genre}'});

    }

    tableBackPack.on('click-row.bs.table', function (row,obj,index) {

        $("#companyId").val(obj.companyId);
    })

    /*增加表格按钮**/
    function formatterFunBack(value,row,index){

        var getCompBackInfo = "getCompBackInfo('"+row.companyId+"')";
        var enter = "enter('" + row.companyId + "')";
        return '<button class="btn btn-info" onclick="'+
                getCompBackInfo+'" type="button" >详情</button> '+
                '&nbsp;&nbsp; ' +
                '<button class="btn btn-warning" <c:if test="${readonly eq true}">style="display: none;"</c:if> onclick="' +
                enter + '" type="button" >入驻</button>'
    }

    function getCompBackInfo(companyId){
        $.post(totabs('<%=basePath %>/etopCompany/getCompInfoById.do?companyId='+companyId+'&readonly='+${readonly},'编辑公司信息'))
    }

    function enter(companyId){
        swal({
                title: "确认入驻?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是",
                cancelButtonText: "否",
                closeOnConfirm: false
            }, function () {
                $.get("<%=basePath %>/etopCompany/enter.do?genre=${genre}", {
                    "companyId": companyId
                }, function (data) {
                    if (data.status == 10001) {
                        swal({
                            title: "成功入驻! ",
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableBack('<%=basePath %>/etopCompany/getEtopCompanyList.do?genre=${genre}');
                        });
                    } else {
                        swal({
                            title: data.msg,
                            type: "error",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        }, function () {
                            tableBack('<%=basePath %>/etopCompany/getEtopCompanyList.do?genre=${genre}');
                        });
                    }

                })
            }
        );
    }

    /*function deleteCompBackPack(){

        var selections = table.bootstrapTable('getSelections');
        if(selections.length){
            var companyIds = [];
            if (selections.length == 0)
                return;
            for (var i = 0; i < selections.length; i++) {
                companyIds = companyIds + selections[i].companyId + ",";
            }
            var reg=/,$/gi;
            companyIds = companyIds.replace(reg,"");

            swal({
                    title: "确定删除这"+selections.length+"家公司?",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "是",
                    cancelButtonText: "否",
                    closeOnConfirm: false
                }, function () {
                    $.get("deleteEtopCompanyInfo.do", {
                        "companyIds": companyIds
                    }, function (data) {
                        if (data.status == 10001) {
                            swal({
                                title: "成功删除" + data.data + "家公司！",
                                type: "success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            }, function () {
                                tableRefresh('getEtopCompanyList.do');
                            });
                        } else {
                            swal({
                                title: data.msg,
                                type: "error",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            }, function () {
                                tableRefresh('getEtopCompanyList.do');
                            });
                        }

                    })
                }
            );
        }else{
            swal({
                title: "请先选择公司!",
                timer: 1000,
                showConfirmButton: false
            });
        }

    }*/

    //新增公司table
    function totabs(herf,msg) {
        window.parent.addTable(herf,msg);
    }

    function tableBack(dataUrl){

        tableBackPack.bootstrapTable('refresh',{url:dataUrl});

    }

</script>