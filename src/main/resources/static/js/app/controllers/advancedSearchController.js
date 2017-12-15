controllerModule.controller('RecipeController', function ($scope, $location, $http, $rootScope, $interval, RecipeService, CuisineService, LikeService) {

    RecipeService.getRecipes().then(function(recipes){
        $scope.AllRecipes = recipes;
        $scope.recipes = $scope.AllRecipes;

    });

    RecipeService.getIngredients().then(function(ingredients){
        $scope.ingredients = ingredients;
    });

    CuisineService.getCuisines().then(function (cuisines) {
        $scope.cuisines = cuisines;
    });

    $scope.ingredientRecipe = {};
    $scope.ingredientRecipe.ingredient = [];
});