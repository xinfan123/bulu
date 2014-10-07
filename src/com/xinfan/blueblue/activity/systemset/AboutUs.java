package com.xinfan.blueblue.activity.systemset;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.xinfan.blueblue.activity.R;


public class AboutUs extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us);

	}

	public void AboutEsc(View v) {
		this.finish();
	}
}
