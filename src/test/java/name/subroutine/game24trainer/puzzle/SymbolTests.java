package name.subroutine.game24trainer.puzzle;

import name.subroutine.game24trainer.puzzle.Symbol;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SymbolTests
{
    Symbol sut = new Symbol();

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
