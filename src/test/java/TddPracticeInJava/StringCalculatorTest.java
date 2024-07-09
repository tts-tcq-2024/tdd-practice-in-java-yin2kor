package TddPracticeInJava;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.security.InvalidParameterException;

public class StringCalculatorTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @Test
    public void ExpectZeroForEmptyInput() throws Exception {
        int expectedResult = 0;
        String input = "";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);

       assertEquals(expectedResult,result);
    }

  @Test
    public void ExpectZeroForSingleZero() throws Exception {
        int expectedResult = 0;
        String input = "0";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);
       assertEquals(expectedResult,result);

    }

   @Test
    public void ExpectSumForTwoNumbers()
    {
        int expectedResult = 3;
        String input = "1,2";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);
        assertEquals(expectedResult,result);
    }

   @Test
    public void ExpectSumWithNewlineDelimiter()
    {
        int expectedResult = 6;
        String input = "1\n2,3";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);
         assertEquals(expectedResult,result);
    }


    @Test
    public void ignoreNumbersGreaterThan1000() {
        int expectedResult = 1;
        String input = "1,1001";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);

       assertEquals(expectedResult,result);
    }
    @Test
    public void ExpectSumWithCustomDelimiter()
    {
        int expectedResult = 3;
        String input = "//;\n1;2";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);

      assertEquals(expectedResult,result);
    }

    @Test
    public void ThrowExceptionWithNegativeNumbers() {
        String input = "-1,-2";
        StringCalculator objUnderTest = new StringCalculator();
        exception.expect(InvalidParameterException.class);
        int result = objUnderTest.add(input);
    }

    @Test
    public void ExpectCustomDelimiter2(){
        String input = "//***\n1***2\n3";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);
        assertEquals(6, result);

    }
}
