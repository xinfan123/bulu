package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class UpdateVersionActivity extends Activity {

	public TextView waitUpdate;

	public TextView updatNow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_dialog);
		// 监听稍等更新
		waitUpdate = (TextView) findViewById(R.id.wait_update_tv);
		updatNow = (TextView) findViewById(R.id.versio_update_now);

		waitUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UpdateVersionActivity.this.finish();
			}
		});

		updatNow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UpdateVersionActivity.this.finish();
			}
		});

	}

	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

}
