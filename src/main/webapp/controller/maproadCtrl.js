app.controller("maproadCtr",function($scope,mapServ){
	var centerPoint = [30.8853,121.8258];
	var map = L.map('leafletMap').setView(centerPoint, 16);
	
	var aerial = new L.tileLayer("http://localhost:8080/Tiles/{z}/{x}/{y}.png", {
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
	
	$scope.map = {};
	$scope.getRoadInfo = function(){
		map.remove();
		map = L.map('leafletMap').setView(centerPoint, 19);
		var aerial = new L.tileLayer("http://localhost:8080/Tiles/{z}/{x}/{y}.png", {
    		maxZoom: 21,
    		minZoom: 16,
//     		maxNativeZoom: 19
		});
		aerial.addTo(map);
		mapServ.getPositionInfoAsFrameNum($scope.map).then(function(data){
			if(data.data!=null){
				L.Routing.control({
					router: L.Routing.graphHopper(undefined /* no api key */, {
				        serviceUrl: 'http://www.1000van.com:8989/route'}),
					waypoints:[
						L.latLng(30.8863413,121.8266332),
//						L.latLng(30.8861684,121.827178),
						L.latLng(data.data.log,data.data.lat)
					           ]
				}).addTo(map);
				var marker = L.marker([data.data.log,data.data.lat]);
				map.addLayer(marker);
				marker.bindPopup("终点").openPopup();
				marker = L.marker([30.8863413,121.8266332]);
				map.addLayer(marker);
				marker.bindPopup("起点");
				map.setView([data.data.log,data.data.lat],18);
			}else{
				alert("该车架号没有位置信息,请确认 是否绑定定位器");
			}
			
		});
		
	}
	
});