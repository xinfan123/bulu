package com.xinfan.blueblue.activity.systemset;



import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.SystemSet;
import com.xinfan.blueblue.util.ToastUtil;

public class MessageNumSelectActivity extends Activity {
	private Spinner messagenumSr;

	private static String[] m = { "20条", "50条", "100条"};

	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_massage_num);

	
		messagenumSr = (Spinner) findViewById(R.id.message_select_type);

		 m = this.getResources().getStringArray(R.array.message_num_list);
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// 将adapter 添加到spinner中
		messagenumSr.setAdapter(adapter);
		messagenumSr.setPrompt(this.getResources().getString(R.string.message_num_tip));
	}

	public void SaveMessage(View v) {
		String typeStr = m[messagenumSr.getSelectedItemPosition()];
		SystemSet.instance.SetMessageNum(typeStr);
		ToastUtil.showMessage(this, "设置接收数量成功");
		this.finish();
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
