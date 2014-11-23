package com.xinfan.blueblue.activity.send;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.base.BaseActivity;

public class TimeSelectActivity extends BaseActivity implements OnClickListener {

	public WriteMessageTimeAdapter timeAdapter;
	public View time_select_layout;

	public ArrayList<TimeListVo> timeList = new ArrayList<TimeListVo>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.write_message_time_popview_item);

		ListView listview = (ListView) findViewById(R.id.time_listView_select);
		timeList = new ArrayList<TimeListVo>();

		TimeListVo t1 = new TimeListVo("20", "20分钟");
		TimeListVo t2 = new TimeListVo("60", "60分钟");
		TimeListVo t3 = new TimeListVo("120", "2小时");
		TimeListVo t4 = new TimeListVo("360", "6小时");
		TimeListVo t5 = new TimeListVo("720", "12小时");
		TimeListVo t6 = new TimeListVo("1440", "24小时");
		
		timeList.add(t1);
		timeList.add(t2);
		timeList.add(t3);
		timeList.add(t4);
		timeList.add(t5);
		timeList.add(t6);

		timeAdapter = new WriteMessageTimeAdapter(this, timeList);
		listview.setAdapter(timeAdapter);
		timeAdapter.notifyDataSetChanged();

		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				TimeListVo select = timeList.get(arg2);
				WriteMessageActivity.instance.updateTimeSelect(select);
				TimeSelectActivity.this.finish();
			}

		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	@Override
	public void onClick(View arg0) {

	}

}
