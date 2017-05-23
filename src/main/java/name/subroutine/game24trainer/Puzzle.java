package name.subroutine.game24trainer;

import java.util.IdentityHashMap;
import java.util.List;

public class Puzzle
{
    public static int YES = 1;
    public static int UNKNOWN = 0;
    public static int NO = -1;

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

    public List<Integer> getNumbers()
    {
        return this.numbers;
    }
}
