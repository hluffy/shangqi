app.service("mapServ",function($http,$q){
	
	this.getPositionInfoAsFrameNum = function(map){
		console.log(map);
		var deferred = $q.defer();

		$http({
			method:"post",
			url:"/shangqi/position/getinfoasframenum.ll",
			data:map,
//			async:false,
			dataType:"json"
		}).success(function(data){

//		$http.get("/shangqi/position/getinfoasframenum.ll?frameNum="+map.framenum).success(function(data){

			deferred.resolve(data);
		}).error(function(){
			deferred.reject();
		});
//		$http.get("http://localhost:8080/shangqi/position/getinfoasframenum.ll").success(function(data){
//			deferred.resolve(data);
//		}).error(function(){
//			deferred.reject("查询失败");
//		});
		
		return deferred.promise;
	}
	
	this.getdamagedcardata=function(A){
		console.log(A);
		var deferredone=$q.defer();
		$http.get("http://localhost:8080/shangqi/position/getifdamagedcardata.ll?Theinputdata="+A.Theinputdata).success(function(data){
			deferredone.resolve(data);
		}).error(function(){
			deferredone.reject("查询失败");
		});
		return deferredone.promise;
	}
	
	this.getinfoastime = function(map){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/position/getinfoastime.ll",
			data:map,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	this.getibeaconformap = function(){
		var deferred = $q.defer();
		$http.get("/shangqi/ibeacon/getinbeaconmap.ll").success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
	
	this.getinfoasuuid = function(map){
		var deferred = $q.defer();
		$http({
			method:"post",
			url:"/shangqi/ibeacon/getinfoasuuid.ll",
			data:map,
			dataType:"json"
		}).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
});