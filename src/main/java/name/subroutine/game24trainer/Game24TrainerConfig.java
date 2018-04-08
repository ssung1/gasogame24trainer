package name.subroutine.game24trainer;

import name.subroutine.game24trainer.solverimpl.Game24SolverImplRosetta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Game24TrainerConfig
{
    // this is an example of using a @Configuration file to build a bean.
    // alternatively, we can add @Service or @Component to the
    // Game24SolverImplRosetta itself.
    @Bean( name = "solver" )
    public Game24Solver getSolver()
    {
        return new Game24SolverImplRosetta();
    }
}
