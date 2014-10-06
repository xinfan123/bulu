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

public class TimeSelectActivity extends Activity implements OnClickListener {

	public WriteMessageTimeAdapter timeAdapter;
	public View time_select_layout;

	public ArrayList<TimeListVo> timeList = new ArrayList<TimeListVo>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.write_message_time_popview_item);

		ListView listview = (ListView) findViewById(R.id.time_listView_select);
		timeList = new ArrayList<TimeListVo>();

		TimeListVo one = new TimeListVo("1", "20分钟");
		TimeListVo two = new TimeListVo("2", "60分钟");
		timeList.add(one);
		timeList.add(two);

		timeAdapter = new WriteMessageTimeAdapter(this, timeList);
		listview.setAdapter(timeAdapter);
		timeAdapter.notifyDataSetChanged();

		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				TimeListVo select = timeList.get(arg2);
				SendMessageActivity.instance.updateTimeSelect(select);
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
