package com.xinfan.blueblue.activity.common;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;

public class TimeButtonTicker2 {

	private String rawText;

	private int time = 60;

	Handler hander = null;

	public TimeButtonTicker2(Context context, final Button button) {
		rawText = button.getText().toString();

		hander = new Handler(context.getMainLooper()) {

			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					button.setText("" + time + "秒");
					button.setClickable(false);
				} else if (msg.what == 2) {
					button.setClickable(true);
					button.setText(rawText);
				}
				super.handleMessage(msg);
			}
		};
	}

	public void start() {
		new Thread() {

			@Override
			public void run() {
				while (true) {

					time--;

					Message msg = new Message();

					msg.what = 1;
					msg.obj = time;

					hander.sendMessage(msg);

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if (time <= 0) {
						time = 60;

						Message msg2 = new Message();

						msg2.what = 2;
						msg2.obj = time;

						hander.sendMessage(msg2);

						break;
					}
				}
			}

		}.start();

	}

}
