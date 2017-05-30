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
        //ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //Game24Analyzer analyzer = (Game24Analyzer)context.getBean( "analyzer" );
        ApplicationContext context = new AnnotationConfigApplicationContext( Game24TrainerConfig.class );
        //Game24Analyzer analyzer = context.getBean( Game24Analyzer.class );

        Game24Solver solver = new Game24SolverImplRosetta();
        Game24Analyzer analyzer = new Game24Analyzer();
        //analyzer.

        //System.out.println( analyzer.getMaxNumber() );
    }
}
