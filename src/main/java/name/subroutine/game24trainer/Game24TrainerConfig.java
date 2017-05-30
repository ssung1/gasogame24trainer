package name.subroutine.game24trainer;

import name.subroutine.game24trainer.solverimpl.Game24SolverImplRosetta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Game24TrainerConfig
{
    @Value( "${max.number}" )
    String maxNumber;

    @Bean( name = "analyzer" )
    public Game24Analyzer getAnalyzer()
    {
        return new Game24Analyzer();
    }

    @Bean( name = "anotherAnalyzer" )
    public Game24Analyzer getAnotherAnalyzer()
    {
        Game24Analyzer result = new Game24Analyzer();
        try {
            result.setMaxNumber( Integer.parseInt( this.maxNumber ) );
        }
        catch( Exception ex ) {
            // oh wells
        }

        return result;
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
