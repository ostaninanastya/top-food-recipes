controllerModule.controller('AdvancedSearchController', function ($scope, $location, $http, $rootScope, $interval, RecipeService, CuisineService, LikeService) {

    RecipeService.getRecipes().then(function (recipes) {
        $scope.AllRecipes = recipes;
        $scope.recipes = recipes;

    });

    RecipeService.getIngredients().then(function (ingredients) {
        $scope.ingredients = ingredients;
    });

    RecipeService.getIngredientRecipes().then(function (ingredientsRecipes) {
        $scope.ingredientsRecipes = ingredientsRecipes;
    })

    CuisineService.getCuisines().then(function (cuisines) {
        $scope.cuisines = cuisines;
    });

    $scope.selectedIngredients = [];
    $scope.searchRecipe = {};

    $scope.updateingredientRecipe = function () {
        if ($scope.selectedIngredients.length !== undefined) {
            $scope.recipes = [];
        }
        for (var i = 0; i < $scope.AllRecipes.length; i++) {
            $scope.ingredientsForOneRecipe = [];
            //получаем ingredientRecipes для одного рецепта
            for (var j = 0; j < $scope.ingredientsRecipes.length; j++) {
                if ($scope.ingredientsRecipes[j].recipe.recipe_id === $scope.AllRecipes[i].recipe_id) {
                    $scope.ingredientsForOneRecipe.push($scope.ingredientsRecipes[j].ingredient);
                }
            }


            for (var j = 0; j < $scope.selectedIngredients.length; j++) {
                var recipeContainsIngredient = false;
                for (var v = 0; v <$scope.ingredientsForOneRecipe.length; v++)
                {
                    if ($scope.selectedIngredients[j].ingredient_id === $scope.ingredientsForOneRecipe[v].ingredient_id){
                        recipeContainsIngredient = true;
                    }
                }
                if (recipeContainsIngredient !== true)
                {
                    break;
                }
            }
            if (recipeContainsIngredient === true) {
                $scope.recipes.push($scope.AllRecipes[i]);
            }
            recipeContainsIngredient = false;
        }
    }

    //from recipeController
    $scope.like = {};
    $scope.sortType = 'rating';
    $scope.sortReverse = true;
    $scope.setSelectedRecipe = function (recipe) {
        $rootScope.selectedRecipe = recipe;
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
    $scope.isOwner = function (recipe) {
        if (recipe.user.name === $rootScope.user.name)
            return true;
        else
            return false;
        return false;
    }
    $rootScope.loggedIn = ($rootScope.user !== undefined);

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
});