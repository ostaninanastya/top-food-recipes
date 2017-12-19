var controllerModule = angular.module('topFoodRecipeAppControllers', ['ui.grid','ui.grid.pagination']);
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