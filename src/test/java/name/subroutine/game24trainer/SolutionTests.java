package name.subroutine.game24trainer;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

public class SolutionTests
{
    Symbol s = new Symbol();

    @Test
    public void testTwoByTwo000()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "1 1 + 1 1 + +" );

        assertTrue( sut.isTwoByTwo() );
    }

    @Test
    public void testTwoByTwo001()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "1 1 + 1 1 + -" );

        assertTrue( sut.isTwoByTwo() );
    }

    @Test
    public void testTwoByTwo002()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "1 1 + 1 1 + *" );

        assertTrue( sut.isTwoByTwo() );
    }

    @Test
    public void testTwoByTwo003()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "1 1 + 1 1 + /" );

        assertTrue( sut.isTwoByTwo() );
    }

    @Test
    public void testTwoByTwo004()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "1 1 + 1 + 1 +" );

        assertFalse( sut.isTwoByTwo() );
    }

    @Test
    public void testTwoByTwo005()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "1 1 1 1 + - *" );

        assertFalse( sut.isTwoByTwo() );
    }

    @Test
    public void testTwoByTwo006()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "99 99 99 + - 87 * /" );

        assertFalse( sut.isTwoByTwo() );
    }

    @Test
    public void testTwoByTwo007()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "3 6 899 - 10 * *" );

        assertFalse( sut.isTwoByTwo() );
    }

    @Test
    public void testIsLastOpDiv000()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 4 /" );
        assertTrue( sut.isLastOpDiv() );
    }

    @Test
    public void testIsLastOpDiv001()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "/" );
        assertTrue( sut.isLastOpDiv() );
    }

    @Test
    public void testIsLastOpMul000()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 4 *" );
        assertTrue( sut.isLastOpMul() );
    }

    @Test
    public void testIsLastOpMul001()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "*" );
        assertTrue( sut.isLastOpMul() );
    }
}
