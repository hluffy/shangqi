app.controller("localCtrl",function($rootScope,$scope,localService){
	$rootScope.countId="620px";
	$rootScope.indexPage = 1;
	$rootScope.pages = new Array();
	$scope.local = {};
	
	function pageselectCallback(page_index, jq){
        $scope.local.page = page_index;
        localService.getInfo($scope.local).then(function(data){
			$rootScope.locals = data.data;
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
	localService.getInfos().then(function(data){
		$rootScope.locals = data.data;
		$rootScope.count = data.count;
		$scope.local.staticTime="";
		$scope.local.runTime="";
		var optInit = getOptionsFromForm();
        $("#Pagination").pagination($rootScope.count, optInit);
        
		$("#setoptions").click(function(){
            var opt = getOptionsFromForm();
            $("#Pagination").pagination($rootScope.count, opt);
        }); 
	});
	
	$scope.addLocal = function(){
		if($scope.local.numberDef==null){
			alert("定位器编号不允许为空");
			return;
		}
		if($scope.local.numberDef.length!=12){
			alert("定位器编号格式不正确，请核对");
			return;
		}
		localService.addInfo($scope.local).then(function(data){
			alert(data.message);
			$scope.local = {};
		});
	}
	
	$scope.getLocalInfo = function(local){
		$rootScope.pages = new Array();
		$rootScope.indexPage = 1;
		$scope.local.page = 0;
		localService.getInfo($scope.local).then(function(data){
			$rootScope.locals = data.data;
			$rootScope.count = data.count;
			var opt = getOptionsFromForm();
            $("#Pagination").pagination($rootScope.count, opt);
		});
	}
	
	$scope.getLocalInfoOfPage = function(page){
		$scope.local.page = page;
		$rootScope.indexPage = page;
		localService.getInfo($scope.local).then(function(data){
			$rootScope.locals = data.data;
		});
	}
	
});

function getOptionsFromForm(){
//    var opt = {callback: pageselectCallback};
	var opt = {};
    opt.prev_text = "上一页";
    opt.next_text = "下一页";
    opt.items_per_page=10;
    opt.num_display_entries=4;
    opt.num_edge_entries=2;
    return opt;
}


app.directive("localedit",function($document){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				scope.$apply(function(){
					scope.master = angular.copy(ngModel.$modelValue);
					scope.isShow = true;
				});
				var id = "input" + ngModel.$modelValue.numberDef;
				console.log(id);
				var obj = $("."+id);
				obj.removeClass("inactive");
				obj.addClass("active");
				obj.attr("readonly",false);
			});
		}
	}
});

app.directive("localcancel",function($document){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				scope.$apply(function(){
					angular.copy(scope.master,ngModel.$modelValue);
				});
				var id = "input" + ngModel.$modelValue.numberDef;
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

app.directive("localdelete",function($document,localService,$rootScope){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				if(confirm("是否删除编号为"+ngModel.$modelValue.number+"的设备?")){
					var id = ngModel.$modelValue.number;
					console.log(id);
					scope.$apply(function(){
						for(var i = 0;i<scope.locals.length;i++){
							if(scope.locals[i].number==id){
								localService.deleteInfo(ngModel.$modelValue).then(function(data){
									console.log(data);
									alert(data.message);
									localService.getInfos().then(function(data){
										$rootScope.locals = data.data;
										$rootScope.count = data.count;
										var optInit = getOptionsFromForm();
										$("#Pagination").pagination($rootScope.count, optInit);
								        
									});
								});
//								scope.locals.splice(i,1);
							}
						}
					});
				}
				
			});
		}
	}
});

app.directive("localload",function($document,localService){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				if(confirm("是否同步参数编号为"+ngModel.$modelValue.number+"的设备?")){
					var id = ngModel.$modelValue.number;
					console.log(id);
					console.log(ngModel.$modelValue);
					scope.$apply(function(){
						for(var i = 0;i<scope.locals.length;i++){
							if(scope.locals[i].number==id){
								localService.loadParame(ngModel.$modelValue).then(function(data){
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

app.directive("localrestart",function($document,localService){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				if(confirm("是否重启编号为"+ngModel.$modelValue.number+"的设备?")){
					var id = ngModel.$modelValue.number;
					console.log(id);
					console.log(ngModel.$modelValue);
					scope.$apply(function(){
						for(var i = 0;i<scope.locals.length;i++){
							if(scope.locals[i].number==id){
								localService.restartLoraMac(ngModel.$modelValue).then(function(data){
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

app.directive("localupdate",function($rootScope,$document,localService){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				if(confirm("是否保存?")){
					var id = "input" + ngModel.$modelValue.numberDef;
					console.log(id);
					var obj = $("."+id);
					obj.removeClass("active");
					obj.addClass("inactive");
					obj.attr("readonly",true);
					scope.$apply(function(){
						localService.setEquipPara(ngModel.$modelValue).then(function(data){
							scope.isShow = false;
							console.log(data);
							alert(data.message);
							localService.getInfos().then(function(data){
								$rootScope.locals = data.data;
							});
						});
					});
				}else{
					var id = "input" + ngModel.$modelValue.numberDef;
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