servicesModule.service('RecipeService', function($http,$rootScope,SERVER_URL) {
    this.getRecipes = function() {
        return $http.get(SERVER_URL+'api/recipe').then(function(response){
            return response.data;
        }).catch(function(err) {
            return [{"name" : "рецепт пиццы", "recipe" : "dhduhdishdisj"}];
        });
    }

    this.getRecipe = function(id) {
        return $http({method: 'GET', url: SERVER_URL + 'api/recipe/', params: {id: id}}).then(function(response){
            return response.data;
        });
    }

    this.logResponse = function(data, status, headers, config) {
      	console.log('SUCCESS');
       	console.log('data: ' + data);
       	console.log('status: ' + status);
       	console.log('headers: ' + headers);
    }

    this.uploadRecipeAndPicture = function(recipe, file) {
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
            logResponse(data, status, headers, config);
        }).error(function (data, status, headers, config) {
        	console.log('ERROR');
            logResponse(data, status, headers, config);
            resp = {success: false, error: data};
        });
   
        return resp;
    }

    this.uploadRecipeOnly = function(recipe) {
        var resp = {success: true, recipe: recipe};

        $http.post(SERVER_URL + 'api/recipe/withoutpicture', JSON.stringify(recipe)
        ).success(function (data, status, headers, config) {
        	console.log('SUCCESS');
            logResponse(data, status, headers, config);
        }).error(function (data, status, headers, config) {
        	console.log('ERROR');
            logResponse(data, status, headers, config);
            resp = {success: false, error: data};
        });
   
        return resp;
    }

    this.addNewRecipe = function(recipe, f, callback) {
        recipe.user = $rootScope.user;
        var resp = {};

        if (f == undefined) {
            resp = this.uploadRecipeOnly(recipe);
            callback(resp);
        } else {
            resp = this.uploadRecipeAndPicture(recipe, f);
            callback(resp);
        }
    }
    this.getIngredients = function() {
        return $http.get(SERVER_URL+'api/ingredient').then(function(response){
            return response.data;
        }).catch(function(err) {
            return [];
        });
    }
});
