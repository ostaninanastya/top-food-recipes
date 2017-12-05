controllerModule.controller('LogoutController', function($scope, $rootScope, $location, LoginService) {

    $scope.$on('$routeChangeSuccess', function () {
        $rootScope.user = undefined;
        $rootScope.$broadcast('reload_page_event');
        $rootScope.isLog = false;
    });

});