package com.incubytes.stringcalculator;

import com.incubytes.stringcalculator.Exception.NegativeNumberException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(@NotNull String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = ",|\n";
        if (hasCustomDelimiter(numbers)) {
            delimiter = extractDelimiters(numbers);
            numbers = stripDelimiterHeader(numbers);
        }

        String[] tokens = numbers.split(delimiter);
        List<String> negatives = new ArrayList<>();
        int sum = computeSum(tokens, negatives);

        if (!negatives.isEmpty()) {
            throw new NegativeNumberException("Negative numbers not allowed: " + String.join(", ", negatives));
        }

        return sum;
    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith("//");
    }

    private String extractDelimiters(String input) {
        int delimiterEndIndex = input.indexOf("\n");
        String delimiterSpec = input.substring(2, delimiterEndIndex);
        List<String> delimiters = new ArrayList<>();

        if (delimiterSpec.contains("[") && delimiterSpec.contains("]")) {
            Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(delimiterSpec);
            while (matcher.find()) {
                delimiters.add(Pattern.quote(matcher.group(1)));
            }
        } else {
            delimiters.add(Pattern.quote(delimiterSpec));
        }

        return String.join("|", delimiters);
    }

    private String stripDelimiterHeader(String input) {
        int delimiterEndIndex = input.indexOf("\n");
        return input.substring(delimiterEndIndex + 1);
    }

    private int computeSum(String[] tokens, List<String> negatives) {
        int sum = 0;

        for (String token : tokens) {
             if (token.isBlank()) {
                throw new IllegalArgumentException("Invalid input: empty number between delimiters");
            }
            ;

            int num = Integer.parseInt(token.trim());

            if (num < 0) {
                negatives.add(token);
            }

            if (num <= 1000) {
                sum += num;
            }
        }

        return sum;
    }
}
