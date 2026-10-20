package com.github.konstantinevashalomidze.humanformatter;

public final class ByteFormatter {

    private static final String[] units = { "KB", "MB", "GB", "TB", "PB", "EB" };

    private ByteFormatter() {}

    public static String format(long _byte) {
        if (Long.MIN_VALUE == _byte) {
            throw new InvalidInputException("_byte value shouldn't equal -9_223_372_036_854_775_808");
        }

        int sign = (_byte < 0) ? -1 : 1;
        _byte = Math.abs(_byte);

        if (_byte < 1_000) {
            return "%.1f B".formatted((double) sign * _byte);
        }

        for (int i = units.length - 1; i >= 0; i--) {
            double unit = _byte / (Math.pow(1000, i + 1));

            if (unit >= 1) {
                return "%.1f %s".formatted(sign * Math.floor(unit * 10) / 10, units[i]);
            }
        }
        throw new IllegalStateException("unreachable");
    }
}