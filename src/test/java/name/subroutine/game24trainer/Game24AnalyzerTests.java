package name.subroutine.game24trainer;

import name.subroutine.game24trainer.puzzle.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static name.subroutine.game24trainer.puzzle.PuzzleTag.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Game24AnalyzerTests
{
    Game24Analyzer sut;

    Game24Solver mockSolver = mock( Game24Solver.class );
    Game24PuzzleSource mockSource = mock( Game24PuzzleSource.class );

    private SolutionSet noSolution( Puzzle p )
    {
        SolutionSet result = new SolutionSet();
        result.setPuzzle( p );
        result.setAlgorithm( "mock solver with no solution" );
        return result;
    }

    private SolutionSet noSolution()
    {
        return noSolution( new Puzzle( 0, 0, 0, 0 ) );
    }

    private SolutionSet solutionOfDifficulty( Puzzle p, DifficultyRank rank )
    {
        SolutionSet result = mock( SolutionSet.class );

        when( result.getDifficultyRank() ).thenReturn( rank );
        when( result.hasSolution() ).thenReturn( true );
        when( result.getPuzzle() ).thenReturn( p );

        return result;
    }

    @Before
    public void configureSut()
    {
        sut = new Game24Analyzer( mockSolver, mockSource );
    }

    @Test
    public void testGetSolutionSetByDifficulty() throws Exception
    {
        when( mockSource.getPuzzleList() ).thenReturn( Arrays.asList(
            new Puzzle( 6, 6, 6, 6 ),
            new Puzzle( 24, 18, 18, 9 )
        ) );

        Puzzle zeroTrickPuzzle = new Puzzle( 24, 18, 18, 9 );
        SolutionSet zeroTrick = solutionOfDifficulty( zeroTrickPuzzle,
            DifficultyRank.ZERO_TRICK );

        when( mockSolver.solve( anyObject() ) ).thenReturn( noSolution() );
        when( mockSolver.solve( eq( zeroTrickPuzzle ) ) ).thenReturn(
            zeroTrick
        );

        sut.analyze();

        SolutionSet ss = sut.getSolutionSetByDifficulty(
            DifficultyRank.ZERO_TRICK );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
        assertThat( ss, is( zeroTrick ) );
    }

    @Test
    public void testGetSolutionSetListByDifficulty() throws Exception
    {
        when( mockSource.getPuzzleList() ).thenReturn( Arrays.asList(
            new Puzzle( 6, 6, 6, 6 ),
            new Puzzle( 24, 18, 18, 9 ),
            new Puzzle( 24, 1, 1, 24 )
        ) );

        Puzzle zeroTrickPuzzle = new Puzzle( 24, 18, 18, 9 );
        SolutionSet zeroTrick = mock( SolutionSet.class );

        when( zeroTrick.getDifficultyRank() ).thenReturn( DifficultyRank
            .ZERO_TRICK );
        when( zeroTrick.hasSolution() ).thenReturn( true );
        when( zeroTrick.getPuzzle() ).thenReturn( zeroTrickPuzzle );

        Puzzle otherZeroTrickPuzzle = new Puzzle( 24, 1, 1, 24 );
        SolutionSet otherZeroTrick = mock( SolutionSet.class );

        when( otherZeroTrick.getDifficultyRank() ).thenReturn( DifficultyRank
            .ZERO_TRICK );
        when( otherZeroTrick.hasSolution() ).thenReturn( true );
        when( otherZeroTrick.getPuzzle() ).thenReturn( otherZeroTrickPuzzle );

        when( mockSolver.solve( anyObject() ) ).thenReturn( noSolution() );
        when( mockSolver.solve( eq( zeroTrickPuzzle ) ) ).thenReturn(
            zeroTrick );
        when( mockSolver.solve( eq( otherZeroTrickPuzzle ) ) ).thenReturn(
            otherZeroTrick );

        sut.analyze();

        List<SolutionSet> ssl = sut.getSolutionSetListByDifficulty(
            DifficultyRank.ZERO_TRICK );
        assertThat( ssl, hasSize( 2 ) );
    }

    @Test
    public void testExcludePuzzlesWithNoSolution() throws Exception
    {
        when( mockSource.getPuzzleList() ).thenReturn( Arrays.asList(
            new Puzzle( 6, 6, 6, 6 ),
            new Puzzle( 24, 18, 18, 9 ),
            new Puzzle( 24, 1, 1, 24 )
        ) );

        when( mockSolver.solve( anyObject() ) ).thenReturn( noSolution() );

        sut.analyze();

        assertThat( sut.getSolutionSetList().parallelStream()
            .count(), is( 0L ) );
    }

    @Test
    public void testGetSolutionSetByDot() throws Exception
    {
        Puzzle oneDotPuzzle = new Puzzle( 1, 4, 8, 8 );
        oneDotPuzzle.setDots( Puzzle.ONE );

        when( mockSource.getPuzzleList() ).thenReturn( Arrays.asList(
            oneDotPuzzle,          // one dot puzzle
            new Puzzle( 2, 6, 8, 24 ),
            new Puzzle( 3, 14, 15, 15 ),
            oneDotPuzzle           // another one dot puzzle
        ) );

        SolutionSet oneDotSolutionSet = solutionOfDifficulty(
            oneDotPuzzle, DifficultyRank.DIST_PROP
        );

        when( mockSolver.solve( anyObject() ) ).thenReturn( noSolution() );
        when( mockSolver.solve( oneDotPuzzle ) ).thenReturn(
            oneDotSolutionSet );
        sut.analyze();

        SolutionSet ss = sut.getSolutionSetByDot( Puzzle.ONE );
        assertThat( ss, is( oneDotSolutionSet ) );
        assertThat( ss.getPuzzle().getDots(), is( Puzzle.ONE ) );
    }

    @Test
    public void testGetSolutionSetListByDotOldStyle()
    {
        Puzzle oneDotPuzzle = new Puzzle( 1, 4, 8, 8 );
        oneDotPuzzle.setDots( Puzzle.ONE );

        when( mockSource.getPuzzleList() ).thenReturn( Arrays.asList(
            oneDotPuzzle,          // one dot puzzle
            new Puzzle( 2, 6, 8, 24 ),
            new Puzzle( 3, 14, 15, 15 ),
            oneDotPuzzle           // another one dot puzzle
        ) );

        SolutionSet oneDotSolutionSet = solutionOfDifficulty(
            oneDotPuzzle, DifficultyRank.DIST_PROP
        );

        when( mockSolver.solve( anyObject() ) ).thenReturn( noSolution() );
        when( mockSolver.solve( oneDotPuzzle ) ).thenReturn(
            oneDotSolutionSet );
        sut.analyze();

        List<SolutionSet> ssl = sut.getSolutionSetListByDot( Puzzle.ONE );
        assertThat( ssl, hasSize( 2 ) );
    }

    @Test
    public void testGetSolutionSetListByDotPositive()
    {
        Puzzle oneDotPuzzle = new Puzzle( 1, 4, 8, 8 );
        oneDotPuzzle.tag( ONE_DOT );

        when( mockSource.getPuzzleList() ).thenReturn( Arrays.asList(
            oneDotPuzzle,          // one dot puzzle
            new Puzzle( 2, 6, 8, 24 ),
            new Puzzle( 3, 14, 15, 15 ),
            oneDotPuzzle           // another one dot puzzle
        ) );

        SolutionSet oneDotSolutionSet = solutionOfDifficulty(
            oneDotPuzzle, DifficultyRank.DIST_PROP
        );

        when( mockSolver.solve( anyObject() ) ).thenReturn( noSolution() );
        when( mockSolver.solve( oneDotPuzzle ) ).thenReturn(
            oneDotSolutionSet );
        sut.analyze();

        List<SolutionSet> ssl = sut.getSolutionSetListByTags( ONE_DOT );
        assertThat( ssl, hasSize( 2 ) );
    }

    @Test
    public void testGetSolutionSetListByDotNegative()
    {
        Puzzle oneDotPuzzle = new Puzzle( 1, 4, 8, 8 );
        oneDotPuzzle.tag( ONE_DOT );

        when( mockSource.getPuzzleList() ).thenReturn( Arrays.asList(
            oneDotPuzzle,          // one dot puzzle
            new Puzzle( 2, 6, 8, 24 ),
            new Puzzle( 3, 14, 15, 15 ),
            oneDotPuzzle           // another one dot puzzle
        ) );

        SolutionSet oneDotSolutionSet = solutionOfDifficulty(
            oneDotPuzzle, DifficultyRank.DIST_PROP
        );

        when( mockSolver.solve( anyObject() ) ).thenReturn( noSolution() );
        when( mockSolver.solve( oneDotPuzzle ) ).thenReturn(
            oneDotSolutionSet );
        sut.analyze();

        List<SolutionSet> ssl = sut.getSolutionSetListByTags( TWO_DOT );
        assertThat( ssl, hasSize( 0 ) );
    }
}
