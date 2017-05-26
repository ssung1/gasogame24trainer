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
    public void testHasLastOpDiv000()
    {
        SolutionSet sut = new SolutionSet();

        assertFalse( sut.hasLastOpDiv() );
    }

    @Test
    public void testHasLastOpDiv001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        sut.add( s1 );

        when( s1.isLastOpDiv() ).thenReturn( true );

        assertTrue( sut.hasLastOpDiv() );
    }

    @Test
    public void testHasLastOpDiv002()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isLastOpDiv() ).thenReturn( true );
        when( s2.isLastOpDiv() ).thenReturn( true );

        assertTrue( sut.hasLastOpDiv() );
    }

    @Test
    public void testHasLastOpDiv003()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isLastOpDiv() ).thenReturn( false );
        when( s2.isLastOpDiv() ).thenReturn( true );

        assertTrue( sut.hasLastOpDiv() );
    }

    @Test
    public void testHasLastOpDiv004()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isLastOpDiv() ).thenReturn( true );
        when( s2.isLastOpDiv() ).thenReturn( false );

        assertTrue( sut.hasLastOpDiv() );
    }

    @Test
    public void testHasLastOpDiv005()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isLastOpDiv() ).thenReturn( false );
        when( s2.isLastOpDiv() ).thenReturn( false );

        assertFalse( sut.hasLastOpDiv() );
    }

    @Test
    public void testHasLastOpMul000()
    {
        SolutionSet sut = new SolutionSet();

        assertFalse( sut.hasLastOpMul() );
    }

    @Test
    public void testHasLastOpMul001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        sut.add( s1 );

        when( s1.isLastOpMul() ).thenReturn( true );

        assertTrue( sut.hasLastOpMul() );
    }

    @Test
    public void testHasLastOpMul002()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isLastOpMul() ).thenReturn( true );
        when( s2.isLastOpMul() ).thenReturn( true );

        assertTrue( sut.hasLastOpMul() );
    }

    @Test
    public void testHasLastOpMul003()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isLastOpMul() ).thenReturn( false );
        when( s2.isLastOpMul() ).thenReturn( true );

        assertTrue( sut.hasLastOpMul() );
    }

    @Test
    public void testHasLastOpMul004()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isLastOpMul() ).thenReturn( true );
        when( s2.isLastOpMul() ).thenReturn( false );

        assertTrue( sut.hasLastOpMul() );
    }

    @Test
    public void testHasLastOpMul005()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isLastOpMul() ).thenReturn( false );
        when( s2.isLastOpMul() ).thenReturn( false );

        assertFalse( sut.hasLastOpMul() );
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
