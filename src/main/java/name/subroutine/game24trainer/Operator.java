package name.subroutine.game24trainer;

public class Operator extends Symbol
{
    public static Operator ADD = new Operator( '+', 0 );
    public static Operator SUB = new Operator( '-', 0 );
    public static Operator MUL = new Operator( '*', 1 );
    public static Operator DIV = new Operator( '/', 1 );

    private char value;
    private int prec;

    public Operator( char value, int prec )
    {
        this.value = value;
        this.prec = prec;
    }

    public char getValue()
    {
        return this.value;
    }

    public int getPrec()
    {
        return this.prec;
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
            return this.getValue() == c;
        }
        else {
            return false;
        }
    }
}

