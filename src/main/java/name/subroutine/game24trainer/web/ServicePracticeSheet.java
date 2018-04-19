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
    @Autowired
    Game24Analyzer analyzer;

    private String createCell( final String location, final Integer content )
    {
        String cssClass;
        if( content == 9 ){
            cssClass = location + " nine";
        }
        else{
            cssClass = location;
        }
        return MessageFormat.format( "<td class=\"{0}\">{1}</td>",
            cssClass, content );
    }

    private String createCell( final String location, final String content )
    {
        return MessageFormat.format( "<td class=\"{0}\">{1}</td>",
                location, content );
    }

    private String card( Puzzle p )
    {
        StringBuffer dots = new StringBuffer();
        for( int i = 0; i < p.getDots(); ++i ){
            dots.append( "*" );
        }

        // a grid of 3 x 3
        String[] grid = {
            "top-left", dots.toString(),
            "top", "0",
            "top-right", "",
            "left", "1",
            "middle", "",
            "right", "2",
            "bottom-left", "",
            "bottom", "3",
            "bottom-right", "",
        };

        StringBuilder result = new StringBuilder();
        result.append( "<table class=\"card\">" );
        for( int i = 0; i < grid.length; i += 2 ){
            String location = grid[i];
            String contentKey = grid[i + 1];
            if( i % 6 == 0 ){
                result.append( "<tr>" );
            }
            if( !contentKey.isEmpty() ){
                if( contentKey.matches( "\\d+" ) ){
                    Integer number = Integer.valueOf( contentKey );
                    result.append( createCell( location,
                        p.getNumbers().get( number ) ) );
                }
                else{
                    result.append( createCell( location, contentKey ) );
                }
            }
            else{
                result.append( createCell( location, "" ) );
            }
            if( i % 6 == 4 ){
                result.append( "</tr>" );
            }
        }

        result.append( "</table>" );

        return result.toString();
    }

    @RequestMapping(
        value = "",
        method = RequestMethod.GET,
        produces = MediaType.TEXT_HTML_VALUE )
    public String practiceSheet() throws Exception
    {
        StringBuilder result = new StringBuilder();

        result.append( "<!DOCTYPE html>" );
        result.append( "<head>" );
        result.append( "    <style>" );
        result.append( "        table {" );
        result.append( "            border-collapse: collapse;" );
        result.append( "            border: solid 1pt;" );
        result.append( "        }" );
        result.append( "        table.card td {" );
        result.append( "            text-align: center;" );
        result.append( "            width: 100pt;" );
        result.append( "            height: 100pt;" );
        result.append( "            font-size: 80pt;" );
        result.append( "            padding: 0pt;" );
        result.append( "            margin: 0pt;" );
        result.append( "        }" );
        result.append( "        table.card td.nine {" );
        result.append( "            background-color: crimson;" );
        result.append( "        }" );
        result.append( "        table.card td.top-left {" );
        result.append( "            font-size: 20pt;" );
        result.append( "            text-align: left;" );
        result.append( "            vertical-align: top;" );
        result.append( "        }" );
        result.append( "        table.card td.left {" );
        result.append( "            transform: rotate(-0.25turn);" );
        result.append( "        }" );
        result.append( "        table.card td.middle {" );
        result.append( "            border: solid 1pt;" );
        result.append( "        }" );
        result.append( "        table.card td.right {" );
        result.append( "            transform: rotate(0.25turn);" );
        result.append( "        }" );
        result.append( "        table.card td.bottom {" );
        result.append( "            transform: rotate(0.5turn);" );
        result.append( "        }" );
        result.append( "        p.page-break {" );
        result.append( "            page-break-after: always;" );
        result.append( "        }" );
        result.append( "    </style>" );
        result.append( "</head>" );
        result.append( "<title>24 Game Practice</title>" );

        Puzzle[] puzzles = {
            // single pack 1 dot 3 player 1
            new Puzzle( " 2  3  3  5" ),
            new Puzzle( " 2  3  8  9" ),
//            new Puzzle( " 2  2  5  8" ),
//            new Puzzle( " 2  3  5  7" ),
//            new Puzzle( " 1  4  6  8" ),
//            new Puzzle( " 5  7  8  9" ),
//            new Puzzle( " 3  4  7  7" ),
//            new Puzzle( " 5  8  8  9" ),
//            new Puzzle( " 2  5  8  8" ),
//            new Puzzle( " 2  2  5  7" ),
//            new Puzzle( " 2  5  7  9" ),
//            new Puzzle( " 2  6  6  7" ),
//            // single pack 1 dot 3 player 2
//            new Puzzle( " 2  4  7  9" ),
//            new Puzzle( " 4  4  7  8" ),
//            new Puzzle( " 3  4  7  8" ),
//            new Puzzle( " 1  2  2  8" ),
//            new Puzzle( " 2  2  5  9" ),
//            new Puzzle( " 4  5  5  7" ),
//            new Puzzle( " 3  3  5  7" ),
//            new Puzzle( " 5  6  6  9" ),
//            new Puzzle( " 1  3  4  8" ),
//            new Puzzle( " 1  4  6  6" ),
//            new Puzzle( " 1  2  4  5" ),
//            new Puzzle( " 2  2  6  9" ),
//            // single pack 2 dot 3 player 1
//            new Puzzle( " 1  4  6  9" ),
//            new Puzzle( " 2  2  3  5" ),
//            new Puzzle( " 1  4  5  8" ),
//            new Puzzle( " 2  3  7  9" ),
//            new Puzzle( " 2  5  5  8" ),
//            new Puzzle( " 2  4  4  5" ),
//            new Puzzle( " 2  4  6  8" ),
//            new Puzzle( " 3  4  4  9" ),
//            new Puzzle( " 4  4  6  8" ),
//            new Puzzle( " 5  7  8  8" ),
//            new Puzzle( " 2  7  8  9" ),
//            new Puzzle( " 1  5  6  7" ),
//            // single pack 2 dot 3 player 1
//            new Puzzle( " 4  4  8  9" ),
//            new Puzzle( " 3  5  7  8" ),
//            new Puzzle( " 3  3  6  8" ),
//            new Puzzle( " 2  3  6  7" ),
//            new Puzzle( " 2  6  8  9" ),
//            new Puzzle( " 1  3  8  8" ),
//            new Puzzle( " 2  3  6  8" ),
//            new Puzzle( " 2  5  6  9" ),
//            new Puzzle( " 1  4  6  7" ),
//            new Puzzle( " 3  3  3  5" ),
//            new Puzzle( " 2  4  5  5" ),
//            new Puzzle( " 3  5  8  9" ),

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
            //result.append( card( puzzles[i] ) );
            result.append( card( analyzer.getSolutionSet().getPuzzle() ) );
            if( i % 2 != 0 ) {
                result.append( "<p class=\"page-break\">" );
            }
        }

        return result.toString();
    }
}

