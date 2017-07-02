angular.module('gasogame24trainer')
.controller('game24puzzler', function($scope, $http) {
    $scope.anotherPuzzle = function() {
        $scope.numbers = []
        for (i = 0; i < 4; i++) {
            $scope.numbers[i] = Math.round( Math.random() * 24 ) + 1
        }
    }
    $scope.anotherPuzzle()

    $scope.isSolutionHidden = true

    $scope.solve = function() {
        var numberParam = $scope.numbers.join( "%20" )
        $scope.solution = "moloading..."
        // if fetching data from a different domain, browser will block
        // request; this is why it only works when this page is run from
        // Spring
        $http.get( '/rest/v0/solution?p=' + numberParam )
            .then(
                function( response ) {
                    $scope.solution = response.data
                    if( $scope.solution.hasFinalMul == "X" ){
                        $scope.colorFinalMul = "red"
                    }
                    else{
                        $scope.colorFinalMul = "#ecedba"
                    }

                    if( $scope.solution.hasFinalMulTwoByTwo == "X" ){
                        $scope.colorFinalMul2 = "red"
                    }
                    else{
                        $scope.colorFinalMul2 = "#ecedba"
                    }

                    if( $scope.solution.hasFinalAdd == "X" ){
                        $scope.colorFinalAdd = "red"
                    }
                    else{
                        $scope.colorFinalAdd = "#ecedba"
                    }

                    if( $scope.solution.hasFinalAddTwoByTwo == "X" ){
                        $scope.colorFinalAdd2 = "red"
                    }
                    else{
                        $scope.colorFinalAdd2 = "#ecedba"
                    }

                    if( $scope.solution.hasFinalDiv == "X" ){
                        $scope.colorFinalDiv = "red"
                    }
                    else{
                        $scope.colorFinalDiv = "#ecedba"
                    }

                    if( $scope.solution.hasFinalDivTwoByTwo == "X" ){
                        $scope.colorFinalDiv2 = "red"
                    }
                    else{
                        $scope.colorFinalDiv2 = "#ecedba"
                    }
                },
                function( error ) {
                    $scope.solution = error
                    console.log( "fail", error )
                }
            )
    }

    $scope.showSolution = function() {
        $scope.solve()
        $scope.isSolutionHidden = false
    }
    $scope.hideSolution = function() {
        $scope.isSolutionHidden = true
    }
})
