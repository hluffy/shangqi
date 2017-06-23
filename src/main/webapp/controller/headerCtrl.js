app.controller("headerCtrl",function($rootScope,$scope,localService,$interval){
	$scope.local = {};
	$scope.local.area = "物流区";
	$scope.local.page = 0;
	localService.getInfo($scope.local).then(function(data){
		$scope.warningNum = data.count;
		$scope.warningInfos = data.data;
		console.log($scope.warningInfos);
	});
//	$interval(function(){
//		console.log("interval");
//	},2000);
//	$interval(function(){
//		localService.getInfo($scope.local).then(function(data){
//		$scope.warningNum = data.count;
//		$scope.warningInfos = data.data;
//		console.log($scope.warningInfos);
//	});
//	},10000)
});