controllerModule.controller('RecipeController', function($scope, $http, RecipeService, CuisineService) {
    $scope.recipes = [];
    $scope.cuisines = [];

    CuisineService.getCuisines().then(function(cuisines){
        $scope.cuisines = cuisines;
    });

    RecipeService.getRecipes().then(function(recipes){
        $scope.recipes = recipes;
    });

    $scope.recipe ={};
    $scope.submit = function() {
        IngredientService.addNewRecipe($scope.recipe).then(function () {
            IngredientService.getRecipes().then(function(recipes){
                $scope.recipes = recipes;
            });
            console.log("completed");
        });
    }

});