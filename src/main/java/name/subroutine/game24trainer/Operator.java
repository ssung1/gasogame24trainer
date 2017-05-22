package name.subroutine.game24trainer;

public class Operator extends Symbol
{
    public static Operator ADD = new Operator( '+' );
    public static Operator SUB = new Operator( '-' );
    public static Operator MUL = new Operator( '*' );
    public static Operator DIV = new Operator( '/' );

    private char value;

    public Operator( char value )
    {
        this.value = value;
    }

    public char getValue()
    {
        return value;
    }

    public boolean equals( Object o )
    {
        if( o == null ) {
            return false;
        }

        if( o instanceof Operator ) {
            Operator op = (Operator)o;
            return this.getValue() == op.getValue();
        }
        else if( o instanceof Character ) {
            Character c = (Character)o;
            return this.getValue() == c.charValue();
        }
        else {
            return false;
        }
    }
}

