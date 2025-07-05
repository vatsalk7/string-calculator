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

        // Handle custom delimiter
        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            String delimiterSpec = numbers.substring(2, delimiterEndIndex);
            numbers = numbers.substring(delimiterEndIndex + 1);

            List<String> delimiters = new ArrayList<>();

            // Support for multiple delimiters like //[*][%][ ]
            if (delimiterSpec.contains("[") && delimiterSpec.contains("]")) {
                Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(delimiterSpec);
                while (matcher.find()) {
                    delimiters.add(Pattern.quote(matcher.group(1)));
                }
            } else {
                delimiters.add(Pattern.quote(delimiterSpec));
            }

            delimiter = String.join("|", delimiters);
        }

        String[] tokens = numbers.split(delimiter);
        int sum = 0;
        List<String> negatives = new ArrayList<>();

        for (String token : tokens) {
            if (token.isBlank()) continue;

            int num = Integer.parseInt(token.trim());

            if (num < 0) {
                negatives.add(token);
            }

            if (num <= 1000) {
                sum += num;
            }
        }

        if (!negatives.isEmpty()) {
            throw new NegativeNumberException("Negative numbers not allowed: " + String.join(", ", negatives));
        }

        return sum;
    }
}
