servicesModule.service('EditRecipeService', function($http,$rootScope,SERVER_URL) {

    this.updateRecipe = function(selectedRecipe){
        $http.put(SERVER_URL + '/api/recipe', JSON.stringify(selectedRecipe));
    }

    this.getIngredientRecipe= function(selectedRecipe, callback){
        $http.post(SERVER_URL + '/api/ingredientRecipe/getByRecipe', JSON.stringify(selectedRecipe))
            .success(function(response){
                callback(response);
            });
    }
    this.updateIngredientRecipe = function(ingredientRecipeArray){
        $http.put(SERVER_URL + '/api/ingredientRecipe', JSON.stringify(ingredientRecipeArray));
    }

    this.deleteIngredientRecipe = function(ingredientRecipeDeleteArray){
        $http.put(SERVER_URL + '/api/ingredientRecipe/delete', JSON.stringify(ingredientRecipeDeleteArray));
    }

});