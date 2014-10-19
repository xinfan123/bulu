package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.activity.context.VersionContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.LoginParam;

public class Appstart extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.appstart);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				SharePreferenceUtil util = new SharePreferenceUtil(Appstart.this, Constants.USER_INFO);

				if (util.getisFirst()) {

					VersionContext.setVersion(Appstart.this, Constants.DETAULT_VERSION);

					Intent intent = new Intent(Appstart.this, Viewpager.class);
					startActivity(intent);
					Appstart.this.finish();

				} else {

					boolean login = LoginUserContext.getIsLogin(Appstart.this);
					if (login) {

						String mobile = LoginUserContext.getMobile(Appstart.this);
						String enPasswd = LoginUserContext.getPassword(Appstart.this);

						Request loginRequest = new Request(FunIdConstants.LOGIN);
						LoginParam param = new LoginParam();
						param.setMobile(mobile);
						param.setPasswd(enPasswd);
						loginRequest.setParam(param);
						loginRequest.setShowDialog(false);

						AnsynHttpRequest.requestSimpleByPost(Appstart.this, loginRequest, new ObserverCallBack() {
							public void call(Request data) {

								Intent intent = new Intent();
								intent.setClass(Appstart.this, MainActivity.class);
								startActivity(intent);
								Appstart.this.finish();
							}
						});
					} else {

						Intent intent = new Intent(Appstart.this, Welcome.class);
						startActivity(intent);
						Appstart.this.finish();
					}

				}
			}
		}, 1000);
	}

}