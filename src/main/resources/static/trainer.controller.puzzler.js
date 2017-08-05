angular.module('gasogame24trainer')
.controller('game24puzzler', function($scope, $http) {
    $scope.anotherPuzzle = function( difficultyRank ) {
        $http.get(`http://localhost:8080/rest/v0/puzzle?d=${difficultyRank}`)
            .then( function(response) {
                    $scope.numbers = response.data.puzzle.numbers;
                },
                function(error){
                    $scope.numbers = [0, 0, 0, 0];
                }
            )
    }
    $scope.anotherPuzzle()

    $scope.isSolutionHidden = true

    $scope.highlightDifficulty = function( rank, colorField ) {
        if( $scope.solution[ rank ] == "X" ){
            $scope[ colorField ] = "red"
        }
        else{
            $scope[ colorField ] = "#ecedba"
        }
    }

    $scope.solve = function() {
        var numberParam = $scope.numbers.join( "%20" )
        $scope.solution = "moloading..."
        // if fetching data from a different domain, browser will block
        // request; this is why it only works when this page is run from
        // Spring
        $http.get( 'http://localhost:8080/rest/v0/solution?p=' + numberParam )
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
                    // for testing UI
                    $scope.solution = {"puzzle":" 2  9 11 22","solutionSet":["2 * 22 - (11 + 9)","2 * 22 - 11 - 9","22 * 2 - (11 + 9)","22 * 2 - 11 - 9","2 * 22 - (9 + 11)","22 * 2 - 9 - 11","22 * 2 - (9 + 11)","2 * 22 - 9 - 11"],"algorithm":"Rosetta: brute force","count":8,"hasFinalDiv":"","hasFinalDivTwoByTwo":"","hasFinalMul":"","hasFinalMulTwoByTwo":"","hasFinalAdd":"X","hasFinalAddTwoByTwo":"X","hasFraction":"","difficultyRank":"+"}
                    $scope.highlightDifficulty( "hasFinalMul", "colorFinalMul" )
                    $scope.highlightDifficulty( "hasFinalMulTwoByTwo", "colorFinalMul2" )
                    $scope.highlightDifficulty( "hasFinalAdd", "colorFinalAdd" )
                    $scope.highlightDifficulty( "hasFinalAddTwoByTwo", "colorFinalAdd2" )
                    $scope.highlightDifficulty( "hasFinalDiv", "colorFinalDiv" )
                    $scope.highlightDifficulty( "hasFinalDivTwoByTwo", "colorFinalDiv2" )

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
