controllerModule.controller('RegisterController', function($scope, $rootScope, $location, LoginService) {

    $scope.register = function() {
        console.log('Register user, name=' + $scope.user.name + ', password=' + $scope.user.password);

        LoginService.registerUser($scope.user, function(response) {
            if (response.success) {
                console.log('Success');
                $rootScope.$broadcast('registration_event', $scope.user);
            } else {
                console.log('Fail');
            }
        });

    }
});