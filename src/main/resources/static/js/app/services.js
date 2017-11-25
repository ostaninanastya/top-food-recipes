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
servicesModule.service('MeasureService', function($http, SERVER_URL) {
    this.getMeasures = function () {
        return $http.get(SERVER_URL+'api/measure').then(function (response) {
            return response.data;
        }).catch(function (err) {
            return [];
        });
    }
});

servicesModule.service('LoginService', function($http, $rootScope, SERVER_URL) {
    var allUsers = [];

    this.loginUser = function(user, callback) {
        console.log('Login user, name=' + user.name + ', password=' + user.password);
        var resp = { success: false, message: 'Username or password is incorrect' };

        $http.get(SERVER_URL + 'api/users').then(function(response) {
            allUsers = response.data;
            console.log('Found ' + allUsers.length + ' users');

            for (var i in allUsers) {
                var usr = allUsers[i];
                console.log('Name: ' + usr.name + ', password: ' + usr.password);

                if (usr.name == user.name && usr.password == user.password) {
                    console.log("User matches!!!");
                    $rootScope.user = user;
                    resp = { success: true };
                }
            }
    
            callback(resp);
        }).catch(function(err) {
            console.log('Error occurred: ' + err.statusText);
            callback(resp);
        });
        
    }

    this.getMenu = function() {
        var menu = [
            {
                title: 'Recipes',
                url: '#/recipes',
                f: 'a()'
            },
            {
                title: 'Cuisines',
                url: '#/cuisines',
                f: 'a()'
            },
            {
                title: 'Ingredients',
                url: '#/ingredients',
                f: 'a()'
            },
            {
                title: 'About',
                url: '#/about',
                f: 'a()'
            }
        ];

        if ($rootScope.user != undefined) {
            menu.push({title: 'Logout', url: '#/logout', f: 'logout()'});
        } else {
            menu.push({title: 'Login', url: '#/login', f: ''});
        }

       return menu;
    }


    this.registerUser = function(user, callback) {
        console.log('Register user, name=' + user.name + ', password=' + user.password);
        var resp = { success: false, message: 'Failed to register a user' };

        $http.post(SERVER_URL + 'api/users', JSON.stringify(user)).then(function (response) {
            console.log('Successfully registered user ' + user.name);
            resp = { success: true };
            callback(resp);
        }).catch(function (err) {
            console.log("Failed to register user " + user.name + ": " + err.statusText);
            callback(resp);
        });
    }



});

