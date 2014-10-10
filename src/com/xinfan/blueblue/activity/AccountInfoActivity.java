package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AccountInfoActivity extends Activity {

	public static AccountInfoActivity instance;

	private RelativeLayout mnickName;
	private TextView nickName;
	private RelativeLayout mpassword;

	private View user_info_exit;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		mnickName = (RelativeLayout) findViewById(R.id.nickname_info_layout);
		nickName = (TextView) findViewById(R.id.nickname_info_edit);
		mpassword = (RelativeLayout) findViewById(R.id.password_update_layout);
		user_info_exit = findViewById(R.id.user_info_exit);
		mnickName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(AccountInfoActivity.this, UpdateNickname.class);
				startActivity(intent);

			}
		});
		mpassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(AccountInfoActivity.this, UpdatePassword.class);
				startActivity(intent);
			}
		});

		user_info_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(AccountInfoActivity.this, AccountEscActivity.class);
				startActivity(intent);
			}
		});

		instance = this;

	}

	public void btn_back(View v) {
		this.finish();
	}

	public void btn_back_send(View v) {
		this.finish();
	}

	public void updateNickName(String name) {
		nickName.setText(name);
	}
	
	/*
	 * public void head_xiaohei(View v) { Intent intent = new Intent();
	 * intent.setClass(InfoXiaohei.this,InfoXiaoheiHead.class);
	 * startActivity(intent); }
	 */
}