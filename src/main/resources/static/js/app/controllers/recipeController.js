controllerModule.controller('RecipeController', function($scope, $location, $http, $rootScope, RecipeService, CuisineService) {
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
        RecipeService.addNewRecipe($scope.recipe).then(function () {
            RecipeService.getRecipes().then(function(recipes){
                $scope.recipes = recipes;
            });
            console.log("completed");
        });
    }

    $scope.setSelectedRecipe = function(recipe) {
        $rootScope.selectedRecipe = recipe;
        console.log("function called!!!");
        $location.path('/recipeView');
    }
});
