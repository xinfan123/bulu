package com.xinfan.blueblue.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.msgbox.http.service.vo.result.ClientVersionResult;

public class UpdateVersionActivity extends BaseActivity {

	public TextView waitUpdate;

	public TextView updatNow;

	public TextView version_mark_text;

	public TextView version_update_tip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_dialog);
		// 监听稍等更新
		waitUpdate = (TextView) findViewById(R.id.wait_update_tv);
		updatNow = (TextView) findViewById(R.id.versio_update_now);

		version_mark_text = (TextView) findViewById(R.id.version_mark_text);
		version_update_tip = (TextView) findViewById(R.id.version_update_tip);

		Bundle bundle = this.getIntent().getExtras();
		final ClientVersionResult vo = (ClientVersionResult) bundle.get("vo");

		StringBuffer tip = new StringBuffer();
		tip.append("最新版本：").append(vo.getCurrentVersion()).append(",是否更新?");
		version_mark_text.setText(vo.getIntroduceUrl());
		version_update_tip.setText(tip.toString());

		waitUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UpdateVersionActivity.this.finish();
			}
		});

		updatNow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse(vo.getFullDownloadUrl());

				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");

				startActivity(it);
				UpdateVersionActivity.this.finish();
			}
		});

	}

	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

}
