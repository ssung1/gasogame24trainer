package name.subroutine.game24trainer;

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
        // 24 is the default max number
        assertThat( sut.getMaxNumber(), is( 24 ) );
    }

    @Test
    public void testAnalyze() throws Exception
    {
        SolutionSet sample = new SolutionSet();
        sample.setPuzzle( new Puzzle( 1, 1, 1, 1 ) );
        Solution sol = new Solution();
        sol.expression = symbol.parse( "1 1 1 1 * * *" );
        sample.add( sol );

        when( mockSolver.solve( anyObject() ) ).thenReturn( sample );
        sut.analyze();

        System.out.println( sut.getMaxNumber() );
    }
}
