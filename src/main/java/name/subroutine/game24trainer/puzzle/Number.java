package name.subroutine.game24trainer.puzzle;

public class Number extends Symbol
{
    private float value;

    public Number( float value )
    {
        this.value = value;
    }

    public float getValue()
    {
        return value;
    }

    @Override
    public boolean equals( Object o )
    {
        if( o == null ) {
            return false;
        }

        if( o instanceof Number ) {
            Number n = (Number)o;
            return this.getValue() == n.getValue();
        }
        else if( o instanceof Integer ) {
            Integer i = (Integer)o;
            return this.getValue() == i;
        }
        else if( o instanceof Float ) {
            Float f = (Float)o;
            return this.getValue() == f;
        }
        else if( o instanceof Double ) {
            Double d = (Double)o;
            return this.getValue() == d;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return (value != +0.0f ? Float.floatToIntBits( value ) : 0);
    }

    String mkString( float value )
    {
        return String.format( "%2.0f", value );
    }

    public String toString()
    {
        return mkString( this.value );
    }
}
