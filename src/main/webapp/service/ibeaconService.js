app.service("ibeaconSer",function($http,$q){
	this.getArea = function(){
		var deferred = $q.defer();
		$http.get("data/area.json").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		return deferred.promise;
	}
	
	this.getIbeaconInfos = function(){
		var deferred = $q.defer();
		$http.get("/shangqi/ibeacon/getinfos.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("连接失败");
		});
		
		return deferred.promise;
	}
	
	this.getIbeaconInfo = function(ibeacon){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/ibeacon/getinfo.ll",
			data:ibeacon,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	this.updateIbeaconInfo = function(ibeacon){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/ibeacon/updateinfo.ll",
			data:ibeacon,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("更新失败");
		});
		return deferred.promise;
	}
	
	this.addIbeaconInfo = function(ibeacon){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/ibeacon/addinfo.ll",
			data:ibeacon,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("保存失败");
		});
		
		return deferred.promise;
	}
	
	this.deleteIbeaconInfo = function(ibeacon){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/ibeacon/deleteinfo.ll",
			data:ibeacon,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("删除失败");
		});
		
		return deferred.promise;
	}
	
	this.getInfoAsArea = function(){
		var deferred = $q.defer();
		$http.get("/shangqi/ibeacon/getinfoasarea.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	
});