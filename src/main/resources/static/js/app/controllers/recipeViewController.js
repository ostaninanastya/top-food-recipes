controllerModule.controller('RecipeViewController', function($scope, $http, $location, RecipeService) {

    $scope.Delete = function () {
        RecipeService.deleteRecipe($scope.selectedRecipe, function() {
            RecipeService.getRecipes().then(function(recipes){
                $location.path('/recipes');
            });
        });
    }
});
