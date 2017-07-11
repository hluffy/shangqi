app.controller("areaCtrl",function($rootScope,$scope,localService){
	$scope.area={};
	$rootScope.areainfo = false;
	$rootScope.countId = "100%";
	$rootScope.areas={};
	$rootScope.indexPage = 1;
	localService.getAreaDatas().then(function(data){
		$rootScope.areas=data.data;
		$scope.sumareadata=data.count;
	});
	
	$scope.hiddeTable = function(){
		$rootScope.areainfo = false;
	}
	
//	localService.getInfo($scope.area).then(function(data){
//		console.log(data);
//		$rootScope.areainfos = data.data;
//	});
});

app.directive("areainfo",function($rootScope,$document,localService){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel,index){
			element.bind("click",function(){
				$rootScope.areainfo = true;
				$rootScope.countId="100%";
				scope.$apply(function(){
					
					function pageselectCallback(page_index, jq){
						ngModel.$modelValue.page = page_index;
//						$scope.user.page = page_index;
						localService.getInfo(ngModel.$modelValue).then(function(data){
							$rootScope.areainfos = data.data;
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
					
//				    $(document).ready(function(){
//				        var optInit = getOptionsFromForm();
//				        $("#Pagination").pagination(members.length, optInit);
//				        
//						$("#setoptions").click(function(){
//				            var opt = getOptionsFromForm();
//				            $("#Pagination").pagination(members.length, opt);
//				        }); 
//				
//				    });
				    
					localService.getInfo(ngModel.$modelValue).then(function(data){
						$rootScope.areainfos = data.data;
						$rootScope.count = data.count;
						var optInit = getOptionsFromForm();
				        $("#Pagination").pagination($rootScope.count, optInit);
				        
						$("#setoptions").click(function(){
				            var opt = getOptionsFromForm();
				            $("#Pagination").pagination($rootScope.count, opt);
				        }); 
					});
				})
			});
		}
	}
});