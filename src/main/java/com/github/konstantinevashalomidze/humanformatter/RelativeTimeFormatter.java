package com.github.konstantinevashalomidze.humanformatter;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author Konstantine Vashalomidze
 */
public final class RelativeTimeFormatter {

    private RelativeTimeFormatter() {}

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

    public static String phrase(long n, String unit) {
        if (n == 1) {
            return n + " " + unit;
        } else {
            return n + " " + unit + "s";
        }
    }

}