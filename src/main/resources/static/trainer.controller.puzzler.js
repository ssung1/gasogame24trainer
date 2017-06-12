angular.module('gasogame24trainer')
.controller('game24puzzler', function($scope, $http) {
    $scope.anotherPuzzle = function() {
        this.numbers = []
        for (i = 0; i < 4; i++) {
            this.numbers[i] = Math.round( Math.random() * 24 ) + 1
        }
    }
    $scope.anotherPuzzle()
})
