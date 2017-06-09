//app.service("loginService",function($http,$q){
//		this.userLogin=function(user){
//			var deferred = $q.defer();
//			$http.get("../resources/user/users.json").success(function(data){
//				deferred.resolve(data);
//			}).error(function(){
//				deferred.reject("连接失败");
//			});
//			
//			return deferred.promise;
//		}
//		
//	});

app.service("loginService",function($http,$q,$location){
//	this.userLogin=function(user){
//		var deferred = $q.defer();
//		$http.jsonp("http://localhost:8080/carlocation/user/userlogin.ll?callback=JSON_CALLBACK&userName="+user.userName+"&password="+user.password).success(function(data){
//			console.log(data);
//			deferred.resolve(data);
//		}).error(function(){
//			deferred.reject("登录失败");
//		});
//		
//		return deferred.promise;
//	}
	
	this.userLogin = function(user){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/user/logininfo.ll",
			data:user,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("登录失败");
		});
		
		return deferred.promise;
	}
});
