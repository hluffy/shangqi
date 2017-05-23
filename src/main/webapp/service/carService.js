app.service("carService",function($http,$q){
	this.getCarInfos = function(){
		var deferred = $q.defer();
		$http.get("http://localhost:8080/shangqi/car/getinfos.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("连接失败");
		});
		
		return deferred.promise;
	}
	
	this.getCarInfo = function(car){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/car/getinfo.ll",
			data:car,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	this.updateCarInfo = function(car){
		var deferred = $q.defer();
		$http({
			method:'post',
			url:'http://localhost:8080/shangqi/car/updateinfo.ll',
			data:car,
			dataType:'json'
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("更新失败");
		});
		
		return deferred.promise;
	}
	
	this.addCarInfo = function(car){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/car/addinfo.ll",
			data:car,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("添加失败");
		});
		
		return deferred.promise;
	}
	
	this.deleteCarInfo = function(car){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/car/deleteinfo.ll",
			data:car,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("删除失败");
		});
		
		return deferred.promise;
	}
});
