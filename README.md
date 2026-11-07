# human-formatter-library

A small, dependency-free library that turns raw numbers, durations and timestamps into
readable text, like `1.5 GB` or `2 days ago`.


## Features

* Formatting byte counts with __decimal__ units from B up to EB
* Formatting durations in days, hours, minutes and seconds
* Formatting relative times against an explicit __now__, with no months or years
* Formatting compact numbers with K, M, B and T suffixes
* Truncating to one decimal instead of rounding
* Static methods with no state
* No dependencies

## Installation

Installation via JitPack:

[![](https://jitpack.io/v/KonstantineVashalomidze/human-formatter-library.svg)](https://jitpack.io/#KonstantineVashalomidze/human-formatter-library)

## Quickstart

```java
import com.github.konstantinevashalomidze.humanformatter.*;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println(ByteFormatter.format(1_500_000_000L)); // 1.5 GB
        System.out.println(ByteFormatter.format(999_999)); // 999.9 KB, truncated not rounded

        System.out.println(DurationFormatter.format(Duration.ofSeconds(11_565))); // 3h 12m 45s

        Instant now = Instant.parse("2026-10-20T10:00:00Z");
        System.out.println(RelativeTimeFormatter.format(now.minus(2, ChronoUnit.DAYS), now)); // 2 days ago
        System.out.println(RelativeTimeFormatter.format(now.plus(3, ChronoUnit.HOURS), now)); // in 3 hours

        System.out.println(CompactNumberFormatter.format(1_500)); // 1.5K
        System.out.println(CompactNumberFormatter.format(-5_000)); // -5.0K

        try {
            ByteFormatter.format(Long.MIN_VALUE);
        } catch (HumanFormatterInputException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
```

Output:

```
1.5 GB
999.9 KB
3h 12m 45s
2 days ago
in 3 hours
1.5K
-5.0K
Rejected: _byte value shouldn't equal -9_223_372_036_854_775_808
```

## API Reference

### Formatting bytes
```java
ByteFormatter.format(800);              // "800.0 B", bytes also get one decimal
ByteFormatter.format(1_500);            // "1.5 KB", units are decimal (1 KB = 1_000 B)
ByteFormatter.format(999_999);          // "999.9 KB", truncated not rounded
ByteFormatter.format(-1_200);           // "-1.2 KB"
ByteFormatter.format(Long.MAX_VALUE);   // "9.2 EB"
```

### Formatting durations
```java
DurationFormatter.format(Duration.ZERO);               // "0s"
DurationFormatter.format(Duration.ofSeconds(90));      // "1m 30s", only non-zero units
DurationFormatter.format(Duration.ofHours(50));        // "2d 2h", days are the largest unit
DurationFormatter.format(Duration.ofMillis(-1_500));   // "-1s", fractions are dropped
```

### Formatting relative times
```java
RelativeTimeFormatter.format(then, now);    // "30 seconds ago" when then is 30 seconds before now
RelativeTimeFormatter.format(then, now);    // "in 2 hours" when then is 2 hours after now
RelativeTimeFormatter.format(then, now);    // "just now" when the difference is under 1 second
RelativeTimeFormatter.format(then, now);    // "400 days ago", no months or years
```

### Formatting compact numbers
```java
CompactNumberFormatter.format(999);             // "999"
CompactNumberFormatter.format(1_500_000);       // "1.5M"
CompactNumberFormatter.format(999_999);         // "999.9K", truncated not rounded
CompactNumberFormatter.format(Long.MAX_VALUE);  // "9223372.0T", keeps counting in T
```

### Invalid input
```java
ByteFormatter.format(Long.MIN_VALUE);                         // Throws HumanFormatterInputException
DurationFormatter.format(Duration.ofSeconds(Long.MIN_VALUE)); // Throws HumanFormatterInputException
CompactNumberFormatter.format(Long.MIN_VALUE);                // Throws HumanFormatterInputException
```

## License

MIT - see [LICENSE](LICENSE) for details.