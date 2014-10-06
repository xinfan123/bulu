package com.xinfan.blueblue.util;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class StringUtil
{
  private static final String CELL_PHONE_REGEX = "^1\\d{10}$";
  private static final String MAIL_REGEX = "([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})";
  private static final int TEN = 10;

  public static String charArray2String(char[] paramArrayOfChar)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfChar.length;
    for (int j = 0; j < i; j++)
      localStringBuilder.append(paramArrayOfChar[j]);
    return localStringBuilder.toString();
  }

  public static String formatDouble(double paramDouble)
  {
    return new DecimalFormat("####.##").format(paramDouble);
  }

  public static String formatMoney(double paramDouble)
  {
    return "¥" + new DecimalFormat("####.##").format(paramDouble);
  }

  private static String formatTime(int paramInt)
  {
    if (paramInt < 10);
    for (String str = "0" + paramInt; ; str = paramInt + "")
      return str;
  }

  public static String getTimeString(int paramInt1, int paramInt2)
  {
    return formatTime(paramInt1) + ":" + formatTime(paramInt2);
  }

  public static boolean isBlank(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString.trim())));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isCellPhone(String paramString)
  {
    if (isBlank(paramString));
    for (boolean bool = false; ; bool = Pattern.matches("^1\\d{10}$", paramString))
      return bool;
  }

  public static boolean isEmail(String paramString)
  {
    if (isBlank(paramString));
    for (boolean bool = false; ; bool = Pattern.matches("([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})", paramString))
      return bool;
  }

  public static boolean isNotBlank(String paramString)
  {
    if (!isBlank(paramString));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static String trim(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
      paramString = paramString.trim();
    return paramString;
  }
}