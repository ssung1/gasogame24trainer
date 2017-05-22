package name.subroutine.game24trainer;

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
            return this.getValue() == i.intValue();
        }
        else if( o instanceof Float ) {
            Float f = (Float)o;
            return this.getValue() == f.floatValue();
        }
        else if( o instanceof Double ) {
            Double d = (Double)o;
            return this.getValue() == d.doubleValue();
        }
        else {
            return false;
        }
    }
}
