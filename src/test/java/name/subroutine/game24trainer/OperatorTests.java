package name.subroutine.game24trainer;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

public class OperatorTests
{
    @Test
    public void testEqualityTrue000()
    {
        Operator a = new Operator( '+', 0 );
        Operator b = new Operator( '+', 0 );

        assertThat( a, is( b ) );
    }

    @Test
    public void testEqualityTrue001()
    {
        Operator a = new Operator( '+', 0 );

        assertThat( a, is( Operator.ADD ) );
    }

    @Test
    public void testEqualityTrue002()
    {
        Operator a = new Operator( '+', 0 );

        assertTrue( a.equals( '+' ) );
    }

    @Test
    public void testEqualityFalse000()
    {
        Operator a = new Operator( '+', 0 );

        assertFalse( a.equals( null ) );
    }

    @Test
    public void testEqualityFalse001()
    {
        Operator a = new Operator( '+', 0 );
        Operator b = new Operator( '-', 0 );

        assertFalse( a.equals( b ) );
    }

    @Test
    public void testEqualityFalse002()
    {
        Operator a = new Operator( '+', 0 );
        Number b = new Number( 1 );

        assertFalse( a.equals( b ) );
    }
}
