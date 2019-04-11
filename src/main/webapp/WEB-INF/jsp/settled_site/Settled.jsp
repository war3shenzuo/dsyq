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
<title>入驻平台</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/sweetalert.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/hzw-city-picker.css"/>
<style>
	table p{ font-weight: 400}
</style>

</head>
<body>
	<jsp:include page="../header.jsp" flush="true" />
	
    <div class="mainBox">
    	<div class="success1" id="success1" style=" text-align: center; display: none;">
    		<h1 style=" font-size: 22px; padding: 20px 100px;">您的入驻申请已提交成功，待审核通过后，会以电子邮件的形式，发送账号信息到你填写的邮箱！</h1>
    		<img src="<%=basePath %>/img/success1.jpg"/>
    	</div>
    	
    	<div class="inapplbox" id="inapplbox" style="">
    	<img src="<%=basePath %>/img/401859468745617306.jpg"/>
    		<h1>入驻申请</h1>
    			<table border="0" cellspacing="0" cellpadding="0" style="margin-top:-50px;">
    				<tr>
    					<td><p>开园时间</p><input class="laydate-icon form-control layer-date"  name="open_time" id="open_time" value="" style="vertical-align: middle;height:33px;"></td>
    					<td><p>名<span style="display: inline-block;height: 24px; width: 28px;;"></span>称 </p><input type="text" name="park_name" id="park_name" value="" /></td>
    				</tr>
    				<tr>
    					<td><p>地<span style="display: inline-block;height: 24px; width: 28px;;"></span>址 </p><input type="text" name="address" id="address" value="" /></td>
    					<td><p>所属单位</p><input type="text" name="belong_unit" id="belong_unit" value="" /></td>
    				</tr>
    				<tr>
    					<td><p>运营单位</p><input type="text" name="operate_unit" id="operate_unit" value="" /></td>
    					<td><p>园区类型 </p><input type="text" name="park_type" id="park_type" value="" /></td>
    				</tr>
    				<tr>
    					<td><p>园区面积</p><input type="text" name="park_size" id="park_size" value="" /></td>
    					<td><p>园区出租率</p><input type="text" name="rental_rate" id="rental_rate" value="" /></td>
    				</tr>
    				<tr>
    					<td><p>联系人</p><input type="text" name="contacts" id="contacts" value="" /></td>
    					<td><p>联系电话</p><input type="text" name="mobile" id="mobile" value="" /></td>
    				</tr>
    				<tr>
    					<td><p>QQ</p><input type="text" name="qq" id="qq" value="" /></td>
    					<td><p>微信</p><input type="text" name="wechat" id="wechat" value="" /></td>
    				</tr>
    				<tr>
    					<td><p>邮箱</p><input type="email" name="email" id="email" value="" /></td>
    					<td><p>城市</p><input type="text" name="city" id="citycont"  value="" /></td>
    				</tr>
    				<tr >
    					<td style="height:10px;line-height:10px;font-size:12px;"><span style="color:red">*</span><e>(提交信息后将进行审核，审核结果将发往该邮箱，请正确填写邮箱地址！)</e></td>		
    				</tr>
    			</table>
    			
	    		<div class="midsub" style=" width:168px;">
	    		<input type="button" id="sub" value="提交" style="margin-right: 0; cursor: pointer;"/>
    			</div>
    		
    	</div>
    </div>
  <jsp:include page="../footer.jsp" flush="true" />
	 <script src="<%=basePath %>/js/layer/laydate/laydate.js"></script>
    <script>
        laydate({elem:"#open_time",event:"focus"});
    function submit(){
    	var open_time=$("#open_time").val();
    	var park_name=$("#park_name").val();
    	var address=$("#address").val();
    	var belong_unit=$("#belong_unit").val();
    	var operate_unit=$("#operate_unit").val();
    	var park_type=$("#park_type").val();
    	var park_size=$("#park_size").val();
    	var rental_rate=$("#rental_rate").val();
    	var contacts=$("#contacts").val();
    	var mobile=$("#mobile").val();
    	var qq=$("#qq").val();
    	var wechat=$("#wechat").val();
    	var aaaa=$('#city').attr('value');
		var city = aaaa.split('-')[1];
		var demo=$.trim(open_time);
		var demo2=$.trim(park_name);
		var email=$("#email").val();
        if($.trim(open_time) == ''){
        	swal("请输入开园时间!");
        	return;
        }
        if($.trim(park_name) == ''){
        	swal("请输入园区名称!");
        	return;
        }
        if($.trim(address) == ''){
            swal("请输入地址!");
        	return;
        }
        if($.trim(belong_unit) == ''){
            swal("请输入所属单位!");
        	return;
        }
        if($.trim(operate_unit) == ''){
            swal("请输入运营单位!");
        	return;
        }
        if($.trim(park_type) == ''){
            swal("请输入园区类型!");
        	return;
        }
        if($.trim(park_size) == ''){
            swal("请输入园区面积!");
        	return;
        }
        if($.trim(rental_rate) == ''){
            swal("请输入园区出租!");
        	return;
        }
        if($.trim(contacts) == ''){
            swal("请输入联系人!");
        	return;
        }
        if($.trim(mobile) == ''){
            swal("请输入联系电话!");
        	return;
        }
        if($.trim(email) == '')
        {
        	 swal("请输入邮箱!");
        		return;
        }
        if($.trim(qq) == ''){
            swal("请输入QQ!");
        	return;
        }
        if($.trim(wechat) == ''){
            swal("请输入微信号!");
        	return;
        }
        if($.trim(city) == ''){
            swal("请输入城市!");
        	return;
        }
        /*var re = /^[0-9-#]*$/;
        if (!re.test(contactPhone)) {
            $.toast("联系人电话格式不正确", 2000);
            return false;
        }*/
    	/*$.post("http://192.168.0.23:8081/etopManagement/etopCompanyIntention/addEtopCompanyIntentionInfo.do",
            {
                "companyName":companyName,
                "companyType":companyType,
                "companyCapital":companyCapital,
                "contact":contact,
                "mobile":mobile,
                "companyMobile":companyMobile,
                "companyFax":companyFax,
                "area":area,
                "layerHigh":layerHigh,
                "floorRoom":floorRoom,
                "businessType":businessType,
                "beforeseat":beforeseat,
                "remarks":remarks
            },function(data){
                if(data.status==10001){
                    swal( "保存成功！", "","success" );

                }else{
                    swal( "保存失败！", "","error");
                }
       });*/
        
    	$.ajax(  
    		    {  
    		        type:'post',  
    		        url:'<%=basePath %>/websettled/addApply.do',  
    		        data : {
    		        	"open_time":open_time,
    		        	"park_name":park_name,
    		        	"address":address,
    		        	"belong_unit":belong_unit,
    		        	"operate_unit":operate_unit,
    		        	"park_type":park_type,
    		        	"park_size":park_size,
    		        	"rental_rate":rental_rate,
    		        	"contacts":contacts,
    		       		"mobile":mobile,
    		       		"email":email,
    		       		"qq":qq,
    		       		"wechat":wechat,
    		       		"city":city
    		        },
    		        
    		        success  : function(data){
    		            if(data.status==10001){
    		               /*  swal( "你的入驻申请已提交，请耐心等待", "","success" ); */
    		            	document.getElementById("inapplbox").style.display="none";
							document.getElementById("success1").style.display="block";

    		            }else{
    		                swal( "提交失败", "","error");
    		            }
    		        }
    		    }  
    		);  
    }
    	
 	document.getElementById("sub").addEventListener("click",submit);
 	
 	
 	
 	
 	
 	var cityPicker = new HzwCityPicker({
 		data: data,
 		target: 'citycont',
 		valType: 'k-v',
 		hideCityInput: {
 			name: 'city',
 			id: 'city'
 		},
 		hideProvinceInput: {
 			name: 'province',
 			id: 'province'
 		}/* ,
 		callback: function(){
 			alert('OK');
 		} */
 	});

 	cityPicker.init();
    </script>
</body>
</html>
