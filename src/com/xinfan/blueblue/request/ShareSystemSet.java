package com.xinfan.blueblue.request;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareSystemSet {
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	public ShareSystemSet(Context context, String file,Long userid) {
		sp = context.getSharedPreferences(file+userid, Context.MODE_PRIVATE);
		editor = sp.edit();
	}

	// 是否接收新消息
	public void setNewMsgNotify(boolean newmsgnotify) {
		editor.putBoolean("newmsgnotify", newmsgnotify);
		editor.commit();
	}

	public boolean getNewMsgNotify() {
		return sp.getBoolean("newmsgnotify", false);
	}

	// 震动
	public void setVibrate(boolean vibrate) {
		editor.putBoolean("vibrate", vibrate);
		editor.commit();
	}

	public boolean getVibrate() {
		return sp.getBoolean("vibrate", false);
	}

	// 声音
	public void setVoice(boolean voice) {
		editor.putBoolean("voice", voice);
		editor.commit();
	}

	public boolean getVoice() {
		return sp.getBoolean("voice", false);
	}
//接收消息数量
	public void setReceivenum(String receivenum) {
		editor.putString("receivenum", receivenum);
		editor.commit();
	}

	public String getReceivenum() {
		return sp.getString("receivenum", "20条");
	}
//消息相似度
	public void setSimilarity(String similarity) {
		editor.putString("similarity", similarity);
		editor.commit();
	}

	public String getSimilarity() {
		return sp.getString("similarity", "三级");
	}
//有偿域
	public void setPaid(String paid) {
		editor.putString("paid", paid);
		editor.commit();
	}

	public String getPaid() {
		return sp.getString("paid", "所有");
	}
//信誉等级
		public void setReputation(String reputation) {
			editor.putString("reputation", reputation);
			editor.commit();
		}

		public String getReputation() {
			return sp.getString("reputation", "所有");
		}
}
