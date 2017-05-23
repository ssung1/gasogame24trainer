package name.subroutine.game24trainer;

import org.assertj.core.util.Lists;

/**
 * List of different types of puzzles
 */
public class Game24Puzzles
{
    /**
     * simple example
     */
    public static Puzzle simple = new Puzzle(
        Lists.newArrayList( 1, 2, 3, 4 )
    );

    /**
     * 7 * 8 + 8 * 10      is the only solution
     */
    public static Puzzle twoByTwo = new Puzzle(
        Lists.newArrayList( 7, 8, 8, 10 )
    );


    /**
     * can be solved with only + and -
     */
    public static Puzzle addSub = new Puzzle(
        Lists.newArrayList( 5, 6, 12, 13 )
    );

    /**
     * requires fraction as an intermediate
     */
    public static Puzzle fraction = new Puzzle(
        Lists.newArrayList( 5, 5, 5, 1 )
    );
}
