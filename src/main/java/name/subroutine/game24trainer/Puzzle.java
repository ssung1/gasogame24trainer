package name.subroutine.game24trainer;

import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.List;

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
    public Puzzle( List<Integer> numbers )
    {
        this.numbers = numbers;
    }

    public Puzzle( int a, int b, int c, int d )
    {
        this.numbers = Arrays.asList( a, b, c, d );
    }

    public List<Integer> getNumbers()
    {
        return this.numbers;
    }
}
