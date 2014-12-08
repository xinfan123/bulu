package com.xinfan.blueblue.activity.context;


import android.content.Context;

import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.ShareSystemSet;

public class SystemSetContext {
	public static void setIsUpdate(Context context, boolean i,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		systemset.setIsUpdate(i);
	}

	public static boolean getIsUpdate(Context context,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		return systemset.getIsUpdate();
	}
	public static void setNewMsgNotify(Context context, boolean newmsgnotify,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		systemset.setNewMsgNotify(newmsgnotify);
	}

	public static boolean getNewMsgNotify(Context context,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		return systemset.getNewMsgNotify();
	}
	public static void setVibrate(Context context, boolean vibrate,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		systemset.setVibrate(vibrate);
	}

	public static boolean getVibrate(Context context,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		return systemset.getVibrate();
	}
	public static void setVoice(Context context, boolean voice,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		systemset.setVoice(voice);
	}

	public static boolean getVoice(Context context,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		return systemset.getVoice();
	}
	public static void setReceivenum(Context context, Integer receivenum,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		systemset.setReceivenum(receivenum);
	}

	public static Integer getReceivenum(Context context,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		return systemset.getReceivenum();
	}
	public static void setSimilarity(Context context, Integer similarity,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		systemset.setSimilarity(similarity);
	}

	public static Integer getSimilarity(Context context,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		return systemset.getSimilarity();
	}
	public static void setPaid(Context context, Integer paid,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		systemset.setPaid(paid);
	}

	public static Integer getPaid(Context context,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		return systemset.getPaid();
	}
	public static void setReputation(Context context, Integer reputation,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		systemset.setReputation(reputation);
	}

	public static Integer getReputation(Context context,Long userid) {
		ShareSystemSet systemset = new ShareSystemSet(context, Constants.SYSTEM_SET, userid);
		return systemset.getReputation();
	}
}
