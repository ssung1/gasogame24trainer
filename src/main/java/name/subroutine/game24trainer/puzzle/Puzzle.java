package name.subroutine.game24trainer.puzzle;

import java.util.*;
import java.util.stream.Collectors;

public class Puzzle
{
    public static int YES = 1;
    public static int UNKNOWN = 0;
    public static int NO = -1;

    public static String flagToString( int flag )
    {
        if( flag == YES ) {
            return "true";
        }
        else if( flag == NO ) {
            return "false";
        }
        else {
            return "unknown";
        }
    }

    /**
     * one dot puzzle
     */
    public static int ONE = 1;

    /**
     * two dot puzzle
     */
    public static int TWO = 2;

    /**
     * three dot puzzle
     */
    public static int THREE = 3;

    /**
     * single digit
     */
    public static int SINGLE = 1;

    /**
     * double digit
     */
    public static int DOUBLE = 2;

    private List<Integer> numbers;
    private int digits;
    private int dots;
    private int pack;
    private int player;
    private Set<PuzzleTag> tags = new HashSet<>();

    public Puzzle( List<Integer> numbers )
    {
        this.numbers = numbers;
    }

    public Puzzle( int a, int b, int c, int d )
    {
        this.numbers = Arrays.asList( a, b, c, d );
    }

    public Puzzle( String p )
    {
        String tokenList[] = p.split( "\\s+" );
        this.numbers = new ArrayList<>();

        for( String token: tokenList ) {
            if( !token.isEmpty() ) {
                try {
                    int num = Integer.parseInt( token );
                    numbers.add( num );
                } catch( NumberFormatException ex ) {
                    throw new RuntimeException( ex );
                }
            }
        }
    }

    public void tag( PuzzleTag t )
    {
        tags.add( t );
    }

    public boolean hasTag( PuzzleTag t )
    {
        return tags.contains( t );
    }

    public List<Integer> getNumbers()
    {
        return this.numbers;
    }

    String mkString( List<Integer> numbers )
    {
        return String.format( "%2d %2d %2d %2d",
            numbers.get( 0 ),
            numbers.get( 1 ),
            numbers.get( 2 ),
            numbers.get( 3 ) );
    }

    public String toString()
    {
        return this.mkString( this.numbers );
    }

    public String toCanonicalString()
    {
        // make a copy first
        List<Integer> sorted = numbers.stream()
            .sorted().collect( Collectors.toList() );
        return mkString( sorted );
    }

    public int getDigits()
    {
        return digits;
    }

    public void setDigits( int digits )
    {
        this.digits = digits;
    }

    public int getDots()
    {
        return dots;
    }

    public void setDots( int dots )
    {
        this.dots = dots;
    }

    public int getPack() {
        return pack;
    }

    public void setPack( int pack ) {
        this.pack = pack;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer( int player ) {
        this.player = player;
    }

    @Override
    public int hashCode()
    {
        return this.toCanonicalString().hashCode();
    }

    @Override
    public boolean equals( Object o )
    {
        if( !(o instanceof Puzzle) ) return false;
        Puzzle p = (Puzzle)o;
        return this.toCanonicalString().equals(
            p.toCanonicalString() );
    }
}
