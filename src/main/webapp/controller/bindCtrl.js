app.controller("bindCtrl",function($rootScope,$scope,bindService){
	$rootScope.countId = "620px";
	$rootScope.indexPage=1;
	$rootScope.pages=new Array();
	$scope.bind = {};
	
	function pageselectCallback(page_index, jq){
		$scope.bind.page = page_index;
		bindService.getBindInfo($scope.bind).then(function(data){
			$scope.binds = data.data;
			$rootScope.count = data.count;
			$rootScope.indexPage = page_index+1;
		})
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
	
	bindService.getBindInfos().then(function(data){
		$rootScope.binds = data.data;
		$rootScope.count = data.count;
		var optInit = getOptionsFromForm();
        $("#Pagination").pagination($rootScope.count, optInit);
        
		$("#setoptions").click(function(){
            var opt = getOptionsFromForm();
            $("#Pagination").pagination($rootScope.count, opt);
        }); 
	});
	
	$scope.addBindInfo = function(){
		$scope.bind.bindType="绑定";
		bindService.addBindInfo($scope.bind).then(function(data){
			alert(data.message);
			$scope.bind = {};
		});
	}
	
	$scope.getBindInfo = function(){
		$rootScope.pages = new Array();
		$scope.bind.page=0;
		$scope.bind.bindStartTime = $("#bindStartTime").val();
		$scope.bind.bindEndTime = $("#bindEndTime").val();
		$scope.bind.unbindStartTime = $("#unbindStartTime").val();
		$scope.bind.unbindEndTime = $("#unbindEndTime").val();
		console.log($scope.bind);
		$rootScope.indexPage=1;
		bindService.getBindInfo($scope.bind).then(function(data){
			$scope.binds = data.data;
			$rootScope.count = data.count;
			var opt = getOptionsFromForm();
            $("#Pagination").pagination($rootScope.count, opt);
		})
	}
	
	$scope.getBindInfoOfPage = function(page){
		$rootScope.indexPage = page;
		$scope.bind.page = page;
		bindService.getBindInfo($scope.bind).then(function(data){
			$scope.binds = data.data;
			$rootScope.count = data.count;
			if($scope.count){
				$scope.page = Math.ceil($scope.count/10);
				$rootScope.lastPage = $scope.page;
				for(var i=1;i<=$scope.page;i++){
					$rootScope.pages[i-1] = i;
				}
			}
		});
	}
	
	$scope.unBindInfo = function(){
		if(!$scope.bind||!$scope.bind.equipmentNum){
			alert("设备编号不允许为空");
			return;
		}
		if(!$scope.bind||!$scope.bind.frameNum){
			alert("车架号不允许为空");
			return;
		}
		bindService.updateBindInfo($scope.bind).then(function(data){
			alert(data.message);
			$scope.bind = {};
		});
	}
});

//app.directive("bindedit",function($rootScope,$document,bindService){
//	return{
//		restrict:"E",
//		require:"ngModel",
//		link:function(scope,element,attrs,ngModel,index){
//			element.bind("click",function(){
//				if(confirm("确定要解绑吗?")){
//					scope.$apply(function(){
//						bindService.updateBindInfo(ngModel.$modelValue).then(function(data){
//							console.log(data);
//							alert(data.message);
//							bindService.getBindInfos().then(function(data){
//								$rootScope.binds = data.data;
//							});
//						});
//					})
//				}
//				
//			});
//		}
//	}
//});