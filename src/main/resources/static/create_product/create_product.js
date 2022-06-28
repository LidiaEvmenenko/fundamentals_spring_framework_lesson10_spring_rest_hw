angular.module('app-front').controller('createProductController', function ($rootScope, $scope, $http, $location) {
    const contextPath = 'http://localhost:8189/app/api/v1/';

     $scope.createNewProduct = function () {
     if($scope.new_product == null){
     alert("Заполните форму!!!");
     return;
     }
                $http.post(contextPath + 'products', $scope.new_product)
                    .then(function successCallback(response) {

                            $scope.new_product = null;
                            alert("Продукт создан!!!");
                            $location.path('/scope');
                        }, function failCallback(response) {
                            alert(response.data.message);
                        }
                    );
            }

});