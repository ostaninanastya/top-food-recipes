servicesModule.service('RecipeService', function($http,$rootScope,SERVER_URL) {
    this.getRecipes = function() {
        return $http.get(SERVER_URL+'api/recipe').then(function(response){
            return response.data;
        }).catch(function(err) {
            return [{"name" : "рецепт пиццы", "recipe" : "dhduhdishdisj"}];
        });
    }

    this.getRecipe = function(id) {
        $http.get(SERVER_URL + 'api/recipe/' + id).then(function(response){
            return response.data;
        });
    }

    this.deleteRecipe = function(recipe, callback) {
        $http.delete(SERVER_URL+'api/recipe/' + recipe.recipe_id)
        .then(function(response) {
            callback();
        })
        .catch(function(err) {
            console.log('Failed to delete recipe: ' + err.data);
        });
    }

    this.logResponse = function(data, status, headers, config) {
      	console.log('SUCCESS');
       	console.log('data: ' + data);
       	console.log('status: ' + status);
       	console.log('headers: ' + headers);
    }

    this.uploadRecipeAndPicture = function(recipe, file, callback) {
        var resp = {success: true, recipe: recipe};

        $http({
            method: 'POST',
            url: SERVER_URL + 'api/recipe',
            headers: {'Content-Type': undefined },
            transformRequest: function (data) {
                var formData = new FormData();

                formData.append('recipe', new Blob([angular.toJson(data.recipe)], {
                    type: "application/json"
                }));

                formData.append("file", data.file);
                return formData;
            },
            data: { recipe: recipe, file: file }

        }).success(function (data, status, headers, config) {
        	console.log('SUCCESS');
            resp.recipe = data;
            callback(resp);
        }).error(function (data, status, headers, config) {
        	console.log('ERROR');
            resp = {success: false, error: data};
            callback(resp);
        });
    }

    this.uploadRecipeOnly = function(recipe, callback) {
        var resp = {success: true, recipe: recipe};

        $http.post(SERVER_URL + 'api/recipe/withoutpicture', JSON.stringify(recipe)
        ).success(function (data, status, headers, config) {
        	console.log('SUCCESS');
            resp.recipe = data;
            callback(resp);
        }).error(function (data, status, headers, config) {
        	console.log('ERROR');
            resp = {success: false, error: data};
            callback(resp);
        });
   
    }

    this.addNewRecipe = function(recipe, f, callback) {

        recipe.user = $rootScope.user;
        var resp = {};

        if (f === undefined) {
            this.uploadRecipeOnly(recipe, callback);
        } else {
            this.uploadRecipeAndPicture(recipe, f, callback);
        }
    }

    this.updateRecipe = function(selectedRecipe){
        $http.put(SERVER_URL + '/api/recipe', JSON.stringify(selectedRecipe));
    }

    this.getIngredients = function() {
        return $http.get(SERVER_URL+'api/ingredient').then(function(response){
            return response.data;
        }).catch(function(err) {
            return [];
        });
    }
    this.addNewIngredientRecipe = function (ir) {
        $http.post(SERVER_URL + '/api/ingredientRecipe', JSON.stringify(ir));
    }

});
