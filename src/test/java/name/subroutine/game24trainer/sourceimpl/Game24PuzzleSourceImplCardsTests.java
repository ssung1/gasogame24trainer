package name.subroutine.game24trainer.sourceimpl;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Game24PuzzleSourceImplCardsTests
{
    Game24PuzzleSourceImplCards sut =
        new Game24PuzzleSourceImplCards();

    @Test
    public void testGetPuzzleListSize()
    {
        int cardsPerPack = 48;
        int puzzlesPerCard = 2;
        int singleAndDouble = 2;
        int numberOfPacks = 2;

//        int expectedSize = cardsPerPack * puzzlesPerCard
//            * singleAndDouble * numberOfPacks;

        // right now we only have 48 typed in
        int expectedSize = 48;
        assertThat( sut.getPuzzleList().size(), is( expectedSize ) );
    }
}
