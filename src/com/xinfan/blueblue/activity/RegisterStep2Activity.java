package com.xinfan.blueblue.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.xinfan.blueblue.location.LocationEntity;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.RegisterParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;

public class RegisterStep2Activity extends Activity implements OnClickListener, TencentLocationListener {

	private EditText mPassword; // 密码编辑框
	private EditText mNickname; // 昵称编辑框
	private String mobile, password, nickname;

	private TencentLocationManager mLocationManager;
	private static LocationEntity userLocation;

	private Handler handlerlocation;

	public String randcode;

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
		// 获取位置信息
		startLocation();
		handlerlocation = new Handler() {
			public void handleMessage(Message msg) {
				LocationEntity message = (LocationEntity) msg.obj;// obj可以是任何类型
				userLocation = message;
				System.out.println(userLocation.getAddress());
			}
		};

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mLocationManager.removeUpdates(this);
	}

	public void register(View v) {
		password = mPassword.getText().toString();
		nickname = mNickname.getText().toString();

		if (nickname == null || nickname.length() <= 0) {// 判断昵称是否为空
			ToastUtil.showMessage(RegisterStep2Activity.this, "昵称不能为空！");
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
		param.setRegEarea(userLocation.getCity());
		param.setRegGpsx(userLocation.getLatitude().toString());
		param.setRegGpsy(userLocation.getLongitude().toString());
		request.setParam(param);

		AnsynHttpRequest.requestSimpleByPost(this, request, new RequestSucessCallBack() {

			public void call(Request data) {

				BaseResult result = (BaseResult) data.getResult();

				ToastUtil.showMessage(RegisterStep2Activity.this, result.getMsg());

				SharePreferenceUtil util = new SharePreferenceUtil(RegisterStep2Activity.this, Constants.USER_INFO);
				util.setMobile(mobile);

				Intent intent = new Intent();
				intent.setClass(RegisterStep2Activity.this, Login.class);
				startActivity(intent);

				RegisterStep2Activity.this.finish();

			}
		});

	}

	public void register_back(View v) { // 返回
		this.finish();
	}

	@Override
	public void onLocationChanged(TencentLocation location, int error, String reason) {
		LocationEntity uersLocation = new LocationEntity();
		if (error == TencentLocation.ERROR_OK) {

			uersLocation.setProvince(location.getProvince());
			uersLocation.setLatitude(location.getLatitude());
			uersLocation.setLongitude(location.getLongitude());
			uersLocation.setAddress(location.getAddress());
			uersLocation.setProvider(location.getProvider());
			uersLocation.setCity(location.getCity());
			uersLocation.setDistrict(location.getDistrict());
			uersLocation.setTown(location.getTown());
			uersLocation.setVillage(location.getVillage());
			uersLocation.setStreetNo(location.getStreetNo());
			Message message = Message.obtain();
			message.obj = uersLocation;
			handlerlocation.sendMessage(message);

			stopLocation();

		}
	}

	private void startLocation() {
		TencentLocationRequest request = TencentLocationRequest.create();
		mLocationManager = TencentLocationManager.getInstance(this);
		request.setInterval(5000).setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA);
		mLocationManager.requestLocationUpdates(request, this);

	}

	private void stopLocation() {
		mLocationManager.removeUpdates(this);
	}

	@Override
	public void onStatusUpdate(String arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}
}
