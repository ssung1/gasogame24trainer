package name.subroutine.game24trainer.puzzle;

public enum PuzzleTag
{
    // tags coming from source
    ONE_DOT( "*", "One Dot" ),
    TWO_DOT( "**", "Two Dot" ),
    THREE_DOT( "***", "Three Dot" ),
    PLAYER_ONE( "p1", "Player 1" ),
    PLAYER_TWO( "p2", "Player 2" ),
    SINGLE( "s", "Only Single Digit Numbers" ),
    DOUBLE_ONE( "1d", "One Double Digit Numbers" ),
    DOUBLE_TWO( "2d", "Two Double Digit Numbers" ),
    DOUBLE_THREE( "3d", "Three Double Digit Numbers" ),
    DOUBLE_FOUR( "4d", "Four Double Digit Numbers" );

    private final String symbol;
    private final String description;

    PuzzleTag( String symbol, String description )
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
