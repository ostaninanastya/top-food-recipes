controllerModule.controller('RecipeViewController', function($scope, $http, $location, $rootScope, RecipeService) {

    $scope.ingredientRecipeArray = [];
    RecipeService.getIngredientRecipes().then(function (ingredientsRecipes) {
        $scope.ingredientsRecipes = ingredientsRecipes;
        for (var i = 0; i < $scope.ingredientsRecipes.length; i++)
        {
            if ($scope.ingredientsRecipes[i].recipe.recipe_id === $rootScope.selectedRecipe.recipe_id)
            {
                $scope.ingredientRecipeArray.push($scope.ingredientsRecipes[i]);
            }
        }
    })


    $scope.Delete = function () {
        RecipeService.deleteRecipe($scope.selectedRecipe, function() {
            RecipeService.getRecipes().then(function(recipes){
                $location.path('/recipes');
            });
        });
    }
    $scope.isOwner = function () {
        if ($scope.selectedRecipe.user.name == $rootScope.user.name)
            return true;
        else
            return false;
    }


});
