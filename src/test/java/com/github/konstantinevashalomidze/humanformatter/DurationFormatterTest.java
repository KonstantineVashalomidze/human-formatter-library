package com.github.konstantinevashalomidze.humanformatter;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
