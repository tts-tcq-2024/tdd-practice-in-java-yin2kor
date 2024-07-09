package TddPracticeInJava;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    public static final String DELIMITER_REGEX = "\n|,";
    public static final int MAX_NUMBER = 1000;
    public static final int MIN_NUMBER = 0;

    public static final String CUSTOM_DELIMITER_REGEX = "^//(.*)\\n";
    // Compile the pattern
    public static final Pattern PATTERN = Pattern.compile(CUSTOM_DELIMITER_REGEX);


    public int add(final String numbers) throws InvalidParameterException {
        int value = 0;
        if (!numbers.isEmpty() && !"0".contentEquals(numbers)) {
            String regex = appendToDelimiterRegex(getCustomDelimiter(numbers));
            value = addSubsetNumbers(numbers.substring(getNumbersStartIndex(numbers)).split(regex));
        }
        return value;
    }


    private int addSubsetNumbers(String[] numbers) {
        int value;
        List<Integer> negativeNumbers = new ArrayList<>();
        List<Integer> validNumbers = new ArrayList<>();
        for (String subNumber : numbers) {
            int number = Integer.parseInt(subNumber);
            AppendToNegativeListIfNegative(negativeNumbers, number);
            AppendToNegativeListIfValid(validNumbers, number);
        }
        if (negativeNumbers.isEmpty()) {
            value = validNumbers.stream().mapToInt(Integer::intValue).sum();
        } else {
            throw new InvalidParameterException("Negatives not allowed:" + negativeNumbers.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
        return value;
    }

    boolean isNumberWithinRange(int number) {
        boolean isValid = false;
        if (number <= MAX_NUMBER && number >= MIN_NUMBER){
            isValid = true;
        }
        return isValid;
    }

    void AppendToNegativeListIfNegative(List<Integer> integers, int number) {
        if (number < MIN_NUMBER) {
            integers.add(number);
        }
    }

    void AppendToNegativeListIfValid(List<Integer> integers, int number) {
        if (isNumberWithinRange(number)) {
            integers.add(number);
        }
    }

    String appendToDelimiterRegex(String customDelimiterRegex) {
        String currentRegex = DELIMITER_REGEX;
        if (!customDelimiterRegex.isEmpty()) {
            currentRegex = currentRegex + "|" + Pattern.quote(customDelimiterRegex);
        }
        return currentRegex;
    }

    String getCustomDelimiter(String str) {
        String regix = "";
        Matcher matcher = getValidCustomDelimiterMatcher(str);
        if (null!= matcher) {
            regix = matcher.group(1);
        }
        return regix;
    }

    Matcher getValidCustomDelimiterMatcher(String s) {
        Matcher matcher = PATTERN.matcher(s);
        if (!matcher.find()) {
            matcher = null;
        }
        return matcher;
    }

    int getNumbersStartIndex(String str) {
        Matcher matcher = getValidCustomDelimiterMatcher(str);
        int index = 0;
        if (null!= matcher) {
            index = matcher.end();
        }
        return index;
    }

    private String[] splitByDelimiter(String number, String regex) {
        return number.split(regex);
    }


}