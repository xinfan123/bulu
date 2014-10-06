package com.xinfan.blueblue.util;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarUtils
{
  private static final int MILLIS_SECONDS_UNIT = 1000;

  public static Calendar getCalendar(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeZone(TimeZone.getDefault());
    localCalendar.setTimeInMillis(1000L * paramLong);
    return localCalendar;
  }

  public static Calendar getCalendar(String paramString1, String paramString2)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(DateUtil.getDate(paramString1, paramString2));
    return localCalendar;
  }

  public static int getDateOfYear(Calendar paramCalendar)
  {
    return paramCalendar.get(6);
  }

  public static int getHourOfDay(Calendar paramCalendar)
  {
    return paramCalendar.get(11);
  }

  public static int getMinute(Calendar paramCalendar)
  {
    return paramCalendar.get(12);
  }

  public static Calendar getSystemCalendar()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(DateUtil.getSystemDate());
    return localCalendar;
  }

  public static int getYear(Calendar paramCalendar)
  {
    return paramCalendar.get(1);
  }
}