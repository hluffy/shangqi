	$(function(){
		var userName = sessionStorage.getItem("userName");
		if(!userName){
			window.location.href="login.html";
		}
	});
//	window.onunload = function() {  
//	   // 要执行的代码  
//		localStorage.removeItem("userName");
//	}  
	
	
	

