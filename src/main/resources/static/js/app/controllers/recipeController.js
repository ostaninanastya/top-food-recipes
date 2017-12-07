controllerModule.controller('RecipeController', function($scope, $location, $http, $rootScope, RecipeService, CuisineService, LikeService) {
    $scope.recipes = [];
    $scope.cuisines = [];
    $scope.like = {};

    CuisineService.getCuisines().then(function(cuisines){
        $scope.cuisines = cuisines;
    });

    RecipeService.getRecipes().then(function(recipes){
        $scope.recipes = recipes;
    });

    RecipeService.getIngredients().then(function(ingredients){
        $scope.ingredients = ingredients;
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

    $scope.addLike = function(recipe) {
        LikeService.addLike(recipe, $scope.like);
        $scope.recipe = RecipeService.getRecipe(recipe.id);
    }

    $scope.addDislike = function(recipe) {
        LikeService.addDislike(recipe, $scope.like);
        $scope.recipe = RecipeService.getRecipe(recipe.id);
    }

    $rootScope.loggedIn = ($rootScope.user !== undefined);


});
