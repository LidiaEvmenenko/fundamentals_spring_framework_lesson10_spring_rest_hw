angular.module('app-front').controller('storeController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/app/api/v1/';


//let pageInt=1;
//let pageMax=0;
let pageElements=0;
//let totalElementsEmpty=false;
let currentPageIndex = 1;
//let inputId;
//let inputTitle;
//let inputPrice;
//let inputCategoryTitle;

 $scope.loadProducts = function(pageIndex = 1){
     currentPageIndex = pageIndex;
      $http({
         url: contextPath + 'products',
         method: 'GET',
         params: {
          p: pageIndex
         }
      }).then(function(response) {
         console.log(response);
         $scope.productsPage = response.data;
         $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
      });
      }

 $scope.loadProducts();

 $scope.deleteProduct = function (product) {
     $http.delete(contextPath + 'products/'+ product.id)
                 .then(function successCallback(response) {
                     $scope.productsPage = response.data;
//                     pageElements = pageElements - 1;
//                     if(pageElements == 0 && pageIndex != 1) {
//                        pageInt = pageInt - 1;
//                     }
//                     else{
//                        if(pageElements == 0 && pageIndex == 1) {
//                           totalElementsEmpty = true;
//                           }
//                     }
                     $scope.loadProducts(currentPageIndex);
                     }, function failCallback(response) {
                          alert(response.data.message);
                 });
    }


 $scope.navToEditProductPage = function (productId) {
        $location.path('edit_product/' + productId);
     //   $location.path('edit_product/').search({params: productId.id});
        console.log("product = "+productId);
        console.log("product = "+productId.id);
        console.log("product = "+productId.title);
        console.log("product = "+productId.price);
     }

 $scope.showInfo=function(product){
    alert("Категория: " + product.categoryTitle);
    }


 $scope.generatePagesIndexes = function (startPage, endPage) {
                let arr = [];
                for (let i = startPage; i < endPage + 1; i++) {
                    arr.push(i);
                }
                return arr;
            }

 $scope.nextPage = function () {
                currentPageIndex++;
                if (currentPageIndex > $scope.productsPage.totalPages) {
                    currentPageIndex = $scope.productsPage.totalPages;
                }
                $scope.loadProducts(currentPageIndex);
 }

 $scope.prevPage = function () {
                currentPageIndex--;
                if (currentPageIndex < 1) {
                    currentPageIndex = 1;
                }
                $scope.loadProducts(currentPageIndex);
 }
  $scope.addCartProduct = function(productId) {
            $http.post(contextPath + 'products/cart/' + productId)
             .then(function successCallback(response) {
                     alert("Продукт добавлен в корзину");
                 }, function failCallback(response) {
                     alert(response.data.messages);
                   //  $location.path('store');
                 }
             );
      }
             //   $location.path('cart/' + productId);

});