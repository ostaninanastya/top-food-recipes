angular.module('topFoodRecipesApp').service('RecipeService', function($http) {
    this.getRecipes = function() {
        return $http.get('http://localhost:8080/top_food_recipes-0.0.1-SNAPSHOT/api/recipe').then(function(response){
            return response.data;
    }).catch(function(err) {
            return [];
    });
    }
});
