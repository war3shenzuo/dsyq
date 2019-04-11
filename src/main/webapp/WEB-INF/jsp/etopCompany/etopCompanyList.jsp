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
        <title>客户列表-客户管理</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <jsp:include page="../shared/css.jsp"/>
    </head>
    <body class="gray-bg">
        <input type="hidden" id="companyId" >

        <div class="wrapper wrapper-content animated fadeInRight">

            <!-- Panel Other -->
            <div class="ibox float-e-margins">

                <div class="ibox-content">
                    <div class="row row-lg">
                        <div class="col-sm-12">
                            <div class="row">
                                <div class="col-md-2">
                                    <label>公司名称</label>
                                    <input type="text" placeholder="公司名称" class="form-control" id="companyName">
                                </div>
                               <!--  <div class="col-md-2">
                                    <label>公司状态</label>
                                    <select class="form-control m-b" id="companyStatus" name="companyStatus">
                                        <option value="">全部</option>
                                        <option value="=1">正式</option>
                                        <option value="=2">合同过期</option>
                                        <option value="=3">合同终止</option>
                                    </select>
                                </div> -->
                                
                                <div class="col-md-2">
                                    <label>注册资金</label>
                                    <input type="number" placeholder="请输入注册资金" class="form-control" id="companyCapital" aria-required="true">
                                </div>
                                <div class="col-md-1" style="position: relative">

                                    <label>员工人数</label> <input type="number" placeholder="请输入员工人数"
                                                               class="form-control" id="employeesNums1" aria-required="true">
                                    <div style="position: absolute; top: 30px; right: 0px;">-</div>
                                </div>
                                <div class="col-sm-1" style="position: relative">
                                    <label>&nbsp;</label> <input type="number" placeholder="请输入员工人数"
                                                                 class="form-control" id="employeesNums2" aria-required="true">
                                </div>
                                
                                <div class="col-md-1" style="position: relative">

                                    <label>月营业额</label> <input type="number" placeholder="请输入月营业额"
                                                               class="form-control" id="aveMonthTurnover1" aria-required="true">
                                    <div style="position: absolute; top: 30px; right: 0px;">-</div>
                                </div>
                                <div class="col-sm-1" style="position: relative">
                                    <label>&nbsp;</label> <input type="number" placeholder="请输入月营业额"
                                                                 class="form-control" id="aveMonthTurnover2" aria-required="true">
                                </div>
                            </div>
                            <div class="hr-line-dashed" style="clear: both;"></div>
                            <div class="row">
                               
                                <div class="col-md-2">
                                    <label>经营类目</label>
                                    <input type="text" placeholder="请输入经营类目" class="form-control" id="businessType">
                                </div>
                                 
                                <div class="col-md-2">
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
                                <c:if test="${userType eq 4}">
                                    <div class="col-md-2">
                                        <label>选择园区</label>
                                        <select class="form-control" id="parkId" name="parkId">
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
                                            onclick="resetCondition()"
                                            type="button" style="margin-top: 23px;;">重置
                                    </button>
                                    <button class="btn btn-primary"
                                     		onclick="AutoExcel()"
                                     		type="button" style="margin-top: 23px;;">导出
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
                                    <button type="button" class="btn btn-outline btn-default" onclick="deleteComp()">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <span>删除</span>
                                    </button>
                                </div>--%>

                                <table id="compTableEvents"
                                       data-mobile-responsive="true"
                                       data-toggle="table"
                                       data-url="<%=basePath%>/etopCompany/getEtopCompanyList.do"
                                       data-data-type="json"
                                       data-side-pagination="server"
                                       data-pagination="true"
                                       data-query-params="queryParams"
                                       data-page-list="[5, 10, 20, 50, 100, 200]"
                                       data-striped="true"
                                        >
                                    <thead>
                                    <tr>
                                        <c:if test="${readonly eq false}">
                                            <th data-field="state" data-checkbox="true"></th>
                                        </c:if>
                                        <th data-field="companyId" data-align="center" data-visible="false">公司id</th>
                                        <th data-field="employeesNums" data-align="center" data-visible="false">员工人数</th>
                                        <th data-field="companyName" data-align="center">公司名称</th>
                                        <th data-field="businessType" data-align="center">经营类目</th>
                                        <th data-field="companyMobile" data-align="center">公司电话</th>
                                        <th data-field="contact" data-align="center">联系人</th>
                                        <th data-field="mobile" data-align="center">联系人号码</th>
<!--                                         <th data-field="companyStatus" data-align="center" data-formatter="formatterCompStatus">状态</th> -->
                                        <th data-field="inAt" data-align="center">迁入时间</th>
                                        <th data-field="aveMonthTurnover" data-align="center">月营业额</th>
                                        <th data-field="remarks" data-align="center"  data-width="30%">备注</th>
                                        <th data-align="center" data-formatter='formatterFun' >操作</th>
                                    </tr>
                                    
                                    </thead>
                                </table>
                            </div>
                            <!-- End Example Events -->
                        </div>
                    </div>
                </div>
            </div>

            <!-- End Panel Other -->
        </div>
        <jsp:include page="../shared/js.jsp"/>
        <script type="text/javascript">

            var table = $("#compTableEvents");

            /*查询条件*/
            function queryParams(params) {
                params.companyName = $("#companyName").val();
                params.companyCapital = $("#companyCapital").val();
                params.employeesNums1 = $("#employeesNums1").val();
                params.employeesNums2 = $("#employeesNums2").val();
                params.aveMonthTurnover1 = $("#aveMonthTurnover1").val();
                params.aveMonthTurnover2 = $("#aveMonthTurnover2").val();
                params.businessType = $("#businessType").val();
                params.companyStatus = $("#companyStatus").val();
                params.businessPractice = $("#businessPractice").val();
                params.parkId = $("#parkId").val();
                return params
            }

            function resetCondition(){
                $("#companyName").val("");
                $("#companyCapital").val("");
                $("#employeesNums1").val("");
                $("#employeesNums2").val("");
                $("#aveMonthTurnover1").val("");
                $("#aveMonthTurnover2").val("");
                $("#companyStatus").val("");
                $("#businessType").val("");
                $("#businessPractice").val("");
            }

            function AutoExcel() {
    			var parkId = $("#parkId option:selected").val();
    			if(parkId == undefined)
    				var parkId = "";
    			var companyName = $("#companyName").val();
    			var companyCapital = $("#companyCapital").val();
    			var employeesNums1 = $("#employeesNums1").val();
    			var employeesNums2 = $("#employeesNums2").val();
    			var aveMonthTurnover1 = $("#aveMonthTurnover1").val();
    			var aveMonthTurnover2 = $("#aveMonthTurnover2").val();
    			var businessType = $("#businessType").val();
    			var companyStatus = $("#companyStatus").val();
    			var businessPractice = $("#businessPractice").val();
    			var type = "7";
    			
    			var param={
    					
    			     	"parkId":$("#parkId").val(),
    			     	"companyName":$("#companyName").val(),
    					"companyCapital" : $("#companyCapital").val(),
    					"employeesNums1" : $("#employeesNums1").val(),
    					"employeesNums2" : $("#employeesNums2").val(),
    					"aveMonthTurnover1" : $("#aveMonthTurnover1").val(),
    					"aveMonthTurnover2" : $("#aveMonthTurnover2").val(),
    					"businessType" : $("#businessType").val(),
    					"companyStatus" : $("#companyStatus").val(),
    					"businessPractice" : $("#businessPractice").val(),
    					"type" : $("#type").val()
    			}
    				location.href = '<%=basePath%>/exportUtil/createXls.do?parkId=' + parkId + '&type=' + type + '&businessPractice=' + businessPractice+ '&companyName=' + companyName+ '&businessType=' + businessType+ '&companyStatus=' + companyStatus;
    			
            }
            
            function getEtopCompanyList(){

                table.bootstrapTable('refresh',{url:'<%=basePath%>/etopCompany/getEtopCompanyList.do'});

            }

            table.on('click-row.bs.table', function (row,obj,index) {

                $("#companyId").val(obj.companyId);
            })

            /*增加表格按钮**/
            function formatterFun(value,row,index){

                var getCompInfo = "getCompInfo('"+row.companyId+"')";
                var backPark = "backPark('" + row.companyId + "')";
                var generated =  "generated('" + row.companyId + "')";
                var res = '<button class="btn btn-info" onclick="'+
                        getCompInfo + '" type="button" >详情</button> ' +
                        '&nbsp;&nbsp; ';
                if(row.companyStatus == '3'){
                    res = res + '<button class="btn btn-danger" <c:if test="${readonly eq true}">style="display: none;"</c:if> onclick="' +
                            backPark + '" type="button" >退园</button> ';
                }
                if(row.userName == null){
                    res = res + '<button class="btn btn-primary" <c:if test="${readonly eq true}">style="display: none;"</c:if> onclick="' +
                            generated + '" type="button" >生成账号</button> ';
                }
                return res
            }

            function getCompInfo(companyId){
                $.post(totabs('<%=basePath %>/etopCompany/getCompInfoById.do?companyId='+companyId+'&readonly='+${readonly},'编辑公司信息'))
            }

            function backPark(companyId){
                swal({
                        title: "确认退园? ",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "是",
                        cancelButtonText: "否",
                        closeOnConfirm: false
                    }, function () {
                        $.get("backPark.do", {
                            "companyId": companyId
                        }, function (data) {
                            if (data.status == 10001) {
                                swal({
                                    title: "成功退园! ",
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
            }

            function generated(companyId){
                $.post('<%=basePath %>/etopCompany/generatedAccount.do?companyId='+companyId, function(data){
                    if (data.status == 10001) {
                        swal({
                            title: data.msg,
                            text: "账号：" + data.data.userName + "    " + "密码：123456",
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
                });
            }

            function deleteComp(){

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
                            title: "确定删除这"+selections.length+"条通知?",
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
                                        title: "成功删除" + data.data + "条通知!",
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
                        title: "请先选择通知!",
                        timer: 1000,
                        showConfirmButton: false
                    });
                }

            }

            //新增公司table
            function totabs(herf,msg) {
        		window.parent.addTable(herf,msg);
        	}

        </script>
    </body>
</html>