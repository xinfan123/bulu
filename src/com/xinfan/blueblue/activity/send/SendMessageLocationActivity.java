package com.xinfan.blueblue.activity.send;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.location.GpsLocation.LocationListener;
import com.xinfan.blueblue.location.GpsLocationManager;
import com.xinfan.blueblue.location.LocationEntity;

public class SendMessageLocationActivity extends BaseActivity {

	public TextView error_notice_button1;

	public TextView error_notice_button2;

	public LinearLayout view1, view2;

	Handler handler = new Handler();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.message_gps_location);

		error_notice_button1 = (TextView) findViewById(R.id.error_notice_button1);
		error_notice_button2 = (TextView) findViewById(R.id.error_notice_button2);

		view1 = (LinearLayout) findViewById(R.id.loading_view1);
		view2 = (LinearLayout) findViewById(R.id.loading_view2);

		view2.setVisibility(View.GONE);

		error_notice_button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				view1.setVisibility(View.VISIBLE);
				view2.setVisibility(View.GONE);

				SendMessageLocationActivity.this.finish();
				SendMessageActivity.instance.location();
			}
		});

		error_notice_button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SendMessageLocationActivity.this.finish();
				SendMessageActivity.instance.finish();
			}
		});

		init();
	}

	public void init() {

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		GpsLocationManager.addLocation(getApplicationContext(), new LocationListener() {

			@Override
			public void onLocationSucess(LocationEntity uersLocation) {
				SendMessageActivity.instance.location = uersLocation;
				SendMessageLocationActivity.this.finish();

				/*
				 * handler.post(new Runnable() {
				 * 
				 * @Override public void run() { view1.setVisibility(View.GONE);
				 * view2.setVisibility(View.VISIBLE); } });
				 */
			}

			@Override
			public void onLocationError() {

				handler.post(new Runnable() {

					@Override
					public void run() {
						view1.setVisibility(View.GONE);
						view2.setVisibility(View.VISIBLE);
					}
				});
			}
		});

	}

}
