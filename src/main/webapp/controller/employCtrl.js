app.controller("employCtrl",function($scope,$rootScope,employService){
	$rootScope.countId="625px";
	$scope.employ = {};
	$rootScope.indexPage = 1;
	
	function pageselectCallback(page_index, jq){
		$scope.employ.page = page_index;
		employService.getEmployInfo($scope.employ).then(function(data){
			$scope.employs = data.data;
//			$rootScope.count = data.count;
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
	
	employService.getEmployInfos().then(function(data){
        var optInit = getOptionsFromForm();
        $("#Pagination").pagination(data.count, optInit);
        
		$("#setoptions").click(function(){
            var opt = getOptionsFromForm();
            $("#Pagination").pagination(data.count, opt);
        }); 

		$rootScope.employs = data.data;
		$rootScope.count = data.count;
	});
	
	
	$scope.addEmploy = function(){
		employService.saveEmployInfo($scope.employ).then(function(data){
			window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
			$scope.employ = {};
		});
	}
	
	$scope.getEmployInfo = function(){
		employService.getEmployInfo($scope.employ).then(function(data){
			$rootScope.employs = data.data;
			$rootScope.count = data.count;
			var opt = getOptionsFromForm();
            $("#Pagination").pagination(data.count, opt);
		});
	}
	
	$scope.leadIn = function(){
		file = $scope.fileToUpload;
		if(file==null||file==""){
			window.wxc.xcConfirm("文件不能为空");
			return;
		}
		var id = $("#myleadin");
		id.attr("disabled","disabled");
		var text = "<i class='fa fa-spinner fa-2x fa-spin'></i>&nbsp;&nbsp;正在导入，请稍等！";
		window.wxc.xcConfirm(text);
		employService.uploadFileToUrl(file).then(function(data){
			id.removeAttr("disabled");
			$(".ok").click();
			window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info);
			$("#close").click();
			employService.getEmployInfos().then(function(data){
				$rootScope.employs = data.data;
			});
		});
	}
	
	$scope.submit = function(){
		console.log(3322);
		console.log($scope.file);
		employService.leadIn($scope.file).then(function(data){
			console.log(data);
		});
	}
//	$scope.leadin = function(){
//		console.log(322323);
//		console.log($scope.file);
//		Upload.upload({
//            //服务端接收
//            url: '/shangqi/file/uploadfile.ll',
//            //上传的同时带的参数
////            data: {'username': $scope.username},
//            //上传的文件
//            file: $scope.file
//        }).progress(function (evt) {
//            //进度条
////            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
////            console.log('progess:' + progressPercentage + '%' + evt.config.file.name);
//        }).success(function (data, status, headers, config) {
//            //上传成功
////            console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
////            $scope.uploadImg = data;
//        }).error(function (data, status, headers, config) {
//            //上传失败
////            console.log('error status: ' + status);
//        });
//	}
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
													$rootScope.count = data.count;
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

app.directive( "fileModel",function( $parse ){
	  return {
	    restrict: "A",
	    link: function( scope, element, attrs ){
	      var model = $parse( attrs.fileModel );
	      var modelSetter = model.assign;

	      element.bind( "change", function(){
	        scope.$apply( function(){
	          modelSetter( scope, element[0].files[0] );
	          console.log( scope );
	        } )
	      } )
	    }
	  }
});