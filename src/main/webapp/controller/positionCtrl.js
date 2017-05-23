app.controller("positionCtrl",function($rootScope,$scope,positionSer,$log,$timeout){
	$rootScope.countId="600px";
//	$rootScope.countId = "100%";
	$scope.position = {};
	$rootScope.indexPage = 1;
	
	function pageselectCallback(page_index, jq){
        $scope.position.page = page_index;
        positionSer.getPositionInfo($scope.position).then(function(data){
			console.log(data);
			$rootScope.positions = data.data;
			$rootScope.indexPage = page_index+1;
		});
        return false;
    }
    
    function getOptionsFromForm(){
        var opt = {callback: pageselectCallback};
        opt.prev_text = "上一页";
        opt.next_text = "下一页";
        opt.items_per_page=10;
        opt.num_display_entries=4;
        opt.num_edge_entries=2;
        return opt;
    }
	
//    $(document).ready(function(){
//        var optInit = getOptionsFromForm();
//        $("#Pagination").pagination(members.length, optInit);
//        
//		$("#setoptions").click(function(){
//            var opt = getOptionsFromForm();
//            $("#Pagination").pagination(members.length, opt);
//        }); 
//
//    });
	
	
	positionSer.getPositionInfos().then(function(data){
		console.log(data.data.length);
		$rootScope.positions = data.data;
        var optInit = getOptionsFromForm();
        $("#Pagination").pagination(data.count, optInit);
        
		$("#setoptions").click(function(){
            var opt = getOptionsFromForm();
            $("#Pagination").pagination(data.count, opt);
        }); 

	});
	
	$scope.getPositionInfo = function(){
		$rootScope.indexPage = 1;
		$scope.position.page=0;
		positionSer.getPositionInfo($scope.position).then(function(data){
			console.log(data);
			$rootScope.positions = data.data;
			$rootScope.count = data.count;
			var opt = getOptionsFromForm();
            $("#Pagination").pagination($rootScope.count, opt);
		});
	}
	
	$scope.addPositionInfo = function(){
		positionSer.addInfo($scope.position).then(function(data){
			console.log(data);
		});
	}
	
	
});