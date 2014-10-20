package com.xinfan.blueblue.activity;

import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xinfan.blueblue.activity.common.TimeButtonTicker2;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.blueblue.util.ValidationUtils;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.ChangePasswdBeforeLoginParam;
import com.xinfan.msgbox.http.service.vo.param.ValidCodeParam;

public class ForgetPasswordStep1 extends Activity {
	private EditText mMobile; // 手机号码编辑框
	private EditText mRanCode; // 验证码编辑框
	private static String mobile, rancode;

	private Button forget_get_btn;

	private TimeButtonTicker2 ticker;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forget_password_step1);
		mMobile = (EditText) findViewById(R.id.forget_mobile_edit);
		mRanCode = (EditText) findViewById(R.id.forget_rancode_edit);
		forget_get_btn = (Button) findViewById(R.id.forget_get_btn);

		ticker = new TimeButtonTicker2(ForgetPasswordStep1.this, forget_get_btn);
	}

	public void forgetPassword(View v) {
		mobile = mMobile.getText().toString();
		rancode = mRanCode.getText().toString();

		if (!ValidationUtils.isMobile(mobile)) {
			ToastUtil.showMessage(this, "手机号码为空或格式不正确！");
			return;
		}

		if (rancode == null || rancode.length() <= 0) {
			ToastUtil.showMessage(this, "验证码不能为空");
			return;
		}

		Request request = new Request(FunIdConstants.VALID_PWDCODE_BEFORE_LOGIN);

		ChangePasswdBeforeLoginParam param = new ChangePasswdBeforeLoginParam();
		param.setMobile(mobile);
		param.setValidCode(rancode);

		request.setParam(param);
		request.setShowDialog(false);

		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {
			public void call(Request data) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("mobile", mobile);
				bundle.putString("rancode", rancode);
				intent.putExtras(bundle);
				intent.setClass(ForgetPasswordStep1.this, ForgetPasswordStep2.class);
				startActivity(intent);
			}
		});

	}

	public void forget_back(View v) { // 返回
		this.finish();
	}

	// 发送信息

	public void sendCode(View v) {
		mobile = mMobile.getText().toString();

		if (!ValidationUtils.isMobile(mobile)) {
			ToastUtil.showMessage(this, "手机号码为空或格式不正确！");
			return;
		}

		Request request = new Request(FunIdConstants.GET_CHANGE_PASSWORD_VALID_CODE);

		ValidCodeParam param = new ValidCodeParam();
		param.setMobile(mobile);
		request.setParam(param);
		request.setShowDialog(false);

		ticker.start();

		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {
			public void call(Request data) {
				ToastUtil.showMessage(ForgetPasswordStep1.this, "验证码发送成功，请查收短信!");
			}
		});

	}

}