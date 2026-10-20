package com.github.konstantinevashalomidze.humanformatter;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RelativeTimeFormatterTest {
    private static final Instant now = Instant.parse("2026-10-20T10:00:00Z");

    @Test
    void formatReturnsJustNowWhenThenEqualsNow() {
        String actual = RelativeTimeFormatter.format(now, now);
        String expected = "just now";

        assertEquals(expected, actual);
    }


    @Test
    void formatReturns30SecondsAgoWhenThenIs30SecondsBeforeNow() {
        String actual = RelativeTimeFormatter.format(now.minusSeconds(30), now);
        String expected = "30 seconds ago";

        assertEquals(expected, actual);
    }


    @Test
    void formatReturnsIn30SecondsWhenThenIs30SecondsAfterNow() {
        String actual = RelativeTimeFormatter.format(now.plusSeconds(30), now);
        String expected = "in 30 seconds";

        assertEquals(expected, actual);
    }


    @Test
    void formatReturns1SecondAgoWhenThenIsExactly1SecondBeforeNow() {
        String actual = RelativeTimeFormatter.format(now.minusSeconds(1), now);
        String expected = "1 second ago";
        assertEquals(expected, actual);
    }

    @Test
    void formatReturnsJustNowWhenThenIs999MillisecondsBeforeNow() {
        String actual = RelativeTimeFormatter.format(now.minusMillis(999), now);
        String expected = "just now";

        assertEquals(expected, actual);
    }



    @Test
    void formatReturns1MinuteAgoWhenThenIs60SecondsBeforeNow() {
        String actual = RelativeTimeFormatter.format(now.minusSeconds(60), now);
        String expected = "1 minute ago";

        assertEquals(expected, actual);
    }


    @Test
    void formatReturns1HourAgoWhenThenIs3_600SecondsBeforeNow() {
        String actual = RelativeTimeFormatter.format(now.minusSeconds(3_600), now);
        String expected = "1 hour ago";

        assertEquals(expected, actual);
    }


    @Test
    void formatReturns400DaysAgoWhenThenIs400DaysBeforeNow() {
        String actual = RelativeTimeFormatter.format(now.minus(400, DAYS), now);
        String expected = "400 days ago";

        assertEquals(expected, actual);
    }


    @Test
    void formatReturnsIn1SecondWhenThenIs1point5SecondsAfterNow() {
        String actual = RelativeTimeFormatter.format(now.plusMillis(1500), now);
        String expected = "in 1 second";

        assertEquals(expected, actual);
    }

}
