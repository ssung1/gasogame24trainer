package name.subroutine.game24trainer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Game24TrainerCli
{
    public static void main( String[] args ) throws Exception
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Game24SolverImplRosetta solver = (Game24SolverImplRosetta)context.getBean( "solver-rosetta" );

//        Puzzle p = new Puzzle( Lists.newA() );
//        p.
//        solver.solve();
    }
}
