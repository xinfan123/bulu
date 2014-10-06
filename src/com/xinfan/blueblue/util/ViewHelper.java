package com.xinfan.blueblue.util;

import android.view.View;

public class ViewHelper
{
  public static boolean isGone(View paramView)
  {
    boolean bool = false;
    if ((paramView != null) && (paramView.getVisibility() == 8))
      bool = true;
    return bool;
  }

  public static boolean isVisible(View paramView)
  {
    boolean bool = false;
    if ((paramView != null) && (paramView.getVisibility() == 0))
      bool = true;
    return bool;
  }

  public static void setGone(View paramView)
  {
    if (paramView != null)
      paramView.setVisibility(8);
  }

  public static void setInvisible(View paramView)
  {
    if (paramView != null)
      paramView.setVisibility(4);
  }

  public static void setVisible(View paramView)
  {
    if (paramView != null)
      paramView.setVisibility(0);
  }
}