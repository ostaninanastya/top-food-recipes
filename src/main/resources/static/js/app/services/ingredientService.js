
servicesModule.service('IngredientService', function($http, SERVER_URL) {
    this.getIngredients = function() {
        return $http.get(SERVER_URL+'api/ingredient').then(function(response){
            return response.data;
        }).catch(function(err) {
            return [];
        });
    }
    this.addNewIngredient = function(ingredient) {
        //ingredient.measure = measure;
        console.log("posting data....");
        return $http.post(SERVER_URL+'api/ingredient', JSON.stringify(ingredient)).success(function(){
            console.log("success");
        });
    }
});