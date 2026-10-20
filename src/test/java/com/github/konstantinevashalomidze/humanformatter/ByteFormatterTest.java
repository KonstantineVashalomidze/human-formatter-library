package com.github.konstantinevashalomidze.humanformatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Konstantine Vashalomidze
 */
class ByteFormatterTest {
    @Test
    void formatReturnsByteCountWithABSuffixWhenValueIsUnder1_000() {
        String actual = ByteFormatter.format(999);
        String expected = "999.0 B";
        assertEquals(expected, actual);
    }

    @Test
    void formatReturnsKiloByteCountWithAKBSuffixWhenValueIsUnder1_000_000() {
        String actual = ByteFormatter.format(999 * 1_000);
        String expected = "999.0 KB";
        assertEquals(expected, actual);
    }

    @Test
    void formatReturnsMegaByteCountWithAMBSuffixWhenValueIsUnder1_000_000_000() {
        String actual = ByteFormatter.format(999 * 1_000 * 1_000);
        String expected = "999.0 MB";
        assertEquals(expected, actual);
    }

    @Test
    void formatReturnsGigaByteCountWithAGBSuffixWhenValueIsUnder1_000_000_000_000() {
        String actual = ByteFormatter.format(999L * 1_000 * 1_000 * 1_000);
        String expected = "999.0 GB";
        assertEquals(expected, actual);
    }

    @Test
    void formatTruncatesWithOneDecimalPlaces() {
        String actual = ByteFormatter.format(999_955_000_000_000L);
        String expected = "999.9 TB";
        assertEquals(expected, actual);
    }

    @Test
    void formatReturnsExaByteCountWithAEBSuffixWhenValueIsUnderLongMaxValue() {
        String actual = ByteFormatter.format(Long.MAX_VALUE);
        String expected = "9.2 EB";
        assertEquals(expected, actual);
    }

    @Test
    void formatHandlesNegativeValue() {
        String actual = ByteFormatter.format(-1200);
        String expected = "-1.2 KB";
        assertEquals(expected, actual);
    }

    @Test
    void formatThrowsOnLongMinValue() {
        assertThrows(InvalidInputException.class, () -> ByteFormatter.format(Long.MIN_VALUE));
    }

    @Test
    void formatHandlesBoundaryValues() {
        String expectedOne = "999.0 B";
        String expectedTwo = "1.0 KB";

        String actualOne = ByteFormatter.format(999);
        assertEquals(expectedOne, actualOne);

        String actualTwo = ByteFormatter.format(1000);
        assertEquals(expectedTwo, actualTwo);
    }

    @Test
    void formatHandlesNegativeBytes() {
        String actual = ByteFormatter.format(-1);
        String expected = "-1.0 B";
        assertEquals(expected, actual);
    }

    @Test
    void formatHandlesZeroBytes() {
        String actual = ByteFormatter.format(0);
        String expected = "0.0 B";
        assertEquals(expected, actual);
    }


    @Test
    void formatTruncatesNegativeValues() {
        String actual = ByteFormatter.format(-999_999);
        String expected = "-999.9 KB";
        assertEquals(expected, actual);
    }

    @Test
    void formatHandlesNegativeLongMaxValue() {
        String actual = ByteFormatter.format(-Long.MAX_VALUE);
        String expected = "-9.2 EB";
        assertEquals(expected, actual);
    }

}