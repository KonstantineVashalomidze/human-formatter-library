package com.github.konstantinevashalomidze.humanformatter;

/**
 * @author Konstantine Vashalomidze
 */
public final class CompactNumberFormatter {

    private CompactNumberFormatter() {}

    public static String format(long number) {
        if (Long.MIN_VALUE == number) {
            throw new InvalidInputException("number value shouldn't equal -9_223_372_036_854_775_808");
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