app.service("employService",function($http,$q){
	this.getEmployInfo = function(employ){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/employ/getinfo.ll",
			data:employ,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	    
	this.getEmployInfos = function(){
		var deferred = $q.defer();
		$http.get("/shangqi/employ/getinfos.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("连接失败");
		});
		
		return deferred.promise;
	}
	
	this.updateEmployInfo = function(employ){
		var deferred = $q.defer();
//		$http.get("http://localhost:8080/management/user/updateinfo.ll").success(function(data){
//			deferred.resolve(data);
//		}).error(function(){
//			deferred.reject("更新失败");
//		});
		$http({
			method:'post',
			url:'/shangqi/employ/updateinfo.ll',
			data:employ,
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
	
	this.saveEmployInfo = function(employ){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/employ/addinfo.ll",
			data:employ,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("保存失败");
		});
		return deferred.promise;
	}
	
	this.deleteEmployInfo = function(employ){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/employ/deleteinfo.ll",
			data:employ,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(data){
			deferred.reject("删除失败");
		});
		
		return deferred.promise;
	}
	
	this.leadIn = function(employ){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/employ/leadin.ll",
			data:employ,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("导入失败");
		});
		
		return deferred.promise;
	}
	
});