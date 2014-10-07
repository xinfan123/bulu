package com.xinfan.blueblue.activity.systemset;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.SystemSet;
import com.xinfan.blueblue.util.ToastUtil;

public class MessageNumSelectActivity extends Activity {
	private Spinner messagenumSr;

	private static String[] m = { "20条", "50条", "100条" };

	private ArrayAdapter<String> adapter;

	private boolean isValid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_massage_num);

		messagenumSr = (Spinner) findViewById(R.id.message_select_type);

		m = this.getResources().getStringArray(R.array.message_num_list);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		//messagenumSr.setSelection(0, true);
		messagenumSr.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View v, int arg2, long arg3) {
				SaveMessage(v);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		// 将adapter 添加到spinner中
		messagenumSr.setAdapter(adapter);
		messagenumSr.setPrompt(this.getResources().getString(R.string.message_num_tip));

	}

	public void SaveMessage(View v) {

		if (isValid) {
			String typeStr = m[messagenumSr.getSelectedItemPosition()];
			SystemSet.instance.SetMessageNum(typeStr);
			ToastUtil.showMessage(this, "设置接收数量成功");
			this.finish();
		}

		isValid = !isValid;

	}

	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
