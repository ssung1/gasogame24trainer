package name.subroutine.game24trainer;

import name.subroutine.game24trainer.solverimpl.Game24SolverImplRosetta;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation
    .AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
public class Game24AnalyzerTests
{
    AnnotationConfigApplicationContext context;
    Game24Analyzer sut;
    Game24SolverImplRosetta mockSolver;
    Symbol symbol = new Symbol();

    @Bean( name = "analyzer" )
    public Game24Analyzer getAnalyzer()
    {
        Game24Analyzer result = new Game24Analyzer();
        result.setMaxNumber( 5 );
        return result;
    }

    @Bean( name = "solver" )
    public Game24SolverImplRosetta getMockSolver()
    {
        return mock( Game24SolverImplRosetta.class );
    }

    @Before
    public void setUp()
    {
        context = new AnnotationConfigApplicationContext(
            Game24AnalyzerTests.class );
        sut = (Game24Analyzer)context.getBean( "analyzer" );
        mockSolver = sut.getSolver();
    }

    @After
    public void tearDown()
    {
        context.close();
    }

    @Test
    public void testInjection()
    {
        assertNotNull( sut.getSolver() );
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

        Game24SolverImplRosetta ms = sut.getSolver();
        when( mockSolver.solve( anyObject() ) ).thenReturn( sample );
        sut.analyze();

        System.out.println( sut.getMaxNumber() );
    }
}
