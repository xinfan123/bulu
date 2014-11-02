package com.xinfan.blueblue.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.location.GpsLocation;
import com.xinfan.blueblue.location.GpsLocationManager;
import com.xinfan.blueblue.location.GpsLocation.LocationListener;
import com.xinfan.blueblue.location.LocationEntity;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.RegisterParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;

public class RegisterStep2Activity extends BaseActivity implements OnClickListener {

	private EditText mPassword; // 密码编辑框
	private EditText mNickname; // 昵称编辑框
	private String mobile, password, nickname;

	public String randcode;

	LocationEntity location;

	@SuppressLint("HandlerLeak")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.register_step2);
		mPassword = (EditText) findViewById(R.id.register_passwd_edit);
		mNickname = (EditText) findViewById(R.id.register_nickname_edit);

		init();

		location();
	}

	public void init() {
		Bundle bundle = this.getIntent().getExtras();
		this.randcode = bundle.getString("randcode");
		this.mobile = bundle.getString("mobile");
	}

	public void location() {
		
		GpsLocationManager.addLocation(getApplicationContext(), new LocationListener() {

			@Override
			public void onLocationSucess(LocationEntity uersLocation) {
				ToastUtil.showMessage(RegisterStep2Activity.this, uersLocation.toString());

				location = uersLocation;
			}

			@Override
			public void onLocationError() {
				ToastUtil.showMessage(RegisterStep2Activity.this, "loation error");
			}
		});

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void register(View v) {
		password = mPassword.getText().toString();
		nickname = mNickname.getText().toString();

		if (nickname == null || nickname.length() <= 0) {// 判断昵称是否为空
			ToastUtil.showMessage(RegisterStep2Activity.this, "昵称不能为空！");
			return;
		}
		if (nickname.length() > 11) {
			ToastUtil.showMessage(RegisterStep2Activity.this, "昵称不能大于11位！");
			return;
		}

		if (password == null || password.length() <= 0) // 判断密码是否为空
		{
			ToastUtil.showMessage(RegisterStep2Activity.this, "密码不能为空！");
			return;
		}

		Request request = new Request(FunIdConstants.USER_REGISTER);
		RegisterParam param = new RegisterParam();
		param.setMobile(mobile);
		param.setPasswd(password);
		param.setUserName(nickname);
		param.setValidCode(randcode);

		if (location != null) {

			param.setRegEarea(location.getCity());
			param.setRegGpsx(String.valueOf(location.getLatitude()));
			param.setRegGpsy(String.valueOf(location.getLongitude()));
		} else {

			param.setRegEarea("长沙");
			param.setRegGpsx("0");
			param.setRegGpsy("0");
		}

		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {

			public void call(Request data) {

				BaseResult result = (BaseResult) data.getResult();

				ToastUtil.showMessage(RegisterStep2Activity.this, result.getMsg());

				SharePreferenceUtil util = new SharePreferenceUtil(RegisterStep2Activity.this, Constants.USER_INFO);
				util.setMobile(mobile);

				Intent intent = new Intent();
				intent.setClass(RegisterStep2Activity.this, WelcomeActivity.class);
				startActivity(intent);

				RegisterStep2Activity.this.finish();

			}
		});

	}

	public void register_back(View v) { // 返回
		this.finish();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}
}
