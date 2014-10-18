package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xinfan.blueblue.activity.R.id;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.DeviceUtils;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.AdviceParam;
import com.xinfan.msgbox.http.service.vo.result.LoginResult;

public class Complian extends Activity {
	private EditText mComplainText;// 文本编辑框
	private String complainText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.complain);
		mComplainText = (EditText) findViewById(id.complainText);
	}

	public void complain_send(View v) {

		complainText = mComplainText.getText().toString();
		if (complainText == null || complainText.equals("") || complainText.trim().length() < 5) {
			ToastUtil.showMessage(Complian.this, "请先编辑内容！");
			return;
		}

		if (complainText.length() > 500) {
			ToastUtil.showMessage(Complian.this, "字数过长，不能超过500个字符！");
			return;
		}

		Request request = new Request(FunIdConstants.SEND_ADVICE);
		AdviceParam param = new AdviceParam();
		param.setMobile(DeviceUtils.getPhoneNumber(this));
		param.setContent(complainText);
		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(this, request, new ObserverCallBack() {

			public void call(Request data) {
				ToastUtil.showMessage(Complian.this, data.getMessage());
				Complian.this.finish();
			}
		});
	}

	public void ComplianBack(View v) { // 返回
		this.finish();
	}

}
