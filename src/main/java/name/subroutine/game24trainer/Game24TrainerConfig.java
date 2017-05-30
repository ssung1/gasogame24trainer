package name.subroutine.game24trainer;

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

    @Bean( name = "anotherAnalyzer" )
    public Game24Analyzer getAnotherAnalyzer()
    {
        return new Game24Analyzer();
    }

    @Bean( name = "solver" )
    public Game24SolverImplRosetta getSolver()
    {
        return new Game24SolverImplRosetta();
    }

    @Bean( name = "anotherSolver" )
    public Game24SolverImplRosetta getAnotherSolver()
    {
        return new Game24SolverImplRosetta();
    }
}
