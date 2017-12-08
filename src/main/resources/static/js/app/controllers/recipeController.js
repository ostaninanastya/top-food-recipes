controllerModule.controller('RecipeController', function($scope, $location, $http, $rootScope, $interval, RecipeService, CuisineService, LikeService) {
    $scope.like = {};

    CuisineService.getCuisines().then(function(cuisines){
        $scope.cuisines = cuisines;
    });


    reloadRecipesPeriodically = function() {
        RecipeService.getRecipes().then(function(recipes){
        $scope.recipes = recipes;
        });
    };

    $interval(reloadRecipesPeriodically, 1000);


    $scope.submit = function() {
        $scope.ingredientRecipe1.recipe =$scope.recipe;
        $scope.ingredientRecipe2.recipe =$scope.recipe;
        $scope.ingredientRecipe3.recipe =$scope.recipe;
        $scope.ingredientRecipe4.recipe =$scope.recipe;
        $scope.ingredientRecipe5.recipe =$scope.recipe;

        RecipeService.addNewRecipe($scope.recipe, $scope.fff, function(response) {
            if (response.success) {
                console.log('Successfully added recipe ' + response.recipe);
            } else {
                console.log('Failed to add a recipe: ' + response.error);
            }
        });
        RecipeService.addNewIngredientRecipe($scope.ingredientRecipe1,$scope.ingredientRecipe2,
            $scope.ingredientRecipe3,$scope.ingredientRecipe4,
            $scope.ingredientRecipe5);
        $location.path('/recipes');
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
    }

    $scope.addDislike = function(recipe) {
        LikeService.addDislike(recipe, $scope.like);
    }

    $rootScope.loggedIn = ($rootScope.user !== undefined);

    $scope.measure = {}
    $scope.addMeasure = function(){
        RecipeService.addNewMeasure($scope.measure);
        $location.path('/createIngredient');
    }

});
