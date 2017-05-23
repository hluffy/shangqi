app.controller("mapAreaCtrl",function($rootScope,$scope,$timeout,mapServ,localService){
//		$scope.map = {};
		
// 		function initmap(){
//		var centerPoint = [31.1707739,121.406607];
		$scope.centerPoint = [30.8853,121.8258];
		$scope.map = L.map('leafletMap').setView($scope.centerPoint, 16);
		
		$scope.aerial = new L.tileLayer("http://localhost:8080/Tiles/{z}/{x}/{y}.png", {
    		maxZoom: 21,
    		minZoom: 15,
		});
		$scope.aerial.addTo($scope.map);
		
		//CAR
		$scope.car = L.polygon([{
			lat:30.8866072,
			lng:121.8266349
		},{
			lat:30.8863413,
			lng:121.8266332
		},{
			lat:30.8863382,
			lng:121.8269272
		},{
			lat:30.886173,
			lng:121.8269312
		},{
			lat:30.886177,
			lng:121.8272759
		},{
			lat:30.8866052,
			lng:121.8272786
		}]);
		$scope.car.addTo($scope.map);
//		.bindPopup("CAR")
//		$scope.car.on("mouseover",function(){
//			console.log(2222);
//			$scope.car.addTo($scope.map).bindPopup("CAR");//点击时触发
//			
//		});
		
		$scope.car.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="CAR";
				$scope.local={"area":"CAR"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
//			angular.element("#myButton").click();
			$("#myButton").click();
		});
//		car.on("click",function(){
//			var modalInstance = $modal.open({  
//				templateUrl: 'myModalContent.html',  
//				controller: ModalInstanceCtrl,  
////				resolve: {  
////					items: function () {  
////						return $scope.items;  
////					}  
////				}  
//			}); 
//			modalInstance.opened.then(function() {// 模态窗口打开之后执行的函数  
////                console.log('modal is opened');  
//				console.log(modalInstance);
//            });  
//            modalInstance.result.then(function(result) {  
//                console.log(result);  
//            }, function(reason) {  
//                console.log(reason);// 点击空白区域，总会输出backdrop  
//                // click，点击取消，则会暑促cancel  
////                $log.info('Modal dismissed at: ' + new Date());  
//            });
//            
//            var ModalInstanceCtrl = function($scope, $modalInstance, items) {  
//                $scope.items = items;  
//                $scope.selected = {  
//                    item : $scope.items[0]  
//                };  
//                $scope.ok = function() {  
//                    $modalInstance.close($scope.selected);  
//                };  
//                $scope.cancel = function() {  
//                    $modalInstance.dismiss('cancel');  
//                };  
//            }; 
//			
//		});
		
		//DVT
		$scope.dvt = L.polygon([{
			lat:30.8863399,
			lng:121.8266335
		},{
			lat:30.8855217,
			lng:121.8266302
		},{
			lat:30.8855203,
			lng:121.8268727
		},{
			lat:30.8863387,
			lng:121.8268649
		}]);
		$scope.dvt.addTo($scope.map);
		$scope.dvt.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="DVT";
				$scope.local={"area":"DVT"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
//			angular.element("#myButton").click();
			$("#myButton").click();
		});
		
		//板链返修区
		$scope.banlian = L.polygon([{
			lat:30.8855199,
			lng:121.8266302
		},{
			lat:30.8851306,
			lng:121.8266318
		},{
			lat:30.8851354,
			lng:121.827227
		},{
			lat:30.885517,
			lng:121.8272303
		}]);
//		.addTo($scope.map).bindPopup("板链返修区")
		$scope.banlian.addTo($scope.map);
		$scope.banlian.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="板链返修区";
				$scope.local={"area":"板链返修区"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
			$("#myButton").click();
		});
		
		//内饰二
		$scope.neishier = L.polygon([{
			lat:30.8851211,
			lng:121.8267126
		},{
			lat:30.8837422,
			lng:121.8267274
		},{
			lat:30.883733,
			lng:121.8269151
		},{
			lat:30.8851199,
			lng:121.8269111
		}]);
//		.addTo($scope.map).bindPopup("内饰二")
		$scope.neishier.addTo($scope.map);
		$scope.neishier.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="内饰二";
				$scope.local = {"area":"内饰二"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
			$("#myButton").click();
		});
		
		//底盘三
		$scope.dipansan = L.polygon([{
			lat:30.8861707,
			lng:121.8270747
		},{
			lat:30.8856666,
			lng:121.8270788
		},{
			lat:30.8856666,
			lng:121.827186
		},{
			lat:30.8861661,
			lng:121.8271807
		}]);
//		.addTo($scope.map).bindPopup("底盘三")
		$scope.dipansan.addTo($scope.map);
		$scope.dipansan.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="底盘三";
				$scope.local = {"area":"底盘三"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
			$("#myButton").click();
		});
		
		//底盘一二
		$scope.other = L.polygon([{
			lat:30.8855032,
			lng:121.8287444
		},{
			lat:30.885128,
			lng:121.8287417
		},{
			lat:30.8851303,
			lng:121.8290368
		},{
			lat:30.8855009,
			lng:121.8290421
		}]);
//		.addTo($scope.map).bindPopup("物流区下方")
		$scope.other.addTo($scope.map);
		$scope.other.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea = "底盘一二";
				$scope.local = {"area":"底盘一二"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
			$("#myButton").click();
		});
		
		//报交区
		$scope.baojiao = L.polygon([{
			lat:30.8867214,
			lng:121.8271257
		},{
			lat:30.8866604,
			lng:121.8271264
		},{
			lat:30.8866604,
			lng:121.8272544
		},{
			lat:30.886722,
			lng:121.8272544
		}]);
//		.addTo($scope.map).bindPopup("报交区")
		$scope.baojiao.addTo($scope.map);
		$scope.baojiao.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="报交区";
				$scope.local = {"area":"报交区"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
			$("#myButton").click();
		});
		
		//滞留区
		$scope.zhiliu = L.polygon([{
			lat:30.8866564,
			lng:121.8272477
		},{
			lat:30.8866161,
			lng:121.8272481
		},{
			lat:30.8866158,
			lng:121.8273409
		},{
			lat:30.8866585,
			lng:121.8273413
		}]);
//		.addTo($scope.map).bindPopup("滞留区")
		$scope.zhiliu.addTo($scope.map);
		$scope.zhiliu.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea = "滞留区";
				$scope.local = {"area":"滞留区"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
			$("#myButton").click();
		});
		
		//物流区
		$scope.wuliu = L.polygon([{
			lat:30.8900769,
			lng:121.8267918
		},{
			lat:30.8883551,
			lng:121.8267864
		},{
			lat:30.8883413,
			lng:121.8271351
		},{
			lat:30.8867358,
			lng:121.8271244
		},{
			lat:30.8867312,
			lng:121.827583
		},{
			lat:30.8889283,
			lng:121.8275803
		},{
			lat:30.8889076,
			lng:121.8296939
		},{
			lat:30.8900953,
			lng:121.8296832
		}],{
			color: 'red'
		});
//		.addTo($scope.map).bindPopup("物流区")
		$scope.wuliu.addTo($scope.map);
		$scope.wuliu.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea = "物流区";
				$scope.local = {"area":"物流区"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
			$("#myButton").click();
		});
		
		
		
		
		L.control.scale().addTo($scope.map);//添加比例尺
// 		}
	
	
// 		$timeout(function(){
// 			initmap();
// 		}, 100);
//		var marker = L.marker();
//		$scope.getPositionInfo = function(){
//			
//			map.removeLayer(marker);
//			mapServ.getPositionInfoAsFrameNum($scope.map).then(function(data){
//				console.log(data);
//				marker = L.marker([data.data.log,data.data.lat]);
//				map.addLayer(marker);
//				marker.bindPopup('test marker').openPopup();
//			});
//		}
});