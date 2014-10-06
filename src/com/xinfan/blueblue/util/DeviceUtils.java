package com.xinfan.blueblue.util;

import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class DeviceUtils
{
  private static final String DEVICE_KEY = "eleme_device.key";
  private static final int DEVICE_KEY_LENGTH = 32;
  private static final int MOD_UNIT = 10;

  private static String getAndroidId(Context paramContext)
  {
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    return str;
  }

  private static String getDeviceId(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
  }

  public static String getDeviceUUID(Context paramContext)
  {
	  return null;
  }

  private static String getHardwareID(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder("35");
    localStringBuilder.append(Build.BOARD.length() % 10);
    localStringBuilder.append(Build.BOARD.length() % 10);
    localStringBuilder.append(Build.BRAND.length() % 10);
    localStringBuilder.append(Build.CPU_ABI.length() % 10);
    localStringBuilder.append(Build.DEVICE.length() % 10);
    localStringBuilder.append(Build.DISPLAY.length() % 10);
    localStringBuilder.append(Build.HOST.length() % 10);
    localStringBuilder.append(Build.ID.length() % 10);
    localStringBuilder.append(Build.MANUFACTURER.length() % 10);
    localStringBuilder.append(Build.MODEL.length() % 10);
    localStringBuilder.append(Build.PRODUCT.length() % 10);
    localStringBuilder.append(Build.TAGS.length() % 10);
    localStringBuilder.append(Build.TYPE.length() % 10);
    localStringBuilder.append(Build.USER.length() % 10);
    LogUtil.d("hardwareID:", localStringBuilder.toString());
    return localStringBuilder.toString();
  }

  private static byte[] getSecret(Context paramContext)
    throws UnsupportedEncodingException
  {
    return (getDeviceId(paramContext) + getHardwareID(paramContext) + getAndroidId(paramContext)).getBytes("UTF-8");
  }
}