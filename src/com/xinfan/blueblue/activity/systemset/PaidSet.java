package com.xinfan.blueblue.activity.systemset;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.SystemSet;
import com.xinfan.blueblue.util.ToastUtil;

public class PaidSet extends Activity {
	private Spinner messageSr;

	private static String[] m = { "全部", "10元以上", "100以上", "1000以上" };

	private ArrayAdapter<String> adapter;

	private boolean isValid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_massage_num);

		messageSr = (Spinner) findViewById(R.id.message_select_type);

		m = this.getResources().getStringArray(R.array.paid_set_list);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// 将adapter 添加到spinner中
		messageSr.setAdapter(adapter);
		messageSr.setPrompt(this.getResources().getString(R.string.paid_Set_tip));

		// messagenumSr.setSelection(0, true);
		messageSr.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View v, int arg2, long arg3) {
				SaveMessage(v);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	}

	public void SaveMessage(View v) {

		if (isValid) {
			String typeStr = m[messageSr.getSelectedItemPosition()];
			SystemSet.instance.SetPaid(typeStr);
			ToastUtil.showMessage(this, "设置有偿额度成功");
			this.finish();
		}

		isValid = !isValid;

	}

	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
