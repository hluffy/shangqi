app.service("positionSer",function($http,$q){
	this.getPositionInfos = function(){
		var deferred = $q.defer();
		$http.get("http://localhost:8080/shangqi/position/getinfos.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("连接失败");
		});
		
		return deferred.promise;
	}
	
	this.getPositionInfo = function(position){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/position/getinfo.ll",
			data:position,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	this.getInfoForPie = function(){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/position/getinfoforpie.ll",
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	this.addInfo = function(position){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/position/addinfo.ll",
			data:position,
			dataType:'json'
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("添加失败");
		});
		
		return deferred.promise;
	}
	
	this.updateInfo = function(position){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/position/updateinfo.ll",
			data:position,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("更新失败");
		});
		
		return deferred.promise;
	}
	
	this.deleteInfo = function(position){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/position/deleteinfo.ll",
			data:position,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("删除失败");
		});
		
		return deferred.promise;
	}
	
	//低电量饼图
	this.getLowInfo = function(){
		var deferred = $q.defer();
		$http.get("http://localhost:8080/shangqi/position/getlowinfo.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(data){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
});