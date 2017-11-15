angular.module('topFoodRecipesApp')
.service('RecipeService', function($http) {
    this.getRecipes = function() {
        return $http.get('http://188.166.30.145:8080/top_food_recipes-0.0.1-SNAPSHOT/api/recipe').then(function(response){
            return response.data;
    }).catch(function(err) {
            return [];
    });
    }
})
.service('CuisineService', function($http) {
    this.getCuisines = function() {
        return $http.get('http://188.166.30.145:8080/top_food_recipes-0.0.1-SNAPSHOT/api/cuisine').then(function(response){
            return response.data;
    }).catch(function(err) {
            return [];
    });
    }
})
    .service('IngredientService', function($http) {
        this.getIngredients = function() {
            return $http.get(SERVER_URL+'api/ingredient').then(function(response){
                return response.data;
            }).catch(function(err) {
                return [];
            });
        }
        this.addNewIngredient = function(ingredient) {
            console.log("posting data....");
            return $http.post(SERVER_URL+'api/ingredient', JSON.stringify(ingredient)).success(function(){
                console.log("success");
            });
        }
    })
;
