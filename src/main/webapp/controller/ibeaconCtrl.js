//app.controller("ibeaconCtrl",function($scope,$http){
//	$scope.isShow = false;
//	$scope.master = {};
//	$http.get("../resources/location/IBeacon.json").success(function(data){
//		$scope.ibeacons = data.ibeacons;
//	});
//});

app.controller("ibeaconCtrl",function($rootScope,$scope,ibeaconSer){
	$rootScope.countId="645px";
	$scope.isShow = false;
	$scope.master = {};
	$scope.ibeacon = {};
	$rootScope.pages = new Array();
	$rootScope.indexPage = 1;
	
	function pageselectCallback(page_index, jq){
		$scope.ibeacon.page = page_index;
		ibeaconSer.getIbeaconInfo($scope.ibeacon).then(function(data){
			$rootScope.count = data.count;
			$scope.ibeacons = data.data;
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
	
	ibeaconSer.getIbeaconInfos().then(function(data){
		console.log(data);
		$rootScope.ibeacons = data.data;
		$rootScope.count = data.count;
		
		var optInit = getOptionsFromForm();
        $("#Pagination").pagination($rootScope.count, optInit);
        
		$("#setoptions").click(function(){
            var opt = getOptionsFromForm();
            $("#Pagination").pagination($rootScope.count, opt);
        }); 
	});
	
	ibeaconSer.getArea().then(function(data){
		$scope.areanames = data.area;
		console.log(data.area);
	});
	
	
	$scope.addIbeacon = function(ibeacon){
		if($scope.ibeacon.uuid==null){
//			alert("iBeacon编号不允许为空");
			window.wxc.xcConfirm("iBeacon编号不允许为空", window.wxc.xcConfirm.typeEnum.info);
			return;
		}
		if($scope.ibeacon.log==null){
//			alert("经度不能为空");
			window.wxc.xcConfirm("经度不能为空", window.wxc.xcConfirm.typeEnum.info);
			return;
		}
		if($scope.ibeacon.lat==null){
//			alert("纬度不能为空");
			window.wxc.xcConfirm("纬度不能为空", window.wxc.xcConfirm.typeEnum.info);
			return;
		}
		if($scope.ibeacon.area==null||$scope.ibeacon.area==""){
//			alert("请选择区域");
			window.wxc.xcConfirm("请选择区域", window.wxc.xcConfirm.typeEnum.info);
			return;
		}
		ibeaconSer.addIbeaconInfo($scope.ibeacon).then(function(data){
			console.log(data);
//			alert(data.message);
			window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
			$scope.ibeacon = {};
		});
	}
	
	$scope.getIbeaconInfo = function(ibeacon){
		$rootScope.pages = new Array();
		$scope.ibeacon.page = 0;
		$scope.ibeacon.startTimeStr = $("#startTimeStr").val();
		$scope.ibeacon.endTimeStr = $("#endTimeStr").val();
		$rootScope.indexPage = 1;
		console.log($scope.ibeacon);
		ibeaconSer.getIbeaconInfo($scope.ibeacon).then(function(data){
			$rootScope.count = data.count;
			console.log(data);
			$scope.ibeacons = data.data;
			var opt = getOptionsFromForm();
            $("#Pagination").pagination($rootScope.count, opt);
		});
	}
	
	$scope.getIbeaconInfoOfPage = function(page){
		$rootScope.indexPage = page;
		$scope.ibeacon.page = page;
		ibeaconSer.getIbeaconInfo($scope.ibeacon).then(function(data){
			$scope.ibeacons = data.data;
		});
	}
});

app.directive("ibeedit",function($document){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				scope.$apply(function(){
					scope.master = angular.copy(ngModel.$modelValue);
					scope.isShow = true;
				});
				var id = "input" + ngModel.$modelValue.uuid;
				console.log(id);
				var obj = $("."+id);
				obj.removeClass("inactive");
				obj.addClass("active");
				obj.attr("readonly",false);
				var selectid = "select" + ngModel.$modelValue.uuid;
		        $("."+selectid).removeAttr("disabled");
			});
		}
	}
});

app.directive("ibecancel",function($document){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				scope.$apply(function(){
					angular.copy(scope.master,ngModel.$modelValue);
				});
				var id = "input" + ngModel.$modelValue.uuid;
				console.log(id);
				var obj = $("."+id);
				obj.removeClass("active");
				obj.addClass("inactive");
				obj.attr("readonly",true);
				var selectid = "select" + ngModel.$modelValue.uuid;
				$("."+selectid).attr("disabled","true");
				scope.$apply(function(){
					scope.isShow = false;
				});
			});
		}
	}
});

app.directive("ibedelete",function($document,ibeaconSer,$rootScope){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				var option = {
						title: "确认信息",
						btn: parseInt("0011",2),
						onOk: function(){
							var id = ngModel.$modelValue.uuid;
							console.log(id);
							scope.$apply(function(){
								for(var i = 0;i<scope.ibeacons.length;i++){
									if(scope.ibeacons[i].uuid==id){
										ibeaconSer.deleteIbeaconInfo(ngModel.$modelValue).then(function(data){
											console.log(data);
//											alert(data.message);
											window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
											ibeaconSer.getIbeaconInfos().then(function(data){
												$rootScope.ibeacons = data.data;
												$rootScope.count = data.count;
											});
										});
										scope.ibeacons.splice(i,1);
									}
								}
							});
						},
						onCancel: function(){
							console.log("取消");
						}
				}
				window.wxc.xcConfirm("是否删除iBeacon编号为"+ngModel.$modelValue.uuid+"的设备?", "custom", option);
//				if(confirm("是否删除iBeacon编号为"+ngModel.$modelValue.uuid+"的设备?")){
//					var id = ngModel.$modelValue.uuid;
//					console.log(id);
//					scope.$apply(function(){
//						for(var i = 0;i<scope.ibeacons.length;i++){
//							if(scope.ibeacons[i].uuid==id){
//								ibeaconSer.deleteIbeaconInfo(ngModel.$modelValue).then(function(data){
//									console.log(data);
////									alert(data.message);
//									window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
//									ibeaconSer.getIbeaconInfos().then(function(data){
//										$rootScope.ibeacons = data.data;
//										$rootScope.count = data.count;
//									});
//								});
//								scope.ibeacons.splice(i,1);
//							}
//						}
//					});
//				}
				
			});
		}
	}
});

app.directive("ibeupdate",function($rootScope,$document,ibeaconSer){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				var option = {
						title: "确认信息",
						btn: parseInt("0011",2),
						onOk: function(){
							var id = "input" + ngModel.$modelValue.uuid;
							console.log(id);
							var obj = $("."+id);
							obj.removeClass("active");
							obj.addClass("inactive");
							obj.attr("readonly",true);
							var selectid = "select" + ngModel.$modelValue.uuid;
							$("."+selectid).attr("disabled","true");
							scope.$apply(function(){
								ibeaconSer.updateIbeaconInfo(ngModel.$modelValue).then(function(data){
									scope.isShow = false;
									console.log(data);
//									alert(data.message);
									window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
									ibeaconSer.getIbeaconInfos().then(function(data){
										$rootScope.ibeacons = data.data;
									});
								});
							});
						},
						onCancel: function(){
							var id = "input" + ngModel.$modelValue.uuid;
							console.log(id);
							var obj = $("."+id);
							obj.removeClass("active");
							obj.addClass("inactive");
							obj.attr("readonly",true);
							scope.$apply(function(){
								scope.isShow = false;
							})
						}
				}
				window.wxc.xcConfirm("是否保存？", "custom", option);
//				if(confirm("是否保存?")){
//					var id = "input" + ngModel.$modelValue.uuid;
//					console.log(id);
//					var obj = $("."+id);
//					obj.removeClass("active");
//					obj.addClass("inactive");
//					obj.attr("readonly",true);
//					var selectid = "select" + ngModel.$modelValue.uuid;
//					$("."+selectid).attr("disabled","true");
//					scope.$apply(function(){
//						ibeaconSer.updateIbeaconInfo(ngModel.$modelValue).then(function(data){
//							scope.isShow = false;
//							console.log(data);
////							alert(data.message);
//							window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
//							ibeaconSer.getIbeaconInfos().then(function(data){
//								$rootScope.ibeacons = data.data;
//							});
//						});
//					});
//				}else{
//					var id = "input" + ngModel.$modelValue.uuid;
//					console.log(id);
//					var obj = $("."+id);
//					obj.removeClass("active");
//					obj.addClass("inactive");
//					obj.attr("readonly",true);
//					scope.$apply(function(){
//						scope.isShow = false;
//					})
//				}
				
				
			});
		}
	}
});