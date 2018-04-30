package name.subroutine.game24trainer.puzzle;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import static name.subroutine.game24trainer.puzzle.PuzzleTag.*;

import org.junit.Test;

public class PuzzleTagTests
{
    @Test
    public void testStringToPuzzleTag()
    {
        PuzzleTag oneDot = PuzzleTag.valueOf( "ONE_DOT" );
        assertThat( oneDot, is( ONE_DOT ) );
    }
}