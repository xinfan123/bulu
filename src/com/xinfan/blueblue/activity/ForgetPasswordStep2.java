package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.ChangePasswdBeforeLoginParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;

public class ForgetPasswordStep2 extends Activity {
	private EditText mNewPassword;
	private EditText mRePassword;
	private String newPassword;
	private String rePassword;

	private String mobile;

	private String randcode;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forget_password_step2);

		mNewPassword = (EditText) findViewById(R.id.new_password_edit);
		mRePassword = (EditText) findViewById(R.id.re_password_edit);

		Bundle bundle = this.getIntent().getExtras();
		mobile = bundle.getString("mobile");
		randcode = bundle.getString("rancode");

	}

	public void set_passowrd(View v) {
		newPassword = mNewPassword.getText().toString();
		rePassword = mRePassword.getText().toString();
		if (newPassword == null || newPassword.equals("")) {
			ToastUtil.showMessage(ForgetPasswordStep2.this, "密码不能为空！");
			return;
		}
		if (newPassword.length()<6||newPassword.length()>11) {
			ToastUtil.showMessage(ForgetPasswordStep2.this, "请输入6-11位有效数字");
			return;
		}
		if (!(newPassword.equals(rePassword))) {
			ToastUtil.showMessage(ForgetPasswordStep2.this, "两次密码不一致");
			return;
		}

		Request request = new Request(FunIdConstants.CHANGE_PASSWD_BEFORE_LOGIN);
		ChangePasswdBeforeLoginParam param = new ChangePasswdBeforeLoginParam();

		param.setMobile(mobile);
		param.setValidCode(randcode);
		param.setNewPasswd(newPassword);

		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {

			public void call(Request data) {

				BaseResult result = (BaseResult) data.getResult();
				ToastUtil.showMessage(ForgetPasswordStep2.this, result.getMsg());

				Intent intent = new Intent();
				intent.setClass(ForgetPasswordStep2.this, Login.class);
				startActivity(intent);

				ForgetPasswordStep2.this.finish();
			}
		});
	}

	public void Update_passowrd_back(View v) {
		this.finish();
	}

}
