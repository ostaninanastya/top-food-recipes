controllerModule.controller('RecipeController', function($scope, $http, $rootScope, RecipeService, CuisineService) {
    $scope.recipes = [];
    $scope.cuisines = [];

    CuisineService.getCuisines().then(function(cuisines){
        $scope.cuisines = cuisines;
    });

    RecipeService.getRecipes().then(function(recipes){
        $scope.recipes = recipes;
    });

    $scope.recipe ={};
    $scope.f = [];
    $scope.submit = function() {
        RecipeService.addNewRecipe($scope.recipe, $scope.f).then(function () {
            RecipeService.getRecipes().then(function(recipes){
                $scope.recipes = recipes;
            });
            console.log("completed");
        });
    }

});