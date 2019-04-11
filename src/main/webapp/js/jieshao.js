var a1=document.getElementById("a1");
			var a2=document.getElementById("a2");
			var a3=document.getElementById("a3");
			var a4=document.getElementById("a4");
			var a5=document.getElementById("a5");
			function show(e){
				for(i=1;i<=5;i++)
				{
					eval("a"+i+".className='null'");
				}
				e.currentTarget.className="act";
			}
			
		a1.addEventListener("click",show);
		a2.addEventListener("click",show);
		a3.addEventListener("click",show);
		a4.addEventListener("click",show);
		a5.addEventListener("click",show);