controllerModule.controller('RecipeViewController', function($scope, $http, $location, $rootScope, RecipeService) {

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
