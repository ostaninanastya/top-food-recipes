angular.module('topFoodRecipesApp')
    .controller('RecipeController', function($scope, $http, RecipeService) {
        $scope.recipes = [];
        RecipeService.getRecipes().then(function(recipes){
            $scope.recipes = recipes;
        });

    })
    .controller('CuisineController', function($scope, $http, CuisineService) {
        $scope.cuisines = [];
        CuisineService.getCuisines().then(function(cuisines){
            $scope.cuisines = cuisines;
        });


        $scope.cuisine ={};


        $scope.submit = function(cuisine, $http, CuisineService) {
          CuisineService.addNewCuisine().then(function () {
			  console.log("complited");
          }) ;
        };
    });

