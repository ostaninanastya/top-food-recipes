const myApp = angular.module('topFoodRecipesApp', ["ngRoute"])
    .config(function($routeProvider){
        $routeProvider.when('/',
            {
                templateUrl:'main.html',
                controller:'RecipeController'
            });
    });