package name.subroutine.game24trainer.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import name.subroutine.game24trainer.*;
import net.minidev.json.JSONObject;
import org.assertj.core.internal.Diff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet
    .AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
    // just for parsing
    Symbol s = new Symbol();

    private final String algorithm = "mock solver";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier( "solver" )
    Game24Solver mockSolver;

    private SolutionSet getSampleSolutionSet()
    {
        Solution so = new Solution();
        so.expression = s.parse( "1 2 * 3 * 4 *" );

        SolutionSet ss = new SolutionSet();
        ss.setPuzzle( new Puzzle( 1, 2, 3, 4 ) );
        ss.add( so );
        ss.setAlgorithm( algorithm );

        return ss;
    }

    @Test
    public void v0SolutionText() throws Exception
    {
        SolutionSet ss = getSampleSolutionSet();
        when( mockSolver.solve( any( Puzzle.class ) ) ).thenReturn( ss );
        mockMvc.perform(
            get( "/rest/v0/solution" )
                .accept( MediaType.TEXT_PLAIN )
                .param( "puzzle", "1 2 3 4" ) )

            .andExpect(
                content().contentTypeCompatibleWith( MediaType.TEXT_PLAIN
                ) )
            .andExpect(
                content().string( containsString( "1  2  3  4" ) ) )
            .andExpect(
                content().string( containsString( "Solutions:" ) ) );
    }

    @Test
    public void v0SolutionJson() throws Exception
    {
        SolutionSet ss = getSampleSolutionSet();
        when( mockSolver.solve( any( Puzzle.class ) ) ).thenReturn( ss );

        mockMvc.perform(
            get( "/rest/v0/solution" )
                .accept( MediaType.APPLICATION_JSON_VALUE )
                .param( "p", "1 1 1 1" ) )

            .andExpect(
                content().contentTypeCompatibleWith(
                    MediaType.APPLICATION_JSON ) )
            .andExpect(
                jsonPath( "$.puzzle" ).value(
                    containsString( "1  2  3  4" ) ) )
            .andExpect(
                jsonPath( "$.difficultyRank" ).value( "x" ) );
    }

    @Test
    public void v0SolutionJsonPlumbing() throws Exception
    {
        SolutionSet ss = getSampleSolutionSet();
        when( mockSolver.solve( any( Puzzle.class ) ) ).thenReturn( ss );

        String exp = "$.solutionSet[0].expression[%d].value";

        mockMvc.perform(
            get( "/rest/v0/solution" )
                .accept( MediaType.APPLICATION_JSON_VALUE )
                .param( "p", "1 1 1 1" )
                .param( "plumbing", "t" ) )

            .andExpect(
                content().contentTypeCompatibleWith(
                    MediaType.APPLICATION_JSON ) )
            .andExpect(
                jsonPath( "$.puzzle.numbers[0]" ).value( 1 ) )
            .andExpect(
                jsonPath( "$.algorithm" ).value( algorithm ) )
            .andExpect(
                jsonPath( "$.difficultyRank.symbol" ).value( "x" ) )
            .andExpect(
                jsonPath( "$.hasFinalMul" ).value( true ) )
            .andExpect(
                jsonPath( "$.hasFinalMulTwoByTwo" ).value( false ) )
            .andExpect(
                jsonPath( "$.hasFinalAdd" ).value( false ) )
            .andExpect(
                jsonPath( "$.hasFinalAddTwoByTwo" ).value( false ) )
            .andExpect(
                jsonPath( "$.hasFinalDiv" ).value( false ) )
            .andExpect(
                jsonPath( "$.hasFinalDivTwoByTwo" ).value( false ) )
            .andExpect(
                jsonPath( "$.hasFraction" ).value( false ) )
            .andExpect(
                jsonPath( exp, 0 ).value( 1 ) )
            .andExpect(
                jsonPath( exp, 1 ).value( 2 ) )
            .andExpect(
                jsonPath( exp, 2 ).value( "*" ) );
    }

    @Test
    public void v0Puzzle() throws Exception
    {
        // cannot mock SolutionSet because it is going to be
        // serialized into JSON later
        SolutionSet finalAdd = new SolutionSet();
        Puzzle finalAddPuzzle = Game24Puzzles.finalAdd;

        Solution solution = new Solution();
        solution.setExpression( s.parse( "10 11 9 - / 19 +" ) );

        finalAdd.add( solution );
        finalAdd.setPuzzle( finalAddPuzzle );

        SolutionSet noSolution = mock( SolutionSet.class );
        when( noSolution.getPuzzle() ).thenReturn( new Puzzle( 0, 0, 0, 0 ) );
        when( noSolution.getDifficultyRank() ).thenReturn(
            DifficultyRank.NO_SOLU );

        when( mockSolver.solve( anyObject() ) ).thenReturn( noSolution );
        when( mockSolver.solve( eq( finalAddPuzzle ) ) ).thenReturn(
            finalAdd );

        mockMvc.perform(
            get( "/rest/v0/puzzle" )
                .accept( MediaType.APPLICATION_JSON_VALUE )
                .param( "d", DifficultyRank.FINAL_ADD.getName() )
                .param( "page", "1" )
                .param( "size", "10" ) )
            .andExpect(
                content().contentTypeCompatibleWith(
                    MediaType.APPLICATION_JSON ) )
            .andExpect(
                jsonPath( "$.puzzle.numbers" ).value( is( finalAddPuzzle
                    .getNumbers() ) ) )
            .andExpect(
                jsonPath( "$.difficultyRank.symbol" ).value(
                    is( DifficultyRank.FINAL_ADD.getSymbol() ) ) );
    }
}
