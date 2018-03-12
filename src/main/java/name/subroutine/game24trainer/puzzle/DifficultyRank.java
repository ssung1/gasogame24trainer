package name.subroutine.game24trainer.puzzle;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat( shape = JsonFormat.Shape.OBJECT )
public enum DifficultyRank
{
    ZERO_TRICK( "0", "(a - a) * b + 24 trick" ),
    DIST_PROP( "Dis", "distributive property: (a * b) + (a * c)" ),
    ALMOST_DIST_PROP( "Alm", "almost distributive property: a * (b + c) + a" ),
    FINAL_MUL( "x", "multiplication as final operation" ),
    FINAL_MUL_2( "2x2", "multiplication as final operation, two-by-two" ),
    ADD_SUB( "+++", "only addition/subtraction" ),
    FINAL_ADD( "+", "addition/subtraction as final operation" ),
    FINAL_ADD_2( "2+2", "addition/subtraction as final operation, two-by-two" ),
    FINAL_DIV( "/", "division as final operation" ),
    FINAL_DIV_2( "2/2", "division as final operation, two-by-two" ),
    FRAC( "f", "fraction as intermediate" ),
    NO_SOLU( "N", "no solution" ),
    UNDEF( "?", "undefined" );

    private final String symbol;
    private final String description;

    DifficultyRank( String symbol, String description )
    {
        this.symbol = symbol;
        this.description = description;
    }

    public String getSymbol()
    {
        return this.symbol;
    }

    public String getName()
    {
        return this.toString();
    }

    public String getDescription()
    {
        return this.description;
    }
}
