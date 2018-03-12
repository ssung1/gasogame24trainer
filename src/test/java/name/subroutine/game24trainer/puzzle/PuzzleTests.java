package name.subroutine.game24trainer.puzzle;

import name.subroutine.game24trainer.puzzle.Puzzle;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
    public void testHasZeroTrick000()
    {
        Puzzle a = new Puzzle( "24 13 13 5" );
        assertTrue( "Should be solvable by zero trick", a.hasZeroTrick() );
    }

    @Test
    public void testHasZeroTrick001()
    {
        Puzzle a = new Puzzle( "5 24 13 5" );
        assertTrue( "Should be solvable by zero trick", a.hasZeroTrick() );
    }

    @Test
    public void testHasZeroTrick002()
    {
        Puzzle a = new Puzzle( "6 6 24 5" );
        assertTrue( "Should be solvable by zero trick", a.hasZeroTrick() );
    }

    @Test
    public void testHasZeroTrick003()
    {
        Puzzle a = new Puzzle( "24 24 24 24" );
        assertTrue( "Should be solvable by zero trick", a.hasZeroTrick() );
    }

    @Test
    public void testHasZeroTrick004()
    {
        Puzzle a = new Puzzle( "24 13 13 5" );
        assertTrue( "Should be solvable by zero trick", a.hasZeroTrick() );
    }

    @Test
    public void testHasZeroTrick005()
    {
        Puzzle a = new Puzzle( "5 24 13 5" );
        assertTrue( "Should be solvable by zero trick", a.hasZeroTrick() );
    }

    @Test
    public void testHasZeroTrick006()
    {
        Puzzle a = new Puzzle( "6 6 24 5" );
        assertTrue( "Should be solvable by zero trick", a.hasZeroTrick() );
    }

    @Test
    public void testHasZeroTrick007()
    {
        Puzzle a = new Puzzle( "24 24 24 24" );
        assertTrue( "Should be solvable by zero trick", a.hasZeroTrick() );
    }
}
