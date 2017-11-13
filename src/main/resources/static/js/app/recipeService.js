angular.module('topFoodRecipesApp').service('RecipeService', function($http) {
    this.getRecipes = function() {
        return $http.get('http://localhost:8080/api/recipe').then(function(response){
            return response.data;
    }).catch(function(err) {
            return [];
    });
    }
});