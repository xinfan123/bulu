package com.xinfan.blueblue.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;

import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.activity.context.SystemConfigContext;
import com.xinfan.blueblue.request.Constants;

public class WelcomeActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);

		SystemConfigContext.init(this);

		init();
		
		autoLogin();
	}
	
	public void autoLogin(){
			
	}

	public void init() {
		View view = this.findViewById(R.id.linearLayout1);
		view.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				showConfigWindow();
				return false;
			}
		});
	}

	public void welcome_login(View v) {
		Intent intent = new Intent();
		intent.setClass(WelcomeActivity.this, LoginActivity.class);
		startActivity(intent);
		this.finish();
	}

	public void welcome_register(View v) {
		Intent intent = new Intent();
		intent.setClass(WelcomeActivity.this, RegisterStep1Activity.class);
		startActivity(intent);
		// AccountInfoActivity.instance.finish();
	}

	public void showConfigWindow() {
		LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.config, null);
		final EditText config_address = (EditText) view.findViewById(R.id.config_address);

		config_address.setText(SystemConfigContext.getAddress(this));

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("设置服务器地址");
		builder.setView(view);
		builder.setNeutralButton("默认", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				config_address.setText(Constants.http.http_request_ip);
				SystemConfigContext.setAddress(WelcomeActivity.this, Constants.http.http_request_ip);
			}
		});

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String address = config_address.getText().toString().trim();
				SystemConfigContext.setAddress(WelcomeActivity.this, address);
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.create().show();
	}
}
