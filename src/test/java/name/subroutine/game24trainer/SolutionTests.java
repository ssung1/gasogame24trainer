package name.subroutine.game24trainer;

import org.junit.Test;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;
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
    public void testTwoByTwo008()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "" );

        assertFalse( sut.isTwoByTwo() );
    }

    @Test
    public void testTwoByTwo009()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "1 1 + 1 + 1 + 1 + 1 + 1 +" );

        assertFalse( sut.isTwoByTwo() );
    }

    @Test
    public void testIsZeroTrick000()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "6 6 - 5 * 24 +" );
        assertTrue( sut.isZeroTrick() );
    }

    @Test
    public void testIsZeroTrick001()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "5 6 6 - * 24 +" );
        assertTrue( sut.isZeroTrick() );
    }

    @Test
    public void testIsZeroTrick002()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "13 13 - 7 * 24 +" );
        assertTrue( sut.isZeroTrick() );
    }

    @Test
    public void testIsZeroTrick003()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "7 13 13 - * 24 +" );
        assertTrue( sut.isZeroTrick() );
    }

    @Test
    public void testIsZeroTrick004()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "5 24 6 / * 4 +" );
        assertFalse( sut.isZeroTrick() );
    }

    @Test
    public void testIsDistProp000()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "8 6 * 4 6 * -" );
        assertTrue( sut.isDistProp() );
    }

    @Test
    public void testIsDistProp001()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "8 6 - 2 + 6 *" );
        assertFalse( sut.isDistProp() );
    }

    @Test
    public void testIsDistProp002()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "2 6 * 2 6 * +" );
        assertTrue( sut.isDistProp() );
    }

    @Test
    public void testIsDistProp003()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "3 4 * 1 2 * *" );
        assertFalse( sut.isDistProp() );
    }

    @Test
    public void testIsDistProp004()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "6 2 * 2 6 * +" );
        assertTrue( sut.isDistProp() );
    }

    @Test
    public void testIsDistProp005()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "2 6 * 3 4 * +" );
        assertFalse( sut.isDistProp() );
    }

    @Test
    public void testIsDistProp006()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "6 7 * 6 3 * -" );
        assertTrue( sut.isDistProp() );
    }

    @Test
    public void testIsDistProp007()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "6 6 * 3 4 * -" );
        assertFalse( sut.isDistProp() );
    }

    @Test
    public void testIsAlmostDistProp000()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "6 8 3 - * 6 -" );
        assertTrue( sut.isAlmostDistProp() );
    }

    @Test
    public void testIsAlmostDistProp001()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "7 8 3 - * 11 -" );
        assertFalse( sut.isAlmostDistProp() );
    }

    @Test
    public void testIsAddSub000()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "6 6 6 + + 6 +" );
        assertTrue( sut.isAddSub() );
    }

    @Test
    public void testIsAddSub001()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "6 6 6 * - 6 -" );
        assertFalse( sut.isAddSub() );
    }

    @Test
    public void testIsFinalDiv000()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 + 4 /" );
        assertTrue( sut.isFinalDiv() );
    }

    @Test
    public void testIsFinalDiv001()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "/" );
        assertTrue( sut.isFinalDiv() );
    }

    @Test
    public void testIsFinalDiv002()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 4 - /" );
        assertFalse( sut.isFinalDiv() );
    }

    @Test
    public void testIsFinalDivTwoByTwo000()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 4 * /" );
        assertTrue( sut.isFinalDivTwoByTwo() );
    }

    @Test
    public void testIsFinalDivTwoByTwo001()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 * 4 /" );
        assertFalse( sut.isFinalDivTwoByTwo() );
    }

    @Test
    public void testIsFinalDivTwoByTwo002()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 4 / +" );
        assertFalse( sut.isFinalDivTwoByTwo() );
    }

    @Test
    public void testIsFinalMul000()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 + 4 *" );
        assertTrue( sut.isFinalMul() );
    }

    @Test
    public void testIsFinalMul001()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "*" );
        assertTrue( sut.isFinalMul() );
    }

    @Test
    public void testIsFinalMul002()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "3 1 + 3 3 + *" );
        assertFalse( sut.isFinalMul() );
    }

    @Test
    public void testIsFinalMul003()
    {
        Solution sut = new Solution();
        // this should not be considered as final multiplication
        // because it requires a fraction as intermediate
        sut.expression = s.parse( "6 9 / 6 * 6 *" );
        sut.fraction = Puzzle.YES;
        assertFalse( "Should not be considered as final multiplication " +
                "because it requires fraction as intermediate",
            sut.isFinalMul() );
    }

    @Test
    public void testIsFinalMulTwoByTwo000()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 4 * *" );
        assertTrue( sut.isFinalMulTwoByTwo() );
    }

    @Test
    public void testIsFinalMulTwoByTwo001()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 * 4 *" );
        assertFalse( sut.isFinalMulTwoByTwo() );
    }

    @Test
    public void testIsFinalMulTwoByTwo002()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 4 * +" );
        assertFalse( sut.isFinalMulTwoByTwo() );
    }

    @Test
    public void testIsFinalAdd000()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 + 4 +" );
        assertTrue( sut.isFinalAdd() );
    }

    @Test
    public void testIsFinalAdd001()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "+" );
        assertTrue( sut.isFinalAdd() );
    }

    @Test
    public void testIsFinalAdd002()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 4 + +" );
        assertFalse( sut.isFinalAdd() );
    }

    @Test
    public void testIsFinalAddTwoByTwo000()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 4 * +" );
        assertTrue( sut.isFinalAddTwoByTwo() );
    }

    @Test
    public void testIsFinalAddTwoByTwo001()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 * 4 +" );
        assertFalse( sut.isFinalAddTwoByTwo() );
    }

    @Test
    public void testIsFinalAddTwoByTwo002()
    {
        Solution sut = new Solution();
        sut.expression = s.parse( "16 9 * 10 4 + *" );
        assertFalse( sut.isFinalAddTwoByTwo() );
    }

    @Test
    public void testEquality000()
    {
        Solution a = new Solution();
        Solution b = new Solution();
        a.expression = s.parse( "1 2 * 3 * 4 *" );
        b.expression = s.parse( "1 2 * 3 * 4 *" );

        assertTrue( a.equals( b ) );
    }

    @Test
    public void testEquality001()
    {
        Solution a = new Solution();
        Solution b = new Solution();
        a.expression = s.parse( "1 2 * 3 * 4 *" );
        b.expression = s.parse( "1 2 * 3 * 4 *   " );

        assertTrue( a.equals( b ) );
    }

    @Test
    public void testEquality002()
    {
        Solution a = new Solution();
        Solution b = new Solution();
        a.expression = s.parse( "1 2 * 3 * 4 *" );
        b.expression = s.parse( "1 3 * 2 * 4 *" );

        assertFalse( a.equals( b ) );
    }

    @Test
    public void testHashCode000()
    {
        Solution a = new Solution();
        Solution b = new Solution();
        a.expression = s.parse( "1 2 * 3 * 4 *" );
        b.expression = s.parse( "1 2 * 3 * 4 *" );

        assertThat( a.hashCode(), is( b.hashCode() ) );
    }

    @Test
    public void testHashCode001()
    {
        Solution a = new Solution();
        Solution b = new Solution();
        a.expression = s.parse( "1 2 * 3 * 4 *" );
        b.expression = s.parse( "1 2 * 4 * 3 *" );

        assertThat( a.hashCode(), not( b.hashCode() ) );
    }
}
