//app.controller("userCtrl",function($scope,$http){
//	$scope.master = {};
//	$scope.isShow = false;
//	$http.get("../resources/user/users.json").success(function(data){
//		$scope.users = data.users;
//		console.log($scope.users);
//	});
//});

app.controller("userCtrl",function($rootScope,$scope,$http,userService){
	$rootScope.countId="645px";
//	$scope.roles = ["op","admin"];
	$scope.master = {};
	$scope.user = {};
	$scope.defIndex = -1;
	$scope.isShow = false;
	$rootScope.pages = new Array();
	$rootScope.indexPage = 1;
	
	function pageselectCallback(page_index, jq){
		$scope.user.page = page_index;
		userService.getUserInfo($scope.user).then(function(data){
			$scope.users = data.data;
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
    
	userService.getUserInfos().then(function(data){
		$rootScope.users = data.data;
		$rootScope.count = data.count;
		var optInit = getOptionsFromForm();
        $("#Pagination").pagination($rootScope.count, optInit);
		$("#setoptions").click(function(){
            var opt = getOptionsFromForm();
            $("#Pagination").pagination($rootScope.count, opt);
        }); 
	});
	
//	$http.get("http://localhost:8080/management/user/getinfos.ll").success(function(data){
//		$scope.users = data.data;
//	});
	
	$scope.addUser = function(valid){
		console.log(valid);
		if(valid){
			console.log($scope.user);
			if($scope.user.password!=$scope.user.confirmpassword){
//				alert("你两次输入的密码不同，请确认");
				window.wxc.xcConfirm("你两次输入的密码不同，请确认", window.wxc.xcConfirm.typeEnum.info);
				$scope.user.password="";
				$scope.user.confirmpassword="";
				return;
			}
			userService.saveUserInfo($scope.user).then(function(data){
//				alert(data.message);
				window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
				$scope.user = {};
			});
		}else{
			if($scope.user.userName==null){
//				alert("用户名不能为空");
				window.wxc.xcConfirm("用户名不能为空", window.wxc.xcConfirm.typeEnum.info);
				return;
			}
			if($scope.user.password==null){
//				alert("密码不能为空");
				window.wxc.xcConfirm("密码不能为空", window.wxc.xcConfirm.typeEnum.info);
				return;
			}
			if($scope.user.confirmpassword==null){
//				alert("确认密码不能为空");
				window.wxc.xcConfirm("确认密码不能为空", window.wxc.xcConfirm.typeEnum.info);
				return;
			}else{
//				alert("邮箱地址不合法");
				window.wxc.xcConfirm("邮箱地址不合法", window.wxc.xcConfirm.typeEnum.info);
				$scope.user.email="";
				return;
			}
		}
	}
	
//	$scope.getUserInfo = function(){
//		console.log(1111);
//		$scope.users = {};
//	}
	
	
	$scope.getUserInfo = function(){
		$scope.users = {};
		$rootScope.pages = new Array();
		$rootScope.indexPage = 1;
		$scope.user.page = 0;
		userService.getUserInfo($scope.user).then(function(data){
			$scope.users = data.data;
			$rootScope.count = data.count;
			var opt = getOptionsFromForm();
            $("#Pagination").pagination($rootScope.count, opt);
		})
	}
	
	$scope.getUserInfoOfPage = function(page){
		$rootScope.indexPage = page;
		$scope.user.page = page;
		userService.getUserInfo($scope.user).then(function(data){
			$scope.users = data.data;
		});
	}
	
	$scope.cancelUser = function($index){
		$scope.isShow = false;
		var id = "input" + $scope.users[$index].userName;
		var obj = $("."+id);
		obj.removeClass("active");
		obj.addClass("inactive");
		obj.attr("readonly",true);
		angular.copy($scope.master,$scope.users[$index]);
	}
	
	$scope.updateUser = function($index){
//		$event.target.show();
//		console.log($event.target);
		$scope.isShow = true;
		var id = "input" + $scope.users[$index].userName;
		var obj = $("."+id);
		obj.removeClass("inactive");
		obj.addClass("active");
		obj.removeAttr("readonly");
//		$("."+id+",.userselect").attr("disabled",false);
		obj.focus();
		$scope.master = angular.copy($scope.users[$index]);
		
	}
	
	$scope.saveUser = function($index){
		var id = "input" + $scope.users[$index].userName;
		var obj = $("."+id);
		obj.removeClass("active");
		obj.addClass("inactive");
		obj.attr("readonly",true);
		$scope.isShow = false;
		$scope.user = angular.copy($scope.users[$index]);
		userService.updateUserInfo($scope.users[$index]).then(function(data){
			console.log($scope.users[$index]);
			console.log(data);
		});
	}
	
	$scope.deleteUser = function($index){
		$scope.users.splice($index,1);
	}
});


app.directive("useredit",function($document){
	return{
		 restrict: "E",
		 require: "ngModel",
		 link:function(scope,element,attrs,ngModel){
			 element.bind("click",function(){
				 var id = "input" + ngModel.$modelValue.userName;
				 console.log(id);
				 var obj = $("."+id);
				 obj.removeClass("inactive");  
		         obj.addClass("active");
		         obj.removeAttr("readonly");
		         obj.focus();
		         console.log(111);
		         console.log(element);
		         var selectid = "select" + ngModel.$modelValue.userName;
		         $("."+selectid).removeAttr("disabled");
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

app.directive("usercancel",function($document){
	return{
		restrict:"E",
		require:"ngModel",
		link:function(scope,element,attrs,ngModel){
			element.bind("click",function(){
				scope.$apply(function(){
					angular.copy(scope.master,ngModel.$modelValue);
				});
				var id = "input" + ngModel.$modelValue.userName;
				console.log(id);
				var obj = $("."+id);
				obj.removeClass("active");
				obj.addClass("inactive");
				obj.attr("readonly",true);
				var selectid = "select" + ngModel.$modelValue.userName;
				$("."+selectid).attr("disabled","true");
				scope.$apply(function(){
					scope.isShow = false;
				});
			});
		}
	}
});

app.directive("userdelete",function($rootScope,$document,userService){
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
							var id = ngModel.$modelValue.userName;
//							console.log(id);
							scope.$apply(function(){
								for(var i = 0;i<scope.users.length;i++){
									if(scope.users[i].userName==id){
										userService.deleteUserInfo(ngModel.$modelValue).then(function(data){
											console.log(data);
//											alert(data.message);
											window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
											$rootScope.count = $rootScope.count-1;
											if(data.states){
												userService.getUserInfos().then(function(data){
													$rootScope.users = data.data;
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

app.directive("userupdate",function($rootScope,$document,userService){
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
							var id = "input" + ngModel.$modelValue.userName;
							console.log(id);
							var obj = $("."+id);
							obj.removeClass("active");
							obj.addClass("inactive");
							obj.attr("readonly",true);
							var selectid = "select" + ngModel.$modelValue.userName;
							$("."+selectid).attr("disabled","true");
							scope.$apply(function(){
								userService.updateUserInfo(ngModel.$modelValue).then(function(data){
									scope.isShow = false;
									console.log(data);
//									alert(data.message);
									window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
									if(data.states){
										userService.getUserInfos().then(function(data){
											$rootScope.users = data.data;
										});
									}
								});
							})
						},
						onCancel: function(){
							var id = "input" + ngModel.$modelValue.userName;
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