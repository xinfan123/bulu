package com.xinfan.blueblue.activity;

import com.xinfan.blueblue.util.ToastUtil;

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
			ToastUtil.showMessage(this, "修改成功！");
			this.finish();
		}
	}

	public void Update_passowrd_back(View v) {
		this.finish();
	}


}
