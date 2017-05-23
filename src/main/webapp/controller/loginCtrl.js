var app = angular.module("myApp",[]);
//	app.controller("loginCtrl",function($scope,$cookies,loginService){
//		$scope.user = {};
//		var flag = false;
//		$scope.userLogin = function(){
//			loginService.userLogin($scope.user).then(function(data){
//				for(var i=0;i<data.users.length;i++){
//					if(data.users[i].userName==$scope.user.name&&data.users[i].password==$scope.user.password){
//						flag = true;
//						break;
//					}
//				}
//				if(flag){
//// 					addCookie("userName",$scope.user.name,2);
////					$cookies.userName = $scope.user.name;
////					localStorage.removeItem("userName");
////					localStorage.userName = $scope.user.name;
////					document.write(localStorage.userName);
//					window.location.href="index.html";
//				}else{
//					$scope.errorImg="用户名或密码错误";
//				}
//			});
//		};
//		
		
//		
//// 		$scope.login();
//		
//	});



app.controller("loginCtrl",function($rootScope,$scope,loginService,$window){
	$scope.user = {};
	$scope.getFocus = function(){
		$scope.errorImg="";
	}
	
	$scope.userLogin = function($rootScope){
		addCookie("username",$scope.user.userName);
		loginService.userLogin($scope.user).then(function(data){
			if(!data.data){
				$scope.errorImg = "用戶名不存在或密碼錯誤！";
			}else{
//				localStorage.userName = data.data.userName;
//				document.write(localStorage.userName);
				localStorage.setItem("userName",data.data.userName);
				localStorage.setItem("userRole",data.data.role);
				window.location.href="index.html";
//				var assignRoute = "/dashboard";
//				var href=$window.location.origin+ $window.location.pathname+"#"+assignRoute;
//				  console.log("Assign to url: "+href);
//				  $window.location.assign(href); 
//				  $window.location.reload();

			}
		});
		
		
		
	}
});

//app.controller("loginCtrl",function($cookies){
//	$cookies.name = 'hanxiao';
////	console.log($cookies);
//	console.log($cookies.name);
//});