//自定义的ajax，默认async:true,cache:false,dataType:json,event:success,beforeSend,complete
//TODO:error event
$.myAjax = function (url, data, type, successfn, beforefn, completefn,errorfn) {

	data = (data == null || data == "" || typeof (data) == "undefined") ? { "date": new Date().getTime() } : data;

    $.ajax({
        url: url,
        data: data,
        type: type,
        cache: false,
        async: true,
        dataType: 'json',
        success: function (d) {

            successfn(d);

        },
        error: function (e) {
            errorfn(e);
        },
        beforeSend: function (s) {

            beforefn(s);
        },
        complete: function (c) {

            completefn(c);

        }

    })

};


$.myPost=function(url, data, successfn){
	
	
	data = (data == null || data == "" || typeof (data) == "undefined") ? { "date": new Date().getTime() } : data;

    $.ajax({
        url: url,
        data: data,
        type: 'post',
        cache: false,
        async: true,
        dataType: 'json',
        success: function (d) {

            successfn(d);

        },
        error: function (e) {
            myNoty('请求错误。',10002);
        },
        beforeSend: function (s) {
        	
        	$.noty.closeAll();
        	
        	showLoading();
        },
        complete: function (c) {

        	hideLoading();

        }

    })
	
	
	
}


function myConfirm(title,func)
{
	
	var n = noty({
        text        : title,
        type        : "warning",
        dismissQueue: true,
        layout      : "center",
        theme       : 'defaultTheme',
        buttons     : [
            {addClass: 'btn btn-primary', text: 'Ok', onClick: function ($noty) {
            	
                $noty.close();
                
        		func;

            }
            },
            {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
                $noty.close();
            }
            }
        ]
    });
	



}


function myNoty(msg, status) {

    //var options;
	
	var type="warning";
	
	var closewith="click";
	
	if(status===10001)
	{
		type="success";
		
		var closewith="hover";
	}
	
	if(status===10003)
	{
		type="error";
		
		var closewith="button";
	}
	
    //options = { "text": msg, "layout": "center", "type": type, "animateOpen": { "opacity": "show", "speed": 100 }, "timeout": timeout, "animateClose": { "opacity": "hide", "speed": 100 } };
    var options = {
        text: msg,
        timeout: false,
        type: type,
        theme: 'relax',
        layout: 'topRight',
        closeWith: [closewith],
        maxVisible: 10,
        animation: {
            open: { height: 'toggle' }, // jQuery animate function property object
            close: { height: 'toggle' }, // jQuery animate function property object
            //open: 'animated bounceInDown', // Animate.css class names
            //close: 'animated bounceOutRight', // Animate.css class names
            easing: 'swing', // unavailable - no need
            speed: 500 // unavailable - no need
        }
    };
    noty(options);
    //noty(options);


}

function myNotyLoading()
{
	
	var n = noty({
        text        : '处理中...',
        type        : 'information',
        dismissQueue: true,
        closeWith   : [],
        modal       : true,
        layout      : 'center',
        theme       : 'defaultTheme',
        maxVisible  : 10
    });

}

function hideNotyLoading()
{
	$.noty.closeAll();
}


function showLoading()
{
	var d='<div	style="position: fixed; top: 0; left: 0; bottom: 0; right: 0; z-index: 98999; background: rgba(0, 0, 0, 0.7);" id="loading">'
			+'<div class="spiner-example">'
				+'<div class="sk-spinner sk-spinner-three-bounce">'
				+	'<div class="sk-bounce1"></div>'
				+	'<div class="sk-bounce2"></div>'
				+	'<div class="sk-bounce3"></div>'
				+'</div>			</div>		</div>';
	
	$('body').append(d);
	
}

function hideLoading()
{
	$('#loading').remove();
}


function getParameterByName(name) {

    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);

    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
};

//检测null
function myNotyNull(msg,obj)
{
	var value=$.trim($(obj).val());
	
    if (value == null || value == "") {
    	
        myNoty(msg, '10002');
        
        $(obj).focus();
        
        return false;
    }
 
    return true;
   
}

function myNotySele(obj)
{
	if($(obj).get(0).selectedIndex < 1)
	{
		myNoty("请选择"+$(obj).attr('placeholder'),10002);
		
		$(obj).focus();
		
		return false;
	}
	
	return true;
	
}

//type:0:int,1:float,2:date,3:string
function myNotyRegx(obj,type)
{		
	var regx=/^[0-9]*$/;
	
	var msg="(数字)。";
	
	if(type===1)
	{
		regx=/^\d+(?=\.{0,1}\d+$|$)/;
		
		msg="(数字或小数)。";
	}
	if(type==2)
	{
		regx=/^(\d{4})\-(\d{1,2})\-(\d{1,2})$/;
		
		msg="(日期)。";
	}
	if(type==3)
	{
		regx=/\S/;
		
		msg="";
	}
	
	for(var i=0;i<$(obj).length;i++)
	{
		
		var value=$.trim($($(obj)[i]).val());
		
		var ph=$($(obj)[i]).attr('placeholder');
		
		if (value == null || value == '' || !regx.test(value)) {
        
	    	myNoty("请输入"+ph+msg, '10002');
	    	
	        $($(obj)[i]).focus();
	        
	        return false;
    	
    	}
	}
    
    return true;       
    
}



function reloadurl(url) {
    window.location.reload(url);
}

function initSelect(obj,json)
{
	var oOption=document.createElement('option');
	
	oOption.text="----";
	
	oOption.value="";
	
	$(obj).append(oOption);
	
	for(var item in json)
	{
		var oOption=document.createElement('option');
		
		oOption.value=item;
		
		oOption.text=json[item];
		
		$(obj).append(oOption);
	}
}

function initTypeSelect(obj,json)
{
	var oOption=document.createElement('option');
	
	oOption.text="----";
	
	oOption.value="";
	
	$(obj).append(oOption);
	
	for(var item in json)
	{
		var oOption=document.createElement('option');
		
		oOption.value=item;
		
		oOption.text=json[item][0].name;
		
		$(obj).append(oOption);
	}
}


function formatDate(date)
{
	if(date=='')
		
		return '';
	
	var d=new Date(date);
	
	return d.getFullYear()+'-'+PrefixInteger((d.getMonth()+1),2)+'-'+PrefixInteger(d.getDate(),2);
}

//sDate1和sDate2是yyyy-MM-dd格式
function DateDiff(sDate1, sDate2) {  
	 
    var aDate, oDate1, oDate2, result;
    aDate = sDate1.split("-");
    oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);  //转换为yyyy-MM-dd格式
    aDate = sDate2.split("-");
    oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
    
   
   	result = parseInt((oDate2 - oDate1) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数
    
   	
   	return result;  
}


var contractCategory={
		"lease":[{"id":"1","name":"租赁合同"}],
		"property":[{"id":"4","name":"物业合同"}],
		"service":[{"id":"5","name":"服务合同"}],
		"energy":[{"id":"3","name":"能源合同"}],
		"sub":[{"id":"2","name":"外包合同"}]
		
}

var energyType={
		
		"power":[{"id":"0","name":"电费"}],
		"water":[{"id":"1","name":"水费"}],
		"gas":[{"id":"2","name":"燃气"}],
		"ac":[{"id":"3","name":"空调"}]
}

var instalmentStatus={
		"none":[{"id":"0","name":"分期未录入"}],
		"partial":[{"id":"1","name":"分期未完整录入"}],
		"finished":[{"id":"2","name":"分期已完整录入"}]
		
}

function initContractCategory(obj,json)
{
//	var oOption=document.createElement('option');
//	
//	oOption.text="----";
//	
//	oOption.value="";
//	
//	$(obj).append(oOption);
	
	for(var item in json)
	{
		var oOption=document.createElement('option');
		
		oOption.value=json[item][0].id;
		
		oOption.text=json[item][0].name;
		
		$(obj).append(oOption);
	}
}


var datediff = function(start, end) {
	
	start=start.split("-");
	start=new Date(start[1] + '-' + start[2] + '-' + start[0]);
	end=end.split("-");
	end=new Date(end[1] + '-' + end[2] + '-' + end[0]);
	  var diff = { years: 0, months: 0, days: 0 };
	  var timeDiff = end - start;

	  if (timeDiff > 0) {
	    diff.years = end.getFullYear() - start.getFullYear();
	    diff.months = end.getMonth() - start.getMonth();
	    diff.days = end.getDate() - start.getDate();

	    if (diff.months < 0) {
	      diff.years--;
	      diff.months += 12;
	      
	    }

	    if (diff.days < 0) {
	      diff.months = Math.max(0, diff.months - 1);
	      diff.days += 30;
	    }
	    else
	    {
	    	diff.months += 1;
	    }
	  }

	  return diff;
	};
	
	function addValToInput(obj,val)
	{
		var ids = [];

	    if ($(obj).val() != '') {
	        ids = $(obj).val().split(',');
	    }

	    ids.push(val);
	    
	   // ids=ids.delRepeat();

	    $(obj).val(ids.join(','));

	}

	function removeValFromInput(obj,val)
	{
		
		var ids = [];

	    if ($(obj).val() != '') {
	     
	    	ids = $(obj).val().split(',');
	    
	    	ids = ids.delId(val);

	    	$(obj).val(ids.join(','));
	    }
	}
	//删除重复
	Array.prototype.delRepeat = function () {
	    var newArray = [];
	    var provisionalTable = {};
	    for (var i = 0, item; (item = this[i]) != null; i++) {
	        if (!provisionalTable[item] && item!="") {
	            newArray.push(item);
	            provisionalTable[item] = true;
	        }
	    }
	    return newArray;
	}

	//删除其中一个
	Array.prototype.delId = function (id) {
	       

	    for (var i = 0; i < this.length; i++) {
	        if (this[i] == id) {
	            this.splice(i, 1);
	            break;
	        }
	    }

	    return this;
	}

	function addDate(oDate,num)
	{
		var aDate, d;
	    
		aDate = oDate.split("-");
	    
		d = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);  //转换为yyyy-MM-dd格式
	    
	    d=d.getTime()+num*(1000 * 60 * 60 * 24);
	    
	    d=new Date(d);
	    
	    return d.getFullYear()+'-'+PrefixInteger((d.getMonth()+1),2)+'-'+PrefixInteger(d.getDate(),2);
	    
	}
	
	

	function PrefixInteger(num, length) {  
		 return (Array(length).join('0') + num).slice(-length);  
		} 
	
	
	
	
//刷新正常合同用
function renderContractStatus3(start,end)
{
	var td=formatDate(new Date());
	
	var s="";
	
	var sd=DateDiff(start,td);
	
	var ed=DateDiff(td,end);
	
	if(sd>=0 && ed>=0)
	{
		s="正常";
	}
	
	if(sd<0)
	{
		s="未开始";
	}
	
	if(ed<0)
	{
		s="已结束";
	}
	
	return s;

}

function renderContractStatus5(start,end)
{
	var td=formatDate(new Date());
	
	var s="";
	
	var sd=DateDiff(start,td);
	
	var ed=DateDiff(td,end);
	
	if(ed>=0)
	{
		s="未结束";
	}

	
	if(ed<0)
	{
		s="已结束";
	}
	
	return s;

}


	
//合同状态
//editable:合同可编辑
var contractStatus={
		
		"deleteAuditing":[{"id":"-11","name":"删除审批中","editable":"0"}],
		"parkerRefuse":[{"id":"-9","name":"园长拒绝","editable":"1"}],
		"financeRefuse":[{"id":"-7","name":"财务拒绝","editable":"1"}],
		"terminateAuditing":[{"id":"-5","name":"终止审批中","editable":"0"}],
		"parkerAuditing":[{"id":"-3","name":"园长审批中","editable":"0"}],
		"financeAuditing":[{"id":"-1","name":"财务审批中","editable":"0"}],
		"init":[{"id":"1","name":"初始","editable":"1"}],
		"parkerAccept":[{"id":"3","name":"已审批","editable":"0"}],
		"terminate":[{"id":"5","name":"终止","editable":"0"}],
		"deleted":[{"id":"7","name":"已删除","editable":"0"}]
		
}

//判断状态是否可编辑
function checkContractStatusEditable(c)
{
	var result=false;
	
	for(var item in contractStatus)
	{	
		
		var id=contractStatus[item][0].id;
		
		var ea=contractStatus[item][0].editable;
		
		if(c==id && ea=='1')
		{
			result=true;
			
			break;
		}		
		
	}
	
	return result;

}

function initContractStatusSelect(obj)
{	
	
	for(var item in contractStatus)
	{
		var oOption=document.createElement('option');
		
		oOption.value=contractStatus[item][0].id;
		
		oOption.text=contractStatus[item][0].name;
		
		$(obj).append(oOption);
	}

}


function renderContractStatus(s,start,end)
{
	s=s+"";
	
	var result='';
	
	var n="";
	
	var t="";
	
	if(s==contractStatus['parkerAccept'][0].id)
	{
		n=renderContractStatus3(start,end);
	}
	
	if(s==contractStatus['terminate'][0].id)
	{
		t=renderContractStatus5(start,end);
	}
	
	
	switch(s)
	{
		case contractStatus['init'][0].id:
			result='<label class="label label-default">初始</label>';
			break;
		case contractStatus['financeAuditing'][0].id:
			result='<label class="label label-primary">财务审批中</label>';
			break;
		case contractStatus['parkerAuditing'][0].id:
			result='<label class="label label-primary">园长审批中</label>';
			break;
		case contractStatus['financeRefuse'][0].id:
			result='<label class="label label-info">财务拒绝</label>';
			break;
		case contractStatus['parkerAccept'][0].id:
			result='<label class="label label-success">已审批('+n+')</label>';
			break;
		case contractStatus['parkerRefuse'][0].id:
			result='<label class="label label-info">园长拒绝</label>';
			break;
		case contractStatus['terminateAuditing'][0].id:
			result='<label class="label label-warning">终止审批中</label>';
			break;
		case contractStatus['terminate'][0].id:
			result='<label class="label label-default">终止('+t+')</label>';
			break;
		case contractStatus['deleteAuditing'][0].id:
			result='<label class="label label-danger">删除审批中</label>';
			break;
		case contractStatus['deleted'][0].id:
			result='<label class="label label-default">已删除</label>';
			break;
		default:
			result='<label class="label label-default">未知</label>';
	}
		
	return result;
	
}



String.format = function() {
    if (arguments.length == 0)
        return null;
    var str = arguments[0];
    for ( var i = 1; i < arguments.length; i++) {
        var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
        str = str.replace(re, arguments[i]);
    }
    return str;
};