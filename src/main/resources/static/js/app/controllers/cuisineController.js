var controllerModule = angular.module('topFoodRecipeAppControllers', []);
controllerModule.controller('CuisineController', function($scope, $http, CuisineService) {
    $scope.cuisines = [];
    CuisineService.getCuisines().then(function(cuisines){
        $scope.cuisines = cuisines;
    });

    $scope.cuisine ={};
    $scope.submit = function() {
        CuisineService.addNewCuisine($scope.cuisine).then(function () {
            CuisineService.getCuisines().then(function(cuisines){
                $scope.cuisines = cuisines;
            });
            console.log("completed");
        }) ;
    }
});