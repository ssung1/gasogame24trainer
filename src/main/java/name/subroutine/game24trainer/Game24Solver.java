package name.subroutine.game24trainer;

import name.subroutine.game24trainer.puzzle.Puzzle;
import name.subroutine.game24trainer.puzzle.SolutionSet;

/**
 * Created by jojo on 5/30/2017.
 */
public interface Game24Solver {
    SolutionSet solve( Puzzle puzzle );
}
