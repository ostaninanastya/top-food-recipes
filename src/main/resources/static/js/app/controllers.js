'use strict';

var controllerModule = angular.module('topFoodRecipeAppControllers', []);

controllerModule.controller('RecipeController', function($scope, $http, RecipeService) {
    $scope.recipes = [];
    RecipeService.getRecipes().then(function(recipes){
        $scope.recipes = recipes;
    });

});

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

controllerModule.controller('LoginController', function($scope, $http, LoginService) {

    $scope.login = function() {
        LoginService.loginUser($scope.user, function(response) {
            if (response.success) {
                console.log('Successfully logged in under ' + $scope.user.name);
                $location.path('/');
            } else {
                console.log('Login under ' + $scope.user.name + ' failed');
            }
        });

/*
        LoginService.loginUser($scope.user).then(function () {
            console.log("Login successful.");
        }) ;
*/
    }
});
