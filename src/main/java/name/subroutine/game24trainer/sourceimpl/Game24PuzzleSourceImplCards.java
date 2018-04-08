package name.subroutine.game24trainer.sourceimpl;

import name.subroutine.game24trainer.Game24PuzzleSource;
import name.subroutine.game24trainer.puzzle.Puzzle;

import java.util.ArrayList;
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
public class Game24PuzzleSourceImplCards implements Game24PuzzleSource
{
    // --- single digit ---
    public final String[] singlePack1Player1 = {
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

    public List<Puzzle> getPuzzleList()
    {
        ArrayList<Puzzle> result = new ArrayList<>();
        for( int i = 0; i < singlePack1Player1.length; i += 2 ) {
            result.add( new Puzzle( singlePack1Player1[i] ) );
        }
        return result;
    }
}
