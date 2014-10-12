package com.xinfan.blueblue.activity;

import java.util.Random;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xinfan.blueblue.activity.common.TimeButtonTicker2;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.ForgetPwdVerifCodeParam;

public class ForgetPassword extends Activity {
	private EditText mMobile; // 手机号码编辑框
	private EditText mRanCode; // 验证码编辑框
	private static String mobile, rancode;
	private String gencode;// 生成验证码

	private Handler handler;
	private final Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

	private Button forget_get_btn;
	
	private TimeButtonTicker2 ticker;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forget_password);
		mMobile = (EditText) findViewById(R.id.forget_mobile_edit);
		mRanCode = (EditText) findViewById(R.id.forget_rancode_edit);
		forget_get_btn = (Button) findViewById(R.id.forget_get_btn);

		gencode = genRanCode(4);
		handler = new Handler() {
			public void handleMessage(Message msg) {

				String message = (String) msg.obj;// obj可以是任何类型
				if (message.equals("发送成功")) {
					ToastUtil.showMessage(ForgetPassword.this, "发送成功！请注意查收！");
				} else {
					ToastUtil.showMessage(ForgetPassword.this, "发送失败！发生未知错误！");
				}
			}
		};
		
		ticker = new TimeButtonTicker2(ForgetPassword.this, forget_get_btn);
	}

	public Handler getHandler() {
		return this.handler;
	}

	public void forgetPassword(View v) {
		mobile = mMobile.getText().toString();
		rancode = mRanCode.getText().toString();
		if (!((p.matcher(mobile)).matches())) {// 判断手机号码格式
			new AlertDialog.Builder(ForgetPassword.this).setIcon(getResources().getDrawable(R.drawable.login_error_icon)).setMessage("手机号码为空或格式不正确！").create()
					.show();
		}

		else if (rancode == null || rancode.length() <= 0) {
			new AlertDialog.Builder(ForgetPassword.this).setIcon(getResources().getDrawable(R.drawable.login_error_icon)).setMessage("验证码不能为空！").create()
					.show();
		} else if (!(rancode.equals(gencode))) {
			new AlertDialog.Builder(ForgetPassword.this).setIcon(getResources().getDrawable(R.drawable.login_error_icon)).setMessage("验证码不正确！").create().show();
		} else {
			Intent intent = new Intent();
			intent.setClass(ForgetPassword.this, MainActivity.class);
			startActivity(intent);
			this.finish();
		}
	}

	public void forget_back(View v) { // 返回
		this.finish();
	}

	// 发送信息

	public void sendCode(View v) {
		mobile = mMobile.getText().toString();
		if (!((p.matcher(mobile)).matches())) {

			ToastUtil.showMessage(this, "手机号码为空或格式不正确！");
			return;
		}

		Request request = new Request(FunIdConstants.GET_CHANGE_PASSWORD_VALID_CODE);

		ForgetPwdVerifCodeParam param = new ForgetPwdVerifCodeParam();
		param.setMobile(mobile);
		request.setParam(param);
		request.setShowDialog(false);

		AnsynHttpRequest.requestSimpleByPost(this, request, new ObserverCallBack() {
			public void call(Request data) {
				ToastUtil.showMessage(ForgetPassword.this, "验证码发送成功，请查收短信!");
				ticker.start();
			}
		});

	}

	// 生成验证码
	public String genRanCode(int lenght) {
		String base = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < lenght; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

}