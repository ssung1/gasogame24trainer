package name.subroutine.game24trainer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Game24Analyzer
{
    Game24SolverImplRosetta solver;

    public Game24Analyzer( Game24SolverImplRosetta solver )
    {
        this.solver = solver;
    }

    public Game24Analyzer()
    {
    }

    public void setSolver( Game24SolverImplRosetta solver )
    {
        this.solver = solver;
    }

    public Game24SolverImplRosetta getSolver()
    {
        return this.solver;
    }

    public void analyze()
    {
        int max = 3;
        for( int a = 1; a <= max; ++a ) {
            for( int b = a; b <= max; ++b ) {
                for( int c = b; c <= max; ++c ) {
                    for( int d = c; d <= max; ++d ) {
                        Puzzle p = new Puzzle( a, b, c, d );
                        SolutionSet ss = solver.solve( p );
                        System.out.print( ss.getPuzzle() );
                        System.out.print( " -- " );
                        System.out.println( ss.difficultyRank().getSymbol() );
                    }
                }
            }
        }
    }
}
