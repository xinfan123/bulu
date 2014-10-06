package com.xinfan.blueblue.activity.send;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;

public class WriteMessageTimeAdapter extends BaseAdapter {
	private List<TimeListVo> list;
	private Context context;

	public WriteMessageTimeAdapter(Context context, List<TimeListVo> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {

		TimeListVo vo = list.get(position);

		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			view = inflater.inflate(R.layout.write_message_time_window_bg, null);
		}

		TextView tv = (TextView) view.findViewById(R.id.tv_list_item);
		tv.setText(vo.getText());
		tv.setTag(vo);

		return view;
	}

}
