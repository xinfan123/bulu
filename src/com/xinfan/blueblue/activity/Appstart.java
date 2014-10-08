package com.xinfan.blueblue.activity;

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
				Intent intent = new Intent(Appstart.this, Viewpager.class);
				startActivity(intent);
				Appstart.this.finish();
			}
		}, 1000);
	}
}