controllerModule.controller('MainController', function($scope, $rootScope, $location, LoginService) {
    $rootScope.menu = LoginService.getMenu();

    $rootScope.$on('registration_event', function(event, data) {
        console.log('XXXXX: received data: ' + data);
        LoginService.loginUser(data, function(response) {
            if (response.success) {
                console.log('Successfully logged in under ' + $rootScope.user.name);
                $rootScope.$broadcast('reload_page_event');
            } else {
                console.log('Login under ' + $scope.user.name + ' failed');
            }
        });
    });

    $rootScope.$on('reload_page_event', function(event, data) {
        $rootScope.menu = LoginService.getMenu();
        $location.path('/');
    });

});