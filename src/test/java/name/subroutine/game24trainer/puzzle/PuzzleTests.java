package name.subroutine.game24trainer.puzzle;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;
import static name.subroutine.game24trainer.puzzle.PuzzleTag.*;

public class PuzzleTests
{
    @Test
    public void testEqualitySameObject()
    {
        Puzzle a = new Puzzle( 1, 2, 3, 4 );
        assertThat( a, is( a ) );
    }

    @Test
    public void testEqualitySameNumbersSameOrder000()
    {
        Puzzle a = new Puzzle( 1, 2, 3, 4 );
        Puzzle b = new Puzzle( 1, 2, 3, 4 );
        assertThat( a, is( b ) );
    }

    @Test
    public void testEqualitySameNumbersSameOrder001()
    {
        Puzzle a = new Puzzle( 2, 2, 3, 4 );
        Puzzle b = new Puzzle( 2, 2, 3, 4 );
        assertThat( a, is( b ) );
    }

    @Test
    public void testEqualitySameNumbersSameOrder002()
    {
        Puzzle a = new Puzzle( 8, 2, 8, 8 );
        Puzzle b = new Puzzle( 8, 2, 8, 8 );
        assertThat( a, is( b ) );
    }

    @Test
    public void testEqualitySameNumbersDifferentOrder000()
    {
        Puzzle a = new Puzzle( 1, 2, 3, 4 );
        Puzzle b = new Puzzle( 2, 3, 4, 1 );
        assertThat( a, is( b ) );
    }

    @Test
    public void testEqualitySameNumbersDifferentOrder001()
    {
        Puzzle a = new Puzzle( 1, 2, 4, 4 );
        Puzzle b = new Puzzle( 2, 4, 4, 1 );
        assertThat( a, is( b ) );
    }

    @Test
    public void testEqualitySameNumbersDifferentOrder002()
    {
        Puzzle a = new Puzzle( 10, 10, 10, 4 );
        Puzzle b = new Puzzle( 10, 10, 4, 10 );
        assertThat( a, is( b ) );
    }

    @Test
    public void testEqualityDifferentNumbers()
    {
        Puzzle a = new Puzzle( 10, 10, 10, 4 );
        Puzzle b = new Puzzle( 10, 10, 4, 11 );
        assertThat( a, not( b ) );
    }

    @Test
    public void testHashSameNumbersSameOrder000()
    {
        Puzzle a = new Puzzle( 1, 2, 3, 4 );
        Puzzle b = new Puzzle( 1, 2, 3, 4 );
        assertThat( a.hashCode(), is( b.hashCode() ) );
    }

    @Test
    public void testHashSameNumbersSameOrder001()
    {
        Puzzle a = new Puzzle( 2, 2, 3, 4 );
        Puzzle b = new Puzzle( 2, 2, 3, 4 );
        assertThat( a.hashCode(), is( b.hashCode() ) );
    }

    @Test
    public void testHashSameNumbersSameOrder002()
    {
        Puzzle a = new Puzzle( 8, 2, 8, 8 );
        Puzzle b = new Puzzle( 8, 2, 8, 8 );
        assertThat( a.hashCode(), is( b.hashCode() ) );
    }

    @Test
    public void testHashSameNumbersDifferentOrder000()
    {
        Puzzle a = new Puzzle( 1, 2, 3, 4 );
        Puzzle b = new Puzzle( 2, 3, 4, 1 );
        assertThat( a.hashCode(), is( b.hashCode() ) );
    }

    @Test
    public void testHashSameNumbersDifferentOrder001()
    {
        Puzzle a = new Puzzle( 1, 2, 4, 4 );
        Puzzle b = new Puzzle( 2, 4, 4, 1 );
        assertThat( a.hashCode(), is( b.hashCode() ) );
    }

    @Test
    public void testHashSameNumbersDifferentOrder002()
    {
        Puzzle a = new Puzzle( 10, 10, 10, 4 );
        Puzzle b = new Puzzle( 10, 10, 4, 10 );
        assertThat( a.hashCode(), is( b.hashCode() ) );
    }

    @Test
    public void testHashDifferentNumbers()
    {
        Puzzle a = new Puzzle( 10, 10, 10, 4 );
        Puzzle b = new Puzzle( 10, 10, 4, 11 );
        assertThat( a.hashCode(), not( b.hashCode() ) );
    }

    @Test
    public void testCanonicalStringIsInCorrectOrder()
    {
        Puzzle a = new Puzzle( 4, 3, 2, 1 );
        assertThat( a.toCanonicalString(), is( " 1  2  3  4" ) );
    }

    @Test
    public void testCanonicalStringDoesNotAlterList()
    {
        Puzzle a = new Puzzle( 4, 3, 2, 1 );
        a.toCanonicalString();
        assertThat( a.getNumbers().get( 0 ), is( 4 ) );
        assertThat( a.getNumbers().get( 1 ), is( 3 ) );
        assertThat( a.getNumbers().get( 2 ), is( 2 ) );
        assertThat( a.getNumbers().get( 3 ), is( 1 ) );
    }

    @Test
    public void testConstructionByString()
    {
        Puzzle a = new Puzzle( "2 3 4 5" );
        List<Integer> n = a.getNumbers();
        assertThat( n.get( 0 ), is( 2 ) );
        assertThat( n.get( 1 ), is( 3 ) );
        assertThat( n.get( 2 ), is( 4 ) );
        assertThat( n.get( 3 ), is( 5 ) );
    }

    @Test
    public void testTagPuzzleOne()
    {
        Puzzle a = new Puzzle( "1 2 3 4" );
        a.tag( PuzzleTag.PLAYER_ONE );
        assertTrue( a.hasTag( PuzzleTag.PLAYER_ONE ) );
    }

    @Test
    public void testPuzzleStartsWithNoTags()
    {
        Puzzle a = new Puzzle( "1 2 3 4" );
        assertFalse( a.hasTag( PLAYER_ONE ) );
        assertFalse( a.hasTag( PLAYER_TWO ) );
        assertFalse( a.hasTag( ONE_DOT ) );
        assertFalse( a.hasTag( TWO_DOT ) );
        assertFalse( a.hasTag( THREE_DOT ) );
        assertFalse( a.hasTag( SINGLE ) );
        assertFalse( a.hasTag( DOUBLE_ONE ) );
        assertFalse( a.hasTag( DOUBLE_TWO ) );
        assertFalse( a.hasTag( DOUBLE_THREE ) );
        assertFalse( a.hasTag( DOUBLE_FOUR ) );
    }

    @Test
    public void testTagPuzzleTwo()
    {
        Puzzle a = new Puzzle( "1 2 3 4" );
        a.tag( PLAYER_ONE );
        a.tag( PLAYER_TWO );
        assertTrue( a.hasTag( PLAYER_ONE ) );
        assertTrue( a.hasTag( PLAYER_TWO ) );
        assertFalse( a.hasTag( ONE_DOT ) );
        assertFalse( a.hasTag( TWO_DOT ) );
        assertFalse( a.hasTag( THREE_DOT ) );
        assertFalse( a.hasTag( SINGLE ) );
        assertFalse( a.hasTag( DOUBLE_ONE ) );
        assertFalse( a.hasTag( DOUBLE_TWO ) );
        assertFalse( a.hasTag( DOUBLE_THREE ) );
        assertFalse( a.hasTag( DOUBLE_FOUR ) );
    }

    @Test
    public void testHasAllTags()
    {
        Puzzle a = new Puzzle( "1 2 3 4" );
        a.tag( PLAYER_ONE );
        a.tag( PLAYER_TWO );
        assertTrue( a.hasTags( PLAYER_ONE, PLAYER_TWO ) );
    }

    @Test
    public void testOnlyHasFirstTag()
    {
        Puzzle a = new Puzzle( "1 2 3 4" );
        a.tag( PLAYER_ONE );
        a.tag( PLAYER_TWO );
        assertFalse( a.hasTags( PLAYER_ONE, ONE_DOT ) );
    }

    @Test
    public void testOnlyHasLastTag()
    {
        Puzzle a = new Puzzle( "1 2 3 4" );
        a.tag( PLAYER_ONE );
        a.tag( PLAYER_TWO );
        assertFalse( a.hasTags( ONE_DOT, PLAYER_TWO ) );
    }

    @Test
    public void testOnlyHasNoneTags()
    {
        Puzzle a = new Puzzle( "1 2 3 4" );
        a.tag( PLAYER_ONE );
        a.tag( PLAYER_TWO );
        assertFalse( a.hasTags( TWO_DOT, ONE_DOT ) );
    }
}
