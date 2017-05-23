app.controller("areaCtrl",function($rootScope,$scope,localService){
	$scope.area={};
	$rootScope.areainfo = false;
	localService.getAreaDatas().then(function(data){
		$rootScope.areas=data.data;
		$scope.sumareadata=data.count;
	});
	
//	localService.getInfo($scope.area).then(function(data){
//		console.log(data);
//		$rootScope.areainfos = data.data;
//	});
});

app.directive("areainfo",function($rootScope,$document,localService){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel,index){
			element.bind("click",function(){
				$rootScope.areainfo = true;
				$rootScope.countId="100%";
				scope.$apply(function(){
					localService.getInfo(ngModel.$modelValue).then(function(data){
						$rootScope.areainfos = data.data;
					});
				})
			});
		}
	}
});