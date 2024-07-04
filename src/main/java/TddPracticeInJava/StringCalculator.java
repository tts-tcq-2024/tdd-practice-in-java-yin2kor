package TddPracticeInJava;

public class StringCalculator {

    public static final int INVALID_STATUS = -1;
    public int add(final String numbers) throws Exception {
        int value = INVALID_STATUS;
        if (numbers.isEmpty()) {
            value = 0;
        }
       return value;
    }
}