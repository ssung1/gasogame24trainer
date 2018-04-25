package name.subroutine.game24trainer.sourceimpl;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Game24PuzzleSourceImplMaxTests
{
    Game24PuzzleSourceImplMax sut =
            new Game24PuzzleSourceImplMax();

    @Test
    public void testGetPuzzleListInitSizeMax1()
    {
        assertThat( sut.getPuzzleListInitSize( 1 ), is( 1 ) );
    }

    @Test
    public void testGetPuzzleListInitSizeMax2()
    {
        assertThat( sut.getPuzzleListInitSize( 2 ), is( 5 ) );
    }

    @Test
    public void testGetPuzzleListInitSizeMax3()
    {
        assertThat( sut.getPuzzleListInitSize( 3 ), is( 15 ) );
    }

    @Test
    public void testGetPuzzleListInitSizeMax24()
    {
        assertThat( sut.getPuzzleListInitSize( 24 ), is( 17550 ) );
    }

    @Test
    public void testGetPuzzleList()
    {
        int maxNumber = 5;
        sut.setMaxNumber( maxNumber );
        int expectedSize = sut.getPuzzleListInitSize( maxNumber );

        assertThat( sut.getPuzzleList().size(), is( expectedSize ) );
    }
}
