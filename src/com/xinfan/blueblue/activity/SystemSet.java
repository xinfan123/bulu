package com.xinfan.blueblue.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R.id;
import com.xinfan.blueblue.activity.send.SendMessageActivity;
import com.xinfan.blueblue.activity.systemset.MessageNumSelectActivity;



public class SystemSet extends Activity {
	 private TextView receivenumText;//消息数量
	 public static SystemSet instance;
	// private String ComplainText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.system_set);
		receivenumText=(TextView) findViewById(R.id.receivenum_sytem_tv);
		// 监听接收数量设置
		LinearLayout receivenum = (LinearLayout) findViewById(R.id.receivenum_sytem_layout);
		receivenum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SystemSet.this,MessageNumSelectActivity.class);
				startActivity(intent);
			}
		});
		// 监听消息相似度
		LinearLayout similarity = (LinearLayout) findViewById(R.id.similarity_sytem_layout);
		similarity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SystemSet.this, ForgetPassword.class);
				startActivity(intent);
			}
		});
		// 监听有偿设置
		LinearLayout paid = (LinearLayout) findViewById(R.id.paid_sytem_layout);
		paid.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SystemSet.this, ForgetPassword.class);
				startActivity(intent);
			}
		});
		// 监听信誉等级
		LinearLayout reputation = (LinearLayout) findViewById(R.id.reputation_sytem_layout);
		reputation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SystemSet.this, ForgetPassword.class);
				startActivity(intent);
			}
		});
		// 监听系统升级
		LinearLayout goupSystem = (LinearLayout) findViewById(R.id.goup_system_layout);
		goupSystem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SystemSet.this, UpdateVersionActivity.class);
				startActivity(intent);
			}
		});
		// 监听关于我们
		LinearLayout aboutUs = (LinearLayout) findViewById(R.id.about_us_layout);
		aboutUs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SystemSet.this, ForgetPassword.class);
				startActivity(intent);
			}
		});
	}



	public void SystemSetBack(View v) { // 返回
		this.finish();
	}
	public void SetMessageNum(String name) {
		receivenumText.setText(name);
	}

}
