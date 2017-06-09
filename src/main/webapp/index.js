angular.module("myApp",['ui.bootstrap']);
angular.module("myApp",['ui.router']);
var app = angular.module("myApp");






//app.controller("myCtrl",function($scope){
//	$('#page').bs_pagination({ 
//	    totalPages:5,//总页数：由接口返回（AJAX） 
//	    currentPage:1,//当前页：由前端指定 
//	    visiblePageLinks:5,//显示的最多分页链接数 
//	    showGoToPage:false, 
//	    showRowsPerPage: false, 
//	    showRowsInfo: false, 
//	    showRowsDefaultInfo: false, 
//	    mainWrapperClass:'dataPage clearfix', 
//	    navListContainerClass:'', 
//	    navListClass:'pagination-sm pagination', 
//	    containerClass:'', 
//	    onChangePage:function(e,obj){//returns page_num and rows_per_page 
//	        //当分页被点击的时候，事件回调 
//	        //obj.currentPage;//获取点击对象里的当前页 
//	          
//	    } 
//	});
//});

//app.config(function($httpProvider){
//	$httpProvider.interceptors.push(HttpInterceptor);
//});
//
//app.factory("HttpInterceptor",function($q){
//	function HttpInterceptor($q) {
//		 return {
//		  // 请求发出之前，可以用于添加各种身份验证信息
//		  request: function(config){
//			  console.log(config);
////		   if(localStorage.token) {
////		    config.headers.token = localStorage.token;
////		   }
//		   return config;
//		  },
//		  // 请求发出时出错
//		  requestError: function(err){
//			  console.log(err);
////		   return $q.reject(err);
//		  },
//		  // 成功返回了响应
//		  response: function(res){
//			  console.log(res);
////		   return res;
//		  },
//		  // 返回的响应出错，包括后端返回响应时，设置了非 200 的 http 状态码
//		  responseError: function(err){
////			  console.log(err);
//			  window.location.href("login.html");
////		   return $q.reject(err);
//		  }
//		 };
//		}
//});





app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/dashboard');
    $stateProvider
	    .state('dashboard',{
	    	url:'/dashboard',
	    	templateUrl:'html/dashboard/dashboard.html'
	    })
        .state('user', {
            url: '/user',
//            templateUrl: 'html/user/usertable.html',
            templateUrl: 'html/user/usertable.html',
            controller: 'userCtrl',
            data:{
            	authorizedRoles:['admin']
            }
        })
        .state('userinfo',{
        	url:'/userinfo',
        	templateUrl: 'html/user/usertable.html',
        	controller: 'userCtrl',
//        	permission: 'admin'
        })
        .state('adduser',{
        	url:'/adduser',
        	templateUrl: 'html/user/adduser.html',
        	controller: 'userCtrl'
        })
//        .state('position', {
//        	url: '/position',
//        	templateUrl: 'html/position/position.html',
//        	controller: 'positionCtrl'
//        })
        .state('addposition',{
        	url: '/addposition',
        	templateUrl: 'html/position/addposition.html',
        	controller: 'positionCtrl'
        })
        .state('car', {
        	url: '/car',
        	templateUrl: 'html/car/cartable.html',
        	controller: 'carCtrl'
        })
        .state('carinfo',{
        	url:'/carinfo',
        	templateUrl: 'html/car/cartable.html',
        	controller: 'carCtrl'
        })
        .state('addcar',{
        	url:'/addcar',
        	templateUrl:'html/car/addcar.html',
        	controller:'carCtrl'
        })
        .state('ibeacon',{
        	url:'/ibeacon',
        	templateUrl: 'html/location/IBeacontable.html',
        	controller : 'ibeaconCtrl'
        })
        .state('ibeaconinfo',{
        	url:'/ibeaconinfo',
        	templateUrl: 'html/location/IBeaconinfotable.html',
        	controller : 'ibeaconCtrl'
        })
        .state('addibeacon',{
        	url:'/addibeacon',
        	templateUrl:'html/location/addibeacon.html',
        	controller:'ibeaconCtrl'
        })
        .state('lora',{
        	url:'/lora',
        	templateUrl:'html/lora/loratable.html',
        	controller:'loraCtrl'
        })
        .state('addlora',{
        	url:'/addlora',
        	templateUrl:'html/lora/addlora.html',
        	controller:'loraCtrl'
        })
        .state('lorainfo',{
        	url:'/lorainfo',
        	templateUrl:'html/lora/lorainfotable.html',
        	controller:'loraCtrl'
        })
        .state('local',{
        	url:'/local',
        	templateUrl:'html/local/localtable.html',
        	controller:'localCtrl'
        })
        .state('addlocal',{
        	url:'/addlocal',
        	templateUrl:'html/local/addlocal.html',
        	controller:'localCtrl'
        })
        .state('localinfo',{
        	url:'/localinfo',
        	templateUrl:'html/local/localinfotable.html',
        	controller:'localCtrl'
        })
        .state('gps',{
        	url:'/gps',
        	templateUrl:'html/location/GPStable.html'
        })
        .state('map',{
        	url:'/map',
        	templateUrl:'html/map/map.html'
        })
        .state('mapposition',{
        	url:'/mapposition',
        	templateUrl:'html/map/map.html'
        })
        .state('maproad',{
        	url:'/maproad',
        	templateUrl:'html/map/maproad.html'
        })
        .state('maparea',{
        	url:'/maparea',
        	templateUrl:'html/map/maparea.html'
        })
        .state('areainfo',{
        	url:'/areainfo',
        	templateUrl:'html/area/areatable.html'
        })
        .state('position',{
        	url:'/position',
        	templateUrl:'html/position/positiontable.html',
        	controller: 'positionCtrl'
        })
        .state('barchart',{
        	url:'/barchart',
        	templateUrl:'html/charts/barchart.html'
        })
        .state('piechart',{
        	url:'/piechart',
        	templateUrl:'html/charts/piechart.html',
        	controller:'peichartCtrl'
        })
        .state('charts',{
        	url:'/charts',
        	templateUrl:'html/charts/barchart.html'
        })
        .state('binding',{
        	url:'/binding',
        	templateUrl:'html/bind/bindtable.html',
        	controller:'bindCtrl'
        })
        .state('addbind',{
        	url:'/addbind',
        	templateUrl:'html/bind/addbind.html',
        	controller:'bindCtrl'
        })
        .state('unbind',{
        	url:'/unbind',
        	templateUrl:'html/bind/unbind.html',
        	controller:'bindCtrl'
        })
        .state('page',{
        	url:'/page',
        	templateUrl:'html/page/page.html',
        	controller:'PaginationDemoCtrl'
        })
        .state('roaded',{
        	url:'/roaded',
        	templateUrl:'html/map/maproaded.html'
        })
        .state('ibeaconmap',{
        	url: '/ibeaconmap',
        	templateUrl:'html/map/ibeaconmap.html'
        })
        
//        .state('login',{
//        	url:'login',
//        	templateUrl:'login.html'
//        })
});


//app.config(function($routeProvider){
//	$routeProvider
//    .when('/user', {
//        templateUrl: 'html/user/usertable.html',
//        controller: 'userCtrl'
//    })
//    .when('/ibeacon', {
//        templateUrl: 'html/location/IBeacontable.html',
//        controller: 'ibeaconCtrl'
//    })
//    .when('/gps',{
//    	templateUrl: 'html/location/GPStable.html',
//    	controller: 'gpsCtrl'
//    })
//    .when('/car',{
//    	templateUrl: 'html/car/cartable.html',
//    	controller: 'carCtrl'
//    })
//    .when('/position',{
//    	templateUrl: 'html/position/position.html',
//    	controller: 'positionCtrl'
//    })
//    .when('/map',{
//    	templateUrl: 'html/map/map.html',
//    	controller: 'mapCtrl'
//    })
//    .when('/barchart',{
//    	templateUrl: 'html/charts/barchart.html',
//    	controller: 'barchartCtrl'
//    })
//    .when('/piechart',{
//    	templateUrl: 'html/charts/piechart.html',
//    	controller: 'peichartCtrl'
//    })
//    .when('/dashboard',{
//    	templateUrl: 'html/dashboard/dashboard.html',
////    	controller: 'dashboardCtrl',
//    })
//    .when('/charts',{
//    	redirectTo: '/barchart'
//    })
//    .otherwise({
//        redirectTo: '/user'
//    });
//});