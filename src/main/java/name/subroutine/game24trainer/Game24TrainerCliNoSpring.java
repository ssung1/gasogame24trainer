package name.subroutine.game24trainer;

import name.subroutine.game24trainer.solverimpl.Game24SolverImplRosetta;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation
    .AnnotationConfigApplicationContext;

/**
 * This is just an example of how to run a Spring Boot application without
 * Spring
 */
public class Game24TrainerCliNoSpring
{
    public static void main( String[] args ) throws Exception
    {
        Game24Solver solver = new Game24SolverImplRosetta();
        Game24Analyzer analyzer = new Game24Analyzer();

        System.out.println( analyzer.getMaxNumber() );
    }
}
