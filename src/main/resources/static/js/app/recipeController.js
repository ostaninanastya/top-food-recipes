angular.module('topFoodRecipesApp')
.controller('RecipeController', function($scope, $http, RecipeService) {
	$scope.recipes = [];

	RecipeService.getRecipes().then(function(recipes){
		$scope.recipes = recipes;
	});
})
.controller('CuisineController', function($scope, $http, CuisineService) {
	$scope.cuisines = [];

	CuisineService.getCuisines().then(function(cuisines){
		$scope.cuisines = cuisines;
	});
})
.controller('IngredientController', function($scope, $http, IngredientService) {
	$scope.ingredients = [];

	IngredientService.getIngredients().then(function(ingredients){
		$scope.ingredients = ingredients;
	});

    $scope.ingredients ={};
    $scope.submit = function() {
        IngredientService.addNewIngredient($scope.ingredient, $scope.measureName).then(function () {
            IngredientService.getIngredients().then(function(ingredients){
                $scope.ingredients = ingredients;
            });
            console.log("completed");
        }) ;
    }
});

