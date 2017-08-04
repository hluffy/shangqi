app.controller("dashboardCtrl",function($rootScope,$scope,localService,positionSer){
//	$scope.bind={};
//	$scope.bind.bindType = "绑定";
	$rootScope.countId="800px";
//	$scope.bindCarNum = 12;
//	$scope.surEquipNum = 24;
//	$scope.lowEleNum = 12;
//	bindService.getBindInfo($scope.bind).then(function(data){
//		$scope.bindCarNum = data.count;
//	});
//	userService.getUserInfos().then(function(data){
//		$scope.userNum = data.data.length;
//	});
//	carService.getCarInfos().then(function(data){
//		$scope.carNum = data.data.length;
//	});
//	ibeaconSer.getIbeaconInfos().then(function(data){
//		$scope.ibeaconNum = data.data.length;
//	});
	localService.getEquipPie().then(function(result){
		$scope.datas = result.data;
		for(var i=0;i<$scope.datas.length;i++){
			if($scope.datas[i].name=="已绑定设备"){
				$scope.bindCarNum = $scope.datas[i].value;
			}
			if($scope.datas[i].name=="剩余设备"){
				$scope.surEquipNum = $scope.datas[i].value;
			}
		}
	});
	localService.getLowInfo().then(function(result){
		$scope.lowdatas = result.data;
		for(var i =0;i<$scope.lowdatas.length;i++){
			if($scope.lowdatas[i].name=="要充电的设备"){
				$scope.lowEleNum = $scope.lowdatas[i].value;
			}
			if($scope.lowdatas[i].name=="正常的设备"){
				$scope.noLowEleNum = $scope.lowdatas[i].value;
			}
		}
	});
	
	
});