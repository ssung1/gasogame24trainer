package name.subroutine.game24trainer;

public class Operator extends Symbol
{
    public static Operator ADD = new Operator( '+', 0, true );
    public static Operator SUB = new Operator( '-', 0, false );
    public static Operator MUL = new Operator( '*', 1, true );
    public static Operator DIV = new Operator( '/', 1, false );

    private char value;
    private int prec;
    private boolean commutative;

    public Operator( char value, int prec, boolean commutative )
    {
        this.value = value;
        this.prec = prec;
        this.commutative = commutative;
    }

    public char getValue()
    {
        return this.value;
    }

    public int getPrec()
    {
        return this.prec;
    }

    @Override
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
            return this.getValue() == c;
        }
        else {
            return false;
        }
    }

    String mkString( char value )
    {
        return String.format( "%c", value );
    }

    public String toString()
    {
        return mkString( this.value );
    }
}

