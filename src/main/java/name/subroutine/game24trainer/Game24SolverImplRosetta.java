package name.subroutine.game24trainer;

import java.util.*;

/**
 * Taken from rosettacode.org
 */
public class Game24SolverImplRosetta
{
    final String[] patterns = { "nnonnoo", "nnonono", "nnnoono", "nnnonoo",
        "nnnnooo" };
    final String ops = "+-*/";

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

//    boolean isSolvable(List<Integer> digits) {
//        List<List<Integer>> dPerms = new ArrayList<>(4 * 3 * 2);
//        permute(digits, dPerms, 0);
//
//        int total = 4 * 4 * 4;
//        List<List<Integer>> oPerms = new ArrayList<>(total);
//        permuteOperators(oPerms, 4);
//
//        StringBuilder sb = new StringBuilder(4 + 3);
//
//        for (String pattern : patterns) {
//            char[] patternChars = pattern.toCharArray();
//
//            for (List<Integer> dig : dPerms) {
//                for (List<Integer> opr : oPerms) {
//
//                    int i = 0, j = 0;
//                    for (char c : patternChars) {
//                        if (c == 'n')
//                            sb.append(dig.get(i++));
//                        else
//                            sb.append(ops.charAt(opr.get(j++)));
//                    }
//
//                    String candidate = sb.toString();
//                    try {
//                        if (evaluate(candidate.toCharArray())) {
//                            solution = postfixToInfix(candidate);
//                            return true;
//                        }
//                    } catch (Exception ignored) {
//                    }
//                    sb.setLength(0);
//                }
//            }
//        }
//        return false;
//    }

//    String postfixToInfix(String postfix) {
//        class Expression {
//            String op, ex;
//            int prec = 3;
//
//            Expression(String e) {
//                ex = e;
//            }
//
//            Expression(String e1, String e2, String o) {
//                ex = String.format("%s %s %s", e1, o, e2);
//                op = o;
//                prec = ops.indexOf(o) / 2;
//            }
//        }
//
//        Stack<Expression> expr = new Stack<>();
//
//        for (char c : postfix.toCharArray()) {
//            int idx = ops.indexOf(c);
//            if (idx != -1) {
//
//                Expression r = expr.pop();
//                Expression l = expr.pop();
//
//                int opPrec = idx / 2;
//
//                if (l.prec < opPrec)
//                    l.ex = '(' + l.ex + ')';
//
//                if (r.prec <= opPrec)
//                    r.ex = '(' + r.ex + ')';
//
//                expr.push(new Expression(l.ex, r.ex, "" + c));
//            } else {
//                expr.push(new Expression("" + c));
//            }
//        }
//        return expr.peek().ex;
//    }
}
