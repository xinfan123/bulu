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

public class SimilaritySet extends Activity {
	private Spinner messageSr;

	private static String[] m = { "一级", "二级", "三级","四级","五级"};

	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_massage_num);

	
		messageSr = (Spinner) findViewById(R.id.message_select_type);

		 m = this.getResources().getStringArray(R.array.similarity_set_list);
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// 将adapter 添加到spinner中
			messageSr.setAdapter(adapter);
			messageSr.setPrompt(this.getResources().getString(R.string.similarity_Set_tip));
	}

	public void SaveMessage(View v) {
		String typeStr = m[messageSr.getSelectedItemPosition()];
		SystemSet.instance.SetSimilarity(typeStr);
		ToastUtil.showMessage(this, "设置消息相似度成功");
		this.finish();
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
