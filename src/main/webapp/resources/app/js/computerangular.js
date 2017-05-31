
var app = angular.module("myApp", ['smart-table']);

app.controller('basicsCtrl', ['$scope', '$http', function ($scope, $http) {


	$http.get("order/computers?computerId=0").success(function(data){
    $scope.rowCollection = data;

  });


}]);
