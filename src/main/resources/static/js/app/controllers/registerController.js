controllerModule.controller('RegisterController', function($scope, $rootScope, $location, LoginService) {

    $scope.register = function() {
        console.log('Register user, name=' + $scope.user.name + ', password=' + $scope.user.password);

        LoginService.registerUser($scope.user, function(response) {
            if (response.success) {
                console.log('Success');
                $rootScope.$broadcast('registration_event', $scope.user);
                $rootScope.isLog = true;
            } else {
                console.log('Fail: '+ response.errorMessage);
                $scope.exists = response.errorMessage;
                console.log($scope.exists);
            }
        });

    }
});