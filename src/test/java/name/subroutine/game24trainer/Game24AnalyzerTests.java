package name.subroutine.game24trainer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Game24AnalyzerTests
{
    @Autowired
    @Qualifier( "analyzer" )
    Game24Analyzer sut;

    @MockBean
    @Qualifier( "solver" )
    Game24Solver mockSolver;

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
        SolutionSet noSolution = mock( SolutionSet.class );
        when( noSolution.getPuzzle() ).thenReturn( new Puzzle( 0, 0, 0, 0 ) );
        when( noSolution.getDifficultyRank() ).thenReturn( DifficultyRank.NO_SOLU );

        this.zeroTrick = mock( SolutionSet.class );
        when( zeroTrick.getDifficultyRank() ).thenReturn( DifficultyRank
            .ZERO_TRICK );
        when( zeroTrick.getPuzzle() ).thenReturn( zeroTrickPuzzle );

        // order is important: start with the most generic case
        when( mockSolver.solve( anyObject() ) ).thenReturn( noSolution );
        when( mockSolver.solve( eq( zeroTrickPuzzle ) ) ).thenReturn( zeroTrick );
        sut.analyze();
    }

    @Test
    public void testInjection()
    {
        assertNotNull( sut.getSolver() );
        assertTrue( sut.getSolver() == mockSolver );
    }

    @Test
    public void testNoSpringNoInjection()
    {
        Game24Analyzer sut = new Game24Analyzer();
        assertNull( sut.getSolver() );
        // -1 is the default max number before injection
        assertThat( sut.getMaxNumber(), is( -1 ) );
    }

    @Test
    public void testGetSolutionSetByDifficulty() throws Exception
    {
        SolutionSet ss = sut.getSolutionSetByDifficulty(
            DifficultyRank.ZERO_TRICK );
        assertThat( ss.getDifficultyRank(), is( DifficultyRank.ZERO_TRICK ) );
        assertThat( ss, is( zeroTrick ) );
    }
}
