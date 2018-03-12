package name.subroutine.game24trainer;

import name.subroutine.game24trainer.puzzle.DifficultyRank;
import name.subroutine.game24trainer.puzzle.SolutionSet;
import name.subroutine.game24trainer.solverimpl.Game24SolverImplRosetta;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * This is just an example of how to run a Spring Boot application without
 * Spring
 */
public class Game24TrainerCliNoSpring
{
    public static void main( String[] args ) throws Exception
    {
        final int max = 4;
        PrintWriter pw = new PrintWriter( System.out, true );

        Game24Solver solver = new Game24SolverImplRosetta();
        Game24Analyzer analyzer = new Game24Analyzer( solver );
        analyzer.setMaxNumber( max );

        analyzer.analyze();

        List<DifficultyRank> dl = Arrays.asList(
            DifficultyRank.ZERO_TRICK,
            DifficultyRank.DIST_PROP,
            DifficultyRank.ALMOST_DIST_PROP,
            DifficultyRank.FINAL_MUL,
            DifficultyRank.FINAL_MUL_2,
            DifficultyRank.ADD_SUB,
            DifficultyRank.FINAL_ADD,
            DifficultyRank.FINAL_ADD_2,
            DifficultyRank.FINAL_DIV,
            DifficultyRank.FINAL_DIV_2,
            DifficultyRank.FRAC,
            DifficultyRank.NO_SOLU,
            DifficultyRank.UNDEF
        );
        List<SolutionSet> ssl = analyzer.getAllSolutionSetsByDifficulty( DifficultyRank.FINAL_MUL );
        for( SolutionSet ss : ssl ) {
            pw.println( ss.getPuzzle() );
        }
        pw.close();
    }
}
