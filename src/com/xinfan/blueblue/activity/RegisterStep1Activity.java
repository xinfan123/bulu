package com.xinfan.blueblue.activity;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.xinfan.blueblue.activity.common.TimeButtonTicker2;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.ValidCodeParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;

public class RegisterStep1Activity extends Activity {
	private EditText mMobile; // 手机号码编辑框
	private Button sendBtn;// 获取验证码按钮
	private EditText mRanCode; // 验证码编辑框

	private static String mobile, rancode, sendBtnStr;

	private final Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

	private TimeButtonTicker2 ticker;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_verify);
		mMobile = (EditText) findViewById(R.id.regirst_verofy_mobile_edit);
		mRanCode = (EditText) findViewById(R.id.regirst_verofy_rancode_edit);
		sendBtn = (Button) findViewById(R.id.regirst_verofy_get_btn);

		sendBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendSMS(v);
			}
		});

		ticker = new TimeButtonTicker2(this, sendBtn);
	}

	// 发送信息

	public void sendSMS(View v) {
		mobile = mMobile.getText().toString();

		if (!((p.matcher(mobile)).matches())) {
			ToastUtil.showMessage(this, "手机号码为空或格式不正确！");
			return;
		}

		ticker.start();

		Request request = new Request(FunIdConstants.GET_USERREGISTER_VALIDCODE);
		ValidCodeParam param = new ValidCodeParam();
		param.setMobile(mobile);
		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(this, request, new ObserverCallBack() {

			public void call(Request data) {
				ToastUtil.showMessage(RegisterStep1Activity.this, "验证码发送成功，请查收短信!");
			}
		});

	}

	// 提交验证
	public void regirstVerofy(View v) {
		mobile = mMobile.getText().toString();
		rancode = mRanCode.getText().toString();
		sendBtnStr = sendBtn.getText().toString();

		if (!((p.matcher(mobile)).matches())) {// 判断手机号码格式
			ToastUtil.showMessage(RegisterStep1Activity.this, "手机号码为空或格式不正确！！");
			return;
		}

		if (sendBtnStr.equals("获取验证码")) {
			ToastUtil.showMessage(RegisterStep1Activity.this, "请先获取验证码");
			return;
		}
		if (rancode == null || rancode.length() <= 0) {
			ToastUtil.showMessage(RegisterStep1Activity.this, "验证码不能为空");
			return;

		}

		Request request = new Request(FunIdConstants.VALID_USER_REGISTER_VALIDCODE);
		ValidCodeParam param = new ValidCodeParam();
		param.setMobile(mobile);
		param.setValidCode(rancode);
		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(this, request, new ObserverCallBack() {

			public void call(Request data) {

				BaseResult result = (BaseResult) data.getResult();

					ToastUtil.showMessage(RegisterStep1Activity.this, result.getMsg());
					Intent intent = new Intent();

					Bundle bundle = new Bundle();
					bundle.putString("mobile", mobile);
					bundle.putString("rancode", rancode);
					intent.putExtras(bundle);

					intent.setClass(RegisterStep1Activity.this, RegisterStep2Activity.class);
					startActivity(intent);

					RegisterStep1Activity.this.finish();
			}
		});

	}

	public void regirst_verofy_back(View v) { // 返回
		this.finish();
	}

}