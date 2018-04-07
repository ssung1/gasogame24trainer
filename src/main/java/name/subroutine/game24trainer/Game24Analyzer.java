package name.subroutine.game24trainer;

import name.subroutine.game24trainer.puzzle.DifficultyRank;
import name.subroutine.game24trainer.puzzle.Puzzle;
import name.subroutine.game24trainer.puzzle.SolutionSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// the @Component annotation is optional; it causes the object to be
// instantiated once in addition to any of the @Bean definitions in the
// config
// @Component
public class Game24Analyzer
{
    final Logger logger = LoggerFactory.getLogger( getClass() );

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

    @PostConstruct
    public void init()
    {
        if( autoAnalyze ) {
            logger.info( "Starting to analyze puzzles with a max of " +
                getMaxNumber() );
            analyze();
        }
    }

    public Game24Solver getSolver()
    {
        return this.solver;
    }

    @Value( "${max.number:24}" )
    private int maxNumber = -1;

    @Value( "${auto.analyze:true}" )
    private boolean autoAnalyze = true;

    private List<SolutionSet> solutionSetList;
    private final Map<DifficultyRank,List<SolutionSet>> solutionSetListByRank =
        new HashMap<>();
    private boolean analysisDone = false;

    private SolutionSet addToMapByRank( SolutionSet sol )
    {
        DifficultyRank rank = sol.getDifficultyRank();
        List<SolutionSet> ss = solutionSetListByRank.get( rank );
        if( ss == null ) {
            ss = new ArrayList<>();
            solutionSetListByRank.put( rank, ss );
        }
        ss.add( sol );
        return sol;
    }

    public int getPuzzleListInitSize( int maxNumber )
    {
        // Math: we are calculating the number of combinations
        // with repeatable elements
        //
        // In this case, we are choosing 4 out of maxNumber
        //
        // So the answer is C( 4 + max - 1, max - 1 )

        // Calculatiing C( n, k )
        int n = 4 + maxNumber - 1;
        int k = maxNumber - 1;

        int result = 1;
        // multiply n * ( n - 1 ) * ( n - 2 ) ... k times
        for( int i = 0; i < k; ++i ) {
            result = result * (n - i);
        }
        // divide by 1 * 2 * 3 ... * k
        // (starting at 2 because dividing by 1 has no effect)
        for( int i = 2; i <= k; ++i ) {
            result = result / i;
        }
        return result;
    }
    
    public List<Puzzle> getPuzzleList()
    {
        int max = this.getMaxNumber();
        List<Puzzle> puzzleList = new ArrayList<>( 17550 );
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
        return puzzleList;
    }

    public void analyze()
    {
        analysisDone = false;
        int max = this.getMaxNumber();
        List<Puzzle> puzzleList = new ArrayList<>( 17550 );
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
            puzzleList.parallelStream().map( solver::solve )
            .filter( SolutionSet::hasSolution )
            .map( this::addToMapByRank );
        this.solutionSetList = sss.collect( Collectors.toList() );
        analysisDone = true;
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

    public List<SolutionSet> getAllSolutionSetsByDifficulty(
        DifficultyRank di ) throws Exception
    {
        return solutionSetListByRank.get( di );
    }

    public SolutionSet getSolutionSetByDifficulty( DifficultyRank di )
        throws Exception
    {
        if( !analysisDone ) {
            throw new Exception( "Still waiting for analysis; please try later" );
        }
        long start = System.currentTimeMillis();
        List<SolutionSet> list = getAllSolutionSetsByDifficulty( di );
        int randomIndex = ThreadLocalRandom.current().nextInt( list.size() );
        SolutionSet result = list.get( randomIndex );
        long stop = System.currentTimeMillis();

        logger.info( "Time took to get a puzzle: " + (stop - start) + "ms" );

        return result;
    }

    public SolutionSet getSolutionSet() throws Exception
    {
        if( !analysisDone ) {
            throw new Exception( "Still waiting for analysis; please try later" );
        }
        List<SolutionSet> list = getSolutionSetList();
        return list.get( ThreadLocalRandom.current().nextInt( list.size() ) );
    }
}
