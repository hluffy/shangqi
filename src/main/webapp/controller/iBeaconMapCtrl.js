app.controller("iBeaconMapCtrl",function($scope,$timeout,mapServ,$rootScope){
	$rootScope.countId="100%";
	$scope.map = {};
//		var centerPoint = [31.1707739,121.406607];
	var centerPoint = [30.8853,121.8258];
	var map = L.map('ibeaconLeafletMap').setView(centerPoint, 16);
	
	var aerial = new L.tileLayer("/Tiles/{z}/{x}/{y}.png", {
		maxZoom: 21,
		minZoom: 15,
	});
	aerial.addTo(map);
	
	
	L.control.scale().addTo(map);//添加比例尺


	var marker = L.marker();
		var greenIcon = L.icon({
            iconUrl: 'img/ibeacon.png',
//            shadowUrl: 'img/caricon.jpg',

            iconSize:     [10, 10], // size of the icon
//            shadowSize:   [20, 20], // size of the shadow
            iconAnchor:   [5, 5], // point of the icon which will correspond to marker's location
//            shadowAnchor: [0, 0],  // the same for the shadow
            popupAnchor:  [0, -10] // point from which the popup should open relative to the iconAnchor
        });
//        L.marker([40, 112], {icon: greenIcon}).addTo(map);
	mapServ.getibeaconformap().then(function(data){
		var result = data.data;
		console.log(result.length);
		for(var i=0;i<result.length;i++){
			marker = L.marker([result[i].lat,result[i].log],{icon: greenIcon});
			map.addLayer(marker);
			marker.bindPopup("iBeacon编号:"+result[i].uuid);
//			marker = L.marker([result[i].lat,result[i].log]);
//			map.addLayer(marker);
		}
	});
	
});