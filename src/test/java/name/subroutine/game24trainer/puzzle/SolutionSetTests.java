package name.subroutine.game24trainer.puzzle;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import name.subroutine.game24trainer.puzzle.DifficultyRank;
import name.subroutine.game24trainer.puzzle.Puzzle;
import name.subroutine.game24trainer.puzzle.Solution;
import name.subroutine.game24trainer.puzzle.SolutionSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.*;

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

        when( s1.isFraction() ).thenReturn( Puzzle.YES );
        when( s2.isFraction() ).thenReturn( Puzzle.YES );

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

        when( s1.isFraction() ).thenReturn( Puzzle.YES );
        when( s2.isFraction() ).thenReturn( Puzzle.NO );

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

        when( s1.isFraction() ).thenReturn( Puzzle.UNKNOWN );
        when( s2.isFraction() ).thenReturn( Puzzle.NO );

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

        when( s1.isFraction() ).thenReturn( Puzzle.UNKNOWN );
        when( s2.isFraction() ).thenReturn( Puzzle.YES );

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
    public void testHasFinalDivTwoByTwo000()
    {
        SolutionSet sut = new SolutionSet();

        assertFalse( sut.hasFinalDivTwoByTwo() );
    }

    @Test
    public void testHasFinalDivTwoByTwo001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        sut.add( s1 );

        when( s1.isFinalDivTwoByTwo() ).thenReturn( true );

        assertTrue( sut.hasFinalDivTwoByTwo() );
    }

    @Test
    public void testHasFinalDivTwoByTwo002()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalDivTwoByTwo() ).thenReturn( true );
        when( s2.isFinalDivTwoByTwo() ).thenReturn( true );

        assertTrue( sut.hasFinalDivTwoByTwo() );
    }

    @Test
    public void testHasFinalDivTwoByTwo003()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalDivTwoByTwo() ).thenReturn( false );
        when( s2.isFinalDivTwoByTwo() ).thenReturn( true );

        assertTrue( sut.hasFinalDivTwoByTwo() );
    }

    @Test
    public void testHasFinalDivTwoByTwo004()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalDivTwoByTwo() ).thenReturn( true );
        when( s2.isFinalDivTwoByTwo() ).thenReturn( false );

        assertTrue( sut.hasFinalDivTwoByTwo() );
    }

    @Test
    public void testHasFinalDivTwoByTwo005()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalDivTwoByTwo() ).thenReturn( false );
        when( s2.isFinalDivTwoByTwo() ).thenReturn( false );

        assertFalse( sut.hasFinalDivTwoByTwo() );
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
    public void testHasFinalMulTwoByTwo000()
    {
        SolutionSet sut = new SolutionSet();

        assertFalse( sut.hasFinalMulTwoByTwo() );
    }

    @Test
    public void testHasFinalMulTwoByTwo001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        sut.add( s1 );

        when( s1.isFinalMulTwoByTwo() ).thenReturn( true );

        assertTrue( sut.hasFinalMulTwoByTwo() );
    }

    @Test
    public void testHasFinalMulTwoByTwo002()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalMulTwoByTwo() ).thenReturn( true );
        when( s2.isFinalMulTwoByTwo() ).thenReturn( true );

        assertTrue( sut.hasFinalMulTwoByTwo() );
    }

    @Test
    public void testHasFinalMulTwoByTwo003()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalMulTwoByTwo() ).thenReturn( false );
        when( s2.isFinalMulTwoByTwo() ).thenReturn( true );

        assertTrue( sut.hasFinalMulTwoByTwo() );
    }

    @Test
    public void testHasFinalMulTwoByTwo004()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalMulTwoByTwo() ).thenReturn( true );
        when( s2.isFinalMulTwoByTwo() ).thenReturn( false );

        assertTrue( sut.hasFinalMulTwoByTwo() );
    }

    @Test
    public void testHasFinalMulTwoByTwo005()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalMulTwoByTwo() ).thenReturn( false );
        when( s2.isFinalMulTwoByTwo() ).thenReturn( false );

        assertFalse( sut.hasFinalMulTwoByTwo() );
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
    public void testHasFinalAddTwoByTwo000()
    {
        SolutionSet sut = new SolutionSet();

        assertFalse( sut.hasFinalAddTwoByTwo() );
    }

    @Test
    public void testHasFinalAddTwoByTwo001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        sut.add( s1 );

        when( s1.isFinalAddTwoByTwo() ).thenReturn( true );

        assertTrue( sut.hasFinalAddTwoByTwo() );
    }

    @Test
    public void testHasFinalAddTwoByTwo002()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalAddTwoByTwo() ).thenReturn( true );
        when( s2.isFinalAddTwoByTwo() ).thenReturn( true );

        assertTrue( sut.hasFinalAddTwoByTwo() );
    }

    @Test
    public void testHasFinalAddTwoByTwo003()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalAddTwoByTwo() ).thenReturn( false );
        when( s2.isFinalAddTwoByTwo() ).thenReturn( true );

        assertTrue( sut.hasFinalAddTwoByTwo() );
    }

    @Test
    public void testHasFinalAddTwoByTwo004()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalAddTwoByTwo() ).thenReturn( true );
        when( s2.isFinalAddTwoByTwo() ).thenReturn( false );

        assertTrue( sut.hasFinalAddTwoByTwo() );
    }

    @Test
    public void testHasFinalAddTwoByTwo005()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFinalAddTwoByTwo() ).thenReturn( false );
        when( s2.isFinalAddTwoByTwo() ).thenReturn( false );

        assertFalse( sut.hasFinalAddTwoByTwo() );
    }

    @Test
    public void testHasFraction000()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isFraction() ).thenReturn( Puzzle.YES );
        when( s2.isFraction() ).thenReturn( Puzzle.YES );

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

        when( s1.isFraction() ).thenReturn( Puzzle.YES );
        when( s2.isFraction() ).thenReturn( Puzzle.NO );

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

        when( s1.isFraction() ).thenReturn( Puzzle.UNKNOWN );
        when( s2.isFraction() ).thenReturn( Puzzle.NO );

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

        when( s1.isFraction() ).thenReturn( Puzzle.UNKNOWN );
        when( s2.isFraction() ).thenReturn( Puzzle.YES );

        assertTrue( sut.hasFraction() );
    }

    @Test
    public void testHasZeroTrick000()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isZeroTrick() ).thenReturn( true );
        when( s2.isZeroTrick() ).thenReturn( false );

        assertTrue( sut.hasZeroTrick() );
    }

    @Test
    public void testHasZeroTrick001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isZeroTrick() ).thenReturn( false );
        when( s2.isZeroTrick() ).thenReturn( false );

        assertFalse( sut.hasZeroTrick() );
    }

    @Test
    public void testHasDistProp000()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isDistProp() ).thenReturn( true );
        when( s2.isDistProp() ).thenReturn( false );

        assertTrue( sut.hasDistProp() );
    }

    @Test
    public void testHasDistProp001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isDistProp() ).thenReturn( false );
        when( s2.isDistProp() ).thenReturn( false );

        assertFalse( sut.hasDistProp() );
    }

    @Test
    public void testHasAlmostDistProp000()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isAlmostDistProp() ).thenReturn( true );
        when( s2.isAlmostDistProp() ).thenReturn( false );

        assertTrue( sut.hasAlmostDistProp() );
    }

    @Test
    public void testHasAlmostDistProp001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isAlmostDistProp() ).thenReturn( false );
        when( s2.isAlmostDistProp() ).thenReturn( false );

        assertFalse( sut.hasAlmostDistProp() );
    }

    @Test
    public void testHasAddSub000()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isAddSub() ).thenReturn( true );
        when( s2.isAddSub() ).thenReturn( false );

        assertTrue( sut.hasAddSub() );
    }

    @Test
    public void testHasAddSub001()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isAddSub() ).thenReturn( false );
        when( s2.isAddSub() ).thenReturn( false );

        assertFalse( sut.hasAddSub() );
    }

    @Test
    public void testSerializeToJson() throws Exception
    {
        SolutionSet sut = new SolutionSet();
        Puzzle p = new Puzzle( "1 2 3 4" );
        Solution s = new Solution();


        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString( sut );

        JsonNode root = mapper.readTree( json );

        //{"solutionSet":[],"puzzle":null,"algorithm":null,
        // "hasFraction":false,"hasFinalAdd":false,"hasFinalDiv":false,isFraction "hasFinalMul":false,"hasFinalDivTwoByTwo":false,
        // "hasFinalMulTwoByTwo":false,"hasFinalAddTwoByTwo":false,
        // "difficultyRank":{"symbol":"N","description":"no solution"},"count":0}

        assertTrue( root.has( "solutionSet" ) );
        assertTrue( root.has( "puzzle" ) );
        assertTrue( root.has( "algorithm" ) );
        assertTrue( root.has( "hasFraction" ) );
        assertTrue( root.has( "hasFinalAdd" ) );
        assertTrue( root.has( "hasFinalDiv" ) );
        assertTrue( root.has( "hasFinalMul" ) );
        assertTrue( root.has( "hasFinalDivTwoByTwo" ) );
        assertTrue( root.has( "hasFinalMulTwoByTwo" ) );
        assertTrue( root.has( "hasFinalAddTwoByTwo" ) );
        assertTrue( root.has( "difficultyRank" ) );
        //assertTrue( root.has( "description" ) );
        assertTrue( root.has( "count" ) );
    }

    @Test
    public void testGetDifficultyRankZeroTrickVsDistProp()
    {
        SolutionSet sut = new SolutionSet();
        Solution s1 = mock( Solution.class );
        Solution s2 = mock( Solution.class );
        sut.add( s1 );
        sut.add( s2 );

        when( s1.isZeroTrick() ).thenReturn( true );
        when( s2.isDistProp() ).thenReturn( true );
        when( s2.isZeroTrick() ).thenReturn( false );

        assertThat( sut.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
    }
}
