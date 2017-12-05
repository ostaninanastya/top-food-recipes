controllerModule.controller('LoginController', function($scope, $rootScope, $location, LoginService) {

    $scope.login = function() {
        LoginService.loginUser($scope.user, function(response) {
            if (response.success) {
                console.log('Successfully logged in under ' + $rootScope.user.name);
                $rootScope.$broadcast('reload_page_event');
                $rootScope.isLog = true;
            } else {
                console.log('Login under ' + $scope.user.name + ' failed');
            }
        });
    }

    $scope.$on('registration_event', function(event, data) {
        console.log('YYYYYY');
    });

});