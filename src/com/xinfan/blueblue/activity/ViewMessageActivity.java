package com.xinfan.blueblue.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ViewMessageActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_message);
	}

	public void send_msg_back(View view) {
		finish();
	}

	@Override
	public void onClick(View arg0) {

	}

}