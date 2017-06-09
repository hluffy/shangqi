app.service("loraService",function($http,$q){
	this.getInfos = function(){
		var deferred = $q.defer();
		$http.get("/shangqi/lora/getinfos.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	this.addInfo = function(lora){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/lora/addinfo.ll",
			data:lora,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("添加失败");
		});
		
		return deferred.promise;
	}
	
	this.updateInfo = function(lora){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/lora/updateinfo.ll",
			data:lora,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("更新失败");
		});
		
		return deferred.promise;
	}
	
	this.deleteInfo = function(lora){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/lora/deleteinfo.ll",
			data:lora,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("删除失败");
		});
		
		return deferred.promise;
	}
	
	this.getInfo = function(lora){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/lora/getinfo.ll",
			data:lora,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	this.loraRestart = function(lora){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/remote/restartlora.ll",
			data:lora,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("重启失败");
		});
		
		return deferred.promise;
	}
	
	this.loraSyncTime = function(lora){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/remote/synctime.ll",
			data:lora,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("同步失败");
		});
		
		return deferred.promise;
	}
});