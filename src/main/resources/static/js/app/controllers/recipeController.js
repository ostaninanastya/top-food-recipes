controllerModule.controller('RecipeController', function ($scope, $location, $http, $rootScope, $interval, RecipeService, CuisineService, LikeService) {
    $scope.like = {};

    /*-------------old version without reload---------------
    $scope.recipes = [];
    $scope.cuisines = [];
    RecipeService.getRecipes().then(function(recipes){
    $scope.recipes = recipes;
    });

    RecipeService.getIngredients().then(function(ingredients){
        $scope.ingredients = ingredients;
    });
    -------------old version without reload---------------*/

    CuisineService.getCuisines().then(function (cuisines) {
        $scope.cuisines = cuisines;
    });

    reloadRecipesPeriodically = function () {
        RecipeService.getRecipes().then(function (recipes) {
            $scope.recipes = recipes;
        });
    };

    $interval(reloadRecipesPeriodically, 1000);

    $scope.ingredientRecipeArray = [{}];
    $scope.addIngedient = function () {
        $scope.ingredientRecipeArray.push({});
    };

    $scope.submit = function () {
        angular.forEach($scope.ingredientRecipeArray, function (ingredientRecipe) {
            ingredientRecipe.recipe = $scope.recipe;
        })

        RecipeService.addNewRecipe($scope.recipe, $scope.fff, function (response) {
            if (response.success) {
                console.log('Successfully added recipe ' + response.recipe);
            } else {
                console.log('Failed to add a recipe: ' + response.error);
            }
        });
        RecipeService.addNewIngredientRecipe($scope.ingredientRecipeArray);
        $location.path('/recipes');
    }


    $scope.fileChanged = function (element) {
        $scope.fff = element.files[0];
        console.log('Uploaded file: ' + $scope.fff.name);
    }

    $scope.setSelectedRecipe = function (recipe) {
        $rootScope.selectedRecipe = recipe;
        console.log("function called!!!");
        $location.path('/recipeView');
    }

    $scope.Edit = function (recipe) {
        $rootScope.selectedRecipe = recipe;
        $location.path('recipeEdit');
    }

    $scope.addLike = function (recipe) {
        LikeService.addLike(recipe, $scope.like);
    }

    $scope.addDislike = function (recipe) {
        LikeService.addDislike(recipe, $scope.like);
    }

    $rootScope.loggedIn = ($rootScope.user !== undefined);

    $scope.measure = {}
    $scope.addMeasure = function () {
        RecipeService.addNewMeasure($scope.measure);
        $location.path('/createIngredient');
    }
});
