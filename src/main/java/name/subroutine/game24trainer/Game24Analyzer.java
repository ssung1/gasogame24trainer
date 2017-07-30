package name.subroutine.game24trainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
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

    public Game24Solver getSolver()
    {
        return this.solver;
    }

    @Value( "${max.number:24}" )
    private int maxNumber = -1;

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

    public SolutionSet getSolutionSetByDifficulty( DifficultyRank di )
        throws Exception
    {
        if( !analysisDone ) {
            throw new Exception( "Still waiting for analysis; please try later" );
        }
        long start = System.currentTimeMillis();
        List<SolutionSet> list = solutionSetListByRank.get( di );
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
