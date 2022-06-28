angular.module('app-front').controller('editProductController', function ($routeParams, $scope, $http, $location) {
    const contextPath = 'http://localhost:8189/app/api/v1/';


     $scope.putFormProduct = function () {
           $http.get(contextPath + 'products/' + $routeParams.productId)
            .then(function successCallback(response) {
                    $scope.put_product = response.data;
                }, function failCallback(response) {
                    alert(response.data.messages);
                    $location.path('store');
                }
            );
     }


      $scope.putProduct = function () {
                    $http.put(contextPath + 'products', $scope.put_product)
              .then(function successCallback(response) {
                      $scope.put_product = null;
                      alert("Продукт успешно обновлен");
                      $location.path('store');
                  }, function failCallback(response) {
                      alert(response.data.messages);
                  }
              );
                         }
      $scope.putFormProduct();

});