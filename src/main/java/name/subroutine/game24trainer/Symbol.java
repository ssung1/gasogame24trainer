package name.subroutine.game24trainer;

import java.util.ArrayList;

/**
 * A symbol can be a number or an operator
 */
public class Symbol
{
    public Symbol[] parse( String s )
    {
        String tokenList[] = s.split( "\\s+" );
        ArrayList<Symbol> result = new ArrayList<>();

        for( String token: tokenList ) {
            if( !token.isEmpty() ) {
                try {
                    int num = Integer.parseInt( token );
                    result.add( new Number( num ) );
                } catch( NumberFormatException ex ) {
                    Operator op;
                    switch( token.charAt( 0 ) ) {
                        case '+':
                            op = Operator.ADD;
                            break;
                        case '-':
                            op = Operator.SUB;
                            break;
                        case '*':
                            op = Operator.MUL;
                            break;
                        case '/':
                            op = Operator.DIV;
                            break;
                        default:
                            throw new IllegalArgumentException(
                                "Invalid operator " + token );
                    }

                    result.add( op );
                }
            }
        }

        return result.toArray( new Symbol[result.size()] );
    }
}
