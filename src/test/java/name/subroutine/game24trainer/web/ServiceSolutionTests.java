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
        // N.B. jsoup can be useful for asserting HTML content
        mockMvc.perform(
            get( "/v0/solution" )
                .accept( MediaType.TEXT_PLAIN )
                .param( "puzzle", "1 1 1 1" ) )

            .andExpect( content().contentTypeCompatibleWith( MediaType.TEXT_PLAIN ) )
            .andExpect( content().string( containsString( "1  1  1  1" ) ) );
    }
}
