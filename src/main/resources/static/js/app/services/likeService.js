servicesModule.service('LikeService', function($http, SERVER_URL) {
    this.getRating = function(recipe) {
        return $http.get(SERVER_URL + 'api/like/rating', JSON.stringify(recipe)).then(function(response){
            return response.data;
        });
    }
    });