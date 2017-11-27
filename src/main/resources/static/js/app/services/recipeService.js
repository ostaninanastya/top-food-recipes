servicesModule.service('RecipeService', function($http,SERVER_URL) {
    this.getRecipes = function() {
        return $http.get(SERVER_URL+'api/recipe').then(function(response){
            return response.data;
        }).catch(function(err) {
            return [{"name" : "рецепт пиццы", "recipe" : "dhduhdishdisj"}];
        });
    }
});
