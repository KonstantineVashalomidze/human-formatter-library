package com.github.konstantinevashalomidze.humanformatter;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DurationFormatterTest {
    @Test
    void formatReturns0sWhenZeroDurationValueIsGiven() {
        String actual = DurationFormatter.format(Duration.ZERO);
        String expected = "0s";

        assertEquals(expected, actual);
    }

    @Test
    void formatReturns1hWhen3_600DurationValueIsGiven() {
        String actual = DurationFormatter.format(Duration.ofSeconds(3_600));
        String expected = "1h";

        assertEquals(expected, actual);
    }

    @Test
    void formatReturnsNegative1m30sWhenNegative90DurationValueIsGiven() {
        String actual = DurationFormatter.format(Duration.ofSeconds(-90));
        String expected = "-1m 30s";

        assertEquals(expected, actual);
    }

    @Test
    void formatReturns1dWhen86_400DurationValueIsGiven() {
        String actual = DurationFormatter.format(Duration.ofSeconds(86_400));
        String expected = "1d";

        assertEquals(expected, actual);
    }


    @Test
    void formatReturns3h12m45sWhen11_565DurationValueIsGiven() {
        String actual = DurationFormatter.format(Duration.ofSeconds(11_565));
        String expected = "3h 12m 45s";

        assertEquals(expected, actual);
    }

    @Test
    void formatReturnsNegative1sWhenDurationOfNegative1500IsValueIsGiven() {
        String actual = DurationFormatter.format(Duration.ofMillis(-1500));
        String expected = "-1s";

        assertEquals(expected, actual);
    }


    @Test
    void formatThrowsInvalidInputExceptionWhenLongMinValueIsGiven() {
        assertThrows(HumanFormatterInputException.class, () -> DurationFormatter.format(Duration.ofSeconds(Long.MIN_VALUE)));
    }


}
