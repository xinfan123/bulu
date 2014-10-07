package com.xinfan.blueblue.activity.systemset;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.SystemSet;
import com.xinfan.blueblue.util.ToastUtil;

public class PaidSet extends Activity {
	private ListView messageSr;

	public ArrayList<SelectVo> list = new ArrayList<SelectVo>();

	private SystemSetAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_massage_num);

		messageSr = (ListView) findViewById(R.id.message_select_type);
		
		init();
		
		adapter = new SystemSetAdapter(this, list);
		// messagenumSr.setSelection(0, true);
		messageSr.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				SaveMessage(arg2);
			}

		});

		// 将adapter 添加到spinner中
		messageSr.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	public void init() {
		SelectVo one = new SelectVo("0", "全部");
		SelectVo two = new SelectVo("10", "10元以上");
		SelectVo three = new SelectVo("100", "100以上");
		SelectVo four = new SelectVo("1000", "1000以上");

		list.add(one);
		list.add(two);
		list.add(three);
		list.add(four);
	}

	public void SaveMessage(int index) {

		SelectVo type = list.get(index);
		SystemSet.instance.SetPaid(type.getText());
		ToastUtil.showMessage(this, "设置有偿额度成功");
		this.finish();

	}

	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
}
