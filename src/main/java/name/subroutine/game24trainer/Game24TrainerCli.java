package name.subroutine.game24trainer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation
    .AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * This is an example of running the old Spring Framework application without
 * Spring Boot
 */
public class Game24TrainerCli
{
    public static void main( String[] args ) throws Exception
    {
        // we don't like using XML, not anymore...
        // ApplicationContext context =
        //     new ClassPathXmlApplicationContext("beans.xml");
        ApplicationContext context =
            new AnnotationConfigApplicationContext( Game24TrainerConfig.class );

        // if there are more than one bean of the same class, ask by name
        Game24Analyzer analyzer = (Game24Analyzer)context.getBean( "analyzer" );
        // otherwise, ask by type
        // Game24Analyzer analyzer = context.getBean( Game24Analyzer.class );

        // analyzer.analyze();

        System.out.println( analyzer.getPuzzleList().size() );
    }
}
