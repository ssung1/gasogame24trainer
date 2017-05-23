package name.subroutine.game24trainer;


import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class Game24SolverImplRosettaTests
{
    Symbol s = new Symbol();
    Game24SolverImplRosetta sut = new Game24SolverImplRosetta();

    @Before
    public void setUp()
    {
    }

    public void printPermutation( List<List<Integer>> a )
    {
        for( List<Integer> p: a ) {
            System.out.println( p );
        }
    }

    @Test
    public void testPermuteTwoValues()
    {
        List<Integer> lst = Lists.newArrayList( 1, 2 );
        List<List<Integer>> result = new ArrayList<>();

        sut.permute( lst, result, 0 );

        //printPermutation( result );

        assertThat( result, hasSize( 2 ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 1, 2 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 2, 1 ) ) );
    }

    @Test
    public void testPermuteThreeValues()
    {
        List<Integer> lst = Lists.newArrayList( 1, 2, 3 );
        List<List<Integer>> result = new ArrayList<>();

        sut.permute( lst, result, 0 );

        assertThat( result, hasSize( 6 ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 1, 2, 3 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 1, 3, 2 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 2, 1, 3 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 2, 3, 1 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 3, 1, 2 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 3, 2, 1 ) ) );
    }

    @Test
    public void testPermuteThreeValuesAt1()
    {
        List<Integer> lst = Lists.newArrayList( 1, 2, 3 );
        List<List<Integer>> result = new ArrayList<>();

        sut.permute( lst, result, 1 );

        assertThat( result, hasSize( 2 ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 1, 2, 3 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 1, 3, 2 ) ) );
    }

    @Test
    public void testPermuteThreeValuesAt2()
    {
        List<Integer> lst = Lists.newArrayList( 1, 2, 3 );
        List<List<Integer>> result = new ArrayList<>();

        sut.permute( lst, result, 2 );

        assertThat( result, hasSize( 1 ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 1, 2, 3 ) ) );
    }

    @Test
    public void testPermuteOperators1()
    {
        List<List<Integer>> result = new ArrayList<>();
        sut.permuteOperators( result, 1 );


        assertThat( result, hasSize( 1 ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 0, 0, 0 ) ) );
    }

    @Test
    public void testPermuteOperators2()
    {
        List<List<Integer>> result = new ArrayList<>();
        sut.permuteOperators( result, 2 );


        assertThat( result, hasSize( 8 ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 0, 0, 0 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 0, 0, 1 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 0, 1, 0 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 0, 1, 1 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 1, 0, 0 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 1, 0, 1 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 1, 1, 0 ) ) );
        assertThat( result,
            hasItem( Lists.newArrayList( 1, 1, 1 ) ) );
    }

    @Test
    public void testApplyOperatorDivide()
    {
        double result = sut.applyOperator( 2, 6, '/' );
        assertThat( result, closeTo( 3.0, .001 ) );
    }

    @Test
    public void testEquals24_000()
        throws Exception
    {
        // 1 * 2 * 3 * 4 = 24
        boolean result = sut.equals24( s.parse( "1 2 * 3 * 4 *" ) );
        assertThat( result, is( true ) );
    }

    @Test
    public void testEquals24_001()
        throws Exception
    {
        // 1 + 2 + 3 + 4 != 24
        boolean result = sut.equals24( s.parse( "1 2 + 3 + 4 +" ) );
        assertThat( result, is( false ) );
    }

    @Test
    public void testPostfixToInfix000()
    {
        String result = sut.postfixToInfix( s.parse( "1 2 + 3 * 4 *" ) );

        System.out.println( result );
    }

    @Test
    public void testSolveSimple()
    {
        sut.isSolvable( Game24Puzzles.simple );
    }

    @Test
    public void testSolveTwoByTwo()
    {
        sut.isSolvable( Game24Puzzles.twoByTwo );
    }

    @Test
    public void testSolveAddSub()
    {
        sut.isSolvable( Game24Puzzles.addSub );
    }
}
