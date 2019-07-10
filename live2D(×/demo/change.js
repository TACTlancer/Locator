
	function demo(){
		var today=new Date();
		var h=today.getHours();
		var m=today.getMinutes();
		
		var hint = document.getElementById("hint");
		if(h<17&&h>14)
		hint.innerHTML="りつ 提醒您：别摸鱼";
		
		if(h<12&&h>9)
		hint.innerHTML="りつ 提醒您：该上工了";
	}
	
	demo();