package name.subroutine.game24trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Game24Analyzer
{
    @Autowired
    private Game24Solver solver;

    public Game24Analyzer()
    {
    }

    @Autowired
    public Game24Analyzer(
        @Qualifier( "solver" ) Game24Solver solver )
    {
        this.solver = solver;
    }

    public Game24Solver getSolver()
    {
        return this.solver;
    }

    @Value( "${max.number:24}" )
    private int maxNumber = -1;

    private List<SolutionSet> solutionSetList;

    public void analyze()
    {
//        OutputStream os = new FileOutputStream( "c:/tmp/game24solutions.csv" );
//        PrintStream ps = new PrintStream( os );

        int max = this.getMaxNumber();
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
        this.solutionSetList = sss.collect( Collectors.toList() );
//        Stream<String> sst =
//            this.solutionSetList.stream().parallel().map( ss -> {
//                StringBuilder sb = new StringBuilder();
//                sb.append( ss.getPuzzle() );
//                sb.append( "," );
//                sb.append( ss.hasZeroTrick() );
//                sb.append( "," );
//                sb.append( ss.hasFinalMul() );
//                sb.append( "," );
//                sb.append( ss.hasFinalMulTwoByTwo() );
//                sb.append( "," );
//                sb.append( ss.hasFinalAdd() );
//                sb.append( "," );
//                sb.append( ss.hasFinalAddTwoByTwo() );
//                sb.append( "," );
//                sb.append( ss.hasFinalDiv() );
//                sb.append( "," );
//                sb.append( ss.hasFinalDivTwoByTwo() );
//                sb.append( "," );
//                sb.append( ss.isFraction() );
//                sb.append( "," );
//                sb.append( ss.hasSolution() );
//                sb.append( "," );
//
//                String rank = ss.getDifficultyRank().getSymbol();
//                if( "+".equals( rank ) ) {
//                    rank = "\"+\"";
//                }
//                sb.append( rank );
//                return sb.toString();
//            } );
//
//        Object[] result = sst.toArray();
//        Arrays.sort( result );
//        for( Object s : result ){
//            ps.println( s );
//        }
//        long timeStop = System.currentTimeMillis();
//
//        System.out.println( "Time: " + (timeStop - timeStart) );
//
//        os.close();
    }

    public int getMaxNumber()
    {
        return maxNumber;
    }

    public void setMaxNumber( int maxNumber )
    {
        this.maxNumber = maxNumber;
    }

    public List<SolutionSet> getSolutionSetList()
    {
        if( this.solutionSetList == null ) {
            analyze();
        }
        return this.solutionSetList;
    }

    public SolutionSet getSolutionSetByDifficulty( DifficultyRank di )
    {
        List<SolutionSet> list = getSolutionSetList().stream()
            .filter( s -> s.getDifficultyRank() == di )
            .collect( Collectors.toList() );
        Collections.shuffle( list );
        return list.get( 0 );
    }

    public SolutionSet getSolutionSet()
    {
        List<SolutionSet> list = getSolutionSetList().stream()
            .filter( s -> s.getDifficultyRank() != DifficultyRank.NO_SOLU )
            .collect( Collectors.toList() );
        Collections.shuffle( list );
        return list.get( 0 );
    }
}
