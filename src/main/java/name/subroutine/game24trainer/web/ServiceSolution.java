package name.subroutine.game24trainer.web;

import name.subroutine.game24trainer.*;
import name.subroutine.game24trainer.solverimpl.Game24SolverImplRosetta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ServiceSolution
{
    Symbol symbol = new Symbol();
    @Autowired
    Game24SolverImplRosetta solver;

    @RequestMapping(
        value = "/v0/solution",
        method = RequestMethod.GET,
        produces = MediaType.TEXT_PLAIN_VALUE )
    public String solutionText(
        @RequestParam( name = "puzzle", required = true ) String puzzle )
        throws Exception
    {
        Symbol[] symbols = symbol.parse( puzzle );
        ArrayList<Integer> numbers = new ArrayList<>();
        for( Symbol s : symbols ) {
            name.subroutine.game24trainer.Number n =
                (name.subroutine.game24trainer.Number)s;
            numbers.add( Math.round( n.getValue() ) );
        }
        SolutionSet ss = solver.solve( new Puzzle( numbers ) );

        StringWriter sw = new StringWriter();
        PrintWriter pr = new PrintWriter( sw );

        pr.println( "Puzzle: " + ss.getPuzzle().toString() );
        pr.println( "Solutions: " );
        ss.getSolutionSet().forEach( s -> pr.println( s.toInfixString() ) );

        sw.close();
        return sw.toString();
    }

    @RequestMapping(
        value = "/v0/solution",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE )
    public SolutionSetJson solutionJson(
        @RequestParam( name = "puzzle", required = true ) String puzzle )
        throws Exception
    {
        List<Integer> ss = Arrays.stream( symbol.parse( puzzle ) )
            .map( s -> (name.subroutine.game24trainer.Number)s )
            .map( s -> Math.round( s.getValue() ) )
            .collect( Collectors.toList() );

        return new SolutionSetJson( solver.solve( new Puzzle( ss ) ) );
    }
}
