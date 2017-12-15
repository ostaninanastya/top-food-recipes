controllerModule.controller('RecipeController', function ($scope, $location, $http, $rootScope, $interval, RecipeService, CuisineService, LikeService) {
    $scope.like = {};
    $scope.sortType = 'rating';
    $scope.sortReverse = true;


    RecipeService.getRecipes().then(function(recipes){
        $scope.AllRecipes = recipes;
        $scope.recipes = $scope.AllRecipes;

    });

    $scope.updateRecipes = function (option) {
        if (option !== null)
        {
            $scope.recipes = $scope.AllRecipes.filter(function (i){
                return angular.equals(i.cuisine,option);
            });

        }
        else
            $scope.recipes = $scope.AllRecipes;

    }

    RecipeService.getIngredients().then(function(ingredients){
        $scope.ingredients = ingredients;
    });

    CuisineService.getCuisines().then(function (cuisines) {
        $scope.cuisines = cuisines;
    });

    $scope.ingredientRecipeArray = [{}];
    $scope.addIngredient = function () {
        $scope.ingredientRecipeArray.push({});
        $scope.lastItem = false;
    };

    $scope.deleteIngredient = function () {
        $scope.ingredientRecipeArray.pop();
        if ($scope.ingredientRecipeArray.length === 1) $scope.lastItem = true;
    };

    $scope.lastItem = true;

    $scope.submit = function () {
        angular.forEach($scope.ingredientRecipeArray, function (ingredientRecipe) {
            ingredientRecipe.recipe = $scope.recipe;
        })

        RecipeService.addNewRecipe($scope.recipe, $scope.fff, function (response) {
            if (response.success) {
                console.log('Successfully added recipe ' + response.recipe);

                for (var i = 0; i < $scope.ingredientRecipeArray.length; i++) {
                    $scope.ingredientRecipeArray[i].recipe = response.recipe;
                }

                RecipeService.addNewIngredientRecipe($scope.ingredientRecipeArray);
                $location.path('/recipes');
            } else {
                console.log('Failed to add a recipe: ' + response.error);
            }
        });
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
    }

    $scope.Delete = function (recipe) {
        RecipeService.deleteRecipe(recipe, function() {
            RecipeService.getRecipes().then(function(recipes){
                $scope.recipes = recipes;
            });
            $location.path('/recipes');
        });
    }

    $scope.addLike = function (recipe) {
        LikeService.addLike(recipe, $scope.like, function(response) {
            if (response.success) {
                $scope.often = '';
                console.log('Controller: success like');
                recipe.rating++;
            } else {
                console.log('Controller: fail like: ' + response.errorMessage);
                $scope.often = response.errorMessage;
            }
        });
    }

    $scope.addDislike = function (recipe) {
        LikeService.addDislike(recipe, $scope.like, function(response) {
            if (response.success) {
                $scope.often = '';
                console.log('Controller: success dislike');
                recipe.rating--;
            } else {
                console.log('Controller: fail dislike: ' + response.errorMessage);
                $scope.often = response.errorMessage;
            }
        });
    }

    $rootScope.loggedIn = ($rootScope.user !== undefined);

    $scope.isOwner = function (recipe) {
        if (recipe.user.name == $rootScope.user.name)
            return true;
        else
            return false;
    }

});
