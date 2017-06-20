app.controller("employCtrl",function($scope,$rootScope,employService){
	$scope.employ = {};
	$rootScope.indexPage = 1;
	employService.getEmployInfos().then(function(data){
		$rootScope.employs = data.data;
	});
	
	
	$scope.addEmploy = function(){
		employService.saveEmployInfo($scope.employ).then(function(data){
			window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
			$scope.employ = {};
		});
	}
	
	$scope.getEmployInfo = function(){
		console.log(1212);
		employService.getEmployInfo($scope.employ).then(function(data){
			$rootScope.employs = data.data;
		});
	}
	
	$scope.leadin = function(){
		employService.leadIn($scope.employ).then(function(data){
			console.log(data);
		});
	}
});

app.directive("employedit",function($document){
	return{
		 restrict: "E",
		 require: "ngModel",
		 link:function(scope,element,attrs,ngModel){
			 element.bind("click",function(){
				 var id = "input" + ngModel.$modelValue.employeeId;
				 console.log(id);
				 var obj = $("."+id);
				 obj.removeClass("inactive");  
		         obj.addClass("active");
		         obj.removeAttr("readonly");
		         obj.focus();
		         console.log(111);
		         console.log(element);
//		         obj.next().removeClass("inactive");
//		         obj.next().addClass("active");
//		         obj.next().removeAttr("readonly");
		         scope.$apply(function(){
//		        	 angular.copy(scope.master,ngModel.$modelValue);
		        	 scope.master = angular.copy(ngModel.$modelValue);
		        	 scope.isShow = true;
		         });
			 });
		 }
	}
});

app.directive("employcancel",function($document){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				scope.$apply(function(){
					angular.copy(scope.master,ngModel.$modelValue);
				});
				var id = "input" + ngModel.$modelValue.employeeId;
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

app.directive("employdelete",function($rootScope,$document,employService){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				var option = {
						title: "确认信息",
						btn: parseInt("0011",2),
						onOk: function(){
//							console.log("确认啦");
							var id = ngModel.$modelValue.employeeId;
							console.log(id);
							scope.$apply(function(){
								for(var i = 0;i<scope.employs.length;i++){
									if(scope.employs[i].employeeId==id){
										employService.deleteEmployInfo(ngModel.$modelValue).then(function(data){
											console.log(data);
//											alert(data.message);
											window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
											$rootScope.count = $rootScope.count-1;
											if(data.states){
												employService.getEmployInfos().then(function(data){
													$rootScope.employs = data.data;
												});
											}
										});
//										scope.users.splice(i,1);
										
									}
								}
							});
						}
					}
				window.wxc.xcConfirm("您确定要删除吗？", "custom", option);
//				if(confirm("你确定要删除吗?")){
//					var id = ngModel.$modelValue.userName;
////					console.log(id);
//					scope.$apply(function(){
//						for(var i = 0;i<scope.users.length;i++){
//							if(scope.users[i].userName==id){
//								userService.deleteUserInfo(ngModel.$modelValue).then(function(data){
//									console.log(data);
////									alert(data.message);
//									window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
//									$rootScope.count = $rootScope.count-1;
//									if(data.states){
//										userService.getUserInfos().then(function(data){
//											$rootScope.users = data.data;
//										});
//									}
//								});
////								scope.users.splice(i,1);
//								
//							}
//						}
//					});
//				}
			});
		}
	}
});

app.directive("employupdate",function($rootScope,$document,employService){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel,index){
			element.bind("click",function(){
				var option = {
						title: "确认信息",
						btn: parseInt("0011",2),
						onOk: function(){
//							console.log("确认啦");
							var id = "input" + ngModel.$modelValue.employeeId;
							console.log(id);
							var obj = $("."+id);
							obj.removeClass("active");
							obj.addClass("inactive");
							obj.attr("readonly",true);
							scope.$apply(function(){
								employService.updateEmployInfo(ngModel.$modelValue).then(function(data){
									scope.isShow = false;
									console.log(data);
//									alert(data.message);
									window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
									if(data.states){
										employService.getUserInfos().then(function(data){
											$rootScope.users = data.data;
										});
									}
								});
							})
						},
						onCancel: function(){
							var id = "input" + ngModel.$modelValue.employeeId;
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
				window.wxc.xcConfirm("您确定要保存吗?", "custom", option);
//				if(confirm("你确定要保存吗?")){
//					var id = "input" + ngModel.$modelValue.userName;
//					console.log(id);
//					var obj = $("."+id);
//					obj.removeClass("active");
//					obj.addClass("inactive");
//					obj.attr("readonly",true);
//					var selectid = "select" + ngModel.$modelValue.userName;
//					$("."+selectid).attr("disabled","true");
//					scope.$apply(function(){
//						userService.updateUserInfo(ngModel.$modelValue).then(function(data){
//							scope.isShow = false;
//							console.log(data);
////							alert(data.message);
//							window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
//							if(data.states){
//								userService.getUserInfos().then(function(data){
//									$rootScope.users = data.data;
//								});
//							}
//						});
//					})
//				}
			});
		}
	}
});