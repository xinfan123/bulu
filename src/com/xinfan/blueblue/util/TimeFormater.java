package com.xinfan.blueblue.util;

import java.util.Calendar;

public class TimeFormater
{
  private static final int HOUR_OF_DAY = 24;
  private static final int MAX_OFFSET_HOUR = 6;
  private static final int UNIT = 60;

  public static String getFormatedTime(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    String str = "";
    if ((StringUtil.isBlank(paramString1) | StringUtil.isBlank(paramString2) | StringUtil.isBlank(paramString3) | StringUtil.isBlank(paramString4))){
      str = "";
    }
    else{
    	
 
      Calendar localCalendar1 = CalendarUtils.getCalendar(paramString3, paramString4);
      Calendar localCalendar2 = CalendarUtils.getCalendar(paramString1, paramString2);
      if (CalendarUtils.getYear(localCalendar1) != CalendarUtils.getYear(localCalendar2))
      {
        str = DateUtil.getDateString(localCalendar2.getTime(), "MM-dd HH:dd");
      }
      else
      {
        /*int i = CalendarUtils.getDateOfYear(localCalendar1) - CalendarUtils.getDateOfYear(localCalendar2);
        int j = 0;
        if (i == 0)
          j = CalendarUtils.getHourOfDay(localCalendar1) - CalendarUtils.getHourOfDay(localCalendar2);
        int k;
        while (true)
        {
          k = CalendarUtils.getMinute(localCalendar1) - CalendarUtils.getMinute(localCalendar2);
          if (j < 6)
            break ;
          str = DateUtil.getDateString(localCalendar2.getTime(), "MM-dd HH:dd");
          if (i >= 1)
            j = i * 24 - CalendarUtils.getHourOfDay(localCalendar1) + CalendarUtils.getHourOfDay(localCalendar2);
        }
        
        if ((k == 0) && (j == 0))
        {
          str = "刚刚";
        }
        else
        {
          int m = Math.abs(k + j * 60);
          int n = m / 60;
          int i1 = m % 60;
          if (n == 0)
            str = i1 + "分钟前";
          else
            str = n + "小时" + i1 + "分钟前";
        }*/
      }
      
     
  } return str;
  
  }
}