package com.xinfan.blueblue.activity.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.vo.ThemeVo;

public class ThemeSetAdapter extends BaseAdapter {
	private Context context;

	private ArrayList list = new ArrayList();

	public ThemeSetAdapter(Context context, ArrayList list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ThemeVo item = (ThemeVo) list.get(position);

		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.theme_content_item, parent, false);
			view.setTag(item);
		} else {
			item = (ThemeVo) view.getTag();
		}

		TextView tView = (TextView) view.findViewById(R.id.theme_item);
		tView.setText(item.getText());
		tView.setTag(item.getId());

		return view;
	}

}
