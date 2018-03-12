package name.subroutine.game24trainer.solverimpl;

import name.subroutine.game24trainer.*;
import name.subroutine.game24trainer.puzzle.*;
import name.subroutine.game24trainer.puzzle.Number;

import java.util.*;

/**
 * Taken from rosettacode.org
 */
public class Game24SolverImplRosetta implements Game24Solver {
    final String[] patterns = { "nnonnoo", "nnonono", "nnnoono", "nnnonoo",
        "nnnnooo" };

    class EvalResult
    {
        public boolean is24;
        public boolean hasFraction;
    }

    public void permute( List<Integer> lst, List<List<Integer>> res, int k )
    {
        for( int i = k; i < lst.size(); i++ ) {
            Collections.swap( lst, i, k );
            permute( lst, res, k + 1 );
            Collections.swap( lst, k, i );
        }
        if( k == lst.size() ) {
            res.add( new ArrayList<>( lst ) );
        }
    }

    void permuteOperators( List<List<Integer>> res, int n )
    {
        int total = n * n * n; // because the list has 3 items
        for( int i = 0, npow = n * n; i < total; i++ ) {
            res.add( Arrays.asList( ( i / npow ), ( i % npow ) / n, i % n ) );
        }
    }

    EvalResult evaluate( Symbol[] line ) throws Exception {
        EvalResult result = new EvalResult();
        Stack<Float> s = new Stack<>();
        try {
            for( Symbol sym : line ){
                if( sym instanceof Number ) {
                    Number num = (Number)sym;
                    s.push( num.getValue() );
                }
                else{
                    Operator op = (Operator)sym;
                    float ans = applyOperator( s.pop(), s.pop(),
                        op.getValue() );

                    if( Math.abs( Math.round( ans ) - ans ) > .001f ) {
                        result.hasFraction = true;
                    }

                    s.push( ans );
                }
            }
        } catch (EmptyStackException e) {
            throw new Exception("Invalid entry.");
        }
        result.is24 = (Math.abs(24 - s.peek()) < 0.001f);
        return result;
    }

    public float applyOperator( float a, float b, char c )
    {
        switch( c ){
            case '+':
                return a + b;
            case '-':
                return b - a;
            case '*':
                return a * b;
            case '/':
                return b / a;
            default:
                return Float.NaN;
        }
    }

    @Override
    public SolutionSet solve( Puzzle puzzle )
    {
        SolutionSet result = new SolutionSet();
        result.setPuzzle( puzzle );
        result.setAlgorithm( "Rosetta: brute force" );

        List<Integer> numbers = puzzle.getNumbers();
        Operator[] ops = {
            Operator.ADD, Operator.SUB, Operator.MUL, Operator.DIV
        };
        List<List<Integer>> dPerms = new ArrayList<>( 4 * 3 * 2 );
        permute( numbers, dPerms, 0 );

        int total = 4 * 4 * 4;
        List<List<Integer>> oPerms = new ArrayList<>( total );
        permuteOperators( oPerms, 4 );

        int cost = 0;
        for( String pattern : patterns ){
            // "nnonnoo" is the two-by-two pattern
            char[] patternChars = pattern.toCharArray();

            for( List<Integer> dig : dPerms ){
                for( List<Integer> opr : oPerms ){
                    ArrayList<Symbol> sb = new ArrayList<>( 4 + 3 );
                    int i = 0, j = 0;
                    for( char c : patternChars ){
                        if( c == 'n' ) {
                            sb.add( new Number( dig.get( i++ ) ) );
                        }
                        else{
                            sb.add( ops[opr.get( j++ )] );
                        }
                    }

                    Symbol[] candidate = sb.toArray( new Symbol[sb.size()] );
                    try {
                        EvalResult retval = evaluate( candidate );
                        // 3 arithmetic operations, although some are more
                        // costly than others
                        cost += 3;
                        if( retval.is24 ){
                            Solution solution = new Solution();
                            solution.cost = cost;
                            solution.expression = candidate;
                            if( retval.hasFraction ) {
                                solution.fraction = Puzzle.YES;
                            }
                            else {
                                solution.fraction = Puzzle.NO;
                            }
                            result.add( solution );
                        }
                    }
                    catch (Exception ignored) {
                    }
                }
            }
        }
        return result;
    }
}
