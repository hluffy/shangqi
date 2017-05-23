app.service("mapServ",function($http,$q){
	
	this.getPositionInfoAsFrameNum = function(map){
		console.log(map);
		var deferred = $q.defer();
		$http.get("http://localhost:8080/shangqi/position/getinfoasframenum.ll?frameNum="+map.framenum).success(function(data){
			deferred.resolve(data);
		}).error(function(){
			deferred.reject("查询失败");
		});
		
		return deferred.promise;
	}
});