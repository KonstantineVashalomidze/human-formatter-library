package com.github.konstantinevashalomidze.humanformatter;

/**
 * Formats byte counts as human-readable text with {@link ByteFormatter#format(long)}.
 * <br />
 * For example:
 * <pre>
 *      999 B -> 999.0 B
 *      999_000 B -> 999.0 KB
 *      999_000_000 B -> 999.0 MB
 * </pre>
 * Negative values are also possible:
 * <pre>
 *      -1200 B -> -1.2 KB
 * </pre>
 * Bytes are truncated not rounded:
 * <pre>
 *      999_999 B -> 999.9 KB (NOT 1000.0 KB or 1.0 MB)
 * </pre>
 * {@link Long#MIN_VALUE} throws {@link HumanFormatterInputException}
 * Units are decimal, from B to EB (1 KB = 1_000 B not 1_024)
 *
 * @author <a href="https://portfolio.kosta-server.org/">Konstantine Vashalomidze</a>
 */
public final class ByteFormatter {

    private static final String[] units = { "KB", "MB", "GB", "TB", "PB", "EB" };

    private ByteFormatter() {}

    /**
     * Formats a byte count as human-readable text.
     * <p>
     * The result always has one decimal digit, such as {@code "1.5 KB"} or
     * {@code "800.0 B"}. Extra digits are truncated, not rounded. Units are
     * decimal and run from B up to EB.
     *
     * @param _byte the number of bytes; negative values are allowed
     * @return the formatted size, with a leading {@code -} for negative values
     * @throws HumanFormatterInputException if {@code _byte} is {@link Long#MIN_VALUE}
     */
    public static String format(long _byte) {
        if (Long.MIN_VALUE == _byte) {
            throw new HumanFormatterInputException("_byte value shouldn't equal -9_223_372_036_854_775_808");
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