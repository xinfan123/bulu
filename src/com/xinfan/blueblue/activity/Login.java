package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import com.xinfan.blueblue.common.LoadingDialogFragment;

public class Login extends Activity {
	private EditText mUser;
	private EditText mPassword;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		mUser = (EditText) findViewById(R.id.login_user_edit);
		mPassword = (EditText) findViewById(R.id.login_passwd_edit);

		/*
		 * AnsynHttpRequest.requestSimpleByPost(this, new ObserverCallBack(){
		 * 
		 * @Override public void call(Response data) { data.getCode();
		 * data.getValue(); }}, "login", new HashMap());
		 */

	}

	public void login_mainweixin(View v) {

		if ("1".equals(mUser.getText().toString()) && "1".equals(mPassword.getText().toString()))

		{
			final LoadingDialogFragment loading = LoadingDialogFragment.newInstance("正在登录...");
			loading.open(this);

			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					loading.close();
					Login.this.finish();

					Intent intent = new Intent();
					intent.setClass(Login.this, MainActivity.class);
					startActivity(intent);

				}

			}, 2000);

		} else if ("".equals(mUser.getText().toString()) || "".equals(mPassword.getText().toString()))

		{
			new AlertDialog.Builder(Login.this).setIcon(getResources().getDrawable(R.drawable.login_error_icon)).setTitle("登录失败").setMessage("密码错误").create()
					.show();
		} else {

			new AlertDialog.Builder(Login.this).setIcon(getResources().getDrawable(R.drawable.login_error_icon)).setTitle("登录失败").setMessage("登录失败").create()
					.show();
		}
	}

	public void login_back(View v) {
		this.finish();
	}

	public void login_pw(View v) {
		Intent intent = new Intent();
		intent.setClass(Login.this, ForgetPassword.class);
		startActivity(intent);
		// Intent intent = new Intent();
		// intent.setClass(Login.this,Whatsnew.class);
		// startActivity(intent);
	}
}
