package name.subroutine.game24trainer;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

public class SymbolTests
{
    Symbol sut = new Symbol();

    @Before
    public void setUp()
    {
    }

    @Test
    public void testParseBlank()
    {
        Symbol[] result = sut.parse( "" );
        assertThat( result.length, is( 0 ) );
    }

    @Test
    public void testParseOneNumber()
    {
        Symbol[] result = sut.parse( "12" );
        assertThat( result.length, is( 1 ) );
        assertTrue( result[0].equals( 12 ) );
    }

    @Test
    public void testParseOneNumberWithSpaces000()
    {
        Symbol[] result = sut.parse( " 12  " );
        assertThat( result.length, is( 1 ) );
        assertTrue( result[0].equals( 12 ) );
    }

    @Test
    public void testParseOneNumberWithSpaces001()
    {
        Symbol[] result = sut.parse( "12  " );
        assertThat( result.length, is( 1 ) );
        assertTrue( result[0].equals( 12 ) );
    }

    @Test
    public void testParseOneNumberWithSpaces002()
    {
        Symbol[] result = sut.parse( "12 " );
        assertThat( result.length, is( 1 ) );
        assertTrue( result[0].equals( 12 ) );
    }

    @Test
    public void testParseOneNumberWithSpaces003()
    {
        Symbol[] result = sut.parse( " 12" );
        assertThat( result.length, is( 1 ) );
        assertTrue( result[0].equals( 12 ) );
    }

    @Test
    public void testParseOneNumberWithSpaces004()
    {
        Symbol[] result = sut.parse( "  12" );
        assertThat( result.length, is( 1 ) );
        assertTrue( result[0].equals( 12 ) );
    }

    @Test
    public void testParseTwoNumbers()
    {
        Symbol[] result = sut.parse( "1 2" );
        assertThat( result.length, is( 2 ) );
        assertTrue( result[0].equals( 1 ) );
        assertTrue( result[1].equals( 2 ) );
    }

    @Test
    public void testParseTwoNumbersWithSpaces000()
    {
        Symbol[] result = sut.parse( " 1 2" );
        assertThat( result.length, is( 2 ) );
        assertTrue( result[0].equals( 1 ) );
        assertTrue( result[1].equals( 2 ) );
    }

    @Test
    public void testParseTwoNumbersWithSpaces001()
    {
        Symbol[] result = sut.parse( "1  2" );
        assertThat( result.length, is( 2 ) );
        assertTrue( result[0].equals( 1 ) );
        assertTrue( result[1].equals( 2 ) );
    }

    @Test
    public void testParseTwoNumbersWithSpaces002()
    {
        Symbol[] result = sut.parse( "1 2 " );
        assertThat( result.length, is( 2 ) );
        assertTrue( result[0].equals( 1 ) );
        assertTrue( result[1].equals( 2 ) );
    }
}
