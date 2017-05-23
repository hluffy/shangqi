app.service("userService",function($http,$q){
	 this.transform = function(jsonData){
	        var string = '';
	        
	        for(str in jsonData){
	            string = string + str +'=' + jsonData[str] +'&';
	        }
	        
	        var _last = string.lastIndexOf('&');
	        
	        string = string.substring(0,_last);
	        
	        return string;
	};
	
	this.getUserInfo = function(user){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/user/getinfo.ll",
			data:user,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	    
	this.getUserInfos = function(){
		var deferred = $q.defer();
		$http.get("http://localhost:8080/shangqi/user/getinfos.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("连接失败");
		});
		
		return deferred.promise;
	}
	
	this.updateUserInfo = function(user){
		console.log(user);
		var deferred = $q.defer();
//		$http.get("http://localhost:8080/management/user/updateinfo.ll").success(function(data){
//			deferred.resolve(data);
//		}).error(function(){
//			deferred.reject("更新失败");
//		});
		$http({
			method:'post',
			url:'http://localhost:8080/shangqi/user/updateinfo.ll',
			data:user,
			dataType: 'json'
//			data:{"userName":"hanxiao","password":"hanxiao"},
//			data:userService.transform(user)
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("更新失败")
		});
		
		return deferred.promise;
	}
	
	this.saveUserInfo = function(user){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/user/addinfo.ll",
			data:user,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("保存失败");
		});
		return deferred.promise;
	}
	
	this.deleteUserInfo = function(user){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/user/deleteinfo.ll",
			data:user,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(data){
			deferred.reject("删除成功");
		});
		
		return deferred.promise;
	}
});