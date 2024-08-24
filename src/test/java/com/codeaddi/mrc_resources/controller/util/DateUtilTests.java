package com.codeaddi.mrc_resources.controller.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTests {
    @Test
    public void getDateAndTimeFromLocalDateTime_validInput_extractsDateAndTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 25, 13, 30);

        Map<Date, LocalTime> result = DateUtil.getDateAndTimeFromLocalDateTime(localDateTime);

        assertEquals(1, result.size());
        Date expectedDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        LocalTime expectedTime = localDateTime.toLocalTime();
        assertEquals(expectedDate, result.keySet().iterator().next());
        assertEquals(expectedTime, result.values().iterator().next());
    }
}
