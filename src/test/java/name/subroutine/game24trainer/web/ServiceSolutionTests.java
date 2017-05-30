package name.subroutine.game24trainer.web;


import name.subroutine.game24trainer.Game24Analyzer;
import name.subroutine.game24trainer.Game24SolverImplRosetta;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet
    .AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request
    .MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result
    .MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result
    .MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServiceSolutionTests
{
    @Autowired
    private MockMvc mockMvc;

//    @Bean( name = "analyzer" )
//    public Game24Analyzer getAnalyzer()
//    {
//        Game24Analyzer result = new Game24Analyzer();
//        result.setMaxNumber( 5 );
//        return result;
//    }
//
//    @Bean( name = "solver" )
//    public Game24SolverImplRosetta getMockSolver()
//    {
//        return mock( Game24SolverImplRosetta.class );
//    }


    @Test
    public void v0SolutionText() throws Exception
    {
        mockMvc.perform(
            get( "/v0/solution" )
                .accept( MediaType.TEXT_PLAIN )
                .param( "puzzle", "1 1 1 1" ) )

            .andExpect(
                content().contentTypeCompatibleWith( MediaType.TEXT_PLAIN ) )
            .andExpect(
                content().string( containsString( "1  1  1  1" ) ) );
    }

    @Test
    public void v0SolutionJson() throws Exception
    {
        mockMvc.perform(
            get( "/v0/solution" )
                .accept( MediaType.APPLICATION_JSON_VALUE )
                .param( "puzzle", "1 1 1 1" ) )

            .andExpect(
                content().contentTypeCompatibleWith(
                    MediaType.APPLICATION_JSON ) )
            .andExpect(
                jsonPath( "$.puzzle" ).value(
                    containsString( "1  1  1  1" ) ) )
            .andExpect(
                jsonPath( "$.difficultyRank" ).value( "N" ) );
    }

    @Test
    public void v0Puzzle() throws Exception
    {
        mockMvc.perform(
            get( "/v0/puzzle" )
                .accept( MediaType.APPLICATION_JSON_VALUE )
                .param( "difficultyRank", "!!!TODO!!!" )
                .param( "page", "1" )
                .param( "size", "10" ) )

            .andExpect(
                content().contentTypeCompatibleWith(
                    MediaType.APPLICATION_JSON ) )
            .andExpect(
                jsonPath( "$.puzzle" ).value(
                    containsString( "1  1  1  1" ) ) )
            .andExpect(
                jsonPath( "$.difficultyRank" ).value( "N" ) );
    }
}
