package com.incubytes.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(1, calculator.add("1"));
    }

    @Test
    void shouldReturnSumOfTwoCommaSeparatedNumbers() {
        assertEquals(6, calculator.add("1,5"));
    }

    @Test
    void shouldReturnSumOfMultipleNumbers() {
        assertEquals(10, calculator.add("1,2,3,4"));
    }

    @Test
    void shouldHandleNewlineAsValidDelimiter() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    void shouldSupportCustomDelimiter() {
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    void shouldThrowExceptionOnNegativeNumbers() {
        StringCalculator calculator = new StringCalculator();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            calculator.add("1,-2,3,-5");
        });
        assertTrue(exception.getMessage().toLowerCase().contains("negative numbers not allowed"));
        assertTrue(exception.getMessage().contains("-2"));
        assertTrue(exception.getMessage().contains("-5"));
    }

    @Test
    void shouldIgnoreNumbersGreaterThanThousand() {
        assertEquals(8, calculator.add("2,1001,6")); // 1001 should be ignored
    }

}
