package com.xinfan.blueblue.activity.base;

import java.util.ArrayList;
import java.util.List;

import com.xinfan.blueblue.common.LoadingDialogFragment;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {

	public static List<BaseActivity> activitys = new ArrayList<BaseActivity>();

	public boolean showLoading = false;

	public LoadingDialogFragment ldf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		activitys.add(this);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		activitys.remove(this);
	}

	public void preLoading() {
		if (ldf == null) {
			ldf = LoadingDialogFragment.newInstance("正在加载当中...");
			ldf.open(this);
		}
	}

	public void afterLoading() {
		if (ldf != null) {
			ldf.dismiss();
			ldf = null;
		}
	}
}
