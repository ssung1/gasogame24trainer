package name.subroutine.game24trainer.web;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet
    .AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
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
}
