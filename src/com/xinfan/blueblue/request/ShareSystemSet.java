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
	public void setIsUpdate(boolean i){
		editor.putBoolean("isUpdate", i);
		editor.commit();
	}
	public boolean getIsUpdate() {
		return sp.getBoolean("isUpdate", false);
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
	public void setReceivenum(Integer receivenum) {
		editor.putInt("receivenum", receivenum);
		editor.commit();
	}

	public Integer getReceivenum() {
		return sp.getInt("receivenum", 20);
	}
//消息相似度
	public void setSimilarity(Integer similarity) {
		editor.putInt("similarity", similarity);
		editor.commit();
	}

	public Integer getSimilarity() {
		return sp.getInt("similarity", 3);
	}
//有偿域
	public void setPaid(Integer paid) {
		editor.putInt("paid", paid);
		editor.commit();
	}

	public Integer getPaid() {
		return sp.getInt("paid", 0);
	}
//信誉等级
		public void setReputation(Integer reputation) {
			editor.putInt("reputation", reputation);
			editor.commit();
		}

		public Integer getReputation() {
			return sp.getInt("reputation", 0);
		}
}
