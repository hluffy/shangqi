app.service("bindService",function($q,$http){
	this.getBindInfos = function(){
		var deferred = $q.defer();
		$http.get("/shangqi/bind/getinfos.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	this.addBindInfo = function(bind){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/bind/addinfo.ll",
			data:bind,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("添加失败");
		});
		
		return deferred.promise;
	}
	
	this.updateBindInfo = function(bind){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/bind/updateinfo.ll",
			data:bind,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("解绑失败");
		});
		
		return deferred.promise;
	}
	
	this.getBindInfo = function(bind){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/bind/getinfo.ll",
			data:bind,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	this.getInfoForCharts = function(){
		var deferred = $q.defer();
		$http.get("/shangqi/bind/getinfoforcharts.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
});