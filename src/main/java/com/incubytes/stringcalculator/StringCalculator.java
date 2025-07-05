package com.incubytes.stringcalculator;

public class StringCalculator {

        public int add(String numbers) {
            if (numbers.isEmpty()) return 0;
            String[] tokens = numbers.split(",");
            int sum = 0;
            for (String num : tokens) {
                sum += Integer.parseInt(num);
            }
            return sum;
        }
}
