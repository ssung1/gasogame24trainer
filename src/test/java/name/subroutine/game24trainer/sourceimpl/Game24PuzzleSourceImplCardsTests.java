package name.subroutine.game24trainer.sourceimpl;

import name.subroutine.game24trainer.puzzle.Puzzle;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static name.subroutine.game24trainer.puzzle.PuzzleTag.*;

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

        int expectedSize = cardsPerPack * puzzlesPerCard
            * singleAndDouble * numberOfPacks;

        assertThat( sut.getPuzzleList().size(), is( expectedSize ) );
    }

    @Test
    public void testNoDuplicates()
    {
        HashSet<String> dup = new HashSet<>();
        for( Puzzle pz : sut.getPuzzleList() ) {
            String p = pz.toCanonicalString();
            if( dup.contains( p ) ) {
                fail( "Already has " + p );
            }
            else {
                dup.add( p );
            }
        }
    }

    @Test
    public void testHasSingle()
    {
        int cardsPerPack = 48;
        int puzzlesPerCard = 2;
        int numberOfPacks = 2;

        long expectedSize = cardsPerPack * puzzlesPerCard * numberOfPacks;

        assertThat( sut.getPuzzleList().parallelStream().filter(
            p -> p.getDigits() == Puzzle.SINGLE ).count(), is( expectedSize ) );
    }

    @Test
    public void testHasDouble()
    {
        int cardsPerPack = 48;
        int puzzlesPerCard = 2;
        int numberOfPacks = 2;

        long expectedSize = cardsPerPack * puzzlesPerCard * numberOfPacks;
        assertThat( sut.getPuzzleList().parallelStream().filter(
            p -> p.getDigits() == Puzzle.DOUBLE ).count(), is( expectedSize ) );
    }

    @Test
    public void testHasOneDot()
    {
        int cardsPerPack = 12;
        int puzzlesPerCard = 2;
        int singleAndDouble = 2;
        int numberOfPacks = 2;

        long expectedSize = cardsPerPack * puzzlesPerCard
            * singleAndDouble * numberOfPacks;

        assertThat( sut.getPuzzleList().parallelStream().filter(
            p -> p.hasTag( ONE_DOT ) ).count(), is( expectedSize ) );
    }

    @Test
    public void testHasTwoDot()
    {
        int cardsPerPack = 24;
        int puzzlesPerCard = 2;
        int singleAndDouble = 2;
        int numberOfPacks = 2;

        long expectedSize = cardsPerPack * puzzlesPerCard
            * singleAndDouble * numberOfPacks;

        assertThat( sut.getPuzzleList().parallelStream().filter(
            p -> p.hasTag( TWO_DOT ) ).count(), is( expectedSize ) );
    }

    @Test
    public void testHasThreeDot()
    {
        int cardsPerPack = 12;
        int puzzlesPerCard = 2;
        int singleAndDouble = 2;
        int numberOfPacks = 2;

        long expectedSize = cardsPerPack * puzzlesPerCard
            * singleAndDouble * numberOfPacks;

        assertThat( sut.getPuzzleList().parallelStream().filter(
            p -> p.hasTag( THREE_DOT ) ).count(), is( expectedSize ) );
    }

    @Test
    public void testHasPack1()
    {
        int cardsPerPack = 48;
        int puzzlesPerCard = 2;
        int singleAndDouble = 2;

        long expectedSize = cardsPerPack * puzzlesPerCard
            * singleAndDouble;

        assertThat( sut.getPuzzleList().parallelStream().filter(
            p -> p.getPack() == Puzzle.ONE ).count(), is( expectedSize ) );
    }

    @Test
    public void testHasPack2()
    {
        int cardsPerPack = 48;
        int puzzlesPerCard = 2;
        int singleAndDouble = 2;

        long expectedSize = cardsPerPack * puzzlesPerCard
            * singleAndDouble;

        assertThat( sut.getPuzzleList().parallelStream().filter(
            p -> p.getPack() == Puzzle.TWO ).count(), is( expectedSize ) );
    }

    @Test
    public void testHasPlayer1()
    {
        int cardsPerPack = 48;
        int puzzlesPerCard = 2;
        int singleAndDouble = 2;
        int numberOfPacks = 2;
        int giveOrTake = 5;

        // each player gets half of the puzzles, give or take
        long expectedSize = cardsPerPack * puzzlesPerCard
            * singleAndDouble * numberOfPacks / 2 - giveOrTake;

        assertThat( sut.getPuzzleList().parallelStream().filter(
            p -> p.getPlayer() == Puzzle.ONE ).count(),
            greaterThan( expectedSize ) );
    }

    @Test
    public void testHasPlayer2()
    {
        int cardsPerPack = 48;
        int puzzlesPerCard = 2;
        int singleAndDouble = 2;
        int numberOfPacks = 2;
        int giveOrTake = 5;

        // each player gets half of the puzzles, give or take
        long expectedSize = cardsPerPack * puzzlesPerCard
            * singleAndDouble * numberOfPacks / 2 - giveOrTake;

        assertThat( sut.getPuzzleList().parallelStream().filter(
            p -> p.getPlayer() == Puzzle.TWO ).count(),
            greaterThan( expectedSize ) );
    }
}
