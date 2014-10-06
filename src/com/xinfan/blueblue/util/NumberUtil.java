package com.xinfan.blueblue.util;

public class NumberUtil
{
  private static final int POSITION_PRIME = 5;
  private static final int SECTION_PRIME = 3;

  public static int uniqueId(int paramInt1, int paramInt2)
  {
    return paramInt1 * 3 + paramInt2 * 5;
  }
}