controllerModule.controller('MeasureController', function($scope, $http, MeasureService, $location) {
    $scope.measures = [];
    MeasureService.getMeasures().then(function (measures) {
        $scope.measures = measures;
    });

    $scope.measure = {};
    $scope.submit = function () {
        MeasureService.addNewMeasure($scope.measure).then(function () {
            MeasureService.getMeasures().then(function (measures) {
                $scope.measures = measures;
            });
            console.log("completed");
            $location.path('/createIngredient');
        });
    }
});