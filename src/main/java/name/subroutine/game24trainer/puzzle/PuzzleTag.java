package name.subroutine.game24trainer.puzzle;

public enum PuzzleTag
{
    // tags coming from source
    ONE_DOT( ".", "One Dot" ),
    TWO_DOT( "..", "Two Dot" ),
    THREE_DOT( "...", "Three Dot" ),
    PLAYER_ONE( "p1", "Player 1" ),
    PLAYER_TWO( "p2", "Player 2" ),
    ZERO_D( "0d", "Zero Double Digit Numbers" ),
    ONE_D( "1d", "One Double Digit Numbers" ),
    TWO_D( "2d", "Two Double Digit Numbers" ),
    THREE_D( "3d", "Three Double Digit Numbers" ),
    FOUR_D( "4d", "Four Double Digit Numbers" );

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
