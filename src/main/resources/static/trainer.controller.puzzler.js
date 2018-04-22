angular.module('gasogame24trainer')
.controller('game24puzzler', function($scope, $http) {
    const none = 999;

    $scope.anotherPuzzle = function( difficultyRank ) {
        if( difficultyRank ){
            $http.get( `/rest/v0/puzzle?d=${difficultyRank}` )
                .then( function( response ){
                        $scope.numbers = response.data.puzzle.numbers;
                    },
                    function(error){
                        $scope.numbers = [2, 22, 11, 9];
                    }
                )
        }
        else{
            $http.get( `/rest/v0/puzzle` )
                .then( function( response ){
                        $scope.numbers = response.data.puzzle.numbers;
                    },
                    function(error){
                        $scope.numbers = [2, 22, 11, 9];
                    }
                )
        }
    }

    $scope.anotherPuzzle();
    $scope.operators = [ "+", "-", "x", "&divide;" ];
    $scope.selectedNumber = none;
    $scope.selectedOperator = none;

    $scope.numberClass = function( position ){
        const number = $scope.numbers[position];
        const result = [];
        if( number === 9 ){
            result.push( "nine" );
        }
        if( position == $scope.selectedNumber ){
            result.push( "selected" );
        }
        return result;
    }

    $scope.operatorClass = function( position ){
        const result = [];
        if( position == $scope.selectedOperator ){
            result.push( "selected" );
        }
        return result;
    }

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
        $http.get( `/rest/v0/solution?p=${numberParam}` )
            .then(
                function( response ) {
                    $scope.solution = response.data
                    $scope.highlightDifficulty( "hasFinalMul", "colorFinalMul" )
                    $scope.highlightDifficulty( "hasFinalMulTwoByTwo", "colorFinalMul2" )
                    $scope.highlightDifficulty( "hasFinalAdd", "colorFinalAdd" )
                    $scope.highlightDifficulty( "hasFinalAddTwoByTwo", "colorFinalAdd2" )
                    $scope.highlightDifficulty( "hasFinalDiv", "colorFinalDiv" )
                    $scope.highlightDifficulty( "hasFinalDivTwoByTwo", "colorFinalDiv2" )
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

    $scope.clicked = [];
    $scope.selectNumber = function( n ) {
        if( $scope.selectedOperator === none ){
            if( $scope.selectedNumber !== n ){
                $scope.selectedNumber = n;
                $scope.clicked.push( $scope.numbers[n] );
            }
        }
        else{
            if( $scope.selectedNumber !== n ){
                const prevNumber = $scope.numbers[$scope.selectedNumber];
                const currNumber = $scope.numbers[n];
                if( $scope.selectedOperator === 0 ){
                    // done with one operation, so clear operator
                    $scope.selectedOperator = none;
                    // calculate result of this operation
                    const result = prevNumber + currNumber;
                    // set current number position to result
                    $scope.numbers[n] = result;
                    // and clear the other number
                    $scope.numbers[selectedNumber] = "";

                    //$scope.selectedNumber = n;
                    $scope.clicked.push( currNumber );
                    $scope.clicked.push( "=" );
                    $scope.clicked.push( result );
                }
            }
        }
    }

    $scope.selectOperator = function( n ) {
        if( $scope.selectedNumber === none ){
            return;
        }
        if( $scope.selectedOperator !== n ){
            $scope.selectedOperator = n;
            $scope.clicked.push( $scope.operators[n] );
        }
    }

    $scope.equation = function( n ) {
//        let result = "";
//        for( int i = 0; i < 5; i = i + 1 ){
//            if( $scope.clicked[i] ){
//                if( i > 0 ){
//                    result += " ";
//                }
//                result += $scope.clicked[i];
//            }
//        }
//        return result;
    }
})
