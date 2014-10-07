package com.xinfan.blueblue.activity.systemset;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;

public class SystemSetAdapter extends BaseAdapter {
	private List<SelectVo> list;
	private Context context;

	public SystemSetAdapter(Context context, List<SelectVo> list) {
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

		SelectVo vo = list.get(position);

		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			view = inflater.inflate(R.layout.systemset_item, null);
		}

		TextView tv = (TextView) view.findViewById(R.id.tv_list_item);
		tv.setText(vo.getText());
		tv.setTag(vo);

		return view;
	}

}
