app.service("localService",function($http,$q){
	this.getInfos = function(){
		var deferred = $q.defer();
		$http.get("http://localhost:8080/shangqi/local/getinfos.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	this.addInfo = function(local){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/local/addinfo.ll",
			data:local,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("添加失败");
		});
		
		return deferred.promise;
	}
	
	this.updateInfo = function(local){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/local/updateinfo.ll",
			data:local,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("更新失败");
		});
		
		return deferred.promise;
	}
	
	this.deleteInfo = function(local){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/local/deleteinfo.ll",
			data:local,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("删除失败");
		});
		
		return deferred.promise;
	}
	
	this.getInfo = function(local){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/local/getinfo.ll",
			data:local,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	this.getAreaDatas = function(){
		var deferred = $q.defer();
		$http.get("http://localhost:8080/shangqi/local/getareadatas.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	this.getInfoOfArea = function(area){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/local/getinfoofarea.ll",
			data:area,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	//重启Lora模块
	this.restartLoraMac = function(local){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"http://localhost:8080/shangqi/remote/restartloramac.ll",
			data:local,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("重启失败");
		});
		
		return deferred.promise;
	}
	
	//设备数量饼图
	this.getEquipPie = function(){
		var deferred = $q.defer();
		$http.get("http://localhost:8080/shangqi/local/getequippie.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	//设备电量饼图
	this.getLowInfo = function(){
		var deferred = $q.defer();
		$http.get("http://localhost:8080/shangqi/local/lowinfo.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
});