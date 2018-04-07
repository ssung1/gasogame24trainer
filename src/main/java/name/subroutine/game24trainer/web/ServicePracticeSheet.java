package name.subroutine.game24trainer.web;

import name.subroutine.game24trainer.*;
import name.subroutine.game24trainer.puzzle.*;
import name.subroutine.game24trainer.puzzle.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping( "/practice_sheet.html" )
public class ServicePracticeSheet {
    private String card( Puzzle p )
    {
        Integer n0 = p.getNumbers().get( 0 );
        Integer n1 = p.getNumbers().get( 1 );
        Integer n2 = p.getNumbers().get( 2 );
        Integer n3 = p.getNumbers().get( 3 );
        String top = MessageFormat.format(
            "<td class=\"top\">{0}</td>", n0
        );
        String left = MessageFormat.format(
            "<tr><td class=\"left\">{0}</td>", n1
        );
        String right = MessageFormat.format(
            "<td class=\"right\">{0}</td></tr>", n2
        );
        String bottom = MessageFormat.format(
            "<td class=\"right\">{0}</td>", n3
        );

        StringBuilder result = new StringBuilder();
        result.append( "<table class=\"card\">" );

        result.append( "    <tr><td></td>" );
        result.append( top );
        result.append( "        <td></td></tr>" );
        result.append( left );
        result.append( "        <td></td>" );
        result.append( right );
        result.append( "    <tr><td></td>" );
        result.append( bottom );
        result.append( "        <td></td></tr>" );

        result.append( "</table>" );

        return result.toString();
    }

    @RequestMapping(
        value = "",
        method = RequestMethod.GET,
        produces = MediaType.TEXT_HTML_VALUE )
    public String practiceSheet()
    {
        StringBuilder result = new StringBuilder();

        result.append( "<!DOCTYPE html>" );
        result.append( "<head>" );
        result.append( "    <style>" );
        result.append( "        table {" );
        result.append( "        border-collapse: collapse;" );
        result.append( "        border: solid 1pt;" );
        result.append( "    }" );
        result.append( "        table.card td {" );
        result.append( "        text-align: center;" );
        result.append( "        width: 100pt;" );
        result.append( "        height: 100pt;" );
        result.append( "        font-size: 80pt;" );
        result.append( "    }" );
        result.append( "        p.page-break {" );
        result.append( "        page-break-after: always;" );
        result.append( "    }" );
        result.append( "    </style>" );
        result.append( "</head>" );
        result.append( "<title>24 Game Practice</title>" );

        Puzzle[] puzzles = {
            // single pack 1 dot 3 player 1
            new Puzzle( " 2  3  3  5" ),
            new Puzzle( " 2  3  8  9" ),
            new Puzzle( " 2  2  5  8" ),
            new Puzzle( " 2  3  5  7" ),
            new Puzzle( " 1  4  6  8" ),
            new Puzzle( " 5  7  8  9" ),
            new Puzzle( " 3  4  7  7" ),
            new Puzzle( " 5  8  8  9" ),
            new Puzzle( " 2  5  8  8" ),
            new Puzzle( " 2  2  5  7" ),
            new Puzzle( " 2  5  7  9" ),
            new Puzzle( " 2  6  6  7" ),
            // single pack 1 dot 3 player 2
            new Puzzle( " 2  4  7  9" ),
            new Puzzle( " 4  4  7  8" ),
            new Puzzle( " 3  4  7  8" ),
            new Puzzle( " 1  2  2  8" ),
            new Puzzle( " 2  2  5  9" ),
            new Puzzle( " 4  5  5  7" ),
            new Puzzle( " 3  3  5  7" ),
            new Puzzle( " 5  6  6  9" ),
            new Puzzle( " 1  3  4  8" ),
            new Puzzle( " 1  4  6  6" ),
            new Puzzle( " 1  2  4  5" ),
            new Puzzle( " 2  2  6  9" ),
            // single pack 2 dot 3 player 1
            new Puzzle( " 1  4  6  9" ),
            new Puzzle( " 2  2  3  5" ),
            new Puzzle( " 1  4  5  8" ),
            new Puzzle( " 2  3  7  9" ),
            new Puzzle( " 2  5  5  8" ),
            new Puzzle( " 2  4  4  5" ),
            new Puzzle( " 2  4  6  8" ),
            new Puzzle( " 3  4  4  9" ),
            new Puzzle( " 4  4  6  8" ),
            new Puzzle( " 5  7  8  8" ),
            new Puzzle( " 2  7  8  9" ),
            new Puzzle( " 1  5  6  7" ),
            // single pack 2 dot 3 player 1
            new Puzzle( " 4  4  8  9" ),
            new Puzzle( " 3  5  7  8" ),
            new Puzzle( " 3  3  6  8" ),
            new Puzzle( " 2  3  6  7" ),
            new Puzzle( " 2  6  8  9" ),
            new Puzzle( " 1  3  8  8" ),
            new Puzzle( " 2  3  6  8" ),
            new Puzzle( " 2  5  6  9" ),
            new Puzzle( " 1  4  6  7" ),
            new Puzzle( " 3  3  3  5" ),
            new Puzzle( " 2  4  5  5" ),
            new Puzzle( " 3  5  8  9" ),

//            // double pack 1 dot 3 player 1
//            new Puzzle( " 5  7 10 10" ),
//            new Puzzle( " 7  9 16 20" ),
//            new Puzzle( " 2  2  7 10" ),
//            new Puzzle( " 2  2 18 22" ),
//            new Puzzle( " 6 20 21 22" ),
//            new Puzzle( " 6  8 10 11" ),
//            new Puzzle( "10 10 12 20" ),
//            new Puzzle( " 5  9 15 17" ),
//            new Puzzle( " 4  9 10 16" ),
//            new Puzzle( "10 13 15 17" ),
//            new Puzzle( " 6 12 15 24" ),
//            new Puzzle( " 3  4  9 20" ),
//            // double pack 1 dot 3 player 2
//            new Puzzle( " 7 11 17 20" ),
//            new Puzzle( " 9 11 20 22" ),
//            new Puzzle( " 2  7 11 15" ),
//            new Puzzle( " 5  7  9 12" ),
//            new Puzzle( " 5  6  7 23" ),
//            new Puzzle( "12 16 18 18" ),
//            new Puzzle( " 5 11 13 18" ),
//            new Puzzle( " 4  4  8 14" ),
//            new Puzzle( " 2  2 21 22" ),
//            new Puzzle( " 5  9 13 22" ),
//            new Puzzle( " 3  8 13 15" ),
        };

        for( int i = 0; i < puzzles.length; ++i ) {
            result.append( card( puzzles[i] ) );
            if( i % 2 != 0 ) {
                result.append( "<p class=\"page-break\">" );
            }
        }

        return result.toString();
    }
}

