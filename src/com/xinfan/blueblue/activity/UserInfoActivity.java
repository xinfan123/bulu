package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserInfoActivity extends Activity {
	
	public static UserInfoActivity instance;
	
	private RelativeLayout mnickName;
	private TextView nickName;
	private  RelativeLayout mpassword;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		mnickName=(RelativeLayout) findViewById(R.id.nickname_info_layout);
		nickName=(TextView) findViewById(R.id.nickname_info_edit);
		mpassword= (RelativeLayout) findViewById(R.id.password_update_layout);
		mnickName.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
		        intent.setClass(UserInfoActivity.this,UpdateNickname.class);
		        startActivity(intent);

			}
		});
		mpassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
		        intent.setClass(UserInfoActivity.this,UpdatePassword.class);
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
	
	public void updateNickName(String name){
		nickName.setText(name);
	}
	/*
	 * public void head_xiaohei(View v) { Intent intent = new Intent();
	 * intent.setClass(InfoXiaohei.this,InfoXiaoheiHead.class);
	 * startActivity(intent); }
	 */
}