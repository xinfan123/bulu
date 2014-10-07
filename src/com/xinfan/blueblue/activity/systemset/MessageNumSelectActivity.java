package com.xinfan.blueblue.activity.systemset;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.SystemSet;
import com.xinfan.blueblue.util.ToastUtil;

public class MessageNumSelectActivity extends Activity {
	private ListView messagenumSr;

	public ArrayList<SelectVo> list = new ArrayList<SelectVo>();

	private SystemSetAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_massage_num);

		messagenumSr = (ListView) findViewById(R.id.message_select_type);

		init();

		adapter = new SystemSetAdapter(this, list);

		messagenumSr.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
				SaveMessage(arg2);
			}

		});

		// 将adapter 添加到spinner中
		messagenumSr.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	public void init() {
		SelectVo one = new SelectVo("20", "20条");
		SelectVo two = new SelectVo("50", "50条");
		SelectVo three = new SelectVo("100", "100条");

		list.add(one);
		list.add(two);
		list.add(three);
	}

	public void SaveMessage(int arg2) {

		SelectVo select = list.get(arg2);
		SystemSet.instance.SetMessageNum(select.getText());
		ToastUtil.showMessage(this, "设置接收数量成功");
		this.finish();

	}

	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
