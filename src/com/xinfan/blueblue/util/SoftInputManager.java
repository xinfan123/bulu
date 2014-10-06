package com.xinfan.blueblue.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

public class SoftInputManager
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  private static final int MIN_DELTA = 100;
  private InputMethodManager imm;
  private SoftInputListener softInputListener;
  private Window window;

  private SoftInputManager(Context paramContext)
  {
    this.imm = ((InputMethodManager)paramContext.getSystemService("input_method"));
  }

  public static SoftInputManager getInstance(Context paramContext)
  {
    return new SoftInputManager(paramContext);
  }

  public static void hideSoftInput(Activity paramActivity)
  {
      getInstance(paramActivity).hideSoftInput(paramActivity.getWindow().getDecorView().getWindowToken());
  }

  public static void hideSoftInput(Context paramContext, IBinder paramIBinder)
  {
      getInstance(paramContext).hideSoftInput(paramIBinder);
  }

  public static void hideSoftInput(Context paramContext, View paramView)
  {
      getInstance(paramContext).hideSoftInput(paramView);
  }

  public static void hideSoftInput(Window paramWindow)
  {
      getInstance(paramWindow.getContext()).hideSoftInput(paramWindow.getDecorView().getWindowToken());
  }

  public static void showSoftInput(Context paramContext, View paramView)
  {
      getInstance(paramContext).showSoftInput(paramView);
  }

  public void hideSoftInput(IBinder paramIBinder)
  {
    this.imm.hideSoftInputFromWindow(paramIBinder, 2);
  }

  public void hideSoftInput(View paramView)
  {
      paramView.clearFocus();
      hideSoftInput(paramView.getWindowToken());
  }

  public void onGlobalLayout()
  {
    if ((this.window != null) && (this.softInputListener != null))
    {
      Rect localRect = new Rect();
      View localView = this.window.getDecorView();
      localView.getWindowVisibleDisplayFrame(localRect);
      int i = localView.getRootView().getHeight() - localRect.bottom;
      if (i > 100)
        this.softInputListener.onShow();
      if (i == 0)
        this.softInputListener.onHide();
    }
  }

  public void removeSoftInputListener()
  {
	  
  }

  public void setSoftInputListener(Window paramWindow, SoftInputListener paramSoftInputListener)
  {
    if ((paramWindow != null) && (paramSoftInputListener != null))
    {
      this.window = paramWindow;
      this.softInputListener = paramSoftInputListener;
      paramWindow.getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this);
    }
  }

  public void showSoftInput(final View paramView)
  {
      paramView.post(new Runnable()
      {
        public void run()
        {
          paramView.requestFocus();
          SoftInputManager.this.imm.showSoftInput(paramView, 1);
        }
      });
  }

  public static abstract interface SoftInputListener
  {
    public abstract void onHide();

    public abstract void onShow();
  }
}