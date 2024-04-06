package name.subroutine.game24trainer.solverimpl;

import name.subroutine.game24trainer.puzzle.*;
import org.assertj.core.util.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Game24SolverImplRosettaTests
{
    Symbol s = new Symbol();
    Game24SolverImplRosetta sut = new Game24SolverImplRosetta();

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
        boolean result = sut.evaluate( s.parse( "1 2 * 3 * 4 *" ) ).is24;
        assertThat( result, is( true ) );
    }

    @Test
    public void testEquals24_001()
        throws Exception
    {
        // 1 + 2 + 3 + 4 != 24
        boolean result = sut.evaluate( s.parse( "1 2 + 3 + 4 +" ) ).is24;
        assertThat( result, is( false ) );
    }

    @Test
    public void testSolveSimple()
    {
        SolutionSet ss = sut.solve( Game24Puzzles.simple );
        assertThat( ss.getDifficultyRank(), Matchers.is( DifficultyRank.FINAL_MUL ) );
    }

    @Disabled("disabled because ADD_SUB is not yet reported as a difficulty rank")
    @Test
    public void testSolveAddSub()
    {
        // can be solved with only + and -
        // also can be solved with * as final operation
        SolutionSet ss = sut.solve( Game24Puzzles.addSub );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ADD_SUB ) );
    }

    @Test
    public void testSolveZeroTrick()
    {
        SolutionSet ss = sut.solve( Game24Puzzles.zeroTrick );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
    }

    @Test
    public void testSolveZeroTrick000()
    {
        Puzzle a = new Puzzle( "24 13 13 5" );
        SolutionSet ss = sut.solve( a );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
    }

    @Test
    public void testSolveZeroTrick001()
    {
        Puzzle a = new Puzzle( "5 24 13 5" );
        SolutionSet ss = sut.solve( a );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
    }

    @Test
    public void testSolveZeroTrick002()
    {
        Puzzle a = new Puzzle( "6 6 24 5" );
        SolutionSet ss = sut.solve( a );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
    }

    @Test
    public void testSolveZeroTrick003()
    {
        Puzzle a = new Puzzle( "24 24 24 24" );
        SolutionSet ss = sut.solve( a );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
    }

    @Test
    public void testSolveZeroTrick004()
    {
        Puzzle a = new Puzzle( "24 13 13 5" );
        SolutionSet ss = sut.solve( a );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
    }

    @Test
    public void testSolveZeroTrick005()
    {
        Puzzle a = new Puzzle( "5 24 13 5" );
        SolutionSet ss = sut.solve( a );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
    }

    @Test
    public void testSolveZeroTrick006()
    {
        Puzzle a = new Puzzle( "6 6 24 5" );
        SolutionSet ss = sut.solve( a );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
    }

    @Test
    public void testSolveZeroTrick007()
    {
        Puzzle a = new Puzzle( "24 24 24 24" );
        SolutionSet ss = sut.solve( a );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
    }

    @Test
    public void testSolveFinalMul()
    {
        SolutionSet ss = sut.solve( Game24Puzzles.finalMul );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.FINAL_MUL ) );
    }

    @Test
    public void testSolveFinalMulTwoByTwo()
    {
        SolutionSet ss = sut.solve( Game24Puzzles.finalMulTwoByTwo );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.FINAL_MUL_2 ) );
    }

    @Test
    public void testSolveFinalAdd()
    {
        SolutionSet ss = sut.solve( Game24Puzzles.finalAdd );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.FINAL_ADD ) );
    }

    @Test
    public void testSolveFinalAddTwoByTwo()
    {
        SolutionSet ss = sut.solve( Game24Puzzles.finalAddTwoByTwo );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.FINAL_ADD_2 ) );
    }

    @Test
    public void testSolveFinalDiv()
    {
        SolutionSet ss = sut.solve( Game24Puzzles.finalDiv );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.FINAL_DIV ) );
    }

    @Test
    public void testSolveFinalDivTwoByTwo()
    {
        SolutionSet ss = sut.solve( Game24Puzzles.finalDivTwoByTwo );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.FINAL_DIV_2 ) );
    }

    @Test
    public void testSolveFraction()
    {
        SolutionSet ss = sut.solve( Game24Puzzles.fraction );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.FRAC ) );
    }

    @Test
    public void testSolveNoSolution()
    {
        SolutionSet ss = sut.solve( Game24Puzzles.noSolution );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.NO_SOLU ) );
        assertFalse( ss.hasSolution() );
    }
}
