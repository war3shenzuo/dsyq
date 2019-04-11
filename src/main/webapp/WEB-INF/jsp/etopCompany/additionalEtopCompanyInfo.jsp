<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.etop.management.properties.ImgProperties"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<div class="ibox float-e-margins">

    <div class="ibox-title">
        <h5>
            <normal>对公信息</normal>
        </h5>
    </div>

    <div class="ibox-content">
        <form method="get" class="form-horizontal">
            <div class="form-group">

                <label class="col-sm-1 control-label">公司对公账户</label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" id="bankAccount" name="bankAccount">
                </div>

                <label class="col-sm-1 control-label">开户银行</label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" id="openBank" name="openBank">
                </div>

                <label class="col-sm-1 control-label">开户名称</label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" id="openName" name="openName">
                </div>

            </div>

            <div class="hr-line-dashed"></div>

            <div class="form-group">
                <label class="col-sm-1 control-label">备用账户</label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" id="bankAccountSpare" name="bankAccountSpare">
                </div>

                <label class="col-sm-1 control-label">备用开户银行</label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" id="openBankSpare" name="openBankSpare">
                </div>

                <label class="col-sm-1 control-label">备用开户名称</label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" id="openNameSpare" name="openNameSpare">
                </div>
            </div>

            <div class="hr-line-dashed"></div>

            <div class="form-group">
                <div>
                    <div class="col-sm-2">
                        <div>
                            <label class="font-noraml">三证（单选）</label>
                            <input type="file" id="file1" name="file1" class="form-control" style="margin-bottom:10px; ">
                            <img style=" display: none;" id="fileImg1" src="" width="100%" height="200%"/>
                        </div>
                    </div>
                    <div class="col-sm-1">
                        <button class="btn btn-group" type="button" onclick="ajaxFileUpload('file1')" style=" margin-top: 23px;;">上传</button>
                        <input type="hidden" class=" form-control" placeholder="" id="threePaper">
                    </div>

                    <div class="col-sm-2">
                        <div>
                            <label class="font-noraml">其他证件（单选）</label>
                            <input type="file" id="file2" name="file2" class="form-control" style="margin-bottom:10px; ">
                            <img style="display: none;" id="fileImg2" src="" width="100%" height="200%"/>
                        </div>
                    </div>
                    <div class="col-sm-1">
                        <button class="btn btn-group" type="button" onclick="ajaxFileUpload('file2')" style=" margin-top: 23px;;">上传</button>
                        <input type="hidden" class=" form-control" placeholder="" id="otherThreePaper">
                    </div>

                    <div class="col-sm-2">
                        <div>
                            <label class="font-noraml">其他资料（单选）</label>
                            <input type="file" id="file3" name="file3" class="form-control" style="margin-bottom:10px; ">
                            <img style="display: none;" id="fileImg3" src="" width="100%" height="200%"/>
                        </div>
                    </div>
                    <div class="col-sm-1">
                        <button class="btn btn-group" type="button" onclick="ajaxFileUpload('file3')" style=" margin-top: 23px;;">上传</button>
                        <input type="hidden" class=" form-control" placeholder="" id="otherPaper">
                    </div>

                </div>
            </div>

        </form>
    </div>


    <div class="ibox-title">
        <h5>
            <normal>法人信息</normal>
        </h5>
    </div>
    <div class="ibox-content">
        <form method="get" class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-1 control-label">法人姓名</label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="" id="legalPersonName"
                           name="legalPersonName">
                </div>

                <label class="col-sm-1 control-label">身份证号</label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="" id="leaglPersonCode"
                           name="leaglPersonCode">
                </div>

                <label class="col-sm-1 control-label">电话</label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="" id="leaglPersonMobile"
                           name="leaglPersonMobile">
                </div>

                <label class="col-sm-1 control-label">其他联系方式</label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="" id="leaglOtherContact"
                           name="leaglOtherContact">
                </div>

            </div>

            <div class="hr-line-dashed"></div>

            <div class="form-group">

                <label class="col-sm-1 control-label">生日</label>

                <div class="col-sm-2">
                    <div class="input-group date">
                        <span class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </span>
                        <input type="text" class="datepicker form-control" id="leaglPersonBirthday" name="leaglPersonBirthday">
                    </div>
                </div>

                <label class="col-sm-1 control-label">法人学历</label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" id="leaglPersonDegrees" name="leaglPersonDegrees">
                </div>

                <label class="col-sm-1 control-label">毕业院校</label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" id="leaglPersonSchool" name="leaglPersonSchool">
                </div>

                <label class="col-sm-1 control-label">毕业时间</label>

                <div class="col-sm-2">
                    <div class="input-group date">
                        <span class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </span>
                        <input type="text" class="datepicker form-control" id="leaglPersonGraduation" name="leaglPersonGraduation">
                    </div>
                </div>

            </div>

            <div class="hr-line-dashed"></div>

            <div class="form-group">

                <label class="col-sm-1 control-label">家庭住址</label>

                <div class="col-sm-6">
                    <input type="text" class="form-control" id="leaglPersonAddress"
                           name="leaglPersonAddress">
                </div>

            </div>

            <div class="hr-line-dashed"></div>

            <div class="form-group">
                <div>
                    <div class="col-sm-2">
                        <div>
                            <label class="font-noraml">学位证（单选）</label>
                            <input type="file" id="file4" name="file4" class="form-control" style="margin-bottom:10px; ">
                            <img style="display: none;" id="fileImg4" src="" width="100%" height="200%"/>
                        </div>
                    </div>
                    <div class="col-sm-1">
                        <button class="btn btn-group" type="button" onclick="ajaxFileUpload('file4')" style=" margin-top: 23px;;">上传</button>
                        <input type="hidden" class=" form-control" placeholder="" id="degree">
                    </div>

                    <div class="col-sm-2">
                        <div>
                            <label class="font-noraml">毕业证（单选）</label>
                            <input type="file" id="file5" name="file5" class="form-control" style="margin-bottom:10px; ">
                            <img style="display: none;" id="fileImg5" src="" width="100%" height="200%"/>
                        </div>
                    </div>
                    <div class="col-sm-1">
                        <button class="btn btn-group" type="button" onclick="ajaxFileUpload('file5')" style=" margin-top: 23px;;">上传</button>
                        <input type="hidden" class=" form-control" placeholder="" id="diplomaImg">
                    </div>

                    <div class="col-sm-2">
                        <div>
                            <label class="font-noraml">其他证件（单选）</label>
                            <input type="file" id="file6" name="file6" class="form-control" style="margin-bottom:10px; ">
                            <img style="display: none;" id="fileImg6" src="" width="100%" height="200%"/>
                        </div>
                    </div>
                    <div class="col-sm-1">
                        <button class="btn btn-group" type="button" onclick="ajaxFileUpload('file6')" style=" margin-top: 23px;;">上传</button>
                        <input type="hidden" class=" form-control" placeholder="" id="otherImg">
                    </div>

                    <div class="col-sm-2">
                        <div>
                            <label class="font-noraml">身份证复印件（单选）</label>
                            <input type="file" id="file7" name="file7" class="form-control" style="margin-bottom:10px; ">
                            <img style="display: none;" id="fileImg7" src="" width="100%" height="200%"/>
                        </div>
                    </div>
                    <div class="col-sm-1">
                        <button class="btn btn-group" type="button" onclick="ajaxFileUpload('file7')" style=" margin-top: 23px;;">上传</button>
                        <input type="hidden" class=" form-control" placeholder="" id="cardImg">
                    </div>
                </div>
            </div>


        </form>
    </div>


    <div class="ibox-title">
        <h5>
            <normal>经营信息</normal>
        </h5>
    </div>
    <div class="form-group">
        <div class="ibox-content">

            <table id="tabdetail" border="0" cellspacing="0" cellpadding="5"  width="100%" class="tableStyle table table-bordered">
                <thead>
                    <tr>
                        <th width="60" height="40" align="center" valign="middle">序号</th>
                        <th align="center" width="100" valign="middle">经营方式</th>
                        <th align="center" width="140" valign="middle">网址</th>
                       <%-- <th align="center" width="140" valign="middle">实体店</th>
                        <th width="100" align="center" valign="middle">招商加盟</th>
                        <th width="100" align="center" valign="middle">代理</th>
                        <th width="100" align="center" valign="middle">其他</th>--%>
                    </tr>
                </thead>
                <tbody id="tbodyId">
                    <tr>
                        <td align="center" valign="middle">
                            <span class="num">1</span>
                            <span class="actionArea">
                                <a href="javascript:;" onClick="removerow(this)" class="delLite"></a>
                            </span>
                        </td>

                        <td align="left" valign="middle" class="highlight">
                            <select class="col-md-2 form-control m-b" name="businessPractice" id="businessPractice">
                                <option value="淘宝">淘宝</option>
                                <option value="天猫">天猫</option>
                                <option value="京东">京东</option>
                                <option value="亚马逊">亚马逊</option>
                                <option value="实体店">实体店</option>
                                <option value="加盟商">加盟商</option>
                                <option value="代理">代理</option>
                                <option value="其他">其他</option>
                            </select>
                        </td>
                        <td align="left" valign="middle" class="highlight">
                            <input name="electronicRetailing" type="text" class=" form-control"/>
                        </td>
                        <%--<td align="left" valign="middle" class="highlight">
                            <input name="physicalStore" type="text" class=" form-control"/>
                        </td>
                        <td align="left" valign="middle" class="highlight">
                            <input name="investmentJoin" type="text" class=" form-control"/>
                        </td>
                        <td align="left" valign="middle" class="highlight">
                            <input name="agency" type="text" class=" form-control"/>
                        </td>
                        <td align="left" valign="middle" class="highlight">
                            <input name="other" type="text" class=" form-control"/>
                        </td>--%>
                    </tr>
                </tbody>
            </table>

            <table width="100%" border="0" cellspacing="0" cellpadding="5" class="tableStyle" style="border-top: 0px;">
                <tbody>
                <tr>
                    <td colspan="2" valign="top" style="border-right: 0px;">
                        <a id="aaddrow" href="javascript:;" onclick="addrow(); initorder();" class="btn btn-primary">
                            增加行
                        </a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="hr-line-dashed"></div>

    <div class="form-group">
        <div class="col-sm-4 col-sm-offset-5">
            <a class="btn btn-primary" onclick="submitInfo()">确认并保存</a>
        </div>
    </div>
    </form>
</div>

<script type="text/javascript">

    function ajaxFileUpload(fileId) {

        $.ajaxFileUpload({
            url : '<%=basePath%>/etopCompany/uploadImg.do?companyId=${companyId}',
            secureuri : false,
            fileElementId : fileId,
            dataType : 'json',
            data : {},
            success : function(data, status) {
                if(data.status==10001){
                    if(fileId=='file1'){
                        $("#fileImg1").attr('src','<%=ImgProperties.LOAD_PATH%>'+data.data.path);
                        $("#threePaper").val(data.data.path);
                        $("#fileImg1").show();
                    }else if(fileId == 'file2'){
                        $("#fileImg2").attr('src','<%=ImgProperties.LOAD_PATH%>'+data.data.path);
                        $("#otherThreePaper").val(data.data.path);
                        $("#fileImg2").show();
                    }else if(fileId == 'file3'){
                        $("#fileImg3").attr('src','<%=ImgProperties.LOAD_PATH%>'+data.data.path);
                        $("#otherPaper").val(data.data.path);
                        $("#fileImg3").show();
                    }else if(fileId == 'file4'){
                        $("#fileImg4").attr('src','<%=ImgProperties.LOAD_PATH%>'+data.data.path);
                        $("#degree").val(data.data.path);
                        $("#fileImg4").show();
                    }else if(fileId == 'file5'){
                        $("#fileImg5").attr('src','<%=ImgProperties.LOAD_PATH%>'+data.data.path);
                        $("#diplomaImg").val(data.data.path);
                        $("#fileImg5").show();
                    }else if(fileId == 'file6'){
                        $("#fileImg6").attr('src','<%=ImgProperties.LOAD_PATH%>'+data.data.path);
                        $("#otherImg").val(data.data.path);
                        $("#fileImg6").show();
                    }else{
                        $("#fileImg7").attr('src','<%=basePath%>'+data.data.path);
                        $("#cardImg").val(data.data.path);
                        $("#fileImg7").show();
                    }
                    swal({
                        title: "上传成功!",
                        timer: 1000,
                        showConfirmButton: false
                    });
                }else if(data.status == 10003){
                    swal({
                        title: "请先保存公司基本信息!",
                        timer: 1000,
                        showConfirmButton: false
                    });

                }else{
                    swal({
                        title: "上传失败!",
                        timer: 1000,
                        showConfirmButton: false
                    });
                }
            },
            error : function(data, status, e) {
                swal({
                    title: "上传失败!",
                    timer: 1000,
                    showConfirmButton: false
                });
            }
        })

        return false;

    }

    //公司附加信息保存
    function submitInfo(){

        var business = [];

        var trList = $("#tbodyId").children("tr")
        for (var i=0; i<trList.length; i++) {
            var tdArr = trList.eq(i).find("td");
            var businessPractice = tdArr.eq(1).find("input").val();//经营方式
            var electronicRetailing = tdArr.eq(2).find("input").val();// 网商
            var physicalStore = tdArr.eq(3).find("input").val();//  实体店
            var investmentJoin = tdArr.eq(4).find("input").val();// 招商加盟
            var agency = tdArr.eq(5).find("input").val();// 代理
            var other = tdArr.eq(6).find("input").val();//  其他
            var createdAt = new Date().toLocaleDateString();
            var updatedAt = new Date().toLocaleDateString();
            business.push(
                {
                    companyId : '${companyId}',
                    businessPractice : businessPractice,
                    electronicRetailing : electronicRetailing,
                    physicalStore : physicalStore,
                    investmentJoin : investmentJoin,
                    agency : agency,
                    other : other,
                    createdAt : createdAt,
                    updatedAt : updatedAt
                }
            );
        }

        var params = {
            "etopCompanyBusinesses" : JSON.stringify(business),
            "companyId" : '${companyId}',
            "bankAccount" : $("#bankAccount").val(),
            "openBank" : $("#openBank").val(),
            "openName" : $("#openName").val(),
            "bankAccountSpare" : $("#bankAccountSpare").val(),
            "openBankSpare" : $("#openBankSpare").val(),
            "openNameSpare" : $("#openNameSpare").val(),
            "threePaper" : $("#threePaper").val(),
            "otherThreePaper" : $("#otherThreePaper").val(),
            "otherPaper" : $("#otherThreePaper").val(),
            "legalPersonName" : $("#legalPersonName").val(),
            "leaglPersonMobile" : $("#leaglPersonMobile").val(),
            "leaglOtherContact" : $("#leaglOtherContact").val(),
            "email" : $("#email").val(),
            "leaglPersonCode" : $("#leaglPersonCode").val(),
            "leaglPersonDegrees" : $("#leaglPersonDegrees").val(),
            "leaglPersonSchool" : $("#leaglPersonSchool").val(),
            "leaglPersonGraduation" : $("#leaglPersonGraduation").val(),
            "leaglPersonBirthday" : $("#leaglPersonBirthday").val(),
            "leaglPersonAddress" : $("#leaglPersonAddress").val(),
            "degreeImg" : $("#degree").val(),
            "diplomaImg" : $("#diplomaImg").val(),
            "otherImg" : $("#otherImg").val(),
            "cardImg" : $("#cardImg").val()
        }

        $.post("addAdditionalEtopCompanyInfo.do",params,function(data){
            if(data.status==9999){
                swal({
                    title: "请先保存公司基本信息! ",
                    timer: 1000,
                    showConfirmButton: false
                });
            }else if(data.status==10001){
                swal( "保存成功！", "","success");
            }else{
                swal( "保存失败！", "","error");
            }
        });

    }

    //初始化序号子表
    function initorder() {
        var num = 1;
        $("#tabdetail tr:gt(0)").find(".num").each(function() {
            $(this).html(num);
            num = num + 1;
        });
    }
    // 删除行子表
    function removerow(obj) {
        if ($("#tabdetail tr:gt(0)").find(".num").length == 1) {
            swal("列表只有一行，不能删除!");
            return;
        }
        $(obj).parent().parent().parent().remove();
        initorder();
    }
    //增加行子表
    function addrow() {

        var newrow = $("#tabdetail>tbody tr:first").clone();
        $(newrow).find("input[name='businessPractice']").val("");//经营方式
        $(newrow).find("input[name='electronicRetailing']").val("");//网商
        $(newrow).find("input[name='physicalStore']").val(""); //实体店
        $(newrow).find("input[name='investmentJoin']").val("");//招商加盟
        $(newrow).find("input[name='agency']").val("");//代理
        $(newrow).find("input[name='other']").val("");//其他
        $("#tabdetail tr:last").after(newrow);
        // 初始化计算方法
        return addrow;
    }

</script>
