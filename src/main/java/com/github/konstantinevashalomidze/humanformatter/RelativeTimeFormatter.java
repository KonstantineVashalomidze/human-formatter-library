package com.github.konstantinevashalomidze.humanformatter;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Formats a moment in time relative to another, such as {@code "2 days ago"}
 * or {@code "in 3 hours"}.
 * <p>
 * Only the largest unit is shown: seconds, minutes, hours or days. Months
 * and years are not used, so 400 days stays {@code "400 days"}.
 *
 * @author Konstantine Vashalomidze
 */
public final class RelativeTimeFormatter {

    private RelativeTimeFormatter() {}

    /**
     * Describes {@code then} relative to {@code now}.
     * <p>
     * A moment in the past gives {@code "30 seconds ago"}, and one in the
     * future gives {@code "in 30 seconds"}. A difference under one second
     * gives {@code "just now"}. The amount is truncated, not rounded, so
     * 119 seconds is {@code "1 minute ago"}. {@code now} is a parameter
     * rather than read from the clock, so results are repeatable.
     *
     * @param then the moment to describe
     * @param now  the moment to compare against
     * @return the relative description
     */
    public static String format(Instant then, Instant now) {
        if (Duration.between(then, now).abs().compareTo(Duration.ofSeconds(1)) < 0) {
            return "just now";
        }
        Duration duration = Duration.between(then, now);
        boolean isNegative = duration.isNegative();
        long seconds = duration.abs().getSeconds();
        String unit = "second";
        long amount = seconds;
        // Second
        long second = seconds;
        if (second > 0 && second < 60) {
            unit = "second";
            amount = second;
        }
        // Minute
        long minute = second / 60;
        if (minute > 0 && minute < 60) {
            unit = "minute";
            amount = minute;
        }
        // Hour
        long hour = minute / 60;
        if (hour > 0 && hour < 24) {
            unit = "hour";
            amount = hour;
        }
        // Day
        long day = hour / 24;
        if (day > 0) {
            unit = "day";
            amount = day;
        }

        return isNegative ? "in " + phrase(amount, unit) : phrase(amount, unit) + " ago";
    }

    private static String phrase(long n, String unit) {
        if (n == 1) {
            return n + " " + unit;
        } else {
            return n + " " + unit + "s";
        }
    }

}