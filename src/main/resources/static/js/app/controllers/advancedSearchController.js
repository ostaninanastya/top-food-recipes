controllerModule.controller('AdvancedSearchController', function ($scope, $location, $http, $rootScope, $interval, RecipeService, CuisineService, LikeService, AdvancedSearchService) {

    RecipeService.getRecipes().then(function(recipes){
        $scope.AllRecipes = recipes;
        $scope.recipes = $scope.AllRecipes;

    });

    RecipeService.getIngredients().then(function(ingredients){
        $scope.ingredients = ingredients;
    });

    RecipeService.getIngredientRecipes().then(function (ingredientsRecipes) {
        $scope.ingredientsRecipes = ingredientsRecipes;
    })

    CuisineService.getCuisines().then(function (cuisines) {
        $scope.cuisines = cuisines;
    });


    $scope.ingredientRecipeArray = [];
    $scope.searchRecipe ={};

    $scope.updateingredientRecipe = function () {


    }
});