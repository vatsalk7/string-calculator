package com.incubytes.stringcalculator;

import com.incubytes.stringcalculator.Exception.NegativeNumberException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(@NotNull String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = ",|\n";

        // Handle custom delimiter syntax
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            String customDelimiter = numbers.substring(2, delimiterIndex);

            // Support [multi-char] delimiters: //[*] or //[***]
            if (customDelimiter.startsWith("[") && customDelimiter.endsWith("]")) {
                customDelimiter = customDelimiter.substring(1, customDelimiter.length() - 1);
            }

            delimiter = Pattern.quote(customDelimiter);
            numbers = numbers.substring(delimiterIndex + 1);
        }

        String[] tokens = numbers.split(delimiter);
        int sum = 0;
        List<String> negatives = new ArrayList<>();

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            int num = Integer.parseInt(token.trim());

            if (num < 0) {
                negatives.add(token);
            }

            if (num <= 1000) {
                sum += num;
            }
        }

        if (!negatives.isEmpty()) {
            throw new NegativeNumberException("Negative numbers not allowed: " + String.join(",", negatives));
        }

        return sum;
    }
}
