/**
 * Created by Administrator on 2017/1/5.
 */
angular.module('ui.bootstrap.demo', ['ui.bootstrap']);
angular.module('ui.bootstrap.demo').controller('PaginationDemoCtrl', function ($scope, $log) {
	$log.log($scope);
    $scope.totalItems = 64;
    $scope.currentPage = 1;

    $scope.setPage = function (pageNo) {
    	$log.log(pageNo);
    };

    $scope.pageChanged = function() {
        $log.log('Page changed to: ' + $scope.bigCurrentPage);
    };
    

    $scope.maxSize = 5;
    $scope.bigTotalItems = 175;
    $scope.bigCurrentPage = 1;
});