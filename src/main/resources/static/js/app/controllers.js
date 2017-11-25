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

controllerModule.controller('MainController', function($scope, $rootScope, $location, LoginService) {
    $rootScope.menu = LoginService.getMenu();

    $rootScope.$on('registration_event', function(event, data) {
        console.log('XXXXX: received data: ' + data);
        LoginService.loginUser(data, function(response) {
            if (response.success) {
                console.log('Successfully logged in under ' + $rootScope.user.name);
                $rootScope.$broadcast('reload_page_event');
            } else {
                console.log('Login under ' + $scope.user.name + ' failed');
            }
        });
    });

    $rootScope.$on('reload_page_event', function(event, data) {
        $rootScope.menu = LoginService.getMenu();
        $location.path('/');
    });

});


controllerModule.controller('LoginController', function($scope, $rootScope, $location, LoginService) {

    $scope.login = function() {
        LoginService.loginUser($scope.user, function(response) {
            if (response.success) {
                console.log('Successfully logged in under ' + $rootScope.user.name);
                $rootScope.$broadcast('reload_page_event');
            } else {
                console.log('Login under ' + $scope.user.name + ' failed');
            }
        });
    }

    $scope.$on('registration_event', function(event, data) {
       console.log('YYYYYY');
    });

});

controllerModule.controller('RegisterController', function($scope, $rootScope, $location, LoginService) {

    $scope.register = function() {
        console.log('Register user, name=' + $scope.user.name + ', password=' + $scope.user.password);

        LoginService.registerUser($scope.user, function(response) {
            if (response.success) {
                console.log('Success');
                $rootScope.$broadcast('registration_event', $scope.user);
            } else {
                console.log('Fail');
            }
        }); 

    }
});

controllerModule.controller('IngredientController', function($scope, $http, IngredientService, MeasureService) {
    $scope.ingredients = [];
    $scope.measures = [];
    MeasureService.getMeasures().then(function(measures){
        $scope.measures = measures;
    });

    IngredientService.getIngredients().then(function(ingredients){
        $scope.ingredients = ingredients;
    });
    $scope.ingredient ={};
    $scope.submit = function() {
        IngredientService.addNewIngredient($scope.ingredient).then(function () {
            IngredientService.getIngredients().then(function(ingredients){
                $scope.ingredients = ingredients;
            });
            console.log("completed");
        });
    }
});

controllerModule.controller('LogoutController', function($scope, $rootScope, $location, LoginService) {

    $scope.$on('$routeChangeSuccess', function () {
        $rootScope.user = undefined;
        $rootScope.$broadcast('reload_page_event');
    });

});

