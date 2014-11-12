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
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.activity.context.SystemConfigContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.Md5PwdFactory;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.LoginParam;
import com.xinfan.msgbox.http.service.vo.result.LoginResult;

public class WelcomeActivity extends BaseActivity {
	private EditText mUser;
	private EditText mPassword;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		SystemConfigContext.init(this);
		mUser = (EditText) findViewById(R.id.login_user_edit);
		mPassword = (EditText) findViewById(R.id.login_passwd_edit);
		init();
		
		autoLogin();
	}
	
	public void autoLogin(){
			
	}
	public void welcome_login(View v) {

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
					
					ToastUtil.showMessage(WelcomeActivity.this,result.getMsg());
					
					SharePreferenceUtil util = new SharePreferenceUtil(WelcomeActivity.this, Constants.USER_INFO);
					util.setUserId(result.getUserId());
					util.setMobile(result.getMobile());
					util.setUsername(result.getUserName());
					util.setPasswd(enPasswd);
					util.setCID(result.getCid());
					
					Intent intent = new Intent();
					intent.setClass(WelcomeActivity.this, MainActivity.class);
					startActivity(intent);
					WelcomeActivity.this.finish();
					
					LoginUserContext.setIsLogin(WelcomeActivity.this, true);
					
				}else{
					ToastUtil.showMessage(WelcomeActivity.this,result.getMsg());
				}
			
			}
		});

	}

	public void login_back(View v) {
		this.finish();
	}

	public void login_pw(View v) {
		Intent intent = new Intent();
		intent.setClass(WelcomeActivity.this, ForgetPasswordStep1.class);
		startActivity(intent);
		// Intent intent = new Intent();
		// intent.setClass(Login.this,Whatsnew.class);
		// startActivity(intent);
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

//	public void welcome_login(View v) {
//		Intent intent = new Intent();
//		intent.setClass(WelcomeActivity.this, LoginActivity.class);
//		startActivity(intent);
//		this.finish();
//	}

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
