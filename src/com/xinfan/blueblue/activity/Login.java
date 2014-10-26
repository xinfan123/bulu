package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.RequestSucessCallBack;
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
		
		final String enPasswd = Md5PwdFactory.getUserMd5PwdEncoder().encodePassword(passwd);
		
		Request request = new Request(FunIdConstants.LOGIN);
		LoginParam param = new LoginParam();
		param.setMobile(username);
		param.setPasswd(enPasswd);
		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {

			public void call(Request data) {

				
				LoginResult result = (LoginResult) data.getResult();
				if(result.getResult()==1){
					
					ToastUtil.showMessage(Login.this,result.getMsg());
					
					SharePreferenceUtil util = new SharePreferenceUtil(Login.this, Constants.USER_INFO);
					util.setUserId(result.getUserId());
					util.setMobile(result.getMobile());
					util.setUsername(result.getUserName());
					util.setPasswd(enPasswd);
					util.setCID(result.getCid());
					
					Intent intent = new Intent();
					intent.setClass(Login.this, MainActivity.class);
					startActivity(intent);
					Login.this.finish();
					
					LoginUserContext.setIsLogin(Login.this, true);
					
				}else{
					ToastUtil.showMessage(Login.this,result.getMsg());
				}
			
			}
		});

	}

	public void login_back(View v) {
		this.finish();
	}

	public void login_pw(View v) {
		Intent intent = new Intent();
		intent.setClass(Login.this, ForgetPasswordStep1.class);
		startActivity(intent);
		// Intent intent = new Intent();
		// intent.setClass(Login.this,Whatsnew.class);
		// startActivity(intent);
	}
}
