package com.xinfan.blueblue.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

public class NetUtil {
	public static String getBlueToothMac(Context paramContext) {
		return BluetoothAdapter.getDefaultAdapter().getAddress();
	}

	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			LogUtil.e("WifiPreference IpAddress", ex.toString());
		}
		return null;
	}

	public static String getLocalMacAddress(Context paramContext) {
		return ((WifiManager) paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
	}

	public static String getWlanMac(Context paramContext) {
		String str = ((WifiManager) paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
		LogUtil.d("wlanMac:", str);
		return str;
	}

	public static boolean isConnectingToInternet(Context paramContext) {
		boolean bool = false;
		ConnectivityManager localConnectivityManager = (ConnectivityManager) paramContext.getSystemService("connectivity");
		if (localConnectivityManager == null) {
			return bool;
		}

		NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
		if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable())) {
			bool = true;
		}
		return bool;
	}

	public static boolean isWiFi(Context paramContext) {
		boolean bool = false;
		try {
			ConnectivityManager localConnectivityManager = (ConnectivityManager) paramContext.getSystemService("connectivity");
			if (localConnectivityManager != null) {
				NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
				if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()) && (localNetworkInfo.getState() == NetworkInfo.State.CONNECTED)) {
					bool = "WIFI".equalsIgnoreCase(localNetworkInfo.getTypeName());
				}
			}
		} catch (Exception localException) {
			bool = false;
		}

		return bool;
	}
}