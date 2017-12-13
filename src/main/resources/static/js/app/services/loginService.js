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

                if (usr.name === user.name && usr.password === user.password) {
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
                title: 'Top Food Recipes',
                url: '#/recipes',
                f: 'a()'
            },
            {
                title: 'RECIPES',
                url: '#/recipes',
                f: 'a()'
            },
            {
                title: 'ADVANCED SEARCH',
                url: '#/advancedSearch',
                f: 'a()'
            }
        ];

        if ($rootScope.user !== undefined) {
            menu.push({title: 'Create Ingredient', url: '#/createIngredient', f: ''});
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
        }).catch(function (response) {
            console.log("Failed to register user " + user.name + ": " + response.data);
            resp = {success: false, errorMessage: response.data};
            callback(resp);
        });
    }



});

