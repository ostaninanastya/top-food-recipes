servicesModule.service('MeasureService', function($http, SERVER_URL) {
    this.getMeasures = function () {
        return $http.get(SERVER_URL+'api/measure').then(function (response) {
            return response.data;
        }).catch(function (err) {
            return [];
        });
    }

    this.addNewMeasure = function(measure){
        return $http.post(SERVER_URL+'api/measure', JSON.stringify(measure)).success(function(){
            console.log("success");
        });
    }
});