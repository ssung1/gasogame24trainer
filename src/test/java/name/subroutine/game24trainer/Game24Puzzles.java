package name.subroutine.game24trainer;

import java.util.Arrays;

/**
 * List of different types of puzzles
 */
public class Game24Puzzles
{
    /**
     * simple example
     */
    public static Puzzle simple = new Puzzle( 1, 2, 3, 4 );

    /**
     * 7 * 8 + 8 * 10 is the only solution
     */
    public static Puzzle twoByTwo = new Puzzle( 7, 8, 8, 10 );

    /**
     * can be solved with only + and -
     */
    public static Puzzle addSub = new Puzzle( 5, 6, 12, 13 );

    /**
     * requires fraction as an intermediate
     */
    public static Puzzle fraction = new Puzzle( 5, 5, 5, 1 );

    /**
     * requires division as the last operation
     */
    public static Puzzle finalDiv = new Puzzle( 16, 9, 10, 4 );

    /**
     * 23 24 24 24
     * (this is actually the 0 trick)
     */
    public static Puzzle zeroTrick = new Puzzle( 23, 24, 24, 24 );
}
