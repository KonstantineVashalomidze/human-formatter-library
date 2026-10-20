package com.github.konstantinevashalomidze.humanformatter;

import java.time.Duration;

public final class DurationFormatter {

    private DurationFormatter() {}

    public static String format(Duration duration) {
        if (duration.getSeconds() == Long.MIN_VALUE) {
            throw new InvalidInputException("duration value shouldn't equal -9_223_372_036_854_775_808");
        }

        boolean isNegative = duration.isNegative();
        long seconds1 = duration.abs().getSeconds();

        long minutes1 = seconds1 / 60; long seconds2 = seconds1 % 60;
        long hours1 = minutes1 / 60; long minutes2 = minutes1 % 60;
        long days1 = hours1 / 24; long hours2 = hours1 % 24;

        StringBuilder sb = new StringBuilder();
        if (days1 != 0) {
            sb.append(days1).append("d ");
        }
        if (hours2 != 0) {
            sb.append(hours2).append("h ");
        }

        if (minutes2 != 0) {
            sb.append(minutes2).append("m ");
        }

        if (sb.isEmpty() || seconds2 != 0) {
            sb.append(seconds2).append("s");
        }

        return ((isNegative ? "-" : "") + sb).trim();
    }
}