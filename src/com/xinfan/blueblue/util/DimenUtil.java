package com.xinfan.blueblue.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DimenUtil
{
  private static final float PADDING = 0.5F;

  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density);
  }

  public static int getScreenHeight(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().heightPixels;
  }

  public static int getScreenWidth(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels;
  }

  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat / paramContext.getResources().getDisplayMetrics().density);
  }
}