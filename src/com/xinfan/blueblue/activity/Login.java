package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.Md5PwdFactory;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.LoginParam;
import com.xinfan.msgbox.http.service.vo.result.LoginResult;

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

		String username = mUser.getText().toString();
		String passwd = mPassword.getText().toString();

		if (username.length() == 0 || passwd.length() == 0) {
			ToastUtil.showMessage(this, "用户名密码不能为空");
			return;
		}
		
		passwd = Md5PwdFactory.getUserMd5PwdEncoder().encodePassword(passwd);
		
		Request request = new Request(FunIdConstants.LOGIN);

		LoginParam param = new LoginParam();
		param.setMobile(username);
		param.setPasswd(passwd);
		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(this, request, new ObserverCallBack() {

			public void call(Request data) {

				ToastUtil.showMessage(Login.this, "登录成功");

				LoginResult result = (LoginResult) data.getResult();
				SharePreferenceUtil util = new SharePreferenceUtil(Login.this, Constants.USER_INFO);
				util.setUserId(result.getUserId());
				util.setMobile(util.getMobile());
				util.setUsername(result.getUserName());

				Intent intent = new Intent();
				intent.setClass(Login.this, MainActivity.class);
				startActivity(intent);
				
				Login.this.finish();
			}
		});

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
