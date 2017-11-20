'use strict';

var servicesModule = angular.module('topFoodRecipeAppServices', []);

servicesModule.service('RecipeService', function($http,SERVER_URL) {
    this.getRecipes = function() {
        return $http.get(SERVER_URL+'api/recipe').then(function(response){
            return response.data;
        }).catch(function(err) {
            return [{"name" : "рецепт пиццы", "recipe" : "dhduhdishdisj"}];
        });
    }
});
    
servicesModule.service('CuisineService', function($http, SERVER_URL) {
    this.getCuisines = function() {
        return $http.get(SERVER_URL+'api/cuisine').then(function(response){
            return response.data;
        }).catch(function(err) {
            return [];
        });
    }
        
    this.addNewCuisine = function(cuisine) {
        console.log("posting data....");
        return $http.post(SERVER_URL+'api/cuisine', JSON.stringify(cuisine)).success(function(){
            console.log("success");
        });
    }
});

servicesModule.service('LoginService', function($http, SERVER_URL) {
    var allUsers = [];
    var resp = { success: false, message: 'Username or password is incorrect' };

    this.loginUser = function(user, callback) {
        console.log('Login user, name=' + user.name + ', password=' + user.password);

        $http.get(SERVER_URL + 'api/users').then(function(response) {
            allUsers = response.data;
            console.log('Found ' + allUsers.length + ' users');

            for (var i in allUsers) {
                var usr = allUsers[i];
                console.log('Name: ' + usr.name + ', password: ' + usr.password);

                if (usr.name == user.name && usr.password == user.password) {
                    console.log("User matches!!!");
                    response = { success: true };
                }
            }
    
            callback(resp);
        }).catch(function(err) {
            console.log('Error occurred: ' + err.statusText);
            callback(resp);
        });
        
    }

    this.registerUser = function(user) {
        console.log('Register user, name=' + user.name + ', password=' + user.password);
        return $http.post(SERVER_URL+'api/cuisine', JSON.stringify(user)).success(function(){
            console.log("success");
        });
    }
});

