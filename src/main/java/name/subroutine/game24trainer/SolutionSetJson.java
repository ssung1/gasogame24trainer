package name.subroutine.game24trainer;

import name.subroutine.game24trainer.puzzle.SolutionSet;

public class SolutionSetJson
{
    private final String puzzle;
    private final String[] solutionSet;
    private final String algorithm;
    private final int count;
    private final String hasFinalDiv;
    private final String hasFinalDivTwoByTwo;
    private final String hasFinalMul;
    private final String hasFinalMulTwoByTwo;
    private final String hasFinalAdd;
    private final String hasFinalAddTwoByTwo;
    private final String hasFraction;
    private final String difficultyRank;

    public SolutionSetJson( SolutionSet ss )
    {
        this.puzzle = ss.getPuzzle().toString();
        this.solutionSet = ss.getSolutionSet().parallelStream()
            .map( s -> s.toInfixString() ).toArray( String[]::new );
        this.algorithm = ss.getAlgorithm();
        this.count = ss.getCount();
        this.hasFinalDiv = booleanString( ss.hasFinalDiv() );
        this.hasFinalDivTwoByTwo = booleanString( ss.hasFinalDivTwoByTwo() );
        this.hasFinalMul = booleanString( ss.hasFinalMul() );
        this.hasFinalMulTwoByTwo = booleanString( ss.hasFinalMulTwoByTwo() );
        this.hasFinalAdd = booleanString( ss.hasFinalAdd() );
        this.hasFinalAddTwoByTwo = booleanString( ss.hasFinalAddTwoByTwo() );
        this.hasFraction = booleanString( ss.hasFraction() );
        this.difficultyRank = ss.getDifficultyRank().getSymbol();
    }

    public String booleanString( boolean value )
    {
        if( value ) {
            return "X";
        }
        else {
            return "";
        }
    }

    public String getPuzzle() {
        return puzzle;
    }

    public String[] getSolutionSet() {
        return solutionSet;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public int getCount() {
        return count;
    }

    public String getHasFinalDiv() {
        return hasFinalDiv;
    }

    public String getHasFinalDivTwoByTwo() {
        return hasFinalDivTwoByTwo;
    }

    public String getHasFinalMul() {
        return hasFinalMul;
    }

    public String getHasFinalMulTwoByTwo() {
        return hasFinalMulTwoByTwo;
    }

    public String getHasFinalAdd() {
        return hasFinalAdd;
    }

    public String getHasFinalAddTwoByTwo() {
        return hasFinalAddTwoByTwo;
    }

    public String getHasFraction() {
        return hasFraction;
    }

    public String getDifficultyRank() {
        return difficultyRank;
    }
}
