function selected(obj,value){
	for(var i=0;i<obj.length;i++){
		if(obj.options[i].value == value){
			obj.options[i].selected=true;
		}
	}
}
