package com.github.konstantinevashalomidze.humanformatter;

/**
 * Formats numbers in compact form, such as {@code "1.2K"} or {@code "3.4M"}.
 * <p>
 * Suffixes are K (thousand), M (million), B (billion) and T (trillion).
 *
 * @author Konstantine Vashalomidze
 */
public final class CompactNumberFormatter {

    private CompactNumberFormatter() {}

    /**
     * Formats a number in compact form.
     * <p>
     * Numbers under 1,000 are returned as plain integers ({@code "999"}).
     * Larger numbers get one decimal digit and a suffix, truncated rather
     * than rounded, so 999,999 becomes {@code "999.9K"}. Numbers past a
     * trillion keep counting in T, so {@link Long#MAX_VALUE} becomes
     * {@code "9223372.0T"}.
     *
     * @param number the number to format; negative values are allowed
     * @return the compact text, with a leading {@code -} for negative values
     * @throws HumanFormatterInputException if {@code number} is {@link Long#MIN_VALUE}
     */
    public static String format(long number) {
        if (Long.MIN_VALUE == number) {
            throw new HumanFormatterInputException("number value shouldn't equal -9_223_372_036_854_775_808");
        }

        boolean isNegative = number < 0;
        number = Math.abs(number);
        if (number <= 999) {
            return (isNegative ? "-" : "") + (number + "");
        } else {
            if (number < 1_000_000L) {
                return (isNegative ? "-" : "") + String.format("%.1fK", Math.floor((number / 1000.) * 10) / 10);
            } else if (number < 1_000_000_000L) {
                return (isNegative ? "-" : "") + String.format("%.1fM", Math.floor((number / 1_000_000.) * 10) / 10);
            } else if (number < 1_000_000_000_000L) {
                return (isNegative ? "-" : "") + String.format("%.1fB", Math.floor((number / 1_000_000_000.) * 10) / 10);
            } else {
                return (isNegative ? "-" : "") + String.format("%.1fT", Math.floor((number / 1_000_000_000_000.) * 10) / 10);
            }
        }
    }

}