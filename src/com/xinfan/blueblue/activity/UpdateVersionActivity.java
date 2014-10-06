package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class UpdateVersionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_dialog);
		// 监听稍等更新
				TextView waitUpdate=(TextView) findViewById(R.id.wait_update_tv);
				waitUpdate.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.setClass(UpdateVersionActivity.this, SystemSet.class);
						startActivity(intent);
					}
				});
				
	}
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
	
}
