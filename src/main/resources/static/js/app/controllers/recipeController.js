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
        RecipeService.addNewRecipe($scope.recipe, $scope.fff, function(response) {
            if (response.success) {
                console.log('Successfully added recipe ' + response.recipe);
                $location.path('/recipes');
            } else {
                console.log('Failed to add a recipe: ' + response.error);
            }

        });
    }

    $scope.fileChanged = function(element) {
        $scope.fff = element.files[0];
        console.log('Uploaded file: ' + $scope.fff.name);
    }


    $scope.setSelectedRecipe = function(recipe) {
        $rootScope.selectedRecipe = recipe;
        console.log("function called!!!");
        $location.path('/recipeView');
    }

    $scope.Edit = function(recipe) {
        $rootScope.selectedRecipe = recipe;
        $location.path('recipeEdit');
    }

    $rootScope.loggedIn = ($rootScope.user !== undefined);


});
