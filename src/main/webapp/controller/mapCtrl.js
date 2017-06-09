app.controller("mapCtrl",function($scope,$timeout,mapServ,$rootScope){
	$rootScope.countId="100%";
	$scope.map = {};
// 		function initmap(){
//		var centerPoint = [31.1707739,121.406607];
		var centerPoint = [30.8853,121.8258];
		var map = L.map('leafletMap').setView(centerPoint, 16);
		
		var aerial = new L.tileLayer("/Tiles/{z}/{x}/{y}.png", {
    		maxZoom: 21,
    		minZoom: 15,
		});
		aerial.addTo(map);
		
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
// 		}
	
	
// 		$timeout(function(){
// 			initmap();
// 		}, 100);
		var marker = L.marker();
//		var greenIcon = L.icon({
//            iconUrl: 'img/caricon.jpg',
//            shadowUrl: 'img/caricon.jpg',

//            iconSize:     [30, 30], // size of the icon
//            shadowSize:   [20, 20], // size of the shadow
//            iconAnchor:   [0, 0], // point of the icon which will correspond to marker's location
//            shadowAnchor: [0, 0],  // the same for the shadow
//            popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
//        });
//        L.marker([40, 112], {icon: greenIcon}).addTo(map);
		$scope.getPositionInfo = function(){
			
			map.removeLayer(marker);
			mapServ.getPositionInfoAsFrameNum($scope.map).then(function(data){
				console.log(data);
				if(data.data!=null){
//					marker = L.marker([data.data.log,data.data.lat],{icon: greenIcon});
					map.setView([data.data.log,data.data.lat],19);
					marker = L.marker([data.data.log,data.data.lat]);
					map.addLayer(marker);
					marker.bindPopup('车架号：'+data.data.frameNum+"<br/>"+
							"区域："+data.data.area).openPopup();
				}else{
					alert("该车架号没有位置信息,请确认是否绑定定位器");
				}
				
			});
		}
});