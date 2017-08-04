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
		
//		function testChart(){
//			var peiChart = echarts.init(document.getElementById("lowEquipChart"));
//			var option = {
//				    color: ['#3398DB'],
//				    tooltip : {
//				        trigger: 'axis',
//				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
//				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
//				        }
//				    },
//				    grid: {
//				        left: '3%',
//				        right: '4%',
//				        bottom: '3%',
//				        containLabel: true
//				    },
//				    xAxis : [
//				        {
//				            type : 'category',
//				            data : ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
//				            axisTick: {
//				                alignWithLabel: true
//				            }
//				        }
//				    ],
//				    yAxis : [
//				        {
//				            type : 'value'
//				        }
//				    ],
//				    series : [
//				        {
//				            name:'直接访问',
//				            type:'bar',
//				            barWidth: '60%',
//				            data:[10, 52, 200, 334, 390, 330, 220]
//				        }
//				    ]
//				};
//			
//			peiChart.setOption(option);
//		}
//		
//		testChart();
		
		
		
		function initpiechart(){
			var peiChart = echarts.init(document.getElementById("lowEquipChart"));
			var peiOption = {
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        left: 'left',
				        data: $scope.legdata
				    },
				    series : [
				        {
				            name: '电量信息',
				            type: 'pie',
				            radius : '55%',
				            center: ['50%', '60%'],
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