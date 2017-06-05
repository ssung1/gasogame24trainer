angular.module('gasogame24trainer', [])
.controller('modal', function($scope, $http) {
    $scope.isHidden = true
    $scope.test = "test"
    $scope.show = function() {
        this.isHidden = false
    }
    $scope.hide = function() {
        this.isHidden = true
    }
});
angular.module('gasogame24trainer', [])
.controller('game24puzzler', function($scope, $http) {
})

