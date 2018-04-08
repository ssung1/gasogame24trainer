package name.subroutine.game24trainer.sourceimpl;

import name.subroutine.game24trainer.Game24PuzzleSource;
import name.subroutine.game24trainer.puzzle.Puzzle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Game24PuzzleSourceImplMax implements
    Game24PuzzleSource
{
    @Value( "${max.number:24}" )
    private int maxNumber = -1;

    public void setMaxNumber( int maxNumber )
    {
        this.maxNumber = maxNumber;
    }

    public int getMaxNumber()
    {
        return maxNumber;
    }

    public int getPuzzleListInitSize( int maxNumber )
    {
        // Math: we are calculating the number of combinations
        // with repeatable elements
        //
        // In this case, we are choosing 4 out of maxNumber
        //
        // So the answer is C( 4 + max - 1, max - 1 )

        // Calculatiing C( n, k )
        // which is also C( n, n - k )
        int n = 4 + maxNumber - 1;
        int k = 4;

        int result = 1;
        // multiply n * ( n - 1 ) * ( n - 2 ) ... k times
        for( int i = 0; i < k; ++i ) {
            result = result * (n - i);
        }
        // divide by 1 * 2 * 3 ... * k
        // (starting at 2 because dividing by 1 has no effect)
        for( int i = 2; i <= k; ++i ) {
            result = result / i;
        }
        return result;
    }

    public List<Puzzle> getPuzzleList()
    {
        int max = this.getMaxNumber();
        int initSize = getPuzzleListInitSize( max );
        List<Puzzle> puzzleList = new ArrayList<>( initSize );
        for( int a = 1; a <= max; ++a ) {
            for( int b = a; b <= max; ++b ) {
                for( int c = b; c <= max; ++c ) {
                    for( int d = c; d <= max; ++d ) {
                        Puzzle p = new Puzzle( a, b, c, d );
                        puzzleList.add( p );
                    }
                }
            }
        }
        return puzzleList;
    }
}
