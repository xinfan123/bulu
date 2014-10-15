package com.xinfan.blueblue.activity;

import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.ChangePasswdAfterLoginParam;
import com.xinfan.msgbox.http.service.vo.param.LoginParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;
import com.xinfan.msgbox.http.service.vo.result.LoginResult;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdatePassword extends Activity {
	private EditText mNewPassword; 
	private EditText mRePassword; 
	private String newPassword;
	private String rePassword;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_password);

		mNewPassword = (EditText) findViewById(R.id.new_password_edit);
		mRePassword = (EditText) findViewById(R.id.re_password_edit);


	}

	public void set_passowrd(View v) {
		newPassword=mNewPassword.getText().toString();
		rePassword=mRePassword.getText().toString();
		if (newPassword==null||newPassword.equals("")){
			ToastUtil.showMessage(UpdatePassword.this, "密码不能为空！");
		} 																							
		 else if(!(newPassword.equals(rePassword))){
			 ToastUtil.showMessage(UpdatePassword.this, "两次密码不一致");
		}
		else {
			Request request = new Request(FunIdConstants.CHANGE_PASSWD_AFTER_LOGIN);
			ChangePasswdAfterLoginParam param = new ChangePasswdAfterLoginParam();
			SharePreferenceUtil util = new SharePreferenceUtil(UpdatePassword.this, Constants.USER_INFO);
			Long userId=util.getUserId();
			param.setNewPasswd(newPassword);
			param.setOldPasswd(rePassword);
			param.setUserId(userId);
			request.setParam(param);
			

			AnsynHttpRequest.requestSimpleByPost(this, request, new ObserverCallBack() {

				public void call(Request data) {

				

					BaseResult result = (BaseResult) data.getResult();
					ToastUtil.showMessage(UpdatePassword.this, result.getMsg());		
				
				}
			});

		
		}
	}

	public void Update_passowrd_back(View v) {
		this.finish();
	}


}
