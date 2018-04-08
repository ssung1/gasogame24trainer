package name.subroutine.game24trainer;

import name.subroutine.game24trainer.puzzle.DifficultyRank;
import name.subroutine.game24trainer.puzzle.Puzzle;
import name.subroutine.game24trainer.puzzle.SolutionSet;
import name.subroutine.game24trainer.puzzle.Symbol;
import name.subroutine.game24trainer.sourceimpl.Game24PuzzleSourceImplMax;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

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

    Symbol symbol = new Symbol();

    SolutionSet zeroTrick;
    Puzzle zeroTrickPuzzle = new Puzzle( 24, 18, 18, 9 );

    public SolutionSet noSolution( Puzzle p )
    {
        SolutionSet result = new SolutionSet();
        result.setPuzzle( p );
        result.setAlgorithm( "mock solver with no solution" );
        return result;
    }

    @Before
    public void configureSut()
    {
        Game24PuzzleSourceImplMax source = new Game24PuzzleSourceImplMax();
        // need to set to 24 because we are testing zeroTrickPuzzle
        source.setMaxNumber( 24 );
        sut = new Game24Analyzer( mockSolver, source );
        ReflectionTestUtils.setField( sut, "solver", mockSolver );

        SolutionSet noSolution = mock( SolutionSet.class );
        when( noSolution.getPuzzle() ).thenReturn( new Puzzle( 0, 0, 0, 0 ) );
        when( noSolution.getDifficultyRank() ).thenReturn(
            DifficultyRank.NO_SOLU );

        this.zeroTrick = mock( SolutionSet.class );
        when( zeroTrick.getDifficultyRank() ).thenReturn( DifficultyRank
            .ZERO_TRICK );
        when( zeroTrick.hasSolution() ).thenReturn( true );
        when( zeroTrick.getPuzzle() ).thenReturn( zeroTrickPuzzle );

        // order is important: start with the most generic case
        when( mockSolver.solve( anyObject() ) ).thenReturn( noSolution );
        when( mockSolver.solve( eq( zeroTrickPuzzle ) ) ).thenReturn(
            zeroTrick );
        sut.analyze();
    }

    @Test
    public void testGetSolutionSetByDifficulty() throws Exception
    {
        SolutionSet ss = sut.getSolutionSetByDifficulty(
            DifficultyRank.ZERO_TRICK );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
        assertThat( ss, is( zeroTrick ) );
    }

    @Test
    public void testGetAllSolutionSetsByDifficulty() throws Exception
    {
        List<SolutionSet> ssl = sut.getAllSolutionSetsByDifficulty(
            DifficultyRank.ZERO_TRICK );
        assertThat( ssl, hasSize( 1 ) );
        SolutionSet ss = ssl.get( 0 );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
        assertThat( ss, is( zeroTrick ) );
    }

    @Test
    public void testExcludePuzzlesWithNoSolution() throws Exception
    {
        assertThat( sut.getSolutionSetList().parallelStream()
            .filter( s -> !s.hasSolution() )
            .count(), is( 0L ) );
    }
}
