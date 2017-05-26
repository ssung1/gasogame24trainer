package name.subroutine.game24trainer;

public enum DiffcultyRank
{
    FINAL_MUL( "M", "multiplication as final operation" ),
    FINAL_MUL_2( "2M2", "multiplication as final operation, two-by-two" ),
    FINAL_ADD( "+", "addition/subtraction as final operation" ),
    FINAL_ADD_2( "2+2", "addition/subtraction as final operation, two-by-two" ),
    FINAL_DIV( "D", "division as final operation" ),
    FRAC( "f", "fraction as intermediate" ),
    NO_SOLU( "X", "no solution" ),
    UNDEF( "?", "undefined" );

    private final String symbol;
    private final String description;

    DiffcultyRank( String symbol, String description )
    {
        this.symbol = symbol;
        this.description = description;
    }

    public String getSymbol()
    {
        return this.symbol;
    }

    public String getDescription()
    {
        return this.description;
    }
}
