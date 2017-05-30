package name.subroutine.game24trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Game24Analyzer
{
    @Autowired
    @Qualifier( "solver" )
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

        int max = 5;
        List<Puzzle> puzzleList = new ArrayList<>( 17550 );
        long timeStart = System.currentTimeMillis();
        for( int a = 1; a <= max; ++a ) {
            for( int b = a; b <= max; ++b ) {
                for( int c = b; c <= max; ++c ) {
                    for( int d = c; d <= max; ++d ) {
                        Puzzle p = new Puzzle( a, b, c, d );
                        puzzleList.add( p );
                    }
                }
            }
        }
        Stream<SolutionSet> sss =
            puzzleList.parallelStream().map( solver::solve );
        Stream<String> sst =
            sss.parallel().map( ss -> {
                StringBuilder sb = new StringBuilder();
                sb.append( ss.getPuzzle() );
                sb.append( "," );
                sb.append( ss.hasFinalMul() );
                sb.append( "," );
                sb.append( ss.hasFinalMulTwoByTwo() );
                sb.append( "," );
                sb.append( ss.hasFinalAdd() );
                sb.append( "," );
                sb.append( ss.hasFinalAddTwoByTwo() );
                sb.append( "," );
                sb.append( ss.hasFinalDiv() );
                sb.append( "," );
                sb.append( ss.hasFinalDivTwoByTwo() );
                sb.append( "," );
                sb.append( ss.hasFraction() );
                sb.append( "," );
                sb.append( ss.hasSolution() );
                sb.append( "," );

                String rank = ss.getDifficultyRank().getSymbol();
                if( "+".equals( rank ) ) {
                    rank = "\"+\"";
                }
                sb.append( rank );
                return sb.toString();
            } );

        Object[] result = sst.toArray();
        Arrays.sort( result );
        for( Object s : result ){
            ps.println( s );
        }
        long timeStop = System.currentTimeMillis();

        System.out.println( "Time: " + (timeStop - timeStart) );

        os.close();
    }
}
