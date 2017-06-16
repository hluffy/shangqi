app.controller("roadedCtr",function($scope,mapServ,$rootScope){
	$rootScope.countId="100%";
	var centerPoint = [30.8853,121.8258];
	var map = L.map('roadedMap').setView(centerPoint, 16);
	
	var aerial = new L.tileLayer("/Tiles/{z}/{x}/{y}.png", {
		maxZoom: 21,
		minZoom: 15,
	});
	aerial.addTo(map);
	
	L.control.scale().addTo(map);//添加比例尺
	
//	var polyline = new L.polyline([  
//		[30.8863387, 121.8266335],
//		[30.8861684, 121.827178],
//		[30.885517, 121.8268749],
//		[30.8856643, 121.8270788]], {  
//		    color: 'blue',  
//		    opacity: 0.5,  
//		    weight: 3
//		}).addTo(map); 
	
	//基站测试
	
	$scope.map = {};
//	$scope.getRoadInfo = function(){
//		map.remove();
//		map = L.map('leafletMap').setView(centerPoint, 19);
//		var aerial = new L.tileLayer("http://localhost:8080/Tiles/{z}/{x}/{y}.png", {
//    		maxZoom: 21,
//    		minZoom: 16,
//		});
//		aerial.addTo(map);
//		
//	}
	
//	var marker6 = L.marker([30.8853881,121.8267542]);
//	map.addLayer(marker6);
//	marker6.bindPopup("006");
//	
//	var marker10 = L.marker([30.8853881,121.8268588]);
//	map.addLayer(marker10);
//	marker10.bindPopup("010");
//	
//	var marker79 = L.marker([30.8852983,121.8268535]);
//	map.addLayer(marker79);
//	marker79.bindPopup("079");
//	
//	var marker85 = L.marker([30.885296,121.8267462]);
//	map.addLayer(marker85);
//	marker85.bindPopup("085");
	
	$scope.getRoadedInfo = function(){
		$scope.map.startTime = $("#mapStartTime").val();
		$scope.map.endTime = $("#mapEndTime").val();
		if(!$scope.map.frameNum){
//			alert("车架号不允许为空")
			window.wxc.xcConfirm("车架号不允许为空", window.wxc.xcConfirm.typeEnum.info);
			return;
		}
		if(!$scope.map.startTime||!$scope.map.endTime){
//			alert("时间不允许为空");
			window.wxc.xcConfirm("时间不允许为空", window.wxc.xcConfirm.typeEnum.info);
			return;
		}
		map.remove();
		map = L.map('roadedMap').setView(centerPoint, 16);
		
		var aerial = new L.tileLayer("/Tiles/{z}/{x}/{y}.png", {
			maxZoom: 21,
			minZoom: 15,
		});
		aerial.addTo(map);
		mapServ.getinfoastime($scope.map).then(function(data){
			if(data.states){
				var result = data.data;
				var positions = [];
				for(var i=0;i<result.length;i++){
					if(result[i].log!=0&&result[i].lat!=0){
						positions.push([result[i].log,result[i].lat]);
						
						var marker = L.marker([result[i].log,result[i].lat]);
//						map.addLayer(marker);
						if(i==0){
							map.addLayer(marker);
							marker.bindPopup("起点");
						}else if(i==result.length-1){
							map.addLayer(marker);
							marker.bindPopup("终点").openPopup();
						}else{
							
						}
					}
					
				}
				
				if(positions.length!=0){
					map.setView([positions[positions.length-1][0],positions[positions.length-1][1]],19);
				}
				
				var polyline = new L.polyline(positions, {  
               		    color: 'blue',  
               		    opacity: 0.5,  
               		    weight: 3
               	}).addTo(map); 
			}
		});
	}
	
	
});