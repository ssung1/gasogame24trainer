package name.subroutine.game24trainer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation
    .AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Game24TrainerCli
{
    public static void main( String[] args ) throws Exception
    {
        //ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //Game24Analyzer analyzer = (Game24Analyzer)context.getBean( "analyzer" );
        ApplicationContext context = new AnnotationConfigApplicationContext( Game24TrainerConfig.class );
        //Game24Analyzer analyzer = context.getBean( Game24Analyzer.class );
        Game24Analyzer analyzer = (Game24Analyzer)context.getBean( "analyzer" );
        analyzer.analyze();
    }
}
