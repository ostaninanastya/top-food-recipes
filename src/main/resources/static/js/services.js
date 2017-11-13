'use strict';

var module = angular.module('topFoodApp.services', []);

module.factory('topFoodAppService', function($http, $q) {
    var factory = {
        getAllCuisines: getAllCuisines
    };

    return factory;

    function getAllCuisines() {
        console.log('Get all cuisines');
        $http.get('http://localhost:8080/top_food_recipes-0.0.1-SNAPSHOT/api/cuisine')
            .then(
                function (response) {
                    console.log('Fetched successfully all cuisines');
                    deferred.resolve(response);
                },
                function (errResponse) {
                    console.error('Error while loading cuisines');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
});
