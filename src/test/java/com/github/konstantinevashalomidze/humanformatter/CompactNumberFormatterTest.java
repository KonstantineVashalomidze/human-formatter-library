package com.github.konstantinevashalomidze.humanformatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompactNumberFormatterTest {


    @Test
    void formatReturns999WhenValueIs999() {
        String actual = CompactNumberFormatter.format(999);
        String expected = "999";

        assertEquals(expected, actual);
    }


    @Test
    void formatReturns1Point0KWhenValueIs1_000() {
        String actual = CompactNumberFormatter.format(1_000);
        String expected = "1.0K";
        assertEquals(expected, actual);
    }

    @Test
    void formatReturns999Point9KWhenValueIs999_999() {
        String actual = CompactNumberFormatter.format(999_999);
        String expected = "999.9K";
        assertEquals(expected, actual);
    }

    @Test
    void formatReturns1Point0MWhenValueIs1_000_000() {
        String actual = CompactNumberFormatter.format(1_000_000);
        String expected = "1.0M";
        assertEquals(expected, actual);
    }


    @Test
    void formatReturns1Point0BWhenValueIs1_000_000_000() {
        String actual = CompactNumberFormatter.format(1_000_000_000);
        String expected = "1.0B";
        assertEquals(expected, actual);
    }

    @Test
    void formatReturns1Point0TWhenValueIs1_000_000_000_000() {
        String actual = CompactNumberFormatter.format(1_000_000_000_000L);
        String expected = "1.0T";
        assertEquals(expected, actual);
    }


    @Test
    void formatReturns9223372Point0TWhenValueIsLongMAX_VALUE() {
        String actual = CompactNumberFormatter.format(Long.MAX_VALUE);
        String expected = "9223372.0T";
        assertEquals(expected, actual);
    }


    @Test
    void formatReturnsNegative5Point0KWhenValueIsNegative5_000() {
        String actual = CompactNumberFormatter.format(-5_000);
        String expected = "-5.0K";
        assertEquals(expected, actual);
    }

    @Test
    void formatReturns0WhenValueIs0() {
        String actual = CompactNumberFormatter.format(0);
        String expected = "0";
        assertEquals(expected, actual);
    }

    @Test
    void formatThrowsWhenValueIsLongMIN_VALUE() {
        assertThrows(HumanFormatterInputException.class, () -> CompactNumberFormatter.format(Long.MIN_VALUE));
    }



}
