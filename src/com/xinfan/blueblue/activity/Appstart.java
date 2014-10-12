package com.xinfan.blueblue.activity;

import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.SharePreferenceUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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