app.controller("lowEquipPieCtrl",function($scope,$rootScope,localService){
	localService.getLowInfo().then(function(result){
		$scope.datas = result.data;
		$scope.data = new Array();
		$scope.legdata = new Array();
		for(var i=0;i<$scope.datas.length;i++){
			$scope.data[i] = {"value":$scope.datas[i].value,"name":$scope.datas[i].name};
			$scope.legdata[i] = $scope.datas[i].name;
		}
		console.log("data");
		console.log($scope.data);
		console.log($scope.legdata);
		
		function initpiechart(){
			var peiChart = echarts.init(document.getElementById("lowEquipChart"));
			var peiOption = {
//				    title : {
//				        text: '电量信息',
//				        subtext: '设备电量状态占比',
//				        x:'center'
//				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        left: 'left',
//				        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
				        data: $scope.legdata
				    },
				    series : [
				        {
				            name: '电量信息',
				            type: 'pie',
				            radius : '55%',
				            center: ['50%', '60%'],
//				            data:[
//				                {value:335, name:'直接访问'},
//				                {value:310, name:'邮件营销'},
//				                {value:234, name:'联盟广告'},
//				                {value:135, name:'视频广告'},
//				                {value:1548, name:'搜索引擎'}
//				            ],
				            data:$scope.data,
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
				};
			
			peiChart.setOption(peiOption);
		}
		
		initpiechart();
		
	});
});