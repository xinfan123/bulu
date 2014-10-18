package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.xinfan.blueblue.activity.context.VersionContext;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.SharePreferenceUtil;

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
					Intent intent = new Intent(Appstart.this, Welcome.class);
					startActivity(intent);
					Appstart.this.finish();
				}
			}
		}, 1000);
	}

}