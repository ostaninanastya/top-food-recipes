servicesModule.service('RecipeEditService', function($http,$rootScope,SERVER_URL) {
    this.editRecipe = function(selectedRecipe) {
        console.log("posting data....");
        return $http.put(SERVER_URL + 'api/recipe', JSON.stringify(selectedRecipe)).success(function () {
            console.log("success");
        });
    }
});