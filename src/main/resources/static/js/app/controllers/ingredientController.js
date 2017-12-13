controllerModule.controller('IngredientController', function($scope, $http, $route, IngredientService, MeasureService) {
    $scope.ingredients = [];
    $scope.measures = [];
    MeasureService.getMeasures().then(function(measures){
        $scope.measures = measures;
    });

    IngredientService.getIngredients().then(function(ingredients){
        $scope.ingredients = ingredients;
    });
    $scope.ingredient ={};
    $scope.submit = function() {
        IngredientService.addNewIngredient($scope.ingredient).then(function () {
            IngredientService.getIngredients().then(function(ingredients){
                $scope.ingredients = ingredients;
            });
            console.log("completed");
            $route.reload();
        });
    }
});