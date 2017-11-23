const myApp = angular.module('topFoodRecipesApp', ["ngRoute", "topFoodRecipeAppControllers", "topFoodRecipeAppServices"])
    .config(function($routeProvider) {
    	$routeProvider.when('/recipes', {
    		templateUrl:'views/recipes.html',
    		controller:'RecipeController'
    	}).when('/cuisines', {
    		templateUrl:'views/cuisines.html',
    		controller:'CuisineController'
    	}).when('/login', {
    		templateUrl:'views/login.html',
    		controller:'LoginController'
    	}).when('/logout', {
    		templateUrl:'views/logout.html',
    		controller:'LogoutController'
    	}).when('/register', {
    		templateUrl:'views/register.html',
    		controller:'RegisterController'
    	});

})
	.constant('SERVER_URL', 'http://localhost:8080/top_food_recipes-0.0.1-SNAPSHOT/')
;
