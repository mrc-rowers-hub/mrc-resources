package com.codeaddi.mrc_resources.controller.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

  public static Map<Date, LocalTime> getDateAndTimeFromLocalDateTime(LocalDateTime localDateTime) {
    Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    LocalTime time = localDateTime.toLocalTime();
    Map<Date, LocalTime> map = new HashMap<>();
    map.put(date, time);
    return map;
  }
}
