angular.module('topFoodRecipesApp')
    .service('RecipeService', function($http) {

        this.getRecipes = function() {

            //return $http.get('http://localhost:8080/top_food_recipes-0.0.1-SNAPSHOT/api/recipe').then(function(response){

            return $http.get('http://localhost:8080/top_food_recipes-0.0.1-SNAPSHOT/api/recipe').then(function(response){
                return response.data;
            }).catch(function(err) {
                return [{"name" : "рецепт пиццы", "recipe" : "dhduhdishdisj"}];
            });
        }
    })
    .service('CuisineService', function($http) {
        this.getCuisines = function() {
            return $http.get('http://localhost:8080/top_food_recipes-0.0.1-SNAPSHOT/api/cuisine').then(function(response){
                return response.data;
            }).catch(function(err) {
                return [];
            });
        }
        this.addNewCuisine = function(cuisine, $http) {
            console.log("posting data....");
            $http.post('http://localhost:8080/top_food_recipes-0.0.1-SNAPSHOT/api/cuisine', JSON.stringify(cuisine)).success(function(){
                /*success callback*/
                console.log("Success!");
            });
        };
    })
;
