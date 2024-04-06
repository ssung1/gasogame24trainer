package name.subroutine.game24trainer.puzzle;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static name.subroutine.game24trainer.puzzle.PuzzleTag.*;

import org.junit.jupiter.api.Test;

public class PuzzleTagTests
{
    @Test
    public void testStringToPuzzleTag()
    {
        PuzzleTag oneDot = PuzzleTag.valueOf( "ONE_DOT" );
        assertThat( oneDot, is( ONE_DOT ) );
    }
}
