package name.subroutine.game24trainer;

import name.subroutine.game24trainer.solverimpl.Game24SolverImplRosetta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Game24TrainerConfig
{
    @Bean( name = "analyzer" )
    public Game24Analyzer getAnalyzer()
    {
        return new Game24Analyzer();
    }

    @Bean( name = "solver" )
    public Game24Solver getSolver()
    {
        return new Game24SolverImplRosetta();
    }
}
