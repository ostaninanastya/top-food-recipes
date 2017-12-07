servicesModule.service('LikeService', function($http, $rootScope, SERVER_URL) {

    this.addLike = function(recipe, like) {
        like.user = $rootScope.user;
        like.recipe = recipe;
        like.sign = true;
        like.timeStamp = Date.now();
        return $http.post(SERVER_URL + 'api/like', JSON.stringify(like)).success(function(){
            console.log("success");
        });
    }

    this.addDislike = function(recipe, like) {
        like.user = $rootScope.user;
        like.recipe = recipe;
        like.sign = false;
        like.timeStamp = Date.now();
        return $http.post(SERVER_URL + 'api/like', JSON.stringify(like)).success(function(){
            console.log("success");
        });
    }

    });