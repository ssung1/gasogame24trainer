package name.subroutine.game24trainer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource( "classpath:/application.properties" )
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
