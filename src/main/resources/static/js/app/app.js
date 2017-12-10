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
    	}).when('/popular', {
            templateUrl:'views/popular.html',
            controller:'PopularController'
        }).when('/createRecipe', {
            templateUrl:'views/createRecipe.html',
            controller:'RecipeController'
        }).when('/createIngredient', {
            templateUrl:'views/createIngredient.html',
            controller:'IngredientController'
        }).when('/createCuisine', {
            templateUrl:'views/createCuisine.html',
            controller:'CuisineController'
        }).when('/recipeView', {
            templateUrl:'views/recipeView.html',
            controller:'RecipeViewController'
        }).when('/recipeEdit', {
            templateUrl: 'views/recipeEdit.html',
            controller: 'RecipeEditController'
        }).when('/createMeasure', {
            templateUrl: 'views/createMeasure.html',
            controller: 'RecipeController'
        });

})
    .constant('SERVER_URL', 'http://localhost:8020/');
	//.constant('SERVER_URL', 'http://188.166.30.145:8080/top_food_recipes-0.0.1-SNAPSHOT/')
;
