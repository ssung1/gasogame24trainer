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
    private final String[] singleDot1Pack1Player1 = {
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
    private final String[] singleDot1Pack1Player2 = {
        "1 2 6 6", "6 x 4 ",
        "3 7 7 7", "8 x 3 ",
        "1 6 8 8", "8 x 3 ",
        "1 5 7 8", "8 x 3 ",
        "1 1 4 7", "6 x 4 ",
        "1 5 5 9", "6 x 4 ",
        "1 1 4 8", "3 x 8 ",
        "3 4 5 5", "25 - 1",
        "1 7 8 9", "8 x 3 ",
        "1 2 3 4", "6 x 4 ",
        "1 2 5 6", "6 x 4 ",
        "6 6 6 6", "18 + 6",
    };
    private final String[] singleDot1Pack2Player1 = {
        "2 4 8 8", "",
        "2 2 4 5", "",
        "2 2 4 6", "",
        "4 4 5 8", "",
        "1 2 5 8", "",
        "1 1 5 6", "",
        "1 7 8 8", "",
        "2 4 4 8", "",
        "2 2 6 8", "",
        "3 4 5 7", "",
        "1 2 6 7", "",
        "4 5 7 8", "",
    };
    private final String[] singleDot1Pack2Player2 = {
        "1 6 8 9", "",
        "5 5 7 7", "",
        "4 8 8 9", "",
        "2 3 4 6", "",
        "4 4 8 8", "",
        "2 3 5 9", "",
        "4 6 6 8", "",
        "3 6 6 9", "",
        "5 6 6 7", "",
        "1 4 5 5", "",
        "3 4 6 8", "",
        "3 3 5 6", "",
    };
    private final String[] singleDot2Pack1Player1 = {
        "3 6 6 8", "8 x 3 ",
        "2 3 4 5", "6 x 4 ",
        "2 2 5 6", "8 x 3 ",
        "2 4 5 6", "26 - 2",
        "1 4 4 8", "28 - 4",
        "1 5 6 6", "30 - 6",
        "2 2 4 8", "8 x 3 ",
        "2 4 6 7", "20 + 4",
        "1 3 6 8", "6 x 4 ",
        "2 3 4 8", "6 x 4 ",
        "1 2 7 8", "6 x 4 ",
        "1 3 5 8", "8 x 3 ",
        "1 5 7 9", "6 x 4 ",
        "2 6 6 8", "6 x 4 ",
        "1 6 7 9", "8 x 3 ",
        "2 2 7 8", "8 x 3 ",
        "1 2 6 9", "8 x 3 ",
        "1 3 4 7", "20 + 4",
        "1 4 7 9", "8 x 3 ",
        "3 3 4 7", "6 x 4 ",
        "2 4 7 8", "28 - 4",
        "2 2 3 7", "8 x 3 ",
        "2 7 8 8", "8 x 3 ",
        "4 4 5 6", "6 x 4 ",
    };
    private final String[] singleDot2Pack1Player2 = {
        "1 4 4 6", "20 + 4",
        "1 5 6 9", "6 x 4 ",
        "2 3 5 6", "12 x 2",
        "1 4 4 9", "6 x 4 ",
        "5 6 7 8", "12 x 2",
        "1 2 3 6", "6 x 4 ",
        "2 5 6 6", "18 + 6",
        "3 5 6 8", "8 x 3 ",
        "1 4 4 7", "17 + 7",
        "2 3 4 7", "6 x 4 ",
        "4 7 8 8", "8 x 3 ",
        "2 4 8 9", "8 x 3 ",
        "1 1 6 9", "18 + 6",
        "2 3 3 7", "6 x 4 ",
        "4 5 8 9", "6 x 4 ",
        "3 5 5 7", "12 x 2",
        "1 2 3 7", "8 x 3 ",
        "1 3 3 7", "21 + 3",
        "1 2 4 7", "6 x 4 ",
        "2 3 6 9", "6 x 4 ",
        "2 5 6 8", "8 x 3 ",
        "2 6 6 9", "8 x 3 ",
        "2 4 5 7", "12 x 2",
        "2 5 5 9", "15 + 9",
    };
    private final String[] singleDot2Pack2Player1 = {
        "1 4 5 7", "",
        "7 8 8 9", "",
        "1 5 5 6", "",
        "3 8 8 9", "",
        "2 8 8 9", "",
        "3 4 4 7", "",
        "3 4 5 9", "",
        "2 7 7 8", "",
        "1 2 4 9", "",
        "3 3 7 8", "",
        "2 3 6 6", "",
        "3 5 6 7", "",
        "1 3 7 8", "",
        "1 3 5 6", "",
        "2 3 7 8", "",
        "5 5 6 8", "",
        "2 3 3 8", "",
        "3 4 5 6", "",
        "2 3 7 7", "",
        "2 2 3 6", "",
        "1 2 8 8", "",
        "1 3 6 6", "",
        "2 5 7 7", "",
        "3 3 5 9", "",
    };
    private final String[] singleDot2Pack2Player2 = {
        "2 4 6 9", "",
        "4 5 6 8", "",
        "4 5 7 7", "",
        "3 4 7 9", "",
        "1 3 5 9", "",
        "3 4 4 5", "",
        "2 2 6 7", "",
        "2 3 5 8", "",
        "5 6 6 8", "",
        "1 5 6 8", "",
        "2 3 4 4", "",
        "2 4 4 6", "",
        "5 6 7 7", "",
        "4 5 6 9", "",
        "2 2 8 9", "",
        "2 4 6 6", "",
        "1 3 6 7", "",
        "3 6 8 9", "",
        "1 3 5 7", "",
        "1 4 7 7", "",
        "2 2 4 7", "",
        "2 4 4 7", "",
        "2 6 7 8", "",
        "3 3 4 5", "",
    };
    private final String[] singleDot3Pack1Player1 = {
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
    private final String[] singleDot3Pack1Player2 = {
        "2 4 7 9", "15 + 9",
        "4 4 7 8", "28 - 4",
        "3 4 7 8", "16 + 8",
        "1 2 2 8", "3 x 8 ",
        "2 2 5 9", "12 x 2",
        "4 5 5 7", "6 x 4 ",
        "3 3 5 7", "8 x 3  ",
        "5 6 6 9", "54 - 30",
        "1 3 4 8", "6 x 4  ",
        "1 4 6 6", "18 + 6 ",
        "1 2 4 5", "6 x 4  ",
        "2 2 6 9", "12 x 2 ",
    };
    private final String[] singleDot3Pack2Player1 = {
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
    private final String[] singleDot3Pack2Player2 = {
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

    // --- double digit ---
    private final String[] doubleDot1Pack1Player1 = {
        " 2  6  8 24", "26 - 2",
        " 4 11 13 22", "20 + 4",
        " 9 13 20 22", "22 + 2",
        " 2  6 12 16", "22 + 2",
        " 1  3  3 17", "23 + 1",
        " 1  4  5 12", "12 x 2",
        " 6  6 16 20", "30 - 6",
        " 2 22 23 24", "24 x 1",
        " 1  6 12 16", "6 x 4 ",
        "11 14 22 23", "25 - 1",
        " 9 11 12 16", "25 - 1",
        " 3  4 11 13", "24 x 1",
    };
    private final String[] doubleDot1Pack1Player2 = {
        " 2  4  5 21", "22 + 2 ",
        " 6 15 22 23", "23 + 1 ",
        " 5  5 13 21", "34 - 10",
        " 2  3  9 14", "21 + 3 ",
        " 7  8 11 14", "25 - 1 ",
        " 2 12 21 21", "24 + 0 ",
        " 4 11 16 23", "28 - 4 ",
        " 1  3 11 15", "23 + 1 ",
        " 1  6  9 21", "21 + 3 ",
        " 3 12 21 22", "12 x 2 ",
        " 2 11 16 17", "22 + 2 ",
        " 4  4  5 21", "21 + 3 ",
    };
    private final String[] doubleDot1Pack2Player1 = {
        " 3  5  9 17", "",
        " 9 12 20 23", "",
        " 3  6 13 13", "",
        " 1  5 14 14", "",
        " 6  7  8 16", "",
        " 4 10 11 20", "",
        " 1 11 18 18", "",
        " 3  7 22 23", "",
        " 1 12 24 24", "",
        " 3  4 14 17", "",
        " 3  3  7 11", "",
        " 1  1  3 24", "",
    };
    private final String[] doubleDot1Pack2Player2 = {
        " 2  4 11 11", "",
        " 3  6 10 12", "",
        " 7 14 14 18", "",
        " 3 14 15 15", "",
        " 1  8 12 15", "",
        " 4  9 12 24", "",
        " 4  8 13 15", "",
        " 1  3 13 14", "",
        " 2 20 22 24", "",
        " 8 11 12 17", "",
        " 9 10 17 22", "",
        " 1  2  7 20", "",
    };
    private final String[] doubleDot2Pack1Player1 = {
        " 2  9 22 22", "22 + 2 ",
        " 6  9 12 18", "12 + 12",
        " 2  3 12 17", "25 - 1 ",
        "14 14 18 22", "28 - 4 ",
        " 5  5 22 23", "25 - 1 ",
        " 4 21 23 24", "21 + 3 ",
        " 7  9 13 22", "13 + 11",
        " 2 16 20 24", "20 + 4 ",
        " 5  6 11 14", "14 + 10",
        " 5 13 17 23", "23 + 1 ",
        " 4 14 18 20", "28 - 4 ",
        " 2  7 12 14", "14 + 10",
        " 9 17 21 23", "21 + 3 ",
        " 3  3  4 15", "15 + 9 ",
        "10 15 22 23", "25 - 1 ",
        "13 14 15 18", "28 - 4 ",
        "13 13 16 18", "26 - 2 ",
        " 7 10 14 16", "14 + 10",
        " 4  5 14 20", "14 + 10",
        " 1  3 17 22", "17 + 7 ",
        "11 13 16 17", "24 x 1 ",
        " 2  6 16 17", "34 - 10",
        " 4  5 16 23", "23 + 1 ",
        " 1  5  8 13", "25 - 1 ",
    };
    private final String[] doubleDot2Pack1Player2 = {
        " 1  3 15 18", "18 + 6 ",
        " 1  2  6 15", "30 - 6 ",
        " 7 10 13 14", "6 x 4  ",
        " 1  3  7 10", "30 - 6 ",
        " 3  6  9 13", "13 + 11",
        "15 15 18 24", "24 + 0 ",
        "16 16 22 24", "24 + 0 ",
        " 4  6  8 12", "12 x 2 ",
        "11 16 18 23", "12 x 2 ",
        " 5  6 17 23", "30 - 6 ",
        " 7  9 18 22", "22 + 2 ",
        " 4  9 11 23", "13 + 11",
        " 3  3  9 12", "12 x 2 ",
        "13 16 16 24", "24 + 0 ",
        " 3  6 11 21", "13 + 11",
        " 7  8  8 24", "24 + 0 ",
        " 9 12 22 23", "23 + 1 ",
        " 6  9 14 24", "24 x 1 ",
        " 9 12 17 24", "17 + 7 ",
        " 2  6 10 11", "13 + 11",
        "12 15 18 21", "27 - 3 ",
        " 6  7 21 22", "22 + 2 ",
        "14 17 17 24", "24 + 0 ",
        " 9 13 16 20", "20 + 4 ",
    };
    private final String[] doubleDot2Pack2Player1 = {
        " 5  8 16 24", "",
        " 2  5  9 10", "",
        " 2  7  9 13", "",
        " 2  5 17 18", "",
        " 1  3  8 16", "",
        " 2  2  8 17", "",
        " 3  9 20 23", "",
        " 2 12 16 22", "",
        " 2  8 11 17", "",
        " 3  3 16 23", "",
        " 7 12 20 24", "",
        " 2 11 12 14", "",
        " 3  3 14 21", "",
        " 3 10 22 24", "",
        " 6  6 12 13", "",
        " 1  5  7 10", "",
        "13 21 21 24", "",
        " 2  6 11 12", "",
        "14 16 17 18", "",
        "20 21 22 23", "",
        " 2  7  9 16", "",
        " 5  9 12 16", "",
        " 5  7 13 23", "",
        " 3 11 21 24", "",
    };
    private final String[] doubleDot2Pack2Player2 = {
        " 4 17 21 24", "",
        " 2 11 20 24", "",
        " 2 10 10 14", "",
        " 3  9 13 17", "",
        "10 12 23 23", "",
        " 1  4 18 24", "",
        " 6  6  8 16", "",
        "13 17 18 24", "",
        " 4 10 16 20", "",
        " 4  8  9 17", "",
        " 2  4 14 18", "",
        " 2  3  8 23", "",
        " 5  9 13 15", "",
        " 3  7 10 13", "",
        " 7 16 18 18", "",
        " 2  4 20 24", "",
        " 5 10 24 24", "",
        " 6  9 13 21", "",
        " 4  8 20 22", "",
        " 2  4  9 10", "",
        " 1  3 12 13", "",
        " 2  4  9 12", "",
        " 7  8 20 24", "",
        " 3  9 12 18", "",
    };
    private final String[] doubleDot3Pack1Player1 = {
        " 5  7 10 10", "14 + 10",
        " 6 20 21 22", "21 + 3 ",
        " 4  9 10 16", "144 / 6",
        " 7  9 16 20", "40 - 16",
        " 6  8 10 11", "30 - 6 ",
        "10 13 15 17", "34 - 10",
        " 2  2  7 10", "12 x 2 ",
        "10 10 12 20", "12 + 12",
        " 6 12 15 24", "30 - 6 ",
        " 2  2 18 22", "42 - 18",
        " 5  9 15 17", "8 x 3  ",
        " 3  4  9 20", "15 + 9 ",
    };
    private final String[] doubleDot3Pack1Player2 = {
        " 7 11 17 20", "20 + 4 ",
        " 5  7  9 12", "12 x 2 ",
        " 4  4  8 14", "56 - 32",
        " 9 11 20 22", "44 - 20",
        " 5  6  7 23", "6 x 4  ",
        " 2  2 21 22", "46 - 22",
        " 2  7 11 15", "15 + 9 ",
        "12 16 18 18", "18 + 6 ",
        " 5  9 13 22", "22 + 2 ",
        " 2  9 11 22", "35 - 11",
        " 5 11 13 18", "37 - 13",
        " 3  8 13 15", "37 - 13",
    };
    private final String[] doubleDot3Pack2Player1 = {
        " 3 10 14 16", "",
        " 2  2 16 21", "",
        " 7  7 13 18", "",
        " 3  9 14 21", "",
        " 2  2 14 21", "",
        " 2  2  9 21", "",
        " 7 11 12 24", "",
        " 8  9 16 18", "",
        " 6  9 15 15", "",
        " 2  9 10 14", "",
        " 1  4 12 24", "",
        " 2  3  5 16", "",
    };
    private final String[] doubleDot3Pack2Player2 = {
        " 6  6  9 13", "",
        " 8 16 18 23", "",
        " 3  5 10 21", "",
        "10 10 17 20", "",
        " 3  3 12 20", "",
        " 7  9 12 15", "",
        " 5  8  9 13", "",
        " 2 14 17 17", "",
        "10 11 13 17", "",
        " 6 10 15 20", "",
        " 1  1 22 23", "",
        " 5  6  6 10", "",
    };

    private List<Puzzle> convertPuzzleList( String[] rawPuzzle, int digits,
        int dots, int pack, int player )
    {
        ArrayList<Puzzle> result = new ArrayList<>();
        for( int i = 0; i < rawPuzzle.length; i += 2 ) {
            Puzzle newPuzzle = new Puzzle( rawPuzzle[i] );
            newPuzzle.setDigits( digits );
            newPuzzle.setDots( dots );
            newPuzzle.setPack( pack );
            newPuzzle.setPlayer( player );
            result.add( newPuzzle );
        }
        return result;
    }

    public List<Puzzle> getPuzzleList()
    {
        final int single = Puzzle.SINGLE;
        final int dubbl = Puzzle.DOUBLE;
        final int one = Puzzle.ONE;
        final int two = Puzzle.TWO;
        final int three = Puzzle.THREE;

        ArrayList<Puzzle> result = new ArrayList<>();

        // each digit-dot combination has four sets
        // we are choosing not to include digits and dots in the data array
        // because we need to save space for more important information
        result.addAll( convertPuzzleList(
            singleDot1Pack1Player1, single, one, one, one ) );
        result.addAll( convertPuzzleList(
            singleDot1Pack1Player2, single, one, one, two ) );
        result.addAll( convertPuzzleList(
            singleDot1Pack2Player1, single, one, two, one ) );
        result.addAll( convertPuzzleList(
            singleDot1Pack2Player2, single, one, two, two ) );

        result.addAll( convertPuzzleList(
            singleDot2Pack1Player1, single, two, one, one ) );
        result.addAll( convertPuzzleList(
            singleDot2Pack1Player2, single, two, one, two ) );
        result.addAll( convertPuzzleList(
            singleDot2Pack2Player1, single, two, two, one ) );
        result.addAll( convertPuzzleList(
            singleDot2Pack2Player2, single, two, two, two ) );

        result.addAll( convertPuzzleList(
            singleDot3Pack1Player1, single, three, one, one ) );
        result.addAll( convertPuzzleList(
            singleDot3Pack1Player2, single, three, one, two ) );
        result.addAll( convertPuzzleList(
            singleDot3Pack2Player1, single, three, two, one ) );
        result.addAll( convertPuzzleList(
            singleDot3Pack2Player2, single, three, two, two ) );

        result.addAll( convertPuzzleList(
            doubleDot1Pack1Player1, dubbl, one, one, one ) );
        result.addAll( convertPuzzleList(
            doubleDot1Pack1Player2, dubbl, one, one, two ) );
        result.addAll( convertPuzzleList(
            doubleDot1Pack2Player1, dubbl, one, two, one ) );
        result.addAll( convertPuzzleList(
            doubleDot1Pack2Player2, dubbl, one, two, two ) );

        result.addAll( convertPuzzleList(
            doubleDot2Pack1Player1, dubbl, two, one, one ) );
        result.addAll( convertPuzzleList(
            doubleDot2Pack1Player2, dubbl, two, one, two ) );
        result.addAll( convertPuzzleList(
            doubleDot2Pack2Player1, dubbl, two, two, one ) );
        result.addAll( convertPuzzleList(
            doubleDot2Pack2Player2, dubbl, two, two, two ) );

        result.addAll( convertPuzzleList(
            doubleDot3Pack1Player1, dubbl, three, one, one ) );
        result.addAll( convertPuzzleList(
            doubleDot3Pack1Player2, dubbl, three, one, two ) );
        result.addAll( convertPuzzleList(
            doubleDot3Pack2Player1, dubbl, three, two, one ) );
        result.addAll( convertPuzzleList(
            doubleDot3Pack2Player2, dubbl, three, two, two ) );
        return result;
    }
}
