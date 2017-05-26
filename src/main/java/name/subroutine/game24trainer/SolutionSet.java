package name.subroutine.game24trainer;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

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
 * 7. 0-trick
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
        return true;
    }

    /**
     * true if some solutions have division as last operation
     * @return
     */
    public boolean hasLastOpDiv()
    {
        return true;
    }

    /**
     * true if some solutions have multiplication as last operation
     * @return
     */
    public boolean hasLastOpMul()
    {
        return true;
    }

    /**
     * true if some solutions have fraction
     * @return
     */
    public boolean hasFraction()
    {
        for( Solution s : solutionSet ) {
            if( s.hasFraction() == Puzzle.YES ) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSolution()
    {
        return !solutionSet.isEmpty();
    }
}
