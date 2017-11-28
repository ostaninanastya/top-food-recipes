controllerModule.controller('PopularController', function($scope, $http, PopularService) {
    $scope.recipes = [];
    PopularService.getRecipes().then(function(recipes){
        $scope.recipes = recipes;
    });

});