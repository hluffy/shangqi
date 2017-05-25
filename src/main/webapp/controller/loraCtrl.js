app.controller("loraCtrl",function($rootScope,$scope,loraService){
	$scope.lora = {};
	$rootScope.countId="620px";
	$rootScope.indexPage = 1;
	$rootScope.pages = new Array();
	
	function pageselectCallback(page_index, jq){
		$scope.lora.page = page_index;
        loraService.getInfo($scope.lora).then(function(data){
			$rootScope.loras = data.data;
			$rootScope.count = data.count;
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
	
	loraService.getInfos().then(function(data){
		$rootScope.loras = data.data;
		$rootScope.count = data.count;
	    var optInit = getOptionsFromForm();
	    $("#Pagination").pagination(data.count, optInit);
	        
	    $("#setoptions").click(function(){
	    	var opt = getOptionsFromForm();
	    	$("#Pagination").pagination(data.count, opt);
	    });
//		if($scope.count){
//			$scope.page = Math.ceil($scope.count/10);
//			$rootScope.lastPage = $scope.page;
//			for(var i=1;i<=$scope.page;i++){
//				$rootScope.pages[i-1] = i;
//			}
//		}
	});
	
	$scope.addLora = function(){
		if($scope.lora.number==null){
			alert("编号不能为空");
			return;
		}
		loraService.addInfo($scope.lora).then(function(data){
			console.log(data);
			alert(data.message);
			$scope.lora = {};
		});
	}
	
	$scope.getLoraInfo = function(lora){
		$rootScope.pages = new Array();
		$rootScope.indexPage = 1;
		$scope.lora.page = 0;
		loraService.getInfo($scope.lora).then(function(data){
			$rootScope.loras = data.data;
			$rootScope.count = data.count;
			var opt = getOptionsFromForm();
			$("#Pagination").pagination(data.count, opt);
//			if($scope.count){
//				$scope.page = Math.ceil($scope.count/10);
//				$rootScope.lastPage = $scope.page;
//				for(var i=1;i<=$scope.page;i++){
//					$rootScope.pages[i-1] = i;
//				}
//			}
		});
	}
	
	$scope.getLoraInfoOfPage = function(page){
		$scope.lora.page = page;
		$rootScope.indexPage = page;
		loraService.getInfo($scope.lora).then(function(data){
			$rootScope.loras = data.data;
		});
	}
	
});

app.directive("loraedit",function($document){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				scope.$apply(function(){
					scope.master = angular.copy(ngModel.$modelValue);
					scope.isShow = true;
				});
				var id = "input" + ngModel.$modelValue.number;
				console.log(id);
				var obj = $("."+id);
				obj.removeClass("inactive");
				obj.addClass("active");
				obj.attr("readonly",false);
			});
		}
	}
});

app.directive("loracancel",function($document){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				scope.$apply(function(){
					angular.copy(scope.master,ngModel.$modelValue);
				});
				var id = "input" + ngModel.$modelValue.number;
				console.log(id);
				var obj = $("."+id);
				obj.removeClass("active");
				obj.addClass("inactive");
				obj.attr("readonly",true);
				scope.$apply(function(){
					scope.isShow = false;
				});
			});
		}
	}
});

app.directive("loradelete",function($document,loraService){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				if(confirm("是否删除编号为"+ngModel.$modelValue.number+"的设备?")){
					var id = ngModel.$modelValue.number;
					console.log(id);
					scope.$apply(function(){
						for(var i = 0;i<scope.loras.length;i++){
							if(scope.loras[i].number==id){
								loraService.deleteInfo(ngModel.$modelValue).then(function(data){
									console.log(data);
									alert(data.message);
								});
								scope.loras.splice(i,1);
							}
						}
					});
				}
				
			});
		}
	}
});


app.directive("lorasync",function($document,loraService){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				if(confirm("是否重启编号为"+ngModel.$modelValue.number+"的设备?")){
					var id = ngModel.$modelValue.number;
					console.log(id);
					scope.$apply(function(){
						for(var i = 0;i<scope.loras.length;i++){
							if(scope.loras[i].number==id){
								loraService.loraSyncTime(ngModel.$modelValue).then(function(data){
									console.log(data);
									alert(data.message);
								});
							}
						}
					});
				}
				
			});
		}
	}
});
app.directive("lorarestart",function($document,loraService){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				if(confirm("是否重启编号为"+ngModel.$modelValue.number+"的设备?")){
					var id = ngModel.$modelValue.number;
					console.log(id);
					scope.$apply(function(){
						for(var i = 0;i<scope.loras.length;i++){
							if(scope.loras[i].number==id){
								loraService.loraRestart(ngModel.$modelValue).then(function(data){
									console.log(data);
									alert(data.message);
								});
							}
						}
					});
				}
				
			});
		}
	}
});

app.directive("loraupdate",function($rootScope,$document,loraService){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				if(confirm("你确定要保存吗?")){
					var id = "input" + ngModel.$modelValue.number;
					console.log(id);
					var obj = $("."+id);
					obj.removeClass("active");
					obj.addClass("inactive");
					obj.attr("readonly",true);
					scope.$apply(function(){
						loraService.updateInfo(ngModel.$modelValue).then(function(data){
							scope.isShow = false;
							alert(data.message);
							loraService.getInfos().then(function(data){
								$rootScope.loras = data.data;
							});
						});
					});
				}else{
					var id = "input" + ngModel.$modelValue.number;
					console.log(id);
					var obj = $("."+id);
					obj.removeClass("active");
					obj.addClass("inactive");
					obj.attr("readonly",true);
					scope.$apply(function(){
						scope.isShow = false;
					})
				}
				
			});
		}
	}
});