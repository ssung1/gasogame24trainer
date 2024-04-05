package name.subroutine.game24trainer.puzzle;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import static name.subroutine.game24trainer.puzzle.DifficultyRank.*;

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
    private String algorithm;

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

    public void setAlgorithm( String algorithm )
    {
        this.algorithm = algorithm;
    }

    public String getAlgorithm()
    {
        return this.algorithm;
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
            if( s.isFraction() != Puzzle.YES ) {
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
        return excludeFraction().parallelStream().anyMatch(
            s -> s.isTwoByTwo() );
    }

    /**
     * true if some solutions have division as last operation
     * @return
     */
    @JsonProperty
    public boolean hasFinalDiv()
    {
        return excludeFraction().parallelStream().anyMatch(
            s -> s.isFinalDiv() );
    }

    /**
     * true if some solutions has a two by two pattern with division
     * as final operation
     */
    @JsonProperty
    public boolean hasFinalDivTwoByTwo()
    {
        return excludeFraction().parallelStream().anyMatch(
            Solution::isFinalDivTwoByTwo );
    }

    /**
     * true if some solutions have multiplication as last operation
     * @return
     */
    @JsonProperty
    public boolean hasFinalMul()
    {
        return excludeFraction().parallelStream().anyMatch(
            s -> s.isFinalMul() );
    }

    /**
     * true if some solutions has a two by two pattern with multiplication
     * as final operation
     */
    @JsonProperty
    public boolean hasFinalMulTwoByTwo()
    {
        return excludeFraction().parallelStream().anyMatch(
            Solution::isFinalMulTwoByTwo );
    }

    /**
     * true if some solutions have addition or subraction as final operation
     */
    @JsonProperty
    public boolean hasFinalAdd()
    {
        return excludeFraction().parallelStream().anyMatch(
            s -> s.isFinalAdd() );
    }

    /**
     * true if some solutions has a two by two pattern with addition or
     * subtraction as final operation
     */
    @JsonProperty
    public boolean hasFinalAddTwoByTwo()
    {
        return excludeFraction().parallelStream().anyMatch(
            Solution::isFinalAddTwoByTwo );
    }

    /**
     * true if some solutions have fraction
     * @return
     */
    @JsonProperty
    public boolean hasFraction()
    {
        return solutionSet.parallelStream().anyMatch(
            s -> s.isFraction() == Puzzle.YES );
    }

    public boolean hasSolution()
    {
        return !solutionSet.isEmpty();
    }

    public boolean hasZeroTrick()
    {
        return excludeFraction().parallelStream().anyMatch(
                Solution::isZeroTrick );
    }

    public boolean hasDistProp()
    {
        return excludeFraction().parallelStream().anyMatch(
                Solution::isDistProp );
    }

    public boolean hasAlmostDistProp()
    {
        return excludeFraction().parallelStream().anyMatch(
                Solution::isAlmostDistProp );
    }

    public boolean hasAddSub()
    {
        return excludeFraction().parallelStream().anyMatch(
                Solution::isAddSub );
    }

    /**
     * Difficulty ranking, from the easiest:
     *
     * 24+0 trick, (a - a) * b + 24
     *
     * Distributive property, (a * b) + (a * c)
     *
     * Almost Distributive property (a * b ? c) + a
     *
     * Multiplication as last operation
     *
     * Two by two, with multiplication as last operation
     *
     * Addition/subtraction as last operation
     *
     * Two by two, with addition/subtraction as final operation
     *
     * Division as last operation
     *
     * Two by two, with division as final operation
     *
     * Fraction required
     */
    public DifficultyRank getDifficultyRank()
    {
        if( hasZeroTrick() ) {
            return ZERO_TRICK;
        }
        if( hasDistProp() ) {
            return DIST_PROP;
        }
        if( hasAlmostDistProp() ) {
            return ALMOST_DIST_PROP;
        }
        if( hasFinalMul() ) {
            return FINAL_MUL;
        }
        if( hasFinalMulTwoByTwo() ) {
            return FINAL_MUL_2;
        }
        if( hasFinalAdd() ) {
            return FINAL_ADD;
        }
        if( hasFinalAddTwoByTwo() ) {
            return FINAL_ADD_2;
        }
        if( hasFinalDiv() ) {
            return FINAL_DIV;
        }
        if( hasFinalDivTwoByTwo() ) {
            return FINAL_DIV_2;
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
