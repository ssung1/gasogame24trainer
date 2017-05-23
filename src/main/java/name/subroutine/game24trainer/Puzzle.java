package name.subroutine.game24trainer;

import java.util.IdentityHashMap;
import java.util.List;

public class Puzzle
{
    public static int YES = 1;
    public static int UNKNOWN = 0;
    public static int NO = -1;

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
