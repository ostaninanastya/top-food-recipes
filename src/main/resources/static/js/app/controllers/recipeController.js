controllerModule.controller('RecipeController', function($scope, $http, RecipeService) {
    $scope.recipes = [];
    RecipeService.getRecipes().then(function(recipes){
        $scope.recipes = recipes;
    });

});