package TddPracticeInJava;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static final String DELIMITER_REGEX = "\n|,";
    public static final int MAX_NUMBER = 1000;

    public static final String CUSTOM_DELIMITER_REGEX = "^//(.*)\\n";
    // Compile the pattern
    public static final Pattern PATTERN = Pattern.compile(CUSTOM_DELIMITER_REGEX);


    public int add(final String numbers) {
        int value = 0;
        if (!numbers.isEmpty() && !"0".contentEquals(numbers)) {
            String regex = appendToDelimiterRegex(getCustomDelimiter(numbers));
            value = value + addSubsetNumbers(numbers.substring(getNumbersStartIndex(numbers)).split(regex));
        }
        return value;
    }


    private int addSubsetNumbers(String[] numbers) {
        int value = 0;
        for (String subNumber : numbers) {
            int number = Integer.parseInt(subNumber);
            if (number <= MAX_NUMBER) {
                value = value + number;
            }
        }
        return value;
    }

    String appendToDelimiterRegex(String customDelimiterRegex) {
        String currentRegex = DELIMITER_REGEX;
        if (!customDelimiterRegex.isEmpty()) {
            currentRegex = currentRegex + "|" + customDelimiterRegex;
        }
        return currentRegex;
    }

    String getCustomDelimiter(String str) {
        String regix = "";
        Matcher matcher = getCustomDelimiterMatcher(str);
        if (matcher.find()) {
            regix = matcher.group(1);
        }
        return regix;
    }

    Matcher getCustomDelimiterMatcher(String s) {
        return PATTERN.matcher(s);
    }

    int getNumbersStartIndex(String str) {
        Matcher matcher = getCustomDelimiterMatcher(str);
        int index = 0;
        if (matcher.find()) {
            index = matcher.end();
        }
        return index;
    }

    private String[] splitByDelimiter(String number, String regex) {
        return number.split(regex);
    }


}