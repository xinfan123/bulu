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

public class ReputationSet extends Activity {
	private Spinner messageSr;

	private static String[] m = { "全部", "一星", "二星","三星","4星","五星"};

	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_massage_num);

	
		messageSr = (Spinner) findViewById(R.id.message_select_type);

		 m = this.getResources().getStringArray(R.array.reputation_set_list);
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// 将adapter 添加到spinner中
			messageSr.setAdapter(adapter);
			messageSr.setPrompt(this.getResources().getString(R.string.reputation_Set_tip));
	}

	public void SaveMessage(View v) {
		String typeStr = m[messageSr.getSelectedItemPosition()];
		SystemSet.instance.SetReputation(typeStr);
		ToastUtil.showMessage(this, "设置信誉等级成功");
		this.finish();
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
