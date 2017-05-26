package name.subroutine.game24trainer;

import java.util.HashSet;
import java.util.Set;

import static name.subroutine.game24trainer.DiffcultyRank.*;

/**
 * Difficulty ranking
 *
 * 1. Fraction required
 * 2. Division as last operation (?)
 * 3. Addition/Subtraction as last operation
 * 4. Two by Two, with multiplication as last operation
 * 5. Multiplication as last operation
 * 6. Two by Two, with addition/subtraction as last operation
 *    (distributive property)
 * 7. 24+0 trick
 */
public class SolutionSet
{
    private Set<Solution> solutionSet = new HashSet<>();
    private Puzzle puzzle;

    public void add( Solution s )
    {
        solutionSet.add( s );
    }

    public int getCount()
    {
        return solutionSet.size();
    }

    public void setPuzzle( Puzzle puzzle )
    {
        this.puzzle = puzzle;
    }

    public Puzzle getPuzzle()
    {
        return this.puzzle;
    }

    public Set<Solution> getSolutionSet()
    {
        return solutionSet;
    }

    /**
     * exclude solutions that use fraction as intermediate
     *
     * @return
     */
    public Set<Solution> excludeFraction()
    {
        Set<Solution> result = new HashSet<>();
        for( Solution s : solutionSet ) {
            if( s.hasFraction() != Puzzle.YES ) {
                result.add( s );
            }
        }
        return result;
    }

    /**
     * true if some solutions are two-by-two
     * @return
     */
    public boolean hasTwoByTwo()
    {
        return solutionSet.parallelStream().anyMatch( s -> s.isTwoByTwo() );
    }

    /**
     * true if some solutions have division as last operation
     * @return
     */
    public boolean hasFinalDiv()
    {
        return solutionSet.parallelStream().anyMatch( s -> s.isFinalDiv() );
    }

    /**
     * true if some solutions have multiplication as last operation
     * @return
     */
    public boolean hasFinalMul()
    {
        return solutionSet.parallelStream().anyMatch( s -> s.isFinalMul() );
    }

    /**
     * true if some solutions have addition or subraction as final operation
     */
    public boolean hasFinalAdd()
    {
        return solutionSet.parallelStream().anyMatch( s -> s.isFinalAdd() );
    }

    /**
     * true if some solutions have fraction
     * @return
     */
    public boolean hasFraction() {
        return solutionSet.parallelStream().anyMatch( s -> s.hasFraction() == Puzzle.YES );
    }

    public boolean hasSolution()
    {
        return !solutionSet.isEmpty();
    }

/**
 * Difficulty ranking
 *
 * 1. Fraction required
 * 2. Division as last operation (?)
 * 3. Addition/Subtraction as last operation
 * 4. Two by Two, with multiplication as last operation
 * 5. Multiplication as last operation
 * 6. Two by Two, with addition/subtraction as last operation
 *    (distributive property)
 * 7. 24+0 trick
 */
    public DiffcultyRank difficultyRank()
    {
        if( hasFinalMul() ) {
            return FINAL_MUL;
        }
        if( !hasFinalDiv() && !hasFinalMul() && hasSolution() ) {
            return FINAL_ADD;
        }
        if( hasFinalDiv() ) {
            return FINAL_DIV;
        }
        if( hasFraction() ) {
            return FRAC;
        }
        if( !hasSolution() ) {
            return NO_SOLU;
        }
        return UNDEF;
    }
}
