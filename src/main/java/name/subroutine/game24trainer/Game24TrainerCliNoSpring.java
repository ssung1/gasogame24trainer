package name.subroutine.game24trainer;

import name.subroutine.game24trainer.puzzle.DifficultyRank;
import name.subroutine.game24trainer.puzzle.SolutionSet;
import name.subroutine.game24trainer.solverimpl.Game24SolverImplRosetta;
import name.subroutine.game24trainer.sourceimpl.Game24PuzzleSourceImplCards;
import name.subroutine.game24trainer.sourceimpl.Game24PuzzleSourceImplMax;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * This is just an example of how to run a Spring Boot application without
 * Spring
 */
public class Game24TrainerCliNoSpring
{
    public static String booleanString( boolean value )
    {
        if( value ) {
            return "X";
        }
        else {
            return " ";
        }
    }

    public static void main( String[] args ) throws Exception
    {
        final int max = 4;
        PrintWriter pw = new PrintWriter( System.out, true );

        Game24Solver solver = new Game24SolverImplRosetta();
        Game24PuzzleSource source = new Game24PuzzleSourceImplCards();
        Game24Analyzer analyzer = new Game24Analyzer( solver, source );

        analyzer.analyze();

        List<SolutionSet> ssl = analyzer.getSolutionSetList();
        pw.print( "Puzzle" );
        pw.print( "," );
        pw.print( "DIGITS" );
        pw.print( "," );
        pw.print( "DOTS" );
        pw.print( "," );
        pw.print( "PACK" );
        pw.print( "," );
        pw.print( "PLAYER" );
        pw.print( "," );
        pw.print( DifficultyRank.FINAL_MUL );
        pw.print( "," );
        pw.print( DifficultyRank.FINAL_MUL_2 );
        pw.print( "," );
        pw.print( DifficultyRank.FINAL_ADD );
        pw.print( "," );
        pw.print( DifficultyRank.FINAL_ADD_2 );
        pw.print( "," );
        pw.print( DifficultyRank.FINAL_DIV );
        pw.print( "," );
        pw.print( DifficultyRank.FINAL_DIV_2 );
        pw.print( "," );
        pw.print( DifficultyRank.FRAC );
        pw.print( "," );
        pw.print( DifficultyRank.ZERO_TRICK );
        pw.print( "," );
        pw.print( DifficultyRank.DIST_PROP );
        pw.print( "," );
        pw.print( DifficultyRank.ALMOST_DIST_PROP );
        pw.print( "," );
        pw.print( DifficultyRank.ADD_SUB );
        pw.println();
        for( SolutionSet ss : ssl ) {
            pw.print( ss.getPuzzle() );
            pw.print( "," );
            pw.print( ss.getPuzzle().getDigits() );
            pw.print( "," );
            pw.print( ss.getPuzzle().getDots() );
            pw.print( "," );
            pw.print( ss.getPuzzle().getPack() );
            pw.print( "," );
            pw.print( ss.getPuzzle().getPlayer() );
            pw.print( "," );
            pw.print( booleanString( ss.hasFinalMul() ) );
            pw.print( "," );
            pw.print( booleanString( ss.hasFinalMulTwoByTwo() ) );
            pw.print( "," );
            pw.print( booleanString( ss.hasFinalAdd() ) );
            pw.print( "," );
            pw.print( booleanString( ss.hasFinalAddTwoByTwo() ) );
            pw.print( "," );
            pw.print( booleanString( ss.hasFinalDiv() ) );
            pw.print( "," );
            pw.print( booleanString( ss.hasFinalDivTwoByTwo() ) );
            pw.print( "," );
            pw.print( booleanString( ss.hasFraction() ) );
            pw.print( "," );
            pw.print( booleanString( ss.hasZeroTrick() ) );
            pw.print( "," );
            pw.print( booleanString( ss.hasDistProp() ) );
            pw.print( "," );
            pw.print( booleanString( ss.hasAlmostDistProp() ) );
            pw.print( "," );
            pw.print( booleanString( ss.hasAddSub() ) );

            pw.println();
        }
        pw.close();
    }
}
