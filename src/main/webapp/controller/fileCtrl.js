app.controller("fileCtrl",function($sceop,$rootScope,Upload){
	$scope.leadin = function(){
		console.log(322323);
		console.log($scope.file);
		Upload.upload({
            //服务端接收
            url: '/shangqi/file/uploadfile.ll',
            //上传的同时带的参数
//            data: {'username': $scope.username},
            //上传的文件
            file: $scope.file
        }).progress(function (evt) {
            //进度条
//            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
//            console.log('progess:' + progressPercentage + '%' + evt.config.file.name);
        }).success(function (data, status, headers, config) {
            //上传成功
//            console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
//            $scope.uploadImg = data;
        }).error(function (data, status, headers, config) {
            //上传失败
//            console.log('error status: ' + status);
        });
	}
});