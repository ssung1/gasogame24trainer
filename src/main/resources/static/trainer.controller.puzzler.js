angular.module( 'gasogame24trainer' )
.controller( 'game24puzzler', [
    "$scope", "$http", "$interval", "$timeout",
    function( $scope, $http, $interval, $timeout ){

    function round( number, precision ){
        var shift = function( number, precision, reverseShift ){
            if( reverseShift ){
                precision = -precision;
            }
            var numArray = ("" + number).split( "e" );
            return +(numArray[0] + "e" +
                (numArray[1] ? (+numArray[1] + precision) : precision));
        };
        return shift( Math.round( shift( number, precision, false ) ),
            precision, true );
    }

    function PuzzleNumber( number ) {
        this.numberHistory = [number];
        this.used = false;

        this.use = function(){
            this.used = true;
        }
        this.unuse = function(){
            this.used = false;
        }
        this.update = function( n ){
            this.numberHistory.push( n );
        }
        this.clearHistory = function(){
            this.numberHistory = [this.numberHistory[0]];
        }
    }

    const none = 999;

    $scope.isSolutionHidden = true;

    $scope.numbers = [];
    $scope.selectedNumber = none;

    $scope.operators = [ "+", "-", "x", "\xf7" ];
    $scope.selectedOperator = none;

    let timeStart;
    let timer;
    $scope.timeSpent;

    $scope.isTransitionHidden = true;

    $scope.init = function(){
        $scope.anotherPuzzle();
    }

    $scope.setNumbers = function( numbers ) {
        $scope.numbers = [];
        numbers.forEach( function( n ) {
            const nn = new PuzzleNumber( n );
            $scope.numbers.push( nn );
        } );
    }

    $scope.anotherPuzzle = function( difficultyRank ) {
        if( difficultyRank ){
            $http.get( `/rest/v0/puzzle?d=${difficultyRank}` )
                .then( function( response ){
                        $scope.setNumbers( response.data.puzzle.numbers );
                    },
                    function( error ){
                        $scope.setNumbers( [2, 22, 11, 9] );
                    }
                )
        }
        else{
            $http.get( `/rest/v0/puzzle` )
                .then( function( response ){
                        $scope.setNumbers( response.data.puzzle.numbers );
                    },
                    function( error ){
                        $scope.setNumbers( [2, 22, 11, 9] );
                    }
                )
        }
        $scope.startTimer();
    }

    $scope.numberClass = function( position ){
        const number = $scope.numberAt( position );
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

    $scope.highlightDifficulty = function( rank, colorField ) {
        if( $scope.solution[ rank ] == "X" ){
            $scope[ colorField ] = "red"
        }
        else{
            $scope[ colorField ] = "#ecedba"
        }
    }

    $scope.solve = function() {
        var numberParam = $scope.numbers.map(n => n.numberHistory[0]).join( "%20" )
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

    $scope.answerGrid = [];
    $scope.selectNumber = function( n ){
        if( $scope.selectedOperator === none ){
            if( !$scope.numbers[n].used ){
                $scope.selectedNumber = n;
            }
        }
        else{
            if( $scope.selectedNumber !== n ){
                const prevNumber = $scope.numberAt( $scope.selectedNumber );
                const currNumber = $scope.numberAt( n );

                // done with one operation, so clear operator
                // calculate result of this operation
                let result;
                if( $scope.selectedOperator === 0 ){
                    result = prevNumber + currNumber;
                }
                else if( $scope.selectedOperator === 1 ){
                    result = prevNumber - currNumber;
                }
                else if( $scope.selectedOperator === 2 ){
                    result = prevNumber * currNumber;
                }
                else if( $scope.selectedOperator === 3 ){
                    result = prevNumber / currNumber;
                }

                // set current number position to result
                $scope.numbers[n].update( result );
                // and clear the other number
                //$scope.setNumber( $scope.selectedNumber, "\xa0" );
                $scope.numbers[$scope.selectedNumber].use();

                $scope.answerGrid.push( prevNumber );
                $scope.answerGrid.push( $scope.operators[$scope.selectedOperator] );
                $scope.answerGrid.push( currNumber );
                $scope.answerGrid.push( result );

                $scope.selectedNumber = n;
                $scope.selectedOperator = none;

                if( $scope.answerGrid.length == 12 && result === 24 ){
                    $scope.stopTimer();
                    $scope.showTransition();
                }
            }
        }
    }

    $scope.selectOperator = function( n ){
        if( $scope.selectedNumber === none ){
            return;
        }
        if( $scope.selectedOperator !== n ){
            $scope.selectedOperator = n;
        }
    }

    $scope.equation = function( n ){
        let result = "";
        for( let i = 0; i < 5; i = i + 1 ){
            if( $scope.answerGrid[i] ){
                if( i > 0 ){
                    result += "\xa0";
                }
                result += $scope.answerGrid[i];
            }
        }
        return result;
    }

    $scope.answerGridValue = function( eq, pos ){
        const index = eq * 4 + pos;
        if( $scope.answerGrid.length < index + 1 ){
            return "\xa0";
        }
        else if( isNaN( $scope.answerGrid[index] ) ){
            return $scope.answerGrid[index];
        }
        else{
            return round( $scope.answerGrid[index], 2 );
        }
    }

    $scope.numberAt = function( p ){
        if( !$scope.numbers ){
            return "\xa0";
        }
        if( $scope.numbers.length < p + 1 ){
            return "\xa0";
        }
        if( $scope.numbers[p].used ){
            return "\xa0";
        }

        // will fail if there is no number, but that should not happen
        return round( $scope.numbers[p].numberHistory.slice( -1 )[0], 2 );
    }

    $scope.selectBackspace = function(){
        for( let i = 0; i < $scope.numbers.length; i = i + 1 ){
            $scope.numbers[i].unuse();
            $scope.numbers[i].clearHistory();
        }

        $scope.answerGrid = [];
        $scope.selectedNumber = none;
        $scope.selectedOperator = none;
    }

    $scope.startTimer = function(){
        if( timer ){
            $interval.cancel( timer );
            timer = undefined;
        }
        timeStart = Date.now();
        timer = $interval( function(){
            const spent = timeSpent = Date.now() - timeStart;
            $scope.timeSpent = Math.round( spent / 1000 );
        }, 1000 );
    }

    $scope.stopTimer = function(){
        $interval.cancel( timer );
    }

    $scope.showTransition = function() {
        $scope.isTransitionHidden = false;
        $timeout( function(){
            $scope.isTransitionHidden = true;
        }, 1000 );
    }

    $scope.$on( "$destroy", function(){
        $scope.stopTimer
    });

    $scope.init();
} ] );
