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
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;

public class AreaSelectActivity extends Activity implements OnClickListener {

	public WriteMessageAreaAdapter areaAdapter;
	public View time_select_layout;

	public ArrayList<AreaListVo> areaList = new ArrayList<AreaListVo>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.write_message_time_popview_item);

		ListView listview = (ListView) findViewById(R.id.time_listView_select);
		
		setTitle();

		AreaListVo one = new AreaListVo("1", "附近");
		AreaListVo two = new AreaListVo("2", "本市");
		areaList.add(one);
		areaList.add(two);

		areaAdapter = new WriteMessageAreaAdapter(this, areaList);
		listview.setAdapter(areaAdapter);
		areaAdapter.notifyDataSetChanged();

		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				AreaListVo select = areaList.get(arg2);
				SendMessageActivity.instance.updateAreaSelect(select);
				AreaSelectActivity.this.finish();
			}

		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}
	
	public void setTitle(){
		TextView view = (TextView)this.findViewById(R.id.time_listview_title);
		view.setText("请选择消息的有效范围");
	}

	@Override
	public void onClick(View arg0) {

	}

}
