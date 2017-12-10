servicesModule.service('LikeService', function($http, $rootScope, SERVER_URL) {

    this.addLike = function(recipe, like, callback) {
        var resp = {success: true};
        like.user = $rootScope.user;
        like.recipe = recipe;
        like.sign = true;
        like.timeStamp = Date.now();
        
        $http.post(SERVER_URL + 'api/like', JSON.stringify(like)).then(function (response) {
            console.log('Successfully put a like ');
            callback(resp);
        }).catch(function (response) {
            console.log('Failed to put a like ');
            resp = {success: false, errorMessage: 'Failed to put a like: ' + response.data};
            callback(resp);
        });

    }

    this.addDislike = function(recipe, like, callback) {
        var resp = {success: true};
        like.user = $rootScope.user;
        like.recipe = recipe;
        like.sign = false;
        like.timeStamp = Date.now();

        $http.post(SERVER_URL + 'api/like', JSON.stringify(like)).then(function (response) {
            console.log('Successfully put a dislike ');
            callback(resp);
        }).catch(function (response) {
            console.log('Failed to put a dislike ');
            resp = {success: false, errorMessage: 'Failed to put a dislike: ' + response.data};
            callback(resp);
        });
    }

});
