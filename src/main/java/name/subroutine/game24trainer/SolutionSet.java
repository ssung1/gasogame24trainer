package name.subroutine.game24trainer;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SolutionSet
{
    private Set<Solution> solutionSet = new HashSet<>();

    public void add( Solution s )
    {
        solutionSet.add( s );
    }

    public int getCount()
    {
        return solutionSet.size();
    }

    /**
     * exclude solutions that use fraction as intermediate
     *
     * @return
     */
    public Set excludeFraction()
    {
        Set<Solution> result = new HashSet<>();
        for( Solution s : solutionSet ) {
            if( s.hasFraction() == Puzzle.YES ) {
                result.add( s );
            }
        }
        return result;
    }

    /**
     * true only if all the solutions are two-by-two
     * @return
     */
    public boolean isTwoByTwo()
    {
        return true;
    }

    /**
     * true only if all solutions have division as last operation
     * @return
     */
    public boolean isLastOpDiv()
    {
        return true;
    }

    /**
     * true only if all solutions have multiplication as last operation
     * @return
     */
    public boolean isLastOpMul()
    {
        return true;
    }

    /**
     * true only if all solution have fraction
     * @return
     */
    public boolean hasFraction()
    {
        return true;
    }
}
