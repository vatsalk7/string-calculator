package com.incubytes.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    private StringCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    public void empty(){
        // Test with the most basic case to confirm the program runs correctly.
    }

    @Test
    void shouldReturnZeroForEmptyString() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    void shouldReturnNumberIfSingleNumberGiven() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(1, calculator.add("1"));
    }

    @Test
    void shouldReturnSumOfTwoCommaSeparatedNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1,5"));
    }

    @Test
    void shouldReturnSumOfMultipleNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(10, calculator.add("1,2,3,4"));
    }

    @Test
    void shouldHandleNewlineAsValidDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    void shouldSupportCustomDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("//;\n1;2"));
    }



}
