app.controller("mapAreaCtrl",function($rootScope,$scope,$timeout,mapServ,localService){
//		$scope.map = {};
		
// 		function initmap(){
//		var centerPoint = [31.1707739,121.406607];
		$scope.centerPoint = [30.8853,121.8258];
		$scope.map = L.map('leafletMap').setView($scope.centerPoint, 16);
		
		$scope.aerial = new L.tileLayer("/Tiles/{z}/{x}/{y}.png", {
    		maxZoom: 21,
    		minZoom: 15,
		});
		$scope.aerial.addTo($scope.map);
		
		//CARE
		$scope.car = L.polygon([{
			lat:30.8866104,
			lng:121.8266308
		},{
			lat:30.8862329,
			lng:121.8266255
		},{
			lat:30.8862329,
			lng:121.8270063
		},{
			lat:30.886165,
			lng:121.8270037
		},{
			lat:30.8861615,
			lng:121.8272813
		},{
			lat:30.8866092,
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
				$scope.thisarea="CARE";
				$scope.local={"area":"CARE"};
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
			lat:30.8862352,
			lng:121.8266281
		},{
			lat:30.8851671,
			lng:121.8266281
		},{
			lat:30.8851684,
			lng:121.8268454
		},{
			lat:30.8862352,
			lng:121.8268427
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
			lat:30.885296,
			lng:121.8268454
		},{
			lat:30.8851717,
			lng:121.8268481
		},{
			lat:30.8851694,
			lng:121.827229
		},{
			lat:30.885296,
			lng:121.8272263
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
		
		//内饰一
		$scope.neishier = L.polygon([{
			lat:30.8851671,
			lng:121.8269339
		},{
			lat:30.884152,
			lng:121.8269339
		},{
			lat:30.8841497,
			lng:121.8270653
		},{
			lat:30.8851717,
			lng:121.8270573
		}]);
//		.addTo($scope.map).bindPopup("内饰二")
		$scope.neishier.addTo($scope.map);
		$scope.neishier.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="内饰一";
				$scope.local = {"area":"内饰一"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
			$("#myButton").click();
		});
		
		//底盘三
		$scope.dipansan = L.polygon([{
			lat:30.8862352,
			lng:121.8268427
		},{
			lat:30.8862352,
			lng:121.827001
		},{
			lat:30.8861615,
			lng:121.8270037
		},{
			lat:30.8861615,
			lng:121.8270358
		},{
			lat:30.885296,
			lng:121.8270385
		},{
			lat:30.8852983,
			lng:121.8268427
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
			lat:30.8852879,
			lng:121.8289322
		},{
			lat:30.8851752,
			lng:121.8289335
		},{
			lat:30.8851775,
			lng:121.8290462
		},{
			lat:30.8852914,
			lng:121.8290462
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
			lat:30.8867246,
			lng:121.827126
		},{
			lat:30.8866621,
			lng:121.8271254
		},{
			lat:30.8866621,
			lng:121.8272548
		},{
			lat:30.8867243,
			lng:121.8272541
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
			lat:30.886657,
			lng:121.8272672
		},{
			lat:30.886619,
			lng:121.8272679
		},{
			lat:30.886619,
			lng:121.8273282
		},{
			lat:30.8866573,
			lng:121.8273275
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
			lat:30.8900999,
			lng:121.8267596
		},{
			lat:30.8883689,
			lng:121.826781
		},{
			lat:30.8883551,
			lng:121.827127
		},{
			lat:30.8867715,
			lng:121.8271217
		},{
			lat:30.8867646,
			lng:121.8275374
		},{
			lat:30.8887971,
			lng:121.8275616
		},{
			lat:30.8888063,
			lng:121.8297556
		},{
			lat:30.890093,
			lng:121.829761
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