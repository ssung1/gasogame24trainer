function selectColor( flag ){
    if( flag ){
        return "green";
    }
    else{
        return "transparent";
    }
}

angular.module('demo', [])
.controller('Hello', function($scope, $http) {
    $http.get('http://rest-service.guides.spring.io/greeting').
        then(function(response) {
            $scope.greeting = response.data
        });
})
.controller('game24analyzer', function($scope, $http) {
    $http.get('http://localhost:8080/rest/v0/solution?p=1 2 3 4&plumbing=t').
        then(function(response) {
            solution = response.data
            $scope.solution = solution
            $scope.colorZero = selectColor( false )
            $scope.colorDistProp = selectColor( false )
            $scope.colorFinalMul = selectColor( solution.hasFinalMul )
            $scope.colorFinalMul2 = selectColor( solution.hasFinalMulTwoByTwo )
            $scope.colorFinalAdd = selectColor( solution.hasFinalAdd )
            $scope.colorFinalAdd2 = selectColor( solution.hasFinalAddTwoByTwo )
            $scope.colorFinalDiv = selectColor( solution.hasFinalDiv )
            $scope.colorFinalDiv2 = selectColor( solution.hasFinalDivTwoByTwo )
            $scope.colorFraction = selectColor( solution.hasFraction )
        })
})
