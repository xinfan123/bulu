package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.igexin.sdk.PushManager;
import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.activity.context.AppContext;
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.activity.context.VersionContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.NetworkErrorCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.LoginParam;

public class AppstartActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.appstart);
		
		AppContext.init(this);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				SharePreferenceUtil util = new SharePreferenceUtil(AppstartActivity.this, Constants.USER_INFO);

				if (util.getisFirst()) {

					VersionContext.setVersion(AppstartActivity.this, Constants.DETAULT_VERSION);

					Intent intent = new Intent(AppstartActivity.this, ViewpagerActivity.class);
					startActivity(intent);
					AppstartActivity.this.finish();

				} else {

					boolean login = LoginUserContext.getIsLogin(AppstartActivity.this);
					if (login) {

						String mobile = LoginUserContext.getMobile(AppstartActivity.this);
						String enPasswd = LoginUserContext.getPassword(AppstartActivity.this);

						Request loginRequest = new Request(FunIdConstants.LOGIN);
						LoginParam param = new LoginParam();
						param.setMobile(mobile);
						param.setPasswd(enPasswd);
						loginRequest.setParam(param);
						loginRequest.setShowDialog(false);

						loginRequest.setNetworkErrorCallBack(new NetworkErrorCallBack() {

							@Override
							public void call(Request request) {
								Intent intent = new Intent(AppstartActivity.this, MainActivity.class);
								startActivity(intent);
								AppstartActivity.this.finish();
							}
						});

						AnsynHttpRequest.requestSimpleByPost(AppstartActivity.this, loginRequest, new RequestSucessCallBack() {
							public void call(Request data) {

								Intent intent = new Intent();
								intent.setClass(AppstartActivity.this, MainActivity.class);
								startActivity(intent);
								AppstartActivity.this.finish();
							}
						});
					} else {

						Intent intent = new Intent(AppstartActivity.this, WelcomeActivity.class);
						startActivity(intent);
						AppstartActivity.this.finish();
					}

				}
			}
		}, 1000);
	}

}