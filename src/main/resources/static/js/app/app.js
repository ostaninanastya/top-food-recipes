const myApp = angular.module('topFoodRecipesApp', ["ngRoute"])
    .config(function($routeProvider){
    	$routeProvider.when('/recipes', {
    		templateUrl:'views/recipes.html',
    		controller:'RecipeController'
    	}).when('/cuisines', {
    		templateUrl:'views/cuisines.html',
    		controller:'CuisineController'
    	}).when('/ingredients', {
    		templateUrl:'views/ingredients.html',
			controller:'IngredientController'
		}).when('/ingredients/add', {
			templateUrl:'views/add_ingredient.html',
			controller:'IngredientController'
		})
		;
});