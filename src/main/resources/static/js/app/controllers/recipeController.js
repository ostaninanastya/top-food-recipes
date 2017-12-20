controllerModule.controller('RecipeController', function ($scope, $location, $http, $rootScope, $interval, RecipeService, CuisineService, LikeService) {
    var paginationOptions = {
        pageNumber: 0,
        pageSize: 5,
        sort: null
    };
    /*
    CuisineService.getCuisines().then(function(cuisines){
        $scope.cuisines = cuisines;
    });
    */
    RecipeService.getRecipes(
        paginationOptions.pageNumber,
        paginationOptions.pageSize).then(function(data){
        $scope.gridOptions.data = data.content;
        $scope.gridOptions.totalItems = data.totalElements;
        $scope.recipes = data.content;
    });

    $scope.gridOptions = {
        paginationPageSizes: [5,10],
        paginationPageSize: paginationOptions.pageSize,
        enableColumnMenus:false,
        useExternalPagination: true,
        columnDefs: [
            { field: 'name', displayName: 'Название' },
            { field: 'recipe', displayName: 'Рецепт' },
            { field: 'cuisine.name', displayName: 'Кухня' },
            { field: 'rating', displayName: 'Рейтинг' }
        ],


    };

    $scope.pagination = {
        pageSize: 5,
        pageNumber: 1,
        totalItems: null,
        getTotalPages: function () {
            return Math.ceil(this.totalItems / this.pageSize);
        },
        nextPage: function () {
            if (this.pageNumber < this.getTotalPages()) {
                this.pageNumber++;
                $scope.load();
            }
        },
        previousPage: function () {
            if (this.pageNumber > 1) {
                this.pageNumber--;
                $scope.load();
            }
        }
    }
    $scope.load = function () {
        RecipeService.getRecipes($scope.pagination.pageSize, $scope.pagination.pageNumber).then(function (response) {
            $scope.gridOptions.data = response.data;
            $scope.pagination.totalItems = response.totalRows;

        });
    }

    $scope.load();




    $scope.pagination = {
        pageSize: 5,
        pageNumber: 1,
        totalItems: null,
        
        getTotalPages: function () {
            return Math.ceil(this.totalItems / this.pageSize);
        },
        
        nextPage: function () {
            if (this.pageNumber < this.getTotalPages()) {
                this.pageNumber++;
                $scope.loadRecipesPage();
            }
        },
        
        previousPage: function () {
            if (this.pageNumber > 1) {
                this.pageNumber--;
                $scope.loadRecipesPage();
            }
        }
        
    }

    $scope.loadRecipesPage = function() {
        RecipeService.getRecipesPage($scope.pagination.pageNumber - 1, $scope.pagination.pageSize).then(function(recipes) {
            $scope.AllRecipes = recipes.content;
            $scope.recipes = $scope.AllRecipes;
            $scope.pagination.totalItems = recipes.totalElements;
        })
    }

    $scope.loadRecipesPage();
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
        if (recipe.user.name === $rootScope.user.name)
            return true;
        else
            return false;
            return false;
    }

});
