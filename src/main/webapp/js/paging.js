/*!
 * 分页js库
 * 作者：wukaihang9009
 */

function paging(a,b){//a为要放生成的分页的容器,请务必用id，b为总页数
	$("#"+a).append('<li><a href="javascript:previousPage()">&lt;上一页</a></li>');
	if(b>5){
		for(i=0;i<5;i++){
			if(i==0){
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goPage('+(i+1)+')" class="hover">'+(i+1)+'</a></li>');
			}else{
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goPage('+(i+1)+')" >'+(i+1)+'</a></li>');
			}
		}
		$("#"+a).append('<li><a href="javascript:moreP(6)">...</a></li>');
	}else{
		for(i=0;i<b;i++){
			if(i==0){
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goPage('+(i+1)+')" class="hover">'+(i+1)+'</a></li>');
			}else{
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goPage('+(i+1)+')" >'+(i+1)+'</a></li>');
			}
		}
	}
	$("#"+a).append('<li><a href="javascript:nextPage()">下一页&gt;</a></li>');
}


function moreP(mun){
	if(mun+4>allPage){
		var nowMun=mun-1;
		var nowMunEnd=allPage;
	}else{
		var nowMun=mun-1;
		var nowMunEnd=mun+4;

	}
	nowMaxPage=nowMunEnd;
	nowMinPage=mun;
	$("#page li").remove();
	$("#page").append('<li><a href="javascript:" onclick="previousPage()">&lt;上一页</a></li>');
	$("#page").append('<li><a href="javascript:moreQ('+nowMun+')">...</a></li>');


	for(var e=nowMun;e<nowMunEnd;e++)
	{
		$("#page").append('<li><a id="page'+(e+1)+'"  href="javascript:goPage('+(e+1)+')">'+(e+1)+'</a></li>');
	}
	if(nowMunEnd!=allPage){
		$("#page").append('<li><a href="javascript:moreP('+(nowMunEnd+1)+')">...</a></li>');
	}
	$("#page").append('<li><a href="javascript:" onclick="nextPage()">下一页&gt;</a></li>');
}

function moreQ(mun){
	$("#page li").remove();
	$("#page").append('<li><a href="javascript:" onclick="previousPage()">&lt;上一页</a></li>');

	if(mun-4<2){
		var nowMun=1;
		var nowMunEnd=6;
	}else{
		var nowMun=mun-4;
		var nowMunEnd=mun+1;
		$("#page").append('<li><a href="javascript:moreQ('+(nowMun-1)+')">...</a></li>');
	}
	nowMaxPage=mun;
	nowMinPage=nowMun;
	for(var e=nowMun;e<nowMunEnd;e++)
	{
		$("#page").append('<li><a id="page'+(e)+'"  href="javascript:goPage('+(e)+')">'+(e)+'</a></li>');
	}
	$("#page").append('<li><a href="javascript:moreP('+nowMunEnd+')">...</a></li>');
	$("#page").append('<li><a href="javascript:" onclick="nextPage()">下一页&gt;</a></li>');
}

function nextPage()
{
	var s=nowPage+1;
	
	if(s>Math.ceil(dataLength/rows)){
		return;
	}
	
	if(s>nowMaxPage){
		moreP(s);
	}
	
	goPage(s);
}
function previousPage()
{
	
	var s=nowPage-1;
	
	if(s<1){
	
		return;
	}
	
	if(s<nowMinPage){
		moreQ(s);
	}
	
	goPage(s);
}




/////////////////////////////////////////////////////////////////////////////////////////////////
//城市筛选：：：;；；
function pagingCity(a,b){//城市筛选：：：;；；a为要放生成的分页的容器,请务必用id，b为总页数
	$("#"+a).append('<li><a href="javascript:previousCityPage(\''+nowcity+'\')">&lt;上一页</a></li>');
	if(b>5){
		for(i=0;i<5;i++){
			if(i==0){
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goCityPage('+(i+1)+',\''+nowcity+'\')" class="hover">'+(i+1)+'</a></li>');
			}else{
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goCityPage('+(i+1)+',\''+nowcity+'\')" >'+(i+1)+'</a></li>');
			}
		}
		$("#"+a).append('<li><a href="javascript:moreP(6)">...</a></li>');
	}else{
		for(i=0;i<b;i++){
			if(i==0){
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goCityPage('+(i+1)+',\''+nowcity+'\')" class="hover">'+(i+1)+'</a></li>');
			}else{
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goCityPage('+(i+1)+',\''+nowcity+'\')" >'+(i+1)+'</a></li>');
			}
		}
	}
	$("#"+a).append('<li><a href="javascript:nextCityPage(\''+nowcity+'\')">下一页&gt;</a></li>');
}

function nextCityPage(cityName)
{
  var s=nowPage+1;
  
  if(s>Math.ceil(dataLength/rows)){
    return;
  }
  if(s>nowMaxPage){
		moreP(s);
	}  
  goCityPage(s,cityName);
}

function previousCityPage(cityName)
{
  var s=nowPage-1;  
  if(s<1){
    return;
  }
  if(s<nowMinPage){
		moreQ(s);
	}
  goCityPage(s,cityName);
}

function morePcity(mun){
	if(mun+4>allPage){
		var nowMun=mun-1;
		var nowMunEnd=allPage;
	}else{
		var nowMun=mun-1;
		var nowMunEnd=mun+4;

	}
	nowMaxPage=nowMunEnd;
	nowMinPage=mun;
	$("#page li").remove();
	$("#page").append('<li><a href="javascript:" onclick="previousCityPage(\''+nowcity+'\')">&lt;上一页</a></li>');
	$("#page").append('<li><a href="javascript:moreQcity('+nowMun+')">...</a></li>');


	for(var e=nowMun;e<nowMunEnd;e++)
	{
		$("#page").append('<li><a id="page'+(e+1)+'"  href="javascript:goCityPage('+(e+1)+',\''+nowcity+'\')">'+(e+1)+'</a></li>');
	}
	if(nowMunEnd!=allPage){
		$("#page").append('<li><a href="javascript:morePcity('+(nowMunEnd+1)+')">...</a></li>');
	}
	$("#page").append('<li><a href="javascript:" onclick="nextCityPage(\''+nowcity+'\')">下一页&gt;</a></li>');
}

function moreQcity(mun){
	$("#page li").remove();
	$("#page").append('<li><a href="javascript:" onclick="previousCityPage(\''+nowcity+'\')">&lt;上一页</a></li>');

	if(mun-4<2){
		var nowMun=1;
		var nowMunEnd=6;
	}else{
		var nowMun=mun-4;
		var nowMunEnd=mun+1;
		$("#page").append('<li><a href="javascript:moreQcity('+(nowMun-1)+')">...</a></li>');
	}
	nowMaxPage=mun;
	nowMinPage=nowMun;
	for(var e=nowMun;e<nowMunEnd;e++)
	{
		$("#page").append('<li><a id="page'+(e)+'"  href="javascript:goCityPage('+(e+1)+',\''+nowcity+'\')">'+(e)+'</a></li>');
	}
	$("#page").append('<li><a href="javascript:morePcity('+nowMunEnd+')">...</a></li>');
	$("#page").append('<li><a href="javascript:" onclick="nextCityPage(\''+nowcity+'\')">下一页&gt;</a></li>');
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////
function pagingEcollege(a,b,nowlistType){//a为要放生成的分页的容器,请务必用id，b为总页数
	$("#"+a).append('<li><a href="javascript:previousEcollegePage('+nowlistType+')">&lt;上一页</a></li>');
	if(b>5){
		for(i=0;i<5;i++){
			if(i==0){
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goPage('+(i+1)+','+nowlistType+')" class="hover">'+(i+1)+'</a></li>');
			}else{
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goPage('+(i+1)+','+nowlistType+')" >'+(i+1)+'</a></li>');
			}
		}
		$("#"+a).append('<li><a href="javascript:morePEcollege(6)">...</a></li>');
	}else{
		for(i=0;i<b;i++){
			if(i==0){
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goPage('+(i+1)+','+nowlistType+')" class="hover">'+(i+1)+'</a></li>');
			}else{
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goPage('+(i+1)+','+nowlistType+')" >'+(i+1)+'</a></li>');
			}
		}
	}
	$("#"+a).append('<li><a href="javascript:nextEcollegePage('+nowlistType+')">下一页&gt;</a></li>');
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////
function pagingEcollege2(a,b,nowlistType,target){//a为要放生成的分页的容器,请务必用id，b为总页数
	$("#"+a).append('<li><a href="javascript:previousEcollegePage('+nowlistType+','+target+','+targetName+','+city+')">&lt;上一页</a></li>');
	if(b>5){
		for(i=0;i<5;i++){
			if(i==0){
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goPage('+(i+1)+','+nowlistType+','+target+','+targetName+','+city+')" class="hover">'+(i+1)+'</a></li>');
			}else{
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goPage('+(i+1)+','+nowlistType+','+target+','+targetName+','+city+')" >'+(i+1)+'</a></li>');
			}
		}
		$("#"+a).append('<li><a href="javascript:morePEcollege(6)">...</a></li>');
	}else{
		for(i=0;i<b;i++){
			if(i==0){
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goPage('+(i+1)+','+nowlistType+','+target+','+targetName+','+city+')" class="hover">'+(i+1)+'</a></li>');
			}else{
				$("#"+a).append('<li><a id="'+a+(i+1)+'"  href="javascript:goPage('+(i+1)+','+nowlistType+','+target+','+targetName+','+city+')" >'+(i+1)+'</a></li>');
			}
		}
	}
	$("#"+a).append('<li><a href="javascript:nextEcollegePage('+nowlistType+','+target+','+targetName+','+city+')">下一页&gt;</a></li>');
}


function morePEcollege(mun){
	if(mun+4>allPage){
		var nowMun=mun-1;
		var nowMunEnd=allPage;
	}else{
		var nowMun=mun-1;
		var nowMunEnd=mun+4;

	}
	nowMaxPage=nowMunEnd;
	nowMinPage=mun;
	$("#page li").remove();
	$("#page").append('<li><a href="javascript:" onclick="previousEcollegePage('+nowlistType+')">&lt;上一页</a></li>');
	$("#page").append('<li><a href="javascript:moreQEcollege('+nowMun+')">...</a></li>');


	for(var e=nowMun;e<nowMunEnd;e++)
	{
		$("#page").append('<li><a id="page'+(e+1)+'"  href="javascript:goPage('+(e+1)+','+nowlistType+')">'+(e+1)+'</a></li>');
	}
	if(nowMunEnd!=allPage){
		$("#page").append('<li><a href="javascript:morePEcollege('+(nowMunEnd+1)+')">...</a></li>');
	}
	$("#page").append('<li><a href="javascript:" onclick="nextEcollegePage('+nowlistType+')">下一页&gt;</a></li>');
}

function moreQEcollege(mun){
	$("#page li").remove();
	$("#page").append('<li><a href="javascript:" onclick="previousEcollegePage('+nowlistType+')">&lt;上一页</a></li>');

	if(mun-4<2){
		var nowMun=1;
		var nowMunEnd=6;
	}else{
		var nowMun=mun-4;
		var nowMunEnd=mun+1;
		$("#page").append('<li><a href="javascript:moreQEcollege('+(nowMun-1)+')">...</a></li>');
	}
	nowMaxPage=mun;
	nowMinPage=nowMun;
	for(var e=nowMun;e<nowMunEnd;e++)
	{
		$("#page").append('<li><a id="page'+(e)+'"  href="javascript:goPage('+(e+1)+','+nowlistType+')">'+(e)+'</a></li>');
	}
	$("#page").append('<li><a href="javascript:morePEcollege('+nowMunEnd+')">...</a></li>');
	$("#page").append('<li><a href="javascript:" onclick="nextEcollegePage('+nowlistType+')">下一页&gt;</a></li>');
}

function nextEcollegePage(a)
{
	var s=nowPage+1;
	
	if(s>Math.ceil(dataLength/rows)){
		return;
	}
	
	if(s>nowMaxPage){
		morePEcollege(s);
	}
	
	goPage(s,a);
}
function previousEcollegePage(a)
{
	
	var s=nowPage-1;
	
	if(s<1){
	
		return;
	}
	
	if(s<nowMinPage){
		moreQEcollege(s);
	}
	
	goPage(s,a);
}

