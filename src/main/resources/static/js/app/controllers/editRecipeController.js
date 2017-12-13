controllerModule.controller('EditRecipeController', function ($scope, $location, $http, $rootScope, RecipeService, CuisineService, EditRecipeService) {

    RecipeService.getIngredients().then(function(ingredients){
        $scope.ingredients = ingredients;
    });

    CuisineService.getCuisines().then(function (cuisines) {
        $scope.cuisines = cuisines;
    });

    EditRecipeService.getIngredientRecipe($rootScope.selectedRecipe, function(ingredientRecipeArray){
        $scope.ingredientRecipeArray = ingredientRecipeArray;
    });

    $scope.ingredientRecipeArray = [];

    $scope.addIngredient = function () {
        $scope.ingredientRecipeArray.push({});
        $scope.lastItem = false;
    };

    $scope.lastItem = $scope.ingredientRecipeArray.length === 1;

    $scope.ingredientRecipeDeleteArray = [];
    $scope.deleteIngredient = function () {
        if ($scope.ingredientRecipeArray[$scope.ingredientRecipeArray.length - 1].ingredient === undefined
            || $scope.ingredientRecipeArray[$scope.ingredientRecipeArray.length - 1].id === undefined) {
            $scope.ingredientRecipeArray.pop();
        }
        else  $scope.ingredientRecipeDeleteArray.push($scope.ingredientRecipeArray.pop());
        if ($scope.ingredientRecipeArray.length === 1) $scope.lastItem = true;
    };

    $scope.editRecipe = function () {
        EditRecipeService.updateRecipe($rootScope.selectedRecipe, function(response){
           $scope.ingredientRecipeArray = response;
        });

        angular.forEach($scope.ingredientRecipeArray, function (ingredientRecipe) {
            ingredientRecipe.recipe = $rootScope.selectedRecipe;
        });

        EditRecipeService.updateIngredientRecipe($scope.ingredientRecipeArray);
        EditRecipeService.deleteIngredientRecipe($scope.ingredientRecipeDeleteArray);

        $location.path('/recipes');
    }
});