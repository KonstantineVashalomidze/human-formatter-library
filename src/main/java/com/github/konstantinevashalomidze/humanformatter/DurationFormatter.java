package com.github.konstantinevashalomidze.humanformatter;

import java.time.Duration;

/**
 * Formats durations as human-readable text.
 * <p>
 * Example: {@code 3h 12m 45s}.
 *
 * @author Konstantine Vashalomidze
 */
public final class DurationFormatter {

    private DurationFormatter() {}

    /**
     * Formats a duration using days, hours, minutes and seconds.
     * <p>
     * Only non-zero units are shown, largest first, so 90 seconds becomes
     * {@code "1m 30s"}. A zero duration returns {@code "0s"}. Days are the
     * largest unit, so 50 hours becomes {@code "2d 2h"}. Fractions of a
     * second are dropped (truncated toward zero), so -1.5 seconds becomes
     * {@code "-1s"}.
     *
     * @param duration the duration to format; negative values are allowed
     * @return the formatted duration, with a leading {@code -} for negative values
     * @throws HumanFormatterInputException if the duration's seconds equal {@link Long#MIN_VALUE}
     */
    public static String format(Duration duration) {
        if (duration.getSeconds() == Long.MIN_VALUE) {
            throw new HumanFormatterInputException("duration value shouldn't equal -9_223_372_036_854_775_808");
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