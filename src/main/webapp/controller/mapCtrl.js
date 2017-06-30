app.controller("mapCtrl",function($scope,$timeout,mapServ,$rootScope){
	$rootScope.countId="100%";
	$scope.map = {};
	$scope.A = {};
// 		function initmap(){
//		var centerPoint = [31.1707739,121.406607];
		var centerPoint = [30.8853,121.8258];
		var map = L.map('leafletMap').setView(centerPoint, 16);
		
		var aerial = new L.tileLayer("/Tiles/{z}/{x}/{y}.png", {
    		maxZoom: 21,
    		minZoom: 15,
		});
		aerial.addTo(map);
		
		
//		$scope.getPositionInfo = function(){
//			$rootScope.pages = new Array();
//			$scope.map.page=0;
//			$scope.map.statittime = $("#statittime").val();
//			$scope.map.endtime = $("#endtime").val();
////			$rootScope.indexPage=1;
//				 map.remove();
//				 map = L.map('leafletMap').setView(centerPoint, 25);
//					var aerial = new L.tileLayer("/Tiles/{z}/{x}/{y}.png", {
//					});
//					aerial.addTo(map);
//				map.removeLayer(marker);
//				console.log(23423);
//				console.log($scope.map);
//				mapServ.getPositionInfoAsFrameNum($scope.map).then(function(data){
//					if(data.data!=null){
//						console.log(data);
//						for(var i=0;i<data.data.length;i++){
//							map.setView([data.data[i].log,data.data[i].lat],19);
//							marker = L.marker([data.data[i].log,data.data[i].lat]);
//							map.addLayer(marker);
//							marker.bindPopup('基本信息：'+data.data[i].frameNum+"<br/>"+
//									"区域："+data.data[i].area).openPopup();
//						}
//					}else if (data.tata.lat==null && data.data.log==null) {
//						alert("该数据没有位置信息,或输入有误");
//					}
//					else{
//						alert("请确认是否绑定定位器");
//					}
//				
//				});
//		}
//		//禁区
//		L.polygon([{  
//            lat: 30.8907444,  
//            lng: 121.8267596
//        }, {  
//            lat: 30.8886544,  
//            lng: 121.8268025  
//        }, {  
//            lat: 30.888682,  
//            lng: 121.8296885              
//        }, {
//        	lat: 30.8907352,
//        	lng: 121.8296885
//        }],{
//        	color: 'red'
//        }).addTo(map).bindPopup("禁区");
//		
//		//A区
//		L.polygon([{  
//            lat: 30.8865839,  
//            lng: 121.826663
//        }, {  
//            lat: 30.8863882,  
//            lng: 121.826663 
//        }, {  
//            lat: 30.8863894,  
//            lng: 121.826887             
//        }, {
//        	lat: 30.8865793,
//        	lng: 121.8268897
//        }],{
//        	color: 'blue'
//        }).addTo(map).bindPopup("A区");
//		
//		//B区
//		L.polygon([{  
//            lat: 30.8861638,  
//            lng: 121.8266872
//        }, {  
//            lat: 30.8857334,  
//            lng: 121.8266845
//        }, {  
//            lat: 30.8857334,  
//            lng: 121.8272316              
//        }, {
//        	lat: 30.8861661,
//        	lng: 121.8272236
//        }],{
//        	color: 'blue'
//        }).addTo(map).bindPopup("B区");
//		
//		//C区
//		L.polygon([{  
//            lat: 30.8863744,  
//            lng: 121.8269339
//        }, {  
//            lat: 30.886196,  
//            lng: 121.8269339
//        }, {  
//            lat: 30.8861949,  
//            lng: 121.8272598              
//        }, {
//        	lat: 30.8863733,
//        	lng: 121.8272611
//        }],{
//        	color: 'blue'
//        }).addTo(map).bindPopup("C区");
//		
//		//D区
//		L.polygon([{  
//            lat: 30.8865804,  
//            lng: 121.8270043
//        }, {  
//            lat: 30.8863911,  
//            lng: 121.8270057
//        }, {  
//            lat: 30.8863934,  
//            lng: 121.8272611              
//        }, {
//        	lat: 30.8865827,
//        	lng: 121.8272605
//        }],{
//        	color: 'blue'
//        }).addTo(map).bindPopup("D区");
//		
//		//E区
//		L.polygon([{  
//            lat: 30.8863681,  
//            lng: 121.8266623
//        }, {  
//            lat: 30.8862357,  
//            lng: 121.8266623
//        }, {  
//            lat: 30.8862386,  
//            lng: 121.8268494              
//        }, {
//        	lat: 30.8863692,
//        	lng: 121.8268488
//        }],{
//        	color: 'blue'
//        }).addTo(map).bindPopup("E区");
//		
//		//F区
//		L.polygon([{  
//            lat: 30.8855653,  
//            lng: 121.8267113
//        }, {  
//            lat: 30.8849945,  
//            lng: 121.8267059
//        }, {  
//            lat: 30.8849945,  
//            lng: 121.8273443             
//        }, {
//        	lat: 30.885464,
//        	lng: 121.8271941
//        }],{
//        	color: 'blue'
//        }).addTo(map).bindPopup("F区");
		
		L.control.scale().addTo(map);//添加比例尺
		var marker = L.marker();
		var number=0;
		$scope.getPositionInfo = function(){
			$rootScope.pages = new Array();
			$scope.map.page=0;
			$scope.map.statittime = $("#statittime").val();
			$scope.map.endtime = $("#endtime").val();
			 number++;
			 map.remove();
			 map = L.map('leafletMap').setView(centerPoint, 25);
				var aerial = new L.tileLayer("/Tiles/{z}/{x}/{y}.png", {
				});
				aerial.addTo(map);
			map.removeLayer(marker);
			mapServ.getPositionInfoAsFrameNum($scope.map).then(function(data){
				console.log(53546);
				console.log(data.data);
				if(data.data !=null){
					console.log(data);
					for(var i=0;i<data.data.length;i++){
						map.setView([data.data[i].log,data.data[i].lat],19);
						marker = L.marker([data.data[i].log,data.data[i].lat]);
						map.addLayer(marker);
//						marker.bindPopup('基本信息：'+data.data[i].frameNum+"<br/>"+
//								"区域："+data.data[i].area).openPopup();
						marker.bindPopup('中文描述:'+data.data[i].chinesedescription+"<br/>"+
								'报缺陷人:'+data.data[i].personliable+"<br/>"+
								'返修人:'+data.data[i].repairman+"<br/>"+
								'vin号:'+data.data[i].vin+"<br/>"+
								'设备号:'+data.data[i].frameNum+"<br/>"+
								'区域:'+data.data[i].area).openPopup();
					}
				}else if (data.data.lat==0 && data.data.log==0) {
//					alert("该数据没有位置信息,或输入有误");
					window.wxc.xcConfirm("该数据没有位置信息,或输入有误", window.wxc.xcConfirm.typeEnum.info);
				}
				else{
//					alert("请确认是否绑定定位器");
					window.wxc.xcConfirm("请确认是否绑定定位器", window.wxc.xcConfirm.typeEnum.info);
					
				}
			
			});
//			 }else{
		}
		
		$scope.getdamagedcar=function(){
			mapServ.getdamagedcardata($scope.A).then(function(data){
//				if (dada.dada!=null) {
//					alert("成功");
//				} else {
//                   alert("测试中");
//				}
				console.log(data);
				if(data.data!=null){
				marker= L.marker([data.data.log,data.data.lat]);
					map.setView([data.data.log,data.data.lat],19);
					marker = L.marker([data.data.log,data.data.lat]);
					map.addLayer(marker);
					marker.bindPopup('缺陷车辆信息：'+data.data.Theinputdata+"<br/>"+
							"区域："+data.data).openPopup();
				}else{
					alert("没有找到该类型的车辆");
				}
			});
		}
		
});