package name.subroutine.game24trainer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
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

    public void analyze() throws Exception
    {
        OutputStream os = new FileOutputStream( "c:/tmp/game24solutions.csv" );
        PrintStream ps = new PrintStream( os );

        int max = 24;
        for( int a = 1; a <= max; ++a ) {
            for( int b = a; b <= max; ++b ) {
                for( int c = b; c <= max; ++c ) {
                    for( int d = c; d <= max; ++d ) {
                        Puzzle p = new Puzzle( a, b, c, d );
                        SolutionSet ss = solver.solve( p );
                        ps.print( ss.getPuzzle() );
                        ps.print( "," );
                        ps.print( ss.hasFinalMul() );
                        ps.print( "," );
                        ps.print( ss.hasFinalMulTwoByTwo() );
                        ps.print( "," );
                        ps.print( ss.hasFinalAdd() );
                        ps.print( "," );
                        ps.print( ss.hasFinalAddTwoByTwo() );
                        ps.print( "," );
                        ps.print( ss.hasFinalDiv() );
                        ps.print( "," );
                        ps.print( ss.hasFinalDivTwoByTwo() );
                        ps.print( "," );
                        ps.print( ss.hasFraction() );
                        ps.print( "," );
                        ps.print( ss.hasSolution() );
                        ps.print( "," );

                        String rank = ss.difficultyRank().getSymbol();
                        if( "+".equals( rank) ) {
                            rank = "\"+\"";
                        }
                        ps.println( rank );
                    }
                }
            }
        }

        os.close();
    }
}
