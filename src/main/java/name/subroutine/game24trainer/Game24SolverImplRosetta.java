package name.subroutine.game24trainer;

import java.util.*;

/**
 * Taken from rosettacode.org
 */
public class Game24SolverImplRosetta
{
    final String[] patterns = { "nnonnoo", "nnonono", "nnnoono", "nnnonoo",
        "nnnnooo" };

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

    boolean equals24( Symbol[] line ) throws Exception {
        Stack<Float> s = new Stack<>();
        try {
            for( Symbol sym : line ){
                if( sym instanceof Number ) {
                    Number num = (Number)sym;
                    s.push( num.getValue() );
                }
                else{
                    Operator op = (Operator)sym;
                    s.push( applyOperator( s.pop(), s.pop(), op.getValue() ) );
                }
            }
        } catch (EmptyStackException e) {
            throw new Exception("Invalid entry.");
        }
        return (Math.abs(24 - s.peek()) < 0.001F);
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

    public boolean solve( Puzzle puzzle )
    {
        List<Integer> numbers = puzzle.getNumbers();
        Operator[] ops = {
            Operator.ADD, Operator.SUB, Operator.MUL, Operator.DIV
        };
        List<List<Integer>> dPerms = new ArrayList<>( 4 * 3 * 2 );
        permute( numbers, dPerms, 0 );

        int total = 4 * 4 * 4;
        List<List<Integer>> oPerms = new ArrayList<>( total );
        permuteOperators( oPerms, 4 );

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
                        if( equals24( candidate ) ){
                            String solution = postfixToInfix( candidate );
                            System.out.println( solution );
                            //return true;
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        return false;
    }

    public String postfixToInfix( Symbol[] symbolList )
    {
        class Expression {
            Operator op;
            String ex;
            int prec = 3;

            Expression( String e )
            {
                ex = e;
            }

            Expression( String e1, String e2, Operator o )
            {
                String opVal = String.valueOf( o.getValue() );
                ex = String.format( "%s %s %s", e1, opVal, e2 );
                op = o;
                prec = o.getPrec();
            }
        }

        Stack<Expression> expr = new Stack<>();

        for (Symbol s : symbolList) {
            if( s instanceof Operator ){
                Operator op = (Operator)s;

                Expression r = expr.pop();
                Expression l = expr.pop();

                int opPrec = op.getPrec();

                if( l.prec < opPrec ){
                    l.ex = '(' + l.ex + ')';
                }

                if( r.prec <= opPrec ) {
                    r.ex = '(' + r.ex + ')';
                }

                expr.push( new Expression( l.ex, r.ex, op ) );
            } else {
                Number n = (Number)s;
                String nVal = String.valueOf( Math.round( n.getValue() ) );
                expr.push( new Expression( nVal ) );
            }
        }
        return expr.peek().ex;
    }
}
