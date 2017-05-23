package name.subroutine.game24trainer;

import java.util.IdentityHashMap;
import java.util.List;

public class Puzzle
{
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
