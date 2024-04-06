package name.subroutine.game24trainer.puzzle;

import name.subroutine.game24trainer.puzzle.Number;
import name.subroutine.game24trainer.puzzle.Operator;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperatorTests
{
    @Test
    public void testEqualityTrue000()
    {
        Operator a = new Operator( '+', 0, true );
        Operator b = new Operator( '+', 0, true );

        assertThat( a, is( b ) );
    }

    @Test
    public void testEqualityTrue001()
    {
        Operator a = new Operator( '+', 0, true );

        assertThat( a, is( Operator.ADD ) );
    }

    @Test
    public void testEqualityTrue002()
    {
        Operator a = new Operator( '+', 0, true );

        assertTrue( a.equals( '+' ) );
    }

    @Test
    public void testEqualityFalse000()
    {
        Operator a = new Operator( '+', 0, true );

        assertFalse( a.equals( null ) );
    }

    @Test
    public void testEqualityFalse001()
    {
        Operator a = new Operator( '+', 0, true );
        Operator b = new Operator( '-', 0, true );

        assertFalse( a.equals( b ) );
    }

    @Test
    public void testEqualityFalse002()
    {
        Operator a = new Operator( '+', 0, true );
        Number b = new Number( 1 );

        assertFalse( a.equals( b ) );
    }
}
