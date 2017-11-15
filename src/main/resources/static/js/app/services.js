angular.module('topFoodRecipesApp')
    .service('RecipeService', function($http,SERVER_URL) {

        this.getRecipes = function() {
            return $http.get(SERVER_URL+'api/recipe').then(function(response){
                return response.data;
            }).catch(function(err) {
                return [{"name" : "рецепт пиццы", "recipe" : "dhduhdishdisj"}];
            });
        }
    })
    .service('CuisineService', function($http, SERVER_URL) {
        this.getCuisines = function() {
            return $http.get(SERVER_URL+'api/cuisine').then(function(response){
                return response.data;
            }).catch(function(err) {
                return [];
            });
        }
        this.addNewCuisine = function(cuisine) {
            console.log("posting data....");
            return $http.post(SERVER_URL+'api/cuisine', JSON.stringify(cuisine)).success(function(){
                console.log("success");
            });
        }
    })
;
