controllerModule.controller('RecipeEditController', function($scope, $http, $rootScope, RecipeEditService, CuisineService) {

    $scope.submit = function() {
        RecipeEditService.editRecipe($rootScope.selectedRecipe).then(function () {
            console.log("completed");
        });
    }
    $scope.cuisines = [];

    CuisineService.getCuisines().then(function(cuisines){
        $scope.cuisines = cuisines;
    });
});