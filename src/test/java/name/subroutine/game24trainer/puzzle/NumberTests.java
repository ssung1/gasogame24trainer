package name.subroutine.game24trainer.puzzle;

import name.subroutine.game24trainer.puzzle.Number;
import name.subroutine.game24trainer.puzzle.Operator;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberTests
{
    @Test
    public void testEqualityTrue000()
    {
        Number a = new Number( 10 );
        Number b = new Number( 10.0f );

        assertThat( a, is( b ) );
    }

    @Test
    public void testEqualityTrue001()
    {
        Number a = new Number( 10 );

        assertTrue( a.equals( 10f ) );
    }

    @Test
    public void testEqualityTrue002()
    {
        Number a = new Number( 10 );

        assertTrue( a.equals( 10 ) );
    }

    @Test
    public void testEqualityTrue003()
    {
        Number a = new Number( 10 );

        assertTrue( a.equals( 10.0 ) );
    }

    @Test
    public void testEqualityFalse000()
    {
        Number a = new Number( 10 );
        Number b = new Number( 100 );

        assertThat( a, not( b ) );
    }

    @Test
    public void testEqualityFalse001()
    {
        Number a = new Number( 10 );
        Number b = null;

        assertThat( a, not( b ) );
    }

    @Test
    public void testEqualityFalse002()
    {
        Number a = new Number( 10 );
        Operator b = new Operator( '+', 0, true );

        assertFalse( a.equals( b ) );
    }

    @Test
    public void testEqualityFalse003()
    {
        Number a = new Number( 10 );

        assertFalse( a.equals( "asdlfk" ) );
    }
}
