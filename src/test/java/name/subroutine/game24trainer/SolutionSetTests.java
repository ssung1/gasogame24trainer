package name.subroutine.game24trainer;

import org.junit.Test;

import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

public class SolutionSetTests
{
    @Test
    public void testHasSolution000()
    {
        SolutionSet sut = new SolutionSet();
        assertFalse( sut.hasSolution() );
    }

    @Test
    public void testHasSolution001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );
        assertTrue( sut.hasSolution() );
    }

    @Test
    public void testExcludeFraction000()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.hasFraction() ).thenReturn( Puzzle.YES );
        when( s2.hasFraction() ).thenReturn( Puzzle.YES );

        assertTrue( sut.excludeFraction().isEmpty() );
    }

    @Test
    public void testExcludeFraction001()
    {
        SolutionSet sut = new SolutionSet();

        assertTrue( sut.excludeFraction().isEmpty() );
    }

    @Test
    public void testExcludeFraction002()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.hasFraction() ).thenReturn( Puzzle.YES );
        when( s2.hasFraction() ).thenReturn( Puzzle.NO );

        assertThat( sut.excludeFraction(), hasSize( 1 ) );
        assertThat( sut.excludeFraction(), hasItem( s2 ) );
    }

    @Test
    public void testExcludeFraction003()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.hasFraction() ).thenReturn( Puzzle.UNKNOWN );
        when( s2.hasFraction() ).thenReturn( Puzzle.NO );

        assertThat( sut.excludeFraction(), hasSize( 2 ) );
        assertThat( sut.excludeFraction(), hasItem( s1 ) );
        assertThat( sut.excludeFraction(), hasItem( s2 ) );
    }

    @Test
    public void testExcludeFraction004()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.hasFraction() ).thenReturn( Puzzle.UNKNOWN );
        when( s2.hasFraction() ).thenReturn( Puzzle.YES );

        assertThat( sut.excludeFraction(), hasSize( 1 ) );
        assertThat( sut.excludeFraction(), hasItem( s1 ) );
    }

    @Test
    public void testHasTwoByTwo000()
    {
        SolutionSet sut = new SolutionSet();

        assertFalse( sut.hasTwoByTwo() );
    }

    @Test
    public void testHasTwoByTwo001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isTwoByTwo() ).thenReturn( true );
        when( s2.isTwoByTwo() ).thenReturn( false );

        assertTrue( sut.hasTwoByTwo() );
    }

    @Test
    public void testHasTwoByTwo002()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isTwoByTwo() ).thenReturn( true );
        when( s2.isTwoByTwo() ).thenReturn( true );

        assertTrue( sut.hasTwoByTwo() );
    }

    @Test
    public void testHasTwoByTwo003()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isTwoByTwo() ).thenReturn( false );
        when( s2.isTwoByTwo() ).thenReturn( false );

        assertFalse( sut.hasTwoByTwo() );
    }

    @Test
    public void testHasFinalDiv000()
    {
        SolutionSet sut = new SolutionSet();

        assertFalse( sut.hasFinalDiv() );
    }

    @Test
    public void testHasFinalDiv001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        sut.add( s1 );

        when( s1.isFinalDiv() ).thenReturn( true );

        assertTrue( sut.hasFinalDiv() );
    }

    @Test
    public void testHasFinalDiv002()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalDiv() ).thenReturn( true );
        when( s2.isFinalDiv() ).thenReturn( true );

        assertTrue( sut.hasFinalDiv() );
    }

    @Test
    public void testHasFinalDiv003()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalDiv() ).thenReturn( false );
        when( s2.isFinalDiv() ).thenReturn( true );

        assertTrue( sut.hasFinalDiv() );
    }

    @Test
    public void testHasFinalDiv004()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalDiv() ).thenReturn( true );
        when( s2.isFinalDiv() ).thenReturn( false );

        assertTrue( sut.hasFinalDiv() );
    }

    @Test
    public void testHasFinalDiv005()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalDiv() ).thenReturn( false );
        when( s2.isFinalDiv() ).thenReturn( false );

        assertFalse( sut.hasFinalDiv() );
    }

    @Test
    public void testHasFinalMul000()
    {
        SolutionSet sut = new SolutionSet();

        assertFalse( sut.hasFinalMul() );
    }

    @Test
    public void testHasFinalMul001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        sut.add( s1 );

        when( s1.isFinalMul() ).thenReturn( true );

        assertTrue( sut.hasFinalMul() );
    }

    @Test
    public void testHasFinalMul002()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalMul() ).thenReturn( true );
        when( s2.isFinalMul() ).thenReturn( true );

        assertTrue( sut.hasFinalMul() );
    }

    @Test
    public void testHasFinalMul003()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalMul() ).thenReturn( false );
        when( s2.isFinalMul() ).thenReturn( true );

        assertTrue( sut.hasFinalMul() );
    }

    @Test
    public void testHasFinalMul004()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalMul() ).thenReturn( true );
        when( s2.isFinalMul() ).thenReturn( false );

        assertTrue( sut.hasFinalMul() );
    }

    @Test
    public void testHasFinalMul005()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalMul() ).thenReturn( false );
        when( s2.isFinalMul() ).thenReturn( false );

        assertFalse( sut.hasFinalMul() );
    }

    @Test
    public void testHasFinalAdd000()
    {
        SolutionSet sut = new SolutionSet();

        assertFalse( sut.hasFinalAdd() );
    }

    @Test
    public void testHasFinalAdd001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        sut.add( s1 );

        when( s1.isFinalAdd() ).thenReturn( true );

        assertTrue( sut.hasFinalAdd() );
    }

    @Test
    public void testHasFinalAdd002()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalAdd() ).thenReturn( true );
        when( s2.isFinalAdd() ).thenReturn( true );

        assertTrue( sut.hasFinalAdd() );
    }

    @Test
    public void testHasFinalAdd003()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalAdd() ).thenReturn( false );
        when( s2.isFinalAdd() ).thenReturn( true );

        assertTrue( sut.hasFinalAdd() );
    }

    @Test
    public void testHasFinalAdd004()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalAdd() ).thenReturn( true );
        when( s2.isFinalAdd() ).thenReturn( false );

        assertTrue( sut.hasFinalAdd() );
    }

    @Test
    public void testHasFinalAdd005()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalAdd() ).thenReturn( false );
        when( s2.isFinalAdd() ).thenReturn( false );

        assertFalse( sut.hasFinalAdd() );
    }

    @Test
    public void testHasFraction000()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.hasFraction() ).thenReturn( Puzzle.YES );
        when( s2.hasFraction() ).thenReturn( Puzzle.YES );

        assertTrue( sut.hasFraction() );
    }

    @Test
    public void testHasFraction001()
    {
        SolutionSet sut = new SolutionSet();

        assertFalse( sut.hasFraction() );
    }

    @Test
    public void testHasFraction002()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.hasFraction() ).thenReturn( Puzzle.YES );
        when( s2.hasFraction() ).thenReturn( Puzzle.NO );

        assertTrue( sut.hasFraction() );
    }

    @Test
    public void testHasFraction003()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.hasFraction() ).thenReturn( Puzzle.UNKNOWN );
        when( s2.hasFraction() ).thenReturn( Puzzle.NO );

        assertFalse( sut.hasFraction() );
    }

    @Test
    public void testHasFraction004()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.hasFraction() ).thenReturn( Puzzle.UNKNOWN );
        when( s2.hasFraction() ).thenReturn( Puzzle.YES );

        assertTrue( sut.hasFraction() );
    }
}
