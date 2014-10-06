package com.xinfan.blueblue.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/**
 * ip帮助类
 * 
 * @author cyp
 * 
 */
public class IpUtils {

	/**
	 * 获取ip地址，可能会有多网卡情况
	 */
	public static List<String> getServerIPs() {
		InetAddress[] inetAdds;
		List<String> ips = new ArrayList<String>();
		try {
			inetAdds = InetAddress.getAllByName(InetAddress.getLocalHost()
					.getHostName());
			for (int i = 0; i < inetAdds.length; i++) {
				ips.add(inetAdds[i].getHostAddress());
			}
		} catch (UnknownHostException e) {
			Log.e("读取IP地址异常", e.getMessage());
		}
		return ips;
	}

	public static String getServerIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			Log.e("读取IP地址异常", e.getMessage());
		}

		return "";

	}
}
