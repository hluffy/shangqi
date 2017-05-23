app.controller("asideCtrl",function($scope){
//	$scope.show = true;
	$scope.userRole = sessionStorage.getItem("userRole");
});

app.directive("myDirective",function($document){
	var roles = ["admin","op"];
	var elemPermissions = {
			"admin":["*"],
//			"op":["USER","CAR"]
			"op":[]
	};
	console.log("my-directive")
	return {
		restrict : "AE",
		link: function(scope,element,attrs,ngModel){
			var level = "none";
			if(attrs&&attrs["myDirectiveLevel"]){
				level = attrs["myDirectiveLevel"];
			}
			var id = "#"+attrs.id;
			switch(level){
			case "none":
				$(id).hide();
			}
			
			
			var access = attrs["myDirective"];
			console.log(access);
			
			var userRole = scope.userRole.toLowerCase();
			if(userRole=="admin"){
				$(id).show();
			}else{
				console.log(userRole);
				for(var i in roles) {
		            var tmp = roles[i].toLowerCase();
		            if(userRole == tmp) {
		              tmp = elemPermissions[userRole];
		              console.log(tmp)
		              for(var j in tmp){
		                console.log(tmp[j]+" "+access);
		                if(access.toLowerCase() == tmp[j].toLowerCase()) {
//		                  element.removeAttr("disabled");
//		                  element.show();
		                  $(id).show();
		                } 
		              }
		            }
				}
			}
			
		}
	}
});

//app.directive("zgAccess", function($scope, $http){
//  var roles = ["admin","op"];
//  var elemPermissions = {
//		  "admin":["*"],
//		  "op":["USER"]
//  };
//  console.log("zg-access");
//  return {
//    restrict: 'A',
//    compile: function(element, attr) {
//        // 初始为不可见状态none，还有 禁用disbaled和可用ok，共三种状态
//        var level = "none";
//        console.log(attr)
//        if(attr && attr["zgAccessLevel"]) {
//          level = attr["zgAccessLevel"];
//        }
//        switch(level) {
//          case "none": element.hide(); break;
//          case "disabled": 
//            element.attr("disabled", "");
//            break;
//        }
//        // 获取元素权限
//        var access = attr["zgAccess"];
//        // 将此权限上传到后端的数据库
////        (function(){
////         //upload 
////        })();
//        return function(scope, element) {
//          // 判断用户有无权限
////	          var user = $sessionStorage.USER;
//          var userRole = $scope.userRole;
////          if(user==null||angular.equals({}, user)) {
////            user = {};
////            user.role = "op";
////          }
//          if(userRole==null||angular.equals({},userRole)){
//        	  userRole = "op";
//          }
//          var role = userRole.toLowerCase();
//          console.log(roles);
//          for(var i in roles) {
//            var tmp = roles[i].toLowerCase();
//            if(role == tmp) {
//              tmp = elemPermission[role];
//              console.log(tmp)
//              for(var j in tmp){
//                console.log(tmp[j]+" "+access);
//                if(access.toLowerCase() == tmp[j].toLowerCase()) {
//                  element.removeAttr("disabled");
//                  element.show();
//                } 
//              }
//            }
//          }
//        };
//      }
//  }
//})