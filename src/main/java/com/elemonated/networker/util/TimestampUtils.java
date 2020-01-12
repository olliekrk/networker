package com.elemonated.networker.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

public interface TimestampUtils {
    static Instant alignTimestampToHours(Timestamp timestamp) {
        return timestamp.toInstant().truncatedTo(ChronoUnit.HOURS);
    }

    static List<Instant> alignTimestampRangeToHours(Timestamp startTimestamp, Timestamp endTimestamp) {
        List<Instant> resultList = new LinkedList<>();
        Instant currentInstant = alignTimestampToHours(startTimestamp);
        Instant lastInstant = alignTimestampToHours(endTimestamp);

        while (!currentInstant.isAfter(lastInstant)) {
            resultList.add(currentInstant);
            currentInstant = currentInstant.plus(1, ChronoUnit.HOURS);
        }

        return resultList;
    }

}
