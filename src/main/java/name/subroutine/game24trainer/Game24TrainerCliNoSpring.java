package name.subroutine.game24trainer;

import name.subroutine.game24trainer.solverimpl.Game24SolverImplRosetta;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation
    .AnnotationConfigApplicationContext;

import java.util.List;

/**
 * This is just an example of how to run a Spring Boot application without
 * Spring
 */
public class Game24TrainerCliNoSpring
{
    public static void main( String[] args ) throws Exception
    {
        Game24Solver solver = new Game24SolverImplRosetta();
        Game24Analyzer analyzer = new Game24Analyzer( solver );
        analyzer.setMaxNumber( 24 );

        analyzer.analyze();

        List<SolutionSet> ssl = analyzer.getAllSolutionSetsByDifficulty( DifficultyRank.FINAL_DIV_2 );
        for( SolutionSet ss : ssl ) {
            System.out.println( ss.getPuzzle() );
        }
        System.out.println( ssl.size() );
        System.out.println( analyzer.getMaxNumber() );
    }
}
