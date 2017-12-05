servicesModule.service('RecipeService', function($http,$rootScope,SERVER_URL) {
    this.getRecipes = function() {
        return $http.get(SERVER_URL+'api/recipe').then(function(response){
            return response.data;
        }).catch(function(err) {
            return [{"name" : "рецепт пиццы", "recipe" : "dhduhdishdisj"}];
        });
    }
    this.addNewRecipe = function(recipe, f) {
        recipe.user = $rootScope.user;
        console.log(recipe);
        console.log("posting data....");
        return $http.post(SERVER_URL + 'api/recipe', JSON.stringify(recipe, f)).success(function () {
            console.log("success");
        });
    }
});
