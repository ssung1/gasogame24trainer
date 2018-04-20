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
import java.util.*;
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
    public String practiceSheet(
        @RequestParam( required = false ) Integer oneDot,
        @RequestParam( required = false ) Integer twoDot,
        @RequestParam( required = false ) Integer threeDot
        ) throws Exception
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

        Integer[] dots = {
            1, oneDot,
            2, twoDot,
            3, threeDot
        };

        ArrayList<Puzzle> puzzles = new ArrayList<>();
        for( int i = 0; i < dots.length; i += 2 ){
            int dot = dots[i];
            Integer countForDot = dots[i + 1];
            if( countForDot == null ) continue;
            for( int j = 0; j < countForDot; ++j ) {
                puzzles.add( analyzer.getSolutionSetByDot( dot ).getPuzzle() );
            }
        }

        // by default, just throw in some random puzzles
        if( puzzles.isEmpty() ){
            for( int i = 0; i < 30; ++ i ) {
                puzzles.add( analyzer.getSolutionSet().getPuzzle() );
            }
        }

        Collections.shuffle( puzzles );

        for( int i = 0; i < puzzles.size(); ++i ) {
            result.append( card( puzzles.get( i ) ) );
            if( i % 2 != 0 ) {
                result.append( "<p class=\"page-break\">" );
            }
        }

        return result.toString();
    }
}

