<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="com.etop.management.properties.ImgProperties" %>
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
    <title>在线招商</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/etopclub.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/xueyuan.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/hzw-city-picker.css"/>
</head>
  <body>
    
    <jsp:include page="../header.jsp" flush="true" />
  
    <div class="wrap">
      <div id="demo01" class="flexslider bigimg">
        <ul class="slides">
          <li><div class="img"><img src="<%=basePath %>/img/362459045591807995.jpg"  alt="" /></div></li>
          <li><div class="img"><img src="<%=basePath %>/img/354871270460500279.jpg"  alt="" /></div></li>
        </ul>
      </div>
      <div class="middletitle">
            <a href="#">城市</a>
            <span class="greyline">&nbsp;</span>  
            <a href="javascript:" id="ningbo">全部</a>    
            <input type="text" id="cityChoice" style=" margin: 0 0 0 15px;" placeholder="搜索城市"> 
                
      </div>
  
  
      <div class="cont" id="cont" >
      </div>  
      <div class="foot">
          <ul class="pagination pagination-sm " id="page">
            
          </ul>
      </div>
      
    </div>
    
        <jsp:include page="../footer.jsp" flush="true" />
  <script>
  $(function(){

    $('#demo01').flexslider({
      animation: "slide",
      direction:"horizontal",
      easing:"swing"
    });
    
  });
  
  
  var nowPage=1;
  var dataLength=0;
  var rows=6;
  var nowcity='';
  var strs= new Array(); //定义一数组 
  var parkImgs="";
  var nowparkGroupId=GetQueryString("parkGroupId");
  
var allPage="";
var nowMaxPage="5";         //此刻分页码能看到的最大页码
var nowMinPage="1";         //此刻分页码能看到的最小页码
  
  $(document).ready(function(){
    $.post("<%=basePath %>/webolmerchants/getWebpark.do",{"parkGroupId":nowparkGroupId},function(data){
     
     	dataLength=data.length;
      	allPage = Math.ceil(dataLength/rows);
		paging('page',allPage);
      for(var j=0;j<rows;j++)
      {
    	  
        if(data[j].parkDescribe.length>140){//当theTxt的字符数大于20位时，截取其中的前20个字符并加上“...”
              data[j].parkDescribe=data[j].parkDescribe.substring(0, 140)+"...";
              }
        if(data[j].parkImg =="" || data[j].parkImg ==null ){
              $("#cont").append("<div class='course'><div class='imgl'><a href='<%=basePath %>/webolmerchants/roomList.do?id="+data[j].id+"' target='_blank'><img src='<%=basePath %>/img/t4.jpg' class='img2'/><div class='imgword'><p>"+data[j].parkName+"</p></div></a></div><div class='word'><div class='up'><img src='<%=basePath %>/img/lang2.png'/><span>"+data[j].parkName+"</span><e style=' float:right; font-size:16px;'>联系人："+data[j].contacts+"&nbsp;&nbsp;&nbsp; 联系电话："+data[j].mobile+"</e></div><p class='p4'><b>联系地址：</b>"+data[j].address+"<br><b>园区介绍：</b>"+data[j].parkDescribe+"<br></p></div></div>");
            }else{
              parkImgs=data[j].parkImg;
              strs=parkImgs.split(","); //字符分割 
              $("#cont").append("<div class='course'><div class='imgl'><a href='<%=basePath %>/webolmerchants/roomList.do?id="+data[j].id+"' target='_blank'><img src='<%=ImgProperties.LOAD_PATH%>"+strs[0]+"' class='img2'/><div class='imgword'><p>"+data[j].parkName+"</p></div></a></div><div class='word'><div class='up'><img src='<%=basePath %>/img/lang2.png'/><span>"+data[j].parkName+"</span><e style=' float:right; font-size:16px;'>联系人："+data[j].contacts+"&nbsp;&nbsp;&nbsp; 联系电话："+data[j].mobile+"</e></div><p class='p4'><b>联系地址：</b>"+data[j].address+"<br><b>园区介绍：</b>"+data[j].parkDescribe+"<br></p></div></div>");
            }
      }
    });
  });
  
    
    function goPage(p){
    	$("#page a").each(function(){
			if ($(this).text()==p) {
				$(this).addClass("hover");
			} else{
				$(this).removeClass("hover");
			}
		});
      $.post("<%=basePath %>/webolmerchants/getWebpark.do",{"parkGroupId":nowparkGroupId},function(data){
        nowPage=p;
        $("#cont div").remove();
        for(j=(p-1)*rows;j<rows*(p-1)+rows;j++)
        {
          if(data[j].parkDescribe.length>140){//当theTxt的字符数大于20位时，截取其中的前20个字符并加上“...”
              data[j].parkDescribe=data[j].parkDescribe.substring(0, 140)+"...";
              }
          if(j>=dataLength){return;}
          if(data[j].parkImg =="" || data[j].parkImg ==null ){
              $("#cont").append("<div class='course'><div class='imgl'><a href='<%=basePath %>/webolmerchants/roomList.do?id="+data[j].id+"' target='_blank'><img src='<%=basePath %>/img/t4.jpg' class='img2'/><div class='imgword'><p>"+data[j].parkName+"</p></div></a></div><div class='word'><div class='up'><img src='<%=basePath %>/img/lang2.png'/><span>"+data[j].parkName+"</span><e style=' float:right; font-size:16px;'>联系人："+data[j].contacts+"&nbsp;&nbsp;&nbsp; 联系电话："+data[j].mobile+"</e></div><p class='p4'><b>联系地址：</b>"+data[j].address+"<br><b>园区介绍：</b>"+data[j].parkDescribe+"<br></p></div></div>");
            }else{
              parkImgs=data[j].parkImg;
              strs=parkImgs.split(","); //字符分割 
              $("#cont").append("<div class='course'><div class='imgl'><a href='<%=basePath %>/webolmerchants/roomList.do?id="+data[j].id+"' target='_blank'><img src='<%=ImgProperties.LOAD_PATH%>"+strs[0]+"' class='img2'/><div class='imgword'><p>"+data[j].parkName+"</p></div></a></div><div class='word'><div class='up'><img src='<%=basePath %>/img/lang2.png'/><span>"+data[j].parkName+"</span><e style=' float:right; font-size:16px;'>联系人："+data[j].contacts+"&nbsp;&nbsp;&nbsp; 联系电话："+data[j].mobile+"</e></div><p class='p4'><b>联系地址：</b>"+data[j].address+"<br><b>园区介绍：</b>"+data[j].parkDescribe+"<br></p></div></div>");
            }
        }
      });
    }
    

    
    
    

  
    
  function quickCity(cityname){
	  
    $("#cont div").remove();
    $("#page li").remove();
    nowcity=cityname;
    $.post("<%=basePath %>/webolmerchants/getCity.do?",{"city":nowcity,"parkGroupId":nowparkGroupId},function(data){
      
      dataLength=data.length;
      allPage = Math.ceil(dataLength/rows);
      pagingCity('page',allPage);
      
      for(var j=0;j<rows;j++)
      { if(data[j]==null)
        break;
        if(data[j].parkDescribe.length>140){//当theTxt的字符数大于20位时，截取其中的前20个字符并加上“...”
              data[j].parkDescribe=data[j].parkDescribe.substring(0, 140)+"...";
              }
        if(data[j].parkImg =="" || data[j].parkImg ==null ){
              $("#cont").append("<div class='course'><div class='imgl'><a href='<%=basePath %>/webolmerchants/roomList.do?id="+data[j].id+"' target='_blank'><img src='<%=basePath %>/img/t4.jpg' class='img2'/><div class='imgword'><p>"+data[j].parkName+"</p></div></a></div><div class='word'><div class='up'><img src='<%=basePath %>/img/lang2.png'/><span>"+data[j].parkName+"</span><e style=' float:right; font-size:16px;'>联系人："+data[j].contacts+"&nbsp;&nbsp;&nbsp; 联系电话："+data[j].mobile+"</e></div><p class='p4'><b>联系地址：</b>"+data[j].address+"<br><b>园区介绍：</b>"+data[j].parkDescribe+"<br></p></div></div>");
            }else{
              parkImgs=data[j].parkImg;
              strs=parkImgs.split(","); //字符分割 
              $("#cont").append("<div class='course'><div class='imgl'><a href='<%=basePath %>/webolmerchants/roomList.do?id="+data[j].id+"' target='_blank'><img src='<%=ImgProperties.LOAD_PATH%>"+strs[0]+"' class='img2'/><div class='imgword'><p>"+data[j].parkName+"</p></div></a></div><div class='word'><div class='up'><img src='<%=basePath %>/img/lang2.png'/><span>"+data[j].parkName+"</span><e style=' float:right; font-size:16px;'>联系人："+data[j].contacts+"&nbsp;&nbsp;&nbsp; 联系电话："+data[j].mobile+"</e></div><p class='p4'><b>联系地址：</b>"+data[j].address+"<br><b>园区介绍：</b>"+data[j].parkDescribe+"<br></p></div></div>");
            }
      }
    });
  }

  
  function goCityPage(p,cityname){
	  $("#page a").each(function(){
			if ($(this).text()==p) {
				$(this).addClass("hover");
			} else{
				$(this).removeClass("hover");
			}
		}); 
    nowcity=cityname;
    $.post("<%=basePath %>/webolmerchants/getCity.do?",{"city":nowcity,"parkGroupId":nowparkGroupId},function(data){
      nowPage=p;
      $("#cont div").remove();
      for(j=(p-1)*rows;j<rows*(p-1)+rows;j++)
      { 
        if(data[j].parkDescribe.length>140){//当theTxt的字符数大于20位时，截取其中的前20个字符并加上“...”
              data[j].parkDescribe=data[j].parkDescribe.substring(0, 140)+"...";
              }
        if(j>=dataLength){return;}
        if(data[j].parkImg =="" || data[j].parkImg ==null ){
              $("#cont").append("<div class='course'><div class='imgl'><a href='<%=basePath %>/webolmerchants/roomList.do?id="+data[j].id+"' target='_blank'><img src='<%=basePath %>/img/t4.jpg' class='img2'/><div class='imgword'><p>"+data[j].parkName+"</p></div></a></div><div class='word'><div class='up'><img src='<%=basePath %>/img/lang2.png'/><span>"+data[j].parkName+"</span><e style=' float:right; font-size:16px;'>联系人："+data[j].contacts+"&nbsp;&nbsp;&nbsp; 联系电话："+data[j].mobile+"</e></div><p class='p4'><b>联系地址：</b>"+data[j].address+"<br><b>园区介绍：</b>"+data[j].parkDescribe+"<br></p></div></div>");
            }else{
              parkImgs=data[j].parkImg;
              strs=parkImgs.split(","); //字符分割 
              $("#cont").append("<div class='course'><div class='imgl'><a href='<%=basePath %>/webolmerchants/roomList.do?id="+data[j].id+"' target='_blank'><img src='<%=ImgProperties.LOAD_PATH%>"+strs[0]+"' class='img2'/><div class='imgword'><p>"+data[j].parkName+"</p></div></a></div><div class='word'><div class='up'><img src='<%=basePath %>/img/lang2.png'/><span>"+data[j].parkName+"</span><e style=' float:right; font-size:16px;'>联系人："+data[j].contacts+"&nbsp;&nbsp;&nbsp; 联系电话："+data[j].mobile+"</e></div><p class='p4'><b>联系地址：</b>"+data[j].address+"<br><b>园区介绍：</b>"+data[j].parkDescribe+"<br></p></div></div>");
            }
      }
    });
  }
  
  

   function citydata(){
      var data3;
      $.ajax({  
          type : "post",  
          url : "<%=basePath%>/webinpark/getCity2.do",  
          async: false,
          data:{"parkGroupId":nowparkGroupId},
          success : function(data2){/* 
            alert(cityses(data2.hot,data2.province));
            return cityses(data2.hot,data2.province); */
            data3=data2;
          }  
      
      });
      return data3;
     }  
    
  var cityPicker = new HzwCityPicker2({
    data: citydata(),
    target: 'cityChoice',
    valType: 'k-v',
    hideCityInput: {
      name: 'city',
      id: 'city'
    },
    hideProvinceInput: {
      name: 'province',
      id: 'province'
    },
    callback: function(){
      var aaaa=$('#city').attr('value');
      var str_after = aaaa.split('-')[1]; 
      quickCity(str_after);
      $("#ningbo").html(str_after);
    }
  });

  cityPicker.init();  
  
  
  function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  decodeURI(r[2]); return null;
	}
  </script>
  </body>
</html>
