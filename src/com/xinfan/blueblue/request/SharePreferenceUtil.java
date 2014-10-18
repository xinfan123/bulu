package com.xinfan.blueblue.request;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtil {
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	public SharePreferenceUtil(Context context, String file) {
		sp = context.getSharedPreferences(file, context.MODE_PRIVATE);
		editor = sp.edit();
	}

	// 用户的密码
	public void setPasswd(String passwd) {
		editor.putString("passwd", passwd);
		editor.commit();
	}

	public String getPasswd() {
		return sp.getString("passwd", "");
	}

	public void setPwdRemember(boolean flag) {
		editor.putBoolean("PwdRemember", flag);
		editor.commit();
	}

	public boolean getPwdRemember() {
		return sp.getBoolean("PwdRemember", true);
	}

	public void setConsumerId(int id) {
		editor.putInt("consumerid", id);
		editor.commit();
	}

	public void setUserId(Long userid) {
		editor.putLong("userid", userid);
		editor.commit();
	}

	public Long getUserId() {
		return sp.getLong("userid", 0);
	}

	public String getUsername() {
		return sp.getString("username", "");
	}

	public void setUsername(String username) {
		editor.putString("username", username);
		editor.commit();
	}

	public void setMobile(String mobile) {
		editor.putString("mobile", mobile);
		editor.commit();
	}

	public String getMobile() {
		return sp.getString("mobile", "");
	}

	public void setIdcard(String idcard) {
		editor.putString("idcard", idcard);
		editor.commit();
	}

	public String getName() {
		return sp.getString("relname", "");
	}

	public void setName(String name) {
		editor.putString("relname", name);
		editor.commit();
	}

	// 用户的邮箱
	public String getEmail() {
		return sp.getString("email", "");
	}

	public void setEmail(String email) {
		editor.putString("email", email);
		editor.commit();
	}

	public String getServerAddress() {
		return sp.getString("server_address", "");
	}

	public void setServerAddress(String address) {
		editor.putString("server_address", address);
		editor.commit();
	}

	public String getServerPort() {
		return sp.getString("server_port", "");
	}

	public void setServerPort(String ip) {
		editor.putString("server_port", ip);
		editor.commit();
	}

	// 是否在后台运行标记
	public void setIsStart(boolean isStart) {
		editor.putBoolean("isStart", isStart);
		editor.commit();
	}

	public boolean getIsStart() {
		return sp.getBoolean("isStart", false);
	}

	// 是否第一次运行本应用
	public void setIsFirst(boolean isFirst) {
		editor.putBoolean("isFirst", isFirst);
		editor.commit();
	}

	public boolean getisFirst() {
		return sp.getBoolean("isFirst", true);
	}

	public void setVersion(String version) {
		editor.putString("version", version);
		editor.commit();
	}

	public String getVersion() {
		return sp.getString("version", "");
	}

}
