package name.subroutine.game24trainer.sourceimpl;

import name.subroutine.game24trainer.Game24PuzzleSource;
import name.subroutine.game24trainer.puzzle.Puzzle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * These puzzles are from the actual published game cards
 *
 * The puzzles are separated into these categories
 *
 * - single digit, double digit
 * - pack 1, pack 2
 * - player 1, player 2
 */
@Service("cards")
public class Game24PuzzleSourceImplCards implements Game24PuzzleSource
{
    // --- single digit ---
    public final String[] singleDot1Pack1Player1 = {
        "1 4 8 8", "32 - 8",
        "3 7 8 8", "24 x 1",
        "1 1 2 9", "8 x 3 ",
        "1 3 3 4", "6 x 4 ",
        "1 5 8 8", "8 x 3 ",
        "3 3 4 8", "6 x 4 ",
        "2 3 3 6", "6 x 4 ",
        "1 2 5 9", "8 x 3 ",
        "1 2 6 8", "8 x 3 ",
        "1 2 2 7", "6 x 4 ",
        "4 6 7 7", "6 x 4 ",
        "1 2 4 4", "6 x 4 ",
    };
    public final String[] singleDot3Pack1Player1 = {
        "2 3 3 5", "12 x 2 ",
        "2 3 8 9", "6 x 4  ",
        "2 2 5 8", "26 - 2 ",
        "2 3 5 7", "22 + 2 ",
        "1 4 6 8", "8 x 3  ",
        "5 7 8 9", "8 x 3  ",
        "3 4 7 7", "21 + 3 ",
        "5 8 8 9", "32 - 8 ",
        "2 5 8 8", "40 - 16",
        "2 2 5 7", "14 + 10",
        "2 5 7 9", "26 - 2 ",
        "2 6 6 7", "30 - 6 ",
    };
    public final String[] singleDot3Pack1Player2 = {
        "2 3 3 5", "12 x 2 ",
        "2 3 8 9", "6 x 4  ",
        "2 2 5 8", "26 - 2 ",
        "2 3 5 7", "22 + 2 ",
        "1 4 6 8", "8 x 3  ",
        "5 7 8 9", "8 x 3  ",
        "3 4 7 7", "21 + 3 ",
        "5 8 8 9", "32 - 8 ",
        "2 5 8 8", "40 - 16",
        "2 2 5 7", "14 + 10",
        "2 5 7 9", "26 - 2 ",
        "2 6 6 7", "30 - 6 ",
    };
    public final String[] singleDot3Pack2Player1 = {
        "1 4 6 9", "4 x 6  ",
        "2 2 3 5", "8 x 3  ",
        "1 4 5 8", "6 x 4  ",
        "2 3 7 9", "8 x 3  ",
        "2 5 5 8", "3 x 8  ",
        "2 4 4 5", "28 - 4 ",
        "2 4 6 8", "3 x 8  ",
        "3 4 4 9", "36 - 12",
        "4 4 6 8", "6 x 4  ",
        "5 7 8 8", "16 + 8 ",
        "2 7 8 9", "32 - 8 ",
        "1 5 6 7", "23 + 1 ",
    };
    public final String[] singleDot3Pack2Player2 = {
        "4 4 8 9", "32 - 8",
        "3 5 7 8", "29 - 5",
        "3 3 6 8", "3 x 8 ",
        "2 3 6 7", "21 + 3",
        "2 6 8 9", "3 x 8 ",
        "1 3 8 8", "16 + 8",
        "2 3 6 8", "26 - 2",
        "2 5 6 9", "15 + 9",
        "1 4 6 7", "4 x 6 ",
        "3 3 3 5", "9 + 15",
        "2 4 5 5", "20 + 4",
        "3 5 8 9", "19 + 5",
    };

    public List<Puzzle> convertPuzzleList( String[] rawPuzzle )
    {
        ArrayList<Puzzle> result = new ArrayList<>();
        for( int i = 0; i < rawPuzzle.length; i += 2 ) {
            result.add( new Puzzle( rawPuzzle[i] ) );
        }
        return result;
    }

    public List<Puzzle> getPuzzleList()
    {
        String[][] category = {
            singleDot3Pack1Player1,
            singleDot3Pack1Player2,
            singleDot3Pack2Player1,
            singleDot3Pack2Player2
        };

        ArrayList<Puzzle> result = new ArrayList<>();

        for( String[] c : category ){
            result.addAll( convertPuzzleList( c ) );
        }

        return result;
    }
}
