package com.codeaddi.mrc_resources.controller.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

    public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static Date getDateFromString(String input) {
        if (input.isEmpty()) {
            return null;
        } else {
            try {
                LocalDate localDate = LocalDate.parse(input, dateFormat);
                return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            } catch (Exception e) {
                return null;
            }
        }
    }


    public static Map<Date, LocalTime> getDateAndTimeFromLocalDateTime(LocalDateTime localDateTime) {
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        LocalTime time = localDateTime.toLocalTime();
        Map<Date, LocalTime> map = new HashMap<>();
        map.put(date, time);
        return map;
    }
}
