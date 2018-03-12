package name.subroutine.game24trainer.puzzle;

import java.util.*;
import java.util.stream.Collectors;

public class Solution
{
    /**
     * postfix expression of the solution
     */
    public Symbol[] expression;

    public Operator getFinalOp()
    {
        return (Operator)expression[expression.length - 1];
    }

    /**
     * for now, this value is calculated by the solver
     */
    public int fraction = Puzzle.UNKNOWN;

    /**
     * the cost of arriving at this solution
     */
    public int cost = 0;

    public void setExpression( Symbol[] expression )
    {
        this.expression = expression;
    }

    public Symbol[] getExpression()
    {
        return this.expression;
    }

    public void setFraction( int fraction )
    {
        this.fraction = fraction;
    }

    public void setCost( int cost )
    {
        this.cost = cost;
    }

    public int getCost()
    {
        return this.cost;
    }

    public String toPostfixString()
    {
        StringBuilder sb = new StringBuilder();
        for( int i = 0; i < expression.length; ++i ) {
            if( i > 0 ) {
                sb.append( ' ' );
            }
            Symbol s = expression[i];
            if( s instanceof Number ) {
                Number n = (Number)s;
                String nVal = String.valueOf( Math.round( n.getValue() ) );
                sb.append( nVal );
            }
            else if( s instanceof Operator ) {
                Operator o = (Operator)s;
                sb.append( o.getValue() );
            }
        }
        return sb.toString();
    }

    public String toInfixString()
    {
        class Ex
        {
            Operator op;
            String ex;
            int prec = 3;

            Ex( String e )
            {
                ex = e;
            }

            Ex( String e1, String e2, Operator o )
            {
                String opVal = String.valueOf( o.getValue() );
                ex = String.format( "%s %s %s", e1, opVal, e2 );
                op = o;
                prec = o.getPrec();
            }
        }

        Stack<Ex> expr = new Stack<>();

        for (Symbol s : expression) {
            if( s instanceof Operator ){
                Operator op = (Operator)s;

                Ex r = expr.pop();
                Ex l = expr.pop();

                int opPrec = op.getPrec();

                if( l.prec < opPrec ){
                    l.ex = '(' + l.ex + ')';
                }

                if( r.prec <= opPrec ) {
                    r.ex = '(' + r.ex + ')';
                }

                expr.push( new Ex( l.ex, r.ex, op ) );
            } else {
                Number n = (Number)s;
                String nVal = String.valueOf( Math.round( n.getValue() ) );
                expr.push( new Ex( nVal ) );
            }
        }
        return expr.peek().ex;
    }

    public List<Symbol> getNumberList()
    {
        return Arrays.asList( expression ).stream()
            .filter( s -> s instanceof Number )
            .collect( Collectors.toList() );
    }

    public Map<Symbol, Integer> getFrequencyMap()
    {
        Map<Symbol, Integer> result = new HashMap<>( 4 );
        getNumberList().forEach( n -> {
            Integer count = result.get( n );
            if( count == null ) {
                result.put( n, 1 );
            }
            else {
                result.put( n, count + 1 );
            }
        } );
        return result;
    }

    /**
     * the only solution must be in the form of (a ? b) ? (c ? d)
     */
    public boolean isTwoByTwo()
    {
        if( expression.length != 7 ) return false;
        // n n o n n o o
        if( !(expression[0] instanceof Number) ) return false;
        if( !(expression[1] instanceof Number) ) return false;
        if( !(expression[2] instanceof Operator) ) return false;
        if( !(expression[3] instanceof Number) ) return false;
        if( !(expression[4] instanceof Number) ) return false;
        if( !(expression[5] instanceof Operator) ) return false;
        if( !(expression[6] instanceof Operator) ) return false;
        return true;
    }

    /**
     * the final operation is division, not two-by-two
     */
    public boolean isFinalDiv()
    {
        return !isTwoByTwo() && getFinalOp().equals( Operator.DIV );
    }

    /**
     * the final operation is division, and the solution is two-by-two
     */
    public boolean isFinalDivTwoByTwo()
    {
        return isTwoByTwo() && getFinalOp().equals( Operator.DIV );
    }

    /**
     * the last operation is multiplication
     */
    public boolean isFinalMul()
    {
        return !isTwoByTwo() && getFinalOp().equals( Operator.MUL );
    }

    /**
     * final operation is multiplication
     * solution is a two-by-two
     */
    public boolean isFinalMulTwoByTwo()
    {
        return isTwoByTwo() && getFinalOp().equals( Operator.MUL );
    }

    /**
     * final operation is addition or subtraction
     * @return
     */
    public boolean isFinalAdd()
    {
        boolean addSub = getFinalOp().equals( Operator.ADD )
                      || getFinalOp().equals( Operator.SUB );

        return !isTwoByTwo() && addSub;
    }

    /**
     * final operation is addition or subtraction
     * @return
     */
    public boolean isFinalAddTwoByTwo()
    {
        boolean addSub = getFinalOp().equals( Operator.ADD )
                      || getFinalOp().equals( Operator.SUB );
        return isTwoByTwo() && addSub;
    }

    /**
     * fraction is required as an intermediate
     * @return
     */
    public int isFraction()
    {
        return this.fraction;
    }

    /**
     * can be solved using the zero trick
     * @return
     */
    public boolean isZeroTrick()
    {
        return isZeroTrickImpl1();
    }

    public boolean isZeroTrickImpl0()
    {
        // this way is not as good because it returns true if the puzzle
        // can be solved with the zero trick even if the solution is not
        // using the zero trick

        // must have at least 2 of the same number
        // must have a 24
        Map<Symbol, Integer> freqMap = getFrequencyMap();
        if( freqMap.get( new Number( 24 ) ) > 2 ){
            return true;
        }
        if( freqMap.get( new Number( 24 ) ) > 0 &&
                freqMap.size() < 4 ) {
            return true;
        }
        return false;
    }

    public boolean isZeroTrickImpl1()
    {
        // 0 24 + or 24 0 +

        // b 0 * 24 + or 0 b * 24 +
        // 24 b 0 * + or 24 0 b * +

        // b a a - * 24 + or a a - b * 24 +
        // 24 b a a - * + or 24 a a - b * +

        if( expression[1].equals( expression[2] )
        &&  expression[3].equals( Operator.SUB )
        &&  expression[4].equals( Operator.MUL )
        &&  expression[5].equals( 24 )
        &&  expression[6].equals( Operator.ADD ) ) {
            return true;
        }

        if( expression[0].equals( expression[1] )
        &&  expression[2].equals( Operator.SUB )
        &&  expression[4].equals( Operator.MUL )
        &&  expression[5].equals( 24 )
        &&  expression[6].equals( Operator.ADD ) ) {
            return true;
        }

        if( expression[0].equals( 24 )
        &&  expression[2].equals( expression[3] )
        &&  expression[4].equals( Operator.SUB )
        &&  expression[5].equals( Operator.MUL )
        &&  expression[6].equals( Operator.ADD ) ) {
            return true;
        }

        if( expression[0].equals( 24 )
        &&  expression[1].equals( expression[2] )
        &&  expression[3].equals( Operator.SUB )
        &&  expression[5].equals( Operator.MUL )
        &&  expression[6].equals( Operator.ADD ) ) {
            return true;
        }
        return false;
    }

    public boolean isDistProp()
    {
        return false;
    }

    public boolean isAlmostDistProp()
    {
        return false;
    }

    public boolean isAddSub()
    {
        return false;
    }

    @Override
    public boolean equals( Object o )
    {
        if( !(o instanceof Solution) ) return false;
        Solution s = (Solution)o;
        return this.toInfixString().equals( s.toInfixString() );
    }

    @Override
    public int hashCode()
    {
        return toInfixString().hashCode();
    }

}
