package name.subroutine.game24trainer;

import java.util.Stack;

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

    public int isFraction()
    {
        return this.fraction;
    }

    public boolean isZeroTrick()
    {
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
