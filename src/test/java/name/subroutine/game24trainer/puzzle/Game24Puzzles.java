package name.subroutine.game24trainer.puzzle;

import name.subroutine.game24trainer.puzzle.Puzzle;

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
     * can be solved with only + and -
     */
    public static Puzzle addSub = new Puzzle( 8, 18, 20, 23 );

    /**
     * 23 24 24 24
     * (this is actually the 0 trick)
     */
    public static Puzzle zeroTrick = new Puzzle( 23, 24, 24, 24 );

    /**
     * distributive property
     */
    public static Puzzle distProp = new Puzzle( 8, 8, 11, 14 );

    /**
     * almost distributive property
     */
    public static Puzzle almostDistProp = new Puzzle( 6, 2, 3, 6 );

    /**
     * multiplication as final operation
     *
     * 3978 puzzles are like this
     */
    public static Puzzle finalMul = new Puzzle( 9, 11, 12, 22 );

    /**
     * (a + b) * (c + d) example
     * two-by-two, with multiplication as final operation
     *
     * 1314 puzzles are like this
     */
    public static Puzzle finalMulTwoByTwo = new Puzzle( 8, 14, 17, 21 );

    /**
     * add/sub as final operation
     *
     * 4752 puzzles are like this
     */
    public static Puzzle finalAdd = new Puzzle( 9, 10, 11, 19 );

    /**
     * two by two, with add/sub as final operation
     *
     * 145 puzzles are like this
     */
    public static Puzzle finalAddTwoByTwo = new Puzzle( 9, 10, 12, 16 );

    /**
     * division as the last operation
     *
     * 270 puzzles are like this
     */
    public static Puzzle finalDiv = new Puzzle( 11, 9, 20, 4 );

    /**
     * two by two, with division as the final operation
     *
     * 32 puzzles are like this
     */
    public static Puzzle finalDivTwoByTwo = new Puzzle( 16, 9, 10, 4 );

    /**
     * requires fraction as an intermediate
     */
    public static Puzzle fraction = new Puzzle( 5, 5, 5, 1 );

    /**
     * no solution
     */
    public static Puzzle noSolution = new Puzzle( 1, 1, 1, 1 );
}
