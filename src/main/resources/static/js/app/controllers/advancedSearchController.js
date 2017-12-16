controllerModule.controller('AdvancedSearchController', function ($scope, $location, $http, $rootScope, $interval, RecipeService, CuisineService, LikeService) {

    RecipeService.getRecipes().then(function (recipes) {
        $scope.AllRecipes = recipes;
        $scope.recipes = recipes;

    });

    RecipeService.getIngredients().then(function (ingredients) {
        $scope.ingredients = ingredients;
    });

    RecipeService.getIngredientRecipes().then(function (ingredientsRecipes) {
        $scope.ingredientsRecipes = ingredientsRecipes;
    })

    CuisineService.getCuisines().then(function (cuisines) {
        $scope.cuisines = cuisines;
    });

    $scope.selectedIngredients = [];
    $scope.searchRecipe = {};

    $scope.updateingredientRecipe = function () {
        $scope.recipes = [];
        for (var i = 0; i < $scope.AllRecipes.length; i++) {
            $scope.ingredientsForOneRecipe = [];
            //получаем ingredientRecipes для одного рецепта
            for (var j = 0; j < $scope.ingredientsRecipes.length; j++) {
                if ($scope.ingredientsRecipes[j].recipe.recipe_id === $scope.AllRecipes[i].recipe_id) {
                    $scope.ingredientsForOneRecipe.push($scope.ingredientsRecipes[j].ingredient);
                }
            }


            for (var j = 0; j < $scope.selectedIngredients.length; j++) {
                var recipeContainsIngredient = false;
                for (var v = 0; v <$scope.ingredientsForOneRecipe.length; v++)
                {
                    if ($scope.selectedIngredients[j].ingredient_id === $scope.ingredientsForOneRecipe[v].ingredient_id){
                        recipeContainsIngredient = true;
                    }
                }
                if (recipeContainsIngredient !== true)
                {
                    break;
                }
            }
            if (recipeContainsIngredient === true) {
                $scope.recipes.push($scope.AllRecipes[i]);
            }
            recipeContainsIngredient = false;
        }
    }
});