package name.subroutine.game24trainer;

import name.subroutine.game24trainer.puzzle.DifficultyRank;
import name.subroutine.game24trainer.puzzle.Puzzle;
import name.subroutine.game24trainer.puzzle.SolutionSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// the @Service annotation (extended from @Component annotation) tells
// Spring Boot to create a @Bean for this object so that we do not have to
// explicitly created a @Bean method to return this object
@Service
public class Game24Analyzer
{
    final Logger logger = LoggerFactory.getLogger( getClass() );

    private Game24Solver solver;

    public Game24PuzzleSource source;

    public Game24Analyzer(
        // use Qualifier to specify which method to get the bean from.
        // on the @Bean method, use @Bean( name = {qualifier} )
        @Qualifier( "solver" ) @Autowired Game24Solver solver,
        @Qualifier( "cards" ) @Autowired Game24PuzzleSource source )
    {
        this.solver = solver;
        this.source = source;
    }

    @PostConstruct
    public void init()
    {
        if( autoAnalyze ) {
            logger.info( "Starting to analyze {} puzzles",
                source.getPuzzleList().size() );
            analyze();
        }
    }

    public Game24Solver getSolver()
    {
        return this.solver;
    }

    @Value( "${auto.analyze:true}" )
    private boolean autoAnalyze = true;

    private List<SolutionSet> solutionSetList;
    private final Map<DifficultyRank, List<SolutionSet>> solutionSetListByRank =
        new HashMap<>();
    private final Map<Integer, List<SolutionSet>> solutionSetListByDot =
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

    private SolutionSet addToMapByDot( SolutionSet sol )
    {
        Integer dot = sol.getPuzzle().getDots();
        List<SolutionSet> ss = solutionSetListByDot.get( dot );
        if( ss == null ) {
            ss = new ArrayList<>();
            solutionSetListByDot.put( dot, ss );
        }
        ss.add( sol );
        return sol;
    }

    public List<Puzzle> getPuzzleList()
    {
        return source.getPuzzleList();
    }

    public void analyze()
    {
        analysisDone = false;
        List<Puzzle> puzzleList = getPuzzleList();
        Stream<SolutionSet> sss =
            puzzleList.parallelStream().map( solver::solve )
            .filter( SolutionSet::hasSolution )
            .map( this::addToMapByRank )
            .map( this::addToMapByDot );
        this.solutionSetList = sss.collect( Collectors.toList() );
        analysisDone = true;
    }

    public List<SolutionSet> getSolutionSetList()
    {
        if( this.solutionSetList == null ) {
            analyze();
        }
        return this.solutionSetList;
    }

    public List<SolutionSet> getSolutionSetListByDifficulty(
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
        List<SolutionSet> list = getSolutionSetListByDifficulty( di );
        int randomIndex = ThreadLocalRandom.current().nextInt( list.size() );
        SolutionSet result = list.get( randomIndex );

        return result;
    }

    public List<SolutionSet> getSolutionSetListByDot( int dot )
    {
        return solutionSetListByDot.get( dot );
    }

    public SolutionSet getSolutionSetByDot( int dot )
    {
        List<SolutionSet> list = getSolutionSetListByDot( dot );
        int randomIndex = ThreadLocalRandom.current().nextInt( list.size() );
        SolutionSet result = list.get( randomIndex );

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
