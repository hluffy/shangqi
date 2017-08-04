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
			lat:30.8866138,
			lng:121.8269695
		},{
			lat:30.8866132,
			lng:121.8272799
		},{
			lat:30.8862029,
			lng:121.8272796
		},{
			lat:30.8862015,
			lng:121.8269711
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
			lat:30.8860992,
			lng:121.8266307
		},{
			lat:30.8860992,
			lng:121.8267125
		},{
			lat:30.8851775,
			lng:121.8267121
		},{
			lat:30.8851776,
			lng:121.8266307
		}]);
		$scope.dvt.addTo($scope.map);
		$scope.dvt.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="DVT返修区";
				$scope.local={"area":"DVT返修区"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
//			angular.element("#myButton").click();
			$("#myButton").click();
		});
		
		//板链返修区
		$scope.banlian = L.polygon([{
			lat:30.8851772,
			lng:121.8269735
		},{
			lat:30.8852733,
			lng:121.8269738
		},{
			lat:30.8852753,
			lng:121.8272028
		},{
			lat:30.8851795,
			lng:121.8272045
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
		
		//内饰门线
		$scope.neishier = L.polygon([{
			lat:30.8851769,
			lng:121.826895
		},{
			lat:30.8851772,
			lng:121.827058
		},{
			lat:30.8841428,
			lng:121.827064
		},{
			lat:30.8841428,
			lng:121.8268977
		}]);
//		.addTo($scope.map).bindPopup("内饰二")
		$scope.neishier.addTo($scope.map);
		$scope.neishier.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="内饰门线";
				$scope.local = {"area":"内饰门线"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
			$("#myButton").click();
		});
		
		//底盘三
		$scope.dipansan = L.polygon([{
			lat:30.885273,
			lng:121.8269738
		},{
//			lat:30.8851775,
//			lng:121.8269738
//		},{
//			lat:30.8851798,
//			lng:121.8272042
//		},{
			lat:30.885275,
			lng:121.8272031
		},{
			lat:30.8862029,
			lng:121.8272031
		},{
			lat:30.8862015,
			lng:121.8269711
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
			lat:30.8851694,
			lng:121.8289429
		},{
			lat:30.8851691,
			lng:121.8290441
		},{
			lat:30.8853121,
			lng:121.8290441
		},{
			lat:30.8853121,
			lng:121.8289426
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
			lat:30.8870851,
			lng:121.8269004
		},{
			lat:30.8870857,
			lng:121.8270586
		},{
			lat:30.8869142,
			lng:121.8270573
		},{
			lat:30.8869153,
			lng:121.8269001
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
		
		//总装滞留区
		$scope.zhiliu = L.polygon([{
			lat:30.8868564,
			lng:121.8271093
		},{
			lat:30.8866515,
			lng:121.8271093
		},{
			lat:30.8866518,
			lng:121.8272652
		},{
			lat:30.8868578,
			lng:121.8272648
		}]);
//		.addTo($scope.map).bindPopup("滞留区")
		$scope.zhiliu.addTo($scope.map);
		$scope.zhiliu.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea = "总装滞留区";
				$scope.local = {"area":"总装滞留区"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
			$("#myButton").click();
		});
		
		//物流区
		$scope.wuliu = L.polygon([{
			lat:30.8874482,
			lng:121.8267596
		},{
			lat:30.8874482,
			lng:121.8271565
		},{
			lat:30.8883321,
			lng:121.8271458
		},{
			lat:30.8883321,
			lng:121.827575
		},{
			lat:30.888903,
			lng:121.8275642
		},{
			lat:30.8889122,
			lng:121.8296778
		},{
			lat:30.890772,
			lng:121.8296778
		},{
			lat:30.8907536,
			lng:121.8267703
		}],{
//			strokeWeight:'0',
//			strokeOpacity:'0',
//			strokeColor:'red',
//			fillColor:'red',
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
		
		//物流2区
//		$scope.wuliu = L.polygon([{
//			lat:30.891085,
//			lng:121.8280363
//		},{
//			lat:30.8910666,
//			lng:121.8300319
//		},{
//			lat:30.8946757,
//			lng:121.8300319
//		},{
//			lat:30.8946572,
//			lng:121.8265128
//		},{
//			lat:30.8925029,
//			lng:121.8265342
//		},{
//			lat:30.8924845,
//			lng:121.8280577
//		}],{
//			color: 'red'
//		});
////		.addTo($scope.map).bindPopup("物流区")
//		$scope.wuliu.addTo($scope.map);
//		$scope.wuliu.on("click",function(){
//			$scope.$apply(function(){
//				$scope.thisarea = "物流2区";
//				$scope.local = {"area":"物流2区"};
//				localService.getInfo($scope.local).then(function(data){
//					$scope.areainfos = data.data;
//				});
//			});
//			$("#myButton").click();
//		});
		
		//油漆返修区
		$scope.dvt = L.polygon([{
			lat:30.8866145,
			lng:121.8266318
		},{
			lat:30.8866142,
			lng:121.8267153
		},{
			lat:30.8863064,
			lng:121.8267118
		},{
			lat:30.8863064,
			lng:121.8266327
		}]);
		$scope.dvt.addTo($scope.map);
		$scope.dvt.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="油漆返修区";
				$scope.local={"area":"油漆返修区"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
//			angular.element("#myButton").click();
			$("#myButton").click();
		});
		
		//车身返修区
		$scope.dvt = L.polygon([{
			lat:30.8863065,
			lng:121.8266327
		},{
			lat:30.8863065,
			lng:121.8267118
		},{
			lat:30.8860993,
			lng:121.8267126
		},{
			lat:30.8860993,
			lng:121.8266307
		}]);
		$scope.dvt.addTo($scope.map);
		$scope.dvt.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="车身返修区";
				$scope.local={"area":"车身返修区"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
//			angular.element("#myButton").click();
			$("#myButton").click();
		});
		
		//检测区
		$scope.dvt = L.polygon([{
			lat:30.8851773,
			lng:121.8267123
		},{
			lat:30.8851775,
			lng:121.826974
		},{
			lat:30.8866132,
			lng:121.8269701
		},{
			lat:30.8866138,
			lng:121.826715
		}]);
		$scope.dvt.addTo($scope.map);
		$scope.dvt.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="检测区";
				$scope.local={"area":"检测区"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
//			angular.element("#myButton").click();
			$("#myButton").click();
		});
		
		//车身滞留区
		$scope.dvt = L.polygon([{
			lat:30.8867994,
			lng:121.8266305
		},{
			lat:30.8866168,
			lng:121.8266315
		},{
			lat:30.8866165,
			lng:121.8267163
		},{
			lat:30.8867995,
			lng:121.8267163
		}]);
		$scope.dvt.addTo($scope.map);
		$scope.dvt.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="车身滞留区";
				$scope.local={"area":"车身滞留区"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
//			angular.element("#myButton").click();
			$("#myButton").click();
		});
		
		//扣车区
		$scope.dvt = L.polygon([{
			lat:30.8871012,
			lng:121.8263713
		},{
			lat:30.8871011,
			lng:121.8264758
		},{
			lat:30.8868258,
			lng:121.8264775
		},{
			lat:30.8868255,
			lng:121.8263719
		}]);
		$scope.dvt.addTo($scope.map);
		$scope.dvt.on("click",function(){
			$scope.$apply(function(){
				$scope.thisarea="扣车区";
				$scope.local={"area":"扣车区"};
				localService.getInfo($scope.local).then(function(data){
					$scope.areainfos = data.data;
				});
			});
//			angular.element("#myButton").click();
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